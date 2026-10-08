package com.mycompany.miubank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexionBD {
    private static final String URL = "jdbc:postgresql://localhost:5432/miubank_db";
    private static final String USUARIO = "postgres";
    private static final String CONTRASENA = "postgres";

    private static Connection conexion;

    public static Connection conectar() {
        try {
            Class.forName("org.postgresql.Driver");
            conexion = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
            System.out.println("Conexión a PostgreSQL establecida.");
            crearTablasSiNoExisten();
            return conexion;
        } catch (ClassNotFoundException e) {
            System.out.println("No se encontró el driver de PostgreSQL.");
            e.printStackTrace();
            return null;
        } catch (SQLException e) {
            System.out.println("Error de conexión a PostgreSQL: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    private static void crearTablasSiNoExisten() {
        String sql = "CREATE TABLE IF NOT EXISTS prestamos ("
                + "id SERIAL PRIMARY KEY,"
                + "cliente VARCHAR(100) NOT NULL,"
                + "cedula VARCHAR(30) NOT NULL UNIQUE,"
                + "monto DOUBLE PRECISION NOT NULL,"
                + "tasa DOUBLE PRECISION NOT NULL,"
                + "plazo INTEGER NOT NULL,"
                + "tipo VARCHAR(50) NOT NULL,"
                + "fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,"
                + "estado VARCHAR(30) DEFAULT 'Activo'"
                + ");";

        try (Statement stmt = obtenerConexion().createStatement()) {
            stmt.execute(sql);
            System.out.println("Tabla prestamos verificada.");
        } catch (SQLException e) {
            System.out.println("Error al crear la tabla prestamos: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static Connection obtenerConexion() {
        if (conexion == null) {
            return conectar();
        }
        return conexion;
    }
}
