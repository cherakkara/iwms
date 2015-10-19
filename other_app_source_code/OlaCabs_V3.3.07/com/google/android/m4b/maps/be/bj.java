package com.google.android.m4b.maps.be;

import android.graphics.Bitmap;
import android.os.Bundle;
import com.google.android.m4b.maps.cc.IObjectWrapper;
import com.google.android.m4b.maps.cc.ObjectWrapper;
import com.google.android.m4b.maps.model.internal.BitmapDescriptorParcelable;
import com.google.android.m4b.maps.model.internal.IBitmapDescriptorFactoryDelegate.IBitmapDescriptorFactoryDelegate;
import com.sothree.slidinguppanel.p086a.R.R;

/* compiled from: BitmapDescriptorFactoryDelegate */
public final class bj extends IBitmapDescriptorFactoryDelegate {
    public final IObjectWrapper m8875a() {
        return ObjectWrapper.m10130a(bk.m8886a());
    }

    public final IObjectWrapper m8876a(float f) {
        return ObjectWrapper.m10130a(bk.m8884a(f));
    }

    public final IObjectWrapper m8877a(int i) {
        return ObjectWrapper.m10130a(bk.m8887a(i));
    }

    public final IObjectWrapper m8880a(String str) {
        return ObjectWrapper.m10130a(bk.m8883a(str));
    }

    public final IObjectWrapper m8881b(String str) {
        return ObjectWrapper.m10130a(bk.m8890b(str));
    }

    public final IObjectWrapper m8878a(Bitmap bitmap) {
        return ObjectWrapper.m10130a(bk.m8885a(bitmap));
    }

    public final IObjectWrapper m8882c(String str) {
        return ObjectWrapper.m10130a(bk.m8892c(str));
    }

    public final IObjectWrapper m8879a(BitmapDescriptorParcelable bitmapDescriptorParcelable) {
        byte b = bitmapDescriptorParcelable.m10843b();
        Bundle c = bitmapDescriptorParcelable.m10844c();
        switch (b) {
            case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
            case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
            case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
            case R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
            case R.SlidingUpPanelLayout_umanoDragView /*5*/:
            case R.SlidingUpPanelLayout_umanoClipPanel /*7*/:
                return m8878a(bitmapDescriptorParcelable.m10845d());
            case R.SlidingUpPanelLayout_umanoOverlay /*6*/:
                return ObjectWrapper.m10130a(bk.m8891b(c.getInt("resourceId")));
            default:
                throw new RuntimeException("Unknown type of BitmapDescriptor: " + b);
        }
    }
}
