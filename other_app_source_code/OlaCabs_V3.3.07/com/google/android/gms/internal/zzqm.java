package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.C0452t;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.List;

public class zzqm implements SafeParcelable {
    public static final ay CREATOR;
    final int f2572a;
    private final String f2573b;
    private final List<zza> f2574c;

    public static final class zza implements SafeParcelable {
        public static final ax CREATOR;
        final int f2569a;
        private final String f2570b;
        private final String f2571c;

        static {
            CREATOR = new ax();
        }

        zza(int i, String str, String str2) {
            this.f2569a = i;
            this.f2570b = str;
            this.f2571c = str2;
        }

        public String m4297a() {
            return this.f2570b;
        }

        public String m4298b() {
            return this.f2571c;
        }

        public int describeContents() {
            ax axVar = CREATOR;
            return 0;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof zza)) {
                return false;
            }
            zza com_google_android_gms_internal_zzqm_zza = (zza) obj;
            return C0452t.m3845a(this.f2570b, com_google_android_gms_internal_zzqm_zza.f2570b) && C0452t.m3845a(this.f2571c, com_google_android_gms_internal_zzqm_zza.f2571c);
        }

        public int hashCode() {
            return C0452t.m3843a(this.f2570b, this.f2571c);
        }

        public String toString() {
            return C0452t.m3844a((Object) this).m3842a("title", this.f2570b).m3842a("uri", this.f2571c).toString();
        }

        public void writeToParcel(Parcel parcel, int i) {
            ax axVar = CREATOR;
            ax.m4040a(this, parcel, i);
        }
    }

    static {
        CREATOR = new ay();
    }

    zzqm(int i, String str, List<zza> list) {
        this.f2572a = i;
        this.f2573b = str;
        this.f2574c = list;
    }

    public String m4299a() {
        return this.f2573b;
    }

    public List<zza> m4300b() {
        return this.f2574c;
    }

    public int describeContents() {
        ay ayVar = CREATOR;
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzqm)) {
            return false;
        }
        zzqm com_google_android_gms_internal_zzqm = (zzqm) obj;
        return C0452t.m3845a(this.f2573b, com_google_android_gms_internal_zzqm.f2573b) && C0452t.m3845a(this.f2574c, com_google_android_gms_internal_zzqm.f2574c);
    }

    public int hashCode() {
        return C0452t.m3843a(this.f2573b, this.f2574c);
    }

    public String toString() {
        return C0452t.m3844a((Object) this).m3842a("data", this.f2573b).m3842a("actions", this.f2574c).toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        ay ayVar = CREATOR;
        ay.m4043a(this, parcel, i);
    }
}
