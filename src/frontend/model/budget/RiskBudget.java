package frontend.model.budget;

import frontend.util.mixedprogressbar.MixedProgressBar;
import javafx.geometry.Pos;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.StringConverter;
import model.gamedata.game.gamestats.GameStats;
import util.Observer;

public class RiskBudget extends StackPane implements Observer<GameStats> {

	private static final double SPACING = 10d;
	private static final double HEIGHT = 25d;
	private HBox hbox;
	private MixedProgressBar progressBar;
	private Label title;
	private Label percent;

	public RiskBudget(double width, double height) {
		this.setPrefSize(width, height);
		hbox = new HBox();
		hbox.setPrefSize(width, height);
		hbox.setSpacing(SPACING);
		hbox.setAlignment(Pos.CENTER);
		
		// Progress Bar
		VBox vbox = new VBox();
		vbox.setSpacing(0);
		vbox.setAlignment(Pos.CENTER);
		progressBar = new MixedProgressBar(width * 0.7d, HEIGHT, 3);
		progressBar.setColor(Color.web("#C6DAFC"), 0);
		progressBar.setColor(Color.web("#3A83FF"), 1);
		progressBar.setColor(Color.web("#3D3E3B"), 2);
		NumberAxis axis = new NumberAxis(-10, 0, 1);
		axis.setMaxSize(width * 0.68d, HEIGHT);
		axis.setTranslateX(width * 0.003d);
		axis.setTranslateY(-5);
		axis.setTickMarkVisible(true);
		axis.setTickLabelFormatter(new StringConverter<Number>() {
	        @Override
	        public String toString(Number object) {
	            return (object.intValue() * -1) + "%";
	        }

	        @Override
	        public Number fromString(String string) {
	            return 0;
	        }
	    });
		vbox.getChildren().addAll(progressBar, axis);
		vbox.setTranslateY(15d);

		title = new Label("Risk");
		title.setPrefWidth(40);
		percent = new Label("");
		percent.setPrefWidth(40);
		
		hbox.getChildren().addAll(title, vbox, percent);

		this.getChildren().addAll(hbox);
	}

	@Override
	public void update(GameStats stats) {
//		double total = stats.getTotalRiskBudget();
//		double current = stats.getCurrentRiskBudget();
//		double expected = stats.getExpectedRiskBudget();
//		double schedule = stats.getCurrentScheduleRisk();
//		double currRatio = current / total;
//		double expectedRatio = expected / total;
//		progressBar.setProgress(currRatio, 0);
//		progressBar.setProgress(expectedRatio, 1);
//		if (current > 0)
//			percent.setText("" + (double) Math.round(currRatio * 100) / 10 + "%");
//		else {
//			percent.setText("out"); 
//			percent.setTextFill(Color.RED);
//		}
		double total_risk = stats.getTotalRiskBudget();
		double current_risk = stats.getCurrentRiskBudget();
		double expect_risk = stats.getExpectedRiskBudget();
		double schedule_risk = stats.getCurrentScheduleRisk();
		double curr_ratio = current_risk / total_risk;
		double expect_ratio = expect_risk / total_risk;
		progressBar.setProgress(curr_ratio, 0);
		progressBar.setProgress(expect_ratio, 1);
		progressBar.setProgress(schedule_risk, 2);
		if (curr_ratio-schedule_risk > 0)
			percent.setText("" + (double) Math.round((curr_ratio - schedule_risk) * 100) / 10 + "%");
		else {
			percent.setText("out"); 
			percent.setTextFill(Color.RED);
		}
	}

}
