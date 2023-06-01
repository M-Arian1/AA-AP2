package view;

import controller.AvatarFilesController;
import controller.FilesController;
import controller.GameController;
import controller.RegisterController;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Avatar;
import model.Level;
import model.User;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class HighScore extends Application {
    public static Stage stage;
    public static Level level = Level.EASY;
    public static Text levelText = new Text("EASY");

    @Override
    public void start(Stage stage) throws Exception {
        HighScore.stage = stage;
        AnchorPane highScore = FXMLLoader.load(
                new URL(MainMenu.class.getResource("/fxml/highScore.fxml").toExternalForm()));
        highScore.setEffect(Runn.colorAdjust);

        ArrayList<User> userSorted = FilesController.getUsers();
//        for (int i=0;i<userSorted.size();i++) {
//            for(int j=0;j<userSorted.size();j++){
//                if(userSorted.get(i).getUsername().equals(userSorted.get(j).getUsername()) && i!=j){
//                    FilesController.deleteUser(userSorted.get(j));
//                    userSorted.remove(j);
//                    j--;
//                }
//            }
//        }
//        for (int i=0;i<userSorted.size();i++) {
//            //a is a random number between 1 and 100
//            userSorted.get(i).setScore(Level.EASY,(int) (Math.random() * 100 + 1));
//            userSorted.get(i).setScore(Level.MEDIUM,(int) (Math.random() * 100 + 1));
//            userSorted.get(i).setScore(Level.HARD,(int) (Math.random() * 100 + 1));
//            userSorted.get(i).setTime(Level.EASY,(int) (Math.random() * 100 + 1));
//            userSorted.get(i).setTime(Level.MEDIUM,(int) (Math.random() * 100 + 1));
//            userSorted.get(i).setTime(Level.HARD,(int) (Math.random() * 100 + 1));
//        }
//        FilesController.rewriteUsers(userSorted);

        Collections.sort(userSorted,new Comparator<User>() {
            @Override
            public int compare(User u1, User u2) {
                if(u2.getScore(level)==u1.getScore(level))
                    return u1.getTime(level)-u2.getTime(level);
                return u2.getScore(level)-u1.getScore(level);
            }
        });

        VBox vbox = new VBox();
        levelText.setStyle("-fx-font-size: 30");
        vbox.getChildren().add(levelText);

        for(int i=0; i<10; i++){
            if(i>=userSorted.size())
                break;
            User user = userSorted.get(i);
            HBox hbox = new HBox();
            hbox.setSpacing(20);
            Text iText = new Text(i+1+":     ");
            iText.setStyle("-fx-font-size: 20; -fx-font-weight: bold; -fx-background-color: #ff0000;");
            Text usernameText = new Text(user.getUsername());
            usernameText.setStyle("-fx-font-size: 20");

            Text scoreText = new Text(user.getScore(level)+"");
            scoreText.setStyle("-fx-font-size: 20");

            Text timeText = new Text(user.getTime(level)+"");
            timeText.setStyle("-fx-font-size: 20");
            if(i==0){
                hbox.setStyle("-fx-background-color: #ffd700;");
            }else if(i==1){
                hbox.setStyle("-fx-background-color: #c0c0c0;");
            }else if(i==2){
                hbox.setStyle("-fx-background-color: #cd7f32;");
            }else {
                hbox.setStyle("-fx-background-color: #ffffff;");
            }
            hbox.getChildren().add(iText);
            hbox.getChildren().add(usernameText);
            hbox.getChildren().add(scoreText);
            hbox.getChildren().add(timeText);
            vbox.getChildren().add(hbox);
        }
        vbox.setLayoutX(300);
        vbox.setLayoutY(120);
        vbox.setAlignment(javafx.geometry.Pos.CENTER);

        highScore.getChildren().add(vbox);
        Scene scene = new Scene(highScore);
        stage.setScene(scene);
        stage.show();
    }


    public void back(MouseEvent mouseEvent) throws Exception {
        new MainMenu().start(stage);
    }

    public void checkLevelEasy(MouseEvent mouseEvent) throws Exception {
        level = Level.EASY;
        levelText.setText("EASY");
        new HighScore().start(stage);
    }

    public void checkLevelMedium(MouseEvent mouseEvent) throws Exception {
        level = Level.MEDIUM;
        levelText.setText("MEDIUM");
        new HighScore().start(stage);
    }

    public void checkLevelHard(MouseEvent mouseEvent) throws Exception {
        level = Level.HARD;
        levelText.setText("HARD");
        new HighScore().start(stage);
    }
}
