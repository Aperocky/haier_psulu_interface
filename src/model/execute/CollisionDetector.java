package model.execute;

import java.util.List;

import javafx.geometry.Point2D;

public class CollisionDetector {
	
	public CollisionDetector() {
		
	}
	
	/**
	 * Check if point M is inside the rectangle formed by the list of vertices
	 * M(x, y) is inside ABCD iff (0<AM⋅AB<AB⋅AB) ^ (0<AM⋅AD<AD⋅AD)
	 * @param target
	 * @param shapes
	 * @return
	 */
	public boolean collide(Point2D M, List<Point2D> rect) {
		double errRange = 1.2d;
		Point2D A = rect.get(0);
		Point2D B = rect.get(1);
		Point2D C = rect.get(2);
		Point2D D = rect.get(3);
		double AMdotAB = dot(A, M, A, B);
		double ABdotAB = dot(A, B, A, B);
		double AMdotAD = dot(A, M, A, D);
		double ADdotAD = dot(A, D, A, D);
		return (0 < AMdotAB) && (AMdotAB < ABdotAB * errRange) && (0 < AMdotAD) && (AMdotAD < ADdotAD * errRange); 
	}
	
	/**
	 * Dot product of A1A2 and B1B2
	 * @param A1
	 * @param A2
	 * @param B1
	 * @param B2
	 * @return
	 */
	private double dot(Point2D A1, Point2D A2, Point2D B1, Point2D B2) {
		double[] v1 = new double[]{A2.getX() - A1.getX(), 
								   A2.getY() - A1.getY()};
		double[] v2 = new double[]{B2.getX() - B1.getX(), 
								   B2.getY() - B1.getY()};
		return v1[0] * v2[0] + v1[1] * v2[1];
		
	}

}
