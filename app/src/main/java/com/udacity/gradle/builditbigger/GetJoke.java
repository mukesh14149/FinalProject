package com.udacity.gradle.builditbigger;

import android.os.AsyncTask;
import com.example.mg.myapplication.backend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;

/**
 * Created by mg on 12/8/16.
 */

public class GetJoke extends AsyncTask<Void, Void, String> {
private static MyApi myApiService = null;


@Override
protected String doInBackground(Void... voids) {
        if(myApiService == null) {  // Only do this once
        MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
        new AndroidJsonFactory(), null)
        // options for running against local devappserver
        // - 10.0.2.2 is localhost's IP address in Android emulator
        // - turn off compression when running against local devappserver
        .setRootUrl("http://10.0.2.2:8080/_ah/api/")
        .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
        @Override
        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                abstractGoogleClientRequest.setDisableGZipContent(true);
                }
                });
        // end options for devappserver

        myApiService = builder.build();
        }
        String joke = null;
                try {
                     joke = myApiService.getJoke().execute().getData();
                } catch (IOException e) {
                            e.printStackTrace();
                }
                return joke;
        }

}