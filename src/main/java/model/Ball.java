package model;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import view.GameMenu;
import view.ShootAnimation;

public class Ball extends Circle {

    public int radius;
    public int number;
    private Text text = null;
    private Stick stick = null;

    private ShootAnimation shootAnimation = null;
    public double angle;

    public Ball(int radius, double X, double Y, int number, double angle) {
        super(X, Y, radius);
        this.setFill(Color.BLACK);
        this.number = number;
        text = new Text(X - radius, Y + 4, number + "");
        text.setFill(Color.WHITE);
        text.setWrappingWidth(radius * 2);
        text.prefHeight(radius * 2);
        text.setTextAlignment(TextAlignment.CENTER);
        this.angle = angle;

        stick = new Stick((int) X, (int) Y, 400, 300);
        stick.setFill(Color.BLACK);
    }


    public Text getBallText() {
        return text;
    }
    public Stick getBallStick() {
        return stick;
    }
    public void nextBall(){

        this.number--;
        this.text.setText(number + "");
        if(number < 1){
            this.setVisible(false);
            this.text.setVisible(false);
        }
    }

    public ShootAnimation getShootAnimation() {
        return shootAnimation;
    }

    public void setShootAnimation(ShootAnimation shootAnimation) {
        this.shootAnimation = shootAnimation;
    }
}
