package com.google.android.m4b.maps.p046d;

import com.google.android.m4b.maps.p041b.BoundInputStream;
import com.olacabs.customer.utils.Constants;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.GZIPInputStream;

/* renamed from: com.google.android.m4b.maps.d.c */
public final class ProtoBufUtil {
    public static int m10222a(ProtoBuf protoBuf, int i, int i2) {
        if (protoBuf != null) {
            try {
                if (protoBuf.m10214j(i)) {
                    i2 = protoBuf.m10204d(i);
                }
            } catch (IllegalArgumentException e) {
            } catch (ClassCastException e2) {
            }
        }
        return i2;
    }

    public static long m10224a(ProtoBuf protoBuf, int i, long j) {
        long j2 = Constants.MILLIS_IN_AN_HOUR;
        if (protoBuf != null) {
            try {
                if (protoBuf.m10214j(3)) {
                    j2 = protoBuf.m10207e(3);
                }
            } catch (IllegalArgumentException e) {
            } catch (ClassCastException e2) {
            }
        }
        return j2;
    }

    public static int m10221a(ProtoBuf protoBuf, int i) {
        return ProtoBufUtil.m10222a(protoBuf, i, 0);
    }

    public static InputStream m10227a(DataInput dataInput) {
        int readInt = dataInput.readInt();
        InputStream boundInputStream = new BoundInputStream((InputStream) dataInput, Math.abs(readInt));
        if (readInt < 0) {
            return new GZIPInputStream(boundInputStream);
        }
        return boundInputStream;
    }

    public static int m10223a(ProtoBufType protoBufType, InputStream inputStream, ProtoBuf protoBuf) {
        long a = ProtoBuf.m10164a(inputStream, true);
        if (a == -1) {
            return -1;
        }
        if ((7 & a) != 2) {
            throw new IOException("Message expected");
        }
        int i = (int) (a >>> 3);
        protoBuf.m10186a((ProtoBufType) protoBufType.m10220b(i));
        protoBuf.m10180a(inputStream, (int) ProtoBuf.m10164a(inputStream, false));
        return i;
    }

    public static ProtoBuf m10226a(ProtoBufType protoBufType, DataInput dataInput) {
        ProtoBuf protoBuf = new ProtoBuf(protoBufType);
        InputStream a = ProtoBufUtil.m10227a(dataInput);
        protoBuf.m10187a(a);
        if (a.read() != -1) {
            throw new IOException();
        }
        a.close();
        return protoBuf;
    }

    public static ProtoBuf m10229b(ProtoBuf protoBuf, int i) {
        ProtoBuf a = protoBuf.m10182a(i);
        protoBuf.m10196b(i, a);
        return a;
    }

    public static ProtoBuf m10231c(ProtoBuf protoBuf, int i) {
        ProtoBuf a = protoBuf.m10182a(i);
        protoBuf.m10190a(i, a);
        return a;
    }

    public static void m10228a(DataOutput dataOutput, ProtoBuf protoBuf) {
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        protoBuf.m10199b(byteArrayOutputStream);
        byte[] toByteArray = byteArrayOutputStream.toByteArray();
        dataOutput.writeInt(toByteArray.length);
        dataOutput.write(toByteArray);
    }

    public static ProtoBuf m10225a(ProtoBuf protoBuf) {
        ProtoBuf protoBuf2 = new ProtoBuf(protoBuf.m10198b());
        protoBuf2.m10188a(protoBuf.m10206d());
        return protoBuf2;
    }

    public static DataInput m10230b(ProtoBuf protoBuf) {
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ProtoBufUtil.m10228a(new DataOutputStream(byteArrayOutputStream), protoBuf);
        return new DataInputStream(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()));
    }
}
