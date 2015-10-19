package com.localytics.android;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory.Options;
import android.location.Location;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.util.TypedValue;
import com.localytics.android.BaseHandler.BaseListener;
import com.localytics.android.Localytics.InAppMessageDismissButtonLocation;
import com.localytics.android.Localytics.ProfileScope;
import com.newrelic.agent.android.instrumentation.BitmapFactoryInstrumentation;
import com.olacabs.customer.utils.Constants;
import java.net.Proxy;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

enum LocalyticsManager implements LocalyticsDao {
    INSTANCE;
    
    private static int mActivityCounter;
    private static boolean mIsAutoIntegrate;
    private String mApiKey;
    private Context mAppContext;
    private String mCustomerId;
    private HandlerWrapper mHandlerWrapper;
    private CountDownLatch mInitiatedLatch;
    private String mInstallationId;
    private Proxy proxy;

    /* renamed from: com.localytics.android.LocalyticsManager.1 */
    class C06911 implements Runnable {
        C06911() {
        }

        public void run() {
            LocalyticsManager.this.getMarketingHandler().dismissCurrentInAppMessage();
        }
    }

    private final class HandlerWrapper {
        AnalyticsHandler analyticsHandler;
        MarketingHandler marketingHandler;
        ProfileHandler profileHandler;

        private HandlerWrapper(AnalyticsHandler analyticsHandler, MarketingHandler marketingHandler, ProfileHandler profileHandler) {
            this.analyticsHandler = analyticsHandler;
            this.marketingHandler = marketingHandler;
            this.profileHandler = profileHandler;
        }
    }

    public static final class LocalyticsNotInitializedException extends RuntimeException {
        private LocalyticsNotInitializedException() {
            super("You must first initialize Localytics");
        }
    }

    static {
        mActivityCounter = 0;
        mIsAutoIntegrate = false;
    }

    static LocalyticsManager getInstance() {
        return INSTANCE;
    }

    public synchronized void integrate(Context context, String str) {
        if (this.mHandlerWrapper == null) {
            if (!BuildConfig.APPLICATION_ID.equals(context.getPackageName()) || context.getClass().getName().equals("android.test.IsolatedContext") || context.getClass().getName().equals("android.test.RenamingDelegatingContext")) {
                boolean equals = context.getClass().getName().equals("android.test.RenamingDelegatingContext");
                if (equals || !(context instanceof Activity)) {
                    if (!equals) {
                        context = context.getApplicationContext();
                    }
                    this.mAppContext = context;
                    this.mApiKey = str;
                    if (TextUtils.isEmpty(this.mApiKey)) {
                        this.mApiKey = DatapointHelper.getLocalyticsAppKeyOrNull(this.mAppContext);
                    }
                    if (TextUtils.isEmpty(this.mApiKey)) {
                        throw new IllegalArgumentException("App key must be declared in a meta data tag in AndroidManifest.xml or passed into integrate(final Context context, final String localyticsKey) or new LocalyticsActivityLifecycleCallbacks(final Context context, final String localyticsKey) (see integration guide).");
                    }
                    try {
                        PackageInfo packageInfo = this.mAppContext.getPackageManager().getPackageInfo(this.mAppContext.getPackageName(), 3);
                        if (classInManifest(packageInfo.receivers, PushReceiver.class.getName()) && !classInManifest(packageInfo.activities, PushTrackingActivity.class.getName())) {
                            throw new IllegalStateException("PushTrackingActivity must be declared in AndroidManifest.xml (see integration guide). If migrating from 3.0, see https://support.localytics.com/Android_SDK_Migration_3.0_to_3.x");
                        }
                    } catch (Throwable e) {
                        Log.m12803w(e);
                    }
                    AnalyticsHandler analyticsHandler = new AnalyticsHandler(this, getHandlerThread(AnalyticsHandler.class.getSimpleName()).getLooper());
                    BaseListener marketingHandler = new MarketingHandler(this, getHandlerThread(MarketingHandler.class.getSimpleName()).getLooper(), this.mAppContext);
                    this.mHandlerWrapper = new HandlerWrapper(analyticsHandler, marketingHandler, new ProfileHandler(this, getHandlerThread(ProfileHandler.class.getSimpleName()).getLooper()), null);
                    analyticsHandler.addListener(marketingHandler);
                } else {
                    throw new IllegalStateException("Activity context use is not supported. You must call integrate() or registerActivityLifecycleCallbacks() from your Application class (see integration guide). If migrating from 3.0, see https://support.localytics.com/Android_SDK_Migration_3.0_to_3.x");
                }
            }
            throw new IllegalArgumentException(String.format("context.getPackageName() returned %s", new Object[]{context.getPackageName()}));
        }
    }

