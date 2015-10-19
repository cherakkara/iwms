package com.olacabs.customer.p076d;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import com.newrelic.agent.android.instrumentation.Trace;
import com.olacabs.customer.app.AppState;
import com.olacabs.customer.app.OLog;
import com.olacabs.customer.tfs.p081a.TFSFareResponse;
import com.olacabs.customer.utils.Constants;
import com.olacabs.customer.utils.Utils;
import com.olacabs.p067a.p068a.p069a.p074d.MegatronCore;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/* compiled from: SessionInfo */
/* renamed from: com.olacabs.customer.d.da */
public class da {
    public static final String APP_INSTALLATION_ID_KEY = "install_id";
    public static final String AUTH_REFRESH_TOKEN = "auth_refresh_token";
    public static final String AUTH_SESSION_EXPIRY = "auth_session_expiry";
    public static final String AUTH_SESSION_ID = "auth_session_id";
    public static final String AUTH_THRESHOLD = "auth_threshold";
    public static final String GCM_REG_ID_KEY = "registration_id";
    public static final String GCM_REG_WITH_BACKEND = "gcm_reg_with_backend";
    private static final String IS_AUTH_ENABLE = "is_auth_enabled";
    public static final String IS_UPDATE_KEY = "isupdate";
    private static final String LAST_SHOWN_APP_VERSION_KEY = "last_shown_app_version";
    private static final String LOGTAG;
    public static final String NEW_INSTALL_IDENTIFIER_KEY = "is_new_install";
    public static final String OFD_CC_NUMBER = "08044050055";
    public static final String PAY_U_TRANSACTION_ID = "pay_u_transaction_id";
    public static final String X_REQUEST_ID = "X-REQUEST-ID";
    private static volatile da sInstance;
    private TFSFareResponse TFSFareResponse;
    private Map<String, Map<String, String>> mAnalyticsEvents;
    private AppState mAppState;
    private String mAuthRefreshToken;
    private long mAuthSessionExpiry;
    private String mAuthSessionId;
    private long mAuthThreshold;
    private boolean mAuthTokenAuthorized;
    private HashMap<String, List<String>> mBookingCancelReasons;
    private boolean mCabInfoTriggered;
    private String mCallCenterNumber;
    private String mCampaignName;
    private ab mCityBasedCarModels;
    private ab mCityBasedFareModels;
    private boolean mConfigLoaded;
    private ad mConfigurationResponse;
    private Context mContext;
    private Map<String, Map<String, String>> mFBAnalyticsEvents;
    private String mFirstTimeOfferHeader;
    private int mFirstTimeOfferId;
    private String mFirstTimeOfferTarget;
    private String mFirstTimeOfferText;
    private String mFoodCallCenterNumber;
    private bb mFoodDeliveryConfigurations;
    private String mGcmRegId;
    private String mInstallationId;
    private boolean mIsAnUpdate;
    private boolean mIsAuthEnabled;
    private boolean mIsFirstSession;
    private boolean mIsGcmRegWithBackend;
    private boolean mIsNewInstall;
    private int mLastShownVersion;
    private boolean mLocAccessDialogShown;
    private boolean mLocationEnabled;
    private int mMaxFoodItem;
    private boolean mOfferFlow;
    private String mPayUOrderId;
    private SharedPreferences mPref;
    private String mSessionId;
    private List<db> mSherlockEvent;
    private dd mSignUpAttempDetails;
    private String mTfsCallCenterNumber;

    static {
        LOGTAG = da.class.getSimpleName();
    }

    public int getMaxFoodItem() {
        return this.mMaxFoodItem;
    }

    public static da getInstance(Context context) {
        if (sInstance == null) {
            synchronized (da.class) {
                if (sInstance == null) {
                    sInstance = new da(context);
                }
            }
        }
        return sInstance;
    }

    private da(Context context) {
        this.mLocAccessDialogShown = false;
        this.mIsNewInstall = false;
        this.mIsAnUpdate = false;
        this.mSignUpAttempDetails = new dd();
        this.mOfferFlow = false;
        this.mIsAuthEnabled = false;
        this.mAuthTokenAuthorized = true;
        this.mCampaignName = "A";
        this.mMaxFoodItem = 10;
        this.mConfigLoaded = false;
        this.mContext = context;
    }

