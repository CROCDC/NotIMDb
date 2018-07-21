package com.example.camilo_romero.notimdb.Model.TMDb;

import java.io.Serializable;
import java.util.List;

public class Pelicula implements Serializable {
    private Integer vote_count;
    private Integer id;
    private Boolean video;
    private Double vote_average;
    private String title;
    private Double popularity;
    private String poster_path;
    private String original_language;
    private String original_title;
    private List<Integer> genre_ids;
    private List<Gender> genres;
    private String backdrop_path;
    private Boolean adult;
    private String overview;
    private String tagline;
    private String release_date;
    private Integer budget;
    private BelongsToCollection belongs_to_collection;
    private String homepage;
    private String imdb_id;
    private List<ProductionCompanies> production_companies;
    private List<ProductionCountries> production_countries;
    private Integer revenue;
    private Integer runtime;
    private String status;


    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public List<Gender> getGenres() {
        return genres;
    }

    public Integer getBudget() {
        return budget;
    }

    public BelongsToCollection getBelongs_to_collection() {
        return belongs_to_collection;
    }

    public String getHomepage() {
        return homepage;
    }

    public String getImdb_id() {
        return imdb_id;
    }

    public List<ProductionCompanies> getProduction_companies() {
        return production_companies;
    }

    public List<ProductionCountries> getProduction_countries() {
        return production_countries;
    }

    public Integer getRevenue() {
        return revenue;
    }

    public Integer getRuntime() {
        return runtime;
    }

    public String getStatus() {
        return status;
    }

    public String getTagline() {
        return tagline;
    }


    public Integer getVote_count() {
        return vote_count;
    }

    public Integer getId() {
        return id;
    }

    public Boolean getVideo() {
        return video;
    }

    public Double getVote_average() {
        return vote_average;
    }

    public String getTitle() {
        return title;
    }

    public Double getPopularity() {
        return popularity;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public List<Integer> getGenre_ids() {
        return genre_ids;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public Boolean getAdult() {
        return adult;
    }

    public String getOverview() {
        return overview;
    }

    public String getRelease_date() {
        return release_date;
    }
}
