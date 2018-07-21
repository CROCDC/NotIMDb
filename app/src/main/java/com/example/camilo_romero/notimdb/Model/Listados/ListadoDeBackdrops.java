package com.example.camilo_romero.notimdb.Model.Listados;

import com.example.camilo_romero.notimdb.Model.TMDb.Backdrop;

import java.io.Serializable;
import java.util.List;

public class ListadoDeBackdrops implements Serializable {
    private List<Backdrop> backdrops;

    public List<Backdrop> getBackdrops() {
        return backdrops;
    }
}
