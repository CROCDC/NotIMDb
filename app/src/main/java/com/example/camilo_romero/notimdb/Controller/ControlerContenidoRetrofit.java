package com.example.camilo_romero.notimdb.Controller;

import com.example.camilo_romero.notimdb.DAO.DAOPeliculasRetrofit;
import com.example.camilo_romero.notimdb.DAO.DAOSeriesRetrofit;
import com.example.camilo_romero.notimdb.Model.TMDb.Contenido;
import com.example.camilo_romero.notimdb.Model.Listados.ListadoDePeliculas;
import com.example.camilo_romero.notimdb.Model.Listados.ListadoDeSeries;
import com.example.camilo_romero.notimdb.Model.TMDb.Pelicula;
import com.example.camilo_romero.notimdb.Model.TMDb.Serie;
import com.example.camilo_romero.notimdb.Utils.Helper;
import com.example.camilo_romero.notimdb.Utils.ResultListener;

import java.util.ArrayList;
import java.util.List;

public class ControlerContenidoRetrofit {

    public void pedirListaDeContenidoPopular(final String idioma, final Integer pagina, final ResultListener<List<Contenido>> escuchadorDeLaVista) {
        final List<Contenido> listaDeContenido = new ArrayList<>();

        new DAOPeliculasRetrofit().pedirListaDePeliculasPopulares(idioma, pagina, new ResultListener<ListadoDePeliculas>() {
            @Override
            public void finish(final ListadoDePeliculas resultadoPeliculas) {

                new DAOSeriesRetrofit().pedirListaDeSeriesPopulares(idioma, pagina, new ResultListener<ListadoDeSeries>() {
                    @Override
                    public void finish(ListadoDeSeries resultadoSeries) {


                        List<Pelicula> listaDePeliculas = resultadoPeliculas.getResults();
                        List<Serie> listaDeSeries = resultadoSeries.getResults();

                        for (Integer i = 0; i < 20; i++) {
                            if (i % 2 == 0) {


                                String titulo = listaDePeliculas.get(i).getTitle();
                                Integer id = listaDePeliculas.get(i).getId();
                                String imagenBackGround = listaDePeliculas.get(i).getBackdrop_path();
                                String queEs = Helper.PELICULA;
                                String imagenPoster = listaDePeliculas.get(i).getPoster_path();
                                String descripcion = listaDePeliculas.get(i).getOverview();
                                Double puntaje = listaDePeliculas.get(i).getVote_average();

                                listaDeContenido.add(new Contenido(titulo, id, queEs, imagenBackGround, imagenPoster, descripcion, puntaje));

                            } else {


                                String titulo = listaDeSeries.get(i).getName();
                                Integer id = listaDeSeries.get(i).getId();
                                String imagenBackGround = listaDeSeries.get(i).getBackdrop_path();
                                String queEs = Helper.SERIE;
                                String imagenPoster = listaDeSeries.get(i).getPoster_path();
                                String descripcion = listaDeSeries.get(i).getOverview();
                                Double puntaje = listaDeSeries.get(i).getVote_average();

                                listaDeContenido.add(new Contenido(titulo, id, queEs, imagenBackGround, imagenPoster, descripcion, puntaje));

                            }
                        }
                        escuchadorDeLaVista.finish(listaDeContenido);

                    }
                });

            }
        });
    }

    public void pedirListaDeContenidoSimilarAUnaPelicula(String idioma, Integer idContenido, final ResultListener<List<Contenido>> escuchadorDeLaVista){
        final List<Contenido> listaDeContenido = new ArrayList<>();

        new DAOPeliculasRetrofit().pedirListaDePeliculasSimiliares(idioma, idContenido, new ResultListener<ListadoDePeliculas>() {
            @Override
            public void finish(ListadoDePeliculas listaDePeliculas) {

                for (Integer i = 0; i < listaDePeliculas.getResults().size();i++){

                    String titulo = listaDePeliculas.getResults().get(i).getTitle();
                    Integer id = listaDePeliculas.getResults().get(i).getId();
                    String imagenBackGround = listaDePeliculas.getResults().get(i).getBackdrop_path();
                    String queEs = Helper.PELICULA;
                    String imagenPoster = listaDePeliculas.getResults().get(i).getPoster_path();
                    String descripcion = listaDePeliculas.getResults().get(i).getOverview();
                    Double puntaje = listaDePeliculas.getResults().get(i).getVote_average();


                    listaDeContenido.add(new Contenido(titulo, id, queEs, imagenBackGround, imagenPoster, descripcion, puntaje));
                }
                escuchadorDeLaVista.finish(listaDeContenido);
            }
        });
    }
}
