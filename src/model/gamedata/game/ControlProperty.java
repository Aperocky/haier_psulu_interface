package model.gamedata.game;

import frontend.util.BiConsumer;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

/**
 * A container for all the user's inputs used for path planning
 * 
 * @author Feng
 *
 */
public class ControlProperty {

	private DoubleProperty riskBudgetProperty;
	private DoubleProperty horizonRadiusProperty;

	public ControlProperty() {
		riskBudgetProperty = new SimpleDoubleProperty();
		horizonRadiusProperty = new SimpleDoubleProperty();
	}

	public void setChangedHandler(BiConsumer<Double, Double> changedHandler) {
		riskBudgetProperty.addListener((observable, oldv, newv) -> {
			changedHandler.accept(newv.doubleValue(), getHorizonRadius());
		});
		horizonRadiusProperty.addListener((observable, oldv, newv) -> {
			changedHandler.accept(getRiskBudget(), newv.doubleValue());
		});
	}

	public void setRiskBudget(double risk) {
		riskBudgetProperty.set(risk);
	}

	public void setHorizonRadiusProperty(double horizon) {
		horizonRadiusProperty.set(horizon);
	}

	public Double getRiskBudget() {
		return riskBudgetProperty.doubleValue();
	}

	public Double getHorizonRadius() {
		return horizonRadiusProperty.doubleValue();
	}

	public DoubleProperty getRiskBudgetProperty() {
		return riskBudgetProperty;
	}

	public DoubleProperty getHorizonRadiusProperty() {
		return horizonRadiusProperty;
	}

}
