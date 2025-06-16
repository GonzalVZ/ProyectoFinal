package com.example;

import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class Meteorologia {

    // Lista estática de estaciones meteorológicas
    public static List<Estacion> listaEstaciones = new ArrayList<>();

    // Rellena la lista de estaciones con datos de prueba (hardcoded)
    public static void rellenarDatosPrueba() {
        listaEstaciones.clear();

        // Se crean 5 estaciones con sus respectivas mediciones
        Estacion e1 = new Estacion(1, "Estación Norte", "Bilbao", "Vizcaya");
        Estacion e2 = new Estacion(2, "Estación Sur", "Sevilla", "Andalucía");
        Estacion e3 = new Estacion(3, "Estación Este", "Valencia", "Comunidad Valenciana");
        Estacion e4 = new Estacion(4, "Estación Oeste", "A Coruña", "Galicia");
        Estacion e5 = new Estacion(5, "Estación Centro", "Madrid", "Comunidad de Madrid");

        // Añade 10 mediciones de ejemplo a cada estación
        // (similar para e2, e3, e4, e5...)

        // Agrega todas las estaciones a la lista principal
        listaEstaciones.addAll(List.of(e1, e2, e3, e4, e5));
    }

    // Escribe los datos de las estaciones en un CSV y las mediciones en un fichero binario
    public static void escribirFichero() {
        try {
            File fichero = new File("mediciones.data");
            fichero.delete(); // Borra el fichero binario anterior si existe

            FileWriter fw = new FileWriter("estaciones.csv");

            for (Estacion estacion : listaEstaciones) {
                // Escribe una línea con los datos de cada estación
                fw.write(estacion.getId() + ";" + estacion.getNombre() + ";" + estacion.getPoblacion() + ";"
                        + estacion.getProvincia() + "\n");

                // Guarda sus mediciones en el fichero binario
                estacion.escribirMediciones();
            }

            fw.close();

        } catch (Exception e) {
            System.out.println("Error al escribir en el fichero estaciones");
        }
    }

    // Carga las estaciones desde el fichero CSV y sus mediciones desde el binario
    public static void cargarFichero() {
        String linea;
        listaEstaciones.clear();

        try {
            BufferedReader br = new BufferedReader(new FileReader("estaciones.csv"));

            while ((linea = br.readLine()) != null) {
                // Divide cada línea por ';'
                String[] split = linea.split(";");

                // Crea una estación y la añade a la lista
                Estacion e = new Estacion(Integer.parseInt(split[0]), split[1], split[2], split[3]);
                listaEstaciones.add(e);

                // Carga sus mediciones desde el fichero binario
                e.cargarMediciones();
            }

            br.close();

        } catch (Exception e) {
            System.out.println("Error");
        }
    }

    // Guarda todas las estaciones y sus mediciones en la base de datos
    public static void guardarBaseDatos() {
        ConexionDB con = new ConexionDB();
        int filasTotales = 0;

        // Primero elimina las mediciones y luego las estaciones (por claves foráneas)
        con.borrar("DELETE FROM medicion");
        con.borrar("DELETE FROM estacion");

        for (Estacion estacion : listaEstaciones) {
            // Sentencia SQL para insertar la estación
            String sql = "INSERT INTO estacion (id, nombre, poblacion, provincia) VALUES (" +
                    estacion.getId() + ", '" +
                    estacion.getNombre() + "', '" +
                    estacion.getPoblacion() + "', '" +
                    estacion.getProvincia() + "')";

            try {
                // Inserta la estación
                int filas = con.insertar(sql);
                filasTotales += filas;

                // Guarda sus mediciones también
                estacion.guardarBaseDatos();

            } catch (Exception e) {
                System.err.println("Error al guardar estación con ID " + estacion.getId() + ": " + e.getMessage());
            }
        }

        System.out.println("Filas insertadas en tabla estacion: " + filasTotales);
    }

    // Carga las estaciones y sus mediciones desde la base de datos
    public static void cargarBaseDatos() {
        ConexionDB con = new ConexionDB();

        // Consulta todas las estaciones
        ResultSet rs = con.consultar("SELECT * FROM estacion");
        listaEstaciones.clear();

        try {
            while (rs != null && rs.next()) {
                // Crea una nueva estación a partir del resultado de la consulta
                Estacion e = new Estacion(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
                listaEstaciones.add(e);

                // Carga sus mediciones desde la base de datos
                e.cargarBaseDatos();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
