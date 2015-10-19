package p004b.p005a.p006a.p007a.p008a.p016g;

import com.crashlytics.android.core.CrashlyticsCore;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import org.json.JSONObject;
import p004b.p005a.p006a.p007a.Fabric;
import p004b.p005a.p006a.p007a.Kit;
import p004b.p005a.p006a.p007a.p008a.p010b.CommonUtils;
import p004b.p005a.p006a.p007a.p008a.p015f.FileStoreImpl;

/* renamed from: b.a.a.a.a.g.i */
class DefaultCachedSettingsIo implements CachedSettingsIo {
    private final Kit f341a;

    public DefaultCachedSettingsIo(Kit kit) {
        this.f341a = kit;
    }

    public JSONObject m429a() {
        Closeable fileInputStream;
        Closeable closeable;
        Throwable th;
        Throwable th2;
        JSONObject jSONObject = null;
        Fabric.m512h().m474a(CrashlyticsCore.TAG, "Reading cached settings...");
        try {
            File file = new File(new FileStoreImpl(this.f341a).m413a(), "com.crashlytics.settings.json");
            if (file.exists()) {
                fileInputStream = new FileInputStream(file);
                try {
                    jSONObject = JSONObjectInstrumentation.init(CommonUtils.m155a((InputStream) fileInputStream));
                } catch (Throwable e) {
                    Throwable th3 = e;
                    closeable = fileInputStream;
                    th = th3;
                    try {
                        Fabric.m512h().m482e(CrashlyticsCore.TAG, "Failed to fetch cached settings", th);
                        CommonUtils.m167a(closeable, "Error while closing settings cache file.");
                        return jSONObject;
                    } catch (Throwable th4) {
                        th2 = th4;
                        CommonUtils.m167a(closeable, "Error while closing settings cache file.");
                        throw th2;
                    }
                } catch (Throwable th5) {
                    th2 = th5;
                    closeable = fileInputStream;
                    CommonUtils.m167a(closeable, "Error while closing settings cache file.");
                    throw th2;
                }
            }
            Fabric.m512h().m474a(CrashlyticsCore.TAG, "No cached settings found.");
            Object obj = jSONObject;
            CommonUtils.m167a(fileInputStream, "Error while closing settings cache file.");
        } catch (Exception e2) {
            th = e2;
            closeable = jSONObject;
            Fabric.m512h().m482e(CrashlyticsCore.TAG, "Failed to fetch cached settings", th);
            CommonUtils.m167a(closeable, "Error while closing settings cache file.");
            return jSONObject;
        } catch (Throwable th6) {
            closeable = jSONObject;
            th2 = th6;
            CommonUtils.m167a(closeable, "Error while closing settings cache file.");
            throw th2;
        }
        return jSONObject;
    }

    public void m430a(long j, JSONObject jSONObject) {
        Closeable fileWriter;
        Throwable e;
        Fabric.m512h().m474a(CrashlyticsCore.TAG, "Writing settings to cache file...");
        if (jSONObject != null) {
            Closeable closeable = null;
            try {
                jSONObject.put("expires_at", j);
                fileWriter = new FileWriter(new File(new FileStoreImpl(this.f341a).m413a(), "com.crashlytics.settings.json"));
                try {
                    fileWriter.write(!(jSONObject instanceof JSONObject) ? jSONObject.toString() : JSONObjectInstrumentation.toString(jSONObject));
                    fileWriter.flush();
                    CommonUtils.m167a(fileWriter, "Failed to close settings writer.");
                } catch (Exception e2) {
                    e = e2;
                    try {
                        Fabric.m512h().m482e(CrashlyticsCore.TAG, "Failed to cache settings", e);
                        CommonUtils.m167a(fileWriter, "Failed to close settings writer.");
                    } catch (Throwable th) {
                        e = th;
                        closeable = fileWriter;
                        CommonUtils.m167a(closeable, "Failed to close settings writer.");
                        throw e;
                    }
                }
            } catch (Exception e3) {
                e = e3;
                fileWriter = null;
                Fabric.m512h().m482e(CrashlyticsCore.TAG, "Failed to cache settings", e);
                CommonUtils.m167a(fileWriter, "Failed to close settings writer.");
            } catch (Throwable th2) {
                e = th2;
                CommonUtils.m167a(closeable, "Failed to close settings writer.");
                throw e;
            }
        }
    }
}
