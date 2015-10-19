package p004b.p005a.p006a.p007a.p008a.p016g;

import android.content.Context;
import android.graphics.BitmapFactory.Options;
import com.crashlytics.android.core.CrashlyticsCore;
import com.newrelic.agent.android.instrumentation.BitmapFactoryInstrumentation;
import p004b.p005a.p006a.p007a.Fabric;
import p004b.p005a.p006a.p007a.p008a.p010b.CommonUtils;

/* renamed from: b.a.a.a.a.g.n */
public class IconRequest {
    public final String f353a;
    public final int f354b;
    public final int f355c;
    public final int f356d;

    public IconRequest(String str, int i, int i2, int i3) {
        this.f353a = str;
        this.f354b = i;
        this.f355c = i2;
        this.f356d = i3;
    }

    public static IconRequest m460a(Context context, String str) {
        if (str != null) {
            try {
                int l = CommonUtils.m189l(context);
                Fabric.m512h().m474a(CrashlyticsCore.TAG, "App icon resource ID is " + l);
                Options options = new Options();
                options.inJustDecodeBounds = true;
                BitmapFactoryInstrumentation.decodeResource(context.getResources(), l, options);
                return new IconRequest(str, l, options.outWidth, options.outHeight);
            } catch (Throwable e) {
                Fabric.m512h().m482e(CrashlyticsCore.TAG, "Failed to load icon", e);
            }
        }
        return null;
    }
}
