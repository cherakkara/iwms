package com.crashlytics.android.core;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.pm.PackageInfo;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;
import com.crashlytics.android.answers.Answers;
import com.crashlytics.android.core.internal.CrashEventDataProvider;
import com.crashlytics.android.core.internal.models.SessionEventData;
import com.newrelic.agent.android.instrumentation.Trace;
import java.io.File;
import java.net.URL;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.HttpsURLConnection;
import p004b.p005a.p006a.p007a.Fabric;
import p004b.p005a.p006a.p007a.Kit;
import p004b.p005a.p006a.p007a.p008a.p010b.CommonUtils;
import p004b.p005a.p006a.p007a.p008a.p010b.Crash.Crash;
import p004b.p005a.p006a.p007a.p008a.p010b.ExecutorUtils;
import p004b.p005a.p006a.p007a.p008a.p011c.DependsOn;
import p004b.p005a.p006a.p007a.p008a.p011c.Priority;
import p004b.p005a.p006a.p007a.p008a.p011c.PriorityCallable;
import p004b.p005a.p006a.p007a.p008a.p011c.Task;
import p004b.p005a.p006a.p007a.p008a.p014e.DefaultHttpRequestFactory;
import p004b.p005a.p006a.p007a.p008a.p014e.HttpMethod;
import p004b.p005a.p006a.p007a.p008a.p014e.HttpRequest;
import p004b.p005a.p006a.p007a.p008a.p014e.HttpRequestFactory;
import p004b.p005a.p006a.p007a.p008a.p014e.PinningInfoProvider;
import p004b.p005a.p006a.p007a.p008a.p015f.FileStoreImpl;
import p004b.p005a.p006a.p007a.p008a.p015f.PreferenceStore;
import p004b.p005a.p006a.p007a.p008a.p015f.PreferenceStoreImpl;
import p004b.p005a.p006a.p007a.p008a.p016g.PromptSettingsData;
import p004b.p005a.p006a.p007a.p008a.p016g.SessionSettingsData;
import p004b.p005a.p006a.p007a.p008a.p016g.Settings.Settings;
import p004b.p005a.p006a.p007a.p008a.p016g.SettingsData;

@DependsOn(a = {CrashEventDataProvider.class})
public class CrashlyticsCore extends Kit<Void> {
    static final float CLS_DEFAULT_PROCESS_DELAY = 1.0f;
    static final String COLLECT_CUSTOM_KEYS = "com.crashlytics.CollectCustomKeys";
    static final String COLLECT_CUSTOM_LOGS = "com.crashlytics.CollectCustomLogs";
    static final String CRASHLYTICS_API_ENDPOINT = "com.crashlytics.ApiEndpoint";
    static final String CRASHLYTICS_REQUIRE_BUILD_ID = "com.crashlytics.RequireBuildId";
    static final boolean CRASHLYTICS_REQUIRE_BUILD_ID_DEFAULT = true;
    static final int DEFAULT_MAIN_HANDLER_TIMEOUT_SEC = 4;
    private static final String INITIALIZATION_MARKER_FILE_NAME = "initialization_marker";
    static final int MAX_ATTRIBUTES = 64;
    static final int MAX_ATTRIBUTE_SIZE = 1024;
    private static final String PREF_ALWAYS_SEND_REPORTS_KEY = "always_send_reports_opt_in";
    private static final boolean SHOULD_PROMPT_BEFORE_SENDING_REPORTS_DEFAULT = false;
    public static final String TAG = "Fabric";
    private final ConcurrentHashMap<String, String> attributes;
    private String buildId;
    private float delay;
    private boolean disabled;
    private CrashlyticsExecutorServiceWrapper executorServiceWrapper;
    private CrashEventDataProvider externalCrashEventDataProvider;
    private CrashlyticsUncaughtExceptionHandler handler;
    private HttpRequestFactory httpRequestFactory;
    private File initializationMarkerFile;
    private String installerPackageName;
    private CrashlyticsListener listener;
    private String packageName;
    private final PinningInfoProvider pinningInfo;
    private final long startTime;
    private String userEmail;
    private String userId;
    private String userName;
    private String versionCode;
    private String versionName;

    /* renamed from: com.crashlytics.android.core.CrashlyticsCore.1 */
    class C01221 extends PriorityCallable<Void> {
        C01221() {
        }

        public Void call() throws Exception {
            return CrashlyticsCore.this.doInBackground();
        }

