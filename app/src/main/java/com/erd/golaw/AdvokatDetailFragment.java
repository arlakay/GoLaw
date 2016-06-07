package com.erd.golaw;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ILM on 6/1/2016.
 */
public class AdvokatDetailFragment extends Fragment {

    public AdvokatDetailFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_advokat_detail, container, false);
        ButterKnife.bind(this,rootView);

        return rootView;
    }

    @OnClick(R.id.btn_close_advokat)
    public void close() {
        getActivity().finish();
    }

}
