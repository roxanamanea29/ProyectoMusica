package com.example.ProyectoMusica.service;

import com.example.ProyectoMusica.database.Conexion;
import com.example.ProyectoMusica.entity.Usuario;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServicioUsuario {
    private final Conexion conexion = new Conexion();
    private final Key clave;

    public ServicioUsuario() {
        // Clave de encriptación
        String claveEncriptacion = "claveencriptacion"; // Clave de encriptación personalizada
        clave = new SecretKeySpec(claveEncriptacion.getBytes(), "AES");
    }

    // Método para registrar un nuevo usuario
    public void registrarUsuario(String nombre, String email, String clave) throws SQLException {
        try (Connection con = conexion.conectar();
             PreparedStatement ps = con.prepareStatement("INSERT INTO Usuario (nombreUsuario, correoElectronico, clave) VALUES (?, ?, AES_ENCRYPT(?, ?))")) {
            ps.setString(1, nombre);
            ps.setString(2, email);
            ps.setString(3, clave);
            ps.setString(4, clave);
            ps.executeUpdate();
        }
    }

    // Método para iniciar sesión
    public Usuario iniciarSesion(String email, String clave) throws SQLException {
        try (Connection con = conexion.conectar();
             PreparedStatement ps = con.prepareStatement("SELECT * FROM Usuario WHERE correoElectronico = ? AND AES_DECRYPT(clave, ?) = ?")) {
            ps.setString(1, email);
            ps.setString(2, clave);
            ps.setString(3, clave);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Usuario(
                            rs.getInt("idUsuario"),
                            rs.getString("nombreUsuario"),
                            rs.getString("correoElectronico"),
                            rs.getString("clave"),
                            rs.getString("lista")

                    );
                }
            }
        }
        return null;
    }

    // Método para encriptar una cadena de texto
    private byte[] encriptar(String texto) throws SQLException {
        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, clave);
            return cipher.doFinal(texto.getBytes());
        } catch (Exception e) {
            throw new SQLException("Error al encriptar la clave.", e);
        }
    }

    public List<Usuario> listarTodosUsuarios() throws SQLException {
        ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
        Connection con = conexion.conectar();
        PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM Usuario");
        try (ResultSet rs = preparedStatement.executeQuery()) {
            while (rs.next()) {
                 usuarios.add(                 new Usuario(
                         rs.getInt("idUsuario"),
                         rs.getString("nombreUsuario"),
                         rs.getString("correoElectronico"),
                         rs.getString("clave"),
                         rs.getString("lista")
                 ));
            }
        }
        return usuarios;
    }
}
