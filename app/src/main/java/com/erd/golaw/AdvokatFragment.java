package com.erd.golaw;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.erd.golaw.adapter.AdvokatAdapter;
import com.erd.golaw.api.RestApi;
import com.erd.golaw.api.services.ApiService;
import com.erd.golaw.comunicate.OnFragmentInteractionListener;
import com.erd.golaw.model.Advokat;
import com.erd.golaw.model.AdvokatResponse;
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
public class AdvokatFragment extends Fragment implements SearchView.OnQueryTextListener {
    @BindView(R.id.recycler_advokat) RecyclerView recyclerView;

    private AdvokatAdapter adapter;
    private List<Advokat> loginList = new ArrayList<>();

    private final static String API_KEY = "3f5dae69801498945f0cfa8c2c1aca94";
    private static final String TAG = AdvokatFragment.class.getSimpleName();
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public AdvokatFragment() {
    }

    public static AdvokatFragment newInstance(String param1, String param2) {
        AdvokatFragment fragment = new AdvokatFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_advokat, container, false);

        ButterKnife.bind(this, rootView);

        getAdvokat();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getBaseContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        return rootView;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.advokat_menu, menu);

        MenuItem item = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        searchView.setOnQueryTextListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_search) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        final List<Advokat> filteredModelList = filter(loginList, newText);
        adapter.animateTo(filteredModelList);
        recyclerView.scrollToPosition(0);
        return true;
    }

    private List<Advokat> filter(List<Advokat> models, String query) {
        query = query.toLowerCase();

        final List<Advokat> filteredModelList = new ArrayList<>();

        for (Advokat model : models) {
            final String text = model.getName().toLowerCase();
            if (text.contains(query)) {
                filteredModelList.add(model);
            }
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new AdvokatAdapter(filteredModelList, R.layout.list_item_advokat, getActivity().getApplicationContext(), new AdvokatAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Advokat model) {
                String namaRes = model.getName();
                String descRes = model.getUsername();

                Intent intent = new Intent(getActivity(), AdvokatDetailActivity.class);
                intent.putExtra("nama", namaRes);
                intent.putExtra("desc", descRes);
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();  // data set changed
        return filteredModelList;
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

    private void getAdvokat() {
        if (API_KEY.isEmpty()) {
            Toast.makeText(getActivity().getApplicationContext(), "Unauthorized", Toast.LENGTH_LONG).show();
            return;
        }

        final ProgressDialog dialog = ProgressDialog.show(getActivity(), "", "loading...");

        ApiService apiService =
                RestApi.getClient().create(ApiService.class);

        Call<AdvokatResponse> call = apiService.getAdvokat(API_KEY);
        call.enqueue(new Callback<AdvokatResponse>() {
            @Override
            public void onResponse(Call<AdvokatResponse>call, Response<AdvokatResponse> response) {
                dialog.dismiss();

                loginList = response.body().getResults();
                Log.d(TAG, "Status Code = " + response.code());
                Log.d(TAG, "Data of Advokat received: " + new Gson().toJson(loginList));

                adapter = new AdvokatAdapter(loginList, R.layout.list_item_advokat, getActivity().getApplicationContext(), new AdvokatAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(Advokat model) {
                        String namaRes = model.getName();
                        String descRes = model.getUsername();

                        Intent intent = new Intent(getActivity(), AdvokatDetailActivity.class);
                        intent.putExtra("nama", namaRes);
                        intent.putExtra("desc", descRes);
                        startActivity(intent);
                    }
                });

                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<AdvokatResponse>call, Throwable t) {
                dialog.dismiss();
                Log.e(TAG, t.toString());
            }
        });
    }

}
