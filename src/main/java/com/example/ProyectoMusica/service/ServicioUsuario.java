package com.example.ProyectoMusica.service;

import com.example.ProyectoMusica.database.Conexion;
import com.example.ProyectoMusica.entity.Usuario;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ServicioUsuario {
    Conexion con = new Conexion();

    public Usuario obtenerUsuario(int id) throws SQLException {
        Usuario usuario = null;
        String query = "SELECT * FROM Usuario WHERE idUsuario = " + id;
        try (Connection connection = con.conectar();
             Statement statement = connection.createStatement();
             ResultSet result = statement.executeQuery(query)) {
            if (result.next()) {
                usuario = new Usuario(
                        result.getInt("idUsuario"),
                        result.getString("nombreUsuario"),
                        result.getString("correoElectronico"),
                        result.getString("clave"),
                        result.getString("lista")
                );
            }
        } catch (SQLException e) {
            throw new SQLException("Error al obtener usuario: " + e.getMessage());
        }
        return usuario;
    }



    public List<Usuario> listarTodosUsuarios() throws SQLException {
        List<Usuario> listaUsuarios = new ArrayList<>();
        String query = "SELECT * FROM Usuario";
        try (Statement statement = con.conectar().createStatement();
             ResultSet result = statement.executeQuery(query)) {
            while (result.next()) {
                Usuario usuario = new Usuario(
                        result.getInt("idUsuario"),
                        result.getString("nombreUsuario"),
                        result.getString("correoElectronico"),
                        result.getString("clave"),
                        result.getString("lista")
                );
                listaUsuarios.add(usuario);
            }
        }
        return listaUsuarios;
    }
}
