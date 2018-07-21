package com.example.camilo_romero.notimdb.Controller;

import com.example.camilo_romero.notimdb.DAO.DAOUsuarioRetrofit;
import com.example.camilo_romero.notimdb.Model.TMDb.Usuario;
import com.example.camilo_romero.notimdb.Utils.ResultListener;

public class ControlerUsuarioRetrofit {
    public void pedirUsuarioInvitado(final ResultListener<Usuario> escuchadorDeLaVista){
        new DAOUsuarioRetrofit().pedirUsuarioInvitado(new ResultListener<Usuario>() {
            @Override
            public void finish(Usuario resultado) {
                escuchadorDeLaVista.finish(resultado);
            }
        });
    }
}
