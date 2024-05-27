package com.example.ProyectoMusica.entity;



/**
 * @author Roxana
 * @date 07/05/2024
 */
public class Cancion {

    private int idCancion;
    private String titulo;
    private int idArtista;
    private String nombreArtista;
    private int idGenero;
    private String nombreGenero;


    public Cancion() {
        this.idCancion = idCancion;
        this.titulo = titulo;
        this.idArtista = idArtista;
        this.nombreArtista = nombreArtista;
        this.idGenero = idGenero;
        this.nombreGenero = nombreGenero;
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

    public int getIdGenero() {
        return idGenero;
    }

    public void setIdGenero(int idGenero) {
        this.idGenero = idGenero;
    }

    public String getNombreGenero() {
        return nombreGenero;
    }

    public void setNombreGenero(String nombreGenero) {
        this.nombreGenero = nombreGenero;
    }
}