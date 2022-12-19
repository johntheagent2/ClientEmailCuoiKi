package com.example.clientemailcuoiki;

import com.example.clientemailcuoiki.Client.Client;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddLabelSceneController implements Initializable {



    public TextField labelField;
    Stage stage;

    Scene scene;

    Parent root;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void addLabel(ActionEvent actionEvent) throws IOException {
        boolean result = Client.addLabel(labelField.getText());
        if(result){
            alert("Label added successfully!");
        }else{
            alert("Label added failed!");
        }

    }

    public void alert(String alertMessage) throws IOException {
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
}
