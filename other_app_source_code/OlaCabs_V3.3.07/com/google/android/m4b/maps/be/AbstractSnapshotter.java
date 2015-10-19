package com.google.android.m4b.maps.be;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.os.RemoteException;
import android.view.TextureView;
import android.view.View;
import com.google.android.m4b.maps.cc.ObjectWrapper;
import com.google.android.m4b.maps.model.RuntimeRemoteException;
import com.google.android.m4b.maps.p042r.ISnapshotReadyCallback;
import java.util.concurrent.Executor;

/* renamed from: com.google.android.m4b.maps.be.a */
public abstract class AbstractSnapshotter implements aq {
    protected final View f5533a;
    protected final Executor f5534b;
    private final boolean f5535c;
    private final View f5536d;
    private final View f5537e;

    /* renamed from: com.google.android.m4b.maps.be.a.1 */
    class AbstractSnapshotter implements Runnable {
        private /* synthetic */ Bitmap f5529a;
        private /* synthetic */ boolean f5530b;
        private /* synthetic */ ISnapshotReadyCallback f5531c;
        private /* synthetic */ AbstractSnapshotter f5532d;

        AbstractSnapshotter(AbstractSnapshotter abstractSnapshotter, Bitmap bitmap, boolean z, ISnapshotReadyCallback iSnapshotReadyCallback) {
            this.f5532d = abstractSnapshotter;
            this.f5529a = bitmap;
            this.f5530b = z;
            this.f5531c = iSnapshotReadyCallback;
        }

        public final void run() {
            Bitmap bitmap;
            int width = this.f5532d.f5533a.getWidth();
            int height = this.f5532d.f5533a.getHeight();
            if (this.f5529a.getWidth() == width && this.f5529a.getHeight() == height) {
                bitmap = this.f5529a;
            } else {
                bitmap = Bitmap.createBitmap(width, height, Config.ARGB_8888);
            }
            ((TextureView) this.f5532d.f5533a).getBitmap(bitmap);
            this.f5532d.m8259a(bitmap, this.f5530b);
            AbstractSnapshotter abstractSnapshotter = this.f5532d;
            AbstractSnapshotter.m8257a(this.f5531c, bitmap);
        }
    }

    protected abstract void m8258a(Bitmap bitmap, ISnapshotReadyCallback iSnapshotReadyCallback, boolean z);

    protected AbstractSnapshotter(View view, View view2, View view3, boolean z, Executor executor) {
        this.f5533a = view;
        this.f5536d = view2;
        this.f5537e = view3;
        this.f5535c = z;
        this.f5534b = executor;
    }

    public final synchronized void m8260b(Bitmap bitmap, ISnapshotReadyCallback iSnapshotReadyCallback, boolean z) {
        int width = this.f5533a.getWidth();
        int height = this.f5533a.getHeight();
        if (bitmap == null) {
            bitmap = Bitmap.createBitmap(width, height, Config.ARGB_8888);
        } else if (!(bitmap.getWidth() == width && bitmap.getHeight() == height)) {
            az.m8757a(5, "The Bitmap provided in the snapshot() method does not match the map's dimensions, hence another Bitmap is allocated with the right dimensions. If you think this is due to the fact that the map was resized, you can ignore this message. Otherwise, you should check the dimensions of the Bitmap passed to the method.");
            bitmap = Bitmap.createBitmap(width, height, Config.ARGB_8888);
        }
        if (this.f5535c) {
            this.f5534b.execute(new AbstractSnapshotter(this, bitmap, false, iSnapshotReadyCallback));
        } else {
            m8258a(bitmap, iSnapshotReadyCallback, false);
        }
    }

    protected static void m8257a(ISnapshotReadyCallback iSnapshotReadyCallback, Bitmap bitmap) {
        try {
            if (bg.m8851a()) {
                iSnapshotReadyCallback.m11138a(bitmap);
            } else {
                iSnapshotReadyCallback.m11139a(ObjectWrapper.m10130a((Object) bitmap));
            }
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    protected final void m8259a(Bitmap bitmap, boolean z) {
        Canvas canvas = new Canvas(bitmap);
        this.f5536d.draw(canvas);
        if (z) {
            this.f5537e.draw(canvas);
        }
    }
}
