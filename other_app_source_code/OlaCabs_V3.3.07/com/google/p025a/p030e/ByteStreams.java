package com.google.p025a.p030e;

import com.google.p025a.p026a.Preconditions;
import com.newrelic.agent.android.analytics.AnalyticAttribute;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* renamed from: com.google.a.e.b */
public final class ByteStreams {
    private static final OutputStream f1892a;

    /* renamed from: com.google.a.e.b.1 */
    static class ByteStreams extends OutputStream {
        ByteStreams() {
        }

        public void write(int i) {
        }

        public void write(byte[] bArr) {
            Preconditions.m1817a((Object) bArr);
        }

        public void write(byte[] bArr, int i, int i2) {
            Preconditions.m1817a((Object) bArr);
        }

        public String toString() {
            return "ByteStreams.nullOutputStream()";
        }
    }

    public static long m2993a(InputStream inputStream, OutputStream outputStream) throws IOException {
        Preconditions.m1817a((Object) inputStream);
        Preconditions.m1817a((Object) outputStream);
        byte[] bArr = new byte[AnalyticAttribute.ATTRIBUTE_VALUE_MAX_LENGTH];
        long j = 0;
        while (true) {
            int read = inputStream.read(bArr);
            if (read == -1) {
                return j;
            }
            outputStream.write(bArr, 0, read);
            j += (long) read;
        }
    }

    public static byte[] m2994a(InputStream inputStream) throws IOException {
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ByteStreams.m2993a(inputStream, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    static {
        f1892a = new ByteStreams();
    }
}
