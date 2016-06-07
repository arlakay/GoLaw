package com.erd.golaw;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by ILM on 6/1/2016.
 */
public class ListServisFragment extends Fragment {
    ArrayAdapter<String> mForecastAdapter;

    public ListServisFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        String[] data = {
                "Servis One",
                "Servis Two",
                "Servis Three",
                "Servis Four",
                "Servis Five",
                "Servis Six",
                "Servis Seven",
                "Servis Eight",
                "Servis Nine",
                "Servis Ten"
        };

        List<String> weekForecast = new ArrayList<String>(Arrays.asList(data));

        mForecastAdapter =
                new ArrayAdapter<String>(
                        getActivity(),
                        R.layout.list_item_servis,
                        R.id.txt_name_servis,
                        weekForecast);

        View rootView = inflater.inflate(R.layout.fragment_list_servis, container, false);

        ListView listView = (ListView) rootView.findViewById(R.id.listview_servis);
        listView.setAdapter(mForecastAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), ServisDetailActivity.class);
                startActivity(intent);
            }
        });

        return rootView;
    }
}
