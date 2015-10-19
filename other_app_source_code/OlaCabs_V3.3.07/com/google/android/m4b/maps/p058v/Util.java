package com.google.android.m4b.maps.p058v;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Environment;
import android.os.StatFs;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.util.Log;
import android.util.Pair;
import com.google.android.m4b.maps.ah.EventLog;
import com.google.android.m4b.maps.an.ac;
import com.google.android.m4b.maps.an.ag;
import com.google.android.m4b.maps.an.ai;
import com.google.android.m4b.maps.p040u.DataRequestDispatcher;
import com.google.android.m4b.maps.p040u.DataRequestDispatcherInterface;
import com.google.android.m4b.maps.p046d.ProtoBuf;
import com.google.android.m4b.maps.p046d.ProtoBufType;
import com.newrelic.agent.android.analytics.AnalyticAttribute;
import com.newrelic.agent.android.instrumentation.Trace;
import com.olacabs.customer.p076d.br;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.zip.CRC32;
import org.apache.http.protocol.HTTP;

/* renamed from: com.google.android.m4b.maps.v.d */
public final class Util {
    private static boolean f8002a;
    private static int f8003b;
    private static final long[] f8004c;
    private static int f8005d;

    static {
        int i = 0;
        f8002a = false;
        f8003b = 0;
        long[] jArr = new long[22];
        long j = 0;
        for (int i2 = 0; i2 < 22; i2++) {
            j += 1 << (i2 * 2);
            jArr[i2] = j - 1;
        }
        f8004c = jArr;
        j = jArr[21];
        if (j < 0) {
            i = 64;
        } else {
            while (j != 0) {
                j >>= 1;
                i++;
            }
        }
        f8005d = i;
    }

    public static void m11552a(String str, Throwable th) {
        if (!f8002a) {
            System.err.println(str);
            System.err.println(Log.getStackTraceString(th));
        }
        EventLog.m4911b(new EventLog.EventLog(th));
    }

    public static void m11550a(String str, String str2) {
        if (!f8002a) {
            System.err.println(str2);
        }
        EventLog.m4911b(new EventLog.EventLog(str, str2));
    }

    private static void m11551a(String str, String str2, boolean z) {
        DataRequestDispatcherInterface b = DataRequestDispatcher.m11411b();
        if (b != null && str != null && str2 != null) {
            int i = f8003b + 1;
            f8003b = i;
            if (i <= 10) {
                OutputStream byteArrayOutputStream = new ByteArrayOutputStream(AccessibilityNodeInfoCompat.ACTION_PREVIOUS_AT_MOVEMENT_GRANULARITY);
                DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
                try {
                    dataOutputStream.writeUTF(new StringBuilder(AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS).append("DA:").append(str).toString());
                    dataOutputStream.writeUTF("DA");
                    dataOutputStream.writeUTF(str2);
                } catch (IOException e) {
                }
                b.m11371a(8, byteArrayOutputStream.toByteArray(), z, false);
            }
        }
    }

    public static void m11555b(String str, String str2) {
        Util.m11551a(str, str2, true);
    }

    public static void m11559c(String str, String str2) {
        Util.m11551a(str, str2, false);
    }

    public static void m11549a() {
        f8002a = true;
    }

    public static boolean m11556b() {
        return Environment.getExternalStorageState().equals("mounted");
    }

    public static ProtoBuf m11547a(Context context, String str, ProtoBufType protoBufType) {
        DataInputStream dataInputStream;
        DataInputStream dataInputStream2;
        String str2;
        Throwable th;
        String str3;
        try {
            dataInputStream = new DataInputStream(new BufferedInputStream(context.openFileInput(str), AnalyticAttribute.ATTRIBUTE_VALUE_MAX_LENGTH));
            try {
                ProtoBuf protoBuf = new ProtoBuf(protoBufType);
                protoBuf.m10187a((InputStream) dataInputStream);
                try {
                    dataInputStream.close();
                    return protoBuf;
                } catch (IOException e) {
                    return protoBuf;
                }
            } catch (IOException e2) {
                dataInputStream2 = dataInputStream;
                try {
                    str2 = "MapsNavigation";
                    "readProtoFromFile failed: " + str;
                    if (dataInputStream2 != null) {
                        try {
                            dataInputStream2.close();
                        } catch (IOException e3) {
                        }
                    }
                    return null;
                } catch (Throwable th2) {
                    dataInputStream = dataInputStream2;
                    th = th2;
                    if (dataInputStream != null) {
                        try {
                            dataInputStream.close();
                        } catch (IOException e4) {
                        }
                    }
                    throw th;
                }
            } catch (RuntimeException e5) {
                try {
                    str3 = "MapsNavigation";
                    "readProtoFromFile failed: " + str;
                    if (dataInputStream != null) {
                        try {
                            dataInputStream.close();
                        } catch (IOException e6) {
                        }
                    }
                    return null;
                } catch (Throwable th3) {
                    th = th3;
                    if (dataInputStream != null) {
                        dataInputStream.close();
                    }
                    throw th;
                }
            }
        } catch (IOException e7) {
            dataInputStream2 = null;
            str2 = "MapsNavigation";
            "readProtoFromFile failed: " + str;
            if (dataInputStream2 != null) {
                dataInputStream2.close();
            }
            return null;
        } catch (RuntimeException e8) {
            dataInputStream = null;
            str3 = "MapsNavigation";
            "readProtoFromFile failed: " + str;
            if (dataInputStream != null) {
                dataInputStream.close();
            }
            return null;
        } catch (Throwable th4) {
            th = th4;
            dataInputStream = null;
            if (dataInputStream != null) {
                dataInputStream.close();
            }
            throw th;
        }
    }

