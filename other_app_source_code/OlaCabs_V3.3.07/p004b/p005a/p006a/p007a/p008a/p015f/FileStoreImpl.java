package p004b.p005a.p006a.p007a.p008a.p015f;

import android.content.Context;
import com.crashlytics.android.core.CrashlyticsCore;
import java.io.File;
import p004b.p005a.p006a.p007a.Fabric;
import p004b.p005a.p006a.p007a.Kit;

/* renamed from: b.a.a.a.a.f.a */
public class FileStoreImpl {
    private final Context f306a;
    private final String f307b;
    private final String f308c;

    public FileStoreImpl(Kit kit) {
        if (kit.getContext() == null) {
            throw new IllegalStateException("Cannot get directory before context has been set. Call Fabric.with() first");
        }
        this.f306a = kit.getContext();
        this.f307b = kit.getPath();
        this.f308c = "Android/" + this.f306a.getPackageName();
    }

    public File m413a() {
        return m414a(this.f306a.getFilesDir());
    }

    File m414a(File file) {
        if (file == null) {
            Fabric.m512h().m474a(CrashlyticsCore.TAG, "Null File");
        } else if (file.exists() || file.mkdirs()) {
            return file;
        } else {
            Fabric.m512h().m479d(CrashlyticsCore.TAG, "Couldn't create file");
        }
        return null;
    }
}
