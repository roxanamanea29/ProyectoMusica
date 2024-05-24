package com.example.ProyectoMusica.service;

import com.example.ProyectoMusica.database.Conexion;
import com.example.ProyectoMusica.entity.Cancion;
import com.example.ProyectoMusica.entity.ListaReproduccion;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Adrian
 * @date 07/05/2024
 */
public class ServicioListaReproduccion {
    Conexion con = new Conexion();
    ListaReproduccion servicioListaReproduccion = new ListaReproduccion();


        Statement consulta = con.conectar().createStatement();


        ResultSet result = consulta.executeQuery("SELECT * FROM ListaReproduccion");

        while (result.next()) {
            int id = result.getInt("idListaReproduccion");
            List<Cancion> canciones = servicioCancionListaReproduccion.obtenerCancionesDeListaDeReproduccion(id);
            ListaReproduccion listaReproduccion = new ListaReproduccion(
                    id,
                    result.getString("nombreListaReproduccion"),
                    canciones
            );
            listaListaReproduccion.add(listaReproduccion);
        }
        result.close();
        consulta.close();
        return listaListaReproduccion;

    }

    }

    public void modificar(ListaReproduccion p) throws SQLException {
        Statement consulta = con.conectar().createStatement();
        String cadena = "UPDATE lista SET "
                + "id = '" + p.getId() + "', "
                + "id_nombre = '" + p.getNombre() + "', "
                + "canciones = '" + p.getCanciones() + "', ";
        // System.out.println(cadena);
        consulta.executeUpdate(cadena);
        consulta.close();
    }
    public void eliminar(int id) throws SQLException {
        Statement consulta = con.conectar().createStatement();
        consulta.executeUpdate("DELETE FROM lista WHERE id = " + id);
        consulta.close();
    }

    public List<ListaReproduccion> listarAllListaReproduccion(ListaReproduccion listaReproducion) throws SQLException{
        List<ListaReproduccion> listaReproduccions = new ArrayList<>();
        Statement consulta = con.conectar().createStatement();
        ResultSet result = consulta.executeQuery("SELECT idListaReproduccion, nombreListaReproduccion FROM ListaReproduccion");
        while (result.next()) {
            ListaReproduccion ListaReproduccion = new ListaReproduccion(
                    result.getInt("idListaReproduccion"),
                    result.getString("nombreListaReproduccion")
            );
            listaReproduccions.add(listaReproducion);
        }
        result.close();
        consulta.close();
        return listaReproduccions;
    }

    public List<ListaReproduccion> listarAllListaReproduccion() {
        return listarAllListaReproduccion();
    }
}
