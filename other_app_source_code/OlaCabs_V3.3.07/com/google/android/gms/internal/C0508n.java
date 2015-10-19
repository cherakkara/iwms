package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.C0447a;
import com.google.android.gms.common.internal.safeparcel.C0447a.C0446a;
import com.google.android.gms.common.internal.safeparcel.C0448b;
import com.sothree.slidinguppanel.p086a.R.R;
import org.apache.http.protocol.HTTP;

/* renamed from: com.google.android.gms.internal.n */
public class C0508n implements Creator<zzlm> {
    static void m4140a(zzlm com_google_android_gms_internal_zzlm, Parcel parcel, int i) {
        int a = C0448b.m3814a(parcel);
        C0448b.m3819a(parcel, 1, com_google_android_gms_internal_zzlm.f2482a);
        C0448b.m3820a(parcel, 2, com_google_android_gms_internal_zzlm.m4243a());
        C0448b.m3826a(parcel, 3, com_google_android_gms_internal_zzlm.m4244b(), false);
        C0448b.m3826a(parcel, 4, com_google_android_gms_internal_zzlm.m4245c(), false);
        C0448b.m3826a(parcel, 5, com_google_android_gms_internal_zzlm.m4246d(), false);
        C0448b.m3826a(parcel, 6, com_google_android_gms_internal_zzlm.m4247e(), false);
        C0448b.m3826a(parcel, 7, com_google_android_gms_internal_zzlm.m4248f(), false);
        C0448b.m3826a(parcel, 8, com_google_android_gms_internal_zzlm.m4249g(), false);
        C0448b.m3820a(parcel, 9, com_google_android_gms_internal_zzlm.m4250h());
        C0448b.m3815a(parcel, a);
    }

    public zzlm m4141a(Parcel parcel) {
        long j = 0;
        String str = null;
        int b = C0447a.m3786b(parcel);
        int i = 0;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        String str5 = null;
        String str6 = null;
        long j2 = 0;
        while (parcel.dataPosition() < b) {
            int a = C0447a.m3780a(parcel);
            switch (C0447a.m3779a(a)) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    i = C0447a.m3793f(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    j2 = C0447a.m3795h(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                    str6 = C0447a.m3800m(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
                    str5 = C0447a.m3800m(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoDragView /*5*/:
                    str4 = C0447a.m3800m(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoOverlay /*6*/:
                    str3 = C0447a.m3800m(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoClipPanel /*7*/:
                    str2 = C0447a.m3800m(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoAnchorPoint /*8*/:
                    str = C0447a.m3800m(parcel, a);
                    break;
                case HTTP.HT /*9*/:
                    j = C0447a.m3795h(parcel, a);
                    break;
                default:
                    C0447a.m3787b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() == b) {
            return new zzlm(i, j2, str6, str5, str4, str3, str2, str, j);
        }
        throw new C0446a("Overread allowed size end=" + b, parcel);
    }

    public zzlm[] m4142a(int i) {
        return new zzlm[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m4141a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m4142a(i);
    }
}
