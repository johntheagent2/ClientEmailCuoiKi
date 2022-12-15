package com.example.clientemailcuoiki;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.EventObject;
import java.util.ResourceBundle;

public class ComposeSceneController implements Initializable {
    @FXML
    TextArea messageText;

    public TextField receiverField;
    public TextField subjectField;

    Stage stage;

    Scene scene;

    Parent root;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void sendMail(ActionEvent actionEvent) throws IOException {

        System.out.println("");
        System.out.println("***********************************");
        System.out.println("*****       New Email         *****");
        System.out.println("***********************************");
        System.out.println("");

        String receiver = receiverField.getText();

        String subject = subjectField.getText();

        String mainBody = messageText.getText();


        boolean emailSent = Client.sendEmail(receiver, subject, mainBody);

        if (emailSent) {
            System.out.println("Email was sent Succesfully");
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
