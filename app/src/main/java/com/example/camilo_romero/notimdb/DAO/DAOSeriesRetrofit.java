package com.example.camilo_romero.notimdb.DAO;

import com.example.camilo_romero.notimdb.Model.Listados.ListadoDeSeries;
import com.example.camilo_romero.notimdb.Model.Listados.ListadoDeTrailers;
import com.example.camilo_romero.notimdb.Model.TMDb.Serie;
import com.example.camilo_romero.notimdb.Utils.ResultListener;
import com.example.camilo_romero.notimdb.Utils.ServiceSeries;
import com.example.camilo_romero.notimdb.Utils.TMDBHelper;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DAOSeriesRetrofit {
    private Retrofit retrofit;
    private ServiceSeries serviceSeries;

    public DAOSeriesRetrofit() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(TMDBHelper.baseUrl)
                .addConverterFactory(GsonConverterFactory.create());

        retrofit = builder.client(httpClient.build()).build();
    }

    public void pedirListaDeSeriesPopulares(String idioma, Integer pagina, final ResultListener<ListadoDeSeries> escuchadorDelControlador) {
        serviceSeries = retrofit.create(ServiceSeries.class);
        Call<ListadoDeSeries> llamada = serviceSeries.pedirListaDeSeriesPopulares(TMDBHelper.apiKeyTMB, idioma, pagina);

        llamada.enqueue(new Callback<ListadoDeSeries>() {
            @Override
            public void onResponse(Call<ListadoDeSeries> call, Response<ListadoDeSeries> response) {
                escuchadorDelControlador.finish(response.body());
            }

            @Override
            public void onFailure(Call<ListadoDeSeries> call, Throwable t) {

            }
        });
    }

    public void pedirSeriePorid(Integer idSerie, String idioma, final ResultListener<Serie> escuchadorDelControlador) {
        serviceSeries = retrofit.create(ServiceSeries.class);

        Call<Serie> llamada = serviceSeries.pedirSeriePorid(idSerie.toString(),TMDBHelper.apiKeyTMB,idioma);

        llamada.enqueue(new Callback<Serie>() {
            @Override
            public void onResponse(Call<Serie> call, Response<Serie> response) {
                escuchadorDelControlador.finish(response.body());
            }

            @Override
            public void onFailure(Call<Serie> call, Throwable t) {

            }
        });
    }

    public void pedirTrailersDeUnaSerie(Integer idSerie, String idioma, final ResultListener<ListadoDeTrailers> escuchadorDelControlador){
        serviceSeries = retrofit.create(ServiceSeries.class);

        Call<ListadoDeTrailers> llamada = serviceSeries.pedirListaDeTrailersDeUnaSerie(idSerie.toString(),TMDBHelper.apiKeyTMB,idioma);

        llamada.enqueue(new Callback<ListadoDeTrailers>() {
            @Override
            public void onResponse(Call<ListadoDeTrailers> call, Response<ListadoDeTrailers> response) {
                escuchadorDelControlador.finish(response.body());
            }

            @Override
            public void onFailure(Call<ListadoDeTrailers> call, Throwable t) {

            }
        });


    }

}
