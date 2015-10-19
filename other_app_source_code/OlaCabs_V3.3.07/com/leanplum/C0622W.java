package com.leanplum;

import android.os.AsyncTask;
import android.util.Log;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.tracing.Trace;
import com.newrelic.agent.android.tracing.TraceMachine;
import java.io.File;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.util.List;
import java.util.Map;
import org.apache.http.HttpException;
import org.apache.http.HttpStatus;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.leanplum.W */
final class C0622W extends AsyncTask<Void, Void, Void> implements TraceFieldInterface {
    public Trace _nr_trace;
    private /* synthetic */ C0618S f8656a;
    private final /* synthetic */ List f8657b;
    private final /* synthetic */ List f8658c;
    private final /* synthetic */ Map f8659d;

    C0622W(C0618S c0618s, List list, List list2, Map map) {
        this.f8656a = c0618s;
        this.f8657b = list;
        this.f8658c = list2;
        this.f8659d = map;
    }

    public void _nr_setTrace(Trace trace) {
        try {
            this._nr_trace = trace;
        } catch (Exception e) {
        }
    }

    protected final /* synthetic */ Object doInBackground(Object... objArr) {
        try {
            TraceMachine.enterMethod(this._nr_trace, "W#doInBackground", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "W#doInBackground", null);
                break;
            }
        }
        Void a = m12591a();
        TraceMachine.exitMethod();
        TraceMachine.unloadTraceContext(this);
        return a;
    }

    private Void m12591a() {
        HttpURLConnection a;
        Throwable e;
        synchronized (C0618S.f8619o) {
            try {
                a = Util.m12566a("file", this.f8657b, this.f8658c, C0633g.f8794a, C0633g.f8810q, this.f8659d, this.f8656a.f8620p, C0633g.f8797d, 60);
                if (a != null) {
                    try {
                        JSONObject a2 = Util.m12567a(a);
                        int responseCode = a.getResponseCode();
                        if (responseCode != HttpStatus.SC_OK) {
                            throw new HttpException("Leanplum: Error sending request: " + responseCode);
                        } else if (this.f8656a.f8623s != null) {
                            this.f8656a.f8623s.m12409a(a2);
                        }
                    } catch (HttpException e2) {
                        e = e2;
                        try {
                            Log.e("Leanplum", "Unable to connect", e);
                            if (this.f8656a.f8624t != null) {
                                this.f8656a.f8624t.m12411a();
                            }
                            if (a != null) {
                                a.disconnect();
                            }
                            for (File put : this.f8657b) {
                                C0618S.f8616l.put(put, Double.valueOf(1.0d));
                            }
                            C0618S.m12549p();
                            return null;
                        } catch (Throwable th) {
                            e = th;
                            if (a != null) {
                                a.disconnect();
                            }
                            throw e;
                        }
                    } catch (JSONException e3) {
                        e = e3;
                        Log.e("Leanplum", "Unable to convert to JSON", e);
                        if (this.f8656a.f8624t != null) {
                            this.f8656a.f8624t.m12411a();
                        }
                        if (a != null) {
                            a.disconnect();
                        }
                        while (r1.hasNext()) {
                            C0618S.f8616l.put(put, Double.valueOf(1.0d));
                        }
                        C0618S.m12549p();
                        return null;
                    } catch (SocketTimeoutException e4) {
                        Log.e("Leanplum", "Timeout uploading files. Try again or limit the number of files to upload with parameters to syncResourcesAsync");
                        if (this.f8656a.f8624t != null) {
                            this.f8656a.f8624t.m12411a();
                        }
                        if (a != null) {
                            a.disconnect();
                        }
                        while (r1.hasNext()) {
                            C0618S.f8616l.put(put, Double.valueOf(1.0d));
                        }
                        C0618S.m12549p();
                        return null;
                    } catch (Exception e5) {
                        e = e5;
                        Log.e("Leanplum", "Unable to send file", e);
                        if (this.f8656a.f8624t != null) {
                            this.f8656a.f8624t.m12411a();
                        }
                        if (a != null) {
                            a.disconnect();
                        }
                        while (r1.hasNext()) {
                            C0618S.f8616l.put(put, Double.valueOf(1.0d));
                        }
                        C0618S.m12549p();
                        return null;
                    }
                } else if (this.f8656a.f8624t != null) {
                    C0602Y b = this.f8656a.f8624t;
                    HttpException httpException = new HttpException("Leanplum: Unable to read file");
                    b.m12411a();
                }
                if (a != null) {
                    a.disconnect();
                }
            } catch (HttpException e6) {
                e = e6;
                a = null;
                Log.e("Leanplum", "Unable to connect", e);
                if (this.f8656a.f8624t != null) {
                    this.f8656a.f8624t.m12411a();
                }
                if (a != null) {
                    a.disconnect();
                }
                while (r1.hasNext()) {
                    C0618S.f8616l.put(put, Double.valueOf(1.0d));
                }
                C0618S.m12549p();
                return null;
            } catch (JSONException e7) {
                e = e7;
                a = null;
                Log.e("Leanplum", "Unable to convert to JSON", e);
                if (this.f8656a.f8624t != null) {
                    this.f8656a.f8624t.m12411a();
                }
                if (a != null) {
                    a.disconnect();
                }
                while (r1.hasNext()) {
                    C0618S.f8616l.put(put, Double.valueOf(1.0d));
                }
                C0618S.m12549p();
                return null;
            } catch (SocketTimeoutException e8) {
                a = null;
                Log.e("Leanplum", "Timeout uploading files. Try again or limit the number of files to upload with parameters to syncResourcesAsync");
                if (this.f8656a.f8624t != null) {
                    this.f8656a.f8624t.m12411a();
                }
                if (a != null) {
                    a.disconnect();
                }
                while (r1.hasNext()) {
                    C0618S.f8616l.put(put, Double.valueOf(1.0d));
                }
                C0618S.m12549p();
                return null;
            } catch (Exception e9) {
                e = e9;
                a = null;
                Log.e("Leanplum", "Unable to send file", e);
                if (this.f8656a.f8624t != null) {
                    this.f8656a.f8624t.m12411a();
                }
                if (a != null) {
                    a.disconnect();
                }
                while (r1.hasNext()) {
                    C0618S.f8616l.put(put, Double.valueOf(1.0d));
                }
                C0618S.m12549p();
                return null;
            } catch (Throwable th2) {
                e = th2;
                a = null;
                if (a != null) {
                    a.disconnect();
                }
                throw e;
            }
            while (r1.hasNext()) {
                C0618S.f8616l.put(put, Double.valueOf(1.0d));
            }
            C0618S.m12549p();
        }
        return null;
    }
}
