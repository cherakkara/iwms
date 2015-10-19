package com.android.volley;

import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.text.TextUtils;
import com.android.volley.Cache.Cache;
import com.android.volley.Response.Response;
import com.android.volley.VolleyLog.VolleyLog;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.Map;
import java.util.Map.Entry;

/* renamed from: com.android.volley.l */
public abstract class Request<T> implements Comparable<Request<T>> {
    private static final String DEFAULT_PARAMS_ENCODING = "UTF-8";
    private static final long SLOW_REQUEST_THRESHOLD_MS = 3000;
    private Cache mCacheEntry;
    private boolean mCanceled;
    private final int mDefaultTrafficStatsTag;
    private final Response mErrorListener;
    private final VolleyLog mEventLog;
    private final int mMethod;
    private String mRedirectUrl;
    private long mRequestBirthTime;
    private RequestQueue mRequestQueue;
    private boolean mResponseDelivered;
    private RetryPolicy mRetryPolicy;
    private Integer mSequence;
    private boolean mShouldCache;
    private Object mTag;
    private final String mUrl;

    /* renamed from: com.android.volley.l.1 */
    class Request implements Runnable {
        final /* synthetic */ String f502a;
        final /* synthetic */ long f503b;
        final /* synthetic */ Request f504c;

        Request(Request request, String str, long j) {
            this.f504c = request;
            this.f502a = str;
            this.f503b = j;
        }

        public void run() {
            this.f504c.mEventLog.m591a(this.f502a, this.f503b);
            this.f504c.mEventLog.m590a(toString());
        }
    }

    /* renamed from: com.android.volley.l.a */
    public enum Request {
        LOW,
        NORMAL,
        HIGH,
        IMMEDIATE
    }

    protected abstract void deliverResponse(T t);

    protected abstract Response<T> parseNetworkResponse(NetworkResponse networkResponse);

    @Deprecated
    public Request(String str, Response response) {
        this(-1, str, response);
    }

    public Request(int i, String str, Response response) {
        VolleyLog volleyLog;
        if (VolleyLog.f529a) {
            volleyLog = new VolleyLog();
        } else {
            volleyLog = null;
        }
        this.mEventLog = volleyLog;
        this.mShouldCache = true;
        this.mCanceled = false;
        this.mResponseDelivered = false;
        this.mRequestBirthTime = 0;
        this.mCacheEntry = null;
        this.mMethod = i;
        this.mUrl = str;
        this.mErrorListener = response;
        setRetryPolicy(new DefaultRetryPolicy());
        this.mDefaultTrafficStatsTag = Request.findDefaultTrafficStatsTag(str);
    }

    public int getMethod() {
        return this.mMethod;
    }

    public Request<?> setTag(Object obj) {
        this.mTag = obj;
        return this;
    }

    public Object getTag() {
        return this.mTag;
    }

    public Response getErrorListener() {
        return this.mErrorListener;
    }

    public int getTrafficStatsTag() {
        return this.mDefaultTrafficStatsTag;
    }

    private static int findDefaultTrafficStatsTag(String str) {
        if (!TextUtils.isEmpty(str)) {
            Uri parse = Uri.parse(str);
            if (parse != null) {
                String host = parse.getHost();
                if (host != null) {
                    return host.hashCode();
                }
            }
        }
        return 0;
    }

    public Request<?> setRetryPolicy(RetryPolicy retryPolicy) {
        this.mRetryPolicy = retryPolicy;
        return this;
    }

    public void addMarker(String str) {
        if (VolleyLog.f529a) {
            this.mEventLog.m591a(str, Thread.currentThread().getId());
        } else if (this.mRequestBirthTime == 0) {
            this.mRequestBirthTime = SystemClock.elapsedRealtime();
        }
    }

    void finish(String str) {
        if (this.mRequestQueue != null) {
            this.mRequestQueue.m582b(this);
        }
        if (VolleyLog.f529a) {
            long id = Thread.currentThread().getId();
            if (Looper.myLooper() != Looper.getMainLooper()) {
                new Handler(Looper.getMainLooper()).post(new Request(this, str, id));
                return;
            }
            this.mEventLog.m591a(str, id);
            this.mEventLog.m590a(toString());
            return;
        }
        if (SystemClock.elapsedRealtime() - this.mRequestBirthTime >= SLOW_REQUEST_THRESHOLD_MS) {
            VolleyLog.m594b("%d ms: %s", Long.valueOf(SystemClock.elapsedRealtime() - this.mRequestBirthTime), toString());
        }
    }

