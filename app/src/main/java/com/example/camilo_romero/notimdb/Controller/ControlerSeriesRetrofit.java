package com.example.camilo_romero.notimdb.Controller;

import com.example.camilo_romero.notimdb.DAO.DAOPeliculasRetrofit;
import com.example.camilo_romero.notimdb.DAO.DAOSeriesRetrofit;
import com.example.camilo_romero.notimdb.Model.Listados.ListadoDeSeries;
import com.example.camilo_romero.notimdb.Model.Listados.ListadoDeTrailers;
import com.example.camilo_romero.notimdb.Model.TMDb.Serie;
import com.example.camilo_romero.notimdb.Utils.ResultListener;

public class ControlerSeriesRetrofit {
    public void pedirListaDeSeriesPopulares(String idioma, Integer pagina, final ResultListener<ListadoDeSeries> escuchadorDeLaVista) {
        new DAOSeriesRetrofit().pedirListaDeSeriesPopulares(idioma, pagina, new ResultListener<ListadoDeSeries>() {
            @Override
            public void finish(ListadoDeSeries resultado) {
                escuchadorDeLaVista.finish(resultado);
            }
        });
    }

    public void pedirSeriePorid(Integer idSerie, String idioma, final ResultListener<Serie> escuchadorDeLaVista) {
        new DAOSeriesRetrofit().pedirSeriePorid(idSerie, idioma, new ResultListener<Serie>() {
            @Override
            public void finish(Serie resultado) {
                escuchadorDeLaVista.finish(resultado);
            }
        });
    }

    public void pedirListaDeTrailerDeUnaSerie(Integer idSerie, String idioma, final ResultListener<ListadoDeTrailers> esuchadorDeLaVista) {
        new DAOSeriesRetrofit().pedirTrailersDeUnaSerie(idSerie, idioma, new ResultListener<ListadoDeTrailers>() {
            @Override
            public void finish(ListadoDeTrailers resultado) {
                esuchadorDeLaVista.finish(resultado);
            }
        });
    }

}


