package com.example.camilo_romero.notimdb.View.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.camilo_romero.notimdb.Controller.ControlerPeliculasRetrofit;
import com.example.camilo_romero.notimdb.Controller.ControlerSeriesRetrofit;
import com.example.camilo_romero.notimdb.Model.Listados.ListadoDeTrailers;
import com.example.camilo_romero.notimdb.Model.TMDb.Pelicula;
import com.example.camilo_romero.notimdb.Model.TMDb.Serie;
import com.example.camilo_romero.notimdb.Model.TMDb.Trailer;
import com.example.camilo_romero.notimdb.R;
import com.example.camilo_romero.notimdb.Utils.Helper;
import com.example.camilo_romero.notimdb.Utils.ResultListener;
import com.example.camilo_romero.notimdb.Utils.TMDBHelper;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class DescripcionesTrailersFragment extends Fragment {

    public static final String CLAVE_ID = "clave";
    public static final String CLAVE_LISTA_DE_TRAILERS = "lista de trailers";
    public static final String CLAVE_QUE_ES = "que es";

    private TextView textViewTituloDelTrailer;
    private TextView textViewDescripcioneDeLaPelicula;

    private ImageView imageViewPosterDeLaPelicula;
    private TextView textViewTitulo;
    private TextView textViewFechaDeEstreno;

    private Integer id;
    private Boolean queEs;
    private List<Trailer> listaDeTrailers;

    public DescripcionesTrailersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_view_pager_descripciones_trailers, container, false);

        textViewTituloDelTrailer = view.findViewById(R.id.textViewTituloDelTrailer_viewpagerdescripcionesdepeliculastrailersfragment);
        textViewDescripcioneDeLaPelicula = view.findViewById(R.id.textViewDescripcion_viewpagerdescripcionesdepeliculastrailersfragment);
        imageViewPosterDeLaPelicula = view.findViewById(R.id.imageViewPoster_fragmentviewpagerdescripcionesdepeliculastrailers);
        textViewTitulo = view.findViewById(R.id.textviewTitulo_fragmentviewpagerdescripcionesdepeliculastrailers);
        textViewFechaDeEstreno = view.findViewById(R.id.textviewFechaDeEstreno_fragmentviewpagerdescripcionesdepeliculastrailers);

        Bundle bundle = getArguments();

        id = bundle.getInt(CLAVE_ID);

        queEs = bundle.getBoolean(CLAVE_QUE_ES);

        final ListadoDeTrailers listadoDeTrailers = (ListadoDeTrailers) bundle.getSerializable(CLAVE_LISTA_DE_TRAILERS);

        listaDeTrailers = listadoDeTrailers.getResults();

        if (queEs){
            new ControlerPeliculasRetrofit().pedirPeliculaPorid(TMDBHelper.IDIOMA_ESPAÑOL, id, new ResultListener<Pelicula>() {
                @Override
                public void finish(Pelicula resultado) {

                    textViewTituloDelTrailer.setText(listaDeTrailers.get(0).getName());

                    textViewDescripcioneDeLaPelicula.setText(resultado.getOverview());
                    textViewTitulo.setText(resultado.getTitle());
                    textViewFechaDeEstreno.setText(resultado.getRelease_date());

                    Helper.cargarImagenesPoster(getContext(), resultado.getPoster_path(), imageViewPosterDeLaPelicula);
                }
            });
        }else {
            new ControlerSeriesRetrofit().pedirSeriePorid(id, TMDBHelper.IDIOMA_ESPAÑOL, new ResultListener<Serie>() {
                @Override
                public void finish(Serie resultado) {
                    textViewTituloDelTrailer.setText(listaDeTrailers.get(0).getName());

                    textViewDescripcioneDeLaPelicula.setText(resultado.getOverview());
                    textViewTitulo.setText(resultado.getName());
                    textViewFechaDeEstreno.setText(resultado.getFirst_air_date());

                    Helper.cargarImagenesPoster(getContext(), resultado.getPoster_path(), imageViewPosterDeLaPelicula);

                }
            });
        }


        return view;
    }

}
