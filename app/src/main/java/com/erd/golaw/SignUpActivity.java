package com.erd.golaw;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ILM on 6/2/2016.
 */
public class SignUpActivity extends AppCompatActivity implements SignUpFragment.OnFragmentInteractionListener{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new SignUpFragment())
                    .commit();
        }
    }

    @OnClick(R.id.fab)
    public void login() {
        finish();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
