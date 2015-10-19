package com.localytics.android;

import android.annotation.TargetApi;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.NotificationCompat.BigTextStyle;
import android.support.v4.app.NotificationCompat.Builder;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.text.ClipboardManager;
import android.text.TextUtils;
import android.util.SparseArray;
import android.widget.Toast;
import com.localytics.android.Localytics.InAppMessageDismissButtonLocation;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.instrumentation.AsyncTaskInstrumentation;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import com.newrelic.agent.android.instrumentation.SQLiteInstrumentation;
import com.newrelic.agent.android.tracing.Trace;
import com.olacabs.customer.p076d.AppInfo;
import com.olacabs.customer.p076d.by;
import com.olacabs.customer.utils.Constants;
import com.sothree.slidinguppanel.p086a.R.R;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.UUID;
import java.util.Vector;
import java.util.concurrent.Callable;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class MarketingHandler extends BaseHandler implements AnalyticsListener {
    private static final String AB_ATTRIBUTE = "ab";
    private static final String ACTION_ATTRIBUTE = "Action";
    private static final String APP_CONTEXT_ATTRIBUTE = "App Context";
    private static final List<Map<String, String>> AUGMENTED_BLACKOUT_TIMES_RULE;
    private static final List<Integer> AUGMENTED_BLACKOUT_WEEKDAYS_RULE;
    private static final String CAMPAIGN_ID_ATTRIBUTE = "Campaign ID";
    private static final String CREATIVE_DISPLAYED_ATTRIBUTE = "Creative Displayed";
    private static final String CREATIVE_ID_ATTRIBUTE = "Creative ID";
    private static final String CREATIVE_TYPE_ATTRIBUTE = "Creative Type";
    private static final SimpleDateFormat DATE_SDF;
    private static final Map<String, Integer> DEFAULT_FREQUENCY_CAPPING_RULE;
    private static final int GLOBAL_FREQUENCY_CAPPING_RULE_CAMPAIGN_ID = -1;
    private static final String[] GLOBAL_FREQUENCY_CAPPING_RULE_REQUIRED_KEYS;
    private static final String[] INDIVIDUAL_FREQUENCY_CAPPING_RULE_REQUIRED_KEYS;
    private static final List JS_STRINGS_THAT_MEAN_NULL;
    private static final int MESSAGE_HANDLE_PUSH_RECEIVED = 204;
    private static final int MESSAGE_MARKETING_MESSAGE_TRIGGER = 201;
    private static final int MESSAGE_SET_MARKETING_MESSAGE_AS_NOT_DISPLAYED = 205;
    private static final int MESSAGE_SHOW_MARKETING_TEST = 203;
    private static final String[] PROJECTION_MARKETING_RULE_RECORD;
    private static final String PUSH_API_URL_TEMPLATE = "http://pushapi.localytics.com/push_test?platform=android&type=prod&campaign=%s&creative=%s&token=%s&install_id=%s&client_ts=%s";
    private static final String PUSH_NOTIFICATIONS_ENABLED_ATTRIBUTE = "Push Notifications Enabled";
    private static final String PUSH_OPENED_EVENT = "Localytics Push Opened";
    private static final String PUSH_RECEIVED_EVENT = "Localytics Push Received";
    private static final SimpleDateFormat TIME_SDF;
    private int lastMarketingMessagesHash;
    private boolean mCampaignDisplaying;
    Map<String, Object> mConfigurations;
    private FragmentManager mFragmentManager;
    private boolean sessionStartMarketingMessageShown;
    private MarketingRulesAdapter testCampaignsListAdapter;

    /* renamed from: com.localytics.android.MarketingHandler.10 */
    class AnonymousClass10 extends MarketingCallable {
        final /* synthetic */ MarketingRulesAdapter val$adapter;
        final /* synthetic */ TestModeListView val$listView;

        AnonymousClass10(MarketingRulesAdapter marketingRulesAdapter, TestModeListView testModeListView) {
            this.val$adapter = marketingRulesAdapter;
            this.val$listView = testModeListView;
        }

        Object call(Object[] objArr) {
            this.val$adapter.updateDataSet();
            this.val$listView.show(MarketingHandler.this.mFragmentManager, "marketing_test_mode_list");
            MarketingHandler.this.mFragmentManager.executePendingTransactions();
            return null;
        }
    }

    /* renamed from: com.localytics.android.MarketingHandler.11 */
    class AnonymousClass11 extends MarketingCallable {
        final /* synthetic */ TestModeButton val$button;

        AnonymousClass11(TestModeButton testModeButton) {
            this.val$button = testModeButton;
        }

        Object call(Object[] objArr) {
            this.val$button.show(MarketingHandler.this.mFragmentManager, "marketing_test_mode_button");
            MarketingHandler.this.mFragmentManager.executePendingTransactions();
            return null;
        }
    }

    /* renamed from: com.localytics.android.MarketingHandler.12 */
    class AnonymousClass12 extends MarketingCallable {
        final /* synthetic */ MarketingRulesAdapter val$adapter;

        AnonymousClass12(MarketingRulesAdapter marketingRulesAdapter) {
            this.val$adapter = marketingRulesAdapter;
        }

        Object call(Object[] objArr) {
            MarketingHandler.this.mLocalyticsDao.tagEvent("Test Mode Update Data");
            MarketingHandler.this.testCampaignsListAdapter = this.val$adapter;
            MarketingHandler.this.upload(MarketingHandler.this.mLocalyticsDao.getCustomerIdInMemory());
            return null;
        }
    }

    /* renamed from: com.localytics.android.MarketingHandler.13 */
    class AnonymousClass13 extends MarketingCallable {
        final /* synthetic */ Context val$appContext;

        /* renamed from: com.localytics.android.MarketingHandler.13.1 */
        class C07011 extends AsyncTask<Void, Void, String> implements TraceFieldInterface {
            public Trace _nr_trace;

            public void _nr_setTrace(Trace trace) {
                try {
                    this._nr_trace = trace;
                } catch (Exception e) {
                }
            }

            C07011() {
            }

            protected String doInBackground(Void... voidArr) {
                return MarketingHandler.this.mLocalyticsDao.getPushRegistrationId();
            }

            @TargetApi(11)
            protected void onPostExecute(String str) {
                if (TextUtils.isEmpty(str)) {
                    Toast.makeText(AnonymousClass13.this.val$appContext, "No push token found. Please check your integration.", 1).show();
                    return;
                }
                if (DatapointHelper.getApiLevel() < 11) {
                    ((ClipboardManager) AnonymousClass13.this.val$appContext.getSystemService("clipboard")).setText(str);
                } else {
                    ((android.content.ClipboardManager) AnonymousClass13.this.val$appContext.getSystemService("clipboard")).setText(str);
                }
                Toast.makeText(AnonymousClass13.this.val$appContext, str + " has been copied to the clipboard.", 1).show();
            }
        }

        AnonymousClass13(Context context) {
            this.val$appContext = context;
        }

        Object call(Object[] objArr) {
            C07011 c07011 = new C07011();
            Void[] voidArr = new Void[0];
            if (c07011 instanceof AsyncTask) {
                AsyncTaskInstrumentation.execute(c07011, voidArr);
            } else {
                c07011.execute(voidArr);
            }
            return null;
        }
    }

    /* renamed from: com.localytics.android.MarketingHandler.14 */
    class AnonymousClass14 extends MarketingCallable {
        final /* synthetic */ Context val$appContext;

        AnonymousClass14(Context context) {
            this.val$appContext = context;
        }

        @TargetApi(11)
        Object call(Object[] objArr) {
            CharSequence installationId = MarketingHandler.this.mLocalyticsDao.getInstallationId();
            if (TextUtils.isEmpty(installationId)) {
                Toast.makeText(this.val$appContext, "No install id found. Please check your integration.", 1).show();
            } else {
                if (DatapointHelper.getApiLevel() < 11) {
                    ((ClipboardManager) this.val$appContext.getSystemService("clipboard")).setText(installationId);
                } else {
                    ((android.content.ClipboardManager) this.val$appContext.getSystemService("clipboard")).setText(installationId);
                }
                Toast.makeText(this.val$appContext, installationId + " has been copied to the clipboard.", 1).show();
            }
            return null;
        }
    }

    /* renamed from: com.localytics.android.MarketingHandler.15 */
    class AnonymousClass15 extends MarketingCallable {
        final /* synthetic */ Context val$appContext;

        /* renamed from: com.localytics.android.MarketingHandler.15.1 */
        class C07021 extends AsyncTask<MarketingMessage, Void, Object[]> implements TraceFieldInterface {
            public Trace _nr_trace;
            final /* synthetic */ MarketingMessage val$marketingMessage;

            public void _nr_setTrace(Trace trace) {
                try {
                    this._nr_trace = trace;
                } catch (Exception e) {
                }
            }

            C07021(MarketingMessage marketingMessage) {
                this.val$marketingMessage = marketingMessage;
            }

            protected void onPreExecute() {
                if (!MarketingHandler.this._doesCreativeExist(((Integer) this.val$marketingMessage.get("_id")).intValue(), MarketingHandler.this._getRemoteFileURL(this.val$marketingMessage).endsWith(".zip"))) {
                    Toast.makeText(AnonymousClass15.this.val$appContext, "Downloading the campaign...\nIt'll be shown in few seconds.", 0).show();
                }
            }

            protected Object[] doInBackground(MarketingMessage... marketingMessageArr) {
                Object obj;
                Object[] objArr = new Object[2];
                if (MarketingHandler.this._updateDisplayingCandidate(marketingMessageArr[0])) {
                    obj = marketingMessageArr[0];
                } else {
                    Map map = null;
                }
                objArr[0] = obj;
                objArr[1] = MarketingHandler.this._getJavaScriptClientCallbacks(null);
                return objArr;
            }

            protected void onPostExecute(Object[] objArr) {
                MarketingMessage marketingMessage = (MarketingMessage) objArr[0];
                SparseArray sparseArray = (SparseArray) objArr[1];
                if (marketingMessage == null) {
                    try {
                        Toast.makeText(AnonymousClass15.this.val$appContext, "The downloaded campaign file is broken.", 0).show();
                    } catch (Exception e) {
                        LocalyticsManager.throwOrLogError(RuntimeException.class, "Localytics library threw an uncaught exception", e);
                    }
                } else if (MarketingHandler.this.mFragmentManager != null && MarketingHandler.this.mFragmentManager.findFragmentByTag("marketing_dialog" + marketingMessage.get("campaign_id")) == null) {
                    MarketingDialogFragment newInstance = MarketingDialogFragment.newInstance();
                    newInstance.setRetainInstance(false);
                    newInstance.setData(marketingMessage).setCallbacks(MarketingHandler.this.getDialogCallbacks()).setJavaScriptClient(new JavaScriptClient(sparseArray)).show(MarketingHandler.this.mFragmentManager, "marketing_dialog" + marketingMessage.get("campaign_id"));
                    MarketingHandler.this.mFragmentManager.executePendingTransactions();
                }
            }
        }

        AnonymousClass15(Context context) {
            this.val$appContext = context;
        }

        Object call(Object[] objArr) {
            C07021 c07021 = new C07021((MarketingMessage) objArr[0]);
            MarketingMessage[] marketingMessageArr = new MarketingMessage[]{r0};
            if (c07021 instanceof AsyncTask) {
                AsyncTaskInstrumentation.execute(c07021, marketingMessageArr);
            } else {
                c07021.execute(marketingMessageArr);
            }
            return null;
        }
    }

    /* renamed from: com.localytics.android.MarketingHandler.1 */
    class C07031 implements Runnable {
        final /* synthetic */ Map val$attributes;
        final /* synthetic */ String val$event;

        C07031(String str, Map map) {
            this.val$event = str;
            this.val$attributes = map;
        }

        public void run() {
            MarketingHandler.this._marketingTrigger(this.val$event, this.val$attributes);
        }
    }

    /* renamed from: com.localytics.android.MarketingHandler.21 */
    class AnonymousClass21 extends MarketingCallable {
        final /* synthetic */ Map val$customerIDs;

        AnonymousClass21(Map map) {
            this.val$customerIDs = map;
        }

        Object call(Object[] objArr) {
            if (this.val$customerIDs.isEmpty()) {
                return null;
            }
            try {
                JSONObject jSONObject = new JSONObject();
                for (Entry entry : this.val$customerIDs.entrySet()) {
                    jSONObject.put((String) entry.getKey(), entry.getValue());
                }
                if (!(jSONObject instanceof JSONObject)) {
                    return jSONObject.toString();
                }
                return JSONObjectInstrumentation.toString(jSONObject);
            } catch (JSONException e) {
                Log.m12801w("[JavaScriptClient]: Failed to get identifiers");
                return null;
            }
        }
    }

    /* renamed from: com.localytics.android.MarketingHandler.22 */
    class AnonymousClass22 extends MarketingCallable {
        final /* synthetic */ JSONObject val$jsonCustomDimensions;

        AnonymousClass22(JSONObject jSONObject) {
            this.val$jsonCustomDimensions = jSONObject;
        }

        Object call(Object[] objArr) {
            JSONObject jSONObject = this.val$jsonCustomDimensions;
            return !(jSONObject instanceof JSONObject) ? jSONObject.toString() : JSONObjectInstrumentation.toString(jSONObject);
        }
    }

    /* renamed from: com.localytics.android.MarketingHandler.23 */
    class AnonymousClass23 extends MarketingCallable {
        final /* synthetic */ Map val$attributes;

        AnonymousClass23(Map map) {
            this.val$attributes = map;
        }

        Object call(Object[] objArr) {
            if (this.val$attributes == null) {
                return null;
            }
            if (this.val$attributes.size() == 0) {
                return null;
            }
            try {
                JSONObject jSONObject = new JSONObject();
                for (Entry entry : this.val$attributes.entrySet()) {
                    jSONObject.put((String) entry.getKey(), (String) entry.getValue());
                }
                if (!(jSONObject instanceof JSONObject)) {
                    return jSONObject.toString();
                }
                return JSONObjectInstrumentation.toString(jSONObject);
            } catch (JSONException e) {
                Log.m12801w("[JavaScriptClient]: Failed to get attributes");
                return null;
            }
        }
    }

    /* renamed from: com.localytics.android.MarketingHandler.25 */
    static /* synthetic */ class AnonymousClass25 {
        static final /* synthetic */ int[] f8899xd9e0ac5d;

        static {
            f8899xd9e0ac5d = new int[FrequencyCappingFilter.values().length];
            try {
                f8899xd9e0ac5d[FrequencyCappingFilter.MAX_COUNT.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f8899xd9e0ac5d[FrequencyCappingFilter.FREQUENCY.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f8899xd9e0ac5d[FrequencyCappingFilter.BLACKOUT.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
        }
    }

    /* renamed from: com.localytics.android.MarketingHandler.2 */
    class C07042 implements Runnable {
        C07042() {
        }

        public void run() {
            MarketingHandler.this.__showMarketingTest();
        }
    }

    /* renamed from: com.localytics.android.MarketingHandler.3 */
    class C07053 implements Runnable {
        final /* synthetic */ Intent val$intent;

        C07053(Intent intent) {
            this.val$intent = intent;
        }

        public void run() {
            MarketingHandler.this._handlePushReceived(this.val$intent);
        }
    }

    /* renamed from: com.localytics.android.MarketingHandler.4 */
    class C07064 implements Runnable {
        final /* synthetic */ int val$campaignId;

        C07064(int i) {
            this.val$campaignId = i;
        }

        public void run() {
            MarketingHandler.this._setMarketingMessageAsNotDisplayed(this.val$campaignId);
        }
    }

    /* renamed from: com.localytics.android.MarketingHandler.5 */
    class C07075 implements Callable<Boolean> {
        final /* synthetic */ int val$campaignId;

        C07075(int i) {
            this.val$campaignId = i;
        }

        public Boolean call() throws Exception {
            return Boolean.valueOf(MarketingHandler.this._setMarketingMessageAsDisplayed(this.val$campaignId));
        }
    }

    /* renamed from: com.localytics.android.MarketingHandler.6 */
    class C07096 extends Thread {
        final /* synthetic */ MarketingRulesAdapter val$adapter;

        /* renamed from: com.localytics.android.MarketingHandler.6.1 */
        class C07081 implements Runnable {
            C07081() {
            }

            public void run() {
                C07096.this.val$adapter.updateDataSet();
                C07096.this.val$adapter.notifyDataSetChanged();
            }
        }

        C07096(MarketingRulesAdapter marketingRulesAdapter) {
            this.val$adapter = marketingRulesAdapter;
        }

        public void run() {
            new Handler(Looper.getMainLooper()).post(new C07081());
        }
    }

    /* renamed from: com.localytics.android.MarketingHandler.7 */
    class C07117 extends AsyncTask<Void, Void, String> implements TraceFieldInterface {
        public Trace _nr_trace;
        final /* synthetic */ Context val$appContext;
        final /* synthetic */ String val$campaign;
        final /* synthetic */ String val$creative;
        final /* synthetic */ String val$customerID;

        /* renamed from: com.localytics.android.MarketingHandler.7.1 */
        class C07101 extends AsyncTask<Void, Void, String> implements TraceFieldInterface {
            public Trace _nr_trace;
            final /* synthetic */ String val$pushRegID;

            public void _nr_setTrace(Trace trace) {
                try {
                    this._nr_trace = trace;
                } catch (Exception e) {
                }
            }

            C07101(String str) {
                this.val$pushRegID = str;
            }

            protected void onPreExecute() {
                if (TextUtils.isEmpty(this.val$pushRegID)) {
                    Toast.makeText(C07117.this.val$appContext, "App isn't registered with GCM to receive push notifications. Please make sure that Localytics.registerPush(<PROJECT_ID>) has been called.", 1).show();
                } else {
                    Toast.makeText(C07117.this.val$appContext, "Push Test Activated\nYou should receive a notification soon.", 1).show();
                }
            }

            protected String doInBackground(Void... voidArr) {
                Throwable th;
                Throwable th2;
                StringBuilder stringBuilder;
                HttpURLConnection httpURLConnection = null;
                if (!TextUtils.isEmpty(this.val$pushRegID)) {
                    String installationId = MarketingHandler.this.mLocalyticsDao.getInstallationId();
                    String format = String.format(MarketingHandler.PUSH_API_URL_TEMPLATE, new Object[]{C07117.this.val$campaign, C07117.this.val$creative, this.val$pushRegID, installationId, Long.toString(Math.round(((double) MarketingHandler.this.mLocalyticsDao.getCurrentTimeMillis()) / 1000.0d))});
                    try {
                        HttpURLConnection httpURLConnection2 = (HttpURLConnection) BaseUploadThread.createURLConnection(new URL(format), MarketingHandler.this.mLocalyticsDao.getProxy());
                        try {
                            httpURLConnection2.setConnectTimeout(by.DEFAULT_TIMEOUT_MS);
                            httpURLConnection2.setReadTimeout(by.DEFAULT_TIMEOUT_MS);
                            httpURLConnection2.setDoOutput(false);
                            httpURLConnection2.setRequestProperty("x-install-id", installationId);
                            httpURLConnection2.setRequestProperty("x-app-id", DatapointHelper.getAppVersion(MarketingHandler.this.mLocalyticsDao.getAppContext()));
                            httpURLConnection2.setRequestProperty("x-client-version", "androida_3.4.0");
                            httpURLConnection2.setRequestProperty("x-app-version", DatapointHelper.getAppVersion(MarketingHandler.this.mLocalyticsDao.getAppContext()));
                            httpURLConnection2.setRequestProperty("x-customer-id", C07117.this.val$customerID);
                            httpURLConnection2.getInputStream();
                            if (httpURLConnection2 != null) {
                                httpURLConnection2.disconnect();
                            }
                        } catch (Throwable e) {
                            th = e;
                            httpURLConnection = httpURLConnection2;
                            th2 = th;
                            try {
                                stringBuilder = new StringBuilder("Unfortunately, something went wrong. Push test activation was unsuccessful.");
                                if (Localytics.isLoggingEnabled() && (th2 instanceof FileNotFoundException)) {
                                    stringBuilder.append("\n\nCause:\nPush registration token has not yet been processed. Please wait a few minutes and try again.");
                                    Log.m12796e("Activating push test has failed", th2);
                                }
                                format = stringBuilder.toString();
                                if (httpURLConnection != null) {
                                    return format;
                                }
                                httpURLConnection.disconnect();
                                return format;
                            } catch (Throwable th3) {
                                th2 = th3;
                                if (httpURLConnection != null) {
                                    httpURLConnection.disconnect();
                                }
                                throw th2;
                            }
                        } catch (Throwable e2) {
                            th = e2;
                            httpURLConnection = httpURLConnection2;
                            th2 = th;
                            if (httpURLConnection != null) {
                                httpURLConnection.disconnect();
                            }
                            throw th2;
                        }
                    } catch (Exception e3) {
                        th2 = e3;
                        stringBuilder = new StringBuilder("Unfortunately, something went wrong. Push test activation was unsuccessful.");
                        stringBuilder.append("\n\nCause:\nPush registration token has not yet been processed. Please wait a few minutes and try again.");
                        Log.m12796e("Activating push test has failed", th2);
                        format = stringBuilder.toString();
                        if (httpURLConnection != null) {
                            return format;
                        }
                        httpURLConnection.disconnect();
                        return format;
                    }
                }
                return null;
            }

            protected void onPostExecute(String str) {
                if (str != null) {
                    Toast.makeText(C07117.this.val$appContext, str, 1).show();
                }
            }
        }

        public void _nr_setTrace(Trace trace) {
            try {
                this._nr_trace = trace;
            } catch (Exception e) {
            }
        }

        C07117(Context context, String str, String str2, String str3) {
            this.val$appContext = context;
            this.val$campaign = str;
            this.val$creative = str2;
            this.val$customerID = str3;
        }

        protected String doInBackground(Void... voidArr) {
            return MarketingHandler.this.mLocalyticsDao.getPushRegistrationId();
        }

        protected void onPostExecute(String str) {
            C07101 c07101 = new C07101(str);
            Void[] voidArr = new Void[0];
            if (c07101 instanceof AsyncTask) {
                AsyncTaskInstrumentation.execute(c07101, voidArr);
            } else {
                c07101.execute(voidArr);
            }
        }
    }

    /* renamed from: com.localytics.android.MarketingHandler.8 */
    class C07138 implements Runnable {
        final /* synthetic */ SparseArray val$jsCallbacks;
        final /* synthetic */ MarketingMessage val$marketingMessage;

        /* renamed from: com.localytics.android.MarketingHandler.8.1 */
        class C07121 extends AsyncTask<Void, Void, Boolean> implements TraceFieldInterface {
            public Trace _nr_trace;
            final /* synthetic */ int val$campaignId;

            public void _nr_setTrace(Trace trace) {
                try {
                    this._nr_trace = trace;
                } catch (Exception e) {
                }
            }

            C07121(int i) {
                this.val$campaignId = i;
            }

            protected Boolean doInBackground(Void... voidArr) {
                return Boolean.valueOf(MarketingHandler.this.setMarketingMessageAsDisplayed(this.val$campaignId));
            }

            protected void onPostExecute(Boolean bool) {
                if (bool.booleanValue()) {
                    if (MarketingHandler.this.mFragmentManager != null) {
                        MarketingDialogFragment.newInstance().setData(C07138.this.val$marketingMessage).setCallbacks(MarketingHandler.this.getDialogCallbacks()).setJavaScriptClient(new JavaScriptClient(C07138.this.val$jsCallbacks)).show(MarketingHandler.this.mFragmentManager, "marketing_dialog");
                        if (!Constants.isTestModeEnabled()) {
                            ((MessagingListener) MarketingHandler.this.listeners).localyticsWillDisplayInAppMessage();
                        }
                        MarketingHandler.this.mFragmentManager.executePendingTransactions();
                    } else {
                        MarketingHandler.this.setMarketingMessageAsNotDisplayed(this.val$campaignId);
                    }
                }
                MarketingHandler.this.mCampaignDisplaying = false;
            }
        }

        C07138(MarketingMessage marketingMessage, SparseArray sparseArray) {
            this.val$marketingMessage = marketingMessage;
            this.val$jsCallbacks = sparseArray;
        }

        public void run() {
            if (MarketingHandler.this.mFragmentManager != null) {
                try {
                    if (MarketingHandler.this.mFragmentManager.findFragmentByTag("marketing_dialog") == null && !MarketingHandler.this.mCampaignDisplaying) {
                        MarketingHandler.this.mCampaignDisplaying = true;
                        C07121 c07121 = new C07121(((Integer) this.val$marketingMessage.get("campaign_id")).intValue());
                        Void[] voidArr = new Void[0];
                        if (c07121 instanceof AsyncTask) {
                            AsyncTaskInstrumentation.execute(c07121, voidArr);
                        } else {
                            c07121.execute(voidArr);
                        }
                    }
                } catch (Exception e) {
                    LocalyticsManager.throwOrLogError(RuntimeException.class, "Localytics library threw an uncaught exception", e);
                }
            }
        }
    }

    /* renamed from: com.localytics.android.MarketingHandler.9 */
    class C07149 implements Callable<List> {
        C07149() {
        }

        public List call() {
            Cursor query;
            Throwable th;
            Cursor cursor = null;
            ArrayList arrayList = new ArrayList();
            try {
                query = MarketingHandler.this.mProvider.query("marketing_rules", null, null, null, "campaign_id");
                while (query.moveToNext()) {
                    try {
                        MarketingMessage marketingMessage = new MarketingMessage();
                        marketingMessage.put("_id", Integer.valueOf(query.getInt(query.getColumnIndexOrThrow("_id"))));
                        marketingMessage.put("campaign_id", Integer.valueOf(query.getInt(query.getColumnIndexOrThrow("campaign_id"))));
                        marketingMessage.put("expiration", Integer.valueOf(query.getInt(query.getColumnIndexOrThrow("expiration"))));
                        marketingMessage.put("display_seconds", Integer.valueOf(query.getInt(query.getColumnIndexOrThrow("display_seconds"))));
                        marketingMessage.put("display_session", Integer.valueOf(query.getInt(query.getColumnIndexOrThrow("display_session"))));
                        marketingMessage.put(AppInfo.APP_VERSION_KEY, query.getString(query.getColumnIndexOrThrow(AppInfo.APP_VERSION_KEY)));
                        marketingMessage.put("phone_location", query.getString(query.getColumnIndexOrThrow("phone_location")));
                        marketingMessage.put("phone_size_width", Integer.valueOf(query.getInt(query.getColumnIndexOrThrow("phone_size_width"))));
                        marketingMessage.put("phone_size_height", Integer.valueOf(query.getInt(query.getColumnIndexOrThrow("phone_size_height"))));
                        marketingMessage.put("tablet_location", query.getString(query.getColumnIndexOrThrow("tablet_location")));
                        marketingMessage.put("tablet_size_width", Integer.valueOf(query.getInt(query.getColumnIndexOrThrow("tablet_size_width"))));
                        marketingMessage.put("tablet_size_height", Integer.valueOf(query.getInt(query.getColumnIndexOrThrow("tablet_size_height"))));
                        marketingMessage.put("time_to_display", Integer.valueOf(query.getInt(query.getColumnIndexOrThrow("time_to_display"))));
                        marketingMessage.put("internet_required", Integer.valueOf(query.getInt(query.getColumnIndexOrThrow("internet_required"))));
                        marketingMessage.put("ab_test", query.getString(query.getColumnIndexOrThrow("ab_test")));
                        marketingMessage.put("rule_name_non_unique", query.getString(query.getColumnIndexOrThrow("rule_name_non_unique")));
                        marketingMessage.put("location", query.getString(query.getColumnIndexOrThrow("location")));
                        marketingMessage.put("devices", query.getString(query.getColumnIndexOrThrow("devices")));
                        marketingMessage.put("control_group", query.getString(query.getColumnIndexOrThrow("control_group")));
                        marketingMessage.put("schema_version", query.getString(query.getColumnIndexOrThrow("schema_version")));
                        arrayList.add(marketingMessage);
                    } catch (Exception e) {
                    } catch (Throwable th2) {
                        cursor = query;
                        th = th2;
                    }
                }
                if (query == null) {
                    return arrayList;
                }
                query.close();
                return arrayList;
            } catch (Exception e2) {
                query = null;
                if (query == null) {
                    return null;
                }
                query.close();
                return null;
            } catch (Throwable th3) {
                th = th3;
                if (cursor != null) {
                    cursor.close();
                }
                throw th;
            }
        }
    }

    public enum FrequencyCappingFilter {
        FREQUENCY,
        MAX_COUNT,
        BLACKOUT
    }

    private final class MessagingListenersSet extends ListenersSet implements MessagingListener {
        private MessagingListenersSet() {
            super();
        }

        public synchronized void localyticsWillDisplayInAppMessage() {
            callListeners("localyticsWillDisplayInAppMessage", null, null);
        }

        public synchronized void localyticsDidDisplayInAppMessage() {
            callListeners("localyticsDidDisplayInAppMessage", null, null);
        }

        public synchronized void localyticsWillDismissInAppMessage() {
            callListeners("localyticsWillDismissInAppMessage", null, null);
        }

        public synchronized void localyticsDidDismissInAppMessage() {
            callListeners("localyticsDidDismissInAppMessage", null, null);
        }
    }

    static {
        PROJECTION_MARKETING_RULE_RECORD = new String[]{"_id", AppInfo.APP_VERSION_KEY};
        JS_STRINGS_THAT_MEAN_NULL = Arrays.asList(new String[]{"undefined", "null", "nil", "\"\"", "''"});
        GLOBAL_FREQUENCY_CAPPING_RULE_REQUIRED_KEYS = new String[]{Constants.DISPLAY_FREQUENCIES_KEY};
        INDIVIDUAL_FREQUENCY_CAPPING_RULE_REQUIRED_KEYS = new String[]{Constants.MAX_DISPLAY_COUNT_KEY, Constants.IGNORE_GLOBAL_KEY};
        TIME_SDF = new SimpleDateFormat("HH:mm");
        DATE_SDF = new SimpleDateFormat("yyyy-MM-dd");
        AUGMENTED_BLACKOUT_TIMES_RULE = new ArrayList();
        HashMap hashMap = new HashMap();
        hashMap.put("start", "00:00");
        hashMap.put("end", "23:59");
        AUGMENTED_BLACKOUT_TIMES_RULE.add(hashMap);
        AUGMENTED_BLACKOUT_WEEKDAYS_RULE = Arrays.asList(new Integer[]{Integer.valueOf(0), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(3), Integer.valueOf(4), Integer.valueOf(5), Integer.valueOf(6)});
        DEFAULT_FREQUENCY_CAPPING_RULE = new HashMap();
        DEFAULT_FREQUENCY_CAPPING_RULE.put("max_display_count", Integer.valueOf(1));
        DEFAULT_FREQUENCY_CAPPING_RULE.put("ignore_global", Integer.valueOf(1));
        TIME_SDF.setLenient(false);
        DATE_SDF.setLenient(false);
    }

    MarketingHandler(LocalyticsDao localyticsDao, Looper looper, Context context) {
        super(localyticsDao, looper);
        this.sessionStartMarketingMessageShown = true;
        this.testCampaignsListAdapter = null;
        this.lastMarketingMessagesHash = GLOBAL_FREQUENCY_CAPPING_RULE_CAMPAIGN_ID;
        this.mCampaignDisplaying = false;
        this.siloName = "In-app";
        this.listeners = new MessagingListenersSet();
        this.doesRetry = false;
        _createLocalyticsDirectory(context);
        queueMessage(obtainMessage(1));
    }

    private boolean _createLocalyticsDirectory(Context context) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(context.getFilesDir().getAbsolutePath());
        stringBuilder.append(File.separator);
        stringBuilder.append(".localytics");
        File file = new File(stringBuilder.toString());
        return file.mkdirs() || file.isDirectory();
    }

    void setFragmentManager(FragmentManager fragmentManager) {
        this.mFragmentManager = fragmentManager;
    }

    private void _handlePushReceived(Intent intent) {
        String string = intent.getExtras().getString("ll");
        if (string == null) {
            Log.m12801w("Ignoring message that aren't from Localytics.");
            return;
        }
        try {
            JSONObject init = JSONObjectInstrumentation.init(string);
            int i = init.getInt("ca");
            CharSequence string2 = intent.getExtras().getString("message");
            boolean isPushDisabled = this.mLocalyticsDao.isPushDisabled();
            Object obj = !TextUtils.isEmpty(string2) ? 1 : null;
            Object obj2 = (isPushDisabled || obj == null) ? null : 1;
            try {
                Object obj3;
                Object obj4;
                String string3 = init.getString("cr");
                String optString = init.optString("v", "1");
                CharSequence optString2 = init.optString("t", null);
                if (!TextUtils.isEmpty(optString2)) {
                    CharSequence charSequence = optString2;
                } else if (obj != null) {
                    obj3 = "Alert";
                } else {
                    String str = "Silent";
                }
                String str2 = "Not Available";
                String str3;
                if (!this.mLocalyticsDao.isAutoIntegrate()) {
                    str3 = str2;
                } else if (this.mLocalyticsDao.isAppInForeground()) {
                    obj4 = "Foreground";
                } else {
                    str3 = "Background";
                }
                Object obj5 = obj != null ? isPushDisabled ? "No" : "Yes" : "Not Applicable";
                Map hashMap = new HashMap();
                hashMap.put(CAMPAIGN_ID_ATTRIBUTE, String.valueOf(i));
                hashMap.put(CREATIVE_ID_ATTRIBUTE, string3);
                hashMap.put(CREATIVE_TYPE_ATTRIBUTE, obj3);
                hashMap.put(CREATIVE_DISPLAYED_ATTRIBUTE, obj5);
                hashMap.put(PUSH_NOTIFICATIONS_ENABLED_ATTRIBUTE, isPushDisabled ? "No" : "Yes");
                hashMap.put(APP_CONTEXT_ATTRIBUTE, obj4);
                hashMap.put("Schema Version - Client", String.valueOf(3));
                hashMap.put("Schema Version - Server", optString);
                if (init.optInt("x", 0) == 0) {
                    this.mLocalyticsDao.tagEvent(PUSH_RECEIVED_EVENT, hashMap);
                    this.mLocalyticsDao.upload();
                }
            } catch (JSONException e) {
                Log.m12801w("Failed to get campaign id or creative id from payload");
            }
            if (obj2 == null) {
                if (isPushDisabled) {
                    Log.m12801w("Got push notification while push is disabled.");
                }
                if (obj == null) {
                    Log.m12801w("Got push notification without a message.");
                    return;
                }
                return;
            }
            Context appContext = this.mLocalyticsDao.getAppContext();
            CharSequence charSequence2 = com.newrelic.agent.android.instrumentation.Trace.NULL;
            int localyticsNotificationIcon = DatapointHelper.getLocalyticsNotificationIcon(appContext);
            try {
                charSequence2 = appContext.getPackageManager().getApplicationLabel(appContext.getPackageManager().getApplicationInfo(appContext.getPackageName(), 0));
            } catch (NameNotFoundException e2) {
                Log.m12801w("Failed to get application name, icon, or launch intent");
            }
            Intent intent2 = new Intent(appContext, PushTrackingActivity.class);
            intent2.putExtras(intent);
            ((NotificationManager) appContext.getSystemService("notification")).notify(i, new Builder(appContext).setSmallIcon(localyticsNotificationIcon).setContentTitle(charSequence2).setContentText(string2).setContentIntent(PendingIntent.getActivity(appContext, 0, intent2, 134217728)).setDefaults(GLOBAL_FREQUENCY_CAPPING_RULE_CAMPAIGN_ID).setStyle(new BigTextStyle().bigText(string2)).setAutoCancel(true).build());
        } catch (JSONException e3) {
            Log.m12801w("Failed to get campaign id from payload, ignoring message");
        }
    }

    void handlePushNotificationOpened(Intent intent) {
        if (intent != null && intent.getExtras() != null) {
            String string = intent.getExtras().getString("ll");
            if (string != null) {
                try {
                    JSONObject init = JSONObjectInstrumentation.init(string);
                    String string2 = init.getString("ca");
                    String string3 = init.getString("cr");
                    String optString = init.optString("v", "1");
                    Object optString2 = init.optString("t", null);
                    if (TextUtils.isEmpty(optString2)) {
                        optString2 = "Alert";
                    }
                    if (!(string2 == null || string3 == null)) {
                        Map hashMap = new HashMap();
                        hashMap.put(CAMPAIGN_ID_ATTRIBUTE, string2);
                        hashMap.put(CREATIVE_ID_ATTRIBUTE, string3);
                        hashMap.put(CREATIVE_TYPE_ATTRIBUTE, optString2);
                        hashMap.put(ACTION_ATTRIBUTE, "Click");
                        hashMap.put("Schema Version - Client", String.valueOf(3));
                        hashMap.put("Schema Version - Server", optString);
                        this.mLocalyticsDao.tagEvent(PUSH_OPENED_EVENT, hashMap);
                    }
                    intent.removeExtra("ll");
                } catch (JSONException e) {
                    Log.m12801w("Failed to get campaign id or creative id from payload");
                }
            }
        }
    }

    protected void handleMessageExtended(Message message) throws Exception {
        switch (message.what) {
            case MESSAGE_MARKETING_MESSAGE_TRIGGER /*201*/:
                Log.m12793d("In-app handler received MESSAGE_INAPP_TRIGGER");
                Object[] objArr = (Object[]) message.obj;
                this.mProvider.runBatchTransaction(new C07031((String) objArr[0], (Map) objArr[1]));
            case MESSAGE_SHOW_MARKETING_TEST /*203*/:
                Log.m12793d("In-app handler received MESSAGE_SHOW_INAPP_TEST");
                _upload(true, (String) message.obj);
                new Handler(Looper.getMainLooper()).postDelayed(new C07042(), 1000);
            case MESSAGE_HANDLE_PUSH_RECEIVED /*204*/:
                Log.m12793d("In-app handler received MESSAGE_HANDLE_PUSH_RECEIVED");
                this.mProvider.runBatchTransaction(new C07053((Intent) message.obj));
            case MESSAGE_SET_MARKETING_MESSAGE_AS_NOT_DISPLAYED /*205*/:
                Log.m12793d("Marketing handler received MESSAGE_SET_MARKETING_MESSAGE_AS_NOT_DISPLAYED");
                this.mProvider.runBatchTransaction(new C07064(((Integer) message.obj).intValue()));
            default:
                super.handleMessageExtended(message);
        }
    }

    boolean setMarketingMessageAsDisplayed(int i) {
        return getBool(new C07075(i));
    }

    boolean _setMarketingMessageAsDisplayed(int i) {
        Cursor query;
        Throwable th;
        try {
            query = this.mProvider.query("frequency_capping_rules", new String[]{"ignore_global"}, String.format("%s = ?", new Object[]{"campaign_id"}), new String[]{String.valueOf(i)}, null);
            try {
                if (query.moveToFirst()) {
                    int i2 = query.getInt(query.getColumnIndex("ignore_global"));
                    SQLiteDatabase sQLiteDatabase = this.mProvider.mDb;
                    String format = String.format("INSERT INTO %s VALUES (?, datetime('%s'), ?);", new Object[]{"campaigns_displayed", this.mLocalyticsDao.getTimeStringForSQLite()});
                    Integer[] numArr = new Integer[]{Integer.valueOf(i), Integer.valueOf(i2)};
                    if (sQLiteDatabase instanceof SQLiteDatabase) {
                        SQLiteInstrumentation.execSQL(sQLiteDatabase, format, numArr);
                    } else {
                        sQLiteDatabase.execSQL(format, numArr);
                    }
                    if (query != null) {
                        query.close();
                    }
                    return true;
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

    private void setMarketingMessageAsNotDisplayed(int i) {
        queueMessage(obtainMessage(MESSAGE_SET_MARKETING_MESSAGE_AS_NOT_DISPLAYED, Integer.valueOf(i)));
    }

    boolean _setMarketingMessageAsNotDisplayed(int i) {
        Throwable th;
        String valueOf = String.valueOf(i);
        Cursor query;
        try {
            query = this.mProvider.query("campaigns_displayed", new String[]{"date"}, String.format("%s = ?", new Object[]{"campaign_id"}), new String[]{valueOf}, String.format("%s DESC", new Object[]{"date"}));
            try {
                if (query.moveToFirst()) {
                    String string = query.getString(query.getColumnIndex("date"));
                    boolean z = this.mProvider.remove("campaigns_displayed", String.format("%s = ? AND %s = ?", new Object[]{"campaign_id", "date"}), new String[]{valueOf, string}) > 0;
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

    protected void _deleteUploadedData(int i) {
    }

    protected void _onUploadCompleted(String str) {
        int hashCode = str != null ? str.hashCode() : this.lastMarketingMessagesHash;
        if (hashCode != this.lastMarketingMessagesHash) {
            try {
                Map toMap = JsonHelper.toMap(JSONObjectInstrumentation.init(str));
                _processConfigObject(toMap);
                _processMarketingObject(toMap);
                this.lastMarketingMessagesHash = hashCode;
            } catch (Throwable e) {
                Log.m12802w("JSONException", e);
            }
        }
        if (this.testCampaignsListAdapter != null) {
            new C07096(this.testCampaignsListAdapter).start();
            this.testCampaignsListAdapter = null;
            return;
        }
        this.mLocalyticsDao.triggerInAppMessage(Constants.PUSH_ACK_TYPE_OPEN);
    }

    void _processConfigObject(Map<String, Object> map) throws JSONException {
        this.mConfigurations = (Map) map.get("config");
    }

    void _processMarketingObject(Map<String, Object> map) throws JSONException {
        List<MarketingMessage> arrayList = new ArrayList();
        Object obj = map.get("amp");
        if (obj != null) {
            for (Map marketingMessage : JsonHelper.toList((JSONArray) JsonHelper.toJSON(obj))) {
                arrayList.add(new MarketingMessage(marketingMessage));
            }
        }
        _removeDeactivatedMarketingMessages(arrayList);
        _validateAndStoreFrequencyCappingRule(map, Integer.valueOf(GLOBAL_FREQUENCY_CAPPING_RULE_CAMPAIGN_ID));
        for (MarketingMessage marketingMessage2 : arrayList) {
            _saveMarketingMessage(marketingMessage2);
            _validateAndStoreFrequencyCappingRule(marketingMessage2, (Integer) marketingMessage2.get(Constants.CAMPAIGN_ID_KEY));
        }
    }

    void _removeDeactivatedMarketingMessages(List<MarketingMessage> list) {
        Iterator it;
        Throwable th;
        List arrayList = new ArrayList();
        for (MarketingMessage safeIntegerFromMap : list) {
            MarketingMessage safeIntegerFromMap2;
            arrayList.add(Integer.valueOf(JsonHelper.getSafeIntegerFromMap(safeIntegerFromMap2, "campaign_id")));
        }
        _deleteFrequencyCappingRule(Integer.valueOf(GLOBAL_FREQUENCY_CAPPING_RULE_CAMPAIGN_ID));
        Vector vector = new Vector();
        Cursor query;
        try {
            query = this.mProvider.query("marketing_rules", null, null, null, null);
            int i = 0;
            while (i < query.getCount()) {
                try {
                    query.moveToPosition(i);
                    if (!arrayList.contains(Integer.valueOf(query.getInt(query.getColumnIndexOrThrow("campaign_id"))))) {
                        MarketingMessage marketingMessage = new MarketingMessage();
                        marketingMessage.put("_id", Integer.valueOf(query.getColumnIndexOrThrow("_id")));
                        marketingMessage.put("campaign_id", Integer.valueOf(query.getInt(query.getColumnIndexOrThrow("campaign_id"))));
                        marketingMessage.put("expiration", Integer.valueOf(query.getInt(query.getColumnIndexOrThrow("expiration"))));
                        marketingMessage.put("display_seconds", Integer.valueOf(query.getInt(query.getColumnIndexOrThrow("display_seconds"))));
                        marketingMessage.put("display_session", Integer.valueOf(query.getInt(query.getColumnIndexOrThrow("display_session"))));
                        marketingMessage.put(AppInfo.APP_VERSION_KEY, query.getString(query.getColumnIndexOrThrow(AppInfo.APP_VERSION_KEY)));
                        marketingMessage.put("phone_location", query.getString(query.getColumnIndexOrThrow("phone_location")));
                        marketingMessage.put("phone_size_width", Integer.valueOf(query.getInt(query.getColumnIndexOrThrow("phone_size_width"))));
                        marketingMessage.put("phone_size_height", Integer.valueOf(query.getInt(query.getColumnIndexOrThrow("phone_size_height"))));
                        marketingMessage.put("tablet_location", query.getString(query.getColumnIndexOrThrow("tablet_location")));
                        marketingMessage.put("tablet_size_width", Integer.valueOf(query.getInt(query.getColumnIndexOrThrow("tablet_size_width"))));
                        marketingMessage.put("tablet_size_height", Integer.valueOf(query.getInt(query.getColumnIndexOrThrow("tablet_size_height"))));
                        marketingMessage.put("time_to_display", Integer.valueOf(query.getInt(query.getColumnIndexOrThrow("time_to_display"))));
                        marketingMessage.put("internet_required", Integer.valueOf(query.getInt(query.getColumnIndexOrThrow("internet_required"))));
                        marketingMessage.put("ab_test", query.getString(query.getColumnIndexOrThrow("ab_test")));
                        marketingMessage.put("rule_name_non_unique", query.getString(query.getColumnIndexOrThrow("rule_name_non_unique")));
                        marketingMessage.put("location", query.getString(query.getColumnIndexOrThrow("location")));
                        marketingMessage.put("devices", query.getString(query.getColumnIndexOrThrow("devices")));
                        vector.add(marketingMessage);
                    }
                    i++;
                } catch (Throwable th2) {
                    th = th2;
                }
            }
            if (query != null) {
                query.close();
            }
            it = vector.iterator();
            while (it.hasNext()) {
                safeIntegerFromMap2 = (MarketingMessage) it.next();
                _destroyLocalMarketingMessage(safeIntegerFromMap2);
                _deleteFrequencyCappingRule((Integer) safeIntegerFromMap2.get("campaign_id"));
            }
            this.mProvider.vacuumIfNecessary();
        } catch (Throwable th3) {
            th = th3;
            query = null;
            if (query != null) {
                query.close();
            }
            throw th;
        }
    }

    int _saveMarketingMessage(MarketingMessage marketingMessage) {
        Throwable th;
        List list = null;
        if (!_validateMarketingMessage(marketingMessage)) {
            return 0;
        }
        int safeIntegerFromMap = JsonHelper.getSafeIntegerFromMap(marketingMessage, "campaign_id");
        Cursor query;
        try {
            query = this.mProvider.query("marketing_rules", PROJECTION_MARKETING_RULE_RECORD, String.format("%s = ?", new Object[]{"campaign_id"}), new String[]{Integer.toString(safeIntegerFromMap)}, null);
            try {
                int i;
                int i2;
                if (query.moveToFirst()) {
                    i = query.getInt(query.getColumnIndexOrThrow("_id"));
                    i2 = query.getInt(query.getColumnIndexOrThrow(AppInfo.APP_VERSION_KEY));
                } else {
                    i2 = 0;
                    i = 0;
                }
                if (query != null) {
                    query.close();
                }
                if (i > 0) {
                    Log.m12801w(String.format("Existing in-app rule already exists for this campaign\n\t campaignID = %d\n\t ruleID = %d", new Object[]{Integer.valueOf(safeIntegerFromMap), Integer.valueOf(i)}));
                    if (i2 >= JsonHelper.getSafeIntegerFromMap(marketingMessage, AppInfo.APP_VERSION_KEY)) {
                        Log.m12801w(String.format("No update needed. Campaign version has not been updated\n\t version: %d", new Object[]{Integer.valueOf(i2)}));
                        return 0;
                    }
                    _destroyLocalMarketingMessage(marketingMessage);
                } else {
                    Log.m12801w("In-app campaign not found. Creating a new one.");
                }
                int insert = (int) this.mProvider.insert("marketing_rules", _parseMarketingMessage(marketingMessage));
                if (insert > 0) {
                    _saveMarketingConditions((long) insert, JsonHelper.getSafeListFromMap(marketingMessage, "conditions"));
                    try {
                        list = JsonHelper.toList((JSONArray) JsonHelper.toJSON(marketingMessage.get("display_events")));
                    } catch (JSONException e) {
                    }
                    if (list != null) {
                        _bindRuleToEvents((long) insert, list);
                    }
                }
                if (insert > 0 && !Constants.isTestModeEnabled()) {
                    Object remoteFileURL = MarketingDownloader.getRemoteFileURL(marketingMessage);
                    Object localFileURL = !TextUtils.isEmpty(remoteFileURL) ? MarketingDownloader.getLocalFileURL((long) insert, remoteFileURL.endsWith(".zip"), this.mLocalyticsDao.getAppContext(), this.mLocalyticsDao.getApiKey()) : com.newrelic.agent.android.instrumentation.Trace.NULL;
                    if (!(TextUtils.isEmpty(remoteFileURL) || TextUtils.isEmpty(localFileURL))) {
                        Log.m12799v(String.format("Saving in-app message with remoteURL = %s and localUrl = %s", new Object[]{remoteFileURL, localFileURL}));
                        MarketingDownloader.downloadFile(remoteFileURL, localFileURL, true, this.mLocalyticsDao.getProxy());
                    }
                }
                return insert;
            } catch (Throwable th2) {
                th = th2;
                if (query != null) {
                    query.close();
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            query = list;
            if (query != null) {
                query.close();
            }
            throw th;
        }
    }

    boolean _validateFrequencyCappingRule(Map<String, Object> map, boolean z) {
        for (Object containsKey : z ? GLOBAL_FREQUENCY_CAPPING_RULE_REQUIRED_KEYS : INDIVIDUAL_FREQUENCY_CAPPING_RULE_REQUIRED_KEYS) {
            if (!map.containsKey(containsKey)) {
                return false;
            }
        }
        List list = (List) map.get(Constants.DISPLAY_FREQUENCIES_KEY);
        if (!_checkFrequencyCappingRuleArray(list, new String[]{Constants.DAYS_KEY, Constants.COUNT_KEY})) {
            return false;
        }
        if (!_additionalFCDisplayFrequencyRulesValidation(list)) {
            return false;
        }
        List<Map> list2 = (List) map.get(Constants.BLACKOUT_RULES_KEY);
        if (list2 != null) {
            if (list2.size() < 1) {
                return false;
            }
            for (Map map2 : list2) {
                if (!map2.containsKey(Constants.TIMES_KEY) && !map2.containsKey(Constants.DATES_KEY) && !map2.containsKey(Constants.WEEKDAYS_KEY)) {
                    return false;
                }
                List list3 = (List) map2.get(Constants.TIMES_KEY);
                if (!_checkFrequencyCappingRuleArray(list3, new String[]{Constants.START_KEY, Constants.END_KEY})) {
                    return false;
                }
                if (!_additionalFCDatesAndTimesRulesValidation(list3, TIME_SDF)) {
                    return false;
                }
                list3 = (List) map2.get(Constants.DATES_KEY);
                if (!_checkFrequencyCappingRuleArray(list3, new String[]{Constants.START_KEY, Constants.END_KEY})) {
                    return false;
                }
                if (!_additionalFCDatesAndTimesRulesValidation(list3, DATE_SDF)) {
                    return false;
                }
                list = (List) map2.get(Constants.WEEKDAYS_KEY);
                if (list != null && (list.size() < 1 || list.size() > 7)) {
                    return false;
                }
                if (!_additionalFCWeekdaysRulesValidation(list)) {
                    return false;
                }
            }
        }
        return true;
    }

    boolean _checkFrequencyCappingRuleArray(List<?> list, String[] strArr) {
        if (list != null) {
            if (list.size() < 1) {
                return false;
            }
            if (strArr.length > 0) {
                for (Object obj : strArr) {
                    Iterator it = list.iterator();
                    while (it.hasNext()) {
                        if (!((Map) it.next()).containsKey(obj)) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    boolean _additionalFCDisplayFrequencyRulesValidation(List<Map<String, Integer>> list) {
        if (list != null) {
            for (Map map : list) {
                if (((Integer) map.get(Constants.DAYS_KEY)).intValue() >= 1) {
                    if (((Integer) map.get(Constants.COUNT_KEY)).intValue() < 1) {
                    }
                }
                return false;
            }
        }
        return true;
    }

    boolean _additionalFCDatesAndTimesRulesValidation(List<Map<String, String>> list, SimpleDateFormat simpleDateFormat) {
        if (list != null) {
            for (Map map : list) {
                try {
                    if (simpleDateFormat.parse((String) map.get(Constants.START_KEY)).after(simpleDateFormat.parse((String) map.get(Constants.END_KEY)))) {
                        throw new Exception();
                    }
                } catch (Exception e) {
                    return false;
                }
            }
        }
        return true;
    }

    boolean _additionalFCWeekdaysRulesValidation(List<Integer> list) {
        if (list != null) {
            if (list.size() > 7) {
                return false;
            }
            for (Integer num : list) {
                if (num.intValue() >= 0) {
                    if (num.intValue() > 6) {
                    }
                }
                return false;
            }
        }
        return true;
    }

    boolean _validateAndStoreFrequencyCappingRule(Map<String, Object> map, Integer num) {
        Map map2 = (Map) map.get(Constants.FREQUENCY_CAPPING_KEY);
        if (map2 != null) {
            if (_validateFrequencyCappingRule(map2, num.intValue() == GLOBAL_FREQUENCY_CAPPING_RULE_CAMPAIGN_ID)) {
                return _saveFrequencyCappingRule(map2, num);
            }
        }
        if (num.intValue() != GLOBAL_FREQUENCY_CAPPING_RULE_CAMPAIGN_ID) {
            return _saveFrequencyCappingRule(new HashMap(DEFAULT_FREQUENCY_CAPPING_RULE), num);
        }
        return false;
    }

    boolean _saveFrequencyCappingRule(Map<String, Object> map, Integer num) {
        boolean z = true;
        this.mProvider.mDb.beginTransaction();
        int _saveFrequencyCappingRuleBase = (int) _saveFrequencyCappingRuleBase(map, num);
        boolean z2 = _saveFrequencyCappingRuleBase > 0 && _saveFrequencyCappingRuleDisplayFrequency((List) map.get(Constants.DISPLAY_FREQUENCIES_KEY), Integer.valueOf(_saveFrequencyCappingRuleBase));
        List list = (List) map.get(Constants.BLACKOUT_RULES_KEY);
        if (!(z2 && _saveFrequencyCappingRuleBlackout(list, Integer.valueOf(_saveFrequencyCappingRuleBase)))) {
            z = false;
        }
        if (z) {
            this.mProvider.mDb.setTransactionSuccessful();
        }
        this.mProvider.mDb.endTransaction();
        return z;
    }

    boolean _deleteFrequencyCappingRule(Integer num) {
        try {
            this.mProvider.remove("frequency_capping_rules", String.format("%s = ?", new Object[]{"campaign_id"}), new String[]{String.valueOf(num)});
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    long _saveFrequencyCappingRuleBase(Map<String, ?> map, Integer num) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("campaign_id", num);
        if (num.intValue() != GLOBAL_FREQUENCY_CAPPING_RULE_CAMPAIGN_ID) {
            contentValues.put("max_display_count", (Integer) map.get(Constants.MAX_DISPLAY_COUNT_KEY));
            contentValues.put("ignore_global", (Integer) map.get(Constants.IGNORE_GLOBAL_KEY));
        } else {
            contentValues.putNull("max_display_count");
            contentValues.putNull("ignore_global");
        }
        SQLiteDatabase sQLiteDatabase = this.mProvider.mDb;
        String str = "frequency_capping_rules";
        return !(sQLiteDatabase instanceof SQLiteDatabase) ? sQLiteDatabase.replace(str, null, contentValues) : SQLiteInstrumentation.replace(sQLiteDatabase, str, null, contentValues);
    }

    boolean _saveFrequencyCappingRuleDisplayFrequency(List<Map<String, Integer>> list, Integer num) {
        if (list != null) {
            for (Map map : list) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("frequency_id", num);
                contentValues.put("count", (Integer) map.get(Constants.COUNT_KEY));
                contentValues.put("days", (Integer) map.get(Constants.DAYS_KEY));
                if (this.mProvider.insert("frequency_capping_display_frequencies", contentValues) <= 0) {
                    return false;
                }
            }
        }
        return true;
    }

    boolean _saveFrequencyCappingRuleBlackout(List<Map<String, Object>> list, Integer num) {
        if (list != null) {
            int i = 0;
            for (Map _augmentBlackoutRule : list) {
                Map _augmentBlackoutRule2 = _augmentBlackoutRule(_augmentBlackoutRule);
                if (!_saveFrequencyCappingRuleBlackoutDates((List) _augmentBlackoutRule2.get(Constants.DATES_KEY), num, Integer.valueOf(i)) || !_saveFrequencyCappingRuleBlackoutWeekdays((List) _augmentBlackoutRule2.get(Constants.WEEKDAYS_KEY), num, Integer.valueOf(i)) || !_saveFrequencyCappingRuleBlackoutTimes((List) _augmentBlackoutRule2.get(Constants.TIMES_KEY), num, Integer.valueOf(i))) {
                    return false;
                }
                i++;
            }
        }
        return true;
    }

    Map<String, Object> _augmentBlackoutRule(Map<String, Object> map) {
        Map<String, Object> hashMap = new HashMap(map);
        boolean containsKey = map.containsKey(Constants.DATES_KEY);
        boolean containsKey2 = map.containsKey(Constants.WEEKDAYS_KEY);
        boolean containsKey3 = map.containsKey(Constants.TIMES_KEY);
        if ((containsKey || containsKey2) && !containsKey3) {
            hashMap.put(Constants.TIMES_KEY, new ArrayList(AUGMENTED_BLACKOUT_TIMES_RULE));
        } else if (!(!containsKey3 || containsKey || containsKey2)) {
            hashMap.put(Constants.WEEKDAYS_KEY, new ArrayList(AUGMENTED_BLACKOUT_WEEKDAYS_RULE));
        }
        return hashMap;
    }

    boolean _saveFrequencyCappingRuleBlackoutDates(List<Map<String, String>> list, Integer num, Integer num2) {
        if (list != null) {
            for (Map map : list) {
                try {
                    SQLiteDatabase sQLiteDatabase = this.mProvider.mDb;
                    String format = String.format("INSERT INTO %s VALUES (?, ?, datetime(?,'start of day'), datetime(?,'start of day','1 day','-1 second'));", new Object[]{"frequency_capping_blackout_dates"});
                    Object[] objArr = new Object[]{num, num2, map.get(Constants.START_KEY), map.get(Constants.END_KEY)};
                    if (sQLiteDatabase instanceof SQLiteDatabase) {
                        SQLiteInstrumentation.execSQL(sQLiteDatabase, format, objArr);
                    } else {
                        sQLiteDatabase.execSQL(format, objArr);
                    }
                } catch (Exception e) {
                    return false;
                }
            }
        }
        return true;
    }

    boolean _saveFrequencyCappingRuleBlackoutWeekdays(List<Integer> list, Integer num, Integer num2) {
        if (list != null) {
            for (Integer num3 : list) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("frequency_id", num);
                contentValues.put("rule_group_id", num2);
                contentValues.put("day", num3);
                if (this.mProvider.insert("frequency_capping_blackout_weekdays", contentValues) <= 0) {
                    return false;
                }
            }
        }
        return true;
    }

    boolean _saveFrequencyCappingRuleBlackoutTimes(List<Map<String, String>> list, Integer num, Integer num2) {
        if (list != null) {
            for (Map map : list) {
                Integer valueOf = Integer.valueOf(_timeStringToSeconds((String) map.get(Constants.START_KEY)));
                Integer valueOf2 = Integer.valueOf(_timeStringToSeconds((String) map.get(Constants.END_KEY)));
                if (valueOf.intValue() == GLOBAL_FREQUENCY_CAPPING_RULE_CAMPAIGN_ID || valueOf2.intValue() == GLOBAL_FREQUENCY_CAPPING_RULE_CAMPAIGN_ID) {
                    return false;
                }
                ContentValues contentValues = new ContentValues();
                contentValues.put("frequency_id", num);
                contentValues.put("rule_group_id", num2);
                contentValues.put("start", valueOf);
                contentValues.put("end", Integer.valueOf(valueOf2.intValue() + 59));
                if (this.mProvider.insert("frequency_capping_blackout_times", contentValues) <= 0) {
                    return false;
                }
            }
        }
        return true;
    }

    private int _timeStringToSeconds(String str) {
        try {
            String[] split = str.split(":");
            return (Integer.valueOf(split[1]).intValue() * 60) + (Integer.valueOf(split[0]).intValue() * 3600);
        } catch (Exception e) {
            return GLOBAL_FREQUENCY_CAPPING_RULE_CAMPAIGN_ID;
        }
    }

    List<MarketingMessage> _filterMarketingMessagesDisqualifiedByFrequencyCappingRules(List<MarketingMessage> list) {
        Set hashSet = new HashSet();
        for (MarketingMessage marketingMessage : list) {
            hashSet.add((Integer) marketingMessage.get("campaign_id"));
        }
        if (hashSet.size() > 0) {
            hashSet.removeAll(_getDisqualifiedCampaigns(FrequencyCappingFilter.MAX_COUNT, hashSet));
        }
        if (hashSet.size() > 0) {
            hashSet.removeAll(_getDisqualifiedCampaigns(FrequencyCappingFilter.FREQUENCY, hashSet));
        }
        if (hashSet.size() > 0) {
            hashSet.removeAll(_getDisqualifiedCampaigns(FrequencyCappingFilter.BLACKOUT, hashSet));
        }
        if (hashSet.size() > 0) {
            hashSet.removeAll(_getGloballyDisqualifiedCampaigns(FrequencyCappingFilter.FREQUENCY, hashSet));
        }
        if (hashSet.size() > 0) {
            hashSet.removeAll(_getGloballyDisqualifiedCampaigns(FrequencyCappingFilter.BLACKOUT, hashSet));
        }
        List<MarketingMessage> linkedList = new LinkedList();
        if (hashSet.size() > 0) {
            for (MarketingMessage marketingMessage2 : list) {
                if (hashSet.contains(Integer.valueOf(((Integer) marketingMessage2.get("campaign_id")).intValue()))) {
                    linkedList.add(marketingMessage2);
                }
            }
        }
        return linkedList;
    }

    Set<Integer> _getIgnoresGlobalCampaigns(boolean z, Set<Integer> set) {
        int i = 1;
        String str = "SELECT %s FROM %s WHERE %s = %s AND %s in (%s);";
        Object[] objArr = new Object[6];
        objArr[0] = "campaign_id";
        objArr[1] = "frequency_capping_rules";
        objArr[2] = "ignore_global";
        if (!z) {
            i = 0;
        }
        objArr[3] = Integer.valueOf(i);
        objArr[4] = "campaign_id";
        objArr[5] = TextUtils.join(",", set);
        return _getCampaignIdsFromFrequencyCappingQuery(String.format(str, objArr));
    }

    Set<Integer> _getDisqualifiedCampaigns(FrequencyCappingFilter frequencyCappingFilter, Set<Integer> set) {
        switch (AnonymousClass25.f8899xd9e0ac5d[frequencyCappingFilter.ordinal()]) {
            case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                return _getCampaignIdsFromFrequencyCappingQuery(String.format("SELECT fc.%s FROM %s AS fc JOIN %s AS cd ON fc.%s=cd.%s WHERE cd.%s in (%s) GROUP BY fc.%s HAVING count(*) >= fc.%s;", new Object[]{"campaign_id", "frequency_capping_rules", "campaigns_displayed", "campaign_id", "campaign_id", "campaign_id", TextUtils.join(",", set), "campaign_id", "max_display_count"}));
            case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                return _getCampaignIdsFromFrequencyCappingQuery(String.format("SELECT DISTINCT fc.%s FROM %s AS fc JOIN %s AS df ON fc.%s=df.%s JOIN %s AS cd ON fc.%s=cd.%s WHERE (cd.%s BETWEEN datetime('%s','-'||df.%s||' days') AND datetime('%s')) AND fc.%s in (%s) GROUP BY df.%s HAVING count(cd.%s) >= df.%s;", new Object[]{"campaign_id", "frequency_capping_rules", "frequency_capping_display_frequencies", "_id", "frequency_id", "campaigns_displayed", "campaign_id", "campaign_id", "date", this.mLocalyticsDao.getTimeStringForSQLite(), "days", this.mLocalyticsDao.getTimeStringForSQLite(), "campaign_id", TextUtils.join(",", set), "_id", "date", "count"}));
            case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                Calendar calendar = this.mLocalyticsDao.getCalendar();
                int i = calendar.get(7) + GLOBAL_FREQUENCY_CAPPING_RULE_CAMPAIGN_ID;
                int i2 = (((calendar.get(11) * 60) + calendar.get(12)) * 60) + calendar.get(13);
                Set<Integer> _getCampaignIdsFromFrequencyCappingQuery = _getCampaignIdsFromFrequencyCappingQuery(String.format("SELECT fc.%s FROM %s AS fc, %s AS d, %s AS t WHERE ((fc.%s=t.%s) AND (fc.%s=d.%s)) AND (d.%s=t.%s) AND (datetime('%s','localtime') BETWEEN d.%s and d.%s) AND (? BETWEEN t.%s AND t.%s) AND fc.%s IN (%s);", new Object[]{"campaign_id", "frequency_capping_rules", "frequency_capping_blackout_dates", "frequency_capping_blackout_times", "_id", "frequency_id", "_id", "frequency_id", "rule_group_id", "rule_group_id", this.mLocalyticsDao.getTimeStringForSQLite(), "start", "end", "start", "end", "campaign_id", TextUtils.join(",", set)}), new String[]{String.valueOf(i2)});
                _getCampaignIdsFromFrequencyCappingQuery.addAll(_getCampaignIdsFromFrequencyCappingQuery(String.format("SELECT fc.%s FROM %s AS fc, %s AS w, %s AS t WHERE ((fc.%s=t.%s) AND (fc.%s=w.%s)) AND (w.%s=t.%s) AND (w.%s=?)  AND (? BETWEEN t.%s AND t.%s) AND fc.%s IN (%s);", new Object[]{"campaign_id", "frequency_capping_rules", "frequency_capping_blackout_weekdays", "frequency_capping_blackout_times", "_id", "frequency_id", "_id", "frequency_id", "rule_group_id", "rule_group_id", "day", "start", "end", "campaign_id", TextUtils.join(",", set)}), new String[]{String.valueOf(i), String.valueOf(i2)}));
                return _getCampaignIdsFromFrequencyCappingQuery;
            default:
                return new HashSet();
        }
    }

    private Set<Integer> _getCampaignIdsFromFrequencyCappingQuery(String str) {
        return _getCampaignIdsFromFrequencyCappingQuery(str, null);
    }

    private Set<Integer> _getCampaignIdsFromFrequencyCappingQuery(String str, String[] strArr) {
        Throwable th;
        Set<Integer> hashSet = new HashSet();
        if (!TextUtils.isEmpty(str)) {
            Cursor cursor = null;
            try {
                SQLiteDatabase sQLiteDatabase = this.mProvider.mDb;
                Cursor rawQuery = !(sQLiteDatabase instanceof SQLiteDatabase) ? sQLiteDatabase.rawQuery(str, strArr) : SQLiteInstrumentation.rawQuery(sQLiteDatabase, str, strArr);
                while (rawQuery.moveToNext()) {
                    try {
                        hashSet.add(Integer.valueOf(rawQuery.getInt(rawQuery.getColumnIndexOrThrow("campaign_id"))));
                    } catch (Throwable th2) {
                        Throwable th3 = th2;
                        cursor = rawQuery;
                        th = th3;
                    }
                }
                if (rawQuery != null) {
                    rawQuery.close();
                }
            } catch (Throwable th4) {
                th = th4;
                if (cursor != null) {
                    cursor.close();
                }
                throw th;
            }
        }
        return hashSet;
    }

    Set<Integer> _getGloballyDisqualifiedCampaigns(FrequencyCappingFilter frequencyCappingFilter, Set<Integer> set) {
        Throwable th;
        Cursor query;
        try {
            query = this.mProvider.query("frequency_capping_rules", null, "campaign_id = ?", new String[]{String.valueOf(GLOBAL_FREQUENCY_CAPPING_RULE_CAMPAIGN_ID)}, null);
            try {
                int i;
                if (query.moveToFirst()) {
                    i = query.getInt(query.getColumnIndexOrThrow("_id"));
                } else {
                    i = GLOBAL_FREQUENCY_CAPPING_RULE_CAMPAIGN_ID;
                }
                if (query != null) {
                    query.close();
                }
                if (i != GLOBAL_FREQUENCY_CAPPING_RULE_CAMPAIGN_ID) {
                    try {
                        SQLiteDatabase sQLiteDatabase;
                        Set<Integer> _getIgnoresGlobalCampaigns;
                        switch (AnonymousClass25.f8899xd9e0ac5d[frequencyCappingFilter.ordinal()]) {
                            case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                                sQLiteDatabase = this.mProvider.mDb;
                                String format = String.format("SELECT df.%s FROM %s AS fc JOIN %s AS df ON fc.%s = df.%s JOIN %s AS cd WHERE cd.%s = 0 AND fc.%s = ? AND (cd.%s BETWEEN datetime('%s','-'||df.%s||' days') AND datetime('%s')) GROUP BY df.%s HAVING count(cd.%s) >= df.%s;", new Object[]{"_id", "frequency_capping_rules", "frequency_capping_display_frequencies", "_id", "frequency_id", "campaigns_displayed", "ignore_global", "campaign_id", "date", this.mLocalyticsDao.getTimeStringForSQLite(), "days", this.mLocalyticsDao.getTimeStringForSQLite(), "_id", "date", "count"});
                                String[] strArr = new String[]{String.valueOf(GLOBAL_FREQUENCY_CAPPING_RULE_CAMPAIGN_ID)};
                                if (sQLiteDatabase instanceof SQLiteDatabase) {
                                    query = SQLiteInstrumentation.rawQuery(sQLiteDatabase, format, strArr);
                                } else {
                                    query = sQLiteDatabase.rawQuery(format, strArr);
                                }
                                if (query.getCount() > 0) {
                                    _getIgnoresGlobalCampaigns = _getIgnoresGlobalCampaigns(false, set);
                                    if (query == null) {
                                        return _getIgnoresGlobalCampaigns;
                                    }
                                    query.close();
                                    return _getIgnoresGlobalCampaigns;
                                }
                                break;
                            case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                                Calendar calendar = this.mLocalyticsDao.getCalendar();
                                int i2 = calendar.get(7) + GLOBAL_FREQUENCY_CAPPING_RULE_CAMPAIGN_ID;
                                int i3 = (((calendar.get(11) * 60) + calendar.get(12)) * 60) + calendar.get(13);
                                sQLiteDatabase = this.mProvider.mDb;
                                String format2 = String.format("SELECT * FROM %s AS d, %s AS t WHERE ((d.%s=?) AND (t.%s=?)) AND (d.%s=t.%s) AND (datetime('%s','localtime') BETWEEN d.%s and d.%s) AND (? BETWEEN t.%s AND t.%s);", new Object[]{"frequency_capping_blackout_dates", "frequency_capping_blackout_times", "frequency_id", "frequency_id", "rule_group_id", "rule_group_id", this.mLocalyticsDao.getTimeStringForSQLite(), "start", "end", "start", "end"});
                                String[] strArr2 = new String[]{String.valueOf(i), String.valueOf(i), String.valueOf(i3)};
                                if (sQLiteDatabase instanceof SQLiteDatabase) {
                                    query = SQLiteInstrumentation.rawQuery(sQLiteDatabase, format2, strArr2);
                                } else {
                                    query = sQLiteDatabase.rawQuery(format2, strArr2);
                                }
                                int count = query.getCount();
                                query.close();
                                sQLiteDatabase = this.mProvider.mDb;
                                String format3 = String.format("SELECT * FROM %s AS w, %s AS t WHERE ((w.%s=?) AND (t.%s=?)) AND (w.%s=t.%s) AND (w.%s=?) AND (? BETWEEN t.%s AND t.%s);", new Object[]{"frequency_capping_blackout_weekdays", "frequency_capping_blackout_times", "frequency_id", "frequency_id", "rule_group_id", "rule_group_id", "day", "start", "end"});
                                String[] strArr3 = new String[]{String.valueOf(i), String.valueOf(i), String.valueOf(i2), String.valueOf(i3)};
                                if (sQLiteDatabase instanceof SQLiteDatabase) {
                                    query = SQLiteInstrumentation.rawQuery(sQLiteDatabase, format3, strArr3);
                                } else {
                                    query = sQLiteDatabase.rawQuery(format3, strArr3);
                                }
                                if (query.getCount() + count > 0) {
                                    _getIgnoresGlobalCampaigns = _getIgnoresGlobalCampaigns(false, set);
                                    if (query == null) {
                                        return _getIgnoresGlobalCampaigns;
                                    }
                                    query.close();
                                    return _getIgnoresGlobalCampaigns;
                                }
                                break;
                        }
                        if (query != null) {
                            query.close();
                        }
                    } catch (Throwable th2) {
                        if (query != null) {
                            query.close();
                        }
                    }
                }
                return new HashSet();
            } catch (Throwable th3) {
                th = th3;
                if (query != null) {
                    query.close();
                }
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            query = null;
            if (query != null) {
                query.close();
            }
            throw th;
        }
    }

    void displayMarketingMessage(String str, Map<String, String> map, boolean z) {
        int i = 1;
        if (Constants.PUSH_ACK_TYPE_OPEN.equals(str)) {
            Message obtainMessage = obtainMessage(MESSAGE_MARKETING_MESSAGE_TRIGGER, new Object[]{str, null});
            if (!z) {
                i = 0;
            }
            queueMessageDelayed(obtainMessage, ((long) i) * 10000);
            return;
        }
        queueMessage(obtainMessage(MESSAGE_MARKETING_MESSAGE_TRIGGER, new Object[]{str, map}));
    }

    void dismissCurrentInAppMessage() {
        if (this.mFragmentManager != null) {
            try {
                Fragment findFragmentByTag = this.mFragmentManager.findFragmentByTag("marketing_dialog");
                if (findFragmentByTag instanceof MarketingDialogFragment) {
                    ((MarketingDialogFragment) findFragmentByTag).dismissCampaign();
                }
            } catch (Exception e) {
                LocalyticsManager.throwOrLogError(RuntimeException.class, "Localytics library threw an uncaught exception", e);
            }
        }
    }

    private void _destroyLocalMarketingMessage(MarketingMessage marketingMessage) {
        int i = 0;
        try {
            int length;
            for (long j : _getConditionIdFromRuleId((long) _getRuleIdFromCampaignId(((Integer) marketingMessage.get("campaign_id")).intValue()))) {
                this.mProvider.remove("marketing_condition_values", String.format("%s = ?", new Object[]{"condition_id_ref"}), new String[]{Long.toString(j)});
            }
            this.mProvider.remove("marketing_conditions", String.format("%s = ?", new Object[]{"rule_id_ref"}), new String[]{Integer.toString(r2)});
            this.mProvider.remove("marketing_ruleevent", String.format("%s = ?", new Object[]{"rule_id_ref"}), new String[]{Integer.toString(r2)});
            this.mProvider.remove("marketing_rules", String.format("%s = ?", new Object[]{"_id"}), new String[]{Integer.toString(r2)});
            String str = (String) marketingMessage.get("base_path");
            if (str != null) {
                File file = new File(str);
                if (file.isDirectory()) {
                    String[] list = file.list();
                    length = list.length;
                    while (i < length) {
                        new File(file, list[i]).delete();
                        i++;
                    }
                }
                if (!file.delete()) {
                    Log.m12801w(String.format("Delete %s failed.", new Object[]{str}));
                }
                if (!new File(str + ".zip").delete()) {
                    Log.m12801w(String.format("Delete %s failed.", new Object[]{str + ".zip"}));
                }
            }
        } catch (Exception e) {
            LocalyticsManager.throwOrLogError(RuntimeException.class, "Localytics library threw an uncaught exception", e);
        }
    }

    protected boolean _validateMarketingMessage(MarketingMessage marketingMessage) {
        return (JsonHelper.getSafeIntegerFromMap(marketingMessage, "campaign_id") == 0 || TextUtils.isEmpty(JsonHelper.getSafeStringFromMap(marketingMessage, "rule_name")) || JsonHelper.getSafeListFromMap(marketingMessage, "display_events") == null || TextUtils.isEmpty(JsonHelper.getSafeStringFromMap(marketingMessage, "location")) || (((long) JsonHelper.getSafeIntegerFromMap(marketingMessage, "expiration")) <= this.mLocalyticsDao.getCurrentTimeMillis() / 1000 && !Constants.isTestModeEnabled())) ? false : true;
    }

    private ContentValues _parseMarketingMessage(MarketingMessage marketingMessage) {
        int i = 1;
        ContentValues contentValues = new ContentValues();
        contentValues.put("campaign_id", Integer.valueOf(JsonHelper.getSafeIntegerFromMap(marketingMessage, "campaign_id")));
        contentValues.put("expiration", Integer.valueOf(JsonHelper.getSafeIntegerFromMap(marketingMessage, "expiration")));
        contentValues.put("display_seconds", Integer.valueOf(JsonHelper.getSafeIntegerFromMap(marketingMessage, "display_seconds")));
        contentValues.put("display_session", Integer.valueOf(JsonHelper.getSafeIntegerFromMap(marketingMessage, "display_session")));
        contentValues.put(AppInfo.APP_VERSION_KEY, Integer.valueOf(JsonHelper.getSafeIntegerFromMap(marketingMessage, AppInfo.APP_VERSION_KEY)));
        contentValues.put("phone_location", JsonHelper.getSafeStringFromMap(marketingMessage, "phone_location"));
        Map safeMapFromMap = JsonHelper.getSafeMapFromMap(marketingMessage, "phone_size");
        contentValues.put("phone_size_width", Integer.valueOf(JsonHelper.getSafeIntegerFromMap(safeMapFromMap, "width")));
        contentValues.put("phone_size_height", Integer.valueOf(JsonHelper.getSafeIntegerFromMap(safeMapFromMap, "height")));
        safeMapFromMap = JsonHelper.getSafeMapFromMap(marketingMessage, "tablet_size");
        contentValues.put("tablet_location", JsonHelper.getSafeStringFromMap(marketingMessage, "tablet_location"));
        contentValues.put("tablet_size_width", Integer.valueOf(JsonHelper.getSafeIntegerFromMap(safeMapFromMap, "width")));
        contentValues.put("tablet_size_height", Integer.valueOf(JsonHelper.getSafeIntegerFromMap(safeMapFromMap, "height")));
        contentValues.put("time_to_display", Integer.valueOf(1));
        String str = "internet_required";
        if (!JsonHelper.getSafeBooleanFromMap(marketingMessage, "internet_required")) {
            i = 0;
        }
        contentValues.put(str, Integer.valueOf(i));
        contentValues.put("ab_test", JsonHelper.getSafeStringFromMap(marketingMessage, AB_ATTRIBUTE));
        contentValues.put("rule_name_non_unique", JsonHelper.getSafeStringFromMap(marketingMessage, "rule_name"));
        contentValues.put("rule_name", UUID.randomUUID().toString());
        contentValues.put("location", JsonHelper.getSafeStringFromMap(marketingMessage, "location"));
        contentValues.put("devices", JsonHelper.getSafeStringFromMap(marketingMessage, "devices"));
        contentValues.put("control_group", Integer.valueOf(JsonHelper.getSafeIntegerFromMap(marketingMessage, "control_group")));
        if (this.mConfigurations != null) {
            i = JsonHelper.getSafeIntegerFromMap(this.mConfigurations, "schema_version");
            if (i > 0) {
                contentValues.put("schema_version", Integer.valueOf(i));
            }
        }
        return contentValues;
    }

    private void _saveMarketingConditions(long j, List<Object> list) {
        if (list != null) {
            int i;
            long insert;
            for (long insert2 : _getConditionIdFromRuleId(j)) {
                this.mProvider.remove("marketing_condition_values", String.format("%s = ?", new Object[]{"condition_id_ref"}), new String[]{Long.toString(insert2)});
            }
            this.mProvider.remove("marketing_conditions", String.format("%s = ?", new Object[]{"rule_id_ref"}), new String[]{Long.toString(j)});
            Iterator it = list.iterator();
            while (it.hasNext()) {
                List list2 = (List) it.next();
                ContentValues contentValues = new ContentValues();
                contentValues.put("attribute_name", (String) list2.get(0));
                contentValues.put("operator", (String) list2.get(1));
                contentValues.put("rule_id_ref", Long.valueOf(j));
                insert2 = this.mProvider.insert("marketing_conditions", contentValues);
                for (i = 2; i < list2.size(); i++) {
                    ContentValues contentValues2 = new ContentValues();
                    contentValues2.put("value", JsonHelper.getSafeStringFromValue(list2.get(i)));
                    contentValues2.put("condition_id_ref", Long.valueOf(insert2));
                    this.mProvider.insert("marketing_condition_values", contentValues2);
                }
            }
        }
    }

    private void _bindRuleToEvents(long j, List<String> list) {
        this.mProvider.remove("marketing_ruleevent", String.format("%s = ?", new Object[]{"rule_id_ref"}), new String[]{Long.toString(j)});
        for (String str : list) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("event_name", str);
            contentValues.put("rule_id_ref", Long.valueOf(j));
            this.mProvider.insert("marketing_ruleevent", contentValues);
        }
    }

    void handleNotificationReceived(Intent intent) {
        queueMessage(obtainMessage(MESSAGE_HANDLE_PUSH_RECEIVED, intent));
    }

    void handleTestModeIntent(Intent intent) {
        if (intent == null) {
            throw new IllegalArgumentException("intent cannot be null");
        }
        Uri data = intent.getData();
        if (data != null && data.getScheme() != null && data.getScheme().equals("amp" + this.mLocalyticsDao.getApiKey())) {
            String substring = data.getPath().substring(1);
            Object host = data.getHost();
            String[] split = substring.split("[/]");
            if (split != null && split.length != 0 && !TextUtils.isEmpty(host) && host.equalsIgnoreCase("testMode")) {
                if (split[0].equalsIgnoreCase("enabled")) {
                    this.mLocalyticsDao.setTestModeEnabled(true);
                } else if (split[0].equalsIgnoreCase("launch") && split[1].equalsIgnoreCase("push")) {
                    Object obj = split[2];
                    Object obj2 = split[3];
                    String customerIdInMemory = this.mLocalyticsDao.getCustomerIdInMemory();
                    Context appContext = this.mLocalyticsDao.getAppContext();
                    if (!TextUtils.isEmpty(obj) && !TextUtils.isEmpty(obj2)) {
                        C07117 c07117 = new C07117(appContext, obj, obj2, customerIdInMemory);
                        Void[] voidArr = new Void[0];
                        if (c07117 instanceof AsyncTask) {
                            AsyncTaskInstrumentation.execute(c07117, voidArr);
                        } else {
                            c07117.execute(voidArr);
                        }
                    }
                }
            }
        }
    }

    private int _getRuleIdFromCampaignId(int i) {
        Cursor query;
        Throwable th;
        try {
            query = this.mProvider.query("marketing_rules", new String[]{"_id"}, String.format("%s = ?", new Object[]{"campaign_id"}), new String[]{Integer.toString(i)}, null);
            try {
                int i2;
                if (query.moveToFirst()) {
                    i2 = query.getInt(query.getColumnIndexOrThrow("_id"));
                } else {
                    i2 = 0;
                }
                if (query != null) {
                    query.close();
                }
                return i2;
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

    private long[] _getConditionIdFromRuleId(long j) {
        Cursor query;
        Throwable th;
        try {
            query = this.mProvider.query("marketing_conditions", new String[]{"_id"}, String.format("%s = ?", new Object[]{"rule_id_ref"}), new String[]{Long.toString(j)}, null);
            try {
                long[] jArr = new long[query.getCount()];
                int i = 0;
                while (query.moveToNext()) {
                    int i2 = i + 1;
                    jArr[i] = (long) query.getInt(query.getColumnIndexOrThrow("_id"));
                    i = i2;
                }
                if (query != null) {
                    query.close();
                }
                return jArr;
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

    protected int _getMaxRowToUpload() {
        return 1;
    }

    protected TreeMap<Integer, Object> _getDataToUpload() {
        TreeMap<Integer, Object> treeMap = new TreeMap();
        treeMap.put(Integer.valueOf(0), com.newrelic.agent.android.instrumentation.Trace.NULL);
        return treeMap;
    }

    protected BaseUploadThread getUploadThread(TreeMap<Integer, Object> treeMap, String str) {
        return new MarketingDownloader(this, treeMap, str, this.mLocalyticsDao);
    }

    protected void _init() {
        this.mProvider = new MarketingProvider(this.siloName.toLowerCase(), this.mLocalyticsDao);
    }

    void _marketingTrigger(String str, Map<String, String> map) {
        MarketingMessage _getQualifiedMarketingMessageForTrigger = _getQualifiedMarketingMessageForTrigger(str, map);
        if (_getQualifiedMarketingMessageForTrigger != null) {
            Integer num = (Integer) _getQualifiedMarketingMessageForTrigger.get("campaign_id");
            Integer num2 = (Integer) _getQualifiedMarketingMessageForTrigger.get("control_group");
            if (num2 == null || num2.intValue() != 1) {
                _tryDisplayingInAppCampaign(_getQualifiedMarketingMessageForTrigger, map);
            } else if (_setMarketingMessageAsDisplayed(num.intValue())) {
                _tagAmpActionEventForControlGroup(_getQualifiedMarketingMessageForTrigger);
            }
        }
    }

    MarketingMessage _getQualifiedMarketingMessageForTrigger(String str, Map<String, String> map) {
        if (Constants.isTestModeEnabled()) {
            return null;
        }
        if (str.equals(Constants.PUSH_ACK_TYPE_OPEN)) {
            if (this.sessionStartMarketingMessageShown || Constants.isTestModeEnabled()) {
                return null;
            }
            str = "AMP Loaded";
            this.sessionStartMarketingMessageShown = true;
        }
        List _getMarketingMessageMaps = _getMarketingMessageMaps(str);
        if (_getMarketingMessageMaps.size() == 0) {
            Context appContext = this.mLocalyticsDao.getAppContext();
            if (str.startsWith(appContext.getPackageName())) {
                _getMarketingMessageMaps = _getMarketingMessageMaps(str.substring(appContext.getPackageName().length() + 1, str.length()));
            }
        }
        return _retrieveDisplayingCandidate(_filterMarketingMessagesDisqualifiedByFrequencyCappingRules(_getMarketingMessageMaps), map);
    }

    void _tryDisplayingInAppCampaign(MarketingMessage marketingMessage, Map<String, String> map) {
        new Handler(Looper.getMainLooper()).post(new C07138(marketingMessage, _getJavaScriptClientCallbacks(map)));
    }

    private void _tagAmpActionEventForControlGroup(MarketingMessage marketingMessage) {
        Map treeMap = new TreeMap();
        String obj = marketingMessage.get("campaign_id").toString();
        String obj2 = marketingMessage.get("rule_name_non_unique").toString();
        String obj3 = marketingMessage.get("schema_version").toString();
        String str = (String) marketingMessage.get("ab_test");
        if (!TextUtils.isEmpty(str)) {
            treeMap.put("ampAB", str);
        }
        treeMap.put("ampAction", "control");
        treeMap.put(Constants.BUNDLE_TYPE, "Control");
        treeMap.put("ampCampaignId", obj);
        treeMap.put("ampCampaign", obj2);
        treeMap.put("Schema Version - Client", String.valueOf(3));
        treeMap.put("Schema Version - Server", obj3);
        this.mLocalyticsDao.tagEvent("ampView", treeMap);
    }

    InAppMessageDismissButtonLocation getInAppDismissButtonLocation() {
        return MarketingDialogFragment.getInAppDismissButtonLocation();
    }

    void setInAppDismissButtonLocation(InAppMessageDismissButtonLocation inAppMessageDismissButtonLocation) {
        MarketingDialogFragment.setInAppDismissButtonLocation(inAppMessageDismissButtonLocation);
    }

    List<MarketingMessage> getMarketingMessages() {
        return getList(new C07149());
    }

    private void __showMarketingTest() {
        if (this.mFragmentManager != null && this.mFragmentManager.findFragmentByTag("marketing_test_mode_button") == null && this.mFragmentManager.findFragmentByTag("marketing_test_mode_list") == null) {
            TestModeButton newInstance = TestModeButton.newInstance();
            TestModeListView newInstance2 = TestModeListView.newInstance();
            Context appContext = this.mLocalyticsDao.getAppContext();
            MarketingRulesAdapter marketingRulesAdapter = new MarketingRulesAdapter(appContext, this);
            newInstance2.setAdapter(marketingRulesAdapter);
            Map treeMap = new TreeMap();
            treeMap.put(Integer.valueOf(9), new AnonymousClass10(marketingRulesAdapter, newInstance2));
            newInstance.setCallbacks(treeMap);
            newInstance.show(this.mFragmentManager, "marketing_test_mode_button");
            this.mFragmentManager.executePendingTransactions();
            treeMap = new TreeMap();
            treeMap.put(Integer.valueOf(10), new AnonymousClass11(newInstance));
            treeMap.put(Integer.valueOf(12), new AnonymousClass12(marketingRulesAdapter));
            treeMap.put(Integer.valueOf(13), new AnonymousClass13(appContext));
            treeMap.put(Integer.valueOf(14), new AnonymousClass14(appContext));
            treeMap.put(Integer.valueOf(11), new AnonymousClass15(appContext));
            newInstance2.setCallbacks(treeMap);
        }
    }

    private List<MarketingMessage> _getMarketingMessageMaps(String str) {
        Throwable th;
        List linkedList = new LinkedList();
        Cursor cursor = null;
        try {
            Cursor rawQuery;
            String l = Long.toString(this.mLocalyticsDao.getCurrentTimeMillis() / 1000);
            String format = String.format("SELECT * FROM %s AS r LEFT OUTER JOIN %s AS re ON r.%s=re.%s WHERE %s > ? AND %s = ? GROUP BY r.%s;", new Object[]{"marketing_rules", "marketing_ruleevent", "_id", "rule_id_ref", "expiration", "event_name", "_id"});
            SQLiteDatabase sQLiteDatabase = this.mProvider.mDb;
            String[] strArr = new String[]{l, str};
            if (sQLiteDatabase instanceof SQLiteDatabase) {
                rawQuery = SQLiteInstrumentation.rawQuery(sQLiteDatabase, format, strArr);
            } else {
                rawQuery = sQLiteDatabase.rawQuery(format, strArr);
            }
            int i = 0;
            while (i < rawQuery.getCount()) {
                try {
                    rawQuery.moveToPosition(i);
                    MarketingMessage marketingMessage = new MarketingMessage();
                    marketingMessage.put("_id", Integer.valueOf(rawQuery.getInt(rawQuery.getColumnIndexOrThrow("_id"))));
                    marketingMessage.put("campaign_id", Integer.valueOf(rawQuery.getInt(rawQuery.getColumnIndexOrThrow("campaign_id"))));
                    marketingMessage.put("expiration", Integer.valueOf(rawQuery.getInt(rawQuery.getColumnIndexOrThrow("expiration"))));
                    marketingMessage.put("display_seconds", Integer.valueOf(rawQuery.getInt(rawQuery.getColumnIndexOrThrow("display_seconds"))));
                    marketingMessage.put("display_session", Integer.valueOf(rawQuery.getInt(rawQuery.getColumnIndexOrThrow("display_session"))));
                    marketingMessage.put(AppInfo.APP_VERSION_KEY, rawQuery.getString(rawQuery.getColumnIndexOrThrow(AppInfo.APP_VERSION_KEY)));
                    marketingMessage.put("phone_location", rawQuery.getString(rawQuery.getColumnIndexOrThrow("phone_location")));
                    marketingMessage.put("phone_size_width", Integer.valueOf(rawQuery.getInt(rawQuery.getColumnIndexOrThrow("phone_size_width"))));
                    marketingMessage.put("phone_size_height", Integer.valueOf(rawQuery.getInt(rawQuery.getColumnIndexOrThrow("phone_size_height"))));
                    marketingMessage.put("tablet_location", rawQuery.getString(rawQuery.getColumnIndexOrThrow("tablet_location")));
                    marketingMessage.put("tablet_size_width", Integer.valueOf(rawQuery.getInt(rawQuery.getColumnIndexOrThrow("tablet_size_width"))));
                    marketingMessage.put("tablet_size_height", Integer.valueOf(rawQuery.getInt(rawQuery.getColumnIndexOrThrow("tablet_size_height"))));
                    marketingMessage.put("time_to_display", Integer.valueOf(rawQuery.getInt(rawQuery.getColumnIndexOrThrow("time_to_display"))));
                    marketingMessage.put("internet_required", Integer.valueOf(rawQuery.getInt(rawQuery.getColumnIndexOrThrow("internet_required"))));
                    marketingMessage.put("ab_test", rawQuery.getString(rawQuery.getColumnIndexOrThrow("ab_test")));
                    marketingMessage.put("rule_name_non_unique", rawQuery.getString(rawQuery.getColumnIndexOrThrow("rule_name_non_unique")));
                    marketingMessage.put("location", rawQuery.getString(rawQuery.getColumnIndexOrThrow("location")));
                    marketingMessage.put("devices", rawQuery.getString(rawQuery.getColumnIndexOrThrow("devices")));
                    marketingMessage.put("control_group", Integer.valueOf(rawQuery.getInt(rawQuery.getColumnIndexOrThrow("control_group"))));
                    marketingMessage.put("schema_version", Integer.valueOf(rawQuery.getInt(rawQuery.getColumnIndexOrThrow("schema_version"))));
                    linkedList.add(marketingMessage);
                    i++;
                } catch (Throwable th2) {
                    Throwable th3 = th2;
                    cursor = rawQuery;
                    th = th3;
                }
            }
            if (rawQuery != null) {
                rawQuery.close();
            }
            return linkedList;
        } catch (Throwable th4) {
            th = th4;
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    private MarketingMessage _retrieveDisplayingCandidate(List<MarketingMessage> list, Map<String, String> map) {
        MarketingMessage marketingMessage = null;
        for (MarketingMessage marketingMessage2 : list) {
            if (((((Integer) marketingMessage2.get("internet_required")).intValue() == 1 ? 1 : null) == null || _isConnectingToInternet()) && _isMarketingMessageSatisfiedConditions(marketingMessage2, map)) {
                marketingMessage = marketingMessage2;
            }
        }
        if (marketingMessage == null || !_updateDisplayingCandidate(marketingMessage)) {
            return null;
        }
        return marketingMessage;
    }

    SparseArray<MarketingCallable> getDialogCallbacks() {
        SparseArray<MarketingCallable> sparseArray = new SparseArray();
        sparseArray.put(1, new MarketingCallable() {
            public Object call(Object[] objArr) {
                ((MessagingListener) MarketingHandler.this.listeners).localyticsDidDismissInAppMessage();
                return null;
            }
        });
        sparseArray.put(2, new MarketingCallable() {
            public Object call(Object[] objArr) {
                MarketingHandler.this.mLocalyticsDao.tagEvent((String) objArr[0], (Map) objArr[1]);
                return null;
            }
        });
        sparseArray.put(16, new MarketingCallable() {
            public Object call(Object[] objArr) {
                ((MessagingListener) MarketingHandler.this.listeners).localyticsDidDisplayInAppMessage();
                return null;
            }
        });
        sparseArray.put(17, new MarketingCallable() {
            public Object call(Object[] objArr) {
                ((MessagingListener) MarketingHandler.this.listeners).localyticsWillDismissInAppMessage();
                return null;
            }
        });
        return sparseArray;
    }

    private SparseArray<MarketingCallable> _getJavaScriptClientCallbacks(Map<String, String> map) {
        SparseArray<MarketingCallable> sparseArray = new SparseArray();
        sparseArray.put(3, new MarketingCallable() {
            Object call(Object[] objArr) {
                String str = (String) objArr[0];
                String str2 = (String) objArr[1];
                String str3 = (String) objArr[2];
                long j = 0;
                Map hashMap = new HashMap();
                if (TextUtils.isEmpty(str)) {
                    LocalyticsManager.throwOrLogError(IllegalArgumentException.class, "event cannot be null or empty");
                }
                try {
                    if (!(TextUtils.isEmpty(str2) || MarketingHandler.JS_STRINGS_THAT_MEAN_NULL.contains(str2))) {
                        for (Entry entry : JsonHelper.toMap(JSONObjectInstrumentation.init(str2)).entrySet()) {
                            hashMap.put(entry.getKey(), String.valueOf(entry.getValue()));
                        }
                    }
                    try {
                        j = Long.valueOf(str3).longValue();
                    } catch (Exception e) {
                        try {
                            if (!(TextUtils.isEmpty(str3) || MarketingHandler.JS_STRINGS_THAT_MEAN_NULL.contains(str3))) {
                                for (Entry entry2 : JsonHelper.toMap(JSONObjectInstrumentation.init(str3)).entrySet()) {
                                    hashMap.put(entry2.getKey(), String.valueOf(entry2.getValue()));
                                }
                            }
                        } catch (JSONException e2) {
                        }
                    }
                    if (str2 != null) {
                        if (hashMap.isEmpty()) {
                            Log.m12801w("attributes is empty.  Did the caller make an error?");
                        }
                        if (hashMap.size() > 50) {
                            Log.m12801w(String.format("attributes size is %d, exceeding the maximum size of %d.  Did the caller make an error?", new Object[]{Integer.valueOf(hashMap.size()), Integer.valueOf(50)}));
                        }
                        for (Entry entry3 : hashMap.entrySet()) {
                            str3 = (String) entry3.getKey();
                            CharSequence valueOf = String.valueOf(entry3.getValue());
                            if (TextUtils.isEmpty(str3)) {
                                LocalyticsManager.throwOrLogError(IllegalArgumentException.class, "attributes cannot contain null or empty keys");
                            }
                            if (TextUtils.isEmpty(valueOf)) {
                                LocalyticsManager.throwOrLogError(IllegalArgumentException.class, "attributes cannot contain null or empty values");
                            }
                        }
                    }
                    MarketingHandler.this.mLocalyticsDao.tagEvent(str, hashMap, j);
                } catch (JSONException e3) {
                    Log.m12801w("[JavaScriptClient]: Failed to parse the json object in tagEventNative");
                }
                return null;
            }
        });
        sparseArray.put(5, new AnonymousClass21(this.mLocalyticsDao.getIdentifiers()));
        JSONObject jSONObject = new JSONObject();
        for (int i = 0; i < 10; i++) {
            try {
                jSONObject.put("c" + i, this.mLocalyticsDao.getCustomDimension(i));
            } catch (JSONException e) {
                Log.m12801w("[JavaScriptClient]: Failed to get custom dimension");
            }
        }
        sparseArray.put(6, new AnonymousClass22(jSONObject));
        sparseArray.put(7, new AnonymousClass23(map));
        sparseArray.put(8, new MarketingCallable() {
            Object call(Object[] objArr) {
                MarketingHandler.this.mLocalyticsDao.setCustomDimension(((Integer) objArr[0]).intValue(), (String) objArr[1]);
                return null;
            }
        });
        return sparseArray;
    }

    private boolean _isConnectingToInternet() {
        ConnectivityManager connectivityManager = (ConnectivityManager) this.mLocalyticsDao.getAppContext().getSystemService("connectivity");
        if (connectivityManager != null) {
            NetworkInfo[] allNetworkInfo = connectivityManager.getAllNetworkInfo();
            if (allNetworkInfo != null) {
                for (NetworkInfo state : allNetworkInfo) {
                    if (state.getState() == State.CONNECTED) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean _isMarketingMessageSatisfiedConditions(MarketingMessage marketingMessage, Map<String, String> map) {
        Vector _getMarketingConditions = _getMarketingConditions(_getRuleIdFromCampaignId(((Integer) marketingMessage.get("campaign_id")).intValue()));
        if (_getMarketingConditions != null) {
            Iterator it = _getMarketingConditions.iterator();
            while (it.hasNext()) {
                if (!((MarketingCondition) it.next()).isSatisfiedByAttributes(map)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean _updateDisplayingCandidate(MarketingMessage marketingMessage) {
        CharSequence charSequence = null;
        int intValue = ((Integer) marketingMessage.get("_id")).intValue();
        String _getZipFileDirPath = _getZipFileDirPath();
        String _getUnzipFileDirPath = _getUnzipFileDirPath(intValue);
        boolean endsWith = _getRemoteFileURL(marketingMessage).endsWith(".zip");
        if (!_doesCreativeExist(intValue, endsWith)) {
            Object remoteFileURL = MarketingDownloader.getRemoteFileURL(marketingMessage);
            Object localFileURL = MarketingDownloader.getLocalFileURL((long) intValue, endsWith, this.mLocalyticsDao.getAppContext(), this.mLocalyticsDao.getApiKey());
            if (!(TextUtils.isEmpty(remoteFileURL) || TextUtils.isEmpty(localFileURL))) {
                MarketingDownloader.downloadFile(remoteFileURL, localFileURL, true, this.mLocalyticsDao.getProxy());
            }
        }
        if (endsWith) {
            if (_decompressZipFile(_getZipFileDirPath, _getUnzipFileDirPath, String.format("amp_rule_%d.zip", new Object[]{Integer.valueOf(intValue)}))) {
                charSequence = "file://" + _getUnzipFileDirPath + File.separator + "index.html";
            }
        } else {
            Object obj = "file://" + _getUnzipFileDirPath + File.separator + "index.html";
        }
        if (TextUtils.isEmpty(charSequence)) {
            return false;
        }
        int intValue2;
        String str = (String) marketingMessage.get("devices");
        if (str.equals("tablet")) {
            intValue2 = ((Integer) marketingMessage.get("tablet_size_width")).intValue();
            intValue = ((Integer) marketingMessage.get("tablet_size_height")).intValue();
        } else if (str.equals("both")) {
            intValue2 = ((Integer) marketingMessage.get("phone_size_width")).intValue();
            intValue = ((Integer) marketingMessage.get("phone_size_height")).intValue();
        } else {
            intValue2 = ((Integer) marketingMessage.get("phone_size_width")).intValue();
            intValue = ((Integer) marketingMessage.get("phone_size_height")).intValue();
        }
        marketingMessage.put("html_url", charSequence);
        marketingMessage.put("base_path", _getUnzipFileDirPath);
        marketingMessage.put("display_width", Float.valueOf((float) intValue2));
        marketingMessage.put("display_height", Float.valueOf((float) intValue));
        return true;
    }

    private Vector<MarketingCondition> _getMarketingConditions(int i) {
        Throwable th;
        Vector<MarketingCondition> vector = null;
        Cursor query;
        try {
            query = this.mProvider.query("marketing_conditions", null, String.format("%s = ?", new Object[]{"rule_id_ref"}), new String[]{Integer.toString(i)}, null);
            while (query.moveToNext()) {
                try {
                    int i2 = query.getInt(query.getColumnIndexOrThrow("_id"));
                    String string = query.getString(query.getColumnIndexOrThrow("attribute_name"));
                    String string2 = query.getString(query.getColumnIndexOrThrow("operator"));
                    Vector _getMarketingConditionValues = _getMarketingConditionValues(i2);
                    if (vector == null) {
                        vector = new Vector();
                    }
                    MarketingCondition marketingCondition = new MarketingCondition(string, string2, _getMarketingConditionValues);
                    marketingCondition.setPackageName(this.mLocalyticsDao.getAppContext().getPackageName());
                    vector.add(marketingCondition);
                } catch (Throwable th2) {
                    th = th2;
                }
            }
            if (query != null) {
                query.close();
            }
            return vector;
        } catch (Throwable th3) {
            th = th3;
            query = null;
            if (query != null) {
                query.close();
            }
            throw th;
        }
    }

    private String _getZipFileDirPath() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.mLocalyticsDao.getAppContext().getFilesDir().getAbsolutePath());
        stringBuilder.append(File.separator);
        stringBuilder.append(".localytics");
        stringBuilder.append(File.separator);
        stringBuilder.append(this.mLocalyticsDao.getApiKey());
        return stringBuilder.toString();
    }

    private String _getUnzipFileDirPath(int i) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.mLocalyticsDao.getAppContext().getFilesDir().getAbsolutePath());
        stringBuilder.append(File.separator);
        stringBuilder.append(".localytics");
        stringBuilder.append(File.separator);
        stringBuilder.append(this.mLocalyticsDao.getApiKey());
        stringBuilder.append(File.separator);
        stringBuilder.append(String.format("marketing_rule_%d", new Object[]{Integer.valueOf(i)}));
        String stringBuilder2 = stringBuilder.toString();
        File file = new File(stringBuilder2);
        if ((file.exists() && file.isDirectory()) || file.mkdirs()) {
            return stringBuilder2;
        }
        Log.m12801w(String.format("Could not create the directory %s for saving the decompressed file.", new Object[]{file.getAbsolutePath()}));
        return null;
    }

    private String _getRemoteFileURL(MarketingMessage marketingMessage) {
        String str = (String) marketingMessage.get("devices");
        if (str.compareTo("tablet") == 0) {
            return (String) marketingMessage.get("tablet_location");
        }
        if (str.compareTo("both") == 0) {
            return (String) marketingMessage.get("phone_location");
        }
        return (String) marketingMessage.get("phone_location");
    }

    private boolean _doesCreativeExist(int i, boolean z) {
        File file;
        if (z) {
            file = new File(_getZipFileDirPath() + File.separator + String.format("amp_rule_%d.zip", new Object[]{Integer.valueOf(i)}));
        } else {
            file = new File(_getUnzipFileDirPath(i) + File.separator + "index.html");
        }
        return file.exists();
    }

    private boolean _decompressZipFile(String str, String str2, String str3) {
        Throwable e;
        ZipInputStream zipInputStream;
        try {
            zipInputStream = new ZipInputStream(new FileInputStream(str + File.separator + str3));
            byte[] bArr = new byte[AccessibilityNodeInfoCompat.ACTION_SCROLL_BACKWARD];
            while (true) {
                ZipEntry nextEntry = zipInputStream.getNextEntry();
                if (nextEntry == null) {
                    break;
                }
                String str4 = str2 + File.separator + nextEntry.getName();
                if (!nextEntry.isDirectory()) {
                    try {
                        FileOutputStream fileOutputStream = new FileOutputStream(str4);
                        while (true) {
                            int read = zipInputStream.read(bArr, 0, bArr.length);
                            if (read <= 0) {
                                break;
                            }
                            fileOutputStream.write(bArr, 0, read);
                        }
                        fileOutputStream.close();
                        zipInputStream.closeEntry();
                    } catch (IOException e2) {
                        e = e2;
                    }
                } else if (!new File(str4).mkdir()) {
                    Log.m12801w(String.format("Could not create directory %s", new Object[]{str4}));
                }
            }
            if (zipInputStream != null) {
                try {
                    zipInputStream.close();
                } catch (Throwable e3) {
                    Log.m12802w("Caught IOException", e3);
                    return false;
                }
            }
            return true;
        } catch (IOException e4) {
            e3 = e4;
            zipInputStream = null;
            try {
                Log.m12802w("Caught IOException", e3);
                if (zipInputStream == null) {
                    return false;
                }
                try {
                    zipInputStream.close();
                    return false;
                } catch (Throwable e32) {
                    Log.m12802w("Caught IOException", e32);
                    return false;
                }
            } catch (Throwable th) {
                e32 = th;
                if (zipInputStream != null) {
                    try {
                        zipInputStream.close();
                    } catch (Throwable e322) {
                        Log.m12802w("Caught IOException", e322);
                        return false;
                    }
                }
                throw e322;
            }
        } catch (Throwable th2) {
            e322 = th2;
            zipInputStream = null;
            if (zipInputStream != null) {
                zipInputStream.close();
            }
            throw e322;
        }
    }

    private Vector<String> _getMarketingConditionValues(int i) {
        Throwable th;
        Vector<String> vector = null;
        Cursor query;
        try {
            query = this.mProvider.query("marketing_condition_values", new String[]{"value"}, String.format("%s = ?", new Object[]{"condition_id_ref"}), new String[]{Integer.toString(i)}, null);
            while (query.moveToNext()) {
                try {
                    String string = query.getString(query.getColumnIndexOrThrow("value"));
                    if (vector == null) {
                        vector = new Vector();
                    }
                    vector.add(string);
                } catch (Throwable th2) {
                    th = th2;
                }
            }
            if (query != null) {
                query.close();
            }
            return vector;
        } catch (Throwable th3) {
            th = th3;
            query = null;
            if (query != null) {
                query.close();
            }
            throw th;
        }
    }

    void showMarketingTest() {
        queueMessage(obtainMessage(MESSAGE_SHOW_MARKETING_TEST, this.mLocalyticsDao.getCustomerIdInMemory()));
    }

    void setDismissButtonImage(Bitmap bitmap) {
        MarketingDialogFragment.setDismissButtonImage(bitmap);
    }

    public void localyticsSessionWillOpen(boolean z, boolean z2, boolean z3) {
    }

    public void localyticsSessionDidOpen(boolean z, boolean z2, boolean z3) {
        if (!z3) {
            upload(this.mLocalyticsDao.getCustomerIdInMemory());
        }
        if (!Constants.isTestModeEnabled()) {
            if (z) {
                this.mLocalyticsDao.triggerInAppMessage("AMP First Run");
            }
            if (z2) {
                this.mLocalyticsDao.triggerInAppMessage("AMP upgrade");
            }
            if (!z3) {
                this.sessionStartMarketingMessageShown = false;
                this.mLocalyticsDao.triggerInAppMessage(Constants.PUSH_ACK_TYPE_OPEN, null, true);
            }
        }
    }

    public void localyticsSessionWillClose() {
    }

    public void localyticsDidTagEvent(String str, Map<String, String> map, long j) {
        this.mLocalyticsDao.triggerInAppMessage(str, map);
    }
}
