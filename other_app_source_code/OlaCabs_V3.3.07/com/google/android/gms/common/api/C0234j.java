package com.google.android.gms.common.api;

import android.os.DeadObjectException;
import android.os.RemoteException;
import com.google.android.gms.common.api.C0218b.C0215b;
import com.google.android.gms.common.api.C0218b.C0217d;
import com.google.android.gms.common.api.C0254k.C0232g;
import com.google.android.gms.common.api.C0254k.C0235e;
import com.google.android.gms.common.internal.C0453u;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: com.google.android.gms.common.api.j */
public class C0234j {

    /* renamed from: com.google.android.gms.common.api.j.b */
    public interface C0231b<R> {
        void m3239a(R r);
    }

    /* renamed from: com.google.android.gms.common.api.j.a */
    public static abstract class C0233a<R extends C0206g, A extends C0215b> extends C0209a<R> implements C0231b<R>, C0232g<A> {
        private final C0217d<A> f2028b;
        private AtomicReference<C0235e> f2029c;

        protected C0233a(C0217d<A> c0217d, C0226d c0226d) {
            super(((C0226d) C0453u.m3847a((Object) c0226d, (Object) "GoogleApiClient must not be null")).m3220a());
            this.f2029c = new AtomicReference();
            this.f2028b = (C0217d) C0453u.m3846a((Object) c0217d);
        }

        private void m3246a(RemoteException remoteException) {
            m3250b(new Status(8, remoteException.getLocalizedMessage(), null));
        }

        public final void m3247a(A a) throws DeadObjectException {
            try {
                m3251b((C0215b) a);
            } catch (RemoteException e) {
                m3246a(e);
                throw e;
            } catch (RemoteException e2) {
                m3246a(e2);
            }
        }

        public void m3248a(C0235e c0235e) {
            this.f2029c.set(c0235e);
        }

        public /* synthetic */ void m3249a(Object obj) {
            super.m3185a((C0206g) obj);
        }

        public final void m3250b(Status status) {
            C0453u.m3855b(!status.m3175f(), "Failed result must not be success");
            m3185a(m3184a(status));
        }

        protected abstract void m3251b(A a) throws RemoteException;

        protected void m3252d() {
            C0235e c0235e = (C0235e) this.f2029c.getAndSet(null);
            if (c0235e != null) {
                c0235e.m3255a(this);
            }
        }

        public final C0217d<A> m3253f() {
            return this.f2028b;
        }

        public int m3254g() {
            return 0;
        }
    }
}
