package view;

import controller.AvatarFilesController;
import controller.FilesController;
import controller.GameController;
import controller.RegisterController;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
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
//TODO : giving scores to users(and gamemode easy, hard ,...), won the game

public class ScoreMenu extends Application {
    public static Stage stage;
    public static Label scoreLabel;
    public static Label timeLabel;

    @Override
    public void start(Stage stage) throws Exception {
        HighScore.stage = stage;
        AnchorPane highScore = FXMLLoader.load(
                new URL(MainMenu.class.getResource("/fxml/scoreMenu.fxml").toExternalForm()));
        highScore.setEffect(Runn.colorAdjust);

        Scene scene = new Scene(highScore);
        stage.setScene(scene);
        stage.show();
    }


    public void back(MouseEvent mouseEvent) throws Exception {
        new MainMenu().start(stage);
    }
}
