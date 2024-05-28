package com.example.ProyectoMusica.service;

import com.example.ProyectoMusica.database.Conexion;
import com.example.ProyectoMusica.entity.Artista;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Renzo
 * @date 07/05/2024
 */

@Service
public class ServicioArtista {
    static Conexion con = new Conexion();

    public Artista getArtista(int idArtista) throws SQLException {
        Artista artista = null;
        String query = "SELECT * FROM artista WHERE idArtista = " + idArtista;
        try (Connection connection = con.conectar();
             PreparedStatement consulta = connection.prepareStatement(query)) {
            consulta.setInt(1, idArtista);
            try (ResultSet result = consulta.executeQuery()) {
                if (result.next()) {
                    artista = new Artista(
                            result.getInt("idArtista"),
                            result.getString("nombreArtista")
                    );
                }
            }
        }
        return artista;
    }
    public List<Artista> listarArtista() throws SQLException {

        List<Artista> listaArtista = new ArrayList<>();

        String query = "SELECT * FROM artista";
        try (Connection connection = con.conectar();
             Statement consulta = connection.createStatement();
             ResultSet result = consulta.executeQuery(query)) {
            while (result.next()) {
                Artista artista = new Artista(
                        result.getInt("idArtista"),
                        result.getString("nombreArtista")
                );
                listaArtista.add(artista);
            }
        }
        return listaArtista;
    }
    public void modificarArtista(Artista a) throws SQLException {
        Statement consulta = con.conectar().createStatement();

        consulta.executeUpdate("UPDATE artista SET "
                + "nombreArtista = '" + a.getNombreArtista() + "', "
                + "WHERE idArtista = " + a.getIdArtista());
        consulta.close();
    }
}
