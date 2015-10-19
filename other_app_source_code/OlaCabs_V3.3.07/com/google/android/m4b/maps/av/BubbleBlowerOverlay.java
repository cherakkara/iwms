package com.google.android.m4b.maps.av;

import com.google.android.m4b.maps.an.Point;
import com.google.android.m4b.maps.ax.Camera;
import java.util.List;

/* renamed from: com.google.android.m4b.maps.av.x */
public abstract class BubbleBlowerOverlay extends al {
    protected final ai f4246a;
    private BubbleBlowerOverlay f4247b;

    /* renamed from: com.google.android.m4b.maps.av.x.a */
    public interface BubbleBlowerOverlay {
        void m7366a(BubbleBlower bubbleBlower);
    }

    public abstract void m6814a(List<af> list, float f, float f2, Point point, Camera camera, int i);

    public BubbleBlowerOverlay(ai aiVar) {
        this.f4246a = aiVar;
    }

    public final void m6813a(BubbleBlowerOverlay bubbleBlowerOverlay) {
        this.f4247b = bubbleBlowerOverlay;
    }

    public final boolean m6816l() {
        return true;
    }

    protected final boolean m6815a(BubbleBlower bubbleBlower) {
        boolean e_ = e_();
        if (this.f4247b == null) {
            return e_;
        }
        this.f4247b.m7366a(bubbleBlower);
        return true;
    }

    public final int m6817n() {
        return 1;
    }
}
