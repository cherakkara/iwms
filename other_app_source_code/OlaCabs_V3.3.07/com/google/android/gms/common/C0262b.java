package com.google.android.gms.common;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.os.Bundle;
import com.google.android.gms.common.internal.C0453u;

/* renamed from: com.google.android.gms.common.b */
public class C0262b extends DialogFragment {
    private Dialog f2100a;
    private OnCancelListener f2101b;

    public C0262b() {
        this.f2100a = null;
        this.f2101b = null;
    }

    public static C0262b m3362a(Dialog dialog, OnCancelListener onCancelListener) {
        C0262b c0262b = new C0262b();
        Dialog dialog2 = (Dialog) C0453u.m3847a((Object) dialog, (Object) "Cannot display null dialog");
        dialog2.setOnCancelListener(null);
        dialog2.setOnDismissListener(null);
        c0262b.f2100a = dialog2;
        if (onCancelListener != null) {
            c0262b.f2101b = onCancelListener;
        }
        return c0262b;
    }

    public void onCancel(DialogInterface dialogInterface) {
        if (this.f2101b != null) {
            this.f2101b.onCancel(dialogInterface);
        }
    }

    public Dialog onCreateDialog(Bundle bundle) {
        if (this.f2100a == null) {
            setShowsDialog(false);
        }
        return this.f2100a;
    }

    public void show(FragmentManager fragmentManager, String str) {
        super.show(fragmentManager, str);
    }
}
