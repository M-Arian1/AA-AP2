package view;

import controller.SettingsController;
import javafx.animation.Transition;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class IceAnimation extends Transition {

    private AnchorPane Anchorpane;

    public IceAnimation(AnchorPane anchorPane) {
        this.setCycleCount(1);
        this.setCycleDuration(Duration.millis(SettingsController.getIceModeTimer()*1000));
    }

    @Override
    protected void interpolate(double v) {
        GameMenu.mainCircle.setFill(Color.rgb(0,0,(int)(50*(1-v))));
        GameMenu.mainCircle.setCenterX( 400 + 10 * Math.sin(v * 10 * Math.PI));
        GameMenu.mainCircle.setCenterY( 300 + 10 * Math.cos(v * 10 * Math.PI));
        GameMenu.mainCircle.setRadius( 40 + 10 * (1-v));

//
//        GameMenu.mainCircle.setCenterX( 400 + 10 * Math.random());
//        GameMenu.mainCircle.setCenterY( 300 + 10 * Math.random());

    }
}
