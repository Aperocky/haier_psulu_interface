package frontend.model.canvas.layers.concrete.obstaclelayer;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import frontend.model.unit.obstacle.Obstacle;
import javafx.scene.input.MouseEvent;
import util.ObservableBase;

public class ObstacleMaster extends ObservableBase<ObstacleMaster>{
	
	private List<Obstacle> obstacles;
	
	public ObstacleMaster() {
		obstacles = new ArrayList<>();
	}
	
	public void forEach(Consumer<Obstacle> handler){
		obstacles.forEach(obstacle -> handler.accept(obstacle));
	}
	
	public void activateAll() {
		obstacles.forEach(obstacle -> obstacle.deactivate());
	}
	
	public void deactivateAll() {
		obstacles.forEach(obstacle -> obstacle.activate());
	}
	
	public void clear() {
		obstacles.clear();
		notifyObservers(this);
	}
	
	public void remove(Obstacle obstacle) {
		obstacles.remove(obstacle);
		notifyObservers(this);
	}
	
	public void add(Obstacle obstacle) {
		obstacle.addEventFilter(MouseEvent.MOUSE_DRAGGED, evt -> notifyObservers(this));
		obstacles.add(obstacle);
		notifyObservers(this);
	}
	
	

}
