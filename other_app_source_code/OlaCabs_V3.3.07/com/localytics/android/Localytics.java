package com.localytics.android;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.location.Location;
import android.support.v4.app.FragmentActivity;
import com.olacabs.customer.utils.Constants;
import java.util.Date;
import java.util.Map;

public class Localytics {

    public enum InAppMessageDismissButtonLocation {
        LEFT,
        RIGHT
    }

    static final class Log {
        Log() {
        }

        static int m12793d(String str) {
            if (Constants.IS_LOGGING_ENABLED) {
                return android.util.Log.d("Localytics", str);
            }
            return -1;
        }

        static int m12794d(String str, Throwable th) {
            if (Constants.IS_LOGGING_ENABLED) {
                return android.util.Log.d("Localytics", str, th);
            }
            return -1;
        }

        static int m12795e(String str) {
            if (Constants.IS_LOGGING_ENABLED) {
                return android.util.Log.e("Localytics", str);
            }
            return -1;
        }

        static int m12796e(String str, Throwable th) {
            if (Constants.IS_LOGGING_ENABLED) {
                return android.util.Log.e("Localytics", str, th);
            }
            return -1;
        }

        static int m12797i(String str) {
            if (Constants.IS_LOGGING_ENABLED) {
                return android.util.Log.i("Localytics", str);
            }
            return -1;
        }

        static int m12798i(String str, Throwable th) {
            if (Constants.IS_LOGGING_ENABLED) {
                return android.util.Log.i("Localytics", str, th);
            }
            return -1;
        }

        static int m12800v(String str, Throwable th) {
            if (Constants.IS_LOGGING_ENABLED) {
                return android.util.Log.v("Localytics", str, th);
            }
            return -1;
        }

        static int m12799v(String str) {
            if (Constants.IS_LOGGING_ENABLED) {
                return android.util.Log.v("Localytics", str);
            }
            return -1;
        }

        static int m12803w(Throwable th) {
            if (Constants.IS_LOGGING_ENABLED) {
                return android.util.Log.w("Localytics", th);
            }
            return -1;
        }

        static int m12802w(String str, Throwable th) {
            if (Constants.IS_LOGGING_ENABLED) {
                return android.util.Log.w("Localytics", str, th);
            }
            return -1;
        }

        static int m12801w(String str) {
            if (Constants.IS_LOGGING_ENABLED) {
                return android.util.Log.w("Localytics", str);
            }
            return -1;
        }

        static int wtf(Throwable th) {
            if (Constants.IS_LOGGING_ENABLED) {
                return android.util.Log.wtf("Localytics", th);
            }
            return -1;
        }

        static int wtf(String str, Throwable th) {
            if (Constants.IS_LOGGING_ENABLED) {
                return android.util.Log.wtf("Localytics", str, th);
            }
            return -1;
        }

        static int wtf(String str) {
            if (Constants.IS_LOGGING_ENABLED) {
                return android.util.Log.wtf("Localytics", str);
            }
            return -1;
        }
    }

    public enum ProfileScope {
        ORGANIZATION("org"),
        APPLICATION("app");
        
        private final String scope;

        private ProfileScope(String str) {
            this.scope = str;
        }

        public String getScope() {
            return this.scope;
        }
    }

    public static void integrate(Context context) {
        integrate(context, null);
    }

    public static void integrate(Context context, String str) {
        if (context == null) {
            throw new IllegalArgumentException("context cannot be null");
        }
        LocalyticsManager.getInstance().integrate(context, str);
    }

    public static void upload() {
        LocalyticsManager.getInstance().upload();
    }

    static boolean isAutoIntegrate() {
        return LocalyticsManager.getInstance().isAutoIntegrate();
    }

    static void setIsAutoIntegrate(boolean z) {
        LocalyticsManager.getInstance().setIsAutoIntegrate(z);
    }

    static boolean isAppInForeground() {
        return LocalyticsManager.getInstance().isAppInForeground();
    }

    static void incrementActivityCounter() {
        LocalyticsManager.getInstance().incrementActivityCounter();
    }

    static void decrementActivityCounter() {
        LocalyticsManager.getInstance().decrementActivityCounter();
    }