        public Priority getPriority() {
            return Priority.IMMEDIATE;
        }
    }

    /* renamed from: com.crashlytics.android.core.CrashlyticsCore.2 */
    class C01232 implements Callable<Void> {
        C01232() {
        }

        public Void call() throws Exception {
            CrashlyticsCore.this.initializationMarkerFile.createNewFile();
            Fabric.m512h().m474a(CrashlyticsCore.TAG, "Initialization marker file created.");
            return null;
        }
    }

    /* renamed from: com.crashlytics.android.core.CrashlyticsCore.3 */
    class C01243 implements Callable<Boolean> {
        C01243() {
        }

        public Boolean call() throws Exception {
            try {
                boolean delete = CrashlyticsCore.this.initializationMarkerFile.delete();
                Fabric.m512h().m474a(CrashlyticsCore.TAG, "Initialization marker file removed: " + delete);
                return Boolean.valueOf(delete);
            } catch (Throwable e) {
                Fabric.m512h().m482e(CrashlyticsCore.TAG, "Problem encountered deleting Crashlytics initialization marker.", e);
                return Boolean.valueOf(CrashlyticsCore.SHOULD_PROMPT_BEFORE_SENDING_REPORTS_DEFAULT);
            }
        }
    }

    /* renamed from: com.crashlytics.android.core.CrashlyticsCore.4 */
    class C01254 implements Callable<Boolean> {
        C01254() {
        }

        public Boolean call() throws Exception {
            return Boolean.valueOf(CrashlyticsCore.this.initializationMarkerFile.exists());
        }
    }

    /* renamed from: com.crashlytics.android.core.CrashlyticsCore.5 */
    class C01265 implements Settings<Boolean> {
        C01265() {
        }

        public Boolean usingSettings(SettingsData settingsData) {
            boolean z = CrashlyticsCore.SHOULD_PROMPT_BEFORE_SENDING_REPORTS_DEFAULT;
            if (!settingsData.f382d.f349a) {
                return Boolean.valueOf(CrashlyticsCore.SHOULD_PROMPT_BEFORE_SENDING_REPORTS_DEFAULT);
            }
            if (!CrashlyticsCore.this.shouldSendReportsWithoutPrompting()) {
                z = CrashlyticsCore.CRASHLYTICS_REQUIRE_BUILD_ID_DEFAULT;
            }
            return Boolean.valueOf(z);
        }
    }

    /* renamed from: com.crashlytics.android.core.CrashlyticsCore.6 */
    class C01276 implements Settings<Boolean> {
        C01276() {
        }

        public Boolean usingSettings(SettingsData settingsData) {
            boolean z = CrashlyticsCore.CRASHLYTICS_REQUIRE_BUILD_ID_DEFAULT;
            Activity b = CrashlyticsCore.this.getFabric().m519b();
            if (!(b == null || b.isFinishing() || !CrashlyticsCore.this.shouldPromptUserBeforeSendingCrashReports())) {
                z = CrashlyticsCore.this.getSendDecisionFromUser(b, settingsData.f381c);
            }
            return Boolean.valueOf(z);
        }
    }

    /* renamed from: com.crashlytics.android.core.CrashlyticsCore.7 */
    class C01317 implements Runnable {
        final /* synthetic */ Activity val$activity;
        final /* synthetic */ OptInLatch val$latch;
        final /* synthetic */ PromptSettingsData val$promptData;
        final /* synthetic */ DialogStringResolver val$stringResolver;

        /* renamed from: com.crashlytics.android.core.CrashlyticsCore.7.1 */
        class C01281 implements OnClickListener {
            C01281() {
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                C01317.this.val$latch.setOptIn(CrashlyticsCore.CRASHLYTICS_REQUIRE_BUILD_ID_DEFAULT);
                dialogInterface.dismiss();
            }
        }

        /* renamed from: com.crashlytics.android.core.CrashlyticsCore.7.2 */
        class C01292 implements OnClickListener {
            C01292() {
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                C01317.this.val$latch.setOptIn(CrashlyticsCore.SHOULD_PROMPT_BEFORE_SENDING_REPORTS_DEFAULT);
                dialogInterface.dismiss();
            }
        }

