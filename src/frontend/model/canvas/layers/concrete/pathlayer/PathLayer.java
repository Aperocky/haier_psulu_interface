package frontend.model.canvas.layers.concrete.pathlayer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import frontend.model.canvas.layers.base.LayerBase;
import frontend.model.canvas.layers.base.LayerType;
import frontend.model.unit.PathSegment;
import javafx.geometry.Point2D;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Line;
import model.gamedata.game.GameStats;
import util.Observer;

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
	protected void primaryPressed(MouseEvent event) {
		System.out.println("Primary Mouse Pressed on PathLayer.");
		if (planning) {
			// Add one more landmark to the plan
			addLandmark(event);
		}
	}

	@Override
	protected void secondaryPressed(MouseEvent event) {
		System.out.println("Secondary Mouse Pressed on PathLayer.");
		if (!planning) {
			planning = true;
			addLandmark(event);
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
		PathSegment path = new PathSegment(start, landmark);
		segments.add(path);
		getChildren().add(path);
		start = landmark;
	}

}
