package com.example.ProyectoMusica.entity;

/**
 * @author Roxana
 * @date 07/05/2024
 */
public class Cancion {
 private int id;
 private String titulo;

    public Cancion(int id, String titulo) {
        this.id = id;
        this.titulo = titulo;
    }

    public Cancion(String titulo) {
        this.titulo = titulo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}
