package frontend.general;

import frontend.model.budget.RiskBudget;
import frontend.model.canvas.Canvas;
import frontend.model.canvas.layers.LayerMaster;
import frontend.model.notification.ProgressIndicator;
import frontend.model.notification.gameresult.SuccessMessage;
import frontend.model.operation.control.ControlPanel;
import frontend.model.operation.setting.Menu;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.BorderPane;

public class Simulator extends BorderPane {
//	private static double COLUMN_CONSTRAINT1 = 70;
//	private static double COLUMN_CONSTRAINT2 = 30;
//	private static double ROW_CONSTRAINT1 = 30;
//	private static double ROW_CONSTRAINT2 = 20;
//	private static double ROW_CONSTRAINT3 = 50;

	private Canvas canvas;
	private ControlPanel controlPanel;
	private RiskBudget riskBudget;
	private Menu menu;
    private ProgressIndicator progressIndicator;
    private SuccessMessage successMessage;

	public Simulator(double width, double height) {
		this.setPrefSize(width, height);
		canvas = new Canvas(height * 0.75d);
		controlPanel = new ControlPanel(width * 0.2d, height * 0.9d);
		riskBudget = new RiskBudget(width, height * 0.1d);
		menu = new Menu(width * 0.2d, height * 0.4d);
		progressIndicator = new ProgressIndicator();
		progressIndicator.setMessage("Planning path...");
		successMessage = new SuccessMessage();
		
		//initializeLayout();
		fillBorder();
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
	
	public Menu getMenu() {
		return menu;
	}
	
	public ProgressIndicator getProgressIndicator() {
		return progressIndicator;
	}
	
	public SuccessMessage getSuccessMessage() {
		return successMessage;
	}
	
	public RiskBudget getRiskBudget() {
		return riskBudget;
	}
	
	private void fillBorder() {
		this.setLeft(menu);
		this.setCenter(canvas);
		this.setRight(controlPanel);
		this.setTop(riskBudget);
	}

//	private void initializeLayout() {
//		GridPaneInitializer initializer = new GridPaneInitializer(this);
//		initializer.columnRatios(COLUMN_CONSTRAINT1, COLUMN_CONSTRAINT2).rowRatios(ROW_CONSTRAINT1, ROW_CONSTRAINT2, ROW_CONSTRAINT3).build();
//	}

//	private void fillGrid() {
//		this.add(canvas, 0, 1, 1, 2);
//		this.add(controlPanel, 1, 2);
//	}

}