    public Context getAppContext() {
        return this.mAppContext;
    }

    public String getApiKey() {
        return this.mApiKey;
    }

    public CountDownLatch getCustomerIdInitiatedLatch() {
        return this.mInitiatedLatch;
    }

    public void upload() {
        if (!TextUtils.isEmpty(this.mApiKey)) {
            String customerIdInMemory = getCustomerIdInMemory();
            getProfileHandler().upload(customerIdInMemory);
            getAnalyticsHandler().upload(customerIdInMemory);
        }
    }

    public void setOptedOut(boolean z) {
        getAnalyticsHandler().setOptedOut(z);
    }

    public boolean isOptedOut() {
        return getAnalyticsHandler().isOptedOut();
    }

    public void openSession() {
        getAnalyticsHandler().openSession();
    }

    public void closeSession() {
        getAnalyticsHandler().closeSession();
    }

    public void tagEvent(String str) {
        tagEvent(str, null, 0);
    }

    public void tagEvent(String str, Map<String, String> map) {
        tagEvent(str, map, 0);
    }

    public void tagEvent(String str, Map<String, String> map, long j) {
        getAnalyticsHandler().tagEvent(str, map, j);
    }

    public void tagScreen(String str) {
        getAnalyticsHandler().tagScreen(str);
    }

    public void setCustomDimension(int i, String str) {
        getAnalyticsHandler().setCustomDimension(i, str);
    }

    public String getCustomDimension(int i) {
        return getAnalyticsHandler().getCustomDimension(i);
    }

    public void addAnalyticsListener(AnalyticsListener analyticsListener) {
        getAnalyticsHandler().addListener(analyticsListener);
    }

    public void removeAnalyticsListener(AnalyticsListener analyticsListener) {
        getAnalyticsHandler().removeListener(analyticsListener);
    }

    public void setProfileAttribute(String str, long j, ProfileScope profileScope) {
        getProfileHandler().setProfileAttribute(str, j, profileScope.getScope());
    }

    public void setProfileAttribute(String str, long j) {
        setProfileAttribute(str, j, ProfileScope.APPLICATION);
    }

    public void setProfileAttribute(String str, long[] jArr, ProfileScope profileScope) {
        getProfileHandler().setProfileAttribute(str, jArr, profileScope.getScope());
    }

    public void setProfileAttribute(String str, long[] jArr) {
        setProfileAttribute(str, jArr, ProfileScope.APPLICATION);
    }

    public void setProfileAttribute(String str, String str2, ProfileScope profileScope) {
        getProfileHandler().setProfileAttribute(str, str2, profileScope.getScope());
    }

    public void setProfileAttribute(String str, String str2) {
        setProfileAttribute(str, str2, ProfileScope.APPLICATION);
    }

    public void setProfileAttribute(String str, String[] strArr, ProfileScope profileScope) {
        getProfileHandler().setProfileAttribute(str, strArr, profileScope.getScope());
    }

    public void setProfileAttribute(String str, String[] strArr) {
        setProfileAttribute(str, strArr, ProfileScope.APPLICATION);
    }

    public void setProfileAttribute(String str, Date date, ProfileScope profileScope) {
        getProfileHandler().setProfileAttribute(str, date, profileScope.getScope());
    }

    public void setProfileAttribute(String str, Date date) {
        setProfileAttribute(str, date, ProfileScope.APPLICATION);
    }

    public void setProfileAttribute(String str, Date[] dateArr, ProfileScope profileScope) {
        getProfileHandler().setProfileAttribute(str, dateArr, profileScope.getScope());
    }

    public void setProfileAttribute(String str, Date[] dateArr) {
        setProfileAttribute(str, dateArr, ProfileScope.APPLICATION);
    }

    public void addProfileAttributesToSet(String str, long[] jArr, ProfileScope profileScope) {
        getProfileHandler().addProfileAttributesToSet(str, jArr, profileScope.getScope());
    }

