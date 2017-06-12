package model.gamedata.jython;

import java.util.List;
import java.util.function.Supplier;

/**
 * Interface for connecting obstacleFactory.py and receiving obstacle vertices
 * User in combination with Jython
 * @author Feng
 *
 */
public interface VerticesSupplier {
	
	/**
	 * indexA: index of each obstacle
	 * indexB: index of each vertex of the obstacle
	 * indexC: 0 for x-coordinate and 1 for y-coordinate
	 * @return a list of x and y coordinates representing vertices of all obstacles in the map
	 */
	public List<List<List<Double>>> getObstacleVertices();

}
