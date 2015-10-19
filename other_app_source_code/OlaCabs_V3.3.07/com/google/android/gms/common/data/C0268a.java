package com.google.android.gms.common.data;

import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.C0447a;
import com.google.android.gms.common.internal.safeparcel.C0447a.C0446a;
import com.google.android.gms.common.internal.safeparcel.C0448b;
import com.sothree.slidinguppanel.p086a.R.R;

/* renamed from: com.google.android.gms.common.data.a */
public class C0268a implements Creator<zza> {
    static void m3371a(zza com_google_android_gms_common_data_zza, Parcel parcel, int i) {
        int a = C0448b.m3814a(parcel);
        C0448b.m3819a(parcel, 1, com_google_android_gms_common_data_zza.f2120a);
        C0448b.m3824a(parcel, 2, com_google_android_gms_common_data_zza.f2121b, i, false);
        C0448b.m3819a(parcel, 3, com_google_android_gms_common_data_zza.f2122c);
        C0448b.m3815a(parcel, a);
    }

    public zza m3372a(Parcel parcel) {
        int i = 0;
        int b = C0447a.m3786b(parcel);
        ParcelFileDescriptor parcelFileDescriptor = null;
        int i2 = 0;
        while (parcel.dataPosition() < b) {
            ParcelFileDescriptor parcelFileDescriptor2;
            int f;
            int a = C0447a.m3780a(parcel);
            switch (C0447a.m3779a(a)) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    int i3 = i;
                    parcelFileDescriptor2 = parcelFileDescriptor;
                    f = C0447a.m3793f(parcel, a);
                    a = i3;
                    break;
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    f = i2;
                    ParcelFileDescriptor parcelFileDescriptor3 = (ParcelFileDescriptor) C0447a.m3782a(parcel, a, ParcelFileDescriptor.CREATOR);
                    a = i;
                    parcelFileDescriptor2 = parcelFileDescriptor3;
                    break;
                case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                    a = C0447a.m3793f(parcel, a);
                    parcelFileDescriptor2 = parcelFileDescriptor;
                    f = i2;
                    break;
                default:
                    C0447a.m3787b(parcel, a);
                    a = i;
                    parcelFileDescriptor2 = parcelFileDescriptor;
                    f = i2;
                    break;
            }
            i2 = f;
            parcelFileDescriptor = parcelFileDescriptor2;
            i = a;
        }
        if (parcel.dataPosition() == b) {
            return new zza(i2, parcelFileDescriptor, i);
        }
        throw new C0446a("Overread allowed size end=" + b, parcel);
    }

    public zza[] m3373a(int i) {
        return new zza[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m3372a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m3373a(i);
    }
}
