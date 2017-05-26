package model.plan.algorithm.Dijkstra;

import java.util.List;

public class DijkstraUnitTest {
	
	public static void main(String[] args) {
		Dijkstra dij = new Dijkstra();
		int[][] neighbors = new int[][] {
			{1, 2},
			{2, 3},
			{1, 3},
			{2, 5},
			{5, 4},
			{3, 4},
			{5, 6},
			{4, 6}
		};
		double[] weights = new double[] {
				1,
				6,
				4,
				2,
				3,
				8,
				8,
				4
		};
		List<Integer> path = dij.getPath(1, 6, 6, neighbors, weights);
		for(Integer index : path) {
			System.out.println(index);
		}
	}

}
