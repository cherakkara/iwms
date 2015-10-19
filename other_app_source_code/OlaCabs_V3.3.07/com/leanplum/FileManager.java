package com.leanplum;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.util.Log;
import com.newrelic.agent.android.instrumentation.Trace;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

final class FileManager {
    static Var<HashMap<String, Object>> f8547a;
    public static final Object f8548b;
    private static C0638l f8549c;
    private static boolean f8550d;
    private static boolean f8551e;

    public enum DownloadFileResult {
        NONE,
        EXISTS_IN_ASSETS,
        DOWNLOADING
    }

    static {
        f8547a = null;
        f8550d = false;
        f8551e = false;
        f8548b = new Object();
    }

    public static DownloadFileResult m12415a(boolean z, String str, String str2, Runnable runnable) {
        if (!(str == null || str.equals(str2) || (z && aT.m12674c(str) != 0))) {
            try {
                if (Leanplum.m12431a().getResources().getAssets().open(str) != null) {
                    return DownloadFileResult.EXISTS_IN_ASSETS;
                }
            } catch (IOException e) {
            }
            if (!(m12428d(m12425b(str)) || m12428d(m12427c(str)))) {
                C0618S a = C0618S.m12518a("downloadFile", null);
                a.m12551a(new C0634h(runnable));
                a.m12550a(new C0635i(runnable));
                a.m12553d(str);
                return DownloadFileResult.DOWNLOADING;
            }
        }
        return DownloadFileResult.NONE;
    }

