package com.example;

import java.io.*;

import javafx.collections.*;
import javafx.fxml.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;;

public class SecondaryController {

    @FXML
    private TableView<Medicion> tableView;
    @FXML
    private TableColumn<Medicion, Integer> id;
    @FXML
    private TableColumn<Medicion, Integer> idEstacion;
    @FXML
    private TableColumn<Medicion, String> fecha;
    @FXML
    private TableColumn<Medicion, String> hora;
    @FXML
    private TableColumn<Medicion, Double> temperatura;
    @FXML
    private TableColumn<Medicion, Double> precipitacion;
    @FXML
    private TableColumn<Medicion, Double> presion;
    @FXML
    private TableColumn<Medicion, Double> humedad;

    @FXML
    private TextField fechaInicio;
    @FXML
    private TextField fechaFin;
    @FXML
    private TextArea resultado;

    Estacion estacionActual;

    public void setEstacion(Estacion e) {
        estacionActual = e;
        actualizarTabla();
    }

    private void actualizarTabla() {
        if (tableView != null) {
            tableView.setItems(FXCollections.observableArrayList(estacionActual.verListaMediciones()));
        }
    }

    @FXML
    public void initialize() {
        tableView.setEditable(true);

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        idEstacion.setCellValueFactory(new PropertyValueFactory<>("idEstacion"));
        fecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        hora.setCellValueFactory(new PropertyValueFactory<>("hora"));
        temperatura.setCellValueFactory(new PropertyValueFactory<>("temperatura"));
        precipitacion.setCellValueFactory(new PropertyValueFactory<>("precipitacion"));
        presion.setCellValueFactory(new PropertyValueFactory<>("presion"));
        humedad.setCellValueFactory(new PropertyValueFactory<>("humedad"));

        // Hacer editables las columnas con StringConverter
        fecha.setCellFactory(TextFieldTableCell.forTableColumn());
        fecha.setOnEditCommit(e -> e.getRowValue().setFecha(e.getNewValue()));

        hora.setCellFactory(TextFieldTableCell.forTableColumn());
        hora.setOnEditCommit(e -> e.getRowValue().setHora(e.getNewValue()));

        temperatura.setCellFactory(
                TextFieldTableCell.<Medicion, Double>forTableColumn(new javafx.util.converter.DoubleStringConverter()));
        temperatura.setOnEditCommit(e -> e.getRowValue().setTemperatura(e.getNewValue()));

        precipitacion.setCellFactory(
                TextFieldTableCell.<Medicion, Double>forTableColumn(new javafx.util.converter.DoubleStringConverter()));
        precipitacion.setOnEditCommit(e -> e.getRowValue().setPrecipitacion(e.getNewValue()));

        presion.setCellFactory(
                TextFieldTableCell.<Medicion, Double>forTableColumn(new javafx.util.converter.DoubleStringConverter()));
        presion.setOnEditCommit(e -> e.getRowValue().setPresion(e.getNewValue()));

        humedad.setCellFactory(
                TextFieldTableCell.<Medicion, Double>forTableColumn(new javafx.util.converter.DoubleStringConverter()));
        humedad.setOnEditCommit(e -> e.getRowValue().setHumedad(e.getNewValue()));
    }

    @FXML
    public void añadirMedicion() {
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("añadirMedicion.fxml"));
            Parent root = loader.load();

            AñadirMedicionController controller = loader.getController();
            controller.setEstacion(estacionActual);

            Stage stage = new Stage();
            stage.setTitle("Nueva medición");
            stage.setScene(new Scene(root));
            stage.showAndWait();

            tableView.setItems(FXCollections.observableArrayList(estacionActual.verListaMediciones()));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void borrar() {

        Medicion seleccionada = tableView.getSelectionModel().getSelectedItem();
        if (seleccionada != null) {

            estacionActual.borrarMedicion(seleccionada.getId());

            tableView.setItems(FXCollections.observableArrayList(estacionActual.verListaMediciones()));

        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Advertencia");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, selecciona una medicion para eliminar.");
            alert.showAndWait();
        }

    }

    @FXML
    public void guardar() {
        ObservableList<Medicion> datosTabla = tableView.getItems();
        estacionActual.listaMediciones.clear();
        estacionActual.listaMediciones.addAll(datosTabla);
        System.out.println(estacionActual.listaMediciones);

    }

    @FXML
    public void estadisticas() {

        if (fechaInicio.getText().isEmpty() && fechaFin.getText().isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Advertencia");
            alert.setHeaderText(null);
            alert.setContentText("Introduce Fecha Inicio y Fecha Fin");
            alert.showAndWait();

        } else {
            String palabra = estacionActual.estadisticas(fechaInicio.getText(), fechaFin.getText());

            resultado.setText(palabra);

        }

    }

    @FXML
    public void volverEstacion() {
        try {
            App.setRoot("primary");

        } catch (Exception e) {
            // TODO: handle exception
        }
    }

}