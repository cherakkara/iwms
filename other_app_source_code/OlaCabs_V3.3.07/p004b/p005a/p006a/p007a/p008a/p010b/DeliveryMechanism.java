package p004b.p005a.p006a.p007a.p008a.p010b;

/* renamed from: b.a.a.a.a.b.l */
public enum DeliveryMechanism {
    DEVELOPER(1),
    USER_SIDELOAD(2),
    TEST_DISTRIBUTION(3),
    APP_STORE(4);
    
    private final int f149e;

    private DeliveryMechanism(int i) {
        this.f149e = i;
    }

    public int m195a() {
        return this.f149e;
    }

    public String toString() {
        return Integer.toString(this.f149e);
    }

    public static DeliveryMechanism m194a(String str) {
        if ("io.crash.air".equals(str)) {
            return TEST_DISTRIBUTION;
        }
        if (str != null) {
            return APP_STORE;
        }
        return DEVELOPER;
    }
}
