package com.example.ProyectoMusica.service;

import com.example.ProyectoMusica.database.Conexion;
import com.example.ProyectoMusica.entity.Usuario;
import com.example.ProyectoMusica.entity.Usuario;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.sql.*;
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
    public void registrarUsuario(String nombreUsuario, String email, String clave) throws SQLException {
        try (Connection con = conexion.conectar();
             PreparedStatement ps = con.prepareStatement("INSERT INTO Usuario (nombreUsuario, correoElectronico, clave) VALUES (?, ?, AES_ENCRYPT(?, ?))")) {
            ps.setString(1, nombreUsuario);
            ps.setString(2, email);
            ps.setString(3, clave);
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
                            rs.getString("clave")
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
                usuarios.add(new Usuario(
                        rs.getInt("idUsuario"),
                        rs.getString("nombreUsuario"),
                        rs.getString("correoElectronico"),
                        rs.getString("clave")
                ));
            }
        }
        return usuarios;
    }

    public void alta(Usuario p) throws SQLException {
        Statement consulta = conexion.conectar().createStatement();
        String cadena = "INSERT INTO usuario(nombreUsuario, correoElectronico, clave) VALUES ('"
                + p.getNombreUsuario() + "','"
                + p.getCorreoElectronico() + "','"
                + p.getClave() + "');";
        consulta.executeUpdate(cadena);
        consulta.close();
    }

    public void altaUsuario(Usuario g) throws SQLException {
        Statement consulta = conexion.conectar().createStatement();
        String cadena = "INSERT INTO usuario (nombreUsuario, correoElectronico, clave) values ('" + g.getNombreUsuario() + "', '" + g.getCorreoElectronico() + "', '" + g.getClave() + "');";
        consulta.execute(cadena);
        consulta.close();
    }

    public void eliminar(int id) throws SQLException {
        Statement consulta = conexion.conectar().createStatement();
        consulta.executeUpdate("DELETE FROM usuario WHERE idUsuario = " + id);
        consulta.close();
    }

    public void modificar(Usuario g) throws SQLException {
        Statement consulta = conexion.conectar().createStatement();
        String cadena = "UPDATE usuario SET nombreUsuario = '" + g.getNombreUsuario() + "', correoElectronico = '" + g.getCorreoElectronico() + "' WHERE idUsuario = " + g.getIdUsuario();
        consulta.executeUpdate(cadena);
        consulta.close();
    }

    // Método para obtener un único usuario por su ID
    public Usuario getUnicoUsuario(int id) throws SQLException {
        Usuario usuario = null;
        try (Connection con = conexion.conectar();
             PreparedStatement ps = con.prepareStatement("SELECT * FROM Usuario WHERE idUsuario = ?")) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    usuario = new Usuario(
                            rs.getInt("idUsuario"),
                            rs.getString("nombreUsuario"),
                            rs.getString("correoElectronico"),
                            rs.getString("clave")
                    );
                }
            }
        }
        return usuario;
    }

    public Usuario getUnicoUsuario(String email) throws SQLException {
        Usuario usuario = null;
        try (Connection con = conexion.conectar();
             PreparedStatement ps = con.prepareStatement("SELECT idUsuario, nombreUsuario, correoElectronico, AES_DECRYPT(UNHEX(clave), ?) AS clave FROM Usuario WHERE correoElectronico = ?"))
            {
                ps.setString(1, "AES_KEY");
                ps.setString(2, email);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        usuario = new Usuario(
                                rs.getInt("idUsuario"),
                                rs.getString("nombreUsuario"),
                                rs.getString("correoElectronico"),
                                rs.getString("clave")
                        );
                    }
                }
            }
        return usuario;
    }
}
