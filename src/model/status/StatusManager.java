package model.status;

import util.ObservableBase;

public class StatusManager extends ObservableBase<StatusManager>{
	private boolean executing;
	private boolean planning;
	
	// TODO: check if planning
	public void setExecuting(boolean execute) {
		executing = execute;
		notifyObservers(this);
	}
	
	public boolean isExecuting() {
		return executing;
	}
	
	// TODO: check if executing
	public void setPlanning(boolean plan) {
		planning = plan;
		notifyObservers(this);
	}
	
	public boolean isPlanning() {
		return planning;
	}
	

}
