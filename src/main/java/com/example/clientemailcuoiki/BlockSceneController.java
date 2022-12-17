package com.example.clientemailcuoiki;

import com.example.clientemailcuoiki.Client.Client;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class BlockSceneController implements Initializable {
    public TextField blockEmailField;
    public Button blockButton;
    public Button backButton;

    Stage stage;

    Scene scene;

    Parent root;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    public void blockUser(ActionEvent actionEvent) throws IOException {
        String blockEmail = blockEmailField.getText();
        Client.blockUserEmail(blockEmail);
        boolean blocked = Client.blockUserEmail(blockEmail);
        if (blocked) {
            System.out.println("Blocked successfully !!!");
        } else {
            System.out.println("Blocked Failed");
        }
    }

    public void backToDetail(ActionEvent actionEvent) throws IOException {
        switchScene(actionEvent, "UserDetails.fxml");
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
