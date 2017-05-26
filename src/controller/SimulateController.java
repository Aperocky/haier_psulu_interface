package controller;

import frontend.general.Simulator;
import model.gamedata.game.GameStats;
import model.general.Game;

/**
 * Pass ControlProperty from ControlPanel to Game so that game can 
 * observe user's inputs in real time and plan path accordingly. 
 * 
 * @author Feng
 *
 */
public class SimulateController {
	
	public SimulateController(Simulator simulator, Game game) {
		// Let the game observe user's inputs in control panel
		game.setControlProperty(simulator.getControlPanel().getControlProperty());
		// Let path layer and obstacle layer observe game information in game stats
		GameStats gameStats = game.getEnvironment().getGameStats();
		gameStats.addObserver(simulator.getLayerMaster().getPathLayer());
		gameStats.addObserver(simulator.getLayerMaster().getObstacleLayer());

	}

}
