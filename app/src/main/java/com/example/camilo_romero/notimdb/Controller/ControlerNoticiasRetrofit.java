package com.example.camilo_romero.notimdb.Controller;

import com.example.camilo_romero.notimdb.DAO.DAONoticiasRetrofit;
import com.example.camilo_romero.notimdb.Model.wp_json.Imagen;
import com.example.camilo_romero.notimdb.Model.wp_json.Noticia;
import com.example.camilo_romero.notimdb.Utils.ResultListener;

import java.util.List;

public class ControlerNoticiasRetrofit {

    public void pedirListaDeNoticias(final String urlBase, Integer cantidadDeNoticias, Integer pagina, final ResultListener<List<Noticia>> escuchadorDeLaVista){
        new DAONoticiasRetrofit(urlBase).pedirListaDeNoticias(cantidadDeNoticias, pagina, new ResultListener<List<Noticia>>() {
            @Override
            public void finish(final List<Noticia> resultado) {
                escuchadorDeLaVista.finish(resultado);

            }
        });


    }



}
