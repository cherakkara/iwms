package com.sothree.slidinguppanel;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.VelocityTrackerCompat;
import android.support.v4.widget.ScrollerCompat;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import com.olacabs.customer.p076d.br;
import com.olacabs.customer.utils.Constants;
import com.sothree.slidinguppanel.p086a.R.R;
import java.util.Arrays;

/* renamed from: com.sothree.slidinguppanel.a */
public class ViewDragHelper {
    private static final Interpolator f11629v;
    private int f11630a;
    private int f11631b;
    private int f11632c;
    private float[] f11633d;
    private float[] f11634e;
    private float[] f11635f;
    private float[] f11636g;
    private int[] f11637h;
    private int[] f11638i;
    private int[] f11639j;
    private int f11640k;
    private VelocityTracker f11641l;
    private float f11642m;
    private float f11643n;
    private int f11644o;
    private int f11645p;
    private ScrollerCompat f11646q;
    private final ViewDragHelper f11647r;
    private View f11648s;
    private boolean f11649t;
    private final ViewGroup f11650u;
    private final Runnable f11651w;

    /* renamed from: com.sothree.slidinguppanel.a.a */
    public static abstract class ViewDragHelper {
        public abstract boolean m14965a(View view, int i);

        public void m14961a(int i) {
        }

        public void m14964a(View view, int i, int i2, int i3, int i4) {
        }

        public void m14969b(View view, int i) {
        }

        public void m14963a(View view, float f, float f2) {
        }

        public void m14962a(int i, int i2) {
        }

        public boolean m14970b(int i) {
            return false;
        }

        public void m14968b(int i, int i2) {
        }

        public int m14971c(int i) {
            return i;
        }

        public int m14966b(View view) {
            return 0;
        }

        public int m14959a(View view) {
            return 0;
        }

        public int m14967b(View view, int i, int i2) {
            return 0;
        }

        public int m14960a(View view, int i, int i2) {
            return 0;
        }
    }

    /* renamed from: com.sothree.slidinguppanel.a.1 */
    static class ViewDragHelper implements Interpolator {
        ViewDragHelper() {
        }

        public float getInterpolation(float f) {
            float f2 = f - br.DEFAULT_BACKOFF_MULT;
            return (f2 * (((f2 * f2) * f2) * f2)) + br.DEFAULT_BACKOFF_MULT;
        }
    }

    /* renamed from: com.sothree.slidinguppanel.a.2 */
    class ViewDragHelper implements Runnable {
        final /* synthetic */ ViewDragHelper f11628a;

        ViewDragHelper(ViewDragHelper viewDragHelper) {
            this.f11628a = viewDragHelper;
        }

        public void run() {
            this.f11628a.m15033a(0);
        }
    }

    static {
        f11629v = new ViewDragHelper();
    }

    public static ViewDragHelper m15015a(ViewGroup viewGroup, ViewDragHelper viewDragHelper) {
        return new ViewDragHelper(viewGroup.getContext(), viewGroup, viewDragHelper);
    }

    public static ViewDragHelper m15014a(ViewGroup viewGroup, float f, ViewDragHelper viewDragHelper) {
        ViewDragHelper a = ViewDragHelper.m15015a(viewGroup, viewDragHelper);
        a.f11631b = (int) (((float) a.f11631b) * (br.DEFAULT_BACKOFF_MULT / f));
        return a;
    }

    private ViewDragHelper(Context context, ViewGroup viewGroup, ViewDragHelper viewDragHelper) {
        this.f11632c = -1;
        this.f11651w = new ViewDragHelper(this);
        if (viewGroup == null) {
            throw new IllegalArgumentException("Parent view may not be null");
        } else if (viewDragHelper == null) {
            throw new IllegalArgumentException("Callback may not be null");
        } else {
            this.f11650u = viewGroup;
            this.f11647r = viewDragHelper;
            ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
            this.f11644o = (int) ((context.getResources().getDisplayMetrics().density * 20.0f) + 0.5f);
            this.f11631b = viewConfiguration.getScaledTouchSlop();
            this.f11642m = (float) viewConfiguration.getScaledMaximumFlingVelocity();
            this.f11643n = (float) viewConfiguration.getScaledMinimumFlingVelocity();
            this.f11646q = ScrollerCompat.create(context, f11629v);
        }
    }

    public void m15032a(float f) {
        this.f11643n = f;
    }

