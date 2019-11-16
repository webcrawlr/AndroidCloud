package org.tensorflow.lite.examples.classification.tflite;
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
public class GoogleCloudModelRequest {

    AsyncMakeGoogleAIModelRequestFromURL async;

        void predictOnline() throws Exception
        {
            HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
            JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();
            Discovery discovery = new Discovery.Builder(httpTransport, jsonFactory, null).build();

            RestDescription api = discovery.apis().getRest("ml", "v1").execute();
            RestMethod method = api.getResources().get("projects").getMethods().get("predict");

            JsonSchema param = new JsonSchema();
            String projectId = "catdogsample";
            // You should have already deployed a model and a version.
            // For reference, see https://cloud.google.com/ml-engine/docs/deploying-models.
            String modelId = "catdogpredict";
            String versionId = "YOUR_VERSION_ID";
            param.set(
                    "name", String.format("projects/%s/models/%s/versions/%s", projectId, modelId, versionId));

            GenericUrl url =
                    new GenericUrl(UriTemplate.expand(api.getBaseUrl() + method.getPath(), param, true));
            System.out.println(url);

            String contentType = "application/json";
            File requestBodyFile = new File("input.txt");
            HttpContent content = new FileContent(contentType, requestBodyFile);
            System.out.println(content.getLength());

            List<String> scopes = new ArrayList<>();
            scopes.add("https://www.googleapis.com/auth/cloud-platform");

            GoogleCredential credential = GoogleCredential.getApplicationDefault().createScoped(scopes);
            HttpRequestFactory requestFactory = httpTransport.createRequestFactory(credential);
            HttpRequest request = requestFactory.buildRequest(method.getHttpMethod(), url, content);

            //String response = request.execute().parseAsString();
            doit.execute(request);
        }
}
