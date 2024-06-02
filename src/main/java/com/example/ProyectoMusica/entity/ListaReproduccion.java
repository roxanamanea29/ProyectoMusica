package com.example.ProyectoMusica.entity;

import java.util.List;

public class ListaReproduccion {
    private int idListaReproduccion;
    private String nombreListaReproduccion;
    private int usuarioId;
    private List<Cancion> canciones;


    public ListaReproduccion(int idListaReproduccion, String nombreListaReproduccion, int usuarioId, List<Cancion> canciones) {
        this.idListaReproduccion = idListaReproduccion;
        this.nombreListaReproduccion = nombreListaReproduccion;
        this.usuarioId = usuarioId;
        this.canciones = canciones;
    }
    public ListaReproduccion(){

    }

    public int getIdListaReproduccion() {
        return idListaReproduccion;
    }

    public void setIdListaReproduccion(int idListaReproduccion) {
        this.idListaReproduccion = idListaReproduccion;
    }

    public String getNombreListaReproduccion() {
        return nombreListaReproduccion;
    }

    public void setNombreListaReproduccion(String nombreListaReproduccion) {
        this.nombreListaReproduccion = nombreListaReproduccion;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public List<Cancion> getCanciones() {
        return canciones;
    }

    public void setCanciones(List<Cancion> canciones) {
        this.canciones = canciones;
    }
}
