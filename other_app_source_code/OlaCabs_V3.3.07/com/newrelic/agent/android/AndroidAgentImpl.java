package com.newrelic.agent.android;

import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Looper;
import android.text.TextUtils;
import com.newrelic.agent.android.analytics.AnalyticsControllerImpl;
import com.newrelic.agent.android.api.common.TransactionData;
import com.newrelic.agent.android.api.common.WanType;
import com.newrelic.agent.android.api.v1.ConnectionEvent;
import com.newrelic.agent.android.api.v1.ConnectionListener;
import com.newrelic.agent.android.api.v1.DeviceForm;
import com.newrelic.agent.android.api.v2.TraceMachineInterface;
import com.newrelic.agent.android.background.ApplicationStateEvent;
import com.newrelic.agent.android.background.ApplicationStateListener;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.crashes.CrashReporter;
import com.newrelic.agent.android.harvest.ApplicationInformation;
import com.newrelic.agent.android.harvest.ConnectInformation;
import com.newrelic.agent.android.harvest.DeviceInformation;
import com.newrelic.agent.android.harvest.Harvest;
import com.newrelic.agent.android.instrumentation.MetricCategory;
import com.newrelic.agent.android.instrumentation.Trace;
import com.newrelic.agent.android.logging.AgentLog;
import com.newrelic.agent.android.logging.AgentLogManager;
import com.newrelic.agent.android.metric.MetricUnit;
import com.newrelic.agent.android.sample.MachineMeasurementConsumer;
import com.newrelic.agent.android.sample.Sampler;
import com.newrelic.agent.android.stats.StatsEngine;
import com.newrelic.agent.android.tracing.TraceMachine;
import com.newrelic.agent.android.util.AndroidEncoder;
import com.newrelic.agent.android.util.Connectivity;
import com.newrelic.agent.android.util.Encoder;
import com.newrelic.agent.android.util.JsonCrashStore;
import com.newrelic.agent.android.util.PersistentUUID;
import com.newrelic.agent.android.util.SharedPrefsAnalyticAttributeStore;
import com.newrelic.agent.android.util.UiBackgroundListener;
import com.sothree.slidinguppanel.p086a.R.R;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import p017c.p018a.NewRelicCanary;

public class AndroidAgentImpl implements AgentImpl, ConnectionListener, TraceMachineInterface, ApplicationStateListener {
    private static final float LOCATION_ACCURACY_THRESHOLD = 500.0f;
    private static final Comparator<TransactionData> cmp;
    private static final AgentLog log;
    private final AgentConfiguration agentConfiguration;
    private ApplicationInformation applicationInformation;
    private final Context context;
    private DeviceInformation deviceInformation;
    private final Encoder encoder;
    private LocationListener locationListener;
    private final Lock lock;
    private MachineMeasurementConsumer machineMeasurementConsumer;
    private SavedState savedState;

    /* renamed from: com.newrelic.agent.android.AndroidAgentImpl.1 */
    static class C07271 implements Comparator<TransactionData> {
        C07271() {
        }

        public int compare(TransactionData transactionData, TransactionData transactionData2) {
            if (transactionData.getTimestamp() > transactionData2.getTimestamp()) {
                return -1;
            }
            if (transactionData.getTimestamp() < transactionData2.getTimestamp()) {
                return 1;
            }
            return 0;
        }
    }

    /* renamed from: com.newrelic.agent.android.AndroidAgentImpl.2 */
    class C07282 implements LocationListener {
        C07282() {
        }

        public void onLocationChanged(Location location) {
            if (AndroidAgentImpl.this.isAccurate(location)) {
                AndroidAgentImpl.this.setLocation(location);
            }
        }

        public void onProviderDisabled(String str) {
            if ("passive".equals(str)) {
                AndroidAgentImpl.this.removeLocationListener();
            }
        }

        public void onProviderEnabled(String str) {
        }

        public void onStatusChanged(String str, int i, Bundle bundle) {
        }
    }

