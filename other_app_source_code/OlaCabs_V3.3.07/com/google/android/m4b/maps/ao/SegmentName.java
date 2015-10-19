package com.google.android.m4b.maps.ao;

import java.util.regex.Pattern;

/* renamed from: com.google.android.m4b.maps.ao.d */
public final class SegmentName {
    private static final Pattern f3739a;
    private final String f3740b;
    private final String f3741c;
    private final boolean f3742d;

    static {
        f3739a = Pattern.compile("[0-9]+[A-Z]?");
    }

    public SegmentName(String str, String str2, boolean z) {
        boolean z2;
        this.f3741c = str;
        this.f3740b = str2;
        if (z) {
            if (f3739a.matcher(this.f3740b == null ? this.f3741c : this.f3740b).matches()) {
                z2 = true;
                this.f3742d = z2;
            }
        }
        z2 = false;
        this.f3742d = z2;
    }

    public final int hashCode() {
        int i = 0;
        int hashCode = ((this.f3742d ? 1231 : 1237) + (((this.f3740b == null ? 0 : this.f3740b.hashCode()) + 31) * 31)) * 31;
        if (this.f3741c != null) {
            i = this.f3741c.hashCode();
        }
        return hashCode + i;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SegmentName)) {
            return false;
        }
        SegmentName segmentName = (SegmentName) obj;
        if (this.f3741c.equals(segmentName.f3741c) && (((this.f3740b == null && segmentName.f3740b == null) || this.f3740b.equals(segmentName.f3740b)) && this.f3742d == segmentName.f3742d)) {
            return true;
        }
        return false;
    }

    public final String toString() {
        return this.f3741c;
    }
}
