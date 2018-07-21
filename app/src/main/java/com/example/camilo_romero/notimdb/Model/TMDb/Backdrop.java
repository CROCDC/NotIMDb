package com.example.camilo_romero.notimdb.Model.TMDb;

import java.io.Serializable;

public class Backdrop implements Serializable {
    private Double aspect_ratio;
    private String file_path;
    private Integer height;
    private String iso_639_1;
    private Double vote_average;
    private Double vote_count;

    public Double getAspect_ratio() {
        return aspect_ratio;
    }

    public String getFile_path() {
        return file_path;
    }

    public Integer getHeight() {
        return height;
    }

    public String getIso_639_1() {
        return iso_639_1;
    }

    public Double getVote_average() {
        return vote_average;
    }

    public Double getVote_count() {
        return vote_count;
    }

    public Integer getWidth() {
        return width;
    }

    private Integer width;
}
