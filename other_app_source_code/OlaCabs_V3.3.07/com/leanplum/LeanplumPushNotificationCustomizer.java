package com.leanplum;

import android.os.Bundle;
import android.support.v4.app.NotificationCompat.Builder;

public interface LeanplumPushNotificationCustomizer {
    void customize(Builder builder, Bundle bundle);
}
