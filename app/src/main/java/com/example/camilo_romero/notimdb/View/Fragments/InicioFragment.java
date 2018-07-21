package com.example.camilo_romero.notimdb.View.Fragments;


import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.example.camilo_romero.notimdb.Controller.ControlerNoticiasRetrofit;
import com.example.camilo_romero.notimdb.Controller.ControlerContenidoRetrofit;
import com.example.camilo_romero.notimdb.Model.TMDb.Contenido;
import com.example.camilo_romero.notimdb.Model.wp_json.Noticia;
import com.example.camilo_romero.notimdb.R;
import com.example.camilo_romero.notimdb.Utils.Helper;
import com.example.camilo_romero.notimdb.Utils.ResultListener;
import com.example.camilo_romero.notimdb.Utils.TMDBHelper;
import com.example.camilo_romero.notimdb.View.Adapters.ListaDeNoticiasAdapter;
import com.example.camilo_romero.notimdb.View.Adapters.ViewPagerContenidoAdapter;

import java.util.List;

public class InicioFragment extends Fragment {

    private ViewPager viewPagerPrincipalListaDeContenido;
    private ViewPagerContenidoAdapter viewPagerContenidoAdapter;
    private NotificadorHaciaMainActivity notificador;

    private RecyclerView recyclerViewListaDeNoticias;

    private ListaDeNoticiasAdapter listaDeNoticiasAdapter;

    private Integer delay = 4000;
    private int page = 1;

    private Handler handler;
    private Runnable runnable;


    public InicioFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        //notificador =  (NotificadorHaciaMainActivity) context;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        listaDeNoticiasAdapter = new ListaDeNoticiasAdapter();

        new ControlerContenidoRetrofit().pedirListaDeContenidoPopular(TMDBHelper.IDIOMA_ESPAÑOL, 1, new ResultListener<List<Contenido>>() {
            @Override
            public void finish(List<Contenido> resultado) {
                /*
                este es un parche por ahora
                ya que no puedo setearle la lista al adpater y hacerle un notifyDataSetChange()
                 */
                viewPagerContenidoAdapter = new ViewPagerContenidoAdapter(getChildFragmentManager(), resultado);
                viewPagerPrincipalListaDeContenido.setAdapter(viewPagerContenidoAdapter);


                //metodo que tiene la responsabilidad de hacer el auto scroll
                //esta puesto aca para que haga el scroll cuando ya esta el pedido

            }
        });

        new ControlerNoticiasRetrofit().pedirListaDeNoticias(Helper.URL_BASE_CINEPREMIERE, 9, 1, new ResultListener<List<Noticia>>() {
            @Override
            public void finish(List<Noticia> resultado) {
                listaDeNoticiasAdapter.setListaDeNoticias(resultado);
            }
        });

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_inicio, container, false);

        recyclerViewListaDeNoticias = view.findViewById(R.id.recyclerViewListaDeNoticias_fragmentinicio);

        recyclerViewListaDeNoticias.setAdapter(listaDeNoticiasAdapter);

        recyclerViewListaDeNoticias.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));



        handler = new Handler();


        viewPagerPrincipalListaDeContenido = view.findViewById(R.id.viewPager_fragmentinicio);


        autoScrollViewPager();
        //metodo que tiene la responsabilidad de parar el auto sroll cuando el usuario toca el view pager
        onTouchViewPager();

        return view;






    }

    @Override
    public void onResume() {
        super.onResume();
        handler.postDelayed(runnable, 4000);

    }

    @Override
    public void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);
    }


    @SuppressLint("ClickableViewAccessibility")
    private void onTouchViewPager() {
        viewPagerPrincipalListaDeContenido.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                handler.removeCallbacks(runnable);
                handler.postDelayed(runnable, 7000);
                return false;
            }
        });
    }


    private void autoScrollViewPager() {
        /*
        el Runnable se encarga de hacer tiempo y setear la pagina
         */
        runnable = new Runnable() {
            public void run() {
                if (40 < page) {
                    page = 0;
                } else {
                    page++;
                }
                viewPagerPrincipalListaDeContenido.setCurrentItem(page, true);
                handler.postDelayed(this, delay);
            }
        };

        /*
        el OnPageChangeListener se encarga de informar la nueva posicion luego del auto scroll para que el handle pueda volver a
        hacer de nuevo el scroll con la posicion actualizada
         */


        viewPagerPrincipalListaDeContenido.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                page = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    public interface NotificadorHaciaMainActivity {
        public void notificar(Integer id);

    }
}



