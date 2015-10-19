package com.google.android.m4b.maps.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.v4.internal.view.SupportMenu;
import com.google.android.m4b.maps.p037h.SafeParcelReader;
import com.google.android.m4b.maps.p037h.SafeParcelWriter;
import com.sothree.slidinguppanel.p086a.R.R;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.protocol.HTTP;

/* renamed from: com.google.android.m4b.maps.model.r */
public class PolygonOptionsCreator implements Creator<PolygonOptions> {
    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m11026a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m11027a(i);
    }

    public PolygonOptions m11026a(Parcel parcel) {
        float f = 0.0f;
        boolean z = false;
        int a = SafeParcelReader.m10468a(parcel);
        List list = null;
        List arrayList = new ArrayList();
        boolean z2 = false;
        int i = 0;
        int i2 = 0;
        float f2 = 0.0f;
        int i3 = 0;
        while (parcel.dataPosition() < a) {
            int readInt = parcel.readInt();
            switch (SupportMenu.USER_MASK & readInt) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    i3 = SafeParcelReader.m10478f(parcel, readInt);
                    break;
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    list = SafeParcelReader.m10474c(parcel, readInt, LatLng.CREATOR);
                    break;
                case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                    ClassLoader classLoader = getClass().getClassLoader();
                    readInt = SafeParcelReader.m10469a(parcel, readInt);
                    int dataPosition = parcel.dataPosition();
                    if (readInt == 0) {
                        break;
                    }
                    parcel.readList(arrayList, classLoader);
                    parcel.setDataPosition(readInt + dataPosition);
                    break;
                case R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
                    f2 = SafeParcelReader.m10481i(parcel, readInt);
                    break;
                case R.SlidingUpPanelLayout_umanoDragView /*5*/:
                    i2 = SafeParcelReader.m10478f(parcel, readInt);
                    break;
                case R.SlidingUpPanelLayout_umanoOverlay /*6*/:
                    i = SafeParcelReader.m10478f(parcel, readInt);
                    break;
                case R.SlidingUpPanelLayout_umanoClipPanel /*7*/:
                    f = SafeParcelReader.m10481i(parcel, readInt);
                    break;
                case R.SlidingUpPanelLayout_umanoAnchorPoint /*8*/:
                    z2 = SafeParcelReader.m10475c(parcel, readInt);
                    break;
                case HTTP.HT /*9*/:
                    z = SafeParcelReader.m10475c(parcel, readInt);
                    break;
                default:
                    SafeParcelReader.m10472b(parcel, readInt);
                    break;
            }
        }
        if (parcel.dataPosition() == a) {
            return new PolygonOptions(i3, list, arrayList, f2, i2, i, f, z2, z);
        }
        throw new SafeParcelReader.SafeParcelReader("Overread allowed size end=" + a, parcel);
    }

    public PolygonOptions[] m11027a(int i) {
        return new PolygonOptions[i];
    }

    static void m11025a(PolygonOptions polygonOptions, Parcel parcel) {
        int a = SafeParcelWriter.m10487a(parcel);
        SafeParcelWriter.m10492a(parcel, 1, polygonOptions.m10775a());
        SafeParcelWriter.m10508b(parcel, 2, polygonOptions.m10777c(), false);
        SafeParcelWriter.m10510c(parcel, 3, polygonOptions.m10776b(), false);
        SafeParcelWriter.m10491a(parcel, 4, polygonOptions.m10779e());
        SafeParcelWriter.m10492a(parcel, 5, polygonOptions.m10780f());
        SafeParcelWriter.m10492a(parcel, 6, polygonOptions.m10781g());
        SafeParcelWriter.m10491a(parcel, 7, polygonOptions.m10782h());
        SafeParcelWriter.m10501a(parcel, 8, polygonOptions.m10783i());
        SafeParcelWriter.m10501a(parcel, 9, polygonOptions.m10784j());
        SafeParcelWriter.m10488a(parcel, a);
    }
}
