package com.example.clientemailcuoiki;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class MessageSceneController implements Initializable{

    @FXML
    Button sendButton;

    @FXML
    TextArea messageText;

    @FXML
    TextField textField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void connectToServer(String username) throws IOException {
        Socket socket = new Socket("localhost", 1234);
        Client client = new Client(socket, username, socket.getRemoteSocketAddress().toString());
//        sendMessageButton(client);

    }

    public void sendMessageButton(Client client) {
                String message = messageText.getText();
                client.sendMessage(message);
    }

}
