package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.C0453u;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.sothree.slidinguppanel.p086a.R.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.protocol.HTTP;

public abstract class zzld {

    /* renamed from: com.google.android.gms.internal.zzld.a */
    public interface C0522a<I, O> {
        I m4186a(O o);
    }

    public static class zza<I, O> implements SafeParcelable {
        public static final C0503i CREATOR;
        protected final int f2454a;
        protected final boolean f2455b;
        protected final int f2456c;
        protected final boolean f2457d;
        protected final String f2458e;
        protected final int f2459f;
        protected final Class<? extends zzld> f2460g;
        protected final String f2461h;
        private final int f2462i;
        private zzlh f2463j;
        private C0522a<I, O> f2464k;

        static {
            CREATOR = new C0503i();
        }

        zza(int i, int i2, boolean z, int i3, boolean z2, String str, int i4, String str2, zzky com_google_android_gms_internal_zzky) {
            this.f2462i = i;
            this.f2454a = i2;
            this.f2455b = z;
            this.f2456c = i3;
            this.f2457d = z2;
            this.f2458e = str;
            this.f2459f = i4;
            if (str2 == null) {
                this.f2460g = null;
                this.f2461h = null;
            } else {
                this.f2460g = zzlk.class;
                this.f2461h = str2;
            }
            if (com_google_android_gms_internal_zzky == null) {
                this.f2464k = null;
            } else {
                this.f2464k = com_google_android_gms_internal_zzky.m4185c();
            }
        }

        public int m4194a() {
            return this.f2462i;
        }

        public I m4195a(O o) {
            return this.f2464k.m4186a(o);
        }

        public void m4196a(zzlh com_google_android_gms_internal_zzlh) {
            this.f2463j = com_google_android_gms_internal_zzlh;
        }

        public int m4197b() {
            return this.f2454a;
        }

        public boolean m4198c() {
            return this.f2455b;
        }

        public int m4199d() {
            return this.f2456c;
        }

        public int describeContents() {
            C0503i c0503i = CREATOR;
            return 0;
        }

        public boolean m4200e() {
            return this.f2457d;
        }

        public String m4201f() {
            return this.f2458e;
        }

        public int m4202g() {
            return this.f2459f;
        }

        public Class<? extends zzld> m4203h() {
            return this.f2460g;
        }

        String m4204i() {
            return this.f2461h == null ? null : this.f2461h;
        }

        public boolean m4205j() {
            return this.f2464k != null;
        }

        zzky m4206k() {
            return this.f2464k == null ? null : zzky.m4182a(this.f2464k);
        }

        public Map<String, zza<?, ?>> m4207l() {
            C0453u.m3846a(this.f2461h);
            C0453u.m3846a(this.f2463j);
            return this.f2463j.m4223a(this.f2461h);
        }

        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Field\n");
            stringBuilder.append("            versionCode=").append(this.f2462i).append('\n');
            stringBuilder.append("                 typeIn=").append(this.f2454a).append('\n');
            stringBuilder.append("            typeInArray=").append(this.f2455b).append('\n');
            stringBuilder.append("                typeOut=").append(this.f2456c).append('\n');
            stringBuilder.append("           typeOutArray=").append(this.f2457d).append('\n');
            stringBuilder.append("        outputFieldName=").append(this.f2458e).append('\n');
            stringBuilder.append("      safeParcelFieldId=").append(this.f2459f).append('\n');
            stringBuilder.append("       concreteTypeName=").append(m4204i()).append('\n');
            if (m4203h() != null) {
                stringBuilder.append("     concreteType.class=").append(m4203h().getCanonicalName()).append('\n');
            }
            stringBuilder.append("          converterName=").append(this.f2464k == null ? "null" : this.f2464k.getClass().getCanonicalName()).append('\n');
            return stringBuilder.toString();
        }

