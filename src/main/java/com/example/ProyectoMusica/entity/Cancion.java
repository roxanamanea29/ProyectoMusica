package com.example.ProyectoMusica.entity;

/**
 * @author Roxana
 * @date 07/05/2024
 */
public class Cancion {
    private int idCancion;
    private String titulo;
    private int generoId; // ID del género
    private String nombreGenero;
    private int idArtista; // ID del artista
    private String nombreArtista;

    public Cancion(String nombreGenero, int idCancion, String titulo, int generoId, int idArtista, String nombreArtista) {
        this.nombreGenero = nombreGenero;
        this.idCancion = idCancion;
        this.titulo = titulo;
        this.generoId = generoId;
        this.idArtista = idArtista;
        this.nombreArtista = nombreArtista;
    }

    public int getIdCancion() {
        return idCancion;
    }

    public void setIdCancion(int idCancion) {
        this.idCancion = idCancion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getGeneroId() {
        return generoId;
    }

    public void setGeneroId(int generoId) {
        this.generoId = generoId;
    }

    public String getNombreGenero() {
        return nombreGenero;
    }

    public void setNombreGenero(String nombreGenero) {
        this.nombreGenero = nombreGenero;
    }

    public int getIdArtista() {
        return idArtista;
    }

    public void setIdArtista(int idArtista) {
        this.idArtista = idArtista;
    }

    public String getNombreArtista() {
        return nombreArtista;
    }

    public void setNombreArtista(String nombreArtista) {
        this.nombreArtista = nombreArtista;
    }
}