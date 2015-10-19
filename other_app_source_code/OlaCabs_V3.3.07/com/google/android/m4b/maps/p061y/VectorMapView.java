package com.google.android.m4b.maps.p061y;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import com.google.android.m4b.maps.av.BubbleBlower;
import com.google.android.m4b.maps.av.BubbleView;
import com.google.android.m4b.maps.av.MyLocationOverlay;
import com.google.android.m4b.maps.av.RenderInstrumentation;
import com.google.android.m4b.maps.av.ai;
import com.google.android.m4b.maps.av.ak;
import com.google.android.m4b.maps.av.al;
import com.google.android.m4b.maps.av.al.GLOverlay;
import com.google.android.m4b.maps.ax.Camera;
import com.google.android.m4b.maps.be.MapRenderer;

/* renamed from: com.google.android.m4b.maps.y.w */
public interface VectorMapView extends MapRenderer {

    /* renamed from: com.google.android.m4b.maps.y.w.a */
    public interface VectorMapView {
        boolean m11780f();
    }

    Bitmap m11860a(Bitmap bitmap);

    ak m11861a(GLOverlay gLOverlay);

    void m11862a(al alVar);

    void m11863a(RenderInstrumentation renderInstrumentation);

    void m11864a(BubbleBlower bubbleBlower, BubbleView bubbleView);

    void m11865a(VectorMapView vectorMapView);

    void m11866a(boolean z, boolean z2);

    void m11867b(al alVar);

    MyLocationOverlay m11868d(boolean z);

    void m11869d();

    ai m11870e();

    Context getContext();

    int getHeight();

    Resources getResources();

    int getWidth();

    void m11871s();

    Camera m11872t();
}
