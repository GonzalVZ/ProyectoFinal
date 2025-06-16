package com.example;

import java.sql.*;

// Clase encargada de manejar la conexión a la base de datos y ejecutar consultas SQL
public class ConexionDB {

    // Objeto de conexión compartido (patrón singleton simplificado)
    private static Connection conexion = null;

    // Contador de instancias creadas de esta clase (para fines de control o depuración)
    private static int contadorInstancias = 0;

    // Parámetros de conexión a la base de datos
    private final String url = "jdbc:mysql://localhost:3306/main"; // URL de conexión (ajustar si es necesario)
    private final String usuario = "root";                         // Usuario de la base de datos
    private final String contraseña = "root*";                     // Contraseña de acceso

    // Constructor que establece la conexión si aún no existe
    public ConexionDB() {
        if (conexion == null) {
            try {
                // Carga del driver de MySQL
                Class.forName("com.mysql.cj.jdbc.Driver");
                // Establece la conexión con la base de datos
                conexion = DriverManager.getConnection(url, usuario, contraseña);
                System.out.println("Conexion establecida!");
            } catch (Exception e) {
                // Captura errores de conexión o carga del driver
                System.out.println("Error al conectar: " + e.getMessage());
            }
        }
        // Incrementa el contador de instancias de la clase
        contadorInstancias++;
        System.out.println("Instancias de ConexionDB: " + contadorInstancias);
    }

    // Ejecuta una consulta SELECT y devuelve un ResultSet con los resultados
    public ResultSet consultar(String query) {
        try {
            Statement stmt = conexion.createStatement();
            return stmt.executeQuery(query);
        } catch (SQLException e) {
            // En caso de error en la consulta, imprime mensaje y devuelve null
            System.out.println("Error en la consulta: " + e.getMessage());
            return null;
        }
    }

    // Ejecuta una instrucción INSERT y devuelve el número de filas afectadas
    public int insertar(String query) {
        try {
            Statement stmt = conexion.createStatement();
            return stmt.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println("Error al insertar: " + e.getMessage());
            return -1;
        }
    }

    // Ejecuta una instrucción DELETE y devuelve el número de filas eliminadas
    public int borrar(String query) {
        try {
            Statement stmt = conexion.createStatement();
            return stmt.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println("Error al borrar: " + e.getMessage());
            return -1;
        }
    }

    // Cierra la conexión con la base de datos si está abierta
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

    // Devuelve el número de veces que se ha instanciado la clase ConexionDB
    public static int getContadorInstancias() {
        return contadorInstancias;
    }
}
