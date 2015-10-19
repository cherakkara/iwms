package com.google.android.m4b.maps.aj;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.view.MotionEventCompat;
import android.util.DisplayMetrics;
import android.view.GestureDetector.OnDoubleTapListener;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import com.google.android.m4b.maps.aj.Gesture.Gesture;
import com.google.android.m4b.maps.p040u.Config;
import com.google.android.m4b.maps.p040u.UserEventReporter;
import com.google.p025a.p028c.ar;
import com.olacabs.customer.p076d.br;
import com.sothree.slidinguppanel.p086a.R.R;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/* renamed from: com.google.android.m4b.maps.aj.i */
public final class GmmGestureDetector {
    private float f3202A;
    private float f3203B;
    private float f3204C;
    private boolean f3205D;
    private boolean f3206E;
    private boolean f3207F;
    private boolean f3208G;
    private GestureDetector f3209H;
    private Context f3210a;
    private MotionEvent f3211b;
    private MotionEvent f3212c;
    private final List<Gesture> f3213d;
    private final List<Gesture> f3214e;
    private final Gesture f3215f;
    private final Gesture f3216g;
    private final Gesture f3217h;
    private final LinkedList<GestureMotionEvent> f3218i;
    private long f3219j;
    private float f3220k;
    private float f3221l;
    private float f3222m;
    private float f3223n;
    private float f3224o;
    private float f3225p;
    private float f3226q;
    private float f3227r;
    private float f3228s;
    private float f3229t;
    private float f3230u;
    private float f3231v;
    private float f3232w;
    private float f3233x;
    private float f3234y;
    private float f3235z;

    /* renamed from: com.google.android.m4b.maps.aj.i.b */
    public interface GmmGestureDetector extends OnDoubleTapListener, OnGestureListener {
        boolean m5125a(GmmGestureDetector gmmGestureDetector);

        boolean m5126a(GmmGestureDetector gmmGestureDetector, boolean z);

        boolean m5127b(GmmGestureDetector gmmGestureDetector);

        boolean m5128b(GmmGestureDetector gmmGestureDetector, boolean z);

        void m5129c(GmmGestureDetector gmmGestureDetector);

        void m5130c(GmmGestureDetector gmmGestureDetector, boolean z);

        boolean m5131d(GmmGestureDetector gmmGestureDetector);

        boolean m5132e(GmmGestureDetector gmmGestureDetector);

        void m5133f(GmmGestureDetector gmmGestureDetector);

        boolean m5134g(GmmGestureDetector gmmGestureDetector);

        boolean m5135h(GmmGestureDetector gmmGestureDetector);

        void m5136i(GmmGestureDetector gmmGestureDetector);
    }

