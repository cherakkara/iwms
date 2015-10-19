package com.facebook.share.internal;

import com.facebook.p022b.DialogFeature;

/* renamed from: com.facebook.share.internal.i */
public enum OpenGraphActionDialogFeature implements DialogFeature {
    OG_ACTION_DIALOG(20130618);
    
    private int f1217b;

    private OpenGraphActionDialogFeature(int i) {
        this.f1217b = i;
    }

    public String m1558a() {
        return "com.facebook.platform.action.request.OGACTIONPUBLISH_DIALOG";
    }

    public int m1559b() {
        return this.f1217b;
    }
}
