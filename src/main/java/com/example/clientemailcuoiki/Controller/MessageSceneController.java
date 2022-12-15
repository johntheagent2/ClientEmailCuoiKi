package com.example.clientemailcuoiki.Controller;

import com.example.clientemailcuoiki.Client.Client;
import com.example.clientemailcuoiki.Client.Email;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class MessageSceneController implements Initializable{

    public TextField receiverField;
    public TextField subjectField;

    private static Socket server = null;
    public TableColumn<Email, String> senderID;
    public TableColumn<Email, String> subject;
    public TableView<Email> tableViewID;
    public TableColumn<Email, Boolean> isMailNew;

    @FXML
    Button sendButton;

    @FXML
    TextArea messageText;

    @FXML
    TextField textField;

    Stage stage;

    Scene scene;

    Parent root;

    Email rowData;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showEmails();
    }

    private void showEmails() {
        ObservableList<Email> list = FXCollections.observableArrayList(Client.showEmails());
        senderID.setCellValueFactory(new PropertyValueFactory<Email, String>("sender"));
        subject.setCellValueFactory(new PropertyValueFactory<Email, String>("subject"));
        tableViewID.setItems(list);
        tableViewID.setRowFactory(tv -> {
            TableRow<Email> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                rowData = row.getItem();
                System.out.println("Double click on: "+rowData.getMainBody() + " - Position: " + row.getIndex());
            });
            return row ;
        });
    }

//    if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
//        rowData = row.getItem();
//        System.out.println("Double click on: "+rowData.getMainBody() + " - Position: " + row.getIndex());
//    }

    public void composeButton(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ComposeScene.fxml"));
        root = loader.load();

        stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("Message!");
        stage.setScene(scene);
        stage.show();
    }

    public void showChosenMail(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("DetailedMailScene.fxml"));
        root = loader.load();

        DetailedMailSceneController detailedMail = loader.getController();
        detailedMail.showDetailedEmail(rowData);

        stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("Message!");
        stage.setScene(scene);
        stage.show();
    }

//    public void chooseEmailToRead(){
//
//        System.out.println("");
//        System.out.println("***********************************");
//        System.out.println("*****       Read Email        *****");
//        System.out.println("***********************************");
//        System.out.println("");
//
//        System.out.println("Type the id of the email you want to read");
//
////        int emailId = readOption(numOfEmails);
//
//        Email requestedEmail = Client.readEmail(emailId);
//
//        System.out.println("");
//        System.out.println("From: " + requestedEmail.getSender());
//        System.out.println("Subject: " + requestedEmail.getSubject());
//        System.out.println("");
//        System.out.println(requestedEmail.getMainBody());
//    }
}
