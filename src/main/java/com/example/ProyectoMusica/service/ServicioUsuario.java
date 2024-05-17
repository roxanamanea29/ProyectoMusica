package com.example.ProyectoMusica.service;

import com.example.ProyectoMusica.database.Conexion;
import com.example.ProyectoMusica.entity.Usuario;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ServicioUsuario {
    Conexion con = new Conexion();
    public Usuario getUnicoUsuario(int id) throws SQLException {//
        Usuario usuario = null;
        Statement consulta = con.conectar().createStatement();
        ResultSet result = consulta.executeQuery("SELECT * FROM usuario where `idUsuario` = " + id);
        while (result.next()) {
            usuario = new Usuario(
                    result.getInt("idUsuario"),
                    result.getString("nombreUsuario")

            );
        }
        result.close();
        consulta.close();
        return usuario;
    }

    public List<Usuario> listarTodosUsuarios() throws SQLException {

        List<Usuario> listaUsuario = new ArrayList<>();

        Statement consulta = con.conectar().createStatement();

        ResultSet result = consulta.executeQuery("SELECT * FROM usuario");

        while (result.next()) {
            Usuario usuario = new Usuario(
                    result.getInt("idUsuario"),
                    result.getString("nombreUsuario")
            );
            listaUsuario.add(usuario);
        }
        result.close();
        consulta.close();
        return listaUsuario;
    }
}