    public static void setOptedOut(boolean z) {
        LocalyticsManager.getInstance().setOptedOut(z);
    }

    public static boolean isOptedOut() {
        return LocalyticsManager.getInstance().isOptedOut();
    }

    public static void openSession() {
        LocalyticsManager.getInstance().openSession();
    }

    public static void closeSession() {
        LocalyticsManager.getInstance().closeSession();
    }

    public static void tagEvent(String str) {
        tagEvent(str, null, 0);
    }

    public static void tagEvent(String str, Map<String, String> map) {
        tagEvent(str, map, 0);
    }

    public static void tagEvent(String str, Map<String, String> map, long j) {
        LocalyticsManager.getInstance().tagEvent(str, map, j);
    }

    public static void tagScreen(String str) {
        LocalyticsManager.getInstance().tagScreen(str);
    }

    public static void setCustomDimension(int i, String str) {
        LocalyticsManager.getInstance().setCustomDimension(i, str);
    }

    public static String getCustomDimension(int i) {
        return LocalyticsManager.getInstance().getCustomDimension(i);
    }

    public static void addAnalyticsListener(AnalyticsListener analyticsListener) {
        LocalyticsManager.getInstance().addAnalyticsListener(analyticsListener);
    }

    public static void removeAnalyticsListener(AnalyticsListener analyticsListener) {
        LocalyticsManager.getInstance().removeAnalyticsListener(analyticsListener);
    }

    public static void setProfileAttribute(String str, long j, ProfileScope profileScope) {
        LocalyticsManager.getInstance().setProfileAttribute(str, j, profileScope);
    }

    public static void setProfileAttribute(String str, long j) {
        setProfileAttribute(str, j, ProfileScope.APPLICATION);
    }

    public static void setProfileAttribute(String str, long[] jArr, ProfileScope profileScope) {
        LocalyticsManager.getInstance().setProfileAttribute(str, jArr, profileScope);
    }

    public static void setProfileAttribute(String str, long[] jArr) {
        setProfileAttribute(str, jArr, ProfileScope.APPLICATION);
    }

    public static void setProfileAttribute(String str, String str2, ProfileScope profileScope) {
        LocalyticsManager.getInstance().setProfileAttribute(str, str2, profileScope);
    }

    public static void setProfileAttribute(String str, String str2) {
        setProfileAttribute(str, str2, ProfileScope.APPLICATION);
    }

    public static void setProfileAttribute(String str, String[] strArr, ProfileScope profileScope) {
        LocalyticsManager.getInstance().setProfileAttribute(str, strArr, profileScope);
    }

    public static void setProfileAttribute(String str, String[] strArr) {
        setProfileAttribute(str, strArr, ProfileScope.APPLICATION);
    }

    public static void setProfileAttribute(String str, Date date, ProfileScope profileScope) {
        LocalyticsManager.getInstance().setProfileAttribute(str, date, profileScope);
    }

    public static void setProfileAttribute(String str, Date date) {
        setProfileAttribute(str, date, ProfileScope.APPLICATION);
    }

    public static void setProfileAttribute(String str, Date[] dateArr, ProfileScope profileScope) {
        LocalyticsManager.getInstance().setProfileAttribute(str, dateArr, profileScope);
    }

    public static void setProfileAttribute(String str, Date[] dateArr) {
        setProfileAttribute(str, dateArr, ProfileScope.APPLICATION);
    }

    public static void addProfileAttributesToSet(String str, long[] jArr, ProfileScope profileScope) {
        LocalyticsManager.getInstance().addProfileAttributesToSet(str, jArr, profileScope);
    }

    public static void addProfileAttributesToSet(String str, long[] jArr) {
        addProfileAttributesToSet(str, jArr, ProfileScope.APPLICATION);
    }

    public static void addProfileAttributesToSet(String str, String[] strArr, ProfileScope profileScope) {
        LocalyticsManager.getInstance().addProfileAttributesToSet(str, strArr, profileScope);
    }

    public static void addProfileAttributesToSet(String str, String[] strArr) {
        addProfileAttributesToSet(str, strArr, ProfileScope.APPLICATION);
    }

