package model.execute;

import java.util.Arrays;
import java.util.List;

import javafx.geometry.Point2D;

public class DetectorUnitTest {
	
	public static void main(String[] args){
		CollisionDetector detector = new CollisionDetector();
		Point2D p1 = new Point2D(0, 1);
		Point2D p2 = new Point2D(1, 1);
		Point2D p3 = new Point2D(0, 0);
		Point2D p4 = new Point2D(1, 0);
		Point2D t = new Point2D(0.1d, .0d);
		List<Point2D> rect = Arrays.asList(p1, p2, p3, p4);
		System.out.println(detector.collide(t, rect));
	}

}
