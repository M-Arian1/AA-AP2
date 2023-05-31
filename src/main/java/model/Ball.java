package model;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import view.GameMenu;
import view.ShootAnimation;

public class Ball extends Circle {


    private int number;
    private boolean isNumVisible = true;
    private Text text = null;
    private Stick stick = null;
    public boolean isPaused = false;

    private ShootAnimation shootAnimation = null;

    public Ball(int radius, double X, double Y, int number) {
        super(X, Y, radius);
        this.setFill(Color.BLACK);
        this.number = number;
        text = new Text(X - radius, Y + 4, number + "");
        text.setFill(Color.WHITE);
        text.setWrappingWidth(radius * 2);
        text.prefHeight(radius * 2);
        text.setTextAlignment(TextAlignment.CENTER);

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
    public Double getAngle(){
        return Math.atan2(this.getCenterY()-300,this.getCenterX()-400);
    }
    public Integer getNumber(){
        return number;
    }

    public boolean getIsNumVisible() {
        return isNumVisible;
    }

    public void setIsNumVisible(boolean isNumVisible) {
        this.isNumVisible = isNumVisible;
        this.text.setVisible(isNumVisible);
    }
    public Text getBallText2(){
        return text;
    }

}
