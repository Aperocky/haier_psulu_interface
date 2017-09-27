package model.gamedata;

import java.util.List;

import javafx.geometry.Point2D;
import model.gamedata.game.gamestats.GameStats;
import model.gamedata.user.UserStats;
import model.status.StatusManager;

/**
 * Responsible for loading XML data into the game, regarding obstacles,
 * initial position and final destination.
 * @author Feng
 *
 */
public class Environment {

	private StatusManager manager;
	private GameStats gameStats;
	private UserStats userStats;

	public Environment() {
		manager = new StatusManager();
		gameStats = new GameStats();
		userStats = new UserStats();
	}
	
	public GameStats getGameStats() {
		return gameStats;
	}
	
	public UserStats getUserStats() {
		return userStats;
	}
	
	public StatusManager getStatusManager() {
		return manager;
	}
	
	public void setPlanning(boolean planning) {
		manager.setPlanning(planning);
	}
	
	public void setPlannedPath(List<Point2D> plannedPath) {
		manager.setFeasible(plannedPath.size() != 0);
		gameStats.setPlannedPath(plannedPath);
	}
	
	public void setExecutedPath(List<Point2D> executedPath) {
	    gameStats.setExecutedPath(executedPath);	
	}
	
}
