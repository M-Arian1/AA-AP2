package model;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class Avatar extends Circle {
    public String avatarText;
    public Avatar(String avatarText) {
        super(200, 200, 50);
        this.avatarText = avatarText;
        this.setFill(new ImagePattern(
                new Image(Game.class.getResource("/images/avatars/"+avatarText).toExternalForm())));
    }
}
