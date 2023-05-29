package model;

public class ConvertBall {

    public static SaveableBall ConvertBallToSavableBall(Ball ball) {
        SaveableBall saveableBall;
        saveableBall = new SaveableBall();
        saveableBall.radius = ball.getRadius();
        saveableBall.number = ball.number;
        saveableBall.text = ball.getBallText().getText();
        saveableBall.X = ball.getCenterX();
        saveableBall.Y = ball.getCenterY();
        saveableBall.X1 = ball.getBallStick().getStartX();
        saveableBall.Y1 = ball.getBallStick().getStartY();
        saveableBall.X2 = ball.getBallStick().getEndX();
        saveableBall.Y2 = ball.getBallStick().getEndY();
        saveableBall.angle = ball.angle;
        return saveableBall;
    }
    public static Ball ConvertSavableBallToBall(SaveableBall saveableBall) {
        Ball ball;
        ball = new Ball((int)saveableBall.radius, saveableBall.X, saveableBall.Y, saveableBall.number, saveableBall.angle);
        ball.getBallText().setText(saveableBall.text);
        ball.getBallStick().setStartX(saveableBall.X1);
        ball.getBallStick().setStartY(saveableBall.Y1);
        ball.getBallStick().setEndX(saveableBall.X2);
        ball.getBallStick().setEndY(saveableBall.Y2);
        return ball;
    }

}
