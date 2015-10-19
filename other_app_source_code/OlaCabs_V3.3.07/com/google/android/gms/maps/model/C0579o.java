package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.C0447a;
import com.google.android.gms.common.internal.safeparcel.C0447a.C0446a;
import com.google.android.gms.common.internal.safeparcel.C0448b;
import com.sothree.slidinguppanel.p086a.R.R;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.protocol.HTTP;

/* renamed from: com.google.android.gms.maps.model.o */
public class C0579o implements Creator<PolygonOptions> {
    static void m4600a(PolygonOptions polygonOptions, Parcel parcel, int i) {
        int a = C0448b.m3814a(parcel);
        C0448b.m3819a(parcel, 1, polygonOptions.m4514a());
        C0448b.m3838c(parcel, 2, polygonOptions.m4516c(), false);
        C0448b.m3839d(parcel, 3, polygonOptions.m4515b(), false);
        C0448b.m3818a(parcel, 4, polygonOptions.m4517d());
        C0448b.m3819a(parcel, 5, polygonOptions.m4518e());
        C0448b.m3819a(parcel, 6, polygonOptions.m4519f());
        C0448b.m3818a(parcel, 7, polygonOptions.m4520g());
        C0448b.m3829a(parcel, 8, polygonOptions.m4521h());
        C0448b.m3829a(parcel, 9, polygonOptions.m4522i());
        C0448b.m3815a(parcel, a);
    }

    public PolygonOptions m4601a(Parcel parcel) {
        float f = 0.0f;
        boolean z = false;
        int b = C0447a.m3786b(parcel);
        List list = null;
        List arrayList = new ArrayList();
        boolean z2 = false;
        int i = 0;
        int i2 = 0;
        float f2 = 0.0f;
        int i3 = 0;
        while (parcel.dataPosition() < b) {
            int a = C0447a.m3780a(parcel);
            switch (C0447a.m3779a(a)) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    i3 = C0447a.m3793f(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    list = C0447a.m3789c(parcel, a, LatLng.CREATOR);
                    break;
                case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                    C0447a.m3785a(parcel, a, arrayList, getClass().getClassLoader());
                    break;
                case R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
                    f2 = C0447a.m3797j(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoDragView /*5*/:
                    i2 = C0447a.m3793f(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoOverlay /*6*/:
                    i = C0447a.m3793f(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoClipPanel /*7*/:
                    f = C0447a.m3797j(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoAnchorPoint /*8*/:
                    z2 = C0447a.m3790c(parcel, a);
                    break;
                case HTTP.HT /*9*/:
                    z = C0447a.m3790c(parcel, a);
                    break;
                default:
                    C0447a.m3787b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() == b) {
            return new PolygonOptions(i3, list, arrayList, f2, i2, i, f, z2, z);
        }
        throw new C0446a("Overread allowed size end=" + b, parcel);
    }

    public PolygonOptions[] m4602a(int i) {
        return new PolygonOptions[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m4601a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m4602a(i);
    }
}
