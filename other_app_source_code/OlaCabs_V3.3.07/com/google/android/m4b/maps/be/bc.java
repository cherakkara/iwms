package com.google.android.m4b.maps.be;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Looper;
import android.os.Process;
import android.os.RemoteException;
import android.util.Pair;
import com.google.android.m4b.maps.cd.IApiTokenService;
import com.google.android.m4b.maps.cm.Clock;
import com.google.android.m4b.maps.p040u.DataRequest;
import com.google.android.m4b.maps.p040u.DataRequestDispatcher;
import com.google.android.m4b.maps.p040u.DataRequestListener;
import com.google.android.m4b.maps.p061y.CredentialsHelper;
import com.google.p025a.p026a.Joiner;
import com.google.p025a.p026a.Preconditions;
import com.google.p025a.p026a.Splitter;
import com.olacabs.customer.utils.Constants;
import com.sothree.slidinguppanel.p086a.R.R;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/* compiled from: Authorizer */
public final class bc implements DataRequestListener, Runnable {
    private final Context f5718a;
    private final String f5719b;
    private final DataRequestDispatcher f5720c;
    private final Clock f5721d;
    private final ScheduledExecutorService f5722e;
    private Future<?> f5723f;
    private final Random f5724g;
    private int f5725h;
    private volatile IApiTokenService f5726i;
    private long f5727j;
    private boolean f5728k;
    private final Runnable f5729l;
    private final ServiceConnection f5730m;

    /* renamed from: com.google.android.m4b.maps.be.bc.1 */
    class Authorizer implements Runnable {
        private /* synthetic */ bc f5714a;

        Authorizer(bc bcVar) {
            this.f5714a = bcVar;
        }

        public final void run() {
            bc.m8785b(this.f5714a);
        }
    }

    /* renamed from: com.google.android.m4b.maps.be.bc.2 */
    class Authorizer implements ServiceConnection {
        private /* synthetic */ bc f5715a;

        Authorizer(bc bcVar) {
            this.f5715a = bcVar;
        }

        public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            this.f5715a.f5726i = IApiTokenService.IApiTokenService.m10134a(iBinder);
            this.f5715a.f5722e.schedule(this.f5715a.f5729l, 0, TimeUnit.MILLISECONDS);
        }

