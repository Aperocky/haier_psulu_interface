package controller;

import java.util.ArrayList;
import java.util.Map;

import frontend.general.Simulator;
import frontend.model.canvas.layers.base.LayerType;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.gamedata.game.GameStats;
import model.gamedata.game.obstacles.ObstacleFactory;
import model.gamedata.game.param.ParamIO;
import model.general.Game;

/**
 * Pass ControlProperty from ControlPanel to Game so that game can observe
 * user's inputs in real time and plan path accordingly.
 * 
 * @author Feng
 *
 */
public class SimulateController {
	
	private static final String CSS_FILE = "frontend/css/dracula.css";
	private Stage stage;
	private Simulator simulator;
	private Game game;

	public SimulateController(Stage stage, Simulator simulator, Game game) {
		this.stage = stage;
		this.simulator = simulator;
		this.game = game;

		initialize();

		setupObserver();
	}

	public void launch() {
		Scene scene = new Scene(simulator, simulator.getPrefWidth(), simulator.getPrefHeight());
		scene.getStylesheets().add(CSS_FILE);
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
		gameStats.notifyObservers(gameStats);

		// Spinner observes StatusManager
		game.getEnvironment().getStatusManager().addObserver(simulator.getProgressIndicator());
		
		simulator.setOnExecute(evt -> game.execute());
	}

	private void initialize() {
		GameStats gameStats = game.getEnvironment().getGameStats();
		gameStats.setObstacles((new ObstacleFactory().loadObstacles()));
		ParamIO io = new ParamIO();
		Map<String, Object> params = (Map<String, Object>) io.loadOriginal();
		ArrayList<String> start = (ArrayList<String>) params.get("start_location");
		ArrayList<String> end = (ArrayList<String>) params.get("end_location");
		Point2D startP = new Point2D(Double.valueOf(start.get(0)), Double.valueOf(start.get(1)));
		Point2D endP = new Point2D(Double.valueOf(end.get(0)), Double.valueOf(end.get(1)));
		gameStats.setCurrentPosition(startP);
		gameStats.setDestination(endP);
	}

}
