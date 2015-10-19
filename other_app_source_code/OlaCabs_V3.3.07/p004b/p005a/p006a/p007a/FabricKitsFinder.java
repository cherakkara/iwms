package p004b.p005a.p006a.p007a;

import android.os.SystemClock;
import android.text.TextUtils;
import com.crashlytics.android.core.CrashlyticsCore;
import java.io.Closeable;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Callable;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import p004b.p005a.p006a.p007a.p008a.p010b.CommonUtils;

/* renamed from: b.a.a.a.e */
class FabricKitsFinder implements Callable<Map<String, KitInfo>> {
    final String f429a;

    public /* synthetic */ Object call() throws Exception {
        return m527a();
    }

    FabricKitsFinder(String str) {
        this.f429a = str;
    }

    public Map<String, KitInfo> m527a() throws Exception {
        Map<String, KitInfo> hashMap = new HashMap();
        long elapsedRealtime = SystemClock.elapsedRealtime();
        ZipFile b = m528b();
        Enumeration entries = b.entries();
        int i = 0;
        while (entries.hasMoreElements()) {
            int i2 = i + 1;
            ZipEntry zipEntry = (ZipEntry) entries.nextElement();
            if (zipEntry.getName().startsWith("fabric/")) {
                KitInfo a = m526a(zipEntry, b);
                if (a != null) {
                    hashMap.put(a.m537a(), a);
                    Fabric.m512h().m477b(CrashlyticsCore.TAG, String.format("Found kit:[%s] version:[%s]", new Object[]{a.m537a(), a.m538b()}));
                }
            }
            i = i2;
        }
        if (b != null) {
            try {
                b.close();
            } catch (IOException e) {
            }
        }
        Fabric.m512h().m477b(CrashlyticsCore.TAG, "finish scanning in " + (SystemClock.elapsedRealtime() - elapsedRealtime) + " reading:" + i);
        return hashMap;
    }

    private KitInfo m526a(ZipEntry zipEntry, ZipFile zipFile) {
        Throwable e;
        Closeable inputStream;
        try {
            inputStream = zipFile.getInputStream(zipEntry);
            try {
                Properties properties = new Properties();
                properties.load(inputStream);
                Object property = properties.getProperty("fabric-identifier");
                Object property2 = properties.getProperty("fabric-version");
                String property3 = properties.getProperty("fabric-build-type");
                if (TextUtils.isEmpty(property) || TextUtils.isEmpty(property2)) {
                    throw new IllegalStateException("Invalid format of fabric file," + zipEntry.getName());
                }
                KitInfo kitInfo = new KitInfo(property, property2, property3);
                CommonUtils.m166a(inputStream);
                return kitInfo;
            } catch (IOException e2) {
                e = e2;
                try {
                    Fabric.m512h().m482e(CrashlyticsCore.TAG, "Error when parsing fabric properties " + zipEntry.getName(), e);
                    CommonUtils.m166a(inputStream);
                    return null;
                } catch (Throwable th) {
                    e = th;
                    CommonUtils.m166a(inputStream);
                    throw e;
                }
            }
        } catch (IOException e3) {
            e = e3;
            inputStream = null;
            Fabric.m512h().m482e(CrashlyticsCore.TAG, "Error when parsing fabric properties " + zipEntry.getName(), e);
            CommonUtils.m166a(inputStream);
            return null;
        } catch (Throwable th2) {
            e = th2;
            inputStream = null;
            CommonUtils.m166a(inputStream);
            throw e;
        }
    }

    protected ZipFile m528b() throws IOException {
        return new ZipFile(this.f429a);
    }
}
