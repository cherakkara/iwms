package com.apsalar.sdk;

import android.content.Context;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import com.newrelic.agent.android.instrumentation.Trace;
import com.newrelic.agent.android.tracing.TraceMachine;
import com.olacabs.customer.utils.Constants;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.ProtocolException;
import java.net.SocketTimeoutException;
import java.net.URLEncoder;
import org.apache.http.HttpStatus;
import org.apache.http.protocol.HTTP;
import org.json.JSONException;
import org.json.JSONObject;
import p004b.p005a.p006a.p007a.p008a.p010b.AbstractSpiCall;

/* compiled from: ApEvent */
class ApsalarCanonical extends ApsalarEvent implements ApsalarAPI, ApsalarJSON {
    static final String TAG = "Apsalar SDK/Canonical";
    static final String supportedDeviceIds = "[ \"AIFA\", \"ANDI\" ]";

    protected void init(Context context) {
        this.ctx = context;
        this.urlbase = "https://e.apsalar.com/api/v1/canonical";
        this.eventType = 6;
    }

    protected ApsalarCanonical(Context context, ApsalarSessionInfo apsalarSessionInfo) {
        super(context, apsalarSessionInfo);
    }

    protected ApsalarCanonical(Context context, ApsalarSessionInfo apsalarSessionInfo, JSONObject jSONObject) {
        super(context, apsalarSessionInfo, jSONObject);
    }

    protected boolean makeURL(boolean z) {
        ApSingleton instance = ApSingleton.getInstance(this.ctx);
        instance.getClass();
        String str = Trace.NULL;
        try {
            this.url = "?a=" + URLEncoder.encode(this.info.apiKey, HTTP.UTF_8) + "&br=" + URLEncoder.encode(this.info.brand, HTTP.UTF_8) + "&c=" + URLEncoder.encode(this.info.connType, HTTP.UTF_8) + "&de=" + URLEncoder.encode(this.info.device, HTTP.UTF_8) + "&ma=" + URLEncoder.encode(this.info.manufacturer, HTTP.UTF_8) + "&mo=" + URLEncoder.encode(this.info.model, HTTP.UTF_8) + "&n=" + URLEncoder.encode(this.info.appName, HTTP.UTF_8) + "&p=" + URLEncoder.encode(this.info.platform, HTTP.UTF_8) + "&pr=" + URLEncoder.encode(this.info.product, HTTP.UTF_8) + "&sdk=" + URLEncoder.encode("Apsalar/" + this.info.sdkVersion, HTTP.UTF_8) + "&v=" + URLEncoder.encode(this.info.osVersion, HTTP.UTF_8) + "&sup=" + URLEncoder.encode(supportedDeviceIds, HTTP.UTF_8) + "&ps=" + URLEncoder.encode(String.valueOf(instance.playStoreAvailable), HTTP.UTF_8);
            if (this.url.length() < ApSingleton.maxUrlSize) {
                return true;
            }
            instance.getClass();
            return false;
        } catch (UnsupportedEncodingException e) {
            instance.incrExceptionCount();
            instance.getClass();
            return false;
        }
    }

