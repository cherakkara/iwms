package com.google.android.m4b.maps.p043e;

import android.content.Context;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import com.google.android.m4b.maps.ca.ConnectionResult;
import com.google.android.m4b.maps.ca.GooglePlayServicesClient.GooglePlayServicesClient;
import com.google.android.m4b.maps.p043e.Api.Api;
import com.google.android.m4b.maps.p043e.BaseImplementation.BaseImplementation;
import com.google.android.m4b.maps.p043e.GoogleApiClient.GoogleApiClient;
import com.google.android.m4b.maps.p047g.ClientSettings;
import com.google.android.m4b.maps.p047g.GmsClientEventManager;
import com.google.android.m4b.maps.p047g.Preconditions;
import com.sothree.slidinguppanel.p086a.R.R;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* renamed from: com.google.android.m4b.maps.e.d */
final class GoogleApiClientImpl implements GoogleApiClient {
    final Handler f7338a;
    final Set<GoogleApiClientImpl<?>> f7339b;
    private final Lock f7340c;
    private final Condition f7341d;
    private final GmsClientEventManager f7342e;
    private Queue<GoogleApiClientImpl<?>> f7343f;
    private ConnectionResult f7344g;
    private int f7345h;
    private int f7346i;
    private int f7347j;
    private boolean f7348k;
    private int f7349l;
    private long f7350m;
    private final Bundle f7351n;
    private final Map<Api<?>, Api> f7352o;
    private boolean f7353p;
    private final GoogleApiClientImpl f7354q;
    private GoogleApiClient f7355r;
    private final GmsClientEventManager.GmsClientEventManager f7356s;

    /* renamed from: com.google.android.m4b.maps.e.d.c */
    interface GoogleApiClientImpl<A extends Api> {
        void m10250a();

        void m10251a(A a);

        void m10252a(GoogleApiClientImpl googleApiClientImpl);

        Api<A> m10253b();

        void m10254b(Status status);
    }

    /* renamed from: com.google.android.m4b.maps.e.d.a */
    interface GoogleApiClientImpl {
    }

    /* renamed from: com.google.android.m4b.maps.e.d.1 */
    class GoogleApiClientImpl implements GoogleApiClientImpl {
        private /* synthetic */ GoogleApiClientImpl f7332a;

        GoogleApiClientImpl(GoogleApiClientImpl googleApiClientImpl) {
            this.f7332a = googleApiClientImpl;
        }
    }

    /* renamed from: com.google.android.m4b.maps.e.d.2 */
    class GoogleApiClientImpl implements GoogleApiClient {
        private /* synthetic */ GoogleApiClientImpl f7333a;

        GoogleApiClientImpl(GoogleApiClientImpl googleApiClientImpl) {
            this.f7333a = googleApiClientImpl;
        }

        public final void m10270a(Bundle bundle) {
            this.f7333a.f7340c.lock();
            try {
                if (this.f7333a.f7346i == 1) {
                    if (bundle != null) {
                        this.f7333a.f7351n.putAll(bundle);
                    }
                    GoogleApiClientImpl.m10283d(this.f7333a);
                }
                this.f7333a.f7340c.unlock();
            } catch (Throwable th) {
                this.f7333a.f7340c.unlock();
            }
        }

        public final void m10269a(int i) {
            this.f7333a.f7340c.lock();
            try {
                this.f7333a.m10276a(i);
                switch (i) {
                    case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                        if (!this.f7333a.m10287f()) {
                            this.f7333a.f7347j = 2;
                            this.f7333a.f7338a.sendMessageDelayed(this.f7333a.f7338a.obtainMessage(1), this.f7333a.f7350m);
                            break;
                        }
                        this.f7333a.f7340c.unlock();
                        return;
                    case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                        this.f7333a.m10293a();
                        break;
                }
                this.f7333a.f7340c.unlock();
            } catch (Throwable th) {
                this.f7333a.f7340c.unlock();
            }
        }
    }

