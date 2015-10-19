package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.maps.internal.C0554b;
import com.google.android.gms.maps.model.internal.C0570d;
import com.google.android.gms.maps.model.internal.C0570d.C0572a;

public final class TileOverlayOptions implements SafeParcelable {
    public static final C0589y CREATOR;
    private final int f2822a;
    private C0570d f2823b;
    private C0557b f2824c;
    private boolean f2825d;
    private float f2826e;
    private boolean f2827f;

    /* renamed from: com.google.android.gms.maps.model.TileOverlayOptions.1 */
    class C05581 implements C0557b {
        final /* synthetic */ TileOverlayOptions f2820a;
        private final C0570d f2821c;

        C05581(TileOverlayOptions tileOverlayOptions) {
            this.f2820a = tileOverlayOptions;
            this.f2821c = this.f2820a.f2823b;
        }
    }

    static {
        CREATOR = new C0589y();
    }

    public TileOverlayOptions() {
        this.f2825d = true;
        this.f2827f = true;
        this.f2822a = 1;
    }

    TileOverlayOptions(int i, IBinder iBinder, boolean z, float f, boolean z2) {
        this.f2825d = true;
        this.f2827f = true;
        this.f2822a = i;
        this.f2823b = C0572a.m4576a(iBinder);
        this.f2824c = this.f2823b == null ? null : new C05581(this);
        this.f2825d = z;
        this.f2826e = f;
        this.f2827f = z2;
    }

    int m4539a() {
        return this.f2822a;
    }

    IBinder m4540b() {
        return this.f2823b.asBinder();
    }

    public float m4541c() {
        return this.f2826e;
    }

    public boolean m4542d() {
        return this.f2825d;
    }

    public int describeContents() {
        return 0;
    }

    public boolean m4543e() {
        return this.f2827f;
    }

    public void writeToParcel(Parcel parcel, int i) {
        if (C0554b.m4471a()) {
            C0590z.m4627a(this, parcel, i);
        } else {
            C0589y.m4624a(this, parcel, i);
        }
    }
}
