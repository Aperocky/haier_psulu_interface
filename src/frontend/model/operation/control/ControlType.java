package frontend.model.operation.control;

public enum ControlType {
	ChanceConstraint("Chance Constraint", 0d, 0.5d),
	MaxVelocity("Maximum Velocity", 0d, 1d),
	WayPoints("Number Of Waypoints", 3d, 15d);
	
	private String label;
	private double minValue;
	private double maxValue;
	
	private ControlType(String label, double min, double max) {
		this.label = label;
		this.minValue = min;
		this.maxValue = max;
	}
	
	public String label() {
		return this.label;
	}
	
	public double min() {
		return this.minValue;
	}
	
	public double max() {
		return this.maxValue;
	}

}
