package com.facebook;

import com.facebook.p022b.Logger;
import com.facebook.p022b.Utility;
import com.newrelic.agent.android.api.common.WanType;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.apache.http.HttpStatus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

/* renamed from: com.facebook.o */
public class GraphResponse {
    private final HttpURLConnection f1022a;
    private final JSONObject f1023b;
    private final JSONArray f1024c;
    private final FacebookRequestError f1025d;
    private final String f1026e;
    private final GraphRequest f1027f;

    GraphResponse(GraphRequest graphRequest, HttpURLConnection httpURLConnection, String str, JSONObject jSONObject) {
        this(graphRequest, httpURLConnection, str, jSONObject, null, null);
    }

    GraphResponse(GraphRequest graphRequest, HttpURLConnection httpURLConnection, String str, JSONArray jSONArray) {
        this(graphRequest, httpURLConnection, str, null, jSONArray, null);
    }

    GraphResponse(GraphRequest graphRequest, HttpURLConnection httpURLConnection, FacebookRequestError facebookRequestError) {
        this(graphRequest, httpURLConnection, null, null, null, facebookRequestError);
    }

    GraphResponse(GraphRequest graphRequest, HttpURLConnection httpURLConnection, String str, JSONObject jSONObject, JSONArray jSONArray, FacebookRequestError facebookRequestError) {
        this.f1027f = graphRequest;
        this.f1022a = httpURLConnection;
        this.f1026e = str;
        this.f1023b = jSONObject;
        this.f1024c = jSONArray;
        this.f1025d = facebookRequestError;
    }

    public final FacebookRequestError m1352a() {
        return this.f1025d;
    }

    public final JSONObject m1353b() {
        return this.f1023b;
    }

    public String toString() {
        String format;
        try {
            Locale locale = Locale.US;
            String str = "%d";
            Object[] objArr = new Object[1];
            objArr[0] = Integer.valueOf(this.f1022a != null ? this.f1022a.getResponseCode() : HttpStatus.SC_OK);
            format = String.format(locale, str, objArr);
        } catch (IOException e) {
            format = WanType.UNKNOWN;
        }
        return "{Response: " + " responseCode: " + format + ", graphObject: " + this.f1023b + ", error: " + this.f1025d + "}";
    }

    static List<GraphResponse> m1349a(HttpURLConnection httpURLConnection, GraphRequestBatch graphRequestBatch) {
        List<GraphResponse> a;
        Closeable closeable = null;
        try {
            if (httpURLConnection.getResponseCode() >= HttpStatus.SC_BAD_REQUEST) {
                closeable = httpURLConnection.getErrorStream();
            } else {
                closeable = httpURLConnection.getInputStream();
            }
            a = GraphResponse.m1347a((InputStream) closeable, httpURLConnection, graphRequestBatch);
        } catch (FacebookException e) {
            Logger.m1000a(LoggingBehavior.REQUESTS, "Response", "Response <Error>: %s", e);
            a = GraphResponse.m1351a((List) graphRequestBatch, httpURLConnection, e);
        } catch (Throwable e2) {
            Logger.m1000a(LoggingBehavior.REQUESTS, "Response", "Response <Error>: %s", e2);
            a = GraphResponse.m1351a((List) graphRequestBatch, httpURLConnection, new FacebookException(e2));
        } catch (Throwable e22) {
            Logger.m1000a(LoggingBehavior.REQUESTS, "Response", "Response <Error>: %s", e22);
            a = GraphResponse.m1351a((List) graphRequestBatch, httpURLConnection, new FacebookException(e22));
        } catch (Throwable e222) {
            Logger.m1000a(LoggingBehavior.REQUESTS, "Response", "Response <Error>: %s", e222);
            a = GraphResponse.m1351a((List) graphRequestBatch, httpURLConnection, new FacebookException(e222));
        } finally {
            Utility.m1116a(closeable);
        }
        return a;
    }

    static List<GraphResponse> m1347a(InputStream inputStream, HttpURLConnection httpURLConnection, GraphRequestBatch graphRequestBatch) throws FacebookException, JSONException, IOException {
        Logger.m1000a(LoggingBehavior.INCLUDE_RAW_RESPONSES, "Response", "Response (raw)\n  Size: %d\n  Response:\n%s\n", Integer.valueOf(Utility.m1098a(inputStream).length()), r0);
        return GraphResponse.m1348a(Utility.m1098a(inputStream), httpURLConnection, graphRequestBatch);
    }

