package com.example;

import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class Meteorologia {

    public static List<Estacion> listaEstaciones = new ArrayList<>();

    public static void rellenarDatosPrueba() {
        listaEstaciones.clear();

        Estacion e1 = new Estacion(1, "Estación Norte", "Bilbao", "Vizcaya");
        Estacion e2 = new Estacion(2, "Estación Sur", "Sevilla", "Andalucía");
        Estacion e3 = new Estacion(3, "Estación Este", "Valencia", "Comunidad Valenciana");
        Estacion e4 = new Estacion(4, "Estación Oeste", "A Coruña", "Galicia");
        Estacion e5 = new Estacion(5, "Estación Centro", "Madrid", "Comunidad de Madrid");

        // Estación 1
        e1.agregarMedicion(1, 1, "11", "01:00", 16, 0.1, 1011, 51);
        e1.agregarMedicion(2, 1, "12", "02:00", 17, 0.2, 1012, 52);
        e1.agregarMedicion(3, 1, "13", "03:00", 18, 0.3, 1013, 53);
        e1.agregarMedicion(4, 1, "14", "04:00", 19, 0.4, 1014, 54);
        e1.agregarMedicion(5, 1, "15", "05:00", 20, 0.5, 1015, 55);
        e1.agregarMedicion(6, 1, "16", "06:00", 21, 0.6, 1016, 56);
        e1.agregarMedicion(7, 1, "17", "07:00", 22, 0.7, 1017, 57);
        e1.agregarMedicion(8, 1, "18", "08:00", 23, 0.8, 1018, 58);
        e1.agregarMedicion(9, 1, "19", "09:00", 24, 0.9, 1019, 59);
        e1.agregarMedicion(10, 1, "20", "10:00", 25, 1.0, 1020, 60);

        // Estación 2
        e2.agregarMedicion(1, 2, "21", "11:00", 26, 0.0, 1001, 61);
        e2.agregarMedicion(2, 2, "22", "12:00", 27, 0.0, 1002, 62);
        e2.agregarMedicion(3, 2, "23", "13:00", 28, 0.0, 1003, 63);
        e2.agregarMedicion(4, 2, "24", "14:00", 29, 0.0, 1004, 64);
        e2.agregarMedicion(5, 2, "25", "15:00", 30, 0.0, 1005, 65);
        e2.agregarMedicion(6, 2, "26", "16:00", 31, 0.0, 1006, 66);
        e2.agregarMedicion(7, 2, "27", "17:00", 32, 0.0, 1007, 67);
        e2.agregarMedicion(8, 2, "28", "18:00", 33, 0.0, 1008, 68);
        e2.agregarMedicion(9, 2, "29", "19:00", 34, 0.0, 1009, 69);
        e2.agregarMedicion(10, 2, "30", "20:00", 35, 0.0, 1010, 70);

        // Estación 3
        e3.agregarMedicion(1, 3, "31", "01:30", 21, 0.2, 991, 56);
        e3.agregarMedicion(2, 3, "32", "02:30", 22, 0.4, 992, 57);
        e3.agregarMedicion(3, 3, "33", "03:30", 23, 0.6, 993, 58);
        e3.agregarMedicion(4, 3, "34", "04:30", 24, 0.8, 994, 59);
        e3.agregarMedicion(5, 3, "35", "05:30", 25, 1.0, 995, 60);
        e3.agregarMedicion(6, 3, "36", "06:30", 26, 1.2, 996, 61);
        e3.agregarMedicion(7, 3, "37", "07:30", 27, 1.4, 997, 62);
        e3.agregarMedicion(8, 3, "38", "08:30", 28, 1.6, 998, 63);
        e3.agregarMedicion(9, 3, "39", "09:30", 29, 1.8, 999, 64);
        e3.agregarMedicion(10, 3, "40", "10:30", 30, 2.0, 1000, 65);

        // Estación 4
        e4.agregarMedicion(1, 4, "41", "11:30", 11, 0.3, 981, 66);
        e4.agregarMedicion(2, 4, "42", "12:30", 12, 0.6, 982, 67);
        e4.agregarMedicion(3, 4, "43", "13:30", 13, 0.9, 983, 68);
        e4.agregarMedicion(4, 4, "44", "14:30", 14, 1.2, 984, 69);
        e4.agregarMedicion(5, 4, "45", "15:30", 15, 1.5, 985, 70);
        e4.agregarMedicion(6, 4, "46", "16:30", 16, 1.8, 986, 71);
        e4.agregarMedicion(7, 4, "47", "17:30", 17, 2.1, 987, 72);
        e4.agregarMedicion(8, 4, "48", "18:30", 18, 2.4, 988, 73);
        e4.agregarMedicion(9, 4, "49", "19:30", 19, 2.7, 989, 74);
        e4.agregarMedicion(10, 4, "50", "20:30", 20, 3.0, 990, 75);

        // Estación 5
        e5.agregarMedicion(1, 5, "51", "01:45", 19, 0.1, 996, 58);
        e5.agregarMedicion(2, 5, "52", "02:45", 20, 0.2, 997, 59);
        e5.agregarMedicion(3, 5, "53", "03:45", 21, 0.3, 998, 60);
        e5.agregarMedicion(4, 5, "54", "04:45", 22, 0.4, 999, 61);
        e5.agregarMedicion(5, 5, "55", "05:45", 23, 0.5, 1000, 62);
        e5.agregarMedicion(6, 5, "56", "06:45", 24, 0.6, 1001, 63);
        e5.agregarMedicion(7, 5, "57", "07:45", 25, 0.7, 1002, 64);
        e5.agregarMedicion(8, 5, "58", "08:45", 26, 0.8, 1003, 65);
        e5.agregarMedicion(9, 5, "59", "09:45", 27, 0.9, 1004, 66);
        e5.agregarMedicion(10, 5, "60", "10:45", 28, 1.0, 1005, 67);

        listaEstaciones.addAll(List.of(e1, e2, e3, e4, e5));
    }

    public static void escribirFichero() {

        try {
            File fichero = new File("mediciones.data");
            fichero.delete();
            FileWriter fw = new FileWriter("estaciones.csv");

            for (Estacion estacion : listaEstaciones) {

                fw.write(estacion.getId() + ";" + estacion.getNombre() + ";" + estacion.getPoblacion() + ";"
                        + estacion.getProvincia() + "\n");
                estacion.escribirMediciones();

            }
            fw.close();

        } catch (Exception e) {

            System.out.println("Error al escribir en el fichero estaciones");
        }

    }

    public static void cargarFichero() {
        String linea;

        listaEstaciones.clear();

        try {
            BufferedReader br = new BufferedReader(new FileReader("estaciones.csv"));

            while ((linea = br.readLine()) != null) {

                String[] split = linea.split(";");

                Estacion e = new Estacion(Integer.parseInt(split[0]), split[1], split[2], split[3]);

                Meteorologia.listaEstaciones.add(e);

                e.cargarMediciones();

            }

            br.close();

        } catch (Exception e) {

            System.out.println("Error");
        }

    }

    public static void guardarBaseDatos() {

        ConexionDB con = new ConexionDB();
        int filasTotales = 0;

        // Borra primero mediciones y luego estaciones (orden correcto por clave
        // foránea)
        con.borrar("DELETE FROM medicion");
        con.borrar("DELETE FROM estacion");

        for (Estacion estacion : listaEstaciones) {
            // Corrección: cierre de paréntesis al final
            String sql = "INSERT INTO estacion (id, nombre, poblacion, provincia) VALUES (" +
                    estacion.getId() + ", '" +
                    estacion.getNombre() + "', '" +
                    estacion.getPoblacion() + "', '" +
                    estacion.getProvincia() + "')"; // <- paréntesis corregido aquí

            try {
                int filas = con.insertar(sql);
                filasTotales += filas;

                // Guardar mediciones asociadas a la estación
                estacion.guardarBaseDatos();
            } catch (Exception e) {
                System.err.println("Error al guardar estación con ID " + estacion.getId() + ": " + e.getMessage());
            }
        }

        System.out.println("Filas insertadas en tabla estacion: " + filasTotales);
    }

    public static void cargarBaseDatos() {
        ConexionDB con = new ConexionDB();

        ResultSet rs = con.consultar("SELECT * FROM estacion");

        listaEstaciones.clear();

        try {
            while (rs != null && rs.next()) {

                Estacion e = new Estacion(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));

                Meteorologia.listaEstaciones.add(e);

                e.cargarBaseDatos();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