    public static boolean m11553a(Context context, ProtoBuf protoBuf, String str) {
        DataOutputStream dataOutputStream;
        IOException e;
        String str2;
        try {
            dataOutputStream = new DataOutputStream(context.openFileOutput(str, 0));
            try {
                protoBuf.m10199b((OutputStream) dataOutputStream);
                dataOutputStream.close();
                return true;
            } catch (IOException e2) {
                e = e2;
                str2 = "MapsNavigation";
                "writeProtoToFile failed: " + e.getMessage();
                if (dataOutputStream != null) {
                    return false;
                }
                try {
                    dataOutputStream.close();
                } catch (IOException e3) {
                }
                context.getFileStreamPath(str).delete();
                return false;
            }
        } catch (IOException e4) {
            e = e4;
            dataOutputStream = null;
            str2 = "MapsNavigation";
            "writeProtoToFile failed: " + e.getMessage();
            if (dataOutputStream != null) {
                return false;
            }
            dataOutputStream.close();
            context.getFileStreamPath(str).delete();
            return false;
        }
    }

    private static File m11560d(Context context) {
        if (!Util.m11556b()) {
            return context.getDir(Trace.NULL, 0);
        }
        return new File(Environment.getExternalStorageDirectory(), "Android/data/" + context.getPackageName());
    }

    public static File m11548a(Context context) {
        return new File(Util.m11560d(context), "testdata");
    }

    public static File m11554b(Context context) {
        return new File(Util.m11560d(context), "debug");
    }

    public static File m11558c(Context context) {
        return new File(Util.m11560d(context), "cache");
    }

    public static long m11557c() {
        long j = 0;
        if (!Util.m11556b()) {
            return j;
        }
        try {
            StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
            return ((long) statFs.getAvailableBlocks()) * ((long) statFs.getBlockSize());
        } catch (IllegalArgumentException e) {
            return j;
        }
    }

    public static Pair<Long, String> m11545a(ai aiVar, ac acVar) {
        long b;
        ac a = acVar.m5434a(acVar.m5447j().m5471a(aiVar));
        ag j = a.m5447j();
        if (j.m5475b()) {
            b = ((((long) (a.m5439b() & 31)) << 58) | (((long) (a.m5440c() & 536870911)) << 29)) | ((long) (a.m5441d() & 536870911));
        } else {
            int b2 = a.m5439b();
            b = ((b2 == 0 ? 0 : f8004c[b2 - 1] + 1) + ((((long) a.m5441d()) << b2) + ((long) a.m5440c()))) | (Long.MIN_VALUE | (((long) j.hashCode()) << f8005d));
        }
        Long valueOf = Long.valueOf(b);
        ag j2 = a.m5447j();
        return new Pair(valueOf, j2.m5475b() ? null : j2.toString());
    }

    public static ac m11546a(long j) {
        if (j < 0) {
            return null;
        }
        return new ac(((int) (j >> 58)) & 31, ((int) (j >> 29)) & 536870911, ((int) j) & 536870911);
    }

    public static long m11543a(String str) {
        CRC32 crc32 = new CRC32();
        try {
            crc32.update(str.getBytes(HTTP.UTF_8));
        } catch (UnsupportedEncodingException e) {
        }
        return (crc32.getValue() << 32) | (((long) str.hashCode()) & 4294967295L);
    }

    public static Bitmap m11544a(Bitmap bitmap) {
        Matrix matrix = new Matrix();
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        matrix.setPolyToPoly(new float[]{0.0f, 0.0f, 0.0f, (float) height, (float) width, 0.0f, (float) width, (float) height}, 0, new float[]{0.0f, (float) height, 0.0f, 0.0f, (float) width, (float) height, (float) width, 0.0f}, 0, 4);
        ColorMatrix colorMatrix = new ColorMatrix(new float[]{0.0f, 0.0f, br.DEFAULT_BACKOFF_MULT, 0.0f, 0.0f, 0.0f, br.DEFAULT_BACKOFF_MULT, 0.0f, 0.0f, 0.0f, br.DEFAULT_BACKOFF_MULT, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, br.DEFAULT_BACKOFF_MULT, 0.0f});
        Paint paint = new Paint();
        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        new Canvas(bitmap).drawBitmap(Bitmap.createBitmap(bitmap), matrix, paint);
        return bitmap;
    }
}
