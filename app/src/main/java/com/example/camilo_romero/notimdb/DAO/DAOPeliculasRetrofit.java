package com.example.camilo_romero.notimdb.DAO;

import com.example.camilo_romero.notimdb.Model.TMDb.Coleccion;
import com.example.camilo_romero.notimdb.Model.TMDb.Creditos;
import com.example.camilo_romero.notimdb.Model.Listados.ListadoDeBackdrops;
import com.example.camilo_romero.notimdb.Model.Listados.ListadoDePeliculas;
import com.example.camilo_romero.notimdb.Model.Listados.ListadoDeTrailers;
import com.example.camilo_romero.notimdb.Model.TMDb.Pelicula;
import com.example.camilo_romero.notimdb.Utils.ResultListener;
import com.example.camilo_romero.notimdb.Utils.ServicePeliculas;
import com.example.camilo_romero.notimdb.Utils.TMDBHelper;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DAOPeliculasRetrofit {
    private Retrofit retrofit;
    private ServicePeliculas servicePeliculas;

    public DAOPeliculasRetrofit() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(TMDBHelper.baseUrl)
                .addConverterFactory(GsonConverterFactory.create());

        retrofit = builder.client(httpClient.build()).build();
    }

    public void pedirListaDePeliculasPopulares(String idioma, Integer pagina, final ResultListener<ListadoDePeliculas> escuchadorDelControlador) {
        servicePeliculas = retrofit.create(ServicePeliculas.class);

        Call<ListadoDePeliculas> llamada = servicePeliculas.pedirListaDePeliculasPopulares(TMDBHelper.apiKeyTMB, idioma, pagina);

        llamada.enqueue(new Callback<ListadoDePeliculas>() {
            @Override
            public void onResponse(Call<ListadoDePeliculas> call, Response<ListadoDePeliculas> response) {
                escuchadorDelControlador.finish(response.body());
            }

            @Override
            public void onFailure(Call<ListadoDePeliculas> call, Throwable t) {

            }
        });
    }

    public void pedirPeliculaPorid(String idioma, Integer id, final ResultListener<Pelicula> escuchadorDelControlador) {
        servicePeliculas = retrofit.create(ServicePeliculas.class);

        Call<Pelicula> llamada = servicePeliculas.pedirPeliculaPorid(id.toString(), TMDBHelper.apiKeyTMB, idioma);

        llamada.enqueue(new Callback<Pelicula>() {
            @Override
            public void onResponse(Call<Pelicula> call, Response<Pelicula> response) {
                escuchadorDelControlador.finish(response.body());
            }

            @Override
            public void onFailure(Call<Pelicula> call, Throwable t) {

            }
        });
    }

    public void pedirListaDeTrailersDeUnaPelicula(String idioma, Integer id, final ResultListener<ListadoDeTrailers> escuchadorDelControlador) {
        servicePeliculas = retrofit.create(ServicePeliculas.class);

        Call<ListadoDeTrailers> llamada = servicePeliculas.pedirListaDeTrailersDeUnaPelicula(id.toString(), TMDBHelper.apiKeyTMB, idioma);

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

    public void pedirCreditosDeUnaPelicula(Integer id, final ResultListener<Creditos> escuchadorDelControlador) {
        servicePeliculas = retrofit.create(ServicePeliculas.class);

        Call<Creditos> llamada = servicePeliculas.pedirCreditosDeUnaPelicula(id.toString(), TMDBHelper.apiKeyTMB);

        llamada.enqueue(new Callback<Creditos>() {
            @Override
            public void onResponse(Call<Creditos> call, Response<Creditos> response) {
                escuchadorDelControlador.finish(response.body());
            }

            @Override
            public void onFailure(Call<Creditos> call, Throwable t) {

            }
        });
    }

    public void pedirListaDeImagenesDeUnaPelicula( Integer id, final ResultListener<ListadoDeBackdrops> escuchadorDelControlador) {
        servicePeliculas = retrofit.create(ServicePeliculas.class);

        Call<ListadoDeBackdrops> llamda = servicePeliculas.pedirListaDeImagenesDeUnaPelicula(id.toString(), TMDBHelper.apiKeyTMB);

        llamda.enqueue(new Callback<ListadoDeBackdrops>() {
            @Override
            public void onResponse(Call<ListadoDeBackdrops> call, Response<ListadoDeBackdrops> response) {
                escuchadorDelControlador.finish(response.body());
            }

            @Override
            public void onFailure(Call<ListadoDeBackdrops> call, Throwable t) {

            }
        });
    }

    public void pedirListaDePeliculasSimiliares(String idioma,Integer id,final ResultListener<ListadoDePeliculas> escuchadorDelControlador){
        servicePeliculas = retrofit.create(ServicePeliculas.class);

        Call<ListadoDePeliculas> llamada = servicePeliculas.pedirListaDePeliculasSimilares(id.toString(),TMDBHelper.apiKeyTMB,idioma);

        llamada.enqueue(new Callback<ListadoDePeliculas>() {
            @Override
            public void onResponse(Call<ListadoDePeliculas> call, Response<ListadoDePeliculas> response) {
                escuchadorDelControlador.finish(response.body());
            }

            @Override
            public void onFailure(Call<ListadoDePeliculas> call, Throwable t) {

            }
        });
    }

    public void pedirColeccionDeUnaPelicula(String idioma, Integer id, final ResultListener<Coleccion> escuchadorDelControlador){
        servicePeliculas = retrofit.create(ServicePeliculas.class);

        Call<Coleccion> llamada = servicePeliculas.pedirColeccionDeUnaPelicua(id.toString(),TMDBHelper.apiKeyTMB,idioma);

        llamada.enqueue(new Callback<Coleccion>() {
            @Override
            public void onResponse(Call<Coleccion> call, Response<Coleccion> response) {
                escuchadorDelControlador.finish(response.body());
            }

            @Override
            public void onFailure(Call<Coleccion> call, Throwable t) {

            }
        });

    }
    public void pedirListaDePeliculasPorGenero(Integer idGenero,String idioma,Integer pagina, final ResultListener<ListadoDePeliculas> escuchadorDelControlador){
        servicePeliculas = retrofit.create(ServicePeliculas.class);

        Call<ListadoDePeliculas> llamada = servicePeliculas.pedirListaDePeliculasPorGenero(idGenero.toString(),TMDBHelper.apiKeyTMB,idioma,pagina);

        llamada.enqueue(new Callback<ListadoDePeliculas>() {
            @Override
            public void onResponse(Call<ListadoDePeliculas> call, Response<ListadoDePeliculas> response) {
                escuchadorDelControlador.finish(response.body());
            }

            @Override
            public void onFailure(Call<ListadoDePeliculas> call, Throwable t) {

            }
        });
    }

    public void enviarPuntuacionDeLaPelicula(String json, Integer idPelicula, String idSesionInvitado, final ResultListener<ResponseBody> escuchadorDelControlador){
        servicePeliculas = retrofit.create(ServicePeliculas.class);

        Call<ResponseBody> llamada = servicePeliculas.enviarPuntuacioDeLaPelicula(idPelicula.toString(),TMDBHelper.apiKeyTMB,idSesionInvitado,TMDBHelper.ContentType,json);


        llamada.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
             escuchadorDelControlador.finish(response.body());
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });

    }

}

