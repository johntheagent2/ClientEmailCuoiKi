package com.example.clientemailcuoiki;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class MessageSceneController implements Initializable {

    @FXML
    Button sendButton;

    @FXML
    TextArea messageText;

    @FXML
    TextField textField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void sendMessage(ActionEvent actionEvent) {
        String message = messageText.getText();
    }
}