    public Request<?> setRequestQueue(RequestQueue requestQueue) {
        this.mRequestQueue = requestQueue;
        return this;
    }

    public final Request<?> setSequence(int i) {
        this.mSequence = Integer.valueOf(i);
        return this;
    }

    public final int getSequence() {
        if (this.mSequence != null) {
            return this.mSequence.intValue();
        }
        throw new IllegalStateException("getSequence called before setSequence");
    }

    public String getUrl() {
        return this.mRedirectUrl != null ? this.mRedirectUrl : this.mUrl;
    }

    public String getOriginUrl() {
        return this.mUrl;
    }

    public void setRedirectUrl(String str) {
        this.mRedirectUrl = str;
    }

    public String getCacheKey() {
        return getUrl();
    }

    public Request<?> setCacheEntry(Cache cache) {
        this.mCacheEntry = cache;
        return this;
    }

    public Cache getCacheEntry() {
        return this.mCacheEntry;
    }

    public void cancel() {
        this.mCanceled = true;
    }

    public boolean isCanceled() {
        return this.mCanceled;
    }

    public Map<String, String> getHeaders() throws AuthFailureError {
        return Collections.emptyMap();
    }

    @Deprecated
    protected Map<String, String> getPostParams() throws AuthFailureError {
        return getParams();
    }

    @Deprecated
    protected String getPostParamsEncoding() {
        return getParamsEncoding();
    }

    @Deprecated
    public String getPostBodyContentType() {
        return getBodyContentType();
    }

    @Deprecated
    public byte[] getPostBody() throws AuthFailureError {
        Map postParams = getPostParams();
        if (postParams == null || postParams.size() <= 0) {
            return null;
        }
        return encodeParameters(postParams, getPostParamsEncoding());
    }

    protected Map<String, String> getParams() throws AuthFailureError {
        return null;
    }

    protected String getParamsEncoding() {
        return DEFAULT_PARAMS_ENCODING;
    }

    public String getBodyContentType() {
        return "application/x-www-form-urlencoded; charset=" + getParamsEncoding();
    }

    public byte[] getBody() throws AuthFailureError {
        Map params = getParams();
        if (params == null || params.size() <= 0) {
            return null;
        }
        return encodeParameters(params, getParamsEncoding());
    }

    private byte[] encodeParameters(Map<String, String> map, String str) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            for (Entry entry : map.entrySet()) {
                stringBuilder.append(URLEncoder.encode((String) entry.getKey(), str));
                stringBuilder.append('=');
                stringBuilder.append(URLEncoder.encode((String) entry.getValue(), str));
                stringBuilder.append('&');
            }
            return stringBuilder.toString().getBytes(str);
        } catch (Throwable e) {
            throw new RuntimeException("Encoding not supported: " + str, e);
        }
    }

    public final Request<?> setShouldCache(boolean z) {
        this.mShouldCache = z;
        return this;
    }

    public final boolean shouldCache() {
        return this.mShouldCache;
    }

    public Request getPriority() {
        return Request.NORMAL;
    }

    public final int getTimeoutMs() {
        return this.mRetryPolicy.getCurrentTimeout();
    }

    public RetryPolicy getRetryPolicy() {
        return this.mRetryPolicy;
    }

    public void markDelivered() {
        this.mResponseDelivered = true;
    }

    public boolean hasHadResponseDelivered() {
        return this.mResponseDelivered;
    }

    protected VolleyError parseNetworkError(VolleyError volleyError) {
        return volleyError;
    }

    public void deliverError(VolleyError volleyError) {
        if (this.mErrorListener != null) {
            this.mErrorListener.m584a(volleyError);
        }
    }

    public int compareTo(Request<T> request) {
        Request priority = getPriority();
        Request priority2 = request.getPriority();
        if (priority == priority2) {
            return this.mSequence.intValue() - request.mSequence.intValue();
        }
        return priority2.ordinal() - priority.ordinal();
    }

    public String toString() {
        return (this.mCanceled ? "[X] " : "[ ] ") + getUrl() + " " + ("0x" + Integer.toHexString(getTrafficStatsTag())) + " " + getPriority() + " " + this.mSequence;
    }
}
