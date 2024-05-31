package com.example.ProyectoMusica.entity;

public class Admin {
    private int idAdmin;
    private String nombreAdmin;
    private String correoElectronico;
    private String clave;

    public Admin(int idAdmin, String nombreAdmin, String correoElectronico, String clave) {
        this.idAdmin = idAdmin;
        this.nombreAdmin = nombreAdmin;
        this.correoElectronico = correoElectronico;
        this.clave = clave;
    }

    public Admin(int idAdmin, String nombreAdmin) {
    }

    public int getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(int idAdmin) {
        this.idAdmin = idAdmin;
    }

    public String getNombreAdmin() {
        return nombreAdmin;
    }

    public void setNombreAdmin(String nombreAdmin) {
        this.nombreAdmin = nombreAdmin;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    @Override
    public String toString() {

        return String.format("%-4d %-20s %-20s %-15s",
                idAdmin,
                nombreAdmin,
                correoElectronico,
                clave);
    }
}
