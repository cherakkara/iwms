package com.google.android.gms.common.api;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.C0226d.C0221b;
import com.google.android.gms.common.api.C0226d.C0222c;
import com.google.android.gms.common.api.C0226d.C0225e;
import com.google.android.gms.common.internal.C0411p;
import com.google.android.gms.common.internal.C0419h;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* renamed from: com.google.android.gms.common.api.b */
public final class C0218b<O extends C0210a> {
    private final C0216c<?, O> f2006a;
    private final C0217d<?> f2007b;
    private final ArrayList<Scope> f2008c;

    /* renamed from: com.google.android.gms.common.api.b.a */
    public interface C0210a {

        /* renamed from: com.google.android.gms.common.api.b.a.a */
        public interface C0211a extends C0210a {
        }

        /* renamed from: com.google.android.gms.common.api.b.a.c */
        public interface C0212c extends C0210a {
        }

        /* renamed from: com.google.android.gms.common.api.b.a.b */
        public static final class C0213b implements C0212c {
            private C0213b() {
            }
        }

        /* renamed from: com.google.android.gms.common.api.b.a.d */
        public interface C0214d extends C0211a, C0212c {
        }
    }

    /* renamed from: com.google.android.gms.common.api.b.b */
    public interface C0215b {
        void m3192a();

        void m3193a(C0225e c0225e);

        void m3194a(C0411p c0411p);

        void m3195a(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr);

        void m3196b();

        void m3197b(C0411p c0411p);

        boolean m3198c();

        boolean m3199d();
    }

    /* renamed from: com.google.android.gms.common.api.b.c */
    public interface C0216c<T extends C0215b, O> {
        int m3200a();

        T m3201a(Context context, Looper looper, C0419h c0419h, O o, C0221b c0221b, C0222c c0222c);
    }

    /* renamed from: com.google.android.gms.common.api.b.d */
    public static final class C0217d<C extends C0215b> {
    }

    public <C extends C0215b> C0218b(C0216c<C, O> c0216c, C0217d<C> c0217d, Scope... scopeArr) {
        this.f2006a = c0216c;
        this.f2007b = c0217d;
        this.f2008c = new ArrayList(Arrays.asList(scopeArr));
    }

    public C0216c<?, O> m3202a() {
        return this.f2006a;
    }

    public List<Scope> m3203b() {
        return this.f2008c;
    }

    public C0217d<?> m3204c() {
        return this.f2007b;
    }
}
