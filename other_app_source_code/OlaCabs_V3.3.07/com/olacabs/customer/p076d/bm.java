package com.olacabs.customer.p076d;

import android.location.Location;

/* compiled from: LocationEvent */
/* renamed from: com.olacabs.customer.d.bm */
public class bm {
    private Location mLoc;

    private bm() {
    }

    public bm(Location location) {
        this.mLoc = location;
    }

    public Location getLocation() {
        return this.mLoc;
    }
}
