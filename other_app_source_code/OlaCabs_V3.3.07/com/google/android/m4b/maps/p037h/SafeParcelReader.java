package com.google.android.m4b.maps.p037h;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.v4.internal.view.SupportMenu;
import java.util.ArrayList;

/* renamed from: com.google.android.m4b.maps.h.a */
public final class SafeParcelReader {

    /* renamed from: com.google.android.m4b.maps.h.a.a */
    public static class SafeParcelReader extends RuntimeException {
        public SafeParcelReader(String str, Parcel parcel) {
            super(str + " Parcel: pos=" + parcel.dataPosition() + " size=" + parcel.dataSize());
        }
    }

    public static int m10469a(Parcel parcel, int i) {
        if ((i & SupportMenu.CATEGORY_MASK) != SupportMenu.CATEGORY_MASK) {
            return (i >> 16) & SupportMenu.USER_MASK;
        }
        return parcel.readInt();
    }

    public static void m10472b(Parcel parcel, int i) {
        parcel.setDataPosition(SafeParcelReader.m10469a(parcel, i) + parcel.dataPosition());
    }

    private static void m10471a(Parcel parcel, int i, int i2) {
        int a = SafeParcelReader.m10469a(parcel, i);
        if (a != i2) {
            throw new SafeParcelReader("Expected size " + i2 + " got " + a + " (0x" + Integer.toHexString(a) + ")", parcel);
        }
    }

    public static int m10468a(Parcel parcel) {
        int readInt = parcel.readInt();
        int a = SafeParcelReader.m10469a(parcel, readInt);
        int dataPosition = parcel.dataPosition();
        if ((SupportMenu.USER_MASK & readInt) != 20293) {
            throw new SafeParcelReader("Expected object header. Got 0x" + Integer.toHexString(readInt), parcel);
        }
        readInt = dataPosition + a;
        if (readInt >= dataPosition && readInt <= parcel.dataSize()) {
            return readInt;
        }
        throw new SafeParcelReader("Size read is invalid start=" + dataPosition + " end=" + readInt, parcel);
    }

    public static boolean m10475c(Parcel parcel, int i) {
        SafeParcelReader.m10471a(parcel, i, 4);
        return parcel.readInt() != 0;
    }

    public static byte m10476d(Parcel parcel, int i) {
        SafeParcelReader.m10471a(parcel, i, 4);
        return (byte) parcel.readInt();
    }

    public static short m10477e(Parcel parcel, int i) {
        SafeParcelReader.m10471a(parcel, i, 4);
        return (short) parcel.readInt();
    }

    public static int m10478f(Parcel parcel, int i) {
        SafeParcelReader.m10471a(parcel, i, 4);
        return parcel.readInt();
    }

    public static Integer m10479g(Parcel parcel, int i) {
        int a = SafeParcelReader.m10469a(parcel, i);
        if (a == 0) {
            return null;
        }
        if (a == 4) {
            return Integer.valueOf(parcel.readInt());
        }
        throw new SafeParcelReader("Expected size " + 4 + " got " + a + " (0x" + Integer.toHexString(a) + ")", parcel);
    }

    public static long m10480h(Parcel parcel, int i) {
        SafeParcelReader.m10471a(parcel, i, 8);
        return parcel.readLong();
    }

    public static float m10481i(Parcel parcel, int i) {
        SafeParcelReader.m10471a(parcel, i, 4);
        return parcel.readFloat();
    }

    public static double m10482j(Parcel parcel, int i) {
        SafeParcelReader.m10471a(parcel, i, 8);
        return parcel.readDouble();
    }

    public static String m10483k(Parcel parcel, int i) {
        int a = SafeParcelReader.m10469a(parcel, i);
        int dataPosition = parcel.dataPosition();
        if (a == 0) {
            return null;
        }
        String readString = parcel.readString();
        parcel.setDataPosition(a + dataPosition);
        return readString;
    }

    public static IBinder m10484l(Parcel parcel, int i) {
        int a = SafeParcelReader.m10469a(parcel, i);
        int dataPosition = parcel.dataPosition();
        if (a == 0) {
            return null;
        }
        IBinder readStrongBinder = parcel.readStrongBinder();
        parcel.setDataPosition(a + dataPosition);
        return readStrongBinder;
    }

    public static <T extends Parcelable> T m10470a(Parcel parcel, int i, Creator<T> creator) {
        int a = SafeParcelReader.m10469a(parcel, i);
        int dataPosition = parcel.dataPosition();
        if (a == 0) {
            return null;
        }
        Parcelable parcelable = (Parcelable) creator.createFromParcel(parcel);
        parcel.setDataPosition(a + dataPosition);
        return parcelable;
    }

    public static Bundle m10485m(Parcel parcel, int i) {
        int a = SafeParcelReader.m10469a(parcel, i);
        int dataPosition = parcel.dataPosition();
        if (a == 0) {
            return null;
        }
        Bundle readBundle = parcel.readBundle();
        parcel.setDataPosition(a + dataPosition);
        return readBundle;
    }

    public static ArrayList<String> m10486n(Parcel parcel, int i) {
        int a = SafeParcelReader.m10469a(parcel, i);
        int dataPosition = parcel.dataPosition();
        if (a == 0) {
            return null;
        }
        ArrayList<String> createStringArrayList = parcel.createStringArrayList();
        parcel.setDataPosition(a + dataPosition);
        return createStringArrayList;
    }

    public static <T> T[] m10473b(Parcel parcel, int i, Creator<T> creator) {
        int a = SafeParcelReader.m10469a(parcel, i);
        int dataPosition = parcel.dataPosition();
        if (a == 0) {
            return null;
        }
        T[] createTypedArray = parcel.createTypedArray(creator);
        parcel.setDataPosition(a + dataPosition);
        return createTypedArray;
    }

    public static <T> ArrayList<T> m10474c(Parcel parcel, int i, Creator<T> creator) {
        int a = SafeParcelReader.m10469a(parcel, i);
        int dataPosition = parcel.dataPosition();
        if (a == 0) {
            return null;
        }
        ArrayList<T> createTypedArrayList = parcel.createTypedArrayList(creator);
        parcel.setDataPosition(a + dataPosition);
        return createTypedArrayList;
    }
}
