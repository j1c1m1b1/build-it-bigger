package com.udacity.gradle.builditbigger.free;

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

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
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

    private InterstitialAd interstitialAd;

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

                if(interstitialAd.isLoaded())
                {
                    interstitialAd.show();
                }
                else
                {
                    tellJoke();
                }
            }
        });

        interstitialAd = new InterstitialAd(getActivity());

        interstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");

        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                requestNewInterstitial();
                tellJoke();
            }
        });

        requestNewInterstitial();

        AdView mAdView = (AdView) root.findViewById(R.id.adView);
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);
        return root;
    }

    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice("SEE_YOUR_LOGCAT_TO_GET_YOUR_DEVICE_ID")
                .build();

        interstitialAd.loadAd(adRequest);
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
