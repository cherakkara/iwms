package com.google.android.m4b.maps.aj;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.MotionEventCompat;
import android.view.GestureDetector.OnDoubleTapListener;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;
import com.olacabs.customer.p076d.br;
import com.olacabs.customer.utils.Constants;
import com.sothree.slidinguppanel.p086a.R.R;

/* renamed from: com.google.android.m4b.maps.aj.g */
public final class GestureDetector {
    private static final int f3177f;
    private static final int f3178g;
    private static final int f3179h;
    private int f3180a;
    private int f3181b;
    private int f3182c;
    private int f3183d;
    private int f3184e;
    private final Handler f3185i;
    private final OnGestureListener f3186j;
    private OnDoubleTapListener f3187k;
    private boolean f3188l;
    private boolean f3189m;
    private boolean f3190n;
    private boolean f3191o;
    private MotionEvent f3192p;
    private MotionEvent f3193q;
    private boolean f3194r;
    private float f3195s;
    private float f3196t;
    private float f3197u;
    private float f3198v;
    private boolean f3199w;
    private VelocityTracker f3200x;

    /* renamed from: com.google.android.m4b.maps.aj.g.a */
    class GestureDetector extends Handler {
        private /* synthetic */ GestureDetector f3176a;

        GestureDetector(GestureDetector gestureDetector, Handler handler) {
            this.f3176a = gestureDetector;
            super(handler.getLooper());
        }

