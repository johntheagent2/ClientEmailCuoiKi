package com.example.clientemailcuoiki;

import com.example.clientemailcuoiki.Client.Client;
import com.example.clientemailcuoiki.Client.Email;
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
import javafx.scene.web.HTMLEditor;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ForwardSceneController implements Initializable {

    public TextField receiver;
    public TextField subjectField;
    public Button sendButton;
    public Button backButton;
    Email emailInfo;

    @FXML
    public HTMLEditor htmlText;

    Stage stage;

    Scene scene;

    Parent root;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void showForwardMail(Email email){
        subjectField.setText(email.getSubject());
        String message = email.getMainBody() +
                "******       Forward from "+ email.getSender() +"      ******\n";

        htmlText.setHtmlText(message);
        emailInfo = email;
    }

    public void forwardEmail(ActionEvent actionEvent) throws IOException {
        String receiverMail = receiver.getText();

        boolean emailSent = Client.sendEmail(receiverMail, emailInfo.getSubject(), htmlText.getHtmlText());

        if (emailSent) {
            System.out.println("Email was sent Succesfully");
            SwitchScene(actionEvent);
        } else {
            System.out.println("The receiver does not exist. Try again.");
        }
    }

    public void backToMain(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MessageScene.fxml"));
        root = loader.load();
        stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("Message!");
        stage.setScene(scene);
        stage.show();
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