    public int m15031a() {
        return this.f11630a;
    }

    public void m15034a(View view, int i) {
        if (view.getParent() != this.f11650u) {
            throw new IllegalArgumentException("captureChildView: parameter must be a descendant of the ViewDragHelper's tracked parent view (" + this.f11650u + ")");
        }
        this.f11648s = view;
        this.f11632c = i;
        this.f11647r.m14969b(view, i);
        m15033a(1);
    }

    public int m15039b() {
        return this.f11631b;
    }

    public void m15045c() {
        this.f11632c = -1;
        m15029e();
        if (this.f11641l != null) {
            this.f11641l.recycle();
            this.f11641l = null;
        }
    }

    public void m15046d() {
        m15045c();
        if (this.f11630a == 2) {
            int currX = this.f11646q.getCurrX();
            int currY = this.f11646q.getCurrY();
            this.f11646q.abortAnimation();
            int currX2 = this.f11646q.getCurrX();
            int currY2 = this.f11646q.getCurrY();
            this.f11647r.m14964a(this.f11648s, currX2, currY2, currX2 - currX, currY2 - currY);
        }
        m15033a(0);
    }

    public boolean m15037a(View view, int i, int i2) {
        this.f11648s = view;
        this.f11632c = -1;
        return m15019a(i, i2, 0, 0);
    }

    public boolean m15035a(int i, int i2) {
        if (this.f11649t) {
            return m15019a(i, i2, (int) VelocityTrackerCompat.getXVelocity(this.f11641l, this.f11632c), (int) VelocityTrackerCompat.getYVelocity(this.f11641l, this.f11632c));
        }
        throw new IllegalStateException("Cannot settleCapturedViewAt outside of a call to Callback#onViewReleased");
    }

    private boolean m15019a(int i, int i2, int i3, int i4) {
        int left = this.f11648s.getLeft();
        int top = this.f11648s.getTop();
        int i5 = i - left;
        int i6 = i2 - top;
        if (i5 == 0 && i6 == 0) {
            this.f11646q.abortAnimation();
            m15033a(0);
            return false;
        }
        this.f11646q.startScroll(left, top, i5, i6, m15013a(this.f11648s, i5, i6, i3, i4));
        m15033a(2);
        return true;
    }

    private int m15013a(View view, int i, int i2, int i3, int i4) {
        int b = m15022b(i3, (int) this.f11643n, (int) this.f11642m);
        int b2 = m15022b(i4, (int) this.f11643n, (int) this.f11642m);
        int abs = Math.abs(i);
        int abs2 = Math.abs(i2);
        int abs3 = Math.abs(b);
        int abs4 = Math.abs(b2);
        int i5 = abs3 + abs4;
        int i6 = abs + abs2;
        return (int) (((b2 != 0 ? ((float) abs4) / ((float) i5) : ((float) abs2) / ((float) i6)) * ((float) m15012a(i2, b2, this.f11647r.m14959a(view)))) + ((b != 0 ? ((float) abs3) / ((float) i5) : ((float) abs) / ((float) i6)) * ((float) m15012a(i, b, this.f11647r.m14966b(view)))));
    }

    private int m15012a(int i, int i2, int i3) {
        if (i == 0) {
            return 0;
        }
        int width = this.f11650u.getWidth();
        int i4 = width / 2;
        float b = (m15021b(Math.min(br.DEFAULT_BACKOFF_MULT, ((float) Math.abs(i)) / ((float) width))) * ((float) i4)) + ((float) i4);
        i4 = Math.abs(i2);
        if (i4 > 0) {
            width = Math.round(Math.abs(b / ((float) i4)) * 1000.0f) * 4;
        } else {
            width = (int) (((((float) Math.abs(i)) / ((float) i3)) + br.DEFAULT_BACKOFF_MULT) * 256.0f);
        }
        return Math.min(width, 600);
    }

    private int m15022b(int i, int i2, int i3) {
        int abs = Math.abs(i);
        if (abs < i2) {
            return 0;
        }
        if (abs <= i3) {
            return i;
        }
        if (i <= 0) {
            return -i3;
        }
        return i3;
    }

    private float m15011a(float f, float f2, float f3) {
        float abs = Math.abs(f);
        if (abs < f2) {
            return 0.0f;
        }
        if (abs <= f3) {
            return f;
        }
        if (f <= 0.0f) {
            return -f3;
        }
        return f3;
    }

