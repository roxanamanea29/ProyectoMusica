package com.example.ProyectoMusica.service;

import com.example.ProyectoMusica.database.Conexion;
import com.example.ProyectoMusica.entity.Cancion;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase para manejar el servicio de canciones en listas de reproducción.
 */
public class ServicioCancionListaReproduccion {
    private final Conexion con = new Conexion();
    private final ServicioCancion servicioCancion = new ServicioCancion();

    /**
     * Obtiene las canciones de una lista de reproducción dada su ID.
     *
     * @param listaReproduccionId El ID de la lista de reproducción.
     * @return Una lista de canciones pertenecientes a la lista de reproducción.
     * @throws SQLException Si ocurre un error en la consulta de la base de datos.
     */
    public List<Cancion> obtenerCancionesDeListaDeReproduccion(int listaReproduccionId) throws SQLException {
        // Variable que guarda las canciones asociadas a esta lista de reproducción
        List<Cancion> canciones = new ArrayList<>();

        Statement consulta = con.conectar().createStatement();
        // Consulta a la tabla intermedia para obtener todas las canciones asociadas a la lista de reproducción con el `id` de arriba
        ResultSet result_n_m = consulta.executeQuery("SELECT * FROM `Cancion_ListaReproduccion` WHERE `lista_reproduccion_id` = " + listaReproduccionId);

        while (result_n_m.next()) {
            // Obtiene la canción usando el servicioCancion y el id de la canción
            Cancion cancion = servicioCancion.buscar(result_n_m.getInt("cancion_id"));
            // Agrega la canción a la lista de canciones
            canciones.add(cancion);
        }
        // Cierra el ResultSet y el Statement
        result_n_m.close();
        consulta.close();
        return canciones;
    }
}
