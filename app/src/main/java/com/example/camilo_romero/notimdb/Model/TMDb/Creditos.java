package com.example.camilo_romero.notimdb.Model.TMDb;

import java.util.List;

public class Creditos {
    private Integer id;
    private List<Actor> cast;
    private List<Equipo> crew;

    public Integer getId() {
        return id;
    }

    public List<Actor> getCast() {
        return cast;
    }

    public List<Equipo> getCrew() {
        return crew;
    }

    public void setCrew(List<Equipo> crew) {
        this.crew = crew;
    }
}
