package frontend.general;

import frontend.model.budget.RiskBudget;
import frontend.model.budget.SurfacingBudget;
import frontend.model.canvas.Canvas;
import frontend.model.canvas.layers.LayerMaster;
import frontend.model.notification.ProgressIndicator;
import frontend.model.notification.gameresult.SuccessMessage;
import frontend.model.operation.control.ControlPanel;
import frontend.model.operation.setting.Menu;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class Simulator extends BorderPane {

	private Canvas canvas;
	private ControlPanel controlPanel;
	private RiskBudget riskBudget;
	private SurfacingBudget surfacingBudget;
	private Menu menu;
    private ProgressIndicator progressIndicator;
    private SuccessMessage successMessage;

	public Simulator(double width, double height) {
		this.setPrefSize(width, height);
		canvas = new Canvas(height * 0.85d);
		controlPanel = new ControlPanel(width * 0.3d, height * 0.9d);
		riskBudget = new RiskBudget(width * 0.7, height * 0.1d);
		surfacingBudget = new SurfacingBudget(width * 0.2, height * 0.1d);
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
	
	public SurfacingBudget getSurfacingBudget() {
		return surfacingBudget;
	}
	
	private void fillBorder() {
//		this.setLeft(menu);
		this.setCenter(canvas);
		this.setRight(controlPanel);
		
		HBox hbox = new HBox();
		hbox.setPrefSize(this.getPrefWidth(), this.getPrefHeight() * 0.1);
		hbox.setSpacing(50d);
		hbox.setAlignment(Pos.CENTER);
		hbox.getChildren().addAll(riskBudget, surfacingBudget);
		this.setTop(hbox);
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
