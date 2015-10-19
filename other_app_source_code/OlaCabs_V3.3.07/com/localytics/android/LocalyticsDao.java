package com.localytics.android;

import android.content.Context;
import java.net.Proxy;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

interface LocalyticsDao {
    String getApiKey();

    Context getAppContext();

    Calendar getCalendar();

    long getCurrentTimeMillis();

    String getCustomDimension(int i);

    String getCustomerIdInMemory();

    String getCustomerIdInMemory(boolean z);

    CountDownLatch getCustomerIdInitiatedLatch();

    Map<String, String> getIdentifiers();

    String getInstallationId();

    Proxy getProxy();

    String getPushRegistrationId();

    String getTimeStringForSQLite();

    boolean isAppInForeground();

    boolean isAutoIntegrate();

    boolean isPushDisabled();

    void setCustomDimension(int i, String str);

    void setCustomerIdInMemory(String str);

    void setInstallationId(String str);

    void setScreenFlow(List<String> list);

    void setTestModeEnabled(boolean z);

    void tagEvent(String str);

    void tagEvent(String str, Map<String, String> map);

    void tagEvent(String str, Map<String, String> map, long j);

    void triggerInAppMessage(String str);

    void triggerInAppMessage(String str, Map<String, String> map);

    void triggerInAppMessage(String str, Map<String, String> map, boolean z);

    void upload();
}
