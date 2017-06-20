package model.gamedata.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import frontend.model.canvas.layers.concrete.obstaclelayer.ObstacleMaster;
import javafx.geometry.Point2D;
import model.gamedata.game.param.ParamIO;
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

	private ParamIO paramIO;
	private List<Point2D> plannedPath;
	private List<Point2D> executedPath;
	private List<List<Point2D>> obstacles;
	private Point2D currentPosition;
	private Point2D finalDestination;
	private double currentRiskBudget;
	private double currentSurfacingBudget;

	public GameStats() {
		super();
		paramIO = new ParamIO();
		plannedPath = new ArrayList<>();
		executedPath = new ArrayList<>();
		obstacles = new ArrayList<>();
	}

	public void setObstacles(List<List<Point2D>> obstacles) {
		this.obstacles = obstacles;
		notifyObservers(this);
	}

	public Point2D getCurrentPosition() {
		return currentPosition;
	}

	public Point2D getFinalDestination() {
		return finalDestination;
	}

	public void setCurrentPosition(Point2D current) {
		//System.out.println("Set Current Starting Position: " + current.getX() + " , " + current.getY());
		currentPosition = current;
		Map<String, Object> raw = paramIO.loadTemp();
		if (raw == null)
			raw = paramIO.loadOriginal();
		List<Double> start = Arrays.asList((double) current.getX(), (double) current.getY(), 0d, 0d);
		raw.put("start_location", start);
		paramIO.writeTemp(raw);
		notifyObservers(this);
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
		plannedPath = plan;
		notifyObservers(this);
	}

	public void setExecutedPath(List<Point2D> executed) {
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
