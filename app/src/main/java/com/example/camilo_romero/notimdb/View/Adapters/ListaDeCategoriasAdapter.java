package com.example.camilo_romero.notimdb.View.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.camilo_romero.notimdb.Model.TMDb.Gender;
import com.example.camilo_romero.notimdb.R;

import java.util.ArrayList;
import java.util.List;

public class ListaDeCategoriasAdapter extends RecyclerView.Adapter {
    private List<Gender> listaDeGeneros;
    private Context context;
    private NotificadorHaciaFragmentInformacionDeLaPelicula notificador;

    public ListaDeCategoriasAdapter(NotificadorHaciaFragmentInformacionDeLaPelicula notificador) {
        listaDeGeneros = new ArrayList<>();
        this.notificador = notificador;
    }

    public void setListaDeGeneros(List<Gender> listaDeGeneros) {
        this.listaDeGeneros = listaDeGeneros;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();

        LayoutInflater layoutInflater = LayoutInflater.from(context);

        View viewDeLaCelda = layoutInflater.inflate(R.layout.celda_categoria, parent, false);

        CategoriasViewHolder categoriasViewHolder = new CategoriasViewHolder(viewDeLaCelda);

        return categoriasViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Gender gender = listaDeGeneros.get(position);

        CategoriasViewHolder categoriasViewHolder = (CategoriasViewHolder) holder;

        categoriasViewHolder.cargarCategoria(gender);

    }

    @Override
    public int getItemCount() {
        return listaDeGeneros.size();
    }

    public class CategoriasViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewTituloDeLaCategoria;

        public CategoriasViewHolder(View itemView) {
            super(itemView);
            textViewTituloDeLaCategoria = itemView.findViewById(R.id.textViewTituloDeLaCategoria_celdacategoria);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    notificador.notificar(listaDeGeneros.get(getAdapterPosition()));
                }
            });
        }

        public void cargarCategoria(Gender gender) {

            textViewTituloDeLaCategoria.setText(gender.getName());

        }
    }

    public interface NotificadorHaciaFragmentInformacionDeLaPelicula{
        public void notificar(Gender gender);
    }
}
