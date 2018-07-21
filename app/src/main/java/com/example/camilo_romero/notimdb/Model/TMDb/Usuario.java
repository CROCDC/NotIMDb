package com.example.camilo_romero.notimdb.Model.TMDb;

import java.io.Serializable;

public class Usuario implements Serializable {
    private Boolean success;
    private String guest_session_id;
    private String expires_at;

    public Boolean getSuccess() {
        return success;
    }

    public String getGuest_session_id() {
        return guest_session_id;
    }

    public String getExpires_at() {
        return expires_at;
    }
}
