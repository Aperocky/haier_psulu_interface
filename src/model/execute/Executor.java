package model.execute;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import org.apache.commons.math3.distribution.MultivariateNormalDistribution;

import javafx.concurrent.Task;
import javafx.geometry.Point2D;
import util.ResourceParser;
import util.YamlIO;

/**
 * Calculates the executed path and outputs to Game, which in turn modifies
 * Environment and let front end know. It aims to build as little dependency on
 * Environment class as possible.
 * 
 * @author Feng
 *
 */
public class Executor {
	MultivariateNormalDistribution gaussian;

	private YamlIO yamlIO;
	private ResourceParser parser;

	public Executor() {
		yamlIO = new YamlIO();
		parser = new ResourceParser("path");
	}

	/**
	 * Return the list of points that represents way points of the EXECUTED path.
	 * Coordinates for the points are normalized, although potentially they can
	 * go outside of 0 or 1.
	 */
	@SuppressWarnings("unchecked")
	public void execute(Consumer<List<Point2D>> executingCallback) throws IOException, InterruptedException {
		Task<List<Point2D>> executingTask = new Task<List<Point2D>>() {
			@Override
			protected List<Point2D> call() throws Exception {
				ArrayList<ArrayList<String>> raw = (ArrayList<ArrayList<String>>) yamlIO
						.loadArray(parser.getString("psulu_output_execute"));
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

		executingTask.setOnSucceeded(evt -> {
			executingCallback.accept(executingTask.getValue());
		});

		Thread planThread = new Thread(executingTask);
		planThread.setDaemon(true);
		planThread.start();
	}
	
	
//
//	/**
//	 * TODO: Need to discuss with Hiro on how to calculate the deviation
//	 * function
//	 */
//	public double[] sample(double std) {
//		double[] means = new double[] { 0, 0 };
//		double[][] covariance = new double[2][2];
//		covariance[0][0] = Math.pow(std, 2);
//		covariance[0][1] = 0d;
//		covariance[1][0] = 0d;
//		covariance[1][1] = Math.pow(std, 2);
//		gaussian = new MultivariateNormalDistribution(means, covariance);
//
//		return gaussian.sample();
//	}
//
//	public List<Point2D> getExecutedPath(List<Point2D> plannedPath) {
//		// Make a copy first
//		List<Point2D> executedPath = new ArrayList<>(plannedPath);
//		Point2D plannedLast = executedPath.remove(executedPath.size() - 1);
//
//		// Sample a deviation amount for the last step
//		// small step: 0.1d; medium step: 0.2d; big step: 0.3d
//		double length = length(plannedLast, executedPath.get(0));
//		double std = length / 10d;
//		double[] deviation = sample(std);
//		Point2D executedLast = new Point2D(plannedLast.getX() + deviation[0], plannedLast.getY() + deviation[1]);
//		executedPath.add(executedLast);
//
//		return executedPath;
//	}
//
//	private double length(Point2D a, Point2D b) {
//		return Math.pow((a.getX() - b.getX()) * (a.getX() - b.getX()) + (a.getY() - b.getY()) * (a.getY() - b.getY()),
//				1 / 2d);
//	}

}
