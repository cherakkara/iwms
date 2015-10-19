package com.google.android.m4b.maps.be;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import com.google.android.m4b.maps.ak.BasicNetwork;
import com.google.android.m4b.maps.ak.DiskBasedCache;
import com.google.android.m4b.maps.ak.HurlStack;
import com.google.android.m4b.maps.be.bo.CachingRequestQueue;
import com.google.android.m4b.maps.p038a.ExecutorDelivery;
import com.google.android.m4b.maps.p038a.Request;
import com.google.android.m4b.maps.p038a.RequestQueue;
import com.google.android.m4b.maps.p040u.Config;
import com.google.android.m4b.maps.p040u.DataRequest;
import com.google.android.m4b.maps.p040u.DataRequestDispatcher;
import com.google.android.m4b.maps.p040u.DataRequestListener;
import com.google.android.m4b.maps.p040u.Util;
import com.google.android.m4b.maps.p058v.GmmSettings;
import com.google.android.m4b.maps.p061y.CredentialsHelper;
import com.google.p025a.p026a.Preconditions;
import com.google.p025a.p028c.bk;
import com.newrelic.agent.android.analytics.AnalyticAttribute;
import com.newrelic.agent.android.api.v1.Defaults;
import com.newrelic.agent.android.instrumentation.Trace;
import java.io.File;
import java.util.Map;
import java.util.Set;
import org.apache.http.HttpResponse;
import org.apache.http.protocol.HTTP;
import p004b.p005a.p006a.p007a.p008a.p010b.AbstractSpiCall;

/* compiled from: ConnectionManager */
public final class bt extends BroadcastReceiver {
    private static bt f5894a;
    private final Context f5895b;
    private final Set<ConnectionManager> f5896c;
    private RequestQueue f5897d;
    private boolean f5898e;
    private DataRequestDispatcher f5899f;
    private int f5900g;

    /* renamed from: com.google.android.m4b.maps.be.bt.1 */
    static class ConnectionManager extends HurlStack {
        private /* synthetic */ String f5891a;

        ConnectionManager(String str) {
            this.f5891a = str;
        }

        public final HttpResponse m9035a(Request<?> request, Map<String, String> map) {
            map.put(HTTP.USER_AGENT, this.f5891a);
            return super.m5246a((Request) request, (Map) map);
        }
    }

    /* renamed from: com.google.android.m4b.maps.be.bt.a */
    static class ConnectionManager implements DataRequestListener {
        private final Handler f5892a;
        private final Handler f5893b;

        /* renamed from: com.google.android.m4b.maps.be.bt.a.1 */
        class ConnectionManager extends Handler {
            ConnectionManager(ConnectionManager connectionManager, Looper looper) {
                super(looper);
            }

            public final void handleMessage(Message message) {
                ((DataRequest) message.obj).m4776g();
            }
        }

        /* renamed from: com.google.android.m4b.maps.be.bt.a.2 */
        class ConnectionManager extends Handler {
            ConnectionManager(ConnectionManager connectionManager, Looper looper) {
                super(looper);
            }

            public final void handleMessage(Message message) {
                ((DataRequest) message.obj).m4777h();
            }
        }

        private ConnectionManager() {
            this.f5892a = new ConnectionManager(this, Looper.getMainLooper());
            this.f5893b = new ConnectionManager(this, Looper.getMainLooper());
        }

        public final void m9038a(DataRequest dataRequest) {
            Message.obtain(this.f5892a, 0, dataRequest).sendToTarget();
        }

        public final void m9037a(int i, boolean z, String str) {
        }

        public final void m9036a() {
        }

        public final void m9039b() {
        }

        public final void m9040b(DataRequest dataRequest) {
            Message.obtain(this.f5893b, 0, dataRequest).sendToTarget();
        }
    }

    /* renamed from: com.google.android.m4b.maps.be.bt.b */
    public interface ConnectionManager {
        void m9041a();
    }

    private bt(Context context) {
        this.f5896c = bk.m2870a();
        this.f5895b = context;
    }

    public static synchronized bt m9042a(Context context) {
        bt btVar;
        synchronized (bt.class) {
            if (f5894a == null) {
                f5894a = new bt(context);
            }
            btVar = f5894a;
        }
        return btVar;
    }

    public final void onReceive(Context context, Intent intent) {
        if (m9046a(true)) {
            Set set = null;
            synchronized (this) {
                if (this.f5898e) {
                    try {
                        set = bk.m2872a(this.f5896c);
                        this.f5896c.clear();
                        m9043e();
                    } catch (Throwable th) {
                        m9043e();
                    }
                }
            }
            if (r0 != null) {
                for (ConnectionManager a : r0) {
                    a.m9041a();
                }
            }
        }
    }

