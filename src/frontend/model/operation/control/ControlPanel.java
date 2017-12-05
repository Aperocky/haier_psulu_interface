package frontend.model.operation.control;

import java.util.ArrayList;
import java.util.List;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import model.gamedata.game.control.ControlProperty;
import model.status.StatusManager;
import util.Observer;

public class ControlPanel extends Pane implements Observer<StatusManager>{
	private static final double WIDTH_RATIO = 0.6d;
	private static final double HEIGHT = 20;

	private ControlProperty controlProperty;
	private ControlSliderFactory sliderFactory;
	private VBox vbox;
	private JFXButton executeButton;
	private JFXButton planButton;
	public List<ControlSlider> sliderMaster; 

	public ControlPanel(double width, double height) {
		this.setPrefSize(width, height);
		vbox = new VBox();
		vbox.setPrefSize(width, height);
		controlProperty = new ControlProperty();
		sliderFactory = new ControlSliderFactory(this.getPrefWidth() * WIDTH_RATIO, HEIGHT);
		sliderMaster = new ArrayList<>();

		vbox.setSpacing(HEIGHT);
		vbox.setAlignment(Pos.CENTER);
		for (ControlType type : ControlType.values()) {
			HBox hbox = new HBox();
			hbox.setSpacing(5d);
			hbox.setAlignment(Pos.CENTER);
			Label min = new Label(""+type.uiMin());
			min.setFont(new Font(10));
			Label max = new Label(""+type.uiMax());
			max.setFont(new Font(10));
			Label label = new Label(type.label());
			ControlSlider slider = sliderFactory.getSlider(type, controlProperty.getControlProperty(type));
			connectSliderToLabel(slider, label);
			hbox.getChildren().addAll(min, slider, max);
			vbox.getChildren().addAll(label, hbox);
			sliderMaster.add(slider);
		}
		planButton = new JFXButton("Plan");
		planButton.setPrefSize(80,  20);
		executeButton = new JFXButton("Execute");
		executeButton.setPrefSize(80, 20);
		executeButton.setTranslateY(100);
		vbox.getChildren().addAll(planButton, executeButton);
		this.getChildren().add(vbox);
		
		setUpPlanButton();
	}
	
	@Override
	public void update(StatusManager status) {
		if(status.isPlanning() || status.isExecuting())
			this.disable();
		else 
			this.enable();
		if(!status.isFeasible())
			executeButton.setDisable(true);
	}

	/**
	 * Give the observable controlProperty
	 * 
	 * @return observable control property
	 */
	public ControlProperty getControlProperty() {
		return controlProperty;
	}

	/**
	 * Fire the input action if the execute button is hit
	 * 
	 * @param executeHandler
	 */
	public void setOnExecuted(EventHandler<ActionEvent> executeHandler) {
		executeButton.setOnAction(executeHandler);
	}

	public void disable() {
		sliderMaster.forEach(slider -> slider.setDisable(true));
		executeButton.setDisable(true);
		planButton.setDisable(true);
	}
	
	public void enable() {
		sliderMaster.forEach(slider -> slider.setDisable(false));
		executeButton.setDisable(false);
		planButton.setDisable(false);
	}
	
	private void connectSliderToLabel(ControlSlider slider, Label label) {
		slider.valueProperty().addListener((obs, oldv, newv) -> {
			if(slider.getType().equals(ControlType.ChanceConstraint)){
				double steprisk =  (int)(newv.doubleValue()*10) / 10d;
				label.setText(slider.getType().label() + " : " + steprisk +"%");
			    // If step risk is smaller than the minimum feasible solution, warn user
			}
			else if(slider.getType().equals(ControlType.WayPoints))
				label.setText(slider.getType().label() + " : " + newv.intValue());
		});
		slider.setValue((slider.getType().uiMax() + slider.getType().uiMin()) / 2);
	}
	
	private void setUpPlanButton() {
		planButton.setOnAction(evt -> {
			for(ControlSlider slider : sliderMaster) {
				ControlType type = slider.getType();
				double value = slider.getValue();
				// Hacky way to make sure even if the value is the same, planner will plan again
				if(type.equals(ControlType.ChanceConstraint)){
					double currValue = controlProperty.getControlProperty(ControlType.ChanceConstraint).doubleValue();
					if(uiToAlgo(ControlType.ChanceConstraint, value) == currValue)
						value = value * 1.000001;
				}
				controlProperty.getControlProperty(type).set(uiToAlgo(type, value));
			}
		});
	}
	
	private double uiToAlgo(ControlType type, double uiValue) {
		double algoRange = type.algoMax() - type.algoMin();
		double uiRange = type.uiMax() - type.uiMin();
		double ratio = algoRange / uiRange;
		return ratio * (uiValue - type.uiMin()) + type.algoMin();
	}

}
