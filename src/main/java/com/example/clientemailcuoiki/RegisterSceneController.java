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

    public void registerAccount(ActionEvent actionEvent) throws SQLException, ClassNotFoundException, IOException {
            String email = emailField.getText();
            String password = passwordField.getText();
            String name = nameField.getText();
            String phoneNum = phoneNumField.getText();

            if(!verifyPassword(email, password, name, phoneNum)){
                return;
            }

            boolean registered = Client.register(email, password, name, phoneNum);
            if (registered) {
                System.out.println("Registered successfully !!!");
            } else {
                loadAlert("This email is already in use. Try again !!!");
            }
    }

    public boolean verifyPassword(String email, String password,String name, String phoneNum) throws IOException {
        if(!email.endsWith("@email.com")){
            loadAlert("Your email need to end with @email.com!");
            return false;
        }else if(password.length() < 8){
            loadAlert("Password need to be 8 characters or longer!");
            return false;
        }else if(name.isEmpty()){
            loadAlert("Please enter your name!");
            return false;
        }else if(!phoneNum.matches("^[0-9]{10}$")){
            loadAlert("Phone Number invalid!");
            return false;
        }return true;
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
