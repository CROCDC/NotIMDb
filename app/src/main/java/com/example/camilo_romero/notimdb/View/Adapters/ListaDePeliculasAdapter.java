package com.example.camilo_romero.notimdb.View.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.camilo_romero.notimdb.Model.TMDb.Pelicula;
import com.example.camilo_romero.notimdb.R;
import com.example.camilo_romero.notimdb.Utils.Helper;
import com.willy.ratingbar.ScaleRatingBar;

import java.util.ArrayList;
import java.util.List;

public class ListaDePeliculasAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<Pelicula> listaDePeliculas;

    private NotificadorListaDePeliculasAdapter notificador;

    public ListaDePeliculasAdapter(NotificadorListaDePeliculasAdapter notificador) {
        this.notificador = notificador;
        listaDePeliculas = new ArrayList<>();
    }

    public void setListaDePeliculas(List<Pelicula> listaDePeliculas) {
        this.listaDePeliculas = listaDePeliculas;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();

        LayoutInflater layoutInflater = LayoutInflater.from(context);

        View viewDeLaCelda = layoutInflater.inflate(R.layout.celda_contenido, parent, false);

        ContenidoViewHolder contenidoViewHolder = new ContenidoViewHolder(viewDeLaCelda);

        return contenidoViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Pelicula pelicula = listaDePeliculas.get(position);

        ContenidoViewHolder contenidoViewHolder = (ContenidoViewHolder) holder;

        contenidoViewHolder.cargarContenido(pelicula);

    }

    @Override
    public int getItemCount() {
        return listaDePeliculas.size();
    }

    public class ContenidoViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageViewPosterDelContenido;
        private TextView textViewTituloDelContenido;
        private ScaleRatingBar scaleRatingBarPuntaje;

        public ContenidoViewHolder(View itemView) {
            super(itemView);

            imageViewPosterDelContenido = itemView.findViewById(R.id.imageViewPosterDeLaContenido_celdacontenido);
            textViewTituloDelContenido = itemView.findViewById(R.id.textViewTituloDelContenido_celdacontenido);
            scaleRatingBarPuntaje = itemView.findViewById(R.id.RatingBarPuntajeDelContenido_celdacontenido);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    notificador.notificar(listaDePeliculas.get(getAdapterPosition()));

                }
            });

        }

        public void cargarContenido(Pelicula pelicula) {
            Helper.cargarImagenesPoster(context, pelicula.getPoster_path(), imageViewPosterDelContenido);

            textViewTituloDelContenido.setText(pelicula.getTitle());
            scaleRatingBarPuntaje.setRating(pelicula.getVote_average().floatValue() / 2);
        }
    }

    public interface NotificadorListaDePeliculasAdapter {
        public void notificar(Pelicula pelicula);
    }
}
