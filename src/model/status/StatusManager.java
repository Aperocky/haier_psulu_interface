package model.status;

import util.ObservableBase;

public class StatusManager extends ObservableBase<StatusManager>{
	private boolean executing;
	private boolean planning;
	
	private boolean success;
	private boolean failure;
	
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
	
	public void setSuccess() {
		success = true;
		notifyObservers(this);
	}
	
	public void setFailure() {
		failure = true;
		notifyObservers(this);
	}
	
	public boolean isSuccess() {
		return success;
	}
	
	public boolean isFailure() {
		return failure;
	}
	

}
