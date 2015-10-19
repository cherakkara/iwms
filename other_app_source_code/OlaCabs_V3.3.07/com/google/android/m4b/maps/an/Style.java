package com.google.android.m4b.maps.an;

import java.io.DataInput;
import java.util.Arrays;

/* renamed from: com.google.android.m4b.maps.an.t */
public final class Style {
    private static final int[] f3701a;
    private static Style f3702b;
    private final int f3703c;
    private final int f3704d;
    private final int[] f3705e;
    private final Stroke[] f3706f;
    private final TextStyle f3707g;
    private final TextBoxStyle f3708h;
    private final Stroke f3709i;
    private final bf f3710j;

    static {
        f3701a = new int[0];
        f3702b = new Style(-1, 0, f3701a, new Stroke[0], TextStyle.m6120a(), TextBoxStyle.m6117a(), Stroke.m6081a(), null);
    }

    public static Style m6091a(int i, DataInput dataInput, int i2) {
        int[] iArr;
        Stroke[] strokeArr;
        TextStyle a;
        TextBoxStyle a2;
        Stroke a3;
        bf bfVar;
        int i3 = 0;
        int readUnsignedByte = dataInput.readUnsignedByte();
        if (ModelUtil.m5887a(readUnsignedByte, 1)) {
            int a4;
            a4 = an.m5578a(dataInput);
            iArr = new int[a4];
            for (int i4 = 0; i4 < a4; i4++) {
                iArr[i4] = dataInput.readInt();
            }
        } else {
            iArr = null;
        }
        if (ModelUtil.m5887a(readUnsignedByte, 2)) {
            a4 = an.m5578a(dataInput);
            strokeArr = new Stroke[a4];
            while (i3 < a4) {
                strokeArr[i3] = Stroke.m6082a(dataInput);
                i3++;
            }
        } else {
            strokeArr = null;
        }
        if (ModelUtil.m5887a(readUnsignedByte, 4)) {
            a = TextStyle.m6121a(dataInput);
        } else {
            a = null;
        }
        if (ModelUtil.m5887a(readUnsignedByte, 8)) {
            a2 = TextBoxStyle.m6118a(dataInput, i2);
        } else {
            a2 = null;
        }
        if (ModelUtil.m5887a(readUnsignedByte, 16)) {
            a3 = Stroke.m6082a(dataInput);
        } else {
            a3 = null;
        }
        if (i2 == 11 && ModelUtil.m5887a(readUnsignedByte, 32)) {
            bfVar = new bf(dataInput.readUTF(), dataInput.readUnsignedByte());
        } else {
            bfVar = null;
        }
        return new Style(i, readUnsignedByte, iArr, strokeArr, a, a2, a3, bfVar);
    }

    public static Style m6090a() {
        return f3702b;
    }

    public Style(int i, int i2, int[] iArr, Stroke[] strokeArr, TextStyle textStyle, TextBoxStyle textBoxStyle, Stroke stroke, bf bfVar) {
        this.f3703c = i;
        this.f3704d = i2;
        this.f3705e = iArr;
        this.f3706f = strokeArr;
        this.f3707g = textStyle;
        this.f3708h = textBoxStyle;
        this.f3709i = stroke;
        this.f3710j = bfVar;
    }

    public final int m6094b() {
        return this.f3706f == null ? 0 : this.f3706f.length;
    }

    public final int m6096c() {
        return this.f3705e == null ? 0 : this.f3705e.length;
    }

    public final int m6093a(int i) {
        return this.f3705e[i];
    }

    public final boolean m6097d() {
        return ModelUtil.m5887a(this.f3704d, 2);
    }

    public final boolean m6098e() {
        return ModelUtil.m5887a(this.f3704d, 4);
    }

    public final boolean m6099f() {
        return ModelUtil.m5887a(this.f3704d, 8);
    }

    public final boolean m6100g() {
        return ModelUtil.m5887a(this.f3704d, 16);
    }

    public final boolean m6101h() {
        return ModelUtil.m5887a(this.f3704d, 32);
    }

    public final Stroke m6095b(int i) {
        return this.f3706f[i];
    }

    public final TextStyle m6102i() {
        return this.f3707g;
    }

    public final TextBoxStyle m6103j() {
        return this.f3708h;
    }

    public final Stroke m6104k() {
        return this.f3709i;
    }

    public final bf m6105l() {
        return this.f3710j;
    }

    public final int hashCode() {
        int i = 0;
        int hashCode = ((this.f3708h == null ? 0 : this.f3708h.hashCode()) + (((((((((((this.f3709i == null ? 0 : this.f3709i.hashCode()) + 31) * 31) + this.f3704d) * 31) + Arrays.hashCode(this.f3705e)) * 31) + this.f3703c) * 31) + Arrays.hashCode(this.f3706f)) * 31)) * 31;
        if (this.f3707g != null) {
            i = this.f3707g.hashCode();
        }
        return hashCode + i;
    }

    public final String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Style{id=").append(this.f3703c).append(", ");
        Style.m6092a("fillColors", this.f3705e, stringBuilder);
        stringBuilder.append(", ");
        stringBuilder.append(", components=").append(this.f3704d).append(", strokes=").append(this.f3706f == null ? null : Arrays.asList(this.f3706f)).append(", textStyle=").append(this.f3707g).append(", textBoxStyle=").append(this.f3708h).append(", arrowStyle=").append(this.f3709i).append(", icon=").append(this.f3710j).append('}');
        return stringBuilder.toString();
    }

    private static void m6092a(String str, int[] iArr, StringBuilder stringBuilder) {
        stringBuilder.append(str).append("=");
        if (iArr == null) {
            stringBuilder.append(iArr);
            return;
        }
        stringBuilder.append("[");
        Object obj = 1;
        for (int i : iArr) {
            if (obj != null) {
                obj = null;
            } else {
                stringBuilder.append(",");
            }
            stringBuilder.append(Integer.toHexString(i));
        }
        stringBuilder.append("]");
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
        Style style = (Style) obj;
        if (this.f3709i == null) {
            if (style.f3709i != null) {
                return false;
            }
        } else if (!this.f3709i.equals(style.f3709i)) {
            return false;
        }
        if (this.f3704d != style.f3704d) {
            return false;
        }
        if (!Arrays.equals(this.f3705e, style.f3705e)) {
            return false;
        }
        if (this.f3703c != style.f3703c) {
            return false;
        }
        if (!Arrays.equals(this.f3706f, style.f3706f)) {
            return false;
        }
        if (this.f3708h == null) {
            if (style.f3708h != null) {
                return false;
            }
        } else if (!this.f3708h.equals(style.f3708h)) {
            return false;
        }
        if (this.f3707g == null) {
            if (style.f3707g != null) {
                return false;
            }
            return true;
        } else if (this.f3707g.equals(style.f3707g)) {
            return true;
        } else {
            return false;
        }
    }

    public final int m6106m() {
        int i;
        int i2 = 0;
        int length = this.f3705e == null ? 0 : this.f3705e.length * 4;
        if (this.f3706f != null) {
            Stroke[] strokeArr = this.f3706f;
            int i3 = 0;
            i = 0;
            while (i3 < strokeArr.length) {
                int h = strokeArr[i3].m6089h() + i;
                i3++;
                i = h;
            }
        } else {
            i = 0;
        }
        Stroke stroke = this.f3709i;
        if (stroke != null) {
            i2 = stroke.m6089h();
        }
        return ((length + 60) + i) + i2;
    }
}
