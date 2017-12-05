package frontend.model.canvas.layers.concrete.pathlayer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import frontend.model.canvas.layers.base.LayerBase;
import frontend.model.canvas.layers.base.LayerType;
import frontend.model.unit.path.PathSegment;
import javafx.geometry.Point2D;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import model.gamedata.game.gamestats.GameStats;

public class PathLayer extends LayerBase {

	private List<PathSegment> segments;
	private Point2D start;
	// True if currently setting landmarks
	private boolean planning;

	public PathLayer(double width, double height) {
		super(width, height);
		segments = new ArrayList<>();
		start = new Point2D(0, height);
	}

	@Override
	public void clear() {
		super.clear();
		segments.clear();
		start = null;
	}

	@Override
	public LayerType getType() {
		return LayerType.PathLayer;
	}

	@Override
	public void update(GameStats game) {
		this.clear();
		// Draw complete historical path
		transformAndDrawPath(game.getCompletePath(), Color.GRAY, 1d);
		
		// Draw last step planned path
		transformAndDrawPath(game.getLastStepPlannedPath(), Color.YELLOW, 0.3d);
		
		// Draw previously planned path
		transformAndDrawPath(game.getPrevPlannedPath(), Color.GRAY, 1d);
		
		// Draw planning path
		List<Point2D> path = game.getPlannedPath();
		if(path.size() == 0)
			return;
		path.stream().forEach(landmark -> addLandmark(transform(landmark)));
		
		// Mark deviation circle
	    Point2D first = path.get(0);
	    Point2D last = path.get(path.size()-1);
	    double oriR = length(last, first) / 10d * 3;
	    Point2D radi = new Point2D(oriR, oriR);
	    Point2D transR = transform(radi);
		addDeviationRange(transform(last), transR.getX());
	}

	public void setStart(Point2D start) {
		this.start = start;
	}

	public void drawPath(Collection<Point2D> landmarks) {
		landmarks.forEach(landmark -> addLandmark(landmark));
	}

	@Override
	protected void primaryPressed(MouseEvent event) {
		// System.out.println("Primary Mouse Pressed on PathLayer.");
		if (planning) {
			// Add one more landmark to the plan
			Point2D end = this.sceneToLocal(event.getSceneX(), event.getSceneY());
			addLandmark(end);
		}
	}

	@Override
	protected void secondaryPressed(MouseEvent event) {
		// System.out.println("Secondary Mouse Pressed on PathLayer.");
		if (!planning) {
			planning = true;
			Point2D end = this.sceneToLocal(event.getSceneX(), event.getSceneY());
			addLandmark(end);
		} else {
			planning = false;
		}
	}

	@Override
	protected void ctlZPressed(KeyEvent event) {
		if (!segments.isEmpty()) {
			Line seg = segments.get(segments.size() - 1);
			start = new Point2D(seg.getStartX(), seg.getStartY());
			segments.remove(seg);
			getChildren().remove(seg);
			if (!segments.isEmpty()) {
				// Request focus for the next line segment in case there are
				// more undo actions
				Line nextSeg = segments.get(segments.size() - 1);
				nextSeg.setFocusTraversable(true);
				nextSeg.requestFocus();
			}
		}

	}

	private void addLandmark(Point2D landmark) {
		if(start == null){
			start = landmark;
			return;
		}
		PathSegment path = new PathSegment(start, landmark);
		segments.add(path);
		Circle startC = new Circle(start.getX(), start.getY(), path.getStrokeWidth());
		startC.setFill(path.getStroke());
		Circle endC = new Circle(landmark.getX(), landmark.getY(), path.getStrokeWidth());
		endC.setFill(path.getStroke());
		getChildren().addAll(path, startC, endC);
		start = landmark;
	}
	
	private void addDeviationRange(Point2D last, double radius) {
		Circle range = new Circle(last.getX(), last.getY(), radius);
		RadialGradient gradient = new RadialGradient(
				0,
	            .1,
	            last.getX(),
	            last.getY(),
	            radius,
	            false,
	            CycleMethod.NO_CYCLE,
	            new Stop(0, Color.YELLOW),
	            new Stop(1, Color.TRANSPARENT));		
		range.setFill(gradient);
		range.setOpacity(0.3d);
		getChildren().add(range);
	}
	
	private void transformAndDrawPath(List<Point2D> path, Color color, double transparency) {
		if(path == null || path.size() == 0)
			return;
		Point2D start = transform(path.get(0));
		for(int i = 1; i < path.size(); i++) {
			Point2D next = transform(path.get(i));
			PathSegment seg = new PathSegment(start, next);
			seg.setOpacity(transparency);
			seg.setStroke(color);
			getChildren().add(seg);
			start = next;
		}
	}

	private double length(Point2D a, Point2D b) {
		return Math.pow((a.getX() - b.getX()) * (a.getX() - b.getX()) + (a.getY() - b.getY()) * (a.getY() - b.getY()),
				1 / 2d);
	}

}
