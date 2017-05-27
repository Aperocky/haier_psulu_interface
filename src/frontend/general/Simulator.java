package frontend.general;

import frontend.model.canvas.layers.LayerMaster;
import frontend.model.operation.control.ControlPanel;
import frontend.util.GridPaneInitializer;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class Simulator extends GridPane {
	private static double COLUMN_CONSTRAINT1 = 70;
	private static double COLUMN_CONSTRAINT2 = 30;
	private static double ROW_CONSTRAINT1 = 10;
	private static double ROW_CONSTRAINT2 = 40;
	private static double ROW_CONSTRAINT3 = 50;

	private LayerMaster layerMaster;
	private ControlPanel controlPanel;

	public Simulator(double width, double height) {
		this.setPrefSize(width, height);
		layerMaster = new LayerMaster(width * COLUMN_CONSTRAINT1 / 100, height * (ROW_CONSTRAINT2 + ROW_CONSTRAINT3) / 100);
		controlPanel = new ControlPanel(width * COLUMN_CONSTRAINT2 / 100, height * ROW_CONSTRAINT3 / 100);

		initializeLayout();
		fillGrid();
	}

	public LayerMaster getLayerMaster() {
		return layerMaster;
	}

	public ControlPanel getControlPanel() {
		return controlPanel;
	}

	private void initializeLayout() {
		GridPaneInitializer initializer = new GridPaneInitializer(this);
		initializer.columnRatios(COLUMN_CONSTRAINT1, COLUMN_CONSTRAINT2).rowRatios(ROW_CONSTRAINT1, ROW_CONSTRAINT2, ROW_CONSTRAINT3).build();
	}

	private void fillGrid() {
		this.add(layerMaster, 0, 1, 1, 2);
		this.add(controlPanel, 1, 2);
	}

}
