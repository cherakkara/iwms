package com.google.android.gms.common.api;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.IntentSender.SendIntentException;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.LoaderManager;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.Loader;
import android.util.Log;
import android.util.SparseArray;
import com.google.android.gms.common.C0270e;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.C0226d.C0221b;
import com.google.android.gms.common.api.C0226d.C0222c;
import com.google.android.gms.common.internal.C0453u;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.instrumentation.Instrumented;
import com.newrelic.agent.android.tracing.TraceMachine;
import java.io.FileDescriptor;
import java.io.PrintWriter;

@Instrumented
/* renamed from: com.google.android.gms.common.api.n */
public class C0261n extends Fragment implements OnCancelListener, LoaderCallbacks<ConnectionResult>, TraceFieldInterface {
    private boolean f2095a;
    private int f2096b;
    private ConnectionResult f2097c;
    private final Handler f2098d;
    private final SparseArray<C0259b> f2099e;

    /* renamed from: com.google.android.gms.common.api.n.a */
    static class C0258a extends Loader<ConnectionResult> implements C0221b, C0222c {
        public final C0226d f2087a;
        private boolean f2088b;
        private ConnectionResult f2089c;

        public C0258a(Context context, C0226d c0226d) {
            super(context);
            this.f2087a = c0226d;
        }

        private void m3347a(ConnectionResult connectionResult) {
            this.f2089c = connectionResult;
            if (isStarted() && !isAbandoned()) {
                deliverResult(connectionResult);
            }
        }

        public void m3348a(int i) {
        }

        public void m3349a(Bundle bundle) {
            this.f2088b = false;
            m3347a(ConnectionResult.f1981a);
        }

        public boolean m3350a() {
            return this.f2088b;
        }

        public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
            super.dump(str, fileDescriptor, printWriter, strArr);
            this.f2087a.m3225a(str, fileDescriptor, printWriter, strArr);
        }

        public void onConnectionFailed(ConnectionResult connectionResult) {
            this.f2088b = true;
            m3347a(connectionResult);
        }

        protected void onReset() {
            this.f2089c = null;
            this.f2088b = false;
            this.f2087a.m3228b((C0221b) this);
            this.f2087a.m3229b((C0222c) this);
            this.f2087a.m3230c();
        }

        protected void onStartLoading() {
            super.onStartLoading();
            this.f2087a.m3223a((C0221b) this);
            this.f2087a.m3224a((C0222c) this);
            if (this.f2089c != null) {
                deliverResult(this.f2089c);
            }
            if (!this.f2087a.m3231d() && !this.f2087a.m3232e() && !this.f2088b) {
                this.f2087a.m3227b();
            }
        }