    public void init() {
        Utils.m14907a();
        OLog.m13311a("Loading up session info", new Object[0]);
        this.mPref = PreferenceManager.getDefaultSharedPreferences(this.mContext);
        this.mLocationEnabled = Utils.m14917d(this.mContext);
        this.mIsNewInstall = this.mPref.getBoolean(NEW_INSTALL_IDENTIFIER_KEY, true);
        this.mLastShownVersion = this.mPref.getInt(LAST_SHOWN_APP_VERSION_KEY, -1);
        if (this.mLastShownVersion < AppInfo.sVersionCode) {
            this.mIsAnUpdate = true;
        }
        this.mInstallationId = this.mPref.getString(APP_INSTALLATION_ID_KEY, null);
        if (TextUtils.isEmpty(this.mInstallationId)) {
            OLog.m13318e("Failed to read installation id! Generating new one", new Object[0]);
            setInstallationId(UUID.randomUUID().toString());
        }
        this.mGcmRegId = this.mPref.getString(GCM_REG_ID_KEY, Trace.NULL);
        this.mIsGcmRegWithBackend = this.mPref.getBoolean(GCM_REG_WITH_BACKEND, false);
        this.mAppState = AppState.BEFORE_BOOKING;
        this.mPayUOrderId = this.mPref.getString(PAY_U_TRANSACTION_ID, Trace.NULL);
        this.mAuthSessionId = this.mPref.getString(AUTH_SESSION_ID, Trace.NULL);
        this.mAuthSessionExpiry = this.mPref.getLong(AUTH_SESSION_EXPIRY, 0);
        this.mAuthRefreshToken = this.mPref.getString(AUTH_REFRESH_TOKEN, Trace.NULL);
        this.mAuthThreshold = this.mPref.getLong(AUTH_THRESHOLD, 1800000);
        this.mIsAuthEnabled = this.mPref.getBoolean(IS_AUTH_ENABLE, true);
    }

    public Map<String, Map<String, String>> getAnalyticsEvents() {
        return this.mAnalyticsEvents;
    }

    public void setAnalyticsEvents(Map<String, Map<String, String>> map) {
        this.mAnalyticsEvents = map;
    }

    public Map<String, Map<String, String>> getFBAnalyticsEvents() {
        return this.mFBAnalyticsEvents;
    }

    public void setFBAnalyticsEvents(Map<String, Map<String, String>> map) {
        this.mFBAnalyticsEvents = map;
    }

    public boolean isLocationAccessDialogShown() {
        return this.mLocAccessDialogShown;
    }

    public void setLocationAccessDialogShown(boolean z) {
        this.mLocAccessDialogShown = z;
    }

    public boolean isPreviouslyLoggedIn() {
        return this.mPref.getBoolean(dt.PREF_LOGGED_IN, false);
    }

    public boolean isLocationEnabled() {
        return this.mLocationEnabled;
    }

    public void setLocationStatus(boolean z) {
        this.mLocationEnabled = z;
    }

    public boolean isNewInstall() {
        return this.mIsNewInstall;
    }

    public void markIsNewInstall(boolean z) {
        this.mIsNewInstall = z;
        Editor edit = this.mPref.edit();
        edit.putBoolean(NEW_INSTALL_IDENTIFIER_KEY, z);
        edit.apply();
    }

    public boolean isAnUpdate() {
        return this.mIsAnUpdate;
    }

    public void updateLastShownVersion(int i) {
        Editor edit = this.mPref.edit();
        edit.putInt(LAST_SHOWN_APP_VERSION_KEY, i);
        edit.apply();
    }

    public int getLastShownVersion() {
        return this.mLastShownVersion;
    }

    public String getInstallationId() {
        return this.mInstallationId;
    }

    public void setInstallationId(String str) {
        this.mInstallationId = str;
        Editor edit = this.mPref.edit();
        edit.putString(APP_INSTALLATION_ID_KEY, str);
        edit.apply();
    }

