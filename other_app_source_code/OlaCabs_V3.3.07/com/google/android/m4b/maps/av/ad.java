package com.google.android.m4b.maps.av;

/* compiled from: DrawState */
public class ad {
    private ac f4135a;
    private int f4136b;
    private RenderPass f4137c;

    public ad(ac acVar, int i) {
        this.f4135a = acVar;
        this.f4136b = i;
    }

    public ad(ad adVar) {
        this(adVar.m6701a(), adVar.m6704b());
        m6703a(adVar.m6705c());
    }

    public ac m6701a() {
        return this.f4135a;
    }

    public int m6704b() {
        return this.f4136b;
    }

    public void m6702a(int i) {
        this.f4136b = i;
    }

    public RenderPass m6705c() {
        return this.f4137c;
    }

    public void m6703a(RenderPass renderPass) {
        this.f4137c = renderPass;
    }
}
