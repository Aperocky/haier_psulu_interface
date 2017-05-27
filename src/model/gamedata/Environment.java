package model.gamedata;

import model.gamedata.game.GameStats;
import model.gamedata.user.UserStats;

/**
 * Responsible for loading XML data into the game, regarding obstacles,
 * initial position and final destination.
 * @author Feng
 *
 */
public class Environment {

	private GameStats gameStats;
	private UserStats userStats;
	//private Constants constants;

	public Environment() {
		gameStats = new GameStats();
		userStats = new UserStats();
	}
	
	public GameStats getGameStats() {
		return gameStats;
	}
	
	public UserStats getUserStats() {
		return userStats;
	}
	
	public void loadGameStats() {
		return;
	}
	
}
