package secondVersion.frontend.model.canvas.layers.concrete;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javafx.geometry.Point2D;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeLineCap;
import secondVersion.frontend.model.canvas.layers.LayerBase;
import secondVersion.frontend.model.canvas.layers.LayerType;
import secondVersion.model.gamedata.game.GameStats;

public class PathLayer extends LayerBase {
	public static final Color DEFAULT_COLOR = Color.RED;
	public static final double PATH_WIDTH = 5;
	// private List<Point2D> landmarks;
	private List<Line> segments;
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
	}
	
	@Override
	public LayerType getType() {
		return LayerType.PathLayer;
	}
	
	@Override
	public void update(GameStats game) {
		// TODO draw the planned path accordingly
	}

	public void setStart(Point2D start) {
		this.start = start;
	}

	public void drawPath(Collection<Point2D> landmarks) {
		landmarks.forEach(landmark -> addLandmark(landmark));
	}

	@Override
	protected void primaryMousePressed(MouseEvent event) {
		System.out.println("Primary Mouse Pressed on PathLayer.");
		if (planning) {
			// Add one more landmark to the plan
			addLandmark(event);
		}
	}

	@Override
	protected void secondaryMousePressed(MouseEvent event) {
		System.out.println("Secondary Mouse Pressed on PathLayer.");
		if (!planning) {
			planning = true;
			addLandmark(event);
		} else {
			planning = false;
		}
	}

	@Override
	protected void undoKeyPressed(KeyEvent event) {
		System.out.println("Undo Key Pressed in PathLayer.");
		if (!segments.isEmpty()) {
			Line seg = segments.get(segments.size() - 1);
			segments.remove(seg);
			getChildren().remove(seg);
			if(!segments.isEmpty()) {
				// Request focus for the next line segment in case there are more
				// undo actions
				Line nextSeg = segments.get(segments.size() - 1);
				nextSeg.setFocusTraversable(true);
				nextSeg.requestFocus();
			}
		}
		
	}

	private void addLandmark(MouseEvent event) {
		Point2D end = this.sceneToLocal(event.getSceneX(), event.getSceneY());
		addLandmark(end);
	}

	private void addLandmark(Point2D landmark) {
		Line seg = null;
		if (segments.isEmpty()) {
			seg = new Line(start.getX(), start.getY(), landmark.getX(), landmark.getY());
		} else {
			Line last = segments.get(segments.size() - 1);
			seg = new Line(last.getEndX(), last.getEndY(), landmark.getX(), landmark.getY());
		}
		segments.add(initializeLine(seg));
		getChildren().add(seg);
		seg.setFocusTraversable(true);
		seg.requestFocus();
	}

	private Line initializeLine(Line line) {
		line.setStroke(DEFAULT_COLOR);
		line.setStrokeWidth(PATH_WIDTH);
		line.setStrokeLineCap(StrokeLineCap.ROUND);
		return line;
	}

}
