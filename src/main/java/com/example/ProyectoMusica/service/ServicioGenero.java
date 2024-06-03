package com.example.ProyectoMusica.service;

import com.example.ProyectoMusica.database.Conexion;
import com.example.ProyectoMusica.entity.Cancion;
import com.example.ProyectoMusica.entity.Genero;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ServicioGenero {
    Conexion con = new Conexion();

    public Genero getUnicoGenero(int id) throws SQLException {//
        Genero genero = null;
        Statement consulta = con.conectar().createStatement();
        ResultSet result = consulta.executeQuery("SELECT * FROM genero where idGenero = " + id);
        while (result.next()) {
            genero = new Genero(
                    result.getInt("idGenero"),
                    result.getString("nombreGenero")
            );
        }
        result.close();
        consulta.close();
        return genero;
    }


    public List<Genero> listarTodosGeneros() throws SQLException {

        List<Genero> listaGenero = new ArrayList<>();

        Statement consulta = con.conectar().createStatement();

        ResultSet result = consulta.executeQuery("SELECT * FROM genero");

        while (result.next()) {
            Genero genero = new Genero(
                    result.getInt("idGenero"),
                    result.getString("nombreGenero")
            );
            listaGenero.add(genero);
        }
        result.close();
        consulta.close();
        return listaGenero;
    }
    public void altaGenero(Genero g)throws SQLException{
        Statement consulta = con.conectar().createStatement();

        String cadena = "INSERT into genero (nombreGenero) Values ('" + g.getNombreGenero()+"');";

        consulta.execute(cadena);
        consulta.close();
    }
    public void eliminar(int id) throws SQLException {
        Statement consulta = con.conectar().createStatement();
        consulta.executeUpdate("DELETE FROM genero WHERE idGenero = " + id);
        consulta.close();
    }
    public void modificar(Genero g) throws SQLException {
        Statement consulta = con.conectar().createStatement();

        String cadena = "UPDATE genero SET nombreGenero = '" + g.getNombreGenero() + "' WHERE idGenero = " + g.getIdGenero();
        consulta.executeUpdate(cadena);
        consulta.close();
    }
    public List<Cancion> listarCancionGenero(int id) throws SQLException {
        ResultSet rs = null;
        List<Cancion> canciones;
        canciones= new ArrayList<>();
        Statement consulta = con.conectar().createStatement();
        String cadena = "SELECT c.idCancion,c.titulo,c.genero_id, g.nombreGenero FROM Cancion c JOIN Genero g ON c.genero_id = g.idGenero where g.idGenero = " + id  ;
        rs = consulta.executeQuery(cadena);
        while (rs.next()) {
            Cancion cancion = new Cancion();
            cancion.setIdCancion(rs.getInt("idCancion"));
            cancion.setTitulo(rs.getString("titulo"));
            cancion.setIdGenero(rs.getInt("genero_id"));
            cancion.setNombreGenero(rs.getString("nombreGenero"));
            canciones.add(cancion);
        }
        return canciones;
    }
    public List<String> imagenGenero() throws SQLException{
        List<String> imageUrls = Arrays.asList(
                "/img/R&B.jpg",
                "/img/reggae.jpg",
                "/img/jazz.jpg",
                "/img/clasica.jpg",
                "/img/pop&rock.jpg",
                "/img/indie.png",
                "/img/metal.jpg",
                "/img/rap.jpg",
                "/img/country.jpg",
                "/img/soul.jpg",
                "/img/proximamente.png",
                "/img/proximamente.png",
                "/img/proximamente.png",
                "/img/proximamente.png",
                "/img/proximamente.png"
        );
        return imageUrls;
    }
}