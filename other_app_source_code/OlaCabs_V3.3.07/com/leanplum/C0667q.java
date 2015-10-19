package com.leanplum;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

/* renamed from: com.leanplum.q */
final class C0667q extends DataInputStream {
    public C0667q(InputStream inputStream) {
        super(inputStream);
    }

    public final byte[] m12790a(int i) {
        byte[] bArr = new byte[i];
        int i2 = 0;
        while (i2 < i) {
            int read = read(bArr, i2, i - i2);
            if (read == -1) {
                break;
            }
            i2 += read;
        }
        if (i2 == i) {
            return bArr;
        }
        throw new IOException(String.format("Read wrong number of bytes. Got: %s, Expected: %s.", new Object[]{Integer.valueOf(i2), Integer.valueOf(i)}));
    }
}
