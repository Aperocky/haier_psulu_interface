package frontend.util.usercontrol.drag;

import java.util.function.Function;

import com.google.common.base.Predicate;

import frontend.util.BiConsumer;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;

/**
 * Enable either primary or secondary mouse to drag the node around in its parent.
 * Also need the parent node in order to correctly position the node in its parent.
 * 
 * @author Feng
 *
 */
public class DragGesture {
	
	private DragContext dragContext = new DragContext();
	private BiConsumer<Double, Double> dragHandler;
	private Node current;
	private Function<Point2D, Point2D> sceneToLocal;
	

	public DragGesture(Node current, Function<Point2D, Point2D> sceneToLocal) {
		this.current = current;
		this.sceneToLocal = sceneToLocal;
	}
	
	/**
	 * Allow the user of this gesture to use the dragged distance in 
	 * x and y direction. 
	 * @param handler how to handle the dragged distance
	 */
	public void setDraggedHandler(BiConsumer<Double, Double> handler) {
		dragHandler = handler;
	}
	
	public void secondaryEnable() {
		 enable(evt -> evt.isSecondaryButtonDown());
	}
	
	public void primaryEnable() {
		enable(evt -> evt.isPrimaryButtonDown());
	}
	
	private void enable(Predicate<MouseEvent> predicate) {
		 current.addEventFilter(MouseEvent.MOUSE_PRESSED, mousePressedHandler(predicate));
		 current.addEventFilter(MouseEvent.MOUSE_DRAGGED, mouseDraggedHandler(predicate));
		 current.addEventFilter(MouseEvent.MOUSE_RELEASED, mouseReleasedHandler());
	}

	private EventHandler<MouseEvent> mousePressedHandler(Predicate<MouseEvent> mouseTest) {
		return event -> {
			if (!mouseTest.test(event))
				return;

			Point2D anchorInParent = sceneToLocal.apply(new Point2D(event.getSceneX(), event.getSceneY()));
			dragContext.mouseAnchorX = anchorInParent.getX();
			dragContext.mouseAnchorY = anchorInParent.getY();
			Node node = (Node) event.getSource();
			dragContext.nodeAnchorX = node.getTranslateX();
			dragContext.nodeAnchorY = node.getTranslateY();
		};
	}
	
	private EventHandler<MouseEvent> mouseDraggedHandler(Predicate<MouseEvent> mouseTest) {
		return event -> {
			if (!mouseTest.test(event))
				return;	
			Node node = (Node) event.getSource();
			Point2D anchorInParent = sceneToLocal.apply(new Point2D(event.getSceneX(), event.getSceneY()));
			node.setTranslateX(
					dragContext.nodeAnchorX + (anchorInParent.getX() - dragContext.mouseAnchorX));
			node.setTranslateY(
					dragContext.nodeAnchorY + (anchorInParent.getY() - dragContext.mouseAnchorY));
		};
	}	
	
	private EventHandler<MouseEvent> mouseReleasedHandler() {
		return event -> {
			Point2D anchorInParent = sceneToLocal.apply(new Point2D(event.getSceneX(), event.getSceneY()));
			if(dragHandler != null) {
				dragHandler.accept(anchorInParent.getX() - dragContext.mouseAnchorX, 
				                   anchorInParent.getY() - dragContext.mouseAnchorY);
			}
		};
	}

}

