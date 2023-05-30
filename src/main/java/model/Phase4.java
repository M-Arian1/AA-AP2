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
    public static void windEventHandler (AnchorPane gamePane){
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(5000),
                actionEvent -> Phase4.changeWindSpeed(gamePane)));
        timeline.setCycleCount(-1);
        timeline.play();
    }

    private static void changeWindSpeed(AnchorPane gamePane) {
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


}
