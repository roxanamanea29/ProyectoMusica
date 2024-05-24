package com.example.ProyectoMusica.entity;

import java.util.List;

public class ListaReproduccion {
    private int id_lista;
    private String nombreLista;

    private List<Cancion> canciones;

    public ListaReproduccion(int id, String nombreLista, List<Cancion> canciones) {
        this.id_lista = id;
        this.nombreLista = nombreLista;
        this.canciones = canciones;
    }

    public int getId() {
        return id_lista;
    }

    public void setId(int id) {
        this.id_lista = id;
    }

    public String getNombre() {
        return nombreLista;
    }

    public void setNombre(String nombre) {
        this.nombreLista = nombre;
    }

    public List<Cancion> getCanciones() {
        return canciones;
    }

    public void setCanciones(List<Cancion> canciones) {
        this.canciones = canciones;
    }
}
