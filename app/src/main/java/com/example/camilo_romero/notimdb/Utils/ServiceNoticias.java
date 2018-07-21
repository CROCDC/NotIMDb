package com.example.camilo_romero.notimdb.Utils;

import com.example.camilo_romero.notimdb.Model.wp_json.Noticia;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ServiceNoticias {
    @GET("wp/v2/posts")
    Call<List<Noticia>> pedirListaDeNoticiasPorCategoria(
            @Query("categories") int categoria,
            @Query("page") int pagina
    );

    @GET("wp/v2/posts?_embed")
    Call<List<Noticia>> pedirListaDeNoticias(
            @Query("page") int pagina,
            @Query("per_page") int tamaño
    );

    @GET("wp/v2/posts/{id_nota}")
    Call<Noticia> pedirNoticiaPorid(
            @Path("id_nota") int pagina
    );

}
