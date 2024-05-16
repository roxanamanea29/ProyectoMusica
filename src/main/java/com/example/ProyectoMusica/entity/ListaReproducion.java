package com.example.ProyectoMusica.entity;

import java.util.List;

public class ListaReproducion {
    private int id_lista;
    private String nombre;
    private List<Cancion> canciones;

    public ListaReproducion(int id, String nombre, List<Cancion> canciones) {
        this.id_lista = id;
        this.nombre = nombre;
        this.canciones = canciones;
    }

    public int getId() {
        return id_lista;
    }

    public void setId(int id) {
        this.id_lista = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Cancion> getCanciones() {
        return canciones;
    }

    public void setCanciones(List<Cancion> canciones) {
        this.canciones = canciones;
    }
}
