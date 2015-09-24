package com.udacity.gradle.builditbigger.paid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.jcmb.jokedisplay.JokeActivity;
import com.udacity.gradle.builditbigger.MainActivity;
import com.udacity.gradle.builditbigger.R;
import com.udacity.gradle.builditbigger.tasks.ApiLoader;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements LoaderManager.LoaderCallbacks<String>{

    private static final int LOADER_ID = 100;
    private ProgressBar pbLoading;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        pbLoading = (ProgressBar) root.findViewById(R.id.pbLoading);
        pbLoading.setVisibility(View.GONE);

        Button btnJoke = (Button)root.findViewById(R.id.btnJoke);

        btnJoke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tellJoke();
            }
        });

        return root;
    }

    public void tellJoke(){

        /*
        Step 1
        JokeSmith jokeSmith = new JokeSmith();
        String joke = jokeSmith.tellAJoke();
        Toast.makeText(this, joke, Toast.LENGTH_LONG).show();
        */

        /*
        Step 2
        JokeSmith jokeSmith = new JokeSmith();
        String joke = jokeSmith.tellAJoke();

        Intent intent = new Intent(this, JokeActivity.class);
        intent.putExtra(JokeActivity.JOKE_EXTRA, joke);

        startActivity(intent);
        */

        /*
        STEP 3
         */
        getActivity().getSupportLoaderManager().initLoader(LOADER_ID, null, this);
        if(pbLoading.getVisibility() == View.GONE)
        {
            pbLoading.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public Loader<String> onCreateLoader(int id, Bundle args) {
        Log.d(MainActivity.class.getSimpleName(), "Loader created");
        return new ApiLoader(getActivity(), ApiLoader.ROOT_URL);
    }

    @Override
    public void onLoadFinished(Loader<String> loader, String data) {
        /*
        Step 3
         */

        pbLoading.setVisibility(View.GONE);

        Log.d(MainActivity.class.getSimpleName(), "Load finished!");
        if(data != null)
        {
            Intent intent = new Intent(getActivity(), JokeActivity.class);
            intent.putExtra(JokeActivity.JOKE_EXTRA, data);

            startActivity(intent);
        }
    }

    @Override
    public void onLoaderReset(Loader<String> loader) {

    }
}
