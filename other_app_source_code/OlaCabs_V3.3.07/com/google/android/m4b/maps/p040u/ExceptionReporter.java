package com.google.android.m4b.maps.p040u;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import org.apache.http.HttpStatus;

/* renamed from: com.google.android.m4b.maps.u.k */
public final class ExceptionReporter {
    private static int f7937a;
    private static final Object f7938b;
    private static String f7939c;

    static {
        f7937a = 0;
        f7938b = new Object();
        f7939c = null;
    }

    public static void m11467a(String str, Throwable th) {
        String str2 = str + ": " + th.toString();
        if (str2 != null) {
            synchronized (f7938b) {
                if (f7939c == null) {
                    f7939c = str2;
                } else {
                    f7939c += "\n" + str2;
                }
                if (f7939c.length() > HttpStatus.SC_MULTIPLE_CHOICES) {
                    f7939c = f7939c.substring(0, HttpStatus.SC_MULTIPLE_CHOICES);
                }
            }
        }
        if (!"REQUEST".equals(str)) {
            int i = f7937a + 1;
            f7937a = i;
            if (i <= 10) {
                OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
                try {
                    dataOutputStream.writeUTF(str);
                    dataOutputStream.writeUTF(th.getClass().toString());
                    Writer stringWriter = new StringWriter();
                    th.printStackTrace(new PrintWriter(stringWriter));
                    dataOutputStream.writeUTF(stringWriter.toString());
                    DataRequestDispatcher a = DataRequestDispatcher.m11393a();
                    if (a != null) {
                        a.m11440a(8, byteArrayOutputStream.toByteArray(), true, false);
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e.toString());
                }
            }
        }
    }
}
