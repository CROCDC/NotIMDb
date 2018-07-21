package com.example.camilo_romero.notimdb.DAO;

import com.example.camilo_romero.notimdb.Model.Listados.ListadoDeResultadosDeBusqueda;
import com.example.camilo_romero.notimdb.Utils.ResultListener;
import com.example.camilo_romero.notimdb.Utils.ServiceBusqueda;
import com.example.camilo_romero.notimdb.Utils.TMDBHelper;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DAOBusquedasRetrofit {
    private Retrofit retrofit;
    private ServiceBusqueda serviceBusqueda;

    public DAOBusquedasRetrofit(){
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(TMDBHelper.baseUrl)
                .addConverterFactory(GsonConverterFactory.create());

        retrofit = builder.client(httpClient.build()).build();
    }

    public void pedirResultadosDeLaBusqueda(String idioma, String busqueda, Integer pagina, final ResultListener<ListadoDeResultadosDeBusqueda> escuchadorDelControler){
        serviceBusqueda = retrofit.create(ServiceBusqueda.class);

        Call<ListadoDeResultadosDeBusqueda> llamada = serviceBusqueda.pedirResultadosDeLaBusqueda(TMDBHelper.apiKeyTMB,idioma,busqueda,pagina);

        llamada.enqueue(new Callback<ListadoDeResultadosDeBusqueda>() {
            @Override
            public void onResponse(Call<ListadoDeResultadosDeBusqueda> call, Response<ListadoDeResultadosDeBusqueda> response) {
                escuchadorDelControler.finish(response.body());
            }

            @Override
            public void onFailure(Call<ListadoDeResultadosDeBusqueda> call, Throwable t) {

            }
        });
    }
}
