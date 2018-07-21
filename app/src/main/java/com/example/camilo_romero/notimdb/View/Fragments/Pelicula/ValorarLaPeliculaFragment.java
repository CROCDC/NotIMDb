package com.example.camilo_romero.notimdb.View.Fragments.Pelicula;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.camilo_romero.notimdb.Controller.ControlerUsuarioRetrofit;
import com.example.camilo_romero.notimdb.DAO.DAOPeliculasRetrofit;
import com.example.camilo_romero.notimdb.Model.TMDb.Usuario;
import com.example.camilo_romero.notimdb.R;
import com.example.camilo_romero.notimdb.Utils.ResultListener;
import com.willy.ratingbar.ScaleRatingBar;

import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/**
 * A simple {@link Fragment} subclass.
 */
public class ValorarLaPeliculaFragment extends Fragment {

    public static final String CLAVE_IDPELICUA = "id pelicua";
    private LinearLayout linearLayoutContenedor;
    private CardView cardViewContenedorDePuntuador;
    private ScaleRatingBar scaleRatingBarPuntuador;
    private TextView textViewFijoEliminarPuntuacion;
    private TextView textViewFijoPuntuarLaPelicula;


    private Integer idPelicula;

    public ValorarLaPeliculaFragment() {
        // Required empty public constructor
    }

    public static ValorarLaPeliculaFragment fabricaDeFragmentValorarLaPelicula(Integer idPelicula){
        ValorarLaPeliculaFragment valorarLaPeliculaFragment = new ValorarLaPeliculaFragment();

        Bundle bundle = new Bundle();

        bundle.putInt(CLAVE_IDPELICUA,idPelicula);

        valorarLaPeliculaFragment.setArguments(bundle);

        return valorarLaPeliculaFragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_puntuar_la_pelicula, container, false);

        Bundle bundle = getArguments();

        idPelicula = bundle.getInt(CLAVE_IDPELICUA);

        linearLayoutContenedor = view.findViewById(R.id.linerLayoutContenedor_fragmentpuntuarlapelicula);
        cardViewContenedorDePuntuador = view.findViewById(R.id.cardViewContenedorDelPuntuador_fragmentpuntuarlapelicula);
        scaleRatingBarPuntuador = view.findViewById(R.id.RatingBarPuntuador_fragmentpuntuarlapelicula);
        textViewFijoEliminarPuntuacion = view.findViewById(R.id.textViewFijoEliminarValoracion_fragmentpuntuarlapelicula);
        textViewFijoPuntuarLaPelicula = view.findViewById(R.id.textViewFijoAplicarValoracion_fragmentpuntuarlapelicula);


        touchLinearLayoutContenedor();
        touchCardViewContenedorDePuntuador();
        touchTextViewFijoPuntuar();

        return view;
    }

    public void touchLinearLayoutContenedor(){
        linearLayoutContenedor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
    }
    public void touchCardViewContenedorDePuntuador(){
        cardViewContenedorDePuntuador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }
    public void touchTextViewFijoPuntuar(){

        final RequestBody requestBodyToken = RequestBody.create(MediaType.parse("text/json"), String.valueOf(scaleRatingBarPuntuador.getRating()));


        textViewFijoPuntuarLaPelicula.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ControlerUsuarioRetrofit().pedirUsuarioInvitado(new ResultListener<Usuario>() {
                    @Override
                    public void finish(Usuario resultado) {

                        JSONObject paramJson = new JSONObject();

                        try {
                            paramJson.put("value",scaleRatingBarPuntuador.getRating());
                        }catch (Exception e){

                        }

                        new DAOPeliculasRetrofit().enviarPuntuacionDeLaPelicula(paramJson.toString(), idPelicula, resultado.getGuest_session_id(), new ResultListener<ResponseBody>() {
                                    @Override
                                    public void finish(ResponseBody resultado) {
                                    }
                                }
                        );


                    }
                });
            }
        });
    }




}