    public void addProfileAttributesToSet(String str, long[] jArr) {
        addProfileAttributesToSet(str, jArr, ProfileScope.APPLICATION);
    }

    public void addProfileAttributesToSet(String str, String[] strArr, ProfileScope profileScope) {
        getProfileHandler().addProfileAttributesToSet(str, strArr, profileScope.getScope());
    }

    public void addProfileAttributesToSet(String str, String[] strArr) {
        addProfileAttributesToSet(str, strArr, ProfileScope.APPLICATION);
    }

    public void addProfileAttributesToSet(String str, Date[] dateArr, ProfileScope profileScope) {
        getProfileHandler().addProfileAttributesToSet(str, dateArr, profileScope.getScope());
    }

    public void addProfileAttributesToSet(String str, Date[] dateArr) {
        addProfileAttributesToSet(str, dateArr, ProfileScope.APPLICATION);
    }

    public void removeProfileAttributesFromSet(String str, long[] jArr, ProfileScope profileScope) {
        getProfileHandler().removeProfileAttributesFromSet(str, jArr, profileScope.getScope());
    }

    public void removeProfileAttributesFromSet(String str, long[] jArr) {
        removeProfileAttributesFromSet(str, jArr, ProfileScope.APPLICATION);
    }

    public void removeProfileAttributesFromSet(String str, String[] strArr, ProfileScope profileScope) {
        getProfileHandler().removeProfileAttributesFromSet(str, strArr, profileScope.getScope());
    }

    public void removeProfileAttributesFromSet(String str, String[] strArr) {
        removeProfileAttributesFromSet(str, strArr, ProfileScope.APPLICATION);
    }

    public void removeProfileAttributesFromSet(String str, Date[] dateArr, ProfileScope profileScope) {
        getProfileHandler().removeProfileAttributesFromSet(str, dateArr, profileScope.getScope());
    }

    public void removeProfileAttributesFromSet(String str, Date[] dateArr) {
        removeProfileAttributesFromSet(str, dateArr, ProfileScope.APPLICATION);
    }

    public void incrementProfileAttribute(String str, long j, ProfileScope profileScope) {
        getProfileHandler().incrementProfileAttribute(str, j, profileScope.getScope());
    }

    public void incrementProfileAttribute(String str, long j) {
        incrementProfileAttribute(str, j, ProfileScope.APPLICATION);
    }

    public void decrementProfileAttribute(String str, long j, ProfileScope profileScope) {
        getProfileHandler().incrementProfileAttribute(str, -1 * j, profileScope.getScope());
    }

    public void decrementProfileAttribute(String str, long j) {
        decrementProfileAttribute(str, j, ProfileScope.APPLICATION);
    }

    public void deleteProfileAttribute(String str, ProfileScope profileScope) {
        getProfileHandler().deleteProfileAttribute(str, profileScope.getScope());
    }

    public void deleteProfileAttribute(String str) {
        deleteProfileAttribute(str, ProfileScope.APPLICATION);
    }

    public void setCustomerEmail(String str) {
        setSpecialCustomerIdentifierAndAttribute(Constants.EMAIL, str);
    }

    public void setCustomerFirstName(String str) {
        setSpecialCustomerIdentifierAndAttribute("first_name", str);
    }

    public void setCustomerLastName(String str) {
        setSpecialCustomerIdentifierAndAttribute("last_name", str);
    }

    public void setCustomerFullName(String str) {
        setSpecialCustomerIdentifierAndAttribute("full_name", str);
    }

    private void setSpecialCustomerIdentifierAndAttribute(String str, String str2) {
        setProfileAttribute("$" + str, str2, ProfileScope.ORGANIZATION);
        setIdentifier(str, str2);
    }

    public void setInAppMessageDisplayActivity(FragmentActivity fragmentActivity) {
        if (fragmentActivity == null) {
            throw new IllegalArgumentException("attached activity cannot be null");
        }
        getMarketingHandler().setFragmentManager(fragmentActivity.getSupportFragmentManager());
        if (isTestModeEnabled()) {
            getMarketingHandler().showMarketingTest();
        }
    }

    public void clearInAppMessageDisplayActivity() {
        getMarketingHandler().setFragmentManager(null);
    }

    public void triggerInAppMessage(String str) {
        triggerInAppMessage(str, null);
    }

