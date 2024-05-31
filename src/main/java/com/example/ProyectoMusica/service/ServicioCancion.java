package com.example.ProyectoMusica.service;

import com.example.ProyectoMusica.database.Conexion;
import com.example.ProyectoMusica.entity.Cancion;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 * @author Roxana
 * @date 07/05/2024
 */

// https://es.stackoverflow.com/questions/58252/operation-not-allowed-after-resultset-closed
@Service
public class ServicioCancion {
    Statement consulta;
    Conexion con = new Conexion();

    private final ServicioArtista servicioArtista = new ServicioArtista();
    private final ServicioGenero servicioGenero = new ServicioGenero();


    public void alta(Cancion cancion_x) throws SQLException {

            consulta = con.conectar().createStatement();
            String cadena = "INSERT INTO Cancion (titulo, artista_id, genero_id) VALUES ('"+ cancion_x.getTitulo() + "','" + cancion_x.getIdArtista() + "','" + cancion_x.getIdGenero() + "')";
            consulta.executeUpdate(cadena);

    }

    public List<Cancion> listar() throws SQLException {
        ResultSet rs = null;
        List<Cancion> canciones;
        canciones= new ArrayList<>();
            consulta = con.conectar().createStatement();
            String cadena = "SELECT c.*, a.nombreArtista, g.nombreGenero FROM Cancion c " +
                    "JOIN Artista a ON c.artista_id=a.idArtista " +
                    "JOIN Genero g ON c.genero_id=g.idGenero;";
            rs = consulta.executeQuery(cadena);
            while (rs.next()) {
                Cancion cancion = new Cancion();
                cancion.setIdCancion(rs.getInt("idCancion"));
                cancion.setTitulo(rs.getString("titulo"));
                cancion.setIdArtista(rs.getInt("artista_id"));
                cancion.setNombreArtista(rs.getString("nombreArtista"));
                cancion.setIdGenero(rs.getInt("genero_id"));
                cancion.setNombreGenero(rs.getString("nombreGenero"));
                canciones.add(cancion);
            }
        return canciones;
    }

    public Cancion buscar(int id) throws SQLException {
        Cancion cancion = new Cancion();
        ResultSet rs = null;
        consulta = con.conectar().createStatement();
        String query = "SELECT c.*, a.nombreArtista, g.nombreGenero FROM Cancion c JOIN Artista a ON c.artista_id=a.idArtista JOIN Genero g ON c.genero_id=g.idGenero WHERE idCancion =" +id+";";
      rs = consulta.executeQuery(query);
            while (rs.next()) {
                cancion.setTitulo(rs.getString("titulo"));
                cancion.setIdArtista(rs.getInt("artista_id"));
                cancion.setNombreArtista(rs.getString("nombreArtista"));
                cancion.setIdGenero(rs.getInt("genero_id"));
                cancion.setNombreGenero(rs.getString("nombreGenero"));
                cancion.setIdCancion(rs.getInt("idCancion"));
                return cancion;
            }
        return null;
    }


    public void eliminarCancion( int idC) throws SQLException {
        consulta = con.conectar().createStatement();
        String cadena = "DELETE FROM Cancion WHERE idCancion="+ idC + ";";
        consulta.executeUpdate(cadena);
    }

    public void modificarCancion(Cancion cancion) throws SQLException {
        consulta = con.conectar().createStatement();
        String cadena = "UPDATE Cancion SET titulo='" + cancion.getTitulo() + "', artista_id=" + cancion.getIdArtista() + ", genero_id=" + cancion.getIdGenero() + " WHERE idCancion=" + cancion.getIdCancion();
        consulta.executeUpdate(cadena);
    }


    public ServicioArtista getServicioArtista() {
        return servicioArtista;
    }
}