    /* renamed from: com.google.android.m4b.maps.e.d.3 */
    class GoogleApiClientImpl implements GmsClientEventManager.GmsClientEventManager {
        private /* synthetic */ GoogleApiClientImpl f7334a;

        GoogleApiClientImpl(GoogleApiClientImpl googleApiClientImpl) {
            this.f7334a = googleApiClientImpl;
        }

        public final boolean k_() {
            return this.f7334a.f7353p;
        }

        public final boolean m10272c() {
            return this.f7334a.m10295c();
        }
    }

    /* renamed from: com.google.android.m4b.maps.e.d.4 */
    class GoogleApiClientImpl implements GooglePlayServicesClient {
        private /* synthetic */ Api f7335a;
        private /* synthetic */ GoogleApiClientImpl f7336b;

        GoogleApiClientImpl(GoogleApiClientImpl googleApiClientImpl, Api api) {
            this.f7336b = googleApiClientImpl;
            this.f7335a = api;
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final void m10273a(com.google.android.m4b.maps.ca.ConnectionResult r3) {
            /*
            r2 = this;
            r1 = 2147483647; // 0x7fffffff float:NaN double:1.060997895E-314;
            r0 = r2.f7336b;
            r0 = r0.f7340c;
            r0.lock();
            r0 = r2.f7336b;	 Catch:{ all -> 0x003c }
            r0 = r0.f7344g;	 Catch:{ all -> 0x003c }
            if (r0 == 0) goto L_0x001e;
        L_0x0014:
            r0 = r2.f7335a;	 Catch:{ all -> 0x003c }
            r0 = r2.f7336b;	 Catch:{ all -> 0x003c }
            r0 = r0.f7345h;	 Catch:{ all -> 0x003c }
            if (r1 >= r0) goto L_0x002d;
        L_0x001e:
            r0 = r2.f7336b;	 Catch:{ all -> 0x003c }
            r0.f7344g = r3;	 Catch:{ all -> 0x003c }
            r0 = r2.f7336b;	 Catch:{ all -> 0x003c }
            r1 = r2.f7335a;	 Catch:{ all -> 0x003c }
            r1 = 2147483647; // 0x7fffffff float:NaN double:1.060997895E-314;
            r0.f7345h = r1;	 Catch:{ all -> 0x003c }
        L_0x002d:
            r0 = r2.f7336b;	 Catch:{ all -> 0x003c }
            com.google.android.m4b.maps.p043e.GoogleApiClientImpl.m10283d(r0);	 Catch:{ all -> 0x003c }
            r0 = r2.f7336b;
            r0 = r0.f7340c;
            r0.unlock();
            return;
        L_0x003c:
            r0 = move-exception;
            r1 = r2.f7336b;
            r1 = r1.f7340c;
            r1.unlock();
            throw r0;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.m4b.maps.e.d.4.a(com.google.android.m4b.maps.ca.a):void");
        }
    }

    /* renamed from: com.google.android.m4b.maps.e.d.b */
    class GoogleApiClientImpl extends Handler {
        private /* synthetic */ GoogleApiClientImpl f7337a;

        GoogleApiClientImpl(GoogleApiClientImpl googleApiClientImpl, Looper looper) {
            this.f7337a = googleApiClientImpl;
            super(looper);
        }

        public final void handleMessage(Message message) {
            if (message.what == 1) {
                this.f7337a.f7340c.lock();
                try {
                    if (!(this.f7337a.m10295c() || this.f7337a.m10296d())) {
                        this.f7337a.m10293a();
                    }
                    this.f7337a.f7340c.unlock();
                } catch (Throwable th) {
                    this.f7337a.f7340c.unlock();
                }
            } else {
                Log.wtf("GoogleApiClientImpl", "Don't know how to handle this message.");
            }
        }
    }

    static /* synthetic */ void m10283d(GoogleApiClientImpl googleApiClientImpl) {
        googleApiClientImpl.f7340c.lock();
        try {
            googleApiClientImpl.f7349l--;
            if (googleApiClientImpl.f7349l == 0) {
                if (googleApiClientImpl.f7344g != null) {
                    googleApiClientImpl.f7348k = false;
                    googleApiClientImpl.m10276a(3);
                    if (googleApiClientImpl.m10287f()) {
                        googleApiClientImpl.f7347j--;
                    }
                    if (googleApiClientImpl.m10287f()) {
                        googleApiClientImpl.f7338a.sendMessageDelayed(googleApiClientImpl.f7338a.obtainMessage(1), googleApiClientImpl.f7350m);
                    } else {
                        googleApiClientImpl.f7342e.m10358a(googleApiClientImpl.f7344g);
                    }
                    googleApiClientImpl.f7353p = false;
                } else {
                    googleApiClientImpl.f7346i = 2;
                    googleApiClientImpl.m10288g();
                    googleApiClientImpl.f7341d.signalAll();
                    googleApiClientImpl.m10284e();
                    if (googleApiClientImpl.f7348k) {
                        googleApiClientImpl.f7348k = false;
                        googleApiClientImpl.m10276a(-1);
                    } else {
                        googleApiClientImpl.f7342e.m10357a(googleApiClientImpl.f7351n.isEmpty() ? null : googleApiClientImpl.f7351n);
                    }
                }
            }
            googleApiClientImpl.f7340c.unlock();
        } catch (Throwable th) {
            googleApiClientImpl.f7340c.unlock();
        }
    }

    public GoogleApiClientImpl(Context context, Looper looper, ClientSettings clientSettings, Map map, Fragment fragment, Set set, Set set2) {
        this.f7340c = new ReentrantLock();
        this.f7341d = this.f7340c.newCondition();
        this.f7343f = new LinkedList();
        this.f7346i = 4;
        this.f7347j = 0;
        this.f7348k = false;
        this.f7350m = 5000;
        this.f7351n = new Bundle();
        this.f7352o = new HashMap();
        this.f7339b = Collections.newSetFromMap(new ConcurrentHashMap());
        this.f7354q = new GoogleApiClientImpl(this);
        this.f7355r = new GoogleApiClientImpl(this);
        this.f7356s = new GoogleApiClientImpl(this);
        this.f7342e = new GmsClientEventManager(looper, this.f7356s);
        this.f7338a = new GoogleApiClientImpl(this, looper);
        for (GoogleApiClient a : set) {
            this.f7342e.m10360a(a);
        }
        for (GooglePlayServicesClient a2 : set2) {
            this.f7342e.m10359a(a2);
        }
        for (Api api : map.keySet()) {
            Api a3 = api.m10240a();
            Object obj = map.get(api);
            this.f7352o.put(api.m10242c(), a3.m10239a(context, looper, clientSettings, obj, this.f7355r, new GoogleApiClientImpl(this, a3)));
        }
        Collections.unmodifiableList(clientSettings.m10322a());
    }

    public final <A extends Api, T extends BaseImplementation<? extends Result, A>> T m10292a(T t) {
        boolean z = m10295c() || m10287f();
        Preconditions.m10463a(z, (Object) "GoogleApiClient is not connected yet.");
        m10284e();
        try {
            m10277a((GoogleApiClientImpl) t);
        } catch (DeadObjectException e) {
            m10276a(1);
        }
        return t;
    }

    private <A extends Api> void m10277a(GoogleApiClientImpl<A> googleApiClientImpl) {
        boolean z = true;
        this.f7340c.lock();
        try {
            boolean z2 = m10295c() || m10287f();
            Preconditions.m10463a(z2, (Object) "GoogleApiClient is not connected yet.");
            if (googleApiClientImpl.m10253b() == null) {
                z = false;
            }
            Preconditions.m10466b(z, "This task can not be executed or enqueued (it's probably a Batch or malformed)");
            this.f7339b.add(googleApiClientImpl);
            googleApiClientImpl.m10252a(this.f7354q);
            if (m10287f()) {
                googleApiClientImpl.m10254b(new Status(8));
                return;
            }
            Api api = (Api) this.f7352o.get(googleApiClientImpl.m10253b());
            Preconditions.m10460a((Object) api, (Object) "Appropriate Api was not requested.");
            googleApiClientImpl.m10251a(api);
            this.f7340c.unlock();
        } finally {
            this.f7340c.unlock();
        }
    }

    private void m10284e() {
        boolean z = m10295c() || m10287f();
        Preconditions.m10463a(z, (Object) "GoogleApiClient is not connected yet.");
        this.f7340c.lock();
        while (!this.f7343f.isEmpty()) {
            try {
                try {
                    m10277a((GoogleApiClientImpl) this.f7343f.remove());
                } catch (DeadObjectException e) {
                }
            } finally {
                this.f7340c.unlock();
            }
        }
    }

    public final void m10293a() {
        this.f7340c.lock();
        try {
            this.f7348k = false;
            if (m10295c() || m10296d()) {
                this.f7340c.unlock();
                return;
            }
            this.f7353p = true;
            this.f7344g = null;
            this.f7346i = 1;
            this.f7351n.clear();
            this.f7349l = this.f7352o.size();
            for (Api a : this.f7352o.values()) {
                a.m10235a();
            }
            this.f7340c.unlock();
        } catch (Throwable th) {
            this.f7340c.unlock();
        }
    }

    public final void m10294b() {
        m10288g();
        m10276a(-1);
    }

    private void m10276a(int i) {
        this.f7340c.lock();
        try {
            if (this.f7346i != 3) {
                if (i == -1) {
                    Iterator it;
                    if (m10296d()) {
                        it = this.f7343f.iterator();
                        while (it.hasNext()) {
                            ((GoogleApiClientImpl) it.next()).m10250a();
                            it.remove();
                        }
                    } else {
                        this.f7343f.clear();
                    }
                    for (GoogleApiClientImpl a : this.f7339b) {
                        a.m10250a();
                    }
                    this.f7339b.clear();
                    if (this.f7344g == null && !this.f7343f.isEmpty()) {
                        this.f7348k = true;
                        return;
                    }
                }
                boolean d = m10296d();
                boolean c = m10295c();
                this.f7346i = 3;
                if (d) {
                    if (i == -1) {
                        this.f7344g = null;
                    }
                    this.f7341d.signalAll();
                }
                this.f7353p = false;
                for (Api api : this.f7352o.values()) {
                    if (api.m10237c()) {
                        api.m10236b();
                    }
                }
                this.f7353p = true;
                this.f7346i = 4;
                if (c) {
                    if (i != -1) {
                        this.f7342e.m10356a(i);
                    }
                    this.f7353p = false;
                }
            }
            this.f7340c.unlock();
        } finally {
            this.f7340c.unlock();
        }
    }

    public final boolean m10295c() {
        this.f7340c.lock();
        try {
            boolean z = this.f7346i == 2;
            this.f7340c.unlock();
            return z;
        } catch (Throwable th) {
            this.f7340c.unlock();
        }
    }

    public final boolean m10296d() {
        boolean z = true;
        this.f7340c.lock();
        try {
            if (this.f7346i != 1) {
                z = false;
            }
            this.f7340c.unlock();
            return z;
        } catch (Throwable th) {
            this.f7340c.unlock();
        }
    }

    private boolean m10287f() {
        this.f7340c.lock();
        try {
            boolean z = this.f7347j != 0;
            this.f7340c.unlock();
            return z;
        } catch (Throwable th) {
            this.f7340c.unlock();
        }
    }

    private void m10288g() {
        this.f7340c.lock();
        try {
            this.f7347j = 0;
            this.f7338a.removeMessages(1);
        } finally {
            this.f7340c.unlock();
        }
    }
}
