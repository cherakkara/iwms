package com.p019a.p020a.p021a;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.os.Build.VERSION;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import com.olacabs.customer.p076d.br;
import com.olacabs.customer.p076d.dm;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;

/* renamed from: com.a.a.a.a */
public final class AnimatorProxy extends Animation {
    public static final boolean f446a;
    private static final WeakHashMap<View, AnimatorProxy> f447b;
    private final WeakReference<View> f448c;
    private final Camera f449d;
    private boolean f450e;
    private float f451f;
    private float f452g;
    private float f453h;
    private float f454i;
    private float f455j;
    private float f456k;
    private float f457l;
    private float f458m;
    private float f459n;
    private float f460o;
    private final RectF f461p;
    private final RectF f462q;
    private final Matrix f463r;

    static {
        f446a = Integer.valueOf(VERSION.SDK).intValue() < 11;
        f447b = new WeakHashMap();
    }

    public static AnimatorProxy m550a(View view) {
        Animation animation = (AnimatorProxy) f447b.get(view);
        if (animation != null && animation == view.getAnimation()) {
            return animation;
        }
        AnimatorProxy animatorProxy = new AnimatorProxy(view);
        f447b.put(view, animatorProxy);
        return animatorProxy;
    }

    private AnimatorProxy(View view) {
        this.f449d = new Camera();
        this.f451f = br.DEFAULT_BACKOFF_MULT;
        this.f457l = br.DEFAULT_BACKOFF_MULT;
        this.f458m = br.DEFAULT_BACKOFF_MULT;
        this.f461p = new RectF();
        this.f462q = new RectF();
        this.f463r = new Matrix();
        setDuration(0);
        setFillAfter(true);
        view.setAnimation(this);
        this.f448c = new WeakReference(view);
    }

    public void m555a(float f) {
        if (this.f460o != f) {
            m551a();
            this.f460o = f;
            m554b();
        }
    }

    private void m551a() {
        View view = (View) this.f448c.get();
        if (view != null) {
            m553a(this.f461p, view);
        }
    }

    private void m554b() {
        View view = (View) this.f448c.get();
        if (view != null && view.getParent() != null) {
            RectF rectF = this.f462q;
            m553a(rectF, view);
            rectF.union(this.f461p);
            ((View) view.getParent()).invalidate((int) Math.floor((double) rectF.left), (int) Math.floor((double) rectF.top), (int) Math.ceil((double) rectF.right), (int) Math.ceil((double) rectF.bottom));
        }
    }

    private void m553a(RectF rectF, View view) {
        rectF.set(0.0f, 0.0f, (float) view.getWidth(), (float) view.getHeight());
        Matrix matrix = this.f463r;
        matrix.reset();
        m552a(matrix, view);
        this.f463r.mapRect(rectF);
        rectF.offset((float) view.getLeft(), (float) view.getTop());
        if (rectF.right < rectF.left) {
            float f = rectF.right;
            rectF.right = rectF.left;
            rectF.left = f;
        }
        if (rectF.bottom < rectF.top) {
            f = rectF.top;
            rectF.top = rectF.bottom;
            rectF.bottom = f;
        }
    }

    private void m552a(Matrix matrix, View view) {
        float width = (float) view.getWidth();
        float height = (float) view.getHeight();
        boolean z = this.f450e;
        float f = z ? this.f452g : width / dm.DEFAULT_BACKOFF_MULT;
        float f2 = z ? this.f453h : height / dm.DEFAULT_BACKOFF_MULT;
        float f3 = this.f454i;
        float f4 = this.f455j;
        float f5 = this.f456k;
        if (!(f3 == 0.0f && f4 == 0.0f && f5 == 0.0f)) {
            Camera camera = this.f449d;
            camera.save();
            camera.rotateX(f3);
            camera.rotateY(f4);
            camera.rotateZ(-f5);
            camera.getMatrix(matrix);
            camera.restore();
            matrix.preTranslate(-f, -f2);
            matrix.postTranslate(f, f2);
        }
        f3 = this.f457l;
        f4 = this.f458m;
        if (!(f3 == br.DEFAULT_BACKOFF_MULT && f4 == br.DEFAULT_BACKOFF_MULT)) {
            matrix.postScale(f3, f4);
            matrix.postTranslate((-(f / width)) * ((f3 * width) - width), (-(f2 / height)) * ((f4 * height) - height));
        }
        matrix.postTranslate(this.f459n, this.f460o);
    }

    protected void applyTransformation(float f, Transformation transformation) {
        View view = (View) this.f448c.get();
        if (view != null) {
            transformation.setAlpha(this.f451f);
            m552a(transformation.getMatrix(), view);
        }
    }
}