    private float m15021b(float f) {
        return (float) Math.sin((double) ((float) (((double) (f - 0.5f)) * 0.4712389167638204d)));
    }

    public boolean m15038a(boolean z) {
        if (this.f11648s == null) {
            return false;
        }
        if (this.f11630a == 2) {
            boolean isFinished;
            boolean computeScrollOffset = this.f11646q.computeScrollOffset();
            int currX = this.f11646q.getCurrX();
            int currY = this.f11646q.getCurrY();
            int left = currX - this.f11648s.getLeft();
            int top = currY - this.f11648s.getTop();
            if (left != 0) {
                this.f11648s.offsetLeftAndRight(left);
            }
            if (top != 0) {
                this.f11648s.offsetTopAndBottom(top);
            }
            if (!(left == 0 && top == 0)) {
                this.f11647r.m14964a(this.f11648s, currX, currY, left, top);
            }
            if (computeScrollOffset && currX == this.f11646q.getFinalX() && currY == this.f11646q.getFinalY()) {
                this.f11646q.abortAnimation();
                isFinished = this.f11646q.isFinished();
            } else {
                isFinished = computeScrollOffset;
            }
            if (!isFinished) {
                if (z) {
                    this.f11650u.post(this.f11651w);
                } else {
                    m15033a(0);
                }
            }
        }
        return this.f11630a == 2;
    }

    private void m15016a(float f, float f2) {
        this.f11649t = true;
        this.f11647r.m14963a(this.f11648s, f, f2);
        this.f11649t = false;
        if (this.f11630a == 1) {
            m15033a(0);
        }
    }

    private void m15029e() {
        if (this.f11633d != null) {
            Arrays.fill(this.f11633d, 0.0f);
            Arrays.fill(this.f11634e, 0.0f);
            Arrays.fill(this.f11635f, 0.0f);
            Arrays.fill(this.f11636g, 0.0f);
            Arrays.fill(this.f11637h, 0);
            Arrays.fill(this.f11638i, 0);
            Arrays.fill(this.f11639j, 0);
            this.f11640k = 0;
        }
    }

    private void m15024b(int i) {
        if (this.f11633d != null) {
            this.f11633d[i] = 0.0f;
            this.f11634e[i] = 0.0f;
            this.f11635f[i] = 0.0f;
            this.f11636g[i] = 0.0f;
            this.f11637h[i] = 0;
            this.f11638i[i] = 0;
            this.f11639j[i] = 0;
            this.f11640k &= (1 << i) ^ -1;
        }
    }

    private void m15026c(int i) {
        if (this.f11633d == null || this.f11633d.length <= i) {
            Object obj = new float[(i + 1)];
            Object obj2 = new float[(i + 1)];
            Object obj3 = new float[(i + 1)];
            Object obj4 = new float[(i + 1)];
            Object obj5 = new int[(i + 1)];
            Object obj6 = new int[(i + 1)];
            Object obj7 = new int[(i + 1)];
            if (this.f11633d != null) {
                System.arraycopy(this.f11633d, 0, obj, 0, this.f11633d.length);
                System.arraycopy(this.f11634e, 0, obj2, 0, this.f11634e.length);
                System.arraycopy(this.f11635f, 0, obj3, 0, this.f11635f.length);
                System.arraycopy(this.f11636g, 0, obj4, 0, this.f11636g.length);
                System.arraycopy(this.f11637h, 0, obj5, 0, this.f11637h.length);
                System.arraycopy(this.f11638i, 0, obj6, 0, this.f11638i.length);
                System.arraycopy(this.f11639j, 0, obj7, 0, this.f11639j.length);
            }
            this.f11633d = obj;
            this.f11634e = obj2;
            this.f11635f = obj3;
            this.f11636g = obj4;
            this.f11637h = obj5;
            this.f11638i = obj6;
            this.f11639j = obj7;
        }
    }

    private void m15017a(float f, float f2, int i) {
        m15026c(i);
        float[] fArr = this.f11633d;
        this.f11635f[i] = f;
        fArr[i] = f;
        fArr = this.f11634e;
        this.f11636g[i] = f2;
        fArr[i] = f2;
        this.f11637h[i] = m15028d((int) f, (int) f2);
        this.f11640k |= 1 << i;
    }

