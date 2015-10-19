package com.olacabs.customer.app;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.preference.PreferenceManager;
import com.olacabs.customer.p076d.CachingHandler;
import com.olacabs.customer.p076d.da;
import com.olacabs.customer.p076d.dt;
import com.olacabs.customer.utils.BackgroundLooper;

/* renamed from: com.olacabs.customer.app.g */
public abstract class DataUpdater {
    protected static final String f8985a;
    protected Context f8986b;
    protected dt f8987c;
    protected da f8988d;
    protected DataManager f8989e;
    protected Handler f8990f;
    protected SharedPreferences f8991g;
    protected CachingHandler f8992h;
    private OlaApp f8993i;

    public abstract void m12883a();

    static {
        f8985a = DataUpdater.class.getSimpleName();
    }

    public DataUpdater(Context context) {
        this.f8990f = new Handler(BackgroundLooper.m14896a());
        this.f8986b = context;
        this.f8987c = dt.getInstance(this.f8986b);
        this.f8989e = DataManager.m13137a(this.f8986b);
        this.f8988d = this.f8989e.m13218d();
        this.f8993i = (OlaApp) this.f8986b.getApplicationContext();
        this.f8991g = PreferenceManager.getDefaultSharedPreferences(this.f8986b);
        this.f8992h = CachingHandler.getInstance(this.f8986b);
    }
}
