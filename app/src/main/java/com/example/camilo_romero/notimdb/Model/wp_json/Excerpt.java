package com.example.camilo_romero.notimdb.Model.wp_json;

import java.io.Serializable;

/**
 * Created by TCR on 05/06/2018.
 */

public class Excerpt implements Serializable {
    private String rendered;//preview

    public String getRendered() {
        return rendered;
    }

    public void setRendered(String rendered) {
        this.rendered = rendered;
    }
}
