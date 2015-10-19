package com.google.android.m4b.maps.an;

import com.google.android.m4b.maps.ab.SupportedCharacterChecker;
import com.olacabs.customer.p076d.br;
import java.io.DataInput;
import java.util.ArrayList;
import java.util.List;

/* compiled from: LabelGroup */
public final class bk {
    private static final StyleInfo f3598a;
    private final List<LabelGroup> f3599b;
    private final ab f3600c;
    private final String f3601d;

    /* renamed from: com.google.android.m4b.maps.an.bk.a */
    public static class LabelGroup {
        private final int f3590a;
        private final String f3591b;
        private final int f3592c;
        private final float f3593d;
        private final String f3594e;
        private final Style f3595f;
        private final String f3596g;
        private final float f3597h;

        public LabelGroup(int i, String str, int i2, String str2, Style style, int i3, String str3, float f) {
            this.f3590a = i;
            this.f3591b = str;
            this.f3592c = i2;
            this.f3593d = br.DEFAULT_BACKOFF_MULT / ((float) i2);
            this.f3594e = str2;
            this.f3595f = style;
            this.f3596g = str3;
            this.f3597h = f;
        }

        public static void m5823a(DataInput dataInput, ae aeVar, StyleInfo styleInfo, List<LabelGroup> list) {
            String intern;
            int readUnsignedByte;
            String intern2;
            int b;
            float f;
            int readUnsignedByte2 = dataInput.readUnsignedByte();
            if (ModelUtil.m5887a(readUnsignedByte2, 1) && aeVar.m5449a() == 10) {
                intern = dataInput.readUTF().intern();
                readUnsignedByte = dataInput.readUnsignedByte();
            } else {
                readUnsignedByte = 1;
                intern = null;
            }
            if (ModelUtil.m5887a(readUnsignedByte2, 2)) {
                intern2 = SupportedCharacterChecker.m4846a(dataInput.readUTF()).intern();
            } else {
                intern2 = null;
            }
            if (ModelUtil.m5887a(readUnsignedByte2, 4)) {
                styleInfo = StyleInfo.m6111a(dataInput, aeVar);
            } else if (!LabelGroup.m5824a(readUnsignedByte2)) {
                styleInfo = bk.f3598a;
            }
            if (aeVar.m5449a() != 10 && ModelUtil.m5887a(readUnsignedByte2, 1)) {
                Style a = styleInfo.m6112a();
                if (a.m6101h()) {
                    bf l = a.m6105l();
                    intern = l.m5768a();
                    b = l.m5769b();
                    f = 0.0f;
                    if (ModelUtil.m5887a(readUnsignedByte2, 16)) {
                        f = ((float) an.m5578a(dataInput)) / 8.0f;
                    }
                    if (ModelUtil.m5887a(readUnsignedByte2, 32)) {
                        an.m5578a(dataInput);
                    }
                    if (ModelUtil.m5887a(readUnsignedByte2, 8) || readUnsignedByte2 == 8) {
                        list.add(new LabelGroup(readUnsignedByte2, intern, b, intern2, styleInfo.m6112a(), styleInfo.m6114c(), styleInfo.m6113b(), f));
                    }
                    list.add(new LabelGroup(readUnsignedByte2 ^ 8, intern, b, intern2, styleInfo.m6112a(), styleInfo.m6114c(), styleInfo.m6113b(), f));
                    list.add(new LabelGroup(8, null, 1, null, null, -1, null, -1.0f));
                    return;
                }
            }
            b = readUnsignedByte;
            f = 0.0f;
            if (ModelUtil.m5887a(readUnsignedByte2, 16)) {
                f = ((float) an.m5578a(dataInput)) / 8.0f;
            }
            if (ModelUtil.m5887a(readUnsignedByte2, 32)) {
                an.m5578a(dataInput);
            }
            if (ModelUtil.m5887a(readUnsignedByte2, 8)) {
            }
            list.add(new LabelGroup(readUnsignedByte2, intern, b, intern2, styleInfo.m6112a(), styleInfo.m6114c(), styleInfo.m6113b(), f));
        }

        public final boolean m5825a() {
            return LabelGroup.m5824a(this.f3590a);
        }

        private static boolean m5824a(int i) {
            return ModelUtil.m5887a(i, 2) && !ModelUtil.m5887a(i, 1);
        }

        public final boolean m5826b() {
            return ModelUtil.m5887a(this.f3590a, 1);
        }

        public final boolean m5827c() {
            return ModelUtil.m5887a(this.f3590a, 2);
        }

        public final boolean m5828d() {
            return ModelUtil.m5887a(this.f3590a, 4);
        }

