package view;

import controller.FilesController;
import controller.GameController;
import controller.NewGameController;
import controller.SettingsController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Avatar;
import model.User;

import java.net.URL;

import static view.Runn.colorAdjust;

public class MainMenu extends Application {
    public static GameController gameController;
    public static Stage stage;
    public static String username;

    @Override
    public void start(Stage stage) throws Exception {
        FilesController.loadSettingsControllerOb();
        Runn.colorAdjust.setSaturation(SettingsController.isBW() ? -1 : 0);
        Runn.mediaPlayer.setMute(SettingsController.isMuted());
        username = FilesController.getCurrentUser();
        MainMenu.stage = stage;
        AnchorPane mainPane = FXMLLoader.load(
                new URL(MainMenu.class.getResource("/fxml/mainMenu.fxml").toExternalForm()));
        mainPane.setEffect(colorAdjust);

        VBox vbox = new VBox();
        Avatar avatar = new Avatar(FilesController.getUsers().get(0).getAvatar(), 50);
        for (User user : FilesController.getUsers()) {
            if(user.getUsername().equals(username)) {
                avatar = new Avatar(user.getAvatar(), 50);
                break;
            }
        }
        vbox.setLayoutX(370);
        vbox.setAlignment(javafx.geometry.Pos.CENTER);
        vbox.getChildren().add(avatar);
        Text text = new Text("hello " + username);
        text.setStyle("-fx-font-size: 20");
        vbox.getChildren().add(text);
        mainPane.getChildren().add(vbox);
        Scene scene = new Scene(mainPane);
        stage.setScene(scene);
        stage.show();
    }


    public void checkNewGame(MouseEvent mouseEvent) throws Exception {

        GameMenu.username = username;
        GameMenu.isBeingLoaded = false;
        new GameMenu().start(stage);
    }

    public void checkLoadGame(MouseEvent mouseEvent) throws Exception {
        if(FilesController.loadOneGame(MainMenu.username)==null || FilesController.loadOneGame(MainMenu.username).getBalls()==null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText("No saved game found!");
            alert.showAndWait();
            return;
        }
        GameMenu.username = username;
        GameMenu.isBeingLoaded = true;

        new GameMenu().start(stage);
    }

    public void checkProfile(MouseEvent mouseEvent) throws Exception {
        new ChangeProfileMenu().start(MainMenu.stage);
    }
    public void checkHighScores(MouseEvent mouseEvent) throws Exception {
        new HighScore().start(stage);
    }

    public void checkSettings(MouseEvent mouseEvent) throws Exception {
        new SettingsMenu().start(stage);
    }

    public void checkExit(MouseEvent mouseEvent) {
        stage.close();
    }

}
