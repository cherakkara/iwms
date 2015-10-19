package com.localytics.android;

final class ReferralUploader extends BaseUploadThread {
    private static final String ANALYTICS_URL_HTTP = "http://%s/api/v2/applications/%s/uploads";
    private static final String ANALYTICS_URL_HTTPS = "https://%s/api/v2/uploads";
    private String mFirstSessionEvent;

    ReferralUploader(AnalyticsHandler analyticsHandler, String str, LocalyticsDao localyticsDao) {
        super(analyticsHandler, null, localyticsDao.getCustomerIdInMemory(), localyticsDao);
        this.mFirstSessionEvent = str;
    }

    int uploadData() {
        if (!this.mFirstSessionEvent.isEmpty()) {
            String format;
            Log.m12797i("[REFERRAL] reupload first session: " + this.mFirstSessionEvent);
            String apiKey = getApiKey();
            if (Constants.USE_HTTPS) {
                format = String.format(ANALYTICS_URL_HTTPS, new Object[]{Constants.ANALYTICS_HOST});
            } else {
                format = String.format(ANALYTICS_URL_HTTP, new Object[]{Constants.ANALYTICS_HOST, apiKey});
            }
            upload(UploadType.ANALYTICS, format, this.mFirstSessionEvent, 0, true);
        }
        return 0;
    }

    public void run() {
        uploadData();
    }
}
