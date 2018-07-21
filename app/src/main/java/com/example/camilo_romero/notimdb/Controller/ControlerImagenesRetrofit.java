package com.example.camilo_romero.notimdb.Controller;

import com.example.camilo_romero.notimdb.DAO.DAOImagenesRetrofit;
import com.example.camilo_romero.notimdb.Model.wp_json.Imagen;
import com.example.camilo_romero.notimdb.Utils.ResultListener;

public class ControlerImagenesRetrofit {


    public void traerImagenDeLaNoticia(String urlBase,Integer idNoticia, final ResultListener<Imagen> escuchadorDeLaVista){
        new DAOImagenesRetrofit(urlBase).traerImagenesDeLaNoticia(idNoticia, new ResultListener<Imagen>() {
            @Override
            public void finish(Imagen resultado) {
                escuchadorDeLaVista.finish(resultado);
            }
        });

    }
}
