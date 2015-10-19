package com.google.android.m4b.maps.aj;

import com.google.android.m4b.maps.aj.Gesture.Gesture;
import com.google.android.m4b.maps.aj.GmmGestureDetector.GmmGestureDetector;
import com.google.android.m4b.maps.p040u.UserEventReporter;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/* renamed from: com.google.android.m4b.maps.aj.k */
public final class RotateGesture extends Gesture {
    public RotateGesture(GmmGestureDetector gmmGestureDetector) {
        super(gmmGestureDetector);
    }

    public final Gesture m5176a(long j, LinkedList<GestureMotionEvent> linkedList, List<Gesture> list) {
        for (Gesture c : list) {
            if (c.m5120c()) {
                return Gesture.NO;
            }
        }
        if (linkedList.size() < 3) {
            return Gesture.MAYBE;
        }
        GestureMotionEvent gestureMotionEvent;
        GestureMotionEvent gestureMotionEvent2 = null;
        Iterator it = linkedList.iterator();
        while (it.hasNext()) {
            gestureMotionEvent = (GestureMotionEvent) it.next();
            if (gestureMotionEvent.m5081b() > 1) {
                gestureMotionEvent2 = gestureMotionEvent;
                break;
            }
        }
        gestureMotionEvent = (GestureMotionEvent) linkedList.getLast();
        if (gestureMotionEvent2 == null) {
            return Gesture.NO;
        }
        if (gestureMotionEvent.m5081b() <= 1) {
            return Gesture.NO;
        }
        float f = list.isEmpty() ? 0.08726646f : 0.17453292f;
        float abs = Math.abs(Gesture.m5112a(gestureMotionEvent2.m5085f(), gestureMotionEvent.m5085f()));
        if (abs < f) {
            return Gesture.NO;
        }
        f = (gestureMotionEvent.m5082c() + gestureMotionEvent.m5083d()) * 0.5f;
        float g = gestureMotionEvent.m5086g() / f;
        if (g < 0.75f) {
            return Gesture.NO;
        }
        g = Math.abs(g - (gestureMotionEvent2.m5086g() / f));
        if (g != 0.0f) {
            g = abs / g;
            if (g < 0.5f) {
                return Gesture.NO;
            }
            if (g < 0.9f) {
                return Gesture.MAYBE;
            }
        }
        return Gesture.YES;
    }

    protected final boolean m5177b(GmmGestureDetector gmmGestureDetector) {
        UserEventReporter.m11501a(99, "r");
        return this.a.m5132e(gmmGestureDetector);
    }

    protected final void m5178d(GmmGestureDetector gmmGestureDetector) {
        this.a.m5133f(gmmGestureDetector);
    }

    protected final boolean m5179f(GmmGestureDetector gmmGestureDetector) {
        return this.a.m5131d(gmmGestureDetector);
    }
}
