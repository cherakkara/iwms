package com.google.android.m4b.maps.p053n;

import com.google.android.m4b.maps.p051l.IGoogleLocationManagerService;
import com.google.android.m4b.maps.p051l.ServiceProvider;
import java.util.Locale;

/* renamed from: com.google.android.m4b.maps.n.b */
public class PlacesClientImpl {
    private final Locale f7667a;

    static {
        PlacesClientImpl.class.getSimpleName();
    }

    public PlacesClientImpl(String str, ServiceProvider<IGoogleLocationManagerService> serviceProvider) {
        this.f7667a = Locale.getDefault();
        PlacesParams placesParams = new PlacesParams(str, this.f7667a);
    }
}
