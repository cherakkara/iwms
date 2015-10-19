package com.newrelic.agent.android;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.newrelic.agent.android.analytics.AnalyticAttribute;
import com.newrelic.agent.android.harvest.ApplicationInformation;
import com.newrelic.agent.android.harvest.ConnectInformation;
import com.newrelic.agent.android.harvest.DeviceInformation;
import com.newrelic.agent.android.harvest.Harvest;
import com.newrelic.agent.android.harvest.HarvestAdapter;
import com.newrelic.agent.android.harvest.HarvestConfiguration;
import com.newrelic.agent.android.logging.AgentLog;
import com.newrelic.agent.android.logging.AgentLogManager;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;

public class SavedState extends HarvestAdapter {
    private static final AgentLog log;
    private final String NEW_RELIC_AGENT_DISABLED_VERSION_KEY;
    private final String PREFERENCE_FILE_PREFIX;
    private final String PREF_ACTIVITY_TRACE_MIN_UTILIZATION;
    private final String PREF_AGENT_NAME;
    private final String PREF_AGENT_VERSION;
    private final String PREF_APP_BUILD;
    private final String PREF_APP_NAME;
    private final String PREF_APP_TOKEN;
    private final String PREF_APP_VERSION;
    private final String PREF_COLLECT_NETWORK_ERRORS;
    private final String PREF_CROSS_PROCESS_ID;
    private final String PREF_DATA_TOKEN;
    private final String PREF_DEVICE_ARCHITECTURE;
    private final String PREF_DEVICE_ID;
    private final String PREF_DEVICE_MANUFACTURER;
    private final String PREF_DEVICE_MODEL;
    private final String PREF_DEVICE_RUN_TIME;
    private final String PREF_DEVICE_SIZE;
    private final String PREF_ERROR_LIMIT;
    private final String PREF_HARVEST_INTERVAL;
    private final String PREF_MAX_TRANSACTION_AGE;
    private final String PREF_MAX_TRANSACTION_COUNT;
    private final String PREF_OS_BUILD;
    private final String PREF_OS_NAME;
    private final String PREF_OS_VERSION;
    private final String PREF_PACKAGE_ID;
    private final String PREF_RESPONSE_BODY_LIMIT;
    private final String PREF_SERVER_TIMESTAMP;
    private final String PREF_STACK_TRACE_LIMIT;
    private Float activityTraceMinUtilization;
    private final HarvestConfiguration configuration;
    private final ConnectInformation connectInformation;
    private final Editor editor;
    private final Lock lock;
    private final SharedPreferences prefs;

    static {
        log = AgentLogManager.getAgentLog();
    }

    public SavedState(Context context) {
        this.PREFERENCE_FILE_PREFIX = "com.newrelic.android.agent.v1_";
        this.PREF_MAX_TRANSACTION_COUNT = "maxTransactionCount";
        this.PREF_MAX_TRANSACTION_AGE = "maxTransactionAgeInSeconds";
        this.PREF_HARVEST_INTERVAL = "harvestIntervalInSeconds";
        this.PREF_SERVER_TIMESTAMP = "serverTimestamp";
        this.PREF_CROSS_PROCESS_ID = "crossProcessId";
        this.PREF_DATA_TOKEN = "dataToken";
        this.PREF_APP_TOKEN = "appToken";
        this.PREF_STACK_TRACE_LIMIT = "stackTraceLimit";
        this.PREF_RESPONSE_BODY_LIMIT = "responseBodyLimit";
        this.PREF_COLLECT_NETWORK_ERRORS = "collectNetworkErrors";
        this.PREF_ERROR_LIMIT = "errorLimit";
        this.NEW_RELIC_AGENT_DISABLED_VERSION_KEY = "NewRelicAgentDisabledVersion";
        this.PREF_ACTIVITY_TRACE_MIN_UTILIZATION = "activityTraceMinUtilization";
        this.configuration = new HarvestConfiguration();
        this.PREF_APP_NAME = AnalyticAttribute.APP_NAME_ATTRIBUTE;
        this.PREF_APP_VERSION = "appVersion";
        this.PREF_APP_BUILD = "appBuild";
        this.PREF_PACKAGE_ID = "packageId";
        this.PREF_AGENT_NAME = "agentName";
        this.PREF_AGENT_VERSION = "agentVersion";
        this.PREF_DEVICE_ARCHITECTURE = "deviceArchitecture";
        this.PREF_DEVICE_ID = "deviceId";
        this.PREF_DEVICE_MODEL = AnalyticAttribute.DEVICE_MODEL_ATTRIBUTE;
        this.PREF_DEVICE_MANUFACTURER = AnalyticAttribute.DEVICE_MANUFACTURER_ATTRIBUTE;
        this.PREF_DEVICE_RUN_TIME = "deviceRunTime";
        this.PREF_DEVICE_SIZE = "deviceSize";
        this.PREF_OS_NAME = AnalyticAttribute.OS_NAME_ATTRIBUTE;
        this.PREF_OS_BUILD = "osBuild";
        this.PREF_OS_VERSION = AnalyticAttribute.OS_VERSION_ATTRIBUTE;
        this.connectInformation = new ConnectInformation(new ApplicationInformation(), new DeviceInformation());
        this.lock = new ReentrantLock();
        this.prefs = context.getSharedPreferences(getPreferenceFileName(context.getPackageName()), 0);
        this.editor = this.prefs.edit();
        loadHarvestConfiguration();
        loadConnectInformation();
    }

