package com.google.android.m4b.maps.aj;

import com.google.android.m4b.maps.aj.Gesture.Gesture;
import com.google.android.m4b.maps.aj.GmmGestureDetector.GmmGestureDetector;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/* renamed from: com.google.android.m4b.maps.aj.m */
public final class ScaleGesture extends Gesture {
    public ScaleGesture(GmmGestureDetector gmmGestureDetector) {
        super(gmmGestureDetector);
    }

    public final boolean m5182c() {
        return true;
    }

    public final Gesture m5180a(long j, LinkedList<GestureMotionEvent> linkedList, List<Gesture> list) {
        if (linkedList.size() < 3) {
            return Gesture.MAYBE;
        }
        float f;
        GestureMotionEvent gestureMotionEvent = (GestureMotionEvent) linkedList.getLast();
        float f2 = gestureMotionEvent.m5085f();
        ListIterator listIterator = linkedList.listIterator(linkedList.size());
        GestureMotionEvent gestureMotionEvent2 = gestureMotionEvent;
        while (listIterator.hasPrevious()) {
            GestureMotionEvent gestureMotionEvent3 = (GestureMotionEvent) listIterator.previous();
            if (gestureMotionEvent3.m5081b() != gestureMotionEvent.m5081b()) {
                break;
            } else if (Math.abs(Gesture.m5112a(f2, gestureMotionEvent3.m5085f())) > 0.17453292f) {
                return Gesture.NO;
            } else {
                gestureMotionEvent2 = gestureMotionEvent3;
            }
        }
        if (list.isEmpty()) {
            f = 0.1f;
        } else {
            f = 0.2f;
        }
        float g = gestureMotionEvent2.m5086g();
        f2 = gestureMotionEvent.m5086g();
        if (Math.abs(g - f2) / ((gestureMotionEvent.m5083d() + gestureMotionEvent.m5082c()) * 0.5f) < f) {
            return Gesture.MAYBE;
        }
        return Gesture.YES;
    }

    protected final boolean m5181b(GmmGestureDetector gmmGestureDetector) {
        return this.a.m5128b(gmmGestureDetector, false);
    }

    protected final void m5183d(GmmGestureDetector gmmGestureDetector) {
        this.a.m5130c(gmmGestureDetector, false);
    }

    protected final boolean m5184f(GmmGestureDetector gmmGestureDetector) {
        return this.a.m5126a(gmmGestureDetector, false);
    }
}
