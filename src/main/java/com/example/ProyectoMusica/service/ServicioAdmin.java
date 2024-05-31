package com.example.ProyectoMusica.service;

import com.example.ProyectoMusica.database.Conexion;
import com.example.ProyectoMusica.entity.Admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServicioAdmin {
    private final Conexion conexion = new Conexion();

    public List<Admin> listarTodosAdmins() throws SQLException {
        List<Admin> admins = new ArrayList<>();
        Connection con = conexion.conectar();
        PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM Admin");
        try (ResultSet rs = preparedStatement.executeQuery()) {
            while (rs.next()) {
                admins.add(new Admin(
                        rs.getInt("idAdmin"),
                        rs.getString("nombreAdmin"),
                        rs.getString("correoElectronico"),
                        rs.getString("clave")
                ));
            }
        }
        return admins;
    }

    public void registrarAdmin(String nombre, String correoElectronico, String clave) throws SQLException {
        try (Connection con = conexion.conectar();
             PreparedStatement ps = con.prepareStatement("INSERT INTO Admin (nombreAdmin, correoElectronico, clave) VALUES (?, ?, ?)")) {
            ps.setString(1, nombre);
            ps.setString(2, correoElectronico);
            ps.setString(3, clave);
            ps.executeUpdate();
        }
    }

    public void modificarAdmin(int idAdmin, String nombre, String correoElectronico, String clave) throws SQLException {
        try (Connection con = conexion.conectar();
             PreparedStatement ps = con.prepareStatement("UPDATE Admin SET nombreAdmin = ?, correoElectronico = ?, clave = ? WHERE idAdmin = ?")) {
            ps.setString(1, nombre);
            ps.setString(2, correoElectronico);
            ps.setString(3, clave);
            ps.setInt(4, idAdmin);
            ps.executeUpdate();
        }
    }

    public void eliminarAdmin(int idAdmin) throws SQLException {
        try (Connection con = conexion.conectar();
             PreparedStatement ps = con.prepareStatement("DELETE FROM Admin WHERE idAdmin = ?")) {
            ps.setInt(1, idAdmin);
            ps.executeUpdate();
        }
    }

    public Admin buscarAdmin(int idAdmin) throws SQLException {
        String sql = "SELECT * FROM Admin WHERE idAdmin = ?";
        try (Connection con = conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idAdmin);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Admin(
                            rs.getInt("idAdmin"),
                            rs.getString("nombreAdmin"),
                            rs.getString("correoElectronico"),
                            rs.getString("clave")
                    );
                }
            }
        }
        return null;
    }
}
