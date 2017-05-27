package frontend.model;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.gamedata.user.UserStats;

public class SplashScreenUnitTest extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		Pane primary = new Pane();
		primary.setPrefSize(500, 500);
		SplashScreen splash = new SplashScreen(500, 500, new UserStats());
		primary.getChildren().add(splash);
		Scene scene = new Scene(primary, 500, 500);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static final void main(String[] args) {
		launch(args);
	}

	
}
