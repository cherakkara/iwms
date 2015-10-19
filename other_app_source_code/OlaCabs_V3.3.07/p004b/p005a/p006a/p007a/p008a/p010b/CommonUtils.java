package p004b.p005a.p006a.p007a.p008a.p010b;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.hardware.SensorManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Debug;
import android.os.StatFs;
import android.provider.Settings.Secure;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.accessibility.AccessibilityEventCompat;
import android.text.TextUtils;
import com.crashlytics.android.core.CrashlyticsCore;
import com.newrelic.agent.android.api.v1.Defaults;
import com.newrelic.agent.android.instrumentation.Trace;
import com.sothree.slidinguppanel.p086a.R.R;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileReader;
import java.io.Flushable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import p004b.p005a.p006a.p007a.Fabric;

/* renamed from: b.a.a.a.a.b.i */
public class CommonUtils {
    public static final Comparator<File> f138a;
    private static Boolean f139b;
    private static final char[] f140c;
    private static long f141d;
    private static Boolean f142e;

    /* renamed from: b.a.a.a.a.b.i.1 */
    static class CommonUtils implements Comparator<File> {
        CommonUtils() {
        }

        public /* synthetic */ int compare(Object obj, Object obj2) {
            return m145a((File) obj, (File) obj2);
        }

        public int m145a(File file, File file2) {
            return (int) (file.lastModified() - file2.lastModified());
        }
    }

    /* renamed from: b.a.a.a.a.b.i.a */
    enum CommonUtils {
        X86_32,
        X86_64,
        ARM_UNKNOWN,
        PPC,
        PPC64,
        ARMV6,
        ARMV7,
        UNKNOWN,
        ARMV7S,
        ARM64;
        
        private static final Map<String, CommonUtils> f136k;

        static {
            f136k = new HashMap(4);
            f136k.put("armeabi-v7a", ARMV7);
            f136k.put("armeabi", ARMV6);
            f136k.put("x86", X86_32);
        }

        static CommonUtils m146a() {
            Object obj = Build.CPU_ABI;
            if (TextUtils.isEmpty(obj)) {
                Fabric.m512h().m474a(CrashlyticsCore.TAG, "Architecture#getValue()::Build.CPU_ABI returned null or empty");
                return UNKNOWN;
            }
            CommonUtils commonUtils = (CommonUtils) f136k.get(obj.toLowerCase(Locale.US));
            if (commonUtils == null) {
                return UNKNOWN;
            }
            return commonUtils;
        }
    }

    static {
        f139b = null;
        f140c = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        f141d = -1;
        f142e = null;
        f138a = new CommonUtils();
    }

    public static SharedPreferences m152a(Context context) {
        return context.getSharedPreferences("com.crashlytics.prefs", 0);
    }

    public static String m154a(File file, String str) {
        Throwable e;
        Throwable th;
        String str2 = null;
        if (file.exists()) {
            Closeable bufferedReader;
            try {
                String[] split;
                bufferedReader = new BufferedReader(new FileReader(file), Defaults.RESPONSE_BODY_LIMIT);
                while (true) {
                    try {
                        CharSequence readLine = bufferedReader.readLine();
                        if (readLine == null) {
                            break;
                        }
                        split = Pattern.compile("\\s*:\\s*").split(readLine, 2);
                        if (split.length > 1 && split[0].equals(str)) {
                            break;
                        }
                    } catch (Exception e2) {
                        e = e2;
                    }
                }
                str2 = split[1];
                CommonUtils.m167a(bufferedReader, "Failed to close system file reader.");
            } catch (Exception e3) {
                e = e3;
                bufferedReader = null;
                try {
                    Fabric.m512h().m482e(CrashlyticsCore.TAG, "Error parsing " + file, e);
                    CommonUtils.m167a(bufferedReader, "Failed to close system file reader.");
                    return str2;
                } catch (Throwable th2) {
                    th = th2;
                    CommonUtils.m167a(bufferedReader, "Failed to close system file reader.");
                    throw th;
                }
            } catch (Throwable e4) {
                bufferedReader = null;
                th = e4;
                CommonUtils.m167a(bufferedReader, "Failed to close system file reader.");
                throw th;
            }
        }
        return str2;
    }

    public static int m147a() {
        return CommonUtils.m146a().ordinal();
    }

