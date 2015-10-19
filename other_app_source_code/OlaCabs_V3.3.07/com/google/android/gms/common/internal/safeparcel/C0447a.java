package com.google.android.gms.common.internal.safeparcel;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.v4.internal.view.SupportMenu;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.google.android.gms.common.internal.safeparcel.a */
public class C0447a {

    /* renamed from: com.google.android.gms.common.internal.safeparcel.a.a */
    public static class C0446a extends RuntimeException {
        public C0446a(String str, Parcel parcel) {
            super(str + " Parcel: pos=" + parcel.dataPosition() + " size=" + parcel.dataSize());
        }
    }

    public static Parcel m3777A(Parcel parcel, int i) {
        int a = C0447a.m3781a(parcel, i);
        int dataPosition = parcel.dataPosition();
        if (a == 0) {
            return null;
        }
        Parcel obtain = Parcel.obtain();
        obtain.appendFrom(parcel, dataPosition, a);
        parcel.setDataPosition(a + dataPosition);
        return obtain;
    }

    public static Parcel[] m3778B(Parcel parcel, int i) {
        int a = C0447a.m3781a(parcel, i);
        int dataPosition = parcel.dataPosition();
        if (a == 0) {
            return null;
        }
        int readInt = parcel.readInt();
        Parcel[] parcelArr = new Parcel[readInt];
        for (int i2 = 0; i2 < readInt; i2++) {
            int readInt2 = parcel.readInt();
            if (readInt2 != 0) {
                int dataPosition2 = parcel.dataPosition();
                Parcel obtain = Parcel.obtain();
                obtain.appendFrom(parcel, dataPosition2, readInt2);
                parcelArr[i2] = obtain;
                parcel.setDataPosition(readInt2 + dataPosition2);
            } else {
                parcelArr[i2] = null;
            }
        }
        parcel.setDataPosition(dataPosition + a);
        return parcelArr;
    }

    public static int m3779a(int i) {
        return SupportMenu.USER_MASK & i;
    }

    public static int m3780a(Parcel parcel) {
        return parcel.readInt();
    }

    public static int m3781a(Parcel parcel, int i) {
        return (i & SupportMenu.CATEGORY_MASK) != SupportMenu.CATEGORY_MASK ? (i >> 16) & SupportMenu.USER_MASK : parcel.readInt();
    }

    public static <T extends Parcelable> T m3782a(Parcel parcel, int i, Creator<T> creator) {
        int a = C0447a.m3781a(parcel, i);
        int dataPosition = parcel.dataPosition();
        if (a == 0) {
            return null;
        }
        Parcelable parcelable = (Parcelable) creator.createFromParcel(parcel);
        parcel.setDataPosition(a + dataPosition);
        return parcelable;
    }

    private static void m3783a(Parcel parcel, int i, int i2) {
        int a = C0447a.m3781a(parcel, i);
        if (a != i2) {
            throw new C0446a("Expected size " + i2 + " got " + a + " (0x" + Integer.toHexString(a) + ")", parcel);
        }
    }

    private static void m3784a(Parcel parcel, int i, int i2, int i3) {
        if (i2 != i3) {
            throw new C0446a("Expected size " + i3 + " got " + i2 + " (0x" + Integer.toHexString(i2) + ")", parcel);
        }
    }

    public static void m3785a(Parcel parcel, int i, List list, ClassLoader classLoader) {
        int a = C0447a.m3781a(parcel, i);
        int dataPosition = parcel.dataPosition();
        if (a != 0) {
            parcel.readList(list, classLoader);
            parcel.setDataPosition(a + dataPosition);
        }
    }

    public static int m3786b(Parcel parcel) {
        int a = C0447a.m3780a(parcel);
        int a2 = C0447a.m3781a(parcel, a);
        int dataPosition = parcel.dataPosition();
        if (C0447a.m3779a(a) != 20293) {
            throw new C0446a("Expected object header. Got 0x" + Integer.toHexString(a), parcel);
        }
        a = dataPosition + a2;
        if (a >= dataPosition && a <= parcel.dataSize()) {
            return a;
        }
        throw new C0446a("Size read is invalid start=" + dataPosition + " end=" + a, parcel);
    }

    public static void m3787b(Parcel parcel, int i) {
        parcel.setDataPosition(C0447a.m3781a(parcel, i) + parcel.dataPosition());
    }

