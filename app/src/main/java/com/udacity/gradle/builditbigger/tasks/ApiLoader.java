package com.udacity.gradle.builditbigger.tasks;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.jcmb.udacity.builditbigger.backend.myApi.MyApi;

import java.io.IOException;

/**
 * @author Julio Mendoza on 9/23/15.
 */
public class ApiLoader extends AsyncTaskLoader<String> {

    private static MyApi myApiService = null;

    public ApiLoader(Context context) {
        super(context);
        forceLoad();
    }

    @Override
    public String loadInBackground() {
        Log.d(ApiLoader.class.getSimpleName(), "Loading in background");
        if(myApiService == null)
        {
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });

            myApiService = builder.build();
        }

        String data;
        try {

            data =  myApiService.tellJoke().execute().getData();
        } catch (IOException e) {
            e.printStackTrace();
            data = "" + e.getMessage();
        }
        Log.d(ApiLoader.class.getSimpleName(), data);
        return data;
    }
}
