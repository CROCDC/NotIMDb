package com.example.camilo_romero.notimdb.View.Fragments.Serie;


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

import com.example.camilo_romero.notimdb.Controller.ControlerSeriesRetrofit;
import com.example.camilo_romero.notimdb.DAO.DAOSeriesRetrofit;
import com.example.camilo_romero.notimdb.Model.TMDb.Gender;
import com.example.camilo_romero.notimdb.Model.TMDb.Serie;
import com.example.camilo_romero.notimdb.R;
import com.example.camilo_romero.notimdb.Utils.Helper;
import com.example.camilo_romero.notimdb.Utils.ResultListener;
import com.example.camilo_romero.notimdb.Utils.TMDBHelper;
import com.example.camilo_romero.notimdb.View.Activitys.PeliculasPorCategoriaActivity;
import com.example.camilo_romero.notimdb.View.Adapters.ListaDeCategoriasAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class InformacionDeLaSerieFragment extends Fragment {

    public static final String CLAVE_ID_SERIE = "id serie";

    private TextView textViewTituloOriginalDeLaSerie;
    private TextView textViewPopularidadDeLaSerie;
    private TextView textViewCantidadDeTemporadasDeLaSerie;
    private TextView textViewCantidadDeCapitulosDeLaSerie;
    private TextView textViewEstadoActualDeLaSerie;
    private TextView textViewIdiomaDeLaSerie;
    private TextView textViewDuracionDeUnCapituloDeLaSerie;
    private TextView textViewDuracionDeLaSerie;
    private TextView textViewFechaDeEstrenoDeLaSerie;
    private TextView textViewFechaDelUltimoCapituloDeLaSerie;
    private WebView webViewPaginaDeLaSerie;
    private RecyclerView recyclerViewListaDeCategorias;

    private Integer idSerie;

    private ListaDeCategoriasAdapter listaDeCategoriasAdapter;

    private NotificadorHaciaDescripcionesDeSeriesActivity notificador;


    public InformacionDeLaSerieFragment() {
    }

    public static InformacionDeLaSerieFragment fabricaDeFragmentsInformacionDeLaSerie(Integer idSerie) {
        InformacionDeLaSerieFragment informacionDeLaSerieFragment = new InformacionDeLaSerieFragment();

        Bundle bundle = new Bundle();

        bundle.putInt(CLAVE_ID_SERIE, idSerie);

        informacionDeLaSerieFragment.setArguments(bundle);

        return informacionDeLaSerieFragment;

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        notificador = (NotificadorHaciaDescripcionesDeSeriesActivity) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();

        idSerie = bundle.getInt(CLAVE_ID_SERIE);

        listaDeCategoriasAdapter = new ListaDeCategoriasAdapter(new ListaDeCategoriasAdapter.NotificadorHaciaFragmentInformacionDeLaPelicula() {
            @Override
            public void notificar(Gender gender) {
                notificador.notificarSeriesPorCategoriaActiivty(gender);
            }
        });

        new ControlerSeriesRetrofit().pedirSeriePorid(idSerie, TMDBHelper.IDIOMA_ESPAÑOL, new ResultListener<Serie>() {
            @Override
            public void finish(Serie resultado) {
                textViewTituloOriginalDeLaSerie.setText(resultado.getOriginal_name());
                textViewPopularidadDeLaSerie.setText(resultado.getPopularity().toString());
                textViewCantidadDeTemporadasDeLaSerie.setText(resultado.getNumber_of_seasons().toString());
                textViewCantidadDeCapitulosDeLaSerie.setText(resultado.getNumber_of_episodes().toString());
                textViewEstadoActualDeLaSerie.setText(resultado.getStatus());
                textViewIdiomaDeLaSerie.setText(resultado.getOriginal_language());
                textViewDuracionDeUnCapituloDeLaSerie.setText(resultado.getEpisode_run_time().get(0).toString());
                textViewDuracionDeLaSerie.setText(Helper.pasarMinutosAHoras(resultado.getNumber_of_seasons() * resultado.getNumber_of_episodes() * resultado.getEpisode_run_time().get(0)));
                textViewFechaDeEstrenoDeLaSerie.setText(resultado.getFirst_air_date());
                textViewFechaDelUltimoCapituloDeLaSerie.setText(resultado.getLast_air_date());
                webViewPaginaDeLaSerie.loadData(Helper.formatearALink(resultado.getHomepage()), "text/html", "UTF-8");
                webViewPaginaDeLaSerie.setBackgroundColor(Color.TRANSPARENT);
                listaDeCategoriasAdapter.setListaDeGeneros(resultado.getGenres());
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_informacion_de_la_serie, container, false);

        textViewTituloOriginalDeLaSerie = view.findViewById(R.id.textViewTituloOriginalDeLaSerie_fragmentinformaciondelaserie);
        textViewPopularidadDeLaSerie = view.findViewById(R.id.textViewPopularidadDeLaSerie_fragmentinformaciondelaserie);
        textViewCantidadDeTemporadasDeLaSerie = view.findViewById(R.id.textViewCantidadDeTemporadas_fragmentinformaciondelaserie);
        textViewCantidadDeCapitulosDeLaSerie = view.findViewById(R.id.textViewCantidadDeCapitulos_fragmentinformaciondelaserie);
        textViewEstadoActualDeLaSerie = view.findViewById(R.id.textViewEstadoActualDeLaSerie_fragmentinformaciondelaserie);
        textViewIdiomaDeLaSerie = view.findViewById(R.id.textViewIdiomaDeLaSerie_fragmentinformaciondelaserie);
        textViewDuracionDeUnCapituloDeLaSerie = view.findViewById(R.id.textViewDuracionCapitulo_fragmentinformaciondelaserie);
        textViewDuracionDeLaSerie = view.findViewById(R.id.textViewDuracionTotalDeLaSerie_fragmentinformaciondelaserie);
        textViewFechaDeEstrenoDeLaSerie = view.findViewById(R.id.textViewFechaDeEstrenoDeLaSerie_fragmentinformaciondelaserie);
        textViewFechaDelUltimoCapituloDeLaSerie = view.findViewById(R.id.textViewFechaDeEmisionDelUltimoCapitlo_fragmentinformaciondelaserie);
        webViewPaginaDeLaSerie = view.findViewById(R.id.webViewPaginaDeLaSerie_fragmentinformaciondelaserie);
        recyclerViewListaDeCategorias = view.findViewById(R.id.recyclerViewListaDeCategorias_fragmentinformaciondelaserie);

        recyclerViewListaDeCategorias.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        recyclerViewListaDeCategorias.setAdapter(listaDeCategoriasAdapter);


        return view;
    }

    public interface NotificadorHaciaDescripcionesDeSeriesActivity {
        public void notificarSeriesPorCategoriaActiivty(Gender gender);
    }

}
