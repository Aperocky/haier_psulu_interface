package frontend.model.operation.control;

public enum ControlType {
	ChanceConstraint("Step Risk", "chance_constraint", 0d, 0.2d, 0, 20), // 0 - 0.4
	// MaxVelocity("Max Leg Length(mile)", "max_velocity", 0.05d, 0.2d, 5, 20);
	WayPoints("Waypoints per Step", "waypoints", 2, 12, 1, 12);  //4-8-12
	
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
