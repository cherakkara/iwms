package com.google.android.m4b.maps.p042r;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.m4b.maps.cc.IObjectWrapper;
import com.google.android.m4b.maps.p042r.aj.IOnMapReadyCallback;
import com.sothree.slidinguppanel.p086a.R.R;
import org.apache.http.protocol.HTTP;

/* compiled from: IMapViewDelegate */
/* renamed from: com.google.android.m4b.maps.r.aa */
public interface aa extends IInterface {

    /* renamed from: com.google.android.m4b.maps.r.aa.a */
    public static abstract class IMapViewDelegate extends Binder implements aa {
        public IMapViewDelegate() {
            attachInterface(this, "com.google.android.m4b.maps.internal.IMapViewDelegate");
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            IBinder iBinder = null;
            Bundle bundle;
            switch (i) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IMapViewDelegate");
                    IGoogleMapDelegate a = m9515a();
                    parcel2.writeNoException();
                    if (a != null) {
                        iBinder = a.asBinder();
                    }
                    parcel2.writeStrongBinder(iBinder);
                    return true;
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IMapViewDelegate");
                    if (parcel.readInt() != 0) {
                        bundle = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                    }
                    m9516a(bundle);
                    parcel2.writeNoException();
                    return true;
                case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IMapViewDelegate");
                    m9518b();
                    parcel2.writeNoException();
                    return true;
                case R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IMapViewDelegate");
                    m9520c();
                    parcel2.writeNoException();
                    return true;
                case R.SlidingUpPanelLayout_umanoDragView /*5*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IMapViewDelegate");
                    m9521d();
                    parcel2.writeNoException();
                    return true;
                case R.SlidingUpPanelLayout_umanoOverlay /*6*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IMapViewDelegate");
                    m9522e();
                    parcel2.writeNoException();
                    return true;
                case R.SlidingUpPanelLayout_umanoClipPanel /*7*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IMapViewDelegate");
                    if (parcel.readInt() != 0) {
                        bundle = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                    }
                    m9519b(bundle);
                    parcel2.writeNoException();
                    if (bundle != null) {
                        parcel2.writeInt(1);
                        bundle.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case R.SlidingUpPanelLayout_umanoAnchorPoint /*8*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IMapViewDelegate");
                    IObjectWrapper f = m9523f();
                    parcel2.writeNoException();
                    if (f != null) {
                        iBinder = f.asBinder();
                    }
                    parcel2.writeStrongBinder(iBinder);
                    return true;
                case HTTP.HT /*9*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IMapViewDelegate");
                    m9517a(IOnMapReadyCallback.m10517a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 1598968902:
                    parcel2.writeString("com.google.android.m4b.maps.internal.IMapViewDelegate");
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    IGoogleMapDelegate m9515a();

    void m9516a(Bundle bundle);

    void m9517a(aj ajVar);

    void m9518b();

    void m9519b(Bundle bundle);

    void m9520c();

    void m9521d();

    void m9522e();

    IObjectWrapper m9523f();
}
