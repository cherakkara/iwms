package com.android.volley.toolbox;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.os.Handler;
import android.os.Looper;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response.Response;
import com.android.volley.VolleyError;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

/* renamed from: com.android.volley.toolbox.h */
public class ImageLoader {
    private final RequestQueue f583a;
    private int f584b;
    private final ImageLoader f585c;
    private final HashMap<String, ImageLoader> f586d;
    private final HashMap<String, ImageLoader> f587e;
    private final Handler f588f;
    private Runnable f589g;

    /* renamed from: com.android.volley.toolbox.h.d */
    public interface ImageLoader extends Response {
        void m598a(ImageLoader imageLoader, boolean z);
    }

    /* renamed from: com.android.volley.toolbox.h.1 */
    class ImageLoader implements Response<Bitmap> {
        final /* synthetic */ String f568a;
        final /* synthetic */ ImageLoader f569b;

        ImageLoader(ImageLoader imageLoader, String str) {
            this.f569b = imageLoader;
            this.f568a = str;
        }

        public void m655a(Bitmap bitmap) {
            this.f569b.m678a(this.f568a, bitmap);
        }
    }

    /* renamed from: com.android.volley.toolbox.h.2 */
    class ImageLoader implements Response {
        final /* synthetic */ String f570a;
        final /* synthetic */ ImageLoader f571b;

        ImageLoader(ImageLoader imageLoader, String str) {
            this.f571b = imageLoader;
            this.f570a = str;
        }

        public void m657a(VolleyError volleyError) {
            this.f571b.m679a(this.f570a, volleyError);
        }
    }

    /* renamed from: com.android.volley.toolbox.h.3 */
    class ImageLoader implements Runnable {
        final /* synthetic */ ImageLoader f572a;

        ImageLoader(ImageLoader imageLoader) {
            this.f572a = imageLoader;
        }

        public void run() {
            for (ImageLoader imageLoader : this.f572a.f587e.values()) {
                Iterator it = imageLoader.f577e.iterator();
                while (it.hasNext()) {
                    ImageLoader imageLoader2 = (ImageLoader) it.next();
                    if (imageLoader2.f580c != null) {
                        if (imageLoader.m661a() == null) {
                            imageLoader2.f579b = imageLoader.f575c;
                            imageLoader2.f580c.m598a(imageLoader2, false);
                        } else {
                            imageLoader2.f580c.m584a(imageLoader.m661a());
                        }
                    }
                }
            }
            this.f572a.f587e.clear();
            this.f572a.f589g = null;
        }
    }

    /* renamed from: com.android.volley.toolbox.h.a */
    private class ImageLoader {
        final /* synthetic */ ImageLoader f573a;
        private final Request<?> f574b;
        private Bitmap f575c;
        private VolleyError f576d;
        private final LinkedList<ImageLoader> f577e;

        public ImageLoader(ImageLoader imageLoader, Request<?> request, ImageLoader imageLoader2) {
            this.f573a = imageLoader;
            this.f577e = new LinkedList();
            this.f574b = request;
            this.f577e.add(imageLoader2);
        }

        public void m662a(VolleyError volleyError) {
            this.f576d = volleyError;
        }

        public VolleyError m661a() {
            return this.f576d;
        }

        public void m663a(ImageLoader imageLoader) {
            this.f577e.add(imageLoader);
        }

        public boolean m664b(ImageLoader imageLoader) {
            this.f577e.remove(imageLoader);
            if (this.f577e.size() != 0) {
                return false;
            }
            this.f574b.cancel();
            return true;
        }
    }

    /* renamed from: com.android.volley.toolbox.h.b */
    public interface ImageLoader {
        Bitmap getBitmap(String str);

        void putBitmap(String str, Bitmap bitmap);
    }

    /* renamed from: com.android.volley.toolbox.h.c */
    public class ImageLoader {
        final /* synthetic */ ImageLoader f578a;
        private Bitmap f579b;
        private final ImageLoader f580c;
        private final String f581d;
        private final String f582e;

        public ImageLoader(ImageLoader imageLoader, Bitmap bitmap, String str, String str2, ImageLoader imageLoader2) {
            this.f578a = imageLoader;
            this.f579b = bitmap;
            this.f582e = str;
            this.f581d = str2;
            this.f580c = imageLoader2;
        }

        public void m667a() {
            if (this.f580c != null) {
                ImageLoader imageLoader = (ImageLoader) this.f578a.f586d.get(this.f581d);
                if (imageLoader == null) {
                    imageLoader = (ImageLoader) this.f578a.f587e.get(this.f581d);
                    if (imageLoader != null) {
                        imageLoader.m664b(this);
                        if (imageLoader.f577e.size() == 0) {
                            this.f578a.f587e.remove(this.f581d);
                        }
                    }
                } else if (imageLoader.m664b(this)) {
                    this.f578a.f586d.remove(this.f581d);
                }
            }
        }

        public Bitmap m668b() {
            return this.f579b;
        }

        public String m669c() {
            return this.f582e;
        }
    }

    public ImageLoader(RequestQueue requestQueue, ImageLoader imageLoader) {
        this.f584b = 100;
        this.f586d = new HashMap();
        this.f587e = new HashMap();
        this.f588f = new Handler(Looper.getMainLooper());
        this.f583a = requestQueue;
        this.f585c = imageLoader;
    }

    public ImageLoader m677a(String str, ImageLoader imageLoader, int i, int i2) {
        m673a();
        String a = ImageLoader.m671a(str, i, i2);
        Bitmap bitmap = this.f585c.getBitmap(a);
        if (bitmap != null) {
            ImageLoader imageLoader2 = new ImageLoader(this, bitmap, str, null, null);
            imageLoader.m598a(imageLoader2, true);
            return imageLoader2;
        }
        ImageLoader imageLoader3 = new ImageLoader(this, null, str, a, imageLoader);
        imageLoader.m598a(imageLoader3, true);
        ImageLoader imageLoader4 = (ImageLoader) this.f586d.get(a);
        if (imageLoader4 != null) {
            imageLoader4.m663a(imageLoader3);
            return imageLoader3;
        }
        Request a2 = m676a(str, i, i2, a);
        this.f583a.m577a(a2);
        this.f586d.put(a, new ImageLoader(this, a2, imageLoader3));
        return imageLoader3;
    }

    protected Request<Bitmap> m676a(String str, int i, int i2, String str2) {
        return new ImageRequest(str, new ImageLoader(this, str2), i, i2, Config.RGB_565, new ImageLoader(this, str2));
    }

    protected void m678a(String str, Bitmap bitmap) {
        this.f585c.putBitmap(str, bitmap);
        ImageLoader imageLoader = (ImageLoader) this.f586d.remove(str);
        if (imageLoader != null) {
            imageLoader.f575c = bitmap;
            m674a(str, imageLoader);
        }
    }

    protected void m679a(String str, VolleyError volleyError) {
        ImageLoader imageLoader = (ImageLoader) this.f586d.remove(str);
        if (imageLoader != null) {
            imageLoader.m662a(volleyError);
            m674a(str, imageLoader);
        }
    }

    private void m674a(String str, ImageLoader imageLoader) {
        this.f587e.put(str, imageLoader);
        if (this.f589g == null) {
            this.f589g = new ImageLoader(this);
            this.f588f.postDelayed(this.f589g, (long) this.f584b);
        }
    }

    private void m673a() {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            throw new IllegalStateException("ImageLoader must be invoked from the main thread.");
        }
    }

    private static String m671a(String str, int i, int i2) {
        return new StringBuilder(str.length() + 12).append("#W").append(i).append("#H").append(i2).append(str).toString();
    }
}
