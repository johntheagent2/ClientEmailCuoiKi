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
    public TableColumn<Email, String> senderID;
    public TableColumn<Email, String> subject;
    public TableView<Email> tableViewID;
    public TableColumn<Email, String> isMailNew;
    public Circle userDetail;
    public Button deleteMail;
    public ChoiceBox<String> labelBox;
    public MenuItem addLabelToMail;

    int indexOfMail;

    Stage stage;

    Scene scene;

    Parent root;

    Email rowData;

    public ArrayList<Email> listMail;
    public TextField filterField;

    private final ObservableList<Email> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getLabelFromAccount();
        showEmails();
    }

    private void getLabelFromAccount(){
        ArrayList<String> labels = Client.showAccountLabels();
        labelBox.getItems().add("All");
        labelBox.setValue("All");
        if(labels != null)
            labelBox.getItems().addAll(labels);
    }

    public void addLabelToMail(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddLabelToEmail.fxml"));
        root = loader.load();

        stage = new Stage();
        scene = new Scene(root);
        stage.setTitle("Add lable!");
        stage.setScene(scene);
        stage.show();
    }

    public void addLabelToAccount(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddlabelScene.fxml"));
        root = loader.load();
        AddLabelToEmailController addLableFunction = loader.getController();
        addLableFunction.addLabelTomail(actionEvent, indexOfMail);
        stage = new Stage();
        scene = new Scene(root);
        stage.setTitle("Add lable!");
        stage.setScene(scene);
        stage.show();
    }

    public void removeLableFromAccount(ActionEvent actionEvent) throws IOException {
        boolean removeLabelResult = Client.removeLabel(labelBox.getSelectionModel().getSelectedItem());
        if(removeLabelResult){
            alert("Remove label successfully");
        }else{
            alert("Remove label failed");
        }
    }

    public void alert(String alertMessage) throws IOException {
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

    public void advanceSearchScene(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AdvanceSearchScene.fxml"));
        root = loader.load();

        stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("Message!");
        stage.setScene(scene);
        stage.show();
    }

}