    public static <T> T[] m3788b(Parcel parcel, int i, Creator<T> creator) {
        int a = C0447a.m3781a(parcel, i);
        int dataPosition = parcel.dataPosition();
        if (a == 0) {
            return null;
        }
        T[] createTypedArray = parcel.createTypedArray(creator);
        parcel.setDataPosition(a + dataPosition);
        return createTypedArray;
    }

    public static <T> ArrayList<T> m3789c(Parcel parcel, int i, Creator<T> creator) {
        int a = C0447a.m3781a(parcel, i);
        int dataPosition = parcel.dataPosition();
        if (a == 0) {
            return null;
        }
        ArrayList<T> createTypedArrayList = parcel.createTypedArrayList(creator);
        parcel.setDataPosition(a + dataPosition);
        return createTypedArrayList;
    }

    public static boolean m3790c(Parcel parcel, int i) {
        C0447a.m3783a(parcel, i, 4);
        return parcel.readInt() != 0;
    }

    public static byte m3791d(Parcel parcel, int i) {
        C0447a.m3783a(parcel, i, 4);
        return (byte) parcel.readInt();
    }

    public static short m3792e(Parcel parcel, int i) {
        C0447a.m3783a(parcel, i, 4);
        return (short) parcel.readInt();
    }

    public static int m3793f(Parcel parcel, int i) {
        C0447a.m3783a(parcel, i, 4);
        return parcel.readInt();
    }

    public static Integer m3794g(Parcel parcel, int i) {
        int a = C0447a.m3781a(parcel, i);
        if (a == 0) {
            return null;
        }
        C0447a.m3784a(parcel, i, a, 4);
        return Integer.valueOf(parcel.readInt());
    }

    public static long m3795h(Parcel parcel, int i) {
        C0447a.m3783a(parcel, i, 8);
        return parcel.readLong();
    }

    public static BigInteger m3796i(Parcel parcel, int i) {
        int a = C0447a.m3781a(parcel, i);
        int dataPosition = parcel.dataPosition();
        if (a == 0) {
            return null;
        }
        byte[] createByteArray = parcel.createByteArray();
        parcel.setDataPosition(a + dataPosition);
        return new BigInteger(createByteArray);
    }

    public static float m3797j(Parcel parcel, int i) {
        C0447a.m3783a(parcel, i, 4);
        return parcel.readFloat();
    }

    public static double m3798k(Parcel parcel, int i) {
        C0447a.m3783a(parcel, i, 8);
        return parcel.readDouble();
    }

    public static BigDecimal m3799l(Parcel parcel, int i) {
        int a = C0447a.m3781a(parcel, i);
        int dataPosition = parcel.dataPosition();
        if (a == 0) {
            return null;
        }
        byte[] createByteArray = parcel.createByteArray();
        int readInt = parcel.readInt();
        parcel.setDataPosition(a + dataPosition);
        return new BigDecimal(new BigInteger(createByteArray), readInt);
    }

    public static String m3800m(Parcel parcel, int i) {
        int a = C0447a.m3781a(parcel, i);
        int dataPosition = parcel.dataPosition();
        if (a == 0) {
            return null;
        }
        String readString = parcel.readString();
        parcel.setDataPosition(a + dataPosition);
        return readString;
    }

    public static IBinder m3801n(Parcel parcel, int i) {
        int a = C0447a.m3781a(parcel, i);
        int dataPosition = parcel.dataPosition();
        if (a == 0) {
            return null;
        }
        IBinder readStrongBinder = parcel.readStrongBinder();
        parcel.setDataPosition(a + dataPosition);
        return readStrongBinder;
    }

    public static Bundle m3802o(Parcel parcel, int i) {
        int a = C0447a.m3781a(parcel, i);
        int dataPosition = parcel.dataPosition();
        if (a == 0) {
            return null;
        }
        Bundle readBundle = parcel.readBundle();
        parcel.setDataPosition(a + dataPosition);
        return readBundle;
    }

    public static byte[] m3803p(Parcel parcel, int i) {
        int a = C0447a.m3781a(parcel, i);
        int dataPosition = parcel.dataPosition();
        if (a == 0) {
            return null;
        }
        byte[] createByteArray = parcel.createByteArray();
        parcel.setDataPosition(a + dataPosition);
        return createByteArray;
    }

