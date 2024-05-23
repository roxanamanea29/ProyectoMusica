package com.example.ProyectoMusica.service;

import com.example.ProyectoMusica.database.Conexion;
import com.example.ProyectoMusica.entity.Cancion;
import com.example.ProyectoMusica.entity.Genero;

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

    public Cancion obtenerCancion(int id) throws SQLException {
        Cancion cancion = null;
        Statement consulta = con.conectar().createStatement();
        ResultSet result = consulta.executeQuery(
                "SELECT c.idCancion, c.titulo, g.idGenero, g.nombreGenero, a.idArtista, a.nombreArtista " +
                        "FROM cancion c " +
                        "JOIN genero g ON c.genero_id = g.idGenero " +
                        "JOIN artista a ON c.artista_id = a.idArtista " +
                        "WHERE c.idCancion = " + id
        );
        if (result.next()) {
            cancion = new Cancion(
                    result.getString("titulo"),
                    result.getInt("idCancion"),
                    result.getString("nombreGenero"),
                    result.getInt("idGenero"),
                    result.getInt("idArtista"),
                    result.getString("nombreArtista")
            );
        }
        result.close();
        consulta.close();
        return cancion;
    }


    public List<Cancion> listarAllCanciones() throws SQLException {
        List<Cancion> listaCanciones = new ArrayList<>();
        Statement consulta = con.conectar().createStatement();
        ResultSet result = consulta.executeQuery(
                "SELECT c.idCancion, c.titulo, g.idGenero, g.nombreGenero, a.idArtista, a.nombreArtista " +
                        "FROM cancion c " +
                        "JOIN genero g ON c.genero_id = g.idGenero " +
                        "JOIN artista a ON c.artista_id = a.idArtista"
        );
        while (result.next()) {
            Cancion cancion = new Cancion(
                    result.getString("titulo"),
                    result.getInt("idCancion"),
                    result.getString("nombreGenero"),
                    result.getInt("idGenero"),
                    result.getInt("idArtista"),
                    result.getString("nombreArtista")
            );
            listaCanciones.add(cancion);
        }
        result.close();
        consulta.close();
        return listaCanciones;
    }

}
