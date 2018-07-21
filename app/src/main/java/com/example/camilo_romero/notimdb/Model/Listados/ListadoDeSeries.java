package com.example.camilo_romero.notimdb.Model.Listados;

import com.example.camilo_romero.notimdb.Model.TMDb.Serie;

import java.io.Serializable;
import java.util.List;

public class ListadoDeSeries implements Serializable {
    private List<Serie> results;

    public List<Serie> getResults() {
        return results;
    }
}
