package model.gamedata.game.gamestats;

import java.util.Map;

import org.apache.commons.math3.distribution.NormalDistribution;

import model.gamedata.game.param.ParamIO;

public class ScheduleRisk {
	public static final double PATH_EFFICIENCY_MEAN = 1.1161d; // 1.1161d
	public static final double PATH_EFFICIENCY_STD = 0.05d; // 0.0258
	public static final double CONSERVATIVE_RATIO = 0.75d;
	public static final double AVERAGE_WAYPOINTS = 6.8d;
	ParamIO paramIO;
	NormalDistribution distribution;
	
	public ScheduleRisk() {
		paramIO = new ParamIO();
		distribution = new NormalDistribution(PATH_EFFICIENCY_MEAN, PATH_EFFICIENCY_STD);
	}
	
	public double getScheduleRisk(double straightDistance, int surfacingBudget) {
		if(straightDistance < 0.0001)
			return 0;
		Map<String, Object> rawMap = paramIO.loadOriginal();
		double maxVelocity = Double.valueOf((String)rawMap.get("max_velocity")) * CONSERVATIVE_RATIO;
		double x = surfacingBudget * AVERAGE_WAYPOINTS * maxVelocity / straightDistance;
		double failProb = 1 - distribution.cumulativeProbability(x);
		return failProb;
	}


}
