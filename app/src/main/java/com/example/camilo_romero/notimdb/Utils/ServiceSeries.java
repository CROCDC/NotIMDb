package com.example.camilo_romero.notimdb.Utils;

import com.example.camilo_romero.notimdb.Model.Listados.ListadoDeResultadosDeBusqueda;
import com.example.camilo_romero.notimdb.Model.Listados.ListadoDeSeries;
import com.example.camilo_romero.notimdb.Model.Listados.ListadoDeTrailers;
import com.example.camilo_romero.notimdb.Model.TMDb.Serie;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ServiceSeries {
    @GET("tv/popular")
    Call<ListadoDeSeries> pedirListaDeSeriesPopulares(
            @Query("api_key") String api_key,
            @Query("language") String idioma,
            @Query("page") Integer pagina

    );

    @GET("tv/{tv_id}/videos")
    Call<ListadoDeTrailers> pedirListaDeTrailersDeUnaSerie(
            @Path("tv_id") String idSerie,
            @Query("api_key") String api_Key,
            @Query("language") String idioma


    );

    @GET("tv/{tv_id}")
    Call<Serie> pedirSeriePorid(
            @Path("tv_id") String idSerie,
            @Query("api_key") String api_key,
            @Query("language") String idioma
    );


}
