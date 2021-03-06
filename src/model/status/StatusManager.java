package model.status;

import util.ObservableBase;

public class StatusManager extends ObservableBase<StatusManager>{
	private boolean executing;
	private boolean planning;
	
	// feasible indicates whether the current plan is feasible
	private boolean feasible;
	private boolean message;
	
	private boolean success;
	private boolean failure;
	
	public StatusManager() {
		feasible = false;
		message = false;
	}
	
	// TODO: check if planning
	public void setExecuting(boolean execute) {
		executing = execute;
		notifyObservers(this);
	}
	
	public boolean isExecuting() {
		return executing;
	}
	
	public void setMessageOn() {
		message = true;
	}
	
	public void setMessageOff() {
		message = false;
	}
	
	public boolean isMessageOn() {
		return message;
	}
	
	// TODO: check if executing
	public void setPlanning(boolean plan) {
		planning = plan;
		notifyObservers(this);
	}
	
	public boolean isPlanning() {
		return planning;
	}
	
	public void setSuccess(boolean success) {
		this.success = success;
		notifyObservers(this);
	}
	
	public void setFailure(boolean failed) {
		this.failure = failed;
		notifyObservers(this);
	}
	
	public boolean isSuccess() {
		return success;
	}
	
	public boolean isFailure() {
		return failure;
	}
	
	public void setFeasible(boolean feasible) {
		this.feasible = feasible;
		notifyObservers(this);
	}
	
	public boolean isFeasible() {
		return feasible;
	}

}
