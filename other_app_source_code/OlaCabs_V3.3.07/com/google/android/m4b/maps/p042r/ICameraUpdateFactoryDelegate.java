package com.google.android.m4b.maps.p042r;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.m4b.maps.cc.IObjectWrapper;
import com.google.android.m4b.maps.model.CameraPosition;
import com.google.android.m4b.maps.model.LatLng;
import com.google.android.m4b.maps.model.LatLngBounds;
import com.sothree.slidinguppanel.p086a.R.R;
import org.apache.http.protocol.HTTP;

/* renamed from: com.google.android.m4b.maps.r.s */
public interface ICameraUpdateFactoryDelegate extends IInterface {

    /* renamed from: com.google.android.m4b.maps.r.s.a */
    public static abstract class ICameraUpdateFactoryDelegate extends Binder implements ICameraUpdateFactoryDelegate {
        public ICameraUpdateFactoryDelegate() {
            attachInterface(this, "com.google.android.m4b.maps.internal.ICameraUpdateFactoryDelegate");
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            IBinder iBinder = null;
            IObjectWrapper a;
            LatLng a2;
            LatLngBounds a3;
            switch (i) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.ICameraUpdateFactoryDelegate");
                    a = m8960a();
                    parcel2.writeNoException();
                    if (a != null) {
                        iBinder = a.asBinder();
                    }
                    parcel2.writeStrongBinder(iBinder);
                    return true;
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.ICameraUpdateFactoryDelegate");
                    a = m8969b();
                    parcel2.writeNoException();
                    if (a != null) {
                        iBinder = a.asBinder();
                    }
                    parcel2.writeStrongBinder(iBinder);
                    return true;
                case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.ICameraUpdateFactoryDelegate");
                    a = m8962a(parcel.readFloat(), parcel.readFloat());
                    parcel2.writeNoException();
                    if (a != null) {
                        iBinder = a.asBinder();
                    }
                    parcel2.writeStrongBinder(iBinder);
                    return true;
                case R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.ICameraUpdateFactoryDelegate");
                    a = m8961a(parcel.readFloat());
                    parcel2.writeNoException();
                    if (a != null) {
                        iBinder = a.asBinder();
                    }
                    parcel2.writeStrongBinder(iBinder);
                    return true;
                case R.SlidingUpPanelLayout_umanoDragView /*5*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.ICameraUpdateFactoryDelegate");
                    a = m8970b(parcel.readFloat());
                    parcel2.writeNoException();
                    if (a != null) {
                        iBinder = a.asBinder();
                    }
                    parcel2.writeStrongBinder(iBinder);
                    return true;
                case R.SlidingUpPanelLayout_umanoOverlay /*6*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.ICameraUpdateFactoryDelegate");
                    a = m8963a(parcel.readFloat(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    if (a != null) {
                        iBinder = a.asBinder();
                    }
                    parcel2.writeStrongBinder(iBinder);
                    return true;
                case R.SlidingUpPanelLayout_umanoClipPanel /*7*/:
                    CameraPosition a4;
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.ICameraUpdateFactoryDelegate");
                    if (parcel.readInt() != 0) {
                        a4 = CameraPosition.CREATOR.m10828a(parcel);
                    } else {
                        a4 = null;
                    }
                    a = m8964a(a4);
                    parcel2.writeNoException();
                    if (a != null) {
                        iBinder = a.asBinder();
                    }
                    parcel2.writeStrongBinder(iBinder);
                    return true;
                case R.SlidingUpPanelLayout_umanoAnchorPoint /*8*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.ICameraUpdateFactoryDelegate");
                    if (parcel.readInt() != 0) {
                        a2 = LatLng.CREATOR.m11012a(parcel);
                    } else {
                        a2 = null;
                    }
                    a = m8965a(a2);
                    parcel2.writeNoException();
                    if (a != null) {
                        iBinder = a.asBinder();
                    }
                    parcel2.writeStrongBinder(iBinder);
                    return true;
                case HTTP.HT /*9*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.ICameraUpdateFactoryDelegate");
                    if (parcel.readInt() != 0) {
                        a2 = LatLng.CREATOR.m11012a(parcel);
                    } else {
                        a2 = null;
                    }
                    a = m8966a(a2, parcel.readFloat());
                    parcel2.writeNoException();
                    if (a != null) {
                        iBinder = a.asBinder();
                    }
                    parcel2.writeStrongBinder(iBinder);
                    return true;
                case HTTP.LF /*10*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.ICameraUpdateFactoryDelegate");
                    if (parcel.readInt() != 0) {
                        a3 = LatLngBounds.CREATOR.m10840a(parcel);
                    } else {
                        a3 = null;
                    }
                    a = m8967a(a3, parcel.readInt());
                    parcel2.writeNoException();
                    if (a != null) {
                        iBinder = a.asBinder();
                    }
                    parcel2.writeStrongBinder(iBinder);
                    return true;
                case com.olacabs.customer.R.R.MapM4bAttrs_m4b_uiZoomControls /*11*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.ICameraUpdateFactoryDelegate");
                    if (parcel.readInt() != 0) {
                        a3 = LatLngBounds.CREATOR.m10840a(parcel);
                    } else {
                        a3 = null;
                    }
                    a = m8968a(a3, parcel.readInt(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    if (a != null) {
                        iBinder = a.asBinder();
                    }
                    parcel2.writeStrongBinder(iBinder);
                    return true;
                case 1598968902:
                    parcel2.writeString("com.google.android.m4b.maps.internal.ICameraUpdateFactoryDelegate");
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    IObjectWrapper m8960a();

    IObjectWrapper m8961a(float f);

    IObjectWrapper m8962a(float f, float f2);

    IObjectWrapper m8963a(float f, int i, int i2);

    IObjectWrapper m8964a(CameraPosition cameraPosition);

    IObjectWrapper m8965a(LatLng latLng);

    IObjectWrapper m8966a(LatLng latLng, float f);

    IObjectWrapper m8967a(LatLngBounds latLngBounds, int i);

    IObjectWrapper m8968a(LatLngBounds latLngBounds, int i, int i2, int i3);

    IObjectWrapper m8969b();

    IObjectWrapper m8970b(float f);
}
