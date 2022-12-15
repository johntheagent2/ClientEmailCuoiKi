package com.example.clientemailcuoiki;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DetailedMailSceneController implements Initializable {

    public TextArea messageText;
    public TextField senderField;
    public TextField subjectField;

    Stage stage;

    Scene scene;

    Parent root;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void showDetailedEmail(Email emailInfo) {
        System.out.print(emailInfo.getSender() + " " + emailInfo.getSubject()+ " " + emailInfo.getMainBody());
        senderField.setText(emailInfo.getSender());
        subjectField.setText(emailInfo.getSubject());
        messageText.setText(emailInfo.getMainBody());
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
}