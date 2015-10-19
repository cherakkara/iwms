package com.google.android.m4b.maps.p061y;

import com.google.android.m4b.maps.an.Point;
import com.google.android.m4b.maps.av.RepaintCallback;
import com.google.android.m4b.maps.av.ad;
import com.google.android.m4b.maps.ax.Camera;
import com.google.android.m4b.maps.ay.GLState;

/* renamed from: com.google.android.m4b.maps.y.m */
interface Overlay {
    void m11680a(long j);

    void m11681a(Camera camera, GLState gLState);

    void m11682a(GLState gLState);

    void m11683a(GLState gLState, RepaintCallback repaintCallback);

    void m11684a(GLState gLState, Camera camera, ad adVar);

    void m11685a(boolean z);

    boolean m11686a(float f, float f2, Point point, Camera camera);

    void m11687b();

    void m11688b(int i);

    boolean m11689c();

    void m11690d();

    String m11691e();

    float m11692f();
}
