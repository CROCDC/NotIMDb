package com.example.camilo_romero.notimdb.Model.TMDb;

import java.io.Serializable;

public class Contenido implements Serializable {

    private String titulo;
    private Integer id;
    private String queEs;
    private String imagenBackground;
    private String imagenPoster;
    private String descripcion;
    private Double puntaje;


    public Contenido(String titulo, Integer id, String queEs, String imagenBackground, String imagenPoster, String descripcion, Double puntaje) {
        this.titulo = titulo;
        this.id = id;
        this.queEs = queEs;
        this.imagenBackground = imagenBackground;
        this.imagenPoster = imagenPoster;
        this.descripcion = descripcion;
        this.puntaje = puntaje;
    }

    public Integer getId() {
        return id;
    }

    public String getQueEs() {
        return queEs;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getImagenBackground() {
        return imagenBackground;
    }

    public String getImagenPoster() {
        return imagenPoster;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Double getPuntaje() {
        return puntaje;
    }
}