        public final void handleMessage(Message message) {
            switch (message.what) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    this.f3176a.f3186j.onShowPress(this.f3176a.f3192p);
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    GestureDetector.m5153c(this.f3176a);
                case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                    if (this.f3176a.f3187k != null && !this.f3176a.f3188l) {
                        this.f3176a.f3187k.onSingleTapConfirmed(this.f3176a.f3192p);
                    }
                default:
                    throw new RuntimeException("Unknown message " + message);
            }
        }
    }

    static /* synthetic */ void m5153c(GestureDetector gestureDetector) {
        gestureDetector.f3185i.removeMessages(3);
        gestureDetector.f3189m = true;
        gestureDetector.f3186j.onLongPress(gestureDetector.f3192p);
    }

    static {
        f3177f = ViewConfiguration.getLongPressTimeout();
        f3178g = ViewConfiguration.getTapTimeout();
        f3179h = ViewConfiguration.getDoubleTapTimeout();
    }

    public GestureDetector(Context context, OnGestureListener onGestureListener, Handler handler) {
        this.f3185i = new GestureDetector(this, handler);
        this.f3186j = onGestureListener;
        if (onGestureListener instanceof OnDoubleTapListener) {
            this.f3187k = (OnDoubleTapListener) onGestureListener;
        }
        if (this.f3186j == null) {
            throw new NullPointerException("OnGestureListener must not be null");
        }
        int touchSlop;
        int i;
        int i2;
        this.f3199w = true;
        if (context == null) {
            touchSlop = ViewConfiguration.getTouchSlop();
            i = touchSlop * 2;
            this.f3183d = ViewConfiguration.getMinimumFlingVelocity();
            this.f3184e = ViewConfiguration.getMaximumFlingVelocity();
            i2 = touchSlop;
        } else {
            ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
            i2 = viewConfiguration.getScaledTouchSlop();
            touchSlop = viewConfiguration.getScaledTouchSlop();
            i = viewConfiguration.getScaledDoubleTapSlop();
            this.f3183d = viewConfiguration.getScaledMinimumFlingVelocity();
            this.f3184e = viewConfiguration.getScaledMaximumFlingVelocity();
        }
        this.f3180a = i2 * i2;
        this.f3181b = touchSlop * touchSlop;
        this.f3182c = i * i;
    }

    public final void m5156a(OnDoubleTapListener onDoubleTapListener) {
        this.f3187k = onDoubleTapListener;
    }

    public final void m5157a(boolean z) {
        this.f3199w = true;
    }

    public final boolean m5158a(MotionEvent motionEvent) {
        int i;
        int action = motionEvent.getAction();
        if (this.f3200x == null) {
            this.f3200x = VelocityTracker.obtain();
        }
        this.f3200x.addMovement(motionEvent);
        boolean z = (action & MotionEventCompat.ACTION_MASK) == 6;
        int actionIndex = z ? motionEvent.getActionIndex() : -1;
        int pointerCount = motionEvent.getPointerCount();
        float f = 0.0f;
        float f2 = 0.0f;
        for (i = 0; i < pointerCount; i++) {
            if (actionIndex != i) {
                f2 += motionEvent.getX(i);
                f += motionEvent.getY(i);
            }
        }
        actionIndex = z ? pointerCount - 1 : pointerCount;
        f2 /= (float) actionIndex;
        f /= (float) actionIndex;
        boolean hasMessages;
        float yVelocity;
        float xVelocity;
        switch (action & MotionEventCompat.ACTION_MASK) {
            case R.SlidingUpPanelLayout_umanoPanelHeight /*0*/:
                if (this.f3187k != null) {
                    hasMessages = this.f3185i.hasMessages(3);
                    if (hasMessages) {
                        this.f3185i.removeMessages(3);
                    }
                    if (!(this.f3192p == null || this.f3193q == null || !hasMessages)) {
                        MotionEvent motionEvent2 = this.f3192p;
                        MotionEvent motionEvent3 = this.f3193q;
                        if (this.f3191o && motionEvent.getEventTime() - motionEvent3.getEventTime() <= ((long) f3179h)) {
                            pointerCount = ((int) motionEvent2.getX()) - ((int) motionEvent.getX());
                            actionIndex = ((int) motionEvent2.getY()) - ((int) motionEvent.getY());
                            if ((actionIndex * actionIndex) + (pointerCount * pointerCount) < this.f3182c) {
                                hasMessages = true;
                                if (hasMessages) {
                                    this.f3194r = true;
                                    actionIndex = (this.f3187k.onDoubleTap(this.f3192p) | 0) | this.f3187k.onDoubleTapEvent(motionEvent);
                                    this.f3195s = f2;
                                    this.f3197u = f2;
                                    this.f3196t = f;
                                    this.f3198v = f;
                                    if (this.f3192p != null) {
                                        this.f3192p.recycle();
                                    }
                                    this.f3192p = MotionEvent.obtain(motionEvent);
                                    this.f3190n = true;
                                    this.f3191o = true;
                                    this.f3188l = true;
                                    this.f3189m = false;
                                    if (this.f3199w) {
                                        this.f3185i.removeMessages(2);
                                        this.f3185i.sendEmptyMessageAtTime(2, (this.f3192p.getDownTime() + ((long) f3178g)) + ((long) f3177f));
                                    }
                                    this.f3185i.sendEmptyMessageAtTime(1, this.f3192p.getDownTime() + ((long) f3178g));
                                    return actionIndex | this.f3186j.onDown(motionEvent);
                                }
                            }
                        }
                        hasMessages = false;
                        if (hasMessages) {
                            this.f3194r = true;
                            actionIndex = (this.f3187k.onDoubleTap(this.f3192p) | 0) | this.f3187k.onDoubleTapEvent(motionEvent);
                            this.f3195s = f2;
                            this.f3197u = f2;
                            this.f3196t = f;
                            this.f3198v = f;
                            if (this.f3192p != null) {
                                this.f3192p.recycle();
                            }
                            this.f3192p = MotionEvent.obtain(motionEvent);
                            this.f3190n = true;
                            this.f3191o = true;
                            this.f3188l = true;
                            this.f3189m = false;
                            if (this.f3199w) {
                                this.f3185i.removeMessages(2);
                                this.f3185i.sendEmptyMessageAtTime(2, (this.f3192p.getDownTime() + ((long) f3178g)) + ((long) f3177f));
                            }
                            this.f3185i.sendEmptyMessageAtTime(1, this.f3192p.getDownTime() + ((long) f3178g));
                            return actionIndex | this.f3186j.onDown(motionEvent);
                        }
                    }
                    this.f3185i.sendEmptyMessageDelayed(3, (long) f3179h);
                }
                actionIndex = 0;
                this.f3195s = f2;
                this.f3197u = f2;
                this.f3196t = f;
                this.f3198v = f;
                if (this.f3192p != null) {
                    this.f3192p.recycle();
                }
                this.f3192p = MotionEvent.obtain(motionEvent);
                this.f3190n = true;
                this.f3191o = true;
                this.f3188l = true;
                this.f3189m = false;
                if (this.f3199w) {
                    this.f3185i.removeMessages(2);
                    this.f3185i.sendEmptyMessageAtTime(2, (this.f3192p.getDownTime() + ((long) f3178g)) + ((long) f3177f));
                }
                this.f3185i.sendEmptyMessageAtTime(1, this.f3192p.getDownTime() + ((long) f3178g));
                return actionIndex | this.f3186j.onDown(motionEvent);
            case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                this.f3188l = false;
                MotionEvent obtain = MotionEvent.obtain(motionEvent);
                if (this.f3194r) {
                    hasMessages = this.f3187k.onDoubleTapEvent(motionEvent) | 0;
                } else if (this.f3189m) {
                    this.f3185i.removeMessages(3);
                    this.f3189m = false;
                    hasMessages = this.f3186j.onSingleTapUp(motionEvent);
                } else if (this.f3190n) {
                    hasMessages = this.f3186j.onSingleTapUp(motionEvent);
                } else {
                    VelocityTracker velocityTracker = this.f3200x;
                    int pointerId = motionEvent.getPointerId(0);
                    velocityTracker.computeCurrentVelocity(Constants.MILLIS_IN_A_SECOND, (float) this.f3184e);
                    yVelocity = velocityTracker.getYVelocity(pointerId);
                    xVelocity = velocityTracker.getXVelocity(pointerId);
                    if (Math.abs(yVelocity) > ((float) this.f3183d) || Math.abs(xVelocity) > ((float) this.f3183d)) {
                        hasMessages = this.f3186j.onFling(this.f3192p, motionEvent, xVelocity, yVelocity);
                    } else {
                        hasMessages = false;
                    }
                }
                if (this.f3193q != null) {
                    this.f3193q.recycle();
                }
                this.f3193q = obtain;
                if (this.f3200x != null) {
                    this.f3200x.recycle();
                    this.f3200x = null;
                }
                this.f3194r = false;
                this.f3185i.removeMessages(1);
                this.f3185i.removeMessages(2);
                return hasMessages;
            case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                xVelocity = this.f3195s - f2;
                yVelocity = this.f3196t - f;
                if (this.f3194r) {
                    return this.f3187k.onDoubleTapEvent(motionEvent) | 0;
                }
                if (this.f3190n) {
                    i = (int) (f2 - this.f3197u);
                    int i2 = (int) (f - this.f3198v);
                    i = (i * i) + (i2 * i2);
                    if (i > this.f3180a) {
                        hasMessages = this.f3186j.onScroll(this.f3192p, motionEvent, xVelocity, yVelocity);
                        this.f3195s = f2;
                        this.f3196t = f;
                        this.f3190n = false;
                        this.f3185i.removeMessages(3);
                        this.f3185i.removeMessages(1);
                        this.f3185i.removeMessages(2);
                    } else {
                        hasMessages = false;
                    }
                    if (i > this.f3181b) {
                        this.f3191o = false;
                    }
                    return hasMessages;
                } else if (Math.abs(xVelocity) < br.DEFAULT_BACKOFF_MULT && Math.abs(yVelocity) < br.DEFAULT_BACKOFF_MULT) {
                    return false;
                } else {
                    boolean onScroll = this.f3186j.onScroll(this.f3192p, motionEvent, xVelocity, yVelocity);
                    this.f3195s = f2;
                    this.f3196t = f;
                    return onScroll;
                }
            case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                this.f3185i.removeMessages(1);
                this.f3185i.removeMessages(2);
                this.f3185i.removeMessages(3);
                this.f3200x.recycle();
                this.f3200x = null;
                this.f3194r = false;
                this.f3188l = false;
                this.f3190n = false;
                this.f3191o = false;
                if (!this.f3189m) {
                    return false;
                }
                this.f3189m = false;
                return false;
            case R.SlidingUpPanelLayout_umanoDragView /*5*/:
                this.f3195s = f2;
                this.f3197u = f2;
                this.f3196t = f;
                this.f3198v = f;
                this.f3185i.removeMessages(1);
                this.f3185i.removeMessages(2);
                this.f3185i.removeMessages(3);
                this.f3194r = false;
                this.f3190n = false;
                this.f3191o = false;
                if (!this.f3189m) {
                    return false;
                }
                this.f3189m = false;
                return false;
            case R.SlidingUpPanelLayout_umanoOverlay /*6*/:
                this.f3195s = f2;
                this.f3197u = f2;
                this.f3196t = f;
                this.f3198v = f;
                this.f3200x.computeCurrentVelocity(Constants.MILLIS_IN_A_SECOND, (float) this.f3184e);
                int actionIndex2 = motionEvent.getActionIndex();
                actionIndex = motionEvent.getPointerId(actionIndex2);
                f2 = this.f3200x.getXVelocity(actionIndex);
                float yVelocity2 = this.f3200x.getYVelocity(actionIndex);
                for (actionIndex = 0; actionIndex < pointerCount; actionIndex++) {
                    if (actionIndex != actionIndex2) {
                        i = motionEvent.getPointerId(actionIndex);
                        if ((this.f3200x.getYVelocity(i) * yVelocity2) + (this.f3200x.getXVelocity(i) * f2) < 0.0f) {
                            this.f3200x.clear();
                            return false;
                        }
                    }
                }
                return false;
            default:
                return false;
        }
    }
}
