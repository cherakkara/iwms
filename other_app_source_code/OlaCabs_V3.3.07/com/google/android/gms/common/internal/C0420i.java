package com.google.android.gms.common.internal;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.util.Log;

/* renamed from: com.google.android.gms.common.internal.i */
public class C0420i implements OnClickListener {
    private final Activity f2243a;
    private final Fragment f2244b;
    private final Intent f2245c;
    private final int f2246d;

    public C0420i(Activity activity, Intent intent, int i) {
        this.f2243a = activity;
        this.f2244b = null;
        this.f2245c = intent;
        this.f2246d = i;
    }

    public C0420i(Fragment fragment, Intent intent, int i) {
        this.f2243a = null;
        this.f2244b = fragment;
        this.f2245c = intent;
        this.f2246d = i;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        try {
            if (this.f2245c != null && this.f2244b != null) {
                this.f2244b.startActivityForResult(this.f2245c, this.f2246d);
            } else if (this.f2245c != null) {
                this.f2243a.startActivityForResult(this.f2245c, this.f2246d);
            }
            dialogInterface.dismiss();
        } catch (ActivityNotFoundException e) {
            Log.e("SettingsRedirect", "Can't redirect to app settings for Google Play services");
        }
    }
}
