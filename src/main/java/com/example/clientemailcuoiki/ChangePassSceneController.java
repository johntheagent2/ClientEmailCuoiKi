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

    public void changePass(ActionEvent actionEvent) {
        String oldPassID = oldPass.getText();
        String newPassID = newPass.getText();

        boolean changed = Client.changePassword(oldPassID, newPassID);
        if (changed) {
            System.out.println("Changed succesefully !!!");
        } else {
            System.out.println("Changed Failed");
        }
    }

}
