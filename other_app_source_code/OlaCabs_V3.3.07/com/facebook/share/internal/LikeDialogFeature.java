package com.facebook.share.internal;

import com.facebook.p022b.DialogFeature;

/* renamed from: com.facebook.share.internal.f */
public enum LikeDialogFeature implements DialogFeature {
    LIKE_DIALOG(20140701);
    
    private int f1213b;

    private LikeDialogFeature(int i) {
        this.f1213b = i;
    }

    public String m1549a() {
        return "com.facebook.platform.action.request.LIKE_DIALOG";
    }

    public int m1550b() {
        return this.f1213b;
    }
}
