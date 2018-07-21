package com.example.camilo_romero.notimdb.Model.TMDb;

import java.io.Serializable;
import java.util.List;

public class Coleccion implements Serializable {
    private Integer id;
    private String name;
    private String overview;
    private String poster_path;
    private String backdrop_path;
    private List<Pelicula> parts;


    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getOverview() {
        return overview;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public List<Pelicula> getParts() {
        return parts;
    }
}
