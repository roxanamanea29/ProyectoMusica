package com.example.ProyectoMusica.service;

import com.example.ProyectoMusica.database.Conexion;
import com.example.ProyectoMusica.entity.Cancion;
import com.example.ProyectoMusica.entity.ListaReproducion;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Roxana
 * @date 07/05/2024
 */
public class ServicioListaReproduccion {
    Conexion con = new Conexion();
    ServicioCancionListaReproduccion servicioCancionListaReproduccion = new ServicioCancionListaReproduccion();

    public List<ListaReproducion> listarAllListaReproduccion() throws SQLException {//
        List<ListaReproducion> listaListaReproduccion = new ArrayList<>();
        Statement consulta = con.conectar().createStatement();

        // Obtengo todas las listas de reproduccion
        ResultSet result = consulta.executeQuery("SELECT * FROM ListaReproduccion");

        while (result.next()) {
            int id = result.getInt("id");
            // Vamos a la tabla intermedia para obtener TODAS las canciones que PERTENECEN a esta lista de reproduccion
            List<Cancion> canciones = servicioCancionListaReproduccion.obtenerCancionesDeListaDeReproduccion(id);
            ListaReproducion listaReproduccion = new ListaReproducion(
                    id,
                    result.getString("nombre"),
                    canciones
            );
            listaListaReproduccion.add(listaReproduccion);
        }
        result.close();
        consulta.close();
        return listaListaReproduccion;
    }
}