    public static synchronized long m171b() {
        long j;
        synchronized (CommonUtils.class) {
            if (f141d == -1) {
                j = 0;
                Object a = CommonUtils.m154a(new File("/proc/meminfo"), "MemTotal");
                if (!TextUtils.isEmpty(a)) {
                    String toUpperCase = a.toUpperCase(Locale.US);
                    try {
                        if (toUpperCase.endsWith("KB")) {
                            j = CommonUtils.m150a(toUpperCase, "KB", (int) Defaults.RESPONSE_BODY_LIMIT);
                        } else if (toUpperCase.endsWith("MB")) {
                            j = CommonUtils.m150a(toUpperCase, "MB", (int) AccessibilityEventCompat.TYPE_TOUCH_INTERACTION_START);
                        } else if (toUpperCase.endsWith("GB")) {
                            j = CommonUtils.m150a(toUpperCase, "GB", 1073741824);
                        } else {
                            Fabric.m512h().m474a(CrashlyticsCore.TAG, "Unexpected meminfo format while computing RAM: " + toUpperCase);
                        }
                    } catch (Throwable e) {
                        Fabric.m512h().m482e(CrashlyticsCore.TAG, "Unexpected meminfo format while computing RAM: " + toUpperCase, e);
                    }
                }
                f141d = j;
            }
            j = f141d;
        }
        return j;
    }

    static long m150a(String str, String str2, int i) {
        return Long.parseLong(str.split(str2)[0].trim()) * ((long) i);
    }

    public static RunningAppProcessInfo m151a(String str, Context context) {
        List<RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses();
        if (runningAppProcesses != null) {
            for (RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                if (runningAppProcessInfo.processName.equals(str)) {
                    return runningAppProcessInfo;
                }
            }
        }
        return null;
    }

    public static String m155a(InputStream inputStream) throws IOException {
        Scanner useDelimiter = new Scanner(inputStream).useDelimiter("\\A");
        return useDelimiter.hasNext() ? useDelimiter.next() : Trace.NULL;
    }

    public static String m157a(String str) {
        return CommonUtils.m158a(str, "SHA-1");
    }

    public static String m176b(InputStream inputStream) {
        return CommonUtils.m156a(inputStream, "SHA-1");
    }

    private static String m156a(InputStream inputStream, String str) {
        try {
            MessageDigest instance = MessageDigest.getInstance("SHA-1");
            byte[] bArr = new byte[Defaults.RESPONSE_BODY_LIMIT];
            while (true) {
                int read = inputStream.read(bArr);
                if (read == -1) {
                    return CommonUtils.m159a(instance.digest());
                }
                instance.update(bArr, 0, read);
            }
        } catch (Throwable e) {
            Fabric.m512h().m482e(CrashlyticsCore.TAG, "Could not calculate hash for app icon.", e);
            return Trace.NULL;
        }
    }

    private static String m160a(byte[] bArr, String str) {
        try {
            MessageDigest instance = MessageDigest.getInstance(str);
            instance.update(bArr);
            return CommonUtils.m159a(instance.digest());
        } catch (Throwable e) {
            Fabric.m512h().m482e(CrashlyticsCore.TAG, "Could not create hashing algorithm: " + str + ", returning empty string.", e);
            return Trace.NULL;
        }
    }

    private static String m158a(String str, String str2) {
        return CommonUtils.m160a(str.getBytes(), str2);
    }

    public static String m161a(String... strArr) {
        if (strArr == null || strArr.length == 0) {
            return null;
        }
        List<String> arrayList = new ArrayList();
        for (String str : strArr) {
            if (str != null) {
                arrayList.add(str.replace("-", Trace.NULL).toLowerCase(Locale.US));
            }
        }
        Collections.sort(arrayList);
        StringBuilder stringBuilder = new StringBuilder();
        for (String append : arrayList) {
            stringBuilder.append(append);
        }
        String append2 = stringBuilder.toString();
        return append2.length() > 0 ? CommonUtils.m157a(append2) : null;
    }

    public static long m172b(Context context) {
        MemoryInfo memoryInfo = new MemoryInfo();
        ((ActivityManager) context.getSystemService("activity")).getMemoryInfo(memoryInfo);
        return memoryInfo.availMem;
    }

    public static long m173b(String str) {
        StatFs statFs = new StatFs(str);
        long blockSize = (long) statFs.getBlockSize();
        return (((long) statFs.getBlockCount()) * blockSize) - (((long) statFs.getAvailableBlocks()) * blockSize);
    }

