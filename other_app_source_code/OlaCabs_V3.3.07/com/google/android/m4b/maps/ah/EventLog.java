package com.google.android.m4b.maps.ah;

import com.google.android.m4b.maps.p040u.DataRequest;

/* renamed from: com.google.android.m4b.maps.ah.a */
public abstract class EventLog {
    private static EventLog f3099a;

    /* renamed from: com.google.android.m4b.maps.ah.a.b */
    public static abstract class EventLog {
        private long f3091a;
        private long f3092b;

        public EventLog() {
            this.f3091a = System.currentTimeMillis();
            this.f3092b = -1;
        }
    }

    /* renamed from: com.google.android.m4b.maps.ah.a.a */
    public static class EventLog extends EventLog {
        private final String f3093a;
        private int f3094b;
        private int f3095c;

        public EventLog(String str, DataRequest dataRequest) {
            this.f3093a = str;
            if (dataRequest != null) {
                this.f3094b = dataRequest.m4778i();
                this.f3095c = dataRequest.hashCode();
            }
        }
    }

    /* renamed from: com.google.android.m4b.maps.ah.a.c */
    public static class EventLog extends EventLog {
        private final String f3096a;
        private final String f3097b;

        public EventLog(String str, String str2) {
            this.f3096a = str;
            this.f3097b = str2;
        }
    }

    /* renamed from: com.google.android.m4b.maps.ah.a.d */
    public static class EventLog extends EventLog {
        private final Throwable f3098a;

        public EventLog(Throwable th) {
            this.f3098a = th;
        }
    }

    public abstract void m4912a(EventLog eventLog);

    public static void m4911b(EventLog eventLog) {
        EventLog eventLog2 = f3099a;
        if (eventLog2 != null) {
            eventLog2.m4912a(eventLog);
        }
    }
}
