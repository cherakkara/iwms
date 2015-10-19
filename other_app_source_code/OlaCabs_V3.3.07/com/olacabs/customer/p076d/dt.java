package com.olacabs.customer.p076d;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.location.Location;
import android.preference.PreferenceManager;
import com.newrelic.agent.android.instrumentation.Trace;
import com.olacabs.customer.app.OLog;
import com.olacabs.customer.utils.Constants;
import com.olacabs.customer.utils.Utils;
import java.util.Map;
import java.util.Map.Entry;

/* compiled from: UserInfo */
/* renamed from: com.olacabs.customer.d.dt */
public class dt {
    public static final String EMAIL = "email";
    private static final String LOGTAG;
    public static final String PREF_EMAIL = "email_entered";
    public static final String PREF_IS_VERIFIED = "is verified";
    public static final String PREF_LOGGED_IN = "loggedIn";
    public static final String PREF_MOBILE = "number_entered";
    private static final String PREF_NAME_ENTERED = "name_entered";
    public static final String PREF_OLA_MONEY_BALANCE = "ola_money_balance";
    public static final String PREF_REFERRAL_CODE = "referral_code";
    public static final String PREF_USER_ID = "userId";
    public static final String USER_CITY_KEY = "city";
    public static final String USER_EC_AUTO_SHARE_KEY = "enable_auto_share";
    public static final String USER_EC_EMAIL_KEY = "email";
    public static final String USER_EC_NAME_KEY = "name";
    public static final String USER_EC_PHONE_KEY = "phone";
    public static final String USER_EMAIL_KEY = "email";
    public static final String USER_ID_KEY = "user_id";
    public static final String USER_IS_VERIFIED_KEY = "is verified";
    public static final String USER_LOC_ACCURACY_KEY = "accuracy";
    public static final String USER_LOC_FIX_TIME_KEY = "fix_time";
    public static final String USER_LOC_LAT_KEY = "lat";
    public static final String USER_LOC_LONG_KEY = "lng";
    public static final String USER_NAME_KEY = "name";
    public static final String USER_PASSWORD_KEY = "password";
    public static final String USER_PHONE_KEY = "mobile";
    public static final String VERIFY_USER_ON_SIGNUP_KEY = "verify";
    private static dt sInstance;
    private String mCity;
    private String mClearUserId;
    private Context mContext;
    private boolean mIsLoggedIn;
    private Location mLocation;
    private String mName;
    private int mOlaBalance;
    private String mPassword;
    private String mPhoneNumber;
    private SharedPreferences mPref;
    private String mRefCode;
    private String mReferrerCode;
    private String mUserAddress;
    private String mUserId;
    private String mUserLoginEmail;
    private String mUserPrimaryEmail;
    private boolean mVerified;

    static {
        LOGTAG = dt.class.getSimpleName();
    }

