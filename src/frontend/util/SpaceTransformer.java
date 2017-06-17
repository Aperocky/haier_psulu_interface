package frontend.util;

import javafx.geometry.Point2D;

/**
 * Transform the height and width of an UI element after switching from an old
 * canvas to a new one whose dimension is different
 * 
 * @author Feng
 *
 */
public class SpaceTransformer {

	public Point2D transformTo(double originWidth, double originHeight, double targetWidth, double targetHeight, Point2D point) {
		double widthR = targetWidth / originWidth;
		double heightR = targetHeight / originHeight;
		double originX = point.getX();
		double originY = point.getY();
		Point2D toPoint = new Point2D(originX * widthR, originY * heightR);
		return toPoint;
	}
	
	public Point2D verticalFlip(Point2D point, double canvasHeight) {
		double y = point.getY();
		y = canvasHeight - y;
		return new Point2D(point.getX(), y);
	}
	
	public Point2D horizontalFlip(Point2D point, double canvasWidth) {
		double x = point.getX();
		x = canvasWidth - x;
		return new Point2D(x, point.getY());
	}

}
