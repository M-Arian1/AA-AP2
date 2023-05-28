package view;

import controller.FilesController;
import controller.GameController;
import controller.RegisterController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.fxml.FXML;
import model.Avatar;
import model.Game;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Scene;
//import javafx.scene.control.Label;
//import javafx.scene.control.TextField;
//import javafx.scene.input.KeyEvent;
//import javafx.scene.input.MouseEvent;
//import javafx.scene.layout.BorderPane;
//import javafx.stage.Stage;
import java.net.URL;


public class SignupMenu extends Application{

    public static Stage stage;
    public TextField username;
    public PasswordField password;
    public ScrollPane scrollPane;
    public String avatarText = " ";



    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        SignupMenu.stage = stage;
        AnchorPane signupPane = FXMLLoader.load(
                new URL(MainMenu.class.getResource("/fxml/signupMenu.fxml").toExternalForm()));


        HBox avatarPane = new HBox();
        for(int i=1; i<=10; i++){
//            avatarPane.getChildren().add(new ImageView(new Image(Game.class.getResource("/images/avatars/p"+i+".png").toString(), 100, 100, false, false)));
            avatarPane.getChildren().add(new Avatar("p"+i+".png"));
        }

        scrollPane = new ScrollPane();
        scrollPane.setContent(avatarPane);
        scrollPane.setLayoutX(347.0);
        scrollPane.setLayoutY(117.0);
        scrollPane.setPrefHeight(50);
        scrollPane.setPrefWidth(200.0);
        signupPane.getChildren().add(scrollPane);

        Scene scene = new Scene(signupPane);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void initialize() {
//        username.textProperty().addListener((observable, oldText, newText)->{
//            label.setText("hello " + newText);});
    }

    public void openLoginMenu(MouseEvent mouseEvent) throws Exception {
        new LoginMenu().start(SignupMenu.stage);
    }

    public void checkSignup(MouseEvent mouseEvent) throws Exception {
        if(RegisterController.checkSignup(username.getText(), password.getText(), avatarText).equals("ok")){
            FilesController.addUserByString(username.getText(), password.getText(), avatarText);
            MainMenu.gameController = new GameController(username.getText());
            new MainMenu().start(SignupMenu.stage);
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Signup Error");
            alert.setContentText(RegisterController.checkSignup(username.getText(), password.getText(), avatarText));
            alert.showAndWait();
        }
    }

    public void checkGuest(MouseEvent mouseEvent) throws Exception {
        MainMenu.gameController = new GameController("guest");
        new MainMenu().start(SignupMenu.stage);
    }
}
