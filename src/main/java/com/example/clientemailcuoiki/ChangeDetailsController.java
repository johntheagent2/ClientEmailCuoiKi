package com.example.clientemailcuoiki;

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
import java.util.ResourceBundle;

public class ChangeDetailsController implements Initializable {
    public TextArea nameDetail;
    public TextArea emailDetail;
    public Button backButton;
    public TextArea phoneNumDetail;
    public Button updateDetails;
    Stage stage;
    Scene scene;
    Parent root;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        
    }

    public void showCurrentDetails(){
        String email = Client.requestUserDetails();
        String name = Client.requestUserName();
        String phoneNum = Client.requestUserPhoneNum();
        emailDetail.setText(email);
        nameDetail.setText(name);
        phoneNumDetail.setText(phoneNum);
    }

    public void backToDetails(ActionEvent actionEvent) throws IOException {
        switchScene(actionEvent,"UserDetails.fxml");
    }

    public void updateDetails(ActionEvent actionEvent) throws IOException {
        String name = nameDetail.getText();
        String phoneNum = phoneNumDetail.getText();
        Client.changeUserDetails(name, phoneNum);
        switchScene(actionEvent,"UserDetails.fxml");
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
}
