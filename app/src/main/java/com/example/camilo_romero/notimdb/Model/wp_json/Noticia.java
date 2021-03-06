package com.example.camilo_romero.notimdb.Model.wp_json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * Created by TCR on 05/06/2018.
 */

public class Noticia implements Serializable {
    private Integer id;
    private String date_gmt;//la hora en que fue publicada
    private String link;
    private Title title;//los titulos
    private Content content;//la nota
    private Excerpt excerpt;//la preview de la nota
    private Integer featured_media;
    private List<Integer> categories;//  a que categoria pertenece
    private Imagen imagen;

    @SerializedName("_links")
    @Expose
    private Links links;//imagenes

    @SerializedName("_embedded")
    @Expose
    private Embedded embedded;

    public Noticia() {
    }

    public Noticia(Integer id) {
        this.id = id;
    }



    public Noticia(Integer id, String link, Title title, Content content, Excerpt excerpt, Integer featured_media, List<Integer> categories, Imagen imagen) {
        this.id = id;
        this.link = link;
        this.title = title;
        this.content = content;
        this.excerpt = excerpt;
        this.featured_media = featured_media;
        this.categories = categories;
        this.imagen = imagen;
    }

    public String getLink() {
        return link;

    }


    public Embedded getEmbedded() {
        return embedded;
    }

    public void setEmbedded(Embedded embedded) {
        this.embedded = embedded;
    }

    public Integer getFeatured_media() {
        return featured_media;
    }

    public Integer getId() {
        return id;

    }

    public void setExcerpt(Excerpt excerpt) {
        this.excerpt = excerpt;
    }

    public Imagen getImagen() {
        return imagen;
    }

    public void setImagen(Imagen imagen) {
        this.imagen = imagen;
    }

    public String getDate_gmt() {
        return date_gmt;
    }

    public Title getTitle() {
        return title;
    }

    public Content getContent() {
        return content;
    }

    public Excerpt getExcerpt() {
        return excerpt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Noticia noticia = (Noticia) o;
        return Objects.equals(categories, noticia.categories);
    }

    @Override
    public int hashCode() {

        return Objects.hash(categories);
    }

    public List<Integer> getCategories() {
        return categories;

    }

    public Links getLinks() {
        return links;
    }
}