    public static C0637k m12416a(InputStream inputStream) {
        Throwable th;
        int i = 0;
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            try {
                InputStream digestInputStream = new DigestInputStream(inputStream, instance);
                try {
                    byte[] bArr = new byte[AccessibilityNodeInfoCompat.ACTION_SCROLL_BACKWARD];
                    int i2 = 0;
                    while (true) {
                        int read = digestInputStream.read(bArr);
                        if (read == -1) {
                            break;
                        }
                        i2 += read;
                    }
                    digestInputStream.close();
                    byte[] digest = instance.digest();
                    StringBuffer stringBuffer = new StringBuffer();
                    while (i < digest.length) {
                        String toHexString = Integer.toHexString(digest[i] & MotionEventCompat.ACTION_MASK);
                        if (toHexString.length() == 1) {
                            stringBuffer.append('0');
                        }
                        stringBuffer.append(toHexString);
                        i++;
                    }
                    return new C0637k(stringBuffer.toString(), i2);
                } catch (Throwable th2) {
                    th = th2;
                    inputStream = digestInputStream;
                    inputStream.close();
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                inputStream.close();
                throw th;
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static int m12414a(String str) {
        return (int) new File(str).length();
    }

    private static boolean m12428d(String str) {
        return new File(str).exists();
    }

    private static String m12418a(String str, String str2) {
        return new StringBuilder(String.valueOf(str)).append("/").append(str2).toString();
    }

    public static String m12425b(String str) {
        if (str == null) {
            return null;
        }
        return m12418a(Trace.NULL, str);
    }

    private static String m12429e(String str) {
        return m12418a(Leanplum.m12431a().getDir("Leanplum_Bundle", 0).getAbsolutePath(), str);
    }

    public static String m12427c(String str) {
        return m12418a(Leanplum.m12431a().getDir("Leanplum_Documents", 0).getAbsolutePath(), str);
    }

    public static boolean m12424a(Map<String, Object> map, Map<String, Object> map2) {
        if (map2 == null) {
            return true;
        }
        String str = (String) map.get("hash");
        String str2 = (String) map2.get("hash");
        Integer num = (Integer) map.get("size");
        Integer num2 = (Integer) map2.get("size");
        if (num2 == null || !num.equals(num2)) {
            return true;
        }
        return str != null && (str2 == null || !str.equals(str2));
    }

    public static void m12420a(C0638l c0638l) {
        f8549c = c0638l;
    }

    public static boolean m12423a() {
        return f8551e;
    }

    private static void m12426b(List<String> list, List<String> list2) {
        ZipInputStream zipInputStream;
        Throwable e;
        f8547a = Var.define("__Android Resources", new HashMap());
        String str = "res/drawable";
        String str2 = "res/layout";
        try {
            zipInputStream = new ZipInputStream(new FileInputStream(Leanplum.m12431a().getPackageResourcePath()));
            while (true) {
                ZipEntry nextEntry = zipInputStream.getNextEntry();
                if (nextEntry == null) {
                    try {
                        break;
                    } catch (Throwable e2) {
                        Log.e("tag", "Error", e2);
                    }
                } else {
                    try {
                        String name = nextEntry.getName();
                        if (name.startsWith(str) || name.startsWith(str2)) {
                            boolean z;
                            String substring = name.substring(4);
                            if (list != null && list.size() > 0) {
                                for (String matches : list) {
                                    if (substring.matches(matches)) {
                                        z = true;
                                        break;
                                    }
                                }
                                z = false;
                                if (!z) {
                                }
                            }
                            if (list2 != null) {
                                for (String matches2 : list2) {
                                    if (substring.matches(matches2)) {
                                        z = true;
                                        break;
                                    }
                                }
                                z = false;
                                if (z) {
                                }
                            }
                            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                            byte[] bArr = new byte[AccessibilityNodeInfoCompat.ACTION_SCROLL_BACKWARD];
                            int i = 0;
                            while (true) {
                                int read = zipInputStream.read(bArr);
                                if (read == -1) {
                                    break;
                                }
                                byteArrayOutputStream.write(bArr, 0, read);
                                i += read;
                            }
                            zipInputStream.closeEntry();
                            Var.m12582a("__Android Resources." + substring.replace(".", "\\.").replace('/', '.'), name, i, nextEntry.getTime() + i, byteArrayOutputStream.toByteArray());
                        }
                    } catch (IOException e3) {
                        e2 = e3;
                    }
                }
            }
            zipInputStream.close();
        } catch (IOException e4) {
            e2 = e4;
            zipInputStream = null;
            try {
                Log.e("tag", "Error", e2);
                if (zipInputStream != null) {
                    try {
                        zipInputStream.close();
                    } catch (Throwable e22) {
                        Log.e("tag", "Error", e22);
                    }
                }
                f8550d = true;
                synchronized (f8548b) {
                    f8551e = false;
                    if (f8549c != null) {
                        f8549c.m12778a();
                    }
                }
            } catch (Throwable th) {
                e22 = th;
                if (zipInputStream != null) {
                    try {
                        zipInputStream.close();
                    } catch (Throwable e5) {
                        Log.e("tag", "Error", e5);
                    }
                }
                throw e22;
            }
        } catch (Throwable th2) {
            e22 = th2;
            zipInputStream = null;
            if (zipInputStream != null) {
                zipInputStream.close();
            }
            throw e22;
        }
        f8550d = true;
        synchronized (f8548b) {
            f8551e = false;
            if (f8549c != null) {
                f8549c.m12778a();
            }
        }
    }

    public static void m12422a(List<String> list, List<String> list2, boolean z) {
        f8551e = true;
        if (!f8550d) {
            if (z) {
                Util.m12568a(new C0636j(list, list2), new Void[0]);
            } else {
                m12426b(list, list2);
            }
        }
    }

    public static String m12419a(String str, String str2, Boolean bool) {
        String b;
        if (str.equals(str2)) {
            b = m12425b(str2.toString());
            if (m12428d(b)) {
                return b;
            }
        }
        if (bool == null) {
            try {
                Leanplum.m12431a().getAssets().open(str);
                return str;
            } catch (IOException e) {
            }
        } else {
            if (bool.booleanValue()) {
                return str;
            }
            b = m12429e(str);
            if (!m12428d(b)) {
                b = m12427c(str);
                if (!m12428d(b)) {
                    b = m12425b(str);
                    if (!m12428d(b)) {
                        b = m12429e(str2.toString());
                        if (!m12428d(b)) {
                            b = m12425b(str2.toString());
                            if (!m12428d(b)) {
                                return str2.toString();
                            }
                        }
                    }
                }
            }
            return b;
        }
    }

    static InputStream m12417a(boolean z, Boolean bool, Boolean bool2, String str, String str2, byte[] bArr) {
        if (str == null || str.equals(Trace.NULL)) {
            return null;
        }
        if (z) {
            try {
                if (str.equals(str2)) {
                    return new ByteArrayInputStream(bArr);
                }
            } catch (Throwable e) {
                Log.e("Leanplum", "Error loading stream", e);
                return null;
            }
        }
        Context a = Leanplum.m12431a();
        if (bool2 == null) {
            try {
                return a.getAssets().open(str);
            } catch (IOException e2) {
            }
        } else {
            if (bool2.booleanValue() || (bool.booleanValue() && str.equals(str2))) {
                return a.getAssets().open(str);
            }
            return new FileInputStream(new File(str));
        }
    }
}
