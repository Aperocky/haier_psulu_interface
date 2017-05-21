package secondVersion.model.plan.algorithm;

import java.util.List;

import javafx.geometry.Point2D;

/**
 * p-sulu implementation of receding-horizon path finding.
 * Calculates the shortest path between start and end point in
 * continuous space using the idea of receding horizon. 
 * 
 * This part will probably use Jython to call p-sulu implemented in Python
 * @author Feng
 *
 */
public class Psulu {
	
	/**
	 * Return a list of points that represents the path on a 2D map. Each point is the local coordinate.
	 * @param startPos start position on the map
	 * @param endPos arrival position on the map
	 * @param obstacles a list of polygon obstacles, each represented by a list of points
	 * @param horizonRadius the radius of receding horizon
	 * @param riskBudget amount of risk budget allowed
	 * @return
	 */
	public List<Point2D> getPath(Point2D startPos, Point2D endPos, List<List<Point2D>> obstacles, double horizonRadius,
			double riskBudget) {
		return null;
	}

}
