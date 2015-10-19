package com.google.android.m4b.maps.model;

import android.graphics.Bitmap;
import android.os.RemoteException;
import com.google.android.m4b.maps.model.internal.IBitmapDescriptorFactoryDelegate;
import com.google.android.m4b.maps.p047g.Preconditions;

/* renamed from: com.google.android.m4b.maps.model.b */
public final class BitmapDescriptorFactory {
    private static IBitmapDescriptorFactoryDelegate f7642a;

    private static IBitmapDescriptorFactoryDelegate m10825a() {
        return (IBitmapDescriptorFactoryDelegate) Preconditions.m10460a(f7642a, (Object) "IBitmapDescriptorFactory is not initialized");
    }

    public static void m10826a(IBitmapDescriptorFactoryDelegate iBitmapDescriptorFactoryDelegate) {
        if (f7642a == null) {
            f7642a = (IBitmapDescriptorFactoryDelegate) Preconditions.m10459a((Object) iBitmapDescriptorFactoryDelegate);
        }
    }

    public static BitmapDescriptor m10823a(int i) {
        try {
            return new BitmapDescriptor(BitmapDescriptorFactory.m10825a().m8870a(i));
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public static BitmapDescriptor m10824a(Bitmap bitmap) {
        try {
            return new BitmapDescriptor(BitmapDescriptorFactory.m10825a().m8871a(bitmap));
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }
}
