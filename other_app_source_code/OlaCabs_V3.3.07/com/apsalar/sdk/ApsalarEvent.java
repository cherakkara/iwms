package com.apsalar.sdk;

import android.content.Context;
import com.newrelic.agent.android.analytics.AnalyticAttribute;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import com.newrelic.agent.android.instrumentation.Trace;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.ProtocolException;
import java.net.SocketTimeoutException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.apache.http.protocol.HTTP;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ApEvent */
class ApsalarEvent implements ApsalarAPI, ApsalarJSON {
    static final String TAG = "Apsalar SDK/Event";
    Context ctx;
    protected String eventData;
    protected String eventName;
    protected long eventTime;
    protected int eventType;
    protected ApsalarSessionInfo info;
    protected String old_k;
    protected String old_u;
    protected String returnData;
    protected JSONObject returnDataJSON;
    protected String url;
    protected String urlbase;

    public int getEventType() {
        return this.eventType;
    }

    public String getEventName() {
        return this.eventName;
    }

    public String getEventData() {
        return this.eventData;
    }

    public long getEventTime() {
        return this.eventTime;
    }

    protected double lagSeconds() {
        return ((double) (System.currentTimeMillis() - this.eventTime)) * 0.001d;
    }

    protected void init(Context context) {
        this.ctx = context;
        this.eventType = 3;
        this.eventTime = System.currentTimeMillis();
    }

    protected ApsalarEvent(Context context) {
        this.ctx = null;
        this.urlbase = "https://e.apsalar.com/api/v1/event";
        this.info = null;
        this.url = Trace.NULL;
        this.eventTime = System.currentTimeMillis();
        this.eventName = Trace.NULL;
        this.eventData = Trace.NULL;
        this.eventType = 0;
        this.returnData = null;
        this.returnDataJSON = null;
        init(context);
    }

    protected ApsalarEvent(Context context, ApsalarSessionInfo apsalarSessionInfo) {
        this.ctx = null;
        this.urlbase = "https://e.apsalar.com/api/v1/event";
        this.info = null;
        this.url = Trace.NULL;
        this.eventTime = System.currentTimeMillis();
        this.eventName = Trace.NULL;
        this.eventData = Trace.NULL;
        this.eventType = 0;
        this.returnData = null;
        this.returnDataJSON = null;
        ApSingleton instance = ApSingleton.getInstance(context);
        init(context);
        this.info = apsalarSessionInfo;
        if (this.info == null) {
            instance.getClass();
        }
    }

    protected ApsalarEvent(Context context, ApsalarSessionInfo apsalarSessionInfo, String str) {
        this.ctx = null;
        this.urlbase = "https://e.apsalar.com/api/v1/event";
        this.info = null;
        this.url = Trace.NULL;
        this.eventTime = System.currentTimeMillis();
        this.eventName = Trace.NULL;
        this.eventData = Trace.NULL;
        this.eventType = 0;
        this.returnData = null;
        this.returnDataJSON = null;
        ApSingleton instance = ApSingleton.getInstance(context);
        init(context);
        this.info = apsalarSessionInfo;
        this.eventName = str;
        if (this.info == null) {
            instance.getClass();
        }
    }

    protected ApsalarEvent(Context context, ApsalarSessionInfo apsalarSessionInfo, String str, String str2) {
        this.ctx = null;
        this.urlbase = "https://e.apsalar.com/api/v1/event";
        this.info = null;
        this.url = Trace.NULL;
        this.eventTime = System.currentTimeMillis();
        this.eventName = Trace.NULL;
        this.eventData = Trace.NULL;
        this.eventType = 0;
        this.returnData = null;
        this.returnDataJSON = null;
        ApSingleton instance = ApSingleton.getInstance(context);
        init(context);
        this.info = apsalarSessionInfo;
        this.eventName = str;
        this.eventData = str2;
        if (this.info == null) {
            instance.getClass();
        }
    }

    protected ApsalarEvent(Context context, ApsalarSessionInfo apsalarSessionInfo, JSONObject jSONObject) {
        this.ctx = null;
        this.urlbase = "https://e.apsalar.com/api/v1/event";
        this.info = null;
        this.url = Trace.NULL;
        this.eventTime = System.currentTimeMillis();
        this.eventName = Trace.NULL;
        this.eventData = Trace.NULL;
        this.eventType = 0;
        this.returnData = null;
        this.returnDataJSON = null;
        ApSingleton instance = ApSingleton.getInstance(context);
        init(context);
        this.info = apsalarSessionInfo;
        if (this.info == null) {
            instance.getClass();
        }
        try {
            this.eventType = jSONObject.getInt(AnalyticAttribute.EVENT_TYPE_ATTRIBUTE);
            this.eventTime = jSONObject.getLong("eventTime");
            this.eventName = jSONObject.getString("eventName");
            this.eventData = jSONObject.getString("eventData");
        } catch (JSONException e) {
            instance.getClass();
            instance.incrExceptionCount();
        }
    }

