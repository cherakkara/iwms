package com.google.android.m4b.maps.p045c;

import android.content.Context;
import com.google.android.m4b.maps.p041b.BasePersistentStore;
import com.google.android.m4b.maps.p041b.PersistentStore;
import com.newrelic.agent.android.analytics.AnalyticAttribute;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;

/* renamed from: com.google.android.m4b.maps.c.b */
public final class AndroidPersistentStore extends BasePersistentStore implements PersistentStore {
    private Context f7225a;

    public AndroidPersistentStore(Context context) {
        Collections.synchronizedSet(new HashSet());
        this.f7225a = context;
    }

    private static String m10064d(String str) {
        return "DATA_" + str;
    }

    public final boolean m10066b(String str) {
        return this.f7225a.deleteFile(AndroidPersistentStore.m10064d(str));
    }

    private int m10063b(byte[] bArr, String str) {
        if (bArr == null) {
            try {
                bArr = new byte[0];
            } catch (FileNotFoundException e) {
                throw new PersistentStore.PersistentStore(e.getMessage(), -1);
            } catch (IOException e2) {
                throw new PersistentStore.PersistentStore(e2.getMessage(), -1);
            }
        }
        FileOutputStream openFileOutput = this.f7225a.openFileOutput(AndroidPersistentStore.m10064d(str), 0);
        openFileOutput.write(bArr);
        openFileOutput.close();
        return (((bArr.length - 1) / AnalyticAttribute.ATTRIBUTE_VALUE_MAX_LENGTH) + 1) * AnalyticAttribute.ATTRIBUTE_VALUE_MAX_LENGTH;
    }

    public final int m10065a(byte[] bArr, String str) {
        try {
            return m10063b(bArr, str);
        } catch (PersistentStore.PersistentStore e) {
            return e.m7782a();
        }
    }

    public final byte[] m10067c(String str) {
        FileInputStream openFileInput;
        FileInputStream fileInputStream;
        Throwable th;
        try {
            String d = AndroidPersistentStore.m10064d(str);
            openFileInput = this.f7225a.openFileInput(d);
            try {
                int length = (int) this.f7225a.getFileStreamPath(d).length();
                byte[] bArr = new byte[length];
                int i = 0;
                while (length > 0) {
                    int read = openFileInput.read(bArr, i, length);
                    if (read == -1) {
                        throw new IOException("Read " + i + " bytes from " + str + "; expected " + length + " more");
                    }
                    length -= read;
                    i += read;
                }
                if (openFileInput == null) {
                    return bArr;
                }
                try {
                    openFileInput.close();
                    return bArr;
                } catch (IOException e) {
                    return bArr;
                }
            } catch (FileNotFoundException e2) {
                fileInputStream = openFileInput;
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (IOException e3) {
                    }
                }
                return null;
            } catch (IOException e4) {
                if (openFileInput != null) {
                    try {
                        openFileInput.close();
                    } catch (IOException e5) {
                    }
                }
                return null;
            } catch (Throwable th2) {
                th = th2;
                if (openFileInput != null) {
                    try {
                        openFileInput.close();
                    } catch (IOException e6) {
                    }
                }
                throw th;
            }
        } catch (FileNotFoundException e7) {
            fileInputStream = null;
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            return null;
        } catch (IOException e8) {
            openFileInput = null;
            if (openFileInput != null) {
                openFileInput.close();
            }
            return null;
        } catch (Throwable th3) {
            th = th3;
            openFileInput = null;
            if (openFileInput != null) {
                openFileInput.close();
            }
            throw th;
        }
    }
}
