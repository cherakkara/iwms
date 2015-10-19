package com.google.android.gms.internal;

import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.v4.util.TimeUtils;
import com.google.android.gms.common.internal.safeparcel.C0447a;
import com.google.android.gms.common.internal.safeparcel.C0447a.C0446a;
import com.google.android.gms.common.internal.safeparcel.C0448b;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.leanplum.utils.SizeUtil;
import com.olacabs.customer.utils.Constants;
import com.sothree.slidinguppanel.p086a.R.R;
import java.util.List;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.LangUtils;

public class ar implements Creator<zzpy> {
    static void m4024a(zzpy com_google_android_gms_internal_zzpy, Parcel parcel, int i) {
        int a = C0448b.m3814a(parcel);
        C0448b.m3826a(parcel, 1, com_google_android_gms_internal_zzpy.m4278a(), false);
        C0448b.m3821a(parcel, 2, com_google_android_gms_internal_zzpy.m4294q(), false);
        C0448b.m3824a(parcel, 3, com_google_android_gms_internal_zzpy.m4296s(), i, false);
        C0448b.m3824a(parcel, 4, com_google_android_gms_internal_zzpy.m4283f(), i, false);
        C0448b.m3818a(parcel, 5, com_google_android_gms_internal_zzpy.m4284g());
        C0448b.m3824a(parcel, 6, com_google_android_gms_internal_zzpy.m4285h(), i, false);
        C0448b.m3826a(parcel, 7, com_google_android_gms_internal_zzpy.m4295r(), false);
        C0448b.m3824a(parcel, 8, com_google_android_gms_internal_zzpy.m4286i(), i, false);
        C0448b.m3829a(parcel, 9, com_google_android_gms_internal_zzpy.m4290m());
        C0448b.m3818a(parcel, 10, com_google_android_gms_internal_zzpy.m4291n());
        C0448b.m3819a(parcel, 11, com_google_android_gms_internal_zzpy.m4292o());
        C0448b.m3820a(parcel, 12, com_google_android_gms_internal_zzpy.m4293p());
        C0448b.m3827a(parcel, 13, com_google_android_gms_internal_zzpy.m4280c(), false);
        C0448b.m3826a(parcel, 14, com_google_android_gms_internal_zzpy.m4282e(), false);
        C0448b.m3826a(parcel, 15, com_google_android_gms_internal_zzpy.m4287j(), false);
        C0448b.m3836b(parcel, 17, com_google_android_gms_internal_zzpy.m4289l(), false);
        C0448b.m3826a(parcel, 16, com_google_android_gms_internal_zzpy.m4288k(), false);
        C0448b.m3819a(parcel, (int) Constants.MILLIS_IN_A_SECOND, com_google_android_gms_internal_zzpy.f2528a);
        C0448b.m3826a(parcel, 19, com_google_android_gms_internal_zzpy.m4281d(), false);
        C0448b.m3829a(parcel, 18, com_google_android_gms_internal_zzpy.f2529b);
        C0448b.m3827a(parcel, 20, com_google_android_gms_internal_zzpy.m4279b(), false);
        C0448b.m3815a(parcel, a);
    }

    public zzpy m4025a(Parcel parcel) {
        int b = C0447a.m3786b(parcel);
        int i = 0;
        String str = null;
        List list = null;
        List list2 = null;
        Bundle bundle = null;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        String str5 = null;
        List list3 = null;
        LatLng latLng = null;
        float f = 0.0f;
        LatLngBounds latLngBounds = null;
        String str6 = null;
        Uri uri = null;
        boolean z = false;
        float f2 = 0.0f;
        int i2 = 0;
        long j = 0;
        boolean z2 = false;
        zzqd com_google_android_gms_internal_zzqd = null;
        while (parcel.dataPosition() < b) {
            int a = C0447a.m3780a(parcel);
            switch (C0447a.m3779a(a)) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    str = C0447a.m3800m(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    bundle = C0447a.m3802o(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                    com_google_android_gms_internal_zzqd = (zzqd) C0447a.m3782a(parcel, a, zzqd.CREATOR);
                    break;
                case R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
                    latLng = (LatLng) C0447a.m3782a(parcel, a, LatLng.CREATOR);
                    break;
                case R.SlidingUpPanelLayout_umanoDragView /*5*/:
                    f = C0447a.m3797j(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoOverlay /*6*/:
                    latLngBounds = (LatLngBounds) C0447a.m3782a(parcel, a, LatLngBounds.CREATOR);
                    break;
                case R.SlidingUpPanelLayout_umanoClipPanel /*7*/:
                    str6 = C0447a.m3800m(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoAnchorPoint /*8*/:
                    uri = (Uri) C0447a.m3782a(parcel, a, Uri.CREATOR);
                    break;
                case HTTP.HT /*9*/:
                    z = C0447a.m3790c(parcel, a);
                    break;
                case HTTP.LF /*10*/:
                    f2 = C0447a.m3797j(parcel, a);
                    break;
                case com.olacabs.customer.R.R.MapM4bAttrs_m4b_uiZoomControls /*11*/:
                    i2 = C0447a.m3793f(parcel, a);
                    break;
                case com.olacabs.customer.R.R.MapM4bAttrs_m4b_uiZoomGestures /*12*/:
                    j = C0447a.m3795h(parcel, a);
                    break;
                case HTTP.CR /*13*/:
                    list2 = C0447a.m3812y(parcel, a);
                    break;
                case com.olacabs.customer.R.R.MapM4bAttrs_m4b_zOrderOnTop /*14*/:
                    str3 = C0447a.m3800m(parcel, a);
                    break;
                case com.olacabs.customer.R.R.MapM4bAttrs_m4b_uiMapToolbar /*15*/:
                    str4 = C0447a.m3800m(parcel, a);
                    break;
                case Constants.DEFAULT_MAP_ZOOM_LEVEL /*16*/:
                    str5 = C0447a.m3800m(parcel, a);
                    break;
                case LangUtils.HASH_SEED /*17*/:
                    list3 = C0447a.m3813z(parcel, a);
                    break;
                case SizeUtil.textSize0_1 /*18*/:
                    z2 = C0447a.m3790c(parcel, a);
                    break;
                case TimeUtils.HUNDRED_DAY_FIELD_LEN /*19*/:
                    str2 = C0447a.m3800m(parcel, a);
                    break;
                case SizeUtil.textSize0 /*20*/:
                    list = C0447a.m3812y(parcel, a);
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
            return new zzpy(i, str, list, list2, bundle, str2, str3, str4, str5, list3, latLng, f, latLngBounds, str6, uri, z, f2, i2, j, z2, com_google_android_gms_internal_zzqd);
        }
        throw new C0446a("Overread allowed size end=" + b, parcel);
    }

    public zzpy[] m4026a(int i) {
        return new zzpy[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m4025a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m4026a(i);
    }
}