    public void saveHarvestConfiguration(HarvestConfiguration harvestConfiguration) {
        if (!this.configuration.equals(harvestConfiguration)) {
            if (!harvestConfiguration.getDataToken().isValid()) {
                harvestConfiguration.setData_token(this.configuration.getData_token());
            }
            log.info("Saving configuration: " + harvestConfiguration);
            String toJsonString = harvestConfiguration.getDataToken().toJsonString();
            log.debug("!! saving data token: " + toJsonString);
            save("dataToken", toJsonString);
            save("crossProcessId", harvestConfiguration.getCross_process_id());
            save("serverTimestamp", harvestConfiguration.getServer_timestamp());
            save("harvestIntervalInSeconds", (long) harvestConfiguration.getData_report_period());
            save("maxTransactionAgeInSeconds", (long) harvestConfiguration.getReport_max_transaction_age());
            save("maxTransactionCount", (long) harvestConfiguration.getReport_max_transaction_count());
            save("stackTraceLimit", harvestConfiguration.getStack_trace_limit());
            save("responseBodyLimit", harvestConfiguration.getResponse_body_limit());
            save("collectNetworkErrors", harvestConfiguration.isCollect_network_errors());
            save("errorLimit", harvestConfiguration.getError_limit());
            saveActivityTraceMinUtilization((float) harvestConfiguration.getActivity_trace_min_utilization());
            loadHarvestConfiguration();
        }
    }

    public void loadHarvestConfiguration() {
        if (has("dataToken")) {
            this.configuration.setData_token(getDataToken());
        }
        if (has("crossProcessId")) {
            this.configuration.setCross_process_id(getCrossProcessId());
        }
        if (has("serverTimestamp")) {
            this.configuration.setServer_timestamp(getServerTimestamp());
        }
        if (has("harvestIntervalInSeconds")) {
            this.configuration.setData_report_period((int) getHarvestIntervalInSeconds());
        }
        if (has("maxTransactionAgeInSeconds")) {
            this.configuration.setReport_max_transaction_age((int) getMaxTransactionAgeInSeconds());
        }
        if (has("maxTransactionCount")) {
            this.configuration.setReport_max_transaction_count((int) getMaxTransactionCount());
        }
        if (has("stackTraceLimit")) {
            this.configuration.setStack_trace_limit(getStackTraceLimit());
        }
        if (has("responseBodyLimit")) {
            this.configuration.setResponse_body_limit(getResponseBodyLimit());
        }
        if (has("collectNetworkErrors")) {
            this.configuration.setCollect_network_errors(isCollectingNetworkErrors());
        }
        if (has("errorLimit")) {
            this.configuration.setError_limit(getErrorLimit());
        }
        if (has("activityTraceMinUtilization")) {
            this.configuration.setActivity_trace_min_utilization((double) getActivityTraceMinUtilization());
        }
        log.info("Loaded configuration: " + this.configuration);
    }

    public void saveConnectInformation(ConnectInformation connectInformation) {
        if (!this.connectInformation.equals(connectInformation)) {
            saveApplicationInformation(connectInformation.getApplicationInformation());
            saveDeviceInformation(connectInformation.getDeviceInformation());
            loadConnectInformation();
        }
    }

    public void saveDeviceId(String str) {
        save("deviceId", str);
        this.connectInformation.getDeviceInformation().setDeviceId(str);
    }

    public String getAppToken() {
        return getString("appToken");
    }

    public void saveAppToken(String str) {
        save("appToken", str);
    }

    private void saveApplicationInformation(ApplicationInformation applicationInformation) {
        save(AnalyticAttribute.APP_NAME_ATTRIBUTE, applicationInformation.getAppName());
        save("appVersion", applicationInformation.getAppVersion());
        save("appBuild", applicationInformation.getAppBuild());
        save("packageId", applicationInformation.getPackageId());
    }

