package com.leanplum;

import android.app.AlertDialog.Builder;

final class ac implements Runnable {
    private final /* synthetic */ String f8725a;

    ac(aO aOVar, String str) {
        this.f8725a = str;
    }

    public final void run() {
        Builder builder = new Builder(LeanplumActivityHelper.f8577b);
        builder.setTitle("Leanplum");
        builder.setMessage("Your device is registered to " + this.f8725a + ".");
        builder.setPositiveButton("OK", new ad(this));
        builder.show();
    }
}
