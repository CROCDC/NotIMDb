package com.example.camilo_romero.notimdb.DAO;

import com.example.camilo_romero.notimdb.Model.TMDb.Usuario;
import com.example.camilo_romero.notimdb.Utils.ResultListener;
import com.example.camilo_romero.notimdb.Utils.ServiceUsuario;
import com.example.camilo_romero.notimdb.Utils.TMDBHelper;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DAOUsuarioRetrofit {
    private Retrofit retrofit;
    private ServiceUsuario serviceUsuario;

    public DAOUsuarioRetrofit() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(TMDBHelper.baseUrl)
                .addConverterFactory(GsonConverterFactory.create());

        retrofit = builder.client(httpClient.build()).build();
    }

    public void pedirUsuarioInvitado(final ResultListener<Usuario> escuchadorDelControler){
        serviceUsuario = retrofit.create(ServiceUsuario.class);

        Call<Usuario> llamada = serviceUsuario.pedirSesionDeInvitado(TMDBHelper.apiKeyTMB);

        llamada.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                escuchadorDelControler.finish(response.body());
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {

            }
        });
    }
}
