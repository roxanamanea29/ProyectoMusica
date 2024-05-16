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
}
