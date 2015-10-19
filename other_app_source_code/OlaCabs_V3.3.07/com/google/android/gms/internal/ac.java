package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import com.google.android.gms.common.api.C0226d.C0221b;
import com.google.android.gms.common.api.C0226d.C0222c;
import com.google.android.gms.common.internal.C0432k;
import com.google.android.gms.internal.ah.C0473a;

public class ac extends C0432k<ah> {
    protected final ao<ah> f2353d;
    private final String f2354e;

    /* renamed from: com.google.android.gms.internal.ac.1 */
    class C04631 implements ao<ah> {
        final /* synthetic */ ac f2352a;

        C04631(ac acVar) {
            this.f2352a = acVar;
        }

        public void m3900a() {
            this.f2352a.m3640l();
        }

        public ah m3901b() throws DeadObjectException {
            return (ah) this.f2352a.m3641m();
        }

        public /* synthetic */ IInterface m3902c() throws DeadObjectException {
            return m3901b();
        }
    }

    public ac(Context context, Looper looper, C0221b c0221b, C0222c c0222c, String str) {
        super(context, looper, 23, c0221b, c0222c);
        this.f2353d = new C04631(this);
        this.f2354e = str;
    }

    protected /* synthetic */ IInterface m3904a(IBinder iBinder) {
        return m3905b(iBinder);
    }

    protected ah m3905b(IBinder iBinder) {
        return C0473a.m3981a(iBinder);
    }

    protected String m3906e() {
        return "com.google.android.location.internal.GoogleLocationManagerService.START";
    }

    protected String m3907f() {
        return "com.google.android.gms.location.internal.IGoogleLocationManagerService";
    }

    protected Bundle m3908k() {
        Bundle bundle = new Bundle();
        bundle.putString("client_name", this.f2354e);
        return bundle;
    }
}