        public void writeToParcel(Parcel parcel, int i) {
            C0503i c0503i = CREATOR;
            C0503i.m4125a(this, parcel, i);
        }
    }

    private void m4208a(StringBuilder stringBuilder, zza com_google_android_gms_internal_zzld_zza, Object obj) {
        if (com_google_android_gms_internal_zzld_zza.m4197b() == 11) {
            stringBuilder.append(((zzld) com_google_android_gms_internal_zzld_zza.m4203h().cast(obj)).toString());
        } else if (com_google_android_gms_internal_zzld_zza.m4197b() == 7) {
            stringBuilder.append("\"");
            stringBuilder.append(C0517v.m4169a((String) obj));
            stringBuilder.append("\"");
        } else {
            stringBuilder.append(obj);
        }
    }

    private void m4209a(StringBuilder stringBuilder, zza com_google_android_gms_internal_zzld_zza, ArrayList<Object> arrayList) {
        stringBuilder.append("[");
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            if (i > 0) {
                stringBuilder.append(",");
            }
            Object obj = arrayList.get(i);
            if (obj != null) {
                m4208a(stringBuilder, com_google_android_gms_internal_zzld_zza, obj);
            }
        }
        stringBuilder.append("]");
    }

    protected <O, I> I m4210a(zza<I, O> com_google_android_gms_internal_zzld_zza_I__O, Object obj) {
        return com_google_android_gms_internal_zzld_zza_I__O.f2464k != null ? com_google_android_gms_internal_zzld_zza_I__O.m4195a(obj) : obj;
    }

    protected abstract Object m4211a(String str);

    public abstract Map<String, zza<?, ?>> m4212a();

    protected boolean m4213a(zza com_google_android_gms_internal_zzld_zza) {
        return com_google_android_gms_internal_zzld_zza.m4199d() == 11 ? com_google_android_gms_internal_zzld_zza.m4200e() ? m4219d(com_google_android_gms_internal_zzld_zza.m4201f()) : m4218c(com_google_android_gms_internal_zzld_zza.m4201f()) : m4216b(com_google_android_gms_internal_zzld_zza.m4201f());
    }

    protected Object m4214b(zza com_google_android_gms_internal_zzld_zza) {
        String f = com_google_android_gms_internal_zzld_zza.m4201f();
        if (com_google_android_gms_internal_zzld_zza.m4203h() == null) {
            return m4211a(com_google_android_gms_internal_zzld_zza.m4201f());
        }
        C0453u.m3852a(m4211a(com_google_android_gms_internal_zzld_zza.m4201f()) == null, "Concrete field shouldn't be value object: %s", com_google_android_gms_internal_zzld_zza.m4201f());
        Map c = com_google_android_gms_internal_zzld_zza.m4200e() ? m4217c() : m4215b();
        if (c != null) {
            return c.get(f);
        }
        try {
            return getClass().getMethod("get" + Character.toUpperCase(f.charAt(0)) + f.substring(1), new Class[0]).invoke(this, new Object[0]);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    public HashMap<String, Object> m4215b() {
        return null;
    }

    protected abstract boolean m4216b(String str);

    public HashMap<String, Object> m4217c() {
        return null;
    }

    protected boolean m4218c(String str) {
        throw new UnsupportedOperationException("Concrete types not supported");
    }

    protected boolean m4219d(String str) {
        throw new UnsupportedOperationException("Concrete type arrays not supported");
    }

    public String toString() {
        Map a = m4212a();
        StringBuilder stringBuilder = new StringBuilder(100);
        for (String str : a.keySet()) {
            zza com_google_android_gms_internal_zzld_zza = (zza) a.get(str);
            if (m4213a(com_google_android_gms_internal_zzld_zza)) {
                Object a2 = m4210a(com_google_android_gms_internal_zzld_zza, m4214b(com_google_android_gms_internal_zzld_zza));
                if (stringBuilder.length() == 0) {
                    stringBuilder.append("{");
                } else {
                    stringBuilder.append(",");
                }
                stringBuilder.append("\"").append(str).append("\":");
                if (a2 != null) {
                    switch (com_google_android_gms_internal_zzld_zza.m4199d()) {
                        case R.SlidingUpPanelLayout_umanoAnchorPoint /*8*/:
                            stringBuilder.append("\"").append(C0515t.m4163a((byte[]) a2)).append("\"");
                            break;
                        case HTTP.HT /*9*/:
                            stringBuilder.append("\"").append(C0515t.m4164b((byte[]) a2)).append("\"");
                            break;
                        case HTTP.LF /*10*/:
                            C0518w.m4170a(stringBuilder, (HashMap) a2);
                            break;
                        default:
                            if (!com_google_android_gms_internal_zzld_zza.m4198c()) {
                                m4208a(stringBuilder, com_google_android_gms_internal_zzld_zza, a2);
                                break;
                            }
                            m4209a(stringBuilder, com_google_android_gms_internal_zzld_zza, (ArrayList) a2);
                            break;
                    }
                }
                stringBuilder.append("null");
            }
        }
        if (stringBuilder.length() > 0) {
            stringBuilder.append("}");
        } else {
            stringBuilder.append("{}");
        }
        return stringBuilder.toString();
    }
}
