package com.google.android.gms.common.data;

import android.database.CursorWindow;
import android.os.Bundle;
import android.os.Parcel;
import android.util.Log;
import com.google.android.gms.common.internal.C0453u;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.ArrayList;
import java.util.HashMap;

public final class DataHolder implements SafeParcelable {
    public static final C0269b CREATOR;
    private static final C0266a f2108l;
    Bundle f2109a;
    int[] f2110b;
    int f2111c;
    boolean f2112d;
    private final int f2113e;
    private final String[] f2114f;
    private final CursorWindow[] f2115g;
    private final int f2116h;
    private final Bundle f2117i;
    private Object f2118j;
    private boolean f2119k;

    /* renamed from: com.google.android.gms.common.data.DataHolder.a */
    public static class C0266a {
        private final String[] f2102a;
        private final ArrayList<HashMap<String, Object>> f2103b;
        private final String f2104c;
        private final HashMap<Object, Integer> f2105d;
        private boolean f2106e;
        private String f2107f;

        private C0266a(String[] strArr, String str) {
            this.f2102a = (String[]) C0453u.m3846a((Object) strArr);
            this.f2103b = new ArrayList();
            this.f2104c = str;
            this.f2105d = new HashMap();
            this.f2106e = false;
            this.f2107f = null;
        }
    }

    /* renamed from: com.google.android.gms.common.data.DataHolder.1 */
    static class C02671 extends C0266a {
        C02671(String[] strArr, String str) {
            super(str, null);
        }
    }

    static {
        CREATOR = new C0269b();
        f2108l = new C02671(new String[0], null);
    }

    DataHolder(int i, String[] strArr, CursorWindow[] cursorWindowArr, int i2, Bundle bundle) {
        this.f2112d = false;
        this.f2119k = true;
        this.f2113e = i;
        this.f2114f = strArr;
        this.f2115g = cursorWindowArr;
        this.f2116h = i2;
        this.f2117i = bundle;
    }

    public void m3363a() {
        int i;
        int i2 = 0;
        this.f2109a = new Bundle();
        for (i = 0; i < this.f2114f.length; i++) {
            this.f2109a.putInt(this.f2114f[i], i);
        }
        this.f2110b = new int[this.f2115g.length];
        i = 0;
        while (i2 < this.f2115g.length) {
            this.f2110b[i2] = i;
            i += this.f2115g[i2].getNumRows() - (i - this.f2115g[i2].getStartPosition());
            i2++;
        }
        this.f2111c = i;
    }

    int m3364b() {
        return this.f2113e;
    }

    String[] m3365c() {
        return this.f2114f;
    }

    CursorWindow[] m3366d() {
        return this.f2115g;
    }

    public int describeContents() {
        return 0;
    }

    public int m3367e() {
        return this.f2116h;
    }

    public Bundle m3368f() {
        return this.f2117i;
    }

    protected void finalize() throws Throwable {
        try {
            if (this.f2119k && this.f2115g.length > 0 && !m3369g()) {
                Log.e("DataBuffer", "Internal data leak within a DataBuffer object detected!  Be sure to explicitly call release() on all DataBuffer extending objects when you are done with them. (" + (this.f2118j == null ? "internal object: " + toString() : this.f2118j.toString()) + ")");
                m3370h();
            }
            super.finalize();
        } catch (Throwable th) {
            super.finalize();
        }
    }

    public boolean m3369g() {
        boolean z;
        synchronized (this) {
            z = this.f2112d;
        }
        return z;
    }

    public void m3370h() {
        synchronized (this) {
            if (!this.f2112d) {
                this.f2112d = true;
                for (CursorWindow close : this.f2115g) {
                    close.close();
                }
            }
        }
    }

    public void writeToParcel(Parcel parcel, int i) {
        C0269b.m3374a(this, parcel, i);
    }
}
