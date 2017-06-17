package model.gamedata.game.control;

import javafx.application.Application;
import javafx.stage.Stage;

public class ControlUnitTest extends Application{

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		@SuppressWarnings("unused")
		ControlProperty control = new ControlProperty();
		long end = System.currentTimeMillis();
		System.out.println("IO time: " + (end - start));
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
