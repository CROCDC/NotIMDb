package com.example.camilo_romero.notimdb.View.Fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.camilo_romero.notimdb.Model.Listados.ListadoDeTrailers;
import com.example.camilo_romero.notimdb.Model.TMDb.Trailer;
import com.example.camilo_romero.notimdb.R;
import com.example.camilo_romero.notimdb.View.Adapters.ListaDeTrailersDeYoutubeAdapter;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListaDeTrailersFragment extends Fragment {


    public static final String CLAVE_LISTA_DE_TRAILERS = "lista de trailers";
    private RecyclerView recyclerViewListaDeTrailers;
    private ListaDeTrailersDeYoutubeAdapter listaDeTrailersDeYoutubeAdapter;
    private NotificadorHaciaTrailersDePeliculasActivity notificador;

    private List<Trailer> listaDeTrailers;


    public ListaDeTrailersFragment() {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        notificador = (NotificadorHaciaTrailersDePeliculasActivity) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();

        ListadoDeTrailers listadoDeTrailers = (ListadoDeTrailers) bundle.getSerializable(CLAVE_LISTA_DE_TRAILERS);

        listaDeTrailers = listadoDeTrailers.getResults();

        listaDeTrailersDeYoutubeAdapter = new ListaDeTrailersDeYoutubeAdapter(new ListaDeTrailersDeYoutubeAdapter.NotificadorHaciaInstanciadorDelAdapter() {
            @Override
            public void notificar(Trailer trailer) {
                notificador.notificar(trailer);
            }
        });

        listaDeTrailersDeYoutubeAdapter.setListaDeTrailers(listaDeTrailers);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_view_pager_lista_de_trailers_de_peliculas, container, false);

        recyclerViewListaDeTrailers = view.findViewById(R.id.recyclerViewListaDeTrailers_fragmentviewpagerlistadetrailersdepeliculas);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false);

        recyclerViewListaDeTrailers.setAdapter(listaDeTrailersDeYoutubeAdapter);
        recyclerViewListaDeTrailers.setLayoutManager(linearLayoutManager);


        return view;
    }

    public interface NotificadorHaciaTrailersDePeliculasActivity {
        public void notificar(Trailer trailer);
    }

}
