package com.example.camilo_romero.notimdb.Controller;

import com.example.camilo_romero.notimdb.DAO.DAOBusquedasRetrofit;
import com.example.camilo_romero.notimdb.Model.Listados.ListadoDeResultadosDeBusqueda;
import com.example.camilo_romero.notimdb.Model.TMDb.Actor;
import com.example.camilo_romero.notimdb.Model.TMDb.Pelicula;
import com.example.camilo_romero.notimdb.Model.TMDb.Serie;
import com.example.camilo_romero.notimdb.Utils.ResultListener;

import java.util.ArrayList;
import java.util.List;

public class ControlerBusquedasRetrofit {
    public void pedirResultadosDeLaBusqueda(String idioma, String busqueda, Integer pagina, final ResultListener<ListadoDeResultadosDeBusqueda> escuchadorDeLaVista){
        new DAOBusquedasRetrofit().pedirResultadosDeLaBusqueda(idioma, busqueda, pagina, new ResultListener<ListadoDeResultadosDeBusqueda>() {
            @Override
            public void finish(ListadoDeResultadosDeBusqueda resultado) {
                List<Pelicula> listaDePeliculas = new ArrayList<>();
                List<Serie> listaDeSeries = new ArrayList<>();
                List<Actor> listaDeActores = new ArrayList<>();
            }
        });
    }
}