        public final boolean m5829e() {
            return ModelUtil.m5887a(this.f3590a, 16);
        }

        public final boolean m5830f() {
            return ModelUtil.m5887a(this.f3590a, 8);
        }

        public final String m5831g() {
            return this.f3591b;
        }

        public final float m5832h() {
            return this.f3593d;
        }

        public final String m5833i() {
            return this.f3594e;
        }

        public final Style m5834j() {
            return this.f3595f;
        }

        public final float m5835k() {
            return this.f3597h;
        }

        public final int hashCode() {
            int i = 0;
            int hashCode = ((((this.f3596g == null ? 0 : this.f3596g.hashCode()) + (((this.f3595f == null ? 0 : this.f3595f.hashCode()) + (((this.f3591b == null ? 0 : this.f3591b.hashCode()) + ((((this.f3590a + 31) * 31) + Float.floatToIntBits(this.f3597h)) * 31)) * 31)) * 31)) * 31) + Float.floatToIntBits((float) this.f3592c)) * 31;
            if (this.f3594e != null) {
                i = this.f3594e.hashCode();
            }
            return hashCode + i;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            LabelGroup labelGroup = (LabelGroup) obj;
            if (this.f3590a != labelGroup.f3590a) {
                return false;
            }
            if (Float.floatToIntBits(this.f3597h) != Float.floatToIntBits(labelGroup.f3597h)) {
                return false;
            }
            if (this.f3591b == null) {
                if (labelGroup.f3591b != null) {
                    return false;
                }
            } else if (!this.f3591b.equals(labelGroup.f3591b)) {
                return false;
            }
            if (this.f3595f == null) {
                if (labelGroup.f3595f != null) {
                    return false;
                }
            } else if (!this.f3595f.equals(labelGroup.f3595f)) {
                return false;
            }
            if (this.f3596g == null) {
                if (labelGroup.f3596g != null) {
                    return false;
                }
            } else if (!this.f3596g.equals(labelGroup.f3596g)) {
                return false;
            }
            if (Float.floatToIntBits((float) this.f3592c) != Float.floatToIntBits((float) labelGroup.f3592c)) {
                return false;
            }
            if (this.f3594e != null) {
                return this.f3594e.equals(labelGroup.f3594e);
            }
            if (labelGroup.f3594e != null) {
                return false;
            }
            return true;
        }

        public final int m5836l() {
            return (((ModelUtil.m5885a(this.f3591b) + 48) + ModelUtil.m5885a(this.f3594e)) + ModelUtil.m5885a(this.f3596g)) + ModelUtil.m5883a(this.f3595f);
        }
    }

    static {
        f3598a = new StyleInfo(null, null, -1);
    }

    public bk(List<LabelGroup> list, ab abVar) {
        StringBuilder stringBuilder = new StringBuilder();
        for (LabelGroup labelGroup : list) {
            if (labelGroup.m5827c()) {
                stringBuilder.append(labelGroup.m5833i());
            }
            if (labelGroup.m5830f()) {
                stringBuilder.append('\n');
            }
        }
        this.f3601d = stringBuilder.toString();
        this.f3600c = abVar;
        this.f3599b = list;
    }

    public static bk m5837a(DataInput dataInput, ae aeVar, StyleInfo styleInfo) {
        ab a;
        int a2 = an.m5578a(dataInput);
        List arrayList = new ArrayList();
        for (int i = 0; i < a2; i++) {
            LabelGroup.m5823a(dataInput, aeVar, styleInfo, arrayList);
        }
        if (a2 > 1) {
            a = ab.m5417a(dataInput);
        } else {
            a = ab.f3378b;
        }
        return new bk(arrayList, a);
    }

    public final String m5840a() {
        return this.f3601d;
    }

    public final int m5841b() {
        return this.f3599b.size();
    }

    public final LabelGroup m5839a(int i) {
        return (LabelGroup) this.f3599b.get(i);
    }

    public final ab m5842c() {
        return this.f3600c;
    }

    public final int hashCode() {
        return (((this.f3600c == null ? 0 : this.f3600c.hashCode()) + 31) * 31) + this.f3599b.hashCode();
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        bk bkVar = (bk) obj;
        if (this.f3600c == null) {
            if (bkVar.f3600c != null) {
                return false;
            }
        } else if (!this.f3600c.equals(bkVar.f3600c)) {
            return false;
        }
        return this.f3599b.equals(bkVar.f3599b);
    }

    public final int m5843d() {
        int i = 0;
        for (LabelGroup l : this.f3599b) {
            i = l.m5836l() + i;
        }
        int a = (i + 24) + ModelUtil.m5885a(this.f3601d);
        ab abVar = this.f3600c;
        return a + ab.m5418c();
    }
}
