package com.example;
import java.io.*;

public class Medicion implements Serializable {
    private int id;
    private int idEstacion;
    private String fecha;
    private String hora;
    private double temperatura;
    private double precipitacion;
    private double presion;
    private double humedad;

    public Medicion() {
    }

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

    public Medicion(int id, int idEstacion, String fecha, String hora, double temperatura, double precipitacion,
            double presion, double humedad) {
        this.id = id;
        this.idEstacion = idEstacion;
        this.fecha = fecha;
        this.hora = hora;
        this.temperatura = temperatura;
        this.precipitacion = precipitacion;
        this.presion = presion;
        this.humedad = humedad;
    }

    @Override
    public String toString() {
        return "Medicion [id=" + id + ", idEstacion=" + idEstacion + ", fecha=" + fecha + ", hora=" + hora
                + ", temperatura=" + temperatura + ", precipitacion=" + precipitacion + ", presion=" + presion
                + ", humedad=" + humedad + "]";
    }

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