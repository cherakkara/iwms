package com.google.android.m4b.maps.bc;

/* renamed from: com.google.android.m4b.maps.bc.b */
public class LabelSource {
    private final String f5405a;
    private final boolean f5406b;

    public LabelSource() {
        this(null);
    }

    public LabelSource(String str) {
        this(str, false);
    }

    public LabelSource(String str, boolean z) {
        this.f5405a = str;
        this.f5406b = z;
    }

    public final boolean m8184b() {
        return this.f5406b;
    }

    public String toString() {
        if (this.f5405a == null) {
            return super.toString();
        }
        return "[LabelSource: " + this.f5405a + "]";
    }

    public static boolean m8183a(LabelSource labelSource, LabelSource labelSource2) {
        if (labelSource == null) {
            return labelSource2 == null;
        } else {
            return labelSource.equals(labelSource2);
        }
    }
}