        /* renamed from: com.crashlytics.android.core.CrashlyticsCore.7.3 */
        class C01303 implements OnClickListener {
            C01303() {
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                CrashlyticsCore.this.setShouldSendUserReportsWithoutPrompting(CrashlyticsCore.CRASHLYTICS_REQUIRE_BUILD_ID_DEFAULT);
                C01317.this.val$latch.setOptIn(CrashlyticsCore.CRASHLYTICS_REQUIRE_BUILD_ID_DEFAULT);
                dialogInterface.dismiss();
            }
        }

        C01317(Activity activity, OptInLatch optInLatch, DialogStringResolver dialogStringResolver, PromptSettingsData promptSettingsData) {
            this.val$activity = activity;
            this.val$latch = optInLatch;
            this.val$stringResolver = dialogStringResolver;
            this.val$promptData = promptSettingsData;
        }

        public void run() {
            android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this.val$activity);
            OnClickListener c01281 = new C01281();
            float f = this.val$activity.getResources().getDisplayMetrics().density;
            int access$300 = CrashlyticsCore.this.dipsToPixels(f, 5);
            View textView = new TextView(this.val$activity);
            textView.setAutoLinkMask(15);
            textView.setText(this.val$stringResolver.getMessage());
            textView.setTextAppearance(this.val$activity, 16973892);
            textView.setPadding(access$300, access$300, access$300, access$300);
            textView.setFocusable(CrashlyticsCore.SHOULD_PROMPT_BEFORE_SENDING_REPORTS_DEFAULT);
            View scrollView = new ScrollView(this.val$activity);
            scrollView.setPadding(CrashlyticsCore.this.dipsToPixels(f, 14), CrashlyticsCore.this.dipsToPixels(f, 2), CrashlyticsCore.this.dipsToPixels(f, 10), CrashlyticsCore.this.dipsToPixels(f, 12));
            scrollView.addView(textView);
            builder.setView(scrollView).setTitle(this.val$stringResolver.getTitle()).setCancelable(CrashlyticsCore.SHOULD_PROMPT_BEFORE_SENDING_REPORTS_DEFAULT).setNeutralButton(this.val$stringResolver.getSendButtonTitle(), c01281);
            if (this.val$promptData.f360d) {
                builder.setNegativeButton(this.val$stringResolver.getCancelButtonTitle(), new C01292());
            }
            if (this.val$promptData.f362f) {
                builder.setPositiveButton(this.val$stringResolver.getAlwaysSendButtonTitle(), new C01303());
            }
            builder.show();
        }
    }

    public static class Builder {
        private float delay;
        private boolean disabled;
        private CrashlyticsListener listener;
        private PinningInfoProvider pinningInfoProvider;

        public Builder() {
            this.delay = -1.0f;
            this.disabled = CrashlyticsCore.SHOULD_PROMPT_BEFORE_SENDING_REPORTS_DEFAULT;
        }

        public Builder delay(float f) {
            if (f <= 0.0f) {
                throw new IllegalArgumentException("delay must be greater than 0");
            } else if (this.delay > 0.0f) {
                throw new IllegalStateException("delay already set.");
            } else {
                this.delay = f;
                return this;
            }
        }

        public Builder listener(CrashlyticsListener crashlyticsListener) {
            if (crashlyticsListener == null) {
                throw new IllegalArgumentException("listener must not be null.");
            } else if (this.listener != null) {
                throw new IllegalStateException("listener already set.");
            } else {
                this.listener = crashlyticsListener;
                return this;
            }
        }

        @Deprecated
        public Builder pinningInfo(PinningInfoProvider pinningInfoProvider) {
            if (pinningInfoProvider == null) {
                throw new IllegalArgumentException("pinningInfoProvider must not be null.");
            } else if (this.pinningInfoProvider != null) {
                throw new IllegalStateException("pinningInfoProvider already set.");
            } else {
                this.pinningInfoProvider = pinningInfoProvider;
                return this;
            }
        }

        public Builder disabled(boolean z) {
            this.disabled = z;
            return this;
        }

        public CrashlyticsCore build() {
            if (this.delay < 0.0f) {
                this.delay = CrashlyticsCore.CLS_DEFAULT_PROCESS_DELAY;
            }
            return new CrashlyticsCore(this.delay, this.listener, this.pinningInfoProvider, this.disabled);
        }
    }

    private class OptInLatch {
        private final CountDownLatch latch;
        private boolean send;

        private OptInLatch() {
            this.send = CrashlyticsCore.SHOULD_PROMPT_BEFORE_SENDING_REPORTS_DEFAULT;
            this.latch = new CountDownLatch(1);
        }

        void setOptIn(boolean z) {
            this.send = z;
            this.latch.countDown();
        }

        boolean getOptIn() {
            return this.send;
        }

        void await() {
            try {
                this.latch.await();
            } catch (InterruptedException e) {
            }
        }
    }

    public CrashlyticsCore() {
        this(CLS_DEFAULT_PROCESS_DELAY, null, null, SHOULD_PROMPT_BEFORE_SENDING_REPORTS_DEFAULT);
    }

    CrashlyticsCore(float f, CrashlyticsListener crashlyticsListener, PinningInfoProvider pinningInfoProvider, boolean z) {
        this(f, crashlyticsListener, pinningInfoProvider, z, ExecutorUtils.m196a("Crashlytics Exception Handler"));
    }

    CrashlyticsCore(float f, CrashlyticsListener crashlyticsListener, PinningInfoProvider pinningInfoProvider, boolean z, ExecutorService executorService) {
        this.userId = null;
        this.userEmail = null;
        this.userName = null;
        this.attributes = new ConcurrentHashMap();
        this.startTime = System.currentTimeMillis();
        this.delay = f;
        this.listener = crashlyticsListener;
        this.pinningInfo = pinningInfoProvider;
        this.disabled = z;
        this.executorServiceWrapper = new CrashlyticsExecutorServiceWrapper(executorService);
    }

    protected boolean onPreExecute() {
        return onPreExecute(super.getContext());
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    boolean onPreExecute(android.content.Context r9) {
        /*
        r8 = this;
        r7 = 0;
        r0 = r8.disabled;
        if (r0 == 0) goto L_0x0007;
    L_0x0005:
        r0 = r7;
    L_0x0006:
        return r0;
    L_0x0007:
        r0 = new b.a.a.a.a.b.g;
        r0.<init>();
        r0 = r0.m141a(r9);
        if (r0 != 0) goto L_0x0014;
    L_0x0012:
        r0 = r7;
        goto L_0x0006;
    L_0x0014:
        r1 = p004b.p005a.p006a.p007a.Fabric.m512h();
        r2 = "Fabric";
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r4 = "Initializing Crashlytics ";
        r3 = r3.append(r4);
        r4 = r8.getVersion();
        r3 = r3.append(r4);
        r3 = r3.toString();
        r1.m478c(r2, r3);
        r1 = new java.io.File;
        r2 = r8.getSdkDirectory();
        r3 = "initialization_marker";
        r1.<init>(r2, r3);
        r8.initializationMarkerFile = r1;
        r8.setAndValidateKitProperties(r9, r0);	 Catch:{ CrashlyticsMissingDependencyException -> 0x00a7, Exception -> 0x00b1 }
        r5 = new com.crashlytics.android.core.SessionDataWriter;	 Catch:{ Exception -> 0x0099, CrashlyticsMissingDependencyException -> 0x00a7 }
        r0 = r8.getContext();	 Catch:{ Exception -> 0x0099, CrashlyticsMissingDependencyException -> 0x00a7 }
        r1 = r8.buildId;	 Catch:{ Exception -> 0x0099, CrashlyticsMissingDependencyException -> 0x00a7 }
        r2 = r8.getPackageName();	 Catch:{ Exception -> 0x0099, CrashlyticsMissingDependencyException -> 0x00a7 }
        r5.<init>(r0, r1, r2);	 Catch:{ Exception -> 0x0099, CrashlyticsMissingDependencyException -> 0x00a7 }
        r0 = p004b.p005a.p006a.p007a.Fabric.m512h();	 Catch:{ Exception -> 0x0099, CrashlyticsMissingDependencyException -> 0x00a7 }
        r1 = "Fabric";
        r2 = "Installing exception handler...";
        r0.m474a(r1, r2);	 Catch:{ Exception -> 0x0099, CrashlyticsMissingDependencyException -> 0x00a7 }
        r0 = new com.crashlytics.android.core.CrashlyticsUncaughtExceptionHandler;	 Catch:{ Exception -> 0x0099, CrashlyticsMissingDependencyException -> 0x00a7 }
        r1 = java.lang.Thread.getDefaultUncaughtExceptionHandler();	 Catch:{ Exception -> 0x0099, CrashlyticsMissingDependencyException -> 0x00a7 }
        r2 = r8.listener;	 Catch:{ Exception -> 0x0099, CrashlyticsMissingDependencyException -> 0x00a7 }
        r3 = r8.executorServiceWrapper;	 Catch:{ Exception -> 0x0099, CrashlyticsMissingDependencyException -> 0x00a7 }
        r4 = r8.getIdManager();	 Catch:{ Exception -> 0x0099, CrashlyticsMissingDependencyException -> 0x00a7 }
        r6 = r8;
        r0.<init>(r1, r2, r3, r4, r5, r6);	 Catch:{ Exception -> 0x0099, CrashlyticsMissingDependencyException -> 0x00a7 }
        r8.handler = r0;	 Catch:{ Exception -> 0x0099, CrashlyticsMissingDependencyException -> 0x00a7 }
        r1 = r8.didPreviousInitializationComplete();	 Catch:{ Exception -> 0x0099, CrashlyticsMissingDependencyException -> 0x00a7 }
        r0 = r8.handler;	 Catch:{ Exception -> 0x00c0, CrashlyticsMissingDependencyException -> 0x00a7 }
        r0.ensureOpenSessionExists();	 Catch:{ Exception -> 0x00c0, CrashlyticsMissingDependencyException -> 0x00a7 }
        r0 = r8.handler;	 Catch:{ Exception -> 0x00c0, CrashlyticsMissingDependencyException -> 0x00a7 }
        java.lang.Thread.setDefaultUncaughtExceptionHandler(r0);	 Catch:{ Exception -> 0x00c0, CrashlyticsMissingDependencyException -> 0x00a7 }
        r0 = p004b.p005a.p006a.p007a.Fabric.m512h();	 Catch:{ Exception -> 0x00c0, CrashlyticsMissingDependencyException -> 0x00a7 }
        r2 = "Fabric";
        r3 = "Successfully installed exception handler.";
        r0.m474a(r2, r3);	 Catch:{ Exception -> 0x00c0, CrashlyticsMissingDependencyException -> 0x00a7 }
    L_0x008b:
        if (r1 == 0) goto L_0x00ae;
    L_0x008d:
        r0 = p004b.p005a.p006a.p007a.p008a.p010b.CommonUtils.m191n(r9);	 Catch:{ CrashlyticsMissingDependencyException -> 0x00a7, Exception -> 0x00b1 }
        if (r0 == 0) goto L_0x00ae;
    L_0x0093:
        r8.finishInitSynchronously();	 Catch:{ CrashlyticsMissingDependencyException -> 0x00a7, Exception -> 0x00b1 }
        r0 = r7;
        goto L_0x0006;
    L_0x0099:
        r0 = move-exception;
        r1 = r7;
    L_0x009b:
        r2 = p004b.p005a.p006a.p007a.Fabric.m512h();	 Catch:{ CrashlyticsMissingDependencyException -> 0x00a7, Exception -> 0x00b1 }
        r3 = "Fabric";
        r4 = "There was a problem installing the exception handler.";
        r2.m482e(r3, r4, r0);	 Catch:{ CrashlyticsMissingDependencyException -> 0x00a7, Exception -> 0x00b1 }
        goto L_0x008b;
    L_0x00a7:
        r0 = move-exception;
        r1 = new b.a.a.a.a.c.m;
        r1.<init>(r0);
        throw r1;
    L_0x00ae:
        r0 = 1;
        goto L_0x0006;
    L_0x00b1:
        r0 = move-exception;
        r1 = p004b.p005a.p006a.p007a.Fabric.m512h();
        r2 = "Fabric";
        r3 = "Crashlytics was not started due to an exception during initialization";
        r1.m482e(r2, r3, r0);
        r0 = r7;
        goto L_0x0006;
    L_0x00c0:
        r0 = move-exception;
        goto L_0x009b;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.crashlytics.android.core.CrashlyticsCore.onPreExecute(android.content.Context):boolean");
    }

    private void setAndValidateKitProperties(Context context, String str) {
        PinningInfoProvider crashlyticsPinningInfoProvider = this.pinningInfo != null ? new CrashlyticsPinningInfoProvider(this.pinningInfo) : null;
        this.httpRequestFactory = new DefaultHttpRequestFactory(Fabric.m512h());
        this.httpRequestFactory.m330a(crashlyticsPinningInfoProvider);
        try {
            this.packageName = context.getPackageName();
            this.installerPackageName = getIdManager().m218h();
            Fabric.m512h().m474a(TAG, "Installer package name is: " + this.installerPackageName);
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(this.packageName, 0);
            this.versionCode = Integer.toString(packageInfo.versionCode);
            this.versionName = packageInfo.versionName == null ? "0.0" : packageInfo.versionName;
            this.buildId = CommonUtils.m190m(context);
        } catch (Throwable e) {
            Fabric.m512h().m482e(TAG, "Error setting up app properties", e);
        }
        getIdManager().m223m();
        getBuildIdValidator(this.buildId, isRequiringBuildId(context)).validate(str, this.packageName);
    }

    protected Void doInBackground() {
        markInitializationStarted();
        this.handler.cleanInvalidTempFiles();
        Object obj = 1;
        try {
            SettingsData b = p004b.p005a.p006a.p007a.p008a.p016g.Settings.m462a().m466b();
            if (b == null) {
                Fabric.m512h().m479d(TAG, "Received null settings, skipping initialization!");
                markInitializationComplete();
                return null;
            }
            if (b.f382d.f351c) {
                obj = null;
                this.handler.finalizeSessions();
                CreateReportSpiCall createReportSpiCall = getCreateReportSpiCall(b);
                if (createReportSpiCall != null) {
                    new ReportUploader(createReportSpiCall).uploadReports(this.delay);
                } else {
                    Fabric.m512h().m479d(TAG, "Unable to create a call to upload reports.");
                }
            }
            if (obj != null) {
                try {
                    Fabric.m512h().m474a(TAG, "Crash reporting disabled.");
                } catch (Throwable e) {
                    Fabric.m512h().m482e(TAG, "Problem encountered during Crashlytics initialization.", e);
                } finally {
                    markInitializationComplete();
                }
            }
            markInitializationComplete();
            return null;
        } catch (Throwable e2) {
            Throwable th = e2;
            Object obj2 = obj;
            Fabric.m512h().m482e(TAG, "Error dealing with settings", th);
            obj = obj2;
        }
    }

    public String getIdentifier() {
        return "com.crashlytics.sdk.android.crashlytics-core";
    }

    public String getVersion() {
        return "2.3.3.61";
    }

    public static CrashlyticsCore getInstance() {
        return (CrashlyticsCore) Fabric.m504a(CrashlyticsCore.class);
    }

    public PinningInfoProvider getPinningInfoProvider() {
        return !this.disabled ? this.pinningInfo : null;
    }

    public void logException(Throwable th) {
        if (this.disabled || !ensureFabricWithCalled("prior to logging exceptions.")) {
            return;
        }
        if (th == null) {
            Fabric.m512h().m472a(5, TAG, "Crashlytics is ignoring a request to log a null exception.");
        } else {
            this.handler.writeNonFatalException(Thread.currentThread(), th);
        }
    }

    public void log(String str) {
        doLog(3, TAG, str);
    }

    private void doLog(int i, String str, String str2) {
        if (!this.disabled && ensureFabricWithCalled("prior to logging messages.")) {
            this.handler.writeToLog(System.currentTimeMillis() - this.startTime, formatLogMessage(i, str, str2));
        }
    }

    public void log(int i, String str, String str2) {
        doLog(i, str, str2);
        Fabric.m512h().m473a(i, Trace.NULL + str, Trace.NULL + str2, CRASHLYTICS_REQUIRE_BUILD_ID_DEFAULT);
    }

    public void setUserIdentifier(String str) {
        if (!this.disabled) {
            this.userId = sanitizeAttribute(str);
            this.handler.cacheUserData(this.userId, this.userName, this.userEmail);
        }
    }

    public void setUserName(String str) {
        if (!this.disabled) {
            this.userName = sanitizeAttribute(str);
            this.handler.cacheUserData(this.userId, this.userName, this.userEmail);
        }
    }

    public void setUserEmail(String str) {
        if (!this.disabled) {
            this.userEmail = sanitizeAttribute(str);
            this.handler.cacheUserData(this.userId, this.userName, this.userEmail);
        }
    }

    public void setString(String str, String str2) {
        if (!this.disabled) {
            if (str != null) {
                String sanitizeAttribute = sanitizeAttribute(str);
                if (this.attributes.size() < MAX_ATTRIBUTES || this.attributes.containsKey(sanitizeAttribute)) {
                    this.attributes.put(sanitizeAttribute, str2 == null ? Trace.NULL : sanitizeAttribute(str2));
                    this.handler.cacheKeyData(this.attributes);
                    return;
                }
                Fabric.m512h().m474a(TAG, "Exceeded maximum number of custom attributes (64)");
            } else if (getContext() == null || !CommonUtils.m186i(getContext())) {
                Fabric.m512h().m482e(TAG, "Attempting to set custom attribute with null key, ignoring.", null);
            } else {
                throw new IllegalArgumentException("Custom attribute key must not be null.");
            }
        }
    }

    public void setBool(String str, boolean z) {
        setString(str, Boolean.toString(z));
    }

    public void setDouble(String str, double d) {
        setString(str, Double.toString(d));
    }

    public void setFloat(String str, float f) {
        setString(str, Float.toString(f));
    }

    public void setInt(String str, int i) {
        setString(str, Integer.toString(i));
    }

    public void setLong(String str, long j) {
        setString(str, Long.toString(j));
    }

    public void crash() {
        new CrashTest().indexOutOfBounds();
    }

    public boolean verifyPinning(URL url) {
        try {
            return internalVerifyPinning(url);
        } catch (Throwable e) {
            Fabric.m512h().m482e(TAG, "Could not verify SSL pinning", e);
            return SHOULD_PROMPT_BEFORE_SENDING_REPORTS_DEFAULT;
        }
    }

    private void finishInitSynchronously() {
        Callable c01221 = new C01221();
        for (Task addDependency : getDependencies()) {
            c01221.addDependency(addDependency);
        }
        Future submit = getFabric().m524f().submit(c01221);
        Fabric.m512h().m474a(TAG, "Crashlytics detected incomplete initialization on previous app launch. Will initialize synchronously.");
        try {
            submit.get(4, TimeUnit.SECONDS);
        } catch (Throwable e) {
            Fabric.m512h().m482e(TAG, "Crashlytics was interrupted during initialization.", e);
        } catch (Throwable e2) {
            Fabric.m512h().m482e(TAG, "Problem encountered during Crashlytics initialization.", e2);
        } catch (Throwable e22) {
            Fabric.m512h().m482e(TAG, "Crashlytics timed out during initialization.", e22);
        }
    }

    @Deprecated
    public synchronized void setListener(CrashlyticsListener crashlyticsListener) {
        Fabric.m512h().m479d(TAG, "Use of setListener is deprecated.");
        if (crashlyticsListener == null) {
            throw new IllegalArgumentException("listener must not be null.");
        }
        this.listener = crashlyticsListener;
    }

    static void recordLoggedExceptionEvent(String str) {
        Answers answers = (Answers) Fabric.m504a(Answers.class);
        if (answers != null) {
            answers.onException(new Crash(str));
        }
    }

    static void recordFatalExceptionEvent(String str) {
        Answers answers = (Answers) Fabric.m504a(Answers.class);
        if (answers != null) {
            answers.onException(new Crash(str));
        }
    }

    Map<String, String> getAttributes() {
        return Collections.unmodifiableMap(this.attributes);
    }

    BuildIdValidator getBuildIdValidator(String str, boolean z) {
        return new BuildIdValidator(str, z);
    }

    String getPackageName() {
        return this.packageName;
    }

    String getInstallerPackageName() {
        return this.installerPackageName;
    }

    String getVersionName() {
        return this.versionName;
    }

    String getVersionCode() {
        return this.versionCode;
    }

    String getOverridenSpiEndpoint() {
        return CommonUtils.m175b(getContext(), CRASHLYTICS_API_ENDPOINT);
    }

    String getBuildId() {
        return this.buildId;
    }

    CrashlyticsUncaughtExceptionHandler getHandler() {
        return this.handler;
    }

    String getUserIdentifier() {
        return getIdManager().m211a() ? this.userId : null;
    }

    String getUserEmail() {
        return getIdManager().m211a() ? this.userEmail : null;
    }

    String getUserName() {
        return getIdManager().m211a() ? this.userName : null;
    }

    void markInitializationStarted() {
        this.executorServiceWrapper.executeSyncLoggingException(new C01232());
    }

    void markInitializationComplete() {
        this.executorServiceWrapper.executeAsync(new C01243());
    }

    boolean didPreviousInitializationComplete() {
        return ((Boolean) this.executorServiceWrapper.executeSyncLoggingException(new C01254())).booleanValue();
    }

    void setExternalCrashEventDataProvider(CrashEventDataProvider crashEventDataProvider) {
        this.externalCrashEventDataProvider = crashEventDataProvider;
    }

    SessionEventData getExternalCrashEventData() {
        if (this.externalCrashEventDataProvider != null) {
            return this.externalCrashEventDataProvider.getCrashEventData();
        }
        return null;
    }

    boolean internalVerifyPinning(URL url) {
        if (getPinningInfoProvider() == null) {
            return SHOULD_PROMPT_BEFORE_SENDING_REPORTS_DEFAULT;
        }
        HttpRequest a = this.httpRequestFactory.m328a(HttpMethod.GET, url.toString());
        ((HttpsURLConnection) a.m377a()).setInstanceFollowRedirects(SHOULD_PROMPT_BEFORE_SENDING_REPORTS_DEFAULT);
        a.m378b();
        return CRASHLYTICS_REQUIRE_BUILD_ID_DEFAULT;
    }

    File getSdkDirectory() {
        return new FileStoreImpl(this).m413a();
    }

    boolean shouldPromptUserBeforeSendingCrashReports() {
        return ((Boolean) p004b.p005a.p006a.p007a.p008a.p016g.Settings.m462a().m465a(new C01265(), Boolean.valueOf(SHOULD_PROMPT_BEFORE_SENDING_REPORTS_DEFAULT))).booleanValue();
    }

    boolean shouldSendReportsWithoutPrompting() {
        return new PreferenceStoreImpl(this).m415a().getBoolean(PREF_ALWAYS_SEND_REPORTS_KEY, SHOULD_PROMPT_BEFORE_SENDING_REPORTS_DEFAULT);
    }

    @SuppressLint({"CommitPrefEdits"})
    void setShouldSendUserReportsWithoutPrompting(boolean z) {
        PreferenceStore preferenceStoreImpl = new PreferenceStoreImpl(this);
        preferenceStoreImpl.m416a(preferenceStoreImpl.m417b().putBoolean(PREF_ALWAYS_SEND_REPORTS_KEY, z));
    }

    boolean canSendWithUserApproval() {
        return ((Boolean) p004b.p005a.p006a.p007a.p008a.p016g.Settings.m462a().m465a(new C01276(), Boolean.valueOf(CRASHLYTICS_REQUIRE_BUILD_ID_DEFAULT))).booleanValue();
    }

    CreateReportSpiCall getCreateReportSpiCall(SettingsData settingsData) {
        if (settingsData != null) {
            return new DefaultCreateReportSpiCall(this, getOverridenSpiEndpoint(), settingsData.f379a.f336d, this.httpRequestFactory);
        }
        return null;
    }

    private boolean getSendDecisionFromUser(Activity activity, PromptSettingsData promptSettingsData) {
        DialogStringResolver dialogStringResolver = new DialogStringResolver(activity, promptSettingsData);
        OptInLatch optInLatch = new OptInLatch();
        activity.runOnUiThread(new C01317(activity, optInLatch, dialogStringResolver, promptSettingsData));
        Fabric.m512h().m474a(TAG, "Waiting for user opt-in.");
        optInLatch.await();
        return optInLatch.getOptIn();
    }

    SessionSettingsData getSessionSettingsData() {
        SettingsData b = p004b.p005a.p006a.p007a.p008a.p016g.Settings.m462a().m466b();
        return b == null ? null : b.f380b;
    }

    private boolean isRequiringBuildId(Context context) {
        return CommonUtils.m170a(context, CRASHLYTICS_REQUIRE_BUILD_ID, (boolean) CRASHLYTICS_REQUIRE_BUILD_ID_DEFAULT);
    }

    private static String formatLogMessage(int i, String str, String str2) {
        return CommonUtils.m174b(i) + "/" + str + " " + str2;
    }

    private static boolean ensureFabricWithCalled(String str) {
        CrashlyticsCore instance = getInstance();
        if (instance != null && instance.handler != null) {
            return CRASHLYTICS_REQUIRE_BUILD_ID_DEFAULT;
        }
        Fabric.m512h().m482e(TAG, "Crashlytics must be initialized by calling Fabric.with(Context) " + str, null);
        return SHOULD_PROMPT_BEFORE_SENDING_REPORTS_DEFAULT;
    }

    private static String sanitizeAttribute(String str) {
        if (str == null) {
            return str;
        }
        str = str.trim();
        if (str.length() > MAX_ATTRIBUTE_SIZE) {
            return str.substring(0, MAX_ATTRIBUTE_SIZE);
        }
        return str;
    }

    private int dipsToPixels(float f, int i) {
        return (int) (((float) i) * f);
    }
}
