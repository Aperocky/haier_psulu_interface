package frontend.model;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXMasonryPane;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import model.gamedata.user.UserStats;

public class SplashScreen extends JFXMasonryPane {
	private static final String TITLE = "Haier/p-sulu interface";
	private static final String ID_TEXTFIELD = "Participant ID: ";
	private static final String DATE_TEXTFIELD = "Experiment Date: ";
	private static final double SPACING = 30;
	private VBox vbox;

	public SplashScreen(double width, double height, UserStats userStats) {
		this.setPrefSize(width, height);
		vbox = new VBox();
		vbox.setAlignment(Pos.CENTER);
		vbox.setSpacing(SPACING);
		this.getChildren().add(vbox);

		fillContents(userStats);
	}

	private void fillContents(UserStats userStats) {
		Label label = new Label(TITLE);

		JFXTextField idField = new JFXTextField();
		idField.setLabelFloat(true);
		idField.setPromptText(ID_TEXTFIELD);

		RequiredFieldValidator validator = new RequiredFieldValidator();
		validator.setMessage("Input Required");
		idField.getValidators().add(validator);
		idField.textProperty().addListener((o, oldVal, newVal) -> {
			if(newVal != null) {
				validator.validate();
				userStats.setId(Integer.valueOf(newVal.toString()));
			}
		});

		JFXDatePicker datePicker = new JFXDatePicker();
		datePicker.setPromptText(DATE_TEXTFIELD);
		datePicker.valueProperty().addListener((o, oldVal, newVal) -> {
			if (newVal != null) {
				userStats.setDate(newVal);
			}
		});

		vbox.getChildren().addAll(label, idField, datePicker);
	}

}
