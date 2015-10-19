package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.Parcel;
import com.google.android.gms.common.internal.C0453u;
import com.google.android.gms.common.internal.safeparcel.C0447a;
import com.google.android.gms.common.internal.safeparcel.C0447a.C0446a;
import com.google.android.gms.common.internal.safeparcel.C0448b;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.zzld.zza;
import com.sothree.slidinguppanel.p086a.R.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.apache.http.protocol.HTTP;

public class zzlk extends zzld implements SafeParcelable {
    public static final C0507m CREATOR;
    private final int f2475a;
    private final Parcel f2476b;
    private final int f2477c;
    private final zzlh f2478d;
    private final String f2479e;
    private int f2480f;
    private int f2481g;

    static {
        CREATOR = new C0507m();
    }

    zzlk(int i, Parcel parcel, zzlh com_google_android_gms_internal_zzlh) {
        this.f2475a = i;
        this.f2476b = (Parcel) C0453u.m3846a((Object) parcel);
        this.f2477c = 2;
        this.f2478d = com_google_android_gms_internal_zzlh;
        if (this.f2478d == null) {
            this.f2479e = null;
        } else {
            this.f2479e = this.f2478d.m4227d();
        }
        this.f2480f = 2;
    }

    public static HashMap<String, String> m4228a(Bundle bundle) {
        HashMap<String, String> hashMap = new HashMap();
        for (String str : bundle.keySet()) {
            hashMap.put(str, bundle.getString(str));
        }
        return hashMap;
    }

    private static HashMap<Integer, Entry<String, zza<?, ?>>> m4229a(Map<String, zza<?, ?>> map) {
        HashMap<Integer, Entry<String, zza<?, ?>>> hashMap = new HashMap();
        for (Entry entry : map.entrySet()) {
            hashMap.put(Integer.valueOf(((zza) entry.getValue()).m4202g()), entry);
        }
        return hashMap;
    }

    private void m4230a(StringBuilder stringBuilder, int i, Object obj) {
        switch (i) {
            case R.SlidingUpPanelLayout_umanoPanelHeight /*0*/:
            case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
            case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
            case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
            case R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
            case R.SlidingUpPanelLayout_umanoDragView /*5*/:
            case R.SlidingUpPanelLayout_umanoOverlay /*6*/:
                stringBuilder.append(obj);
            case R.SlidingUpPanelLayout_umanoClipPanel /*7*/:
                stringBuilder.append("\"").append(C0517v.m4169a(obj.toString())).append("\"");
            case R.SlidingUpPanelLayout_umanoAnchorPoint /*8*/:
                stringBuilder.append("\"").append(C0515t.m4163a((byte[]) obj)).append("\"");
            case HTTP.HT /*9*/:
                stringBuilder.append("\"").append(C0515t.m4164b((byte[]) obj));
                stringBuilder.append("\"");
            case HTTP.LF /*10*/:
                C0518w.m4170a(stringBuilder, (HashMap) obj);
            case com.olacabs.customer.R.R.MapM4bAttrs_m4b_uiZoomControls /*11*/:
                throw new IllegalArgumentException("Method does not accept concrete type.");
            default:
                throw new IllegalArgumentException("Unknown type = " + i);
        }
    }

    private void m4231a(StringBuilder stringBuilder, zza<?, ?> com_google_android_gms_internal_zzld_zza___, Parcel parcel, int i) {
        switch (com_google_android_gms_internal_zzld_zza___.m4199d()) {
            case R.SlidingUpPanelLayout_umanoPanelHeight /*0*/:
                m4232a(stringBuilder, (zza) com_google_android_gms_internal_zzld_zza___, m4210a(com_google_android_gms_internal_zzld_zza___, Integer.valueOf(C0447a.m3793f(parcel, i))));
            case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                m4232a(stringBuilder, (zza) com_google_android_gms_internal_zzld_zza___, m4210a(com_google_android_gms_internal_zzld_zza___, C0447a.m3796i(parcel, i)));
            case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                m4232a(stringBuilder, (zza) com_google_android_gms_internal_zzld_zza___, m4210a(com_google_android_gms_internal_zzld_zza___, Long.valueOf(C0447a.m3795h(parcel, i))));
            case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                m4232a(stringBuilder, (zza) com_google_android_gms_internal_zzld_zza___, m4210a(com_google_android_gms_internal_zzld_zza___, Float.valueOf(C0447a.m3797j(parcel, i))));
            case R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
                m4232a(stringBuilder, (zza) com_google_android_gms_internal_zzld_zza___, m4210a(com_google_android_gms_internal_zzld_zza___, Double.valueOf(C0447a.m3798k(parcel, i))));
            case R.SlidingUpPanelLayout_umanoDragView /*5*/:
                m4232a(stringBuilder, (zza) com_google_android_gms_internal_zzld_zza___, m4210a(com_google_android_gms_internal_zzld_zza___, C0447a.m3799l(parcel, i)));
            case R.SlidingUpPanelLayout_umanoOverlay /*6*/:
                m4232a(stringBuilder, (zza) com_google_android_gms_internal_zzld_zza___, m4210a(com_google_android_gms_internal_zzld_zza___, Boolean.valueOf(C0447a.m3790c(parcel, i))));
            case R.SlidingUpPanelLayout_umanoClipPanel /*7*/:
                m4232a(stringBuilder, (zza) com_google_android_gms_internal_zzld_zza___, m4210a(com_google_android_gms_internal_zzld_zza___, C0447a.m3800m(parcel, i)));
            case R.SlidingUpPanelLayout_umanoAnchorPoint /*8*/:
            case HTTP.HT /*9*/:
                m4232a(stringBuilder, (zza) com_google_android_gms_internal_zzld_zza___, m4210a(com_google_android_gms_internal_zzld_zza___, C0447a.m3803p(parcel, i)));
            case HTTP.LF /*10*/:
                m4232a(stringBuilder, (zza) com_google_android_gms_internal_zzld_zza___, m4210a(com_google_android_gms_internal_zzld_zza___, m4228a(C0447a.m3802o(parcel, i))));
            case com.olacabs.customer.R.R.MapM4bAttrs_m4b_uiZoomControls /*11*/:
                throw new IllegalArgumentException("Method does not accept concrete type.");
            default:
                throw new IllegalArgumentException("Unknown field out type = " + com_google_android_gms_internal_zzld_zza___.m4199d());
        }
    }

