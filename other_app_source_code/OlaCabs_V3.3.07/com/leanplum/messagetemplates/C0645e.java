package com.leanplum.messagetemplates;

import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

/* renamed from: com.leanplum.messagetemplates.e */
final class C0645e implements AnimationListener {
    private /* synthetic */ BaseMessageDialog f8839a;

    C0645e(BaseMessageDialog baseMessageDialog) {
        this.f8839a = baseMessageDialog;
    }

    public final void onAnimationStart(Animation animation) {
    }

    public final void onAnimationRepeat(Animation animation) {
    }

    public final void onAnimationEnd(Animation animation) {
        new Handler().postDelayed(new C0646f(this), 10);
    }
}
