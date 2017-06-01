package frontend.model.canvas.layers.base;

import model.gamedata.game.GameStats;
import util.Observer;

/**
 * Basic layer interface that observes the GameStats
 * @author Feng
 *
 */
public interface ILayer extends Observer<GameStats>{
	
	public boolean isActive();
	
	public void activate();
	
	public void deactivate();
		
	public void clear();

	public LayerType getType();
	
	public void toFront();
	
	@Override
	public void update(GameStats gameStats);
	
}
