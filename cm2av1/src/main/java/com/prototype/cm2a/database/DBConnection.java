package com.prototype.cm2a.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL = "jdbc:postgresql://yamabiko.proxy.rlwy.net:41438/railway";
    private static final String USER = "postgres";
    private static final String PASSWORD = "pccldnUFglddTsgjFGQbsItktpCLrEQv";

    public static Connection connection() {
        Connection con = null;
        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexión establecida con éxito.");
        } catch (SQLException e) {
            System.err.println("Error al conectar: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println("Controlador no encontrado: " + e.getMessage());
        }
        return con;
    }
}