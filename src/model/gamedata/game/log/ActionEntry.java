package model.gamedata.game.log;

public class ActionEntry {
	private int uid;
	private String date;
	private double[] currPosition;
	private double[] plannedPath;
	private double[] executedPath;
	private double plannedPathLength;
	private double executedPathLength;
	private double scheduleRisk;
	private double collisionRisk;
	private double remainingRisk;
	private double riskLevel;
	private int numMidpoints;
	private int surfacingLeft;
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public double[] getCurrPosition() {
		return currPosition;
	}
	public void setCurrPosition(double[] currPosition) {
		this.currPosition = currPosition;
	}
	public double[] getPlannedPath() {
		return plannedPath;
	}
	public void setPlannedPath(double[] plannedPath) {
		this.plannedPath = plannedPath;
	}
	public double[] getExecutedPath() {
		return executedPath;
	}
	public void setExecutedPath(double[] executedPath) {
		this.executedPath = executedPath;
	}
	public double getPlannedPathLength() {
		return plannedPathLength;
	}
	public void setPlannedPathLength(double plannedPathLength) {
		this.plannedPathLength = plannedPathLength;
	}
	public double getExecutedPathLength() {
		return executedPathLength;
	}
	public void setExecutedPathLength(double executedPathLength) {
		this.executedPathLength = executedPathLength;
	}
	public double getScheduleRisk() {
		return scheduleRisk;
	}
	public void setScheduleRisk(double scheduleRisk) {
		this.scheduleRisk = scheduleRisk;
	}
	public double getCollisionRisk() {
		return collisionRisk;
	}
	public void setCollisionRisk(double collisionRisk) {
		this.collisionRisk = collisionRisk;
	}
	public double getRemainingRisk() {
		return remainingRisk;
	}
	public void setRemainingRisk(double remainingRisk) {
		this.remainingRisk = remainingRisk;
	}
	public double getRiskLevel() {
		return riskLevel;
	}
	public void setRiskLevel(double riskLevel) {
		this.riskLevel = riskLevel;
	}
	public int getNumMidpoints() {
		return numMidpoints;
	}
	public void setNumMidpoints(int numMidpoints) {
		this.numMidpoints = numMidpoints;
	}
	public int getSurfacingLeft() {
		return surfacingLeft;
	}
	public void setSurfacingLeft(int surfacingLeft) {
		this.surfacingLeft = surfacingLeft;
	}
	
	

}
