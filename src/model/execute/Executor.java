package model.execute;

import java.util.ArrayList;
import java.util.List;

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

	public Executor() {

	}

	/**
	 * TODO: Need to discuss with Hiro on how to calculate the deviation function
	 */
	@Override
	public void setDeviationFunction() {

	}

	@Override
	public List<Point2D> getExecutedPath(List<Point2D> plannedPath) {
		// Make a copy first
		List<Point2D> executed = new ArrayList<>(plannedPath);

		return plannedPath;
	}

}
