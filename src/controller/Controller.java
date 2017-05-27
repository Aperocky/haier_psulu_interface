package controller;

import frontend.general.Simulator;
import javafx.stage.Stage;
import model.general.Game;

public class Controller {
	private Stage stage;
	private Game game;
	private EditController editController;
	private SimulateController simulateController;
	
	public Controller(Stage stage) {
		this.stage = stage;
		game = new Game();
		
	}
	
	
	public void launchEditor(Stage stage) {
		
	}
	
	public void launchSimulator() {
		Simulator simulator = new Simulator(500, 500);
		simulateController = new SimulateController(stage, simulator, game);
		simulateController.launch();
	}
	
	

}
