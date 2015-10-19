package com.google.android.m4b.maps.be;

import android.content.Context;
import android.graphics.Bitmap;
import com.google.p025a.p026a.Preconditions;
import com.google.p025a.p028c.au;
import java.util.Map;

/* compiled from: BitmapManager */
public final class bl {
    private final Map<bk, BitmapManager> f5829a;
    private final Context f5830b;

    /* renamed from: com.google.android.m4b.maps.be.bl.a */
    public static class BitmapManager {
        private int f5827a;
        private final Bitmap f5828b;

        public BitmapManager(Bitmap bitmap) {
            this.f5828b = (Bitmap) Preconditions.m1817a((Object) bitmap);
            this.f5827a = 1;
        }

        final void m8901a() {
            this.f5827a++;
        }

        final void m8902b() {
            this.f5827a--;
        }

        public final Bitmap m8903c() {
            return this.f5828b;
        }

        public final int m8904d() {
            return this.f5827a;
        }
    }

    private bl(Context context) {
        this.f5829a = au.m2807a();
        this.f5830b = context;
    }

    public static bl m8905a(Context context) {
        return new bl(context);
    }

    public final void m8906a(bk bkVar) {
        BitmapManager bitmapManager = (BitmapManager) this.f5829a.get(bkVar);
        if (bitmapManager == null) {
            this.f5829a.put(bkVar, new BitmapManager(bkVar.m8893a(this.f5830b)));
            return;
        }
        bitmapManager.m8901a();
    }

    public final BitmapManager m8907b(bk bkVar) {
        return (BitmapManager) this.f5829a.get(bkVar);
    }

    public final void m8908c(bk bkVar) {
        BitmapManager bitmapManager = (BitmapManager) this.f5829a.get(bkVar);
        Preconditions.m1823a(bitmapManager != null, (Object) "Released unknown imageData reference");
        if (bitmapManager.m8904d() == 1) {
            this.f5829a.remove(bkVar);
        } else {
            bitmapManager.m8902b();
        }
    }
}
