package com.example.ProyectoMusica.service;

import com.example.ProyectoMusica.database.Conexion;
import com.example.ProyectoMusica.entity.ListaReproduccion;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ServicioListaReproduccion {
    Conexion con = new Conexion();

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
        consulta.executeUpdate("DELETE FROM listareproduccion WHERE idListaReproduccion = " + id);
        consulta.close();
    }
    public void modificar(ListaReproduccion lr) throws SQLException {
        Statement consulta = con.conectar().createStatement();

        String cadena = "UPDATE listareproduccion SET nombreListaReproduccion = '" + lr.getNombreListaReproduccion() + "' WHERE idListaReproduccion = " + lr.getIdListaReproduccion();
        consulta.executeUpdate(cadena);
        consulta.close();
    }
}