        protected void onStopLoading() {
            this.f2087a.m3230c();
        }
    }

    /* renamed from: com.google.android.gms.common.api.n.b */
    private static class C0259b {
        public final C0226d f2090a;
        public final C0222c f2091b;

        private C0259b(C0226d c0226d, C0222c c0222c) {
            this.f2090a = c0226d;
            this.f2091b = c0222c;
        }
    }

    /* renamed from: com.google.android.gms.common.api.n.c */
    private class C0260c implements Runnable {
        final /* synthetic */ C0261n f2092a;
        private final int f2093b;
        private final ConnectionResult f2094c;

        public C0260c(C0261n c0261n, int i, ConnectionResult connectionResult) {
            this.f2092a = c0261n;
            this.f2093b = i;
            this.f2094c = connectionResult;
        }

        public void run() {
            if (this.f2094c.m3159a()) {
                try {
                    this.f2094c.m3158a(this.f2092a.getActivity(), ((this.f2092a.getActivity().getSupportFragmentManager().getFragments().indexOf(this.f2092a) + 1) << 16) + 1);
                } catch (SendIntentException e) {
                    this.f2092a.m3352a();
                }
            } else if (C0270e.m3394b(this.f2094c.m3161c())) {
                C0270e.m3384a(this.f2094c.m3161c(), this.f2092a.getActivity(), this.f2092a, 2, this.f2092a);
            } else {
                this.f2092a.m3356b(this.f2093b, this.f2094c);
            }
        }
    }

    public C0261n() {
        this.f2096b = -1;
        this.f2098d = new Handler(Looper.getMainLooper());
        this.f2099e = new SparseArray();
    }

    public static C0261n m3351a(FragmentActivity fragmentActivity) {
        C0453u.m3853b("Must be called from main thread of process");
        FragmentManager supportFragmentManager = fragmentActivity.getSupportFragmentManager();
        try {
            C0261n c0261n = (C0261n) supportFragmentManager.findFragmentByTag("GmsSupportLifecycleFragment");
            if (c0261n != null && !c0261n.isRemoving()) {
                return c0261n;
            }
            Fragment c0261n2 = new C0261n();
            supportFragmentManager.beginTransaction().add(c0261n2, "GmsSupportLifecycleFragment").commit();
            supportFragmentManager.executePendingTransactions();
            return c0261n2;
        } catch (Throwable e) {
            throw new IllegalStateException("Fragment with tag GmsSupportLifecycleFragment is not a SupportLifecycleFragment", e);
        }
    }

    private void m3352a() {
        int i = 0;
        this.f2095a = false;
        this.f2096b = -1;
        this.f2097c = null;
        LoaderManager loaderManager = getLoaderManager();
        while (i < this.f2099e.size()) {
            int keyAt = this.f2099e.keyAt(i);
            C0258a c = m3361c(keyAt);
            if (c != null && c.m3350a()) {
                loaderManager.destroyLoader(keyAt);
                loaderManager.initLoader(keyAt, null, this);
            }
            i++;
        }
    }

    private void m3353a(int i, ConnectionResult connectionResult) {
        if (!this.f2095a) {
            this.f2095a = true;
            this.f2096b = i;
            this.f2097c = connectionResult;
            this.f2098d.post(new C0260c(this, i, connectionResult));
        }
    }

    private void m3356b(int i, ConnectionResult connectionResult) {
        Log.w("GmsSupportLifecycleFragment", "Unresolved error while connecting client. Stopping auto-manage.");
        C0259b c0259b = (C0259b) this.f2099e.get(i);
        if (c0259b != null) {
            m3360b(i);
            C0222c c0222c = c0259b.f2091b;
            if (c0222c != null) {
                c0222c.onConnectionFailed(connectionResult);
            }
        }
        m3352a();
    }

    public C0226d m3357a(int i) {
        if (getActivity() != null) {
            C0258a c = m3361c(i);
            if (c != null) {
                return c.f2087a;
            }
        }
        return null;
    }

    public void m3358a(int i, C0226d c0226d, C0222c c0222c) {
        C0453u.m3847a((Object) c0226d, (Object) "GoogleApiClient instance cannot be null");
        C0453u.m3851a(this.f2099e.indexOfKey(i) < 0, "Already managing a GoogleApiClient with id " + i);
        this.f2099e.put(i, new C0259b(c0222c, null));
        if (getActivity() != null) {
            LoaderManager.enableDebugLogging(false);
            getLoaderManager().initLoader(i, null, this);
        }
    }

    public void m3359a(Loader<ConnectionResult> loader, ConnectionResult connectionResult) {
        if (!connectionResult.m3160b()) {
            m3353a(loader.getId(), connectionResult);
        }
    }

    public void m3360b(int i) {
        this.f2099e.remove(i);
        getLoaderManager().destroyLoader(i);
    }

    C0258a m3361c(int i) {
        try {
            return (C0258a) getLoaderManager().getLoader(i);
        } catch (Throwable e) {
            throw new IllegalStateException("Unknown loader in SupportLifecycleFragment", e);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onActivityResult(int r4, int r5, android.content.Intent r6) {
        /*
        r3 = this;
        r0 = 1;
        r1 = 0;
        switch(r4) {
            case 1: goto L_0x0017;
            case 2: goto L_0x000c;
            default: goto L_0x0005;
        };
    L_0x0005:
        r0 = r1;
    L_0x0006:
        if (r0 == 0) goto L_0x001b;
    L_0x0008:
        r3.m3352a();
    L_0x000b:
        return;
    L_0x000c:
        r2 = r3.getActivity();
        r2 = com.google.android.gms.common.C0270e.m3379a(r2);
        if (r2 != 0) goto L_0x0005;
    L_0x0016:
        goto L_0x0006;
    L_0x0017:
        r2 = -1;
        if (r5 != r2) goto L_0x0005;
    L_0x001a:
        goto L_0x0006;
    L_0x001b:
        r0 = r3.f2096b;
        r1 = r3.f2097c;
        r3.m3356b(r0, r1);
        goto L_0x000b;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.api.n.onActivityResult(int, int, android.content.Intent):void");
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        int i = 0;
        while (i < this.f2099e.size()) {
            int keyAt = this.f2099e.keyAt(i);
            C0258a c = m3361c(keyAt);
            if (c == null || ((C0259b) this.f2099e.valueAt(i)).f2090a == c.f2087a) {
                getLoaderManager().initLoader(keyAt, null, this);
            } else {
                getLoaderManager().restartLoader(keyAt, null, this);
            }
            i++;
        }
    }

    public void onCancel(DialogInterface dialogInterface) {
        m3356b(this.f2096b, this.f2097c);
    }

    public void onCreate(Bundle bundle) {
        TraceMachine.startTracing("n");
        try {
            TraceMachine.enterMethod(this._nr_trace, "n#onCreate", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "n#onCreate", null);
                break;
            }
        }
        super.onCreate(bundle);
        if (bundle != null) {
            this.f2095a = bundle.getBoolean("resolving_error", false);
            this.f2096b = bundle.getInt("failed_client_id", -1);
            if (this.f2096b >= 0) {
                this.f2097c = new ConnectionResult(bundle.getInt("failed_status"), (PendingIntent) bundle.getParcelable("failed_resolution"));
            }
        }
        TraceMachine.exitMethod();
    }

    public Loader<ConnectionResult> onCreateLoader(int i, Bundle bundle) {
        return new C0258a(getActivity(), ((C0259b) this.f2099e.get(i)).f2090a);
    }

    public /* synthetic */ void onLoadFinished(Loader loader, Object obj) {
        m3359a(loader, (ConnectionResult) obj);
    }

    public void onLoaderReset(Loader<ConnectionResult> loader) {
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putBoolean("resolving_error", this.f2095a);
        if (this.f2096b >= 0) {
            bundle.putInt("failed_client_id", this.f2096b);
            bundle.putInt("failed_status", this.f2097c.m3161c());
            bundle.putParcelable("failed_resolution", this.f2097c.m3162d());
        }
    }

    public void onStart() {
        ApplicationStateMonitor.getInstance().activityStarted();
        super.onStart();
        if (!this.f2095a) {
            for (int i = 0; i < this.f2099e.size(); i++) {
                getLoaderManager().initLoader(this.f2099e.keyAt(i), null, this);
            }
        }
    }

    protected void onStop() {
        super.onStop();
        ApplicationStateMonitor.getInstance().activityStopped();
    }
}
