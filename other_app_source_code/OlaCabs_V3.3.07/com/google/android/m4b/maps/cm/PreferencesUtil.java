package com.google.android.m4b.maps.cm;

import com.google.android.m4b.maps.p041b.PersistentStore;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/* renamed from: com.google.android.m4b.maps.cm.c */
public final class PreferencesUtil {
    private final PersistentStore f7295a;

    public PreferencesUtil(PersistentStore persistentStore) {
        this.f7295a = persistentStore;
    }

    public final void m10156a(String str, String str2) {
        if (str2 == null) {
            this.f7295a.m7754a(str, null);
            return;
        }
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutput dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        try {
            if (str2 instanceof Boolean) {
                dataOutputStream.writeBoolean(((Boolean) str2).booleanValue());
            } else if (str2 instanceof String) {
                dataOutputStream.writeUTF(str2);
            } else if (str2 instanceof Integer) {
                dataOutputStream.writeInt(((Integer) str2).intValue());
            } else if (str2 instanceof Long) {
                dataOutputStream.writeLong(((Long) str2).longValue());
            } else {
                throw new IllegalArgumentException("Bad type: " + str2.getClass() + " for " + str);
            }
            this.f7295a.m7754a(str, byteArrayOutputStream.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public final String m10155a(String str) {
        return (String) m10154a(str, 3);
    }

    private Object m10154a(String str, int i) {
        Object obj = null;
        DataInput b = m10157b(str);
        if (b != null) {
            try {
                obj = b.readUTF();
            } catch (IOException e) {
            }
        }
        return obj;
    }

    public final DataInput m10157b(String str) {
        byte[] a = this.f7295a.m7755a(str);
        if (a == null) {
            return null;
        }
        return new DataInputStream(new ByteArrayInputStream(a));
    }
}
