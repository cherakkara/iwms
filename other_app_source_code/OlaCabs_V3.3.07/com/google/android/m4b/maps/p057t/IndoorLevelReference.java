package com.google.android.m4b.maps.p057t;

import com.google.android.m4b.maps.p057t.FeatureId.FeatureId;

/* renamed from: com.google.android.m4b.maps.t.c */
public final class IndoorLevelReference {
    private final FeatureId f7857a;
    private final int f7858b;

    public IndoorLevelReference(FeatureId featureId, int i) {
        this.f7857a = featureId;
        this.f7858b = i;
    }

    public final FeatureId m11306a() {
        return this.f7857a;
    }

    public final int m11307b() {
        return this.f7858b;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof IndoorLevelReference) {
            return this.f7857a.equals(((IndoorLevelReference) obj).f7857a);
        }
        if (obj instanceof FeatureId) {
            return this.f7857a.equals(obj);
        }
        return false;
    }

    public final int hashCode() {
        return this.f7857a.hashCode();
    }

    public final String toString() {
        return "{" + getClass().getSimpleName() + ":mId=" + this.f7857a + ", mLevelNumberE3=" + this.f7858b + "}";
    }
}
