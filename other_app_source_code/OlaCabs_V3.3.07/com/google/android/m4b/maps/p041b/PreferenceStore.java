package com.google.android.m4b.maps.p041b;

import com.newrelic.agent.android.tracing.ActivityTrace;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.Hashtable;

/* renamed from: com.google.android.m4b.maps.b.j */
public final class PreferenceStore {
    private final PersistentStore f5028a;
    private Hashtable<String, byte[]> f5029b;
    private boolean f5030c;

    public PreferenceStore(PersistentStore persistentStore) {
        this.f5029b = null;
        this.f5030c = false;
        this.f5028a = persistentStore;
    }

    public final synchronized boolean m7786a(String str, byte[] bArr) {
        boolean z = true;
        synchronized (this) {
            m7784b();
            this.f5030c = true;
            if (bArr != null) {
                this.f5029b.put(str, bArr);
            } else if (this.f5029b.remove(str) == null) {
                z = false;
            }
        }
        return z;
    }

    public final synchronized byte[] m7787a(String str) {
        m7784b();
        return (byte[]) this.f5029b.get(str);
    }

    private static Hashtable<String, byte[]> m7783a(byte[] bArr) {
        DataInput dataInputStream = new DataInputStream(new ByteArrayInputStream(bArr));
        Hashtable<String, byte[]> hashtable = new Hashtable();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Preferences {");
        try {
            int readUnsignedShort = dataInputStream.readUnsignedShort();
            stringBuilder.append("numPrefs: ");
            stringBuilder.append(readUnsignedShort);
            for (int i = 0; i < readUnsignedShort; i++) {
                stringBuilder.append(i);
                stringBuilder.append("(");
                String readUTF = dataInputStream.readUTF();
                stringBuilder.append(readUTF);
                stringBuilder.append(":");
                int readUnsignedShort2 = dataInputStream.readUnsignedShort();
                stringBuilder.append(readUnsignedShort2);
                stringBuilder.append(":");
                Object obj = new byte[readUnsignedShort2];
                dataInputStream.readFully(obj);
                stringBuilder.append(new String(obj));
                hashtable.put(readUTF, obj);
                stringBuilder.append(")");
            }
        } catch (IOException e) {
        }
        return hashtable;
    }

    private synchronized void m7784b() {
        if (this.f5029b == null) {
            byte[] c = this.f5028a.m7757c("Preferences");
            if (c != null) {
                this.f5029b = PreferenceStore.m7783a(c);
            } else {
                this.f5028a.m7752a(new byte[ActivityTrace.MAX_TRACES], "Preferences");
                this.f5029b = new Hashtable();
            }
            this.f5030c = false;
        }
    }

    public final synchronized void m7785a() {
        if (this.f5030c) {
            try {
                Hashtable hashtable = this.f5029b;
                OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                DataOutput dataOutputStream = new DataOutputStream(byteArrayOutputStream);
                dataOutputStream.writeShort(hashtable.size());
                Enumeration keys = hashtable.keys();
                while (keys.hasMoreElements()) {
                    String str = (String) keys.nextElement();
                    byte[] bArr = (byte[]) hashtable.get(str);
                    if (bArr == null) {
                        bArr = new byte[0];
                    }
                    dataOutputStream.writeUTF(str);
                    dataOutputStream.writeShort(bArr.length);
                    dataOutputStream.write(bArr);
                }
                byte[] toByteArray = byteArrayOutputStream.toByteArray();
                if (toByteArray.length <= ActivityTrace.MAX_TRACES) {
                    Object obj = new byte[ActivityTrace.MAX_TRACES];
                    System.arraycopy(toByteArray, 0, obj, 0, toByteArray.length);
                    Object obj2 = obj;
                }
                this.f5028a.m7752a(toByteArray, "Preferences");
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.f5030c = false;
        }
    }
}
