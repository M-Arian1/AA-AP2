package view;

import controller.SettingsController;
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

public class RotationAnimation2 extends Transition {

    private Ball ball;
    private AnchorPane Anchorpane;
    private Circle invisibleCircle;
    private double angleSpeed = SettingsController.getAngleSpeedInput();
    private double angle = Math.PI/2;
    public RotationAnimation2(AnchorPane anchorPane, Ball ball, Circle invisibleCircle, double angleSpeedInput, double angleInput) {
        this.ball = ball;
        this.Anchorpane = anchorPane;
        this.invisibleCircle = invisibleCircle;

        this.angleSpeed = angleSpeedInput;
        this.setCycleDuration(Duration.millis(1));
        this.setCycleCount(-1);
        this.angle = angleInput;

        this.ball.setCenterX(invisibleCircle.getCenterX() + invisibleCircle.getRadius() * Math.cos(angle));
        this.ball.setCenterY(invisibleCircle.getCenterY() + invisibleCircle.getRadius() * Math.sin(angle));
        this.ball.getBallText().setX(ball.getCenterX()- SettingsController.getBallRadius());
        this.ball.getBallText().setY(ball.getCenterY()+4);
        this.ball.getBallStick().setStartX(ball.getCenterX());
        this.ball.getBallStick().setStartY(ball.getCenterY());
        this.ball.getBallStick().setVisible(true);
    }

    @Override
    protected void interpolate(double v) {
        //rotate the ball with math.sin and math.cos
        angle += 0.001*angleSpeed;
        ball.setCenterX(invisibleCircle.getCenterX() + invisibleCircle.getRadius() * Math.cos(angle));
        ball.setCenterY(invisibleCircle.getCenterY() + invisibleCircle.getRadius() * Math.sin(angle));
        ball.getBallText().setX(ball.getCenterX()- SettingsController.getBallRadius());
        ball.getBallText().setY(ball.getCenterY()+4);
        ball.getBallStick().setStartX(ball.getCenterX());
        ball.getBallStick().setStartY(ball.getCenterY());
    }

    public double getAngleSpeed() {
        return angleSpeed;
    }

    public void setAngleSpeed(double angleSpeed) {
        this.angleSpeed = angleSpeed;
    }
}
