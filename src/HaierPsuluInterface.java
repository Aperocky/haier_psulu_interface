import controller.Controller;
import javafx.application.Application;
import javafx.stage.Stage;

public class HaierPsuluInterface extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		Controller controller = new Controller(primaryStage);
		//controller.launchSimulator();	
		controller.launchEditor();
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
