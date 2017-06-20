package frontend.general;

import frontend.model.canvas.layers.Canvas;
import frontend.model.canvas.layers.LayerMaster;
import frontend.model.notification.ProgressIndicator;
import frontend.model.notification.gameresult.SuccessMessage;
import frontend.model.operation.control.ControlPanel;
import frontend.util.GridPaneInitializer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.GridPane;

public class Simulator extends GridPane {
	private static double COLUMN_CONSTRAINT1 = 70;
	private static double COLUMN_CONSTRAINT2 = 30;
	private static double ROW_CONSTRAINT1 = 30;
	private static double ROW_CONSTRAINT2 = 20;
	private static double ROW_CONSTRAINT3 = 50;

	private Canvas canvas;
	private ControlPanel controlPanel;
    private ProgressIndicator progressIndicator;
    private SuccessMessage successMessage;

	public Simulator(double width, double height) {
		this.setPrefSize(width, height);
		canvas = new Canvas(width * COLUMN_CONSTRAINT1 / 100, height * (ROW_CONSTRAINT2 + ROW_CONSTRAINT3) / 100);
		controlPanel = new ControlPanel(width * COLUMN_CONSTRAINT2 / 100, height * ROW_CONSTRAINT3 / 100);
		progressIndicator = new ProgressIndicator();
		progressIndicator.setMessage("Planning path...");
		successMessage = new SuccessMessage();
		
		initializeLayout();
		fillGrid();
	}
	
	public void setOnExecute(EventHandler<ActionEvent> executeHandler){
		controlPanel.setOnExecuted(executeHandler);
	}

	public LayerMaster getLayerMaster() {
		return canvas.getLayerMaster();
	}

	public ControlPanel getControlPanel() {
		return controlPanel;
	}
	
	public ProgressIndicator getProgressIndicator() {
		return progressIndicator;
	}
	
	public SuccessMessage getSuccessMessage() {
		return successMessage;
	}

	private void initializeLayout() {
		GridPaneInitializer initializer = new GridPaneInitializer(this);
		initializer.columnRatios(COLUMN_CONSTRAINT1, COLUMN_CONSTRAINT2).rowRatios(ROW_CONSTRAINT1, ROW_CONSTRAINT2, ROW_CONSTRAINT3).build();
	}

	private void fillGrid() {
		this.add(canvas, 0, 1, 1, 2);
		this.add(controlPanel, 1, 2);
	}

}
