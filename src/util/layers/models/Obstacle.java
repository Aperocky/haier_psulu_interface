package util.layers.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import util.dragndrop.DragGesture;

public class Obstacle extends Polygon implements IObstacle {
	public static final Color DEFAULT_COLOR = Color.GREENYELLOW;
	public static final double STROKE_WIDTH = 3d;
	private Node parent;
	private List<Point2D> vertices;
	private DragGesture dragGesture;
	private boolean selected;
	
	private EventHandler<MouseEvent> mouseHandler = e -> {
		if(e.isPrimaryButtonDown()){
			selected = true;
			// This makes KeyEvent functional
		    this.setFocusTraversable(true);
		    this.requestFocus();
		}
	};

	public Obstacle(Node parent) {
		super();
	    this.parent = parent;
		vertices = new ArrayList<>();
		dragGesture = new DragGesture(parent, this);
		dragGesture.primaryEnable();
		setColor(DEFAULT_COLOR);
		setOnMousePressed(mouseHandler);
	}
	
	public boolean selected() {
		return selected;
	}
	
	public void unselect() {
		selected = false;
	}

	@Override
	public void setColor(Color color) {
		this.setFill(color);
		this.setStroke(color.darker());
		this.setStrokeWidth(STROKE_WIDTH);
	}

	@Override
	public void addVertex(Point2D vertex) {
		vertices.add(vertex);
		this.getPoints().addAll(vertex.getX(), vertex.getY());
	}

	@Override
	public Collection<Point2D> getVertices() {
		return Collections.unmodifiableList(vertices);
	}
	
	@Override
	public Obstacle copy() {
		Obstacle obs = new Obstacle(parent);
		vertices.forEach(vertex -> obs.addVertex(vertex));
		return obs;
	}

}
