package com.google.android.m4b.maps.aj;

import com.google.android.m4b.maps.aj.Gesture.Gesture;
import com.google.android.m4b.maps.aj.GmmGestureDetector.GmmGestureDetector;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/* renamed from: com.google.android.m4b.maps.aj.s */
public final class TwoFingerTapScaleGesture extends Gesture {
    public TwoFingerTapScaleGesture(GmmGestureDetector gmmGestureDetector) {
        super(gmmGestureDetector);
    }

    public final boolean m5207b() {
        return true;
    }

    public final boolean m5209c() {
        return true;
    }

    public final boolean m5211d() {
        return true;
    }

    public final Gesture m5206a(long j, LinkedList<GestureMotionEvent> linkedList, List<Gesture> list) {
        GestureMotionEvent gestureMotionEvent;
        GestureMotionEvent gestureMotionEvent2;
        Iterator it = linkedList.iterator();
        while (it.hasNext()) {
            gestureMotionEvent = (GestureMotionEvent) it.next();
            if (gestureMotionEvent.m5081b() == 2) {
                gestureMotionEvent2 = gestureMotionEvent;
                break;
            }
        }
        gestureMotionEvent2 = null;
        ListIterator listIterator = linkedList.listIterator(linkedList.size());
        while (listIterator.hasPrevious()) {
            gestureMotionEvent = (GestureMotionEvent) listIterator.previous();
            if (gestureMotionEvent.m5081b() == 2) {
                break;
            }
        }
        gestureMotionEvent = null;
        if (gestureMotionEvent2 == null || gestureMotionEvent == null) {
            return Gesture.NO;
        }
        if (gestureMotionEvent.m5079a() - j > 300) {
            return Gesture.NO;
        }
        if (Math.max(Math.max(Math.abs(gestureMotionEvent.m5078a(0) - gestureMotionEvent2.m5078a(0)) / gestureMotionEvent.m5082c(), Math.abs(gestureMotionEvent.m5080b(0) - gestureMotionEvent2.m5080b(0)) / gestureMotionEvent.m5083d()), Math.max(Math.abs(gestureMotionEvent.m5078a(1) - gestureMotionEvent2.m5078a(1)) / gestureMotionEvent.m5082c(), Math.abs(gestureMotionEvent.m5080b(1) - gestureMotionEvent2.m5080b(1)) / gestureMotionEvent.m5083d())) > 0.125f) {
            return Gesture.NO;
        }
        return Gesture.YES;
    }

    protected final boolean m5208b(GmmGestureDetector gmmGestureDetector) {
        return this.a.m5128b(gmmGestureDetector, true);
    }

    protected final void m5210d(GmmGestureDetector gmmGestureDetector) {
        this.a.m5130c(gmmGestureDetector, true);
    }

    protected final boolean m5212f(GmmGestureDetector gmmGestureDetector) {
        return this.a.m5126a(gmmGestureDetector, true);
    }
}