    public final synchronized void m9045a(ConnectionManager connectionManager) {
        Preconditions.m1818a((Object) connectionManager, (Object) "Listener is null.");
        this.f5896c.add(connectionManager);
        if (!this.f5898e) {
            this.f5898e = true;
            this.f5895b.registerReceiver(this, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
        }
    }

    private synchronized void m9043e() {
        if (this.f5898e) {
            this.f5895b.unregisterReceiver(this);
            this.f5898e = false;
        }
    }

    public final boolean m9046a(boolean z) {
        if (this.f5895b.checkPermission("android.permission.ACCESS_NETWORK_STATE", Process.myPid(), Process.myUid()) != 0) {
            return z;
        }
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.f5895b.getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public final synchronized DataRequestDispatcher m9044a() {
        if (this.f5899f == null) {
            Context context = this.f5895b;
            Resources a = InternalResourceLoader.m9392a();
            String packageName = this.f5895b.getPackageName();
            Config.m11321a(context);
            DataRequestDispatcher.DataRequestDispatcher dataRequestDispatcher = new DataRequestDispatcher.DataRequestDispatcher();
            GmmSettings.m11527a();
            dataRequestDispatcher = dataRequestDispatcher.m11345a(GmmSettings.m11530c()).m11348b(Config.m11326m()).m11350c("2.12.0").m11346a(false).m11349b(true).m11352d(Util.m11520a(context));
            GmmSettings.m11527a();
            DataRequestDispatcher a2 = dataRequestDispatcher.m11351c(GmmSettings.m11529b()).m11344a(a.getDisplayMetrics().densityDpi).m11353e(packageName).m11347a();
            a2.m11444a(new ConnectionManager());
            a2.m11446a(true);
            Config.m11320a();
            a2.m11452c(Config.m11325c());
            a2.m11455d(String.valueOf(CredentialsHelper.m11641a(context).versionCode));
            a2.m11449b("SYSTEM");
            a2.m11450b(true);
            a2.m11454d();
            a2.m11454d();
            this.f5899f = a2;
        }
        return this.f5899f;
    }

    public final synchronized RequestQueue m9047b() {
        if (this.f5897d == null) {
            Context context = this.f5895b;
            File file = new File(context.getCacheDir(), "com.google.android.m4b.maps.volley");
            Config.m11321a(context);
            String str = VERSION.SDK;
            String str2 = Config.m11324b().toString();
            String str3 = AbstractSpiCall.ANDROID_CLIENT_TYPE;
            String packageName = context.getPackageName();
            GmmSettings.m11527a();
            GmmSettings.m11529b();
            String str4 = AnalyticAttribute.EVENT_TYPE_ATTRIBUTE_MOBILE;
            String m = Config.m11326m();
            String n = DataRequestDispatcher.m11430n();
            StringBuilder stringBuilder = new StringBuilder(String.format("Mozilla/5.0 (%s; U; %s; %s; ) AppleWebKit/0.0 (KHTML, like Gecko) Version/0.0; GmmClient:%s/%s/%s/%s/%s/%s", new Object[]{"Android", str, str2, str3, packageName, "2.12.0", str4, m, n}));
            String c = Config.m11325c();
            str = Trace.NULL;
            str2 = String.valueOf(CredentialsHelper.m11641a(context).versionCode);
            stringBuilder.append(String.format("/%s/%s/%s", new Object[]{str, str2, c}));
            this.f5897d = new bo(new DiskBasedCache(file, 20971520), new BasicNetwork(new ConnectionManager(stringBuilder.toString())), new CachingRequestQueue(((((ActivityManager) context.getSystemService("activity")).getMemoryClass() * Defaults.RESPONSE_BODY_LIMIT) * Defaults.RESPONSE_BODY_LIMIT) / 8), new ExecutorDelivery(new Handler(Looper.getMainLooper())));
            if (this.f5900g > 0) {
                this.f5897d.m4722a();
            }
        }
        return this.f5897d;
    }

    public final void m9048c() {
        if (this.f5900g == 0 && this.f5897d != null) {
            this.f5897d.m4722a();
        }
        this.f5900g++;
    }

    public final void m9049d() {
        this.f5900g--;
        if (this.f5900g == 0 && this.f5897d != null) {
            this.f5897d.m4723b();
        }
    }
}
