package com.google.android.m4b.maps.bj;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.android.m4b.maps.be.ar;
import com.google.android.m4b.maps.p046d.ProtoBuf;

/* renamed from: com.google.android.m4b.maps.bj.t */
public final class PanoramaLink implements Parcelable {
    public static final Creator<PanoramaLink> CREATOR;
    public final float f6621a;
    public final int f6622b;
    public final String f6623c;
    public final int f6624d;
    public String f6625e;

    /* renamed from: com.google.android.m4b.maps.bj.t.1 */
    static class PanoramaLink implements Creator<PanoramaLink> {
        PanoramaLink() {
        }

        public final /* synthetic */ Object createFromParcel(Parcel parcel) {
            return new PanoramaLink(parcel);
        }

        public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
            return new PanoramaLink[i];
        }
    }

    private PanoramaLink(float f, String str, int i, String str2) {
        this.f6621a = f;
        this.f6622b = ar.m8615p(f);
        this.f6623c = str;
        this.f6624d = i;
        this.f6625e = str2;
    }

    public PanoramaLink(Parcel parcel) {
        this.f6621a = parcel.readFloat();
        this.f6622b = parcel.readInt();
        this.f6623c = parcel.readString();
        this.f6624d = parcel.readInt();
        this.f6625e = parcel.readString();
    }

    public PanoramaLink(ProtoBuf protoBuf) {
        this(PanoramaConfig.m9987a(protoBuf.m10204d(53)), protoBuf.m10212h(54), protoBuf.m10204d(55), protoBuf.m10212h(56));
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        PanoramaLink panoramaLink = (PanoramaLink) obj;
        if (this.f6623c.equals(panoramaLink.f6623c) && this.f6625e.equals(panoramaLink.f6625e) && this.f6624d == panoramaLink.f6624d && Float.floatToRawIntBits(this.f6621a) == Float.floatToRawIntBits(panoramaLink.f6621a)) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return this.f6623c.hashCode();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeFloat(this.f6621a);
        parcel.writeInt(this.f6622b);
        parcel.writeString(this.f6623c);
        parcel.writeInt(this.f6624d);
        parcel.writeString(this.f6625e);
    }

    public final int describeContents() {
        return 0;
    }

    static {
        CREATOR = new PanoramaLink();
    }
}
