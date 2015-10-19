package com.google.android.m4b.maps.ay;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.m4b.maps.p058v.Util;
import com.google.p025a.p028c.au;
import com.newrelic.agent.android.analytics.AnalyticAttribute;
import java.util.Map;
import java.util.Map.Entry;

/* renamed from: com.google.android.m4b.maps.ay.a */
public final class ColorPalette {
    private Texture f4818a;
    private boolean f4819b;
    private final Bitmap f4820c;
    private final Map<Integer, Integer> f4821d;

    /* renamed from: com.google.android.m4b.maps.ay.a.a */
    static class ColorPalette {
        public int f4816a;
        public int f4817b;

        ColorPalette() {
        }
    }

    public ColorPalette() {
        this.f4818a = null;
        this.f4819b = true;
        this.f4820c = Bitmap.createBitmap(AnalyticAttribute.ATTRIBUTE_NAME_MAX_LENGTH, 1, Config.ARGB_8888);
        this.f4821d = au.m2815b();
    }

    public final synchronized Texture m7471a(GLState gLState) {
        m7474b(gLState);
        return this.f4818a;
    }

    public final synchronized void m7474b(GLState gLState) {
        if (this.f4819b || this.f4818a == null) {
            m7468b();
            if (!(this.f4818a == null || gLState.f4847a == this.f4818a.m7609a())) {
                m7472a();
            }
            if (this.f4818a == null) {
                this.f4818a = new Texture(gLState);
                this.f4818a.m7621c(false);
            }
            this.f4818a.m7617b(this.f4820c);
        }
    }

    public static void m7469c(GLState gLState) {
        gLState.f4847a.glMatrixMode(5890);
        gLState.f4847a.glLoadIdentity();
        gLState.f4847a.glScalex(1, 1, 0);
        gLState.f4847a.glMatrixMode(5888);
    }

    public static void m7470d(GLState gLState) {
        gLState.f4847a.glMatrixMode(5890);
        gLState.f4847a.glLoadIdentity();
        gLState.f4847a.glMatrixMode(5888);
    }

    final synchronized void m7473a(int i, ColorPalette colorPalette) {
        Integer num = (Integer) this.f4821d.get(Integer.valueOf(i));
        if (num == null) {
            num = Integer.valueOf(this.f4821d.size());
            if (num.intValue() >= AnalyticAttribute.ATTRIBUTE_NAME_MAX_LENGTH) {
                Util.m11550a("ColorPalette", "Color texture is full");
            } else {
                this.f4821d.put(Integer.valueOf(i), num);
                this.f4819b = true;
            }
        }
        colorPalette.f4816a = (((num.intValue() % AnalyticAttribute.ATTRIBUTE_NAME_MAX_LENGTH) * AccessibilityNodeInfoCompat.ACTION_CUT) + AccessibilityNodeInfoCompat.ACTION_PASTE) / AnalyticAttribute.ATTRIBUTE_NAME_MAX_LENGTH;
        colorPalette.f4817b = (((num.intValue() / AnalyticAttribute.ATTRIBUTE_NAME_MAX_LENGTH) * AccessibilityNodeInfoCompat.ACTION_CUT) + AccessibilityNodeInfoCompat.ACTION_PASTE) / 1;
    }

    public final synchronized void m7472a() {
        if (this.f4818a != null) {
            this.f4818a.m7626f();
            this.f4818a = null;
        }
    }

    private synchronized void m7468b() {
        this.f4819b = false;
        for (Entry entry : this.f4821d.entrySet()) {
            this.f4820c.setPixel(((Integer) entry.getValue()).intValue() % AnalyticAttribute.ATTRIBUTE_NAME_MAX_LENGTH, ((Integer) entry.getValue()).intValue() / AnalyticAttribute.ATTRIBUTE_NAME_MAX_LENGTH, ((Integer) entry.getKey()).intValue());
        }
    }
}
