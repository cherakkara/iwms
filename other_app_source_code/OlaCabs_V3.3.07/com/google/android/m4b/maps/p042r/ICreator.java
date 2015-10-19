package com.google.android.m4b.maps.p042r;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.m4b.maps.GoogleMapOptions;
import com.google.android.m4b.maps.StreetViewPanoramaOptions;
import com.google.android.m4b.maps.cc.IObjectWrapper;
import com.google.android.m4b.maps.model.internal.IBitmapDescriptorFactoryDelegate;
import com.sothree.slidinguppanel.p086a.R.R;

/* renamed from: com.google.android.m4b.maps.r.u */
public interface ICreator extends IInterface {

    /* renamed from: com.google.android.m4b.maps.r.u.a */
    public static abstract class ICreator extends Binder implements ICreator {
        public ICreator() {
            attachInterface(this, "com.google.android.m4b.maps.internal.ICreator");
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            IBinder iBinder = null;
            IObjectWrapper a;
            switch (i) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.ICreator");
                    m11192a(IObjectWrapper.IObjectWrapper.m10120a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.ICreator");
                    IMapFragmentDelegate b = m11195b(IObjectWrapper.IObjectWrapper.m10120a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    if (b != null) {
                        iBinder = b.asBinder();
                    }
                    parcel2.writeStrongBinder(iBinder);
                    return true;
                case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                    GoogleMapOptions a2;
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.ICreator");
                    a = IObjectWrapper.IObjectWrapper.m10120a(parcel.readStrongBinder());
                    if (parcel.readInt() != 0) {
                        a2 = GoogleMapOptions.CREATOR.m10233a(parcel);
                    } else {
                        a2 = null;
                    }
                    aa a3 = m11189a(a, a2);
                    parcel2.writeNoException();
                    if (a3 != null) {
                        iBinder = a3.asBinder();
                    }
                    parcel2.writeStrongBinder(iBinder);
                    return true;
                case R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.ICreator");
                    ICameraUpdateFactoryDelegate a4 = m11191a();
                    parcel2.writeNoException();
                    if (a4 != null) {
                        iBinder = a4.asBinder();
                    }
                    parcel2.writeStrongBinder(iBinder);
                    return true;
                case R.SlidingUpPanelLayout_umanoDragView /*5*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.ICreator");
                    IBitmapDescriptorFactoryDelegate b2 = m11194b();
                    parcel2.writeNoException();
                    if (b2 != null) {
                        iBinder = b2.asBinder();
                    }
                    parcel2.writeStrongBinder(iBinder);
                    return true;
                case R.SlidingUpPanelLayout_umanoOverlay /*6*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.ICreator");
                    m11193a(IObjectWrapper.IObjectWrapper.m10120a(parcel.readStrongBinder()), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case R.SlidingUpPanelLayout_umanoClipPanel /*7*/:
                    StreetViewPanoramaOptions a5;
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.ICreator");
                    a = IObjectWrapper.IObjectWrapper.m10120a(parcel.readStrongBinder());
                    if (parcel.readInt() != 0) {
                        a5 = StreetViewPanoramaOptions.CREATOR.m10514a(parcel);
                    } else {
                        a5 = null;
                    }
                    IStreetViewPanoramaViewDelegate a6 = m11190a(a, a5);
                    parcel2.writeNoException();
                    if (a6 != null) {
                        iBinder = a6.asBinder();
                    }
                    parcel2.writeStrongBinder(iBinder);
                    return true;
                case R.SlidingUpPanelLayout_umanoAnchorPoint /*8*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.ICreator");
                    IStreetViewPanoramaFragmentDelegate c = m11196c(IObjectWrapper.IObjectWrapper.m10120a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    if (c != null) {
                        iBinder = c.asBinder();
                    }
                    parcel2.writeStrongBinder(iBinder);
                    return true;
                case 1598968902:
                    parcel2.writeString("com.google.android.m4b.maps.internal.ICreator");
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    aa m11189a(IObjectWrapper iObjectWrapper, GoogleMapOptions googleMapOptions);

    IStreetViewPanoramaViewDelegate m11190a(IObjectWrapper iObjectWrapper, StreetViewPanoramaOptions streetViewPanoramaOptions);

    ICameraUpdateFactoryDelegate m11191a();

    void m11192a(IObjectWrapper iObjectWrapper);

    void m11193a(IObjectWrapper iObjectWrapper, int i);

    IBitmapDescriptorFactoryDelegate m11194b();

    IMapFragmentDelegate m11195b(IObjectWrapper iObjectWrapper);

    IStreetViewPanoramaFragmentDelegate m11196c(IObjectWrapper iObjectWrapper);
}
