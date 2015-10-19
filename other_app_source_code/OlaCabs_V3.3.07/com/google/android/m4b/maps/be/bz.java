package com.google.android.m4b.maps.be;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Canvas;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import com.google.android.m4b.maps.ce.IGoogleMapCallback;

@TargetApi(11)
/* compiled from: GoogleMapServiceView */
public class bz extends FrameLayout {
    private IGoogleMapCallback f5964a;
    private Dialog f5965b;
    private IBinder f5966c;
    private int f5967d;
    private int f5968e;
    private int f5969f;
    private int f5970g;
    private int f5971h;
    private int f5972i;
    private int f5973j;
    private int f5974k;
    private Handler f5975l;

    /* renamed from: com.google.android.m4b.maps.be.bz.1 */
    class GoogleMapServiceView implements Runnable {
        private /* synthetic */ bz f5963a;

        GoogleMapServiceView(bz bzVar) {
            this.f5963a = bzVar;
        }

        public final void run() {
            this.f5963a.m9288a();
        }
    }

    static {
        bz.class.getSimpleName();
    }

    public bz(Context context, View view) {
        super(context);
        this.f5971h = 0;
        this.f5972i = 0;
        this.f5973j = 0;
        this.f5974k = 0;
        this.f5975l = new Handler(Looper.getMainLooper());
        addView(view, new LayoutParams(-1, -1));
        m9288a();
    }

    private void m9288a() {
        if (Looper.getMainLooper() != Looper.myLooper()) {
            this.f5975l.post(new GoogleMapServiceView(this));
        } else if (this.f5966c != null) {
            Window window;
            WindowManager.LayoutParams attributes;
            if (this.f5965b == null) {
                this.f5965b = new Dialog(getContext(), 16973914);
                window = this.f5965b.getWindow();
                attributes = window.getAttributes();
                attributes.token = this.f5966c;
                attributes.gravity = 51;
                attributes.softInputMode = 32;
                attributes.type = 1001;
                attributes.flags = 16908808;
                this.f5965b.setContentView(this);
            } else {
                window = this.f5965b.getWindow();
                attributes = window.getAttributes();
            }
            attributes.x = this.f5967d;
            attributes.y = this.f5968e;
            attributes.width = this.f5969f;
            attributes.height = this.f5970g;
            window.setAttributes(attributes);
            this.f5965b.show();
            requestLayout();
        }
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.f5964a != null) {
            try {
                this.f5964a.m10135a();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(this.f5971h, this.f5972i);
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        if (this.f5973j != measuredWidth || this.f5974k != measuredHeight) {
            this.f5973j = measuredWidth;
            this.f5974k = measuredHeight;
            this.f5969f = this.f5973j;
            this.f5970g = this.f5974k;
            m9288a();
            if (this.f5964a != null) {
                try {
                    this.f5964a.m10136a(this.f5973j, this.f5974k);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
