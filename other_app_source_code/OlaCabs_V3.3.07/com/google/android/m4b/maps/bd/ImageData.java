package com.google.android.m4b.maps.bd;

import android.graphics.Bitmap;
import android.opengl.GLES20;
import android.opengl.GLUtils;
import com.google.android.m4b.maps.bd.Entity.Entity;

/* renamed from: com.google.android.m4b.maps.bd.k */
public final class ImageData {
    private Bitmap f5517a;
    private int f5518b;
    private int f5519c;

    public ImageData(Bitmap bitmap) {
        this.f5517a = null;
        this.f5518b = 0;
        this.f5519c = 0;
        this.f5517a = bitmap;
        this.f5518b = bitmap.getWidth();
        this.f5519c = bitmap.getHeight();
    }

    final boolean m8246a(Entity entity) {
        if (entity.f5481d) {
            if (this.f5517a != null) {
                GLUtils.texImage2D(3553, 0, this.f5517a, 0);
            } else {
                GLES20.glTexImage2D(3553, 0, 6407, this.f5518b, this.f5519c, 0, 6407, 33635, null);
                EntityRenderer.m8233c();
            }
        }
        boolean z = entity.f5481d;
        return true;
    }
}
