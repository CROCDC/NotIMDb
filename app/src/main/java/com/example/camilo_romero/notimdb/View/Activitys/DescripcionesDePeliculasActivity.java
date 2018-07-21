package com.example.camilo_romero.notimdb.View.Activitys;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.camilo_romero.notimdb.Controller.ControlerPeliculasRetrofit;
import com.example.camilo_romero.notimdb.Model.Listados.ListadoDeTrailers;
import com.example.camilo_romero.notimdb.Model.TMDb.Gender;
import com.example.camilo_romero.notimdb.Model.TMDb.Pelicula;
import com.example.camilo_romero.notimdb.R;
import com.example.camilo_romero.notimdb.Utils.Helper;
import com.example.camilo_romero.notimdb.Utils.ResultListener;
import com.example.camilo_romero.notimdb.Utils.TMDBHelper;
import com.example.camilo_romero.notimdb.View.Fragments.Pelicula.ColeccionDeLaPeliculaFragment;
import com.example.camilo_romero.notimdb.View.Fragments.Pelicula.CreditosDeLaPeliculaFragment;
import com.example.camilo_romero.notimdb.View.Fragments.Pelicula.InformacionDeLaPeliculaFragment;
import com.example.camilo_romero.notimdb.View.Fragments.Pelicula.PeliculasSimilaresFragment;
import com.example.camilo_romero.notimdb.View.Fragments.Pelicula.ValorarLaPeliculaFragment;
import com.willy.ratingbar.ScaleRatingBar;

import de.hdodenhof.circleimageview.CircleImageView;

