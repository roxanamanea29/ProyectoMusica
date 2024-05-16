package com.example.ProyectoMusica.service;

import com.example.ProyectoMusica.database.Conexion;
import com.example.ProyectoMusica.entity.Autor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Roxana
 * @date 07/05/2024
 */
// https://es.stackoverflow.com/questions/58252/operation-not-allowed-after-resultset-closed
public class ServicioAutor {
    Conexion con = new Conexion();

    public Autor obtenerAutor(int id) throws SQLException {
        Autor autor = null;
        Statement consulta = con.conectar().createStatement();
        ResultSet result = consulta.executeQuery("SELECT * FROM Autor where 'id' = " + id);
        while (result.next()) {
            autor = new Autor(
                    result.getInt("id"),
                    result.getString("nombre")

            );
        }
        result.close();
        consulta.close();
        return autor;
    }
    public Autor obtenerCanción(int cancion_id) throws SQLException {
        Autor autor = null;
        Statement consulta = con.conectar().createStatement();
        ResultSet result = consulta.executeQuery("SELECT * FROM Autor where 'cancion_id' = " + cancion_id);
        while (result.next()) {
            autor = new Autor(
                    result.getInt("cancion_id"),
                    result.getString("titulo")

            );
        }
        result.close();
        consulta.close();
        return autor;
    }
}