    public static dt getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new dt(context);
        }
        return sInstance;
    }

    private dt(Context context) {
        this.mUserPrimaryEmail = null;
        this.mUserLoginEmail = null;
        this.mLocation = null;
        this.mContext = context;
    }

    public void init() {
        Utils.m14907a();
        OLog.m13311a("Loading up user info", new Object[0]);
        this.mPref = PreferenceManager.getDefaultSharedPreferences(this.mContext);
        Account[] accountsByType = AccountManager.get(this.mContext).getAccountsByType("com.google");
        if (accountsByType.length > 0) {
            this.mUserPrimaryEmail = accountsByType[0].name;
        }
        this.mUserId = this.mPref.getString(PREF_USER_ID, null);
        this.mClearUserId = Utils.m14922f(this.mUserId);
        this.mRefCode = this.mPref.getString(PREF_REFERRAL_CODE, null);
        this.mPhoneNumber = this.mPref.getString(PREF_MOBILE, null);
        this.mOlaBalance = this.mPref.getInt(PREF_OLA_MONEY_BALANCE, 0);
        this.mUserLoginEmail = this.mPref.getString(PREF_EMAIL, null);
        this.mIsLoggedIn = this.mPref.getBoolean(PREF_LOGGED_IN, false);
        this.mName = this.mPref.getString(USER_NAME_KEY, null);
        this.mUserPrimaryEmail = this.mPref.getString(USER_EMAIL_KEY, null);
        this.mVerified = this.mPref.getBoolean(USER_IS_VERIFIED_KEY, true);
    }

    public String getPrimaryMailId() {
        return this.mUserPrimaryEmail;
    }

    public void setPrimaryMailId(String str) {
        this.mUserPrimaryEmail = str;
    }

    public String getUserId() {
        return this.mUserId;
    }

    public void setUserId(String str) {
        this.mUserId = str;
        this.mClearUserId = Utils.m14922f(this.mUserId);
        Editor edit = this.mPref.edit();
        edit.putString(PREF_USER_ID, str);
        edit.apply();
        this.mClearUserId = Utils.m14922f(this.mUserId);
    }

    public String getName() {
        return this.mName;
    }

    public String getFirstName() {
        return getFirstWord(this.mName);
    }

    public void setName(String str) {
        this.mName = str;
        Editor edit = this.mPref.edit();
        edit.putString(PREF_NAME_ENTERED, str);
        edit.apply();
    }

    public String getReferralCode() {
        return this.mRefCode;
    }

    public void setReferralCode(String str) {
        this.mRefCode = str;
        Editor edit = this.mPref.edit();
        edit.putString(PREF_REFERRAL_CODE, str);
        edit.apply();
    }

    public String getPhoneNumber() {
        return this.mPhoneNumber;
    }

    public void setPhoneNumber(String str) {
        this.mPhoneNumber = str;
        Editor edit = this.mPref.edit();
        edit.putString(PREF_MOBILE, str);
        edit.putBoolean(USER_IS_VERIFIED_KEY, true);
        edit.apply();
    }

    public int getOlaBalance() {
        return this.mOlaBalance;
    }

    public void setOlaBalance(int i) {
        this.mOlaBalance = i;
        Editor edit = this.mPref.edit();
        edit.putInt(PREF_OLA_MONEY_BALANCE, i);
        edit.apply();
    }

    public String getUserLoginEmail() {
        return this.mUserLoginEmail;
    }

    public void setEmailId(String str) {
        this.mUserLoginEmail = str;
        Editor edit = this.mPref.edit();
        edit.putString(PREF_EMAIL, str);
        edit.putString(USER_EMAIL_KEY, str);
    }

    public boolean isLoggedIn() {
        return this.mIsLoggedIn;
    }

    public void markAsLoggedIn(boolean z) {
        this.mIsLoggedIn = z;
        Editor edit = this.mPref.edit();
        edit.putBoolean(PREF_LOGGED_IN, z);
        edit.apply();
    }

    public Location getUserLocation() {
        return this.mLocation;
    }

    public void setUserLocation(Location location) {
        this.mLocation = location;
    }

    public String getPassword() {
        return this.mPassword;
    }

    public void setPassword(String str) {
        this.mPassword = str;
    }

    public String getReferrerCode() {
        return this.mReferrerCode;
    }

    public void setReferrerCode(String str) {
        this.mReferrerCode = str;
    }

    public String getCity() {
        return this.mCity;
    }

    public void setCity(String str) {
        this.mCity = str;
        Editor edit = this.mPref.edit();
        edit.putString(USER_CITY_KEY, str);
        edit.apply();
    }

    public String getUserAddress() {
        return this.mUserAddress;
    }

    public void setUserAddress(String str) {
        this.mUserAddress = str;
    }

    public String getClearUserId() {
        return this.mClearUserId;
    }

    public void updateUserInfoOnLogin(String str, String str2, String str3, int i, String str4, boolean z, String str5) {
        Editor edit = this.mPref.edit();
        this.mUserId = str;
        edit.putString(PREF_USER_ID, str);
        this.mClearUserId = Utils.m14922f(this.mUserId);
        this.mRefCode = str2;
        edit.putString(PREF_REFERRAL_CODE, str2);
        this.mPhoneNumber = str3;
        edit.putString(PREF_MOBILE, str3);
        this.mOlaBalance = i;
        edit.putInt(PREF_OLA_MONEY_BALANCE, i);
        this.mUserLoginEmail = str4;
        this.mUserPrimaryEmail = str4;
        edit.putString(PREF_EMAIL, str4);
        edit.putString(USER_EMAIL_KEY, str4);
        this.mIsLoggedIn = z;
        edit.putBoolean(PREF_LOGGED_IN, z);
        this.mName = str5;
        edit.putString(PREF_NAME_ENTERED, str5);
        edit.apply();
    }

    public void updateUserInfoOnSignUp(String str, String str2, String str3, String str4, String str5, String str6, boolean z) {
        Editor edit = this.mPref.edit();
        this.mUserId = str;
        edit.putString(PREF_USER_ID, str);
        this.mClearUserId = Utils.m14922f(this.mUserId);
        this.mRefCode = str2;
        edit.putString(PREF_REFERRAL_CODE, str2);
        this.mName = str3;
        edit.putString(PREF_NAME_ENTERED, str3);
        this.mPhoneNumber = str4;
        edit.putString(PREF_MOBILE, this.mPhoneNumber);
        this.mUserLoginEmail = str5;
        this.mUserPrimaryEmail = str5;
        edit.putString(PREF_EMAIL, str5);
        edit.putString(USER_EMAIL_KEY, str5);
        this.mCity = str6;
        edit.putString(USER_CITY_KEY, str6);
        this.mIsLoggedIn = true;
        edit.putBoolean(PREF_LOGGED_IN, true);
        this.mVerified = z;
        edit.putBoolean(USER_IS_VERIFIED_KEY, z);
        edit.apply();
    }

    public void storeReferralUrls(Map<String, String> map) {
        Editor edit = this.mPref.edit();
        if (map != null) {
            for (Entry entry : map.entrySet()) {
                edit.putString((String) entry.getKey(), (String) entry.getValue());
            }
        }
        edit.apply();
    }

    public void updateUserInfoOnAutoLogin(String str, String str2, int i, String str3, boolean z, String str4, boolean z2) {
        Editor edit = this.mPref.edit();
        this.mRefCode = str;
        edit.putString(PREF_REFERRAL_CODE, str);
        this.mPhoneNumber = str2;
        edit.putString(PREF_MOBILE, str2);
        this.mOlaBalance = i;
        edit.putInt(PREF_OLA_MONEY_BALANCE, i);
        this.mUserLoginEmail = str3;
        edit.putString(PREF_EMAIL, str3);
        edit.putString(USER_EMAIL_KEY, str3);
        this.mIsLoggedIn = z;
        edit.putBoolean(PREF_LOGGED_IN, z);
        this.mName = str4;
        edit.putString(PREF_NAME_ENTERED, str4);
        this.mVerified = z2;
        edit.putBoolean(USER_IS_VERIFIED_KEY, z2);
        edit.apply();
    }

    public void setVerified(boolean z) {
        this.mVerified = z;
    }

    public boolean isVerified() {
        return this.mVerified;
    }

    public void setReferralSchemeForUser(boolean z, int i, int i2) {
        Editor edit = this.mPref.edit();
        edit.putBoolean(Constants.PREF_IS_SCHEME_ON, z);
        edit.putInt(Constants.PREF_REFERRER_EARNS, i2);
        edit.putInt(Constants.PREF_REFERRED_EARNS, i);
        edit.apply();
    }

    private String getFirstWord(String str) {
        if (!Utils.m14924g(str)) {
            return Trace.NULL;
        }
        String trim = str.trim();
        int indexOf = trim.indexOf(" ");
        if (indexOf != -1) {
            return trim.substring(0, indexOf);
        }
        return trim;
    }
}
