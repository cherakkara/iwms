package com.google.android.gms.common.api;

import android.accounts.Account;
import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.C0218b.C0210a;
import com.google.android.gms.common.api.C0218b.C0210a.C0212c;
import com.google.android.gms.common.api.C0218b.C0215b;
import com.google.android.gms.common.api.C0218b.C0216c;
import com.google.android.gms.common.api.C0218b.C0217d;
import com.google.android.gms.common.api.C0234j.C0233a;
import com.google.android.gms.common.internal.C0419h;
import com.google.android.gms.common.internal.C0453u;
import com.google.android.gms.internal.bf;
import com.google.android.gms.internal.bh;
import com.google.android.gms.internal.bi;
import com.google.android.gms.internal.bi.C0489a;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* renamed from: com.google.android.gms.common.api.d */
public interface C0226d {

    /* renamed from: com.google.android.gms.common.api.d.a */
    public static final class C0220a {
        private Account f2009a;
        private final Set<String> f2010b;
        private int f2011c;
        private View f2012d;
        private String f2013e;
        private String f2014f;
        private final Context f2015g;
        private final Map<C0218b<?>, C0210a> f2016h;
        private final Map<C0218b<?>, Boolean> f2017i;
        private FragmentActivity f2018j;
        private int f2019k;
        private C0222c f2020l;
        private Looper f2021m;
        private C0216c<? extends bh, bi> f2022n;
        private final Set<C0221b> f2023o;
        private final Set<C0222c> f2024p;
        private C0489a f2025q;

        public C0220a(Context context) {
            this.f2010b = new HashSet();
            this.f2016h = new HashMap();
            this.f2017i = new HashMap();
            this.f2019k = -1;
            this.f2023o = new HashSet();
            this.f2024p = new HashSet();
            this.f2025q = new C0489a();
            this.f2015g = context;
            this.f2021m = context.getMainLooper();
            this.f2013e = context.getPackageName();
            this.f2014f = context.getClass().getName();
            this.f2022n = bf.f2386b;
        }

        private C0226d m3206c() {
            C0261n a = C0261n.m3351a(this.f2018j);
            C0226d a2 = a.m3357a(this.f2019k);
            if (a2 == null) {
                a2 = new C0254k(this.f2015g.getApplicationContext(), this.f2021m, m3210a(), this.f2022n, this.f2016h, this.f2017i, this.f2023o, this.f2024p, this.f2019k);
            }
            a.m3358a(this.f2019k, a2, this.f2020l);
            return a2;
        }

        public C0220a m3207a(C0218b<? extends C0212c> c0218b) {
            this.f2016h.put(c0218b, null);
            List b = c0218b.m3203b();
            int size = b.size();
            for (int i = 0; i < size; i++) {
                this.f2010b.add(((Scope) b.get(i)).m3166a());
            }
            return this;
        }

        public C0220a m3208a(C0221b c0221b) {
            this.f2023o.add(c0221b);
            return this;
        }

        public C0220a m3209a(C0222c c0222c) {
            this.f2024p.add(c0222c);
            return this;
        }

        public C0419h m3210a() {
            return new C0419h(this.f2009a, this.f2010b, this.f2011c, this.f2012d, this.f2013e, this.f2014f, this.f2025q.m4070a());
        }

        public C0226d m3211b() {
            C0453u.m3855b(!this.f2016h.isEmpty(), "must call addApi() to add at least one API");
            return this.f2019k >= 0 ? m3206c() : new C0254k(this.f2015g, this.f2021m, m3210a(), this.f2022n, this.f2016h, this.f2017i, this.f2023o, this.f2024p, -1);
        }
    }

    /* renamed from: com.google.android.gms.common.api.d.b */
    public interface C0221b {
        void m3212a(int i);

        void m3213a(Bundle bundle);
    }

    /* renamed from: com.google.android.gms.common.api.d.c */
    public interface C0222c {
        void onConnectionFailed(ConnectionResult connectionResult);
    }

    /* renamed from: com.google.android.gms.common.api.d.d */
    public interface C0224d {

        /* renamed from: com.google.android.gms.common.api.d.d.a */
        public static class C0223a {
            private boolean f2026a;
            private Set<Scope> f2027b;

            public boolean m3214a() {
                return this.f2026a;
            }

            public Set<Scope> m3215b() {
                return this.f2027b;
            }
        }

        C0223a m3216a(String str, Set<Scope> set);

        boolean m3217a(String str, String str2);
    }

    /* renamed from: com.google.android.gms.common.api.d.e */
    public interface C0225e {
        void m3218a();

        void m3219b();
    }

    Looper m3220a();

    <C extends C0215b> C m3221a(C0217d<C> c0217d);

    <A extends C0215b, R extends C0206g, T extends C0233a<R, A>> T m3222a(T t);

    void m3223a(C0221b c0221b);

    void m3224a(C0222c c0222c);

    void m3225a(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr);

    <A extends C0215b, T extends C0233a<? extends C0206g, A>> T m3226b(T t);

    void m3227b();

    void m3228b(C0221b c0221b);

    void m3229b(C0222c c0222c);

    void m3230c();

    boolean m3231d();

    boolean m3232e();
}
