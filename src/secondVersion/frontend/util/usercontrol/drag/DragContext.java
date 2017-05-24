package secondVersion.frontend.util.usercontrol.drag;

import java.io.Serializable;

@SuppressWarnings("serial")
public class DragContext implements Serializable {
	
	/**
	 * Mouse X position on the parental Pane
	 */
	public double mouseAnchorX;
	
	/**
	 * Mouse Y position on the parental Pane
	 */
	public double mouseAnchorY;

	/**
	 * Node Translate X position on the parental Pane
	 */
	public double nodeAnchorX;
	
	/**
	 * Node Translate Y position on the parental Pane
	 */
	public double nodeAnchorY;
}
