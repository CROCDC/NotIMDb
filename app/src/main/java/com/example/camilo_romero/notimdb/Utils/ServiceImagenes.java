package com.example.camilo_romero.notimdb.Utils;

import com.example.camilo_romero.notimdb.Model.wp_json.Imagen;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ServiceImagenes {
    @GET("wp/v2/media/{idNota}")
    Call<Imagen> pedirImagenDeLaNoticia(
            @Path("idNota") int idNoticia
    );
}