    public String getGcmRegId() {
        return this.mGcmRegId;
    }

    public void setGcmRegId(String str) {
        this.mGcmRegId = str;
        Editor edit = this.mPref.edit();
        edit.putString(GCM_REG_ID_KEY, this.mGcmRegId);
        edit.apply();
    }

    public boolean isGcmRegWithBackend() {
        return this.mIsGcmRegWithBackend;
    }

    public void setGcmRegWithBackend(boolean z) {
        this.mIsGcmRegWithBackend = z;
        Editor edit = this.mPref.edit();
        edit.putBoolean(GCM_REG_WITH_BACKEND, z);
        edit.apply();
    }

    public boolean isFirstSession() {
        return this.mIsFirstSession;
    }

    public void setIsFirstSession(boolean z) {
        this.mIsFirstSession = z;
    }

    public dd getSignUpAttemptDetails() {
        if (this.mSignUpAttempDetails == null) {
            this.mSignUpAttempDetails = new dd();
        }
        return this.mSignUpAttempDetails;
    }

    public void setAppState(AppState appState) {
        this.mAppState = appState;
    }

    public AppState getAppState() {
        return this.mAppState;
    }

    public void clearSignUpAttemptDetail() {
        this.mSignUpAttempDetails = null;
    }

    public void setBookingCancelReasons(HashMap<String, List<String>> hashMap) {
        this.mBookingCancelReasons = hashMap;
    }

    public HashMap<String, List<String>> getBookingCancelReasons() {
        return this.mBookingCancelReasons;
    }

    public void setCityBasedCarModels(ab abVar) {
        this.mCityBasedCarModels = abVar;
    }

    public ab getCityBasedCarModels() {
        return this.mCityBasedCarModels;
    }

    public void setCityBasedFareModels(ab abVar) {
        this.mCityBasedFareModels = abVar;
    }

    public ab getCityBasedFareModels() {
        return this.mCityBasedFareModels;
    }

    public void setTfsFareModel(TFSFareResponse tFSFareResponse) {
        this.TFSFareResponse = tFSFareResponse;
    }

    public TFSFareResponse getTfsFareModel() {
        return this.TFSFareResponse;
    }

    public void setFoodDeliveryConfigurations(bb bbVar) {
        this.mFoodDeliveryConfigurations = bbVar;
        this.mMaxFoodItem = bbVar.getMaxSingleItemCount();
    }

    public bb getFoodDeliveryConfigurations() {
        return this.mFoodDeliveryConfigurations;
    }

    public void setConfigurations(ad adVar) {
        this.mConfigurationResponse = adVar;
    }

    public void setCallCenterNumber(String str) {
        this.mCallCenterNumber = str;
    }

    public void setFoodCallCenterNumber(String str) {
        this.mFoodCallCenterNumber = str;
    }

    public void setTfsCallCenterNumber(String str) {
        this.mTfsCallCenterNumber = str;
    }

    public String getFoodCallCenterNumber() {
        if (TextUtils.isEmpty(this.mFoodCallCenterNumber)) {
            return OFD_CC_NUMBER;
        }
        return this.mFoodCallCenterNumber;
    }

    public String getCallCenterNumber() {
        return this.mCallCenterNumber;
    }

    public String getTfsCallCenterNumber() {
        return this.mTfsCallCenterNumber;
    }

    public ad getConfigurationResponse() {
        return this.mConfigurationResponse;
    }

    public void setPayUOrderId(String str) {
        this.mPayUOrderId = str;
        Editor edit = this.mPref.edit();
        edit.putString(PAY_U_TRANSACTION_ID, str);
        edit.apply();
    }

    public String getPayUOrderId() {
        return this.mPayUOrderId;
    }

    public boolean isOfferFlow() {
        return this.mOfferFlow;
    }

    public void setOfferFlow(boolean z) {
        this.mOfferFlow = z;
    }

    public String getFirstTimeOfferHeader() {
        return this.mFirstTimeOfferHeader;
    }

    public void setFirstTimeOfferHeader(String str) {
        this.mFirstTimeOfferHeader = str;
    }

    public String getFirstTimeOfferText() {
        return this.mFirstTimeOfferText;
    }

