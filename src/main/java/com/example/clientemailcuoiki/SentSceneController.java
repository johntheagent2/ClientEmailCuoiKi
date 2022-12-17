package com.example.clientemailcuoiki;

import com.example.clientemailcuoiki.Client.Client;
import com.example.clientemailcuoiki.Client.Email;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SentSceneController implements Initializable {
    public TableColumn isMailNew;
    public TableColumn senderID;
    public TableColumn subject;
    public Button showChosenMail;
    public Circle userDetail;
    public Button backToInbox;
    public TableView tableViewID;
    Email rowData;
    int indexOfMail;
    Stage stage;

    Scene scene;

    Parent root;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showEmails();
    }

    private void showEmails() {
        ArrayList<Email> listMail = new ArrayList<>();
        listMail.addAll(Client.showSentMails());

//        for(Email i : Client.showEmails()){
//            if(i.getSender().equals("test1@socketmail.gr"))
//                listMail.add(i);
//        }

        ObservableList<Email> list = FXCollections.observableArrayList(listMail);
        senderID.setCellValueFactory(new PropertyValueFactory<Email, String>("sender"));
        subject.setCellValueFactory(new PropertyValueFactory<Email, String>("subject"));
        tableViewID.setItems(list);
        tableViewID.setRowFactory(tv -> {
            TableRow<Email> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                rowData = row.getItem();
                indexOfMail = row.getIndex();
            });
            return row ;
        });
    }

    public int showChosenMail(ActionEvent actionEvent) throws IOException {
        if(rowData == null){
            return 0;
        }
        FXMLLoader loader = new FXMLLoader(getClass().getResource("DetailedMailScene.fxml"));
        root = loader.load();
        DetailedMailSceneController detailedMail = loader.getController();
        detailedMail.showDetailedEmail(rowData);
        stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("Message!");
        stage.setScene(scene);
        stage.show();
        return 1;
    }

    public void showDetails(MouseEvent mouseEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("UserDetails.fxml"));
        root = loader.load();
        stage = (Stage)((Node) userDetail.getParent()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("Message!");
        stage.setScene(scene);
        stage.show();
    }

    public void backToInbox(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MessageScene.fxml"));
        root = loader.load();
        stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("Message!");
        stage.setScene(scene);
        stage.show();
    }

}
