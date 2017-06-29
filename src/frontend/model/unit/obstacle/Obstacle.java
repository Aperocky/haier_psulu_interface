package frontend.model.unit.obstacle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

import frontend.util.usercontrol.basic.ICloneable;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class Obstacle extends Polygon implements ICloneable<Obstacle> {
	public static final Color DEFAULT_COLOR = Color.RED;
	public static final double DEFAULT_OPACITY = 0.6d;
	public static final double STROKE_WIDTH = 3d;
	private ObstacleUserControl userControl;
	
	private List<Point2D> vertices;
	private Node parent;

	public Obstacle(Node parent) {
		this(parent, new ArrayList<Point2D>());
	}
	
	// TODO Potential bug with this constructor
	public Obstacle(Node parent, List<Point2D> vertices) {
		super();
		this.parent = parent;
		this.vertices = new ArrayList<>();
		vertices.forEach(vertex -> addVertex(vertex));
		userControl = new ObstacleUserControl(this, point -> {
			return parent.sceneToLocal(point.getX(), point.getY());
		});
		
		userControl.activate();

		setColor(DEFAULT_COLOR);
		setOpacity(DEFAULT_OPACITY);
	}

	public void setColor(Color color) {
		this.setFill(color);
		this.setStroke(color.darker());
		this.setStrokeWidth(STROKE_WIDTH);
		addBorderGlow(this, color);
	}

	public void addVertex(Point2D vertex) {
		vertices.add(vertex);
		this.getPoints().addAll(vertex.getX(), vertex.getY());
	}

	public List<Point2D> getVertices() {
		return Collections.unmodifiableList(vertices);
	}
	
	public void setVertices(List<Point2D> vertices) {
		this.vertices = vertices;
	}
	
	public void activate() {
		userControl.activate();
	}
	
	public void deactivate() {
		userControl.deactivate();
	}
	
	public void deleteHandler(Consumer<Obstacle> deleteConsumer) {
		userControl.setDeleteHandler(deleteConsumer);
	}

	public void pasteHandler(Consumer<Obstacle> pasteConsumer) {
		userControl.setPasteHandler(pasteConsumer);
	}

	@Override
	public Obstacle copy() {
		Obstacle obs = new Obstacle(parent);
		vertices.forEach(vertex -> obs.addVertex(vertex));
		obs.deleteHandler(userControl.getDeleteHandler());
		obs.pasteHandler(userControl.getPasteHandler());
		return obs;
	}
	
	private void addBorderGlow(Node node, Color color) {		 
		DropShadow borderGlow= new DropShadow();
		borderGlow.setOffsetY(0f);
		borderGlow.setOffsetX(0f);
		borderGlow.setColor(color);
		borderGlow.setWidth(40);
		borderGlow.setHeight(40);
		 
		node.setEffect(borderGlow);
	}
}
