package secondVersion.frontend.model.unit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import secondVersion.model.gamedata.constant.Constants;
import secondVersion.model.space.DiscreteTransformer;
import util.dragndrop.DragGesture;

public class Obstacle extends Polygon implements IObstacle {
	public static final Color DEFAULT_COLOR = Color.GREENYELLOW;
	public static final double STROKE_WIDTH = 3d;
	private DragGesture dragGesture;
	private List<Point2D> vertices;
	private List<Circle> discreteForm;
	private Node parent;
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
		discreteForm = new ArrayList<>();
		dragGesture = new DragGesture(parent, this);
		dragGesture.primaryEnable();
		setColor(DEFAULT_COLOR);
		setOpacity(0.6d);
		setOnMousePressed(mouseHandler);
	}
	
	public List<Circle> getDiscreteForm() {
		return Collections.unmodifiableList(discreteForm);
	}
	
	public boolean selected() {
		return selected;
	}
	
	public void unselect() {
		selected = false;
	}
	
	public void updateDiscreteForm() {
		discreteForm.clear();
		DiscreteTransformer transformer = new DiscreteTransformer(Constants.UNIT.value());
		List<Point2D> polygon = this.getVertices();
		System.out.println("Vertex position: " + polygon.get(0).getX());
		List<Point2D> discrete = transformer.toDiscretePolygon(polygon);
		for(Point2D point : discrete) {
			Circle dot = new Circle(point.getX(), point.getY(), 5, this.getFill());
			discreteForm.add(dot);
		}
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
	public List<Point2D> getVertices() {
		return Collections.unmodifiableList(vertices);
	}
	
	@Override
	public Obstacle copy() {
		Obstacle obs = new Obstacle(parent);
		vertices.forEach(vertex -> obs.addVertex(vertex));
		return obs;
	}

}
