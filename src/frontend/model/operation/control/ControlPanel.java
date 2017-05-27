package frontend.model.operation.control;

import javafx.beans.property.DoubleProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import model.gamedata.game.ControlProperty;

public class ControlPanel extends Pane {
	private static final double WIDTH_RATIO = 0.8d;
	private static final double HEIGHT = 20;
	private static final double SPACING = 20;

	private ControlProperty controlProperty;
	private VBox vbox;
	private Label riskLabel;
	private Label horizonLabel;
	private Slider riskSlider;
	private Slider horizonSlider;
	
	public ControlPanel(double width, double height) {
		this.setPrefSize(width, height);
		vbox = new VBox();
		vbox.setPrefSize(width, height);
		controlProperty = new ControlProperty();
		riskLabel = initLabel("Risk Budget");
		horizonLabel = initLabel("Horizon Radius");
		riskSlider = initSlider(0 ,100, controlProperty.getRiskBudgetProperty());
		horizonSlider = initSlider(0 ,100, controlProperty.getHorizonRadiusProperty());
		
	    vbox.setSpacing(SPACING);
		vbox.setAlignment(Pos.TOP_CENTER);
		vbox.getChildren().addAll(riskLabel, riskSlider, horizonLabel, horizonSlider);	
		this.getChildren().add(vbox);
	}
	
	/**
	 * Give the observable controlProperty 
	 * @return observable control property 
	 */
	public ControlProperty getControlProperty() {
		return controlProperty;
	}
	
	private Slider initSlider(double min, double max, DoubleProperty property) {
		Slider slider = new Slider(min, max, (min + max) / 2);
		property.bind(slider.valueProperty());
		slider.setPrefWidth(WIDTH_RATIO * this.getPrefWidth());
		slider.setPrefHeight(HEIGHT);
		return slider;
	}
	
	private Label initLabel(String name) {
		Label label = new Label(name);
		return label;
	}

}
