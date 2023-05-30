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
import javafx.scene.text.Text;
import javafx.util.Duration;
import model.Ball;
import model.Game;

import java.util.ArrayList;

public class ShootAnimation extends Transition {

    private Ball ball;
    private AnchorPane Anchorpane;


    public ShootAnimation(AnchorPane anchorPane, Ball ball) {
        this.ball = ball;
        this.Anchorpane = anchorPane;

        this.setCycleDuration(Duration.millis(1000));
        this.setCycleCount(-1);
    }

    @Override
    protected void interpolate(double v) {

        if(checkCollision(Anchorpane,ball)) {
            ball.setCenterY(ball.getCenterY() - 5);
            ball.getBallText().setY(ball.getBallText().getY() - 5);
        }else{
            this.stop();
        }
    }

    private Boolean checkCollision(AnchorPane gamePane, Ball ball) {
        Boolean isLost = false;
        ArrayList<Ball> balls = GameMenu.gameController.getConnectedBalls();
        for (Ball ball1 : balls) {
//            if (ball.getBoundsInParent().intersects(ball1.getBoundsInParent())) {
            if(!ball.equals(ball1)) {
                if (Math.pow(ball.getCenterX() - ball1.getCenterX(), 2) + Math.pow(ball.getCenterY() - ball1.getCenterY(), 2) < Math.pow(2* SettingsController.getBallRadius(), 2)) {
                    isLost = true;
                    break;
                }
            }
        }


        if (isLost) {
            for (Animation allAnimation : GameMenu.gameController.getAllAnimations()) {
                allAnimation.stop();
            }
            return false;
            //TODO: show lost page
            //please dont f up
        }else{
            if(Math.pow(ball.getCenterY()-300,2) + Math.pow(ball.getCenterX()-400,2) < Math.pow(GameMenu.invisibleCircleRadius,2)) {
                this.stop();
                GameMenu.gameController.addConnectedBall(ball);
                gamePane.getChildren().add(ball.getBallStick());
                GameMenu.gameController.removeAllAnimation(this);

                RotationAnimation2 rotationAnimation = new RotationAnimation2(gamePane, ball, GameMenu.invisibleCircle, GameMenu.angleSpeedInput ,Math.PI/2);
                GameMenu.gameController.addAllAnimation(rotationAnimation);
                rotationAnimation.play();
                return false;
            }
        }
        return true;
    }
}
