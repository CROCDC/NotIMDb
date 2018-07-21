package com.example.camilo_romero.notimdb.Model.Listados;

import com.example.camilo_romero.notimdb.Model.TMDb.Pelicula;

import java.io.Serializable;
import java.util.List;

public class ListadoDePeliculas implements Serializable {
    private List<Pelicula> results;

    public List<Pelicula> getResults() {
        return results;
    }
}
