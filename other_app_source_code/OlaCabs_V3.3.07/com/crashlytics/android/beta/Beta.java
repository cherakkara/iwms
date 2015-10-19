package com.crashlytics.android.beta;

import android.annotation.TargetApi;
import android.app.Application;
import android.content.Context;
import android.os.Build.VERSION;
import android.text.TextUtils;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import p004b.p005a.p006a.p007a.Fabric;
import p004b.p005a.p006a.p007a.Kit;
import p004b.p005a.p006a.p007a.p008a.p009a.MemoryValueCache;
import p004b.p005a.p006a.p007a.p008a.p010b.CommonUtils;
import p004b.p005a.p006a.p007a.p008a.p010b.DeviceIdentifierProvider;
import p004b.p005a.p006a.p007a.p008a.p010b.IdManager;
import p004b.p005a.p006a.p007a.p008a.p010b.SystemCurrentTimeProvider;
import p004b.p005a.p006a.p007a.p008a.p014e.DefaultHttpRequestFactory;
import p004b.p005a.p006a.p007a.p008a.p015f.PreferenceStoreImpl;
import p004b.p005a.p006a.p007a.p008a.p016g.BetaSettingsData;
import p004b.p005a.p006a.p007a.p008a.p016g.Settings;
import p004b.p005a.p006a.p007a.p008a.p016g.SettingsData;

public class Beta extends Kit<Boolean> implements DeviceIdentifierProvider {
    private static final String CRASHLYTICS_API_ENDPOINT = "com.crashlytics.ApiEndpoint";
    private static final String CRASHLYTICS_BUILD_PROPERTIES = "crashlytics-build.properties";
    static final String NO_DEVICE_TOKEN = "";
    public static final String TAG = "Beta";
    private final MemoryValueCache<String> deviceTokenCache;
    private final DeviceTokenLoader deviceTokenLoader;
    private UpdatesController updatesController;

    public Beta() {
        this.deviceTokenCache = new MemoryValueCache();
        this.deviceTokenLoader = new DeviceTokenLoader();
    }

    public static Beta getInstance() {
        return (Beta) Fabric.m504a(Beta.class);
    }

    @TargetApi(14)
    protected boolean onPreExecute() {
        this.updatesController = createUpdatesController(VERSION.SDK_INT, (Application) getContext().getApplicationContext());
        return true;
    }

    protected Boolean doInBackground() {
        Fabric.m512h().m474a(TAG, "Beta kit initializing...");
        Context context = getContext();
        IdManager idManager = getIdManager();
        if (TextUtils.isEmpty(getBetaDeviceToken(context, idManager.m218h()))) {
            Fabric.m512h().m474a(TAG, "A Beta device token was not found for this app");
            return Boolean.valueOf(false);
        }
        Fabric.m512h().m474a(TAG, "Beta device token is present, checking for app updates.");
        BetaSettingsData betaSettingsData = getBetaSettingsData();
        BuildProperties loadBuildProperties = loadBuildProperties(context);
        if (canCheckForUpdates(betaSettingsData, loadBuildProperties)) {
            this.updatesController.initialize(context, this, idManager, betaSettingsData, loadBuildProperties, new PreferenceStoreImpl(this), new SystemCurrentTimeProvider(), new DefaultHttpRequestFactory(Fabric.m512h()));
        }
        return Boolean.valueOf(true);
    }

    @TargetApi(14)
    UpdatesController createUpdatesController(int i, Application application) {
        if (i >= 14) {
            return new ActivityLifecycleCheckForUpdatesController(getFabric().m523e(), getFabric().m524f());
        }
        return new ImmediateCheckForUpdatesController();
    }

    public Map<IdManager.IdManager, String> getDeviceIdentifiers() {
        CharSequence betaDeviceToken = getBetaDeviceToken(getContext(), getIdManager().m218h());
        Map<IdManager.IdManager, String> hashMap = new HashMap();
        if (!TextUtils.isEmpty(betaDeviceToken)) {
            hashMap.put(IdManager.IdManager.FONT_TOKEN, betaDeviceToken);
        }
        return hashMap;
    }

    public String getIdentifier() {
        return "com.crashlytics.sdk.android:beta";
    }

