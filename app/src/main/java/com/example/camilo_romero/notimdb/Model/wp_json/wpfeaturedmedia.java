package com.example.camilo_romero.notimdb.Model.wp_json;

import java.io.Serializable;

/**
 * Created by TCR on 05/06/2018.
 */

public class wpfeaturedmedia implements Serializable {
    private String href;//pedido de imagenes
    private Integer id;
    private String link;
    private Media_details media_details;


    public Media_details getMedia_details() {
        return media_details;
    }

    public String getHref() {
        return href;
    }

    public Integer getId() {
        return id;
    }

    public String getLink() {
        return link;
    }
}
