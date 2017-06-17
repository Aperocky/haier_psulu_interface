package model.plan;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Point2D;
import util.YamlIO;

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
	private YamlIO yamlIO;

	public Planner() {
		yamlIO = new YamlIO();

	}

	/**
	 * Return the list of points that represents the way points of the path.
	 * Coordinates for the points are normalized, although potentially they can
	 * go outside of 0 or 1.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Point2D> getPlannedPath() throws IOException, InterruptedException {

		Runtime r = Runtime.getRuntime();
		Process p = r.exec("./PuLPpSulu.py", null,
				new File("/Users/Feng/Documents/workspace/haier_psulu_interface/src/psulu/"));
		// Test
		BufferedReader stdOut = new BufferedReader(new InputStreamReader(p.getInputStream()));
		String s;
		while ((s = stdOut.readLine()) != null) {
			System.out.println(s);
		}

		ArrayList<ArrayList<String>> raw = (ArrayList<ArrayList<String>>) yamlIO
				.loadArray("/Users/Feng/Documents/workspace/haier_psulu_interface/src/psulu/output/path.yaml");
		List<Point2D> vertices = new ArrayList<>();
		for (ArrayList<String> v : raw) {
			double x = Double.valueOf(v.get(0));
			double y = Double.valueOf(v.get(1));
			Point2D vertice = new Point2D(x, y);
			vertices.add(vertice);
		}

		return vertices;
	}

}
