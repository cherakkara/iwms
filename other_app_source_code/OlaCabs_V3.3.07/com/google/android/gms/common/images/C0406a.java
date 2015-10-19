package com.google.android.gms.common.images;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import com.google.android.gms.common.images.ImageManager.C0400a;
import com.google.android.gms.common.internal.C0416e;
import com.google.android.gms.common.internal.C0452t;
import com.google.android.gms.internal.C0486b;
import com.google.android.gms.internal.C0498c;
import com.google.android.gms.internal.C0498c.C0497a;
import java.lang.ref.WeakReference;

/* renamed from: com.google.android.gms.common.images.a */
public abstract class C0406a {
    final C0405a f2225a;
    protected int f2226b;
    protected C0400a f2227c;
    protected int f2228d;

    /* renamed from: com.google.android.gms.common.images.a.a */
    static final class C0405a {
        public final Uri f2224a;

        public C0405a(Uri uri) {
            this.f2224a = uri;
        }

        public boolean equals(Object obj) {
            return !(obj instanceof C0405a) ? false : this == obj ? true : C0452t.m3845a(((C0405a) obj).f2224a, this.f2224a);
        }

        public int hashCode() {
            return C0452t.m3843a(this.f2224a);
        }
    }

    /* renamed from: com.google.android.gms.common.images.a.b */
    public static final class C0407b extends C0406a {
        private WeakReference<C0400a> f2229e;

        protected void m3549a(Drawable drawable, boolean z, boolean z2, boolean z3) {
            if (!z2) {
                C0400a c0400a = (C0400a) this.f2229e.get();
                if (c0400a != null) {
                    c0400a.m3516a(this.a.f2224a, drawable, z3);
                }
            }
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof C0407b)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            C0407b c0407b = (C0407b) obj;
            C0400a c0400a = (C0400a) this.f2229e.get();
            C0400a c0400a2 = (C0400a) c0407b.f2229e.get();
            boolean z = c0400a2 != null && c0400a != null && C0452t.m3845a(c0400a2, c0400a) && C0452t.m3845a(c0407b.a, this.a);
            return z;
        }

        public int hashCode() {
            return C0452t.m3843a(this.a);
        }
    }

    private Drawable m3544a(Context context, C0498c c0498c, int i) {
        Resources resources = context.getResources();
        if (this.f2228d <= 0) {
            return resources.getDrawable(i);
        }
        C0497a c0497a = new C0497a(i, this.f2228d);
        Drawable drawable = (Drawable) c0498c.m3519a((Object) c0497a);
        if (drawable != null) {
            return drawable;
        }
        drawable = resources.getDrawable(i);
        if ((this.f2228d & 1) != 0) {
            drawable = m3545a(resources, drawable);
        }
        c0498c.m3524b(c0497a, drawable);
        return drawable;
    }

    protected Drawable m3545a(Resources resources, Drawable drawable) {
        return C0486b.m4051a(resources, drawable);
    }

    void m3546a(Context context, Bitmap bitmap, boolean z) {
        C0416e.m3566a((Object) bitmap);
        if ((this.f2228d & 1) != 0) {
            bitmap = C0486b.m4049a(bitmap);
        }
        Drawable bitmapDrawable = new BitmapDrawable(context.getResources(), bitmap);
        if (this.f2227c != null) {
            this.f2227c.m3516a(this.f2225a.f2224a, bitmapDrawable, true);
        }
        m3548a(bitmapDrawable, z, false, true);
    }

    void m3547a(Context context, C0498c c0498c, boolean z) {
        Drawable drawable = null;
        if (this.f2226b != 0) {
            drawable = m3544a(context, c0498c, this.f2226b);
        }
        if (this.f2227c != null) {
            this.f2227c.m3516a(this.f2225a.f2224a, drawable, false);
        }
        m3548a(drawable, z, false, false);
    }

    protected abstract void m3548a(Drawable drawable, boolean z, boolean z2, boolean z3);
}
