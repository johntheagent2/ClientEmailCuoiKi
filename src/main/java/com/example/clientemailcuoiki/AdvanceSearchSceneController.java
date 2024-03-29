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
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AdvanceSearchSceneController implements Initializable {
    public TableColumn<Email, String> senderID;
    public TableColumn<Email, String> subject;
    public TableView<Email> tableViewID;
    public TableColumn<Email, String> isMailNew;
    public Circle userDetail;
    public Button deleteMail;
    public TextField filterTo;
    public TextField filterSubject;
    public TextField filterHas;
    public TextField filterDoesntHave;
    public TextField filterFrom;

    int indexOfMail;

    Stage stage;

    Scene scene;

    Parent root;

    Email rowData;

    public ArrayList<Email> listMail;

    private final ObservableList<Email> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showEmails();
    }

    private void showEmails() {
        isMailNew.setCellValueFactory(cd -> new SimpleStringProperty(checkIfNewMail(cd.getValue().isNew())));
        senderID.setCellValueFactory(new PropertyValueFactory<Email, String>("sender"));
        subject.setCellValueFactory(new PropertyValueFactory<Email, String>("subject"));

        setItemList(Client.showEmails());

        filterMail();

        tableViewID.setRowFactory(tv -> {
            TableRow<Email> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                rowData = row.getItem();
                indexOfMail = row.getIndex() + 1;
            });
            return row ;
        });
    }

    private void filterMail(){
        FilteredList<Email> filteredData = new FilteredList<>(list, b -> true);

        filterFrom.textProperty().addListener(((observableValue, oldValue, newValue) -> {
            filteredData.setPredicate(emailFilter -> {
                if(newValue == null || newValue.isEmpty()){
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                return emailFilter.getSender().toLowerCase().contains(lowerCaseFilter);
            });
        }));

        filterSubject.textProperty().addListener(((observableValue, oldValue, newValue) -> {
            filteredData.setPredicate(emailFilter -> {
                if(newValue == null || newValue.isEmpty()){
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                return emailFilter.getSubject().toLowerCase().contains(lowerCaseFilter);
            });
        }));

        filterHas.textProperty().addListener(((observableValue, oldValue, newValue) -> {
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

        filterDoesntHave.textProperty().addListener(((observableValue, oldValue, newValue) -> {
            filteredData.setPredicate(emailFilter -> {
                if(newValue == null || newValue.isEmpty()){
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if(emailFilter.getSender().toLowerCase().contains(lowerCaseFilter)){
                    return false;
                }else if(emailFilter.getSubject().toLowerCase().contains(lowerCaseFilter)){
                    return false;
                }else if(emailFilter.getMainBody().toLowerCase().contains(lowerCaseFilter)){
                    return false;
                }else{
                    return true;
                }
            });
        }));

        SortedList<Email> sortedData = new SortedList<>(filteredData);

        sortedData.comparatorProperty().bind(tableViewID.comparatorProperty());

        tableViewID.setItems(sortedData);
    }

    public void setItemList(ArrayList<Email> itemList) {
        listMail = new ArrayList<>();
        listMail.addAll(itemList);
        if(itemList==null) {
            return;
        }
        this.listMail = itemList;
        list.addAll(itemList);
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
        detailedMail.showDetailedEmail(rowData, indexOfMail);
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

    public void simpleSearchScene(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MessageScene.fxml"));
        root = loader.load();

        stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("Message!");
        stage.setScene(scene);
        stage.show();
    }

}
