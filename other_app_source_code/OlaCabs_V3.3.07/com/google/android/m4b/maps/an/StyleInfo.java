package com.google.android.m4b.maps.an;

import com.newrelic.agent.android.instrumentation.Trace;
import java.io.DataInput;

/* renamed from: com.google.android.m4b.maps.an.w */
public final class StyleInfo {
    private final Style f3714a;
    private final String f3715b;
    private final int f3716c;

    public StyleInfo(Style style, String str, int i) {
        this.f3714a = style;
        this.f3715b = str;
        this.f3716c = i;
    }

    public static StyleInfo m6111a(DataInput dataInput, ae aeVar) {
        int a;
        Style b;
        String a2;
        if (aeVar.m5449a() == 11) {
            a = an.m5578a(dataInput);
            StyleEntry b2 = aeVar.m5452b(a);
            if (b2 != null) {
                b = b2.m6108b();
                a2 = b2.m6107a();
            } else {
                b = Style.m6090a();
                a2 = Trace.NULL;
            }
        } else {
            b = aeVar.m5450a(an.m5578a(dataInput));
            a = an.m5578a(dataInput);
            a2 = aeVar.m5453c(a);
        }
        return new StyleInfo(b, a2, a);
    }

    public final Style m6112a() {
        return this.f3714a;
    }

    public final String m6113b() {
        return this.f3715b;
    }

    public final int m6114c() {
        return this.f3716c;
    }
}
