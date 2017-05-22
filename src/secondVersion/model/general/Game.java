package secondVersion.model.general;

import java.util.List;

import javafx.beans.property.DoubleProperty;
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
	
	/**
	 * Store a pre-calculated execution time path
	 */
	public void execute() {
		List<Point2D> plannedPath = environment.getGameStats().getPlannedPath();
		if(plannedPath.isEmpty())
			return;
		List<Point2D> executedPath = executor.getExecutedPath(plannedPath);
		environment.getGameStats().setExecutedPath(executedPath);
	}
	
	/**
	 * Execute next step of the path based on the pre-calculated execution path OR path calculated on the fly
	 */
	public void executeStep() {
		
	}
	
	/**
	 * Observe the risk budget property set in the ControlPanel by user
	 * @param riskProperty risk budget property to be observed
	 */
	public void observeRiskBudgetProperty(DoubleProperty riskProperty) {
		riskProperty.addListener((observable, oldv, newv) -> {
			planner.setRiskBudget(newv.doubleValue());
		});

	}

	/**
	 * Observe the receding horizon property set in the ControlPanel by user
	 * @param horizonProperty receding horizon property to be observed
	 */
	public void observeRecedingHorizonProperty(DoubleProperty horizonProperty) {
		horizonProperty.addListener((observable, oldv, newv) -> {
			planner.setRecedingHorizon(newv.doubleValue());
		});
	}
	
	

}
