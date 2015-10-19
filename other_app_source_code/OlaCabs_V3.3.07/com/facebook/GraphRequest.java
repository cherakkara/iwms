package com.facebook;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.ParcelFileDescriptor.AutoCloseInputStream;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import com.facebook.GraphRequestBatch.GraphRequestBatch;
import com.facebook.p022b.Logger;
import com.facebook.p022b.ServerProtocol;
import com.facebook.p022b.Utility;
import com.facebook.p022b.Validate;
import com.newrelic.agent.android.instrumentation.HttpInstrumentation;
import com.newrelic.agent.android.instrumentation.JSONArrayInstrumentation;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import com.newrelic.agent.android.instrumentation.Trace;
import com.olacabs.customer.utils.Constants;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPOutputStream;
import org.apache.http.HttpHeaders;
import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import p004b.p005a.p006a.p007a.p008a.p010b.AbstractSpiCall;

public class GraphRequest {
    public static final String f627a;
    private static String f628b;
    private static Pattern f629c;
    private static volatile String f630q;
    private AccessToken f631d;
    private HttpMethod f632e;
    private String f633f;
    private JSONObject f634g;
    private String f635h;
    private String f636i;
    private boolean f637j;
    private Bundle f638k;
    private C0153b f639l;
    private String f640m;
    private Object f641n;
    private String f642o;
    private boolean f643p;

    /* renamed from: com.facebook.GraphRequest.b */
    public interface C0153b {
        void m709a(GraphResponse graphResponse);
    }

    /* renamed from: com.facebook.GraphRequest.1 */
    static class C01541 implements C0153b {
        final /* synthetic */ C0161c f612a;

        public void m710a(GraphResponse graphResponse) {
            if (this.f612a != null) {
                this.f612a.m720a(graphResponse.m1353b(), graphResponse);
            }
        }
    }

    /* renamed from: com.facebook.GraphRequest.2 */
    class C01552 implements C0153b {
        final /* synthetic */ C0153b f613a;
        final /* synthetic */ GraphRequest f614b;

        C01552(GraphRequest graphRequest, C0153b c0153b) {
            this.f614b = graphRequest;
            this.f613a = c0153b;
        }

        public void m711a(GraphResponse graphResponse) {
            JSONArray optJSONArray;
            JSONObject b = graphResponse.m1353b();
            b = b != null ? b.optJSONObject("__debug__") : null;
            if (b != null) {
                optJSONArray = b.optJSONArray("messages");
            } else {
                optJSONArray = null;
            }
            if (optJSONArray != null) {
                for (int i = 0; i < optJSONArray.length(); i++) {
                    String optString;
                    String optString2;
                    String optString3;
                    JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                    if (optJSONObject != null) {
                        optString = optJSONObject.optString("message");
                    } else {
                        optString = null;
                    }
                    if (optJSONObject != null) {
                        optString2 = optJSONObject.optString(Constants.BUNDLE_TYPE);
                    } else {
                        optString2 = null;
                    }
                    if (optJSONObject != null) {
                        optString3 = optJSONObject.optString("link");
                    } else {
                        optString3 = null;
                    }
                    if (!(optString == null || optString2 == null)) {
                        LoggingBehavior loggingBehavior = LoggingBehavior.GRAPH_API_DEBUG_INFO;
                        if (optString2.equals("warning")) {
                            loggingBehavior = LoggingBehavior.GRAPH_API_DEBUG_WARNING;
                        }
                        if (!Utility.m1126a(optString3)) {
                            optString = optString + " Link: " + optString3;
                        }
                        Logger.m999a(loggingBehavior, GraphRequest.f627a, optString);
                    }
                }
            }
            if (this.f613a != null) {
                this.f613a.m709a(graphResponse);
            }
        }
    }

    /* renamed from: com.facebook.GraphRequest.3 */
    static class C01563 implements Runnable {
        final /* synthetic */ ArrayList f615a;
        final /* synthetic */ GraphRequestBatch f616b;

        C01563(ArrayList arrayList, GraphRequestBatch graphRequestBatch) {
            this.f615a = arrayList;
            this.f616b = graphRequestBatch;
        }

        public void run() {
            Iterator it = this.f615a.iterator();
            while (it.hasNext()) {
                Pair pair = (Pair) it.next();
                ((C0153b) pair.first).m709a((GraphResponse) pair.second);
            }
            for (GraphRequestBatch a : this.f616b.m1340e()) {
                a.m1327a(this.f616b);
            }
        }
    }

    /* renamed from: com.facebook.GraphRequest.d */
    private interface C0157d {
        void m712a(String str, String str2) throws IOException;
    }

    /* renamed from: com.facebook.GraphRequest.4 */
    class C01584 implements C0157d {
        final /* synthetic */ ArrayList f617a;
        final /* synthetic */ GraphRequest f618b;

