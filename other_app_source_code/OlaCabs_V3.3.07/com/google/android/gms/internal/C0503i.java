package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.C0447a;
import com.google.android.gms.common.internal.safeparcel.C0447a.C0446a;
import com.google.android.gms.common.internal.safeparcel.C0448b;
import com.google.android.gms.internal.zzld.zza;
import com.sothree.slidinguppanel.p086a.R.R;
import org.apache.http.protocol.HTTP;

/* renamed from: com.google.android.gms.internal.i */
public class C0503i implements Creator<zza> {
    static void m4125a(zza com_google_android_gms_internal_zzld_zza, Parcel parcel, int i) {
        int a = C0448b.m3814a(parcel);
        C0448b.m3819a(parcel, 1, com_google_android_gms_internal_zzld_zza.m4194a());
        C0448b.m3819a(parcel, 2, com_google_android_gms_internal_zzld_zza.m4197b());
        C0448b.m3829a(parcel, 3, com_google_android_gms_internal_zzld_zza.m4198c());
        C0448b.m3819a(parcel, 4, com_google_android_gms_internal_zzld_zza.m4199d());
        C0448b.m3829a(parcel, 5, com_google_android_gms_internal_zzld_zza.m4200e());
        C0448b.m3826a(parcel, 6, com_google_android_gms_internal_zzld_zza.m4201f(), false);
        C0448b.m3819a(parcel, 7, com_google_android_gms_internal_zzld_zza.m4202g());
        C0448b.m3826a(parcel, 8, com_google_android_gms_internal_zzld_zza.m4204i(), false);
        C0448b.m3824a(parcel, 9, com_google_android_gms_internal_zzld_zza.m4206k(), i, false);
        C0448b.m3815a(parcel, a);
    }

    public zza m4126a(Parcel parcel) {
        zzky com_google_android_gms_internal_zzky = null;
        int i = 0;
        int b = C0447a.m3786b(parcel);
        String str = null;
        String str2 = null;
        boolean z = false;
        int i2 = 0;
        boolean z2 = false;
        int i3 = 0;
        int i4 = 0;
        while (parcel.dataPosition() < b) {
            int a = C0447a.m3780a(parcel);
            switch (C0447a.m3779a(a)) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    i4 = C0447a.m3793f(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    i3 = C0447a.m3793f(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                    z2 = C0447a.m3790c(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
                    i2 = C0447a.m3793f(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoDragView /*5*/:
                    z = C0447a.m3790c(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoOverlay /*6*/:
                    str2 = C0447a.m3800m(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoClipPanel /*7*/:
                    i = C0447a.m3793f(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoAnchorPoint /*8*/:
                    str = C0447a.m3800m(parcel, a);
                    break;
                case HTTP.HT /*9*/:
                    com_google_android_gms_internal_zzky = (zzky) C0447a.m3782a(parcel, a, zzky.CREATOR);
                    break;
                default:
                    C0447a.m3787b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() == b) {
            return new zza(i4, i3, z2, i2, z, str2, i, str, com_google_android_gms_internal_zzky);
        }
        throw new C0446a("Overread allowed size end=" + b, parcel);
    }

    public zza[] m4127a(int i) {
        return new zza[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m4126a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m4127a(i);
    }
}
