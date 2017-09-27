package model.execute;

import java.util.List;

import javafx.geometry.Point2D;

public interface IExecutor {
	
	/**
	 * Set the function which calculates amount of deviation and relevant distribution
	 */
	public double[] sample(double std);

	/**
	 * Modify the planned path according to the deviation function stored.
	 * @param plannedPath
	 */
	public List<Point2D> getExecutedPath(List<Point2D> plannedPath);
}
