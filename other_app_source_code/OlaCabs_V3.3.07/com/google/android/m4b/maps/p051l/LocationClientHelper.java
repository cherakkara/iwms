package com.google.android.m4b.maps.p051l;

import android.content.Context;
import android.location.Location;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.google.android.m4b.maps.p044k.ILocationListener.ILocationListener;
import com.google.android.m4b.maps.p044k.LocationListener;
import com.google.android.m4b.maps.p044k.LocationRequest;
import com.google.android.m4b.maps.p047g.Preconditions;
import com.sothree.slidinguppanel.p086a.R.R;
import java.util.HashMap;

/* renamed from: com.google.android.m4b.maps.l.d */
public final class LocationClientHelper {
    private final ServiceProvider<IGoogleLocationManagerService> f7471a;
    private final Context f7472b;
    private boolean f7473c;
    private HashMap<LocationListener, LocationClientHelper> f7474d;

    /* renamed from: com.google.android.m4b.maps.l.d.a */
    static class LocationClientHelper extends Handler {
        private final LocationListener f7469a;

        public LocationClientHelper(LocationListener locationListener) {
            this.f7469a = locationListener;
        }

        public LocationClientHelper(LocationListener locationListener, Looper looper) {
            super(looper);
            this.f7469a = locationListener;
        }

        public final void handleMessage(Message message) {
            switch (message.what) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    this.f7469a.m9433a(new Location((Location) message.obj));
                default:
            }
        }
    }

    /* renamed from: com.google.android.m4b.maps.l.d.b */
    static class LocationClientHelper extends ILocationListener {
        private Handler f7470a;

        LocationClientHelper(LocationListener locationListener, Looper looper) {
            this.f7470a = looper == null ? new LocationClientHelper(locationListener) : new LocationClientHelper(locationListener, looper);
        }

        public final void m10645a(Location location) {
            if (this.f7470a != null) {
                Message obtain = Message.obtain();
                obtain.what = 1;
                obtain.obj = location;
                this.f7470a.sendMessage(obtain);
            }
        }
    }

    public LocationClientHelper(Context context, ServiceProvider<IGoogleLocationManagerService> serviceProvider) {
        this.f7473c = false;
        this.f7474d = new HashMap();
        this.f7472b = context;
        this.f7471a = serviceProvider;
    }

    public final void m10647a(LocationRequest locationRequest, LocationListener locationListener, Looper looper) {
        this.f7471a.m10649a();
        if (looper == null) {
            Preconditions.m10460a(Looper.myLooper(), (Object) "Can't create handler inside thread that has not called Looper.prepare()");
        }
        synchronized (this.f7474d) {
            com.google.android.m4b.maps.p044k.ILocationListener locationClientHelper;
            LocationClientHelper locationClientHelper2 = (LocationClientHelper) this.f7474d.get(locationListener);
            if (locationClientHelper2 == null) {
                locationClientHelper = new LocationClientHelper(locationListener, looper);
            } else {
                Object obj = locationClientHelper2;
            }
            this.f7474d.put(locationListener, locationClientHelper);
            ((IGoogleLocationManagerService) this.f7471a.m10650b()).m10590a(locationRequest, locationClientHelper, this.f7472b.getPackageName());
        }
    }

    public final void m10646a() {
        try {
            synchronized (this.f7474d) {
                for (com.google.android.m4b.maps.p044k.ILocationListener iLocationListener : this.f7474d.values()) {
                    if (iLocationListener != null) {
                        ((IGoogleLocationManagerService) this.f7471a.m10650b()).m10587a(iLocationListener);
                    }
                }
                this.f7474d.clear();
            }
        } catch (Throwable e) {
            throw new IllegalStateException(e);
        }
    }

    public final void m10648b() {
        if (this.f7473c) {
            try {
                this.f7471a.m10649a();
                ((IGoogleLocationManagerService) this.f7471a.m10650b()).m10605a(false);
                this.f7473c = false;
            } catch (Throwable e) {
                throw new IllegalStateException(e);
            }
        }
    }
}
