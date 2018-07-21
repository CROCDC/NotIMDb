package com.example.camilo_romero.notimdb.Utils;

import com.example.camilo_romero.notimdb.Model.TMDb.Persona;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ServicePersonas {


    @GET("person/{person_id}")
    Call<Persona> pedirPersonaPorid(
            @Path("person_id") Integer idSerie,
            @Query("api_key") String api_key,
            @Query("language") String idioma
    );
}
