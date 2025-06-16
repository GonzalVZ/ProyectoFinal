package com.example;

import java.io.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.*;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class PrimaryController {

    // Elementos de la interfaz definidos en el archivo FXML
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

    // Método que se ejecuta automáticamente al iniciar el controlador
    @FXML
    public void initialize() {
        // Se enlazan las columnas de la tabla con los atributos de la clase Estacion
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        poblacion.setCellValueFactory(new PropertyValueFactory<>("poblacion"));
        provincia.setCellValueFactory(new PropertyValueFactory<>("provincia"));

        // Se crea una lista observable a partir de las estaciones existentes
        ObservableList<Estacion> listaEstaciones = FXCollections.observableArrayList(Meteorologia.listaEstaciones);

        // Se asigna la lista a la tabla
        tableView.setItems(listaEstaciones);
    }

    // Método que se ejecuta al hacer clic en el botón "Ver Mediciones"
    @FXML
    private void verMediciones() {
        // Obtiene la estación seleccionada en la tabla
        Estacion seleccionada = tableView.getSelectionModel().getSelectedItem();
        if (seleccionada == null) {
            // Muestra una alerta si no se ha seleccionado ninguna estación
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Advertencia");
            alert.setHeaderText(null);
            alert.setContentText("Selecciona una estación para ver sus mediciones.");
            alert.showAndWait();
            return;
        }

        try {
            // Carga la vista secundaria desde el archivo FXML
            FXMLLoader loader = new FXMLLoader(App.class.getResource("secondary.fxml"));
            Parent root = loader.load();

            // Obtiene el controlador asociado y le pasa la estación seleccionada
            SecondaryController controller = loader.getController();
            controller.setEstacion(seleccionada);

            // Cambia la escena actual por la vista secundaria
            App.scene.setRoot(root);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método que se ejecuta al hacer clic en "Guardar en Fichero"
    @FXML
    public void guardarFichero() {
        // Llama al método que escribe las estaciones y sus mediciones en ficheros
        Meteorologia.escribirFichero();
    }

    // Método que se ejecuta al hacer clic en "Cargar Fichero"
    @FXML
    public void cargarFichero() {
        // Carga los datos desde el fichero
        Meteorologia.cargarFichero();

        // Actualiza la tabla con los datos cargados
        ObservableList<Estacion> listaEstaciones = FXCollections.observableArrayList(Meteorologia.listaEstaciones);
        tableView.setItems(listaEstaciones);
    }

    // Método que se ejecuta al hacer clic en "Guardar en Base de Datos"
    @FXML
    public void guardarBaseDatos(){
        // Llama al método que guarda los datos en la base de datos
        Meteorologia.guardarBaseDatos();
    }

    // Método que se ejecuta al hacer clic en "Cargar desde Base de Datos"
    @FXML
    public void cargarBaseDatos(){
        // Carga los datos desde la base de datos
        Meteorologia.cargarBaseDatos();

        // Actualiza la tabla con los datos cargados
        ObservableList<Estacion> listaEstaciones = FXCollections.observableArrayList(Meteorologia.listaEstaciones);
        tableView.setItems(listaEstaciones);
    }
}
