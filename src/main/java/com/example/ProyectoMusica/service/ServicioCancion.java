package com.example.ProyectoMusica.service;

import com.example.ProyectoMusica.database.Conexion;
import com.example.ProyectoMusica.entity.Cancion;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Roxana
 * @date 07/05/2024
 */
// https://es.stackoverflow.com/questions/58252/operation-not-allowed-after-resultset-closed
public class ServicioCancion {
    Conexion con = new Conexion();


    public Cancion obtenerCancion(int id) throws SQLException {//
        Cancion cancion = null;
        Statement consulta = con.conectar().createStatement();
        ResultSet result = consulta.executeQuery("SELECT * FROM Cancion where `id` = " + id);
        while (result.next()) {
            cancion = new Cancion(
                    result.getInt("id"),
                    result.getString("titulo")


            );
        }
        result.close();
        consulta.close();
        return cancion;
    }

    public List<Cancion> listarAllCanciones() throws SQLException {//
        List<Cancion> listaCanciones = new ArrayList<>();
        Statement consulta = con.conectar().createStatement();
        ResultSet result = consulta.executeQuery("SELECT * FROM Cancion");
        while (result.next()) {
            Cancion cancion = new Cancion(
                    result.getInt("id"),
                    result.getString("titulo")
                   // result.getString("artista"),
                   // result.getString("genero")

            );
            listaCanciones.add(cancion);
        }
        result.close();
        consulta.close();
        return listaCanciones;
    }
}
