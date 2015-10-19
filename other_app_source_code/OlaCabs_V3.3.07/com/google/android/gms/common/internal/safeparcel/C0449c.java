package com.google.android.gms.common.internal.safeparcel;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.C0453u;

/* renamed from: com.google.android.gms.common.internal.safeparcel.c */
public final class C0449c {
    public static <T extends SafeParcelable> T m3840a(Intent intent, String str, Creator<T> creator) {
        byte[] byteArrayExtra = intent.getByteArrayExtra(str);
        return byteArrayExtra == null ? null : C0449c.m3841a(byteArrayExtra, creator);
    }

    public static <T extends SafeParcelable> T m3841a(byte[] bArr, Creator<T> creator) {
        C0453u.m3846a((Object) creator);
        Parcel obtain = Parcel.obtain();
        obtain.unmarshall(bArr, 0, bArr.length);
        obtain.setDataPosition(0);
        SafeParcelable safeParcelable = (SafeParcelable) creator.createFromParcel(obtain);
        obtain.recycle();
        return safeParcelable;
    }
}
