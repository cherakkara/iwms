package com.google.android.m4b.maps.bf;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.View;
import com.google.android.m4b.maps.be.AbstractSnapshotter;
import com.google.android.m4b.maps.p042r.ISnapshotReadyCallback;
import java.util.concurrent.Executor;

/* renamed from: com.google.android.m4b.maps.bf.m */
public final class SnapshotterLite extends AbstractSnapshotter {

    /* renamed from: com.google.android.m4b.maps.bf.m.1 */
    class SnapshotterLite implements Runnable {
        private /* synthetic */ Bitmap f6199a;
        private /* synthetic */ boolean f6200b;
        private /* synthetic */ ISnapshotReadyCallback f6201c;
        private /* synthetic */ SnapshotterLite f6202d;

        SnapshotterLite(SnapshotterLite snapshotterLite, Bitmap bitmap, boolean z, ISnapshotReadyCallback iSnapshotReadyCallback) {
            this.f6202d = snapshotterLite;
            this.f6199a = bitmap;
            this.f6200b = z;
            this.f6201c = iSnapshotReadyCallback;
        }

        public final void run() {
            this.f6202d.a.draw(new Canvas(this.f6199a));
            this.f6202d.m8259a(this.f6199a, this.f6200b);
            AbstractSnapshotter.m8257a(this.f6201c, this.f6199a);
        }
    }

    protected SnapshotterLite(View view, View view2, View view3, Executor executor) {
        super(view, view2, view3, false, executor);
    }

    protected final void m9704a(Bitmap bitmap, ISnapshotReadyCallback iSnapshotReadyCallback, boolean z) {
        this.b.execute(new SnapshotterLite(this, bitmap, z, iSnapshotReadyCallback));
    }
}
