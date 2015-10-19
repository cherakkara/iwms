package com.android.volley.toolbox;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response.Response;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import org.json.JSONObject;

/* renamed from: com.android.volley.toolbox.j */
public class JsonObjectRequest extends JsonRequest<JSONObject> {
    public JsonObjectRequest(int i, String str, JSONObject jSONObject, Response<JSONObject> response, Response response2) {
        String jSONObject2 = jSONObject == null ? null : !(jSONObject instanceof JSONObject) ? jSONObject.toString() : JSONObjectInstrumentation.toString(jSONObject);
        super(i, str, jSONObject2, response, response2);
    }

    public JsonObjectRequest(String str, JSONObject jSONObject, Response<JSONObject> response, Response response2) {
        this(jSONObject == null ? 0 : 1, str, jSONObject, response, response2);
    }

    protected com.android.volley.Response<JSONObject> parseNetworkResponse(NetworkResponse networkResponse) {
        try {
            return com.android.volley.Response.m587a(JSONObjectInstrumentation.init(new String(networkResponse.f498b, HttpHeaderParser.m647a(networkResponse.f499c))), HttpHeaderParser.m646a(networkResponse));
        } catch (Throwable e) {
            return com.android.volley.Response.m586a(new ParseError(e));
        } catch (Throwable e2) {
            return com.android.volley.Response.m586a(new ParseError(e2));
        }
    }
}
