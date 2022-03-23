module com.example.konyvjava {
    requires javafx.controls;
    requires javafx.fxml;


    opens hu.petrik.konyvjava to javafx.fxml;
    exports hu.petrik.konyvjava;
}