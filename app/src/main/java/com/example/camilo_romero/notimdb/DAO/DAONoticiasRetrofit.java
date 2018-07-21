package com.example.camilo_romero.notimdb.DAO;

import com.example.camilo_romero.notimdb.Model.wp_json.Noticia;
import com.example.camilo_romero.notimdb.Utils.Helper;
import com.example.camilo_romero.notimdb.Utils.ResultListener;
import com.example.camilo_romero.notimdb.Utils.ServiceNoticias;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DAONoticiasRetrofit {
    private Retrofit retrofit;
    private ServiceNoticias serviceNoticias;

    public DAONoticiasRetrofit(String urlBase) {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(urlBase)
                .addConverterFactory(GsonConverterFactory.create());

        retrofit = builder.client(httpClient.build()).build();
    }

    public void pedirListaDeNoticias(Integer cantidadDeNoticias,Integer pagina, final ResultListener<List<Noticia>> escuchadorDelControler){
        serviceNoticias = retrofit.create(ServiceNoticias.class);

        Call<List<Noticia>> llamada = serviceNoticias.pedirListaDeNoticias(pagina,cantidadDeNoticias);

        llamada.enqueue(new Callback<List<Noticia>>() {
            @Override
            public void onResponse(Call<List<Noticia>> call, Response<List<Noticia>> response) {
                escuchadorDelControler.finish(response.body());
            }

            @Override
            public void onFailure(Call<List<Noticia>> call, Throwable t) {

            }
        });
    }

    public void pedirLaUltimaNoticia(final ResultListener<List<Noticia>> escuchadorDelControler){
        serviceNoticias = retrofit.create(ServiceNoticias.class);

        Call<List<Noticia>> llamada = serviceNoticias.pedirListaDeNoticias(1,1);

        llamada.enqueue(new Callback<List<Noticia>>() {
            @Override
            public void onResponse(Call<List<Noticia>> call, Response<List<Noticia>> response) {
                escuchadorDelControler.finish(response.body());
            }

            @Override
            public void onFailure(Call<List<Noticia>> call, Throwable t) {

            }
        });
    }


}
