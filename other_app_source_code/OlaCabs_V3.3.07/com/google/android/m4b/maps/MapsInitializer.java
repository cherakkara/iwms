package com.google.android.m4b.maps;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.m4b.maps.ca.GooglePlayServicesNotAvailableException;
import com.google.android.m4b.maps.model.BitmapDescriptorFactory;
import com.google.android.m4b.maps.model.RuntimeRemoteException;
import com.google.android.m4b.maps.p042r.ICreator;
import com.google.android.m4b.maps.p042r.MapsDynamicJar;
import com.google.android.m4b.maps.p047g.Preconditions;

/* renamed from: com.google.android.m4b.maps.f */
public final class MapsInitializer {
    public static int m10315a(Context context) {
        Preconditions.m10459a((Object) context);
        try {
            MapsInitializer.m10316a(MapsDynamicJar.m11182a(context));
            return 0;
        } catch (GooglePlayServicesNotAvailableException e) {
            return e.f7230a;
        }
    }

    public static void m10316a(ICreator iCreator) {
        try {
            CameraUpdateFactory.m7795a(iCreator.m11191a());
            BitmapDescriptorFactory.m10826a(iCreator.m11194b());
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }
}