public class DescripcionesDePeliculasActivity extends AppCompatActivity implements
        InformacionDeLaPeliculaFragment.NotificadorHaciaDescripcionesDePeliculasActivity,
        ColeccionDeLaPeliculaFragment.NotificadorColleccionDeLaPeliculaFragment, PeliculasSimilaresFragment.NotificadorPeliculasSimilaresFragment {
    public static final String CLAVE_ID_PELICULA = "id";


    private ImageView imageViewBackgroundDeLaPelicula;
    private ImageView imageViewPlayTrailer;
    private CircleImageView circleImageViewPosterDeLaPelicula;
    private TextView textViewTituloDeLaPelicula;
    private ScaleRatingBar scaleRatingBarPuntaje;
    private TextView textViewFijoValorarLaPelicula;
    private TextView textViewArgumentoDeLaPelicula;

    private CardView cardViewContenedorDeLaInformacionDeLaPelicula;
    private CardView cardViewContenedorDeLaColeccion;
    private CardView cardViewContenedorDeLosCreditos;
    private CardView cardViewContenedorDeLasPeliculasSimilares;

    private Integer idPelicula;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descripciones_de_peliculas);

        Intent intent = getIntent();

        Bundle bundle = intent.getExtras();

        idPelicula = bundle.getInt(CLAVE_ID_PELICULA);

        imageViewBackgroundDeLaPelicula = findViewById(R.id.imageViewBackground_activitydescripcionesdepeliculas);
        imageViewPlayTrailer = findViewById(R.id.imageViewPlay_activitydescripcionesdepeliculas);
        circleImageViewPosterDeLaPelicula = findViewById(R.id.circleImageViewPosterDeLaPelicula_activivtydescripcionesdepeliculas);
        textViewTituloDeLaPelicula = findViewById(R.id.textViewTituloDeLaPelicula_activitydescripcionesdepeliculas);
        scaleRatingBarPuntaje = findViewById(R.id.RatingBarPuntaje_activitydescripcionesdepeliculas);
        textViewFijoValorarLaPelicula = findViewById(R.id.textViewFijoValorar_activitydescripcionesdepeliculas);
        textViewArgumentoDeLaPelicula = findViewById(R.id.textViewArgumentoDeLaPelicula_activitydescripcionesdepeliculas);

        new ControlerPeliculasRetrofit().pedirPeliculaPorid(TMDBHelper.IDIOMA_ESPAÑOL, idPelicula, new ResultListener<Pelicula>() {
            @Override
            public void finish(Pelicula resultado) {
                Helper.cargarImagenesBackground(getApplicationContext(), resultado.getBackdrop_path(), imageViewBackgroundDeLaPelicula);
                Helper.cargarImagenCircular(getApplicationContext(), resultado.getPoster_path(), circleImageViewPosterDeLaPelicula);
                textViewTituloDeLaPelicula.setText(resultado.getTitle());
                scaleRatingBarPuntaje.setRating(resultado.getVote_average().floatValue() / 2);
                textViewArgumentoDeLaPelicula.setText(resultado.getOverview());

            }
        });


        cardViewContenedorDeLaInformacionDeLaPelicula = findViewById(R.id.cardViewContenedorDeInformacionDeLaPeliculaFragment_activitydescripcionesdepeliculas);
        cardViewContenedorDeLaColeccion = findViewById(R.id.cardViewContendorDelCreador_activitydescripcionesdeseries);
        cardViewContenedorDeLosCreditos = findViewById(R.id.cardViewContenedorCreditosDeLaPelicula_activitydescripcionesdepeliculas);
        cardViewContenedorDeLasPeliculasSimilares = findViewById(R.id.cardViewContenedorDePeliculasSimilares_activitydescripcionesdepeliculas);

        InformacionDeLaPeliculaFragment informacionDeLaPeliculaFragment = InformacionDeLaPeliculaFragment.fabricaDefragmentinformaciondelapelicula(idPelicula);
        cargarFragment(informacionDeLaPeliculaFragment, R.id.cardViewContenedorDeInformacionDeLaPeliculaFragment_activitydescripcionesdepeliculas);

        ColeccionDeLaPeliculaFragment coleccionDeLaPeliculaFragment = ColeccionDeLaPeliculaFragment.fabricaFragmentsColeccionDeLaPelicula(idPelicula);
        cargarFragment(coleccionDeLaPeliculaFragment, R.id.cardViewContendorDelCreador_activitydescripcionesdeseries);

        CreditosDeLaPeliculaFragment creditosDeLaPeliculaFragment = CreditosDeLaPeliculaFragment.fabricaDeFragmentsCreditosDeLaPelicula(idPelicula);
        cargarFragment(creditosDeLaPeliculaFragment, R.id.cardViewContenedorCreditosDeLaPelicula_activitydescripcionesdepeliculas);

        PeliculasSimilaresFragment peliculasSimilaresFragment = PeliculasSimilaresFragment.fabricaDeFragmentsPeliculasSimilares(idPelicula);
        cargarFragment(peliculasSimilaresFragment, R.id.cardViewContenedorDePeliculasSimilares_activitydescripcionesdepeliculas);


        //metodos touch
        touchImageViewPlayTrailer();
        touchTextViewValorarLaPelicula();


    }

    public void cargarFragment(Fragment fragment, Integer posicion) {
        FragmentManager fragmentManager = getSupportFragmentManager();

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(posicion, fragment);
        fragmentTransaction.commit();

    }

    public void touchImageViewPlayTrailer() {

        new ControlerPeliculasRetrofit().pedirListaDeTrailerDeUnaPelicula(TMDBHelper.IDIOMA_ESPAÑOL, idPelicula, new ResultListener<ListadoDeTrailers>() {
            @Override
            public void finish(final ListadoDeTrailers resultado) {
                imageViewPlayTrailer.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(DescripcionesDePeliculasActivity.this, TrailersActivity.class);

                        Bundle bundle = new Bundle();

                        bundle.putSerializable(TrailersActivity.LISTA_DE_TRAILERS, new ListadoDeTrailers(resultado.getResults()));

                        intent.putExtras(bundle);

                        startActivity(intent);
                    }
                });
            }
        });


    }

    public void touchTextViewValorarLaPelicula() {
        textViewFijoValorarLaPelicula.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ValorarLaPeliculaFragment valorarLaPeliculaFragment = ValorarLaPeliculaFragment.fabricaDeFragmentValorarLaPelicula(idPelicula);

                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.add(R.id.frameLayoutContenedor_activitydescripcionesdepeliculas, valorarLaPeliculaFragment).addToBackStack("camilo");
                fragmentTransaction.commit();
            }
        });
    }

    @Override
    public void notificarPeliculasPorCategoria(Gender gender) {
       irHaciaPeliculasPorCategoriaActivity(gender);
    }

    @Override
    public void notificar(Pelicula pelicula) {
        Intent intent = new Intent(DescripcionesDePeliculasActivity.this, DescripcionesDePeliculasActivity.class);

        Bundle bundle = new Bundle();

        bundle.putSerializable(DescripcionesDePeliculasActivity.CLAVE_ID_PELICULA, pelicula.getId());

        intent.putExtras(bundle);

        startActivity(intent);

    }

    @Override
    public void notificarColecionNula() {
        cardViewContenedorDeLaColeccion.setVisibility(View.GONE);
    }

    public void irHaciaPeliculasPorCategoriaActivity(Gender gender){
        Intent intent = new Intent(DescripcionesDePeliculasActivity.this, PeliculasPorCategoriaActivity.class);

        Bundle bundle = new Bundle();

        bundle.putSerializable(PeliculasPorCategoriaActivity.CLAVE_OBJETO_CATEGORIA, gender);

        intent.putExtras(bundle);

        startActivity(intent);
    }
}
