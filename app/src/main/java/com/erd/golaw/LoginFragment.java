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
import com.erd.golaw.model.Login;
import com.google.gson.Gson;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ILM on 6/2/2016.
 */
public class LoginFragment extends Fragment {
    private final static String API_KEY = "3f5dae69801498945f0cfa8c2c1aca94";
    private static final String TAG = LoginFragment.class.getSimpleName();
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private List<Login> loginList;

    private String mParam1;
    private String mParam2;
    private String m, p;

    private OnFragmentInteractionListener mListener;

    @BindView(R.id.et_email) EditText email;
    @BindView(R.id.et_pass)  EditText pass;

    public LoginFragment() {
    }

    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, rootView);

        return rootView;
    }

    @OnClick(R.id.btn_login)
    public void login_auth() {
        m = email.getText().toString();
        p = pass.getText().toString();
        advokatAuth(m,p);
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

    private void advokatAuth(String user, String pass) {
        if (API_KEY.isEmpty()) {
            Toast.makeText(getActivity().getApplicationContext(), "Unauthorized", Toast.LENGTH_LONG).show();
            return;
        }

        final ProgressDialog dialog = ProgressDialog.show(getActivity(), "", "loading...");

        ApiService apiService =
                RestApi.getClient().create(ApiService.class);

        Call<Login> call = apiService.advokatAuth(user, pass, API_KEY);
        call.enqueue(new Callback<Login>() {
            @Override
            public void onResponse(Call<Login>call, Response<Login> response) {
                dialog.dismiss();

                Log.d(TAG, "Status Code : " + response.code());
                Log.d(TAG, "Data Advokat : " + new Gson().toJson(response.body()));

                if (response.code() == 200 && response.body().isError() == false) {
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getActivity(), "Maaf Username atau Password salah", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Login>call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(getActivity(), "Kesalahan Jaringan", Toast.LENGTH_LONG).show();
            }
        });
    }

}
