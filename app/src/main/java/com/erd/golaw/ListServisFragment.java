package com.erd.golaw;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.erd.golaw.adapter.ServicesAdapter;
import com.erd.golaw.api.RestApi;
import com.erd.golaw.api.services.ApiService;
import com.erd.golaw.comunicate.OnFragmentInteractionListener;
import com.erd.golaw.model.Pelayanan;
import com.erd.golaw.model.PelayananResponse;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ILM on 6/1/2016.
 */
public class ListServisFragment extends Fragment {
    @BindView(R.id.recycler_services)
    RecyclerView recyclerView;

    private ServicesAdapter adapter;
    private List<Pelayanan> servisList = new ArrayList<>();

    private final static String API_KEY = "3f5dae69801498945f0cfa8c2c1aca94";
    private static final String TAG = ListServisFragment.class.getSimpleName();
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ListServisFragment() {
    }

    public static ListServisFragment newInstance(String param1, String param2) {
        ListServisFragment fragment = new ListServisFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_list_servis, container, false);
        ButterKnife.bind(this, rootView);

        getServices();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getBaseContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        return rootView;
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

    private void getServices() {
        if (API_KEY.isEmpty()) {
            Toast.makeText(getActivity().getApplicationContext(), "Unauthorized", Toast.LENGTH_LONG).show();
            return;
        }

        final ProgressDialog dialog = ProgressDialog.show(getActivity(), "", "loading...");

        ApiService apiService =
                RestApi.getClient().create(ApiService.class);

        Call<PelayananResponse> call = apiService.getPelayanan(API_KEY);
        call.enqueue(new Callback<PelayananResponse>() {
            @Override
            public void onResponse(Call<PelayananResponse>call, Response<PelayananResponse> response) {
                dialog.dismiss();

                servisList = response.body().getResults();
                Log.d(TAG, "Status Code = " + response.code());
                Log.d(TAG, "Data of Pelayanan received: " + new Gson().toJson(servisList));

                adapter = new ServicesAdapter(servisList, R.layout.list_item_servis, getActivity().getApplicationContext(), new ServicesAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(Pelayanan model) {
                        String namaRes = model.getName();
                        String descRes = model.getDetails();

                        Intent intent = new Intent(getActivity(), ServisDetailActivity.class);
                        intent.putExtra("nama", namaRes);
                        intent.putExtra("desc", descRes);
                        startActivity(intent);
                    }
                });

                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<PelayananResponse>call, Throwable t) {
                dialog.dismiss();
                Log.e(TAG, t.toString());
            }
        });
    }

}
