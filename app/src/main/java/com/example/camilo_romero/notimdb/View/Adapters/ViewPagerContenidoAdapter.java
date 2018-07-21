package com.example.camilo_romero.notimdb.View.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.camilo_romero.notimdb.Model.TMDb.Contenido;
import com.example.camilo_romero.notimdb.View.Fragments.ParaViewPager.ViewPagerContenidoFragment;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerContenidoAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> listaDeFragments;


    public ViewPagerContenidoAdapter(FragmentManager fm, List<Contenido> listaDeContenido) {
        super(fm);
        listaDeFragments = new ArrayList<>();


        for (Contenido contenido : listaDeContenido) {
            ViewPagerContenidoFragment viewPagerContenidoFragment = ViewPagerContenidoFragment.fabricaDeFragments(contenido);
            listaDeFragments.add(viewPagerContenidoFragment);
        }

    }

    @Override
    public Fragment getItem(int position) {
        return listaDeFragments.get(position);
    }

    @Override
    public int getCount() {
        return listaDeFragments.size();
    }
}