    protected ApsalarEvent(Context context, ApsalarSessionInfo apsalarSessionInfo, String str, long j, String str2) {
        this.ctx = null;
        this.urlbase = "https://e.apsalar.com/api/v1/event";
        this.info = null;
        this.url = Trace.NULL;
        this.eventTime = System.currentTimeMillis();
        this.eventName = Trace.NULL;
        this.eventData = Trace.NULL;
        this.eventType = 0;
        this.returnData = null;
        this.returnDataJSON = null;
        ApSingleton instance = ApSingleton.getInstance(context);
        init(context);
        this.info = apsalarSessionInfo;
        this.eventName = str;
        this.eventData = str2;
        if (this.info == null) {
            instance.getClass();
        }
        if (j != 0) {
            this.eventTime = j;
        }
    }

    protected String makeHash(String str, String str2) {
        ApSingleton instance = ApSingleton.getInstance(this.ctx);
        String str3 = null;
        try {
            MessageDigest instance2 = MessageDigest.getInstance("SHA-1");
            instance2.update(str.getBytes(HTTP.UTF_8));
            instance2.update(str2.getBytes(HTTP.UTF_8));
            str3 = Apsalar.hexDigest(instance2.digest());
        } catch (NoSuchAlgorithmException e) {
            instance.getClass();
            instance.incrExceptionCount();
        } catch (UnsupportedEncodingException e2) {
            instance.getClass();
            instance.incrExceptionCount();
        }
        return str3;
    }

    protected String makeQueryString() {
        ApSingleton instance = ApSingleton.getInstance(this.ctx);
        String str = Trace.NULL;
        try {
            String deviceId = Apsalar.getDeviceId(instance.canonicalKeyspace);
            String str2 = instance.canonicalKeyspace;
            str = "?a=" + URLEncoder.encode(this.info.apiKey, HTTP.UTF_8) + "&av=" + URLEncoder.encode(this.info.appVersion, HTTP.UTF_8) + "&e=" + URLEncoder.encode(this.eventData, HTTP.UTF_8) + "&i=" + URLEncoder.encode(this.info.clsPackage, HTTP.UTF_8) + "&n=" + URLEncoder.encode(this.eventName, HTTP.UTF_8) + "&p=" + URLEncoder.encode(this.info.platform, HTTP.UTF_8) + "&rt=" + URLEncoder.encode(this.info.retType, HTTP.UTF_8) + "&s=" + URLEncoder.encode(this.info.sessionId, HTTP.UTF_8) + "&sdk=" + URLEncoder.encode("Apsalar/" + this.info.sdkVersion, HTTP.UTF_8) + "&t=" + (((double) (this.eventTime - this.info.sessionStart)) * 0.001d) + "&u=" + URLEncoder.encode(deviceId, HTTP.UTF_8) + "&k=" + URLEncoder.encode(str2, HTTP.UTF_8);
            this.old_k = str2;
            this.old_u = deviceId;
            return str;
        } catch (UnsupportedEncodingException e) {
            instance.getClass();
            instance.incrExceptionCount();
            return str;
        }
    }

    protected boolean makeURL() {
        ApSingleton instance = ApSingleton.getInstance(this.ctx);
        this.url = makeQueryString();
        if (this.url.length() < ApSingleton.maxUrlSize) {
            return true;
        }
        instance.getClass();
        return false;
    }

    public int REST() {
        return REST(Boolean.valueOf(true));
    }

    public int REST(Boolean bool) {
        ApSingleton instance = ApSingleton.getInstance(this.ctx);
        instance.getClass();
        if (makeURL()) {
            String str = this.url + "&lag=" + lagSeconds();
            String makeHash = makeHash(this.info.secret, str);
            if (makeHash == null) {
                return -1;
            }
            try {
                this.returnData = ApsalarHttpClient.get(this.urlbase + str + "&h=" + makeHash);
                if (this.returnData == null) {
                    return 1;
                }
                instance.incrSentEventsCount();
                if (!bool.booleanValue()) {
                    return 1;
                }
                try {
                    this.returnDataJSON = JSONObjectInstrumentation.init(this.returnData);
                    try {
                        if (this.returnDataJSON.getString(NotificationCompatApi21.CATEGORY_STATUS).toLowerCase().equals("ok")) {
                            return 1;
                        }
                        instance.getClass();
                        return 0;
                    } catch (JSONException e) {
                        instance.incrExceptionCount();
                        if (this.eventType == 1) {
                            return ApsalarSession.handleRedirect(this.returnDataJSON);
                        }
                        instance.getClass();
                        return 0;
                    }
                } catch (JSONException e2) {
                    instance.incrExceptionCount();
                    instance.getClass();
                    return 0;
                }
            } catch (ProtocolException e3) {
                instance.getClass();
                instance.incrExceptionCount();
                return 0;
            } catch (SocketTimeoutException e4) {
                instance.getClass();
                instance.incrNetworkErrorCount();
                instance.incrExceptionCount();
                return 0;
            } catch (IOException e5) {
                instance.getClass();
                instance.incrNetworkErrorCount();
                instance.incrExceptionCount();
                return 0;
            }
        }
        instance.getClass();
        return -1;
    }

