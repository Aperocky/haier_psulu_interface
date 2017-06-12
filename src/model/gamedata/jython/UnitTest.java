package model.gamedata.jython;

import org.python.core.PyObject;
import org.python.core.PyString;
import org.python.util.PythonInterpreter;

public class UnitTest {

	public static void main(String[] args) {
		PythonInterpreter pi = new PythonInterpreter();
		pi.exec("import sys, os");
		pi.exec("sys.path.append('/Users/Feng/Documents/workspace/haier_psulu_interface/src/model/gamedata/jython/')");
		pi.exec("sys.path.append('/Users/Feng/anaconda2/lib/python2.7/')");
		pi.exec("sys.path.append('/Users/Feng/anaconda2/lib/python2.7/site-packages/')");
		pi.exec("from obstacleFactory import ObstacleFactory");
		PyObject obstacleClass = pi.get("ObstacleFactory");
		PyObject obstacleFactory = obstacleClass.__call__(
				new PyString("[/Users/Feng/Documents/workspace/haier_psulu_interface/obstacleRsrc/newEnvi.yaml]"));
		VerticesSupplier supplier = (VerticesSupplier) obstacleFactory.__tojava__(VerticesSupplier.class);
		for (int obs = 0; obs < supplier.getObstacleVertices().size(); obs++) {
			System.out.println(supplier.getObstacleVertices().get(obs));
		}

	}

}
