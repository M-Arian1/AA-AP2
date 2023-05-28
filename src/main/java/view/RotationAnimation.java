package view;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.RotateTransition;
import javafx.animation.Transition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import model.Ball;

import java.util.ArrayList;

public class RotationAnimation extends Transition {

    private Ball ball;
    private AnchorPane Anchorpane;
    private Circle invisibleCircle;

    public RotationAnimation(AnchorPane anchorPane, Ball ball, Circle invisibleCircle) {
        this.ball = ball;
        this.Anchorpane = anchorPane;
        this.invisibleCircle = invisibleCircle;
        this.setCycleDuration(Duration.millis(1000));
        this.setCycleCount(-1);
    }

    @Override
    protected void interpolate(double v) {
       Rotate rotate = new Rotate(2, invisibleCircle.getCenterX(), invisibleCircle.getCenterY());
       ball.getTransforms().add(rotate);
       ball.getBallText().getTransforms().add(rotate);

    }
}
