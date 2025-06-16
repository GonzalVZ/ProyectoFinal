package com.example;

import java.io.*;

// Clase que representa una medición meteorológica y permite su serialización
public class Medicion implements Serializable {

    // Atributos de la medición
    private int id;                // ID único de la medición
    private int idEstacion;        // ID de la estación asociada
    private String fecha;          // Fecha de la medición (formato texto)
    private String hora;           // Hora de la medición
    private double temperatura;    // Temperatura registrada (°C)
    private double precipitacion;  // Precipitación registrada (mm)
    private double presion;        // Presión atmosférica (hPa)
    private double humedad;        // Porcentaje de humedad (%)

    // Constructor vacío (necesario para serialización o frameworks como JavaFX)
    public Medicion() {
    }

    // Constructor copia: crea una nueva instancia duplicando otra medición
    public Medicion(Medicion m) {
        this.id = m.id;
        this.idEstacion = m.idEstacion;
        this.fecha = m.fecha;
        this.hora = m.hora;
        this.temperatura = m.temperatura;
        this.precipitacion = m.precipitacion;
        this.presion = m.presion;
        this.humedad = m.humedad;
    }

    // Constructor principal con todos los campos necesarios para una medición
    public Medicion(int id, int idEstacion, String fecha, String hora,
                    double temperatura, double precipitacion, double presion, double humedad) {
        this.id = id;
        this.idEstacion = idEstacion;
        this.fecha = fecha;
        this.hora = hora;
        this.temperatura = temperatura;
        this.precipitacion = precipitacion;
        this.presion = presion;
        this.humedad = humedad;
    }

    // Representación textual de la medición (útil para debug o listas)
    @Override
    public String toString() {
        return "Medicion [id=" + id + ", idEstacion=" + idEstacion + ", fecha=" + fecha + ", hora=" + hora
                + ", temperatura=" + temperatura + ", precipitacion=" + precipitacion + ", presion=" + presion
                + ", humedad=" + humedad + "]";
    }

    // Getters y setters de todos los atributos

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdEstacion() {
        return idEstacion;
    }

    public void setIdEstacion(int idEstacion) {
        this.idEstacion = idEstacion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(double temperatura) {
        this.temperatura = temperatura;
    }

    public double getPrecipitacion() {
        return precipitacion;
    }

    public void setPrecipitacion(double precipitacion) {
        this.precipitacion = precipitacion;
    }

    public double getPresion() {
        return presion;
    }

    public void setPresion(double presion) {
        this.presion = presion;
    }

    public double getHumedad() {
        return humedad;
    }

    public void setHumedad(double humedad) {
        this.humedad = humedad;
    }

}
