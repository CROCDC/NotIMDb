package com.example.camilo_romero.notimdb.View.Fragments.Pelicula;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.camilo_romero.notimdb.Controller.ControlerPeliculasRetrofit;
import com.example.camilo_romero.notimdb.Model.TMDb.Creditos;
import com.example.camilo_romero.notimdb.R;
import com.example.camilo_romero.notimdb.Utils.ResultListener;
import com.example.camilo_romero.notimdb.View.Adapters.ActoresAdapter;
import com.example.camilo_romero.notimdb.View.Adapters.EquipoAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class CreditosDeLaPeliculaFragment extends Fragment {

    public static final String CLAVE_ID_PELICULA = "id pelicula";

    private RecyclerView recyclerViewListaDeActores;
    private RecyclerView recyclerViewListaDelEquipo;

    private ActoresAdapter listaDeActoresAdapter;
    private EquipoAdapter listaDelEquipoAdapter;

    private Integer idPelicula;

    public static CreditosDeLaPeliculaFragment fabricaDeFragmentsCreditosDeLaPelicula(Integer idPelicula) {
        CreditosDeLaPeliculaFragment creditosDeLaPeliculaFragment = new CreditosDeLaPeliculaFragment();

        Bundle bundle = new Bundle();

        bundle.putInt(CLAVE_ID_PELICULA, idPelicula);

        creditosDeLaPeliculaFragment.setArguments(bundle);

        return creditosDeLaPeliculaFragment;


    }

    public CreditosDeLaPeliculaFragment() {


    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        listaDeActoresAdapter = new ActoresAdapter();
        listaDelEquipoAdapter = new EquipoAdapter();

        Bundle bundle = getArguments();

        idPelicula = bundle.getInt(CLAVE_ID_PELICULA);

        new ControlerPeliculasRetrofit().pedirCreditosDeUnaPelicula(idPelicula, new ResultListener<Creditos>() {
            @Override
            public void finish(Creditos resultado) {
                listaDeActoresAdapter.setListaDeActores(resultado.getCast());
                listaDelEquipoAdapter.setListaDelEquipo(resultado.getCrew());
            }
        });

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_creditos_de_la_pelicula, container, false);

        recyclerViewListaDeActores = view.findViewById(R.id.recyclerViewListaDeActores_fragmentcreditosdelapelicula);
        recyclerViewListaDelEquipo = view.findViewById(R.id.recyclerViewListaDelEquipo_fragmentcreditosdelapelicula);

        recyclerViewListaDeActores.setAdapter(listaDeActoresAdapter);
        recyclerViewListaDelEquipo.setAdapter(listaDelEquipoAdapter);

        LinearLayoutManager linearLayoutManagerListaDeActores = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager linearLayoutManagerListaDeEquipo = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);

        recyclerViewListaDeActores.setLayoutManager(linearLayoutManagerListaDeActores);
        recyclerViewListaDelEquipo.setLayoutManager(linearLayoutManagerListaDeEquipo);

        return view;
    }

}
