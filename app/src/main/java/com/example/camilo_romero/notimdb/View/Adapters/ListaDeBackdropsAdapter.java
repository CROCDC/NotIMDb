package com.example.camilo_romero.notimdb.View.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.camilo_romero.notimdb.Model.TMDb.Backdrop;
import com.example.camilo_romero.notimdb.R;
import com.example.camilo_romero.notimdb.Utils.TMDBHelper;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ListaDeBackdropsAdapter extends RecyclerView.Adapter {
    private List<Backdrop> listaDeBackdrops;
    private Context context;

    public void setListaDeBackdrops(List<Backdrop> listaDeBackdrops) {
        this.listaDeBackdrops = listaDeBackdrops;
        notifyDataSetChanged();
    }

    public ListaDeBackdropsAdapter() {
        listaDeBackdrops = new ArrayList<>();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();

        LayoutInflater layoutInflater = LayoutInflater.from(context);

        View viewDeLaCelda = layoutInflater.inflate(R.layout.celda_backdrop,parent,false);

        BackdropViewHolder backdropViewHolder = new BackdropViewHolder(viewDeLaCelda);

        return backdropViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Backdrop backdrop = listaDeBackdrops.get(position);

        BackdropViewHolder backdropViewHolder = (BackdropViewHolder) holder;

        backdropViewHolder.cargarBackdrop(backdrop);

    }

    @Override
    public int getItemCount() {
        return listaDeBackdrops.size();
    }


    public class BackdropViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewBackdrop;
        public BackdropViewHolder(View itemView) {
            super(itemView);

            imageViewBackdrop = itemView.findViewById(R.id.imageViewBackdrop_celdabackdrop);
        }

        public void cargarBackdrop(Backdrop backdrop){
            Picasso.with(context)
                    .load(TMDBHelper.baseURLImagenes + TMDBHelper.TAMANO_IMAGEN_ORIGINAL + backdrop.getFile_path())
                    .resize(520,300)
                    .into(imageViewBackdrop);

        }
    }
}
