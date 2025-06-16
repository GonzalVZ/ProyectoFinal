package com.example;

import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

// Clase que representa una estación meteorológica y su lista de mediciones
public class Estacion {

    // Atributos principales de la estación
    private int id;
    private String nombre;
    private String poblacion;
    private String provincia;

    // Lista que almacena todas las mediciones asociadas a esta estación
    public List<Medicion> listaMediciones = new ArrayList<>();

    // Guarda todas las mediciones actuales en un archivo binario usando RandomAccessFile
    public void escribirMediciones() {
        try {
            RandomAccessFile raf = new RandomAccessFile("mediciones.data", "rw");
            for (Medicion medicion : listaMediciones) {
                raf.seek(raf.length()); // Coloca el puntero al final del archivo
                raf.writeInt(medicion.getId());
                raf.writeInt(medicion.getIdEstacion());
                raf.writeUTF(medicion.getFecha());
                raf.writeUTF(medicion.getHora());
                raf.writeDouble(medicion.getTemperatura());
                raf.writeDouble(medicion.getPrecipitacion());
                raf.writeDouble(medicion.getPresion());
                raf.writeDouble(medicion.getHumedad());
            }
            raf.close();
        } catch (Exception e) {
            System.out.println("Error");
        }
    }

    // Carga las mediciones desde el archivo binario y filtra solo las de esta estación
    public void cargarMediciones() {
        listaMediciones.clear();

        try {
            RandomAccessFile raf = new RandomAccessFile("mediciones.data", "rw");

            while (raf.getFilePointer() < raf.length()) {
                int id = raf.readInt();
                int idEstacion = raf.readInt();
                String fecha = raf.readUTF();
                String hora = raf.readUTF();
                double temperatura = raf.readDouble();
                double precipitacion = raf.readDouble();
                double presion = raf.readDouble();
                double humedad = raf.readDouble();

                if (idEstacion == this.id) {
                    Medicion m = new Medicion(id, idEstacion, fecha, hora, temperatura, precipitacion, presion, humedad);
                    listaMediciones.add(m);
                }
            }
            raf.close();
        } catch (Exception e) {
            // Manejo de error omitido
        }
    }

    // Guarda todas las mediciones actuales en la base de datos
    public void guardarBaseDatos() {
        ConexionDB con = new ConexionDB();
        int filasTotales = 0;

        for (Medicion medicion : listaMediciones) {
            String sql = "INSERT INTO medicion (id, id_estacion, fecha, hora, temperatura, precipitacion, presion, humedad) VALUES (" +
                    medicion.getId() + ", " +
                    medicion.getIdEstacion() + ", '" +
                    medicion.getFecha() + "', '" +
                    medicion.getHora() + "', " +
                    medicion.getTemperatura() + ", " +
                    medicion.getPrecipitacion() + ", " +
                    medicion.getPresion() + ", " +
                    medicion.getHumedad() + ");";

            try {
                int filas = con.insertar(sql);
                filasTotales += filas;
            } catch (Exception e) {
                System.err.println("Error al insertar medición con ID " + medicion.getId() + ": " + e.getMessage());
            }
        }

        System.out.println("Filas insertadas: " + filasTotales);
    }

