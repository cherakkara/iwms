package com.google.android.m4b.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.m4b.maps.model.internal.ITileProviderDelegate;
import com.google.android.m4b.maps.p037h.C0591c;
import com.google.android.m4b.maps.p042r.SafeParcelableVersionManager;

public final class TileOverlayOptions implements C0591c {
    public static final ac CREATOR;
    private final int f7629a;
    private ITileProviderDelegate f7630b;
    private ae f7631c;
    private boolean f7632d;
    private float f7633e;
    private boolean f7634f;

    /* renamed from: com.google.android.m4b.maps.model.TileOverlayOptions.1 */
    class C05971 implements ae {
        private final ITileProviderDelegate f7627b;
        private /* synthetic */ TileOverlayOptions f7628c;

        C05971(TileOverlayOptions tileOverlayOptions) {
            this.f7628c = tileOverlayOptions;
            this.f7627b = this.f7628c.f7630b;
        }

        public final Tile m10801a(int i, int i2, int i3) {
            try {
                return this.f7627b.m11004a(i, i2, i3);
            } catch (RemoteException e) {
                return null;
            }
        }
    }

    static {
        CREATOR = new ac();
    }

    public TileOverlayOptions() {
        this.f7632d = true;
        this.f7634f = true;
        this.f7629a = 1;
    }

    TileOverlayOptions(int i, IBinder iBinder, boolean z, float f, boolean z2) {
        ae aeVar;
        this.f7632d = true;
        this.f7634f = true;
        this.f7629a = i;
        this.f7630b = ITileProviderDelegate.ITileProviderDelegate.m11006a(iBinder);
        if (this.f7630b == null) {
            aeVar = null;
        } else {
            aeVar = new C05971(this);
        }
        this.f7631c = aeVar;
        this.f7632d = z;
        this.f7633e = f;
        this.f7634f = z2;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        if (SafeParcelableVersionManager.m11188a()) {
            ad.m10818a(this, parcel);
        } else {
            ac.m10815a(this, parcel);
        }
    }

    public final int describeContents() {
        return 0;
    }

    final int m10803a() {
        return this.f7629a;
    }

    final IBinder m10804b() {
        return this.f7630b.asBinder();
    }

    public final ae m10805c() {
        return this.f7631c;
    }

    public final float m10806d() {
        return this.f7633e;
    }

    public final boolean m10807e() {
        return this.f7632d;
    }

    public final boolean m10808f() {
        return this.f7634f;
    }
}
