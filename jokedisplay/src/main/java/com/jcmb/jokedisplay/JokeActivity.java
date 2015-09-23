package com.jcmb.jokedisplay;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class JokeActivity extends AppCompatActivity {

    public static final String JOKE_EXTRA = "joke_etxra";

    private String joke;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);
        Intent intent = getIntent();

        if(savedInstanceState != null && savedInstanceState.containsKey(JOKE_EXTRA))
        {
            joke = savedInstanceState.getString(JOKE_EXTRA);
        }
        else if(intent != null && intent.hasExtra(JOKE_EXTRA))
        {
            joke = intent.getStringExtra(JOKE_EXTRA);
        }

        if(joke != null)
        {
            TextView tvJoke = (TextView)findViewById(R.id.tvJoke);
            tvJoke.setText(joke);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        if(joke != null)
        {
            outState.putString(JOKE_EXTRA, joke);
        }
    }
}