    private void saveDeviceInformation(DeviceInformation deviceInformation) {
        save("agentName", deviceInformation.getAgentName());
        save("agentVersion", deviceInformation.getAgentVersion());
        save("deviceArchitecture", deviceInformation.getArchitecture());
        save("deviceId", deviceInformation.getDeviceId());
        save(AnalyticAttribute.DEVICE_MODEL_ATTRIBUTE, deviceInformation.getModel());
        save(AnalyticAttribute.DEVICE_MANUFACTURER_ATTRIBUTE, deviceInformation.getManufacturer());
        save("deviceRunTime", deviceInformation.getRunTime());
        save("deviceSize", deviceInformation.getSize());
        save(AnalyticAttribute.OS_NAME_ATTRIBUTE, deviceInformation.getOsName());
        save("osBuild", deviceInformation.getOsBuild());
        save(AnalyticAttribute.OS_VERSION_ATTRIBUTE, deviceInformation.getOsVersion());
    }

    public void loadConnectInformation() {
        ApplicationInformation applicationInformation = new ApplicationInformation();
        if (has(AnalyticAttribute.APP_NAME_ATTRIBUTE)) {
            applicationInformation.setAppName(getAppName());
        }
        if (has("appVersion")) {
            applicationInformation.setAppVersion(getAppVersion());
        }
        if (has("appBuild")) {
            applicationInformation.setAppBuild(getAppBuild());
        }
        if (has("packageId")) {
            applicationInformation.setPackageId(getPackageId());
        }
        DeviceInformation deviceInformation = new DeviceInformation();
        if (has("agentName")) {
            deviceInformation.setAgentName(getAgentName());
        }
        if (has("agentVersion")) {
            deviceInformation.setAgentVersion(getAgentVersion());
        }
        if (has("deviceArchitecture")) {
            deviceInformation.setArchitecture(getDeviceArchitecture());
        }
        if (has("deviceId")) {
            deviceInformation.setDeviceId(getDeviceId());
        }
        if (has(AnalyticAttribute.DEVICE_MODEL_ATTRIBUTE)) {
            deviceInformation.setModel(getDeviceModel());
        }
        if (has(AnalyticAttribute.DEVICE_MANUFACTURER_ATTRIBUTE)) {
            deviceInformation.setManufacturer(getDeviceManufacturer());
        }
        if (has("deviceRunTime")) {
            deviceInformation.setRunTime(getDeviceRunTime());
        }
        if (has("deviceSize")) {
            deviceInformation.setSize(getDeviceSize());
        }
        if (has(AnalyticAttribute.OS_NAME_ATTRIBUTE)) {
            deviceInformation.setOsName(getOsName());
        }
        if (has("osBuild")) {
            deviceInformation.setOsBuild(getOsBuild());
        }
        if (has(AnalyticAttribute.OS_VERSION_ATTRIBUTE)) {
            deviceInformation.setOsVersion(getOsVersion());
        }
        this.connectInformation.setApplicationInformation(applicationInformation);
        this.connectInformation.setDeviceInformation(deviceInformation);
    }

    public HarvestConfiguration getHarvestConfiguration() {
        return this.configuration;
    }

    public ConnectInformation getConnectInformation() {
        return this.connectInformation;
    }

    private boolean has(String str) {
        return this.prefs.contains(str);
    }

    public void onHarvestConnected() {
        saveHarvestConfiguration(Harvest.getHarvestConfiguration());
    }

    public void onHarvestComplete() {
        saveHarvestConfiguration(Harvest.getHarvestConfiguration());
    }

    public void onHarvestDisconnected() {
        log.info("Clearing harvest configuration.");
        clear();
    }

    public void onHarvestDisabled() {
        String agentVersion = Agent.getDeviceInformation().getAgentVersion();
        log.info("Disabling agent version " + agentVersion);
        saveDisabledVersion(agentVersion);
    }

    public void save(String str, String str2) {
        this.lock.lock();
        try {
            this.editor.putString(str, str2);
            this.editor.commit();
        } finally {
            this.lock.unlock();
        }
    }

    public void save(String str, boolean z) {
        this.lock.lock();
        try {
            this.editor.putBoolean(str, z);
            this.editor.commit();
        } finally {
            this.lock.unlock();
        }
    }

    public void save(String str, int i) {
        this.lock.lock();
        try {
            this.editor.putInt(str, i);
            this.editor.commit();
        } finally {
            this.lock.unlock();
        }
    }

    public void save(String str, long j) {
        this.lock.lock();
        try {
            this.editor.putLong(str, j);
            this.editor.commit();
        } finally {
            this.lock.unlock();
        }
    }

