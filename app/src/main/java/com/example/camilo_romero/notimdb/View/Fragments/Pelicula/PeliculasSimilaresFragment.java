package com.example.camilo_romero.notimdb.View.Fragments.Pelicula;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.camilo_romero.notimdb.Controller.ControlerPeliculasRetrofit;
import com.example.camilo_romero.notimdb.Model.Listados.ListadoDePeliculas;
import com.example.camilo_romero.notimdb.Model.TMDb.Pelicula;
import com.example.camilo_romero.notimdb.R;
import com.example.camilo_romero.notimdb.Utils.ResultListener;
import com.example.camilo_romero.notimdb.Utils.TMDBHelper;
import com.example.camilo_romero.notimdb.View.Adapters.ListaDePeliculasAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class PeliculasSimilaresFragment extends Fragment {

    public static final String CLAVE_ID_PELICULA = "id pelicula";

    private RecyclerView recyclerViewListaDePeliculasSimilares;

    private ListaDePeliculasAdapter listaDePeliculasSimilaresAdapter;

    private Integer idPelicula;

    private NotificadorPeliculasSimilaresFragment notificador;



    public static PeliculasSimilaresFragment fabricaDeFragmentsPeliculasSimilares(Integer idPelicula){
        PeliculasSimilaresFragment peliculasSimilaresFragment = new PeliculasSimilaresFragment();

        Bundle bundle = new Bundle();

        bundle.putInt(CLAVE_ID_PELICULA,idPelicula);

        peliculasSimilaresFragment.setArguments(bundle);

        return peliculasSimilaresFragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        notificador = (NotificadorPeliculasSimilaresFragment) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();

        idPelicula = bundle.getInt(CLAVE_ID_PELICULA);

        listaDePeliculasSimilaresAdapter = new ListaDePeliculasAdapter(new ListaDePeliculasAdapter.NotificadorListaDePeliculasAdapter() {
            @Override
            public void notificar(Pelicula pelicula) {
                notificador.notificar(pelicula);
            }
        });
        new ControlerPeliculasRetrofit().pedirListaDePeliculasSimilares(TMDBHelper.IDIOMA_ESPAÑOL, idPelicula, new ResultListener<ListadoDePeliculas>() {
            @Override
            public void finish(ListadoDePeliculas resultado) {
                listaDePeliculasSimilaresAdapter.setListaDePeliculas(resultado.getResults());
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_peliculas_similares, container, false);

        recyclerViewListaDePeliculasSimilares = view.findViewById(R.id.recyclerViewListaDeContenidoSimilar_fragmentpeliculassimilares);

        recyclerViewListaDePeliculasSimilares.setAdapter(listaDePeliculasSimilaresAdapter);

        recyclerViewListaDePeliculasSimilares.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));


        return view;
    }

    public interface NotificadorPeliculasSimilaresFragment{
        public void notificar(Pelicula pelicula);
    }
}
