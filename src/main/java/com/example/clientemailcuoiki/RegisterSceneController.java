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
import java.sql.SQLException;
import java.util.ResourceBundle;

public class RegisterSceneController implements Initializable {

    public TextField emailField;
    public TextField passwordField;
    public TextField nameField;
    public TextField phoneNumField;
    public Button registerButton;
    public Button backButton;

    Stage stage;

    Scene scene;

    Parent root;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void registerAccount(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
            String email = emailField.getText();
            String password = passwordField.getText();
            String name = nameField.getText();
            String phoneNum = phoneNumField.getText();

            boolean registered = Client.register(email, password, name, phoneNum);
            if (registered) {
                System.out.println("Registered successfully !!!");
            } else {
                System.out.println("This email is already in use. Try again.");
            }
    }

    public void backToLogin(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
        root = loader.load();
        stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("Message!");
        stage.setScene(scene);
        stage.show();
    }
}
