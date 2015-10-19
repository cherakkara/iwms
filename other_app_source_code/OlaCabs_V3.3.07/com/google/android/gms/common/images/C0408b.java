package com.google.android.gms.common.images;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.C0447a;
import com.google.android.gms.common.internal.safeparcel.C0447a.C0446a;
import com.google.android.gms.common.internal.safeparcel.C0448b;
import com.sothree.slidinguppanel.p086a.R.R;

/* renamed from: com.google.android.gms.common.images.b */
public class C0408b implements Creator<WebImage> {
    static void m3550a(WebImage webImage, Parcel parcel, int i) {
        int a = C0448b.m3814a(parcel);
        C0448b.m3819a(parcel, 1, webImage.m3540a());
        C0448b.m3824a(parcel, 2, webImage.m3541b(), i, false);
        C0448b.m3819a(parcel, 3, webImage.m3542c());
        C0448b.m3819a(parcel, 4, webImage.m3543d());
        C0448b.m3815a(parcel, a);
    }

    public WebImage m3551a(Parcel parcel) {
        int i = 0;
        int b = C0447a.m3786b(parcel);
        Uri uri = null;
        int i2 = 0;
        int i3 = 0;
        while (parcel.dataPosition() < b) {
            Uri uri2;
            int f;
            int a = C0447a.m3780a(parcel);
            int i4;
            switch (C0447a.m3779a(a)) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    i4 = i;
                    i = i2;
                    uri2 = uri;
                    f = C0447a.m3793f(parcel, a);
                    a = i4;
                    break;
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    f = i3;
                    i4 = i2;
                    uri2 = (Uri) C0447a.m3782a(parcel, a, Uri.CREATOR);
                    a = i;
                    i = i4;
                    break;
                case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                    uri2 = uri;
                    f = i3;
                    i4 = i;
                    i = C0447a.m3793f(parcel, a);
                    a = i4;
                    break;
                case R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
                    a = C0447a.m3793f(parcel, a);
                    i = i2;
                    uri2 = uri;
                    f = i3;
                    break;
                default:
                    C0447a.m3787b(parcel, a);
                    a = i;
                    i = i2;
                    uri2 = uri;
                    f = i3;
                    break;
            }
            i3 = f;
            uri = uri2;
            i2 = i;
            i = a;
        }
        if (parcel.dataPosition() == b) {
            return new WebImage(i3, uri, i2, i);
        }
        throw new C0446a("Overread allowed size end=" + b, parcel);
    }

    public WebImage[] m3552a(int i) {
        return new WebImage[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m3551a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m3552a(i);
    }
}
