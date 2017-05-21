package secondVersion.frontend.model.canvas.layers;

import secondVersion.model.gamedata.game.GameStats;
import secondVersion.util.Observer;

public interface ILayer extends Observer<GameStats>{
	
	public boolean isActive();
	
	public void activate();
	
	public void deactivate();
		
	public void clear();

	public LayerType getType();
	
	public void toFront();
	
}
