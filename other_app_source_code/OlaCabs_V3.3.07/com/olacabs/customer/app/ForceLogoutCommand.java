package com.olacabs.customer.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;
import android.widget.Toast;
import com.olacabs.customer.R;
import com.olacabs.customer.p076d.aj;
import com.olacabs.customer.p076d.da;
import com.olacabs.customer.ui.SplashActivity;
import com.olacabs.customer.utils.Constants;
import java.lang.ref.WeakReference;

/* renamed from: com.olacabs.customer.app.i */
public class ForceLogoutCommand {
    private static final String f9374a;
    private boolean f9375b;
    private boolean f9376c;
    private boolean f9377d;
    private Context f9378e;
    private aj f9379f;

    /* renamed from: com.olacabs.customer.app.i.1 */
    class ForceLogoutCommand implements aj {
        final /* synthetic */ ForceLogoutCommand f9373a;

        ForceLogoutCommand(ForceLogoutCommand forceLogoutCommand) {
            this.f9373a = forceLogoutCommand;
        }

        public void onFailure(Throwable th) {
            OLog.m13311a("User log out failed", new Object[0]);
            m13265a();
        }

        public void onSuccess(Object obj) {
            OLog.m13311a("User logged out", new Object[0]);
            m13265a();
        }

        private void m13265a() {
            OlaApp olaApp = (OlaApp) this.f9373a.f9378e.getApplicationContext();
            olaApp.m12878a().m13234g();
            DataUpdaterManager.m13261a(this.f9373a.f9378e).m13263a().m13321a();
            Editor edit = PreferenceManager.getDefaultSharedPreferences(this.f9373a.f9378e).edit();
            edit.clear();
            edit.putBoolean(Constants.PREF_SAFETY_CAROUSEL_SHOWN_ALREADY, true);
            edit.putBoolean(da.NEW_INSTALL_IDENTIFIER_KEY, false);
            edit.apply();
            olaApp.m12880c();
            if (this.f9373a.f9376c) {
                if (this.f9373a.f9375b) {
                    Toast.makeText(this.f9373a.f9378e, this.f9373a.f9378e.getString(R.string.force_logout_notify_user), 1).show();
                }
                Intent intent = new Intent(this.f9373a.f9378e, SplashActivity.class);
                intent.setFlags(268468224);
                this.f9373a.f9378e.startActivity(intent);
                if (this.f9373a.f9377d) {
                    ((Activity) this.f9373a.f9378e).finish();
                }
            }
        }
    }

    static {
        f9374a = ForceLogoutCommand.class.getSimpleName();
    }

    public ForceLogoutCommand(boolean z) {
        this.f9379f = new ForceLogoutCommand(this);
        this.f9375b = z;
        this.f9376c = true;
        this.f9377d = true;
    }

    public ForceLogoutCommand() {
        this.f9379f = new ForceLogoutCommand(this);
        this.f9375b = false;
        this.f9376c = true;
        this.f9377d = true;
    }

    public ForceLogoutCommand(boolean z, boolean z2, boolean z3) {
        this.f9379f = new ForceLogoutCommand(this);
        this.f9375b = z;
        this.f9376c = z2;
        this.f9377d = z3;
    }

    public void m13270a(Context context) {
        OLog.m13311a("Forcing the user out of the app", new Object[0]);
        this.f9378e = context;
        ((OlaApp) context.getApplicationContext()).m12878a().m13231f(new WeakReference(this.f9379f), f9374a);
    }
}
