package frontend.util.mixedprogressbar;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MixedProgressBarUnitTest extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		MixedProgressBar bar = new MixedProgressBar(200, 40, 2);
		bar.setProgress(0.5d, 0);
		bar.setProgress(0.3d, 1);
		Scene scene = new Scene(bar);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static final void main(String[] args) {
		launch(args);
	}

	
}
