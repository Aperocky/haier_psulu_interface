package model.plan;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Point2D;
import model.gamedata.game.ControlProperty;

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
	private ControlProperty control;

	public Planner() {
		control = new ControlProperty();
	}

	/**
	 * Called whenever the user changes chance constraint in ControlPanel
	 * @param riskBudget
	 */
	public void setChanceConstraint(double constraint) {
		control.setChanceConstraint(constraint);
	}

	/**
	 * Called whenver user changes maximum velocity in ControlPanel
	 * @param horizonRadius
	 */
	public void setMaxVelocity(double maxVelocity) {
		control.setMaxVelocity(maxVelocity);
	}
	
	public void setWayPoints(int waypoints) {
		control.setWayPoints(waypoints);
	} 

	@Override
	public List<Point2D> getPlannedPath(Point2D start, Point2D end, List<List<Point2D>> obstacles) {
		return new ArrayList<>();
	}

}
