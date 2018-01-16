package frontend.general;

import frontend.model.budget.RiskBudget;
import frontend.model.budget.ScheduleRiskBudget;
import frontend.model.budget.SurfacingBudget;
import frontend.model.canvas.Canvas;
import frontend.model.canvas.layers.LayerMaster;
import frontend.model.notification.ProgressIndicator;
import frontend.model.notification.gameresult.MessageMaster;
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
//	private ScheduleRiskBudget scheduleBudget;
	private SurfacingBudget surfacingBudget;
	private Menu menu;
    private ProgressIndicator progressIndicator;
    private MessageMaster messageMaster;

	public Simulator(double width, double height) {
		this.setPrefSize(width, height);
		canvas = new Canvas(height * 0.85d);
		controlPanel = new ControlPanel(width * 0.3d, height * 0.9d);
		riskBudget = new RiskBudget(width * 0.8d, height * 0.1d);
//		scheduleBudget = new ScheduleRiskBudget(width * 0.4d, height * 0.1d);
		surfacingBudget = new SurfacingBudget(width * 0.2d, height * 0.1d);
		menu = new Menu(width * 0.2d, height * 0.4d);
		progressIndicator = new ProgressIndicator();
		progressIndicator.setMessage("Planning path...");
		messageMaster = new MessageMaster();
		
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
	
	public MessageMaster getMessageMaster() {
		return messageMaster;
	}
	
	public RiskBudget getRiskBudget() {
		return riskBudget;
	}
	
//	public ScheduleRiskBudget getScheduleRiskBudget() {
//		return scheduleBudget;
//	}
	
	public SurfacingBudget getSurfacingBudget() {
		return surfacingBudget;
	}
	
	private void fillBorder() {
//		this.setLeft(menu);
		this.setCenter(canvas);
		this.setRight(controlPanel);
		
		HBox hbox = new HBox();
		hbox.setPrefSize(this.getPrefWidth(), this.getPrefHeight() * 0.1);
		hbox.setSpacing(20d);
		hbox.setAlignment(Pos.CENTER);
		hbox.getChildren().addAll(riskBudget, surfacingBudget);
		this.setTop(hbox);
	}

}
