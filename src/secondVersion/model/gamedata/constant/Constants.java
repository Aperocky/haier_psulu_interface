package secondVersion.model.gamedata.constant;

public enum Constants {
	Surfacing(7d),
	RiskBudget(0.1d),
	UNIT(20d);
	
	private final Double value;
	private Constants(double v) {
		value = v;
	}
	
	public double value() {
		return value;
	} 

}
