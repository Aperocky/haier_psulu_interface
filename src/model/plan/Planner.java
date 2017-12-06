package model.plan;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import javafx.concurrent.Task;
import javafx.geometry.Point2D;
import util.ResourceParser;
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
	private ResourceParser parser;

	public Planner() {
		yamlIO = new YamlIO();
		parser = new ResourceParser("path");
	}

	/**
	 * Return the list of points that represents the way points of the PLANNED path.
	 * Coordinates for the points are normalized, although potentially they can
	 * go outside of 0 or 1.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void plan(Consumer<List<Point2D>> planningCallback) throws IOException, InterruptedException {
		Task<List<Point2D>> planningTask = new Task<List<Point2D>>() {
			@Override
			protected List<Point2D> call() throws Exception {
				Runtime r = Runtime.getRuntime();
				// This only works on Feng's computer!

				Process p = r.exec("./PuLPpSulu.py", null, new File(parser.getString("psulu_planner")));
				
				// Use this for other Windows machine after adding Anaconda's python to system Path variable
				// Process p = r.exec("python ./PuLPpSulu.py", null, new File(parser.getString("psulu_planner")));
				p.waitFor();
				// Debug
				 BufferedReader stdOut = new BufferedReader(new
				 InputStreamReader(p.getErrorStream()));
				 String s;
				 while ((s = stdOut.readLine()) != null) {
				 System.out.println(s);
				 }
				ArrayList<ArrayList<String>> raw = (ArrayList<ArrayList<String>>) yamlIO
						.loadArray(parser.getString("psulu_output_plan"));
				List<Point2D> vertices = new ArrayList<>();
				for (ArrayList<String> v : raw) {
					double x = Double.valueOf(v.get(0));
					double y = Double.valueOf(v.get(1));
					Point2D vertice = new Point2D(x, y);
					vertices.add(vertice);
				}
				return vertices;
			}
		};

		planningTask.setOnSucceeded(evt -> {
			planningCallback.accept(planningTask.getValue());
		});

		Thread planThread = new Thread(planningTask);
		planThread.setDaemon(true);
		planThread.start();
	}

}
