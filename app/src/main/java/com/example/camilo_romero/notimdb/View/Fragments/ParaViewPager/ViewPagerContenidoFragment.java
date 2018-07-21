package com.example.camilo_romero.notimdb.View.Fragments.ParaViewPager;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.camilo_romero.notimdb.Model.TMDb.Contenido;
import com.example.camilo_romero.notimdb.R;
import com.example.camilo_romero.notimdb.Utils.Helper;
import com.willy.ratingbar.ScaleRatingBar;

/**
 * A simple {@link Fragment} subclass.
 */
public class ViewPagerContenidoFragment extends Fragment {

    public static final String CLAVE_OBJETO_CONTENIDO = "contenido";

    private CardView cardViewContenedor;
    private ImageView imageViewBackground;
    private TextView textViewTituloDelContenido;
    private ScaleRatingBar scaleRatingBarPuntaje;
    private ImageView imageViewPoster;
    private ImageView imageViewPlay;
    private NotificadorHaciaContenedorDeViewPager notificador;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        notificador = (NotificadorHaciaContenedorDeViewPager) context;
    }

    public static ViewPagerContenidoFragment fabricaDeFragments(Contenido contenido) {
        ViewPagerContenidoFragment viewPagerContenidoFragment = new ViewPagerContenidoFragment();

        Bundle bundle = new Bundle();

        bundle.putSerializable(CLAVE_OBJETO_CONTENIDO, contenido);

        viewPagerContenidoFragment.setArguments(bundle);

        return viewPagerContenidoFragment;


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_view_pager_contenido, container, false);

        cardViewContenedor = view.findViewById(R.id.cardViewContenedor_fragmentviewpagercontenido);
        imageViewBackground = view.findViewById(R.id.imageViewBackground_fragmentcontenidoviewpager);
        textViewTituloDelContenido = view.findViewById(R.id.textViewTituloDelContenido_fragmentcontenidoviewpage);
        scaleRatingBarPuntaje = view.findViewById(R.id.RatingBarPuntaje_fragmentviewpagercontenido);
        imageViewPoster = view.findViewById(R.id.imageViewPoster_fragmentcontenidoviewpager);
        imageViewPlay = view.findViewById(R.id.imageViewPlay_fragmentcontenidoviewpager);


        Bundle bundle = getArguments();

        final Contenido contenido = (Contenido) bundle.getSerializable(CLAVE_OBJETO_CONTENIDO);

        Helper.cargarImagenesBackground(getContext(), contenido.getImagenBackground(), imageViewBackground);

        textViewTituloDelContenido.setText(contenido.getTitulo());
        scaleRatingBarPuntaje.setRating((float) (contenido.getPuntaje().floatValue() / 2.0));


        Helper.cargarImagenesPoster(getContext(), contenido.getImagenPoster(), imageViewPoster);

        imageViewPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (contenido.getQueEs().equals(Helper.PELICULA)) {
                    notificador.notificarTrailerPelicula(contenido.getId());
                } else {
                    notificador.notificarTrailerSerie(contenido.getId());
                }
            }
        });

        cardViewContenedor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (contenido.getQueEs().equals(Helper.PELICULA)) {
                    notificador.notificarDescripcionPelicula(contenido.getId());
                } else {
                    notificador.notificarDescripcionSerie(contenido.getId());
                }
            }
        });
        return view;
    }

    public interface NotificadorHaciaContenedorDeViewPager {
        public void notificarTrailerPelicula(Integer id);

        public void notificarDescripcionPelicula(Integer id);

        public void notificarDescripcionSerie(Integer id);

        public void notificarTrailerSerie(Integer id);
    }
}