    public void triggerInAppMessage(String str, Map<String, String> map) {
        triggerInAppMessage(str, map, false);
    }

    public void triggerInAppMessage(String str, Map<String, String> map, boolean z) {
        getMarketingHandler().displayMarketingMessage(str, map, z);
    }

    public void dismissCurrentInAppMessage() {
        Runnable c06911 = new C06911();
        if (Looper.myLooper() == Looper.getMainLooper()) {
            c06911.run();
        } else {
            new Handler(Looper.getMainLooper()).post(c06911);
        }
    }

    public void registerPush(String str) {
        registerPush(str, 0);
    }

    public void registerPush(String str, long j) {
        getAnalyticsHandler().registerPush(str, j);
    }

    public String getPushRegistrationId() {
        return getAnalyticsHandler().getPushRegistrationID();
    }

    public void setPushRegistrationId(String str) {
        getAnalyticsHandler().setPushRegistrationId(str);
    }

    public void setPushDisabled(boolean z) {
        getAnalyticsHandler().setPushDisabled(z);
    }

    public boolean isPushDisabled() {
        return getAnalyticsHandler().isPushDisabled();
    }

    public void handlePushNotificationOpened(Intent intent) {
        getMarketingHandler().handlePushNotificationOpened(intent);
    }

    public void handleTestMode(Intent intent) {
        getMarketingHandler().handleTestModeIntent(intent);
    }

    public void setTestModeEnabled(boolean z) {
        if (Constants.isTestModeEnabled() != z) {
            Constants.setTestModeEnabled(z);
            if (z) {
                getMarketingHandler().showMarketingTest();
            }
        }
    }

    public boolean isTestModeEnabled() {
        return Constants.isTestModeEnabled();
    }

    public void setInAppMessageDismissButtonImage(Resources resources, int i) {
        Options options = new Options();
        options.inPurgeable = true;
        options.inPreferredConfig = Config.ARGB_8888;
        Bitmap decodeResource = BitmapFactoryInstrumentation.decodeResource(resources, i, options);
        if (decodeResource == null) {
            Log.m12801w("Cannot load the new dismiss button image. Is the resource id corrected?");
            getMarketingHandler().setDismissButtonImage(null);
            return;
        }
        Bitmap scaleDownBitmap = scaleDownBitmap(decodeResource, TypedValue.applyDimension(1, 40.0f, resources.getDisplayMetrics()));
        if (scaleDownBitmap != decodeResource) {
            decodeResource.recycle();
        }
        getMarketingHandler().setDismissButtonImage(scaleDownBitmap);
    }

    public void setInAppMessageDismissButtonImage(Resources resources, Bitmap bitmap) {
        Bitmap bitmap2 = null;
        if (bitmap != null) {
            bitmap2 = scaleDownBitmap(bitmap, TypedValue.applyDimension(1, 40.0f, resources.getDisplayMetrics()));
            if (bitmap2 == bitmap) {
                bitmap2 = bitmap.copy(Config.ARGB_8888, false);
            }
        }
        getMarketingHandler().setDismissButtonImage(bitmap2);
    }

    public void addMessagingListener(MessagingListener messagingListener) {
        getMarketingHandler().addListener(messagingListener);
    }

    public void removeMessagingListener(MessagingListener messagingListener) {
        getMarketingHandler().removeListener(messagingListener);
    }

    public void setIdentifier(String str, String str2) {
        if ("customer_id".equals(str)) {
            setCustomerIdInMemory(str2);
        }
        getAnalyticsHandler().setIdentifier(str, str2);
    }

    public synchronized void setCustomerIdInMemory(String str) {
        if (TextUtils.isEmpty(str)) {
            str = this.mInstallationId;
        }
        this.mCustomerId = str;
    }

    public String getCustomerIdInMemory() {
        return getCustomerIdInMemory(false);
    }

    public String getCustomerIdInMemory(boolean z) {
        String str;
        if (!z) {
            try {
                this.mInitiatedLatch.await();
            } catch (Exception e) {
            }
        }
        synchronized (Localytics.class) {
            str = this.mCustomerId;
        }
        return str;
    }

    public void setCustomerId(String str) {
        setIdentifier("customer_id", str);
    }

    public String getCustomerId() {
        return getIdentifier("customer_id");
    }

    public String getIdentifier(String str) {
        return getAnalyticsHandler().getIdentifier(str);
    }

