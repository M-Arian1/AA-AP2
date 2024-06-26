package view;

import controller.*;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.*;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class GameMenu extends Application {
    public static GameController gameController ;
    public static ProgressBar progressBar;

    public static Label windLabel;
    public static Label scoreLabel;
    public static Label timeLabel;
    public static Label remainedBallsLabel;
    @FXML
    public static AnchorPane pausePane;
    @FXML
    public static Button pauseButton;
    @FXML
    public Button muteButton;
    @FXML
    public Label musicName;
    public static final int mainCircleRadius = 40;
    public static final int invisibleCircleRadius = 150;

    public static final int ballRadius = SettingsController.getBallRadius();
    public static Stage stage;
    public static String username;
    public static int numberOfBalls = SettingsController.getMaxNumberOfBalls();
    public static int windSpeed = 0;
    public static double windSpeedRate = SettingsController.getWindSpeedRate();
    public static int iceModeCount = 0;
    public static int phase = 0;
    public static int time = SettingsController.getTime();
    public static int score = 0;

    public static double angleSpeedInput = SettingsController.getAngleSpeedInput();
    public static Circle mainCircle = new Circle(400, 300, mainCircleRadius);

    public static Circle invisibleCircle = new Circle(400, 300, invisibleCircleRadius);
    public static Ball shootBall;
    public static boolean isBeingLoaded = false;
    public static Timeline timeTimeline;
    public static MediaPlayer shootMedia = new MediaPlayer(new Media(new File("src/main/resources/effectsMusics/shoot.mp3").toURI().toString()));

    @Override
    public void start(Stage stage) throws Exception {

        numberOfBalls = SettingsController.getMaxNumberOfBalls();
        windSpeedRate = SettingsController.getWindSpeedRate();
        time = SettingsController.getTime();
        score = 0;
        angleSpeedInput = SettingsController.getAngleSpeedInput();
        gameController = new GameController(username);
        GameMenu.stage = stage;
        AnchorPane gamePane = FXMLLoader.load(
                new URL(MainMenu.class.getResource("/fxml/gameMenu.fxml").toExternalForm()));
        gamePane.setEffect(Runn.colorAdjust);
//        ImageView background = new ImageView(new Image(GameMenu.class.getResource("/images/b1.jpg").toString(), 800 ,600, false, false));
//        gamePane.getChildren().add(background);
        BackgroundImage background = new BackgroundImage(new Image(GameMenu.class.getResource("/images/b5.jpg").toString(), 800 ,600, false, false),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        gamePane.setBackground(new Background(background));


        musicName = (Label)((AnchorPane)gamePane.getChildren().get(1)).getChildren().get(7);
        musicName.setMaxWidth(200);
        musicName.setText("Music: "+Runn.mediaFileName);
        pausePane = (AnchorPane)gamePane.getChildren().get(1);
        pausePane.setVisible(false);
        pauseButton = (Button)gamePane.getChildren().get(0);
        gameController.setGamePane(gamePane);
        progressBar = new ProgressBar(0);
        progressBar.setLayoutX(100);
        progressBar.setLayoutY(100);
        windLabel = new Label();
        windLabel.setLayoutX(100);
        windLabel.setLayoutY(150);

        scoreLabel = new Label();
        scoreLabel.setLayoutX(100);
        scoreLabel.setLayoutY(200);
        scoreLabel.setText("Score: "+score);

        timeLabel = new Label();
        timeLabel.setLayoutX(100);
        timeLabel.setLayoutY(250);
        timeLabel.setText(String.format("%02d:%02d", time / 60, time % 60));
        timeTimeLineStart();
        remainedBallsLabel = new Label();
        remainedBallsLabel.setLayoutX(100);
        remainedBallsLabel.setLayoutY(300);
        remainedBallsLabel.setText("Remained Balls: "+numberOfBalls);
        setBallsLabelColor();

        windLabel.setText("WindSpeed: "+windSpeed);

        if(isBeingLoaded){
            NewGameController.loadGame(gamePane);
        }else{
            NewGameController.newGame(gamePane,SettingsController.getMapNumber());
        }
        mainCircle = new Circle(400, 300, mainCircleRadius);
        gamePane.getChildren().add(mainCircle);
        gamePane.getChildren().add(progressBar);
        gamePane.getChildren().add(windLabel);
        gamePane.getChildren().add(scoreLabel);
        gamePane.getChildren().add(timeLabel);
        gamePane.getChildren().add(remainedBallsLabel);
        invisibleCircle = new Circle(400, 300, invisibleCircleRadius);
        invisibleCircle.setVisible(false);
        gamePane.getChildren().add(invisibleCircle);

        shootBall = createBall(gamePane);
        focusTimeLime(gamePane);

        Scene scene = new Scene(gamePane);
        stage.setScene(scene);
        shootBall.requestFocus();
        stage.show();
    }
    @FXML
    public void initialize() {
//        pausePane = (AnchorPane)gamePane.getChildren().get(1);
//        pausePane.setVisible(false);
    }
    private Ball createBall(AnchorPane gamePane) {
        Ball ball = new Ball(ballRadius, 400, 500, numberOfBalls);
        ball.getBallStick().setVisible(false);
        gamePane.getChildren().addAll(ball, ball.getBallText(), ball.getBallStick());

        ball.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if(gameController.isLost()){
                    return;
                }
                String keyName = keyEvent.getCode().getName();
                if (keyName.equals(SettingsController.getShootButton())) {
                    ball.nextBall();
                    shootBall(gamePane, ball.getCenterX(), ball.getCenterY(), ballRadius);
                    numberOfBalls--;
                    remainedBallsLabel.setText("Remained Balls: "+numberOfBalls);
                    setBallsLabelColor();


                    shootMedia.stop();
                    shootMedia = new MediaPlayer(new Media(new File("src/main/resources/effectsMusics/shoot.mp3").toURI().toString()));
                    shootMedia.play();
                    ShootingTheBallAnimation iceAnimation = new ShootingTheBallAnimation(gamePane);
                    iceAnimation.play();
                    gameController.addAllAnimation(iceAnimation);
//                    Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1000),
//                            actionEvent -> shootMedia.stop()));
//                    timeline.setCycleCount(1);
//                    timeline.play();


                } else if (keyName.equals(SettingsController.getIceButton())) {
                    iceModeTimeline(gamePane);

                } else if (keyName.equals("Left")){
                    if(phase == 4 && ball.getCenterX() > 161){
                        ball.setCenterX(ball.getCenterX() - 10);
                        ball.getBallText().setX(ball.getBallText().getX() - 10);
                    }

                }else if (keyName.equals("Right")) {
                    if(phase == 4 && ball.getCenterX() < 639){
                        ball.setCenterX(ball.getCenterX() + 10);
                        ball.getBallText().setX(ball.getBallText().getX() + 10);
                    }
                }else if(keyName.equals("S")) {
                    try {
                        NewGameController.saveGame();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
        return ball;
    }

    private void setBallsLabelColor() {
        double colorPercent = (((double)(SettingsController.getMaxNumberOfBalls()))-(double)numberOfBalls)/(double)SettingsController.getMaxNumberOfBalls() * 255;
//        System.out.println(SettingsController.getMaxNumberOfBalls() + "  " + numberOfBalls);
        remainedBallsLabel.setTextFill(Color.rgb(255 - (int)colorPercent, (int)colorPercent, 0));
    }

    public static void checkPhase(AnchorPane gamePane) throws IOException {
        int maxNumberOfBalls = SettingsController.getMaxNumberOfBalls();
        if (numberOfBalls <= maxNumberOfBalls && numberOfBalls > maxNumberOfBalls*3/4 && phase!=1) {

            phase = 1;

        }else if(numberOfBalls <= maxNumberOfBalls*3/4 && numberOfBalls > maxNumberOfBalls/2 && phase!=2) {

//            System.out.println(numberOfBalls);
            phase = 2;
            Phase2.setIsPhase2Finished(false);
            Timeline timeline = new Timeline(new KeyFrame(Duration.millis(300),
                    actionEvent -> timelineForPhase2(gamePane)));
            timeline.setCycleCount(1);
            timeline.play();

        }else if(numberOfBalls <= maxNumberOfBalls/2 && numberOfBalls > maxNumberOfBalls/4 && phase!=3) {
            phase = 3;
//            System.out.println("yo");
            Phase3.setIsPhase3Finished(false);
            Phase3.timeHandlerChangeVisibility(gamePane);

        }else if (numberOfBalls <= maxNumberOfBalls/4 && numberOfBalls > 0 && phase!=4) {
            phase = 4;
            Phase4.setIsPhase4Finished(false);
            Phase4.windEventHandler(gamePane);

        }else if(numberOfBalls == 0) {
            wonTheGame();
        }
    }

    public static void timelineForPhase2(AnchorPane gamePane) {
        changeRotateSpeed(gamePane);
        Phase2.timeLineHandlerChangeRotateSpeed(gamePane);
        Phase2.timeHandlerChangeRadius(gamePane);
    }

    private static void changeRotateSpeed(AnchorPane gamePane) {
        for (Animation allAnimation : GameMenu.gameController.getAllAnimations()) {
            if (allAnimation instanceof RotationAnimation2) {
                ((RotationAnimation2) allAnimation).setAngleSpeed(-((RotationAnimation2) allAnimation).getAngleSpeed());
            }
        }
        angleSpeedInput = -angleSpeedInput;
    }


    private void focusTimeLime(AnchorPane gamePane) {
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(100),
                actionEvent -> shootBall.requestFocus()));
        timeline.setCycleCount(-1);
        timeline.play();
    }
    private void iceModeTimeline(AnchorPane gamePane) {
        if(iceModeCount < 5) return;
        iceModeCount = 0;
        progressBar.setProgress(0);
        iceMode(gamePane);
        IceAnimation iceAnimation = new IceAnimation(gamePane);
        iceAnimation.play();
        gameController.addAllAnimation(iceAnimation);
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1000*SettingsController.getIceModeTimer()),
                actionEvent -> revertIceMode(gamePane)));
        timeline.setCycleCount(1);
        timeline.play();
    }

    private void iceMode(AnchorPane gamePane) {
        for (Animation allAnimation : GameMenu.gameController.getAllAnimations()) {
            if (allAnimation instanceof RotationAnimation2) {
                ((RotationAnimation2) allAnimation).setAngleSpeed(((RotationAnimation2) allAnimation).getAngleSpeed()/2);
            }
        }
        angleSpeedInput = angleSpeedInput/2;
    }
    private void revertIceMode(AnchorPane gamePane) {
        for (Animation allAnimation : GameMenu.gameController.getAllAnimations()) {
            if (allAnimation instanceof RotationAnimation2) {
                ((RotationAnimation2) allAnimation).setAngleSpeed(((RotationAnimation2) allAnimation).getAngleSpeed()*2);
            }
        }
        angleSpeedInput = angleSpeedInput*2;

    }

    private void shootBall(AnchorPane gamePane, double X, double Y, int radius) {
        Ball shootedball = new Ball(radius, X, Y, numberOfBalls);
        gamePane.getChildren().addAll(shootedball, shootedball.getBallText(), shootedball.getBallStick());
        shootedball.getBallStick().setVisible(false);
        ShootAnimation shootAnimation = new ShootAnimation(gamePane, shootedball);
        gameController.addAllAnimation(shootAnimation);
        shootAnimation.play();
    }

    public static boolean checkCollision(AnchorPane gamePane) {
        Boolean isLost = false;
        ArrayList<Ball> balls = GameMenu.gameController.getConnectedBalls();
        for(Ball ball1 : balls) {
            for(Ball ball2 : balls){
                if(!ball1.equals(ball2)) {
                    if (Math.pow(ball1.getCenterX() - ball2.getCenterX(), 2) + Math.pow(ball1.getCenterY() - ball2.getCenterY(), 2) < Math.pow(2*ball1.getRadius(), 2)) {
                        isLost = true;
                        break;
                    }
                }
            }
        }
        return isLost;

    }

    public void timeTimeLineStart(){
        timeTimeline = new Timeline(new KeyFrame(Duration.millis(1000),
                actionEvent -> {
                    if(pausePane.isVisible()) return;
                    if(gameController.isLost()) {
                        try {
                            this.stop();
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                    if(time==0){
                        try {
                            lostTheGame();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    time--;
                    timeLabel.setText(String.format("%02d:%02d", time / 60, time % 60));
                }));
        timeTimeline.setCycleCount(-1);
        timeTimeline.play();
    }

    public void checkPause(MouseEvent mouseEvent) {
        pausePane.setVisible(!pausePane.isVisible());
        pauseButton.setText(pausePane.isVisible() ? "Resume" : "Pause");
        for (Animation allAnimation : GameMenu.gameController.getAllAnimations()) {
            if (pausePane.isVisible()) {
                allAnimation.pause();
            } else {
                allAnimation.play();
            }
        }
        for (Ball ball : GameMenu.gameController.getConnectedBalls()) {
            if(pausePane.isVisible()) {
                ball.isPaused = true;
                ball.setVisible(false);
                ball.getBallStick().setVisible(false);
                ball.getBallText().setVisible(false);
            }
            else {
                ball.isPaused = false;
                ball.setVisible(true);
                ball.getBallStick().setVisible(true);
                if(!ball.getBallText().getText().equals("0"))ball.getBallText().setVisible(true);
            }
        }
        if (pausePane.isVisible()){
            shootBall.setVisible(false);
            shootBall.getBallText2().setVisible(false);
            windLabel.setVisible(false);
            progressBar.setVisible(false);
            mainCircle.setVisible(false);
            pausePane.requestFocus();
        } else {
            shootBall.setVisible(true);
            shootBall.getBallText2().setVisible(true);
            windLabel.setVisible(true);
            progressBar.setVisible(true);
            mainCircle.setVisible(true);
            shootBall.requestFocus();
        }


    }

    public void back(MouseEvent mouseEvent) throws Exception {
        timeTimeline.stop();

        for (Animation allAnimation : GameMenu.gameController.getAllAnimations()) {
            allAnimation.stop();
        }
        GameMenu.gameController.getAllAnimations().clear();
        Phase2.setIsPhase2Finished(true);
        Phase3.setIsPhase3Finished(true);
        Phase4.setIsPhase4Finished(true);
        GameMenu.gameController.getConnectedBalls().clear();

        new MainMenu().start(stage);

    }

    public void checkSave(MouseEvent mouseEvent) throws IOException {
        NewGameController.saveGame();
    }

    public void checkRestart(MouseEvent mouseEvent) {
        checkPause(mouseEvent);
        NewGameController.newGame(gameController.getGamePane(),SettingsController.getMapNumber());

    }

    public void checkMuteMusic(MouseEvent mouseEvent) {
        Runn.mediaPlayer.setMute(!Runn.mediaPlayer.isMute());
        SettingsController.setMuted(!SettingsController.isMuted());
        muteButton.setText(!SettingsController.isMuted() ? "Music : OFF" : "Music : ON");

    }

    public void checkGuide(MouseEvent mouseEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Guide");
        alert.setHeaderText("Controller Guide");
        alert.setContentText("Shoot Ball with:  " + SettingsController.getShootButton() + "\nIceMode with:     " + SettingsController.getIceButton());
        alert.showAndWait();
    }

    public void checkNextMusic(MouseEvent mouseEvent) {
        File folder = new File("src/main/resources/media/");
        File[] listOfFiles = folder.listFiles();

        for(int i=0; i<listOfFiles.length; i++){
            if(listOfFiles[i].getName().equals(Runn.mediaFileName)){
                Runn.mediaPlayer.stop();
                Runn.mediaFileName = listOfFiles[(i + 1) % listOfFiles.length].getName();
                Runn.media = new Media(new File("src/main/resources/media/" + listOfFiles[(i + 1) % listOfFiles.length].getName()).toURI().toString());
                Runn.mediaPlayer = new MediaPlayer(Runn.media);
                Runn.mediaPlayer.setAutoPlay(true);
                break;
            }
        }
        musicName.setText("Music: "+Runn.mediaFileName);
    }
    public void checkPrevMusic(MouseEvent mouseEvent) {
        File folder = new File("src/main/resources/media/");
        File[] listOfFiles = folder.listFiles();

        for(int i=0; i<listOfFiles.length; i++){
            if(listOfFiles[i].getName().equals(Runn.mediaFileName)){
                Runn.mediaPlayer.stop();
                Runn.mediaFileName = listOfFiles[(i - 1 + listOfFiles.length) % listOfFiles.length].getName();
                Runn.media = new Media(new File("src/main/resources/media/" + listOfFiles[(i - 1 + listOfFiles.length) % listOfFiles.length].getName()).toURI().toString());
                Runn.mediaPlayer = new MediaPlayer(Runn.media);
                Runn.mediaPlayer.setAutoPlay(true);
                break;
            }
        }
        musicName.setText("Music: "+Runn.mediaFileName);
    }
    public static void lostTheGame() throws IOException {
        for (Ball connectedBall : GameMenu.gameController.getConnectedBalls()) {
            connectedBall.setVisible(true);
            connectedBall.getBallText2().setVisible(false);
            connectedBall.getBallText().setVisible(true);
            connectedBall.getBallStick().setVisible(true);
        }
        gameController.setLost(true);
        pauseButton.setVisible(false);
        windLabel.setVisible(false);
        progressBar.setVisible(false);
        scoreLabel.setVisible(false);
        timeLabel.setVisible(false);
        remainedBallsLabel.setVisible(false);
        timeTimeline.stop();
        for (Animation allAnimation : GameMenu.gameController.getAllAnimations()) {
            allAnimation.stop();
        }
        GameMenu.gameController.getAllAnimations().clear();
        Phase2.setIsPhase2Finished(true);
        Phase3.setIsPhase3Finished(true);
        Phase4.setIsPhase4Finished(true);
        GameMenu.gameController.getConnectedBalls().clear();


        gameController.getGamePane().setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1000),
                actionEvent -> {
                    try {
                        lostTimeline();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }));
        timeline.setCycleCount(1);
        timeline.play();

        if(score > FilesController.getUserByUsername(username).getScore(SettingsController.getLevel())){
            FilesController.changeUserScore(FilesController.getUserByUsername(username), score, SettingsController.getLevel());
            FilesController.changeUserTime(FilesController.getUserByUsername(username), time, SettingsController.getLevel());
        }else if(score == FilesController.getUserByUsername(username).getScore(SettingsController.getLevel())){
            if(time < FilesController.getUserByUsername(username).getTime(SettingsController.getLevel())){
                FilesController.changeUserScore(FilesController.getUserByUsername(username), score, SettingsController.getLevel());
                FilesController.changeUserTime(FilesController.getUserByUsername(username), time, SettingsController.getLevel());
            }
        }

    }
    public static void wonTheGame() throws IOException {
        for (Ball connectedBall : GameMenu.gameController.getConnectedBalls()) {
            connectedBall.setVisible(true);
            connectedBall.getBallText2().setVisible(false);
            connectedBall.getBallText().setVisible(true);
            connectedBall.getBallStick().setVisible(true);
        }
        gameController.setLost(true);
        pauseButton.setVisible(false);
        windLabel.setVisible(false);
        progressBar.setVisible(false);
        scoreLabel.setVisible(false);
        timeLabel.setVisible(false);
        remainedBallsLabel.setVisible(false);
        timeTimeline.stop();
        for (Animation allAnimation : GameMenu.gameController.getAllAnimations()) {
            allAnimation.stop();
        }
        GameMenu.gameController.getAllAnimations().clear();
        Phase2.setIsPhase2Finished(true);
        Phase3.setIsPhase3Finished(true);
        Phase4.setIsPhase4Finished(true);
        GameMenu.gameController.getConnectedBalls().clear();



        gameController.getGamePane().setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1000),
                actionEvent -> {
                    try {
                        wonTimeLine();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }));
        timeline.setCycleCount(1);
        timeline.play();

        if(score > FilesController.getUserByUsername(username).getScore(SettingsController.getLevel())){
            FilesController.changeUserScore(FilesController.getUserByUsername(username), score, SettingsController.getLevel());
            FilesController.changeUserTime(FilesController.getUserByUsername(username), time, SettingsController.getLevel());
        }else if(score == FilesController.getUserByUsername(username).getScore(SettingsController.getLevel())){
            if(time < FilesController.getUserByUsername(username).getTime(SettingsController.getLevel())){
                FilesController.changeUserScore(FilesController.getUserByUsername(username), score, SettingsController.getLevel());
                FilesController.changeUserTime(FilesController.getUserByUsername(username), time, SettingsController.getLevel());
            }
        }
    }

    private static void wonTimeLine() throws Exception {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("You Won");
        alert.setHeaderText("You Won");
        alert.setContentText("Your Score: " + score + "\nYour Time: " + time);
        alert.show();

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1000),
                actionEvent -> {
                    try {
                        exitTimeline();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }));
        timeline.setCycleCount(1);
        timeline.play();
    }
    private static void lostTimeline() throws Exception {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("You Lost");
        alert.setHeaderText("You Lost");
        alert.setContentText("Your Score: " + score + "\nYour Time: " + time);
        alert.show();

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1000),
                actionEvent -> {
                    try {
                        exitTimeline();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }));
        timeline.setCycleCount(1);
        timeline.play();
    }

    private static void exitTimeline() throws Exception {
        new MainMenu().start(stage);
    }

}
