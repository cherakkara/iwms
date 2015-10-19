package com.google.android.m4b.maps.bd;

/* renamed from: com.google.android.m4b.maps.bd.m */
public abstract class RenderBin {
    private Camera3D f5521a;

    final void m8248a(Camera3D camera3D) {
        if (this.f5521a != null) {
            throw new RuntimeException("setCamera can only be called once");
        }
        this.f5521a = camera3D;
    }
}
