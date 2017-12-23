package model.gamedata.game.gamestats;

import java.util.Map;

import org.apache.commons.math3.distribution.NormalDistribution;

import model.gamedata.game.param.ParamIO;

public class ScheduleRisk {
	public static final double PATH_EFFICIENCY_MEAN = 1.1161d;
	public static final double PATH_EFFICIENCY_STD = 0.0258d;
	public static final double CONSERVATIVE_RATIO = 0.73d;
	public static final double AVERAGE_WAYPOINTS = 6d;
	ParamIO paramIO;
	NormalDistribution distribution;
	
	public ScheduleRisk() {
		paramIO = new ParamIO();
		distribution = new NormalDistribution(PATH_EFFICIENCY_MEAN, PATH_EFFICIENCY_STD);
	}
	
	public double getScheduleRisk(double straightDistance, int surfacingBudget) {
		Map<String, Object> rawMap = paramIO.loadOriginal();
		double maxVelocity = Double.valueOf((String)rawMap.get("max_velocity")) * CONSERVATIVE_RATIO;
		double x = surfacingBudget * AVERAGE_WAYPOINTS * maxVelocity / straightDistance;
		double failProb = 1 - distribution.cumulativeProbability(x);
//		System.out.println("ScheduleRisk line 22. x: " + x + " bunch " + (x-PATH_EFFICIENCY_MEAN) / (Math.sqrt(2)*PATH_EFFICIENCY_STD));
//		System.out.println("ScheduleRisk line 23. maxVelocity: " + maxVelocity + " waypoints: " + waypoints + " failProb: " + failProb);
//		System.out.println("ScheduleRisk line 24. Distance: " + straightDistance + " surfacingBudget: " + surfacingBudget);
//		System.out.println();
		return failProb;
	}
	
//	public static void main(String[] args) {
//		NormalDistribution distribution = new NormalDistribution(PATH_EFFICIENCY_MEAN, PATH_EFFICIENCY_STD);
//		double prob = distribution.cumulativeProbability(1.12d);
//		System.out.println(prob);
//	}

}
