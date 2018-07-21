package com.example.camilo_romero.notimdb.View.Fragments.Serie;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.camilo_romero.notimdb.Controller.ControlerSeriesRetrofit;
import com.example.camilo_romero.notimdb.Model.TMDb.Serie;
import com.example.camilo_romero.notimdb.R;
import com.example.camilo_romero.notimdb.Utils.Helper;
import com.example.camilo_romero.notimdb.Utils.ResultListener;
import com.example.camilo_romero.notimdb.Utils.TMDBHelper;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 */
public class CreadorDeLaSerieFragment extends Fragment {
    public static final String CLAVE_ID_SERIE = "clave id serie";

    TextView textViewNombreDelCreador;
    CircleImageView circleImageViewPefilDelCreador;

    private Integer idSerie;


    public CreadorDeLaSerieFragment() {
        // Required empty public constructor
    }


    public static CreadorDeLaSerieFragment fabricaDeFragmentsCreadorDeLaSerie(Integer idSerie) {
        CreadorDeLaSerieFragment creadorDeLaSerieFragment = new CreadorDeLaSerieFragment();

        Bundle bundle = new Bundle();

        bundle.putInt(CLAVE_ID_SERIE, idSerie);

        creadorDeLaSerieFragment.setArguments(bundle);

        return creadorDeLaSerieFragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();

        idSerie = bundle.getInt(CLAVE_ID_SERIE);

        new ControlerSeriesRetrofit().pedirSeriePorid(idSerie, TMDBHelper.IDIOMA_ESPAÑOL, new ResultListener<Serie>() {
            @Override
            public void finish(Serie resultado) {
                textViewNombreDelCreador.setText(resultado.getCreated_by().get(0).getName());

                Helper.cargarImagenCircular(getContext(), resultado.getCreated_by().get(0).getProfile_path(), circleImageViewPefilDelCreador);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_creador_de_la_serie, container, false);

        textViewNombreDelCreador = view.findViewById(R.id.textViewNombreDelCreador_fragmentcreadordelaserie);
        circleImageViewPefilDelCreador = view.findViewById(R.id.circleImageViewPerfilCreadorDeLaSerie_fragmentcreadordelaserie);

        return view;
    }

}
