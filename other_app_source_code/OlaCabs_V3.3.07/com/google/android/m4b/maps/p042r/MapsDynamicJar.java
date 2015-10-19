package com.google.android.m4b.maps.p042r;

import android.content.Context;
import android.os.Build.VERSION;
import android.os.RemoteException;
import com.google.android.m4b.maps.ca.GooglePlayServicesNotAvailableException;
import com.google.android.m4b.maps.ca.GooglePlayServicesUtil;
import com.google.android.m4b.maps.cc.ObjectWrapper;
import com.google.android.m4b.maps.model.RuntimeRemoteException;
import com.google.android.m4b.maps.p047g.Preconditions;
import com.sothree.slidinguppanel.p086a.R.R;

/* renamed from: com.google.android.m4b.maps.r.n */
public class MapsDynamicJar {
    private static Context f7722a;
    private static ICreator f7723b;

    public static ICreator m11182a(Context context) {
        Preconditions.m10459a((Object) context);
        if (f7723b != null) {
            return f7723b;
        }
        int a = GooglePlayServicesUtil.m10078a(context);
        switch (a) {
            case R.SlidingUpPanelLayout_umanoPanelHeight /*0*/:
                MapsDynamicJar.class.getSimpleName();
                f7723b = (ICreator) MapsDynamicJar.m11184a(MapsDynamicJar.m11183a());
                try {
                    ICreator iCreator = f7723b;
                    if (f7722a == null) {
                        f7722a = context.getApplicationContext();
                    }
                    iCreator.m11193a(ObjectWrapper.m10130a(f7722a.getResources()), GooglePlayServicesUtil.f7231a);
                    return f7723b;
                } catch (RemoteException e) {
                    throw new RuntimeRemoteException(e);
                }
            default:
                throw new GooglePlayServicesNotAvailableException(a);
        }
    }

    private static Class<?> m11183a() {
        try {
            if (VERSION.SDK_INT < 15) {
                return Class.forName("com.google.android.m4b.maps.r.r");
            }
            return Class.forName("com.google.android.m4b.maps.r.q");
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    private static <T> T m11184a(Class<?> cls) {
        try {
            return cls.newInstance();
        } catch (InstantiationException e) {
            throw new IllegalStateException("Unable to instantiate the dynamic class " + cls.getName());
        } catch (IllegalAccessException e2) {
            throw new IllegalStateException("Unable to call the default constructor of " + cls.getName());
        }
    }
}
