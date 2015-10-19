package com.google.android.m4b.maps.p052m;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.v4.internal.view.SupportMenu;
import com.google.android.m4b.maps.p037h.SafeParcelReader;
import com.google.android.m4b.maps.p037h.SafeParcelWriter;
import com.sothree.slidinguppanel.p086a.R.R;

/* renamed from: com.google.android.m4b.maps.m.f */
public final class PlaceReportCreator implements Creator<PlaceReport> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        return PlaceReportCreator.m10685a(parcel);
    }

    public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
        return new PlaceReport[i];
    }

    public static PlaceReport m10685a(Parcel parcel) {
        String str = null;
        int a = SafeParcelReader.m10468a(parcel);
        int i = 0;
        String str2 = null;
        while (parcel.dataPosition() < a) {
            int readInt = parcel.readInt();
            switch (SupportMenu.USER_MASK & readInt) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    i = SafeParcelReader.m10478f(parcel, readInt);
                    break;
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    str2 = SafeParcelReader.m10483k(parcel, readInt);
                    break;
                case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                    str = SafeParcelReader.m10483k(parcel, readInt);
                    break;
                default:
                    SafeParcelReader.m10472b(parcel, readInt);
                    break;
            }
        }
        if (parcel.dataPosition() == a) {
            return new PlaceReport(i, str2, str);
        }
        throw new SafeParcelReader.SafeParcelReader("Overread allowed size end=" + a, parcel);
    }

    static void m10686a(PlaceReport placeReport, Parcel parcel) {
        int a = SafeParcelWriter.m10487a(parcel);
        SafeParcelWriter.m10492a(parcel, 1, placeReport.f7503a);
        SafeParcelWriter.m10498a(parcel, 2, placeReport.m10683a(), false);
        SafeParcelWriter.m10498a(parcel, 3, placeReport.m10684b(), false);
        SafeParcelWriter.m10488a(parcel, a);
    }
}
