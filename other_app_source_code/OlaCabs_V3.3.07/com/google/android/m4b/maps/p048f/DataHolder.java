package com.google.android.m4b.maps.p048f;

import android.database.CursorWindow;
import android.os.Bundle;
import android.os.Parcel;
import com.google.android.m4b.maps.p037h.C0591c;
import com.google.android.m4b.maps.p047g.Preconditions;
import java.util.ArrayList;
import java.util.HashMap;

/* renamed from: com.google.android.m4b.maps.f.a */
public final class DataHolder implements C0591c {
    public static final DataHolderCreator CREATOR;
    private final int f7365a;
    private final String[] f7366b;
    private Bundle f7367c;
    private final CursorWindow[] f7368d;
    private final int f7369e;
    private final Bundle f7370f;
    private int[] f7371g;
    private boolean f7372h;
    private boolean f7373i;

    /* renamed from: com.google.android.m4b.maps.f.a.a */
    public static class DataHolder {
        private DataHolder(String[] strArr, String str) {
            Preconditions.m10459a((Object) strArr);
            ArrayList arrayList = new ArrayList();
            HashMap hashMap = new HashMap();
        }
    }

    /* renamed from: com.google.android.m4b.maps.f.a.1 */
    static class DataHolder extends DataHolder {
        DataHolder(String[] strArr, String str) {
            super(null, (byte) 0);
        }
    }

    static {
        CREATOR = new DataHolderCreator();
        DataHolder dataHolder = new DataHolder(new String[0], null);
    }

    DataHolder(int i, String[] strArr, CursorWindow[] cursorWindowArr, int i2, Bundle bundle) {
        this.f7372h = false;
        this.f7373i = true;
        this.f7365a = i;
        this.f7366b = strArr;
        this.f7368d = cursorWindowArr;
        this.f7369e = i2;
        this.f7370f = bundle;
    }

    public final void m10307a() {
        int i;
        int i2 = 0;
        this.f7367c = new Bundle();
        for (i = 0; i < this.f7366b.length; i++) {
            this.f7367c.putInt(this.f7366b[i], i);
        }
        this.f7371g = new int[this.f7368d.length];
        i = 0;
        while (i2 < this.f7368d.length) {
            this.f7371g[i2] = i;
            i += this.f7368d[i2].getNumRows() - (i - this.f7368d[i2].getStartPosition());
            i2++;
        }
    }

    public final int describeContents() {
        return 0;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        DataHolderCreator.m10314a(this, parcel, i);
    }

    final int m10308b() {
        return this.f7365a;
    }

    final String[] m10309c() {
        return this.f7366b;
    }

    final CursorWindow[] m10310d() {
        return this.f7368d;
    }

    public final int m10311e() {
        return this.f7369e;
    }

    public final Bundle m10312f() {
        return this.f7370f;
    }

    private boolean m10305g() {
        boolean z;
        synchronized (this) {
            z = this.f7372h;
        }
        return z;
    }

    private void m10306h() {
        synchronized (this) {
            if (!this.f7372h) {
                this.f7372h = true;
                for (CursorWindow close : this.f7368d) {
                    close.close();
                }
            }
        }
    }

    protected final void finalize() {
        try {
            if (this.f7373i && this.f7368d.length > 0 && !m10305g()) {
                new StringBuilder("Internal data leak within a DataBuffer object detected!  Be sure to explicitly call close() on all DataBuffer extending objects when you are done with them. (").append("internal object: " + toString()).append(")");
                m10306h();
            }
            super.finalize();
        } catch (Throwable th) {
            super.finalize();
        }
    }
}
