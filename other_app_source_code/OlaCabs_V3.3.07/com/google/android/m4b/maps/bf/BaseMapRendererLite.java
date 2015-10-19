package com.google.android.m4b.maps.bf;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;
import com.olacabs.customer.p076d.dm;

/* renamed from: com.google.android.m4b.maps.bf.b */
final class BaseMapRendererLite {
    private final View f6130a;
    private Bitmap f6131b;
    private ProjectionLite f6132c;

    BaseMapRendererLite(View view) {
        this.f6130a = view;
    }

    final void m9591a(Bitmap bitmap, ProjectionLite projectionLite) {
        this.f6131b = bitmap;
        this.f6132c = projectionLite;
        this.f6130a.invalidate();
    }

    final ProjectionLite m9590a(Canvas canvas, int i, int i2) {
        if (this.f6131b != null) {
            float width = ((float) (i - this.f6131b.getWidth())) / dm.DEFAULT_BACKOFF_MULT;
            float height = ((float) (i2 - this.f6131b.getHeight())) / dm.DEFAULT_BACKOFF_MULT;
            canvas.drawBitmap(this.f6131b, width, height, new Paint());
        } else {
            BaseMapRendererLite.m9589a(canvas);
        }
        return this.f6132c;
    }

    private static void m9589a(Canvas canvas) {
        Paint paint = new Paint();
        int width = canvas.getWidth();
        int height = canvas.getHeight();
        for (int i = 0; i < width; i += 15) {
            canvas.drawLine((float) i, 0.0f, (float) i, (float) height, paint);
        }
        for (int i2 = 0; i2 < height; i2 += 15) {
            canvas.drawLine(0.0f, (float) i2, (float) width, (float) i2, paint);
        }
    }
}
