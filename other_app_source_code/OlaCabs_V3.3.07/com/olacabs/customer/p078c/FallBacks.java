package com.olacabs.customer.p078c;

import com.newrelic.agent.android.instrumentation.HttpInstrumentation;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import java.io.IOException;
import java.io.InputStream;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.olacabs.customer.c.a */
public class FallBacks {
    public String m13350a(String str) {
        HttpUriRequest httpGet = new HttpGet("https://maps.googleapis.com/maps/api/geocode/json?latlng=" + str + "&language=en-GB");
        HttpClient defaultHttpClient = new DefaultHttpClient();
        StringBuilder stringBuilder = new StringBuilder();
        try {
            InputStream content = (!(defaultHttpClient instanceof HttpClient) ? defaultHttpClient.execute(httpGet) : HttpInstrumentation.execute(defaultHttpClient, httpGet)).getEntity().getContent();
            while (true) {
                int read = content.read();
                if (read == -1) {
                    break;
                }
                stringBuilder.append((char) read);
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        String str2 = null;
        try {
            JSONObject init = JSONObjectInstrumentation.init(stringBuilder.toString());
            if (init.getString(NotificationCompatApi21.CATEGORY_STATUS).equals("OK")) {
                str2 = init.getJSONArray("results").getJSONObject(0).getString("formatted_address");
            }
        } catch (JSONException e3) {
            e3.printStackTrace();
        }
        return str2;
    }
}
