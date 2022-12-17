package com.example.clientemailcuoiki;

import com.example.clientemailcuoiki.Client.Client;
import com.example.clientemailcuoiki.Client.Email;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.web.HTMLEditor;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ReplySceneController implements Initializable {
    public TextArea messageText;
    public TextField receiver;
    public TextField subjectField;
    public Button sendButton;
    public Button backButton;
    public HTMLEditor HTMLText;

    Email emailInfo;

    Stage stage;

    Scene scene;

    Parent root;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void showReplyEmail(Email email){
        receiver.setText(email.getSender());
        subjectField.setText(email.getSubject());
        String message = email.getMainBody() +
                        "\n***********************************************\n" +
                        "******       This is a Reply Message       ******\n" +
                        "*************************************************\n";

        HTMLText.setHtmlText(message);
        emailInfo = email;
    }

    public void replyButton(ActionEvent actionEvent) throws IOException {

        boolean emailSent = Client.sendEmail(emailInfo.getSender(), emailInfo.getSubject(), HTMLText.getHtmlText());

        if (emailSent) {
            System.out.println("Email was sent Successfully");
            SwitchScene(actionEvent);
        } else {
            System.out.println("The receiver does not exist. Try again.");
        }
    }

    public void backToMain(ActionEvent actionEvent) throws IOException {
        SwitchScene(actionEvent);
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
