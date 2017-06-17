package model.plan;

import java.io.IOException;
import java.util.List;

import javafx.geometry.Point2D;

public class PlannerUnitTest {
	
	public static void main(String[] args) {
		Planner p = new Planner();
		try {
			long start = System.currentTimeMillis();
			List<Point2D> waypoints = p.getPlannedPath();	
		    long end = System.currentTimeMillis();
		    System.out.println("IO time: " + (end - start));
		    waypoints.forEach(pt -> {
		    	System.out.println(pt.getX() + ", " + pt.getY());
		    });
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}

}
