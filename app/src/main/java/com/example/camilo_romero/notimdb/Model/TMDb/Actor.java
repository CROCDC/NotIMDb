package com.example.camilo_romero.notimdb.Model.TMDb;

import java.io.Serializable;

public class Actor implements Serializable {
    private Integer cast_id;
    private String character;
    private String credit_id;
    private Integer gender;
    private Integer id;
    private String name;
    private Integer order;
    private String profile_path;

    public Integer getCast_id() {
        return cast_id;
    }

    public String getCharacter() {
        return character;
    }

    public String getCredit_id() {
        return credit_id;
    }

    public Integer getGender() {
        return gender;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getOrder() {
        return order;
    }

    public String getProfile_path() {
        return profile_path;
    }
}
