package com.example.camilo_romero.notimdb.Utils;

import com.example.camilo_romero.notimdb.Model.Listados.ListadoDeResultadosDeBusqueda;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ServiceBusqueda {
    @GET("search/multi")
    Call<ListadoDeResultadosDeBusqueda> pedirResultadosDeLaBusqueda(

            @Query("api_key") String api_key,
            @Query("languague") String idioma,
            @Query("query") String busqueda,
            @Query("page") Integer pagina

    );
}
