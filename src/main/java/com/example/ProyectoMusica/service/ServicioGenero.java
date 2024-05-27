package com.example.ProyectoMusica.service;

import com.example.ProyectoMusica.database.Conexion;
import com.example.ProyectoMusica.entity.Cancion;
import com.example.ProyectoMusica.entity.Genero;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ServicioGenero {
    Conexion con = new Conexion();

    public Genero getUnicoGenero(int id) throws SQLException {//
        Genero genero = null;
        Statement consulta = con.conectar().createStatement();
        ResultSet result = consulta.executeQuery("SELECT * FROM genero where `idGenero` = " + id);
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
 /*   public void modificarGenero(Genero g) throws SQLException {
        Statement consulta = con.conectar().createStatement();

        String cadena = "UPDATE genero SET " + "nombreGenero = '" + g.getNombreGenero() + "' " + "WHERE idGenero = " + g.getIdGenero();
        consulta.executeUpdate(cadena);
        consulta.close();
    }*/
    public void eliminar(int id) throws SQLException {
        Statement consulta = con.conectar().createStatement();
        consulta.executeUpdate("DELETE FROM genero WHERE idGenero = " + id);
        consulta.close();
    }
}