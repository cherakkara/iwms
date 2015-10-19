package p004b.p005a.p006a.p007a.p008a.p010b;

import android.content.Context;
import com.crashlytics.android.core.CrashlyticsCore;
import com.newrelic.agent.android.instrumentation.Trace;
import p004b.p005a.p006a.p007a.Fabric;
import p004b.p005a.p006a.p007a.p008a.p009a.MemoryValueCache;
import p004b.p005a.p006a.p007a.p008a.p009a.ValueLoader;

/* renamed from: b.a.a.a.a.b.p */
public class InstallerPackageNameProvider {
    private final ValueLoader<String> f178a;
    private final MemoryValueCache<String> f179b;

    /* renamed from: b.a.a.a.a.b.p.1 */
    class InstallerPackageNameProvider implements ValueLoader<String> {
        final /* synthetic */ InstallerPackageNameProvider f177a;

        InstallerPackageNameProvider(InstallerPackageNameProvider installerPackageNameProvider) {
            this.f177a = installerPackageNameProvider;
        }

        public /* synthetic */ Object load(Context context) throws Exception {
            return m225a(context);
        }

        public String m225a(Context context) throws Exception {
            String installerPackageName = context.getPackageManager().getInstallerPackageName(context.getPackageName());
            return installerPackageName == null ? Trace.NULL : installerPackageName;
        }
    }

    public InstallerPackageNameProvider() {
        this.f178a = new InstallerPackageNameProvider(this);
        this.f179b = new MemoryValueCache();
    }

    public String m226a(Context context) {
        try {
            String str = (String) this.f179b.m116a(context, this.f178a);
            if (Trace.NULL.equals(str)) {
                return null;
            }
            return str;
        } catch (Throwable e) {
            Fabric.m512h().m482e(CrashlyticsCore.TAG, "Failed to determine installer package name", e);
            return null;
        }
    }
}
