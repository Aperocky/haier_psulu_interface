package controller;

import frontend.general.Simulator;
import frontend.model.canvas.layers.base.LayerType;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.gamedata.game.GameStats;
import model.general.Game;

/**
 * Pass ControlProperty from ControlPanel to Game so that game can observe
 * user's inputs in real time and plan path accordingly.
 * 
 * @author Feng
 *
 */
public class SimulateController {
	private Stage stage;
	private Simulator simulator;
	private Game game;

	public SimulateController(Stage stage, Simulator simulator, Game game) {
		this.stage = stage;
		this.simulator = simulator;
		this.game = game;

		setupObserver();
	}

	public void launch() {
		Scene scene = new Scene(simulator, simulator.getPrefWidth(), simulator.getPrefHeight());
		stage.setScene(scene);
		stage.show();
	}

	private void setupObserver() {
		// Let the game observe user's inputs in control panel
		game.setControlProperty(simulator.getControlPanel().getControlProperty());
		// Let path layer and obstacle layer observe game information in game stats
		GameStats gameStats = game.getEnvironment().getGameStats();
		gameStats.addObserver(simulator.getLayerMaster().getLayer(LayerType.PathLayer));
		gameStats.addObserver(simulator.getLayerMaster().getLayer(LayerType.ObstacleLayer));
		
		simulator.setOnExecute(evt -> game.execute());
	}

}
