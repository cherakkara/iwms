package com.localytics.android;

import com.localytics.android.BaseHandler.BaseListener;
import java.util.Map;

public interface AnalyticsListener extends BaseListener {
    void localyticsDidTagEvent(String str, Map<String, String> map, long j);

    void localyticsSessionDidOpen(boolean z, boolean z2, boolean z3);

    void localyticsSessionWillClose();

    void localyticsSessionWillOpen(boolean z, boolean z2, boolean z3);
}
