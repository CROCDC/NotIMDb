package com.example.camilo_romero.notimdb.Model.TMDb;

import java.io.Serializable;

public class BelongsToCollection implements Serializable {
    private Integer id;
    private String name;
    private String poster_path;
    private String backdrop_path;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }
}
