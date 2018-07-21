package com.example.camilo_romero.notimdb.Model.wp_json;

import android.graphics.Bitmap;

public class ImageBitmap {
    private String url;
    private Bitmap bitmap;

    public void setUrl(String url) {
        this.url = url;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public String getUrl() {
        return url;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }
}
