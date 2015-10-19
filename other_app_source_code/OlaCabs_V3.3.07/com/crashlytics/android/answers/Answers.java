package com.crashlytics.android.answers;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.os.Build.VERSION;
import java.io.File;
import java.util.Map;
import java.util.UUID;
import p004b.p005a.p006a.p007a.Fabric;
import p004b.p005a.p006a.p007a.Kit;
import p004b.p005a.p006a.p007a.p008a.p010b.CommonUtils;
import p004b.p005a.p006a.p007a.p008a.p010b.Crash.Crash;
import p004b.p005a.p006a.p007a.p008a.p010b.IdManager;
import p004b.p005a.p006a.p007a.p008a.p010b.SystemCurrentTimeProvider;
import p004b.p005a.p006a.p007a.p008a.p013d.GZIPQueueFileEventStorage;
import p004b.p005a.p006a.p007a.p008a.p014e.DefaultHttpRequestFactory;
import p004b.p005a.p006a.p007a.p008a.p015f.FileStoreImpl;
import p004b.p005a.p006a.p007a.p008a.p015f.PreferenceStore;
import p004b.p005a.p006a.p007a.p008a.p015f.PreferenceStoreImpl;
import p004b.p005a.p006a.p007a.p008a.p016g.Settings;
import p004b.p005a.p006a.p007a.p008a.p016g.SettingsData;

public class Answers extends Kit<Boolean> {
    static final String CRASHLYTICS_API_ENDPOINT = "com.crashlytics.ApiEndpoint";
    static final long FIRST_LAUNCH_INTERVAL_IN_MS = 3600000;
    static final String PREFKEY_ANALYTICS_LAUNCHED = "analytics_launched";
    static final String SESSION_ANALYTICS_FILE_EXTENSION = ".tap";
    static final String SESSION_ANALYTICS_FILE_NAME = "session_analytics.tap";
    private static final String SESSION_ANALYTICS_TO_SEND_DIR = "session_analytics_to_send";
    public static final String TAG = "Answers";
    private long installedAt;
    private PreferenceStore preferenceStore;
    SessionAnalyticsManager sessionAnalyticsManager;
    private String versionCode;
    private String versionName;

    public static Answers getInstance() {
        return (Answers) Fabric.m504a(Answers.class);
    }

    public void logEvent(String str) {
        logEvent(str, new EventAttributes());
    }

    public void logEvent(String str, EventAttributes eventAttributes) {
        if (str == null) {
            throw new NullPointerException("eventName must not be null");
        } else if (eventAttributes == null) {
            throw new NullPointerException("attributes must not be null");
        } else if (this.sessionAnalyticsManager != null) {
            this.sessionAnalyticsManager.onCustom(str, eventAttributes.attributes);
        }
    }

    public void onException(Crash crash) {
        if (this.sessionAnalyticsManager != null) {
            this.sessionAnalyticsManager.onError(crash.m192a());
        }
    }

    public void onException(Crash crash) {
        if (this.sessionAnalyticsManager != null) {
            this.sessionAnalyticsManager.onCrash(crash.m192a());
        }
    }

    @SuppressLint({"NewApi"})
    protected boolean onPreExecute() {
        try {
            this.preferenceStore = new PreferenceStoreImpl(this);
            Context context = getContext();
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            this.versionCode = Integer.toString(packageInfo.versionCode);
            this.versionName = packageInfo.versionName == null ? "0.0" : packageInfo.versionName;
            if (VERSION.SDK_INT >= 9) {
                this.installedAt = packageInfo.firstInstallTime;
            } else {
                this.installedAt = new File(context.getPackageManager().getApplicationInfo(context.getPackageName(), 0).sourceDir).lastModified();
            }
            return true;
        } catch (Throwable e) {
            Fabric.m512h().m482e(TAG, "Error setting up app properties", e);
            return false;
        }
    }

    protected Boolean doInBackground() {
        Context context = getContext();
        initializeSessionAnalytics(context);
        try {
            SettingsData b = Settings.m462a().m466b();
            if (b == null) {
                return Boolean.valueOf(false);
            }
            if (b.f382d.f352d) {
                this.sessionAnalyticsManager.setAnalyticsSettingsData(b.f383e, getOverridenSpiEndpoint());
                return Boolean.valueOf(true);
            }
            CommonUtils.m164a(context, "Disabling analytics collection based on settings flag value.");
            this.sessionAnalyticsManager.disable();
            return Boolean.valueOf(false);
        } catch (Throwable e) {
            Fabric.m512h().m482e(TAG, "Error dealing with settings", e);
            return Boolean.valueOf(false);
        }
    }

    public String getIdentifier() {
        return "com.crashlytics.sdk.android:answers";
    }

    public String getVersion() {
        return "1.2.2.56";
    }

    @SuppressLint({"CommitPrefEdits"})
    @TargetApi(14)
    private void initializeSessionAnalytics(Context context) {
        try {
            SessionAnalyticsFilesManager sessionAnalyticsFilesManager = new SessionAnalyticsFilesManager(context, new SessionEventTransform(), new SystemCurrentTimeProvider(), new GZIPQueueFileEventStorage(getContext(), getSdkDirectory(), SESSION_ANALYTICS_FILE_NAME, SESSION_ANALYTICS_TO_SEND_DIR));
            IdManager idManager = getIdManager();
            Map g = idManager.m217g();
            SessionEventMetadata sessionEventMetadata = new SessionEventMetadata(context.getPackageName(), UUID.randomUUID().toString(), idManager.m212b(), (String) g.get(IdManager.IdManager.ANDROID_ID), (String) g.get(IdManager.IdManager.ANDROID_ADVERTISING_ID), (String) g.get(IdManager.IdManager.FONT_TOKEN), CommonUtils.m190m(context), idManager.m214d(), idManager.m215e(), this.versionCode, this.versionName);
            Application application = (Application) getContext().getApplicationContext();
            if (application == null || VERSION.SDK_INT < 14) {
                this.sessionAnalyticsManager = SessionAnalyticsManager.build(context, sessionEventMetadata, sessionAnalyticsFilesManager, new DefaultHttpRequestFactory(Fabric.m512h()));
            } else {
                this.sessionAnalyticsManager = AutoSessionAnalyticsManager.build(application, sessionEventMetadata, sessionAnalyticsFilesManager, new DefaultHttpRequestFactory(Fabric.m512h()));
            }
            if (isFirstLaunch(this.installedAt)) {
                Fabric.m512h().m474a(TAG, "First launch");
                this.sessionAnalyticsManager.onInstall();
                this.preferenceStore.m416a(this.preferenceStore.m417b().putBoolean(PREFKEY_ANALYTICS_LAUNCHED, true));
            }
        } catch (Throwable e) {
            CommonUtils.m165a(context, "Crashlytics failed to initialize session analytics.", e);
        }
    }

    String getOverridenSpiEndpoint() {
        return CommonUtils.m175b(getContext(), CRASHLYTICS_API_ENDPOINT);
    }

    boolean getAnalyticsLaunched() {
        return this.preferenceStore.m415a().getBoolean(PREFKEY_ANALYTICS_LAUNCHED, false);
    }

    boolean isFirstLaunch(long j) {
        return !getAnalyticsLaunched() && installedRecently(j);
    }

    boolean installedRecently(long j) {
        return System.currentTimeMillis() - j < FIRST_LAUNCH_INTERVAL_IN_MS;
    }

    File getSdkDirectory() {
        return new FileStoreImpl(this).m413a();
    }
}
