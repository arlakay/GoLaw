package com.erd.golaw;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.erd.golaw.comunicate.OnFragmentInteractionListener;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ILM on 6/1/2016.
 */
public class ServisFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ServisFragment() {
    }

    public static ServisFragment newInstance(String param1, String param2) {
        ServisFragment fragment = new ServisFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
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

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

}