    public static boolean[] m3804q(Parcel parcel, int i) {
        int a = C0447a.m3781a(parcel, i);
        int dataPosition = parcel.dataPosition();
        if (a == 0) {
            return null;
        }
        boolean[] createBooleanArray = parcel.createBooleanArray();
        parcel.setDataPosition(a + dataPosition);
        return createBooleanArray;
    }

    public static int[] m3805r(Parcel parcel, int i) {
        int a = C0447a.m3781a(parcel, i);
        int dataPosition = parcel.dataPosition();
        if (a == 0) {
            return null;
        }
        int[] createIntArray = parcel.createIntArray();
        parcel.setDataPosition(a + dataPosition);
        return createIntArray;
    }

    public static long[] m3806s(Parcel parcel, int i) {
        int a = C0447a.m3781a(parcel, i);
        int dataPosition = parcel.dataPosition();
        if (a == 0) {
            return null;
        }
        long[] createLongArray = parcel.createLongArray();
        parcel.setDataPosition(a + dataPosition);
        return createLongArray;
    }

    public static BigInteger[] m3807t(Parcel parcel, int i) {
        int a = C0447a.m3781a(parcel, i);
        int dataPosition = parcel.dataPosition();
        if (a == 0) {
            return null;
        }
        int readInt = parcel.readInt();
        BigInteger[] bigIntegerArr = new BigInteger[readInt];
        for (int i2 = 0; i2 < readInt; i2++) {
            bigIntegerArr[i2] = new BigInteger(parcel.createByteArray());
        }
        parcel.setDataPosition(dataPosition + a);
        return bigIntegerArr;
    }

    public static float[] m3808u(Parcel parcel, int i) {
        int a = C0447a.m3781a(parcel, i);
        int dataPosition = parcel.dataPosition();
        if (a == 0) {
            return null;
        }
        float[] createFloatArray = parcel.createFloatArray();
        parcel.setDataPosition(a + dataPosition);
        return createFloatArray;
    }

    public static double[] m3809v(Parcel parcel, int i) {
        int a = C0447a.m3781a(parcel, i);
        int dataPosition = parcel.dataPosition();
        if (a == 0) {
            return null;
        }
        double[] createDoubleArray = parcel.createDoubleArray();
        parcel.setDataPosition(a + dataPosition);
        return createDoubleArray;
    }

    public static BigDecimal[] m3810w(Parcel parcel, int i) {
        int a = C0447a.m3781a(parcel, i);
        int dataPosition = parcel.dataPosition();
        if (a == 0) {
            return null;
        }
        int readInt = parcel.readInt();
        BigDecimal[] bigDecimalArr = new BigDecimal[readInt];
        for (int i2 = 0; i2 < readInt; i2++) {
            byte[] createByteArray = parcel.createByteArray();
            bigDecimalArr[i2] = new BigDecimal(new BigInteger(createByteArray), parcel.readInt());
        }
        parcel.setDataPosition(dataPosition + a);
        return bigDecimalArr;
    }

    public static String[] m3811x(Parcel parcel, int i) {
        int a = C0447a.m3781a(parcel, i);
        int dataPosition = parcel.dataPosition();
        if (a == 0) {
            return null;
        }
        String[] createStringArray = parcel.createStringArray();
        parcel.setDataPosition(a + dataPosition);
        return createStringArray;
    }

    public static ArrayList<Integer> m3812y(Parcel parcel, int i) {
        int a = C0447a.m3781a(parcel, i);
        int dataPosition = parcel.dataPosition();
        if (a == 0) {
            return null;
        }
        ArrayList<Integer> arrayList = new ArrayList();
        int readInt = parcel.readInt();
        for (int i2 = 0; i2 < readInt; i2++) {
            arrayList.add(Integer.valueOf(parcel.readInt()));
        }
        parcel.setDataPosition(dataPosition + a);
        return arrayList;
    }

    public static ArrayList<String> m3813z(Parcel parcel, int i) {
        int a = C0447a.m3781a(parcel, i);
        int dataPosition = parcel.dataPosition();
        if (a == 0) {
            return null;
        }
        ArrayList<String> createStringArrayList = parcel.createStringArrayList();
        parcel.setDataPosition(a + dataPosition);
        return createStringArrayList;
    }
}
