package model.gamedata.game.gamestats;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javafx.geometry.Point2D;
import model.gamedata.game.param.ParamIO;

public class PathStats {
	
	private ParamIO paramIO;
	private List<Point2D> plannedPath;
	private List<Point2D> prevPlannedPath;
	private List<Point2D> executedPath;
	// All historical executed paths to current position
	private List<Point2D> completePath;
	// Planned path for the previous step
	private List<Point2D> lastStepPlannedPath;
	private List<List<Point2D>> obstacles;
	private Point2D startPosition;
	private Point2D currentPosition;
	private Point2D finalDestination;
	private Point2D plannedPosition;
	
	public PathStats() {
		paramIO = new ParamIO();
		plannedPath = new ArrayList<>();
		executedPath = new ArrayList<>();
		completePath = new ArrayList<>();
		obstacles = new ArrayList<>();
	}
	
	public void setObstacles(List<List<Point2D>> obstacles) {
		this.obstacles = obstacles;
	}
	
	public Point2D getStartPosition() {
		return startPosition;
	}
	
	public Point2D getPlannedPosition() {
		return plannedPosition;
	}

	public void setPlannedPosition(Point2D plannedPosition) {
		this.plannedPosition = plannedPosition;
	}

	public void setStartPosition(Point2D startPosition) {
		this.startPosition = startPosition;
		this.currentPosition = startPosition;
	}

	public void resetStartPosition() {
		setCurrentPosition(this.startPosition);
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
		Map<String, Object> raw = paramIO.loadTemp();
		if (raw == null)
			raw = paramIO.loadOriginal();
		List<Double> start = Arrays.asList((double) current.getX(), (double) current.getY(), 0d, 0d);
		raw.put("start_location", start);
		paramIO.writeTemp(raw);
	}

	public Point2D getDestination() {
		return finalDestination;
	}

	public void setDestination(Point2D destination) {
		// System.out.println("Set Destination: " + destination.getX() + " , " +
		// destination.getY());
		finalDestination = destination;
	}
	
	public List<List<Point2D>> getObstacles() {
		return obstacles;
	}

	public void setPlannedPath(List<Point2D> plan) {
		plannedPath = plan;
	}

	public void setExecutedPath(List<Point2D> executed) {
		executedPath = executed;
	}
	
	public List<Point2D> getPrevPlannedPath() {
		return prevPlannedPath;
	}

	public void setPrevPlannedPath(List<Point2D> prevPlannedPath) {
		this.prevPlannedPath = prevPlannedPath;
	}

	public List<Point2D> getLastStepPlannedPath() {
		return lastStepPlannedPath;
	}

	public void setLastStepPlannedPath(List<Point2D> lastStepPlannedPath) {
		this.lastStepPlannedPath = lastStepPlannedPath;
	}

	public List<Point2D> getPlannedPath() {
		return Collections.unmodifiableList(plannedPath);
	}

	public List<Point2D> getExecutedPath() {
		return Collections.unmodifiableList(executedPath);
	}
	
	public List<Point2D> getCompletePath() {
		return Collections.unmodifiableList(completePath);
	}
	
	public void addToCompletePath(List<Point2D> currentExecutedPath) {
		currentExecutedPath.forEach(point -> completePath.add(point));
	}

}
