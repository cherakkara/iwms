package com.google.android.m4b.maps.p061y;

import android.graphics.Color;
import android.support.v4.view.MotionEventCompat;
import com.google.android.m4b.maps.an.Point;
import com.google.android.m4b.maps.an.Rectangle2D;
import com.google.android.m4b.maps.an.as;
import com.google.android.m4b.maps.be.ay;
import com.google.android.m4b.maps.bj.PanoramaLink;
import com.google.android.m4b.maps.model.CameraPosition;
import com.google.android.m4b.maps.model.CameraPosition.C0594a;
import com.google.android.m4b.maps.model.LatLng;
import com.google.android.m4b.maps.model.LatLngBounds;
import com.google.android.m4b.maps.model.StreetViewPanoramaCamera;
import com.google.android.m4b.maps.model.StreetViewPanoramaLink;
import com.google.android.m4b.maps.p057t.GeoPoint;

/* renamed from: com.google.android.m4b.maps.y.b */
public final class ConversionUtils {
    public static GeoPoint m11637a(LatLng latLng) {
        return new GeoPoint(ConversionUtils.m11630a(latLng.f7554a), ConversionUtils.m11630a(latLng.f7555b));
    }

    public static Point m11640b(LatLng latLng) {
        return Point.m5923a(latLng.f7554a, latLng.f7555b);
    }

    public static LatLng m11635a(Point point) {
        return new LatLng(point.m5949b(), point.m5953d());
    }

    public static CameraPosition m11634a(com.google.android.m4b.maps.ax.CameraPosition cameraPosition) {
        return new C0594a().m10704a(ConversionUtils.m11635a(cameraPosition.m7460c())).m10703a(cameraPosition.m7457a()).m10707c(cameraPosition.m7462e()).m10706b(cameraPosition.m7461d()).m10705a();
    }

    public static int m11630a(double d) {
        return (int) Math.round(1000000.0d * d);
    }

    public static int m11639b(double d) {
        return (int) Math.round(1.0E7d * d);
    }

    public static as m11632a(LatLngBounds latLngBounds) {
        if (latLngBounds.f7562b.f7555b >= latLngBounds.f7561a.f7555b) {
            return as.m5674a(new Rectangle2D(ConversionUtils.m11640b(latLngBounds.f7561a), ConversionUtils.m11640b(latLngBounds.f7562b)));
        }
        return as.m5674a(new Rectangle2D(ConversionUtils.m11640b(latLngBounds.f7561a), ConversionUtils.m11640b(latLngBounds.f7562b).m5957e(new Point(1073741824, 0))));
    }

    public static int m11631a(int i) {
        int alpha = Color.alpha(i);
        return Color.argb(alpha, (Color.red(i) * alpha) / MotionEventCompat.ACTION_MASK, (Color.green(i) * alpha) / MotionEventCompat.ACTION_MASK, (Color.blue(i) * alpha) / MotionEventCompat.ACTION_MASK);
    }

    public static ay m11633a(StreetViewPanoramaCamera streetViewPanoramaCamera) {
        return new ay(streetViewPanoramaCamera.f7607c, (streetViewPanoramaCamera.f7606b / 180.0f) + 0.5f, streetViewPanoramaCamera.f7605a);
    }

    public static StreetViewPanoramaCamera m11636a(ay ayVar) {
        if (ayVar == null) {
            return null;
        }
        return new StreetViewPanoramaCamera(ayVar.m8755e(), ayVar.m8754d(), ayVar.m8750b());
    }

    public static StreetViewPanoramaLink[] m11638a(PanoramaLink[] panoramaLinkArr) {
        if (panoramaLinkArr == null) {
            return null;
        }
        StreetViewPanoramaLink[] streetViewPanoramaLinkArr = new StreetViewPanoramaLink[panoramaLinkArr.length];
        for (int i = 0; i < panoramaLinkArr.length; i++) {
            streetViewPanoramaLinkArr[i] = new StreetViewPanoramaLink(panoramaLinkArr[i].f6623c, panoramaLinkArr[i].f6621a);
        }
        return streetViewPanoramaLinkArr;
    }
}
