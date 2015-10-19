package com.google.android.m4b.maps.p061y;

import android.graphics.Bitmap;
import android.view.View;
import com.google.android.m4b.maps.be.AbstractSnapshotter;
import com.google.android.m4b.maps.p042r.ISnapshotReadyCallback;
import com.google.android.m4b.maps.p058v.Util;
import java.util.concurrent.Executor;

/* renamed from: com.google.android.m4b.maps.y.r */
public final class SnapshotterImpl extends AbstractSnapshotter {

    /* renamed from: com.google.android.m4b.maps.y.r.1 */
    class SnapshotterImpl implements Runnable {
        private /* synthetic */ ISnapshotReadyCallback f8162a;
        private /* synthetic */ Bitmap f8163b;
        private /* synthetic */ SnapshotterImpl f8164c;

        SnapshotterImpl(SnapshotterImpl snapshotterImpl, ISnapshotReadyCallback iSnapshotReadyCallback, Bitmap bitmap) {
            this.f8164c = snapshotterImpl;
            this.f8162a = iSnapshotReadyCallback;
            this.f8163b = bitmap;
        }

        public final void run() {
            AbstractSnapshotter.m8257a(this.f8162a, this.f8163b);
        }
    }

    public SnapshotterImpl(VectorMapView vectorMapView, View view, View view2, boolean z, Executor executor) {
        super((View) vectorMapView, view, view2, z, executor);
    }

    protected final void m11842a(Bitmap bitmap, ISnapshotReadyCallback iSnapshotReadyCallback, boolean z) {
        Bitmap a = Util.m11544a(((VectorMapView) this.a).m11860a(bitmap));
        m8259a(a, z);
        this.b.execute(new SnapshotterImpl(this, iSnapshotReadyCallback, a));
    }
}