    private void m4232a(StringBuilder stringBuilder, zza<?, ?> com_google_android_gms_internal_zzld_zza___, Object obj) {
        if (com_google_android_gms_internal_zzld_zza___.m4198c()) {
            m4233a(stringBuilder, (zza) com_google_android_gms_internal_zzld_zza___, (ArrayList) obj);
        } else {
            m4230a(stringBuilder, com_google_android_gms_internal_zzld_zza___.m4197b(), obj);
        }
    }

    private void m4233a(StringBuilder stringBuilder, zza<?, ?> com_google_android_gms_internal_zzld_zza___, ArrayList<?> arrayList) {
        stringBuilder.append("[");
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            if (i != 0) {
                stringBuilder.append(",");
            }
            m4230a(stringBuilder, com_google_android_gms_internal_zzld_zza___.m4197b(), arrayList.get(i));
        }
        stringBuilder.append("]");
    }

    private void m4234a(StringBuilder stringBuilder, String str, zza<?, ?> com_google_android_gms_internal_zzld_zza___, Parcel parcel, int i) {
        stringBuilder.append("\"").append(str).append("\":");
        if (com_google_android_gms_internal_zzld_zza___.m4205j()) {
            m4231a(stringBuilder, com_google_android_gms_internal_zzld_zza___, parcel, i);
        } else {
            m4236b(stringBuilder, com_google_android_gms_internal_zzld_zza___, parcel, i);
        }
    }

    private void m4235a(StringBuilder stringBuilder, Map<String, zza<?, ?>> map, Parcel parcel) {
        HashMap a = m4229a((Map) map);
        stringBuilder.append('{');
        int b = C0447a.m3786b(parcel);
        Object obj = null;
        while (parcel.dataPosition() < b) {
            int a2 = C0447a.m3780a(parcel);
            Entry entry = (Entry) a.get(Integer.valueOf(C0447a.m3779a(a2)));
            if (entry != null) {
                if (obj != null) {
                    stringBuilder.append(",");
                }
                m4234a(stringBuilder, (String) entry.getKey(), (zza) entry.getValue(), parcel, a2);
                obj = 1;
            }
        }
        if (parcel.dataPosition() != b) {
            throw new C0446a("Overread allowed size end=" + b, parcel);
        }
        stringBuilder.append('}');
    }

    private void m4236b(StringBuilder stringBuilder, zza<?, ?> com_google_android_gms_internal_zzld_zza___, Parcel parcel, int i) {
        if (com_google_android_gms_internal_zzld_zza___.m4200e()) {
            stringBuilder.append("[");
            switch (com_google_android_gms_internal_zzld_zza___.m4199d()) {
                case R.SlidingUpPanelLayout_umanoPanelHeight /*0*/:
                    C0514s.m4158a(stringBuilder, C0447a.m3805r(parcel, i));
                    break;
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    C0514s.m4160a(stringBuilder, C0447a.m3807t(parcel, i));
                    break;
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    C0514s.m4159a(stringBuilder, C0447a.m3806s(parcel, i));
                    break;
                case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                    C0514s.m4157a(stringBuilder, C0447a.m3808u(parcel, i));
                    break;
                case R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
                    C0514s.m4156a(stringBuilder, C0447a.m3809v(parcel, i));
                    break;
                case R.SlidingUpPanelLayout_umanoDragView /*5*/:
                    C0514s.m4160a(stringBuilder, C0447a.m3810w(parcel, i));
                    break;
                case R.SlidingUpPanelLayout_umanoOverlay /*6*/:
                    C0514s.m4162a(stringBuilder, C0447a.m3804q(parcel, i));
                    break;
                case R.SlidingUpPanelLayout_umanoClipPanel /*7*/:
                    C0514s.m4161a(stringBuilder, C0447a.m3811x(parcel, i));
                    break;
                case R.SlidingUpPanelLayout_umanoAnchorPoint /*8*/:
                case HTTP.HT /*9*/:
                case HTTP.LF /*10*/:
                    throw new UnsupportedOperationException("List of type BASE64, BASE64_URL_SAFE, or STRING_MAP is not supported");
                case com.olacabs.customer.R.R.MapM4bAttrs_m4b_uiZoomControls /*11*/:
                    Parcel[] B = C0447a.m3778B(parcel, i);
                    int length = B.length;
                    for (int i2 = 0; i2 < length; i2++) {
                        if (i2 > 0) {
                            stringBuilder.append(",");
                        }
                        B[i2].setDataPosition(0);
                        m4235a(stringBuilder, com_google_android_gms_internal_zzld_zza___.m4207l(), B[i2]);
                    }
                    break;
                default:
                    throw new IllegalStateException("Unknown field type out.");
            }
            stringBuilder.append("]");
            return;
        }
        switch (com_google_android_gms_internal_zzld_zza___.m4199d()) {
            case R.SlidingUpPanelLayout_umanoPanelHeight /*0*/:
                stringBuilder.append(C0447a.m3793f(parcel, i));
            case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                stringBuilder.append(C0447a.m3796i(parcel, i));
            case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                stringBuilder.append(C0447a.m3795h(parcel, i));
            case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                stringBuilder.append(C0447a.m3797j(parcel, i));
            case R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
                stringBuilder.append(C0447a.m3798k(parcel, i));
            case R.SlidingUpPanelLayout_umanoDragView /*5*/:
                stringBuilder.append(C0447a.m3799l(parcel, i));
            case R.SlidingUpPanelLayout_umanoOverlay /*6*/:
                stringBuilder.append(C0447a.m3790c(parcel, i));
            case R.SlidingUpPanelLayout_umanoClipPanel /*7*/:
                stringBuilder.append("\"").append(C0517v.m4169a(C0447a.m3800m(parcel, i))).append("\"");
            case R.SlidingUpPanelLayout_umanoAnchorPoint /*8*/:
                stringBuilder.append("\"").append(C0515t.m4163a(C0447a.m3803p(parcel, i))).append("\"");
            case HTTP.HT /*9*/:
                stringBuilder.append("\"").append(C0515t.m4164b(C0447a.m3803p(parcel, i)));
                stringBuilder.append("\"");
            case HTTP.LF /*10*/:
                Bundle o = C0447a.m3802o(parcel, i);
                Set<String> keySet = o.keySet();
                keySet.size();
                stringBuilder.append("{");
                int i3 = 1;
                for (String str : keySet) {
                    if (i3 == 0) {
                        stringBuilder.append(",");
                    }
                    stringBuilder.append("\"").append(str).append("\"");
                    stringBuilder.append(":");
                    stringBuilder.append("\"").append(C0517v.m4169a(o.getString(str))).append("\"");
                    i3 = 0;
                }
                stringBuilder.append("}");
            case com.olacabs.customer.R.R.MapM4bAttrs_m4b_uiZoomControls /*11*/:
                Parcel A = C0447a.m3777A(parcel, i);
                A.setDataPosition(0);
                m4235a(stringBuilder, com_google_android_gms_internal_zzld_zza___.m4207l(), A);
            default:
                throw new IllegalStateException("Unknown field type out");
        }
    }

    protected Object m4237a(String str) {
        throw new UnsupportedOperationException("Converting to JSON does not require this method.");
    }

    public Map<String, zza<?, ?>> m4238a() {
        return this.f2478d == null ? null : this.f2478d.m4223a(this.f2479e);
    }

    protected boolean m4239b(String str) {
        throw new UnsupportedOperationException("Converting to JSON does not require this method.");
    }

    public int m4240d() {
        return this.f2475a;
    }

    public int describeContents() {
        C0507m c0507m = CREATOR;
        return 0;
    }

    public Parcel m4241e() {
        switch (this.f2480f) {
            case R.SlidingUpPanelLayout_umanoPanelHeight /*0*/:
                this.f2481g = C0448b.m3814a(this.f2476b);
                C0448b.m3815a(this.f2476b, this.f2481g);
                this.f2480f = 2;
                break;
            case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                C0448b.m3815a(this.f2476b, this.f2481g);
                this.f2480f = 2;
                break;
        }
        return this.f2476b;
    }

    zzlh m4242f() {
        switch (this.f2477c) {
            case R.SlidingUpPanelLayout_umanoPanelHeight /*0*/:
                return null;
            case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                return this.f2478d;
            case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                return this.f2478d;
            default:
                throw new IllegalStateException("Invalid creation type: " + this.f2477c);
        }
    }

    public String toString() {
        C0453u.m3847a(this.f2478d, (Object) "Cannot convert to JSON on client side.");
        Parcel e = m4241e();
        e.setDataPosition(0);
        StringBuilder stringBuilder = new StringBuilder(100);
        m4235a(stringBuilder, this.f2478d.m4223a(this.f2479e), e);
        return stringBuilder.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        C0507m c0507m = CREATOR;
        C0507m.m4137a(this, parcel, i);
    }
}