    static List<GraphResponse> m1348a(String str, HttpURLConnection httpURLConnection, GraphRequestBatch graphRequestBatch) throws FacebookException, JSONException, IOException {
        Logger.m1000a(LoggingBehavior.REQUESTS, "Response", "Response\n  Id: %s\n  Size: %d\n  Responses:\n%s\n", graphRequestBatch.m1337b(), Integer.valueOf(str.length()), GraphResponse.m1350a(httpURLConnection, (List) graphRequestBatch, new JSONTokener(str).nextValue()));
        return GraphResponse.m1350a(httpURLConnection, (List) graphRequestBatch, new JSONTokener(str).nextValue());
    }

    private static List<GraphResponse> m1350a(HttpURLConnection httpURLConnection, List<GraphRequest> list, Object obj) throws FacebookException, JSONException {
        JSONArray jSONArray;
        int i = 0;
        int size = list.size();
        List<GraphResponse> arrayList = new ArrayList(size);
        if (size == 1) {
            GraphRequest graphRequest = (GraphRequest) list.get(0);
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("body", obj);
                jSONObject.put("code", httpURLConnection != null ? httpURLConnection.getResponseCode() : HttpStatus.SC_OK);
                jSONArray = new JSONArray();
                jSONArray.put(jSONObject);
            } catch (Exception e) {
                arrayList.add(new GraphResponse(graphRequest, httpURLConnection, new FacebookRequestError(httpURLConnection, e)));
                jSONArray = obj;
            } catch (Exception e2) {
                arrayList.add(new GraphResponse(graphRequest, httpURLConnection, new FacebookRequestError(httpURLConnection, e2)));
            }
            if ((jSONArray instanceof JSONArray) || jSONArray.length() != size) {
                throw new FacebookException("Unexpected number of results");
            }
            jSONArray = jSONArray;
            while (i < jSONArray.length()) {
                graphRequest = (GraphRequest) list.get(i);
                try {
                    arrayList.add(GraphResponse.m1346a(graphRequest, httpURLConnection, jSONArray.get(i), obj));
                } catch (Exception e3) {
                    arrayList.add(new GraphResponse(graphRequest, httpURLConnection, new FacebookRequestError(httpURLConnection, e3)));
                } catch (Exception e32) {
                    arrayList.add(new GraphResponse(graphRequest, httpURLConnection, new FacebookRequestError(httpURLConnection, e32)));
                }
                i++;
            }
            return arrayList;
        }
        jSONArray = obj;
        if (jSONArray instanceof JSONArray) {
        }
        throw new FacebookException("Unexpected number of results");
    }

    private static GraphResponse m1346a(GraphRequest graphRequest, HttpURLConnection httpURLConnection, Object obj, Object obj2) throws JSONException {
        if (obj instanceof JSONObject) {
            JSONObject jSONObject = (JSONObject) obj;
            FacebookRequestError a = FacebookRequestError.m1188a(jSONObject, obj2, httpURLConnection);
            if (a != null) {
                if (a.m1191b() == 190 && Utility.m1124a(graphRequest.m780d())) {
                    AccessToken.m695a(null);
                }
                return new GraphResponse(graphRequest, httpURLConnection, a);
            }
            Object a2 = Utility.m1095a(jSONObject, "body", "FACEBOOK_NON_JSON_RESULT");
            if (a2 instanceof JSONObject) {
                return new GraphResponse(graphRequest, httpURLConnection, a2.toString(), (JSONObject) a2);
            }
            if (a2 instanceof JSONArray) {
                return new GraphResponse(graphRequest, httpURLConnection, a2.toString(), (JSONArray) a2);
            }
            obj = JSONObject.NULL;
        }
        if (obj == JSONObject.NULL) {
            return new GraphResponse(graphRequest, httpURLConnection, obj.toString(), (JSONObject) null);
        }
        throw new FacebookException("Got unexpected object type in response, class: " + obj.getClass().getSimpleName());
    }

    static List<GraphResponse> m1351a(List<GraphRequest> list, HttpURLConnection httpURLConnection, FacebookException facebookException) {
        int size = list.size();
        List<GraphResponse> arrayList = new ArrayList(size);
        for (int i = 0; i < size; i++) {
            arrayList.add(new GraphResponse((GraphRequest) list.get(i), httpURLConnection, new FacebookRequestError(httpURLConnection, facebookException)));
        }
        return arrayList;
    }
}
