package secondVersion.model.gamedata.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javafx.geometry.Point2D;
import secondVersion.frontend.model.canvas.layers.LayerMaster;
import secondVersion.frontend.model.canvas.layers.LayerType;
import secondVersion.util.ObservableBase;

/**
 * GameStats is observed by front end in order to properly update the value of risk budget,
 * surfacing budget and current position.
 * @author Feng
 *
 */
public class GameStats extends ObservableBase<GameStats>{
	
	private List<Point2D> plannedPath;
	private List<Point2D> executedPath;
	private List<List<Point2D>> obstacles;
	private Point2D currentPosition;
    private Point2D finalDestination;
    private double currentRiskBudget;
    private double currentSurfacingBudget;
    
    public GameStats(LayerMaster layerMaster) {
    	super();
    	plannedPath = new ArrayList<>();
    	executedPath = new ArrayList<>();
    	obstacles = new ArrayList<>();
    	this.addObserver(layerMaster.getLayer(LayerType.PathLayer));
    }
    
    public Point2D getCurrentPosition() {
    	return currentPosition;
    }
    
    public Point2D getDestination() {
    	return finalDestination;
    }
    
    public double getCurrentRiskBudget() {
    	return currentRiskBudget;
    }
    
    public double getCurrentSurfacingBudget() {
    	return currentSurfacingBudget;
    }
    
    public List<List<Point2D>> getObstacles() {
    	return obstacles;
    }
    
    public void setPlannedPath(List<Point2D> plan) {
    	plannedPath = plan;
    	notifyObservers(this);
    }
    
    public void setExecutedPath(List<Point2D> executed) {
    	executedPath = executed;
    	notifyObservers(this);
    }
    
    public List<Point2D> getPlannedPath() {
    	return Collections.unmodifiableList(plannedPath);
    }
    
    public List<Point2D> getExecutedPath() {
    	return Collections.unmodifiableList(executedPath);
    }

}
