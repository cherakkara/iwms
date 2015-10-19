package com.olacabs.customer.ui.widgets;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.util.AttributeSet;
import com.android.volley.toolbox.NetworkImageView;

public class RoundedNetworkImageView extends NetworkImageView {
    Context f11341a;

    public RoundedNetworkImageView(Context context) {
        super(context);
        this.f11341a = context;
    }

    public RoundedNetworkImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
        this.f11341a = context;
    }

    public RoundedNetworkImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f11341a = context;
    }

    public void setImageBitmap(Bitmap bitmap) {
        if (bitmap != null) {
            Drawable create = RoundedBitmapDrawableFactory.create(getResources(), bitmap);
            create.setCornerRadius((float) bitmap.getWidth());
            setImageDrawable(create);
        }
    }
}
