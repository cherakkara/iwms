package com.leanplum.messagetemplates;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewCompat;
import android.util.Log;
import com.leanplum.ActionArgs;
import com.leanplum.ActionContext;
import com.leanplum.utils.BitmapUtil;
import com.newrelic.agent.android.instrumentation.BitmapFactoryInstrumentation;
import java.io.InputStream;

/* renamed from: com.leanplum.messagetemplates.j */
abstract class C0640j {
    private ActionContext f8821a;
    private String f8822b;
    private int f8823c;
    private String f8824d;
    private int f8825e;
    private Bitmap f8826f;
    private int f8827g;
    private String f8828h;
    private int f8829i;
    private int f8830j;

    protected C0640j(ActionContext actionContext) {
        this.f8821a = actionContext;
        this.f8822b = actionContext.stringNamed("Title.Text");
        this.f8823c = actionContext.numberNamed("Title.Color").intValue();
        this.f8824d = actionContext.stringNamed("Message.Text");
        this.f8825e = actionContext.numberNamed("Message.Color").intValue();
        InputStream streamNamed = actionContext.streamNamed("Background image");
        if (streamNamed != null) {
            try {
                this.f8826f = BitmapFactoryInstrumentation.decodeStream(streamNamed);
            } catch (Throwable e) {
                Log.e("Leanplum", "Error loading background image", e);
            }
        }
        this.f8827g = actionContext.numberNamed("Background color").intValue();
        this.f8828h = actionContext.stringNamed("Accept button.Text");
        this.f8829i = actionContext.numberNamed("Accept button.Background color").intValue();
        this.f8830j = actionContext.numberNamed("Accept button.Text color").intValue();
    }

    public int getBackgroundColor() {
        return this.f8827g;
    }

    public String getAcceptButtonText() {
        return this.f8828h;
    }

    public String getTitle() {
        return this.f8822b;
    }

    public int getTitleColor() {
        return this.f8823c;
    }

    public String getMessageText() {
        return this.f8824d;
    }

    public int getMessageColor() {
        return this.f8825e;
    }

    public Bitmap getBackgroundImage() {
        return this.f8826f;
    }

    public Bitmap getBackgroundImageRounded(int i) {
        return BitmapUtil.getRoundedCornerBitmap(this.f8826f, i);
    }

    public int getAcceptButtonBackgroundColor() {
        return this.f8829i;
    }

    public int getAcceptButtonTextColor() {
        return this.f8830j;
    }

    public void accept() {
        this.f8821a.runTrackedActionNamed("Accept action");
    }

    public static ActionArgs toArgs(Context context) {
        return new ActionArgs().with("Title.Text", MessageTemplates.m12782a(context)).withColor("Title.Color", ViewCompat.MEASURED_STATE_MASK).with("Message.Text", "Popup message goes here.").withColor("Message.Color", ViewCompat.MEASURED_STATE_MASK).withFile("Background image", null).withColor("Background color", -1).with("Accept button.Text", "OK").withColor("Accept button.Background color", -1).withColor("Accept button.Text color", Color.argb(MotionEventCompat.ACTION_MASK, 0, 122, MotionEventCompat.ACTION_MASK)).withAction("Accept action", null);
    }
}
