package com.google.p025a.p030e;

import java.io.IOException;

/* renamed from: com.google.a.e.c */
final class GwtWorkarounds {

    /* renamed from: com.google.a.e.c.a */
    interface GwtWorkarounds {
        void m2980a() throws IOException;

        void m2981a(byte b) throws IOException;
    }

    /* renamed from: com.google.a.e.c.b */
    interface GwtWorkarounds {
        void m2995a() throws IOException;

        void m2996a(char c) throws IOException;
    }

    /* renamed from: com.google.a.e.c.1 */
    static class GwtWorkarounds implements GwtWorkarounds {
        final /* synthetic */ StringBuilder f1893a;

        GwtWorkarounds(StringBuilder stringBuilder) {
            this.f1893a = stringBuilder;
        }

        public void m2998a(char c) {
            this.f1893a.append(c);
        }

        public void m2997a() {
        }

        public String toString() {
            return this.f1893a.toString();
        }
    }

    static GwtWorkarounds m2999a(int i) {
        return new GwtWorkarounds(new StringBuilder(i));
    }
}