    public String getVersion() {
        return "1.1.3.61";
    }

    @TargetApi(11)
    boolean isAppPossiblyInstalledByBeta(String str, int i) {
        if (i < 11) {
            return str == null;
        } else {
            return "io.crash.air".equals(str);
        }
    }

    boolean canCheckForUpdates(BetaSettingsData betaSettingsData, BuildProperties buildProperties) {
        return (betaSettingsData == null || TextUtils.isEmpty(betaSettingsData.f339a) || buildProperties == null) ? false : true;
    }

    private String getBetaDeviceToken(Context context, String str) {
        if (isAppPossiblyInstalledByBeta(str, VERSION.SDK_INT)) {
            Fabric.m512h().m474a(TAG, "App was possibly installed by Beta. Getting device token");
            try {
                String str2 = (String) this.deviceTokenCache.m116a(context, this.deviceTokenLoader);
                if (NO_DEVICE_TOKEN.equals(str2)) {
                    str2 = null;
                }
                return str2;
            } catch (Throwable e) {
                Fabric.m512h().m482e(TAG, "Failed to load the Beta device token", e);
                return null;
            }
        }
        Fabric.m512h().m474a(TAG, "App was not installed by Beta. Skipping device token");
        return null;
    }

    private BetaSettingsData getBetaSettingsData() {
        SettingsData b = Settings.m462a().m466b();
        if (b != null) {
            return b.f384f;
        }
        return null;
    }

    private BuildProperties loadBuildProperties(Context context) {
        InputStream open;
        BuildProperties fromPropertiesStream;
        Throwable th;
        Throwable e;
        Throwable th2;
        BuildProperties buildProperties;
        InputStream inputStream = null;
        try {
            open = context.getAssets().open(CRASHLYTICS_BUILD_PROPERTIES);
            Object obj;
            if (open != null) {
                try {
                    fromPropertiesStream = BuildProperties.fromPropertiesStream(open);
                } catch (Throwable e2) {
                    th = e2;
                    obj = inputStream;
                    th2 = th;
                    try {
                        Fabric.m512h().m482e(TAG, "Error reading Beta build properties", th2);
                        if (open != null) {
                            try {
                                open.close();
                            } catch (Throwable th22) {
                                Fabric.m512h().m482e(TAG, "Error closing Beta build properties asset", th22);
                            }
                        }
                        return buildProperties;
                    } catch (Throwable th3) {
                        e2 = th3;
                        if (open != null) {
                            try {
                                open.close();
                            } catch (Throwable th222) {
                                Fabric.m512h().m482e(TAG, "Error closing Beta build properties asset", th222);
                            }
                        }
                        throw e2;
                    }
                }
                try {
                    Fabric.m512h().m474a(TAG, fromPropertiesStream.packageName + " build properties: " + fromPropertiesStream.versionName + " (" + fromPropertiesStream.versionCode + ")" + " - " + fromPropertiesStream.buildId);
                    buildProperties = fromPropertiesStream;
                } catch (Throwable e22) {
                    th = e22;
                    buildProperties = fromPropertiesStream;
                    th222 = th;
                    Fabric.m512h().m482e(TAG, "Error reading Beta build properties", th222);
                    if (open != null) {
                        open.close();
                    }
                    return buildProperties;
                }
            }
            obj = inputStream;
            if (open != null) {
                try {
                    open.close();
                } catch (Throwable th2222) {
                    Fabric.m512h().m482e(TAG, "Error closing Beta build properties asset", th2222);
                }
            }
        } catch (Throwable e222) {
            open = inputStream;
            InputStream inputStream2 = inputStream;
            th2222 = e222;
            buildProperties = inputStream2;
            Fabric.m512h().m482e(TAG, "Error reading Beta build properties", th2222);
            if (open != null) {
                open.close();
            }
            return buildProperties;
        } catch (Throwable th4) {
            e222 = th4;
            open = inputStream;
            if (open != null) {
                open.close();
            }
            throw e222;
        }
        return buildProperties;
    }

    String getOverridenSpiEndpoint() {
        return CommonUtils.m175b(getContext(), CRASHLYTICS_API_ENDPOINT);
    }
}
