package com.example.clientemailcuoiki;

import com.example.clientemailcuoiki.Client.Client;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    public Button registerButton;
    @FXML
    Button loginButton;
    @FXML
    TextField userID;

    @FXML
    TextField password;

    Stage stage;

    Scene scene;

    Parent root;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        connectToServer();
    }

    public void Login(ActionEvent actionEvent) throws IOException {
            boolean logged = Client.login(userID.getText(), password.getText());

            if (logged) {
                SwitchScene(actionEvent);
            } else {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("AlertScene.fxml"));
                root = loader.load();
                AlertSceneController alert = loader.getController();
                alert.showAlert("Wrong Email or Password!!!");
                stage = new Stage();
                scene = new Scene(root);
                stage.setTitle("ALERT!");
                stage.setScene(scene);
                stage.show();

        }
    }

    public void connectToServer(){
        boolean connected = Client.connectToServer();
        if (!connected) {
            System.out.println("There is a problem with the server. Try again Later");
            System.exit(0);
        }
    }

    public void SwitchScene(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MessageScene.fxml"));
        root = loader.load();

        stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("Message!");
        stage.setScene(scene);
        stage.show();
    }

    public void registerButton(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("RegisterScene.fxml"));
        root = loader.load();

        stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("Message!");
        stage.setScene(scene);
        stage.show();
    }
}
