package view;

import controller.FilesController;
import controller.GameController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Avatar;
import model.Ball;
import model.User;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class GameMenu extends Application {
    public static GameController gameController ;
    public static final int mainCircleRadius = 40;
    public static final int invisibleCircleRadius = 150;

    public static final int ballRadius = 16;
    public static Stage stage;
    public static String username;
    public static int numberOfBalls = 8;
    public static Circle mainCircle = new Circle(400, 300, mainCircleRadius);

    public static Circle invisibleCircle = new Circle(400, 300, invisibleCircleRadius);

    @Override
    public void start(Stage stage) throws Exception {
        gameController = new GameController(username);
        GameMenu.stage = stage;
        AnchorPane gamePane = FXMLLoader.load(
                new URL(MainMenu.class.getResource("/fxml/gameMenu.fxml").toExternalForm()));

//        Circle mainCircle = new Circle(400, 300, mainCircleRadius);
        mainCircle = new Circle(400, 300, mainCircleRadius);
        gamePane.getChildren().add(mainCircle);

//        Circle invisibleCircle = new Circle(400, 300, invisibleCircleRadius);
        invisibleCircle = new Circle(400, 300, invisibleCircleRadius);
        invisibleCircle.setVisible(false);
        gamePane.getChildren().add(invisibleCircle);

        Ball shootBall = createBall(gamePane);


        Scene scene = new Scene(gamePane);
        stage.setScene(scene);
        shootBall.requestFocus();
        stage.show();
    }

    private Ball createBall(AnchorPane gamePane) {
        Ball ball = new Ball(ballRadius, 400, 500, numberOfBalls);

        gamePane.getChildren().addAll(ball, ball.getBallText());

        ball.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                String keyName = keyEvent.getCode().getName();
                if (keyEvent.getCode().equals(KeyCode.SPACE)) {
                    ball.nextBall();
                    shootBall(gamePane, ball.getCenterX(), ball.getCenterY(), 16);
                    numberOfBalls--;
                } else if (keyName.equals("Tab")) {

                } else if (keyName.equals("Left")){

                }else if (keyName.equals("Right")) {

                }
            }
        });
        return ball;
    }

    private void shootBall(AnchorPane gamePane, double X, double Y, int radius) {
        Ball shootedball = new Ball(radius, X, Y, numberOfBalls);
        gamePane.getChildren().addAll(shootedball, shootedball.getBallText());
        ShootAnimation shootAnimation = new ShootAnimation(gamePane, shootedball);
        gameController.addAllAnimation(shootAnimation);
        shootAnimation.play();
    }


}
