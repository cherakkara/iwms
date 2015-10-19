package com.google.p025a.p027b;

/* renamed from: com.google.a.b.j */
public enum RemovalCause {
    EXPLICIT {
        boolean m2273a() {
            return false;
        }
    },
    REPLACED {
        boolean m2274a() {
            return false;
        }
    },
    COLLECTED {
        boolean m2275a() {
            return true;
        }
    },
    EXPIRED {
        boolean m2276a() {
            return true;
        }
    },
    SIZE {
        boolean m2277a() {
            return true;
        }
    };

    abstract boolean m2272a();
}