    public int REST(Boolean bool) {
        ApSingleton instance = ApSingleton.getInstance(this.ctx);
        instance.getClass();
        if (makeURL(true)) {
            int i;
            String str = this.urlbase + this.url;
            if (str.length() > ApSingleton.maxUrlSize) {
                instance.getClass();
                i = true;
            } else {
                boolean z = true;
            }
            this.returnData = null;
            if (i == true) {
                try {
                    this.returnData = ApsalarHttpClient.get(str);
                } catch (ProtocolException e) {
                    instance.incrExceptionCount();
                    instance.getClass();
                    i = 0;
                } catch (SocketTimeoutException e2) {
                    instance.incrExceptionCount();
                    instance.getClass();
                    instance.incrNetworkErrorCount();
                    i = 0;
                } catch (IOException e3) {
                    instance.incrExceptionCount();
                    instance.getClass();
                    instance.incrNetworkErrorCount();
                    i = 0;
                }
            }
            String str2 = instance.canonicalKeyspace;
            String str3 = instance.canonicalDeviceId;
            if (i == 1 && this.returnData != null) {
                instance.incrSentEventsCount();
                if (bool.booleanValue()) {
                    try {
                        this.returnDataJSON = JSONObjectInstrumentation.init(this.returnData);
                        str = this.returnDataJSON.getString("canonical").toUpperCase();
                        if (str == null) {
                            instance.getClass();
                            i = 0;
                        } else {
                            boolean z2;
                            if (instance.playStoreAvailable || !instance.canonicalKeyspace.equals("AIFA")) {
                                instance.canonicalKeyspace = str;
                            } else {
                                instance.getClass();
                                instance.canonicalKeyspace = "ANDI";
                            }
                            instance.canonicalDeviceId = Apsalar.getDeviceId(instance.canonicalKeyspace);
                            instance.getClass();
                            instance.expires = Integer.valueOf(this.returnDataJSON.getString("expires")).intValue();
                            if (instance.expires < 0) {
                                instance.expires = 0;
                            }
                            if (instance.expires > HttpStatus.SC_MULTIPLE_CHOICES) {
                                instance.expires = HttpStatus.SC_MULTIPLE_CHOICES;
                            }
                            instance.SHORTSLEEP = Integer.valueOf(this.returnDataJSON.getString("shtsleep")).intValue();
                            if (instance.SHORTSLEEP < 1) {
                                instance.SHORTSLEEP = 1;
                            }
                            if (instance.SHORTSLEEP > AbstractSpiCall.DEFAULT_TIMEOUT) {
                                instance.SHORTSLEEP = AbstractSpiCall.DEFAULT_TIMEOUT;
                            }
                            instance.MEDIUMSLEEP = Integer.valueOf(this.returnDataJSON.getString("medsleep")).intValue();
                            if (instance.MEDIUMSLEEP < 1) {
                                instance.MEDIUMSLEEP = 1;
                            }
                            if (instance.MEDIUMSLEEP > TraceMachine.UNHEALTHY_TRACE_TIMEOUT) {
                                instance.MEDIUMSLEEP = TraceMachine.UNHEALTHY_TRACE_TIMEOUT;
                            }
                            instance.LONGSLEEP = Integer.valueOf(this.returnDataJSON.getString("lngsleep")).intValue();
                            if (instance.LONGSLEEP < 1) {
                                instance.LONGSLEEP = 1;
                            }
                            if (instance.LONGSLEEP > 900000) {
                                instance.LONGSLEEP = 900000;
                            }
                            instance.VERYLONGSLEEP = Integer.valueOf(this.returnDataJSON.getString("xlnsleep")).intValue();
                            if (instance.LONGSLEEP < 1) {
                                instance.LONGSLEEP = 1;
                            }
                            if (instance.LONGSLEEP > 2700000) {
                                instance.LONGSLEEP = 2700000;
                            }
                            instance.RESOLVE_ALL_AVAILABLE = Integer.valueOf(this.returnDataJSON.getString("resolveall")).intValue() != 0;
                            if (Integer.valueOf(this.returnDataJSON.getString("alwayscanon")).intValue() != 0) {
                                z2 = true;
                            } else {
                                z2 = false;
                            }
                            instance.ALWAYS_REQUEST_CANONICAL = z2;
                            instance.NUM_EVENTS_B4_SLEEP = Integer.valueOf(this.returnDataJSON.getString("evb4sleep")).intValue();
                            if (instance.NUM_EVENTS_B4_SLEEP < 1) {
                                instance.NUM_EVENTS_B4_SLEEP = 1;
                            }
                            if (instance.NUM_EVENTS_B4_SLEEP > Constants.MILLIS_IN_A_SECOND) {
                                instance.NUM_EVENTS_B4_SLEEP = Constants.MILLIS_IN_A_SECOND;
                            }
                            instance.QUEUE_SIZE_MAX = Integer.valueOf(this.returnDataJSON.getString("qsizemax")).intValue();
                            if (instance.QUEUE_SIZE_MAX < 1) {
                                instance.QUEUE_SIZE_MAX = 1;
                            }
                            if (instance.QUEUE_SIZE_MAX > AbstractSpiCall.DEFAULT_TIMEOUT) {
                                instance.QUEUE_SIZE_MAX = AbstractSpiCall.DEFAULT_TIMEOUT;
                            }
                            instance.BUFFER_SIZE_MAX = Integer.valueOf(this.returnDataJSON.getString("bsizemax")).intValue();
                            if (instance.BUFFER_SIZE_MAX < 1) {
                                instance.BUFFER_SIZE_MAX = 1;
                            }
                            if (instance.BUFFER_SIZE_MAX > AbstractSpiCall.DEFAULT_TIMEOUT) {
                                instance.BUFFER_SIZE_MAX = AbstractSpiCall.DEFAULT_TIMEOUT;
                            }
                            instance.HEARTBEAT_INTERVAL_BACKOFF = Integer.valueOf(this.returnDataJSON.getString("hrtbackoff")).intValue();
                            if (instance.HEARTBEAT_INTERVAL_BACKOFF < 1) {
                                instance.HEARTBEAT_INTERVAL_BACKOFF = 1;
                            }
                            if (instance.HEARTBEAT_INTERVAL_BACKOFF > Constants.MILLIS_IN_A_SECOND) {
                                instance.HEARTBEAT_INTERVAL_BACKOFF = Constants.MILLIS_IN_A_SECOND;
                            }
                            instance.HEARTBEAT_INTERVAL_MAX = Integer.valueOf(this.returnDataJSON.getString("hrtintmax")).intValue();
                            if (instance.HEARTBEAT_INTERVAL_MAX < 1) {
                                instance.HEARTBEAT_INTERVAL_MAX = 1;
                            }
                            if (instance.HEARTBEAT_INTERVAL_MAX > 100000000) {
                                instance.HEARTBEAT_INTERVAL_MAX = 100000000;
                            }
                            instance.HEARTBEAT_INTERVAL_MIN = Integer.valueOf(this.returnDataJSON.getString("hrtintmin")).intValue();
                            if (instance.HEARTBEAT_INTERVAL_MIN > 10000000) {
                                instance.HEARTBEAT_INTERVAL_MIN = 10000000;
                            }
                            if (instance.HEARTBEAT_INTERVAL_MIN > instance.HEARTBEAT_INTERVAL_MAX) {
                                instance.HEARTBEAT_INTERVAL_MIN = instance.HEARTBEAT_INTERVAL_MAX - 1;
                            }
                            if (instance.HEARTBEAT_INTERVAL_MIN < 1) {
                                instance.HEARTBEAT_INTERVAL_MIN = 1;
                            }
                            instance.RETRY_INTERVAL_BACKOFF = Integer.valueOf(this.returnDataJSON.getString("retbackoff")).intValue();
                            if (instance.RETRY_INTERVAL_BACKOFF < 1) {
                                instance.RETRY_INTERVAL_BACKOFF = 1;
                            }
                            if (instance.RETRY_INTERVAL_BACKOFF > Constants.MILLIS_IN_A_SECOND) {
                                instance.RETRY_INTERVAL_BACKOFF = Constants.MILLIS_IN_A_SECOND;
                            }
                            instance.RETRY_INTERVAL_MAX = Integer.valueOf(this.returnDataJSON.getString("retintmax")).intValue();
                            if (instance.RETRY_INTERVAL_MAX < 1) {
                                instance.RETRY_INTERVAL_MAX = 1;
                            }
                            if (instance.RETRY_INTERVAL_MAX > 100000000) {
                                instance.RETRY_INTERVAL_MAX = 100000000;
                            }
                            instance.RETRY_INTERVAL_MIN = Integer.valueOf(this.returnDataJSON.getString("retintmin")).intValue();
                            if (instance.RETRY_INTERVAL_MIN > 10000000) {
                                instance.RETRY_INTERVAL_MIN = 10000000;
                            }
                            if (instance.RETRY_INTERVAL_MIN > instance.RETRY_INTERVAL_MAX) {
                                instance.RETRY_INTERVAL_MIN = instance.RETRY_INTERVAL_MAX - 1;
                            }
                            if (instance.RETRY_INTERVAL_MIN < 1) {
                                instance.RETRY_INTERVAL_MIN = 1;
                            }
                            instance.BATCHES_MAX = Integer.valueOf(this.returnDataJSON.getString("batmax")).intValue();
                            if (instance.BATCHES_MAX < 1) {
                                instance.BATCHES_MAX = 1;
                            }
                            if (instance.BATCHES_MAX > AbstractSpiCall.DEFAULT_TIMEOUT) {
                                instance.BATCHES_MAX = AbstractSpiCall.DEFAULT_TIMEOUT;
                            }
                            instance.BATCHES_INTERVAL = Integer.valueOf(this.returnDataJSON.getString("batint")).intValue();
                            if (instance.BATCHES_INTERVAL < 0) {
                                instance.BATCHES_INTERVAL = 0;
                            }
                            if (instance.BATCHES_INTERVAL > 3600) {
                                instance.BATCHES_INTERVAL = 3600;
                            }
                            instance.RESOLVER_MAX = Integer.valueOf(this.returnDataJSON.getString("resmax")).intValue();
                            if (instance.RESOLVER_MAX < 1) {
                                instance.RESOLVER_MAX = 1;
                            }
                            if (instance.RESOLVER_MAX > 100) {
                                instance.RESOLVER_MAX = 100;
                            }
                        }
                    } catch (JSONException e4) {
                        instance.incrExceptionCount();
                        instance.getClass();
                        i = 1;
                    } catch (NumberFormatException e5) {
                        instance.incrExceptionCount();
                        instance.getClass();
                    } catch (Throwable th) {
                        instance.incrExceptionCount();
                        instance.getClass();
                        i = 0;
                    }
                }
            }
            if (i != 1) {
                return i;
            }
            instance.getClass();
            if (!instance.playStoreAvailable && instance.canonicalKeyspace.equals("AIFA")) {
                instance.getClass();
                instance.canonicalKeyspace = "ANDI";
            }
            if (str2 == null || str3 == null || str2.length() <= 0 || str3.length() <= 0 || str2.equals(instance.canonicalKeyspace) || str3.equals(instance.canonicalDeviceId)) {
                return i;
            }
            try {
                instance.getClass();
                instance.already_did_SQL = false;
            } catch (Exception e6) {
                instance.incrExceptionCount();
                instance.getClass();
            } finally {
                ApsalarSQLiteHelper.closeDatabase();
            }
            instance.expires = 0;
            return i;
        }
        instance.getClass();
        return -1;
    }
}
