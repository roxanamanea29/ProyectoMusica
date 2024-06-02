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
     * @param id El ID de la lista de reproducción.
     * @return Una lista de canciones pertenecientes a la lista de reproducción.
     * @throws SQLException Si ocurre un error en la consulta de la base de datos.
     */
    /* Listar canciones de lista de reproduccin */

    public List<Cancion> listarCancionesListasReproduccion(int id) throws SQLException {
        ResultSet rs = null;
        List<Cancion> canciones;
        canciones= new ArrayList<>();
        Statement consulta = con.conectar().createStatement();
        String cadena = "SELECT t1.titulo FROM cancion t1 INNER JOIN cancion_listareproduccion t2" +
                " ON t1.idCancion=t2.cancion_id WHERE t2.lista_reproduccion_id = " + id;
        rs = consulta.executeQuery(cadena);
        while (rs.next()) {
            Cancion cancion = new Cancion();
            // cancion.setIdCancion(result.getInt("idCancion"));
            cancion.setTitulo(rs.getString("titulo"));
            // cancion.setIdArtista(result.getInt("artista_id"));
            //cancion.setNombreArtista(result.getString("nombreArtista"));
            //cancion.setIdGenero(result.getInt("genero_id"));
            //cancion.setNombreGenero(result.getString("nombreGenero"));
            canciones.add(cancion);
        }

        return canciones;
    }
}