        public final void onServiceDisconnected(ComponentName componentName) {
            this.f5715a.f5726i = null;
        }
    }

    /* renamed from: com.google.android.m4b.maps.be.bc.a */
    final class Authorizer extends BroadcastReceiver {
        private volatile boolean f5716a;
        private /* synthetic */ bc f5717b;

        Authorizer(bc bcVar) {
            this.f5717b = bcVar;
        }

        public final void onReceive(Context context, Intent intent) {
            bc bcVar = this.f5717b;
            if (bc.m8784a(context) && this.f5716a) {
                this.f5717b.m8791f();
                context.unregisterReceiver(this);
                this.f5716a = false;
            }
        }

        public final void m8777a(Context context) {
            this.f5716a = true;
            context.registerReceiver(this, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
        }
    }

    static /* synthetic */ void m8785b(bc bcVar) {
        Preconditions.m1829b(Looper.getMainLooper() != Looper.myLooper(), (Object) "Running on main thread.");
        IApiTokenService iApiTokenService;
        IApiTokenService bundle;
        try {
            iApiTokenService = bcVar.f5726i;
            bundle = new Bundle();
            bundle.putString("PACKAGE_NAME", bcVar.f5718a.getPackageName());
            bundle.putString("API_KEY", bcVar.f5719b);
            iApiTokenService = iApiTokenService.m10132a(bundle);
            try {
                bcVar.f5726i = bundle;
                bcVar.f5718a.unbindService(bcVar.f5730m);
            } catch (IllegalArgumentException e) {
            }
            short s = r0.getShort("ERROR_CODE", (short) -1);
            if (s != (short) -1) {
                switch (s) {
                    case R.SlidingUpPanelLayout_umanoDragView /*5*/:
                        bcVar.m8781a(Math.min(Constants.MILLIS_IN_AN_HOUR, (long) ((bcVar.f5724g.nextDouble() * 5000.0d) + (10000.0d * Math.pow(1.6d, (double) bcVar.f5725h)))));
                        bcVar.f5725h++;
                        return;
                    default:
                        bcVar.m8789d();
                        bcVar.m8792g();
                        return;
                }
            }
            String string = r0.getString("API_TOKEN");
            if (string != null) {
                long j;
                long a;
                if (r0.containsKey("VALIDITY_DURATION")) {
                    j = r0.getLong("VALIDITY_DURATION");
                    a = bcVar.f5721d.m10151a() + j;
                } else {
                    a = r0.getLong("EXPIRY_TIME");
                    j = a - bcVar.f5721d.m10151a();
                }
                bcVar.m8786b(string, j);
                bcVar.m8783a(string, a);
                return;
            }
            bcVar.m8792g();
        } catch (RemoteException e2) {
            iApiTokenService = e2;
            bcVar.m8792g();
            try {
                bcVar.f5726i = iApiTokenService;
                bcVar.f5718a.unbindService(bcVar.f5730m);
            } catch (IllegalArgumentException e3) {
            }
        } finally {
            bundle = null;
            try {
                bcVar.f5726i = null;
                bcVar.f5718a.unbindService(bcVar.f5730m);
            } catch (IllegalArgumentException e4) {
            }
        }
    }

    static {
        bc.class.getSimpleName();
    }

    private bc(Context context, String str, DataRequestDispatcher dataRequestDispatcher, Clock clock, Random random, ScheduledExecutorService scheduledExecutorService) {
        this.f5728k = false;
        this.f5729l = new Authorizer(this);
        this.f5730m = new Authorizer(this);
        this.f5718a = context;
        this.f5719b = str;
        this.f5720c = dataRequestDispatcher;
        this.f5721d = clock;
        this.f5724g = random;
        this.f5722e = scheduledExecutorService;
    }

    public static bc m8779a(Context context, String str, DataRequestDispatcher dataRequestDispatcher) {
        return new bc(context, str, dataRequestDispatcher, new Clock(), new Random(), Executors.newSingleThreadScheduledExecutor());
    }

    private void m8789d() {
        if (!this.f5728k) {
            az.m8757a(6, "Authorization failure.  Please see https://developers.google.com/maps/documentation/android/start for how to correctly set up the map.");
            String str = this.f5719b;
            String packageName = this.f5718a.getPackageName();
            String a = CredentialsHelper.m11643a(this.f5718a.getPackageManager(), packageName);
            if (a == null) {
                a = null;
            } else {
                a = Joiner.m1780a(":").m1785a(Splitter.m1853a(2).m1858a(a.toUpperCase()));
            }
            az.m8757a(6, "In the Google Developer Console (https://console.developers.google.com)\nEnsure that the \"Google Maps Android API v2\" is enabled.\nEnsure that the following Android Key exists:\n\tAPI Key: " + str + "\n\tAndroid Application (<cert_fingerprint>;<package_name>): " + a + ";" + packageName);
            this.f5728k = true;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void m8783a(java.lang.String r5, long r6) {
        /*
        r4 = this;
        if (r5 != 0) goto L_0x000d;
    L_0x0002:
        r0 = r4.f5718a;
        r1 = "_m_t";
        r0 = r0.deleteFile(r1);
        if (r0 == 0) goto L_0x000c;
    L_0x000c:
        return;
    L_0x000d:
        r0 = r4.f5718a;	 Catch:{ FileNotFoundException -> 0x003a }
        r1 = "_m_t";
        r2 = 0;
        r0 = r0.openFileOutput(r1, r2);	 Catch:{ FileNotFoundException -> 0x003a }
        r1 = new java.io.DataOutputStream;
        r1.<init>(r0);
        r1.writeLong(r6);	 Catch:{ IOException -> 0x002c, all -> 0x0033 }
        r1.writeUTF(r5);	 Catch:{ IOException -> 0x002c, all -> 0x0033 }
        r0 = r4.f5719b;	 Catch:{ IOException -> 0x002c, all -> 0x0033 }
        r1.writeUTF(r0);	 Catch:{ IOException -> 0x002c, all -> 0x0033 }
        r1.close();	 Catch:{ IOException -> 0x002a }
        goto L_0x000c;
    L_0x002a:
        r0 = move-exception;
        goto L_0x000c;
    L_0x002c:
        r0 = move-exception;
        r1.close();	 Catch:{ IOException -> 0x0031 }
        goto L_0x000c;
    L_0x0031:
        r0 = move-exception;
        goto L_0x000c;
    L_0x0033:
        r0 = move-exception;
        r1.close();	 Catch:{ IOException -> 0x0038 }
    L_0x0037:
        throw r0;
    L_0x0038:
        r1 = move-exception;
        goto L_0x0037;
    L_0x003a:
        r0 = move-exception;
        goto L_0x000c;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.m4b.maps.be.bc.a(java.lang.String, long):void");
    }

    private Pair<String, Long> m8790e() {
        try {
            return m8778a(this.f5718a.openFileInput("_m_t"));
        } catch (FileNotFoundException e) {
            return null;
        }
    }

    public final synchronized void m8798c() {
        Pair e = m8790e();
        if (e == null) {
            m8781a(0);
        } else {
            String str = (String) e.first;
            long longValue = ((Long) e.second).longValue() - this.f5721d.m10151a();
            if (longValue <= 300000) {
                m8781a(0);
            } else {
                m8786b(str, longValue);
            }
        }
    }

    private synchronized void m8786b(String str, long j) {
        this.f5720c.m11459f(str);
        this.f5720c.m11444a((DataRequestListener) this);
        this.f5720c.m11456e();
        this.f5720c.m11456e();
        this.f5725h = 0;
        long j2 = j - 300000;
        if (j2 > 0) {
            m8781a(j2);
        }
    }

    private void m8781a(long j) {
        if (this.f5723f != null) {
            this.f5723f.cancel(true);
        }
        if (j == 0) {
            this.f5723f = null;
            run();
            return;
        }
        this.f5723f = this.f5722e.schedule(this, j, TimeUnit.MILLISECONDS);
    }

    private void m8791f() {
        Intent intent = new Intent();
        intent.setClassName("com.google.android.gms", "com.google.android.gms.maps.auth.ApiTokenService");
        if (!this.f5718a.bindService(intent, this.f5730m, 1)) {
            m8792g();
        }
    }

    private void m8792g() {
        if (this.f5723f != null) {
            this.f5723f.cancel(true);
        }
    }

    public final synchronized void run() {
        if (m8784a(this.f5718a)) {
            m8791f();
        } else {
            new Authorizer(this).m8777a(this.f5718a);
        }
    }

    public final synchronized void m8793a() {
        long a = this.f5721d.m10151a();
        if (a >= this.f5727j + Constants.MILLIS_IN_A_MINUTE) {
            if (!this.f5720c.m11453c()) {
                this.f5727j = a;
                this.f5720c.m11454d();
                this.f5720c.m11454d();
                m8783a(null, -1);
                m8781a(0);
            }
        }
    }

    public final synchronized void m8796b() {
        m8789d();
    }

    public final void m8795a(DataRequest dataRequest) {
    }

    public final void m8794a(int i, boolean z, String str) {
    }

    public final void m8797b(DataRequest dataRequest) {
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private android.util.Pair<java.lang.String, java.lang.Long> m8778a(java.io.FileInputStream r8) {
        /*
        r7 = this;
        r0 = 0;
        r1 = new java.io.DataInputStream;
        r1.<init>(r8);
        r2 = r1.readLong();	 Catch:{ IOException -> 0x002d, all -> 0x0034 }
        r4 = r1.readUTF();	 Catch:{ IOException -> 0x002d, all -> 0x0034 }
        r5 = r1.readUTF();	 Catch:{ EOFException -> 0x001e }
        r6 = r7.f5719b;	 Catch:{ EOFException -> 0x001e }
        r5 = r6.equals(r5);	 Catch:{ EOFException -> 0x001e }
        if (r5 != 0) goto L_0x001f;
    L_0x001a:
        r1.close();	 Catch:{ IOException -> 0x0039 }
    L_0x001d:
        return r0;
    L_0x001e:
        r5 = move-exception;
    L_0x001f:
        r2 = java.lang.Long.valueOf(r2);	 Catch:{ IOException -> 0x002d, all -> 0x0034 }
        r0 = android.util.Pair.create(r4, r2);	 Catch:{ IOException -> 0x002d, all -> 0x0034 }
        r1.close();	 Catch:{ IOException -> 0x002b }
        goto L_0x001d;
    L_0x002b:
        r1 = move-exception;
        goto L_0x001d;
    L_0x002d:
        r2 = move-exception;
        r1.close();	 Catch:{ IOException -> 0x0032 }
        goto L_0x001d;
    L_0x0032:
        r1 = move-exception;
        goto L_0x001d;
    L_0x0034:
        r0 = move-exception;
        r1.close();	 Catch:{ IOException -> 0x003b }
    L_0x0038:
        throw r0;
    L_0x0039:
        r1 = move-exception;
        goto L_0x001d;
    L_0x003b:
        r1 = move-exception;
        goto L_0x0038;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.m4b.maps.be.bc.a(java.io.FileInputStream):android.util.Pair<java.lang.String, java.lang.Long>");
    }

    public static boolean m8784a(Context context) {
        if (context.checkPermission("android.permission.ACCESS_NETWORK_STATE", Process.myPid(), Process.myUid()) != 0) {
            return true;
        }
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
