package com.newrelic.agent.android.analytics;

public class AnalyticAttribute {
    public static final String ACCOUNT_ID_ATTRIBUTE = "accountId";
    public static final String APP_ID_ATTRIBUTE = "appId";
    public static final String APP_NAME_ATTRIBUTE = "appName";
    public static final int ATTRIBUTE_NAME_MAX_LENGTH = 256;
    public static final int ATTRIBUTE_VALUE_MAX_LENGTH = 4096;
    public static final String CAMPAIGN_ID_ATTRIBUTE = "campaignId";
    public static final String CARRIER_ATTRIBUTE = "carrier";
    public static final String DEVICE_MANUFACTURER_ATTRIBUTE = "deviceManufacturer";
    public static final String DEVICE_MODEL_ATTRIBUTE = "deviceModel";
    private static final String DOUBLE_ATTRIBUTE_FORMAT = "\"%s\"=%f";
    public static final String EVENT_CATEGORY_ATTRIBUTE = "category";
    public static final String EVENT_NAME_ATTRIBUTE = "name";
    public static final String EVENT_SESSION_ELAPSED_TIME_ATTRIBUTE = "sessionElapsedTime";
    public static final String EVENT_TIMESTAMP_ATTRIBUTE = "timestamp";
    public static final String EVENT_TIME_SINCE_LOAD_ATTRIBUTE = "timeSinceLoad";
    public static final String EVENT_TYPE_ATTRIBUTE = "eventType";
    public static final String EVENT_TYPE_ATTRIBUTE_MOBILE = "Mobile";
    public static final String INTERACTION_DURATION_ATTRIBUTE = "interactionDuration";
    public static final String MEM_USAGE_MB_ATTRIBUTE = "memUsageMb";
    public static final String NEW_RELIC_VERSION_ATTRIBUTE = "newRelicVersion";
    public static final String OS_MAJOR_VERSION_ATTRIBUTE = "osMajorVersion";
    public static final String OS_NAME_ATTRIBUTE = "osName";
    public static final String OS_VERSION_ATTRIBUTE = "osVersion";
    public static final String PURCHASE_EVENT_ATTRIBUTE = "Purchase";
    public static final String PURCHASE_EVENT_QUANTITY_ATTRIBUTE = "quantity";
    public static final String PURCHASE_EVENT_SKU_ATTRIBUTE = "sku";
    public static final String PURCHASE_EVENT_TOTAL_PRICE_ATTRIBUTE = "total";
    public static final String PURCHASE_EVENT_UNIT_PRICE_ATTRIBUTE = "unitprice";
    public static final String SESSION_DURATION_ATTRIBUTE = "sessionDuration";
    public static final String SESSION_ID_ATTRIBUTE = "sessionId";
    public static final String SESSION_REVENUE_ATTRIBUTE = "sessionRevenue";
    public static final String SESSION_TIME_SINCE_LOAD_ATTRIBUTE = "timeSinceLoad";
    private static final String STRING_ATTRIBUTE_FORMAT = "\"%s\"=\"%s\"";
    public static final String SUBSCRIPTION_ATTRIBUTE = "subscription";
    public static final String SUBSCRIPTION_REVENUE_ATTRIBUTE = "subscriptionRevenue";
    public static final String TYPE_ATTRIBUTE = "type";
    public static final String USERNAME_ATTRIBUTE = "username";
    public static final String USER_EMAIL_ATTRIBUTE = "email";
    public static final String USER_ID_ATTRIBUTE = "userId";
    public static final String UUID_ATTRIBUTE = "uuid";
    private float floatValue;
    private boolean isPersistent;
    private boolean isStringValue;
    private String name;
    private String stringValue;

    public AnalyticAttribute(String str, String str2) {
        this(str, str2, true);
    }

    public AnalyticAttribute(String str, String str2, boolean z) {
        this.name = str;
        this.stringValue = str2;
        this.isStringValue = true;
        this.floatValue = Float.NaN;
        this.isPersistent = z;
    }

    public AnalyticAttribute(String str, float f) {
        this(str, f, true);
    }

    public AnalyticAttribute(String str, float f, boolean z) {
        this.name = str;
        this.floatValue = f;
        this.isStringValue = false;
        this.stringValue = null;
        this.isPersistent = z;
    }

    public AnalyticAttribute(AnalyticAttribute analyticAttribute) {
        this.name = analyticAttribute.name;
        this.floatValue = analyticAttribute.floatValue;
        this.stringValue = analyticAttribute.stringValue;
        this.isStringValue = analyticAttribute.isStringValue;
        this.isPersistent = analyticAttribute.isPersistent;
    }

    public String getName() {
        return this.name;
    }

    public boolean isStringAttribute() {
        return this.isStringValue;
    }

    public String getStringValue() {
        return this.stringValue;
    }

    public void setStringValue(String str) {
        this.stringValue = str;
        this.floatValue = Float.NaN;
        this.isStringValue = true;
    }

    public float getFloatValue() {
        return this.floatValue;
    }

    public void setFloatValue(float f) {
        this.floatValue = f;
        this.stringValue = null;
        this.isStringValue = false;
    }

    public boolean isPersistent() {
        return this.isPersistent;
    }

    public void setPersistent(boolean z) {
        this.isPersistent = z;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        if (this.name.equals(((AnalyticAttribute) obj).name)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return this.name.hashCode();
    }

    public String toString() {
        return "AnalyticAttribute{name='" + this.name + '\'' + (isStringAttribute() ? ", stringValue='" + this.stringValue + '\'' : ", floatValue=" + this.floatValue) + ", isPersistent=" + this.isPersistent + '}';
    }
}
