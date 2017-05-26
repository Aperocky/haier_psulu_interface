package model.plan.algorithm.Dijkstra;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Stack;

/**
 * Calculates the shortest path between the one node to the other in discrete
 * space. 
 * 
 * Time Complexity: O((E + V) * log(V)) where V is number of nodes and E is number of edges
 * Space Complexity: O(E + V)
 * 
 * @author Feng
 *
 */
public class Dijkstra {
	// Each set of connections represents all connections the node at current
	// index has
	private Map<Integer, HashSet<Neighbor>> neighbors;
	private Map<Integer, Integer> previous;
	private Map<Integer, Double> minDist;
	private PriorityQueue<Integer> unvisited;

	/**
	 * 
	 * @param startIndex
	 *            the index of node that we start off with
	 * @param endIndex
	 *            the index of node we intend to arrive at
	 * @param maxIndex
	 *            the maximum index existing in the map starting from 0
	 * @param connections
	 *            a list of all connections between two nodes, where each
	 *            connection is represented by a list pair consisting of two
	 *            indices
	 * @param distances
	 *            distance corresponding to each pair of connection
	 * @return a list of indices that form the shortest path from start to end
	 */
	public List<Integer> getPath(int startIndex, int targetIndex, int maxIndex, int[][] connections,
			double[] distances) {
		if (distances.length != connections.length)
			throw new IllegalArgumentException("Weights of connection pairs are not properly assigned.");
		initialize(startIndex, maxIndex);
		buildNeighbors(connections, distances);

		while (!unvisited.isEmpty()) {
			int current = unvisited.poll();
			System.out.println("Current Index of Minimum Distance: " + current);
			System.out.println("Current Distance from Target: " + minDist.get(current));
			System.out.println();
			if (current == targetIndex)
				break;
			for (Neighbor conn : neighbors.get(current)) {
				int neighbor = conn.A == current ? conn.B : conn.A;
				double dist = conn.distance;
				double alter = dist + minDist.get(current);
				if (alter < minDist.get(neighbor)) {
					minDist.put(neighbor, alter);
					unvisited.remove(neighbor);
					unvisited.add(neighbor);
					previous.put(neighbor, current);
				}
			}
		}
		List<Integer> path = rebuildPath(startIndex, targetIndex);
		return path;
	}

	/**
	 * Rebuild the in-order path from {@link #previous}.
	 * 
	 * @param start
	 *            start index
	 * @param target
	 *            target index
	 * @return list of indices that represent the path from start to target
	 */
	private List<Integer> rebuildPath(int start, int target) {
		List<Integer> path = new ArrayList<>();
		Stack<Integer> stackPath = new Stack<>();
		int current = target;
		stackPath.add(current);
		while (current != start) {
			int prev = previous.get(current);
			stackPath.push(prev);
			current = prev;
		}
		while (!stackPath.isEmpty()) {
			path.add(stackPath.pop());
		}
		return path;
	}

	/**
	 * 
	 * @param start
	 *            index we start off with
	 * @param max
	 *            the maximum index existing in the map
	 */
	private void initialize(int start, int max) {
		neighbors = new HashMap<>();
		previous = new HashMap<>();
		minDist = new HashMap<>();
		unvisited = new PriorityQueue<>(max + 1, new Comparator<Integer>() {
			@Override
			public int compare(Integer a, Integer b) {
				double minA = minDist.get(a);
				double minB = minDist.get(b);
				if (minA < minB)
					return -1;
				else
					return 1;
			}
		});
		
		for (int i = 0; i < max + 1; i++) {
			if (i != start) {
				minDist.put(i, Double.MAX_VALUE);
				previous.put(i, -1);
				unvisited.add(i);
			}
		}
		minDist.put(start, 0d);
		previous.put(start, start);
		unvisited.add(start);
	}

	private void buildNeighbors(int[][] connections, double[] weights) {
		for (int index = 0; index < connections.length; index++) {
			int[] conn = connections[index];
			double weight = weights[index];
			int a = conn[0];
			int b = conn[1];
			Neighbor connection = new Neighbor(a, b, weight);
			addToMapConnection(a, connection);
			addToMapConnection(b, connection);
		}
	}

	private void addToMapConnection(int index, Neighbor conn) {
		if (neighbors.containsKey(index)) {
			HashSet<Neighbor> conns = neighbors.get(index);
			conns.add(conn);
		} else {
			HashSet<Neighbor> conns = new HashSet<>();
			conns.add(conn);
			neighbors.put(index, conns);
		}
	}

}
