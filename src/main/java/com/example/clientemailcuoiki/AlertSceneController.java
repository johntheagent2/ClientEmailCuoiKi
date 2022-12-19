package com.example.clientemailcuoiki;

import javafx.fxml.Initializable;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class AlertSceneController implements Initializable {
    public Text alertText;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void showAlert(String alert){
        alertText.setText(alert);
    }
}
