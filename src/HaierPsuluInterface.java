import controller.Controller;
import frontend.model.splashscreen.SplashScreen;
import javafx.application.Application;
import javafx.stage.Stage;

public class HaierPsuluInterface extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		Controller controller = new Controller();
		
		SplashScreen splash = new SplashScreen(controller.getGame().getEnvironment());
		splash.setOnSimulate(evt -> controller.launchSimulator());
		splash.setOnEdit(evt -> controller.launchEditor());
		
		splash.show(primaryStage);
		//controller.launchSimulator();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
