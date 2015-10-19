package com.google.android.m4b.maps.be;

import android.view.View;
import android.view.View.OnClickListener;

/* compiled from: MyLocationButton */
public final class af {
    private final View f5597a;

    public af(View view) {
        this.f5597a = view;
    }

    public final View m8409a() {
        return this.f5597a;
    }

    public final void m8411a(boolean z) {
        this.f5597a.setVisibility(z ? 0 : 8);
    }

    public final void m8410a(OnClickListener onClickListener) {
        this.f5597a.setOnClickListener(onClickListener);
    }
}
