package com.example.clientemailcuoiki;

import com.example.clientemailcuoiki.Client.Client;
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
import javafx.scene.web.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ComposeSceneController implements Initializable {
    @FXML
    TextArea messageText;

    public TextField receiverField;
    public TextField subjectField;

    public HTMLEditor htmlText;

    Stage stage;

    Scene scene;

    Parent root;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void sendMail(ActionEvent actionEvent) throws IOException {
        String receiver = receiverField.getText();

        String subject = subjectField.getText();

        String mainBody = htmlText.getHtmlText();

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime now = LocalDateTime.now();
        String date = dtf.format(now);

        if(!mailVerify(receiver)){
            return;
        }

        boolean emailSent = Client.sendEmail(receiver, subject, mainBody, date);

        if (emailSent) {
            loadAlert("Email was sent Succesfully");
            SwitchScene(actionEvent);
        } else {
            loadAlert("The receiver does not exist. Try again.");
        }

    }

    public void backToMain(ActionEvent actionEvent) throws IOException {
        SwitchScene(actionEvent);
    }

    public boolean mailVerify(String receiver) throws IOException {
        if(receiver.isEmpty()){
            loadAlert("Please enter receiver mail");
            return false;
        }
        return true;
    }

    public void loadAlert(String alertMessage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AlertScene.fxml"));
        root = loader.load();
        AlertSceneController alert = loader.getController();
        alert.showAlert(alertMessage);
        stage = new Stage();
        scene = new Scene(root);
        stage.setTitle("ALERT!");
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
