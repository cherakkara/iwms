package com.google.android.m4b.maps.bj;

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

/* renamed from: com.google.android.m4b.maps.bj.n */
public final class GestureDetector {
    private static final int f6545f;
    private static final int f6546g;
    private static final int f6547h;
    private int f6548a;
    private int f6549b;
    private int f6550c;
    private int f6551d;
    private int f6552e;
    private final Handler f6553i;
    private final OnGestureListener f6554j;
    private OnDoubleTapListener f6555k;
    private boolean f6556l;
    private boolean f6557m;
    private boolean f6558n;
    private boolean f6559o;
    private MotionEvent f6560p;
    private MotionEvent f6561q;
    private boolean f6562r;
    private float f6563s;
    private float f6564t;
    private float f6565u;
    private float f6566v;
    private boolean f6567w;
    private VelocityTracker f6568x;

    /* renamed from: com.google.android.m4b.maps.bj.n.a */
    class GestureDetector extends Handler {
        private /* synthetic */ GestureDetector f6544a;

        GestureDetector(GestureDetector gestureDetector) {
            this.f6544a = gestureDetector;
        }

        public final void handleMessage(Message message) {
            switch (message.what) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    this.f6544a.f6554j.onShowPress(this.f6544a.f6560p);
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    GestureDetector.m9970c(this.f6544a);
                case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                    if (this.f6544a.f6555k != null && !this.f6544a.f6556l) {
                        this.f6544a.f6555k.onSingleTapConfirmed(this.f6544a.f6560p);
                    }
                default:
                    throw new RuntimeException("Unknown message " + message);
            }
        }
    }

    static /* synthetic */ void m9970c(GestureDetector gestureDetector) {
        gestureDetector.f6553i.removeMessages(3);
        gestureDetector.f6557m = true;
        gestureDetector.f6554j.onLongPress(gestureDetector.f6560p);
    }

    static {
        f6545f = ViewConfiguration.getLongPressTimeout();
        f6546g = ViewConfiguration.getTapTimeout();
        f6547h = ViewConfiguration.getDoubleTapTimeout();
    }

    public GestureDetector(Context context, OnGestureListener onGestureListener) {
        this(context, onGestureListener, null);
    }

    private GestureDetector(Context context, OnGestureListener onGestureListener, Handler handler) {
        this.f6553i = new GestureDetector(this);
        this.f6554j = onGestureListener;
        if (onGestureListener instanceof OnDoubleTapListener) {
            this.f6555k = (OnDoubleTapListener) onGestureListener;
        }
        if (this.f6554j == null) {
            throw new NullPointerException("OnGestureListener must not be null");
        }
        int touchSlop;
        int i;
        int i2;
        this.f6567w = true;
        if (context == null) {
            touchSlop = ViewConfiguration.getTouchSlop();
            i = touchSlop * 2;
            this.f6551d = ViewConfiguration.getMinimumFlingVelocity();
            this.f6552e = ViewConfiguration.getMaximumFlingVelocity();
            i2 = touchSlop;
        } else {
            ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
            i2 = viewConfiguration.getScaledTouchSlop();
            touchSlop = viewConfiguration.getScaledTouchSlop();
            i = viewConfiguration.getScaledDoubleTapSlop();
            this.f6551d = viewConfiguration.getScaledMinimumFlingVelocity();
            this.f6552e = viewConfiguration.getScaledMaximumFlingVelocity();
        }
        this.f6548a = i2 * i2;
        this.f6549b = touchSlop * touchSlop;
        this.f6550c = i * i;
    }

    public final void m9973a(OnDoubleTapListener onDoubleTapListener) {
        this.f6555k = onDoubleTapListener;
    }

    public final void m9974a(boolean z) {
        this.f6567w = z;
    }

    public final boolean m9975a(MotionEvent motionEvent) {
        int i;
        int action = motionEvent.getAction();
        if (this.f6568x == null) {
            this.f6568x = VelocityTracker.obtain();
        }
        this.f6568x.addMovement(motionEvent);
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
                if (this.f6555k != null) {
                    hasMessages = this.f6553i.hasMessages(3);
                    if (hasMessages) {
                        this.f6553i.removeMessages(3);
                    }
                    if (!(this.f6560p == null || this.f6561q == null || !hasMessages)) {
                        MotionEvent motionEvent2 = this.f6560p;
                        MotionEvent motionEvent3 = this.f6561q;
                        if (this.f6559o && motionEvent.getEventTime() - motionEvent3.getEventTime() <= ((long) f6547h)) {
                            pointerCount = ((int) motionEvent2.getX()) - ((int) motionEvent.getX());
                            actionIndex = ((int) motionEvent2.getY()) - ((int) motionEvent.getY());
                            if ((actionIndex * actionIndex) + (pointerCount * pointerCount) < this.f6550c) {
                                hasMessages = true;
                                if (hasMessages) {
                                    this.f6562r = true;
                                    actionIndex = (this.f6555k.onDoubleTap(this.f6560p) | 0) | this.f6555k.onDoubleTapEvent(motionEvent);
                                    this.f6563s = f2;
                                    this.f6565u = f2;
                                    this.f6564t = f;
                                    this.f6566v = f;
                                    if (this.f6560p != null) {
                                        this.f6560p.recycle();
                                    }
                                    this.f6560p = MotionEvent.obtain(motionEvent);
                                    this.f6558n = true;
                                    this.f6559o = true;
                                    this.f6556l = true;
                                    this.f6557m = false;
                                    if (this.f6567w) {
                                        this.f6553i.removeMessages(2);
                                        this.f6553i.sendEmptyMessageAtTime(2, (this.f6560p.getDownTime() + ((long) f6546g)) + ((long) f6545f));
                                    }
                                    this.f6553i.sendEmptyMessageAtTime(1, this.f6560p.getDownTime() + ((long) f6546g));
                                    return actionIndex | this.f6554j.onDown(motionEvent);
                                }
                            }
                        }
                        hasMessages = false;
                        if (hasMessages) {
                            this.f6562r = true;
                            actionIndex = (this.f6555k.onDoubleTap(this.f6560p) | 0) | this.f6555k.onDoubleTapEvent(motionEvent);
                            this.f6563s = f2;
                            this.f6565u = f2;
                            this.f6564t = f;
                            this.f6566v = f;
                            if (this.f6560p != null) {
                                this.f6560p.recycle();
                            }
                            this.f6560p = MotionEvent.obtain(motionEvent);
                            this.f6558n = true;
                            this.f6559o = true;
                            this.f6556l = true;
                            this.f6557m = false;
                            if (this.f6567w) {
                                this.f6553i.removeMessages(2);
                                this.f6553i.sendEmptyMessageAtTime(2, (this.f6560p.getDownTime() + ((long) f6546g)) + ((long) f6545f));
                            }
                            this.f6553i.sendEmptyMessageAtTime(1, this.f6560p.getDownTime() + ((long) f6546g));
                            return actionIndex | this.f6554j.onDown(motionEvent);
                        }
                    }
                    this.f6553i.sendEmptyMessageDelayed(3, (long) f6547h);
                }
                actionIndex = 0;
                this.f6563s = f2;
                this.f6565u = f2;
                this.f6564t = f;
                this.f6566v = f;
                if (this.f6560p != null) {
                    this.f6560p.recycle();
                }
                this.f6560p = MotionEvent.obtain(motionEvent);
                this.f6558n = true;
                this.f6559o = true;
                this.f6556l = true;
                this.f6557m = false;
                if (this.f6567w) {
                    this.f6553i.removeMessages(2);
                    this.f6553i.sendEmptyMessageAtTime(2, (this.f6560p.getDownTime() + ((long) f6546g)) + ((long) f6545f));
                }
                this.f6553i.sendEmptyMessageAtTime(1, this.f6560p.getDownTime() + ((long) f6546g));
                return actionIndex | this.f6554j.onDown(motionEvent);
            case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                this.f6556l = false;
                MotionEvent obtain = MotionEvent.obtain(motionEvent);
                if (this.f6562r) {
                    hasMessages = this.f6555k.onDoubleTapEvent(motionEvent) | 0;
                } else if (this.f6557m) {
                    this.f6553i.removeMessages(3);
                    this.f6557m = false;
                    hasMessages = false;
                } else if (this.f6558n) {
                    hasMessages = this.f6554j.onSingleTapUp(motionEvent);
                } else {
                    VelocityTracker velocityTracker = this.f6568x;
                    int pointerId = motionEvent.getPointerId(0);
                    velocityTracker.computeCurrentVelocity(Constants.MILLIS_IN_A_SECOND, (float) this.f6552e);
                    yVelocity = velocityTracker.getYVelocity(pointerId);
                    xVelocity = velocityTracker.getXVelocity(pointerId);
                    if (Math.abs(yVelocity) > ((float) this.f6551d) || Math.abs(xVelocity) > ((float) this.f6551d)) {
                        hasMessages = this.f6554j.onFling(this.f6560p, motionEvent, xVelocity, yVelocity);
                    } else {
                        hasMessages = false;
                    }
                }
                if (this.f6561q != null) {
                    this.f6561q.recycle();
                }
                this.f6561q = obtain;
                if (this.f6568x != null) {
                    this.f6568x.recycle();
                    this.f6568x = null;
                }
                this.f6562r = false;
                this.f6553i.removeMessages(1);
                this.f6553i.removeMessages(2);
                return hasMessages;
            case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                if (this.f6557m) {
                    return false;
                }
                xVelocity = this.f6563s - f2;
                yVelocity = this.f6564t - f;
                if (this.f6562r) {
                    return this.f6555k.onDoubleTapEvent(motionEvent) | 0;
                }
                if (this.f6558n) {
                    i = (int) (f2 - this.f6565u);
                    int i2 = (int) (f - this.f6566v);
                    i = (i * i) + (i2 * i2);
                    if (i > this.f6548a) {
                        hasMessages = this.f6554j.onScroll(this.f6560p, motionEvent, xVelocity, yVelocity);
                        this.f6563s = f2;
                        this.f6564t = f;
                        this.f6558n = false;
                        this.f6553i.removeMessages(3);
                        this.f6553i.removeMessages(1);
                        this.f6553i.removeMessages(2);
                    } else {
                        hasMessages = false;
                    }
                    if (i > this.f6549b) {
                        this.f6559o = false;
                    }
                    return hasMessages;
                } else if (Math.abs(xVelocity) < br.DEFAULT_BACKOFF_MULT && Math.abs(yVelocity) < br.DEFAULT_BACKOFF_MULT) {
                    return false;
                } else {
                    boolean onScroll = this.f6554j.onScroll(this.f6560p, motionEvent, xVelocity, yVelocity);
                    this.f6563s = f2;
                    this.f6564t = f;
                    return onScroll;
                }
            case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                this.f6553i.removeMessages(1);
                this.f6553i.removeMessages(2);
                this.f6553i.removeMessages(3);
                this.f6568x.recycle();
                this.f6568x = null;
                this.f6562r = false;
                this.f6556l = false;
                this.f6558n = false;
                this.f6559o = false;
                if (!this.f6557m) {
                    return false;
                }
                this.f6557m = false;
                return false;
            case R.SlidingUpPanelLayout_umanoDragView /*5*/:
                this.f6563s = f2;
                this.f6565u = f2;
                this.f6564t = f;
                this.f6566v = f;
                this.f6553i.removeMessages(1);
                this.f6553i.removeMessages(2);
                this.f6553i.removeMessages(3);
                this.f6562r = false;
                this.f6558n = false;
                this.f6559o = false;
                if (!this.f6557m) {
                    return false;
                }
                this.f6557m = false;
                return false;
            case R.SlidingUpPanelLayout_umanoOverlay /*6*/:
                this.f6563s = f2;
                this.f6565u = f2;
                this.f6564t = f;
                this.f6566v = f;
                this.f6568x.computeCurrentVelocity(Constants.MILLIS_IN_A_SECOND, (float) this.f6552e);
                int actionIndex2 = motionEvent.getActionIndex();
                actionIndex = motionEvent.getPointerId(actionIndex2);
                f2 = this.f6568x.getXVelocity(actionIndex);
                float yVelocity2 = this.f6568x.getYVelocity(actionIndex);
                for (actionIndex = 0; actionIndex < pointerCount; actionIndex++) {
                    if (actionIndex != actionIndex2) {
                        i = motionEvent.getPointerId(actionIndex);
                        if ((this.f6568x.getYVelocity(i) * yVelocity2) + (this.f6568x.getXVelocity(i) * f2) < 0.0f) {
                            this.f6568x.clear();
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
