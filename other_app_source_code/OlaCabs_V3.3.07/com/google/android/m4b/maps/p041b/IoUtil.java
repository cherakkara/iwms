package com.google.android.m4b.maps.p041b;

import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.m4b.maps.p049i.ByteArrayDataInput;
import java.io.ByteArrayOutputStream;
import java.io.DataInput;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* renamed from: com.google.android.m4b.maps.b.g */
public final class IoUtil {
    static {
        char[] cArr = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    }

    private static byte[] m7772a(InputStream inputStream, int i) {
        OutputStream byteArrayOutputStream;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream(32);
            IoUtil.m7769a(inputStream, byteArrayOutputStream);
            IoUtil.m7770a(byteArrayOutputStream);
            byte[] toByteArray = byteArrayOutputStream.toByteArray();
            IoUtil.m7773b(inputStream);
            return toByteArray;
        } catch (Throwable th) {
            IoUtil.m7773b(inputStream);
        }
    }

    public static byte[] m7771a(InputStream inputStream) {
        return IoUtil.m7772a(inputStream, 32);
    }

    private static void m7769a(InputStream inputStream, OutputStream outputStream) {
        byte[] bArr = new byte[AccessibilityNodeInfoCompat.ACTION_PREVIOUS_AT_MOVEMENT_GRANULARITY];
        while (true) {
            int read = inputStream.read(bArr);
            if (read != -1) {
                outputStream.write(bArr, 0, read);
            } else {
                return;
            }
        }
    }

    public static void m7773b(InputStream inputStream) {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e) {
            }
        }
    }

    public static void m7770a(OutputStream outputStream) {
        if (outputStream != null) {
            try {
                outputStream.close();
            } catch (IOException e) {
            }
        }
    }

    public static DataInput m7768a(byte[] bArr) {
        return new ByteArrayDataInput(bArr);
    }
}
