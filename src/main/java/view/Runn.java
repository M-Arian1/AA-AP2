package view;

import controller.FilesController;
import controller.GameController;
import controller.RegisterController;
import controller.SettingsController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.application.Application;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Scene;
//import javafx.scene.control.Label;
//import javafx.scene.control.TextField;
//import javafx.scene.input.KeyEvent;
//import javafx.scene.input.MouseEvent;
//import javafx.scene.layout.BorderPane;
//import javafx.stage.Stage;
import java.io.File;
import java.net.URL;


public class Runn extends Application{

    public static Stage stage;
    public static Media media;
    public static MediaPlayer mediaPlayer;
    public static String mediaFileName;
    public static ColorAdjust colorAdjust = new ColorAdjust();

    public static void main(String[] args) throws Exception {
        launch();
    }


    @Override
    public void start(Stage stage) throws Exception {
        //TODO : filechoose panel
        Runn.stage = stage;
        AnchorPane runnpane = FXMLLoader.load(
                new URL(MainMenu.class.getResource("/fxml/loginMenu.fxml").toExternalForm()));
        Scene scene = new Scene(runnpane);
        stage.setScene(scene);
        stage.show();

        stage.getIcons().add(new Image("file:src/main/resources/images/icon2.png"));


        mediaFileName = "1.mp3";
        File mediasFile = new File("src/main/resources/media/" + mediaFileName);
        media = new Media(mediasFile.toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);

        MainMenu.username = FilesController.getCurrentUser();
        FilesController.loadSettingsControllerOb();
        colorAdjust.setContrast(0);
        colorAdjust.setHue(0);
        colorAdjust.setBrightness(0);
        colorAdjust.setSaturation(SettingsController.isBW() ? -1 : 0);

        if(FilesController.getCurrentUser()!=null){
            MainMenu.gameController = new GameController(FilesController.getCurrentUser());
            MainMenu.username = FilesController.getCurrentUser();
            new MainMenu().start(Runn.stage);
        }else{
            new LoginMenu().start(Runn.stage);
        }
    }
}
