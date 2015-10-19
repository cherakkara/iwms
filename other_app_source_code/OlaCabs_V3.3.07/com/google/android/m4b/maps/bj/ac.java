package com.google.android.m4b.maps.bj;

import p004b.p005a.p006a.p007a.p008a.p013d.EventsFilesManager;

/* compiled from: PanoramaImageKey */
final class ac {
    private final String f6316a;
    private final int f6317b;
    private final int f6318c;
    private final int f6319d;
    private final int f6320e;
    private final int f6321f;

    public ac(String str, int i, int i2, int i3, int i4) {
        this.f6316a = str;
        this.f6321f = ((((i2 << (i4 + 1)) | i) | (i3 << (i4 + 2))) ^ i4) ^ this.f6316a.hashCode();
        this.f6317b = i;
        this.f6318c = i2;
        this.f6319d = i3;
        this.f6320e = i4;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ac)) {
            return false;
        }
        ac acVar = (ac) obj;
        if (acVar.f6317b == this.f6317b && acVar.f6318c == this.f6318c && acVar.f6319d == this.f6319d && acVar.f6320e == this.f6320e && acVar.f6316a.equals(this.f6316a)) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return this.f6321f;
    }

    public final String toString() {
        return "panoid=" + this.f6316a + "&zoom=" + this.f6320e + "&x=" + this.f6317b + "&y=" + this.f6318c + "&face=" + this.f6319d;
    }

    public final String m9744a() {
        return "tile_" + this.f6316a + EventsFilesManager.ROLL_OVER_FILE_NAME_SEPARATOR + this.f6320e + EventsFilesManager.ROLL_OVER_FILE_NAME_SEPARATOR + this.f6317b + EventsFilesManager.ROLL_OVER_FILE_NAME_SEPARATOR + this.f6318c;
    }

    public final String m9745b() {
        return this.f6316a;
    }

    public final int m9746c() {
        return this.f6317b;
    }

    public final int m9747d() {
        return this.f6318c;
    }

    public final int m9748e() {
        return this.f6319d;
    }

    public final int m9749f() {
        return this.f6320e;
    }
}
