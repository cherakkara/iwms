package com.payu.sdk;

/* renamed from: com.payu.sdk.s */
class C0941s implements Runnable {
    final /* synthetic */ C0940r f11584a;

    C0941s(C0940r c0940r) {
        this.f11584a = c0940r;
    }

    public void run() {
        C0940r c0940r = this.f11584a;
        c0940r.f11580a++;
        if (this.f11584a.f11580a >= this.f11584a.f11581b.length) {
            this.f11584a.f11580a = 0;
        }
        this.f11584a.f11582c.setImageBitmap(null);
        this.f11584a.f11582c.destroyDrawingCache();
        this.f11584a.f11582c.refreshDrawableState();
        this.f11584a.f11582c.setImageDrawable(this.f11584a.f11581b[this.f11584a.f11580a]);
    }
}
