module com.example.konyvjava {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens hu.petrik.konyvjava to javafx.fxml;
    exports hu.petrik.konyvjava;
    exports hu.petrik.konyvjava.data;
    opens hu.petrik.konyvjava.data to javafx.fxml;
}