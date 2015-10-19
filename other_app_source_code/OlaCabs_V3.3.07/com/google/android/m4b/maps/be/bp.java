package com.google.android.m4b.maps.be;

import com.google.android.m4b.maps.model.CameraPosition;
import com.google.android.m4b.maps.model.LatLng;
import com.google.android.m4b.maps.model.LatLngBounds;
import com.google.android.m4b.maps.p042r.ICancelableCallback;
import com.google.android.m4b.maps.p042r.ac;

/* compiled from: CameraManager */
public interface bp {
    public static final CameraPosition f5847a;

    /* renamed from: com.google.android.m4b.maps.be.bp.a */
    public interface CameraManager {
        void m8928a(bp bpVar, int i, be beVar);
    }

    float m8929a(LatLng latLng);

    CameraPosition m8930a(LatLngBounds latLngBounds);

    void m8931a();

    void m8932a(float f, float f2, int i);

    void m8933a(float f, int i);

    void m8934a(float f, int i, int i2, int i3);

    void m8935a(int i, int i2, int i3, int i4);

    void m8936a(CameraManager cameraManager, int i, ICancelableCallback iCancelableCallback, be beVar);

    void m8937a(CameraPosition cameraPosition, int i);

    void m8938a(LatLng latLng, float f, int i);

    void m8939a(LatLng latLng, int i);

    void m8940a(LatLngBounds latLngBounds, int i, int i2);

    void m8941a(LatLngBounds latLngBounds, int i, int i2, int i3, int i4);

    void m8942a(ac acVar);

    void m8943b(float f, int i);

    void m8944b(ac acVar);

    CameraPosition m8945c();

    void m8946c(float f, int i);

    void m8947c(ac acVar);

    float m8948d();

    static {
        f5847a = CameraPosition.m10710a(new LatLng(0.0d, 0.0d), 0.0f);
    }
}
