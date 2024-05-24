package com.example.ProyectoMusica.entity;

public class Artista {
    private int idArtista;
    private String nombreArtista;

    public Artista(int idArtista, String nombreArtista) {
        this.idArtista = idArtista;
        this.nombreArtista = nombreArtista;
    }

    public int getIdArtista() {
        return idArtista;
    }

    public void setIdArtista(int idArt) {
        this.idArtista = idArtista;
    }

    public String getNombreArtista() {
        return nombreArtista;
    }

    public void setNombreArtista(String Artnombre) {
        this.nombreArtista = nombreArtista;
    }

}
