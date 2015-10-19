package com.leanplum.messagetemplates;

import android.content.Context;
import android.graphics.Bitmap;
import com.leanplum.ActionArgs;
import com.leanplum.ActionContext;
import org.apache.http.HttpStatus;

public class CenterPopupOptions extends C0640j {
    private int f8831a;
    private int f8832b;

    public /* bridge */ /* synthetic */ void accept() {
        super.accept();
    }

    public /* bridge */ /* synthetic */ int getAcceptButtonBackgroundColor() {
        return super.getAcceptButtonBackgroundColor();
    }

    public /* bridge */ /* synthetic */ String getAcceptButtonText() {
        return super.getAcceptButtonText();
    }

    public /* bridge */ /* synthetic */ int getAcceptButtonTextColor() {
        return super.getAcceptButtonTextColor();
    }

    public /* bridge */ /* synthetic */ int getBackgroundColor() {
        return super.getBackgroundColor();
    }

    public /* bridge */ /* synthetic */ Bitmap getBackgroundImage() {
        return super.getBackgroundImage();
    }

    public /* bridge */ /* synthetic */ Bitmap getBackgroundImageRounded(int i) {
        return super.getBackgroundImageRounded(i);
    }

    public /* bridge */ /* synthetic */ int getMessageColor() {
        return super.getMessageColor();
    }

    public /* bridge */ /* synthetic */ String getMessageText() {
        return super.getMessageText();
    }

    public /* bridge */ /* synthetic */ String getTitle() {
        return super.getTitle();
    }

    public /* bridge */ /* synthetic */ int getTitleColor() {
        return super.getTitleColor();
    }

    public CenterPopupOptions(ActionContext actionContext) {
        super(actionContext);
        this.f8831a = actionContext.numberNamed("Layout.Width").intValue();
        this.f8832b = actionContext.numberNamed("Layout.Height").intValue();
    }

    public int getWidth() {
        return this.f8831a;
    }

    public int getHeight() {
        return this.f8832b;
    }

    public static ActionArgs toArgs(Context context) {
        return C0640j.toArgs(context).with("Layout.Width", Integer.valueOf(HttpStatus.SC_MULTIPLE_CHOICES)).with("Layout.Height", Integer.valueOf(250));
    }
}
