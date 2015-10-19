package com.olacabs.customer.app;

import android.content.Context;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.olacabs.customer.p076d.bx;

/* renamed from: com.olacabs.customer.app.k */
public class ImageCacheManager {
    private static ImageCacheManager f9386a;
    private ImageLoader f9387b;
    private ImageLoader.ImageLoader f9388c;

    public static ImageCacheManager m13274a() {
        if (f9386a == null) {
            f9386a = new ImageCacheManager();
        }
        return f9386a;
    }

    public void m13275a(Context context, int i, RequestQueue requestQueue) {
        this.f9388c = new bx(i);
        this.f9387b = new ImageLoader(requestQueue, this.f9388c);
    }

    public ImageLoader m13276b() {
        return this.f9387b;
    }
}
