package com.android.volley.toolbox;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import java.io.IOException;
import java.util.Map;
import org.apache.http.HttpResponse;

/* renamed from: com.android.volley.toolbox.f */
public interface HttpStack {
    HttpResponse m639a(Request<?> request, Map<String, String> map) throws IOException, AuthFailureError;
}
