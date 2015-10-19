package com.olacabs.customer.p076d;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import com.android.volley.toolbox.ImageLoader.ImageLoader;

/* compiled from: OlaImageCache */
/* renamed from: com.olacabs.customer.d.bx */
public class bx extends LruCache<String, Bitmap> implements ImageLoader {
    public bx(int i) {
        super(i);
    }

    protected int sizeOf(String str, Bitmap bitmap) {
        return bitmap.getRowBytes() * bitmap.getHeight();
    }

    public Bitmap getBitmap(String str) {
        return (Bitmap) get(str);
    }

    public void putBitmap(String str, Bitmap bitmap) {
        put(str, bitmap);
    }
}