    public boolean changeDeviceKeys(String str, String str2, String str3, String str4, boolean z) {
        boolean z2;
        boolean z3 = false;
        ApSingleton instance = ApSingleton.getInstance(this.ctx);
        instance.getClass();
        if (str3.equals(str)) {
            z2 = false;
        } else {
            instance.getClass();
            z2 = true;
        }
        if (!str4.equals(str2)) {
            instance.getClass();
            z3 = true;
        } else if (z2) {
            instance.getClass();
        } else {
            z3 = z2;
        }
        if (z3) {
            instance.getClass();
            if (str.equals("ANDI")) {
                instance.resolved_ANDI = true;
                instance.ANDI = str2;
                instance.getClass();
            } else if (str.equals("AIFA")) {
                instance.resolved_AIFA = true;
                instance.old_AIFA = instance.AIFA;
                instance.AIFA = str2;
                instance.AIFA_changed = true;
                instance.getClass();
            }
            if (!((z || instance.canonicalKeyspace.equals(str)) && instance.canonicalDeviceId.equals(str2))) {
                instance.canonicalKeyspace = str;
                instance.canonicalDeviceId = str2;
                instance.getClass();
            }
        }
        return z3;
    }

    public ApsalarSessionInfo getInfo() {
        return this.info;
    }

