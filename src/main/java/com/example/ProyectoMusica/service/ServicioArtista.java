package com.example.ProyectoMusica.service;

import com.example.ProyectoMusica.database.Conexion;
import com.example.ProyectoMusica.entity.Artista;

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
public class ServicioArtista {
    Conexion con = new Conexion();

    public Artista getArtista(int idArt) throws SQLException {
        Artista artista = null;
        Statement consulta = con.conectar().createStatement();
        ResultSet result = consulta.executeQuery("SELECT * FROM artista where idArtista = " + idArt);
        while (result.next()) {
            artista = new Artista(
                    result.getInt("idArtista"),
                    result.getString("nombreArtista")

            );
        }
        result.close();
        consulta.close();
        return artista;
    }
    public List<Artista> listarArtista() throws SQLException {

        List<Artista> listaArtista = new ArrayList<>();

        Statement consulta = con.conectar().createStatement();

        ResultSet result = consulta.executeQuery("SELECT * FROM artista");

        while (result.next()) {
            Artista artista = new Artista(
                    result.getInt("idArtista"),
                    result.getString("nombreArtista")
            );
            listaArtista.add(artista);
        }
        result.close();
        consulta.close();
        return listaArtista;
    }
}
