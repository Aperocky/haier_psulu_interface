package frontend.model.budget;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class RiskBudgetUnitTest extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {

		Scene scene = new Scene(new RiskBudget(300, 20), 500, 500);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static final void main(String[] args) {
		launch(args);
	}

}