        C01584(GraphRequest graphRequest, ArrayList arrayList) {
            this.f618b = graphRequest;
            this.f617a = arrayList;
        }

        public void m713a(String str, String str2) throws IOException {
            this.f617a.add(String.format(Locale.US, "%s=%s", new Object[]{str, URLEncoder.encode(str2, HTTP.UTF_8)}));
        }
    }

    public static class ParcelableResourceWithMimeType<RESOURCE extends Parcelable> implements Parcelable {
        public static final Creator<ParcelableResourceWithMimeType> CREATOR;
        private final String f619a;
        private final RESOURCE f620b;

        /* renamed from: com.facebook.GraphRequest.ParcelableResourceWithMimeType.1 */
        static class C01591 implements Creator<ParcelableResourceWithMimeType> {
            C01591() {
            }

            public /* synthetic */ Object createFromParcel(Parcel parcel) {
                return m714a(parcel);
            }

            public /* synthetic */ Object[] newArray(int i) {
                return m715a(i);
            }

            public ParcelableResourceWithMimeType m714a(Parcel parcel) {
                return new ParcelableResourceWithMimeType(null);
            }

            public ParcelableResourceWithMimeType[] m715a(int i) {
                return new ParcelableResourceWithMimeType[i];
            }
        }

        public String m716a() {
            return this.f619a;
        }

        public RESOURCE m717b() {
            return this.f620b;
        }

        public int describeContents() {
            return 1;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.f619a);
            parcel.writeParcelable(this.f620b, i);
        }

        static {
            CREATOR = new C01591();
        }

