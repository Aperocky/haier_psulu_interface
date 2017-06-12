package controller;

import frontend.general.Editor;
import frontend.model.canvas.layers.LayerMaster;
import frontend.model.canvas.layers.base.LayerType;
import frontend.model.canvas.layers.concrete.KeyComponentLayer;
import frontend.model.canvas.layers.concrete.obstaclelayer.ObstacleLayer;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.gamedata.game.GameStats;

public class EditController {
	
	private Stage stage;
    private GameStats gameStats;
	private Editor editor;
	
	
	public EditController(Stage stage, Editor editor, GameStats game) {
		this.stage = stage;
		this.editor = editor;
		this.gameStats = game;
			
		setUpObserver();
	}
	
	public void launch() {
		Scene scene = new Scene(editor, editor.getPrefWidth(), editor.getPrefHeight());
		stage.setScene(scene);
		stage.show();
	}

	/**
	 * In Edit mode, GameStats should observe the positions of obstacles, vehicle and goal
	 * in front end layers.
	 */
	private void setUpObserver() {
		LayerMaster layers = editor.getLayerMaster();
		layers.activateLayer(LayerType.KeyComponentLayer);
		layers.activateLayer(LayerType.ObstacleLayer);
		KeyComponentLayer goalLayer = (KeyComponentLayer) layers.getLayer(LayerType.KeyComponentLayer);
		goalLayer.setGoalPositionListener(point -> gameStats.setDestination(point));
		goalLayer.setVehiclePositionListener(point -> gameStats.setCurrentPosition(point));
		
		// TODO: let gamestats observe obstacleLayer 
		ObstacleLayer obstacleLayer = (ObstacleLayer) layers.getLayer(LayerType.ObstacleLayer);
		obstacleLayer.addObserver(gameStats);
	}
	
}
