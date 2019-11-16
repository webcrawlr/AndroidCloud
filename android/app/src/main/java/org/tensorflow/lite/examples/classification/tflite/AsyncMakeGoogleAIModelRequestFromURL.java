package org.tensorflow.lite.examples.classification.tflite;
import android.os.AsyncTask;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.FileContent;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpContent;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.UriTemplate;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.discovery.Discovery;
import com.google.api.services.discovery.model.JsonSchema;
import com.google.api.services.discovery.model.RestDescription;
import com.google.api.services.discovery.model.RestMethod;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class AsyncMakeGoogleAIModelRequestFromURL extends AsyncTask<HttpRequest, Integer, Long>
{

    protected void onProgressUpdate(Integer... progress)
    {
            setProgressPercent(progress[0]);
    }

    @Override
    protected Long doInBackground(HttpRequest... httpRequests) {
        String response = httpRequests.execute().parseAsString();
        return httpRequests;
    }

    protected void onPostExecute(Long result) {
        showDialog("Downloaded " + result + " bytes");
        //put your code here to process results
        }
}
