package frontend.model.operation.control;

public enum ControlType {
	ChanceConstraint("Risk", "chance_constraint", 0.02d, 0.3d, 10, 75),
	MaxVelocity("Maximum Lag Length", "max_velocity", 0.1d, 0.2d, 10, 20),
	WayPoints("Number of Waypoints", "waypoints", 3d, 15d, 3, 15);
	
	private String label;
	private String key;
	private double minValue;
	private double maxValue;
	private int minValueUI;
	private int maxValueUI;
	
	private ControlType(String label, String yamlName, double min, double max, int uiMin, int uiMax) {
		this.label = label;
		this.key = yamlName;
		this.minValue = min;
		this.maxValue = max;
		this.minValueUI = uiMin;
		this.maxValueUI = uiMax;
	}
	
	public String label() {
		return this.label;
	}
	
	public String key() {
		return this.key;
	}
	
	public double algoMin() {
		return this.minValue;
	}
	
	public double algoMax() {
		return this.maxValue;
	}
	
	public int uiMin() {
		return this.minValueUI;
	}
	
	public int uiMax() {
		return this.maxValueUI;
	}

}
