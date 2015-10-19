package com.localytics.android;

import java.util.Map.Entry;
import java.util.TreeMap;

final class AnalyticsUploadHandler extends BaseUploadThread {
    private static final String ANALYTICS_URL_HTTP = "http://%s/api/v2/applications/%s/uploads";
    private static final String ANALYTICS_URL_HTTPS = "https://%s/api/v2/uploads";

    AnalyticsUploadHandler(BaseHandler baseHandler, TreeMap<Integer, Object> treeMap, String str, LocalyticsDao localyticsDao) {
        super(baseHandler, treeMap, str, localyticsDao);
    }

    int uploadData() {
        try {
            if (!this.mData.isEmpty()) {
                String format;
                StringBuilder stringBuilder = new StringBuilder();
                String apiKey = getApiKey();
                for (Entry value : this.mData.entrySet()) {
                    stringBuilder.append(value.getValue());
                    stringBuilder.append('\n');
                }
                UploadType uploadType = UploadType.ANALYTICS;
                if (Constants.USE_HTTPS) {
                    format = String.format(ANALYTICS_URL_HTTPS, new Object[]{Constants.ANALYTICS_HOST});
                } else {
                    format = String.format(ANALYTICS_URL_HTTP, new Object[]{Constants.ANALYTICS_HOST, apiKey});
                }
                if (upload(uploadType, format, stringBuilder.toString(), 0)) {
                    return ((Integer) this.mData.lastKey()).intValue();
                }
            }
            return 0;
        } catch (Throwable th) {
            return 0;
        }
    }
}
