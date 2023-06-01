package view;

import javafx.animation.Transition;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class ShootingTheBallAnimation extends Transition {

    private AnchorPane Anchorpane;

    public ShootingTheBallAnimation(AnchorPane anchorPane) {
        this.setCycleCount(1);
        this.setCycleDuration(Duration.millis(300));
    }

    @Override
    protected void interpolate(double v) {
        GameMenu.mainCircle.setRadius( 40 + 15 * Math.sin(v * Math.PI));

    }
}
