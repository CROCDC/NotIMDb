package com.example.camilo_romero.notimdb.View.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.camilo_romero.notimdb.Model.TMDb.Actor;
import com.example.camilo_romero.notimdb.R;
import com.example.camilo_romero.notimdb.Utils.Helper;

import java.util.ArrayList;
import java.util.List;

public class ActoresAdapter extends RecyclerView.Adapter {
    private List<Actor> listaDeActores;
    private Context context;

    public ActoresAdapter() {
        listaDeActores = new ArrayList<>();
    }

    public void setListaDeActores(List<Actor> listaDeActores) {
        this.listaDeActores = listaDeActores;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();

        LayoutInflater layoutInflater = LayoutInflater.from(context);

        View viewDeLaCelda = layoutInflater.inflate(R.layout.celda_persona,parent,false);

        ActorViewHolder actorViewHolder = new ActorViewHolder(viewDeLaCelda);


        return actorViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Actor actor = listaDeActores.get(position);

        ActorViewHolder actorViewHolder = (ActorViewHolder) holder;

        actorViewHolder.cargarActor(actor);

    }

    @Override
    public int getItemCount() {
        return listaDeActores.size();
    }


    public class ActorViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageViewPosterDelActor;
        private TextView textViewNombreDelActor;
        private TextView textViewPapelDelActor;

        public ActorViewHolder(View itemView) {
            super(itemView);

            imageViewPosterDelActor = itemView.findViewById(R.id.imageViewPosterDeLaPersona_celdapersona);
            textViewNombreDelActor = itemView.findViewById(R.id.textViewNombreDeLaPersona_celdapersona);
            textViewPapelDelActor = itemView.findViewById(R.id.textViewRolDeLaPersona_celdapersona);
        }

        public void cargarActor(Actor actor){
            Helper.cargarImagenesPosterDeActor(context,actor.getProfile_path(),imageViewPosterDelActor);
            textViewNombreDelActor.setText(actor.getName());
            textViewPapelDelActor.setText(actor.getCharacter());
        }
    }
}