    public static float m177c(Context context) {
        Intent registerReceiver = context.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        return ((float) registerReceiver.getIntExtra("level", -1)) / ((float) registerReceiver.getIntExtra("scale", -1));
    }

    public static boolean m181d(Context context) {
        if (CommonUtils.m183f(context)) {
            return false;
        }
        return ((SensorManager) context.getSystemService("sensor")).getDefaultSensor(8) != null;
    }

    public static void m164a(Context context, String str) {
        if (CommonUtils.m182e(context)) {
            Fabric.m512h().m474a(CrashlyticsCore.TAG, str);
        }
    }

    public static void m165a(Context context, String str, Throwable th) {
        if (CommonUtils.m182e(context)) {
            Fabric.m512h().m481e(CrashlyticsCore.TAG, str);
        }
    }

    public static void m163a(Context context, int i, String str, String str2) {
        if (CommonUtils.m182e(context)) {
            Fabric.m512h().m472a(i, CrashlyticsCore.TAG, str2);
        }
    }

    public static boolean m182e(Context context) {
        if (f139b == null) {
            f139b = Boolean.valueOf(CommonUtils.m170a(context, "com.crashlytics.Trace", false));
        }
        return f139b.booleanValue();
    }

    public static boolean m170a(Context context, String str, boolean z) {
        if (context == null) {
            return z;
        }
        Resources resources = context.getResources();
        if (resources == null) {
            return z;
        }
        int a = CommonUtils.m148a(context, str, "bool");
        if (a > 0) {
            return resources.getBoolean(a);
        }
        int a2 = CommonUtils.m148a(context, str, "string");
        if (a2 > 0) {
            return Boolean.parseBoolean(context.getString(a2));
        }
        return z;
    }

    public static int m148a(Context context, String str, String str2) {
        return context.getResources().getIdentifier(str, str2, CommonUtils.m187j(context));
    }

    public static boolean m183f(Context context) {
        return "sdk".equals(Build.PRODUCT) || "google_sdk".equals(Build.PRODUCT) || Secure.getString(context.getContentResolver(), "android_id") == null;
    }

    public static boolean m184g(Context context) {
        boolean f = CommonUtils.m183f(context);
        String str = Build.TAGS;
        if ((!f && str != null && str.contains("test-keys")) || new File("/system/app/Superuser.apk").exists()) {
            return true;
        }
        File file = new File("/system/xbin/su");
        if (f || !file.exists()) {
            return false;
        }
        return true;
    }

    public static boolean m178c() {
        return Debug.isDebuggerConnected() || Debug.waitingForDebugger();
    }

    public static int m185h(Context context) {
        int i = 0;
        if (CommonUtils.m183f(context)) {
            i = 1;
        }
        if (CommonUtils.m184g(context)) {
            i |= 2;
        }
        if (CommonUtils.m178c()) {
            return i | 4;
        }
        return i;
    }

    public static int m149a(Context context, boolean z) {
        float c = CommonUtils.m177c(context);
        if (!z) {
            return 1;
        }
        if (z && ((double) c) >= 99.0d) {
            return 3;
        }
        if (!z || ((double) c) >= 99.0d) {
            return 0;
        }
        return 2;
    }

    @SuppressLint({"GetInstance"})
    public static Cipher m162a(int i, String str) throws InvalidKeyException {
        if (str.length() < 32) {
            throw new InvalidKeyException("Key must be at least 32 bytes.");
        }
        Key secretKeySpec = new SecretKeySpec(str.getBytes(), 0, 32, "AES/ECB/PKCS7Padding");
        try {
            Cipher instance = Cipher.getInstance("AES/ECB/PKCS7Padding");
            instance.init(i, secretKeySpec);
            return instance;
        } catch (Throwable e) {
            Fabric.m512h().m482e(CrashlyticsCore.TAG, "Could not create Cipher for AES/ECB/PKCS7Padding - should never happen.", e);
            throw new RuntimeException(e);
        }
    }

    public static String m159a(byte[] bArr) {
        char[] cArr = new char[(bArr.length * 2)];
        for (int i = 0; i < bArr.length; i++) {
            int i2 = bArr[i] & MotionEventCompat.ACTION_MASK;
            cArr[i * 2] = f140c[i2 >>> 4];
            cArr[(i * 2) + 1] = f140c[i2 & 15];
        }
        return new String(cArr);
    }

