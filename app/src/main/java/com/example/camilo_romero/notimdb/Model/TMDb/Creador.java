package com.example.camilo_romero.notimdb.Model.TMDb;

import java.io.Serializable;

public class Creador implements Serializable {
    private Integer id;
    private String name;
    private Integer gender;
    private String profile_path;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getGender() {
        return gender;
    }

    public String getProfile_path() {
        return profile_path;
    }
}
