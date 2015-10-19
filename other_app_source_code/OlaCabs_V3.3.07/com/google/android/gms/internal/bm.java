package com.google.android.gms.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.internal.bo.C0493a;
import com.sothree.slidinguppanel.p086a.R.R;
import java.util.List;

public interface bm extends IInterface {

    /* renamed from: com.google.android.gms.internal.bm.a */
    public static abstract class C0490a extends Binder implements bm {
        public C0490a() {
            attachInterface(this, "com.google.android.gms.signin.internal.IOfflineAccessCallbacks");
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            switch (i) {
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    parcel.enforceInterface("com.google.android.gms.signin.internal.IOfflineAccessCallbacks");
                    m4082a(parcel.readString(), parcel.createTypedArrayList(Scope.CREATOR), C0493a.m4099a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                    parcel.enforceInterface("com.google.android.gms.signin.internal.IOfflineAccessCallbacks");
                    m4081a(parcel.readString(), parcel.readString(), C0493a.m4099a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 1598968902:
                    parcel2.writeString("com.google.android.gms.signin.internal.IOfflineAccessCallbacks");
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    void m4081a(String str, String str2, bo boVar) throws RemoteException;

    void m4082a(String str, List<Scope> list, bo boVar) throws RemoteException;
}
