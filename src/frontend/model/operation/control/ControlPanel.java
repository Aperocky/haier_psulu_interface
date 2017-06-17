package frontend.model.operation.control;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import model.gamedata.game.control.ControlProperty;

public class ControlPanel extends Pane {
	private static final double WIDTH_RATIO = 0.8d;
	private static final double HEIGHT = 20;

	private ControlProperty controlProperty;
	private ControlSliderFactory sliderFactory;
	private VBox vbox;
	private JFXButton executeButton;

	public ControlPanel(double width, double height) {
		this.setPrefSize(width, height);
		vbox = new VBox();
		vbox.setPrefSize(width, height);
		controlProperty = new ControlProperty();
		sliderFactory = new ControlSliderFactory(this.getPrefWidth() * WIDTH_RATIO, HEIGHT);

		vbox.setSpacing(HEIGHT);
		vbox.setAlignment(Pos.TOP_CENTER);
		for (ControlType type : ControlType.values()) {
			Label label = initLabel(type.label());
			ControlSlider slider = sliderFactory.getSlider(type, controlProperty.getControlProperty(type));
			vbox.getChildren().addAll(label, slider);
		}

		executeButton = new JFXButton("Execute");
		executeButton.setPrefSize(80, 20);
		executeButton.setTranslateY(20d);
		vbox.getChildren().add(executeButton);
		this.getChildren().add(vbox);
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

	private Label initLabel(String name) {
		Label label = new Label(name);
		return label;
	}

}
