package secondVersion.model.plan;

import java.util.List;

import javafx.geometry.Point2D;

/**
 * General interface of a path planner in receding horizon
 * @author Feng
 *
 */
public interface IPlanner {
	
	/**
	 * 
	 * @param start current start position 
	 * @param end final arrival position
	 * @param obstacles list of polygon obstacles in the environment
	 * @return planned path represented by a list of points on the map
	 */
	public List<Point2D> getPlannedPath(Point2D start, Point2D end, List<List<Point2D>> obstacles);

}
