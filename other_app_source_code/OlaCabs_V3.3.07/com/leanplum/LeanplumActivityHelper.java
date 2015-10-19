package com.leanplum;

import android.app.Activity;
import android.app.Application;
import android.content.res.Resources;
import android.os.Build.VERSION;
import com.leanplum.annotations.Parser;
import com.leanplum.callbacks.VariablesChangedCallback;
import java.util.LinkedList;
import java.util.Queue;

public class LeanplumActivityHelper {
    static boolean f8576a;
    static Activity f8577b;
    private static boolean f8578c;
    private static Queue<VariablesChangedCallback> f8579g;
    private Activity f8580d;
    private LeanplumResources f8581e;
    private LeanplumInflater f8582f;

    static {
        f8576a = false;
        f8579g = new LinkedList();
    }

    public LeanplumActivityHelper(Activity activity) {
        this.f8580d = activity;
        Leanplum.setApplicationContext(activity.getApplicationContext());
        Parser.parseVariables(activity);
    }

    public static Activity getCurrentActivity() {
        return f8577b;
    }

    public static void enableLifecycleCallbacks(Application application) {
        Leanplum.setApplicationContext(application.getApplicationContext());
        if (VERSION.SDK_INT >= 14) {
            application.registerActivityLifecycleCallbacks(new C0613N());
            f8578c = true;
        }
    }

    public LeanplumResources getLeanplumResources() {
        return getLeanplumResources(null);
    }

    public LeanplumResources getLeanplumResources(Resources resources) {
        if (this.f8581e != null) {
            return this.f8581e;
        }
        Resources resources2;
        if (resources == null) {
            resources2 = this.f8580d.getResources();
        } else {
            resources2 = resources;
        }
        if (resources2 instanceof LeanplumResources) {
            return (LeanplumResources) resources2;
        }
        this.f8581e = new LeanplumResources(resources2);
        return this.f8581e;
    }

    public void setContentView(int i) {
        if (this.f8582f == null) {
            this.f8582f = LeanplumInflater.from(this.f8580d);
        }
        this.f8580d.setContentView(this.f8582f.inflate(i));
    }

    public void onPause() {
        if (!f8578c) {
            Activity activity = this.f8580d;
            f8576a = true;
        }
    }

    private static void m12485d(Activity activity) {
        f8576a = false;
        f8577b = activity;
        if (Leanplum.f8555b || Leanplum.f8556c) {
            Leanplum.m12455c();
            af b = C0629c.m12768b();
            if (b != null) {
                b.m12502a();
            }
        }
        synchronized (f8579g) {
            Queue<VariablesChangedCallback> linkedList = new LinkedList(f8579g);
            f8579g.clear();
        }
        for (VariablesChangedCallback variablesChanged : linkedList) {
            variablesChanged.variablesChanged();
        }
    }

    public void onResume() {
        if (!f8578c) {
            m12485d(this.f8580d);
        }
    }

    private static void m12486e(Activity activity) {
        if (f8576a) {
            Leanplum.m12449b();
            af b = C0629c.m12768b();
            if (b != null) {
                b.m12502a();
            }
        }
        if (f8577b == activity) {
            f8577b = null;
        }
    }

    public void onStop() {
        if (!f8578c) {
            m12486e(this.f8580d);
        }
    }

    public static void queueActionUponActive(VariablesChangedCallback variablesChangedCallback) {
        if (f8577b == null || f8577b.isFinishing() || f8576a) {
            synchronized (f8579g) {
                f8579g.add(variablesChangedCallback);
            }
            return;
        }
        variablesChangedCallback.variablesChanged();
    }
}
