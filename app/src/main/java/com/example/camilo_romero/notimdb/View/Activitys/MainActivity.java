package com.example.camilo_romero.notimdb.View.Activitys;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import com.example.camilo_romero.notimdb.Controller.ControlerSeriesRetrofit;
import com.example.camilo_romero.notimdb.DAO.DAOPeliculasRetrofit;
import com.example.camilo_romero.notimdb.Model.Listados.ListadoDeTrailers;
import com.example.camilo_romero.notimdb.Model.TMDb.Usuario;
import com.example.camilo_romero.notimdb.R;
import com.example.camilo_romero.notimdb.Utils.ResultListener;
import com.example.camilo_romero.notimdb.Utils.TMDBHelper;
import com.example.camilo_romero.notimdb.View.Adapters.ViewPagerAdapterPorListaDeFragments;
import com.example.camilo_romero.notimdb.View.Fragments.InicioFragment;
import com.example.camilo_romero.notimdb.View.Fragments.PeliculasFragment;
import com.example.camilo_romero.notimdb.View.Fragments.ParaViewPager.ViewPagerContenidoFragment;

import java.util.List;

public class MainActivity extends AppCompatActivity implements ViewPagerContenidoFragment.NotificadorHaciaContenedorDeViewPager {

    private TabLayout tabLayoutPrincipal;
    private ViewPager viewPagerNavegacionPrincipal;
    private ViewPagerAdapterPorListaDeFragments viewPagerAdapterPorListaDeFragments;

    private Integer posicionAnterior;
    private Integer posicionActual;

    private Usuario usuario;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayoutPrincipal = findViewById(R.id.tabLayoutPrincipal_activitymain);
        viewPagerNavegacionPrincipal = findViewById(R.id.viewPager_activitymain);

        InicioFragment inicioFragment = new InicioFragment();
        PeliculasFragment peliculasFragment = new PeliculasFragment();

        viewPagerAdapterPorListaDeFragments = new ViewPagerAdapterPorListaDeFragments(getSupportFragmentManager());

        viewPagerAdapterPorListaDeFragments.addFragment(inicioFragment, "INICIO");
        viewPagerAdapterPorListaDeFragments.addFragment(peliculasFragment, "PELICULAS");

        viewPagerNavegacionPrincipal.setAdapter(viewPagerAdapterPorListaDeFragments);

        tabLayoutPrincipal.setupWithViewPager(viewPagerNavegacionPrincipal);

        tabLayoutPrincipal.setTabMode(TabLayout.MODE_SCROLLABLE);

        //Con este metodo obtengo la posicion anterior al scroll y la actual
        informacionScrollViewPager();


    }

    @Override
    public void onBackPressed() {
       try{
           if (posicionActual == 0) {
               super.onBackPressed();
           } else {
               viewPagerNavegacionPrincipal.setCurrentItem(posicionAnterior);
           }
       }catch (Exception e){
           super.onBackPressed();
       }

    }

    public void informacionScrollViewPager() {

        viewPagerNavegacionPrincipal.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                posicionAnterior = viewPagerNavegacionPrincipal.getCurrentItem();
                posicionActual = position;

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public void cargarFragments(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.contenedorLinearLayout_activitymain, fragment);
        fragmentTransaction.commit();
    }

    public void cargarFragmentPorLugar(Fragment fragment , Integer id){
        FragmentManager fragmentManager = getSupportFragmentManager();

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(id,fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void notificarTrailerPelicula(final Integer id) {

        new DAOPeliculasRetrofit().pedirListaDeTrailersDeUnaPelicula(TMDBHelper.IDIOMA_ESPAÑOL, id, new ResultListener<ListadoDeTrailers>() {
            @Override
            public void finish(ListadoDeTrailers resultado) {
                Intent intent = new Intent(MainActivity.this, TrailersActivity.class);

                Bundle bundle = new Bundle();
                bundle.putSerializable(TrailersActivity.LISTA_DE_TRAILERS, new ListadoDeTrailers(resultado.getResults()));
                bundle.putInt(TrailersActivity.CLAVE_ID, id);
                bundle.putBoolean(TrailersActivity.CLAVE_QUE_ES, true);
                intent.putExtras(bundle);

                startActivity(intent);
            }
        });


    }

    @Override
    public void notificarTrailerSerie(final Integer id) {
        new ControlerSeriesRetrofit().pedirListaDeTrailerDeUnaSerie(id, TMDBHelper.IDIOMA_INGLES, new ResultListener<ListadoDeTrailers>() {
            @Override
            public void finish(ListadoDeTrailers resultado) {
                Intent intent = new Intent(MainActivity.this, TrailersActivity.class);

                Bundle bundle = new Bundle();
                bundle.putSerializable(TrailersActivity.LISTA_DE_TRAILERS, new ListadoDeTrailers(resultado.getResults()));
                bundle.putInt(TrailersActivity.CLAVE_ID, id);
                bundle.putBoolean(TrailersActivity.CLAVE_QUE_ES, false);
                intent.putExtras(bundle);

                startActivity(intent);
            }
        });

    }



    @Override
    public void notificarDescripcionPelicula(Integer id) {
        Intent intent = new Intent(MainActivity.this, DescripcionesDePeliculasActivity.class);

        Bundle bundle = new Bundle();
        bundle.putInt(DescripcionesDePeliculasActivity.CLAVE_ID_PELICULA, id);

        intent.putExtras(bundle);

        startActivity(intent);
    }

    @Override
    public void notificarDescripcionSerie(Integer id) {
        Intent intent = new Intent(MainActivity.this, DescripcionesDeSeriesActivity.class);

        Bundle bundle = new Bundle();
        bundle.putInt(DescripcionesDeSeriesActivity.CLAVE_ID_SERIE, id);

        intent.putExtras(bundle);

        startActivity(intent);
    }


}
