package secondVersion.model.plan.algorithm;

import java.util.List;

import javafx.geometry.Point2D;

/**
 * Calculates the shortest path between the one node to the other in 
 * discrete space. The calculation of path is based on the idea of weights
 * of either the nodes or the edges between nodes. 
 * @author Feng
 *
 */
public class Dijkstra {
	
	/**
	 * 
	 * @param startIndex the index of node that we start off with
	 * @param endIndex the index of node we intend to arrive at
	 * @param connections a list of all connections between two nodes, where each connection is represented
	 * by a list pair consisting of two indices 
	 * @param weights amount of weight corresponding to each pair of connection
	 * @return a list of indices that form the shortest path from start to end
	 */
	public List<Integer> getPath(int startIndex, int endIndex, int[][] connections, double[] weights) {
		return null;
	}

}
