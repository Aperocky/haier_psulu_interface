package model.execute;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math3.distribution.MultivariateNormalDistribution;

import javafx.geometry.Point2D;

/**
 * Calculates the executed path and outputs to Game, which in turn modifies
 * Environment and let front end know. It aims to build as little dependency on
 * Environment class as possible.
 * 
 * @author Feng
 *
 */
public class Executor implements IExecutor {
	MultivariateNormalDistribution gaussian;

	public Executor() {

	}

	/**
	 * TODO: Need to discuss with Hiro on how to calculate the deviation
	 * function
	 */
	@Override
	public double[] sample(double std) {
		double[] means = new double[] { 0, 0 };
		double[][] covariance = new double[2][2];
		covariance[0][0] = Math.pow(std, 2);
		covariance[0][1] = 0d;
		covariance[1][0] = 0d;
		covariance[1][1] = Math.pow(std, 2);
		gaussian = new MultivariateNormalDistribution(means, covariance);

		return gaussian.sample();
	}

	@Override
	public List<Point2D> getExecutedPath(List<Point2D> plannedPath) {
		// Make a copy first
		List<Point2D> executedPath = new ArrayList<>(plannedPath);
		Point2D plannedLast = executedPath.remove(executedPath.size() - 1);

		// Sample a deviation amount for the last step
		// small step: 0.1d; medium step: 0.2d; big step: 0.3d
		double length = length(plannedLast, executedPath.get(0));
		double std = 1d / 88d * (length - 0.1d) / 0.08d;
		double[] deviation = sample(std);
		Point2D executedLast = new Point2D(plannedLast.getX() + deviation[0], plannedLast.getY() + deviation[1]);
		executedPath.add(executedLast);

		return executedPath;
	}

	private double length(Point2D a, Point2D b) {
		return Math.pow((a.getX() - b.getX()) * (a.getX() - b.getX()) + (a.getY() - b.getY()) * (a.getY() - b.getY()),
				1 / 2d);
	}

}
