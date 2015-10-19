package org.p087a.p090b.p091a;

import org.p087a.p088a.p089a.SourceLocation;

/* renamed from: org.a.b.a.g */
class SourceLocationImpl implements SourceLocation {
    Class f11683a;
    String f11684b;
    int f11685c;

    SourceLocationImpl(Class cls, String str, int i) {
        this.f11683a = cls;
        this.f11684b = str;
        this.f11685c = i;
    }

    public String m15086a() {
        return this.f11684b;
    }

    public int m15087b() {
        return this.f11685c;
    }

    public String toString() {
        return new StringBuffer().append(m15086a()).append(":").append(m15087b()).toString();
    }
}
