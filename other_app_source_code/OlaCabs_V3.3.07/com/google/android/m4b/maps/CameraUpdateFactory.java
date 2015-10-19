package com.google.android.m4b.maps;

import android.os.RemoteException;
import com.google.android.m4b.maps.model.CameraPosition;
import com.google.android.m4b.maps.model.LatLngBounds;
import com.google.android.m4b.maps.model.RuntimeRemoteException;
import com.google.android.m4b.maps.p042r.ICameraUpdateFactoryDelegate;
import com.google.android.m4b.maps.p047g.Preconditions;

/* renamed from: com.google.android.m4b.maps.b */
public final class CameraUpdateFactory {
    private static ICameraUpdateFactoryDelegate f5031a;

    private static ICameraUpdateFactoryDelegate m7794a() {
        return (ICameraUpdateFactoryDelegate) Preconditions.m10460a(f5031a, (Object) "CameraUpdateFactory is not initialized");
    }

    public static void m7795a(ICameraUpdateFactoryDelegate iCameraUpdateFactoryDelegate) {
        f5031a = (ICameraUpdateFactoryDelegate) Preconditions.m10459a((Object) iCameraUpdateFactoryDelegate);
    }

    public static CameraUpdate m7791a(CameraPosition cameraPosition) {
        try {
            return new CameraUpdate(CameraUpdateFactory.m7794a().m8964a(cameraPosition));
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public static CameraUpdate m7792a(LatLngBounds latLngBounds, int i) {
        try {
            return new CameraUpdate(CameraUpdateFactory.m7794a().m8967a(latLngBounds, i));
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public static CameraUpdate m7793a(LatLngBounds latLngBounds, int i, int i2, int i3) {
        try {
            return new CameraUpdate(CameraUpdateFactory.m7794a().m8968a(latLngBounds, i, i2, i3));
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }
}
