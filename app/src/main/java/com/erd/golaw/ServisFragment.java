package com.erd.golaw;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ILM on 6/1/2016.
 */
public class ServisFragment extends Fragment {

    public ServisFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.servis_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_servis, container, false);
        ButterKnife.bind(this, rootView);

        return rootView;
    }

    @OnClick(R.id.btn_nikah)
    public void nikah() {
        Intent intent = new Intent(getActivity(), ListServisActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_gono_gini)
    public void harta() {
        Intent intent = new Intent(getActivity(), ListServisActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_perdata)
    public void perdata() {
        Intent intent = new Intent(getActivity(), ListServisActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_pidana)
    public void pidana() {
        Intent intent = new Intent(getActivity(), ListServisActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_lalu_lintas)
    public void lalu_lintas() {
        Intent intent = new Intent(getActivity(), ListServisActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_hutang_piutang)
    public void hutang_piutang() {
        Intent intent = new Intent(getActivity(), ListServisActivity.class);
        startActivity(intent);
    }


}