    public JSONObject toJSON() {
        ApSingleton instance = ApSingleton.getInstance(this.ctx);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(AnalyticAttribute.EVENT_TYPE_ATTRIBUTE, this.eventType);
            jSONObject.put("eventTime", this.eventTime);
            jSONObject.put("eventName", this.eventName);
            jSONObject.put("eventData", this.eventData);
        } catch (JSONException e) {
            instance.incrExceptionCount();
            instance.getClass();
        }
        return jSONObject;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    void constructDK() {
        /*
        r18 = this;
        r0 = r18;
        r1 = r0.ctx;
        r15 = com.apsalar.sdk.ApSingleton.getInstance(r1);
        r15.getClass();
        r1 = 0;
        r2 = r15.desired;
        if (r2 == 0) goto L_0x0016;
    L_0x0010:
        r1 = r15.desired;
        r1 = r1.length();
    L_0x0016:
        r3 = "";
        r2 = 0;
        r4 = 0;
        r5 = r15.AIFA_changed;
        if (r5 == 0) goto L_0x0281;
    L_0x001e:
        r5 = r15.old_AIFA;
        r6 = r15.AIFA;
        r5 = r5.equals(r6);
        if (r5 != 0) goto L_0x0281;
    L_0x0028:
        r15.getClass();
        r4 = r15.canonicalKeyspace;
        r5 = "AIFA";
        r4 = r4.equals(r5);
        if (r4 == 0) goto L_0x006f;
    L_0x0035:
        r4 = r15.AIFA;
        r5 = r15.canonicalDeviceId;
        r4 = r4.equals(r5);
        if (r4 == 0) goto L_0x006f;
    L_0x003f:
        r15.getClass();
    L_0x0042:
        r4 = 1;
        r17 = r4;
        r4 = r3;
        r3 = r2;
        r2 = r17;
    L_0x0049:
        r15.getClass();
        r5 = r15.RESOLVE_ALL_AVAILABLE;
        if (r5 == 0) goto L_0x009c;
    L_0x0050:
        r5 = 4;
    L_0x0051:
        r11 = 0;
        r10 = 0;
        r9 = 0;
        r8 = 0;
        r7 = 0;
        r6 = 0;
        r14 = r6;
        r13 = r5;
        r12 = r3;
        r5 = r7;
        r6 = r8;
        r7 = r9;
        r8 = r10;
        r9 = r11;
    L_0x005f:
        r3 = r1 + r13;
        if (r14 > r3) goto L_0x0139;
    L_0x0063:
        r3 = r1 + 4;
        if (r14 != r3) goto L_0x00cd;
    L_0x0067:
        if (r5 == 0) goto L_0x009e;
    L_0x0069:
        r3 = r13;
    L_0x006a:
        r10 = r14 + 1;
        r14 = r10;
        r13 = r3;
        goto L_0x005f;
    L_0x006f:
        r2 = "AIFA";
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r4 = "[{\"k\":\"";
        r3 = r3.append(r4);
        r2 = r3.append(r2);
        r3 = "\", \"v\":\"";
        r2 = r2.append(r3);
        r3 = r15.old_AIFA;
        r2 = r2.append(r3);
        r3 = "\"},";
        r2 = r2.append(r3);
        r2 = r2.toString();
        r15.getClass();
        r3 = r15.old_AIFA;
        goto L_0x0042;
    L_0x009c:
        r5 = 0;
        goto L_0x0051;
    L_0x009e:
        r3 = "BMAC";
    L_0x00a0:
        r11 = com.apsalar.sdk.Apsalar.getDeviceId(r3);
        r10 = r15.canonicalKeyspace;
        r16 = "AIFA";
        r0 = r16;
        r16 = r3.equals(r0);
        if (r16 == 0) goto L_0x01cb;
    L_0x00b0:
        r10 = r15.AIFA;
        if (r10 == 0) goto L_0x019a;
    L_0x00b4:
        r10 = r15.AIFA;
    L_0x00b6:
        r11 = "AIFA";
        r15.getClass();
        r16 = r10.length();
        if (r16 == 0) goto L_0x027c;
    L_0x00c1:
        r16 = "None";
        r0 = r16;
        r16 = r10.equals(r0);
        if (r16 == 0) goto L_0x01a0;
    L_0x00cb:
        r3 = r13;
        goto L_0x006a;
    L_0x00cd:
        r3 = r1 + 3;
        if (r14 != r3) goto L_0x00d8;
    L_0x00d1:
        if (r6 == 0) goto L_0x00d5;
    L_0x00d3:
        r3 = r13;
        goto L_0x006a;
    L_0x00d5:
        r3 = "MAC1";
        goto L_0x00a0;
    L_0x00d8:
        r3 = r1 + 2;
        if (r14 != r3) goto L_0x00e3;
    L_0x00dc:
        if (r7 == 0) goto L_0x00e0;
    L_0x00de:
        r3 = r13;
        goto L_0x006a;
    L_0x00e0:
        r3 = "IMEI";
        goto L_0x00a0;
    L_0x00e3:
        r3 = r1 + 1;
        if (r14 != r3) goto L_0x00ee;
    L_0x00e7:
        if (r8 == 0) goto L_0x00eb;
    L_0x00e9:
        r3 = r13;
        goto L_0x006a;
    L_0x00eb:
        r3 = "ANDI";
        goto L_0x00a0;
    L_0x00ee:
        if (r14 != r1) goto L_0x00f8;
    L_0x00f0:
        if (r9 == 0) goto L_0x00f5;
    L_0x00f2:
        r3 = r13;
        goto L_0x006a;
    L_0x00f5:
        r3 = "AIFA";
        goto L_0x00a0;
    L_0x00f8:
        r3 = r15.desired;	 Catch:{ JSONException -> 0x0132 }
        r3 = r3.getString(r14);	 Catch:{ JSONException -> 0x0132 }
        r10 = "BMAC";
        r10 = r3.equals(r10);	 Catch:{ JSONException -> 0x0132 }
        if (r10 == 0) goto L_0x0108;
    L_0x0106:
        r5 = 1;
        goto L_0x00a0;
    L_0x0108:
        r10 = "MAC1";
        r10 = r3.equals(r10);	 Catch:{ JSONException -> 0x0132 }
        if (r10 == 0) goto L_0x0112;
    L_0x0110:
        r6 = 1;
        goto L_0x00a0;
    L_0x0112:
        r10 = "IMEI";
        r10 = r3.equals(r10);	 Catch:{ JSONException -> 0x0132 }
        if (r10 == 0) goto L_0x011c;
    L_0x011a:
        r7 = 1;
        goto L_0x00a0;
    L_0x011c:
        r10 = "ANDI";
        r10 = r3.equals(r10);	 Catch:{ JSONException -> 0x0132 }
        if (r10 == 0) goto L_0x0127;
    L_0x0124:
        r8 = 1;
        goto L_0x00a0;
    L_0x0127:
        r10 = "AIFA";
        r10 = r3.equals(r10);	 Catch:{ JSONException -> 0x0132 }
        if (r10 == 0) goto L_0x00a0;
    L_0x012f:
        r9 = 1;
        goto L_0x00a0;
    L_0x0132:
        r1 = move-exception;
        r15.incrExceptionCount();
        r15.getClass();
    L_0x0139:
        r15.getClass();
        if (r12 == 0) goto L_0x0279;
    L_0x013e:
        r1 = r12.length();
        if (r1 == 0) goto L_0x0279;
    L_0x0144:
        r1 = r12.length();
        r1 = r1 + -1;
        r1 = r12.charAt(r1);
        r2 = 44;
        if (r1 != r2) goto L_0x015d;
    L_0x0152:
        r1 = 0;
        r2 = r12.length();
        r2 = r2 + -1;
        r12 = r12.substring(r1, r2);
    L_0x015d:
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r1 = r1.append(r12);
        r2 = "]";
        r1 = r1.append(r2);
        r12 = r1.toString();
        r1 = r12;
    L_0x0171:
        if (r1 != 0) goto L_0x0175;
    L_0x0173:
        r1 = "[]";
    L_0x0175:
        r15.dk = r1;
        r15.getClass();
        r1 = r15.canonicalKeyspace;
        if (r1 == 0) goto L_0x0262;
    L_0x017e:
        r15.getClass();
        r1 = r15.canonicalKeyspace;
        r1 = com.apsalar.sdk.Apsalar.getDeviceId(r1);
        r15.canonicalDeviceId = r1;
    L_0x0189:
        r15.getClass();
        r0 = r18;
        r1 = r0.info;
        r2 = r15.canonicalDeviceId;
        r1.deviceId = r2;
        r1 = r15.canonicalKeyspace;
        com.apsalar.sdk.Apsalar.setKeySpace(r1);
        return;
    L_0x019a:
        r10 = com.apsalar.sdk.AIFA_Helper.getAIFA();
        goto L_0x00b6;
    L_0x01a0:
        if (r2 == 0) goto L_0x01ba;
    L_0x01a2:
        r0 = r15.AIFA;
        r16 = r0;
        r0 = r16;
        r16 = r10.equals(r0);
        if (r16 == 0) goto L_0x01ba;
    L_0x01ae:
        r0 = r15.canonicalDeviceId;
        r16 = r0;
        r0 = r16;
        r16 = r10.equals(r0);
        if (r16 != 0) goto L_0x01c0;
    L_0x01ba:
        r16 = r4.equals(r10);
        if (r16 == 0) goto L_0x01c6;
    L_0x01c0:
        r15.getClass();
        r3 = r13;
        goto L_0x006a;
    L_0x01c6:
        r17 = r11;
        r11 = r10;
        r10 = r17;
    L_0x01cb:
        r15.getClass();
        r0 = r15.canonicalKeyspace;
        r16 = r0;
        r0 = r16;
        r16 = r3.equals(r0);
        if (r16 == 0) goto L_0x01ec;
    L_0x01da:
        r0 = r15.canonicalDeviceId;
        r16 = r0;
        r0 = r16;
        r16 = r11.equals(r0);
        if (r16 == 0) goto L_0x01ec;
    L_0x01e6:
        r15.getClass();
        r3 = r13;
        goto L_0x006a;
    L_0x01ec:
        r16 = r3.equals(r10);
        if (r16 == 0) goto L_0x0252;
    L_0x01f2:
        r16 = "None";
        r0 = r16;
        r16 = r11.equals(r0);
        if (r16 != 0) goto L_0x0252;
    L_0x01fc:
        r15.getClass();
        r3 = "AIFA";
        r3 = r10.equals(r3);
        if (r3 == 0) goto L_0x0221;
    L_0x0207:
        r3 = r15.canonicalKeyspace;
        r16 = "AIFA";
        r0 = r16;
        r3 = r3.equals(r0);
        if (r3 == 0) goto L_0x0221;
    L_0x0213:
        r3 = r15.canonicalDeviceId;
        r3 = r11.equals(r3);
        if (r3 == 0) goto L_0x0221;
    L_0x021b:
        r15.getClass();
        r3 = r13;
        goto L_0x006a;
    L_0x0221:
        if (r12 != 0) goto L_0x027f;
    L_0x0223:
        r3 = "[";
    L_0x0225:
        r12 = new java.lang.StringBuilder;
        r12.<init>();
        r3 = r12.append(r3);
        r12 = "{\"k\":\"";
        r3 = r3.append(r12);
        r3 = r3.append(r10);
        r10 = "\", \"v\":\"";
        r3 = r3.append(r10);
        r3 = r3.append(r11);
        r10 = "\"},";
        r3 = r3.append(r10);
        r12 = r3.toString();
        r15.getClass();
        r3 = r13;
        goto L_0x006a;
    L_0x0252:
        r15.getClass();
        r10 = "AIFA";
        r3 = r3.equals(r10);
        if (r3 == 0) goto L_0x027c;
    L_0x025d:
        if (r13 != 0) goto L_0x027c;
    L_0x025f:
        r3 = 1;
        goto L_0x006a;
    L_0x0262:
        r1 = "ANDI";
        r1 = com.apsalar.sdk.Apsalar.getDeviceId(r1);
        r15.canonicalDeviceId = r1;
        r1 = "ANDI";
        r15.canonicalKeyspace = r1;
        r1 = r15.info;
        r2 = r15.canonicalDeviceId;
        r1.deviceId = r2;
        r15.getClass();
        goto L_0x0189;
    L_0x0279:
        r1 = r12;
        goto L_0x0171;
    L_0x027c:
        r3 = r13;
        goto L_0x006a;
    L_0x027f:
        r3 = r12;
        goto L_0x0225;
    L_0x0281:
        r17 = r4;
        r4 = r3;
        r3 = r2;
        r2 = r17;
        goto L_0x0049;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.apsalar.sdk.ApsalarEvent.constructDK():void");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    boolean resolveHelper() {
        /*
        r18 = this;
        r0 = r18;
        r1 = r0.ctx;
        r15 = com.apsalar.sdk.ApSingleton.getInstance(r1);
        r15.getClass();
        r1 = r15.already_did_SQL;
        if (r1 == 0) goto L_0x0021;
    L_0x000f:
        r15.getClass();
    L_0x0012:
        r18.constructDK();
        r1 = r15.canonicalDeviceId;
        r2 = "None";
        r1 = r1.equals(r2);
        if (r1 != 0) goto L_0x01cf;
    L_0x001f:
        r1 = 1;
    L_0x0020:
        return r1;
    L_0x0021:
        r12 = 0;
        r11 = 0;
        r15.getClass();	 Catch:{ Exception -> 0x0126 }
        r1 = r15.ctx;	 Catch:{ Exception -> 0x0126 }
        r1 = com.apsalar.sdk.ApsalarSQLiteHelper.getSQLWritableDatabase(r1);	 Catch:{ Exception -> 0x0126 }
        r15.database = r1;	 Catch:{ Exception -> 0x0126 }
        r1 = r15.database;	 Catch:{ Exception -> 0x0126 }
        if (r1 != 0) goto L_0x0042;
    L_0x0032:
        r15.getClass();	 Catch:{ Exception -> 0x0126 }
        r18.constructDK();	 Catch:{ Exception -> 0x0126 }
        r1 = 0;
        if (r11 == 0) goto L_0x003e;
    L_0x003b:
        r11.close();
    L_0x003e:
        com.apsalar.sdk.ApsalarSQLiteHelper.closeDatabase();
        goto L_0x0020;
    L_0x0042:
        r10 = 0;
        r1 = 1;
        r14 = r1;
    L_0x0045:
        r1 = 6;
        if (r14 > r1) goto L_0x01a8;
    L_0x0048:
        if (r10 != 0) goto L_0x01a8;
    L_0x004a:
        r13 = "";
        switch(r14) {
            case 1: goto L_0x00cf;
            case 2: goto L_0x00d3;
            case 3: goto L_0x00db;
            case 4: goto L_0x00df;
            case 5: goto L_0x00e3;
            case 6: goto L_0x00e7;
            default: goto L_0x004f;
        };	 Catch:{ Exception -> 0x0126 }
    L_0x004f:
        r16 = new android.content.ContentValues;	 Catch:{ Exception -> 0x0126 }
        r16.<init>();	 Catch:{ Exception -> 0x0126 }
        r1 = r15.database;	 Catch:{ Exception -> 0x0126 }
        r2 = "device_keys";
        r3 = 0;
        r4 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0126 }
        r4.<init>();	 Catch:{ Exception -> 0x0126 }
        r5 = "keyspace='";
        r4 = r4.append(r5);	 Catch:{ Exception -> 0x0126 }
        r4 = r4.append(r13);	 Catch:{ Exception -> 0x0126 }
        r5 = "'";
        r4 = r4.append(r5);	 Catch:{ Exception -> 0x0126 }
        r4 = r4.toString();	 Catch:{ Exception -> 0x0126 }
        r5 = 0;
        r6 = 0;
        r7 = 0;
        r8 = 0;
        r9 = 0;
        r0 = r1 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ Exception -> 0x0126 }
        r17 = r0;
        if (r17 != 0) goto L_0x00eb;
    L_0x007d:
        r11 = r1.query(r2, r3, r4, r5, r6, r7, r8, r9);	 Catch:{ Exception -> 0x0126 }
    L_0x0081:
        r1 = r11.getCount();	 Catch:{ Exception -> 0x0126 }
        r2 = 1;
        if (r1 >= r2) goto L_0x014d;
    L_0x0088:
        r1 = 0;
        r5 = com.apsalar.sdk.Apsalar.getDeviceId(r13);	 Catch:{ Exception -> 0x0126 }
        if (r5 == 0) goto L_0x01d2;
    L_0x008f:
        r2 = r15.canonicalKeyspace;	 Catch:{ Exception -> 0x0126 }
        r3 = r15.canonicalKeyspace;	 Catch:{ Exception -> 0x0126 }
        r2 = r2.equals(r3);	 Catch:{ Exception -> 0x0126 }
        if (r2 == 0) goto L_0x01d2;
    L_0x0099:
        r1 = 1;
        r12 = 1;
        r0 = r18;
        r2 = r0.info;	 Catch:{ Exception -> 0x0126 }
        r2 = r2.deviceId;	 Catch:{ Exception -> 0x0126 }
        r15.canonicalDeviceId = r2;	 Catch:{ Exception -> 0x0126 }
        r10 = 1;
        r4 = r1;
        r2 = r10;
        r3 = r12;
    L_0x00a7:
        r1 = r15.canonicalKeyspace;	 Catch:{ Exception -> 0x0126 }
        r6 = "NONE";
        r1 = r1.equals(r6);	 Catch:{ Exception -> 0x0126 }
        if (r1 == 0) goto L_0x00f2;
    L_0x00b1:
        r1 = r13;
    L_0x00b2:
        r6 = "NONE";
        r6 = r1.equals(r6);	 Catch:{ Exception -> 0x0126 }
        if (r6 != 0) goto L_0x00c2;
    L_0x00ba:
        r6 = "None";
        r6 = r5.equals(r6);	 Catch:{ Exception -> 0x0126 }
        if (r6 == 0) goto L_0x00f5;
    L_0x00c2:
        r1 = r2;
    L_0x00c3:
        r11.close();	 Catch:{ Exception -> 0x0126 }
        r2 = 0;
    L_0x00c7:
        r4 = r14 + 1;
        r14 = r4;
        r10 = r1;
        r11 = r2;
        r12 = r3;
        goto L_0x0045;
    L_0x00cf:
        r13 = r15.canonicalKeyspace;	 Catch:{ Exception -> 0x0126 }
        goto L_0x004f;
    L_0x00d3:
        r1 = r15.playStoreAvailable;	 Catch:{ Exception -> 0x0126 }
        if (r1 == 0) goto L_0x01d7;
    L_0x00d7:
        r13 = "AIFA";
        goto L_0x004f;
    L_0x00db:
        r13 = "ANDI";
        goto L_0x004f;
    L_0x00df:
        r13 = "IMEI";
        goto L_0x004f;
    L_0x00e3:
        r13 = "MAC1";
        goto L_0x004f;
    L_0x00e7:
        r13 = "BMAC";
        goto L_0x004f;
    L_0x00eb:
        r1 = (android.database.sqlite.SQLiteDatabase) r1;	 Catch:{ Exception -> 0x0126 }
        r11 = com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.query(r1, r2, r3, r4, r5, r6, r7, r8, r9);	 Catch:{ Exception -> 0x0126 }
        goto L_0x0081;
    L_0x00f2:
        r1 = r15.canonicalKeyspace;	 Catch:{ Exception -> 0x0126 }
        goto L_0x00b2;
    L_0x00f5:
        r6 = "keyspace";
        r0 = r16;
        r0.put(r6, r1);	 Catch:{ Exception -> 0x0126 }
        r1 = "val";
        r0 = r16;
        r0.put(r1, r5);	 Catch:{ Exception -> 0x0126 }
        r1 = "canonical";
        r4 = java.lang.Integer.valueOf(r4);	 Catch:{ Exception -> 0x0126 }
        r0 = r16;
        r0.put(r1, r4);	 Catch:{ Exception -> 0x0126 }
        r1 = r15.database;	 Catch:{ Exception -> 0x0126 }
        r4 = "device_keys";
        r6 = 0;
        r7 = r1 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ Exception -> 0x0126 }
        if (r7 != 0) goto L_0x013b;
    L_0x0117:
        r0 = r16;
        r1.insert(r4, r6, r0);	 Catch:{ Exception -> 0x0126 }
    L_0x011c:
        r0 = r18;
        r1 = r0.info;	 Catch:{ Exception -> 0x0126 }
        r1.deviceId = r5;	 Catch:{ Exception -> 0x0126 }
        r15.getClass();	 Catch:{ Exception -> 0x0126 }
        goto L_0x00c2;
    L_0x0126:
        r1 = move-exception;
        r15.incrExceptionCount();	 Catch:{ all -> 0x0143 }
        r15.getClass();	 Catch:{ all -> 0x0143 }
        r18.constructDK();	 Catch:{ all -> 0x0143 }
        r1 = 0;
        if (r11 == 0) goto L_0x0136;
    L_0x0133:
        r11.close();
    L_0x0136:
        com.apsalar.sdk.ApsalarSQLiteHelper.closeDatabase();
        goto L_0x0020;
    L_0x013b:
        r1 = (android.database.sqlite.SQLiteDatabase) r1;	 Catch:{ Exception -> 0x0126 }
        r0 = r16;
        com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.insert(r1, r4, r6, r0);	 Catch:{ Exception -> 0x0126 }
        goto L_0x011c;
    L_0x0143:
        r1 = move-exception;
        if (r11 == 0) goto L_0x0149;
    L_0x0146:
        r11.close();
    L_0x0149:
        com.apsalar.sdk.ApsalarSQLiteHelper.closeDatabase();
        throw r1;
    L_0x014d:
        r15.getClass();	 Catch:{ Exception -> 0x0126 }
        r11.moveToFirst();	 Catch:{ Exception -> 0x0126 }
        r1 = r10;
        r3 = r12;
    L_0x0155:
        r2 = r11.isAfterLast();	 Catch:{ Exception -> 0x0126 }
        if (r2 != 0) goto L_0x00c3;
    L_0x015b:
        r2 = 0;
        r2 = r11.getString(r2);	 Catch:{ Exception -> 0x0126 }
        r0 = r18;
        r4 = r0.info;	 Catch:{ Exception -> 0x0126 }
        r5 = 1;
        r5 = r11.getString(r5);	 Catch:{ Exception -> 0x0126 }
        r4.deviceId = r5;	 Catch:{ Exception -> 0x0126 }
        com.apsalar.sdk.Apsalar.setKeySpace(r2);	 Catch:{ Exception -> 0x0126 }
        r4 = "ANDI";
        r4 = r2.equals(r4);	 Catch:{ Exception -> 0x0126 }
        if (r4 == 0) goto L_0x0197;
    L_0x0176:
        r0 = r18;
        r2 = r0.info;	 Catch:{ Exception -> 0x0126 }
        r2 = r2.deviceId;	 Catch:{ Exception -> 0x0126 }
        r15.ANDI = r2;	 Catch:{ Exception -> 0x0126 }
    L_0x017e:
        r2 = 2;
        r2 = r11.getShort(r2);	 Catch:{ Exception -> 0x0126 }
        r4 = 1;
        if (r2 != r4) goto L_0x0190;
    L_0x0186:
        r3 = 1;
        r1 = r15.canonicalKeyspace;	 Catch:{ Exception -> 0x0126 }
        r1 = com.apsalar.sdk.Apsalar.getDeviceId(r1);	 Catch:{ Exception -> 0x0126 }
        r15.canonicalDeviceId = r1;	 Catch:{ Exception -> 0x0126 }
        r1 = 1;
    L_0x0190:
        r15.getClass();	 Catch:{ Exception -> 0x0126 }
        r11.moveToNext();	 Catch:{ Exception -> 0x0126 }
        goto L_0x0155;
    L_0x0197:
        r4 = "AIFA";
        r2 = r2.equals(r4);	 Catch:{ Exception -> 0x0126 }
        if (r2 == 0) goto L_0x017e;
    L_0x019f:
        r0 = r18;
        r2 = r0.info;	 Catch:{ Exception -> 0x0126 }
        r2 = r2.deviceId;	 Catch:{ Exception -> 0x0126 }
        r15.AIFA = r2;	 Catch:{ Exception -> 0x0126 }
        goto L_0x017e;
    L_0x01a8:
        r1 = 1;
        r15.already_did_SQL = r1;	 Catch:{ Exception -> 0x0126 }
        if (r11 == 0) goto L_0x01b0;
    L_0x01ad:
        r11.close();
    L_0x01b0:
        com.apsalar.sdk.ApsalarSQLiteHelper.closeDatabase();
        if (r12 == 0) goto L_0x0012;
    L_0x01b5:
        r15.getClass();
        r1 = r15.canonicalKeyspace;
        com.apsalar.sdk.Apsalar.setKeySpace(r1);
        r1 = r15.canonicalKeyspace;
        r1 = com.apsalar.sdk.Apsalar.getDeviceId(r1);
        r15.canonicalDeviceId = r1;
        r0 = r18;
        r1 = r0.info;
        r2 = r15.canonicalDeviceId;
        r1.deviceId = r2;
        goto L_0x0012;
    L_0x01cf:
        r1 = 0;
        goto L_0x0020;
    L_0x01d2:
        r4 = r1;
        r2 = r10;
        r3 = r12;
        goto L_0x00a7;
    L_0x01d7:
        r1 = r10;
        r2 = r11;
        r3 = r12;
        goto L_0x00c7;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.apsalar.sdk.ApsalarEvent.resolveHelper():boolean");
    }
}
