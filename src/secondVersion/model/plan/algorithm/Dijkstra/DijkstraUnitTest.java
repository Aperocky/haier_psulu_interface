package secondVersion.model.plan.algorithm.Dijkstra;

import java.util.List;

public class DijkstraUnitTest {
	
	public static void main(String[] args) {
		Dijkstra dij = new Dijkstra();
		int[][] neighbors = new int[][] {
			{1, 2},
			{1, 3},
			{2, 3},
			{2, 5},
			{3, 4},
			{3, 5},
			{1, 4},
			{4, 5},
			{4, 6},
			{5, 6},
			{5, 7},
			{6, 7}
		};
		double[] weights = new double[] {
				4,
				3, 
				6, 
				5, 
				8, 
				11, 
				7, 
				2, 
				5, 
				10,
				2, 
				3
		};
		List<Integer> path = dij.getPath(1, 7, 7, neighbors, weights);
		for(Integer index : path) {
			System.out.println(index);
		}
	}

}
