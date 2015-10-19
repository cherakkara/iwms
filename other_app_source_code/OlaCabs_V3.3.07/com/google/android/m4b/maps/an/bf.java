package com.google.android.m4b.maps.an;

/* compiled from: Icon */
public final class bf {
    private final String f3566a;
    private final int f3567b;

    public bf(String str, int i) {
        this.f3566a = str;
        this.f3567b = i;
    }

    public final String m5768a() {
        return this.f3566a;
    }

    public final int m5769b() {
        return this.f3567b;
    }

    public final int hashCode() {
        return ((this.f3566a.hashCode() + 31) * 31) + this.f3567b;
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
        bf bfVar = (bf) obj;
        if (this.f3566a == bfVar.f3566a && this.f3567b == bfVar.f3567b) {
            return true;
        }
        return false;
    }

    public final String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Icon{url=").append(this.f3566a).append(", , scaleDownFactor=").append(this.f3567b).append('}');
        return stringBuilder.toString();
    }
}
