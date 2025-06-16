package com.example;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AñadirMedicionController {

    @FXML private TextField campoFecha;
    @FXML private TextField campoHora;
    @FXML private TextField campoTemperatura;
    @FXML private TextField campoPrecipitacion;
    @FXML private TextField campoPresion;
    @FXML private TextField campoHumedad;

    private Estacion estacionActual;

    public void setEstacion(Estacion estacion) {
        this.estacionActual = estacion;
    }

    @FXML
    private void guardarMedicion() {

    estacionActual.agregarMedicion(estacionActual.ultimoId() + 1,estacionActual.getId(),campoFecha.getText(),campoHora.getText(),Double.parseDouble(campoTemperatura.getText()),Double.parseDouble(campoPrecipitacion.getText()),
            Double.parseDouble(campoPresion.getText()),
            Double.parseDouble(campoHumedad.getText()));
        


        // Cierra la ventana
        Stage stage = (Stage) campoFecha.getScene().getWindow();
        stage.close();
    }
}

