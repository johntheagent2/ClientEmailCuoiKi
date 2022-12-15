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
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
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
        System.out.println("");
        System.out.println("***********************************");
        System.out.println("*****       Login Form        *****");
        System.out.println("***********************************");
        System.out.println("");

            boolean logged = Client.login(userID.getText(), password.getText());

            if (logged) {
                System.out.println("Welcome back " + userID.getText());
                SwitchScene(actionEvent);
            } else {
                System.out.println("Email or password is incorrect. Try again.");

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
}
