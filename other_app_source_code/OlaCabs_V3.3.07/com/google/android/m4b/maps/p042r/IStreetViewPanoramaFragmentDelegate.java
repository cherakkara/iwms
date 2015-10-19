package com.google.android.m4b.maps.p042r;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.m4b.maps.StreetViewPanoramaOptions;
import com.google.android.m4b.maps.cc.IObjectWrapper;
import com.google.android.m4b.maps.p042r.IOnStreetViewPanoramaReadyCallback.IOnStreetViewPanoramaReadyCallback;
import com.sothree.slidinguppanel.p086a.R.R;
import org.apache.http.protocol.HTTP;

/* renamed from: com.google.android.m4b.maps.r.i */
public interface IStreetViewPanoramaFragmentDelegate extends IInterface {

    /* renamed from: com.google.android.m4b.maps.r.i.a */
    public static abstract class IStreetViewPanoramaFragmentDelegate extends Binder implements IStreetViewPanoramaFragmentDelegate {
        public IStreetViewPanoramaFragmentDelegate() {
            attachInterface(this, "com.google.android.m4b.maps.internal.IStreetViewPanoramaFragmentDelegate");
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
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IStreetViewPanoramaFragmentDelegate");
                    IStreetViewPanoramaDelegate a2 = m8619a();
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(a2 != null ? a2.asBinder() : null);
                    return true;
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    StreetViewPanoramaOptions a3;
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IStreetViewPanoramaFragmentDelegate");
                    a = IObjectWrapper.IObjectWrapper.m10120a(parcel.readStrongBinder());
                    if (parcel.readInt() != 0) {
                        a3 = StreetViewPanoramaOptions.CREATOR.m10514a(parcel);
                    } else {
                        a3 = null;
                    }
                    if (parcel.readInt() != 0) {
                        bundle = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                    } else {
                        bundle = null;
                    }
                    m8621a(a, a3, bundle);
                    parcel2.writeNoException();
                    return true;
                case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IStreetViewPanoramaFragmentDelegate");
                    if (parcel.readInt() != 0) {
                        bundle = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                    } else {
                        bundle = null;
                    }
                    m8620a(bundle);
                    parcel2.writeNoException();
                    return true;
                case R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IStreetViewPanoramaFragmentDelegate");
                    IObjectWrapper a4 = IObjectWrapper.IObjectWrapper.m10120a(parcel.readStrongBinder());
                    a = IObjectWrapper.IObjectWrapper.m10120a(parcel.readStrongBinder());
                    if (parcel.readInt() != 0) {
                        bundle = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                    } else {
                        bundle = null;
                    }
                    IObjectWrapper a5 = m8618a(a4, a, bundle);
                    parcel2.writeNoException();
                    if (a5 != null) {
                        iBinder = a5.asBinder();
                    }
                    parcel2.writeStrongBinder(iBinder);
                    return true;
                case R.SlidingUpPanelLayout_umanoDragView /*5*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IStreetViewPanoramaFragmentDelegate");
                    m8623b();
                    parcel2.writeNoException();
                    return true;
                case R.SlidingUpPanelLayout_umanoOverlay /*6*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IStreetViewPanoramaFragmentDelegate");
                    m8625c();
                    parcel2.writeNoException();
                    return true;
                case R.SlidingUpPanelLayout_umanoClipPanel /*7*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IStreetViewPanoramaFragmentDelegate");
                    m8626d();
                    parcel2.writeNoException();
                    return true;
                case R.SlidingUpPanelLayout_umanoAnchorPoint /*8*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IStreetViewPanoramaFragmentDelegate");
                    m8627e();
                    parcel2.writeNoException();
                    return true;
                case HTTP.HT /*9*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IStreetViewPanoramaFragmentDelegate");
                    m8628f();
                    parcel2.writeNoException();
                    return true;
                case HTTP.LF /*10*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IStreetViewPanoramaFragmentDelegate");
                    if (parcel.readInt() != 0) {
                        bundle = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                    } else {
                        bundle = null;
                    }
                    m8624b(bundle);
                    parcel2.writeNoException();
                    if (bundle != null) {
                        parcel2.writeInt(1);
                        bundle.writeToParcel(parcel2, 1);
                        return true;
                    }
                    parcel2.writeInt(0);
                    return true;
                case com.olacabs.customer.R.R.MapM4bAttrs_m4b_uiZoomControls /*11*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IStreetViewPanoramaFragmentDelegate");
                    boolean g = m8629g();
                    parcel2.writeNoException();
                    parcel2.writeInt(g ? 1 : 0);
                    return true;
                case com.olacabs.customer.R.R.MapM4bAttrs_m4b_uiZoomGestures /*12*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IStreetViewPanoramaFragmentDelegate");
                    m8622a(IOnStreetViewPanoramaReadyCallback.m11132a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 1598968902:
                    parcel2.writeString("com.google.android.m4b.maps.internal.IStreetViewPanoramaFragmentDelegate");
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    IObjectWrapper m8618a(IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2, Bundle bundle);

    IStreetViewPanoramaDelegate m8619a();

    void m8620a(Bundle bundle);

    void m8621a(IObjectWrapper iObjectWrapper, StreetViewPanoramaOptions streetViewPanoramaOptions, Bundle bundle);

    void m8622a(IOnStreetViewPanoramaReadyCallback iOnStreetViewPanoramaReadyCallback);

    void m8623b();

    void m8624b(Bundle bundle);

    void m8625c();

    void m8626d();

    void m8627e();

    void m8628f();

    boolean m8629g();
}
