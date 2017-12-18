package model.general;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import frontend.model.operation.control.ControlType;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.geometry.Point2D;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;
import model.execute.CollisionDetector;
import model.execute.Executor;
import model.gamedata.Environment;
import model.gamedata.game.control.ControlProperty;
import model.gamedata.game.gamestats.GameStats;
import model.gamedata.game.obstacles.ObstacleFactory;
import model.gamedata.game.param.ParamIO;
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
	private CollisionDetector detector;

	public Game() {
		environment = new Environment();
		planner = new Planner();
		executor = new Executor();
		detector = new CollisionDetector();

		initialize();
	}

	public void setControlProperty(ControlProperty control) {
		controlProperty = control;
		controlProperty.setOnChanged(() -> {
			environment.setPlanning(true);
			double expectedRiskBudget = environment.getGameStats().getCurrentRiskBudget()
					- controlProperty.getControlValue(ControlType.ChanceConstraint);
			environment.getGameStats().setExpectedRiskBudget(expectedRiskBudget);
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
	 */
	public void plan() {
		try {
			environment.getStatusManager().setMessageOn();
			planner.plan(plannedPath -> {
				environment.setPlannedPath(plannedPath);
				environment.setPlanning(false);
				environment.getStatusManager().setMessageOff();
			});
			
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Load the executed path from pSulu and update the game status
	 */
	public void execute() {
		try {
			executor.execute(executedPath -> {
				environment.setExecutedPath(executedPath);
				GameStats gameStats = environment.getGameStats();
				gameStats.addToCompletePath(executedPath);
				gameStats.setCurrentPosition(executedPath.get(executedPath.size()-1));
				// Check for success and failure
				checkFailure(executedPath.get(executedPath.size() - 1));
				checkSuccess(executedPath.get(executedPath.size() - 1));
			});
			environment.setPlannedPath(new ArrayList<>());
			GameStats gameStats = environment.getGameStats();
			gameStats.setLastStepPlannedPath(gameStats.getPlannedPath());
			gameStats.setPrevPlannedPath(new ArrayList<>());
			gameStats.setCurrentRiskBudget(gameStats.getExpectedRiskBudget());
			gameStats.setCurrentSurfacingBudget(
					gameStats.getCurrentSurfacingBudget() - 1);
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Execute next step of the path based on the pre-calculated execution path
	 * OR path calculated on the fly
	 */
	public void executeStep() {
		
	}

	private void initialize() {
		GameStats gameStats = environment.getGameStats();
		gameStats.setObstacles((new ObstacleFactory().loadObstacles()));
		ParamIO io = new ParamIO();
		Map<String, Object> params = (Map<String, Object>) io.loadOriginal();
		ArrayList<String> start = (ArrayList<String>) params.get("start_location");
		ArrayList<String> end = (ArrayList<String>) params.get("end_location");
		Point2D startP = new Point2D(Double.valueOf(start.get(0)), Double.valueOf(start.get(1)));
		Point2D endP = new Point2D(Double.valueOf(end.get(0)), Double.valueOf(end.get(1)));
		gameStats.setStartPosition(startP);
		gameStats.setDestination(endP);

		// Set the real total risk budget, not the UI risk budget
		gameStats.setTotalRiskBudget(0.8d); // TODO: hardcoded risk budget 2d
		gameStats.setCurrentSurfacingBudget(6);
	}
	
	private void checkFailure(Point2D lastStep) {
		List<List<Point2D>> obstacles = environment.getGameStats().getPathStats().getObstacles();
		obstacles.forEach(obstacle -> {
			double delta = 0.01d;
			Point2D lastNW = new Point2D(lastStep.getX()-delta, lastStep.getY()+delta);
			Point2D lastNE = new Point2D(lastStep.getX()+delta, lastStep.getY()+delta);
			Point2D lastSW = new Point2D(lastStep.getX()-delta, lastStep.getY()-delta);
			Point2D lastSE = new Point2D(lastStep.getX()+delta, lastStep.getY()-delta);
			List<Point2D> detects = Arrays.asList(lastNW, lastNE, lastSW, lastSE, lastStep);
			for(Point2D detect : detects) {
				if(detector.collide(detect, obstacle)) {
					environment.getStatusManager().setFailure(true);
				}
			}
		});
	}

	private void checkSuccess(Point2D lastStep) {
		Point2D destination = environment.getGameStats().getDestination();
		if (Math.abs(lastStep.getX() - destination.getX()) <= 1d/36d
				&& Math.abs(lastStep.getY() - destination.getY()) <= 1d/36d) {
			environment.getStatusManager().setSuccess(true);
		}
	}

}
