package com.example;

// Importa elementos de JavaFX necesarios para el controlador
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AñadirMedicionController {

    // Campos de texto enlazados con el formulario FXML para ingresar los datos de la medición
    @FXML private TextField campoFecha;
    @FXML private TextField campoHora;
    @FXML private TextField campoTemperatura;
    @FXML private TextField campoPrecipitacion;
    @FXML private TextField campoPresion;
    @FXML private TextField campoHumedad;

    // Referencia a la estación a la que se le va a añadir una medición
    private Estacion estacionActual;

    // Método público para establecer la estación actual desde otro controlador
    public void setEstacion(Estacion estacion) {
        this.estacionActual = estacion;
    }

    // Método que se ejecuta al pulsar el botón "Guardar"
    @FXML
    private void guardarMedicion() {

        // Añade una nueva medición a la estación actual con los datos introducidos por el usuario
        estacionActual.agregarMedicion(
            estacionActual.ultimoId() + 1,                // Genera un nuevo ID
            estacionActual.getId(),                       // ID de la estación
            campoFecha.getText(),                         // Fecha introducida
            campoHora.getText(),                          // Hora introducida
            Double.parseDouble(campoTemperatura.getText()),   // Temperatura (convertida a double)
            Double.parseDouble(campoPrecipitacion.getText()), // Precipitación (convertida a double)
            Double.parseDouble(campoPresion.getText()),       // Presión (convertida a double)
            Double.parseDouble(campoHumedad.getText())        // Humedad (convertida a double)
        );

        // Cierra la ventana actual después de guardar la medición
        Stage stage = (Stage) campoFecha.getScene().getWindow();
        stage.close();
    }
}