    private void m15027c(MotionEvent motionEvent) {
        int pointerCount = MotionEventCompat.getPointerCount(motionEvent);
        for (int i = 0; i < pointerCount; i++) {
            int pointerId = MotionEventCompat.getPointerId(motionEvent, i);
            float x = MotionEventCompat.getX(motionEvent, i);
            float y = MotionEventCompat.getY(motionEvent, i);
            if (!(this.f11635f == null || this.f11636g == null)) {
                this.f11635f[pointerId] = x;
                this.f11636g[pointerId] = y;
            }
        }
    }

    void m15033a(int i) {
        if (this.f11630a != i) {
            this.f11630a = i;
            this.f11647r.m14961a(i);
            if (i == 0) {
                this.f11648s = null;
            }
        }
    }

    boolean m15042b(View view, int i) {
        if (view == this.f11648s && this.f11632c == i) {
            return true;
        }
        if (view == null || !this.f11647r.m14965a(view, i)) {
            return false;
        }
        this.f11632c = i;
        m15034a(view, i);
        return true;
    }

    public boolean m15036a(MotionEvent motionEvent) {
        int actionMasked = MotionEventCompat.getActionMasked(motionEvent);
        int actionIndex = MotionEventCompat.getActionIndex(motionEvent);
        if (actionMasked == 0) {
            m15045c();
        }
        if (this.f11641l == null) {
            this.f11641l = VelocityTracker.obtain();
        }
        this.f11641l.addMovement(motionEvent);
        float y;
        int pointerId;
        switch (actionMasked) {
            case R.SlidingUpPanelLayout_umanoPanelHeight /*0*/:
                float x = motionEvent.getX();
                y = motionEvent.getY();
                pointerId = MotionEventCompat.getPointerId(motionEvent, 0);
                m15017a(x, y, pointerId);
                View c = m15044c((int) x, (int) y);
                if (c == this.f11648s && this.f11630a == 2) {
                    m15042b(c, pointerId);
                }
                actionMasked = this.f11637h[pointerId];
                if ((this.f11645p & actionMasked) != 0) {
                    this.f11647r.m14962a(actionMasked & this.f11645p, pointerId);
                    break;
                }
                break;
            case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
            case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                m15045c();
                break;
            case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                actionIndex = MotionEventCompat.getPointerCount(motionEvent);
                for (actionMasked = 0; actionMasked < actionIndex && this.f11633d != null && this.f11634e != null; actionMasked++) {
                    pointerId = MotionEventCompat.getPointerId(motionEvent, actionMasked);
                    if (pointerId < this.f11633d.length && pointerId < this.f11634e.length) {
                        float x2 = MotionEventCompat.getX(motionEvent, actionMasked);
                        x2 -= this.f11633d[pointerId];
                        float y2 = MotionEventCompat.getY(motionEvent, actionMasked) - this.f11634e[pointerId];
                        m15023b(x2, y2, pointerId);
                        if (this.f11630a != 1) {
                            View c2 = m15044c((int) this.f11633d[pointerId], (int) this.f11634e[pointerId]);
                            if (c2 != null && m15020a(c2, x2, y2) && m15042b(c2, pointerId)) {
                            }
                        }
                    }
                }
                m15027c(motionEvent);
                break;
            case R.SlidingUpPanelLayout_umanoDragView /*5*/:
                actionMasked = MotionEventCompat.getPointerId(motionEvent, actionIndex);
                float x3 = MotionEventCompat.getX(motionEvent, actionIndex);
                y = MotionEventCompat.getY(motionEvent, actionIndex);
                m15017a(x3, y, actionMasked);
                if (this.f11630a != 0) {
                    if (this.f11630a == 2) {
                        View c3 = m15044c((int) x3, (int) y);
                        if (c3 == this.f11648s) {
                            m15042b(c3, actionMasked);
                            break;
                        }
                    }
                }
                actionIndex = this.f11637h[actionMasked];
                if ((this.f11645p & actionIndex) != 0) {
                    this.f11647r.m14962a(actionIndex & this.f11645p, actionMasked);
                    break;
                }
                break;
            case R.SlidingUpPanelLayout_umanoOverlay /*6*/:
                m15024b(MotionEventCompat.getPointerId(motionEvent, actionIndex));
                break;
        }
        if (this.f11630a == 1) {
            return true;
        }
        return false;
    }

