module com.example.clientemailcuoiki {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;


    opens com.example.clientemailcuoiki to javafx.fxml;
    exports com.example.clientemailcuoiki;
}