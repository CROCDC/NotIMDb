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
import android.widget.ImageView;
import android.widget.TextView;

import com.example.camilo_romero.notimdb.Controller.ControlerPeliculasRetrofit;
import com.example.camilo_romero.notimdb.Model.TMDb.Coleccion;
import com.example.camilo_romero.notimdb.Model.TMDb.Pelicula;
import com.example.camilo_romero.notimdb.R;
import com.example.camilo_romero.notimdb.Utils.Helper;
import com.example.camilo_romero.notimdb.Utils.ResultListener;
import com.example.camilo_romero.notimdb.Utils.TMDBHelper;
import com.example.camilo_romero.notimdb.View.Adapters.ListaDePeliculasAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class ColeccionDeLaPeliculaFragment extends Fragment {
    public static final String CLAVE_ID_DE_LA_PELICULA = "iddelapelicula";


    private TextView textViewTituloDeLaColeccion;
    private ImageView imageViewBackgroundDeLaColeccion;
    private TextView textViewDescripcionDeLaColeccion;
    private RecyclerView recyclerViewListaDePeliculasDeLaColeccion;

    private ListaDePeliculasAdapter adapterListaDePeliculasDeLaColeccion;

    private Integer idDeLaColeccion;

    private NotificadorColleccionDeLaPeliculaFragment notificador;


    public static ColeccionDeLaPeliculaFragment fabricaFragmentsColeccionDeLaPelicula(Integer idDeLaPelicula) {

        ColeccionDeLaPeliculaFragment coleccionDeLaPeliculaFragment = new ColeccionDeLaPeliculaFragment();


        Bundle bundle = new Bundle();

        bundle.putInt(CLAVE_ID_DE_LA_PELICULA, idDeLaPelicula);

        coleccionDeLaPeliculaFragment.setArguments(bundle);

        return coleccionDeLaPeliculaFragment;


    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        notificador = (NotificadorColleccionDeLaPeliculaFragment) context;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();

        idDeLaColeccion = bundle.getInt(CLAVE_ID_DE_LA_PELICULA);

        adapterListaDePeliculasDeLaColeccion = new ListaDePeliculasAdapter(new ListaDePeliculasAdapter.NotificadorListaDePeliculasAdapter() {
            @Override
            public void notificar(Pelicula pelicula) {
                notificador.notificar(pelicula);
            }
        });

        new ControlerPeliculasRetrofit().pedirPeliculaPorid(TMDBHelper.IDIOMA_ESPAÑOL, idDeLaColeccion, new ResultListener<Pelicula>() {
            @Override
            public void finish(Pelicula resultado) {
                try {
                    new ControlerPeliculasRetrofit().pedirColeccionDeUnaPelicula(TMDBHelper.IDIOMA_ESPAÑOL, resultado.getBelongs_to_collection().getId(), new ResultListener<Coleccion>() {
                        @Override
                        public void finish(Coleccion resultado) {
                            textViewTituloDeLaColeccion.setText(resultado.getName());

                            Helper.cargarImagenesBackground(getContext(), resultado.getBackdrop_path(), imageViewBackgroundDeLaColeccion);

                            textViewDescripcionDeLaColeccion.setText(resultado.getOverview());

                            adapterListaDePeliculasDeLaColeccion.setListaDePeliculas(resultado.getParts());
                        }
                    });
                } catch (Exception e) {
                    notificador.notificarColecionNula();
                }

            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_coleccion_de_la_pelicula, container, false);

        textViewTituloDeLaColeccion = view.findViewById(R.id.textViewTituloDeLaColeccion_fragmentcolecciondelapelicula);
        imageViewBackgroundDeLaColeccion = view.findViewById(R.id.imageViewBackgrounDeLaColeccion_fragmentcolecciondelapelicula);
        textViewDescripcionDeLaColeccion = view.findViewById(R.id.textViewDescripcionDeLaColeccion_fragmentcolecciondelapelicula);
        recyclerViewListaDePeliculasDeLaColeccion = view.findViewById(R.id.recyclerViewListaDePeliculasDeLaColeccion_fragmentcolecciondelapelicula);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);

        recyclerViewListaDePeliculasDeLaColeccion.setAdapter(adapterListaDePeliculasDeLaColeccion);

        recyclerViewListaDePeliculasDeLaColeccion.setLayoutManager(linearLayoutManager);

        return view;
    }

    public interface NotificadorColleccionDeLaPeliculaFragment{
        public void notificar(Pelicula pelicula);
        public void notificarColecionNula();
    }
}
