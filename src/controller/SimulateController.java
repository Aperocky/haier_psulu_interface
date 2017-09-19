package controller;

import frontend.general.Simulator;
import frontend.model.canvas.layers.base.LayerType;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.gamedata.game.gamestats.GameStats;
import model.general.Game;
import model.status.StatusManager;
import util.ResourceParser;

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
	private ResourceParser parser;

	public SimulateController(Stage stage, Simulator simulator, Game game) {
		this.stage = stage;
		this.simulator = simulator;
		this.game = game;
		this.parser = new ResourceParser("path");

		setupObserver();
		setupMenu();
	}

	public void launch() {
		Scene scene = new Scene(simulator, simulator.getPrefWidth(), simulator.getPrefHeight());
		scene.getStylesheets().add(parser.getString("css"));
		stage.setScene(scene);
		stage.show();
	}

	private void setupObserver() {
		// Game observes control panel
		game.setControlProperty(simulator.getControlPanel().getControlProperty());

		// Layers observe game stats
		GameStats gameStats = game.getEnvironment().getGameStats();
		for (LayerType layer : LayerType.values()) {
			gameStats.addObserver(simulator.getLayerMaster().getLayer(layer));
		}
		gameStats.addObserver(simulator.getRiskBudget());
		gameStats.addObserver(simulator.getSurfacingBudget());
		gameStats.notifyObservers(gameStats);

		// Spinner, GameResultMessage observe StatusManager
		StatusManager manager = game.getEnvironment().getStatusManager();
		manager.addObservers(simulator.getProgressIndicator(), simulator.getSuccessMessage(),
				simulator.getControlPanel());
		manager.notifyObservers(manager);

		simulator.setOnExecute(evt -> game.execute());

	}

	private void setupMenu() {
		simulator.getMenu().fill(game.getEnvironment());
	}

}