    public static void addProfileAttributesToSet(String str, Date[] dateArr, ProfileScope profileScope) {
        LocalyticsManager.getInstance().addProfileAttributesToSet(str, dateArr, profileScope);
    }

    public static void addProfileAttributesToSet(String str, Date[] dateArr) {
        addProfileAttributesToSet(str, dateArr, ProfileScope.APPLICATION);
    }

    public static void removeProfileAttributesFromSet(String str, long[] jArr, ProfileScope profileScope) {
        LocalyticsManager.getInstance().removeProfileAttributesFromSet(str, jArr, profileScope);
    }

    public static void removeProfileAttributesFromSet(String str, long[] jArr) {
        removeProfileAttributesFromSet(str, jArr, ProfileScope.APPLICATION);
    }

    public static void removeProfileAttributesFromSet(String str, String[] strArr, ProfileScope profileScope) {
        LocalyticsManager.getInstance().removeProfileAttributesFromSet(str, strArr, profileScope);
    }

    public static void removeProfileAttributesFromSet(String str, String[] strArr) {
        removeProfileAttributesFromSet(str, strArr, ProfileScope.APPLICATION);
    }

    public static void removeProfileAttributesFromSet(String str, Date[] dateArr, ProfileScope profileScope) {
        LocalyticsManager.getInstance().removeProfileAttributesFromSet(str, dateArr, profileScope);
    }

    public static void removeProfileAttributesFromSet(String str, Date[] dateArr) {
        removeProfileAttributesFromSet(str, dateArr, ProfileScope.APPLICATION);
    }

    public static void incrementProfileAttribute(String str, long j, ProfileScope profileScope) {
        LocalyticsManager.getInstance().incrementProfileAttribute(str, j, profileScope);
    }

    public static void incrementProfileAttribute(String str, long j) {
        incrementProfileAttribute(str, j, ProfileScope.APPLICATION);
    }

    public static void decrementProfileAttribute(String str, long j, ProfileScope profileScope) {
        LocalyticsManager.getInstance().incrementProfileAttribute(str, -1 * j, profileScope);
    }

    public static void decrementProfileAttribute(String str, long j) {
        decrementProfileAttribute(str, j, ProfileScope.APPLICATION);
    }

    public static void deleteProfileAttribute(String str, ProfileScope profileScope) {
        LocalyticsManager.getInstance().deleteProfileAttribute(str, profileScope);
    }

    public static void deleteProfileAttribute(String str) {
        deleteProfileAttribute(str, ProfileScope.APPLICATION);
    }

    public static void setCustomerEmail(String str) {
        setSpecialCustomerIdentifierAndAttribute(Constants.EMAIL, str);
    }

    public static void setCustomerFirstName(String str) {
        setSpecialCustomerIdentifierAndAttribute("first_name", str);
    }

    public static void setCustomerLastName(String str) {
        setSpecialCustomerIdentifierAndAttribute("last_name", str);
    }

    public static void setCustomerFullName(String str) {
        setSpecialCustomerIdentifierAndAttribute("full_name", str);
    }

    private static void setSpecialCustomerIdentifierAndAttribute(String str, String str2) {
        setProfileAttribute("$" + str, str2, ProfileScope.ORGANIZATION);
        setIdentifier(str, str2);
    }

    public static void setInAppMessageDisplayActivity(FragmentActivity fragmentActivity) {
        LocalyticsManager.getInstance().setInAppMessageDisplayActivity(fragmentActivity);
    }

    public static void clearInAppMessageDisplayActivity() {
        LocalyticsManager.getInstance().clearInAppMessageDisplayActivity();
    }

    public static void triggerInAppMessage(String str) {
        triggerInAppMessage(str, null);
    }

    public static void triggerInAppMessage(String str, Map<String, String> map) {
        LocalyticsManager.getInstance().triggerInAppMessage(str, map, false);
    }

    public static void dismissCurrentInAppMessage() {
        LocalyticsManager.getInstance().dismissCurrentInAppMessage();
    }

    public static void registerPush(String str) {
        LocalyticsManager.getInstance().registerPush(str);
    }

    static void registerPush(String str, long j) {
        LocalyticsManager.getInstance().registerPush(str, j);
    }

