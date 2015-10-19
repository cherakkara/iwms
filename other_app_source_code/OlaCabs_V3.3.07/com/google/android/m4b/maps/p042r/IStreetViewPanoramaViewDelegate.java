package com.google.android.m4b.maps.p042r;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.m4b.maps.cc.IObjectWrapper;
import com.google.android.m4b.maps.p042r.IOnStreetViewPanoramaReadyCallback.IOnStreetViewPanoramaReadyCallback;
import com.sothree.slidinguppanel.p086a.R.R;
import org.apache.http.protocol.HTTP;

/* renamed from: com.google.android.m4b.maps.r.j */
public interface IStreetViewPanoramaViewDelegate extends IInterface {

    /* renamed from: com.google.android.m4b.maps.r.j.a */
    public static abstract class IStreetViewPanoramaViewDelegate extends Binder implements IStreetViewPanoramaViewDelegate {
        public IStreetViewPanoramaViewDelegate() {
            attachInterface(this, "com.google.android.m4b.maps.internal.IStreetViewPanoramaViewDelegate");
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            IBinder iBinder = null;
            Bundle bundle;
            switch (i) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IStreetViewPanoramaViewDelegate");
                    IStreetViewPanoramaDelegate a = m8727a();
                    parcel2.writeNoException();
                    if (a != null) {
                        iBinder = a.asBinder();
                    }
                    parcel2.writeStrongBinder(iBinder);
                    return true;
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IStreetViewPanoramaViewDelegate");
                    if (parcel.readInt() != 0) {
                        bundle = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                    }
                    m8728a(bundle);
                    parcel2.writeNoException();
                    return true;
                case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IStreetViewPanoramaViewDelegate");
                    m8732c();
                    parcel2.writeNoException();
                    return true;
                case R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IStreetViewPanoramaViewDelegate");
                    m8733d();
                    parcel2.writeNoException();
                    return true;
                case R.SlidingUpPanelLayout_umanoDragView /*5*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IStreetViewPanoramaViewDelegate");
                    m8730b();
                    parcel2.writeNoException();
                    return true;
                case R.SlidingUpPanelLayout_umanoOverlay /*6*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IStreetViewPanoramaViewDelegate");
                    m8734e();
                    parcel2.writeNoException();
                    return true;
                case R.SlidingUpPanelLayout_umanoClipPanel /*7*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IStreetViewPanoramaViewDelegate");
                    if (parcel.readInt() != 0) {
                        bundle = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                    }
                    m8731b(bundle);
                    parcel2.writeNoException();
                    if (bundle != null) {
                        parcel2.writeInt(1);
                        bundle.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case R.SlidingUpPanelLayout_umanoAnchorPoint /*8*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IStreetViewPanoramaViewDelegate");
                    IObjectWrapper f = m8735f();
                    parcel2.writeNoException();
                    if (f != null) {
                        iBinder = f.asBinder();
                    }
                    parcel2.writeStrongBinder(iBinder);
                    return true;
                case HTTP.HT /*9*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IStreetViewPanoramaViewDelegate");
                    m8729a(IOnStreetViewPanoramaReadyCallback.m11132a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 1598968902:
                    parcel2.writeString("com.google.android.m4b.maps.internal.IStreetViewPanoramaViewDelegate");
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    IStreetViewPanoramaDelegate m8727a();

    void m8728a(Bundle bundle);

    void m8729a(IOnStreetViewPanoramaReadyCallback iOnStreetViewPanoramaReadyCallback);

    void m8730b();

    void m8731b(Bundle bundle);

    void m8732c();

    void m8733d();

    void m8734e();

    IObjectWrapper m8735f();
}
