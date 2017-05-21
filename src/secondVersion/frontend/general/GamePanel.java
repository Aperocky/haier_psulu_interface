package secondVersion.frontend.general;

import javafx.beans.property.DoubleProperty;
import javafx.scene.layout.GridPane;
import secondVersion.frontend.model.canvas.layers.LayerMaster;
import secondVersion.frontend.model.operation.control.ControlPanel;
import secondVersion.frontend.util.GridPaneInitializer;

public class GamePanel extends GridPane{
	private static double COLUMN_RATIO1 = 0.7;
	private static double COLUMN_RATIO2 = 0.3;
	private static double ROW_RATIO1 = 0.1;
	private static double ROW_RATIO2 = 0.4;
	private static double ROW_RATIO3 = 0.5;
	
	private LayerMaster layerMaster;
	private ControlPanel controlPanel;
	
	public GamePanel(double width, double height) {
		controlPanel = new ControlPanel(width * COLUMN_RATIO2, height * ROW_RATIO3);
		
		initializeLayout();
	}
	
	public DoubleProperty getRiskProperty() {
		return controlPanel.getRiskProperty();
	}
	
	public DoubleProperty getHorizonProperty() {
		return controlPanel.getHorizonProperty();
	}
	
	private void initializeLayout() {
		GridPaneInitializer initializer = new GridPaneInitializer(this);
		initializer.columnRatios(COLUMN_RATIO1, COLUMN_RATIO2)
		           .rowRatios(ROW_RATIO1, ROW_RATIO2, ROW_RATIO3)
		           .build();
	}

}
