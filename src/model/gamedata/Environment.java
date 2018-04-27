package model.gamedata;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import frontend.model.operation.control.ControlType;
import javafx.geometry.Point2D;
import model.gamedata.game.control.ControlProperty;
import model.gamedata.game.gamestats.GameStats;
import model.gamedata.game.gamestats.ScheduleRisk;
import model.gamedata.game.log.ActionEntry;
import model.gamedata.game.log.Log;
import model.gamedata.user.UserStats;
import model.status.StatusManager;

/**
 * Responsible for loading XML data into the game, regarding obstacles,
 * initial position and final destination.
 * @author Feng
 *
 */
public class Environment {

	private StatusManager manager;
	private GameStats gameStats;
	private UserStats userStats;
	private Log log;

	public Environment() {
		manager = new StatusManager();
		gameStats = new GameStats();
		userStats = new UserStats();
	}
	
	public GameStats getGameStats() {
		return gameStats;
	}
	
	public UserStats getUserStats() {
		return userStats;
	}
	
	public void setUID(int id) {
		userStats.setId(id);
		log = new Log(userStats.getId());
	}
	
	public StatusManager getStatusManager() {
		return manager;
	}
	
	public void setPlanning(boolean planning) {
		manager.setPlanning(planning);
	}
	
	public void setPlannedPath(List<Point2D> plannedPath) {
		manager.setFeasible(plannedPath.size() != 0);
		List<Point2D> prevPlannedPath = gameStats.getPlannedPath();
		gameStats.setPrevPlannedPath(prevPlannedPath);
		gameStats.setPlannedPath(plannedPath);	
		
		if(plannedPath.size() != 0)
			calculateScheduleRisk(plannedPath.get(plannedPath.size() - 1), gameStats.getCurrentSurfacingBudget()-1);
	}
	
	public void setExecutedPath(List<Point2D> executedPath) {
	    this.setPlannedPath(new ArrayList<>());
		gameStats.setLastStepPlannedPath(gameStats.getPlannedPath());
		gameStats.setPrevPlannedPath(new ArrayList<>());
		gameStats.addToCompletePath(executedPath);
		gameStats.setCurrentPosition(executedPath.get(executedPath.size()-1));
	    gameStats.setExecutedPath(executedPath);
	    
	    gameStats.setCurrentRiskBudget(gameStats.getExpectedRiskBudget());
		gameStats.setCurrentSurfacingBudget(
				gameStats.getCurrentSurfacingBudget() - 1);
		if(executedPath != null && executedPath.size() != 0)
			calculateScheduleRisk(executedPath.get(executedPath.size() - 1), gameStats.getCurrentSurfacingBudget());
	}
	
	public void log(ControlProperty controlProperty) {
		ActionEntry action = new ActionEntry();
		action.setUid(userStats.getId());
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
	    Date date = new Date();  
	    action.setDate(formatter.format(date));
	    Point2D currPos = gameStats.getCurrentPosition();
	    action.setCurrPosition(new double[]{currPos.getX(), currPos.getY()});
	    double[] plannedPath = new double[gameStats.getPlannedPath().size()*2];
	    for (int i=0; i < gameStats.getPlannedPath().size(); i++) {
	    	plannedPath[2*i] = gameStats.getPlannedPath().get(i).getX();
	    	plannedPath[2*i+1] = gameStats.getPlannedPath().get(i).getY();
	    }
	    action.setPlannedPath(plannedPath);
	    double[] executedPath = new double[gameStats.getPlannedPath().size()*2];
	    for (int i=0; i < gameStats.getPlannedPath().size(); i++) {
	    	executedPath[2*i] = gameStats.getPlannedPath().get(i).getX();
	    	executedPath[2*i+1] = gameStats.getPlannedPath().get(i).getY();
	    }
	    action.setExecutedPath(executedPath);
	    action.setPlannedPathLength(pathDistance(gameStats.getPlannedPath()));
	    action.setExecutedPathLength(pathDistance(gameStats.getExecutedPath()));
	    action.setScheduleRisk(gameStats.getCurrentScheduleRisk());
	    action.setCollisionRisk(controlProperty.getControlValue(ControlType.ChanceConstraint));
	    action.setRemainingRisk(gameStats.getCurrentRiskBudget());
	    action.setRiskLevel(controlProperty.getControlValue(ControlType.ChanceConstraint));
	    action.setNumMidpoints((int)controlProperty.getControlValue(ControlType.WayPoints));
	    action.setSurfacingLeft(gameStats.getCurrentSurfacingBudget());
	    log.writeAction(action);
	}
	
	public void flushOutput() {
		log.flushOutput();
	}
	
	private double pathDistance(List<Point2D> path) {
		if(path.size() <= 1)
			return 0;
		double distance = 0;
		Point2D prev = path.get(0);
		for (int i = 1; i < path.size(); i++) {
			Point2D point = path.get(i);
			distance += Math.sqrt(Math.pow(point.getX()-prev.getX(), 2)+Math.pow(point.getY()-prev.getY(), 2));
			prev = point;
		}
		return distance;
	}
	
	private void calculateScheduleRisk(Point2D last, int surfacingBudget) {
		ScheduleRisk riskCalculator = new ScheduleRisk();
		double straightDistance = (last.getX() - 1) * (last.getX() - 1)
                                + (last.getY() - 1) * (last.getY() - 1);
		straightDistance = Math.sqrt(straightDistance);
		double scheduleRisk = riskCalculator.getScheduleRisk(straightDistance, surfacingBudget);
		gameStats.setCurrentScheduleRisk(scheduleRisk);
//		System.out.println("Environment line 79. ScheduledRisk: " + scheduleRisk);
	}
	
}
