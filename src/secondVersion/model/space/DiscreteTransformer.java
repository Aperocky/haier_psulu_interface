package secondVersion.model.space;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Point2D;
import javafx.scene.shape.Rectangle;

/**
 * A transformer that maps 2D polygons from continuous space to discrete space
 * 
 * @author Feng
 *
 */
public class DiscreteTransformer {
	private DoubleProperty unitProperty;

	public DiscreteTransformer(double unit) {
		unitProperty = new SimpleDoubleProperty(unit);
	}

	public void setDiscreteUnit(double unit) {
		unitProperty.set(unit);
	}

	/**
	 * Turn a polygon in continuous space into one in discrete space
	 * 
	 * Time Complexity: O(V * E) where V is the number of nodes in discrete space and E is number of edges
	 * for the polygon 
	 * @param width
	 * @param height
	 * @param polygon
	 * @return
	 */
	public List<Point2D> toDiscretePolygon(List<Point2D> polygon) {
		List<Point2D> discretePoints = new ArrayList<>();
		double unit = unitProperty.get();
		if(unit == 0)
			throw new IllegalArgumentException("Unit of the discrete space is not properly set.");
		RectBoundary boundary = boundary(polygon);
		for (double x = boundary.getMinX(); x < boundary.getMaxX(); x += unit) {
			for (double y = boundary.getMinY(); y < boundary.getMaxY(); y += unit) {
				Point2D point = new Point2D(x, y);
				if (contains(polygon, point)) {
					discretePoints.add(point);
				}
			}
		}
		return discretePoints;
	}

	/**
	 * Turn a list of polygons into discrete points in the discrete space
	 * 
	 * Time Complexity: O(n * V * E) where n is number of polygons. TODO: This
	 * is too slow
	 * 
	 * @param width
	 * @param height
	 * @param polygons
	 * @return
	 */
	public List<Point2D> toDiscretePolygons(List<List<Point2D>> polygons) {
		List<Point2D> discretePoints = new ArrayList<>();
		polygons.forEach(polygon -> discretePoints.addAll(toDiscretePolygon(polygon)));
		return discretePoints;
	}

	/**
	 * Checks if the polygon contains the target point. The polygon could be
	 * concave or convex. Note that point on the edge of the polygon will return
	 * false.
	 * 
	 * Time Complexity: O(E) where E is the number of edges in polygon
	 * 
	 * @param polygon
	 *            represented by a list of Point2D
	 * @param point
	 *            the target point we are examining
	 * @return whether the polygon contains the target completely(not on edge)
	 */
	public boolean contains(List<Point2D> polygon, Point2D point) {
		boolean result = false;
		for (int i = 0, j = polygon.size() - 1; i < polygon.size(); j = i++) {
			// i and j each represents one vertex of an edge in the polygon
			Point2D pointA = polygon.get(i);
			Point2D pointB = polygon.get(j);
			if (pointA.getY() > point.getY() != pointB.getY() > point.getY()) {
				// target point is vertically in between two vertices A and B
				double slope = (pointB.getY() - pointA.getY()) / (pointB.getX() - pointA.getX());
				double vertical = point.getY() - pointA.getY();
				double horizontalIfOnEdge = vertical / slope;
				if (point.getX() - pointA.getX() < horizontalIfOnEdge)
					result = !result;
			}
		}
		return result;
	}
	
	private RectBoundary boundary(List<Point2D> polygon) {
		double minX = Double.MAX_VALUE;
		double minY = Double.MAX_VALUE;
		double maxX = Double.MIN_VALUE;
		double maxY = Double.MIN_VALUE;
		for(Point2D point : polygon) {
			minX = Math.min(point.getX(), minX);
			minY = Math.min(point.getY(), minY);
			maxX = Math.max(point.getX(), maxX);
			maxY = Math.max(point.getY(), maxY);
		}
		return new RectBoundary(minX, maxX, minY, maxY);
	}
	
	private class RectBoundary{
		private final double minX;
		private final double minY;
		private final double maxX;
		private final double maxY;
		
		private RectBoundary(double minx, double maxx, double miny, double maxy) {
			minX = minx;
			minY = miny;
			maxX = maxx;
			maxY = maxy;
		}

		public double getMinX() {
			return minX;
		}

		public double getMinY() {
			return minY;
		}

		public double getMaxX() {
			return maxX;
		}

		public double getMaxY() {
			return maxY;
		}
		
		
	}

}
