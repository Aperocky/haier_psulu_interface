package model.plan;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Point2D;

/**
 * Path planner that employs one of the algorithms(p-sulu or Dijkstra) for path
 * finding. At each turn Planner plans the path based on the current game status
 * and user's inputs from the control panel. It then outputs the planned path to
 * Game which will modify the Environment accordingly. (Possibly employ Strategy
 * and/or Builder design pattern)
 * 
 * @author Feng
 *
 */
public class Planner implements IPlanner {
	private double chanceConstraint;
	private double maxVelocity;
	private double wayPoints;

	public Planner() {

	}

	/**
	 * Called whenever the user changes chance constraint in ControlPanel
	 * 
	 * @param riskBudget
	 */
	public void setChanceConstraint(double constraint) {
		this.chanceConstraint = constraint;
	}

	/**
	 * Called whenver user changes maximum velocity in ControlPanel
	 * 
	 * @param horizonRadius
	 */
	public void setMaxVelocity(double maxVelocity) {
		this.maxVelocity = maxVelocity;
	}

	public void setWayPoints(int wayPoints) {
		this.wayPoints = wayPoints;
	}

	@Override
	public List<Point2D> getPlannedPath(Point2D start, Point2D end, List<List<Point2D>> obstacles) {
		ArrayList<Point2D> result = new ArrayList<>();
		result.add(new Point2D(20, 20));
		result.add(new Point2D(100, 100));
		return result;
	}

}
