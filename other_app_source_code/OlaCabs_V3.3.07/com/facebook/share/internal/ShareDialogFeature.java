package com.facebook.share.internal;

import com.facebook.p022b.DialogFeature;

/* renamed from: com.facebook.share.internal.l */
public enum ShareDialogFeature implements DialogFeature {
    SHARE_DIALOG(20130618),
    PHOTOS(20140204),
    VIDEO(20141028);
    
    private int f1225d;

    private ShareDialogFeature(int i) {
        this.f1225d = i;
    }

    public String m1605a() {
        return "com.facebook.platform.action.request.FEED_DIALOG";
    }

    public int m1606b() {
        return this.f1225d;
    }
}