    public static boolean m186i(Context context) {
        return (context.getApplicationInfo().flags & 2) != 0;
    }

    public static String m175b(Context context, String str) {
        int a = CommonUtils.m148a(context, str, "string");
        if (a > 0) {
            return context.getString(a);
        }
        return Trace.NULL;
    }

    public static void m167a(Closeable closeable, String str) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Throwable e) {
                Fabric.m512h().m482e(CrashlyticsCore.TAG, str, e);
            }
        }
    }

    public static void m168a(Flushable flushable, String str) {
        if (flushable != null) {
            try {
                flushable.flush();
            } catch (Throwable e) {
                Fabric.m512h().m482e(CrashlyticsCore.TAG, str, e);
            }
        }
    }

    public static boolean m180c(String str) {
        return str == null || str.length() == 0;
    }

    public static String m153a(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("value must be zero or greater");
        }
        return String.format(Locale.US, "%1$10s", new Object[]{Integer.valueOf(i)}).replace(' ', '0');
    }

    public static String m187j(Context context) {
        int i = context.getApplicationContext().getApplicationInfo().icon;
        if (i > 0) {
            return context.getResources().getResourcePackageName(i);
        }
        return context.getPackageName();
    }

    public static void m169a(InputStream inputStream, OutputStream outputStream, byte[] bArr) throws IOException {
        while (true) {
            int read = inputStream.read(bArr);
            if (read != -1) {
                outputStream.write(bArr, 0, read);
            } else {
                return;
            }
        }
    }

    public static String m174b(int i) {
        switch (i) {
            case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                return "V";
            case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                return "D";
            case R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
                return "I";
            case R.SlidingUpPanelLayout_umanoDragView /*5*/:
                return "W";
            case R.SlidingUpPanelLayout_umanoOverlay /*6*/:
                return "E";
            case R.SlidingUpPanelLayout_umanoClipPanel /*7*/:
                return "A";
            default:
                return "?";
        }
    }

    public static String m188k(Context context) {
        Closeable openRawResource;
        Throwable e;
        Throwable th;
        String str = null;
        try {
            openRawResource = context.getResources().openRawResource(CommonUtils.m189l(context));
            try {
                String b = CommonUtils.m176b((InputStream) openRawResource);
                if (!CommonUtils.m180c(b)) {
                    str = b;
                }
                CommonUtils.m167a(openRawResource, "Failed to close icon input stream.");
            } catch (Exception e2) {
                e = e2;
                try {
                    Fabric.m512h().m482e(CrashlyticsCore.TAG, "Could not calculate hash for app icon.", e);
                    CommonUtils.m167a(openRawResource, "Failed to close icon input stream.");
                    return str;
                } catch (Throwable th2) {
                    th = th2;
                    CommonUtils.m167a(openRawResource, "Failed to close icon input stream.");
                    throw th;
                }
            }
        } catch (Exception e3) {
            e = e3;
            openRawResource = null;
            Fabric.m512h().m482e(CrashlyticsCore.TAG, "Could not calculate hash for app icon.", e);
            CommonUtils.m167a(openRawResource, "Failed to close icon input stream.");
            return str;
        } catch (Throwable e4) {
            openRawResource = null;
            th = e4;
            CommonUtils.m167a(openRawResource, "Failed to close icon input stream.");
            throw th;
        }
        return str;
    }

    public static int m189l(Context context) {
        return context.getApplicationContext().getApplicationInfo().icon;
    }

    public static String m190m(Context context) {
        int a = CommonUtils.m148a(context, "io.fabric.android.build_id", "string");
        if (a == 0) {
            a = CommonUtils.m148a(context, "com.crashlytics.android.build_id", "string");
        }
        if (a == 0) {
            return null;
        }
        String string = context.getResources().getString(a);
        Fabric.m512h().m474a(CrashlyticsCore.TAG, "Build ID is: " + string);
        return string;
    }

    public static void m166a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (RuntimeException e) {
                throw e;
            } catch (Exception e2) {
            }
        }
    }

    public static boolean m179c(Context context, String str) {
        return context.checkCallingOrSelfPermission(str) == 0;
    }

    public static boolean m191n(Context context) {
        if (!CommonUtils.m179c(context, "android.permission.ACCESS_NETWORK_STATE")) {
            return true;
        }
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }
}
