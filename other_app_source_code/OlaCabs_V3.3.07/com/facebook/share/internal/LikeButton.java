package com.facebook.share.internal;

import com.facebook.FacebookButtonBase;
import com.facebook.R.R;

/* renamed from: com.facebook.share.internal.d */
public class LikeButton extends FacebookButtonBase {
    public void setSelected(boolean z) {
        super.setSelected(z);
        m1530a();
    }

    protected int getDefaultStyleResource() {
        return R.com_facebook_button_like;
    }

    private void m1530a() {
        if (isSelected()) {
            setCompoundDrawablesWithIntrinsicBounds(R.com_facebook_button_like_icon_selected, 0, 0, 0);
            setText(getResources().getString(R.com_facebook_like_button_liked));
            return;
        }
        setCompoundDrawablesWithIntrinsicBounds(R.com_facebook_button_icon, 0, 0, 0);
        setText(getResources().getString(R.com_facebook_like_button_not_liked));
    }
}
