package com.example.camilo_romero.notimdb.View.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.camilo_romero.notimdb.Model.TMDb.Trailer;
import com.example.camilo_romero.notimdb.R;
import com.example.camilo_romero.notimdb.Utils.Helper;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ListaDeTrailersDeYoutubeAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<Trailer> listaDeTrailers;
    private NotificadorHaciaInstanciadorDelAdapter notificador;

    public ListaDeTrailersDeYoutubeAdapter(NotificadorHaciaInstanciadorDelAdapter notificador) {
        this.notificador = notificador;
        listaDeTrailers = new ArrayList<>();
    }

    public void setListaDeTrailers(List<Trailer> listaDeTrailers) {
        this.listaDeTrailers = listaDeTrailers;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        View viewDeLaCelda = layoutInflater.inflate(R.layout.celda_video_de_youtube,parent,false);

        TrailersViewHolder trailersViewHolder = new TrailersViewHolder(viewDeLaCelda);

        return trailersViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Trailer trailer = listaDeTrailers.get(position);

        TrailersViewHolder trailersViewHolder = (TrailersViewHolder) holder;

        trailersViewHolder.cargarTrailer(trailer);
    }

    @Override
    public int getItemCount() {
        return listaDeTrailers.size();
    }

    private class TrailersViewHolder extends RecyclerView.ViewHolder{
        private TextView textViewTituloDelTrailer;
        private ImageView imageViewThumbailDelTrailer;
        public TrailersViewHolder(View itemView) {
            super(itemView);
            textViewTituloDelTrailer = itemView.findViewById(R.id.textviewTituloDelTrailer_celdavideodeyoutube);
            imageViewThumbailDelTrailer = itemView.findViewById(R.id.imageViewThumbailDelTrailer_celdavideodeyoutube);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    notificador.notificar(listaDeTrailers.get(getAdapterPosition()));
                }
            });
        }

        public void cargarTrailer(Trailer trailer){

            textViewTituloDelTrailer.setText(trailer.getName());

            Picasso.with(context)
                    .load(Helper.URL_YOUTUBE_PARTE1 + trailer.getKey() + Helper.URL_YOUTUBE_PARTE2)
                    .into(imageViewThumbailDelTrailer);

        }
    }

    public interface NotificadorHaciaInstanciadorDelAdapter{
        public void notificar(Trailer trailer);
    }
}
