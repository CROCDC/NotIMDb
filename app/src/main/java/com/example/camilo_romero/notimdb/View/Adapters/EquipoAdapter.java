package com.example.camilo_romero.notimdb.View.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.camilo_romero.notimdb.Model.TMDb.Equipo;
import com.example.camilo_romero.notimdb.R;
import com.example.camilo_romero.notimdb.Utils.Helper;

import java.util.ArrayList;
import java.util.List;

public class EquipoAdapter extends RecyclerView.Adapter {
    private List<Equipo> listaDelEquipo;
    private Context context;

    public EquipoAdapter(){
        listaDelEquipo = new ArrayList<>();
    }

    public void setListaDelEquipo(List<Equipo> listaDelEquipo) {
        this.listaDelEquipo = listaDelEquipo;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();

        LayoutInflater layoutInflater = LayoutInflater.from(context);

        View ViewDeLaCelda = layoutInflater.inflate(R.layout.celda_persona,parent,false);

        EquipoViewHolder equipoViewHolder = new EquipoViewHolder(ViewDeLaCelda);

        return equipoViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Equipo equipo = listaDelEquipo.get(position);

        EquipoViewHolder equipoViewHolder = (EquipoViewHolder) holder;

        equipoViewHolder.cargarEquipo(equipo);
    }

    @Override
    public int getItemCount() {
        return listaDelEquipo.size();
    }

    public class EquipoViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageViewPosterDelEquipo;
        private TextView textViewNombreDelEquipo;
        private TextView textViewRolDelEquipo;

        public EquipoViewHolder(View itemView) {
            super(itemView);

            imageViewPosterDelEquipo = itemView.findViewById(R.id.imageViewPosterDeLaPersona_celdapersona);
            textViewNombreDelEquipo = itemView.findViewById(R.id.textViewNombreDeLaPersona_celdapersona);
            textViewRolDelEquipo = itemView.findViewById(R.id.textViewRolDeLaPersona_celdapersona);
        }

        public void cargarEquipo(Equipo equipo){
            Helper.cargarImagenesPoster(context,equipo.getProfile_path(),imageViewPosterDelEquipo);

            textViewNombreDelEquipo.setText(equipo.getName());
            textViewRolDelEquipo.setText(equipo.getJob());
        }
    }
}
