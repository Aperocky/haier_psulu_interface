package frontend.model.operation.control;

public enum ControlType {
	ChanceConstraint("Chance Constraint", "chance_constraint", 0.1d, 0.3d),
	MaxVelocity("Maximum Velocity", "max_velocity", 0.1d, 0.2d),
	WayPoints("Number Of Waypoints", "waypoints", 3d, 15d);
	
	private String label;
	private String key;
	private double minValue;
	private double maxValue;
	
	private ControlType(String label, String yamlName, double min, double max) {
		this.label = label;
		this.key = yamlName;
		this.minValue = min;
		this.maxValue = max;
	}
	
	public String label() {
		return this.label;
	}
	
	public String key() {
		return this.key;
	}
	
	public double min() {
		return this.minValue;
	}
	
	public double max() {
		return this.maxValue;
	}

}
