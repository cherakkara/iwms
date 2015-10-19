package com.google.android.m4b.maps.be;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.m4b.maps.be.be.UsageLog;
import com.google.android.m4b.maps.cc.IObjectWrapper;
import com.google.android.m4b.maps.model.LatLng;
import com.google.android.m4b.maps.model.MarkerOptions;
import com.google.android.m4b.maps.model.internal.BitmapDescriptorParcelable;
import com.google.android.m4b.maps.model.internal.IMarkerDelegate.IMarkerDelegate;
import com.google.p025a.p026a.Preconditions;
import com.newrelic.agent.android.analytics.AnalyticAttribute;
import com.newrelic.agent.android.api.v1.Defaults;

/* compiled from: MarkerImpl */
public final class ad extends IMarkerDelegate {
    private static final MarkerOptions f5561a;
    private final String f5562b;
    private final ae f5563c;
    private final bl f5564d;
    private final bj f5565e;
    private final be f5566f;
    private final ba f5567g;
    private MarkerImpl f5568h;
    private LatLng f5569i;
    private bk f5570j;
    private float f5571k;
    private float f5572l;
    private boolean f5573m;
    private float f5574n;
    private boolean f5575o;
    private boolean f5576p;
    private String f5577q;
    private String f5578r;
    private float f5579s;
    private float f5580t;
    private boolean f5581u;
    private float f5582v;

    /* renamed from: com.google.android.m4b.maps.be.ad.a */
    public interface MarkerImpl {
        void m8305a();

        void m8306a(int i);

        void m8307b();

        void m8308c();

        void m8309d();

        void m8310e();

        boolean m8311f();

        Rect m8312i();
    }

    static {
        f5561a = new MarkerOptions();
    }

    public ad(String str, MarkerOptions markerOptions, ae aeVar, bl blVar, bj bjVar, ba baVar, be beVar) {
        this.f5562b = str;
        this.f5563c = (ae) Preconditions.m1817a((Object) aeVar);
        this.f5564d = blVar;
        this.f5565e = bjVar;
        this.f5566f = beVar;
        this.f5567g = baVar;
        this.f5569i = (LatLng) Preconditions.m1817a(markerOptions.m10762c());
        this.f5570j = bk.m8889a(markerOptions.m10765f());
        this.f5564d.m8906a(this.f5570j);
        this.f5571k = markerOptions.m10766g();
        this.f5572l = markerOptions.m10767h();
        this.f5573m = markerOptions.m10770k();
        this.f5574n = markerOptions.m10771l();
        this.f5582v = markerOptions.m10774o();
        this.f5577q = markerOptions.m10763d();
        this.f5578r = markerOptions.m10764e();
        this.f5575o = markerOptions.m10768i();
        this.f5576p = markerOptions.m10769j();
        this.f5579s = markerOptions.m10772m();
        this.f5580t = markerOptions.m10773n();
        if (!(markerOptions.m10766g() == f5561a.m10766g() && markerOptions.m10767h() == f5561a.m10767h())) {
            this.f5566f.m8835b(UsageLog.MARKER_ANCHOR);
        }
        if (!(markerOptions.m10772m() == f5561a.m10772m() && markerOptions.m10773n() == f5561a.m10773n())) {
            this.f5566f.m8835b(UsageLog.MARKER_INFO_WINDOW_ANCHOR);
        }
        if (markerOptions.m10765f() != f5561a.m10765f()) {
            this.f5566f.m8835b(UsageLog.MARKER_ICON);
        }
        if (markerOptions.m10763d() != f5561a.m10763d()) {
            this.f5566f.m8835b(UsageLog.MARKER_TITLE);
        }
        if (markerOptions.m10764e() != f5561a.m10764e()) {
            this.f5566f.m8835b(UsageLog.MARKER_SNIPPET);
        }
        if (markerOptions.m10768i() != f5561a.m10768i()) {
            this.f5566f.m8835b(UsageLog.MARKER_DRAGGABLE);
        }
        if (markerOptions.m10769j() != f5561a.m10769j()) {
            this.f5566f.m8835b(UsageLog.MARKER_VISIBILITY);
        }
        if (markerOptions.m10770k() != f5561a.m10770k()) {
            this.f5566f.m8835b(UsageLog.MARKER_FLAT);
        }
        if (markerOptions.m10771l() != f5561a.m10771l()) {
            this.f5566f.m8835b(UsageLog.MARKER_ROTATION);
        }
        if (markerOptions.m10774o() != f5561a.m10774o()) {
            this.f5566f.m8835b(UsageLog.MARKER_ALPHA);
        }
    }

    public final void m8353a(MarkerImpl markerImpl) {
        this.f5568h = markerImpl;
    }

    private void m8341a(int i) {
        if (!this.f5581u) {
            MarkerImpl c = this.f5563c.m8402c(this);
            if (c != null) {
                c.m8306a(i);
            }
        }
    }

    public final String m8350a() {
        return this.f5562b;
    }

    public final void m8360b() {
        this.f5567g.m8774a();
        this.f5566f.m8835b(UsageLog.MARKER_REMOVE);
        m8366c();
    }

    final void m8366c() {
        if (!this.f5581u) {
            if (m8387w()) {
                m8386v();
            }
            this.f5581u = true;
            this.f5564d.m8908c(this.f5570j);
            this.f5563c.m8395a(this);
        }
    }

    public final void m8355a(LatLng latLng) {
        this.f5567g.m8774a();
        this.f5566f.m8835b(UsageLog.MARKER_SET_POSITION);
        m8363b(latLng);
        m8341a(1);
    }

    public final synchronized void m8363b(LatLng latLng) {
        this.f5569i = latLng;
    }

