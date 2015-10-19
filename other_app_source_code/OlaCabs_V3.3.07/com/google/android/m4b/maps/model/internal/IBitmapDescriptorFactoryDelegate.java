package com.google.android.m4b.maps.model.internal;

import android.graphics.Bitmap;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.m4b.maps.cc.IObjectWrapper;
import com.sothree.slidinguppanel.p086a.R.R;

/* renamed from: com.google.android.m4b.maps.model.internal.d */
public interface IBitmapDescriptorFactoryDelegate extends IInterface {

    /* renamed from: com.google.android.m4b.maps.model.internal.d.a */
    public static abstract class IBitmapDescriptorFactoryDelegate extends Binder implements IBitmapDescriptorFactoryDelegate {
        public IBitmapDescriptorFactoryDelegate() {
            attachInterface(this, "com.google.android.m4b.maps.model.internal.IBitmapDescriptorFactoryDelegate");
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            IBinder iBinder = null;
            IObjectWrapper a;
            switch (i) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    IBinder asBinder;
                    parcel.enforceInterface("com.google.android.m4b.maps.model.internal.IBitmapDescriptorFactoryDelegate");
                    a = m8870a(parcel.readInt());
                    parcel2.writeNoException();
                    if (a != null) {
                        asBinder = a.asBinder();
                    } else {
                        asBinder = null;
                    }
                    parcel2.writeStrongBinder(asBinder);
                    return true;
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.model.internal.IBitmapDescriptorFactoryDelegate");
                    a = m8872a(parcel.readString());
                    parcel2.writeNoException();
                    if (a != null) {
                        iBinder = a.asBinder();
                    }
                    parcel2.writeStrongBinder(iBinder);
                    return true;
                case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.model.internal.IBitmapDescriptorFactoryDelegate");
                    a = m8873b(parcel.readString());
                    parcel2.writeNoException();
                    if (a != null) {
                        iBinder = a.asBinder();
                    }
                    parcel2.writeStrongBinder(iBinder);
                    return true;
                case R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.model.internal.IBitmapDescriptorFactoryDelegate");
                    a = m8868a();
                    parcel2.writeNoException();
                    if (a != null) {
                        iBinder = a.asBinder();
                    }
                    parcel2.writeStrongBinder(iBinder);
                    return true;
                case R.SlidingUpPanelLayout_umanoDragView /*5*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.model.internal.IBitmapDescriptorFactoryDelegate");
                    a = m8869a(parcel.readFloat());
                    parcel2.writeNoException();
                    if (a != null) {
                        iBinder = a.asBinder();
                    }
                    parcel2.writeStrongBinder(iBinder);
                    return true;
                case R.SlidingUpPanelLayout_umanoOverlay /*6*/:
                    Bitmap bitmap;
                    parcel.enforceInterface("com.google.android.m4b.maps.model.internal.IBitmapDescriptorFactoryDelegate");
                    if (parcel.readInt() != 0) {
                        bitmap = (Bitmap) Bitmap.CREATOR.createFromParcel(parcel);
                    } else {
                        bitmap = null;
                    }
                    a = m8871a(bitmap);
                    parcel2.writeNoException();
                    if (a != null) {
                        iBinder = a.asBinder();
                    }
                    parcel2.writeStrongBinder(iBinder);
                    return true;
                case R.SlidingUpPanelLayout_umanoClipPanel /*7*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.model.internal.IBitmapDescriptorFactoryDelegate");
                    a = m8874c(parcel.readString());
                    parcel2.writeNoException();
                    if (a != null) {
                        iBinder = a.asBinder();
                    }
                    parcel2.writeStrongBinder(iBinder);
                    return true;
                case 1598968902:
                    parcel2.writeString("com.google.android.m4b.maps.model.internal.IBitmapDescriptorFactoryDelegate");
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    IObjectWrapper m8868a();

    IObjectWrapper m8869a(float f);

    IObjectWrapper m8870a(int i);

    IObjectWrapper m8871a(Bitmap bitmap);

    IObjectWrapper m8872a(String str);

    IObjectWrapper m8873b(String str);

    IObjectWrapper m8874c(String str);
}
