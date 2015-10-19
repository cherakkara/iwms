package com.localytics.android;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Looper;
import android.os.Message;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.newrelic.agent.android.analytics.AnalyticAttribute;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import com.newrelic.agent.android.instrumentation.SQLiteInstrumentation;
import com.newrelic.agent.android.instrumentation.Trace;
import com.olacabs.customer.p076d.AppInfo;
import com.olacabs.customer.p076d.da;
import com.olacabs.customer.utils.Constants;
import java.net.URLDecoder;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TimeZone;
import java.util.TreeMap;
import java.util.UUID;
import java.util.concurrent.Callable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class AnalyticsHandler extends BaseHandler {
    private static final int MESSAGE_CLOSE = 102;
    private static final int MESSAGE_DISABLE_PUSH = 112;
    private static final int MESSAGE_HANDLE_PUSH_REGISTRATION = 110;
    private static final int MESSAGE_OPEN = 101;
    private static final int MESSAGE_OPT_OUT = 108;
    private static final int MESSAGE_REGISTER_PUSH = 109;
    private static final int MESSAGE_SET_CUSTOM_DIMENSION = 107;
    private static final int MESSAGE_SET_IDENTIFIER = 105;
    private static final int MESSAGE_SET_LOCATION = 106;
    private static final int MESSAGE_SET_PUSH_REGID = 113;
    private static final int MESSAGE_SET_REFERRERID = 114;
    private static final int MESSAGE_TAG_EVENT = 103;
    private static final int MESSAGE_TAG_SCREEN = 104;
    private static final String PARAM_LOCALYTICS_REFERRER_TEST_MODE = "localytics_test_mode";
    private static final String[] PROJECTION_SET_CUSTOM_DIMENSION;
    private static final String[] PROJECTION_SET_IDENTIFIER;
    private static final String SELECTION_SET_CUSTOM_DIMENSION;
    private static final String SELECTION_SET_IDENTIFIER;
    private static Location sLastLocation;
    private boolean mAppWasUpgraded;
    boolean mFirstSessionEver;
    boolean mReferrerTestModeEnabled;
    boolean mSentReferrerTestMode;
    private final List<String> screenFlow;

    /* renamed from: com.localytics.android.AnalyticsHandler.10 */
    class AnonymousClass10 implements Runnable {
        final /* synthetic */ int val$disabled;

        AnonymousClass10(int i) {
            this.val$disabled = i;
        }

        public void run() {
            AnalyticsHandler.this._setPushDisabled(this.val$disabled);
        }
    }

    /* renamed from: com.localytics.android.AnalyticsHandler.11 */
    class AnonymousClass11 implements Runnable {
        final /* synthetic */ boolean val$isOptingOut;

        AnonymousClass11(boolean z) {
            this.val$isOptingOut = z;
        }

        public void run() {
            AnalyticsHandler.this._setOptedOut(this.val$isOptingOut);
        }
    }

    /* renamed from: com.localytics.android.AnalyticsHandler.12 */
    class AnonymousClass12 implements Runnable {
        final /* synthetic */ String val$newSenderId;

        AnonymousClass12(String str) {
            this.val$newSenderId = str;
        }

        public void run() {
            AnalyticsHandler.this._registerPush(this.val$newSenderId);
        }
    }

    /* renamed from: com.localytics.android.AnalyticsHandler.13 */
    class AnonymousClass13 implements Runnable {
        final /* synthetic */ Intent val$intent;

        AnonymousClass13(Intent intent) {
            this.val$intent = intent;
        }

        public void run() {
            AnalyticsHandler.this._handlePushRegistration(this.val$intent);
        }
    }

    /* renamed from: com.localytics.android.AnalyticsHandler.14 */
    class AnonymousClass14 implements Runnable {
        final /* synthetic */ String val$referrerId;

        AnonymousClass14(String str) {
            this.val$referrerId = str;
        }

        public void run() {
            AnalyticsHandler.this._setReferrerId(this.val$referrerId);
        }
    }

    /* renamed from: com.localytics.android.AnalyticsHandler.15 */
    class AnonymousClass15 implements Callable<String> {
        final /* synthetic */ int val$dimension;

        AnonymousClass15(int i) {
            this.val$dimension = i;
        }

        public String call() {
            return AnalyticsHandler.this._getCustomDimension(this.val$dimension);
        }
    }

    /* renamed from: com.localytics.android.AnalyticsHandler.17 */
    class AnonymousClass17 implements Callable<String> {
        final /* synthetic */ String val$key;

        AnonymousClass17(String str) {
            this.val$key = str;
        }

        public String call() {
            Throwable th;
            String str = null;
            Cursor query;
            try {
                query = AnalyticsHandler.this.mProvider.query("identifiers", AnalyticsHandler.PROJECTION_SET_IDENTIFIER, AnalyticsHandler.SELECTION_SET_IDENTIFIER, new String[]{this.val$key}, null);
                try {
                    if (query.moveToFirst()) {
                        str = query.getString(query.getColumnIndexOrThrow("value"));
                    }
                    if (query != null) {
                        query.close();
                    }
                    return str;
                } catch (Throwable th2) {
                    th = th2;
                    if (query != null) {
                        query.close();
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                query = null;
                if (query != null) {
                    query.close();
                }
                throw th;
            }
        }
    }

    /* renamed from: com.localytics.android.AnalyticsHandler.1 */
    class C06771 implements Callable<Boolean> {
        C06771() {
        }

        public Boolean call() {
            return Boolean.valueOf(AnalyticsHandler.this._isPushDisabled());
        }
    }

    /* renamed from: com.localytics.android.AnalyticsHandler.2 */
    class C06782 implements Callable<Map> {
        C06782() {
        }

        public Map call() {
            Map hashMap = new HashMap();
            Cursor query;
            try {
                query = AnalyticsHandler.this.mProvider.query("identifiers", null, null, null, null);
                while (query.moveToNext()) {
                    try {
                        hashMap.put(query.getString(query.getColumnIndexOrThrow("key")), query.getString(query.getColumnIndexOrThrow("value")));
                    } catch (Throwable th) {
                    }
                }
                if (query != null) {
                    query.close();
                }
                return hashMap;
            } catch (Throwable th2) {
                query = null;
                if (query != null) {
                    query.close();
                }
                return hashMap;
            }
        }
    }

    /* renamed from: com.localytics.android.AnalyticsHandler.3 */
    class C06793 implements Runnable {
        C06793() {
        }

        public void run() {
            if (AnalyticsHandler.this._isOptedOut()) {
                Log.m12793d("Data collection is opted out");
            } else {
                AnalyticsHandler.this._open();
            }
        }
    }

    /* renamed from: com.localytics.android.AnalyticsHandler.4 */
    class C06804 implements Runnable {
        C06804() {
        }

        public void run() {
            if (AnalyticsHandler.this._isOptedOut()) {
                Log.m12793d("Data collection is opted out");
            } else {
                AnalyticsHandler.this._close();
            }
        }
    }

    /* renamed from: com.localytics.android.AnalyticsHandler.5 */
    class C06815 implements Runnable {
        final /* synthetic */ Map val$attributes;
        final /* synthetic */ Long val$clv;
        final /* synthetic */ String val$event;

        C06815(String str, Map map, Long l) {
            this.val$event = str;
            this.val$attributes = map;
            this.val$clv = l;
        }

        public void run() {
            if (AnalyticsHandler.this._isOptedOut()) {
                Log.m12793d("Data collection is opted out");
            } else {
                AnalyticsHandler.this._tagEvent(this.val$event, this.val$attributes, this.val$clv);
            }
        }
    }

    /* renamed from: com.localytics.android.AnalyticsHandler.6 */
    class C06826 implements Runnable {
        final /* synthetic */ String val$screen;

        C06826(String str) {
            this.val$screen = str;
        }

        public void run() {
            if (AnalyticsHandler.this._isOptedOut()) {
                Log.m12793d("Data collection is opted out");
            } else {
                AnalyticsHandler.this._tagScreen(this.val$screen);
            }
        }
    }

    /* renamed from: com.localytics.android.AnalyticsHandler.7 */
    class C06837 implements Runnable {
        final /* synthetic */ int val$dimension;
        final /* synthetic */ String val$value;

        C06837(int i, String str) {
            this.val$dimension = i;
            this.val$value = str;
        }

        public void run() {
            AnalyticsHandler.this._setCustomDimension(this.val$dimension, this.val$value);
        }
    }

    /* renamed from: com.localytics.android.AnalyticsHandler.8 */
    class C06848 implements Runnable {
        final /* synthetic */ String val$key;
        final /* synthetic */ String val$value;

        C06848(String str, String str2) {
            this.val$key = str;
            this.val$value = str2;
        }

        public void run() {
            AnalyticsHandler.this._setIdentifier(this.val$key, this.val$value);
        }
    }

    /* renamed from: com.localytics.android.AnalyticsHandler.9 */
    class C06859 implements Runnable {
        final /* synthetic */ String val$pushRegId;

        C06859(String str) {
            this.val$pushRegId = str;
        }

        public void run() {
            if (AnalyticsHandler.this._isPushDisabled()) {
                Log.m12799v("Registering for GCM but push disabled");
            } else {
                AnalyticsHandler.this._setPushID(this.val$pushRegId);
            }
        }
    }

    private final class AnalyticsListenersSet extends ListenersSet implements AnalyticsListener {
        private AnalyticsListenersSet() {
            super();
        }

        public synchronized void localyticsSessionWillOpen(boolean z, boolean z2, boolean z3) {
            callListeners("localyticsSessionWillOpen", new Class[]{Boolean.TYPE, Boolean.TYPE, Boolean.TYPE}, new Object[]{Boolean.valueOf(z), Boolean.valueOf(z2), Boolean.valueOf(z3)});
        }

        public synchronized void localyticsSessionDidOpen(boolean z, boolean z2, boolean z3) {
            callListeners("localyticsSessionDidOpen", new Class[]{Boolean.TYPE, Boolean.TYPE, Boolean.TYPE}, new Object[]{Boolean.valueOf(z), Boolean.valueOf(z2), Boolean.valueOf(z3)});
        }

        public synchronized void localyticsSessionWillClose() {
            callListeners("localyticsSessionWillClose", null, null);
        }

        public synchronized void localyticsDidTagEvent(String str, Map<String, String> map, long j) {
            callListeners("localyticsDidTagEvent", new Class[]{String.class, Map.class, Long.TYPE}, new Object[]{str, map, Long.valueOf(j)});
        }
    }

    static {
        PROJECTION_SET_CUSTOM_DIMENSION = new String[]{"custom_dimension_value"};
        SELECTION_SET_CUSTOM_DIMENSION = String.format("%s = ?", new Object[]{"custom_dimension_key"});
        PROJECTION_SET_IDENTIFIER = new String[]{"key", "value"};
        SELECTION_SET_IDENTIFIER = String.format("%s = ?", new Object[]{"key"});
        sLastLocation = null;
    }

    boolean isPushDisabled() {
        return getBool(new C06771());
    }

    void setScreenFlow(List<String> list) {
        this.screenFlow.addAll(0, list);
    }

    AnalyticsHandler(LocalyticsDao localyticsDao, Looper looper) {
        super(localyticsDao, looper);
        this.screenFlow = new ArrayList();
        this.mAppWasUpgraded = false;
        this.mFirstSessionEver = false;
        this.mReferrerTestModeEnabled = false;
        this.mSentReferrerTestMode = false;
        this.siloName = "Analytics";
        this.listeners = new AnalyticsListenersSet();
        queueMessage(obtainMessage(1));
    }

    Map<String, String> getIdentifiers() {
        return getMap(new C06782());
    }

    void tagEvent(String str, Map<String, String> map, long j) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("event cannot be null or empty");
        }
        if (map != null) {
            if (map.isEmpty()) {
                LocalyticsManager.throwOrLogError(IllegalArgumentException.class, "attributes is empty.  Did the caller make an error?");
            }
            if (map.size() > 50) {
                LocalyticsManager.throwOrLogError(IllegalArgumentException.class, String.format("attributes size is %d, exceeding the maximum size of %d.  Did the caller make an error?", new Object[]{Integer.valueOf(map.size()), Integer.valueOf(50)}));
            }
            for (Entry entry : map.entrySet()) {
                String str2 = (String) entry.getKey();
                String str3 = (String) entry.getValue();
                if (TextUtils.isEmpty(str2)) {
                    LocalyticsManager.throwOrLogError(IllegalArgumentException.class, "attributes cannot contain null or empty keys");
                }
                if (TextUtils.isEmpty(str3)) {
                    LocalyticsManager.throwOrLogError(IllegalArgumentException.class, "attributes cannot contain null or empty values");
                }
            }
        }
        queueMessage(obtainMessage(MESSAGE_TAG_EVENT, new Object[]{str, map, Long.valueOf(j)}));
    }

    protected void handleMessageExtended(Message message) throws Exception {
        Object[] objArr;
        switch (message.what) {
            case MESSAGE_OPEN /*101*/:
                Log.m12793d("Analytics handler received MESSAGE_OPEN");
                this.mProvider.runBatchTransaction(new C06793());
            case MESSAGE_CLOSE /*102*/:
                Log.m12793d("Analytics handler received MESSAGE_CLOSE");
                this.mProvider.runBatchTransaction(new C06804());
            case MESSAGE_TAG_EVENT /*103*/:
                Log.m12793d("Analytics handler received MESSAGE_TAG_EVENT");
                objArr = (Object[]) message.obj;
                this.mProvider.runBatchTransaction(new C06815((String) objArr[0], (Map) objArr[1], (Long) objArr[2]));
            case MESSAGE_TAG_SCREEN /*104*/:
                Log.m12793d("Analytics handler received MESSAGE_TAG_SCREEN");
                this.mProvider.runBatchTransaction(new C06826((String) message.obj));
            case MESSAGE_SET_IDENTIFIER /*105*/:
                Log.m12793d("Analytics handler received MESSAGE_SET_IDENTIFIER");
                objArr = (Object[]) message.obj;
                this.mProvider.runBatchTransaction(new C06848((String) objArr[0], (String) objArr[1]));
            case MESSAGE_SET_LOCATION /*106*/:
                Log.m12793d("Analytics handler received MESSAGE_SET_LOCATION");
                sLastLocation = (Location) message.obj;
            case MESSAGE_SET_CUSTOM_DIMENSION /*107*/:
                Log.m12793d("Analytics handler received MESSAGE_SET_CUSTOM_DIMENSION");
                objArr = (Object[]) message.obj;
                this.mProvider.runBatchTransaction(new C06837(((Integer) objArr[0]).intValue(), (String) objArr[1]));
            case MESSAGE_OPT_OUT /*108*/:
                boolean z;
                Log.m12799v("Analytics handler received MESSAGE_OPT_OUT");
                if (message.arg1 != 0) {
                    z = true;
                } else {
                    z = false;
                }
                this.mProvider.runBatchTransaction(new AnonymousClass11(z));
            case MESSAGE_REGISTER_PUSH /*109*/:
                Log.m12793d("Analytics handler received MESSAGE_REGISTER_PUSH");
                this.mProvider.runBatchTransaction(new AnonymousClass12((String) message.obj));
            case MESSAGE_HANDLE_PUSH_REGISTRATION /*110*/:
                Log.m12793d("Analytics handler received MESSAGE_HANDLE_PUSH_REGISTRATION");
                this.mProvider.runBatchTransaction(new AnonymousClass13((Intent) message.obj));
            case MESSAGE_DISABLE_PUSH /*112*/:
                Log.m12793d("Analytics handler received MESSAGE_DISABLE_PUSH");
                this.mProvider.runBatchTransaction(new AnonymousClass10(message.arg1));
            case MESSAGE_SET_PUSH_REGID /*113*/:
                Log.m12793d("Analytics handler received MESSAGE_SET_PUSH_REGID");
                this.mProvider.runBatchTransaction(new C06859((String) message.obj));
            case MESSAGE_SET_REFERRERID /*114*/:
                Log.m12793d("Analytics handler received MESSAGE_SET_REFERRERID");
                this.mProvider.runBatchTransaction(new AnonymousClass14((String) message.obj));
            default:
                super.handleMessageExtended(message);
        }
    }

    protected void _deleteUploadedData(int i) {
        this.mProvider.remove("events", "_id <= " + i, null);
    }

    private void _setPushDisabled(int i) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("push_disabled", Integer.valueOf(i));
        BaseProvider baseProvider = this.mProvider;
        String str = "info";
        if (baseProvider instanceof SQLiteDatabase) {
            SQLiteInstrumentation.update((SQLiteDatabase) baseProvider, str, contentValues, null, null);
        } else {
            baseProvider.update(str, contentValues, null, null);
        }
    }

    private void _handlePushRegistration(Intent intent) {
        String stringExtra = intent.getStringExtra(da.GCM_REG_ID_KEY);
        if (_isPushDisabled()) {
            Log.m12799v("GCM registered but push disabled: removing id");
            _setPushID(null);
        } else if (intent.getStringExtra("error") != null) {
            Log.m12799v("GCM registration failed");
        } else if (intent.getStringExtra("unregistered") != null) {
            Log.m12799v("GCM unregistered: removing id");
            _setPushID(null);
        } else if (stringExtra != null) {
            Log.m12799v(String.format("GCM registered, new id: %s", new Object[]{stringExtra}));
            _setPushID(stringExtra);
        }
    }

    protected void _onUploadCompleted(String str) {
        this.mProvider.vacuumIfNecessary();
    }

    protected int _getMaxRowToUpload() {
        Cursor cursor;
        try {
            Cursor query = this.mProvider.query("events", new String[]{"_id"}, null, null, "_id ASC");
            try {
                int i;
                if (query.moveToLast()) {
                    i = query.getInt(query.getColumnIndexOrThrow("_id"));
                } else {
                    i = 0;
                }
                if (query == null) {
                    return i;
                }
                query.close();
                return i;
            } catch (Throwable th) {
                cursor = query;
                if (cursor != null) {
                    cursor.close();
                }
                return 0;
            }
        } catch (Throwable th2) {
            cursor = null;
            if (cursor != null) {
                cursor.close();
            }
            return 0;
        }
    }

    protected TreeMap<Integer, Object> _getDataToUpload() {
        TreeMap<Integer, Object> treeMap = new TreeMap();
        Cursor query;
        try {
            query = this.mProvider.query("events", null, null, null, "_id ASC");
            while (query.moveToNext() && treeMap.size() < 100) {
                try {
                    int i = query.getInt(query.getColumnIndexOrThrow("_id"));
                    treeMap.put(Integer.valueOf(i), query.getString(query.getColumnIndexOrThrow("blob")));
                } catch (Throwable th) {
                }
            }
            if (query != null) {
                query.close();
            }
            return treeMap;
        } catch (Throwable th2) {
            query = null;
            if (query != null) {
                query.close();
            }
            return treeMap;
        }
    }

    protected BaseUploadThread getUploadThread(TreeMap<Integer, Object> treeMap, String str) {
        return new AnalyticsUploadHandler(this, treeMap, str, this.mLocalyticsDao);
    }

    private void _registerPush(String str) {
        Throwable th;
        Cursor cursor = null;
        if (_isPushDisabled()) {
            Log.m12799v("Registering for GCM but push disabled");
            return;
        }
        try {
            Cursor query = this.mProvider.query("info", new String[]{"sender_id", "registration_version", da.GCM_REG_ID_KEY}, null, null, null);
            try {
                Object string;
                CharSequence string2;
                Object obj;
                if (query.moveToFirst()) {
                    string = query.getString(query.getColumnIndexOrThrow("sender_id"));
                    String string3 = query.getString(query.getColumnIndexOrThrow("registration_version"));
                    string2 = query.getString(query.getColumnIndexOrThrow(da.GCM_REG_ID_KEY));
                    obj = string3;
                } else {
                    obj = null;
                    string2 = null;
                    string = null;
                }
                if (query != null) {
                    query.close();
                }
                if (!str.equals(string)) {
                    _setPushID(str, null);
                    string2 = null;
                }
                String appVersion = DatapointHelper.getAppVersion(this.mLocalyticsDao.getAppContext());
                if (TextUtils.isEmpty(string2) || !appVersion.equals(r2)) {
                    Object tryInvokeStatic = ReflectionUtils.tryInvokeStatic("com.google.android.gms.gcm.GoogleCloudMessaging", "getInstance", new Class[]{Context.class}, new Object[]{this.mLocalyticsDao.getAppContext()});
                    if (tryInvokeStatic != null) {
                        PushReceiver.retrySenderId = str;
                        Class[] clsArr = new Class[]{String[].class};
                        Object[] objArr = new Object[1];
                        objArr[0] = new String[]{str};
                        ReflectionUtils.tryInvokeInstance(tryInvokeStatic, "register", clsArr, objArr);
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                cursor = query;
                if (cursor != null) {
                    cursor.close();
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    private boolean _isPushDisabled() {
        Cursor query;
        boolean z;
        try {
            query = this.mProvider.query("info", new String[]{"push_disabled"}, null, null, null);
            z = false;
            while (query.moveToNext()) {
                try {
                    z = query.getInt(query.getColumnIndexOrThrow("push_disabled")) == 1;
                } catch (Throwable th) {
                }
            }
            if (query != null) {
                query.close();
            }
        } catch (Throwable th2) {
            query = null;
            z = false;
            if (query != null) {
                query.close();
            }
            return z;
        }
        return z;
    }

    private void _tagEvent(String str) {
        _tagEvent(str, null, null);
    }

    String getCustomDimension(int i) {
        return getString(new AnonymousClass15(i));
    }

    private String _getCustomDimension(int i) {
        Throwable th;
        Cursor cursor = null;
        if (i < 0 || i > 10) {
            return null;
        }
        String customDimensionAttributeKey = getCustomDimensionAttributeKey(i);
        try {
            Cursor query = this.mProvider.query("custom_dimensions", PROJECTION_SET_CUSTOM_DIMENSION, SELECTION_SET_CUSTOM_DIMENSION, new String[]{customDimensionAttributeKey}, null);
            try {
                String string;
                if (query.moveToFirst()) {
                    string = query.getString(query.getColumnIndexOrThrow("custom_dimension_value"));
                } else {
                    string = null;
                }
                if (query == null) {
                    return string;
                }
                query.close();
                return string;
            } catch (Throwable th2) {
                th = th2;
                cursor = query;
                if (cursor != null) {
                    cursor.close();
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    private String getCustomDimensionAttributeKey(int i) {
        if (i < 0 || i > 10) {
            return (String) LocalyticsManager.throwOrLogError(IndexOutOfBoundsException.class, "Custom dimension index cannot exceed " + String.valueOf(10));
        }
        return String.format("%s%s", new Object[]{"custom_dimension_", String.valueOf(i)});
    }

    String getPushRegistrationID() {
        return getString(new Callable<String>() {
            public String call() {
                Cursor cursor;
                try {
                    Cursor query = AnalyticsHandler.this.mProvider.query("info", new String[]{da.GCM_REG_ID_KEY}, null, null, null);
                    try {
                        String string;
                        if (query.moveToFirst()) {
                            string = query.getString(query.getColumnIndexOrThrow(da.GCM_REG_ID_KEY));
                        } else {
                            string = null;
                        }
                        if (query == null) {
                            return string;
                        }
                        query.close();
                        return string;
                    } catch (Throwable th) {
                        cursor = query;
                        if (cursor != null) {
                            cursor.close();
                        }
                        return null;
                    }
                } catch (Throwable th2) {
                    cursor = null;
                    if (cursor != null) {
                        cursor.close();
                    }
                    return null;
                }
            }
        });
    }

    void setPushRegistrationId(String str) {
        queueMessage(obtainMessage(MESSAGE_SET_PUSH_REGID, str));
    }

    private void _tagEvent(String str, Map<String, String> map, Long l) {
        Cursor query;
        Throwable th;
        try {
            String str2 = Trace.NULL;
            String str3 = Trace.NULL;
            String str4 = Trace.NULL;
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("dt", "h");
            jSONObject.put("u", UUID.randomUUID().toString());
            Context appContext = this.mLocalyticsDao.getAppContext();
            TelephonyManager telephonyManager = (TelephonyManager) appContext.getSystemService(Constants.PHONE);
            JSONObject jSONObject2 = new JSONObject();
            try {
                query = this.mProvider.query("info", null, null, null, null);
                try {
                    int i;
                    String string;
                    String string2;
                    String string3;
                    String androidIdHashOrNull;
                    String str5;
                    long j;
                    int i2;
                    Object obj;
                    if (query.moveToFirst()) {
                        jSONObject.put("pa", Math.round((float) (query.getLong(query.getColumnIndexOrThrow("created_time")) / 1000)));
                        int i3 = query.getInt(query.getColumnIndexOrThrow("next_header_number"));
                        i = query.getInt(query.getColumnIndexOrThrow("next_session_number"));
                        string = query.getString(query.getColumnIndexOrThrow("customer_id"));
                        string2 = query.getString(query.getColumnIndexOrThrow("user_type"));
                        string3 = query.getString(query.getColumnIndexOrThrow("current_session_uuid"));
                        jSONObject.put("seq", i3);
                        jSONObject2.put("dt", "a");
                        jSONObject2.put("au", this.mLocalyticsDao.getApiKey());
                        androidIdHashOrNull = DatapointHelper.getAndroidIdHashOrNull(this.mLocalyticsDao.getAppContext());
                        if (androidIdHashOrNull != null) {
                            jSONObject2.put("du", androidIdHashOrNull);
                        }
                        jSONObject2.put("lv", "androida_3.4.0");
                        jSONObject2.put("av", DatapointHelper.getAppVersion(this.mLocalyticsDao.getAppContext()));
                        jSONObject2.put("dp", "Android");
                        jSONObject2.put("dll", Locale.getDefault().getLanguage());
                        jSONObject2.put("dlc", Locale.getDefault().getCountry());
                        jSONObject2.put("nc", telephonyManager.getNetworkCountryIso());
                        jSONObject2.put("dc", telephonyManager.getSimCountryIso());
                        jSONObject2.put("dma", DatapointHelper.getManufacturer());
                        jSONObject2.put("dmo", Build.MODEL);
                        jSONObject2.put("dov", VERSION.RELEASE);
                        jSONObject2.put("nca", telephonyManager.getNetworkOperatorName());
                        jSONObject2.put("dac", DatapointHelper.getNetworkType(telephonyManager, this.mLocalyticsDao.getAppContext()));
                        jSONObject2.put("iu", this.mLocalyticsDao.getInstallationId());
                        androidIdHashOrNull = query.getString(query.getColumnIndexOrThrow("fb_attribution"));
                        if (androidIdHashOrNull != null) {
                            jSONObject2.put("fbat", androidIdHashOrNull);
                        }
                        androidIdHashOrNull = query.getString(query.getColumnIndexOrThrow(da.GCM_REG_ID_KEY));
                        if (androidIdHashOrNull != null) {
                            jSONObject2.put("push", androidIdHashOrNull);
                        }
                        androidIdHashOrNull = query.getString(query.getColumnIndexOrThrow("first_android_id"));
                        if (androidIdHashOrNull != null) {
                            jSONObject2.put("aid", androidIdHashOrNull);
                        }
                        Object androidIdOrNull = DatapointHelper.getAndroidIdOrNull(this.mLocalyticsDao.getAppContext());
                        str5 = "caid";
                        if (androidIdOrNull == null) {
                            androidIdOrNull = JSONObject.NULL;
                        }
                        jSONObject2.put(str5, androidIdOrNull);
                        AdvertisingInfo advertisingInfo = DatapointHelper.getAdvertisingInfo(this.mLocalyticsDao.getAppContext());
                        str5 = "lad";
                        boolean z = advertisingInfo != null && advertisingInfo.limitAdTracking;
                        jSONObject2.put(str5, z);
                        androidIdHashOrNull = query.getString(query.getColumnIndexOrThrow("first_advertising_id"));
                        if (androidIdHashOrNull != null) {
                            jSONObject2.put("gadid", androidIdHashOrNull);
                        }
                        if (!(advertisingInfo == null || advertisingInfo.id == null)) {
                            jSONObject2.put("gcadid", advertisingInfo.id);
                        }
                        TimeZone timeZone = TimeZone.getDefault();
                        jSONObject2.put("tz", ((long) (timeZone.inDaylightTime(Calendar.getInstance(timeZone).getTime()) ? timeZone.getDSTSavings() + timeZone.getRawOffset() : timeZone.getRawOffset())) / 1000);
                        androidIdHashOrNull = query.getString(query.getColumnIndexOrThrow("package_name"));
                        if (androidIdHashOrNull != null) {
                            jSONObject2.put("pkg", androidIdHashOrNull);
                        }
                        androidIdOrNull = DatapointHelper.getSerialNumberHashOrNull();
                        str5 = "dms";
                        if (androidIdOrNull == null) {
                            androidIdOrNull = JSONObject.NULL;
                        }
                        jSONObject2.put(str5, androidIdOrNull);
                        jSONObject2.put("dsdk", Integer.valueOf(Constants.CURRENT_API_LEVEL));
                        String string4 = query.getString(query.getColumnIndexOrThrow("play_attribution"));
                        if (string4 != null) {
                            jSONObject2.put("aurl", string4);
                        }
                        j = query.getLong(query.getColumnIndexOrThrow("last_session_open_time"));
                        _updateHeaderForTestModeAttribution(string4, jSONObject2, advertisingInfo, false);
                        i2 = i;
                        i = i3;
                        String str6 = string3;
                        string3 = string;
                        string = string2;
                        obj = str6;
                    } else {
                        j = 0;
                        string = str4;
                        string3 = str3;
                        string2 = str2;
                        i2 = 0;
                        i = 0;
                    }
                    if (query != null) {
                        query.close();
                    }
                    jSONObject.put("attrs", jSONObject2);
                    JSONObject _getIdentifiers = _getIdentifiers();
                    if (_getIdentifiers.length() > 0) {
                        jSONObject.put("ids", _getIdentifiers);
                    }
                    ContentValues contentValues = new ContentValues();
                    ContentValues contentValues2 = new ContentValues();
                    contentValues.put("next_header_number", Integer.valueOf(i + 1));
                    JSONObject jSONObject3 = new JSONObject();
                    long currentTimeMillis = System.currentTimeMillis();
                    String uuid = UUID.randomUUID().toString();
                    if (Constants.PUSH_ACK_TYPE_OPEN.equals(str)) {
                        jSONObject3.put("dt", "s");
                        jSONObject3.put("ct", Math.round((double) (currentTimeMillis / 1000)));
                        jSONObject3.put("u", uuid);
                        long j2 = currentTimeMillis - j;
                        if (j == 0) {
                            jSONObject3.put("sl", 0.0d);
                        } else if (j2 > 0) {
                            jSONObject3.put("sl", Math.round((double) (j2 / 1000)));
                        }
                        jSONObject3.put("nth", i2);
                        _addLocationIDsAndCustomDimensions(jSONObject3, _getIdentifiers, string3, string);
                        androidIdHashOrNull = "%s\n%s";
                        Object[] objArr = new Object[2];
                        if (jSONObject instanceof JSONObject) {
                            string2 = JSONObjectInstrumentation.toString(jSONObject);
                        } else {
                            string2 = jSONObject.toString();
                        }
                        objArr[0] = string2;
                        if (jSONObject3 instanceof JSONObject) {
                            string2 = JSONObjectInstrumentation.toString(jSONObject3);
                        } else {
                            string2 = jSONObject3.toString();
                        }
                        objArr[1] = string2;
                        string2 = String.format(androidIdHashOrNull, objArr);
                        contentValues2.put("blob", string2);
                        contentValues2.put("upload_format", Integer.valueOf(UploadFormat.V2.getValue()));
                        contentValues.put("last_session_open_time", Long.valueOf(currentTimeMillis));
                        contentValues.put("next_session_number", Integer.valueOf(i2 + 1));
                        contentValues.put("current_session_uuid", uuid);
                        if (this.mFirstSessionEver) {
                            contentValues.put("first_open_event_blob", string2);
                        }
                    } else if ("close".equals(str)) {
                        jSONObject3.put("dt", "c");
                        jSONObject3.put("u", uuid);
                        jSONObject3.put("su", obj);
                        jSONObject3.put("ss", Math.round((double) (j / 1000)));
                        jSONObject3.put("ct", Math.round((double) (currentTimeMillis / 1000)));
                        jSONObject3.put("ctl", Math.round((double) ((currentTimeMillis - j) / 1000)));
                        JSONArray jSONArray = new JSONArray(this.screenFlow);
                        if (jSONArray.length() > 0) {
                            jSONObject3.put("fl", jSONArray);
                        }
                        _addLocationIDsAndCustomDimensions(jSONObject3, _getIdentifiers, string3, string);
                        contentValues.put("last_session_close_time", Long.valueOf(currentTimeMillis));
                        androidIdHashOrNull = "queued_close_session_blob";
                        str5 = "%s\n%s";
                        r6 = new Object[2];
                        r6[0] = !(jSONObject instanceof JSONObject) ? jSONObject.toString() : JSONObjectInstrumentation.toString(jSONObject);
                        r6[1] = !(jSONObject3 instanceof JSONObject) ? jSONObject3.toString() : JSONObjectInstrumentation.toString(jSONObject3);
                        contentValues.put(androidIdHashOrNull, String.format(str5, r6));
                        contentValues.put("queued_close_session_blob_upload_format", Integer.valueOf(UploadFormat.V2.getValue()));
                    } else if ("opt_in".equals(str) || "opt_out".equals(str)) {
                        jSONObject3.put("dt", "o");
                        jSONObject3.put("u", this.mLocalyticsDao.getApiKey());
                        jSONObject3.put("out", "opt_out".equals(str) ? Boolean.TRUE.toString() : Boolean.FALSE.toString());
                        jSONObject3.put("ct", Math.round((double) (currentTimeMillis / 1000)));
                        androidIdHashOrNull = "blob";
                        str5 = "%s\n%s";
                        r6 = new Object[2];
                        r6[0] = !(jSONObject instanceof JSONObject) ? jSONObject.toString() : JSONObjectInstrumentation.toString(jSONObject);
                        r6[1] = !(jSONObject3 instanceof JSONObject) ? jSONObject3.toString() : JSONObjectInstrumentation.toString(jSONObject3);
                        contentValues2.put(androidIdHashOrNull, String.format(str5, r6));
                        contentValues2.put("upload_format", Integer.valueOf(UploadFormat.V2.getValue()));
                    } else {
                        jSONObject3.put("dt", com.apsalar.sdk.Constants.API_PREFIX);
                        jSONObject3.put("ct", Math.round((double) (currentTimeMillis / 1000)));
                        jSONObject3.put("u", uuid);
                        androidIdHashOrNull = "su";
                        if (!_isSessionOpen()) {
                            obj = Trace.NULL;
                        }
                        jSONObject3.put(androidIdHashOrNull, obj);
                        if (str.startsWith(appContext.getPackageName())) {
                            jSONObject3.put("n", str.substring(appContext.getPackageName().length() + 1, str.length()));
                        } else {
                            jSONObject3.put("n", str);
                        }
                        if (l.longValue() != 0) {
                            jSONObject3.put("v", l);
                        }
                        _addLocationIDsAndCustomDimensions(jSONObject3, _getIdentifiers, string3, string);
                        if (map != null) {
                            jSONObject3.put("attrs", new JSONObject(map));
                        }
                        androidIdHashOrNull = "blob";
                        str5 = "%s\n%s";
                        r6 = new Object[2];
                        r6[0] = !(jSONObject instanceof JSONObject) ? jSONObject.toString() : JSONObjectInstrumentation.toString(jSONObject);
                        r6[1] = !(jSONObject3 instanceof JSONObject) ? jSONObject3.toString() : JSONObjectInstrumentation.toString(jSONObject3);
                        contentValues2.put(androidIdHashOrNull, String.format(str5, r6));
                        contentValues2.put("upload_format", Integer.valueOf(UploadFormat.V2.getValue()));
                    }
                    BaseProvider baseProvider = this.mProvider;
                    String str7 = "info";
                    if (baseProvider instanceof SQLiteDatabase) {
                        SQLiteInstrumentation.update((SQLiteDatabase) baseProvider, str7, contentValues, null, null);
                    } else {
                        baseProvider.update(str7, contentValues, null, null);
                    }
                    if (contentValues2.size() > 0) {
                        this.mProvider.insert("events", contentValues2);
                    }
                    if (!Arrays.asList(new String[]{Constants.PUSH_ACK_TYPE_OPEN, "close", "opt_in", "opt_out"}).contains(str)) {
                        ((AnalyticsListener) this.listeners).localyticsDidTagEvent(str, map, l == null ? 0 : l.longValue());
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (query != null) {
                        query.close();
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                query = null;
                if (query != null) {
                    query.close();
                }
                throw th;
            }
        } catch (Exception e) {
        }
    }

    private void _updateHeaderForTestModeAttribution(String str, JSONObject jSONObject, AdvertisingInfo advertisingInfo, boolean z) {
        if (!this.mSentReferrerTestMode) {
            String toLowerCase;
            if (!TextUtils.isEmpty(str)) {
                for (String toLowerCase2 : URLDecoder.decode(str).split("[&]")) {
                    String[] split = toLowerCase2.split("[=]");
                    if (split.length > 1) {
                        String toLowerCase3 = split[0].toLowerCase();
                        toLowerCase2 = split[1].toLowerCase();
                        boolean z2 = toLowerCase3.equals(PARAM_LOCALYTICS_REFERRER_TEST_MODE) && (toLowerCase2.equals("1") || toLowerCase2.equals("true"));
                        this.mReferrerTestModeEnabled = z2;
                    }
                }
            }
            if ((z || this.mFirstSessionEver) && this.mReferrerTestModeEnabled) {
                try {
                    Log.m12797i("[REFERRAL] using fake id for attribution test mode");
                    toLowerCase2 = Long.toHexString(new SecureRandom().nextLong());
                    jSONObject.put("aid", toLowerCase2);
                    jSONObject.put("du", DatapointHelper.getSha256_buggy(toLowerCase2));
                    jSONObject.put("caid", toLowerCase2);
                    if (advertisingInfo != null) {
                        toLowerCase2 = UUID.randomUUID().toString();
                        jSONObject.put("gadid", toLowerCase2);
                        jSONObject.put("gcadid", toLowerCase2);
                    }
                    this.mSentReferrerTestMode = true;
                } catch (JSONException e) {
                    LocalyticsManager.throwOrLogError(JSONException.class, "Exception adding values to object");
                }
            }
        }
    }

    private void _addLocationIDsAndCustomDimensions(JSONObject jSONObject, JSONObject jSONObject2, String str, String str2) {
        Throwable th;
        try {
            if (sLastLocation != null) {
                double latitude = sLastLocation.getLatitude();
                double longitude = sLastLocation.getLongitude();
                if (!(latitude == 0.0d || longitude == 0.0d)) {
                    jSONObject.put(Constants.LAT, latitude);
                    jSONObject.put(Constants.LNG, longitude);
                }
            }
            jSONObject.put("cid", str);
            jSONObject.put("utp", str2);
            if (jSONObject2.length() > 0) {
                jSONObject.put("ids", jSONObject2);
            }
            Cursor query;
            try {
                query = this.mProvider.query("custom_dimensions", null, null, null, null);
                while (query.moveToNext()) {
                    try {
                        String string = query.getString(query.getColumnIndexOrThrow("custom_dimension_key"));
                        jSONObject.put(string.replace("custom_dimension_", "c"), query.getString(query.getColumnIndexOrThrow("custom_dimension_value")));
                    } catch (Throwable th2) {
                        th = th2;
                    }
                }
                if (query != null) {
                    query.close();
                }
            } catch (Throwable th3) {
                th = th3;
                query = null;
                if (query != null) {
                    query.close();
                }
                throw th;
            }
        } catch (JSONException e) {
        }
    }

    private void _tagScreen(String str) {
        if (!_isSessionOpen()) {
            Log.m12801w("Screen not tagged because a session is not open");
        } else if (this.screenFlow.size() == 0 || !str.equals(this.screenFlow.get(this.screenFlow.size() - 1))) {
            this.screenFlow.add(str);
        }
    }

    private void _open() {
        Cursor query;
        Throwable th;
        String str = null;
        if (_isSessionOpen()) {
            Log.m12801w("Session was already open");
            return;
        }
        try {
            query = this.mProvider.query("info", new String[]{"last_session_close_time"}, null, null, null);
            try {
                if (query.moveToFirst()) {
                    if (System.currentTimeMillis() < query.getLong(query.getColumnIndexOrThrow("last_session_close_time")) + Constants.SESSION_EXPIRATION) {
                        ((AnalyticsListener) this.listeners).localyticsSessionWillOpen(false, this.mAppWasUpgraded, true);
                        Log.m12799v("Opening old closed session and reconnecting");
                        _dequeQueuedCloseSessionTag(false);
                        ((AnalyticsListener) this.listeners).localyticsSessionDidOpen(false, this.mAppWasUpgraded, true);
                    } else {
                        ((AnalyticsListener) this.listeners).localyticsSessionWillOpen(this.mFirstSessionEver, this.mAppWasUpgraded, false);
                        Log.m12799v("Opening new session");
                        _dequeQueuedCloseSessionTag(true);
                        AdvertisingInfo advertisingInfo = DatapointHelper.getAdvertisingInfo(this.mLocalyticsDao.getAppContext());
                        if (advertisingInfo != null) {
                            str = advertisingInfo.id;
                        }
                        _setFirstAdvertisingId(str);
                        _tagEvent(Constants.PUSH_ACK_TYPE_OPEN);
                        BaseProvider.deleteOldFiles(this.mLocalyticsDao.getAppContext());
                        ((AnalyticsListener) this.listeners).localyticsSessionDidOpen(this.mFirstSessionEver, this.mAppWasUpgraded, false);
                    }
                }
                if (query != null) {
                    query.close();
                }
            } catch (Throwable th2) {
                th = th2;
                if (query != null) {
                    query.close();
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            query = null;
            if (query != null) {
                query.close();
            }
            throw th;
        }
    }

    private void _dequeQueuedCloseSessionTag(boolean z) {
        Throwable th;
        ContentValues contentValues = new ContentValues();
        if (z) {
            Cursor query;
            try {
                query = this.mProvider.query("info", new String[]{"queued_close_session_blob", "queued_close_session_blob_upload_format"}, null, null, null);
                try {
                    if (query.moveToFirst()) {
                        Object string = query.getString(query.getColumnIndexOrThrow("queued_close_session_blob"));
                        int i = query.getInt(query.getColumnIndexOrThrow("queued_close_session_blob_upload_format"));
                        if (!TextUtils.isEmpty(string)) {
                            this.screenFlow.clear();
                            contentValues.put("blob", string);
                            contentValues.put("upload_format", Integer.valueOf(i));
                            this.mProvider.insert("events", contentValues);
                            contentValues.clear();
                            this.mAppWasUpgraded = false;
                            this.mFirstSessionEver = false;
                        }
                    }
                    if (query != null) {
                        query.close();
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (query != null) {
                        query.close();
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                query = null;
                if (query != null) {
                    query.close();
                }
                throw th;
            }
        }
        contentValues.putNull("queued_close_session_blob");
        contentValues.putNull("queued_close_session_blob_upload_format");
        contentValues.put("last_session_close_time", Integer.valueOf(0));
        BaseProvider baseProvider = this.mProvider;
        String str = "info";
        if (baseProvider instanceof SQLiteDatabase) {
            SQLiteInstrumentation.update((SQLiteDatabase) baseProvider, str, contentValues, null, null);
        } else {
            baseProvider.update(str, contentValues, null, null);
        }
    }

    private void _setFirstAdvertisingId(String str) {
        Throwable th;
        Cursor query;
        try {
            query = this.mProvider.query("info", new String[]{"first_advertising_id"}, null, null, null);
            try {
                CharSequence string;
                if (query.moveToFirst()) {
                    string = query.getString(query.getColumnIndexOrThrow("first_advertising_id"));
                } else {
                    string = null;
                }
                if (query != null) {
                    query.close();
                }
                if (TextUtils.isEmpty(string)) {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("first_advertising_id", str);
                    BaseProvider baseProvider = this.mProvider;
                    String str2 = "info";
                    if (baseProvider instanceof SQLiteDatabase) {
                        SQLiteInstrumentation.update((SQLiteDatabase) baseProvider, str2, contentValues, null, null);
                        return;
                    } else {
                        baseProvider.update(str2, contentValues, null, null);
                        return;
                    }
                }
                Log.m12799v("First advertising id has already been set before.");
            } catch (Throwable th2) {
                th = th2;
                if (query != null) {
                    query.close();
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            query = null;
            if (query != null) {
                query.close();
            }
            throw th;
        }
    }

    private void _close() {
        if (_isSessionOpen()) {
            ((AnalyticsListener) this.listeners).localyticsSessionWillClose();
            _tagEvent("close");
            return;
        }
        Log.m12801w("Session was not open, so close is not possible.");
    }

    private void _setCustomDimension(int i, String str) {
        String customDimensionAttributeKey = getCustomDimensionAttributeKey(i);
        if (TextUtils.isEmpty(str)) {
            this.mProvider.remove("custom_dimensions", String.format("%s = ?", new Object[]{"custom_dimension_key"}), new String[]{customDimensionAttributeKey});
            return;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("custom_dimension_key", customDimensionAttributeKey);
        contentValues.put("custom_dimension_value", str);
        BaseProvider baseProvider = this.mProvider;
        String str2 = "custom_dimensions";
        String str3 = SELECTION_SET_CUSTOM_DIMENSION;
        String[] strArr = new String[]{customDimensionAttributeKey};
        if ((!(baseProvider instanceof SQLiteDatabase) ? baseProvider.update(str2, contentValues, str3, strArr) : SQLiteInstrumentation.update((SQLiteDatabase) baseProvider, str2, contentValues, str3, strArr)) == 0) {
            this.mProvider.insert("custom_dimensions", contentValues);
        }
    }

    private void _setIdentifier(String str, String str2) {
        ContentValues contentValues;
        BaseProvider baseProvider;
        String str3;
        if (str2 != null) {
            str2 = str2.trim();
        }
        if (TextUtils.isEmpty(str2)) {
            this.mProvider.remove("identifiers", String.format("%s = ?", new Object[]{"key"}), new String[]{str});
        } else {
            int update;
            contentValues = new ContentValues();
            contentValues.put("key", str);
            contentValues.put("value", str2);
            baseProvider = this.mProvider;
            str3 = "identifiers";
            String str4 = SELECTION_SET_IDENTIFIER;
            String[] strArr = new String[]{str};
            if (baseProvider instanceof SQLiteDatabase) {
                update = SQLiteInstrumentation.update((SQLiteDatabase) baseProvider, str3, contentValues, str4, strArr);
            } else {
                update = baseProvider.update(str3, contentValues, str4, strArr);
            }
            if (update == 0) {
                this.mProvider.insert("identifiers", contentValues);
            }
        }
        if (str.equals("customer_id")) {
            contentValues = new ContentValues();
            if (TextUtils.isEmpty(str2)) {
                contentValues.put("customer_id", this.mLocalyticsDao.getInstallationId());
                contentValues.put("user_type", "anonymous");
            } else {
                contentValues.put("customer_id", str2);
                contentValues.put("user_type", "known");
            }
            baseProvider = this.mProvider;
            str3 = "info";
            if (baseProvider instanceof SQLiteDatabase) {
                SQLiteInstrumentation.update((SQLiteDatabase) baseProvider, str3, contentValues, null, null);
            } else {
                baseProvider.update(str3, contentValues, null, null);
            }
        }
    }

    private boolean _isSessionOpen() {
        Cursor query;
        Throwable th;
        try {
            query = this.mProvider.query("info", new String[]{"last_session_open_time", "last_session_close_time"}, null, null, null);
            try {
                long j;
                long j2;
                if (query.moveToFirst()) {
                    j = query.getLong(query.getColumnIndexOrThrow("last_session_open_time"));
                    j2 = query.getLong(query.getColumnIndexOrThrow("last_session_close_time"));
                } else {
                    j2 = 0;
                    j = 0;
                }
                if (query != null) {
                    query.close();
                }
                if (j == 0 || j < r2) {
                    return false;
                }
                return true;
            } catch (Throwable th2) {
                th = th2;
                if (query != null) {
                    query.close();
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            query = null;
            if (query != null) {
                query.close();
            }
            throw th;
        }
    }

    private JSONObject _getIdentifiers() {
        JSONObject jSONObject = new JSONObject();
        Cursor query;
        try {
            query = this.mProvider.query("identifiers", null, null, null, null);
            while (query.moveToNext()) {
                try {
                    jSONObject.put(query.getString(query.getColumnIndexOrThrow("key")), query.getString(query.getColumnIndexOrThrow("value")));
                } catch (JSONException e) {
                } catch (Throwable th) {
                }
            }
            if (query != null) {
                query.close();
            }
            return jSONObject;
        } catch (JSONException e2) {
            query = null;
            if (query != null) {
                query.close();
            }
            return jSONObject;
        } catch (Throwable th2) {
            query = null;
            if (query != null) {
                query.close();
            }
            return jSONObject;
        }
    }

    void openSession() {
        queueMessage(obtainMessage(MESSAGE_OPEN));
    }

    void setCustomDimension(int i, String str) {
        if (i < 0 || i >= 10) {
            throw new IllegalArgumentException("Only valid dimensions are 0 - 9");
        }
        queueMessage(obtainMessage(MESSAGE_SET_CUSTOM_DIMENSION, new Object[]{Integer.valueOf(i), str}));
    }

    void setIdentifier(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("key cannot be null or empty");
        }
        queueMessage(obtainMessage(MESSAGE_SET_IDENTIFIER, new Object[]{str, str2}));
    }

    String getIdentifier(String str) {
        return getString(new AnonymousClass17(str));
    }

    void closeSession() {
        queueMessage(obtainMessage(MESSAGE_CLOSE));
    }

    void tagScreen(String str) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("event cannot be null or empty");
        }
        queueMessage(obtainMessage(MESSAGE_TAG_SCREEN, str));
    }

    void setLocation(Location location) {
        queueMessage(obtainMessage(MESSAGE_SET_LOCATION, new Location(location)));
    }

    private boolean _isOptedOut() {
        Throwable th;
        Cursor query;
        try {
            query = this.mProvider.query("info", new String[]{"opt_out"}, null, null, null);
            try {
                if (query.moveToFirst()) {
                    boolean z = query.getInt(query.getColumnIndexOrThrow("opt_out")) != 0;
                    if (query == null) {
                        return z;
                    }
                    query.close();
                    return z;
                }
                if (query != null) {
                    query.close();
                }
                return false;
            } catch (Throwable th2) {
                th = th2;
                if (query != null) {
                    query.close();
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            query = null;
            if (query != null) {
                query.close();
            }
            throw th;
        }
    }

    private void _setOptedOut(boolean z) {
        if (_isOptedOut() != z) {
            _tagEvent(z ? "opt_out" : "opt_in");
            if (_isSessionOpen() && z) {
                _close();
            }
            ContentValues contentValues = new ContentValues();
            contentValues.put("opt_out", Boolean.valueOf(z));
            BaseProvider baseProvider = this.mProvider;
            String str = "info";
            if (baseProvider instanceof SQLiteDatabase) {
                SQLiteInstrumentation.update((SQLiteDatabase) baseProvider, str, contentValues, null, null);
            } else {
                baseProvider.update(str, contentValues, null, null);
            }
        }
    }

    void setOptedOut(boolean z) {
        int i = 1;
        Log.m12799v(String.format("Requested opt-out state is %b", new Object[]{Boolean.valueOf(z)}));
        if (!z) {
            i = 0;
        }
        queueMessage(obtainMessage(MESSAGE_OPT_OUT, i, 0));
    }

    boolean isOptedOut() {
        return getBool(new Callable<Boolean>() {
            public Boolean call() {
                return Boolean.valueOf(AnalyticsHandler.this._isOptedOut());
            }
        });
    }

    void setPushDisabled(boolean z) {
        int i;
        if (z) {
            i = 1;
        } else {
            i = 0;
        }
        queueMessage(obtainMessage(MESSAGE_DISABLE_PUSH, i, 0));
    }

    private void _setPushID(String str) {
        ContentValues contentValues = new ContentValues();
        String str2 = da.GCM_REG_ID_KEY;
        if (str == null) {
            str = Trace.NULL;
        }
        contentValues.put(str2, str);
        contentValues.put("registration_version", DatapointHelper.getAppVersion(this.mLocalyticsDao.getAppContext()));
        BaseProvider baseProvider = this.mProvider;
        String str3 = "info";
        if (baseProvider instanceof SQLiteDatabase) {
            SQLiteInstrumentation.update((SQLiteDatabase) baseProvider, str3, contentValues, null, null);
        } else {
            baseProvider.update(str3, contentValues, null, null);
        }
    }

    private void _setPushID(String str, String str2) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("sender_id", str);
        String str3 = da.GCM_REG_ID_KEY;
        if (str2 == null) {
            str2 = Trace.NULL;
        }
        contentValues.put(str3, str2);
        BaseProvider baseProvider = this.mProvider;
        String str4 = "info";
        if (baseProvider instanceof SQLiteDatabase) {
            SQLiteInstrumentation.update((SQLiteDatabase) baseProvider, str4, contentValues, null, null);
        } else {
            baseProvider.update(str4, contentValues, null, null);
        }
    }

    void registerPush(String str, long j) {
        queueMessageDelayed(obtainMessage(MESSAGE_REGISTER_PUSH, str), j);
    }

    protected void _init() {
        if (this.mProvider == null) {
            this.mProvider = new AnalyticsProvider(this.siloName.toLowerCase(), this.mLocalyticsDao);
        }
        _initApiKey();
    }

    protected void _initApiKey() {
        Cursor query;
        Throwable th;
        try {
            String appVersion = DatapointHelper.getAppVersion(this.mLocalyticsDao.getAppContext());
            query = this.mProvider.query("info", new String[]{AppInfo.APP_VERSION_KEY_HEADER, AnalyticAttribute.UUID_ATTRIBUTE, "next_session_number", "customer_id"}, null, null, null);
            try {
                ContentValues contentValues;
                String string;
                if (query.moveToFirst()) {
                    Log.m12799v(String.format("Loading details for API key %s", new Object[]{this.mLocalyticsDao.getApiKey()}));
                    contentValues = new ContentValues();
                    if (!query.getString(query.getColumnIndexOrThrow(AppInfo.APP_VERSION_KEY_HEADER)).equals(appVersion)) {
                        contentValues.put(AppInfo.APP_VERSION_KEY_HEADER, appVersion);
                        this.mAppWasUpgraded = true;
                    }
                    if (contentValues.size() != 0) {
                        BaseProvider baseProvider = this.mProvider;
                        String str = "info";
                        if (baseProvider instanceof SQLiteDatabase) {
                            SQLiteInstrumentation.update((SQLiteDatabase) baseProvider, str, contentValues, null, null);
                        } else {
                            baseProvider.update(str, contentValues, null, null);
                        }
                    }
                    this.mFirstSessionEver = query.getInt(query.getColumnIndexOrThrow("next_session_number")) == 1;
                    this.mLocalyticsDao.setInstallationId(query.getString(query.getColumnIndexOrThrow(AnalyticAttribute.UUID_ATTRIBUTE)));
                    string = query.getString(query.getColumnIndexOrThrow("customer_id"));
                    synchronized (LocalyticsManager.class) {
                        if (this.mLocalyticsDao.getCustomerIdInMemory(true) == null) {
                            this.mLocalyticsDao.setCustomerIdInMemory(string);
                        }
                    }
                } else {
                    Log.m12799v(String.format("Performing first-time initialization for new API key %s", new Object[]{this.mLocalyticsDao.getApiKey()}));
                    string = UUID.randomUUID().toString();
                    this.mLocalyticsDao.setInstallationId(string);
                    contentValues = new ContentValues();
                    contentValues.put("api_key", this.mLocalyticsDao.getApiKey());
                    contentValues.put(AnalyticAttribute.UUID_ATTRIBUTE, string);
                    contentValues.put("created_time", Long.valueOf(System.currentTimeMillis()));
                    contentValues.put("opt_out", Boolean.FALSE);
                    contentValues.put("push_disabled", Boolean.FALSE);
                    contentValues.put("customer_id", string);
                    contentValues.put("user_type", "anonymous");
                    contentValues.put("fb_attribution", DatapointHelper.getFBAttribution(this.mLocalyticsDao.getAppContext()));
                    contentValues.put("first_android_id", DatapointHelper.getAndroidIdOrNull(this.mLocalyticsDao.getAppContext()));
                    contentValues.put("package_name", this.mLocalyticsDao.getAppContext().getPackageName());
                    contentValues.put(AppInfo.APP_VERSION_KEY_HEADER, appVersion);
                    contentValues.put("next_session_number", Integer.valueOf(1));
                    contentValues.put("next_header_number", Integer.valueOf(1));
                    contentValues.put("last_session_open_time", Integer.valueOf(0));
                    contentValues.put("last_session_close_time", Integer.valueOf(0));
                    this.mProvider.insert("info", contentValues);
                    this.mFirstSessionEver = true;
                    synchronized (LocalyticsManager.class) {
                        if (this.mLocalyticsDao.getCustomerIdInMemory(true) == null) {
                            this.mLocalyticsDao.setCustomerIdInMemory(string);
                        }
                    }
                }
                if (query != null) {
                    query.close();
                }
                this.mLocalyticsDao.getCustomerIdInitiatedLatch().countDown();
                this.mProvider.vacuumIfNecessary();
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Throwable th3) {
            th = th3;
            query = null;
            if (query != null) {
                query.close();
            }
            this.mLocalyticsDao.getCustomerIdInitiatedLatch().countDown();
            throw th;
        }
    }

    void setReferrerId(String str) {
        queueMessage(obtainMessage(MESSAGE_SET_REFERRERID, str));
    }

    private void _setReferrerId(String str) {
        Throwable th;
        if (!TextUtils.isEmpty(str)) {
            Cursor query;
            try {
                query = this.mProvider.query("info", new String[]{"play_attribution"}, null, null, null);
                try {
                    if (query.moveToFirst() && TextUtils.isEmpty(query.getString(query.getColumnIndexOrThrow("play_attribution")))) {
                        ContentValues contentValues = new ContentValues();
                        contentValues.put("play_attribution", str);
                        BaseProvider baseProvider = this.mProvider;
                        String str2 = "info";
                        if (baseProvider instanceof SQLiteDatabase) {
                            SQLiteInstrumentation.update((SQLiteDatabase) baseProvider, str2, contentValues, null, null);
                        } else {
                            baseProvider.update(str2, contentValues, null, null);
                        }
                        Log.m12797i("[REFERRAL] _setReferrerId: " + str);
                        _reuploadFirstSession(str);
                    }
                    if (query != null) {
                        query.close();
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (query != null) {
                        query.close();
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                query = null;
                if (query != null) {
                    query.close();
                }
                throw th;
            }
        }
    }

    private void _reuploadFirstSession(String str) {
        Object _replaceAttributionInFirstSession = _replaceAttributionInFirstSession(str);
        if (!TextUtils.isEmpty(_replaceAttributionInFirstSession)) {
            new ReferralUploader(this, _replaceAttributionInFirstSession, this.mLocalyticsDao).start();
        }
    }

    String _replaceAttributionInFirstSession(String str) {
        Throwable e;
        Cursor cursor = null;
        try {
            Cursor query = this.mProvider.query("info", new String[]{"first_open_event_blob"}, null, null, null);
            try {
                if (query.moveToFirst()) {
                    Object string = query.getString(query.getColumnIndexOrThrow("first_open_event_blob"));
                    if (!TextUtils.isEmpty(string)) {
                        String jSONObjectInstrumentation;
                        String[] split = string.split("[\n]");
                        JSONObject init = JSONObjectInstrumentation.init(split[0]);
                        JSONObject jSONObject = (JSONObject) init.get("attrs");
                        _updateHeaderForTestModeAttribution(str, jSONObject, DatapointHelper.getAdvertisingInfo(this.mLocalyticsDao.getAppContext()), true);
                        jSONObject.put("aurl", str);
                        String str2 = "%s\n%s";
                        Object[] objArr = new Object[2];
                        if (init instanceof JSONObject) {
                            jSONObjectInstrumentation = JSONObjectInstrumentation.toString(init);
                        } else {
                            jSONObjectInstrumentation = init.toString();
                        }
                        objArr[0] = jSONObjectInstrumentation;
                        objArr[1] = split[1];
                        jSONObjectInstrumentation = String.format(str2, objArr);
                        if (query == null) {
                            return jSONObjectInstrumentation;
                        }
                        query.close();
                        return jSONObjectInstrumentation;
                    }
                }
            } catch (Throwable e2) {
                Log.m12796e("JSONException", e2);
            } catch (Throwable th) {
                e2 = th;
                cursor = query;
                if (cursor != null) {
                    cursor.close();
                }
                throw e2;
            }
            if (query != null) {
                query.close();
            }
            return null;
        } catch (Throwable th2) {
            e2 = th2;
            if (cursor != null) {
                cursor.close();
            }
            throw e2;
        }
    }

    void handleRegistration(Intent intent) {
        queueMessage(obtainMessage(MESSAGE_HANDLE_PUSH_REGISTRATION, intent));
    }
}
