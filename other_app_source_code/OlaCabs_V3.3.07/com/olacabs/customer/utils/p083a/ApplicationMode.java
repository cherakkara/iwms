package com.olacabs.customer.utils.p083a;

/* renamed from: com.olacabs.customer.utils.a.a */
public enum ApplicationMode {
    DEV(0),
    MN(1),
    PROD(2),
    BETA_PROD(3),
    CLUSTER_PROD(4),
    QA(5);
    
    private int f11475g;

    private ApplicationMode(int i) {
        m14895a(i);
    }

    private void m14895a(int i) {
        this.f11475g = i;
    }
}
