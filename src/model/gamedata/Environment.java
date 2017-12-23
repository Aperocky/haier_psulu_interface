package model.gamedata;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Point2D;
import model.gamedata.game.gamestats.GameStats;
import model.gamedata.game.gamestats.ScheduleRisk;
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
	
	private void calculateScheduleRisk(Point2D last, int surfacingBudget) {
		ScheduleRisk riskCalculator = new ScheduleRisk();
		double straightDistance = (last.getX() - 1) * (last.getX() - 1)
                                + (last.getY() - 1) * (last.getY() - 1);
		straightDistance = Math.sqrt(straightDistance);
		double scheduleRisk = riskCalculator.getScheduleRisk(straightDistance, surfacingBudget);
		gameStats.setCurrentScheduleRisk(scheduleRisk);
		System.out.println("Environment line 79. ScheduledRisk: " + scheduleRisk);
	}
	
}
