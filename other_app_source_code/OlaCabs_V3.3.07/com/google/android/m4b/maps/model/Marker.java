package com.google.android.m4b.maps.model;

import android.os.RemoteException;
import com.google.android.m4b.maps.model.internal.IMarkerDelegate;
import com.google.android.m4b.maps.p047g.Preconditions;

/* renamed from: com.google.android.m4b.maps.model.o */
public final class Marker {
    private final IMarkerDelegate f7665a;

    public Marker(IMarkerDelegate iMarkerDelegate) {
        this.f7665a = (IMarkerDelegate) Preconditions.m10459a((Object) iMarkerDelegate);
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof Marker)) {
            return false;
        }
        try {
            return this.f7665a.m8324a(((Marker) obj).f7665a);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final int hashCode() {
        try {
            return this.f7665a.m8315F();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }
}
