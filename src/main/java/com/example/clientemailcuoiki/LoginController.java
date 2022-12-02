package com.example.clientemailcuoiki;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class LoginController implements Initializable {

    @FXML
    Button loginButton;
    @FXML
    TextArea userID;

    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String username;
    private String ipAddress;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void Login(ActionEvent actionEvent) throws IOException {
        String username = userID.getText();
        Socket socket = new Socket("localhost", 1234);
        Client client = new Client(socket, username, socket.getRemoteSocketAddress().toString());
    }
}
