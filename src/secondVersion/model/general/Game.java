package secondVersion.model.general;

import java.util.List;

import javafx.geometry.Point2D;
import secondVersion.model.execute.Executor;
import secondVersion.model.gamedata.Environment;
import secondVersion.model.plan.Planner;

/**
 * Controls the game flow and is responsible for properly updating the game
 * stats in environment. Game also holds a Timeline that will help update front
 * end view in certain time step during the execution.
 * 
 * @author Feng
 *
 */
public class Game {

	private Environment environment;
	private Planner planner;
	private Executor executor;
	// TODO: a Timeline class that helps with counting elapsed time step
	
	/**
	 * Given the user input of riskBudget limitation and receding horizon radius(step size),
	 * plan and store the planned path into environment. 
	 * @param riskBudget
	 * @param horizonRadius
	 */
	public void plan(double riskBudget, double horizonRadius) {
		planner.setRecedingHorizon(horizonRadius);
		planner.setRiskBudget(riskBudget);
		List<Point2D> plannedPath = planner.getPlannedPath(environment.getGameStats().getCurrentPosition(), 
				environment.getGameStats().getDestination(), environment.getGameStats().getObstacles());
		environment.getGameStats().setPlannedPath(plannedPath);
	}
	
	public void execute() {
		List<Point2D> plannedPath = environment.getGameStats().getPlannedPath();
		if(plannedPath.isEmpty())
			return;
		List<Point2D> executedPath = executor.getExecutedPath(plannedPath);
		environment.getGameStats().setExecutedPath(executedPath);
	}
	
	

}
