package com.olacabs.customer.p078c;

import android.location.Address;
import android.location.Geocoder;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.google.android.m4b.maps.model.LatLng;
import com.olacabs.customer.utils.Utils;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.olacabs.customer.c.b */
public class FetchAddress implements Runnable {
    private Geocoder f9403a;
    private LatLng f9404b;
    private String f9405c;
    private WeakReference<FetchAddress> f9406d;
    private String f9407e;

    /* renamed from: com.olacabs.customer.c.b.1 */
    class FetchAddress implements Runnable {
        final /* synthetic */ FetchAddress f9401a;
        final /* synthetic */ FetchAddress f9402b;

        FetchAddress(FetchAddress fetchAddress, FetchAddress fetchAddress2) {
            this.f9402b = fetchAddress;
            this.f9401a = fetchAddress2;
        }

        public void run() {
            this.f9401a.m13351a(this.f9402b.f9407e);
        }
    }

    /* renamed from: com.olacabs.customer.c.b.a */
    public interface FetchAddress {
        void m13351a(String str);
    }

    public FetchAddress(Geocoder geocoder, LatLng latLng, String str, WeakReference<FetchAddress> weakReference) {
        this.f9403a = geocoder;
        this.f9404b = latLng;
        this.f9405c = str;
        this.f9406d = weakReference;
        this.f9407e = str;
    }

    public void run() {
        List list = null;
        try {
            list = this.f9403a.getFromLocation(this.f9404b.f7554a, this.f9404b.f7555b, 1);
        } catch (IOException e) {
        } catch (IllegalArgumentException e2) {
        }
        if (list == null || list.size() <= 0) {
            try {
                this.f9407e = new FallBacks().m13350a(this.f9404b.f7554a + "," + this.f9404b.f7555b);
            } catch (Exception e3) {
                e3.printStackTrace();
            }
            if (TextUtils.isEmpty(this.f9407e)) {
                this.f9407e = this.f9405c;
            }
        } else {
            Address address = (Address) list.get(0);
            Iterable arrayList = new ArrayList();
            for (int i = 0; i <= address.getMaxAddressLineIndex(); i++) {
                arrayList.add(address.getAddressLine(i));
            }
            this.f9407e = TextUtils.join(", ", arrayList);
            if (TextUtils.isEmpty(this.f9407e)) {
                if (Utils.m14924g(address.getFeatureName())) {
                    this.f9407e = address.getFeatureName();
                } else if (Utils.m14924g(address.getAddressLine(0))) {
                    this.f9407e = address.getAddressLine(0);
                } else if (Utils.m14924g(address.getAdminArea())) {
                    this.f9407e = address.getAdminArea();
                } else {
                    this.f9407e = this.f9405c;
                }
            }
        }
        if (Utils.m14924g(this.f9407e) && this.f9406d != null) {
            FetchAddress fetchAddress = (FetchAddress) this.f9406d.get();
            if (fetchAddress != null) {
                new Handler(Looper.getMainLooper()).post(new FetchAddress(this, fetchAddress));
            }
        }
    }
}
