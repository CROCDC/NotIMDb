package com.example.camilo_romero.notimdb.Model.TMDb;

import java.io.Serializable;
import java.util.List;

public class Serie implements Serializable {

    private String backdrop_path;
    private List<Creador> created_by;
    private List<Integer> episode_run_time;
    private String first_air_date;
    private List<Gender> genres;
    private String homepage;
    private Integer id;
    private Boolean in_production;
    private List<String> languages;
    private String last_air_date;
    private String name;
    private Integer number_of_episodes;
    private Integer number_of_seasons;
    private List<String> origin_country;
    private String original_language;
    private String original_name;
    private String overview;
    private Double popularity;
    private String poster_path;
    private List<Temporada> seasons;
    private String status;
    private String type;
    private Double vote_average;
    private Integer vote_count;




    public List<Gender> getGenres() {
        return genres;
    }

    public String getHomepage() {
        return homepage;
    }

    public Integer getId() {
        return id;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public List<Creador> getCreated_by() {
        return created_by;
    }

    public List<Integer> getEpisode_run_time() {
        return episode_run_time;
    }

    public String getFirst_air_date() {
        return first_air_date;
    }

    public Boolean getIn_production() {
        return in_production;
    }

    public String getLast_air_date() {
        return last_air_date;
    }

    public Integer getNumber_of_episodes() {
        return number_of_episodes;
    }

    public Integer getNumber_of_seasons() {
        return number_of_seasons;
    }

    public List<String> getOrigin_country() {
        return origin_country;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public String getOriginal_name() {
        return original_name;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public Double getVote_average() {
        return vote_average;
    }

    public Integer getVote_count() {
        return vote_count;
    }

    public List<String> getLanguages() {
        return languages;
    }



    public String getName() {
        return name;
    }

    public String getOverview() {
        return overview;
    }

    public Double getPopularity() {
        return popularity;
    }


    public List<Temporada> getSeasons() {
        return seasons;
    }

    public String getStatus() {
        return status;
    }

    public String getType() {
        return type;
    }


}
