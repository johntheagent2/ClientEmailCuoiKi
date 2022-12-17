package com.example.clientemailcuoiki;

import com.example.clientemailcuoiki.Client.Client;
import com.example.clientemailcuoiki.Client.Email;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MessageSceneController implements Initializable{

    private static Socket server = null;
    public TableColumn<Email, String> senderID;
    public TableColumn<Email, String> subject;
    public TableView<Email> tableViewID;
    public TableColumn<Email, String> isMailNew;
    public Circle userDetail;
    public Button deleteMail;

    public ArrayList<Email> listMail;
    public TextField filterField;

    int indexOfMail;

    Stage stage;

    Scene scene;

    Parent root;

    Email rowData;

    private final ObservableList<Email> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showEmails();
    }

    private void showEmails() {


//        for(Email i : Client.showEmails()){
//            if(i.getSender().equals("test1@socketmail.gr"))
//                listMail.add(i);
//        }

//        ObservableList<Email> list = FXCollections.observableArrayList(listMail);

        isMailNew.setCellValueFactory(cd -> new SimpleStringProperty(checkIfNewMail(cd.getValue().isNew())));
        senderID.setCellValueFactory(new PropertyValueFactory<Email, String>("sender"));
        subject.setCellValueFactory(new PropertyValueFactory<Email, String>("subject"));

        listMail = new ArrayList<>();
        listMail.addAll(Client.showEmails());

        list.addAll(listMail);

        FilteredList<Email> filteredData = new FilteredList<>(list, b -> true);

        filterField.textProperty().addListener(((observableValue, oldValue, newValue) -> {
            filteredData.setPredicate(emailFilter -> {
                if(newValue == null || newValue.isEmpty()){
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if(emailFilter.getSender().toLowerCase().contains(lowerCaseFilter)){
                    return true;
                }else if(emailFilter.getSubject().toLowerCase().contains(lowerCaseFilter)){
                    return true;
                }else if(emailFilter.getMainBody().toLowerCase().contains(lowerCaseFilter)){
                    return true;
                }else{
                    return false;
                }
            });
        }));

        SortedList<Email> sortedData = new SortedList<>(filteredData);

        sortedData.comparatorProperty().bind(tableViewID.comparatorProperty());

        tableViewID.setItems(sortedData);
        tableViewID.setRowFactory(tv -> {
            TableRow<Email> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                rowData = row.getItem();
                indexOfMail = row.getIndex() + 1;
            });
            return row ;
        });
    }

    private String checkIfNewMail(Boolean isNew){
        if(isNew){
            return "[NEW]";
        }return "[READ]";
    }

    public void composeButton(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ComposeScene.fxml"));
        root = loader.load();

        stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("Message!");
        stage.setScene(scene);
        stage.show();
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

    public void deleteMail(ActionEvent actionEvent) {
        Client.deleteEmail(indexOfMail);
    }

    public void sentScene(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SentScene.fxml"));
        root = loader.load();

        stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("Message!");
        stage.setScene(scene);
        stage.show();
    }
}
