package view;

import controller.AvatarFilesController;
import controller.FilesController;
import controller.GameController;
import controller.RegisterController;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
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
    private static String avatarText="";



    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        SignupMenu.stage = stage;
        AnchorPane signupPane = FXMLLoader.load(
                new URL(MainMenu.class.getResource("/fxml/signupMenu.fxml").toExternalForm()));
        signupPane.setEffect(Runn.colorAdjust);

        HBox avatarPane = new HBox();
        for (Avatar avatar : AvatarFilesController.avatarHandler()) {
            avatar.setOnMouseClicked(new EventHandler<>(){
                @Override
                public void handle(MouseEvent event) {
                    avatarText = avatar.getAvatarName();
                    avatar.setRadius(70);
                    avatarPane.getChildren().forEach(node -> {
                        if(!node.equals(avatar)){
                            ((Avatar)node).setRadius(50);
                        }
                    });
                }
            });
            avatarPane.getChildren().add(avatar);
        }
        avatarPane.setSpacing(10);
        scrollPane = new ScrollPane();
        scrollPane.setContent(avatarPane);
        scrollPane.setLayoutX(300.0);
        scrollPane.setLayoutY(117.0);
        scrollPane.setPrefHeight(170);
        scrollPane.setPrefWidth(400.0);

        Button RandomAvatarButton = new Button("Random Avatar");
        RandomAvatarButton.setLayoutX(300.0);
        RandomAvatarButton.setLayoutY(300.0);
        RandomAvatarButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                avatarText = AvatarFilesController.getRandomAvatar().getAvatarName();
                avatarPane.getChildren().forEach(node -> {
                    if(((Avatar)node).getAvatarName().equals(avatarText)){
                        ((Avatar)node).setRadius(70);
                    }
                    else{
                        ((Avatar)node).setRadius(50);
                    }
                });
            }
        });
        signupPane.getChildren().add(scrollPane);
        signupPane.getChildren().add(RandomAvatarButton);



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
            MainMenu.username = username.getText();
            FilesController.setCurrentUser(FilesController.getUserByUsername(username.getText()));
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
        MainMenu.username = "guest";
        FilesController.setCurrentUser(FilesController.getUserByUsername("guest"));
        new MainMenu().start(SignupMenu.stage);
    }
}
