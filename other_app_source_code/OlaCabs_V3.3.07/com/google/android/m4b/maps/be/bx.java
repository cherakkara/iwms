package com.google.android.m4b.maps.be;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import com.google.android.m4b.maps.be.be.UsageLog;
import com.google.android.m4b.maps.model.CameraPosition;
import com.google.android.m4b.maps.model.LatLng;
import com.google.p025a.p026a.Strings;

/* compiled from: GmmLauncher */
public final class bx {
    private final Context f5913a;
    private final be f5914b;
    private String f5915c;
    private boolean f5916d;
    private boolean f5917e;

    public bx(Context context, be beVar) {
        boolean z = true;
        this.f5916d = false;
        this.f5917e = false;
        this.f5913a = context;
        this.f5914b = beVar;
        PackageManager packageManager = this.f5913a.getPackageManager();
        if (m9067a("com.google.android.apps.gmm", packageManager)) {
            this.f5915c = "com.google.android.apps.gmm";
        } else if (m9067a("com.google.android.apps.maps", packageManager)) {
            this.f5915c = "com.google.android.apps.maps";
        } else {
            this.f5915c = null;
        }
        if (this.f5915c != null) {
            try {
                boolean z2;
                int i = packageManager.getPackageInfo(this.f5915c, 1).versionCode;
                if (i > 700000000) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                this.f5916d = z2;
                if (i <= 703000000) {
                    z = false;
                }
                this.f5917e = z;
            } catch (NameNotFoundException e) {
            }
        }
    }

    public final void m9068a(ad adVar) {
        this.f5914b.m8835b(UsageLog.INTENT_DIRECTIONS);
        LatLng d = adVar.m8368d();
        m9066a("http://maps.google.com/maps?saddr=&daddr=" + d.f7554a + "," + d.f7555b);
    }

    public final void m9069a(CameraPosition cameraPosition, ad adVar, boolean z) {
        if (z) {
            if (adVar == null) {
                this.f5914b.m8835b(UsageLog.INTENT_VIEW_MULTIPLE_MARKERS_NONE_SELECTED);
            } else {
                this.f5914b.m8835b(UsageLog.INTENT_VIEW_MULTIPLE_MARKERS_ONE_SELECTED);
            }
        } else if (adVar != null) {
            this.f5914b.m8835b(UsageLog.INTENT_VIEW_ONE_MARKER);
        } else {
            this.f5914b.m8835b(UsageLog.INTENT_VIEW_NO_MARKERS);
        }
        String str = "geo:" + cameraPosition.f7529a.f7554a + "," + cameraPosition.f7529a.f7555b + "?z=";
        if (this.f5916d) {
            str = str + cameraPosition.f7530b;
        } else {
            str = str + ((int) Math.floor((double) cameraPosition.f7530b));
        }
        if (adVar != null) {
            LatLng d = adVar.m8368d();
            str = str + "&q=" + d.f7554a + "," + d.f7555b;
            String o = adVar.m8379o();
            if (!Strings.m1866b(o) && this.f5917e) {
                str = str + "(" + o + ")";
            }
        }
        m9066a(str);
    }

    private void m9066a(String str) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse(str));
        intent.addFlags(268435456);
        if (this.f5915c != null) {
            intent.setPackage(this.f5915c);
            this.f5913a.startActivity(intent);
        }
    }

    private static boolean m9067a(String str, PackageManager packageManager) {
        try {
            packageManager.getPackageInfo(str, 1);
            return true;
        } catch (NameNotFoundException e) {
            return false;
        }
    }
}