    public void m15040b(MotionEvent motionEvent) {
        int i = 0;
        int actionMasked = MotionEventCompat.getActionMasked(motionEvent);
        int actionIndex = MotionEventCompat.getActionIndex(motionEvent);
        if (actionMasked == 0) {
            m15045c();
        }
        if (this.f11641l == null) {
            this.f11641l = VelocityTracker.obtain();
        }
        this.f11641l.addMovement(motionEvent);
        float x;
        float y;
        View c;
        int i2;
        switch (actionMasked) {
            case R.SlidingUpPanelLayout_umanoPanelHeight /*0*/:
                x = motionEvent.getX();
                y = motionEvent.getY();
                i = MotionEventCompat.getPointerId(motionEvent, 0);
                c = m15044c((int) x, (int) y);
                m15017a(x, y, i);
                m15042b(c, i);
                i2 = this.f11637h[i];
                if ((this.f11645p & i2) != 0) {
                    this.f11647r.m14962a(i2 & this.f11645p, i);
                }
            case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                if (this.f11630a == 1) {
                    m15030f();
                }
                m15045c();
            case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                if (this.f11630a == 1) {
                    i = MotionEventCompat.findPointerIndex(motionEvent, this.f11632c);
                    x = MotionEventCompat.getX(motionEvent, i);
                    i2 = (int) (x - this.f11635f[this.f11632c]);
                    i = (int) (MotionEventCompat.getY(motionEvent, i) - this.f11636g[this.f11632c]);
                    m15025b(this.f11648s.getLeft() + i2, this.f11648s.getTop() + i, i2, i);
                    m15027c(motionEvent);
                    return;
                }
                i2 = MotionEventCompat.getPointerCount(motionEvent);
                while (i < i2) {
                    actionMasked = MotionEventCompat.getPointerId(motionEvent, i);
                    float x2 = MotionEventCompat.getX(motionEvent, i);
                    float y2 = MotionEventCompat.getY(motionEvent, i);
                    float f = x2 - this.f11633d[actionMasked];
                    float f2 = y2 - this.f11634e[actionMasked];
                    m15023b(f, f2, actionMasked);
                    if (this.f11630a != 1) {
                        c = m15044c((int) x2, (int) y2);
                        if (!m15020a(c, f, f2) || !m15042b(c, actionMasked)) {
                            i++;
                        }
                    }
                    m15027c(motionEvent);
                }
                m15027c(motionEvent);
            case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                if (this.f11630a == 1) {
                    m15016a(0.0f, 0.0f);
                }
                m15045c();
            case R.SlidingUpPanelLayout_umanoDragView /*5*/:
                i = MotionEventCompat.getPointerId(motionEvent, actionIndex);
                x = MotionEventCompat.getX(motionEvent, actionIndex);
                y = MotionEventCompat.getY(motionEvent, actionIndex);
                m15017a(x, y, i);
                if (this.f11630a == 0) {
                    m15042b(m15044c((int) x, (int) y), i);
                    i2 = this.f11637h[i];
                    if ((this.f11645p & i2) != 0) {
                        this.f11647r.m14962a(i2 & this.f11645p, i);
                    }
                } else if (m15041b((int) x, (int) y)) {
                    m15042b(this.f11648s, i);
                }
            case R.SlidingUpPanelLayout_umanoOverlay /*6*/:
                actionMasked = MotionEventCompat.getPointerId(motionEvent, actionIndex);
                if (this.f11630a == 1 && actionMasked == this.f11632c) {
                    actionIndex = MotionEventCompat.getPointerCount(motionEvent);
                    while (i < actionIndex) {
                        int pointerId = MotionEventCompat.getPointerId(motionEvent, i);
                        if (pointerId != this.f11632c) {
                            if (m15044c((int) MotionEventCompat.getX(motionEvent, i), (int) MotionEventCompat.getY(motionEvent, i)) == this.f11648s && m15042b(this.f11648s, pointerId)) {
                                i = this.f11632c;
                                if (i == -1) {
                                    m15030f();
                                }
                            }
                        }
                        i++;
                    }
                    i = -1;
                    if (i == -1) {
                        m15030f();
                    }
                }
                m15024b(actionMasked);
            default:
        }
    }

