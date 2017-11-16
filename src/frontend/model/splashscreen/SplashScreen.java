package frontend.model.splashscreen;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.gamedata.user.UserStats;
import util.ResourceParser;

public class SplashScreen extends BorderPane {

	private static final double DEFAULT_WIDTH = 400d;
	private static final double DEFAULT_HEIGHT = 400d;
	private static final String TITLE = "Haier/p-sulu interface";
	private static final String ID_TEXTFIELD = "Participant ID: ";
	private static final double SPACING = 30;
	private VBox vbox;
	private Label title;
	private JFXTextField idField;
	private JFXButton simulateButton;
	private JFXButton editButton;

	public SplashScreen(UserStats userStats) {
		this.setPrefSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		vbox = new VBox();
		vbox.setAlignment(Pos.CENTER);
		vbox.setSpacing(SPACING);

		fillContents(userStats);
		this.setCenter(vbox);
	}

	public void show(Stage stage) {
		ResourceParser parser = new ResourceParser("path");
		Scene scene = new Scene(this);
		scene.getStylesheets().add(parser.getString("css"));
		stage.setScene(scene);
		stage.show();
	}

	public void setOnSimulate(EventHandler<ActionEvent> simulateHandler) {
		simulateButton.setOnAction(simulateHandler);
	}

	public void setOnEdit(EventHandler<ActionEvent> editHandler) {
		editButton.setOnAction(editHandler);
	}

	private void fillContents(UserStats userStats) {
		title = new Label(TITLE);
		title.setFont(new Font("Lucida Sans Unicode", 18));
		title.setTranslateY(-20);

		idField = new JFXTextField();
		idField.setMaxWidth(DEFAULT_WIDTH * 0.6d);
		idField.setLabelFloat(true);
		idField.setPromptText(ID_TEXTFIELD);

		// RequiredFieldValidator validator = new RequiredFieldValidator();
		// validator.setMessage("Input Required");
		// idField.getValidators().add(validator);
		idField.textProperty().addListener((o, oldVal, newVal) -> {
			// validator.validate();
			simulateButton.setDisable(false);
			try {
				userStats.setId(Integer.valueOf(newVal.toString()));
			} catch (NumberFormatException e) {
				simulateButton.setDisable(true);
			}

		});

		HBox hbox = new HBox();
		hbox.setSpacing(SPACING);
		hbox.setAlignment(Pos.CENTER);
		simulateButton = button("Simulator");
		simulateButton.getStyleClass().add("button-raised");
		editButton = button("Editor");
		simulateButton.setDisable(true);
		editButton.setDisable(true);
		hbox.getChildren().addAll(editButton, simulateButton);

		vbox.getChildren().addAll(title, idField, hbox);
	}

	private JFXButton button(String label) {
		JFXButton button = new JFXButton(label);
		button.setPrefSize(100d, 70d);
		return button;
	}

}