    public static String getPushRegistrationId() {
        return LocalyticsManager.getInstance().getPushRegistrationId();
    }

    public static void setPushRegistrationId(String str) {
        LocalyticsManager.getInstance().setPushRegistrationId(str);
    }

    public static void setPushDisabled(boolean z) {
        LocalyticsManager.getInstance().setPushDisabled(z);
    }

    public static boolean isPushDisabled() {
        return LocalyticsManager.getInstance().isPushDisabled();
    }

    public static void handlePushNotificationOpened(Intent intent) {
        LocalyticsManager.getInstance().handlePushNotificationOpened(intent);
    }

    static void handleRegistration(Intent intent) {
        LocalyticsManager.getInstance().handleRegistration(intent);
    }

    static void handleNotificationReceived(Intent intent) {
        LocalyticsManager.getInstance().handleNotificationReceived(intent);
    }

    public static void handleTestMode(Intent intent) {
        LocalyticsManager.getInstance().handleTestMode(intent);
    }

    public static void setTestModeEnabled(boolean z) {
        LocalyticsManager.getInstance().setTestModeEnabled(z);
    }

    public static boolean isTestModeEnabled() {
        return LocalyticsManager.getInstance().isTestModeEnabled();
    }

    public static void setInAppMessageDismissButtonImage(Resources resources, int i) {
        LocalyticsManager.getInstance().setInAppMessageDismissButtonImage(resources, i);
    }

    public static void setInAppMessageDismissButtonImage(Resources resources, Bitmap bitmap) {
        LocalyticsManager.getInstance().setInAppMessageDismissButtonImage(resources, bitmap);
    }

    public static void addMessagingListener(MessagingListener messagingListener) {
        LocalyticsManager.getInstance().addMessagingListener(messagingListener);
    }

    public static void removeMessagingListener(MessagingListener messagingListener) {
        LocalyticsManager.getInstance().removeMessagingListener(messagingListener);
    }

    public static void setIdentifier(String str, String str2) {
        LocalyticsManager.getInstance().setIdentifier(str, str2);
    }

    public static void setCustomerId(String str) {
        setIdentifier("customer_id", str);
    }

    public static String getCustomerId() {
        return getIdentifier("customer_id");
    }

    public static String getIdentifier(String str) {
        return LocalyticsManager.getInstance().getIdentifier(str);
    }

    public static void setLocation(Location location) {
        LocalyticsManager.getInstance().setLocation(location);
    }

    public static void setLoggingEnabled(boolean z) {
        Constants.IS_LOGGING_ENABLED = z;
    }

    public static boolean isLoggingEnabled() {
        return Constants.IS_LOGGING_ENABLED;
    }

    public static void setSessionTimeoutInterval(long j) {
        Constants.SESSION_EXPIRATION = 1000 * j;
    }

    public static long getSessionTimeoutInterval() {
        return Constants.SESSION_EXPIRATION / 1000;
    }

    public static String getInstallId() {
        return LocalyticsManager.getInstance().getInstallationId();
    }

    public static String getAppKey() {
        return LocalyticsManager.getInstance().getAppKey();
    }

    public static String getLibraryVersion() {
        return "androida_3.4.0";
    }

    public static void setInAppMessageDismissButtonLocation(InAppMessageDismissButtonLocation inAppMessageDismissButtonLocation) {
        LocalyticsManager.getInstance().setInAppMessageDismissButtonLocation(inAppMessageDismissButtonLocation);
    }

    public static InAppMessageDismissButtonLocation getInAppMessageDismissButtonLocation() {
        return LocalyticsManager.getInstance().getInAppMessageDismissButtonLocation();
    }

    public static String getAnalyticsHost() {
        return Constants.ANALYTICS_HOST;
    }

    public static void setAnalyticsHost(String str) {
        Constants.ANALYTICS_HOST = str;
    }

    public static String getMessagingHost() {
        return Constants.MARKETING_HOST;
    }

    public static void setMessagingHost(String str) {
        Constants.MARKETING_HOST = str;
    }

    public static String getProfilesHost() {
        return Constants.PROFILES_HOST;
    }

    public static void setProfilesHost(String str) {
        Constants.PROFILES_HOST = str;
    }
}
