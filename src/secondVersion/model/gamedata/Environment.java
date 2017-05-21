package secondVersion.model.gamedata;

import secondVersion.model.gamedata.game.GameStats;
import secondVersion.model.gamedata.user.UserStats;

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
