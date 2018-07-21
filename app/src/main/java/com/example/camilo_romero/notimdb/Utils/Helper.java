package com.example.camilo_romero.notimdb.Utils;

import android.content.Context;
import android.widget.ImageView;

import com.example.camilo_romero.notimdb.Model.TMDb.Equipo;
import com.example.camilo_romero.notimdb.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;
import java.util.concurrent.TimeUnit;

import de.hdodenhof.circleimageview.CircleImageView;

public class Helper {

    public static final String PELICULA = "Pelicula";
    public static final String SERIE = "Serie";
    public static final String CLAVE_API_YOUTUBE = "AIzaSyAHsRF_E-q7yj4PFAFLBu5bf77EiE7u0Ng";
    public static final String URL_YOUTUBE_PARTE1 = "https://img.youtube.com/vi/";
    public static final String URL_YOUTUBE_PARTE2 = "/0.jpg";


    public static final String URL_BASE_CINEPREMIERE = "https://www.cinepremiere.com.mx/wp-json/";


    public static void cargarImagenesBackground(Context context, String url, ImageView imageView) {

        Picasso.with(context)
                .load(TMDBHelper.baseURLImagenes + TMDBHelper.TAMANO_IMAGEN_W300 + url)
                .into(imageView);

    }


    public static void cargarImagenesPosterDeActor(Context context, String url, ImageView imageView) {
        Picasso.with(context)
                .load(TMDBHelper.baseURLImagenes + TMDBHelper.TAMANO_IMAGEN_W300 + url)
                .error(R.drawable.user)
                .into(imageView);
    }

    public static void cargarImagenCircular(Context context, String url, CircleImageView circleImageView) {
        Picasso.with(context)
                .load(TMDBHelper.baseURLImagenes + TMDBHelper.TAMANO_IMAGEN_W300 + url)
                .error(R.drawable.user)
                .into(circleImageView);
    }

    public static void cargarImagenesPoster(Context context, String url, ImageView imageView) {
        Picasso.with(context)
                .load(TMDBHelper.baseURLImagenes + TMDBHelper.TAMANO_IMAGEN_W300 + url)
                .into(imageView);
    }

    public static void cargarImagenesDeNoticias(Context context,String url,ImageView imageView){
        Picasso.with(context)
                .load(url)
                .error(R.color.blanco)
                .into(imageView);
    }
    public static String identificarIdioma(String idioma) {
        switch (idioma) {
            case "en":
                return "Ingles";
        }

        return "Chino";
    }


    public static String puntearNumero(Integer numero) {
        DecimalFormat formateador = new DecimalFormat("###,###.##");

        return formateador.format(numero);


    }

    public static String formatearALink(String web) {
        String link = "<a href=" + web + ">" + web + "</a>";

        return link;

    }

    public static String estadoAEspanol(String estado){
        switch (estado){
            case "Released":
                return "Estrenada";
        }

        return "si no sabes vos gil";
    }

    public static String pasarMinutosAHoras(int minutos) {
        String formato = "%02d:%02d";

        long horasReales = TimeUnit.MINUTES.toHours(minutos);

        long minutosReales = TimeUnit.MINUTES.toMinutes(minutos) - TimeUnit.HOURS.toMinutes(TimeUnit.MINUTES.toHours(minutos));

        return String.format(formato, horasReales, minutosReales);
    }

    public static Equipo encontrarAlDirector(List<Equipo> listaDeEquipo) {
        Equipo equipo = new Equipo();
        for (Integer i = 0; i < listaDeEquipo.size(); i++) {
            if (listaDeEquipo.get(i).getJob().equals(TMDBHelper.DIRECTOR)) {
                equipo = listaDeEquipo.get(i);
            }
        }
        return equipo;
    }


    public static String dejarSoloLosAnosReleaseDate(String fecha) {

        return (String) fecha.subSequence(0, 4);
    }
}