    // Carga todas las mediciones desde la base de datos que correspondan a esta estación
    public void cargarBaseDatos() {
        ConexionDB con = new ConexionDB();
        ResultSet rs = con.consultar("SELECT * FROM medicion WHERE id_estacion = " + this.id);
        listaMediciones.clear();

        try {
            while (rs != null && rs.next()) {
                Medicion m = new Medicion(
                        rs.getInt("id"),
                        rs.getInt("id_estacion"),
                        rs.getString("fecha"),
                        rs.getString("hora"),
                        rs.getDouble("temperatura"),
                        rs.getDouble("precipitacion"),
                        rs.getDouble("presion"),
                        rs.getDouble("humedad"));
                listaMediciones.add(m);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Extrae el día de la fecha en formato dd/MM/yyyy (solo devuelve los dos primeros caracteres)
    public int fechaParse(String fecha) {
        int fechaParseada = Integer.parseInt(fecha.substring(0, 2));
        return fechaParseada;
    }

    // Calcula estadísticas entre dos fechas dadas sobre las mediciones
    public String estadisticas(String fechaInicio, String fechaFin) {
        double temperaturaMaxima = Double.NEGATIVE_INFINITY;
        double temperaturaMinima = Double.POSITIVE_INFINITY;
        double temperaturaMedia = 0;
        double precipitacionMaxima = Double.NEGATIVE_INFINITY;
        double precipitacionMinima = Double.POSITIVE_INFINITY;
        double precipitacionTotal = 0;
        double humedadMaxima = Double.NEGATIVE_INFINITY;
        double humedadMinima = Double.POSITIVE_INFINITY;
        double humedadMedia = 0;
        int contador = 0;

        for (int i = 0; i < listaMediciones.size(); i++) {
            if (fechaParse(listaMediciones.get(i).getFecha()) >= fechaParse(fechaInicio)
                    && fechaParse(listaMediciones.get(i).getFecha()) <= fechaParse(fechaFin)) {
                contador++;

                temperaturaMedia += listaMediciones.get(i).getTemperatura();
                temperaturaMaxima = Math.max(temperaturaMaxima, listaMediciones.get(i).getTemperatura());
                temperaturaMinima = Math.min(temperaturaMinima, listaMediciones.get(i).getTemperatura());

                precipitacionTotal += listaMediciones.get(i).getPrecipitacion();
                precipitacionMaxima = Math.max(precipitacionMaxima, listaMediciones.get(i).getPrecipitacion());
                precipitacionMinima = Math.min(precipitacionMinima, listaMediciones.get(i).getPrecipitacion());

                humedadMedia += listaMediciones.get(i).getHumedad();
                humedadMaxima = Math.max(humedadMaxima, listaMediciones.get(i).getHumedad());
                humedadMinima = Math.min(humedadMinima, listaMediciones.get(i).getHumedad());
            }
        }

        temperaturaMedia = temperaturaMedia / contador;
        humedadMedia = humedadMedia / contador;

        String resultado = " Temperatura Maxima: " + temperaturaMaxima + " Temperatura Minima: " + temperaturaMinima
                + " Temperatura Media: " + temperaturaMedia + "\n" + " Precipitacion Maxima: " + precipitacionMaxima
                + " Precipìtacion Minima: "
                + precipitacionMinima + " Precipitacion Total: "
                + precipitacionTotal + "\n" + " Humedad Maxima: " + humedadMaxima + " Humedad Minima: " + humedadMinima
                + " Humedad Media: "
                + humedadMedia + "\n";

        return resultado;
    }

    // Añade una nueva medición a la lista
    public void agregarMedicion(int id, int idEstacion, String fecha, String hora, double temperatura,
            double precipitacion, double presion, double humedad) {
        Medicion m = new Medicion(id, idEstacion, fecha, hora, temperatura, precipitacion, presion, humedad);
        listaMediciones.add(m);
    }

    // Devuelve la lista de mediciones
    public List<Medicion> verListaMediciones() {
        return listaMediciones;
    }

    // Borra una medición según su ID
    public void borrarMedicion(int id) {
        for (int i = 0; i < listaMediciones.size(); i++) {
            if (listaMediciones.get(i).getId() == id) {
                listaMediciones.remove(i);
            }
        }
    }

    // Devuelve el ID más alto de las mediciones actuales
    public int ultimoId() {
        int ultimoId = 0;
        for (Medicion m : listaMediciones) {
            if (m.getId() > ultimoId) {
                ultimoId = m.getId();
            }
        }
        return ultimoId;
    }

    // Constructor por defecto
    public Estacion() {}

    // Constructor completo
    public Estacion(int id, String nombre, String poblacion, String provincia) {
        this.id = id;
        this.nombre = nombre;
        this.poblacion = poblacion;
        this.provincia = provincia;
    }

    // Constructor copia
    public Estacion(Estacion copia) {
        this.id = copia.id;
        this.nombre = copia.nombre;
        this.poblacion = copia.poblacion;
        this.provincia = copia.provincia;
    }

    // Representación en texto de una estación
    @Override
    public String toString() {
        return "ID: " + id + " NOMBRE: " + nombre + " POBLACION: " + poblacion + " PROVINCIA: " + provincia;
    }

    // Getters y setters
    public List<Medicion> getListaMediciones() {
        return listaMediciones;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPoblacion() {
        return poblacion;
    }

    public void setPoblacion(String poblacion) {
        this.poblacion = poblacion;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

}
