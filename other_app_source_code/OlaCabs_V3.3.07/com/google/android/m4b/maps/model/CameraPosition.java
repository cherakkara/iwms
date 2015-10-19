package com.google.android.m4b.maps.model;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.util.AttributeSet;
import com.google.android.m4b.maps.R.R;
import com.google.android.m4b.maps.p037h.C0591c;
import com.google.android.m4b.maps.p042r.SafeParcelableVersionManager;
import com.google.android.m4b.maps.p047g.Objects;
import com.google.android.m4b.maps.p047g.Preconditions;
import java.util.Arrays;

public final class CameraPosition implements C0591c {
    public static final CameraPositionCreator CREATOR;
    public final LatLng f7529a;
    public final float f7530b;
    public final float f7531c;
    public final float f7532d;
    private final int f7533e;

    /* renamed from: com.google.android.m4b.maps.model.CameraPosition.a */
    public static final class C0594a {
        private LatLng f7525a;
        private float f7526b;
        private float f7527c;
        private float f7528d;

        public C0594a(CameraPosition cameraPosition) {
            this.f7525a = cameraPosition.f7529a;
            this.f7526b = cameraPosition.f7530b;
            this.f7527c = cameraPosition.f7531c;
            this.f7528d = cameraPosition.f7532d;
        }

        public final C0594a m10704a(LatLng latLng) {
            this.f7525a = latLng;
            return this;
        }

        public final C0594a m10703a(float f) {
            this.f7526b = f;
            return this;
        }

        public final C0594a m10706b(float f) {
            this.f7527c = f;
            return this;
        }

        public final C0594a m10707c(float f) {
            this.f7528d = f;
            return this;
        }

        public final CameraPosition m10705a() {
            return new CameraPosition(this.f7525a, this.f7526b, this.f7527c, this.f7528d);
        }
    }

    static {
        CREATOR = new CameraPositionCreator();
    }

    CameraPosition(int i, LatLng latLng, float f, float f2, float f3) {
        Preconditions.m10460a((Object) latLng, (Object) "null camera target");
        boolean z = 0.0f <= f2 && f2 <= 90.0f;
        Preconditions.m10466b(z, "Tilt needs to be between 0 and 90 inclusive");
        this.f7533e = i;
        this.f7529a = latLng;
        this.f7530b = f;
        this.f7531c = f2 + 0.0f;
        if (((double) f3) <= 0.0d) {
            f3 = (f3 % 360.0f) + 360.0f;
        }
        this.f7532d = f3 % 360.0f;
    }

    public CameraPosition(LatLng latLng, float f, float f2, float f3) {
        this(1, latLng, f, f2, f3);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        if (SafeParcelableVersionManager.m11188a()) {
            CameraPositionCreatorCheddar.m10830a(this, parcel, i);
        } else {
            CameraPositionCreator.m10827a(this, parcel, i);
        }
    }

    public final int describeContents() {
        return 0;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.f7529a, Float.valueOf(this.f7530b), Float.valueOf(this.f7531c), Float.valueOf(this.f7532d)});
    }

    final int m10712a() {
        return this.f7533e;
    }

    public static final CameraPosition m10710a(LatLng latLng, float f) {
        return new CameraPosition(latLng, f, 0.0f, 0.0f);
    }

    public static C0594a m10711b() {
        return new C0594a();
    }

    public static C0594a m10708a(CameraPosition cameraPosition) {
        return new C0594a(cameraPosition);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CameraPosition)) {
            return false;
        }
        CameraPosition cameraPosition = (CameraPosition) obj;
        if (this.f7529a.equals(cameraPosition.f7529a) && Float.floatToIntBits(this.f7530b) == Float.floatToIntBits(cameraPosition.f7530b) && Float.floatToIntBits(this.f7531c) == Float.floatToIntBits(cameraPosition.f7531c) && Float.floatToIntBits(this.f7532d) == Float.floatToIntBits(cameraPosition.f7532d)) {
            return true;
        }
        return false;
    }

    public final String toString() {
        return Objects.m10456a(this).m10455a("target", this.f7529a).m10455a("zoom", Float.valueOf(this.f7530b)).m10455a("tilt", Float.valueOf(this.f7531c)).m10455a("bearing", Float.valueOf(this.f7532d)).toString();
    }

    public static CameraPosition m10709a(Context context, AttributeSet attributeSet) {
        if (attributeSet == null) {
            return null;
        }
        float f;
        float f2;
        TypedArray obtainAttributes = context.getResources().obtainAttributes(attributeSet, R.MapM4bAttrs);
        if (obtainAttributes.hasValue(2)) {
            f = obtainAttributes.getFloat(2, 0.0f);
        } else {
            f = 0.0f;
        }
        if (obtainAttributes.hasValue(3)) {
            f2 = obtainAttributes.getFloat(3, 0.0f);
        } else {
            f2 = 0.0f;
        }
        LatLng latLng = new LatLng((double) f, (double) f2);
        C0594a b = m10711b();
        b.m10704a(latLng);
        if (obtainAttributes.hasValue(5)) {
            b.m10703a(obtainAttributes.getFloat(5, 0.0f));
        }
        if (obtainAttributes.hasValue(1)) {
            b.m10707c(obtainAttributes.getFloat(1, 0.0f));
        }
        if (obtainAttributes.hasValue(4)) {
            b.m10706b(obtainAttributes.getFloat(4, 0.0f));
        }
        return b.m10705a();
    }
}
