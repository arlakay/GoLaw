package com.erd.golaw;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.erd.golaw.comunicate.OnFragmentInteractionListener;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ILM on 6/1/2016.
 */
public class AdvokatActivity extends AppCompatActivity implements OnFragmentInteractionListener {
    @BindView(R.id.toolbar) Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advokat);
        ButterKnife.bind(this);
        setupToolbar();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new AdvokatFragment())
                    .commit();
        }
    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (getSupportActionBar() == null) {
            throw new IllegalStateException("Activity must implement toolbar");
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
