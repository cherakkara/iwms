package com.google.android.gms.location;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.C0206g;
import com.google.android.gms.common.api.C0218b;
import com.google.android.gms.common.api.C0218b.C0210a.C0213b;
import com.google.android.gms.common.api.C0218b.C0215b;
import com.google.android.gms.common.api.C0218b.C0216c;
import com.google.android.gms.common.api.C0218b.C0217d;
import com.google.android.gms.common.api.C0226d;
import com.google.android.gms.common.api.C0226d.C0221b;
import com.google.android.gms.common.api.C0226d.C0222c;
import com.google.android.gms.common.api.C0234j.C0233a;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.C0419h;
import com.google.android.gms.common.internal.C0453u;
import com.google.android.gms.internal.ae;
import com.google.android.gms.internal.af;
import com.google.android.gms.internal.ak;
import com.google.android.gms.internal.ap;

/* renamed from: com.google.android.gms.location.g */
public class C0532g {
    public static final C0218b<C0213b> f2650a;
    public static C0468c f2651b;
    public static C0469e f2652c;
    public static C0485i f2653d;
    private static final C0217d<ak> f2654e;
    private static final C0216c<ak, C0213b> f2655f;

    /* renamed from: com.google.android.gms.location.g.a */
    public static abstract class C0464a<R extends C0206g> extends C0233a<R, ak> {
        public C0464a(C0226d c0226d) {
            super(C0532g.f2654e, c0226d);
        }
    }

    /* renamed from: com.google.android.gms.location.g.1 */
    static class C05311 implements C0216c<ak, C0213b> {
        C05311() {
        }

        public int m4360a() {
            return Integer.MAX_VALUE;
        }

        public /* synthetic */ C0215b m4361a(Context context, Looper looper, C0419h c0419h, Object obj, C0221b c0221b, C0222c c0222c) {
            return m4362a(context, looper, c0419h, (C0213b) obj, c0221b, c0222c);
        }

        public ak m4362a(Context context, Looper looper, C0419h c0419h, C0213b c0213b, C0221b c0221b, C0222c c0222c) {
            return new ak(context, looper, context.getPackageName(), c0221b, c0222c, "locationServices", c0419h.m3573a());
        }
    }

    static {
        f2654e = new C0217d();
        f2655f = new C05311();
        f2650a = new C0218b(f2655f, f2654e, new Scope[0]);
        f2651b = new ae();
        f2652c = new af();
        f2653d = new ap();
    }

    public static ak m4364a(C0226d c0226d) {
        boolean z = true;
        C0453u.m3855b(c0226d != null, "GoogleApiClient parameter is required.");
        ak akVar = (ak) c0226d.m3221a(f2654e);
        if (akVar == null) {
            z = false;
        }
        C0453u.m3851a(z, (Object) "GoogleApiClient is not configured to use the LocationServices.API Api. Pass thisinto GoogleApiClient.Builder#addApi() to use this feature.");
        return akVar;
    }
}
