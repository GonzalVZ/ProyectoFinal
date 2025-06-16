package com.example;

import java.io.*;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class PrimaryController {

    @FXML
    private TableView<Estacion> tableView;
    @FXML
    private TableColumn<Estacion, Integer> id;
    @FXML
    private TableColumn<Estacion, String> nombre;
    @FXML
    private TableColumn<Estacion, String> poblacion;
    @FXML
    private TableColumn<Estacion, String> provincia;

    @FXML
    public void initialize() {
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        poblacion.setCellValueFactory(new PropertyValueFactory<>("poblacion"));
        provincia.setCellValueFactory(new PropertyValueFactory<>("provincia"));

        ObservableList<Estacion> listaEstaciones = FXCollections.observableArrayList(Meteorologia.listaEstaciones);

        tableView.setItems(listaEstaciones);

    }

    @FXML
    private void verMediciones() {
        Estacion seleccionada = tableView.getSelectionModel().getSelectedItem();
        if (seleccionada == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Advertencia");
            alert.setHeaderText(null);
            alert.setContentText("Selecciona una estación para ver sus mediciones.");
            alert.showAndWait();
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("secondary.fxml"));
            Parent root = loader.load();

            SecondaryController controller = loader.getController();
            controller.setEstacion(seleccionada);

            // CAMBIO IMPORTANTE: usar el root cargado manualmente
            App.scene.setRoot(root); // necesitas que 'scene' sea pública o accesible

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void guardarFichero() {

        Meteorologia.escribirFichero();

    }

    @FXML
    public void cargarFichero() {

        Meteorologia.cargarFichero();
        ObservableList<Estacion> listaEstaciones = FXCollections.observableArrayList(Meteorologia.listaEstaciones);

        tableView.setItems(listaEstaciones);
    }

    @FXML
    public void guardarBaseDatos(){

    Meteorologia.guardarBaseDatos();

    }

   @FXML
   public void cargarBaseDatos(){
    Meteorologia.cargarBaseDatos();
     ObservableList<Estacion> listaEstaciones = FXCollections.observableArrayList(Meteorologia.listaEstaciones);

    tableView.setItems(listaEstaciones);

   }

}
