package com.google.android.m4b.maps.aj;

import com.google.android.m4b.maps.aj.Gesture.Gesture;
import com.google.android.m4b.maps.aj.GmmGestureDetector.GmmGestureDetector;
import com.olacabs.customer.p076d.br;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/* renamed from: com.google.android.m4b.maps.aj.r */
public abstract class TwoFingerSwipeGesture extends Gesture {
    protected float f3238b;
    protected float f3239c;
    private float f3240d;
    private float f3241e;

    protected abstract float m5189a(float f);

    protected abstract float m5190a(GestureMotionEvent gestureMotionEvent, int i);

    protected abstract float m5192b(GestureMotionEvent gestureMotionEvent, int i);

    public TwoFingerSwipeGesture(GmmGestureDetector gmmGestureDetector) {
        super(gmmGestureDetector);
        this.f3240d = 0.7853982f;
        this.f3241e = 0.25f;
        this.f3238b = 0.125f;
        this.f3239c = br.DEFAULT_BACKOFF_MULT;
    }

    public final boolean m5193b() {
        return true;
    }

    public final Gesture m5191a(long j, LinkedList<GestureMotionEvent> linkedList, List<Gesture> list) {
        if (linkedList.size() < 3) {
            return Gesture.MAYBE;
        }
        GestureMotionEvent gestureMotionEvent = (GestureMotionEvent) linkedList.getLast();
        if (gestureMotionEvent.m5081b() != 2) {
            return Gesture.NO;
        }
        float abs;
        ListIterator listIterator = linkedList.listIterator(linkedList.size());
        float f = 0.0f;
        float f2 = 0.0f;
        float f3 = 0.0f;
        float f4 = 0.0f;
        GestureMotionEvent gestureMotionEvent2 = gestureMotionEvent;
        GestureMotionEvent gestureMotionEvent3 = null;
        while (listIterator.hasPrevious()) {
            GestureMotionEvent gestureMotionEvent4 = (GestureMotionEvent) listIterator.previous();
            if (gestureMotionEvent4.m5081b() != gestureMotionEvent.m5081b()) {
                break;
            } else if (m5189a(gestureMotionEvent4.m5085f()) >= this.f3240d) {
                return Gesture.NO;
            } else {
                if (gestureMotionEvent4.m5086g() / gestureMotionEvent4.m5082c() < this.f3241e) {
                    return Gesture.NO;
                }
                float abs2;
                if (gestureMotionEvent3 != null) {
                    f2 += Math.abs(m5190a(gestureMotionEvent4, 0) - m5190a(gestureMotionEvent3, 0));
                    f += Math.abs(m5190a(gestureMotionEvent4, gestureMotionEvent4.m5081b() - 1) - m5190a(gestureMotionEvent3, gestureMotionEvent3.m5081b() - 1));
                    abs = Math.abs(m5192b(gestureMotionEvent4, gestureMotionEvent4.m5081b() - 1) - m5192b(gestureMotionEvent3, gestureMotionEvent3.m5081b() - 1)) + f3;
                    abs2 = f4 + Math.abs(m5192b(gestureMotionEvent4, 0) - m5192b(gestureMotionEvent3, 0));
                    f3 = f;
                    f4 = f2;
                } else {
                    abs = f3;
                    abs2 = f4;
                    f3 = f;
                    f4 = f2;
                }
                f = f3;
                f2 = f4;
                f3 = abs;
                f4 = abs2;
                gestureMotionEvent2 = gestureMotionEvent4;
                gestureMotionEvent3 = gestureMotionEvent4;
            }
        }
        if (f2 + f > (f4 + f3) * this.f3239c) {
            return Gesture.NO;
        }
        float b = m5192b(gestureMotionEvent, 0) - m5192b(gestureMotionEvent2, 0);
        abs = m5192b(gestureMotionEvent, gestureMotionEvent.m5081b() - 1) - m5192b(gestureMotionEvent2, gestureMotionEvent2.m5081b() - 1);
        if (b * abs < 0.0f) {
            return Gesture.NO;
        }
        if (Math.min(Math.abs(b) / gestureMotionEvent.m5083d(), Math.abs(abs) / gestureMotionEvent.m5083d()) < this.f3238b) {
            return Gesture.MAYBE;
        }
        return Gesture.YES;
    }
}
