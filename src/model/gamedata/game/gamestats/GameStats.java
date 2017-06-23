package model.gamedata.game.gamestats;

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

	private BudgetStats budgetStats;
	private PathStats pathStats;

	public GameStats() {
		super();
		budgetStats = new BudgetStats();
		pathStats = new PathStats();
	}

	public BudgetStats getBudgetStats() {
		return budgetStats;
	}

	public PathStats getPathStats() {
		return pathStats;
	}

	public void setObstacles(List<List<Point2D>> obstacles) {
		pathStats.setObstacles(obstacles);
		notifyObservers(this);
	}

	public Point2D getStartPosition() {
		return pathStats.getStartPosition();
	}

	public void setStartPosition(Point2D startPosition) {
		pathStats.setStartPosition(startPosition);
		notifyObservers(this);
	}

	public void resetStartPosition() {
		pathStats.setCurrentPosition(pathStats.getStartPosition());
		notifyObservers(this);
	}

	public Point2D getCurrentPosition() {
		return pathStats.getCurrentPosition();
	}

	public Point2D getFinalDestination() {
		return pathStats.getFinalDestination();
	}

	public void setCurrentPosition(Point2D current) {
		pathStats.setCurrentPosition(current);
		notifyObservers(this);
	}

	public Point2D getDestination() {
		return pathStats.getDestination();
	}

	public void setDestination(Point2D destination) {
		pathStats.setDestination(destination);
	}

	public List<List<Point2D>> getObstacles() {
		return pathStats.getObstacles();
	}

	public void setPlannedPath(List<Point2D> plan) {
		pathStats.setPlannedPath(plan);
		notifyObservers(this);
	}

	public void setExecutedPath(List<Point2D> executed) {
		pathStats.setExecutedPath(executed);
		notifyObservers(this);
	}

	public List<Point2D> getPlannedPath() {
		return pathStats.getPlannedPath();
	}

	public List<Point2D> getExecutedPath() {
		return pathStats.getExecutedPath();
	}

	public double getTotalRiskBudget() {
		return budgetStats.getTotalRiskBudget();
	}

	public void setTotalRiskBudget(double totalRiskBudget) {
		budgetStats.setTotalRiskBudget(totalRiskBudget);
		notifyObservers(this);
	}

	public void resetCurrentRiskBudget() {
		budgetStats.resetCurrentRiskBudget();
		notifyObservers(this);
	}

	public void setCurrentRiskBudget(double riskBudget) {
		budgetStats.setCurrentRiskBudget(riskBudget);
		notifyObservers(this);
	}

	public double getCurrentRiskBudget() {
		return budgetStats.getCurrentRiskBudget();
	}

	public double getCurrentSurfacingBudget() {
		return budgetStats.getCurrentSurfacingBudget();
	}
	
	public double getExpectedRiskBudget() {
		return budgetStats.getExpectedRiskBudget();
	}

	public void setExpectedRiskBudget(double expectedRiskBudget) {
		budgetStats.setExpectedRiskBudget(expectedRiskBudget);
		notifyObservers(this);
	}

	@Override
	public void update(ObstacleMaster obstacleMaster) {
		pathStats.getObstacles().clear();
		obstacleMaster.forEach(obstacle -> {
			pathStats.getObstacles().add(obstacle.getVertices());
		});
	}

}
