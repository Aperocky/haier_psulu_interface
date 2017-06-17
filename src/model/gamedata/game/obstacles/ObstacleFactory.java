package model.gamedata.game.obstacles;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.python.core.PyObject;
import org.python.core.PyString;
import org.python.util.PythonInterpreter;

import javafx.geometry.Point2D;
import model.gamedata.jython.VerticesSupplier;
import util.YamlIO;

/**
 * Responsible for pipelining obstacles from Jython to Java
 * 
 * @author Feng
 *
 */
public class ObstacleFactory {
	private static final String DEFAULT_OBS_PATH = "/Users/Feng/Documents/workspace/haier_psulu_interface/src/psulu/config/newEnvi.yaml";
	private YamlIO yamlIO;

	public ObstacleFactory() {
		yamlIO = new YamlIO();
	}

	/**
	 * TODO Use cleaner structure instead of casting
	 * 
	 * @return
	 */
	public List<List<Point2D>> loadObstacles() {
		Map<String, Object> envi = (Map<String, Object>) ((Map<String, Object>) yamlIO.loadMap(DEFAULT_OBS_PATH))
				.get("environment");
		Map<String, Object> obstacles = (Map<String, Object>) envi.get("obstacles");
		List<List<Point2D>> result = new ArrayList<>();
		for (Object obs : obstacles.values()) {
			ArrayList<ArrayList<String>> obstacle = (ArrayList<ArrayList<String>>) ((Map<String, ?>) obs)
					.get("corners");
			List<Point2D> single = new ArrayList<>();
			System.out.println("Obstacle: ");
			for (ArrayList<String> lis : obstacle) {
				Double x = Double.valueOf(lis.get(0));
				Double y = Double.valueOf(lis.get(1));
				Point2D point = new Point2D(x, y);
				System.out.println(x + ", " + y);
				single.add(point);
			}
			result.add(single);
		}
		return result;

	}

	@Deprecated
	private VerticesSupplier getVerticesSupplier(String path) {
		PythonInterpreter pi = new PythonInterpreter();
		pi.exec("import sys, os");
		pi.exec("sys.path.append('/Users/Feng/Documents/workspace/haier_psulu_interface/src/model/gamedata/jython/')");
		// pi.exec("sys.path.append('/Users/Feng/anaconda2/lib/python2.7/')");
		pi.exec("sys.path.append('/Users/Feng/anaconda2/lib/python2.7/site-packages/')");
		pi.exec("from obstacleFactory import ObstacleFactory");
		PyObject obstacleClass = pi.get("ObstacleFactory");
		PyObject obstacleFactory = obstacleClass.__call__(new PyString(path));
		VerticesSupplier supplier = (VerticesSupplier) obstacleFactory.__tojava__(VerticesSupplier.class);
		pi.cleanup();
		pi.close();
		return supplier;
	}

}
