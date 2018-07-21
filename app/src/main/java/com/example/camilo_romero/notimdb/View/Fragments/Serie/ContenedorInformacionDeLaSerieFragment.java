package com.example.camilo_romero.notimdb.View.Fragments.Serie;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.camilo_romero.notimdb.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContenedorInformacionDeLaSerieFragment extends Fragment {


    private static final String CLAVE_IDSERIE = "id serie";

    private Integer idSerie;

    public ContenedorInformacionDeLaSerieFragment() {
        // Required empty public constructor
    }

    public static ContenedorInformacionDeLaSerieFragment frabricaDeFragmentsContenedoInformacionDeLaSerie(Integer idSerie) {
        ContenedorInformacionDeLaSerieFragment contenedorInformacionDeLaSerieFragment = new ContenedorInformacionDeLaSerieFragment();

        Bundle bundle = new Bundle();

        bundle.putInt(CLAVE_IDSERIE, idSerie);

        contenedorInformacionDeLaSerieFragment.setArguments(bundle);

        return contenedorInformacionDeLaSerieFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        Bundle bundle = getArguments();

        idSerie = bundle.getInt(CLAVE_IDSERIE);

        View view = inflater.inflate(R.layout.fragment_contenedor_informacion_de_la_serie, container, false);

        cargarFragmentPorLugar(InformacionDeLaSerieFragment.fabricaDeFragmentsInformacionDeLaSerie(idSerie),R.id.cardViewContenedorInformacionDeLaSerie_fragmentcontenedorinformaciondelaserie);
        cargarFragmentPorLugar(CreadorDeLaSerieFragment.fabricaDeFragmentsCreadorDeLaSerie(idSerie),R.id.cardViewContenedorCreadorDeLaSerie_fragmentcontenedorinformaciondelaserie);


        return view;
    }


    public void cargarFragmentPorLugar(Fragment fragment, Integer id) {
        FragmentManager fragmentManager = getChildFragmentManager();

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(id, fragment);
        fragmentTransaction.commit();
    }


}
