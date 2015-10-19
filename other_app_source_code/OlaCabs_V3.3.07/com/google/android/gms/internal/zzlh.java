package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.C0453u;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class zzlh implements SafeParcelable {
    public static final C0505k CREATOR;
    private final int f2471a;
    private final HashMap<String, Map<String, com.google.android.gms.internal.zzld.zza<?, ?>>> f2472b;
    private final ArrayList<zza> f2473c;
    private final String f2474d;

    public static class zza implements SafeParcelable {
        public static final C0506l CREATOR;
        final int f2465a;
        final String f2466b;
        final ArrayList<zzb> f2467c;

        static {
            CREATOR = new C0506l();
        }

        zza(int i, String str, ArrayList<zzb> arrayList) {
            this.f2465a = i;
            this.f2466b = str;
            this.f2467c = arrayList;
        }

        zza(String str, Map<String, com.google.android.gms.internal.zzld.zza<?, ?>> map) {
            this.f2465a = 1;
            this.f2466b = str;
            this.f2467c = m4220a(map);
        }

        private static ArrayList<zzb> m4220a(Map<String, com.google.android.gms.internal.zzld.zza<?, ?>> map) {
            if (map == null) {
                return null;
            }
            ArrayList<zzb> arrayList = new ArrayList();
            for (String str : map.keySet()) {
                arrayList.add(new zzb(str, (com.google.android.gms.internal.zzld.zza) map.get(str)));
            }
            return arrayList;
        }

        HashMap<String, com.google.android.gms.internal.zzld.zza<?, ?>> m4221a() {
            HashMap<String, com.google.android.gms.internal.zzld.zza<?, ?>> hashMap = new HashMap();
            int size = this.f2467c.size();
            for (int i = 0; i < size; i++) {
                zzb com_google_android_gms_internal_zzlh_zzb = (zzb) this.f2467c.get(i);
                hashMap.put(com_google_android_gms_internal_zzlh_zzb.f2469b, com_google_android_gms_internal_zzlh_zzb.f2470c);
            }
            return hashMap;
        }

        public int describeContents() {
            C0506l c0506l = CREATOR;
            return 0;
        }

        public void writeToParcel(Parcel parcel, int i) {
            C0506l c0506l = CREATOR;
            C0506l.m4134a(this, parcel, i);
        }
    }

    public static class zzb implements SafeParcelable {
        public static final C0504j CREATOR;
        final int f2468a;
        final String f2469b;
        final com.google.android.gms.internal.zzld.zza<?, ?> f2470c;

        static {
            CREATOR = new C0504j();
        }

        zzb(int i, String str, com.google.android.gms.internal.zzld.zza<?, ?> com_google_android_gms_internal_zzld_zza___) {
            this.f2468a = i;
            this.f2469b = str;
            this.f2470c = com_google_android_gms_internal_zzld_zza___;
        }

        zzb(String str, com.google.android.gms.internal.zzld.zza<?, ?> com_google_android_gms_internal_zzld_zza___) {
            this.f2468a = 1;
            this.f2469b = str;
            this.f2470c = com_google_android_gms_internal_zzld_zza___;
        }

        public int describeContents() {
            C0504j c0504j = CREATOR;
            return 0;
        }

        public void writeToParcel(Parcel parcel, int i) {
            C0504j c0504j = CREATOR;
            C0504j.m4128a(this, parcel, i);
        }
    }

    static {
        CREATOR = new C0505k();
    }

    zzlh(int i, ArrayList<zza> arrayList, String str) {
        this.f2471a = i;
        this.f2473c = null;
        this.f2472b = m4222a((ArrayList) arrayList);
        this.f2474d = (String) C0453u.m3846a((Object) str);
        m4224a();
    }

    private static HashMap<String, Map<String, com.google.android.gms.internal.zzld.zza<?, ?>>> m4222a(ArrayList<zza> arrayList) {
        HashMap<String, Map<String, com.google.android.gms.internal.zzld.zza<?, ?>>> hashMap = new HashMap();
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            zza com_google_android_gms_internal_zzlh_zza = (zza) arrayList.get(i);
            hashMap.put(com_google_android_gms_internal_zzlh_zza.f2466b, com_google_android_gms_internal_zzlh_zza.m4221a());
        }
        return hashMap;
    }

    public Map<String, com.google.android.gms.internal.zzld.zza<?, ?>> m4223a(String str) {
        return (Map) this.f2472b.get(str);
    }

    public void m4224a() {
        for (String str : this.f2472b.keySet()) {
            Map map = (Map) this.f2472b.get(str);
            for (String str2 : map.keySet()) {
                ((com.google.android.gms.internal.zzld.zza) map.get(str2)).m4196a(this);
            }
        }
    }

    int m4225b() {
        return this.f2471a;
    }

    ArrayList<zza> m4226c() {
        ArrayList<zza> arrayList = new ArrayList();
        for (String str : this.f2472b.keySet()) {
            arrayList.add(new zza(str, (Map) this.f2472b.get(str)));
        }
        return arrayList;
    }

    public String m4227d() {
        return this.f2474d;
    }

    public int describeContents() {
        C0505k c0505k = CREATOR;
        return 0;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (String str : this.f2472b.keySet()) {
            stringBuilder.append(str).append(":\n");
            Map map = (Map) this.f2472b.get(str);
            for (String str2 : map.keySet()) {
                stringBuilder.append("  ").append(str2).append(": ");
                stringBuilder.append(map.get(str2));
            }
        }
        return stringBuilder.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        C0505k c0505k = CREATOR;
        C0505k.m4131a(this, parcel, i);
    }
}
