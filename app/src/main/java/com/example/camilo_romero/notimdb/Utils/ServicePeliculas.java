package com.example.camilo_romero.notimdb.Utils;

import com.example.camilo_romero.notimdb.Model.TMDb.Coleccion;
import com.example.camilo_romero.notimdb.Model.TMDb.Creditos;
import com.example.camilo_romero.notimdb.Model.Listados.ListadoDeBackdrops;
import com.example.camilo_romero.notimdb.Model.Listados.ListadoDePeliculas;
import com.example.camilo_romero.notimdb.Model.Listados.ListadoDeTrailers;
import com.example.camilo_romero.notimdb.Model.TMDb.Pelicula;
import com.example.camilo_romero.notimdb.Model.TMDb.Respuesta;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ServicePeliculas {

    @GET("movie/popular")
    Call<ListadoDePeliculas> pedirListaDePeliculasPopulares(
            @Query("api_key") String api_key,
            @Query("language") String idioma,
            @Query("page") Integer pagina
    );

    @GET("movie/{movie_id}")
    Call<Pelicula> pedirPeliculaPorid(
            @Path("movie_id") String idPelicula,
            @Query("api_key") String api_key,
            @Query("language") String idioma
    );

   @GET("movie/{movie_id}/videos")
    Call<ListadoDeTrailers> pedirListaDeTrailersDeUnaPelicula(
           @Path("movie_id") String idPelicula,
           @Query("api_key") String api_key,
           @Query("language") String idioma
   );

   @GET("movie/{movie_id}/credits")
    Call<Creditos> pedirCreditosDeUnaPelicula(
            @Path("movie_id") String idPelicula,
            @Query("api_key") String api_key
   );

   @GET("movie/{movie_id}/images")
    Call<ListadoDeBackdrops> pedirListaDeImagenesDeUnaPelicula(
            @Path("movie_id") String idPelicula,
            @Query("api_key") String api_key

   );

   @GET("movie/{movie_id}/similar")
    Call<ListadoDePeliculas> pedirListaDePeliculasSimilares(
            @Path("movie_id") String idPelicula,
            @Query("api_key") String api_key,
            @Query("language") String idioma
   );

   @GET("collection/{id_collection}?")
    Call<Coleccion> pedirColeccionDeUnaPelicua(
            @Path("id_collection") String idCollection,
            @Query("api_key") String api_key,
            @Query("language") String idioma
   );
    @GET("genre/{id_genero}/movies")
    Call<ListadoDePeliculas> pedirListaDePeliculasPorGenero (
            @Path("id_genero") String id_genero,
            @Query("api_key") String api_key,
            @Query("language") String idioma,
            @Query("page") Integer page
    );


    @POST("movie/{movie_id}/rating")
    Call<ResponseBody>  enviarPuntuacioDeLaPelicula(
            @Path("movie_id") String idPelicula,
            @Query("api_key") String api_key,
            @Query("guest_session_id") String idSesionDeInvitado,
            @Header("Content-Type") String contentType,
            @Body() String json

    );

}
