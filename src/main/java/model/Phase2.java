package model;

import controller.SettingsController;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import view.GameMenu;
import view.RotationAnimation2;

public class Phase2 {
    public static void timeLineHandlerChangeRotateSpeed(AnchorPane gamePane) {
        int randomTime = 4000 + (int) (Math.random() * (8000 - 4000));

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(randomTime),
                actionEvent -> Phase2.changeRotateSpeed(gamePane)));
        timeline.setCycleCount(1);
        timeline.play();
    }
    public static void changeRotateSpeed(AnchorPane gamePane) {
        for (Animation allAnimation : GameMenu.gameController.getAllAnimations()) {
            if (allAnimation instanceof RotationAnimation2) {
                ((RotationAnimation2) allAnimation).setAngleSpeed(-((RotationAnimation2) allAnimation).getAngleSpeed());
            }
        }
        GameMenu.angleSpeedInput = -GameMenu.angleSpeedInput;
        timeLineHandlerChangeRotateSpeed(gamePane);
    }



    public static void timeHandlerChangeRadius(AnchorPane gamePane) {
        for (Ball connectedBall : GameMenu.gameController.getConnectedBalls()) {
            connectedBall.setRadius(SettingsController.getBallRadius());
        }
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1000),
                actionEvent -> Phase2.changeRadius(gamePane)));
        timeline.setCycleCount(1);
        timeline.play();
    }
    private static void timeHandlerTimeHandlerChangeRadius(AnchorPane gamePane) {
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1000),
                actionEvent -> Phase2.timeHandlerChangeRadius(gamePane)));
        timeline.setCycleCount(1);
        timeline.play();
    }
    private static void changeRadius(AnchorPane gamePane) {
        int randomRadius = 10 + (int) (Math.random() * (15 - 10));

        for (Ball connectedBall : GameMenu.gameController.getConnectedBalls()) {
            connectedBall.setRadius(connectedBall.getRadius() * (100+randomRadius)/100);
        }
        if(GameMenu.checkCollision(gamePane)==true){
                for (Animation allAnimation : GameMenu.gameController.getAllAnimations()) {
                    allAnimation.stop();
                }
                //TODO: show lost page
        }
        timeHandlerTimeHandlerChangeRadius(gamePane);
    }


}
