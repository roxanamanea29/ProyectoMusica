package com.example.ProyectoMusica.service;

import com.example.ProyectoMusica.database.Conexion;
import com.example.ProyectoMusica.entity.Cancion;

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
public class ServicioCancionListaReproduccion {
    Conexion con = new Conexion();
    ServicioCancion servicioCancion = new ServicioCancion();


    public List<Cancion> obtenerCancionesDeListaDeReproduccion(int listaReproduccionId) throws SQLException {//
        // Variable que guarda las canciones asociadas a esta lista de reproducción
        List<Cancion> canciones = new ArrayList<>();

        Statement consulta = con.conectar().createStatement();
        // Consulta a la tabla intermedia para obtener TODAS las canciones asociadas a la lista de reproducción con el `id` de arriba
        ResultSet result_n_m = consulta.executeQuery("SELECT * FROM `Cancion_ListaReproduccion` WHERE `lista_reproduccion_id` = " + listaReproduccionId);

        while (result_n_m.next()) {
            Cancion cancion = servicioCancion.buscar(result_n_m.getInt("cancion_id"));
            canciones.add(cancion);
        }
        result_n_m.close();
        consulta.close();
        return canciones;
    }
}
