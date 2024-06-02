package com.example.ProyectoMusica.service;

import com.example.ProyectoMusica.database.Conexion;
import com.example.ProyectoMusica.entity.*;
import com.example.ProyectoMusica.entity.Cancion;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ServicioListaReproduccion {
    Conexion con = new Conexion();

    /* Listar canciones de lista de reproduccion */

    public List<Cancion> listarCancionesListasReproduccion(int id) throws SQLException {
        ResultSet rs = null;
        List<Cancion> canciones;
        canciones= new ArrayList<>();
        Statement consulta = con.conectar().createStatement();
        String cadena = "SELECT lr.idCancionListaReproduccion as idCancion,c.titulo, a.nombreArtista,g.nombreGenero "+
        "FROM cancion c "+
        "INNER JOIN cancion_listareproduccion lr "+
        "ON c.idCancion=lr.cancion_id "+
        "INNER JOIN genero g "+
        "ON c.genero_id=g.idGenero "+
        "INNER JOIN artista a "+
        "ON c.artista_id=a.idArtista "+
        "WHERE lr.lista_reproduccion_id = " + id;
        rs = consulta.executeQuery(cadena);
        while (rs.next()) {
            Cancion cancion = new Cancion();
            cancion.setIdCancion(rs.getInt("idCancion"));
            cancion.setTitulo(rs.getString("titulo"));
            //cancion.setIdArtista(result.getInt("artista_id"));
            cancion.setNombreArtista(rs.getString("nombreArtista"));
            //cancion.setIdGenero(result.getInt("genero_id"));
            cancion.setNombreGenero(rs.getString("nombreGenero"));
            canciones.add(cancion);
        }

        return canciones;
    }

    public List<ListaReproduccion> listarTodasListasReproduccion() throws SQLException {
        List<ListaReproduccion> listaListaReproduccion = new ArrayList<>();
        Statement consulta = con.conectar().createStatement();
        ResultSet result = consulta.executeQuery("SELECT * FROM listareproduccion");

        while (result.next()) {
            ListaReproduccion listaReproduccion = new ListaReproduccion(
                    result.getInt("idListaReproduccion"),
                    result.getString("nombreListaReproduccion"),
                    result.getInt("usuario_id"),
                    new ArrayList<>() // Inicializamos la lista de canciones vacía por defecto
            );
            listaListaReproduccion.add(listaReproduccion);
        }
        result.close();
        consulta.close();
        return listaListaReproduccion;
    }



    public void agregar(ListaReproduccion lr) throws SQLException {
        Statement consulta = con.conectar().createStatement();
        String cadena = "INSERT INTO listareproduccion (nombreListaReproduccion) VALUES ('" + lr.getNombreListaReproduccion() + "')";
        consulta.executeUpdate(cadena);
        consulta.close();
    }



    public void eliminar(int id) throws SQLException {
        Statement consulta = con.conectar().createStatement();
        consulta.executeUpdate("DELETE FROM cancion_listareproduccion WHERE idCancionListaReproduccion = " + id);
        consulta.close();
    }
    public void modificar(ListaReproduccion lr) throws SQLException {
        Statement consulta = con.conectar().createStatement();

        String cadena = "UPDATE listareproduccion SET nombreListaReproduccion= '" + lr.getNombreListaReproduccion() + "' WHERE idListaReproduccion = " + lr.getIdListaReproduccion();
        consulta.executeUpdate(cadena);
        consulta.close();
    }

}
