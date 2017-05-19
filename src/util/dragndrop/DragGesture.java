package util.dragndrop;

import com.google.common.base.Predicate;

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
	private Node parent;
	private Node current;
	

	public DragGesture(Node parent, Node current) {
		this.current = current;
		this.parent = parent;
	}
	
	
	public void secondaryEnable() {
		 current.addEventFilter(MouseEvent.MOUSE_PRESSED, mousePressedHandler(evt -> evt.isSecondaryButtonDown()));
		 current.addEventFilter(MouseEvent.MOUSE_DRAGGED, mouseDraggedHandler(evt -> evt.isSecondaryButtonDown()));
	}
	
	public void primaryEnable() {
		 current.addEventFilter(MouseEvent.MOUSE_PRESSED, mousePressedHandler(evt -> evt.isPrimaryButtonDown()));
		 current.addEventFilter(MouseEvent.MOUSE_DRAGGED, mouseDraggedHandler(evt -> evt.isPrimaryButtonDown()));
	}

	private EventHandler<MouseEvent> mousePressedHandler(Predicate<MouseEvent> mouseTest) {
		return event -> {
			if (!mouseTest.test(event))
				return;

			Point2D anchorInParent = parent.sceneToLocal(event.getSceneX(), event.getSceneY());
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
			Point2D anchorInParent = parent.sceneToLocal(event.getSceneX(), event.getSceneY());
			node.setTranslateX(
					dragContext.nodeAnchorX + (anchorInParent.getX() - dragContext.mouseAnchorX));
			node.setTranslateY(
					dragContext.nodeAnchorY + (anchorInParent.getY() - dragContext.mouseAnchorY));
			event.consume();
		};
	}	

}
