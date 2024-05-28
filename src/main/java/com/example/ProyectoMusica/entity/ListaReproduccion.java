package com.example.ProyectoMusica.entity;

import java.util.List;

public class ListaReproduccion {
    private int idListaReproduccion;
    private String nombreListaReproduccion;
    private List<Cancion> canciones;

    public ListaReproduccion(int idListaReproduccion, String nombreListaReproduccion, List<Cancion> canciones) {
        this.idListaReproduccion = idListaReproduccion;
        this.nombreListaReproduccion = nombreListaReproduccion;
        this.canciones = canciones;
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

    public List<Cancion> getCanciones() {
        return canciones;
    }

    public void setCanciones(List<Cancion> canciones) {
        this.canciones = canciones;
    }
}

