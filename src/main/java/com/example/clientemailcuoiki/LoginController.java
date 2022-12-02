package com.example.clientemailcuoiki;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.Scanner;

public class LoginController implements Initializable {

    @FXML
    Button loginButton;
    @FXML
    TextArea userID;

    Stage stage;

    Scene scene;

    Parent root;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void Login(ActionEvent actionEvent) throws IOException {
        String username = userID.getText();
        SwitchScene(actionEvent, username);
    }

    public void SwitchScene(ActionEvent actionEvent, String userName) throws IOException {
//        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("MessageScene.fxml")))
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MessageScene.fxml"));
        root = loader.load();

        MessageSceneController messageSceneController = loader.getController();

        messageSceneController.connectToServer(userName);

        stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("Message!");
        stage.setScene(scene);
        stage.show();
    }
}
