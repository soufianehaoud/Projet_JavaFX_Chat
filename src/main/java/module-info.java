module com.example.controlre_manager {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.controlre_manager to javafx.fxml;
    exports com.example.controlre_manager;
}