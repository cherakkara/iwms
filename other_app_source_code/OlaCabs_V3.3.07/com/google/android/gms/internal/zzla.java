package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.zzld.C0522a;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public final class zzla implements SafeParcelable, C0522a<String, Integer> {
    public static final C0501g CREATOR;
    private final int f2450a;
    private final HashMap<String, Integer> f2451b;
    private final HashMap<Integer, String> f2452c;
    private final ArrayList<zza> f2453d;

    public static final class zza implements SafeParcelable {
        public static final C0502h CREATOR;
        final int f2447a;
        final String f2448b;
        final int f2449c;

        static {
            CREATOR = new C0502h();
        }

        zza(int i, String str, int i2) {
            this.f2447a = i;
            this.f2448b = str;
            this.f2449c = i2;
        }

        zza(String str, int i) {
            this.f2447a = 1;
            this.f2448b = str;
            this.f2449c = i;
        }

        public int describeContents() {
            C0502h c0502h = CREATOR;
            return 0;
        }

        public void writeToParcel(Parcel parcel, int i) {
            C0502h c0502h = CREATOR;
            C0502h.m4122a(this, parcel, i);
        }
    }

    static {
        CREATOR = new C0501g();
    }

    public zzla() {
        this.f2450a = 1;
        this.f2451b = new HashMap();
        this.f2452c = new HashMap();
        this.f2453d = null;
    }

    zzla(int i, ArrayList<zza> arrayList) {
        this.f2450a = i;
        this.f2451b = new HashMap();
        this.f2452c = new HashMap();
        this.f2453d = null;
        m4187a((ArrayList) arrayList);
    }

    private void m4187a(ArrayList<zza> arrayList) {
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            zza com_google_android_gms_internal_zzla_zza = (zza) it.next();
            m4189a(com_google_android_gms_internal_zzla_zza.f2448b, com_google_android_gms_internal_zzla_zza.f2449c);
        }
    }

    int m4188a() {
        return this.f2450a;
    }

    public zzla m4189a(String str, int i) {
        this.f2451b.put(str, Integer.valueOf(i));
        this.f2452c.put(Integer.valueOf(i), str);
        return this;
    }

    public /* synthetic */ Object m4190a(Object obj) {
        return m4191a((Integer) obj);
    }

    public String m4191a(Integer num) {
        String str = (String) this.f2452c.get(num);
        return (str == null && this.f2451b.containsKey("gms_unknown")) ? "gms_unknown" : str;
    }

    ArrayList<zza> m4192b() {
        ArrayList<zza> arrayList = new ArrayList();
        for (String str : this.f2451b.keySet()) {
            arrayList.add(new zza(str, ((Integer) this.f2451b.get(str)).intValue()));
        }
        return arrayList;
    }

    public int describeContents() {
        C0501g c0501g = CREATOR;
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        C0501g c0501g = CREATOR;
        C0501g.m4119a(this, parcel, i);
    }
}