        private ParcelableResourceWithMimeType(Parcel parcel) {
            this.f619a = parcel.readString();
            this.f620b = parcel.readParcelable(FacebookSdk.m1208f().getClassLoader());
        }
    }

    /* renamed from: com.facebook.GraphRequest.a */
    private static class C0160a {
        private final GraphRequest f621a;
        private final Object f622b;

        public C0160a(GraphRequest graphRequest, Object obj) {
            this.f621a = graphRequest;
            this.f622b = obj;
        }

        public GraphRequest m718a() {
            return this.f621a;
        }

        public Object m719b() {
            return this.f622b;
        }
    }

    /* renamed from: com.facebook.GraphRequest.c */
    public interface C0161c {
        void m720a(JSONObject jSONObject, GraphResponse graphResponse);
    }

    /* renamed from: com.facebook.GraphRequest.e */
    public interface C0162e extends C0153b {
        void m721a(long j, long j2);
    }

    /* renamed from: com.facebook.GraphRequest.f */
    private static class C0163f implements C0157d {
        private final OutputStream f623a;
        private final Logger f624b;
        private boolean f625c;
        private boolean f626d;

        public C0163f(OutputStream outputStream, Logger logger, boolean z) {
            this.f625c = true;
            this.f626d = false;
            this.f623a = outputStream;
            this.f624b = logger;
            this.f626d = z;
        }

        public void m727a(String str, Object obj, GraphRequest graphRequest) throws IOException {
            if (this.f623a instanceof RequestOutputStream) {
                ((RequestOutputStream) this.f623a).m1714a(graphRequest);
            }
            if (GraphRequest.m763e(obj)) {
                m728a(str, GraphRequest.m765f(obj));
            } else if (obj instanceof Bitmap) {
                m724a(str, (Bitmap) obj);
            } else if (obj instanceof byte[]) {
                m731a(str, (byte[]) obj);
            } else if (obj instanceof Uri) {
                m725a(str, (Uri) obj, null);
            } else if (obj instanceof ParcelFileDescriptor) {
                m726a(str, (ParcelFileDescriptor) obj, null);
            } else if (obj instanceof ParcelableResourceWithMimeType) {
                ParcelableResourceWithMimeType parcelableResourceWithMimeType = (ParcelableResourceWithMimeType) obj;
                Parcelable b = parcelableResourceWithMimeType.m717b();
                String a = parcelableResourceWithMimeType.m716a();
                if (b instanceof ParcelFileDescriptor) {
                    m726a(str, (ParcelFileDescriptor) b, a);
                } else if (b instanceof Uri) {
                    m725a(str, (Uri) b, a);
                } else {
                    throw m722b();
                }
            } else {
                throw m722b();
            }
        }

        private RuntimeException m722b() {
            return new IllegalArgumentException("value is not a supported type.");
        }

        public void m730a(String str, JSONArray jSONArray, Collection<GraphRequest> collection) throws IOException, JSONException {
            if (this.f623a instanceof RequestOutputStream) {
                RequestOutputStream requestOutputStream = (RequestOutputStream) this.f623a;
                m729a(str, null, null);
                m732a("[", new Object[0]);
                int i = 0;
                for (GraphRequest graphRequest : collection) {
                    JSONObject jSONObject = jSONArray.getJSONObject(i);
                    requestOutputStream.m1714a(graphRequest);
                    String str2;
                    Object[] objArr;
                    if (i > 0) {
                        str2 = ",%s";
                        objArr = new Object[1];
                        objArr[0] = !(jSONObject instanceof JSONObject) ? jSONObject.toString() : JSONObjectInstrumentation.toString(jSONObject);
                        m732a(str2, objArr);
                    } else {
                        str2 = "%s";
                        objArr = new Object[1];
                        objArr[0] = !(jSONObject instanceof JSONObject) ? jSONObject.toString() : JSONObjectInstrumentation.toString(jSONObject);
                        m732a(str2, objArr);
                    }
                    i++;
                }
                m732a("]", new Object[0]);
                if (this.f624b != null) {
                    this.f624b.m1006a("    " + str, !(jSONArray instanceof JSONArray) ? jSONArray.toString() : JSONArrayInstrumentation.toString(jSONArray));
                    return;
                }
                return;
            }
            String jSONArrayInstrumentation;
            if (jSONArray instanceof JSONArray) {
                jSONArrayInstrumentation = JSONArrayInstrumentation.toString(jSONArray);
            } else {
                jSONArrayInstrumentation = jSONArray.toString();
            }
            m728a(str, jSONArrayInstrumentation);
        }

        public void m728a(String str, String str2) throws IOException {
            m729a(str, null, null);
            m733b("%s", str2);
            m723a();
            if (this.f624b != null) {
                this.f624b.m1006a("    " + str, (Object) str2);
            }
        }

        public void m724a(String str, Bitmap bitmap) throws IOException {
            m729a(str, str, "image/png");
            bitmap.compress(CompressFormat.PNG, 100, this.f623a);
            m733b(Trace.NULL, new Object[0]);
            m723a();
            if (this.f624b != null) {
                this.f624b.m1006a("    " + str, (Object) "<Image>");
            }
        }

        public void m731a(String str, byte[] bArr) throws IOException {
            m729a(str, str, "content/unknown");
            this.f623a.write(bArr);
            m733b(Trace.NULL, new Object[0]);
            m723a();
            if (this.f624b != null) {
                this.f624b.m1006a("    " + str, String.format(Locale.ROOT, "<Data: %d>", new Object[]{Integer.valueOf(bArr.length)}));
            }
        }

        public void m725a(String str, Uri uri, String str2) throws IOException {
            if (str2 == null) {
                str2 = "content/unknown";
            }
            m729a(str, str, str2);
            int a = Utility.m1088a(FacebookSdk.m1208f().getContentResolver().openInputStream(uri), this.f623a);
            m733b(Trace.NULL, new Object[0]);
            m723a();
            if (this.f624b != null) {
                this.f624b.m1006a("    " + str, String.format(Locale.ROOT, "<Data: %d>", new Object[]{Integer.valueOf(a)}));
            }
        }

        public void m726a(String str, ParcelFileDescriptor parcelFileDescriptor, String str2) throws IOException {
            int i;
            if (str2 == null) {
                str2 = "content/unknown";
            }
            m729a(str, str, str2);
            if (this.f623a instanceof ProgressNoopOutputStream) {
                ((ProgressNoopOutputStream) this.f623a).m1716a(parcelFileDescriptor.getStatSize());
                i = 0;
            } else {
                i = Utility.m1088a(new AutoCloseInputStream(parcelFileDescriptor), this.f623a) + 0;
            }
            m733b(Trace.NULL, new Object[0]);
            m723a();
            if (this.f624b != null) {
                this.f624b.m1006a("    " + str, String.format(Locale.ROOT, "<Data: %d>", new Object[]{Integer.valueOf(i)}));
            }
        }

        public void m723a() throws IOException {
            if (this.f626d) {
                this.f623a.write("&".getBytes());
                return;
            }
            m733b("--%s", "3i2ndDfv2rTHiSisAbouNdArYfORhtTPEefj3q2f");
        }

        public void m729a(String str, String str2, String str3) throws IOException {
            if (this.f626d) {
                this.f623a.write(String.format("%s=", new Object[]{str}).getBytes());
                return;
            }
            m732a("Content-Disposition: form-data; name=\"%s\"", str);
            if (str2 != null) {
                m732a("; filename=\"%s\"", str2);
            }
            m733b(Trace.NULL, new Object[0]);
            if (str3 != null) {
                m733b("%s: %s", HTTP.CONTENT_TYPE, str3);
            }
            m733b(Trace.NULL, new Object[0]);
        }

        public void m732a(String str, Object... objArr) throws IOException {
            if (this.f626d) {
                this.f623a.write(URLEncoder.encode(String.format(Locale.US, str, objArr), HTTP.UTF_8).getBytes());
                return;
            }
            if (this.f625c) {
                this.f623a.write("--".getBytes());
                this.f623a.write("3i2ndDfv2rTHiSisAbouNdArYfORhtTPEefj3q2f".getBytes());
                this.f623a.write("\r\n".getBytes());
                this.f625c = false;
            }
            this.f623a.write(String.format(str, objArr).getBytes());
        }

        public void m733b(String str, Object... objArr) throws IOException {
            m732a(str, objArr);
            if (!this.f626d) {
                m732a("\r\n", new Object[0]);
            }
        }
    }

    static {
        f627a = GraphRequest.class.getSimpleName();
        f629c = Pattern.compile("^/?v\\d+\\.\\d+/(.*)");
    }

    public GraphRequest() {
        this(null, null, null, null, null);
    }

    public GraphRequest(AccessToken accessToken, String str, Bundle bundle, HttpMethod httpMethod) {
        this(accessToken, str, bundle, httpMethod, null);
    }

    public GraphRequest(AccessToken accessToken, String str, Bundle bundle, HttpMethod httpMethod, C0153b c0153b) {
        this(accessToken, str, bundle, httpMethod, c0153b, null);
    }

    public GraphRequest(AccessToken accessToken, String str, Bundle bundle, HttpMethod httpMethod, C0153b c0153b, String str2) {
        this.f637j = true;
        this.f643p = false;
        this.f631d = accessToken;
        this.f633f = str;
        this.f642o = str2;
        m772a(c0153b);
        m773a(httpMethod);
        if (bundle != null) {
            this.f638k = new Bundle(bundle);
        } else {
            this.f638k = new Bundle();
        }
        if (this.f642o == null) {
            this.f642o = ServerProtocol.m1073d();
        }
    }

    public static GraphRequest m735a(AccessToken accessToken, String str, JSONObject jSONObject, C0153b c0153b) {
        GraphRequest graphRequest = new GraphRequest(accessToken, str, null, HttpMethod.POST, c0153b);
        graphRequest.m776a(jSONObject);
        return graphRequest;
    }

    public static GraphRequest m734a(AccessToken accessToken, String str, C0153b c0153b) {
        return new GraphRequest(accessToken, str, null, null, c0153b);
    }

    public final JSONObject m770a() {
        return this.f634g;
    }

    public final void m776a(JSONObject jSONObject) {
        this.f634g = jSONObject;
    }

    public final HttpMethod m778b() {
        return this.f632e;
    }

    public final void m773a(HttpMethod httpMethod) {
        if (this.f640m == null || httpMethod == HttpMethod.GET) {
            if (httpMethod == null) {
                httpMethod = HttpMethod.GET;
            }
            this.f632e = httpMethod;
            return;
        }
        throw new FacebookException("Can't change HTTP method on request with overridden URL.");
    }

    public final void m775a(String str) {
        this.f642o = str;
    }

    public final void m777a(boolean z) {
        this.f643p = z;
    }

    public final Bundle m779c() {
        return this.f638k;
    }

    public final void m771a(Bundle bundle) {
        this.f638k = bundle;
    }

    public final AccessToken m780d() {
        return this.f631d;
    }

    public final C0153b m781e() {
        return this.f639l;
    }

    public final void m772a(C0153b c0153b) {
        if (FacebookSdk.m1200a(LoggingBehavior.GRAPH_API_DEBUG_INFO) || FacebookSdk.m1200a(LoggingBehavior.GRAPH_API_DEBUG_WARNING)) {
            this.f639l = new C01552(this, c0153b);
        } else {
            this.f639l = c0153b;
        }
    }

    public final void m774a(Object obj) {
        this.f641n = obj;
    }

    public final Object m782f() {
        return this.f641n;
    }

    public final GraphResponse m783g() {
        return m736a(this);
    }

    public final GraphRequestAsyncTask m784h() {
        return m753b(this);
    }

    public static HttpURLConnection m737a(GraphRequestBatch graphRequestBatch) {
        try {
            URL url;
            if (graphRequestBatch.size() == 1) {
                url = new URL(graphRequestBatch.m1330a(0).m786j());
            } else {
                url = new URL(ServerProtocol.m1071b());
            }
            try {
                HttpURLConnection a = m738a(url);
                m745a(graphRequestBatch, a);
                return a;
            } catch (Throwable e) {
                throw new FacebookException("could not construct request body", e);
            } catch (Throwable e2) {
                throw new FacebookException("could not construct request body", e2);
            }
        } catch (Throwable e22) {
            throw new FacebookException("could not construct URL for request", e22);
        }
    }

    public static GraphResponse m736a(GraphRequest graphRequest) {
        List a = m741a(graphRequest);
        if (a != null && a.size() == 1) {
            return (GraphResponse) a.get(0);
        }
        throw new FacebookException("invalid state: expected a single response");
    }

    public static List<GraphResponse> m741a(GraphRequest... graphRequestArr) {
        Validate.m1146a((Object) graphRequestArr, "requests");
        return m740a(Arrays.asList(graphRequestArr));
    }

    public static List<GraphResponse> m740a(Collection<GraphRequest> collection) {
        return m755b(new GraphRequestBatch(collection));
    }

    public static List<GraphResponse> m755b(GraphRequestBatch graphRequestBatch) {
        Validate.m1150c(graphRequestBatch, "requests");
        try {
            return m739a(m737a(graphRequestBatch), graphRequestBatch);
        } catch (Throwable e) {
            List a = GraphResponse.m1351a(graphRequestBatch.m1339d(), null, new FacebookException(e));
            m746a(graphRequestBatch, a);
            return a;
        }
    }

    public static GraphRequestAsyncTask m753b(GraphRequest... graphRequestArr) {
        Validate.m1146a((Object) graphRequestArr, "requests");
        return m752b(Arrays.asList(graphRequestArr));
    }

    public static GraphRequestAsyncTask m752b(Collection<GraphRequest> collection) {
        return m757c(new GraphRequestBatch(collection));
    }

    public static GraphRequestAsyncTask m757c(GraphRequestBatch graphRequestBatch) {
        Validate.m1150c(graphRequestBatch, "requests");
        GraphRequestAsyncTask graphRequestAsyncTask = new GraphRequestAsyncTask(graphRequestBatch);
        graphRequestAsyncTask.m1324a();
        return graphRequestAsyncTask;
    }

    public static List<GraphResponse> m739a(HttpURLConnection httpURLConnection, GraphRequestBatch graphRequestBatch) {
        List a = GraphResponse.m1349a(httpURLConnection, graphRequestBatch);
        Utility.m1121a((URLConnection) httpURLConnection);
        if (graphRequestBatch.size() != a.size()) {
            throw new FacebookException(String.format(Locale.US, "Received %d responses while expecting %d", new Object[]{Integer.valueOf(a.size()), Integer.valueOf(graphRequestBatch.size())}));
        }
        m746a(graphRequestBatch, a);
        AccessTokenManager.m1171a().m1179d();
        return a;
    }

    public String toString() {
        return "{Request: " + " accessToken: " + (this.f631d == null ? "null" : this.f631d) + ", graphPath: " + this.f633f + ", graphObject: " + this.f634g + ", httpMethod: " + this.f632e + ", parameters: " + this.f638k + "}";
    }

    static void m746a(GraphRequestBatch graphRequestBatch, List<GraphResponse> list) {
        int size = graphRequestBatch.size();
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < size; i++) {
            GraphRequest a = graphRequestBatch.m1330a(i);
            if (a.f639l != null) {
                arrayList.add(new Pair(a.f639l, list.get(i)));
            }
        }
        if (arrayList.size() > 0) {
            Runnable c01563 = new C01563(arrayList, graphRequestBatch);
            Handler c = graphRequestBatch.m1338c();
            if (c == null) {
                c01563.run();
            } else {
                c.post(c01563);
            }
        }
    }

    private static HttpURLConnection m738a(URL url) throws IOException {
        HttpURLConnection httpURLConnection = (HttpURLConnection) HttpInstrumentation.openConnection(url.openConnection());
        httpURLConnection.setRequestProperty(HTTP.USER_AGENT, m769n());
        httpURLConnection.setRequestProperty(HttpHeaders.ACCEPT_LANGUAGE, Locale.getDefault().toString());
        httpURLConnection.setChunkedStreamingMode(0);
        return httpURLConnection;
    }

    private void m766k() {
        String b;
        if (this.f631d != null) {
            if (!this.f638k.containsKey("access_token")) {
                b = this.f631d.m698b();
                Logger.m1001a(b);
                this.f638k.putString("access_token", b);
            }
        } else if (!(this.f643p || this.f638k.containsKey("access_token"))) {
            b = FacebookSdk.m1210h();
            String j = FacebookSdk.m1212j();
            if (Utility.m1126a(b) || Utility.m1126a(j)) {
                Log.d(f627a, "Warning: Request without access token missing application ID or client token.");
            } else {
                this.f638k.putString("access_token", b + "|" + j);
            }
        }
        this.f638k.putString("sdk", AbstractSpiCall.ANDROID_CLIENT_TYPE);
        this.f638k.putString("format", "json");
        if (FacebookSdk.m1200a(LoggingBehavior.GRAPH_API_DEBUG_INFO)) {
            this.f638k.putString("debug", "info");
        } else if (FacebookSdk.m1200a(LoggingBehavior.GRAPH_API_DEBUG_WARNING)) {
            this.f638k.putString("debug", "warning");
        }
    }

    private String m754b(String str) {
        Builder encodedPath = new Builder().encodedPath(str);
        for (String str2 : this.f638k.keySet()) {
            Object obj = this.f638k.get(str2);
            if (obj == null) {
                obj = Trace.NULL;
            }
            if (m763e(obj)) {
                encodedPath.appendQueryParameter(str2, m765f(obj).toString());
            } else if (this.f632e == HttpMethod.GET) {
                throw new IllegalArgumentException(String.format(Locale.US, "Unsupported parameter type for GET request: %s", new Object[]{obj.getClass().getSimpleName()}));
            }
        }
        return encodedPath.toString();
    }

    final String m785i() {
        if (this.f640m != null) {
            throw new FacebookException("Can't override URL for a batch request");
        }
        String l = m767l();
        m766k();
        return m754b(l);
    }

    final String m786j() {
        if (this.f640m != null) {
            return this.f640m.toString();
        }
        String c;
        if (m778b() == HttpMethod.POST && this.f633f != null && this.f633f.endsWith("/videos")) {
            c = ServerProtocol.m1072c();
        } else {
            c = ServerProtocol.m1071b();
        }
        c = String.format("%s/%s", new Object[]{c, m767l()});
        m766k();
        return m754b(c);
    }

    private String m767l() {
        if (f629c.matcher(this.f633f).matches()) {
            return this.f633f;
        }
        return String.format("%s/%s", new Object[]{this.f642o, this.f633f});
    }

    private void m750a(JSONArray jSONArray, Map<String, C0160a> map) throws JSONException, IOException {
        JSONObject jSONObject = new JSONObject();
        if (this.f635h != null) {
            jSONObject.put(Constants.BUNDLE_NAME, this.f635h);
            jSONObject.put("omit_response_on_success", this.f637j);
        }
        if (this.f636i != null) {
            jSONObject.put("depends_on", this.f636i);
        }
        String i = m785i();
        jSONObject.put("relative_url", i);
        jSONObject.put("method", this.f632e);
        if (this.f631d != null) {
            Logger.m1001a(this.f631d.m698b());
        }
        Iterable arrayList = new ArrayList();
        for (String str : this.f638k.keySet()) {
            Object obj = this.f638k.get(str);
            if (m761d(obj)) {
                String format = String.format(Locale.ROOT, "%s%d", new Object[]{"file", Integer.valueOf(map.size())});
                arrayList.add(format);
                map.put(format, new C0160a(this, obj));
            }
        }
        if (!arrayList.isEmpty()) {
            jSONObject.put("attached_files", TextUtils.join(",", arrayList));
        }
        if (this.f634g != null) {
            Iterable arrayList2 = new ArrayList();
            m751a(this.f634g, i, new C01584(this, arrayList2));
            jSONObject.put("body", TextUtils.join("&", arrayList2));
        }
        jSONArray.put(jSONObject);
    }

    private static boolean m760d(GraphRequestBatch graphRequestBatch) {
        for (GraphRequestBatch graphRequestBatch2 : graphRequestBatch.m1340e()) {
            if (graphRequestBatch2 instanceof GraphRequestBatch) {
                return true;
            }
        }
        Iterator it = graphRequestBatch.iterator();
        while (it.hasNext()) {
            if (((GraphRequest) it.next()).m781e() instanceof C0162e) {
                return true;
            }
        }
        return false;
    }

    private static void m748a(HttpURLConnection httpURLConnection, boolean z) {
        if (z) {
            httpURLConnection.setRequestProperty(HTTP.CONTENT_TYPE, "application/x-www-form-urlencoded");
            httpURLConnection.setRequestProperty(HTTP.CONTENT_ENCODING, "gzip");
            return;
        }
        httpURLConnection.setRequestProperty(HTTP.CONTENT_TYPE, m768m());
    }

    private static boolean m762e(GraphRequestBatch graphRequestBatch) {
        Iterator it = graphRequestBatch.iterator();
        while (it.hasNext()) {
            GraphRequest graphRequest = (GraphRequest) it.next();
            for (String str : graphRequest.f638k.keySet()) {
                if (m761d(graphRequest.f638k.get(str))) {
                    return false;
                }
            }
        }
        return true;
    }

    static final void m745a(GraphRequestBatch graphRequestBatch, HttpURLConnection httpURLConnection) throws IOException, JSONException {
        Throwable th;
        Logger logger = new Logger(LoggingBehavior.REQUESTS, "Request");
        int size = graphRequestBatch.size();
        boolean e = m762e(graphRequestBatch);
        HttpMethod httpMethod = size == 1 ? graphRequestBatch.m1330a(0).f632e : HttpMethod.POST;
        httpURLConnection.setRequestMethod(httpMethod.name());
        m748a(httpURLConnection, e);
        Object url = httpURLConnection.getURL();
        logger.m1009c("Request:\n");
        logger.m1006a("Id", graphRequestBatch.m1337b());
        logger.m1006a("URL", url);
        logger.m1006a("Method", httpURLConnection.getRequestMethod());
        logger.m1006a(HTTP.USER_AGENT, httpURLConnection.getRequestProperty(HTTP.USER_AGENT));
        logger.m1006a(HTTP.CONTENT_TYPE, httpURLConnection.getRequestProperty(HTTP.CONTENT_TYPE));
        httpURLConnection.setConnectTimeout(graphRequestBatch.m1329a());
        httpURLConnection.setReadTimeout(graphRequestBatch.m1329a());
        if (httpMethod == HttpMethod.POST) {
            httpURLConnection.setDoOutput(true);
            OutputStream bufferedOutputStream;
            try {
                OutputStream progressNoopOutputStream;
                bufferedOutputStream = new BufferedOutputStream(httpURLConnection.getOutputStream());
                if (e) {
                    try {
                        bufferedOutputStream = new GZIPOutputStream(bufferedOutputStream);
                    } catch (Throwable th2) {
                        th = th2;
                        if (bufferedOutputStream != null) {
                            bufferedOutputStream.close();
                        }
                        throw th;
                    }
                }
                if (m760d(graphRequestBatch)) {
                    progressNoopOutputStream = new ProgressNoopOutputStream(graphRequestBatch.m1338c());
                    m744a(graphRequestBatch, null, size, url, progressNoopOutputStream, e);
                    progressNoopOutputStream = new ProgressOutputStream(bufferedOutputStream, graphRequestBatch, progressNoopOutputStream.m1718b(), (long) progressNoopOutputStream.m1715a());
                } else {
                    progressNoopOutputStream = bufferedOutputStream;
                }
                try {
                    m744a(graphRequestBatch, logger, size, url, progressNoopOutputStream, e);
                    if (progressNoopOutputStream != null) {
                        progressNoopOutputStream.close();
                    }
                    logger.m1005a();
                    return;
                } catch (Throwable th3) {
                    th = th3;
                    bufferedOutputStream = progressNoopOutputStream;
                    if (bufferedOutputStream != null) {
                        bufferedOutputStream.close();
                    }
                    throw th;
                }
            } catch (Throwable th4) {
                th = th4;
                bufferedOutputStream = null;
                if (bufferedOutputStream != null) {
                    bufferedOutputStream.close();
                }
                throw th;
            }
        }
        logger.m1005a();
    }

    private static void m744a(GraphRequestBatch graphRequestBatch, Logger logger, int i, URL url, OutputStream outputStream, boolean z) throws IOException, JSONException {
        C0163f c0163f = new C0163f(outputStream, logger, z);
        String f;
        if (i == 1) {
            GraphRequest a = graphRequestBatch.m1330a(0);
            Map hashMap = new HashMap();
            for (String f2 : a.f638k.keySet()) {
                Object obj = a.f638k.get(f2);
                if (m761d(obj)) {
                    hashMap.put(f2, new C0160a(a, obj));
                }
            }
            if (logger != null) {
                logger.m1009c("  Parameters:\n");
            }
            m742a(a.f638k, c0163f, a);
            if (logger != null) {
                logger.m1009c("  Attachments:\n");
            }
            m749a(hashMap, c0163f);
            if (a.f634g != null) {
                m751a(a.f634g, url.getPath(), (C0157d) c0163f);
                return;
            }
            return;
        }
        f2 = m764f(graphRequestBatch);
        if (Utility.m1126a(f2)) {
            throw new FacebookException("App ID was not specified at the request or Settings.");
        }
        c0163f.m728a("batch_app_id", f2);
        Map hashMap2 = new HashMap();
        m743a(c0163f, (Collection) graphRequestBatch, hashMap2);
        if (logger != null) {
            logger.m1009c("  Attachments:\n");
        }
        m749a(hashMap2, c0163f);
    }

    private static boolean m759c(String str) {
        Matcher matcher = f629c.matcher(str);
        if (matcher.matches()) {
            str = matcher.group(1);
        }
        if (str.startsWith("me/") || str.startsWith("/me/")) {
            return true;
        }
        return false;
    }

    private static void m751a(JSONObject jSONObject, String str, C0157d c0157d) throws IOException {
        Object obj;
        if (m759c(str)) {
            int indexOf = str.indexOf(":");
            int indexOf2 = str.indexOf("?");
            Object obj2 = (indexOf <= 3 || (indexOf2 != -1 && indexOf >= indexOf2)) ? null : 1;
            obj = obj2;
        } else {
            obj = null;
        }
        Iterator keys = jSONObject.keys();
        while (keys.hasNext()) {
            boolean z;
            String str2 = (String) keys.next();
            Object opt = jSONObject.opt(str2);
            if (obj == null || !str2.equalsIgnoreCase("image")) {
                z = false;
            } else {
                z = true;
            }
            m747a(str2, opt, c0157d, z);
        }
    }

    private static void m747a(String str, Object obj, C0157d c0157d, boolean z) throws IOException {
        Class cls = obj.getClass();
        if (JSONObject.class.isAssignableFrom(cls)) {
            JSONObject jSONObject = (JSONObject) obj;
            if (z) {
                Iterator keys = jSONObject.keys();
                while (keys.hasNext()) {
                    m747a(String.format("%s[%s]", new Object[]{str, (String) keys.next()}), jSONObject.opt((String) keys.next()), c0157d, z);
                }
            } else if (jSONObject.has("id")) {
                m747a(str, jSONObject.optString("id"), c0157d, z);
            } else if (jSONObject.has("url")) {
                m747a(str, jSONObject.optString("url"), c0157d, z);
            } else if (jSONObject.has("fbsdk:create_object")) {
                m747a(str, !(jSONObject instanceof JSONObject) ? jSONObject.toString() : JSONObjectInstrumentation.toString(jSONObject), c0157d, z);
            }
        } else if (JSONArray.class.isAssignableFrom(cls)) {
            JSONArray jSONArray = (JSONArray) obj;
            int length = jSONArray.length();
            for (int i = 0; i < length; i++) {
                m747a(String.format(Locale.ROOT, "%s[%d]", new Object[]{str, Integer.valueOf(i)}), jSONArray.opt(i), c0157d, z);
            }
        } else if (String.class.isAssignableFrom(cls) || Number.class.isAssignableFrom(cls) || Boolean.class.isAssignableFrom(cls)) {
            c0157d.m712a(str, obj.toString());
        } else if (Date.class.isAssignableFrom(cls)) {
            c0157d.m712a(str, new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.US).format((Date) obj));
        }
    }

    private static void m742a(Bundle bundle, C0163f c0163f, GraphRequest graphRequest) throws IOException {
        for (String str : bundle.keySet()) {
            Object obj = bundle.get(str);
            if (m763e(obj)) {
                c0163f.m727a(str, obj, graphRequest);
            }
        }
    }

    private static void m749a(Map<String, C0160a> map, C0163f c0163f) throws IOException {
        for (String str : map.keySet()) {
            C0160a c0160a = (C0160a) map.get(str);
            if (m761d(c0160a.m719b())) {
                c0163f.m727a(str, c0160a.m719b(), c0160a.m718a());
            }
        }
    }

    private static void m743a(C0163f c0163f, Collection<GraphRequest> collection, Map<String, C0160a> map) throws JSONException, IOException {
        JSONArray jSONArray = new JSONArray();
        for (GraphRequest a : collection) {
            a.m750a(jSONArray, (Map) map);
        }
        c0163f.m730a("batch", jSONArray, (Collection) collection);
    }

    private static String m768m() {
        return String.format("multipart/form-data; boundary=%s", new Object[]{"3i2ndDfv2rTHiSisAbouNdArYfORhtTPEefj3q2f"});
    }

    private static String m769n() {
        if (f630q == null) {
            f630q = String.format("%s.%s", new Object[]{"FBAndroidSDK", "4.0.0"});
        }
        return f630q;
    }

    private static String m764f(GraphRequestBatch graphRequestBatch) {
        if (!Utility.m1126a(graphRequestBatch.m1341f())) {
            return graphRequestBatch.m1341f();
        }
        Iterator it = graphRequestBatch.iterator();
        while (it.hasNext()) {
            AccessToken accessToken = ((GraphRequest) it.next()).f631d;
            if (accessToken != null) {
                String g = accessToken.m703g();
                if (g != null) {
                    return g;
                }
            }
        }
        if (Utility.m1126a(f628b)) {
            return FacebookSdk.m1210h();
        }
        return f628b;
    }

    private static boolean m761d(Object obj) {
        return (obj instanceof Bitmap) || (obj instanceof byte[]) || (obj instanceof Uri) || (obj instanceof ParcelFileDescriptor) || (obj instanceof ParcelableResourceWithMimeType);
    }

    private static boolean m763e(Object obj) {
        return (obj instanceof String) || (obj instanceof Boolean) || (obj instanceof Number) || (obj instanceof Date);
    }

    private static String m765f(Object obj) {
        if (obj instanceof String) {
            return (String) obj;
        }
        if ((obj instanceof Boolean) || (obj instanceof Number)) {
            return obj.toString();
        }
        if (obj instanceof Date) {
            return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.US).format(obj);
        }
        throw new IllegalArgumentException("Unsupported parameter type.");
    }
}
