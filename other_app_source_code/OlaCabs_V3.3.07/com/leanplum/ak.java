package com.leanplum;

import java.util.Map;

final class ak {
    private static ak f8748a;

    ak() {
    }

    public static synchronized ak m12732a() {
        ak akVar;
        synchronized (ak.class) {
            if (f8748a == null) {
                f8748a = new ak();
            }
            akVar = f8748a;
        }
        return akVar;
    }

    public static C0618S m12731a(String str, String str2, Map<String, String> map) {
        return new C0618S(str, str2, map);
    }
}
