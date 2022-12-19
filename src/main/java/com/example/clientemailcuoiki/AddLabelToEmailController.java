package com.example.clientemailcuoiki;

import com.example.clientemailcuoiki.Client.Client;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddLabelToEmailController implements Initializable {
    public ListView<String> labelMenuInAccount;
    public Button addlabel;
    public Button removeLabel;
    int indexMail;
    String selectedlabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getLabelFromAccount();
    }

    private void getLabelFromAccount(){
        ArrayList<String> labels = Client.showAccountLabels();

        if(labels != null){
            labelMenuInAccount.getItems().addAll(labels);
            labelMenuInAccount.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                    selectedlabel = labelMenuInAccount.getSelectionModel().getSelectedItem();
                }
            });
        }
    }

    public void getData(int index) {
        indexMail = index;
    }

    public void removeLabelFrommail(ActionEvent actionEvent) {
    }

    public void addLabelTomail(ActionEvent actionEvent) {
        boolean resultAddLabel = Client.addLabelToMail(indexMail, selectedlabel);
        if(resultAddLabel){
            System.out.println("added");
        }else{
            System.out.println("failed");
        }

    }
}
