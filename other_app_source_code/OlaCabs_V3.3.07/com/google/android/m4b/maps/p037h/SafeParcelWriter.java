package com.google.android.m4b.maps.p037h;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.internal.view.SupportMenu;
import java.util.List;

/* renamed from: com.google.android.m4b.maps.h.b */
public final class SafeParcelWriter {
    private static void m10507b(Parcel parcel, int i, int i2) {
        if (i2 >= SupportMenu.USER_MASK) {
            parcel.writeInt(SupportMenu.CATEGORY_MASK | i);
            parcel.writeInt(i2);
            return;
        }
        parcel.writeInt((i2 << 16) | i);
    }

    private static int m10506b(Parcel parcel, int i) {
        parcel.writeInt(SupportMenu.CATEGORY_MASK | i);
        parcel.writeInt(0);
        return parcel.dataPosition();
    }

    private static void m10509c(Parcel parcel, int i) {
        int dataPosition = parcel.dataPosition();
        int i2 = dataPosition - i;
        parcel.setDataPosition(i - 4);
        parcel.writeInt(i2);
        parcel.setDataPosition(dataPosition);
    }

    public static int m10487a(Parcel parcel) {
        return SafeParcelWriter.m10506b(parcel, 20293);
    }

    public static void m10488a(Parcel parcel, int i) {
        SafeParcelWriter.m10509c(parcel, i);
    }

    public static void m10501a(Parcel parcel, int i, boolean z) {
        SafeParcelWriter.m10507b(parcel, i, 4);
        parcel.writeInt(z ? 1 : 0);
    }

    public static void m10489a(Parcel parcel, int i, byte b) {
        SafeParcelWriter.m10507b(parcel, i, 4);
        parcel.writeInt(b);
    }

    public static void m10500a(Parcel parcel, int i, short s) {
        SafeParcelWriter.m10507b(parcel, 3, 4);
        parcel.writeInt(s);
    }

    public static void m10492a(Parcel parcel, int i, int i2) {
        SafeParcelWriter.m10507b(parcel, i, 4);
        parcel.writeInt(i2);
    }

    public static void m10497a(Parcel parcel, int i, Integer num, boolean z) {
        if (num != null) {
            SafeParcelWriter.m10507b(parcel, 5, 4);
            parcel.writeInt(num.intValue());
        }
    }

    public static void m10493a(Parcel parcel, int i, long j) {
        SafeParcelWriter.m10507b(parcel, i, 8);
        parcel.writeLong(j);
    }

    public static void m10491a(Parcel parcel, int i, float f) {
        SafeParcelWriter.m10507b(parcel, i, 4);
        parcel.writeFloat(f);
    }

    public static void m10490a(Parcel parcel, int i, double d) {
        SafeParcelWriter.m10507b(parcel, i, 8);
        parcel.writeDouble(d);
    }

    public static void m10498a(Parcel parcel, int i, String str, boolean z) {
        if (str != null) {
            int b = SafeParcelWriter.m10506b(parcel, i);
            parcel.writeString(str);
            SafeParcelWriter.m10509c(parcel, b);
        }
    }

    public static void m10495a(Parcel parcel, int i, IBinder iBinder, boolean z) {
        if (iBinder != null) {
            int b = SafeParcelWriter.m10506b(parcel, i);
            parcel.writeStrongBinder(iBinder);
            SafeParcelWriter.m10509c(parcel, b);
        }
    }

    public static void m10496a(Parcel parcel, int i, Parcelable parcelable, int i2, boolean z) {
        if (parcelable != null) {
            int b = SafeParcelWriter.m10506b(parcel, i);
            parcelable.writeToParcel(parcel, i2);
            SafeParcelWriter.m10509c(parcel, b);
        }
    }

    public static void m10494a(Parcel parcel, int i, Bundle bundle, boolean z) {
        if (bundle != null) {
            int b = SafeParcelWriter.m10506b(parcel, i);
            parcel.writeBundle(bundle);
            SafeParcelWriter.m10509c(parcel, b);
        }
    }

    public static void m10502a(Parcel parcel, int i, byte[] bArr, boolean z) {
        if (bArr != null) {
            int b = SafeParcelWriter.m10506b(parcel, 4);
            parcel.writeByteArray(bArr);
            SafeParcelWriter.m10509c(parcel, b);
        }
    }

    public static void m10504a(Parcel parcel, int i, String[] strArr, boolean z) {
        if (strArr != null) {
            int b = SafeParcelWriter.m10506b(parcel, 1);
            parcel.writeStringArray(strArr);
            SafeParcelWriter.m10509c(parcel, b);
        }
    }

    public static void m10499a(Parcel parcel, int i, List<String> list, boolean z) {
        if (list != null) {
            int b = SafeParcelWriter.m10506b(parcel, i);
            parcel.writeStringList(list);
            SafeParcelWriter.m10509c(parcel, b);
        }
    }

    public static <T extends Parcelable> void m10503a(Parcel parcel, int i, T[] tArr, int i2, boolean z) {
        if (tArr != null) {
            int b = SafeParcelWriter.m10506b(parcel, 2);
            parcel.writeInt(r3);
            for (Parcelable parcelable : tArr) {
                if (parcelable == null) {
                    parcel.writeInt(0);
                } else {
                    SafeParcelWriter.m10505a(parcel, parcelable, i2);
                }
            }
            SafeParcelWriter.m10509c(parcel, b);
        }
    }

    public static <T extends Parcelable> void m10508b(Parcel parcel, int i, List<T> list, boolean z) {
        if (list != null) {
            int b = SafeParcelWriter.m10506b(parcel, i);
            int size = list.size();
            parcel.writeInt(size);
            for (int i2 = 0; i2 < size; i2++) {
                Parcelable parcelable = (Parcelable) list.get(i2);
                if (parcelable == null) {
                    parcel.writeInt(0);
                } else {
                    SafeParcelWriter.m10505a(parcel, parcelable, 0);
                }
            }
            SafeParcelWriter.m10509c(parcel, b);
        }
    }

    private static <T extends Parcelable> void m10505a(Parcel parcel, T t, int i) {
        int dataPosition = parcel.dataPosition();
        parcel.writeInt(1);
        int dataPosition2 = parcel.dataPosition();
        t.writeToParcel(parcel, i);
        int dataPosition3 = parcel.dataPosition();
        parcel.setDataPosition(dataPosition);
        parcel.writeInt(dataPosition3 - dataPosition2);
        parcel.setDataPosition(dataPosition3);
    }

    public static void m10510c(Parcel parcel, int i, List list, boolean z) {
        if (list != null) {
            int b = SafeParcelWriter.m10506b(parcel, 3);
            parcel.writeList(list);
            SafeParcelWriter.m10509c(parcel, b);
        }
    }
}
