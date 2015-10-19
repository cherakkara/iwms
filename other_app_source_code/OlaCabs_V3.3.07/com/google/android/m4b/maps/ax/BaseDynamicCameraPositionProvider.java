package com.google.android.m4b.maps.ax;

/* renamed from: com.google.android.m4b.maps.ax.a */
public abstract class BaseDynamicCameraPositionProvider implements DynamicCameraPositionProvider {
    protected volatile CameraPosition f4649a;

    public BaseDynamicCameraPositionProvider(CameraPosition cameraPosition) {
        this.f4649a = cameraPosition;
    }

    public final CameraPosition m7294b() {
        return this.f4649a;
    }
}
