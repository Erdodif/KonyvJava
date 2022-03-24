package hu.petrik.konyvjava;

import hu.petrik.konyvjava.data.Database;
import hu.petrik.konyvjava.data.Konyv;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;
import java.util.Optional;

public class HelloController {
    @FXML
    public TableView<Konyv> tableview;
    @FXML
    public TableColumn<Konyv,String> columnTitle;
    @FXML
    public TableColumn<Konyv,String> columnAuthor;
    @FXML
    public TableColumn<Konyv,Integer> columnYear;
    @FXML
    public TableColumn<Konyv,Integer> columnPages;
    Database database;

    public void initialize() throws SQLException {
        database = new Database();
        columnTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        columnAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
        columnYear.setCellValueFactory(new PropertyValueFactory<>("publishYear"));
        columnPages.setCellValueFactory(new PropertyValueFactory<>("pageCount"));
        tableview.getItems().setAll(database.fetchKonyvek());
    }

    @FXML
    public void DeleteKonyv(ActionEvent actionEvent) {
        try {
            Konyv konyvToDelete = tableview.getSelectionModel().getSelectedItem();
            konyvToDelete.getId();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Törlési kísérlet");
            alert.setHeaderText("Törlés, törlés");
            alert.setContentText("Biztosan törölni szeretné?");

            ButtonType buttonTypeYes = new ButtonType("Igen");
            ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

            alert.getButtonTypes().setAll(buttonTypeYes,buttonTypeCancel);

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == buttonTypeYes){
                try {
                    database.deleteKonyv(konyvToDelete);
                    initialize();
                }
                catch (Exception e){
                    Alert alertError = new Alert(Alert.AlertType.ERROR);
                    alertError.setContentText(e.getMessage());
                    alertError.show();
                }
            } else {
                Alert alertError = new Alert(Alert.AlertType.ERROR);
                alertError.setContentText("Törlés megszakítva");
                alertError.show();
            }
        }
        catch (Exception e){
            Alert alertError = new Alert(Alert.AlertType.ERROR);
            alertError.setContentText("Törléshez előbb válasszon ki egy elemet!");
            alertError.show();
        }
    }
}