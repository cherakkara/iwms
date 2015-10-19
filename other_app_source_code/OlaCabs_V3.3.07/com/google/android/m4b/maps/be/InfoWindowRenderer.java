package com.google.android.m4b.maps.be;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.os.RemoteException;
import android.support.v4.widget.ExploreByTouchHelper;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import com.google.android.m4b.maps.R.R;
import com.google.android.m4b.maps.cc.ObjectWrapper;
import com.google.android.m4b.maps.model.RuntimeRemoteException;
import com.google.android.m4b.maps.model.internal.IMarkerDelegate;
import com.google.android.m4b.maps.p042r.IInfoWindowAdapter;
import com.google.android.m4b.maps.p042r.IInfoWindowRenderer.IInfoWindowRenderer;
import com.google.p025a.p026a.Strings;

/* renamed from: com.google.android.m4b.maps.be.g */
public class InfoWindowRenderer extends IInfoWindowRenderer {
    private final ba f6011a;
    private final ViewGroup f6012b;
    private final InfoContentsView f6013c;
    private IInfoWindowAdapter f6014d;

    static {
        InfoWindowRenderer.class.getSimpleName();
    }

    public static InfoWindowRenderer m9385a(ba baVar, Context context, Resources resources) {
        ViewGroup linearLayout = new LinearLayout(context);
        linearLayout.setLayoutParams(new LayoutParams(-2, -2));
        linearLayout.setOrientation(1);
        linearLayout.setBackgroundDrawable(resources.getDrawable(R.popup_pointer_button_normal));
        return new InfoWindowRenderer(baVar, linearLayout, InfoContentsView.m9380a(context));
    }

    private InfoWindowRenderer(ba baVar, ViewGroup viewGroup, InfoContentsView infoContentsView) {
        this.f6011a = baVar;
        this.f6012b = viewGroup;
        this.f6013c = infoContentsView;
    }

    public final void m9387a(IInfoWindowAdapter iInfoWindowAdapter) {
        this.f6011a.m8774a();
        this.f6014d = iInfoWindowAdapter;
    }

    private View m9384a(IMarkerDelegate iMarkerDelegate) {
        try {
            View view = this.f6014d != null ? (View) ObjectWrapper.m10131a(this.f6014d.m11282a(iMarkerDelegate)) : null;
            if (view == null) {
                try {
                    if (this.f6014d != null) {
                        view = (View) ObjectWrapper.m10131a(this.f6014d.m11283b(iMarkerDelegate));
                    } else {
                        view = null;
                    }
                    if (view == null) {
                        if (Strings.m1866b(iMarkerDelegate.m8332o())) {
                            return null;
                        }
                        this.f6013c.m9381a(iMarkerDelegate.m8332o());
                        this.f6013c.m9382b(iMarkerDelegate.m8333q());
                        view = this.f6013c;
                    }
                    this.f6012b.removeAllViews();
                    this.f6012b.addView(view);
                    view = this.f6012b;
                } catch (RemoteException e) {
                    throw new RuntimeRemoteException(e);
                }
            }
            return view;
        } catch (RemoteException e2) {
            throw new RuntimeRemoteException(e2);
        }
    }

    public final Bitmap m9386a(IMarkerDelegate iMarkerDelegate, int i, int i2) {
        View a;
        try {
            a = m9384a(iMarkerDelegate);
        } catch (RemoteException e) {
            e.printStackTrace();
            a = null;
        }
        if (a == null) {
            return null;
        }
        int makeMeasureSpec = MeasureSpec.makeMeasureSpec(i, ExploreByTouchHelper.INVALID_ID);
        int makeMeasureSpec2 = MeasureSpec.makeMeasureSpec(i2, ExploreByTouchHelper.INVALID_ID);
        a.setLayoutParams(new LayoutParams(-2, -2));
        a.measure(makeMeasureSpec, makeMeasureSpec2);
        makeMeasureSpec = a.getMeasuredWidth();
        makeMeasureSpec2 = a.getMeasuredHeight();
        if (makeMeasureSpec == 0 || makeMeasureSpec2 == 0) {
            return null;
        }
        a.layout(0, 0, makeMeasureSpec, makeMeasureSpec2);
        Bitmap createBitmap = Bitmap.createBitmap(makeMeasureSpec, makeMeasureSpec2, Config.ARGB_8888);
        a.draw(new Canvas(createBitmap));
        return createBitmap;
    }
}
