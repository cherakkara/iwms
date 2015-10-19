package com.google.android.m4b.maps.p051l;

import android.app.PendingIntent;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.sothree.slidinguppanel.p086a.R.R;

/* renamed from: com.google.android.m4b.maps.l.b */
public interface IGeofencerCallbacks extends IInterface {

    /* renamed from: com.google.android.m4b.maps.l.b.a */
    public static abstract class IGeofencerCallbacks extends Binder implements IGeofencerCallbacks {

        /* renamed from: com.google.android.m4b.maps.l.b.a.a */
        static class IGeofencerCallbacks implements IGeofencerCallbacks {
            private IBinder f7467a;

            IGeofencerCallbacks(IBinder iBinder) {
                this.f7467a = iBinder;
            }

            public final IBinder asBinder() {
                return this.f7467a;
            }

            public final void m10577a(int i, String[] strArr) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGeofencerCallbacks");
                    obtain.writeInt(i);
                    obtain.writeStringArray(strArr);
                    this.f7467a.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final void m10578b(int i, String[] strArr) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGeofencerCallbacks");
                    obtain.writeInt(i);
                    obtain.writeStringArray(strArr);
                    this.f7467a.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final void m10576a(int i, PendingIntent pendingIntent) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGeofencerCallbacks");
                    obtain.writeInt(i);
                    if (pendingIntent != null) {
                        obtain.writeInt(1);
                        pendingIntent.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.f7467a.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static IGeofencerCallbacks m10579a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.location.internal.IGeofencerCallbacks");
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IGeofencerCallbacks)) {
                return new IGeofencerCallbacks(iBinder);
            }
            return (IGeofencerCallbacks) queryLocalInterface;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            switch (i) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    parcel.enforceInterface("com.google.android.gms.location.internal.IGeofencerCallbacks");
                    m10574a(parcel.readInt(), parcel.createStringArray());
                    parcel2.writeNoException();
                    return true;
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    parcel.enforceInterface("com.google.android.gms.location.internal.IGeofencerCallbacks");
                    m10575b(parcel.readInt(), parcel.createStringArray());
                    parcel2.writeNoException();
                    return true;
                case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                    PendingIntent pendingIntent;
                    parcel.enforceInterface("com.google.android.gms.location.internal.IGeofencerCallbacks");
                    int readInt = parcel.readInt();
                    if (parcel.readInt() != 0) {
                        pendingIntent = (PendingIntent) PendingIntent.CREATOR.createFromParcel(parcel);
                    } else {
                        pendingIntent = null;
                    }
                    m10573a(readInt, pendingIntent);
                    parcel2.writeNoException();
                    return true;
                case 1598968902:
                    parcel2.writeString("com.google.android.gms.location.internal.IGeofencerCallbacks");
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    void m10573a(int i, PendingIntent pendingIntent);

    void m10574a(int i, String[] strArr);

    void m10575b(int i, String[] strArr);
}
