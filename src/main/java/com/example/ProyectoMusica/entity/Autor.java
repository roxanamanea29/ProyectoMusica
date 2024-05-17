package com.example.ProyectoMusica.entity;

public class Autor {
    private int id;
    private int cancion_id;
    private String nombre;
    private String titulo;

    public Autor(int id, int cancion_id, String nombre, String titulo) {
        this.id = id;
        this.cancion_id = cancion_id;
        this.nombre = nombre;
        this.titulo = titulo;
    }

    public Autor(int cancion_id, String titulo) {
        this.cancion_id = cancion_id;
        this.titulo = titulo;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}
