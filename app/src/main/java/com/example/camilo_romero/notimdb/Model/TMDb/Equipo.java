package com.example.camilo_romero.notimdb.Model.TMDb;

import java.io.Serializable;

public class Equipo implements Serializable {
    private String credit_id;
    private String departament;
    private Integer gender;
    private Integer id;
    private String job;
    private String name;
    private String profile_path;

    public String getCredit_id() {
        return credit_id;
    }

    public String getDepartament() {
        return departament;
    }

    public Integer getGender() {
        return gender;
    }

    public Integer getId() {
        return id;
    }

    public String getJob() {
        return job;
    }

    public String getName() {
        return name;
    }

    public String getProfile_path() {
        return profile_path;
    }
}
