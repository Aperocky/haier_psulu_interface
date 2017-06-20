package controller;

import frontend.general.Editor;
import frontend.general.Simulator;
import javafx.stage.Stage;
import model.general.Game;

public class Controller {
	private Stage stage;
	private Game game;
	private EditController editController;
	private SimulateController simulateController;

	public Controller() {
		stage = new Stage();
		game = new Game();
	}

	public Game getGame() {
		return game;
	}

	public void launchEditor() {
		Editor editor = new Editor(500, 500);
		editController = new EditController(stage, editor, game.getEnvironment().getGameStats());
		editController.launch();
	}

	public void launchSimulator() {
		Simulator simulator = new Simulator(500, 500);
		simulateController = new SimulateController(stage, simulator, game);
		simulateController.launch();
	}

}