    public void setLocation(Location location) {
        getAnalyticsHandler().setLocation(location);
    }

    public String getInstallationId() {
        if (!TextUtils.isEmpty(this.mInstallationId)) {
            return this.mInstallationId;
        }
        throw new RuntimeException("No installation id!");
    }

    public void setInstallationId(String str) {
        this.mInstallationId = str;
    }

    public String getAppKey() {
        return DatapointHelper.getLocalyticsAppKeyOrNull(this.mAppContext);
    }

    public void setInAppMessageDismissButtonLocation(InAppMessageDismissButtonLocation inAppMessageDismissButtonLocation) {
        getMarketingHandler().setInAppDismissButtonLocation(inAppMessageDismissButtonLocation);
    }

    public InAppMessageDismissButtonLocation getInAppMessageDismissButtonLocation() {
        return getMarketingHandler().getInAppDismissButtonLocation();
    }

    public void setScreenFlow(List<String> list) {
        getAnalyticsHandler().setScreenFlow(list);
    }

    public void setReferrerId(String str) {
        getAnalyticsHandler().setReferrerId(str);
    }

    public void handleRegistration(Intent intent) {
        getAnalyticsHandler().handleRegistration(intent);
    }

    public void handleNotificationReceived(Intent intent) {
        getMarketingHandler().handleNotificationReceived(intent);
    }

    public Map<String, String> getIdentifiers() {
        return getAnalyticsHandler().getIdentifiers();
    }

    void incrementActivityCounter() {
        mActivityCounter++;
    }

    void decrementActivityCounter() {
        mActivityCounter--;
    }

    public boolean isAppInForeground() {
        return mActivityCounter > 0;
    }

    public boolean isAutoIntegrate() {
        return mIsAutoIntegrate;
    }

    void setIsAutoIntegrate(boolean z) {
        mIsAutoIntegrate = z;
    }

    AnalyticsHandler getAnalyticsHandler() {
        if (this.mHandlerWrapper != null) {
            return this.mHandlerWrapper.analyticsHandler;
        }
        throw new LocalyticsNotInitializedException();
    }

    MarketingHandler getMarketingHandler() {
        if (this.mHandlerWrapper != null) {
            return this.mHandlerWrapper.marketingHandler;
        }
        throw new LocalyticsNotInitializedException();
    }

    ProfileHandler getProfileHandler() {
        if (this.mHandlerWrapper != null) {
            return this.mHandlerWrapper.profileHandler;
        }
        throw new LocalyticsNotInitializedException();
    }

    private boolean classInManifest(ActivityInfo[] activityInfoArr, String str) {
        if (activityInfoArr == null) {
            return false;
        }
        for (ActivityInfo activityInfo : activityInfoArr) {
            if (activityInfo.name.equalsIgnoreCase(str)) {
                return true;
            }
        }
        return false;
    }

    private HandlerThread getHandlerThread(String str) {
        HandlerThread handlerThread = new HandlerThread(str, 10);
        handlerThread.start();
        return handlerThread;
    }

    private Bitmap scaleDownBitmap(Bitmap bitmap, float f) {
        float width = ((float) bitmap.getWidth()) / ((float) bitmap.getHeight());
        if (((float) Math.max(bitmap.getWidth(), bitmap.getHeight())) > f) {
            if (bitmap.getWidth() >= bitmap.getHeight()) {
                bitmap = Bitmap.createScaledBitmap(bitmap, (int) f, (int) ((f / width) + 0.5f), true);
            } else {
                bitmap = Bitmap.createScaledBitmap(bitmap, (int) ((f * width) + 0.5f), (int) f, true);
            }
            if (bitmap == null) {
                Log.m12801w("Cannot scale down the new dismiss button image.");
            }
        }
        return bitmap;
    }

    static Object throwOrLogError(Class cls, String str) {
        return throwOrLogError(cls, str, null);
    }

    static Object throwOrLogError(Class cls, String str, Exception exception) {
        return null;
    }

    public long getCurrentTimeMillis() {
        return System.currentTimeMillis();
    }

    public Calendar getCalendar() {
        return Calendar.getInstance();
    }

    public String getTimeStringForSQLite() {
        return "now";
    }

    public Proxy getProxy() {
        return this.proxy;
    }

    public void setProxy(Proxy proxy) {
        this.proxy = proxy;
    }
}