    public final LatLng m8368d() {
        this.f5567g.m8774a();
        return m8369e();
    }

    public final synchronized LatLng m8369e() {
        return this.f5569i;
    }

    public final void m8354a(IObjectWrapper iObjectWrapper) {
        this.f5567g.m8774a();
        synchronized (this) {
            this.f5564d.m8908c(this.f5570j);
            this.f5570j = bk.m8888a(iObjectWrapper);
            this.f5564d.m8906a(this.f5570j);
        }
        m8341a(2);
    }

    public final void m8356a(BitmapDescriptorParcelable bitmapDescriptorParcelable) {
        this.f5567g.m8774a();
        m8354a(this.f5565e.m8879a(bitmapDescriptorParcelable));
    }

    public final synchronized Bitmap m8370f() {
        return this.f5564d.m8907b(this.f5570j).m8903c();
    }

    public final void m8352a(float f, float f2) {
        this.f5567g.m8774a();
        synchronized (this) {
            this.f5571k = f;
            this.f5572l = f2;
        }
        m8341a(4);
    }

    public final float m8371g() {
        this.f5567g.m8774a();
        return m8372h();
    }

    public final synchronized float m8372h() {
        return this.f5571k;
    }

    public final float m8373i() {
        this.f5567g.m8774a();
        return m8374j();
    }

    public final synchronized float m8374j() {
        return this.f5572l;
    }

    public final void m8362b(float f, float f2) {
        this.f5567g.m8774a();
        synchronized (this) {
            this.f5579s = f;
            this.f5580t = f2;
        }
        m8341a((int) AccessibilityNodeInfoCompat.ACTION_PREVIOUS_AT_MOVEMENT_GRANULARITY);
    }

    public final float m8375k() {
        this.f5567g.m8774a();
        return m8376l();
    }

    public final synchronized float m8376l() {
        return this.f5579s;
    }

    public final float m8377m() {
        this.f5567g.m8774a();
        return m8378n();
    }

    public final synchronized float m8378n() {
        return this.f5580t;
    }

    public final void m8357a(String str) {
        this.f5567g.m8774a();
        synchronized (this) {
            this.f5577q = str;
        }
        m8341a((int) AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS);
    }

    public final String m8379o() {
        this.f5567g.m8774a();
        return m8380p();
    }

    public final synchronized String m8380p() {
        return this.f5577q;
    }

    public final void m8364b(String str) {
        this.f5567g.m8774a();
        this.f5578r = str;
        m8341a((int) AnalyticAttribute.ATTRIBUTE_NAME_MAX_LENGTH);
    }

    public final String m8381q() {
        this.f5567g.m8774a();
        return this.f5578r;
    }

    public final String m8382r() {
        return this.f5578r;
    }

    public final void m8358a(boolean z) {
        this.f5567g.m8774a();
        this.f5575o = z;
        m8341a(32);
    }

    public final boolean m8383s() {
        this.f5567g.m8774a();
        return m8384t();
    }

    public final synchronized boolean m8384t() {
        return this.f5575o;
    }

    public final void m8385u() {
        if (!this.f5581u) {
            this.f5567g.m8774a();
            this.f5566f.m8835b(UsageLog.MARKER_SHOW_INFO_BUBBLE);
            MarkerImpl c = this.f5563c.m8402c(this);
            if (c != null) {
                c.m8308c();
            }
        }
    }

    public final void m8386v() {
        if (!this.f5581u && m8387w()) {
            this.f5567g.m8774a();
            this.f5566f.m8835b(UsageLog.MARKER_HIDE_INFO_BUBBLE);
            MarkerImpl c = this.f5563c.m8402c(this);
            if (c != null) {
                c.m8309d();
            }
        }
    }

    public final boolean m8387w() {
        this.f5567g.m8774a();
        return !this.f5581u ? this.f5563c.m8401b(this) : false;
    }

    public final void m8365b(boolean z) {
        this.f5567g.m8774a();
        synchronized (this) {
            this.f5576p = z;
        }
        m8341a(64);
    }

    public final boolean m8388x() {
        this.f5567g.m8774a();
        return m8389y();
    }

    public final synchronized boolean m8389y() {
        return this.f5576p;
    }

    public final void m8367c(boolean z) {
        this.f5567g.m8774a();
        synchronized (this) {
            this.f5573m = z;
        }
        m8341a(8);
    }

    public final boolean m8390z() {
        this.f5567g.m8774a();
        return m8342A();
    }

    public final synchronized boolean m8342A() {
        return this.f5573m;
    }

    public final void m8351a(float f) {
        this.f5567g.m8774a();
        synchronized (this) {
            this.f5574n = f;
        }
        m8341a(16);
    }

    public final float m8343B() {
        this.f5567g.m8774a();
        return m8344C();
    }

    public final synchronized float m8344C() {
        return this.f5574n;
    }

    public final void m8361b(float f) {
        this.f5567g.m8774a();
        synchronized (this) {
            this.f5582v = f;
        }
        m8341a((int) Defaults.RESPONSE_BODY_LIMIT);
    }

    public final float m8345D() {
        this.f5567g.m8774a();
        return m8346E();
    }

    public final synchronized float m8346E() {
        return this.f5582v;
    }

    public final boolean m8359a(com.google.android.m4b.maps.model.internal.IMarkerDelegate iMarkerDelegate) {
        return equals(iMarkerDelegate);
    }

    public final int m8347F() {
        return hashCode();
    }

    public final String toString() {
        return this.f5562b;
    }

    public final ae m8348G() {
        return this.f5563c;
    }

    public final Rect m8349H() {
        return this.f5568h.m8312i();
    }
}
