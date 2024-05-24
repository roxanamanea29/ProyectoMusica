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
 * @author Roxana & Adrian
 * @date 07/05/2024
 */
public class ServicioListaReproduccion {
    Conexion con = new Conexion();
    ListaReproduccion servicioListaReproduccion = new ListaReproduccion();

    public List<ListaReproduccion> obtenerListasDeListaDeReproduccion(int lista_id) throws SQLException {//
        // Variable que guarda las canciones asociadas a esta lista de reproducción
        List<ListaReproduccion> listas = new ArrayList<>();

        Statement consulta = con.conectar().createStatement();
        // Consulta a la tabla intermedia para obtener TODAS las listas asociadas a la lista de reproducción con el `id` de arriba
        ResultSet result_n_m = consulta.executeQuery("SELECT * FROM ListaReproduccion` WHERE `lista_reproduccion_id` = " + lista_id);

        while (result_n_m.next()) {
            ListaReproduccion listasReproducion = servicioListaReproduccion(result_n_m.getInt("listaReproduccion_Id"));
            listas.add(listasReproducion);
        }
        result_n_m.close();
        consulta.close();
        return listas;
    }

    private ListaReproduccion servicioListaReproduccion(int listaReproduccion_Id) {
        return new ListaReproduccion();
    }


    /*
    public String obtenerLista(Model model) {
        // Suponiendo que tienes un servicio que obtiene la lista del usuario
        List<ListaReproducion> listas = ServicioListaReproduccion.obtener);
        model.addAttribute("listas", listas);
        return "mi-lista"; // El nombre de tu plantilla Thymeleaf
    }
    public List<ListaReproducion> listarAllListaReproduccion() throws SQLException {//
        List<ListaReproducion> listaListaReproduccion = new ArrayList<>();
        Statement consulta = con.conectar().createStatement();


        ResultSet result = consulta.executeQuery("SELECT * FROM ListaReproduccion");

        while (result.next()) {
            int id = result.getInt("idListaReproduccion");
            List<Cancion> canciones = servicioCancionListaReproduccion.obtenerCancionesDeListaDeReproduccion(id);
            ListaReproducion listaReproduccion = new ListaReproducion(
                    id,
                    result.getString("nombreListaReproduccion"),
                    canciones
            );
            listaListaReproduccion.add(listaReproduccion);
        }
        result.close();
        consulta.close();
        return listaListaReproduccion;
    }*/
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
