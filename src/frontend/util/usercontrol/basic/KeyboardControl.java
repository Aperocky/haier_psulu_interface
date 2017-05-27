package frontend.util.usercontrol.basic;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

/**
 * Translate basic key board action into event handling. 
 * 
 * @author Feng
 *
 */
public class KeyboardControl {
	private Node base;
	private boolean active;
	
	private EventHandler<MouseEvent> secondaryHandler;
	private EventHandler<MouseEvent> primaryHandler;
	private EventHandler<KeyEvent> ctlCHandler;
	private EventHandler<KeyEvent> ctlVHandler;
	private EventHandler<KeyEvent> deleteHandler;
	private EventHandler<KeyEvent> ctlZHandler;
	
	
	public KeyboardControl(Node base) {
		this.base = base;
	}

	public void activate() {
		active = true;
		base.addEventHandler(MouseEvent.MOUSE_PRESSED, mouseHandler);
		base.addEventHandler(KeyEvent.KEY_PRESSED, keyHandler);
	};

	public void deactivate() {
		active = false;
		base.removeEventHandler(MouseEvent.MOUSE_PRESSED, mouseHandler);
		base.removeEventHandler(KeyEvent.KEY_PRESSED, keyHandler);
	};
	
	public boolean isActive() {
		return active;
	}
	
	public void setSecondaryHandler(EventHandler<MouseEvent> secondaryHandler) {
		this.secondaryHandler = secondaryHandler;
	}

	public void setPrimaryHandler(EventHandler<MouseEvent> primaryHandler) {
		this.primaryHandler = primaryHandler;
	}

	public void setCtlCHandler(EventHandler<KeyEvent> ctlCHandler) {
		this.ctlCHandler = ctlCHandler;
	}

	public void setCtlVHandler(EventHandler<KeyEvent> ctlVHandler) {
		this.ctlVHandler = ctlVHandler;
	}

	public void setDeleteHandler(EventHandler<KeyEvent> deleteHandler) {
		this.deleteHandler = deleteHandler;
	}

	public void setCtlZHandler(EventHandler<KeyEvent> ctlZHandler) {
		this.ctlZHandler = ctlZHandler;
	}
	

	private void secondaryPressed(MouseEvent event) {
		if(secondaryHandler != null)
			secondaryHandler.handle(event);
	}

	private void primaryPressed(MouseEvent event) {
		if(primaryHandler != null)
			primaryHandler.handle(event);
	}

	private void deletePressed(KeyEvent event) {
		if(deleteHandler != null)
			deleteHandler.handle(event);
	}

	private void ctlCPressed(KeyEvent event) {
		if(ctlCHandler != null)
			ctlCHandler.handle(event);
	}

	private void ctlVPressed(KeyEvent event) {
		if(ctlVHandler != null)
			ctlVHandler.handle(event);
	}

	private void ctlZPressed(KeyEvent event) {
		if(ctlZHandler != null)
			ctlZHandler.handle(event);
	}

	private EventHandler<MouseEvent> mouseHandler = e -> {
		if (!active)
			return;
		if (e.getEventType().equals(MouseEvent.MOUSE_PRESSED)) {
			if (e.getButton().equals(MouseButton.SECONDARY))
				secondaryPressed(e);
			else
				primaryPressed(e);
		}
	};

	private EventHandler<KeyEvent> keyHandler = e -> {
		if (!active)
			return;
		KeyCode key = e.getCode();
		if (key.equals(KeyCode.BACK_SPACE)) {
			deletePressed(e);
		}
		if (e.isControlDown()) {
			switch (key) {
			case C:
				ctlCPressed(e);
				break;
			case V:
				ctlVPressed(e);
				break;
			case Z:
				ctlZPressed(e);
				break;
			default:
				break;
			}
		}
	};

}
