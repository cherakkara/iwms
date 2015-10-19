package com.google.android.m4b.maps.be;

import com.google.android.m4b.maps.be.bu.CsiReporter;
import com.google.android.m4b.maps.cm.Clock;
import com.google.p025a.p026a.Preconditions;
import com.google.p025a.p028c.ar;
import com.google.p025a.p028c.au;
import com.newrelic.agent.android.instrumentation.HttpInstrumentation;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import org.apache.http.protocol.HTTP;

/* compiled from: CsiReporterImpl */
final class bv implements bu {
    private final Clock f5906a;
    private final String f5907b;
    private final Map<String, CsiReporter> f5908c;
    private final boolean f5909d;
    private boolean f5910e;
    private boolean f5911f;
    private long f5912g;

    /* renamed from: com.google.android.m4b.maps.be.bv.1 */
    class CsiReporterImpl extends Thread {
        private /* synthetic */ List f5904a;
        private /* synthetic */ bv f5905b;

        CsiReporterImpl(bv bvVar, List list) {
            this.f5905b = bvVar;
            this.f5904a = list;
        }

        public final void run() {
            Throwable th;
            StringBuilder stringBuilder = new StringBuilder("http://csi.gstatic.com/csi");
            stringBuilder.append("?s=");
            stringBuilder.append("maps_android_api");
            stringBuilder.append("&v=");
            stringBuilder.append(3);
            stringBuilder.append("&action=");
            stringBuilder.append(this.f5905b.f5907b);
            String str = "&it=";
            for (CsiReporter csiReporter : this.f5904a) {
                stringBuilder.append(str);
                stringBuilder.append(csiReporter.f5901a);
                stringBuilder.append(".");
                stringBuilder.append(csiReporter.m9050a());
                str = ",";
            }
            str = "&irt=";
            for (CsiReporter csiReporter2 : this.f5904a) {
                stringBuilder.append(str);
                stringBuilder.append(csiReporter2.f5903c - this.f5905b.f5912g);
                str = ",";
            }
            try {
                HttpURLConnection httpURLConnection = null;
                HttpURLConnection httpURLConnection2;
                try {
                    httpURLConnection2 = (HttpURLConnection) HttpInstrumentation.openConnection(new URL(stringBuilder.toString()).openConnection());
                    try {
                        httpURLConnection2.setRequestProperty(HTTP.USER_AGENT, System.getProperty("http.agent"));
                        httpURLConnection2.getResponseCode();
                        if (httpURLConnection2 != null) {
                            httpURLConnection2.disconnect();
                        }
                    } catch (IOException e) {
                        if (httpURLConnection2 != null) {
                            httpURLConnection2.disconnect();
                        }
                    } catch (Throwable th2) {
                        Throwable th3 = th2;
                        httpURLConnection = httpURLConnection2;
                        th = th3;
                        if (httpURLConnection != null) {
                            httpURLConnection.disconnect();
                        }
                        throw th;
                    }
                } catch (IOException e2) {
                    httpURLConnection2 = null;
                    if (httpURLConnection2 != null) {
                        httpURLConnection2.disconnect();
                    }
                } catch (Throwable th4) {
                    th = th4;
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                    throw th;
                }
            } catch (MalformedURLException e3) {
            }
        }
    }

    bv(Clock clock, String str, boolean z) {
        this.f5908c = au.m2807a();
        this.f5906a = clock;
        this.f5907b = str;
        this.f5909d = z;
    }

    public final synchronized void m9058a() {
        boolean z = true;
        synchronized (this) {
            if (this.f5910e) {
                z = false;
            }
            Preconditions.m1829b(z, String.format("Action with name %s already started", new Object[]{this.f5907b}));
            this.f5912g = this.f5906a.m10152b();
            this.f5910e = true;
        }
    }

    public final synchronized CsiReporter m9057a(String str) {
        CsiReporter csiReporter;
        Preconditions.m1830b(this.f5910e, "Action with name %s not started", this.f5907b);
        csiReporter = new CsiReporter(str);
        csiReporter.f5902b = this.f5906a.m10152b();
        return csiReporter;
    }

    public final synchronized void m9059a(CsiReporter csiReporter) {
        boolean z = true;
        synchronized (this) {
            Preconditions.m1830b(this.f5910e, "Action with name %s not started", this.f5907b);
            if (this.f5908c.get(csiReporter.f5901a) == csiReporter) {
                z = false;
            }
            Preconditions.m1830b(z, "This event with name %s already ended", csiReporter.f5901a);
            if (!this.f5911f) {
                if (!this.f5908c.containsKey(csiReporter.f5901a)) {
                    csiReporter.f5903c = this.f5906a.m10152b();
                    this.f5908c.put(csiReporter.f5901a, csiReporter);
                }
            }
        }
    }

    public final synchronized void m9060b() {
        boolean z = true;
        synchronized (this) {
            if (!this.f5910e || this.f5911f) {
                z = false;
            }
            Preconditions.m1828b(z);
            this.f5911f = true;
            List a = ar.m2516a(this.f5908c.values());
            this.f5908c.clear();
            if (this.f5909d) {
                new CsiReporterImpl(this, a).start();
            }
        }
    }
}
