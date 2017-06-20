package model.general;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Point2D;
import model.execute.Executor;
import model.gamedata.Environment;
import model.gamedata.game.control.ControlProperty;
import model.plan.Planner;

/**
 * Controls the game flow and is responsible for properly updating the game
 * stats in environment. Game also holds a Timeline that will help update front
 * end view in certain time step during the execution.
 * 
 * @author Feng
 *
 */
public class Game {

	private ControlProperty controlProperty;
	private Environment environment;
	private Planner planner;
	private Executor executor;

	public Game() {
		environment = new Environment();
		planner = new Planner();
		executor = new Executor();
	}

	public void setControlProperty(ControlProperty control) {
		controlProperty = control;
		controlProperty.setOnChanged(() -> {
			environment.setPlanning(true);
			plan();
		});
	}

	public Environment getEnvironment() {
		return environment;
	}

	/**
	 * Plan and store the planned path into environment.Note that the planned
	 * path here is not adapted to the canvas size yet
	 * 
	 * @param riskBudget
	 * @param horizonRadius
	 */
	public void plan() {
		try {
			planner.plan(plannedPath -> {
				environment.setPlannedPath(plannedPath);
				environment.setPlanning(false);
			});
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Store a pre-calculated execution time path
	 */
	public void execute() {
		List<Point2D> plannedPath = environment.getGameStats().getPlannedPath();
		environment.getGameStats().setPlannedPath(new ArrayList<>());
		List<Point2D> executedPath = executor.getExecutedPath(plannedPath);
		environment.setExecutedPath(executedPath);
		Point2D lastStep = executedPath.get(executedPath.size() - 1);
		environment.getGameStats().setCurrentPosition(lastStep);
		checkSuccess(lastStep);
	}

	/**
	 * Execute next step of the path based on the pre-calculated execution path
	 * OR path calculated on the fly
	 */
	public void executeStep() {

	}

	private void checkSuccess(Point2D lastStep) {
		Point2D destination = environment.getGameStats().getDestination();
		if (Math.abs(lastStep.getX() - destination.getX()) <= 0.001d
				&& Math.abs(lastStep.getY() - destination.getY()) <= 0.001d) {
			environment.getStatusManager().setSuccess();
		}
	}

}
