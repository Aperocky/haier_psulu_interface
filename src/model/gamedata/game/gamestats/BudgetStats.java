package model.gamedata.game.gamestats;

public class BudgetStats {

	private double totalRiskBudget;
	private double currentRiskBudget;
	private double expectedRiskBudget;
	private int currentSurfacingBudget;

	public BudgetStats() {

	}

	public double getTotalRiskBudget() {
		return totalRiskBudget;
	}

	public void setTotalRiskBudget(double totalRiskBudget) {
		this.totalRiskBudget = totalRiskBudget;
		this.currentRiskBudget = totalRiskBudget;
		this.expectedRiskBudget = totalRiskBudget;
	}

	public void resetCurrentRiskBudget() {
		this.currentRiskBudget = this.totalRiskBudget;
	}

	public void setCurrentRiskBudget(double riskBudget) {
		currentRiskBudget = riskBudget;
	}

	public double getCurrentRiskBudget() {
		return currentRiskBudget;
	}

	public double getExpectedRiskBudget() {
		return expectedRiskBudget;
	}

	public void setExpectedRiskBudget(double expectedRiskBudget) {
		this.expectedRiskBudget = expectedRiskBudget;
	}
	
	public void setCurrentSurfacingBudget(int bgt) {
		this.currentSurfacingBudget = bgt;
	}

	public int getCurrentSurfacingBudget() {
		return currentSurfacingBudget;
	}

}
