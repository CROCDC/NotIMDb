package com.example.camilo_romero.notimdb.View.Adapters;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.camilo_romero.notimdb.Model.wp_json.Noticia;
import com.example.camilo_romero.notimdb.R;
import com.example.camilo_romero.notimdb.Utils.Helper;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ListaDeNoticiasAdapter extends RecyclerView.Adapter {
    private List<Noticia> listaDeNoticias;
    private Context context;

    public ListaDeNoticiasAdapter() {
        listaDeNoticias = new ArrayList<>();
    }

    public void setListaDeNoticias(List<Noticia> listaDeNoticias) {
        this.listaDeNoticias = listaDeNoticias;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();

        LayoutInflater layoutInflater = LayoutInflater.from(context);

        View viewDeLaCelda = layoutInflater.inflate(R.layout.celda_noticia,parent,false);

        NoticiaViewHolder noticiaViewHolder = new NoticiaViewHolder(viewDeLaCelda);

        return noticiaViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Noticia noticia = listaDeNoticias.get(position);

        NoticiaViewHolder noticiaViewHolder = (NoticiaViewHolder) holder;

        noticiaViewHolder.cargarNoticia(noticia);
    }

    @Override
    public int getItemCount() {
        return listaDeNoticias.size();
    }

    public class NoticiaViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageViewDeLaNoticia;
        private WebView webViewTituloDeLaNoticia;
        private WebView webViewDescripcionDeLaNoticias;



        public NoticiaViewHolder(View itemView) {
            super(itemView);

            imageViewDeLaNoticia = itemView.findViewById(R.id.imageViewDeLaNoticia_celdanoticia);
            webViewTituloDeLaNoticia = itemView.findViewById(R.id.webViewTituloDeLaNoticia_celdanoticia);
            webViewDescripcionDeLaNoticias = itemView.findViewById(R.id.webViewDescripcionDeLaNoticia_celdanoticia);
        }

        public void cargarNoticia(Noticia noticia) {

            final WebSettings webSettings = webViewTituloDeLaNoticia.getSettings();
            webSettings.setDefaultFontSize((int)20);

            Helper.cargarImagenesDeNoticias(context,noticia.getEmbedded().getListaDeImagenes().get(0).getMedia_details().getSizes().getThumbnail().getSource_url(),imageViewDeLaNoticia);

            webViewTituloDeLaNoticia.loadData(noticia.getTitle().getRendered(),"text/html","UTF-8");
            webViewDescripcionDeLaNoticias.loadData(noticia.getExcerpt().getRendered(),"text/html","UTF-8");

            webViewTituloDeLaNoticia.setBackgroundColor(Color.TRANSPARENT);
            webViewDescripcionDeLaNoticias.setBackgroundColor(Color.TRANSPARENT);

        }
    }

}
