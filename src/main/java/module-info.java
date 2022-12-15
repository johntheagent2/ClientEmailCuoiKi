module com.example.clientemailcuoiki {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;
    requires java.sql;
    requires javafx.web;


    opens com.example.clientemailcuoiki to javafx.fxml;
    exports com.example.clientemailcuoiki;
    exports com.example.clientemailcuoiki.Account;
    opens com.example.clientemailcuoiki.Account to javafx.fxml;
    exports com.example.clientemailcuoiki.Client;
    opens com.example.clientemailcuoiki.Client to javafx.fxml;
}