    public void setFirstTimeOfferText(String str) {
        this.mFirstTimeOfferText = str;
    }

    public int getFirstTimeOfferId() {
        return this.mFirstTimeOfferId;
    }

    public void setFirstTimeOfferId(int i) {
        this.mFirstTimeOfferId = i;
    }

    public String getFirstTimeOfferTarget() {
        return this.mFirstTimeOfferTarget;
    }

    public void setFirstTimeOfferTarget(String str) {
        this.mFirstTimeOfferTarget = str;
    }

    public void setFirstTimeOfferDetails(String str, String str2, int i, String str3) {
        this.mFirstTimeOfferHeader = str;
        this.mFirstTimeOfferText = str2;
        this.mFirstTimeOfferId = i;
        this.mFirstTimeOfferTarget = str3;
        if (!TextUtils.isEmpty(str) || !TextUtils.isEmpty(str2)) {
            this.mPref.edit().putBoolean(Constants.PREF_COACHMARK_BOOKING_SCREEN, false).apply();
        }
    }

    public String getSessionId() {
        if (this.mSessionId == null) {
            this.mSessionId = UUID.randomUUID().toString();
        }
        return this.mSessionId;
    }

    public void setSessionId(String str) {
        this.mSessionId = str;
    }

    public void setAuthSessionId(String str) {
        this.mAuthSessionId = str;
        this.mPref.edit().putString(AUTH_SESSION_ID, str).apply();
        MegatronCore.m12842e().m12847a(str);
    }

    public String getAuthSessionId() {
        return this.mAuthSessionId;
    }

    public void setAuthSessionExpiry(long j) {
        this.mAuthSessionExpiry = j;
        Editor edit = this.mPref.edit();
        edit.putLong(AUTH_SESSION_EXPIRY, this.mAuthSessionExpiry);
        edit.apply();
    }

    public long getAuthSessionExpiry() {
        return this.mAuthSessionExpiry;
    }

    public void setAuthRefreshToken(String str) {
        this.mAuthRefreshToken = str;
        Editor edit = this.mPref.edit();
        edit.putString(AUTH_REFRESH_TOKEN, this.mAuthRefreshToken);
        edit.apply();
    }

    public String getAuthRefreshToken() {
        return this.mAuthRefreshToken;
    }

    public void setAuthRefreshTokenThreshold(long j) {
        this.mAuthThreshold = j;
        Editor edit = this.mPref.edit();
        edit.putLong(AUTH_THRESHOLD, this.mAuthThreshold);
        edit.apply();
    }

    public long getAuthRefreshTokenThreshold() {
        return this.mAuthThreshold;
    }

    public void setIsAuthEnable(boolean z) {
        this.mIsAuthEnabled = z;
        Editor edit = this.mPref.edit();
        edit.putBoolean(IS_AUTH_ENABLE, z);
        edit.apply();
    }

    public boolean isAuthEnabled() {
        return this.mIsAuthEnabled;
    }

    public synchronized void setTokenAuthorizedStatus(boolean z) {
        this.mAuthTokenAuthorized = z;
    }

    public synchronized boolean isAuthTokenAuthorized() {
        return this.mAuthTokenAuthorized;
    }

    public synchronized void setConfigurationLoaded(boolean z) {
        this.mConfigLoaded = z;
    }

    public synchronized boolean isConfigurationLoaded() {
        return this.mConfigLoaded;
    }

    public boolean isA() {
        return this.mCampaignName.equalsIgnoreCase("A");
    }

    public boolean isB() {
        return this.mCampaignName.equalsIgnoreCase("B");
    }

    public String getCampaignName() {
        return this.mCampaignName;
    }

    public void setCampaignName(String str) {
        this.mCampaignName = str;
    }

    public List<db> getSherlockEvent() {
        return this.mSherlockEvent;
    }

    public void setSherlockEvent(List<db> list) {
        this.mSherlockEvent = list;
    }

    public void cabInfoTriggered(boolean z) {
        this.mCabInfoTriggered = z;
    }

    public boolean isCabInfoTriggered() {
        return this.mCabInfoTriggered;
    }
}
