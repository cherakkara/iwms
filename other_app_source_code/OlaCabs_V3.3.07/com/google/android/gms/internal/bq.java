package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.C0226d.C0221b;
import com.google.android.gms.common.api.C0226d.C0222c;
import com.google.android.gms.common.api.C0226d.C0224d;
import com.google.android.gms.common.api.C0226d.C0224d.C0223a;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.BinderWrapper;
import com.google.android.gms.common.internal.C0249s;
import com.google.android.gms.common.internal.C0411p;
import com.google.android.gms.common.internal.C0419h;
import com.google.android.gms.common.internal.C0432k;
import com.google.android.gms.common.internal.C0453u;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.common.internal.zzc;
import com.google.android.gms.common.internal.zzy;
import com.google.android.gms.internal.bm.C0490a;
import com.google.android.gms.internal.bo.C0493a;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;

public class bq extends C0432k<bo> implements bh {
    private final C0419h f2410d;
    private final bi f2411e;
    private Integer f2412f;
    private final ExecutorService f2413g;

    /* renamed from: com.google.android.gms.internal.bq.a */
    private static class C0496a extends C0490a {
        private final bi f2408a;
        private final ExecutorService f2409b;

        /* renamed from: com.google.android.gms.internal.bq.a.1 */
        class C04941 implements Runnable {
            final /* synthetic */ List f2400a;
            final /* synthetic */ String f2401b;
            final /* synthetic */ bo f2402c;
            final /* synthetic */ C0496a f2403d;

            C04941(C0496a c0496a, List list, String str, bo boVar) {
                this.f2403d = c0496a;
                this.f2400a = list;
                this.f2401b = str;
                this.f2402c = boVar;
            }

            public void run() {
                try {
                    C0223a a = this.f2403d.m4100a().m3216a(this.f2401b, Collections.unmodifiableSet(new HashSet(this.f2400a)));
                    this.f2402c.m4090a(new zzuw(a.m3214a(), a.m3215b()));
                } catch (Throwable e) {
                    Log.e("SignInClientImpl", "RemoteException thrown when processing checkServerAuthorization callback", e);
                }
            }
        }

        /* renamed from: com.google.android.gms.internal.bq.a.2 */
        class C04952 implements Runnable {
            final /* synthetic */ String f2404a;
            final /* synthetic */ String f2405b;
            final /* synthetic */ bo f2406c;
            final /* synthetic */ C0496a f2407d;

            C04952(C0496a c0496a, String str, String str2, bo boVar) {
                this.f2407d = c0496a;
                this.f2404a = str;
                this.f2405b = str2;
                this.f2406c = boVar;
            }

            public void run() {
                try {
                    this.f2406c.m4091a(this.f2407d.m4100a().m3217a(this.f2404a, this.f2405b));
                } catch (Throwable e) {
                    Log.e("SignInClientImpl", "RemoteException thrown when processing uploadServerAuthCode callback", e);
                }
            }
        }

        public C0496a(bi biVar, ExecutorService executorService) {
            this.f2408a = biVar;
            this.f2409b = executorService;
        }

        private C0224d m4100a() throws RemoteException {
            return this.f2408a.m4074d();
        }

        public void m4102a(String str, String str2, bo boVar) throws RemoteException {
            this.f2409b.submit(new C04952(this, str, str2, boVar));
        }

        public void m4103a(String str, List<Scope> list, bo boVar) throws RemoteException {
            this.f2409b.submit(new C04941(this, list, str, boVar));
        }
    }

    public bq(Context context, Looper looper, C0419h c0419h, bi biVar, C0221b c0221b, C0222c c0222c, ExecutorService executorService) {
        super(context, looper, 44, c0221b, c0222c, c0419h);
        this.f2410d = c0419h;
        this.f2411e = biVar;
        this.f2412f = c0419h.m3581h();
        this.f2413g = executorService;
    }

    public static Bundle m4104a(bi biVar, Integer num, ExecutorService executorService) {
        Bundle bundle = new Bundle();
        bundle.putBoolean("com.google.android.gms.signin.internal.offlineAccessRequested", biVar.m4071a());
        bundle.putBoolean("com.google.android.gms.signin.internal.idTokenRequested", biVar.m4072b());
        bundle.putString("com.google.android.gms.signin.internal.serverClientId", biVar.m4073c());
        if (biVar.m4074d() != null) {
            bundle.putParcelable("com.google.android.gms.signin.internal.signInCallbacks", new BinderWrapper(new C0496a(biVar, executorService).asBinder()));
        }
        if (num != null) {
            bundle.putInt("com.google.android.gms.common.internal.ClientSettings.sessionId", num.intValue());
        }
        return bundle;
    }

    protected /* synthetic */ IInterface m4105a(IBinder iBinder) {
        return m4109b(iBinder);
    }

    public void m4106a(C0411p c0411p, Set<Scope> set, bn bnVar) {
        C0453u.m3847a((Object) bnVar, (Object) "Expecting a valid ISignInCallbacks");
        try {
            ((bo) m3641m()).m4088a(new zzc(c0411p, set), bnVar);
        } catch (RemoteException e) {
            Log.w("SignInClientImpl", "Remote service probably died when authAccount is called");
            try {
                bnVar.m3265a(new ConnectionResult(8, null), new zzut());
            } catch (RemoteException e2) {
                Log.wtf("SignInClientImpl", "ISignInCallbacks#onAuthAccount should be executed from the same process, unexpected RemoteException.");
            }
        }
    }

    public void m4107a(C0411p c0411p, boolean z) {
        try {
            ((bo) m3641m()).m4087a(c0411p, this.f2412f.intValue(), z);
        } catch (RemoteException e) {
            Log.w("SignInClientImpl", "Remote service probably died when saveDefaultAccount is called");
        }
    }

    public void m4108a(C0249s c0249s) {
        C0453u.m3847a((Object) c0249s, (Object) "Expecting a valid IResolveAccountCallbacks");
        try {
            ((bo) m3641m()).m4089a(new zzy(this.f2410d.m3576c(), this.f2412f.intValue()), c0249s);
        } catch (RemoteException e) {
            Log.w("SignInClientImpl", "Remote service probably died when resolveAccount is called");
            try {
                c0249s.m3271a(new zzaa(8));
            } catch (RemoteException e2) {
                Log.wtf("SignInClientImpl", "IResolveAccountCallbacks#onAccountResolutionComplete should be executed from the same process, unexpected RemoteException.");
            }
        }
    }

    protected bo m4109b(IBinder iBinder) {
        return C0493a.m4099a(iBinder);
    }

    public void c_() {
        try {
            ((bo) m3641m()).m4085a(this.f2412f.intValue());
        } catch (RemoteException e) {
            Log.w("SignInClientImpl", "Remote service probably died when clearAccountFromSessionStore is called");
        }
    }

    protected String m4110e() {
        return "com.google.android.gms.signin.service.START";
    }

    protected String m4111f() {
        return "com.google.android.gms.signin.internal.ISignInService";
    }

    protected Bundle m4112k() {
        Bundle a = m4104a(this.f2411e, this.f2410d.m3581h(), this.f2413g);
        if (!m3637i().getPackageName().equals(this.f2410d.m3578e())) {
            a.putString("com.google.android.gms.signin.internal.realClientPackageName", this.f2410d.m3578e());
        }
        return a;
    }
}
