package com.google.android.gms.common.api;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import android.util.Pair;
import com.google.android.gms.common.api.C0208e.C0227a;
import com.google.android.gms.common.internal.C0410a;
import com.google.android.gms.common.internal.C0453u;
import com.sothree.slidinguppanel.p086a.R.R;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CountDownLatch;

/* renamed from: com.google.android.gms.common.api.a */
public abstract class C0209a<R extends C0206g> implements C0208e<R> {
    protected final C0207a<R> f1996a;
    private final Object f1997b;
    private final CountDownLatch f1998c;
    private final ArrayList<C0227a> f1999d;
    private C0229h<R> f2000e;
    private volatile R f2001f;
    private volatile boolean f2002g;
    private boolean f2003h;
    private boolean f2004i;
    private C0410a f2005j;

    /* renamed from: com.google.android.gms.common.api.a.a */
    public static class C0207a<R extends C0206g> extends Handler {
        public C0207a() {
            this(Looper.getMainLooper());
        }

        public C0207a(Looper looper) {
            super(looper);
        }

        public void m3177a() {
            removeMessages(2);
        }

        public void m3178a(C0229h<R> c0229h, R r) {
            sendMessage(obtainMessage(1, new Pair(c0229h, r)));
        }

        protected void m3179b(C0229h<R> c0229h, R r) {
            try {
                c0229h.m3235a(r);
            } catch (RuntimeException e) {
                C0209a.m3181b(r);
                throw e;
            }
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    Pair pair = (Pair) message.obj;
                    m3179b((C0229h) pair.first, (C0206g) pair.second);
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    ((C0209a) message.obj).m3191e();
                default:
                    Log.wtf("AbstractPendingResult", "Don't know how to handle this message.");
            }
        }
    }

    protected C0209a(Looper looper) {
        this.f1997b = new Object();
        this.f1998c = new CountDownLatch(1);
        this.f1999d = new ArrayList();
        this.f1996a = new C0207a(looper);
    }

    static void m3181b(C0206g c0206g) {
        if (c0206g instanceof C0228f) {
            try {
                ((C0228f) c0206g).m3234a();
            } catch (Throwable e) {
                Log.w("AbstractPendingResult", "Unable to release " + c0206g, e);
            }
        }
    }

    private void m3182c(R r) {
        this.f2001f = r;
        this.f2005j = null;
        this.f1998c.countDown();
        Status a = this.f2001f.m3167a();
        if (this.f2000e != null) {
            this.f1996a.m3177a();
            if (!this.f2003h) {
                this.f1996a.m3178a(this.f2000e, m3183h());
            }
        }
        Iterator it = this.f1999d.iterator();
        while (it.hasNext()) {
            ((C0227a) it.next()).m3233a(a);
        }
        this.f1999d.clear();
    }

    private R m3183h() {
        R r;
        boolean z = true;
        synchronized (this.f1997b) {
            if (this.f2002g) {
                z = false;
            }
            C0453u.m3851a(z, (Object) "Result has already been consumed.");
            C0453u.m3851a(m3187a(), (Object) "Result is not ready.");
            r = this.f2001f;
            this.f2001f = null;
            this.f2000e = null;
            this.f2002g = true;
        }
        m3190d();
        return r;
    }

    protected abstract R m3184a(Status status);

    public final void m3185a(R r) {
        boolean z = true;
        synchronized (this.f1997b) {
            if (this.f2004i || this.f2003h) {
                C0209a.m3181b(r);
                return;
            }
            C0453u.m3851a(!m3187a(), (Object) "Results have already been set");
            if (this.f2002g) {
                z = false;
            }
            C0453u.m3851a(z, (Object) "Result has already been consumed");
            m3182c(r);
        }
    }

    public final void m3186a(C0229h<R> c0229h) {
        C0453u.m3851a(!this.f2002g, (Object) "Result has already been consumed.");
        synchronized (this.f1997b) {
            if (m3189c()) {
                return;
            }
            if (m3187a()) {
                this.f1996a.m3178a(c0229h, m3183h());
            } else {
                this.f2000e = c0229h;
            }
        }
    }

    public final boolean m3187a() {
        return this.f1998c.getCount() == 0;
    }

    public void m3188b() {
        synchronized (this.f1997b) {
            if (this.f2003h || this.f2002g) {
                return;
            }
            if (this.f2005j != null) {
                try {
                    this.f2005j.m3555a();
                } catch (RemoteException e) {
                }
            }
            C0209a.m3181b(this.f2001f);
            this.f2000e = null;
            this.f2003h = true;
            m3182c(m3184a(Status.f1991e));
        }
    }

    public boolean m3189c() {
        boolean z;
        synchronized (this.f1997b) {
            z = this.f2003h;
        }
        return z;
    }

    protected void m3190d() {
    }

    void m3191e() {
        synchronized (this.f1997b) {
            if (!m3187a()) {
                m3185a(m3184a(Status.f1990d));
                this.f2004i = true;
            }
        }
    }
}
