package model.gamedata.game;

import java.util.ArrayList;
import java.util.List;

import org.python.core.PyObject;
import org.python.core.PyString;
import org.python.util.PythonInterpreter;

import javafx.geometry.Point2D;
import model.gamedata.jython.VerticesSupplier;

/**
 * Responsible for pipelining obstacles from Jython to Java
 * 
 * @author Feng
 *
 */
public class ObstacleFactory {
	private double canvasWidth = 600d;
	private double canvasHeight = 600d;
	
	public ObstacleFactory() {
		
	}
	
	/**
	 * Set the size of canvas to adjust the size of each obstacle from Jython
	 * @param width
	 * @param height
	 */
	public void setCanvasSize(double width, double height) {
		canvasWidth = width;
		canvasHeight = height;
	}
	
	/**
	 * Load the obstacles in the form of a list of vertices of each obstacle
	 * @param canvasWidth
	 * @param canvasHeight
	 * @param pathToObstacles
	 * @return a list of vertices
	 */
	public List<List<Point2D>> loadObstacleFrom(String pathToObstacles) {
		// "[/Users/Feng/Documents/workspace/haier_psulu_interface/obstacleRsrc/newEnvi.yaml]"
		VerticesSupplier supplier = getVerticesSupplier(pathToObstacles);
		List<List<List<Double>>> vertices = supplier.getObstacleVertices();
		List<List<Point2D>> obstacles = new ArrayList<>();
        for(int obs = 0; obs < vertices.size(); obs++) {
        	List<Point2D> obstacle = new ArrayList<>();
        	for(int v = 0; v < vertices.get(obs).size(); v++){
        		double x = vertices.get(obs).get(v).get(0) * canvasWidth;
        		double y = vertices.get(obs).get(v).get(1) * canvasHeight;
        		Point2D vertex = new Point2D(x, y);
        		obstacle.add(vertex);
        	}
        	obstacles.add(obstacle);
        }
        return obstacles;
	}
	
	private VerticesSupplier getVerticesSupplier(String path) {
		PythonInterpreter pi = new PythonInterpreter();
    	pi.exec("import sys, os");
    	pi.exec("sys.path.append('/Users/Feng/Documents/workspace/haier_psulu_interface/src/model/gamedata/jython/')");
    	// pi.exec("sys.path.append('/Users/Feng/anaconda2/lib/python2.7/')");
    	pi.exec("sys.path.append('/Users/Feng/anaconda2/lib/python2.7/site-packages/')");
        pi.exec("from obstacleFactory import ObstacleFactory");
        PyObject obstacleClass = pi.get("ObstacleFactory");
        PyObject obstacleFactory = obstacleClass.__call__(new PyString(path));
        VerticesSupplier supplier = (VerticesSupplier)obstacleFactory.__tojava__(VerticesSupplier.class);
        pi.cleanup();
        pi.close();
        return supplier;
	}

}
