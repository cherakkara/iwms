package com.google.android.m4b.maps.p042r;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.m4b.maps.cc.IObjectWrapper;
import com.google.android.m4b.maps.model.LatLng;
import com.google.android.m4b.maps.model.VisibleRegion;
import com.sothree.slidinguppanel.p086a.R.R;

/* renamed from: com.google.android.m4b.maps.r.f */
public interface IProjectionDelegate extends IInterface {

    /* renamed from: com.google.android.m4b.maps.r.f.a */
    public static abstract class IProjectionDelegate extends Binder implements IProjectionDelegate {

        /* renamed from: com.google.android.m4b.maps.r.f.a.a */
        static class IProjectionDelegate implements IProjectionDelegate {
            private IBinder f7718a;

            IProjectionDelegate(IBinder iBinder) {
                this.f7718a = iBinder;
            }

            public final IBinder asBinder() {
                return this.f7718a;
            }

            public final LatLng m11134a(IObjectWrapper iObjectWrapper) {
                LatLng latLng = null;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.internal.IProjectionDelegate");
                    obtain.writeStrongBinder(iObjectWrapper != null ? iObjectWrapper.asBinder() : null);
                    this.f7718a.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        latLng = LatLng.CREATOR.m11012a(obtain2);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return latLng;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final IObjectWrapper m11133a(LatLng latLng) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.internal.IProjectionDelegate");
                    if (latLng != null) {
                        obtain.writeInt(1);
                        latLng.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.f7718a.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    IObjectWrapper a = IObjectWrapper.IObjectWrapper.m10120a(obtain2.readStrongBinder());
                    return a;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final VisibleRegion m11136a() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    VisibleRegion a;
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.internal.IProjectionDelegate");
                    this.f7718a.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        a = VisibleRegion.CREATOR.m10820a(obtain2);
                    } else {
                        a = null;
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return a;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final LatLng m11135a(an anVar) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    LatLng a;
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.internal.IProjectionDelegate");
                    if (anVar != null) {
                        obtain.writeInt(1);
                        anVar.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.f7718a.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        a = LatLng.CREATOR.m11012a(obtain2);
                    } else {
                        a = null;
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return a;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final an m11137b(LatLng latLng) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    an a;
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.internal.IProjectionDelegate");
                    if (latLng != null) {
                        obtain.writeInt(1);
                        latLng.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.f7718a.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        PointCreator pointCreator = an.CREATOR;
                        a = PointCreator.m11185a(obtain2);
                    } else {
                        a = null;
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return a;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public IProjectionDelegate() {
            attachInterface(this, "com.google.android.m4b.maps.internal.IProjectionDelegate");
        }

        public static IProjectionDelegate m8558a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.m4b.maps.internal.IProjectionDelegate");
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IProjectionDelegate)) {
                return new IProjectionDelegate(iBinder);
            }
            return (IProjectionDelegate) queryLocalInterface;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            LatLng latLng = null;
            LatLng a;
            switch (i) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IProjectionDelegate");
                    a = m8554a(IObjectWrapper.IObjectWrapper.m10120a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    if (a != null) {
                        parcel2.writeInt(1);
                        a.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    IBinder asBinder;
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IProjectionDelegate");
                    if (parcel.readInt() != 0) {
                        a = LatLng.CREATOR.m11012a(parcel);
                    } else {
                        a = null;
                    }
                    IObjectWrapper a2 = m8553a(a);
                    parcel2.writeNoException();
                    if (a2 != null) {
                        asBinder = a2.asBinder();
                    }
                    parcel2.writeStrongBinder(asBinder);
                    return true;
                case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IProjectionDelegate");
                    VisibleRegion a3 = m8556a();
                    parcel2.writeNoException();
                    if (a3 != null) {
                        parcel2.writeInt(1);
                        a3.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
                    an a4;
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IProjectionDelegate");
                    if (parcel.readInt() != 0) {
                        PointCreator pointCreator = an.CREATOR;
                        a4 = PointCreator.m11185a(parcel);
                    }
                    a = m8555a(a4);
                    parcel2.writeNoException();
                    if (a != null) {
                        parcel2.writeInt(1);
                        a.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case R.SlidingUpPanelLayout_umanoDragView /*5*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IProjectionDelegate");
                    if (parcel.readInt() != 0) {
                        latLng = LatLng.CREATOR.m11012a(parcel);
                    }
                    an b = m8557b(latLng);
                    parcel2.writeNoException();
                    if (b != null) {
                        parcel2.writeInt(1);
                        b.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 1598968902:
                    parcel2.writeString("com.google.android.m4b.maps.internal.IProjectionDelegate");
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    IObjectWrapper m8553a(LatLng latLng);

    LatLng m8554a(IObjectWrapper iObjectWrapper);

    LatLng m8555a(an anVar);

    VisibleRegion m8556a();

    an m8557b(LatLng latLng);
}
