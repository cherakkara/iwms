package com.localytics.android;

import com.localytics.android.BaseHandler.BaseListener;

public interface MessagingListener extends BaseListener {
    void localyticsDidDismissInAppMessage();

    void localyticsDidDisplayInAppMessage();

    void localyticsWillDismissInAppMessage();

    void localyticsWillDisplayInAppMessage();
}
