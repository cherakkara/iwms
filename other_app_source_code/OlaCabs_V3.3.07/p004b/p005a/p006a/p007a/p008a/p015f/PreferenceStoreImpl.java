package p004b.p005a.p006a.p007a.p008a.p015f;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build.VERSION;
import p004b.p005a.p006a.p007a.Kit;

/* renamed from: b.a.a.a.a.f.c */
public class PreferenceStoreImpl implements PreferenceStore {
    private final SharedPreferences f309a;
    private final String f310b;
    private final Context f311c;

    public PreferenceStoreImpl(Context context, String str) {
        if (context == null) {
            throw new IllegalStateException("Cannot get directory before context has been set. Call Fabric.with() first");
        }
        this.f311c = context;
        this.f310b = str;
        this.f309a = this.f311c.getSharedPreferences(this.f310b, 0);
    }

    public PreferenceStoreImpl(Kit kit) {
        this(kit.getContext(), kit.getClass().getName());
    }

    public SharedPreferences m418a() {
        return this.f309a;
    }

    public Editor m420b() {
        return this.f309a.edit();
    }

    @TargetApi(9)
    public boolean m419a(Editor editor) {
        if (VERSION.SDK_INT < 9) {
            return editor.commit();
        }
        editor.apply();
        return true;
    }
}