    private void m15023b(float f, float f2, int i) {
        int i2 = 1;
        if (!m15018a(f, f2, i, 1)) {
            i2 = 0;
        }
        if (m15018a(f2, f, i, 4)) {
            i2 |= 4;
        }
        if (m15018a(f, f2, i, 2)) {
            i2 |= 2;
        }
        if (m15018a(f2, f, i, 8)) {
            i2 |= 8;
        }
        if (i2 != 0) {
            int[] iArr = this.f11638i;
            iArr[i] = iArr[i] | i2;
            this.f11647r.m14968b(i2, i);
        }
    }

    private boolean m15018a(float f, float f2, int i, int i2) {
        float abs = Math.abs(f);
        float abs2 = Math.abs(f2);
        if ((this.f11637h[i] & i2) != i2 || (this.f11645p & i2) == 0 || (this.f11639j[i] & i2) == i2 || (this.f11638i[i] & i2) == i2) {
            return false;
        }
        if (abs <= ((float) this.f11631b) && abs2 <= ((float) this.f11631b)) {
            return false;
        }
        if (abs < abs2 * 0.5f && this.f11647r.m14970b(i2)) {
            int[] iArr = this.f11639j;
            iArr[i] = iArr[i] | i2;
            return false;
        } else if ((this.f11638i[i] & i2) != 0 || abs <= ((float) this.f11631b)) {
            return false;
        } else {
            return true;
        }
    }

    private boolean m15020a(View view, float f, float f2) {
        if (view == null) {
            return false;
        }
        boolean z;
        boolean z2;
        if (this.f11647r.m14966b(view) > 0) {
            z = true;
        } else {
            z = false;
        }
        if (this.f11647r.m14959a(view) > 0) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z && z2) {
            if ((f * f) + (f2 * f2) <= ((float) (this.f11631b * this.f11631b))) {
                return false;
            }
            return true;
        } else if (z) {
            if (Math.abs(f) <= ((float) this.f11631b)) {
                return false;
            }
            return true;
        } else if (!z2) {
            return false;
        } else {
            if (Math.abs(f2) <= ((float) this.f11631b)) {
                return false;
            }
            return true;
        }
    }

    private void m15030f() {
        this.f11641l.computeCurrentVelocity(Constants.MILLIS_IN_A_SECOND, this.f11642m);
        m15016a(m15011a(VelocityTrackerCompat.getXVelocity(this.f11641l, this.f11632c), this.f11643n, this.f11642m), m15011a(VelocityTrackerCompat.getYVelocity(this.f11641l, this.f11632c), this.f11643n, this.f11642m));
    }

    private void m15025b(int i, int i2, int i3, int i4) {
        int b;
        int a;
        int left = this.f11648s.getLeft();
        int top = this.f11648s.getTop();
        if (i3 != 0) {
            b = this.f11647r.m14967b(this.f11648s, i, i3);
            this.f11648s.offsetLeftAndRight(b - left);
        } else {
            b = i;
        }
        if (i4 != 0) {
            a = this.f11647r.m14960a(this.f11648s, i2, i4);
            this.f11648s.offsetTopAndBottom(a - top);
        } else {
            a = i2;
        }
        if (i3 != 0 || i4 != 0) {
            this.f11647r.m14964a(this.f11648s, b, a, b - left, a - top);
        }
    }

    public boolean m15041b(int i, int i2) {
        return m15043b(this.f11648s, i, i2);
    }

    public boolean m15043b(View view, int i, int i2) {
        if (view != null && i >= view.getLeft() && i < view.getRight() && i2 >= view.getTop() && i2 < view.getBottom()) {
            return true;
        }
        return false;
    }

    public View m15044c(int i, int i2) {
        for (int childCount = this.f11650u.getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = this.f11650u.getChildAt(this.f11647r.m14971c(childCount));
            if (i >= childAt.getLeft() && i < childAt.getRight() && i2 >= childAt.getTop() && i2 < childAt.getBottom()) {
                return childAt;
            }
        }
        return null;
    }

    private int m15028d(int i, int i2) {
        int i3 = 0;
        if (i < this.f11650u.getLeft() + this.f11644o) {
            i3 = 1;
        }
        if (i2 < this.f11650u.getTop() + this.f11644o) {
            i3 |= 4;
        }
        if (i > this.f11650u.getRight() - this.f11644o) {
            i3 |= 2;
        }
        if (i2 > this.f11650u.getBottom() - this.f11644o) {
            return i3 | 8;
        }
        return i3;
    }
}
