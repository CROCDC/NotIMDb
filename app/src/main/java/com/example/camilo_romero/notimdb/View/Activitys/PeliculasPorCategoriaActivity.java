package com.example.camilo_romero.notimdb.View.Activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.camilo_romero.notimdb.Controller.ControlerPeliculasRetrofit;
import com.example.camilo_romero.notimdb.Model.TMDb.Gender;
import com.example.camilo_romero.notimdb.Model.Listados.ListadoDePeliculas;
import com.example.camilo_romero.notimdb.Model.TMDb.Pelicula;
import com.example.camilo_romero.notimdb.R;
import com.example.camilo_romero.notimdb.Utils.ResultListener;
import com.example.camilo_romero.notimdb.Utils.TMDBHelper;
import com.example.camilo_romero.notimdb.View.Adapters.ListaDePeliculasAdapter;

public class PeliculasPorCategoriaActivity extends AppCompatActivity {

    public static final String CLAVE_OBJETO_CATEGORIA ="categoria";

    private Gender gender;

    private TextView textViewTituloDeLaCategoria;
    private RecyclerView recyclerViewListaDePeliculas;

    private ListaDePeliculasAdapter listaDePeliculasAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_peliculas_por_categoria);

        listaDePeliculasAdapter = new ListaDePeliculasAdapter(new ListaDePeliculasAdapter.NotificadorListaDePeliculasAdapter() {
            @Override
            public void notificar(Pelicula pelicula) {
                Intent intent = new Intent(PeliculasPorCategoriaActivity.this, DescripcionesDePeliculasActivity.class);

                Bundle bundle = new Bundle();

                bundle.putInt(DescripcionesDePeliculasActivity.CLAVE_ID_PELICULA,pelicula.getId());

                intent.putExtras(bundle);

                startActivity(intent);

            }
        });

        textViewTituloDeLaCategoria = findViewById(R.id.textViewTituloDeLaCategoria_activitypeliculasporcategoria);
        recyclerViewListaDePeliculas = findViewById(R.id.recyclerViewListaDePeliculasPorGenero_activitypeliculasporcategoria);



        Intent intent = getIntent();

        Bundle bundle = intent.getExtras();

        gender = (Gender) bundle.getSerializable(CLAVE_OBJETO_CATEGORIA);

        textViewTituloDeLaCategoria.setText(gender.getName());

        new ControlerPeliculasRetrofit().pedirListaDePeliculasPorGenero(gender.getId(), TMDBHelper.IDIOMA_ESPAÑOL, 1, new ResultListener<ListadoDePeliculas>() {
            @Override
            public void finish(ListadoDePeliculas resultado) {
                listaDePeliculasAdapter.setListaDePeliculas(resultado.getResults());
            }
        });

        recyclerViewListaDePeliculas.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
        recyclerViewListaDePeliculas.setAdapter(listaDePeliculasAdapter);
    }
}
