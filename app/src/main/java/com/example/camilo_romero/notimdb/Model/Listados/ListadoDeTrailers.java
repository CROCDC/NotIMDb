package com.example.camilo_romero.notimdb.Model.Listados;

import com.example.camilo_romero.notimdb.Model.TMDb.Trailer;

import java.io.Serializable;
import java.util.List;

public class ListadoDeTrailers implements Serializable {
    private List<Trailer> results;


    public ListadoDeTrailers(List<Trailer> listaDeTrailers){
        this.results = listaDeTrailers;
    }
    public List<Trailer> getResults() {
        return results;
    }
}
