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
    Conexion con = new Conexion();

    public Artista getUnicoArtista(int id) throws SQLException {//
        Artista artista = null;
        Statement consulta = con.conectar().createStatement();
        ResultSet result = consulta.executeQuery("SELECT * FROM Artista where idArtista = " + id);
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


    public List<Artista> listarTodosLosArtistas() throws SQLException {

        List<Artista> listaArtista = new ArrayList<>();

        Statement consulta = con.conectar().createStatement();

        ResultSet result = consulta.executeQuery("SELECT * FROM artista");

        while (result.next()) {
            Artista artista  = new Artista(
                    result.getInt("idArtista"),
                    result.getString("nombreArtista")
            );
            listaArtista.add(artista);
        }
        result.close();
        consulta.close();
        return listaArtista;
    }
    public void altaArtista(Artista artista)throws SQLException{
        Statement consulta = con.conectar().createStatement();

        String cadena = "INSERT into Artista (nombreArtista) Values ('" + artista.getNombreArtista()+"');";

        consulta.execute(cadena);
        consulta.close();
    }
    public void eliminar(int id) throws SQLException {
        Statement consulta = con.conectar().createStatement();
        consulta.executeUpdate("DELETE FROM Artista WHERE idArtista = " + id);
        consulta.close();
    }
    public void modificar(Artista a) throws SQLException {
        Statement consulta = con.conectar().createStatement();

        String cadena = "UPDATE Artista SET nombreArtista = '" + a.getNombreArtista() + "' WHERE idArtista = " + a.getIdArtista();
        consulta.executeUpdate(cadena);
        consulta.close();
    }
}