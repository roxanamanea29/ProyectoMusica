package com.example.ProyectoMusica.entity;

public class CancionListaReproduccion {
    private int id;
    private int cancion_id;
    private int lista_reproduccion;

    public CancionListaReproduccion(int id, int cancion_id, int lista_reproduccion) {
        this.id = id;
        this.cancion_id = cancion_id;
        this.lista_reproduccion = lista_reproduccion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCancion_id() {
        return cancion_id;
    }

    public void setCancion_id(int cancion_id) {
        this.cancion_id = cancion_id;
    }

    public int getLista_reproduccion() {
        return lista_reproduccion;
    }

    public void setLista_reproduccion(int lista_reproduccion) {
        this.lista_reproduccion = lista_reproduccion;
    }
}