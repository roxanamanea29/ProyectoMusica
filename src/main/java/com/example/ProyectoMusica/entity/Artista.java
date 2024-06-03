package com.example.ProyectoMusica.entity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Artista {
    private int idArtista;
    private String nombreArtista;

    public Artista() {
    }

    public Artista(int idArtista, String nombreArtista) {
        this.idArtista = idArtista;
        this.nombreArtista = nombreArtista;
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