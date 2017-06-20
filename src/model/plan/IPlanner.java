package model.plan;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Future;
import java.util.function.Consumer;

import javafx.geometry.Point2D;

/**
 * General interface of a path planner in receding horizon
 * 
 * @author Feng
 *
 */
public interface IPlanner {

	void plan(Consumer<List<Point2D>> planningCallback) throws IOException, InterruptedException;

}
