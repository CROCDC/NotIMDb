package com.example.camilo_romero.notimdb.Model.wp_json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Embedded {
    @SerializedName("wp:featuredmedia")
    @Expose
    private List<wpfeaturedmedia> listaDeImagenes;

    public List<wpfeaturedmedia> getListaDeImagenes() {
        return listaDeImagenes;
    }
}
