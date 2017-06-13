package model.gamedata.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import frontend.model.canvas.layers.concrete.obstaclelayer.ObstacleMaster;
import javafx.geometry.Point2D;
import util.ObservableBase;
import util.Observer;

/**
 * GameStats is observed by Front end in order to properly update the value of
 * risk budget, surfacing budget, current position and planned paths.
 * 
 * @author Feng
 *
 */
public class GameStats extends ObservableBase<GameStats> implements Observer<ObstacleMaster> {

	private List<Point2D> plannedPath;
	private List<Point2D> executedPath;
	private List<List<Point2D>> obstacles;
	private Point2D currentPosition;
	private Point2D finalDestination;
	private double currentRiskBudget;
	private double currentSurfacingBudget;
	private boolean executing;

	public GameStats() {
		super();
		plannedPath = new ArrayList<>();
		executedPath = new ArrayList<>();
		obstacles = new ArrayList<>();
	}

	public boolean isExecuting() {
		return executing;
	}

	public Point2D getCurrentPosition() {
		return currentPosition;
	}

	public Point2D getFinalDestination() {
		return finalDestination;
	}

	public void setCurrentPosition(Point2D current) {
		// System.out.println("Set Current Starting Position: " + current.getX()
		// + " , " + current.getY());
		currentPosition = current;
	}

	public Point2D getDestination() {
		return finalDestination;
	}

	public void setDestination(Point2D destination) {
		// System.out.println("Set Destination: " + destination.getX() + " , " +
		// destination.getY());
		finalDestination = destination;
	}

	public double getCurrentRiskBudget() {
		return currentRiskBudget;
	}

	public double getCurrentSurfacingBudget() {
		return currentSurfacingBudget;
	}

	public List<List<Point2D>> getObstacles() {
		return obstacles;
	}

	public void setPlannedPath(List<Point2D> plan) {
		executing = false;
		plannedPath = plan;
		notifyObservers(this);
	}

	public void setExecutedPath(List<Point2D> executed) {
		executing = true;
		executedPath = executed;
		notifyObservers(this);
	}

	public List<Point2D> getPlannedPath() {
		return Collections.unmodifiableList(plannedPath);
	}

	public List<Point2D> getExecutedPath() {
		return Collections.unmodifiableList(executedPath);
	}

	@Override
	public void update(ObstacleMaster obstacleMaster) {
		obstacles.clear();
		obstacleMaster.forEach(obstacle -> {
			obstacles.add(obstacle.getVertices());
		});
	}

}
