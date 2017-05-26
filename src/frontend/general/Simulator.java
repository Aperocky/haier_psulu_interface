package frontend.general;

import frontend.model.canvas.layers.LayerMaster;
import frontend.model.operation.control.ControlPanel;
import frontend.util.GridPaneInitializer;
import javafx.scene.layout.GridPane;

public class Simulator extends GridPane {
	private static double COLUMN_RATIO1 = 0.7;
	private static double COLUMN_RATIO2 = 0.3;
	private static double ROW_RATIO1 = 0.1;
	private static double ROW_RATIO2 = 0.4;
	private static double ROW_RATIO3 = 0.5;

	private LayerMaster layerMaster;
	private ControlPanel controlPanel;

	public Simulator(double width, double height) {
		controlPanel = new ControlPanel(width * COLUMN_RATIO2, height * ROW_RATIO3);

		initializeLayout();
	}

	public LayerMaster getLayerMaster() {
		return layerMaster;
	}

	public ControlPanel getControlPanel() {
		return controlPanel;
	}

	private void initializeLayout() {
		GridPaneInitializer initializer = new GridPaneInitializer(this);
		initializer.columnRatios(COLUMN_RATIO1, COLUMN_RATIO2)
		  		   .rowRatios(ROW_RATIO1, ROW_RATIO2, ROW_RATIO3)
		           .build();
	}

}
