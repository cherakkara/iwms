package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public final class BinderWrapper implements Parcelable {
    public static final Creator<BinderWrapper> CREATOR;
    private IBinder f2230a;

    /* renamed from: com.google.android.gms.common.internal.BinderWrapper.1 */
    static class C04091 implements Creator<BinderWrapper> {
        C04091() {
        }

        public BinderWrapper m3553a(Parcel parcel) {
            return new BinderWrapper(null);
        }

        public BinderWrapper[] m3554a(int i) {
            return new BinderWrapper[i];
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return m3553a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return m3554a(i);
        }
    }

    static {
        CREATOR = new C04091();
    }

    public BinderWrapper() {
        this.f2230a = null;
    }

    public BinderWrapper(IBinder iBinder) {
        this.f2230a = null;
        this.f2230a = iBinder;
    }

    private BinderWrapper(Parcel parcel) {
        this.f2230a = null;
        this.f2230a = parcel.readStrongBinder();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeStrongBinder(this.f2230a);
    }
}
