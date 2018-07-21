package com.example.camilo_romero.notimdb.View.Activitys;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.camilo_romero.notimdb.Model.Listados.ListadoDeTrailers;
import com.example.camilo_romero.notimdb.Model.TMDb.Trailer;
import com.example.camilo_romero.notimdb.R;
import com.example.camilo_romero.notimdb.Utils.Helper;
import com.example.camilo_romero.notimdb.View.Adapters.ViewPagerAdapterPorListaDeFragments;
import com.example.camilo_romero.notimdb.View.Fragments.DescripcionesTrailersFragment;
import com.example.camilo_romero.notimdb.View.Fragments.ListaDeTrailersFragment;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;

import java.util.List;

public class TrailersActivity extends AppCompatActivity implements ListaDeTrailersFragment.NotificadorHaciaTrailersDePeliculasActivity {

    public static final String LISTA_DE_TRAILERS = "trailers";
    public static final String CLAVE_ID = " id";
    public static final String CLAVE_QUE_ES = "que es ";


    private ViewPager viewPager;
    private TabLayout tabLayout;
    private ViewPagerAdapterPorListaDeFragments viewPagerAdapterPorListaDeFragments;

    private YouTubePlayerSupportFragment youTubePlayerFragment;
    private YouTubePlayer youTubePlayer;

    private List<Trailer> listaDeTrailers;
    private Integer id;
    private Boolean queEs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trailers_de_pelicula);

        Intent intent = getIntent();

        Bundle bundle = intent.getExtras();

        ListadoDeTrailers listadoDeTrailers = (ListadoDeTrailers) bundle.getSerializable(LISTA_DE_TRAILERS);

        listaDeTrailers = listadoDeTrailers.getResults();

        id = bundle.getInt(CLAVE_ID);
        queEs = bundle.getBoolean(CLAVE_QUE_ES);


        viewPager = findViewById(R.id.viewPagerTrailerDePeliculas_activitytrailersdepeliculas);
        tabLayout = findViewById(R.id.tabLayoutTrailerPeliculas_activitytrailersdepeliculas);

        viewPagerAdapterPorListaDeFragments = new ViewPagerAdapterPorListaDeFragments(getSupportFragmentManager());

        DescripcionesTrailersFragment descripcionesTrailersFragment = new DescripcionesTrailersFragment();

        ListaDeTrailersFragment listaDeTrailersFragment = new ListaDeTrailersFragment();



        agregarFragmentDescripcionTrailer(descripcionesTrailersFragment);
        agregarFragmentListaDeTrailers(listaDeTrailersFragment);


        viewPager.setAdapter(viewPagerAdapterPorListaDeFragments);

        tabLayout.setupWithViewPager(viewPager);

        initializeYoutubePlayer();


    }

    private void initializeYoutubePlayer() {

        youTubePlayerFragment = (YouTubePlayerSupportFragment) getSupportFragmentManager()
                .findFragmentById(R.id.youtube_player_fragment);

        if (youTubePlayerFragment == null)
            return;

        youTubePlayerFragment.initialize(Helper.CLAVE_API_YOUTUBE, new YouTubePlayer.OnInitializedListener() {

            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player,
                                                boolean wasRestored) {
                if (!wasRestored) {
                    youTubePlayer = player;

                    //set the player style default
                    youTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);

                    youTubePlayer.cueVideo(listaDeTrailers.get(0).getKey());


                }
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider arg0, YouTubeInitializationResult arg1) {

            }
        });
    }

    public void agregarFragmentConArgumento(String CLAVE, Fragment fragment, String titulo) {

        Bundle bundle = new Bundle();

        bundle.putInt(CLAVE, id);

        fragment.setArguments(bundle);

        viewPagerAdapterPorListaDeFragments.addFragment(fragment, titulo);

    }

    public void agregarFragmentDescripcionTrailer(Fragment fragment){

        Bundle bundle = new Bundle();

        bundle.putSerializable(DescripcionesTrailersFragment.CLAVE_LISTA_DE_TRAILERS, new ListadoDeTrailers(listaDeTrailers));
        bundle.putSerializable(DescripcionesTrailersFragment.CLAVE_QUE_ES,queEs);
        bundle.putSerializable(DescripcionesTrailersFragment.CLAVE_ID, id);

        fragment.setArguments(bundle);

        viewPagerAdapterPorListaDeFragments.addFragment(fragment, "INFORMACION");
    }

    public void agregarFragmentListaDeTrailers(Fragment fragment){

        Bundle bundle = new Bundle();

        bundle.putSerializable(ListaDeTrailersFragment.CLAVE_LISTA_DE_TRAILERS,new ListadoDeTrailers(listaDeTrailers));

        fragment.setArguments(bundle);

        viewPagerAdapterPorListaDeFragments.addFragment(fragment,"TRAILERS");
    }




    @Override
    public void notificar(Trailer trailer) {
        youTubePlayer.cueVideo(trailer.getKey());

    }


}
