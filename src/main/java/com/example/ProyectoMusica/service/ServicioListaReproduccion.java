package com.example.ProyectoMusica.service;

import com.example.ProyectoMusica.database.Conexion;
import com.example.ProyectoMusica.entity.Cancion;
import com.example.ProyectoMusica.entity.ListaReproduccion;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ServicioListaReproduccion {
    Conexion con = new Conexion();


    public List<Cancion> listarCancionesListasReproduccion(int id) throws SQLException {

        ResultSet rs = null;
        List<Cancion> canciones;
        canciones= new ArrayList<>();
        Statement consulta = con.conectar().createStatement();
        String cadena = "SELECT lr.idCancionListaReproduccion as idCancion,c.titulo, a.nombreArtista,g.nombreGenero " +
                "FROM cancion c " +
                "INNER JOIN cancion_listareproduccion lr ON c.idCancion=lr.cancion_id " +
                "INNER JOIN genero g ON c.genero_id=g.idGenero " +
                "INNER JOIN artista a ON c.artista_id=a.idArtista " +
                "WHERE lr.lista_reproduccion_id = " + id;
        rs = consulta.executeQuery(cadena);
        while (rs.next()) {
            Cancion cancion = new Cancion();
            cancion.setIdCancion(rs.getInt("idCancion"));
            cancion.setTitulo(rs.getString("titulo"));
            cancion.setNombreArtista(rs.getString("nombreArtista"));
            cancion.setNombreGenero(rs.getString("nombreGenero"));
            canciones.add(cancion);
        }
        return canciones;
    }

    public List<Cancion> listarCancionesTodasListasReproduccion() throws SQLException {

        ResultSet rs = null;
        List<Cancion> canciones;
        canciones= new ArrayList<>();
        Statement consulta = con.conectar().createStatement();
        String cadena = "SELECT c.*, a.nombreArtista, g.nombreGenero FROM Cancion c " +
                "JOIN Artista a ON c.artista_id=a.idArtista " +
                "JOIN Genero g ON c.genero_id=g.idGenero;";
        rs = consulta.executeQuery(cadena);
        while (rs.next()) {
            Cancion cancion = new Cancion();
            cancion.setIdCancion(rs.getInt("idCancion"));
            cancion.setTitulo(rs.getString("titulo"));
            cancion.setNombreArtista(rs.getString("nombreArtista"));
            cancion.setNombreGenero(rs.getString("nombreGenero"));
            canciones.add(cancion);
        }
        return canciones;
    }

    public ListaReproduccion getUnicaListaReproduccion(int id) throws SQLException {
        ListaReproduccion listaReproduccion = null;
        Statement consulta = con.conectar().createStatement();
        ResultSet result = consulta.executeQuery("SELECT * FROM listareproduccion WHERE idListaReproduccion = " + id);
        if (result.next()) {
            listaReproduccion = new ListaReproduccion(
                    result.getInt("idListaReproduccion"),
                    result.getString("nombreListaReproduccion"),
                    result.getInt("usuario_id"),
                    new ArrayList<>()
            );
        }
        result.close();
        consulta.close();
        return listaReproduccion;
    }

    public List<ListaReproduccion> listarTodasListasReproduccion() throws SQLException {
        List<ListaReproduccion> listaListaReproduccion = new ArrayList<>();
        Statement consulta = con.conectar().createStatement();
        ResultSet result = consulta.executeQuery("SELECT * FROM listareproduccion WHERE idListaReproduccion > 10");

        while (result.next()) {
            ListaReproduccion listaReproduccion = new ListaReproduccion(
                    result.getInt("idListaReproduccion"),
                    result.getString("nombreListaReproduccion"),
                    result.getInt("usuario_id"),
                    new ArrayList<>()
            );
            listaListaReproduccion.add(listaReproduccion);
        }
        result.close();
        consulta.close();
        return listaListaReproduccion;
    }
    public void agregarListaR(ListaReproduccion lr) throws SQLException {
        Statement consulta = con.conectar().createStatement();
        String cadena = "INSERT INTO listareproduccion (nombreListaReproduccion) VALUES ('" + lr.getNombreListaReproduccion() + "');";
        consulta.executeUpdate(cadena);
        consulta.close();
    }

    public void agregarCancionLR(int idCancion,int idListaR) throws SQLException {
        Statement consulta = con.conectar().createStatement();
        String cadena = "INSERT INTO cancion_listareproduccion (cancion_id,lista_reproduccion_id) VALUES ('"
                + idCancion + "','"
                + idListaR + "');";
        consulta.executeUpdate(cadena);
        consulta.close();
    }



    public void eliminar(int idCancion) throws SQLException {
        Statement consulta = con.conectar().createStatement();
        consulta.executeUpdate("DELETE FROM cancion_listareproduccion WHERE idCancionListaReproduccion = " + idCancion);
        consulta.close();
    }

    public void eliminarLR(int idListaLR) throws SQLException {
        Statement consulta = con.conectar().createStatement();
        consulta.executeUpdate("DELETE FROM cancion_listareproduccion WHERE lista_reproduccion_id = " + idListaLR);
        consulta.executeUpdate("DELETE FROM listareproduccion WHERE idListaReproduccion = " + idListaLR);
        consulta.close();
    }
    public void modificarLR(ListaReproduccion lr) throws SQLException {
        Statement consulta = con.conectar().createStatement();

        String cadena = "UPDATE listareproduccion SET nombreListaReproduccion = '" + lr.getNombreListaReproduccion() + "' WHERE idListaReproduccion = " + lr.getIdListaReproduccion();
        consulta.executeUpdate(cadena);
        consulta.close();
    }

    public List<String> imagenListaR() throws SQLException{
        List<String> imageUrls = Arrays.asList(
                "/img/musica1.png",
                "/img/musica1.png",
                "/img/musica1.png"
        );
        return imageUrls;
    }
}
