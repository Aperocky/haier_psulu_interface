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

	public Planner() {

	}

	/**
	 * Called whenever the user changes risk budget in ControlPanel
	 * @param riskBudget
	 */
	public void setRiskBudget(double riskBudget) {

	}

	/**
	 * Called whenver user changes receding horizon radius in ControlPanel
	 * @param horizonRadius
	 */
	public void setRecedingHorizon(double horizonRadius) {

	}

	@Override
	public List<Point2D> getPlannedPath(Point2D start, Point2D end, List<List<Point2D>> obstacles) {
		return new ArrayList<>();
	}

}
