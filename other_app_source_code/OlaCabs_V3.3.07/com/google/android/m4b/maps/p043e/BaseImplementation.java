package com.google.android.m4b.maps.p043e;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import android.util.Pair;
import com.google.android.m4b.maps.p043e.Api.Api;
import com.google.android.m4b.maps.p043e.GoogleApiClientImpl.GoogleApiClientImpl;
import com.google.android.m4b.maps.p047g.ICancelToken;
import com.google.android.m4b.maps.p047g.Preconditions;
import com.sothree.slidinguppanel.p086a.R.R;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CountDownLatch;

/* renamed from: com.google.android.m4b.maps.e.b */
public final class BaseImplementation {

    /* renamed from: com.google.android.m4b.maps.e.b.a */
    public static abstract class BaseImplementation implements PendingResult {
        private final Object f7313a;
        private BaseImplementation<R> f7314b;
        private final CountDownLatch f7315c;
        private final ArrayList f7316d;
        private ResultCallback<R> f7317e;
        private volatile R f7318f;
        private volatile boolean f7319g;
        private boolean f7320h;
        private boolean f7321i;
        private ICancelToken f7322j;

        protected abstract R m10246a(Status status);

        static /* synthetic */ void m10243a(BaseImplementation baseImplementation) {
            synchronized (baseImplementation.f7313a) {
                if (!baseImplementation.m10245c()) {
                    baseImplementation.m10249a(baseImplementation.m10246a(Status.f7359b));
                    baseImplementation.f7321i = true;
                }
            }
        }

        BaseImplementation() {
            this.f7313a = new Object();
            this.f7315c = new CountDownLatch(1);
            this.f7316d = new ArrayList();
        }

        private boolean m10245c() {
            return this.f7315c.getCount() == 0;
        }

        public final void m10247a() {
            synchronized (this.f7313a) {
                if (this.f7320h || this.f7319g) {
                    return;
                }
                BaseImplementation.m10261a(this.f7318f);
                this.f7317e = null;
                this.f7320h = true;
                m10244b(m10246a(Status.f7360c));
            }
        }

        public final void m10249a(R r) {
            boolean z = true;
            synchronized (this.f7313a) {
                if (this.f7321i || this.f7320h) {
                    BaseImplementation.m10261a(r);
                    return;
                }
                boolean z2;
                if (m10245c()) {
                    z2 = false;
                } else {
                    z2 = true;
                }
                Preconditions.m10463a(z2, (Object) "Results have already been set");
                if (this.f7319g) {
                    z = false;
                }
                Preconditions.m10463a(z, (Object) "Result has already been consumed");
                m10244b(r);
            }
        }

        private void m10244b(R r) {
            this.f7318f = r;
            this.f7322j = null;
            this.f7315c.countDown();
            Result result = this.f7318f;
            Iterator it = this.f7316d.iterator();
            while (it.hasNext()) {
                it.next();
            }
            this.f7316d.clear();
        }

        protected final void m10248a(BaseImplementation<R> baseImplementation) {
            this.f7314b = baseImplementation;
        }
    }

    /* renamed from: com.google.android.m4b.maps.e.b.b */
    public static abstract class BaseImplementation<R extends Result, A extends Api> extends BaseImplementation<R> implements GoogleApiClientImpl<A> {
        private final Api<A> f7323a;
        private GoogleApiClientImpl f7324b;

        protected abstract void m10259b(A a);

        protected BaseImplementation(Api<A> api) {
            this.f7323a = (Api) Preconditions.m10459a((Object) api);
        }

        public final Api<A> m10258b() {
            return this.f7323a;
        }

        public final void m10256a(A a) {
            m10248a(new BaseImplementation(a.m10238d()));
            try {
                m10259b((Api) a);
            } catch (RemoteException e) {
                m10255a(e);
                throw e;
            } catch (RemoteException e2) {
                m10255a(e2);
            }
        }

        public final void m10260b(Status status) {
            Preconditions.m10466b(!status.m10301d(), "Failed result must not be success");
            m10249a(m10246a(status));
        }

        public final void m10257a(GoogleApiClientImpl googleApiClientImpl) {
            this.f7324b = googleApiClientImpl;
        }

        private void m10255a(RemoteException remoteException) {
            m10260b(new Status(8, remoteException.getLocalizedMessage(), null));
        }
    }

    /* renamed from: com.google.android.m4b.maps.e.b.c */
    public static class BaseImplementation<R extends Result> extends Handler {
        public BaseImplementation() {
            this(Looper.getMainLooper());
        }

        public BaseImplementation(Looper looper) {
            super(looper);
        }

        public final void handleMessage(Message message) {
            switch (message.what) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    Pair pair = (Pair) message.obj;
                    ResultCallback resultCallback = (ResultCallback) pair.first;
                    Result result = (Result) pair.second;
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    BaseImplementation.m10243a((BaseImplementation) message.obj);
                default:
                    Log.wtf("GoogleApi", "Don't know how to handle this message.");
            }
        }
    }

    static void m10261a(Result result) {
        if (!(result instanceof Releasable)) {
        }
    }
}