    /* renamed from: com.google.android.m4b.maps.aj.i.1 */
    static /* synthetic */ class GmmGestureDetector {
        static final /* synthetic */ int[] f3201a;

        static {
            f3201a = new int[Gesture.values().length];
            try {
                f3201a[Gesture.NO.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f3201a[Gesture.MAYBE.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f3201a[Gesture.YES.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
        }
    }

    /* renamed from: com.google.android.m4b.maps.aj.i.a */
    public interface GmmGestureDetector extends OnDoubleTapListener, OnGestureListener {
        boolean m5159a(RotateEvent rotateEvent);

        boolean m5160a(ScaleEvent scaleEvent);

        boolean m5161a(TiltEvent tiltEvent);
    }

    public GmmGestureDetector(Context context, GmmGestureDetector gmmGestureDetector) {
        this.f3213d = ar.m2515a();
        this.f3214e = ar.m2515a();
        this.f3218i = new LinkedList();
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        this.f3210a = context;
        this.f3202A = (float) viewConfiguration.getScaledEdgeSlop();
        List list = this.f3213d;
        Gesture tiltGesture = new TiltGesture(gmmGestureDetector);
        this.f3216g = tiltGesture;
        list.add(tiltGesture);
        if (Config.m11320a().m11337k()) {
            list = this.f3213d;
            tiltGesture = new RotateGesture(gmmGestureDetector);
            this.f3217h = tiltGesture;
            list.add(tiltGesture);
            UserEventReporter.m11506a("MD", "T");
        } else {
            list = this.f3213d;
            tiltGesture = new SideSwipeRotateGesture(gmmGestureDetector);
            this.f3217h = tiltGesture;
            list.add(tiltGesture);
            UserEventReporter.m11506a("MD", "F");
        }
        list = this.f3213d;
        tiltGesture = new ScaleGesture(gmmGestureDetector);
        this.f3215f = tiltGesture;
        list.add(tiltGesture);
        this.f3213d.add(new TwoFingerTapScaleGesture(gmmGestureDetector));
        this.f3209H = new GestureDetector(context, gmmGestureDetector, new Handler(Looper.getMainLooper()));
        this.f3209H.m5157a(true);
        this.f3209H.m5156a((OnDoubleTapListener) gmmGestureDetector);
    }

    public final boolean m5170a(MotionEvent motionEvent) {
        int i = 0;
        int action = motionEvent.getAction();
        int i2 = (MotionEventCompat.ACTION_POINTER_INDEX_MASK & action) >> 8;
        int i3;
        if (this.f3208G) {
            boolean z;
            if (this.f3214e.isEmpty()) {
                z = false;
            } else {
                z = true;
            }
            if (z) {
                switch (action) {
                    case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    case R.SlidingUpPanelLayout_umanoOverlay /*6*/:
                    case 262:
                        m5166b(motionEvent);
                        if (i2 == 0) {
                            i = motionEvent.getPointerCount() - 1;
                        }
                        this.f3220k = motionEvent.getX(i);
                        this.f3221l = motionEvent.getY(i);
                        if (!this.f3205D) {
                            m5167g();
                        }
                        m5168h();
                        break;
                    case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                        m5166b(motionEvent);
                        m5163a(this.f3212c, null);
                        if (this.f3234y / this.f3235z > 0.67f) {
                            i3 = 0;
                            for (Gesture e : this.f3214e) {
                                i3 = e.m5123e(this) | i3;
                            }
                            if (i3 != 0) {
                                this.f3211b = MotionEvent.obtain(motionEvent);
                                break;
                            }
                        }
                        break;
                    case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                        if (!this.f3205D) {
                            m5167g();
                        }
                        m5168h();
                        break;
                    default:
                        break;
                }
            }
            m5163a(MotionEvent.obtain(motionEvent), null);
        } else if (action == 5 || action == 261 || action == 0) {
            DisplayMetrics displayMetrics = this.f3210a.getResources().getDisplayMetrics();
            this.f3203B = ((float) displayMetrics.widthPixels) - this.f3202A;
            this.f3204C = ((float) displayMetrics.heightPixels) - this.f3202A;
            m5168h();
            this.f3211b = MotionEvent.obtain(motionEvent);
            m5166b(motionEvent);
            r1 = this.f3202A;
            r4 = this.f3203B;
            r5 = this.f3204C;
            r3 = motionEvent.getRawX();
            r6 = motionEvent.getRawY();
            r7 = GmmGestureDetector.m5162a(motionEvent, motionEvent.getPointerCount() - 1);
            r8 = GmmGestureDetector.m5165b(motionEvent, motionEvent.getPointerCount() - 1);
            action = (r3 < r1 || r6 < r1 || r3 > r4 || r6 > r5) ? true : 0;
            if (r7 < r1 || r8 < r1 || r7 > r4 || r8 > r5) {
                i3 = true;
            } else {
                i3 = 0;
            }
            if (action != 0 && i3 != 0) {
                this.f3220k = -1.0f;
                this.f3221l = -1.0f;
                this.f3205D = true;
            } else if (action != 0) {
                this.f3220k = motionEvent.getX(motionEvent.getPointerCount() - 1);
                this.f3221l = motionEvent.getY(motionEvent.getPointerCount() - 1);
                this.f3205D = true;
            } else if (i3 != 0) {
                this.f3220k = motionEvent.getX(0);
                this.f3221l = motionEvent.getY(0);
                this.f3205D = true;
            } else {
                this.f3208G = true;
            }
        } else if (action == 2 && this.f3205D) {
            r1 = this.f3202A;
            r4 = this.f3203B;
            r5 = this.f3204C;
            r3 = motionEvent.getRawX();
            r6 = motionEvent.getRawY();
            r7 = GmmGestureDetector.m5162a(motionEvent, motionEvent.getPointerCount() - 1);
            r8 = GmmGestureDetector.m5165b(motionEvent, motionEvent.getPointerCount() - 1);
            action = (r3 < r1 || r6 < r1 || r3 > r4 || r6 > r5) ? true : 0;
            if (r7 < r1 || r8 < r1 || r7 > r4 || r8 > r5) {
                i3 = true;
            } else {
                i3 = 0;
            }
            if (action != 0 && i3 != 0) {
                this.f3220k = -1.0f;
                this.f3221l = -1.0f;
            } else if (action != 0) {
                this.f3220k = motionEvent.getX(motionEvent.getPointerCount() - 1);
                this.f3221l = motionEvent.getY(motionEvent.getPointerCount() - 1);
            } else if (i3 != 0) {
                this.f3220k = motionEvent.getX(0);
                this.f3221l = motionEvent.getY(0);
            } else {
                this.f3205D = false;
                this.f3208G = true;
            }
        } else if ((action == 6 || action == 262 || action == 1) && this.f3205D) {
            if (i2 == 0) {
                i = motionEvent.getPointerCount() - 1;
            }
            this.f3220k = motionEvent.getX(i);
            this.f3221l = motionEvent.getY(i);
        }
        this.f3209H.m5158a(motionEvent);
        return true;
    }

    private static boolean m5164a(Gesture gesture) {
        return gesture != null && gesture.m5115a();
    }

    private void m5167g() {
        for (Gesture c : this.f3214e) {
            c.m5119c(this);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void m5163a(android.view.MotionEvent r11, java.lang.StringBuilder r12) {
        /*
        r10 = this;
        r5 = 1;
        r8 = 0;
        r1 = r11.getAction();
        r0 = r10.f3218i;
        r0 = r0.isEmpty();
        if (r0 == 0) goto L_0x0014;
    L_0x000e:
        r2 = r11.getEventTime();
        r10.f3219j = r2;
    L_0x0014:
        r0 = r10.f3218i;
        r2 = new com.google.android.m4b.maps.aj.a;
        r2.<init>(r11);
        r0.addLast(r2);
        r0 = r10.f3218i;
        r0 = r0.size();
        r2 = 20;
        if (r0 <= r2) goto L_0x0033;
    L_0x0028:
        r0 = r10.f3218i;
        r0 = r0.removeFirst();
        r0 = (com.google.android.m4b.maps.aj.GestureMotionEvent) r0;
        r0.m5084e();
    L_0x0033:
        r0 = r10.f3218i;
        r0 = r0.getFirst();
        r0 = (com.google.android.m4b.maps.aj.GestureMotionEvent) r0;
        r2 = r0.m5079a();
        r0 = r10.f3218i;
        r0 = r0.getLast();
        r0 = (com.google.android.m4b.maps.aj.GestureMotionEvent) r0;
        r6 = r0.m5079a();
        r2 = r6 - r2;
        r6 = 250; // 0xfa float:3.5E-43 double:1.235E-321;
        r0 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1));
        if (r0 < 0) goto L_0x006b;
    L_0x0053:
        r0 = r5;
    L_0x0054:
        if (r0 == 0) goto L_0x006d;
    L_0x0056:
        r0 = r10.f3218i;
        r0 = r0.size();
        r2 = 3;
        if (r0 <= r2) goto L_0x006d;
    L_0x005f:
        r0 = r10.f3218i;
        r0 = r0.removeFirst();
        r0 = (com.google.android.m4b.maps.aj.GestureMotionEvent) r0;
        r0.m5084e();
        goto L_0x0033;
    L_0x006b:
        r0 = r8;
        goto L_0x0054;
    L_0x006d:
        switch(r1) {
            case 1: goto L_0x0071;
            case 3: goto L_0x00ac;
            case 6: goto L_0x0071;
            case 262: goto L_0x0071;
            default: goto L_0x0070;
        };
    L_0x0070:
        r5 = r8;
    L_0x0071:
        r0 = r10.f3208G;
        if (r0 == 0) goto L_0x00a4;
    L_0x0075:
        r0 = r10.f3213d;
        r0 = r0.iterator();
    L_0x007b:
        r1 = r0.hasNext();
        if (r1 == 0) goto L_0x00a4;
    L_0x0081:
        r1 = r0.next();
        r1 = (com.google.android.m4b.maps.aj.Gesture) r1;
        r2 = r1.m5115a();
        if (r2 != 0) goto L_0x007b;
    L_0x008d:
        r9 = com.google.android.m4b.maps.aj.GmmGestureDetector.GmmGestureDetector.f3201a;
        r2 = r10.f3219j;
        r4 = r10.f3218i;
        r6 = r10.f3214e;
        r7 = 0;
        r2 = r1.m5114a(r2, r4, r5, r6, r7);
        r2 = r2.ordinal();
        r2 = r9[r2];
        switch(r2) {
            case 1: goto L_0x007b;
            case 2: goto L_0x00a4;
            case 3: goto L_0x00af;
            default: goto L_0x00a3;
        };
    L_0x00a3:
        goto L_0x007b;
    L_0x00a4:
        if (r5 == 0) goto L_0x00ab;
    L_0x00a6:
        r10.m5167g();
        r10.f3208G = r8;
    L_0x00ab:
        return;
    L_0x00ac:
        r10.f3208G = r8;
        goto L_0x0070;
    L_0x00af:
        r2 = r1.m5116a(r10);
        if (r2 == 0) goto L_0x007b;
    L_0x00b5:
        r2 = r10.f3214e;
        r2.add(r1);
        goto L_0x007b;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.m4b.maps.aj.i.a(android.view.MotionEvent, java.lang.StringBuilder):void");
    }

    private static float m5162a(MotionEvent motionEvent, int i) {
        return (motionEvent.getRawX() - motionEvent.getX()) + motionEvent.getX(i);
    }

    private static float m5165b(MotionEvent motionEvent, int i) {
        return (motionEvent.getRawY() - motionEvent.getY()) + motionEvent.getY(i);
    }

    private void m5166b(MotionEvent motionEvent) {
        this.f3212c = MotionEvent.obtain(motionEvent);
        this.f3227r = -1.0f;
        this.f3228s = -1.0f;
        this.f3231v = -1.0f;
        this.f3232w = 0.0f;
        this.f3206E = false;
        this.f3207F = false;
        MotionEvent motionEvent2 = this.f3211b;
        float x = motionEvent2.getX(0);
        float y = motionEvent2.getY(0);
        float x2 = motionEvent2.getX(motionEvent2.getPointerCount() - 1);
        float y2 = motionEvent2.getY(motionEvent2.getPointerCount() - 1);
        float x3 = motionEvent.getX(0);
        float y3 = motionEvent.getY(0);
        x2 -= x;
        y2 -= y;
        float x4 = motionEvent.getX(motionEvent.getPointerCount() - 1) - x3;
        float y4 = motionEvent.getY(motionEvent.getPointerCount() - 1) - y3;
        this.f3223n = x2;
        this.f3224o = y2;
        this.f3225p = x4;
        this.f3226q = y4;
        this.f3229t = y;
        this.f3230u = y3;
        this.f3220k = (x4 * 0.5f) + x3;
        this.f3221l = (y4 * 0.5f) + y3;
        this.f3222m = x + (x2 * 0.5f);
        motionEvent.getEventTime();
        motionEvent2.getEventTime();
        this.f3234y = motionEvent.getPressure(0) + motionEvent.getPressure(motionEvent.getPointerCount() - 1);
        this.f3235z = motionEvent2.getPressure(motionEvent2.getPointerCount() - 1) + motionEvent2.getPressure(0);
    }

    private void m5168h() {
        this.f3211b = null;
        this.f3212c = null;
        this.f3205D = false;
        this.f3208G = false;
        this.f3214e.clear();
        Iterator it = this.f3218i.iterator();
        while (it.hasNext()) {
            ((GestureMotionEvent) it.next()).m5084e();
        }
        this.f3218i.clear();
        for (Gesture gesture : this.f3213d) {
            if (gesture.m5115a()) {
                gesture.m5119c(this);
            }
        }
    }

    public final float m5169a() {
        return this.f3220k;
    }

    public final float m5171b() {
        return this.f3221l;
    }

    public final float m5172c() {
        return this.f3222m;
    }

    public final float m5173d() {
        if (!GmmGestureDetector.m5164a(this.f3215f) || this.f3212c.getPointerCount() != this.f3211b.getPointerCount()) {
            return br.DEFAULT_BACKOFF_MULT;
        }
        if (this.f3231v == -1.0f) {
            float f;
            float f2;
            if (this.f3227r == -1.0f) {
                f = this.f3225p;
                f2 = this.f3226q;
                this.f3227r = (float) Math.sqrt((double) ((f * f) + (f2 * f2)));
            }
            f = this.f3227r;
            if (this.f3228s == -1.0f) {
                f2 = this.f3223n;
                float f3 = this.f3224o;
                this.f3228s = (float) Math.sqrt((double) ((f2 * f2) + (f3 * f3)));
            }
            this.f3231v = f / this.f3228s;
        }
        return this.f3231v;
    }

    public final float m5174e() {
        if (!GmmGestureDetector.m5164a(this.f3216g)) {
            return 0.0f;
        }
        if (!this.f3206E) {
            this.f3232w = (this.f3230u - this.f3229t) * 0.25f;
            this.f3206E = true;
        }
        return this.f3232w;
    }

    public final float m5175f() {
        if (!GmmGestureDetector.m5164a(this.f3217h) || this.f3212c.getPointerCount() != this.f3211b.getPointerCount()) {
            return 0.0f;
        }
        if (!this.f3207F) {
            this.f3233x = Gesture.m5112a(GestureMotionEvent.m5077a(this.f3211b.getX(0), this.f3211b.getY(0), this.f3211b.getX(this.f3211b.getPointerCount() - 1), this.f3211b.getY(this.f3211b.getPointerCount() - 1)), GestureMotionEvent.m5077a(this.f3212c.getX(0), this.f3212c.getY(0), this.f3212c.getX(this.f3212c.getPointerCount() - 1), this.f3212c.getY(this.f3212c.getPointerCount() - 1)));
            this.f3207F = true;
        }
        return this.f3233x;
    }
}