    public com.newrelic.agent.android.harvest.EnvironmentInformation getEnvironmentInformation() {
        /* JADX: method processing error */
/*
        Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find block by offset: 0x00b9 in list []
	at jadx.core.utils.BlockUtils.getBlockByOffset(BlockUtils.java:42)
	at jadx.core.dex.instructions.IfNode.initBlocks(IfNode.java:58)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.initBlocksInIfNodes(BlockFinish.java:48)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.visit(BlockFinish.java:33)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:281)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:161)
*/
        /*
        r14 = this;
        r13 = 1;
        r12 = 0;
        r10 = 0;
        r2 = new com.newrelic.agent.android.harvest.EnvironmentInformation;
        r2.<init>();
        r0 = r14.context;
        r1 = "activity";
        r0 = r0.getSystemService(r1);
        r0 = (android.app.ActivityManager) r0;
        r1 = 2;
        r3 = new long[r1];
        r1 = new android.os.StatFs;	 Catch:{ Exception -> 0x00ad, all -> 0x00c5 }
        r4 = android.os.Environment.getRootDirectory();	 Catch:{ Exception -> 0x00ad, all -> 0x00c5 }
        r4 = r4.getAbsolutePath();	 Catch:{ Exception -> 0x00ad, all -> 0x00c5 }
        r1.<init>(r4);	 Catch:{ Exception -> 0x00ad, all -> 0x00c5 }
        r4 = new android.os.StatFs;	 Catch:{ Exception -> 0x00ad, all -> 0x00c5 }
        r5 = android.os.Environment.getExternalStorageDirectory();	 Catch:{ Exception -> 0x00ad, all -> 0x00c5 }
        r5 = r5.getAbsolutePath();	 Catch:{ Exception -> 0x00ad, all -> 0x00c5 }
        r4.<init>(r5);	 Catch:{ Exception -> 0x00ad, all -> 0x00c5 }
        r5 = android.os.Build.VERSION.SDK_INT;	 Catch:{ Exception -> 0x00ad, all -> 0x00c5 }
        r6 = 18;	 Catch:{ Exception -> 0x00ad, all -> 0x00c5 }
        if (r5 < r6) goto L_0x0092;	 Catch:{ Exception -> 0x00ad, all -> 0x00c5 }
    L_0x0036:
        r5 = 0;	 Catch:{ Exception -> 0x00ad, all -> 0x00c5 }
        r6 = r1.getAvailableBlocksLong();	 Catch:{ Exception -> 0x00ad, all -> 0x00c5 }
        r8 = r1.getBlockSizeLong();	 Catch:{ Exception -> 0x00ad, all -> 0x00c5 }
        r6 = r6 * r8;	 Catch:{ Exception -> 0x00ad, all -> 0x00c5 }
        r3[r5] = r6;	 Catch:{ Exception -> 0x00ad, all -> 0x00c5 }
        r5 = 1;	 Catch:{ Exception -> 0x00ad, all -> 0x00c5 }
        r6 = r4.getAvailableBlocksLong();	 Catch:{ Exception -> 0x00ad, all -> 0x00c5 }
        r8 = r1.getBlockSizeLong();	 Catch:{ Exception -> 0x00ad, all -> 0x00c5 }
        r6 = r6 * r8;	 Catch:{ Exception -> 0x00ad, all -> 0x00c5 }
        r3[r5] = r6;	 Catch:{ Exception -> 0x00ad, all -> 0x00c5 }
    L_0x004e:
        r4 = r3[r12];
        r1 = (r4 > r10 ? 1 : (r4 == r10 ? 0 : -1));
        if (r1 >= 0) goto L_0x0056;
    L_0x0054:
        r3[r12] = r10;
    L_0x0056:
        r4 = r3[r13];
        r1 = (r4 > r10 ? 1 : (r4 == r10 ? 0 : -1));
        if (r1 >= 0) goto L_0x005e;
    L_0x005c:
        r3[r13] = r10;
    L_0x005e:
        r2.setDiskAvailable(r3);
    L_0x0061:
        r0 = com.newrelic.agent.android.sample.Sampler.sampleMemory(r0);
        r0 = r0.getSampleValue();
        r0 = r0.asLong();
        r0 = r0.longValue();
        r2.setMemoryUsage(r0);
        r0 = r14.context;
        r0 = r0.getResources();
        r0 = r0.getConfiguration();
        r0 = r0.orientation;
        r2.setOrientation(r0);
        r0 = r14.getNetworkCarrier();
        r2.setNetworkStatus(r0);
        r0 = r14.getNetworkWanType();
        r2.setNetworkWanType(r0);
        return r2;
    L_0x0092:
        r5 = 0;
        r6 = r1.getAvailableBlocks();	 Catch:{ Exception -> 0x00ad, all -> 0x00c5 }
        r1 = r1.getBlockSize();	 Catch:{ Exception -> 0x00ad, all -> 0x00c5 }
        r1 = r1 * r6;	 Catch:{ Exception -> 0x00ad, all -> 0x00c5 }
        r6 = (long) r1;	 Catch:{ Exception -> 0x00ad, all -> 0x00c5 }
        r3[r5] = r6;	 Catch:{ Exception -> 0x00ad, all -> 0x00c5 }
        r1 = 1;	 Catch:{ Exception -> 0x00ad, all -> 0x00c5 }
        r5 = r4.getAvailableBlocks();	 Catch:{ Exception -> 0x00ad, all -> 0x00c5 }
        r4 = r4.getBlockSize();	 Catch:{ Exception -> 0x00ad, all -> 0x00c5 }
        r4 = r4 * r5;	 Catch:{ Exception -> 0x00ad, all -> 0x00c5 }
        r4 = (long) r4;	 Catch:{ Exception -> 0x00ad, all -> 0x00c5 }
        r3[r1] = r4;	 Catch:{ Exception -> 0x00ad, all -> 0x00c5 }
        goto L_0x004e;
    L_0x00ad:
        r1 = move-exception;
        com.newrelic.agent.android.harvest.AgentHealth.noticeException(r1);	 Catch:{ Exception -> 0x00ad, all -> 0x00c5 }
        r4 = r3[r12];
        r1 = (r4 > r10 ? 1 : (r4 == r10 ? 0 : -1));
        if (r1 >= 0) goto L_0x00b9;
    L_0x00b7:
        r3[r12] = r10;
    L_0x00b9:
        r4 = r3[r13];
        r1 = (r4 > r10 ? 1 : (r4 == r10 ? 0 : -1));
        if (r1 >= 0) goto L_0x00c1;
    L_0x00bf:
        r3[r13] = r10;
    L_0x00c1:
        r2.setDiskAvailable(r3);
        goto L_0x0061;
    L_0x00c5:
        r0 = move-exception;
        r4 = r3[r12];
        r1 = (r4 > r10 ? 1 : (r4 == r10 ? 0 : -1));
        if (r1 >= 0) goto L_0x00ce;
    L_0x00cc:
        r3[r12] = r10;
    L_0x00ce:
        r4 = r3[r13];
        r1 = (r4 > r10 ? 1 : (r4 == r10 ? 0 : -1));
        if (r1 >= 0) goto L_0x00d6;
    L_0x00d4:
        r3[r13] = r10;
    L_0x00d6:
        r2.setDiskAvailable(r3);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.newrelic.agent.android.AndroidAgentImpl.getEnvironmentInformation():com.newrelic.agent.android.harvest.EnvironmentInformation");
    }

    static {
        log = AgentLogManager.getAgentLog();
        cmp = new C07271();
    }

    public AndroidAgentImpl(Context context, AgentConfiguration agentConfiguration) throws AgentInitializationException {
        this.lock = new ReentrantLock();
        this.encoder = new AndroidEncoder();
        this.context = appContext(context);
        this.agentConfiguration = agentConfiguration;
        this.savedState = new SavedState(this.context);
        if (isDisabled()) {
            throw new AgentInitializationException("This version of the agent has been disabled");
        }
        initApplicationInformation();
        if (agentConfiguration.useLocationService() && this.context.getPackageManager().checkPermission("android.permission.ACCESS_FINE_LOCATION", getApplicationInformation().getPackageId()) == 0) {
            log.debug("Location stats enabled");
            addLocationListener();
        }
        TraceMachine.setTraceMachineInterface(this);
        agentConfiguration.setCrashStore(new JsonCrashStore(context));
        agentConfiguration.setAnalyticAttributeStore(new SharedPrefsAnalyticAttributeStore(context));
        ApplicationStateMonitor.getInstance().addApplicationStateListener(this);
        if (VERSION.SDK_INT >= 14) {
            context.registerComponentCallbacks(new UiBackgroundListener());
        }
        Sampler.init(context);
    }

    protected void initialize() {
        Harvest.addHarvestListener(this.savedState);
        Harvest.initialize(this.agentConfiguration);
        Harvest.setHarvestConfiguration(this.savedState.getHarvestConfiguration());
        Harvest.setHarvestConnectInformation(this.savedState.getConnectInformation());
        Measurements.initialize();
        log.info(MessageFormat.format("New Relic Agent v{0}", new Object[]{Agent.getVersion()}));
        log.verbose(MessageFormat.format("Application token: {0}", new Object[]{this.agentConfiguration.getApplicationToken()}));
        this.machineMeasurementConsumer = new MachineMeasurementConsumer();
        Measurements.addMeasurementConsumer(this.machineMeasurementConsumer);
        AnalyticsControllerImpl.getInstance();
        AnalyticsControllerImpl.initialize(this.agentConfiguration, this);
        StatsEngine.get().inc("Supportability/AgentHealth/UncaughtExceptionHandler/" + getUnhandledExceptionHandlerName());
        CrashReporter.initialize(this.agentConfiguration);
    }

    public boolean updateSavedConnectInformation() {
        ConnectInformation connectInformation = this.savedState.getConnectInformation();
        ConnectInformation connectInformation2 = new ConnectInformation(getApplicationInformation(), getDeviceInformation());
        String appToken = this.savedState.getAppToken();
        if (connectInformation2.equals(connectInformation) && this.agentConfiguration.getApplicationToken().equals(appToken)) {
            return false;
        }
        this.savedState.clear();
        this.savedState.saveConnectInformation(connectInformation2);
        this.savedState.saveAppToken(this.agentConfiguration.getApplicationToken());
        return true;
    }

    public DeviceInformation getDeviceInformation() {
        if (this.deviceInformation != null) {
            return this.deviceInformation;
        }
        DeviceInformation deviceInformation = new DeviceInformation();
        deviceInformation.setOsName("Android");
        deviceInformation.setOsVersion(VERSION.RELEASE);
        deviceInformation.setOsBuild(VERSION.INCREMENTAL);
        deviceInformation.setModel(Build.MODEL);
        deviceInformation.setAgentName("AndroidAgent");
        deviceInformation.setAgentVersion(Agent.getVersion());
        deviceInformation.setManufacturer(Build.MANUFACTURER);
        deviceInformation.setDeviceId(getUUID());
        deviceInformation.setArchitecture(System.getProperty("os.arch"));
        deviceInformation.setRunTime(System.getProperty("java.vm.version"));
        deviceInformation.setSize(deviceForm(this.context).name().toLowerCase());
        this.deviceInformation = deviceInformation;
        return this.deviceInformation;
    }

    public void initApplicationInformation() throws AgentInitializationException {
        if (this.applicationInformation != null) {
            log.debug("attempted to reinitialize ApplicationInformation.");
            return;
        }
        String packageName = this.context.getPackageName();
        PackageManager packageManager = this.context.getPackageManager();
        try {
            String charSequence;
            PackageInfo packageInfo = packageManager.getPackageInfo(packageName, 0);
            String customApplicationVersion = this.agentConfiguration.getCustomApplicationVersion();
            if (TextUtils.isEmpty(customApplicationVersion)) {
                if (packageInfo == null || packageInfo.versionName == null || packageInfo.versionName.length() <= 0) {
                    throw new AgentInitializationException("Your app doesn't appear to have a version defined. Ensure you have defined 'versionName' in your manifest.");
                }
                customApplicationVersion = packageInfo.versionName;
            }
            log.debug("Using application version " + customApplicationVersion);
            try {
                ApplicationInfo applicationInfo = packageManager.getApplicationInfo(packageName, 0);
                if (applicationInfo != null) {
                    charSequence = packageManager.getApplicationLabel(applicationInfo).toString();
                } else {
                    charSequence = packageName;
                }
            } catch (NameNotFoundException e) {
                log.warning(e.toString());
                charSequence = packageName;
            } catch (SecurityException e2) {
                log.warning(e2.toString());
                charSequence = packageName;
            }
            log.debug("Using application name " + charSequence);
            String customBuildIdentifier = this.agentConfiguration.getCustomBuildIdentifier();
            if (TextUtils.isEmpty(customBuildIdentifier)) {
                if (packageInfo != null) {
                    customBuildIdentifier = String.valueOf(packageInfo.versionCode);
                } else {
                    customBuildIdentifier = Trace.NULL;
                    log.warning("Your app doesn't appear to have a version code defined. Ensure you have defined 'versionCode' in your manifest.");
                }
            }
            log.debug("Using build  " + customBuildIdentifier);
            this.applicationInformation = new ApplicationInformation(charSequence, customApplicationVersion, packageName, customBuildIdentifier);
        } catch (NameNotFoundException e3) {
            throw new AgentInitializationException("Could not determine package version: " + e3.getMessage());
        }
    }

    public ApplicationInformation getApplicationInformation() {
        return this.applicationInformation;
    }

    public long getSessionDurationMillis() {
        Harvest.getInstance();
        return Harvest.getMillisSinceStart();
    }

    private static DeviceForm deviceForm(Context context) {
        int i = context.getResources().getConfiguration().screenLayout & 15;
        switch (i) {
            case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                return DeviceForm.SMALL;
            case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                return DeviceForm.NORMAL;
            case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                return DeviceForm.LARGE;
            default:
                if (i > 3) {
                    return DeviceForm.XLARGE;
                }
                return DeviceForm.UNKNOWN;
        }
    }

    private static Context appContext(Context context) {
        if (context instanceof Application) {
            return context;
        }
        return context.getApplicationContext();
    }

    @Deprecated
    public void addTransactionData(TransactionData transactionData) {
    }

    @Deprecated
    public void mergeTransactionData(List<TransactionData> list) {
    }

    @Deprecated
    public List<TransactionData> getAndClearTransactionData() {
        return null;
    }

    public String getCrossProcessId() {
        this.lock.lock();
        try {
            String crossProcessId = this.savedState.getCrossProcessId();
            return crossProcessId;
        } finally {
            this.lock.unlock();
        }
    }

    public int getStackTraceLimit() {
        this.lock.lock();
        try {
            int stackTraceLimit = this.savedState.getStackTraceLimit();
            return stackTraceLimit;
        } finally {
            this.lock.unlock();
        }
    }

    public int getResponseBodyLimit() {
        this.lock.lock();
        try {
            int response_body_limit = this.savedState.getHarvestConfiguration().getResponse_body_limit();
            return response_body_limit;
        } finally {
            this.lock.unlock();
        }
    }

    public void start() {
        if (isDisabled()) {
            stop(false);
            return;
        }
        initialize();
        Harvest.start();
    }

    public void stop() {
        stop(true);
    }

    private void stop(boolean z) {
        Sampler.shutdown();
        TraceMachine.haltTracing();
        int eventsRecorded = AnalyticsControllerImpl.getInstance().getEventManager().getEventsRecorded();
        int eventsEjected = AnalyticsControllerImpl.getInstance().getEventManager().getEventsEjected();
        Measurements.addCustomMetric("Supportability/Events/Recorded", MetricCategory.NONE.name(), eventsRecorded, (double) eventsEjected, (double) eventsEjected, MetricUnit.OPERATIONS, MetricUnit.OPERATIONS);
        if (z) {
            Harvest.harvestNow();
        }
        AnalyticsControllerImpl.shutdown();
        TraceMachine.clearActivityHistory();
        Harvest.shutdown();
        Measurements.shutdown();
    }

    public void disable() {
        log.warning("PERMANENTLY DISABLING AGENT v" + Agent.getVersion());
        try {
            this.savedState.saveDisabledVersion(Agent.getVersion());
            try {
                stop(false);
            } finally {
                Agent.setImpl(NullAgentImpl.instance);
            }
        } catch (Throwable th) {
            stop(false);
        } finally {
            Agent.setImpl(NullAgentImpl.instance);
        }
    }

    public boolean isDisabled() {
        return Agent.getVersion().equals(this.savedState.getDisabledVersion());
    }

    public String getNetworkCarrier() {
        return Connectivity.carrierNameFromContext(this.context);
    }

    public String getNetworkWanType() {
        return Connectivity.wanType(this.context);
    }

    public static void init(Context context, AgentConfiguration agentConfiguration) {
        try {
            Agent.setImpl(new AndroidAgentImpl(context, agentConfiguration));
            Agent.start();
        } catch (AgentInitializationException e) {
            log.error("Failed to initialize the agent: " + e.toString());
        }
    }

    @Deprecated
    public void connected(ConnectionEvent connectionEvent) {
        log.error("AndroidAgentImpl: connected ");
    }

    @Deprecated
    public void disconnected(ConnectionEvent connectionEvent) {
        this.savedState.clear();
    }

    @Deprecated
    public void applicationForegrounded(ApplicationStateEvent applicationStateEvent) {
        log.info("AndroidAgentImpl: application foregrounded ");
        start();
    }

    @Deprecated
    public void applicationBackgrounded(ApplicationStateEvent applicationStateEvent) {
        log.info("AndroidAgentImpl: application backgrounded ");
        stop();
    }

    public void setLocation(String str, String str2) {
        if (str == null || str2 == null) {
            throw new IllegalArgumentException("Country code and administrative region are required.");
        }
    }

    public void setLocation(Location location) {
        if (location == null) {
            throw new IllegalArgumentException("Location must not be null.");
        }
        List fromLocation;
        try {
            fromLocation = new Geocoder(this.context).getFromLocation(location.getLatitude(), location.getLongitude(), 1);
        } catch (IOException e) {
            log.error("Unable to geocode location: " + e.toString());
            fromLocation = null;
        }
        if (fromLocation != null && fromLocation.size() != 0) {
            Address address = (Address) fromLocation.get(0);
            if (address != null) {
                String countryCode = address.getCountryCode();
                String adminArea = address.getAdminArea();
                if (countryCode != null && adminArea != null) {
                    setLocation(countryCode, adminArea);
                    removeLocationListener();
                }
            }
        }
    }

    private void addLocationListener() {
        LocationManager locationManager = (LocationManager) this.context.getSystemService("location");
        if (locationManager == null) {
            log.error("Unable to retrieve reference to LocationManager. Disabling location listener.");
            return;
        }
        this.locationListener = new C07282();
        locationManager.requestLocationUpdates("passive", 1000, 0.0f, this.locationListener);
    }

    private void removeLocationListener() {
        if (this.locationListener != null) {
            LocationManager locationManager = (LocationManager) this.context.getSystemService("location");
            if (locationManager == null) {
                log.error("Unable to retrieve reference to LocationManager. Can't unregister location listener.");
                return;
            }
            synchronized (locationManager) {
                locationManager.removeUpdates(this.locationListener);
                this.locationListener = null;
            }
        }
    }

    private boolean isAccurate(Location location) {
        if (location != null && LOCATION_ACCURACY_THRESHOLD >= location.getAccuracy()) {
            return true;
        }
        return false;
    }

    private String getUUID() {
        String deviceId = this.savedState.getConnectInformation().getDeviceInformation().getDeviceId();
        if (!TextUtils.isEmpty(deviceId)) {
            return deviceId;
        }
        deviceId = new PersistentUUID(this.context).getPersistentUUID();
        this.savedState.saveDeviceId(deviceId);
        return deviceId;
    }

    private String getUnhandledExceptionHandlerName() {
        try {
            return Thread.getDefaultUncaughtExceptionHandler().getClass().getName();
        } catch (Exception e) {
            return WanType.UNKNOWN;
        }
    }

    public Encoder getEncoder() {
        return this.encoder;
    }

    public long getCurrentThreadId() {
        return Thread.currentThread().getId();
    }

    public boolean isUIThread() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

    public String getCurrentThreadName() {
        return Thread.currentThread().getName();
    }

    private void pokeCanary() {
        NewRelicCanary.m549a();
    }
}
