package com.example;

import java.sql.*;

public class ConexionDB {

    private static Connection conexion = null;
    private static int contadorInstancias = 0;

    private final String url = "jdbc:mysql://localhost:3306/main";
    private final String usuario = "root";
    private final String contraseña = "root*";


    public ConexionDB() {
        if (conexion == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conexion = DriverManager.getConnection(url, usuario, contraseña);
                System.out.println("Conexion establecida!");
            } catch (Exception e) {
                System.out.println("Error al conectar: " + e.getMessage());
            }
        }
        contadorInstancias++;
        System.out.println("Instancias de ConexionDB: " + contadorInstancias);
    }

    public ResultSet consultar(String query) {
        try {
            Statement stmt = conexion.createStatement();
            return stmt.executeQuery(query);
        } catch (SQLException e) {
            System.out.println("Error en la consulta: " + e.getMessage());
            return null;
        }
    }

    public int insertar(String query) {
        try {
            Statement stmt = conexion.createStatement();
            return stmt.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println("Error al insertar: " + e.getMessage());
            return -1;
        }
    }

    public int borrar(String query) {
        try {
            Statement stmt = conexion.createStatement();
            return stmt.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println("Error al borrar: " + e.getMessage());
            return -1;
        }
    }

    public static void cerrarConexion() {
        if (conexion != null) {
            try {
                conexion.close();
                conexion = null;
                System.out.println("Conexión cerrada.");
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }

    public static int getContadorInstancias() {
        return contadorInstancias;
    }
}




/*package com.example;

import java.sql.*;

public class Main {
    public static void main(String[] args) {
        ConexionDB db = new ConexionDB();

        // CONSULTAR
        ResultSet rs = db.consultar("SELECT * FROM tabla");
        try {
            while (rs != null && rs.next()) {
                System.out.println("Dato: " + rs.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // INSERTAR
        int filas = db.insertar("INSERT INTO tabla (columna) VALUES ('dato')");
        System.out.println("Filas insertadas: " + filas);

        // BORRAR
        int eliminadas = db.borrar("DELETE FROM tabla WHERE id = 1");
        System.out.println("Filas eliminadas: " + eliminadas);

        // CERRAR CONEXIÓN
        ConexionDB.cerrarConexion();
    }
}
 */