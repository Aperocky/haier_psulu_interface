package model.gamedata.game;

import frontend.model.canvas.layers.base.ILayer;
import util.ObservableBase;
import util.Observer;

public class AbstractStats implements Observer<ILayer>{
	private ObservableBase<AbstractStats> observable;
	
	public AbstractStats() {
		observable = new ObservableBase<>();
	}
	
	public void addObserver(ILayer layer) {
		observable.addObserver(layer);
	}
	
	
	@Override
	public void update(ILayer layer) {
		
	}

}
