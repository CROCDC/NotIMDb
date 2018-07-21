package com.example.camilo_romero.notimdb.View.Activitys;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.camilo_romero.notimdb.Controller.ControlerSeriesRetrofit;
import com.example.camilo_romero.notimdb.Model.Listados.ListadoDeTrailers;
import com.example.camilo_romero.notimdb.Model.TMDb.Gender;
import com.example.camilo_romero.notimdb.Model.TMDb.Serie;
import com.example.camilo_romero.notimdb.R;
import com.example.camilo_romero.notimdb.Utils.Helper;
import com.example.camilo_romero.notimdb.Utils.ResultListener;
import com.example.camilo_romero.notimdb.Utils.TMDBHelper;
import com.example.camilo_romero.notimdb.View.Adapters.ViewPagerAdapterPorListaDeFragments;
import com.example.camilo_romero.notimdb.View.Fragments.Serie.ContenedorInformacionDeLaSerieFragment;
import com.example.camilo_romero.notimdb.View.Fragments.Serie.CreadorDeLaSerieFragment;
import com.example.camilo_romero.notimdb.View.Fragments.Serie.InformacionDeLaSerieFragment;
import com.willy.ratingbar.ScaleRatingBar;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class DescripcionesDeSeriesActivity extends MainActivity implements InformacionDeLaSerieFragment.NotificadorHaciaDescripcionesDeSeriesActivity {

    public static final String CLAVE_ID_SERIE = "id";


    private ImageView imageViewBackgroundDeLaSerie;
    private ImageView imageViewPlayTrailer;
    private CircleImageView circleImageViewDeLaSerie;
    private TextView textViewTituloDeLaSerie;
    private ScaleRatingBar scaleRatingBarPuntaje;
    private TextView textViewFijoValorarLaSerie;

    private TabLayout tabLayoutMenuSeries;
    private ViewPager viewPagerNavegacionSeries;

    private Integer idSerie;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descripciones_de_series);

        Intent intent = getIntent();

        Bundle bundle = intent.getExtras();

        idSerie = bundle.getInt(CLAVE_ID_SERIE);

        imageViewBackgroundDeLaSerie = findViewById(R.id.imageViewBackground_activitydescripcionesdeseries);
        imageViewPlayTrailer = findViewById(R.id.imageViewPlay_activitydescripcionesdeseries);
        circleImageViewDeLaSerie = findViewById(R.id.circleImageViewPosterDeLaSerie_activivtydescripcionesdeseries);
        textViewTituloDeLaSerie = findViewById(R.id.textViewTituloDeLaSerie_activitydescripcionesdeseries);
        scaleRatingBarPuntaje = findViewById(R.id.RatingBarPuntaje_activitydescripcionesdeseries);
        textViewFijoValorarLaSerie = findViewById(R.id.textViewFijoValorar_activitydescripcionesdeseries);

        tabLayoutMenuSeries = findViewById(R.id.tablLayoutMenuSeries_activitydescipcionesdeseries);
        viewPagerNavegacionSeries = findViewById(R.id.viewPagerNavegacionSeries_activitydescripcionesdeseries);


        new ControlerSeriesRetrofit().pedirSeriePorid(idSerie, TMDBHelper.IDIOMA_ESPAÑOL, new ResultListener<Serie>() {
            @Override
            public void finish(Serie resultado) {
                Helper.cargarImagenesBackground(getApplicationContext(), resultado.getBackdrop_path(), imageViewBackgroundDeLaSerie);
                Helper.cargarImagenCircular(getApplicationContext(), resultado.getPoster_path(), circleImageViewDeLaSerie);

                textViewTituloDeLaSerie.setText(resultado.getName());
                scaleRatingBarPuntaje.setRating(resultado.getVote_average().floatValue() / 2);

            }
        });

        ViewPagerAdapterPorListaDeFragments viewPagerAdapterPorListaDeFragments = new ViewPagerAdapterPorListaDeFragments(getSupportFragmentManager());

        viewPagerAdapterPorListaDeFragments.addFragment(ContenedorInformacionDeLaSerieFragment.frabricaDeFragmentsContenedoInformacionDeLaSerie(idSerie),"INFORMACION");

        tabLayoutMenuSeries.setupWithViewPager(viewPagerNavegacionSeries);

        viewPagerNavegacionSeries.setAdapter(viewPagerAdapterPorListaDeFragments);


        touchImageViewPlayTrailer();
    }


    public void touchImageViewPlayTrailer() {

        new ControlerSeriesRetrofit().pedirListaDeTrailerDeUnaSerie(idSerie, TMDBHelper.IDIOMA_INGLES, new ResultListener<ListadoDeTrailers>() {
            @Override
            public void finish(final ListadoDeTrailers resultado) {
                imageViewPlayTrailer.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(DescripcionesDeSeriesActivity.this, TrailersActivity.class);

                        Bundle bundle = new Bundle();

                        bundle.putSerializable(TrailersActivity.LISTA_DE_TRAILERS, new ListadoDeTrailers(resultado.getResults()));

                        intent.putExtras(bundle);

                        startActivity(intent);
                    }
                });
            }
        });

    }

    @Override
    public void notificarSeriesPorCategoriaActiivty(Gender gender) {

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
