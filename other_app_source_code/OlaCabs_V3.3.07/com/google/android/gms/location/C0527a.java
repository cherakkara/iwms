package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.C0447a;
import com.google.android.gms.common.internal.safeparcel.C0447a.C0446a;
import com.google.android.gms.common.internal.safeparcel.C0448b;
import com.olacabs.customer.utils.Constants;
import com.sothree.slidinguppanel.p086a.R.R;
import java.util.List;

/* renamed from: com.google.android.gms.location.a */
public class C0527a implements Creator<ActivityRecognitionResult> {
    static void m4348a(ActivityRecognitionResult activityRecognitionResult, Parcel parcel, int i) {
        int a = C0448b.m3814a(parcel);
        C0448b.m3838c(parcel, 1, activityRecognitionResult.f2604a, false);
        C0448b.m3819a(parcel, (int) Constants.MILLIS_IN_A_SECOND, activityRecognitionResult.m4309a());
        C0448b.m3820a(parcel, 2, activityRecognitionResult.f2605b);
        C0448b.m3820a(parcel, 3, activityRecognitionResult.f2606c);
        C0448b.m3815a(parcel, a);
    }

    public ActivityRecognitionResult m4349a(Parcel parcel) {
        long j = 0;
        int b = C0447a.m3786b(parcel);
        int i = 0;
        List list = null;
        long j2 = 0;
        while (parcel.dataPosition() < b) {
            int a = C0447a.m3780a(parcel);
            switch (C0447a.m3779a(a)) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    list = C0447a.m3789c(parcel, a, DetectedActivity.CREATOR);
                    break;
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    j2 = C0447a.m3795h(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                    j = C0447a.m3795h(parcel, a);
                    break;
                case Constants.MILLIS_IN_A_SECOND /*1000*/:
                    i = C0447a.m3793f(parcel, a);
                    break;
                default:
                    C0447a.m3787b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() == b) {
            return new ActivityRecognitionResult(i, list, j2, j);
        }
        throw new C0446a("Overread allowed size end=" + b, parcel);
    }

    public ActivityRecognitionResult[] m4350a(int i) {
        return new ActivityRecognitionResult[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m4349a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m4350a(i);
    }
}
