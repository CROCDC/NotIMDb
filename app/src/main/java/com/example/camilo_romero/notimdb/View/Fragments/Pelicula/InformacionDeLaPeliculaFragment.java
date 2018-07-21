package com.example.camilo_romero.notimdb.View.Fragments.Pelicula;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.camilo_romero.notimdb.Controller.ControlerPeliculasRetrofit;
import com.example.camilo_romero.notimdb.Model.TMDb.Gender;
import com.example.camilo_romero.notimdb.Model.TMDb.Pelicula;
import com.example.camilo_romero.notimdb.R;
import com.example.camilo_romero.notimdb.Utils.Helper;
import com.example.camilo_romero.notimdb.Utils.ResultListener;
import com.example.camilo_romero.notimdb.Utils.TMDBHelper;
import com.example.camilo_romero.notimdb.View.Adapters.ListaDeCategoriasAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class InformacionDeLaPeliculaFragment extends Fragment {

    private TextView textViewTituloOriginalDeLaPelicula;
    private TextView textViewLemaDeLaPelicula;
    private TextView textViewEstadoActualDeLaPelicula;
    private TextView textViewDuracionDeLaPelicula;
    private TextView textViewFechaDeEstrenoDeLaPelicula;
    private TextView textViewIdiomaDeLaPelicula;
    private TextView textViewPresupuestoDeLaPelicula;
    private TextView textViewIngresosDeLaPelicula;
    private WebView webViewPaginaWebDeLaPelicula;
    private RecyclerView recyclerViewListaDeCategorias;

    private ListaDeCategoriasAdapter listaDeCategoriasAdapter;

    private NotificadorHaciaDescripcionesDePeliculasActivity notificador;

    public static final String CLAVE_ID_PELICULA = "id";
    public static final String CLAVE_OBJETO_PELICULA = "objeto";

    private Integer idPelicula;


    public static InformacionDeLaPeliculaFragment fabricaDefragmentinformaciondelapelicula(Integer idPelicula) {

        InformacionDeLaPeliculaFragment informacionDeLaPeliculaFragment = new InformacionDeLaPeliculaFragment();

        Bundle bundle = new Bundle();

        bundle.putInt(CLAVE_ID_PELICULA, idPelicula);

        informacionDeLaPeliculaFragment.setArguments(bundle);

        return informacionDeLaPeliculaFragment;

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        notificador = (NotificadorHaciaDescripcionesDePeliculasActivity) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final Bundle bundle = getArguments();

        idPelicula = bundle.getInt(CLAVE_ID_PELICULA);

        new ControlerPeliculasRetrofit().pedirPeliculaPorid(TMDBHelper.IDIOMA_ESPAÑOL, idPelicula, new ResultListener<Pelicula>() {
            @Override
            public void finish(Pelicula pelicula) {

                try {
                    textViewTituloOriginalDeLaPelicula.setText(pelicula.getTitle());
                    textViewLemaDeLaPelicula.setText(pelicula.getTagline());
                    textViewEstadoActualDeLaPelicula.setText(Helper.estadoAEspanol(pelicula.getStatus()));
                    textViewDuracionDeLaPelicula.setText(Helper.pasarMinutosAHoras(pelicula.getRuntime()).toString());
                    textViewFechaDeEstrenoDeLaPelicula.setText(pelicula.getRelease_date());
                    textViewIdiomaDeLaPelicula.setText(Helper.identificarIdioma(pelicula.getOriginal_language()));
                    textViewPresupuestoDeLaPelicula.setText(Helper.puntearNumero(pelicula.getBudget()));
                    textViewIngresosDeLaPelicula.setText(Helper.puntearNumero(pelicula.getRevenue()));
                    webViewPaginaWebDeLaPelicula.loadData(Helper.formatearALink(pelicula.getHomepage()), "text/html", "UTF-8");
                    listaDeCategoriasAdapter.setListaDeGeneros(pelicula.getGenres());
                } catch (Exception e) {
                    getActivity().onBackPressed();
                    Toast.makeText(getContext(), "Contenido no disponible sepa disculpar las molestias", Toast.LENGTH_SHORT).show();
                }


            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_informacion_de_la_pelicula, container, false);

        textViewTituloOriginalDeLaPelicula = view.findViewById(R.id.textViewTituloOriginalDeLaPelicula_fragmentinformaciondelapelicula);
        textViewLemaDeLaPelicula = view.findViewById(R.id.textViewLemaDeLaPelicula_fragmentinformaciondelapelicula);
        textViewEstadoActualDeLaPelicula = view.findViewById(R.id.textViewEstadoActualDeLaPelicula_fragmentinformaciondelapelicula);
        textViewDuracionDeLaPelicula = view.findViewById(R.id.textViewDuracionDeLaPelicula_fragmentinformaciondelapelicula);
        textViewFechaDeEstrenoDeLaPelicula = view.findViewById(R.id.textViewFechaDeEstrenoDeLaPelicula_fragmentinformaciondelapelicula);
        textViewIdiomaDeLaPelicula = view.findViewById(R.id.textViewIdiomaDeLaPelicula_fragmentinformaciondelapelicula);
        textViewPresupuestoDeLaPelicula = view.findViewById(R.id.textViewPresupuestoDeLaPelicula_fragmentinformaciondelapelicula);
        textViewIngresosDeLaPelicula = view.findViewById(R.id.textViewIngresosDeLaPelicula_fragmentinformaciondelapelicula);
        webViewPaginaWebDeLaPelicula = view.findViewById(R.id.webViewPaginaDeLaPelicula_fragmentinformaciondelapelicula);
        webViewPaginaWebDeLaPelicula.setBackgroundColor(Color.TRANSPARENT);
        recyclerViewListaDeCategorias = view.findViewById(R.id.recyclerViewListaDeCategorias_fragmentinformaciondelapelicula);

        listaDeCategoriasAdapter = new ListaDeCategoriasAdapter(new ListaDeCategoriasAdapter.NotificadorHaciaFragmentInformacionDeLaPelicula() {
            @Override
            public void notificar(Gender gender) {
                notificador.notificarPeliculasPorCategoria(gender);
            }
        });


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewListaDeCategorias.setAdapter(listaDeCategoriasAdapter);
        recyclerViewListaDeCategorias.setLayoutManager(linearLayoutManager);

        return view;
    }

    public interface NotificadorHaciaDescripcionesDePeliculasActivity {
        public void notificarPeliculasPorCategoria(Gender gender);
    }
}
