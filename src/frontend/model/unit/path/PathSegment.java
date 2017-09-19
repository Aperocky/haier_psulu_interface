package frontend.model.unit.path;


import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeLineCap;

public class PathSegment extends Line{
	public static final Color DEFAULT_COLOR = Color.YELLOWGREEN;
	public static final double DEFAULT_WIDTH = 2;
	
	public PathSegment(Point2D a, Point2D b) {
		super(a.getX(), a.getY(), b.getX(), b.getY());
		this.setStroke(DEFAULT_COLOR);
		this.setStrokeWidth(DEFAULT_WIDTH);
		this.setStrokeLineCap(StrokeLineCap.ROUND);
		
		this.setFocusTraversable(true);
		this.requestFocus();
	}

}
