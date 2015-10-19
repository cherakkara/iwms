package com.google.android.m4b.maps.p042r;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.m4b.maps.GoogleMapOptions;
import com.google.android.m4b.maps.cc.IObjectWrapper;
import com.google.android.m4b.maps.p042r.aj.IOnMapReadyCallback;
import com.sothree.slidinguppanel.p086a.R.R;
import org.apache.http.protocol.HTTP;

/* renamed from: com.google.android.m4b.maps.r.z */
public interface IMapFragmentDelegate extends IInterface {

    /* renamed from: com.google.android.m4b.maps.r.z.a */
    public static abstract class IMapFragmentDelegate extends Binder implements IMapFragmentDelegate {
        public IMapFragmentDelegate() {
            attachInterface(this, "com.google.android.m4b.maps.internal.IMapFragmentDelegate");
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            IBinder iBinder = null;
            IObjectWrapper a;
            Bundle bundle;
            switch (i) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IMapFragmentDelegate");
                    IGoogleMapDelegate a2 = m9448a();
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(a2 != null ? a2.asBinder() : null);
                    return true;
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    GoogleMapOptions a3;
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IMapFragmentDelegate");
                    a = IObjectWrapper.IObjectWrapper.m10120a(parcel.readStrongBinder());
                    if (parcel.readInt() != 0) {
                        a3 = GoogleMapOptions.CREATOR.m10233a(parcel);
                    } else {
                        a3 = null;
                    }
                    if (parcel.readInt() != 0) {
                        bundle = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                    } else {
                        bundle = null;
                    }
                    m9450a(a, a3, bundle);
                    parcel2.writeNoException();
                    return true;
                case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IMapFragmentDelegate");
                    if (parcel.readInt() != 0) {
                        bundle = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                    } else {
                        bundle = null;
                    }
                    m9449a(bundle);
                    parcel2.writeNoException();
                    return true;
                case R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IMapFragmentDelegate");
                    IObjectWrapper a4 = IObjectWrapper.IObjectWrapper.m10120a(parcel.readStrongBinder());
                    a = IObjectWrapper.IObjectWrapper.m10120a(parcel.readStrongBinder());
                    if (parcel.readInt() != 0) {
                        bundle = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                    } else {
                        bundle = null;
                    }
                    IObjectWrapper a5 = m9447a(a4, a, bundle);
                    parcel2.writeNoException();
                    if (a5 != null) {
                        iBinder = a5.asBinder();
                    }
                    parcel2.writeStrongBinder(iBinder);
                    return true;
                case R.SlidingUpPanelLayout_umanoDragView /*5*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IMapFragmentDelegate");
                    m9452b();
                    parcel2.writeNoException();
                    return true;
                case R.SlidingUpPanelLayout_umanoOverlay /*6*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IMapFragmentDelegate");
                    m9454c();
                    parcel2.writeNoException();
                    return true;
                case R.SlidingUpPanelLayout_umanoClipPanel /*7*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IMapFragmentDelegate");
                    m9455d();
                    parcel2.writeNoException();
                    return true;
                case R.SlidingUpPanelLayout_umanoAnchorPoint /*8*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IMapFragmentDelegate");
                    m9456e();
                    parcel2.writeNoException();
                    return true;
                case HTTP.HT /*9*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IMapFragmentDelegate");
                    m9457f();
                    parcel2.writeNoException();
                    return true;
                case HTTP.LF /*10*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IMapFragmentDelegate");
                    if (parcel.readInt() != 0) {
                        bundle = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                    } else {
                        bundle = null;
                    }
                    m9453b(bundle);
                    parcel2.writeNoException();
                    if (bundle != null) {
                        parcel2.writeInt(1);
                        bundle.writeToParcel(parcel2, 1);
                        return true;
                    }
                    parcel2.writeInt(0);
                    return true;
                case com.olacabs.customer.R.R.MapM4bAttrs_m4b_uiZoomControls /*11*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IMapFragmentDelegate");
                    boolean g = m9458g();
                    parcel2.writeNoException();
                    parcel2.writeInt(g ? 1 : 0);
                    return true;
                case com.olacabs.customer.R.R.MapM4bAttrs_m4b_uiZoomGestures /*12*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IMapFragmentDelegate");
                    m9451a(IOnMapReadyCallback.m10517a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 1598968902:
                    parcel2.writeString("com.google.android.m4b.maps.internal.IMapFragmentDelegate");
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    IObjectWrapper m9447a(IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2, Bundle bundle);

    IGoogleMapDelegate m9448a();

    void m9449a(Bundle bundle);

    void m9450a(IObjectWrapper iObjectWrapper, GoogleMapOptions googleMapOptions, Bundle bundle);

    void m9451a(aj ajVar);

    void m9452b();

    void m9453b(Bundle bundle);

    void m9454c();

    void m9455d();

    void m9456e();

    void m9457f();

    boolean m9458g();
}
