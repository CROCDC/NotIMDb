package com.example.camilo_romero.notimdb.Utils;

import com.example.camilo_romero.notimdb.Model.TMDb.Usuario;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ServiceUsuario {


    @GET("authentication/guest_session/new")
    Call<Usuario> pedirSesionDeInvitado(
            @Query("api_key") String api_key
    );
}
