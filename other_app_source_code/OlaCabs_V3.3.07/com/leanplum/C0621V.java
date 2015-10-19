package com.leanplum;

import android.os.AsyncTask;
import android.util.Log;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.tracing.Trace;
import com.newrelic.agent.android.tracing.TraceMachine;
import java.net.HttpURLConnection;
import java.util.List;
import java.util.Map;
import org.apache.http.HttpStatus;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.leanplum.V */
final class C0621V extends AsyncTask<Void, Void, Void> implements TraceFieldInterface {
    public Trace _nr_trace;
    private /* synthetic */ C0618S f8633a;
    private final /* synthetic */ Map f8634b;
    private final /* synthetic */ List f8635c;

    C0621V(C0618S c0618s, Map map, List list) {
        this.f8633a = c0618s;
        this.f8634b = map;
        this.f8635c = list;
    }

    public void _nr_setTrace(Trace trace) {
        try {
            this._nr_trace = trace;
        } catch (Exception e) {
        }
    }

    protected final /* synthetic */ Object doInBackground(Object... objArr) {
        try {
            TraceMachine.enterMethod(this._nr_trace, "V#doInBackground", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "V#doInBackground", null);
                break;
            }
        }
        Void a = m12580a();
        TraceMachine.exitMethod();
        TraceMachine.unloadTraceContext(this);
        return a;
    }

    private Void m12580a() {
        Throwable e;
        HttpURLConnection a;
        try {
            a = Util.m12565a(C0633g.f8794a, C0633g.f8810q, this.f8634b, this.f8633a.f8620p, C0633g.f8797d, C0633g.f8798e);
            try {
                JSONObject a2 = Util.m12567a(a);
                int responseCode = a.getResponseCode();
                C0602Y b;
                Exception exception;
                if (responseCode == HttpStatus.SC_REQUEST_TIMEOUT || responseCode == HttpStatus.SC_BAD_GATEWAY || responseCode == HttpStatus.SC_SERVICE_UNAVAILABLE || responseCode == HttpStatus.SC_GATEWAY_TIMEOUT) {
                    C0618S.m12528a(this.f8635c);
                    if (this.f8633a.f8624t != null) {
                        b = this.f8633a.f8624t;
                        exception = new Exception("HTTP error " + responseCode);
                        b.m12411a();
                    }
                } else if (responseCode >= HttpStatus.SC_BAD_REQUEST) {
                    String str = "HTTP error " + responseCode;
                    if (a2 != null) {
                        r0 = C0618S.m12533b(a2);
                        if (r0 != null) {
                            r0 = C0618S.m12541d(r0);
                            if (r0 != null) {
                                if (r0.startsWith("App not found")) {
                                    r0 = "No app matching the provided app ID was found.";
                                    C0633g.f8809p = true;
                                } else if (r0.startsWith("Invalid access key")) {
                                    r0 = "The access key you provided is not valid for this app.";
                                    C0633g.f8809p = true;
                                } else if (r0.startsWith("Development mode requested but not permitted")) {
                                    r0 = "A call to Leanplum.setAppIdForDevelopmentMode with your production key was made, which is not permitted.";
                                    C0633g.f8809p = true;
                                }
                                Log.e("Leanplum", r0);
                                if (this.f8633a.f8624t != null) {
                                    b = this.f8633a.f8624t;
                                    exception = new Exception(r0);
                                    b.m12411a();
                                }
                            }
                        }
                    }
                    r0 = str;
                    Log.e("Leanplum", r0);
                    if (this.f8633a.f8624t != null) {
                        b = this.f8633a.f8624t;
                        exception = new Exception(r0);
                        b.m12411a();
                    }
                } else {
                    if (a2 != null) {
                        int a3 = C0618S.m12517a(a2);
                        for (int i = 0; i < a3; i++) {
                            r0 = C0618S.m12522a(a2, i);
                            if (!(r0 == null || C0618S.m12539c(r0))) {
                                r0 = C0618S.m12541d(r0);
                                if (r0 == null || r0.length() == 0) {
                                    r0 = "API error";
                                } else {
                                    r0 = "API error: " + r0;
                                }
                                Log.e("Leanplum", r0);
                                if (i == a3 - 1) {
                                    if (this.f8633a.f8624t != null) {
                                        b = this.f8633a.f8624t;
                                        exception = new Exception(r0);
                                        b.m12411a();
                                    }
                                    if (a != null) {
                                        a.disconnect();
                                    }
                                    return null;
                                }
                            }
                        }
                    }
                    if (this.f8633a.f8623s != null) {
                        this.f8633a.f8623s.m12409a(a2);
                    }
                }
                if (a != null) {
                    a.disconnect();
                }
            } catch (JSONException e2) {
                e = e2;
            } catch (Exception e3) {
                e = e3;
            }
        } catch (JSONException e4) {
            e = e4;
            a = null;
            try {
                Log.e("Leanplum", "Error parsing JSON response: " + e.toString(), e);
                if (this.f8633a.f8624t != null) {
                    this.f8633a.f8624t.m12411a();
                }
                if (a != null) {
                    a.disconnect();
                }
                return null;
            } catch (Throwable th) {
                e = th;
                if (a != null) {
                    a.disconnect();
                }
                throw e;
            }
        } catch (Exception e5) {
            e = e5;
            a = null;
            C0618S.m12528a(this.f8635c);
            Log.e("Leanplum", "Unable to send request: " + e.toString(), e);
            if (this.f8633a.f8624t != null) {
                this.f8633a.f8624t.m12411a();
            }
            if (a != null) {
                a.disconnect();
            }
            return null;
        } catch (Throwable th2) {
            e = th2;
            a = null;
            if (a != null) {
                a.disconnect();
            }
            throw e;
        }
        return null;
    }
}
