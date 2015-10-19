package com.google.android.m4b.maps.be;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.google.android.m4b.maps.model.StreetViewPanoramaCamera;
import com.google.android.m4b.maps.model.StreetViewPanoramaLocation;

/* compiled from: StreetViewProblemReporter */
public class ax {
    private Context f5702a;

    static {
        ax.class.getSimpleName();
    }

    ax(Context context) {
        this.f5702a = context;
    }

    public final void m8746a(StreetViewPanoramaLocation streetViewPanoramaLocation, StreetViewPanoramaCamera streetViewPanoramaCamera) {
        float f = -1.0f * streetViewPanoramaCamera.f7606b;
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("http://cbk0.google.com/cbk?cb_client=an_mobile&output=report&panoid=" + streetViewPanoramaLocation.f7615c + "&" + String.format("&cbp=1,%f,,%f,%f", new Object[]{Float.valueOf(streetViewPanoramaCamera.f7607c), Float.valueOf(streetViewPanoramaCamera.f7605a), Float.valueOf(f)})));
        intent.setFlags(268435456);
        try {
            this.f5702a.startActivity(intent);
        } catch (ActivityNotFoundException e) {
        }
    }
}
