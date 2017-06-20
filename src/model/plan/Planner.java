package model.plan;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

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
public class Planner implements IPlanner{

	private ExecutorService executor;
	private YamlIO yamlIO;

	public Planner() {
		yamlIO = new YamlIO();
		executor = Executors.newSingleThreadExecutor();

	}

	/**
	 * Return the list of points that represents the way points of the path.
	 * Coordinates for the points are normalized, although potentially they can
	 * go outside of 0 or 1.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Future<List<Point2D>> getPlannedPath() throws IOException, InterruptedException {
		Callable<List<Point2D>> planTask = () -> {
			Runtime r = Runtime.getRuntime();
			Process p = r.exec("./PuLPpSulu.py", null,
					new File("/Users/Feng/Documents/workspace/haier_psulu_interface/src/psulu/"));
			p.waitFor();
			// Debug
			// BufferedReader stdOut = new BufferedReader(new
			// InputStreamReader(p.getInputStream()));
			// String s;
			// while ((s = stdOut.readLine()) != null) {
			// System.out.println(s);
			// }
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
		};

		Future<List<Point2D>> plannedPath = executor.submit(planTask);
		//softShutDown();
		return plannedPath;
	}

	private void softShutDown() {
		try {
			System.out.println("Attempt to shutdown planning executor");
			executor.shutdown();
			executor.awaitTermination(6, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			System.err.println("Non-finished tasks interrupted");
		} finally {
			if (!executor.isTerminated()) {
				System.err.println("Cancel non-finished tasks");
			}
			executor.shutdownNow();
			System.out.println("Shutdown completed");
		}
	}

}
