package com.example.ProyectoMusica.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Roxana
 * @date 07/05/2024
 */
public class Conexion {
    String url = "jdbc:mysql://localhost:3306/Music_Match";
    String user = "root";
    String pass = "makai2511/";
    Connection con;

    public Connection conectar() throws SQLException {
        con = DriverManager.getConnection(url, user, pass);
        return con;
    }
}
