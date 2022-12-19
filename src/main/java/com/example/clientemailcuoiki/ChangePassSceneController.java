package com.example.clientemailcuoiki;

import com.example.clientemailcuoiki.Account.Account;
import com.example.clientemailcuoiki.Client.Client;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ChangePassSceneController implements Initializable {

    public TextField oldPass;
    public TextField newPass;

    Stage stage;

    Scene scene;

    Parent root;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    public void backToDetails(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("UserDetails.fxml"));
        root = loader.load();
        stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("Message!");
        stage.setScene(scene);
        stage.show();
    }

    public void changePass(ActionEvent actionEvent) throws IOException {
        String oldPassID = oldPass.getText();
        String newPassID = newPass.getText();

        if(oldPassID.isEmpty() || newPassID.isEmpty()){
            loadAlert("Enter old and new Password");
            return;
        }else if(newPassID.length() < 8){
            loadAlert("New password must be 8 characters or longer");
            return;
        }

        boolean changed = Client.changePassword(oldPassID, newPassID);
        if (changed) {
            loadAlert("Changed succesefully !!!");
        } else {
            loadAlert("Changed Failed");
        }
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

}
