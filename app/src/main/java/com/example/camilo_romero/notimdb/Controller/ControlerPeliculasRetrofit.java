package com.example.camilo_romero.notimdb.Controller;

import com.example.camilo_romero.notimdb.DAO.DAOPeliculasRetrofit;
import com.example.camilo_romero.notimdb.Model.TMDb.Coleccion;
import com.example.camilo_romero.notimdb.Model.TMDb.Creditos;
import com.example.camilo_romero.notimdb.Model.TMDb.Equipo;
import com.example.camilo_romero.notimdb.Model.Listados.ListadoDeBackdrops;
import com.example.camilo_romero.notimdb.Model.Listados.ListadoDePeliculas;
import com.example.camilo_romero.notimdb.Model.Listados.ListadoDeTrailers;
import com.example.camilo_romero.notimdb.Model.TMDb.Pelicula;
import com.example.camilo_romero.notimdb.Model.TMDb.Trailer;
import com.example.camilo_romero.notimdb.Utils.ResultListener;
import com.example.camilo_romero.notimdb.Utils.TMDBHelper;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Response;

public class ControlerPeliculasRetrofit {
    public void pedirListaDePeliculasPopulares(String idioma, Integer pagina, final ResultListener<ListadoDePeliculas> escuchadorDeLaVista){
        new DAOPeliculasRetrofit().pedirListaDePeliculasPopulares(idioma, pagina, new ResultListener<ListadoDePeliculas>() {
            @Override
            public void finish(ListadoDePeliculas resultado) {
                escuchadorDeLaVista.finish(resultado);
            }
        });
    }
    public void pedirPeliculaPorid(String idioma, Integer id, final ResultListener<Pelicula> escuchadorDeLaVista){
        new DAOPeliculasRetrofit().pedirPeliculaPorid(idioma, id, new ResultListener<Pelicula>() {
            @Override
            public void finish(Pelicula resultado) {

                resultado.setRelease_date(resultado.getRelease_date());

                escuchadorDeLaVista.finish(resultado);
            }
        });

    }

    public void pedirListaDeTrailerDeUnaPelicula(String idioma, Integer id, final ResultListener<ListadoDeTrailers> escuchadorDeLaVista){
        new DAOPeliculasRetrofit().pedirListaDeTrailersDeUnaPelicula(idioma, id, new ResultListener<ListadoDeTrailers>() {
            @Override
            public void finish(ListadoDeTrailers resultado) {
                List<Trailer> listaDeTrailersDeYouTube = new ArrayList<>();

                for (Integer i = 0; i < resultado.getResults().size();i++){

                    if (resultado.getResults().get(i).getSite().equals(TMDBHelper.Youtube)){

                        listaDeTrailersDeYouTube.add(resultado.getResults().get(i));
                    }

                }

                escuchadorDeLaVista.finish(new ListadoDeTrailers(listaDeTrailersDeYouTube));
            }
        });
    }

    public void pedirCreditosDeUnaPelicula(Integer id, final ResultListener<Creditos> escuchadorDeLaVista){
        new DAOPeliculasRetrofit().pedirCreditosDeUnaPelicula(id, new ResultListener<Creditos>() {
            @Override
            public void finish(Creditos resultado) {
                List<Equipo> listaDelEquipo = new ArrayList<>();
                for (Integer i = 0; i < resultado.getCrew().size();i++){
                    if (resultado.getCrew().get(i).getProfile_path() != null){
                        listaDelEquipo.add(resultado.getCrew().get(i));
                    }

                }
                resultado.setCrew(listaDelEquipo);

                escuchadorDeLaVista.finish(resultado);
            }
        });
    }
    public void pedirImagenesDeUnaPelicula(Integer id, final ResultListener<ListadoDeBackdrops> escuchadorDeLaVista){
        new DAOPeliculasRetrofit().pedirListaDeImagenesDeUnaPelicula(id, new ResultListener<ListadoDeBackdrops>() {
            @Override
            public void finish(ListadoDeBackdrops resultado) {
                escuchadorDeLaVista.finish(resultado);
            }
        });

    }
    public void pedirListaDePeliculasSimilares(String idioma, Integer idPelicula, final ResultListener<ListadoDePeliculas> escuchadorDeLaVista){
        new DAOPeliculasRetrofit().pedirListaDePeliculasSimiliares(idioma, idPelicula, new ResultListener<ListadoDePeliculas>() {
            @Override
            public void finish(ListadoDePeliculas resultado) {
                escuchadorDeLaVista.finish(resultado);
            }
        });
    }

    public void pedirColeccionDeUnaPelicula(String idioma, Integer idColeccion, final ResultListener<Coleccion> escuchadorDeLaVista){
        new DAOPeliculasRetrofit().pedirColeccionDeUnaPelicula(idioma, idColeccion, new ResultListener<Coleccion>() {
            @Override
            public void finish(Coleccion resultado) {
                escuchadorDeLaVista.finish(resultado);
            }
        });
    }

    public void pedirListaDePeliculasPorGenero(Integer idGenero,String idioma,Integer pagina,final ResultListener<ListadoDePeliculas> escuchadorDeLaVista){
        new DAOPeliculasRetrofit().pedirListaDePeliculasPorGenero(idGenero, idioma, pagina, new ResultListener<ListadoDePeliculas>() {
            @Override
            public void finish(ListadoDePeliculas resultado) {
                escuchadorDeLaVista.finish(resultado);
            }
        });
    }

    public void enviarPuntuacionDeUnaPelicula(String json, Integer idPelicula, String idSesionInvitado, final ResultListener<Response> escuhadorDeLaVista){

    }

}
