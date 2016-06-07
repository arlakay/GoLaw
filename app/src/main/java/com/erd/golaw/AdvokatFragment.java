package com.erd.golaw;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by ILM on 6/1/2016.
 */
public class AdvokatFragment extends Fragment implements SearchView.OnQueryTextListener {
    ArrayAdapter<String> mForecastAdapter;

    public AdvokatFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        String[] data = {
                "Advokat One",
                "Advokat Two",
                "Advokat Three",
                "Advokat Four",
                "Advokat Five",
                "Advokat Six",
                "Advokat Seven",
                "Advokat Eight",
                "Advokat Nine",
                "Advokat Ten"
        };

        List<String> weekForecast = new ArrayList<String>(Arrays.asList(data));

        mForecastAdapter =
                new ArrayAdapter<String>(
                        getActivity(),
                        R.layout.list_item_advokat,
                        R.id.txt_name_advokat,
                        weekForecast);

        View rootView = inflater.inflate(R.layout.fragment_advokat, container, false);

        ButterKnife.bind(this, rootView);

        ListView listView = (ListView) rootView.findViewById(R.id.listview_advokat);
        listView.setAdapter(mForecastAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), AdvokatDetailActivity.class);
                startActivity(intent);
            }
        });

        return rootView;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
//        final List<Restaurant> filteredModelList = filter(restaurantList, newText);
//        adapter.animateTo(filteredModelList);
//        recyclerView.scrollToPosition(0);
        return true;
    }

}