    public void save(String str, float f) {
        this.lock.lock();
        try {
            this.editor.putFloat(str, f);
            this.editor.commit();
        } finally {
            this.lock.unlock();
        }
    }

    public String getString(String str) {
        if (this.prefs.contains(str)) {
            return this.prefs.getString(str, null);
        }
        return null;
    }

    public boolean getBoolean(String str) {
        return this.prefs.getBoolean(str, false);
    }

    public long getLong(String str) {
        return this.prefs.getLong(str, 0);
    }

    public int getInt(String str) {
        return this.prefs.getInt(str, 0);
    }

    public Float getFloat(String str) {
        if (this.prefs.contains(str)) {
            return Float.valueOf(((float) ((int) (this.prefs.getFloat(str, 0.0f) * 100.0f))) / 100.0f);
        }
        return null;
    }

    public String getDisabledVersion() {
        return getString("NewRelicAgentDisabledVersion");
    }

    public void saveDisabledVersion(String str) {
        save("NewRelicAgentDisabledVersion", str);
    }

    public int[] getDataToken() {
        int[] iArr = new int[2];
        String string = getString("dataToken");
        if (string == null) {
            return null;
        }
        try {
            JSONTokener jSONTokener = new JSONTokener(string);
            if (jSONTokener == null) {
                return null;
            }
            JSONArray jSONArray = (JSONArray) jSONTokener.nextValue();
            if (jSONArray == null) {
                return null;
            }
            iArr[0] = jSONArray.getInt(0);
            iArr[1] = jSONArray.getInt(1);
            return iArr;
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getCrossProcessId() {
        return getString("crossProcessId");
    }

    public boolean isCollectingNetworkErrors() {
        return getBoolean("collectNetworkErrors");
    }

    public long getServerTimestamp() {
        return getLong("serverTimestamp");
    }

    public long getHarvestInterval() {
        return getLong("harvestIntervalInSeconds");
    }

    public long getMaxTransactionAge() {
        return getLong("maxTransactionAgeInSeconds");
    }

    public long getMaxTransactionCount() {
        return getLong("maxTransactionCount");
    }

    public int getStackTraceLimit() {
        return getInt("stackTraceLimit");
    }

    public int getResponseBodyLimit() {
        return getInt("responseBodyLimit");
    }

    public int getErrorLimit() {
        return getInt("errorLimit");
    }

    public void saveActivityTraceMinUtilization(float f) {
        this.activityTraceMinUtilization = Float.valueOf(f);
        save("activityTraceMinUtilization", f);
    }

    public float getActivityTraceMinUtilization() {
        if (this.activityTraceMinUtilization == null) {
            this.activityTraceMinUtilization = getFloat("activityTraceMinUtilization");
        }
        return this.activityTraceMinUtilization.floatValue();
    }

    public long getHarvestIntervalInSeconds() {
        return getHarvestInterval();
    }

    public long getMaxTransactionAgeInSeconds() {
        return getMaxTransactionAge();
    }

    public String getAppName() {
        return getString(AnalyticAttribute.APP_NAME_ATTRIBUTE);
    }

    public String getAppVersion() {
        return getString("appVersion");
    }

    public String getAppBuild() {
        return getString("appBuild");
    }

    public String getPackageId() {
        return getString("packageId");
    }

    public String getAgentName() {
        return getString("agentName");
    }

    public String getAgentVersion() {
        return getString("agentVersion");
    }

    public String getDeviceArchitecture() {
        return getString("deviceArchitecture");
    }

    public String getDeviceId() {
        return getString("deviceId");
    }

    public String getDeviceModel() {
        return getString(AnalyticAttribute.DEVICE_MODEL_ATTRIBUTE);
    }

    public String getDeviceManufacturer() {
        return getString(AnalyticAttribute.DEVICE_MANUFACTURER_ATTRIBUTE);
    }

    public String getDeviceRunTime() {
        return getString("deviceRunTime");
    }

    public String getDeviceSize() {
        return getString("deviceSize");
    }

    public String getOsName() {
        return getString(AnalyticAttribute.OS_NAME_ATTRIBUTE);
    }

    public String getOsBuild() {
        return getString("osBuild");
    }

    public String getOsVersion() {
        return getString(AnalyticAttribute.OS_VERSION_ATTRIBUTE);
    }

    private String getPreferenceFileName(String str) {
        return "com.newrelic.android.agent.v1_" + str;
    }

    public void clear() {
        this.lock.lock();
        try {
            this.editor.clear();
            this.editor.commit();
            this.configuration.setDefaultValues();
        } finally {
            this.lock.unlock();
        }
    }
}
