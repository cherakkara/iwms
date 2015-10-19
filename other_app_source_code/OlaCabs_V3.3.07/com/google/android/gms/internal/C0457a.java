package com.google.android.gms.internal;

/* renamed from: com.google.android.gms.internal.a */
public abstract class C0457a<T> {
    private static final Object f2341c;
    private static C0462a f2342d;
    private static int f2343e;
    protected final String f2344a;
    protected final T f2345b;
    private T f2346f;

    /* renamed from: com.google.android.gms.internal.a.1 */
    static class C04581 extends C0457a<Boolean> {
        C04581(String str, Boolean bool) {
            super(str, bool);
        }

        protected /* synthetic */ Object m3885a(String str) {
            return m3886b(str);
        }

        protected Boolean m3886b(String str) {
            return C0457a.f2342d.m3893a(this.a, (Boolean) this.b);
        }
    }

    /* renamed from: com.google.android.gms.internal.a.2 */
    static class C04592 extends C0457a<Long> {
        C04592(String str, Long l) {
            super(str, l);
        }

        protected /* synthetic */ Object m3887a(String str) {
            return m3888b(str);
        }

        protected Long m3888b(String str) {
            return C0457a.f2342d.m3895a(this.a, (Long) this.b);
        }
    }

    /* renamed from: com.google.android.gms.internal.a.3 */
    static class C04603 extends C0457a<Integer> {
        C04603(String str, Integer num) {
            super(str, num);
        }

        protected /* synthetic */ Object m3889a(String str) {
            return m3890b(str);
        }

        protected Integer m3890b(String str) {
            return C0457a.f2342d.m3894a(this.a, (Integer) this.b);
        }
    }

    /* renamed from: com.google.android.gms.internal.a.4 */
    static class C04614 extends C0457a<String> {
        C04614(String str, String str2) {
            super(str, str2);
        }

        protected /* synthetic */ Object m3891a(String str) {
            return m3892b(str);
        }

        protected String m3892b(String str) {
            return C0457a.f2342d.m3896a(this.a, (String) this.b);
        }
    }

    /* renamed from: com.google.android.gms.internal.a.a */
    private interface C0462a {
        Boolean m3893a(String str, Boolean bool);

        Integer m3894a(String str, Integer num);

        Long m3895a(String str, Long l);

        String m3896a(String str, String str2);
    }

    static {
        f2341c = new Object();
        f2342d = null;
        f2343e = 0;
    }

    protected C0457a(String str, T t) {
        this.f2346f = null;
        this.f2344a = str;
        this.f2345b = t;
    }

    public static int m3876a() {
        return f2343e;
    }

    public static C0457a<Integer> m3877a(String str, Integer num) {
        return new C04603(str, num);
    }

    public static C0457a<Long> m3878a(String str, Long l) {
        return new C04592(str, l);
    }

    public static C0457a<String> m3879a(String str, String str2) {
        return new C04614(str, str2);
    }

    public static C0457a<Boolean> m3880a(String str, boolean z) {
        return new C04581(str, Boolean.valueOf(z));
    }

    public static boolean m3881b() {
        return f2342d != null;
    }

    protected abstract T m3883a(String str);

    public final T m3884c() {
        return this.f2346f != null ? this.f2346f : m3883a(this.f2344a);
    }
}
