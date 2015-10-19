package com.google.android.gms.internal;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.C0218b;
import com.google.android.gms.common.api.C0218b.C0216c;
import com.google.android.gms.common.api.C0218b.C0217d;
import com.google.android.gms.common.api.C0226d.C0221b;
import com.google.android.gms.common.api.C0226d.C0222c;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.C0419h;
import java.util.concurrent.Executors;

public final class bf {
    public static final C0217d<bq> f2385a;
    public static final C0216c<bq, bi> f2386b;
    public static final C0218b<bi> f2387c;
    public static final bg f2388d;

    /* renamed from: com.google.android.gms.internal.bf.1 */
    static class C04871 implements C0216c<bq, bi> {
        C04871() {
        }

        public int m4064a() {
            return Integer.MAX_VALUE;
        }

        public bq m4066a(Context context, Looper looper, C0419h c0419h, bi biVar, C0221b c0221b, C0222c c0222c) {
            return new bq(context, looper, c0419h, biVar == null ? bi.f2393a : biVar, c0221b, c0222c, Executors.newSingleThreadExecutor());
        }
    }

    static {
        f2385a = new C0217d();
        f2386b = new C04871();
        f2387c = new C0218b(f2386b, f2385a, new Scope[0]);
        f2388d = new bp();
    }
}
