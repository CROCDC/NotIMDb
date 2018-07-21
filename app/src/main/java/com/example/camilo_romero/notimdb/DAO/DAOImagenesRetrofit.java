package com.example.camilo_romero.notimdb.DAO;

import com.example.camilo_romero.notimdb.Model.wp_json.Imagen;
import com.example.camilo_romero.notimdb.Utils.Helper;
import com.example.camilo_romero.notimdb.Utils.ResultListener;
import com.example.camilo_romero.notimdb.Utils.ServiceImagenes;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DAOImagenesRetrofit {
    private Retrofit retrofit;
    private ServiceImagenes serviceImagenes;

    public DAOImagenesRetrofit(String urlBase){
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(urlBase)
                .addConverterFactory(GsonConverterFactory.create());

        retrofit = builder.client(httpClient.build()).build();
    }

    public void traerImagenesDeLaNoticia(Integer idNoticia, final ResultListener<Imagen> escuchadorDelControler){
        serviceImagenes = retrofit.create(ServiceImagenes.class);

        Call<Imagen> llamada = serviceImagenes.pedirImagenDeLaNoticia(idNoticia);

        llamada.enqueue(new Callback<Imagen>() {
            @Override
            public void onResponse(Call<Imagen> call, Response<Imagen> response) {
                escuchadorDelControler.finish(response.body());
            }

            @Override
            public void onFailure(Call<Imagen> call, Throwable t) {

            }
        });
    }
}
