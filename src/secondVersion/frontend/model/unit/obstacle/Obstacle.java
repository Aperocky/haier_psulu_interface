package secondVersion.frontend.model.unit.obstacle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import secondVersion.frontend.util.usercontrol.basic.ICloneable;

public class Obstacle extends Polygon implements ICloneable<Obstacle> {
	public static final Color DEFAULT_COLOR = Color.GREENYELLOW;
	public static final double DEFAULT_OPACITY = 0.6d;
	public static final double STROKE_WIDTH = 3d;
	private ObstacleUserControl userControl;
	
	private List<Point2D> vertices;
	private Node parent;

	public Obstacle(Node parent) {
		super();
		this.parent = parent;
		vertices = new ArrayList<>();
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
		userControl.deleteHandler(deleteConsumer);
	}

	public void pasteHandler(Consumer<Obstacle> pasteConsumer) {
		userControl.pasteHandler(pasteConsumer);
	}

	@Override
	public Obstacle copy() {
		Obstacle obs = new Obstacle(parent);
		vertices.forEach(vertex -> obs.addVertex(vertex));
		obs.deleteHandler(userControl.getDeleteHandler());
		obs.pasteHandler(userControl.getPasteHandler());
		return obs;
	}
	
//	private void initializeUserControl() {
//		dragGesture = new DragGesture(parent, this);
//		dragGesture.setDraggedHandler((dx, dy) -> {
//			List<Point2D> altVertice = new ArrayList<>();
//			for (Point2D point : vertices) {
//				altVertice.add(new Point2D(point.getX() + dx, point.getY() + dy));
//			}
//			vertices = altVertice;
//		});
//		dragGesture.primaryEnable();
//		
//		userControl.activate();
//	}

	// public void updateDiscreteForm() {
	// discreteForm.clear();
	// DiscreteTransformer transformer = new
	// DiscreteTransformer(Constants.UNIT.value());
	// List<Point2D> polygon = this.getVertices();
	// System.out.println("Vertex position: " + polygon.get(0).getX());
	// List<Point2D> discrete = transformer.toDiscretePolygon(polygon);
	// for (Point2D point : discrete) {
	// Circle dot = new Circle(point.getX(), point.getY(), 5, this.getFill());
	// discreteForm.add(dot);
	// }
	// }

}
