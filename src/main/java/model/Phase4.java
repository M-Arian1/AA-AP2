package model;

import controller.SettingsController;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import view.GameMenu;
import view.RotationAnimation2;

import static view.GameMenu.*;

public class Phase4 {
    private static boolean isPhase4Finished = false;
    public static void windEventHandler (AnchorPane gamePane){
        if(isPhase4Finished) return;
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(5000),
                actionEvent -> Phase4.changeWindSpeed(gamePane)));
        timeline.setCycleCount(-1);
        timeline.play();
    }

    private static void changeWindSpeed(AnchorPane gamePane) {
        if(isPhase4Finished) return;
        if(windSpeedRate >= 0){
            windSpeed += windSpeedRate;
            if(windSpeed > SettingsController.getMaxWindSpeed()){
                windSpeedRate = -windSpeedRate;
            }
        }
        if(windSpeedRate < 0){
            windSpeed += windSpeedRate;
            if(windSpeed < -SettingsController.getMaxWindSpeed()){
                windSpeedRate = -windSpeedRate;
            }
        }
        label.setText("WindSpeed: " + windSpeed);
    }


    public static boolean isIsPhase4Finished() {
        return isPhase4Finished;
    }

    public static void setIsPhase4Finished(boolean isPhase4Finished) {
        Phase4.isPhase4Finished = isPhase4Finished;
    }
}
