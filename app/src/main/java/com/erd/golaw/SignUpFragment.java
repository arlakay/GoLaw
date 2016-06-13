package com.erd.golaw;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.erd.golaw.api.RestApi;
import com.erd.golaw.api.services.ApiService;
import com.erd.golaw.comunicate.OnFragmentInteractionListener;
import com.erd.golaw.model.Advokat;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ILM on 6/2/2016.
 */
public class SignUpFragment extends Fragment {
    @BindView(R.id.et_fname) EditText fname;
    @BindView(R.id.et_desc)  EditText desc;
    @BindView(R.id.et_username) EditText username;
    @BindView(R.id.et_pass)  EditText pass;

    private final static String API_KEY = "3f5dae69801498945f0cfa8c2c1aca94";
    private static final String TAG = SignUpFragment.class.getSimpleName();
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public SignUpFragment() {
    }

    public static SignUpFragment newInstance(String param1, String param2) {
        SignUpFragment fragment = new SignUpFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_signup, container, false);
        ButterKnife.bind(this, rootView);

        return rootView;
    }

    @OnClick(R.id.btn_save)
    public void save_registration() {
        String m = username.getText().toString();
        String p = pass.getText().toString();
        String f = fname.getText().toString();
        String d = desc.getText().toString();
        createAdvokat(f, m, p, d);
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

    private void createAdvokat(String name, String user, String pass, String desc) {
        if (API_KEY.isEmpty()) {
            Toast.makeText(getActivity().getApplicationContext(), "Unauthorized", Toast.LENGTH_LONG).show();
            return;
        }

        final ProgressDialog dialog = ProgressDialog.show(getActivity(), "", "loading...");

        ApiService apiService =
                RestApi.getClient().create(ApiService.class);

        Call<Advokat> call = apiService.newAdvokat(name, user, pass, desc, API_KEY);
        call.enqueue(new Callback<Advokat>() {
            @Override
            public void onResponse(Call<Advokat>call, Response<Advokat> response) {
                dialog.dismiss();

                Log.d(TAG, "Status Code : " + response.code());
                Log.d(TAG, "Data Advokat : " + new Gson().toJson(response.message()));

                if (response.code() == 201 ) {
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getActivity(), "Maaf, Gagal Melakukan Pendaftaran", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Advokat>call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(getActivity(), "Kesalahan Jaringan", Toast.LENGTH_LONG).show();
            }
        });
    }

    }
