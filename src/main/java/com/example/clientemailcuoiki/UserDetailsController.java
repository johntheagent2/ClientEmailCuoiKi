package com.example.clientemailcuoiki;

import com.example.clientemailcuoiki.Account.Account;
import com.example.clientemailcuoiki.Client.Client;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class UserDetailsController implements Initializable {
    public TextArea nameDetail;
    public TextArea emailDetail;
    public TextArea phoneNumDetail;
    public Button changePassButton;
    public Button backButton;
    public Button blockButton;

    Stage stage;

    Scene scene;

    Parent root;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showAccDetails();
    }

    public void showAccDetails(){
        String email = Client.requestUserDetails();
        String name = Client.requestUserName();
        String phoneNum = Client.requestUserPhoneNum();
        emailDetail.setText(email);
        nameDetail.setText(name);
        phoneNumDetail.setText(phoneNum);
    }

    public void changePassword(ActionEvent actionEvent) throws IOException {
        switchScene(actionEvent, "ChangePassScene.fxml");

    }

    public void backToMain(ActionEvent actionEvent) throws IOException {
        switchScene(actionEvent, "MessageScene.fxml");

    }

    public void switchScene(ActionEvent actionEvent, String sceneName) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(sceneName));
        root = loader.load();
        stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("Message!");
        stage.setScene(scene);
        stage.show();
    }


    public void toBlockScene(ActionEvent actionEvent) throws IOException {
        switchScene(actionEvent, "BlockScene.fxml");
    }

    public void changeDetails(ActionEvent actionEvent) throws IOException {
        switchScene(actionEvent, "ChangeDetails.fxml");
    }
}
