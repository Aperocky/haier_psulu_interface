package model.general;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

/**
 * Responsible for connecting the game to a Timeline
 * @author Feng
 *
 */
public class Model {
	
	private static final int DEFAULT_FPS = 30;
	private static final int MILLIS_PER_SECOND = 1000;
	
	private Game game;
	private Timeline timeline;
	
	public Model() {
		game = new Game();
		timeline = getTimeline();
	}
	
	public void play() {
		timeline.play();
	}
	
	public void step() {
		step(1 / DEFAULT_FPS);
	}
	
	public void pause() {
		timeline.pause();
	}
	
	/**
	 * Update the environment given a certain amount of elapsed time in seconds
	 * @param elapsedTime
	 */
	public void step(double elapsedTime) {
		
	}
	
	private Timeline getTimeline() {
		Timeline tl = new Timeline();
        tl.setCycleCount(Timeline.INDEFINITE);
        KeyFrame frame = new KeyFrame(Duration.millis(MILLIS_PER_SECOND / DEFAULT_FPS),
                e -> step());
        tl.getKeyFrames().add(frame);
        return tl;
	}

}
