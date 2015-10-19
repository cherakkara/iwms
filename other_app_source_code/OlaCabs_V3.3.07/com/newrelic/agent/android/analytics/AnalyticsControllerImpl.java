package com.newrelic.agent.android.analytics;

import com.newrelic.agent.android.AgentConfiguration;
import com.newrelic.agent.android.AgentImpl;
import com.newrelic.agent.android.harvest.DeviceInformation;
import com.newrelic.agent.android.harvest.EnvironmentInformation;
import com.newrelic.agent.android.instrumentation.Trace;
import com.newrelic.agent.android.logging.AgentLog;
import com.newrelic.agent.android.logging.AgentLogManager;
import com.newrelic.agent.android.tracing.ActivityTrace;
import com.newrelic.agent.android.tracing.TraceLifecycleAware;
import com.newrelic.agent.android.tracing.TraceMachine;
import com.olacabs.customer.utils.Constants;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

public class AnalyticsControllerImpl implements AnalyticsController {
    private static final int MAX_ATTRIBUTES = 64;
    private static final String NEW_RELIC_PREFIX = "newRelic";
    private static final String NR_PREFIX = "nr.";
    private static final AtomicBoolean initialized;
    private static final AnalyticsControllerImpl instance;
    static final AgentLog log;
    private static final List<String> reservedNames;
    private AgentConfiguration agentConfiguration;
    private AgentImpl agentImpl;
    private EventManager eventManager;
    private boolean isEnabled;
    private InteractionCompleteListener listener;
    private Set<AnalyticAttribute> systemAttributes;
    private Set<AnalyticAttribute> userAttributes;

    class InteractionCompleteListener implements TraceLifecycleAware {
        InteractionCompleteListener() {
        }

        public void onEnterMethod() {
        }

        public void onExitMethod() {
        }

        public void onTraceStart(ActivityTrace activityTrace) {
        }

        public void onTraceComplete(ActivityTrace activityTrace) {
            AnalyticsControllerImpl.log.debug("AnalyticsControllerImpl.InteractionCompleteListener.onTraceComplete invoke.");
            AnalyticsControllerImpl.getInstance().addEvent(createTraceEvent(activityTrace));
        }

        private AnalyticsEvent createTraceEvent(ActivityTrace activityTrace) {
            float durationAsSeconds = activityTrace.rootTrace.getDurationAsSeconds();
            Set hashSet = new HashSet();
            hashSet.add(new AnalyticAttribute(AnalyticAttribute.INTERACTION_DURATION_ATTRIBUTE, durationAsSeconds));
            return AnalyticsEventFactory.createEvent(activityTrace.rootTrace.displayName, AnalyticsEventCategory.Interaction, AnalyticAttribute.EVENT_TYPE_ATTRIBUTE_MOBILE, hashSet);
        }
    }

    static {
        log = AgentLogManager.getAgentLog();
        instance = new AnalyticsControllerImpl();
        initialized = new AtomicBoolean(false);
        reservedNames = new ArrayList();
    }

    public static void initialize(AgentConfiguration agentConfiguration, AgentImpl agentImpl) {
        log.debug("AnalyticsControllerImpl.initialize invoked.");
        if (initialized.compareAndSet(false, true)) {
            instance.clear();
            reservedNames.add(AnalyticAttribute.EVENT_TYPE_ATTRIBUTE);
            reservedNames.add(Constants.BUNDLE_TYPE);
            reservedNames.add(Constants.PUSH_ACK_TIME);
            reservedNames.add(AnalyticAttribute.EVENT_CATEGORY_ATTRIBUTE);
            reservedNames.add(AnalyticAttribute.ACCOUNT_ID_ATTRIBUTE);
            reservedNames.add(AnalyticAttribute.APP_ID_ATTRIBUTE);
            reservedNames.add(AnalyticAttribute.APP_NAME_ATTRIBUTE);
            reservedNames.add(AnalyticAttribute.UUID_ATTRIBUTE);
            reservedNames.add(AnalyticAttribute.SESSION_ID_ATTRIBUTE);
            reservedNames.add(AnalyticAttribute.OS_NAME_ATTRIBUTE);
            reservedNames.add(AnalyticAttribute.OS_VERSION_ATTRIBUTE);
            reservedNames.add(AnalyticAttribute.OS_MAJOR_VERSION_ATTRIBUTE);
            reservedNames.add(AnalyticAttribute.DEVICE_MANUFACTURER_ATTRIBUTE);
            reservedNames.add(AnalyticAttribute.DEVICE_MODEL_ATTRIBUTE);
            reservedNames.add(AnalyticAttribute.MEM_USAGE_MB_ATTRIBUTE);
            reservedNames.add(AnalyticAttribute.CARRIER_ATTRIBUTE);
            reservedNames.add(AnalyticAttribute.NEW_RELIC_VERSION_ATTRIBUTE);
            reservedNames.add(AnalyticAttribute.INTERACTION_DURATION_ATTRIBUTE);
            instance.reinitialize(agentConfiguration, agentImpl);
            TraceMachine.addTraceListener(instance.listener);
            log.info("Analytics Controller started.");
            return;
        }
        log.debug("AnalyticsControllerImpl has already been initialized.  Bypassing..");
    }

    public static void shutdown() {
        TraceMachine.removeTraceListener(instance.listener);
        initialized.compareAndSet(true, false);
        instance.getEventManager().shutdown();
    }

    private AnalyticsControllerImpl() {
        this.eventManager = new EventManagerImpl();
        this.systemAttributes = Collections.synchronizedSet(new HashSet());
        this.userAttributes = Collections.synchronizedSet(new HashSet());
        this.listener = new InteractionCompleteListener();
    }

    void reinitialize(AgentConfiguration agentConfiguration, AgentImpl agentImpl) {
        String str;
        this.agentImpl = agentImpl;
        this.agentConfiguration = agentConfiguration;
        this.eventManager.initialize();
        this.isEnabled = agentConfiguration.getEnableAnalyticsEvents();
        loadPersistentAttributes();
        removeAttribute(AnalyticAttribute.SESSION_DURATION_ATTRIBUTE);
        DeviceInformation deviceInformation = agentImpl.getDeviceInformation();
        String replace = deviceInformation.getOsVersion().replace(" ", Trace.NULL);
        String[] split = replace.split("[.:-]");
        if (split.length > 0) {
            str = split[0];
        } else {
            str = replace;
        }
        EnvironmentInformation environmentInformation = agentImpl.getEnvironmentInformation();
        this.systemAttributes.add(new AnalyticAttribute(AnalyticAttribute.OS_NAME_ATTRIBUTE, deviceInformation.getOsName()));
        this.systemAttributes.add(new AnalyticAttribute(AnalyticAttribute.OS_VERSION_ATTRIBUTE, replace));
        this.systemAttributes.add(new AnalyticAttribute(AnalyticAttribute.OS_MAJOR_VERSION_ATTRIBUTE, str));
        this.systemAttributes.add(new AnalyticAttribute(AnalyticAttribute.DEVICE_MANUFACTURER_ATTRIBUTE, deviceInformation.getManufacturer()));
        this.systemAttributes.add(new AnalyticAttribute(AnalyticAttribute.DEVICE_MODEL_ATTRIBUTE, deviceInformation.getModel()));
        this.systemAttributes.add(new AnalyticAttribute(AnalyticAttribute.UUID_ATTRIBUTE, deviceInformation.getDeviceId()));
        this.systemAttributes.add(new AnalyticAttribute(AnalyticAttribute.CARRIER_ATTRIBUTE, agentImpl.getNetworkCarrier()));
        this.systemAttributes.add(new AnalyticAttribute(AnalyticAttribute.NEW_RELIC_VERSION_ATTRIBUTE, deviceInformation.getAgentVersion()));
        this.systemAttributes.add(new AnalyticAttribute(AnalyticAttribute.MEM_USAGE_MB_ATTRIBUTE, (float) environmentInformation.getMemoryUsage()));
        this.systemAttributes.add(new AnalyticAttribute(AnalyticAttribute.SESSION_ID_ATTRIBUTE, agentConfiguration.getSessionID()));
    }

    public AnalyticAttribute getAttribute(String str) {
        log.debug("AnalyticsControllerImpl.getAttribute - retrieving " + str);
        AnalyticAttribute userAttribute = getUserAttribute(str);
        if (userAttribute == null) {
            return getSystemAttribute(str);
        }
        return userAttribute;
    }

    public Set<AnalyticAttribute> getSystemAttributes() {
        Set hashSet = new HashSet(this.systemAttributes.size());
        for (AnalyticAttribute analyticAttribute : this.systemAttributes) {
            hashSet.add(new AnalyticAttribute(analyticAttribute));
        }
        return Collections.unmodifiableSet(hashSet);
    }

    public Set<AnalyticAttribute> getUserAttributes() {
        Set hashSet = new HashSet(this.userAttributes.size());
        for (AnalyticAttribute analyticAttribute : this.userAttributes) {
            hashSet.add(new AnalyticAttribute(analyticAttribute));
        }
        return Collections.unmodifiableSet(hashSet);
    }

    public Set<AnalyticAttribute> getSessionAttributes() {
        Set hashSet = new HashSet(getSessionAttributeCount());
        hashSet.addAll(getSystemAttributes());
        hashSet.addAll(getUserAttributes());
        return Collections.unmodifiableSet(hashSet);
    }

    public int getSystemAttributeCount() {
        return this.systemAttributes.size();
    }

    public int getUserAttributeCount() {
        return this.userAttributes.size();
    }

    public int getSessionAttributeCount() {
        return this.systemAttributes.size() + this.userAttributes.size();
    }

    public boolean setAttribute(String str, String str2) {
        log.debug("AnalyticsControllerImpl.setAttribute - " + str + ": " + str2);
        return setAttribute(str, str2, true);
    }

    public boolean setAttribute(String str, String str2, boolean z) {
        log.debug("AnalyticsControllerImpl.setAttribute - " + str + ": " + str2 + (z ? " (persistent)" : " (transient)"));
        if (!initialized.get()) {
            log.warning("Analytics controller is not initialized!");
            return false;
        } else if (!this.isEnabled) {
            log.warning("Analytics controller is not enabled!");
            return false;
        } else if (!isNameValid(str) || !isStringValueValid(str, str2)) {
            return false;
        } else {
            AnalyticAttribute attribute = getAttribute(str);
            boolean store;
            if (attribute != null) {
                attribute.setStringValue(str2);
                attribute.setPersistent(z);
                if (attribute.isPersistent()) {
                    store = this.agentConfiguration.getAnalyticAttributeStore().store(attribute);
                    if (!store) {
                        log.error("Failed to store attribute " + attribute + " to attribute store.");
                        return store;
                    }
                }
                this.agentConfiguration.getAnalyticAttributeStore().delete(attribute);
            } else if (this.userAttributes.size() < MAX_ATTRIBUTES) {
                attribute = new AnalyticAttribute(str, str2, true);
                this.userAttributes.add(attribute);
                if (z) {
                    store = this.agentConfiguration.getAnalyticAttributeStore().store(attribute);
                    if (!store) {
                        log.error("Failed to store attribute " + attribute + " to attribute store.");
                        return store;
                    }
                }
            } else {
                log.warning("Attribute limit exceeded: at most 64 are allowed.");
                log.warning("Currently defined attributes:");
                for (AnalyticAttribute analyticAttribute : this.userAttributes) {
                    log.warning("\t" + analyticAttribute.getName() + ": " + (analyticAttribute.isStringAttribute() ? analyticAttribute.getStringValue() : Float.valueOf(analyticAttribute.getFloatValue())));
                }
            }
            return true;
        }
    }

    public boolean setAttribute(String str, float f) {
        log.debug("AnalyticsControllerImpl.setAttribute - " + str + ": " + f);
        return setAttribute(str, f, true);
    }

    public boolean setAttribute(String str, float f, boolean z) {
        log.debug("AnalyticsControllerImpl.setAttribute - " + str + ": " + f + (z ? " (persistent)" : " (transient)"));
        if (!initialized.get()) {
            log.warning("Analytics controller is not initialized!");
            return false;
        } else if (!this.isEnabled) {
            log.warning("Analytics controller is not enabled!");
            return false;
        } else if (!isNameValid(str)) {
            return false;
        } else {
            AnalyticAttribute attribute = getAttribute(str);
            boolean store;
            if (attribute != null) {
                attribute.setFloatValue(f);
                attribute.setPersistent(z);
                if (attribute.isPersistent()) {
                    store = this.agentConfiguration.getAnalyticAttributeStore().store(attribute);
                    if (!store) {
                        log.error("Failed to store attribute " + attribute + " to attribute store.");
                        return store;
                    }
                }
                this.agentConfiguration.getAnalyticAttributeStore().delete(attribute);
            } else if (this.userAttributes.size() < MAX_ATTRIBUTES) {
                attribute = new AnalyticAttribute(str, f, true);
                this.userAttributes.add(attribute);
                if (z) {
                    this.agentConfiguration.getAnalyticAttributeStore().store(attribute);
                    store = this.agentConfiguration.getAnalyticAttributeStore().store(attribute);
                    if (!store) {
                        log.error("Failed to store attribute " + attribute + " to attribute store.");
                        return store;
                    }
                }
            } else {
                log.warning("Attribute limit exceeded: at most 64 are allowed.");
                log.warning("Currently defined attributes:");
                for (AnalyticAttribute analyticAttribute : this.userAttributes) {
                    log.warning("\t" + analyticAttribute.getName() + ": " + (analyticAttribute.isStringAttribute() ? analyticAttribute.getStringValue() : Float.valueOf(analyticAttribute.getFloatValue())));
                }
            }
            return true;
        }
    }

    public boolean incrementAttribute(String str, float f) {
        log.debug("AnalyticsControllerImpl.incrementAttribute - " + str + ": " + f);
        return incrementAttribute(str, f, true);
    }

    public boolean incrementAttribute(String str, float f, boolean z) {
        log.debug("AnalyticsControllerImpl.incrementAttribute - " + str + ": " + f + (z ? " (persistent)" : " (transient)"));
        if (!initialized.get()) {
            log.warning("Analytics controller is not initialized!");
            return false;
        } else if (!this.isEnabled) {
            log.warning("Analytics controller is not enabled!");
            return false;
        } else if (!isNameValid(str)) {
            return false;
        } else {
            AnalyticAttribute attribute = getAttribute(str);
            boolean store;
            if (attribute != null && !attribute.isStringAttribute()) {
                attribute.setFloatValue(attribute.getFloatValue() + f);
                attribute.setPersistent(z);
                if (attribute.isPersistent()) {
                    store = this.agentConfiguration.getAnalyticAttributeStore().store(attribute);
                    if (!store) {
                        log.error("Failed to store attribute " + attribute + " to attribute store.");
                        return store;
                    }
                }
            } else if (attribute != null) {
                log.warning("Cannot increment attribute " + str + ": the attribute is already defined as a String value.");
                return false;
            } else if (this.userAttributes.size() < MAX_ATTRIBUTES) {
                AnalyticAttribute analyticAttribute = new AnalyticAttribute(str, f, z);
                this.userAttributes.add(analyticAttribute);
                if (analyticAttribute.isPersistent()) {
                    this.agentConfiguration.getAnalyticAttributeStore().store(analyticAttribute);
                    store = this.agentConfiguration.getAnalyticAttributeStore().store(analyticAttribute);
                    if (!store) {
                        log.error("Failed to store attribute " + analyticAttribute + " to attribute store.");
                        return store;
                    }
                }
                this.agentConfiguration.getAnalyticAttributeStore().delete(analyticAttribute);
            }
            return true;
        }
    }

    public boolean removeAttribute(String str) {
        log.debug("AnalyticsControllerImpl.removeAttribute - " + str);
        if (!initialized.get()) {
            log.warning("Analytics controller is not initialized!");
            return false;
        } else if (this.isEnabled) {
            AnalyticAttribute attribute = getAttribute(str);
            if (attribute != null) {
                this.userAttributes.remove(attribute);
                if (attribute.isPersistent()) {
                    this.agentConfiguration.getAnalyticAttributeStore().delete(attribute);
                }
            }
            return true;
        } else {
            log.warning("Analytics controller is not enabled!");
            return false;
        }
    }

    public boolean removeAllAttributes() {
        log.debug("AnalyticsControllerImpl.removeAttributes - ");
        if (!initialized.get()) {
            log.warning("Analytics controller is not initialized!");
        } else if (this.isEnabled) {
            this.agentConfiguration.getAnalyticAttributeStore().clear();
            this.userAttributes.clear();
        } else {
            log.warning("Analytics controller is not enabled!");
        }
        return false;
    }

    public boolean addEvent(String str, Set<AnalyticAttribute> set) {
        return addEvent(str, AnalyticsEventCategory.Custom, AnalyticAttribute.EVENT_TYPE_ATTRIBUTE_MOBILE, set);
    }

    public boolean addEvent(String str, AnalyticsEventCategory analyticsEventCategory, String str2, Set<AnalyticAttribute> set) {
        if (!initialized.get()) {
            log.warning("Analytics controller is not initialized!");
            return false;
        } else if (this.isEnabled) {
            log.debug("AnalyticsControllerImpl.addEvent - " + str + ": category=" + analyticsEventCategory + ", eventType: " + str2 + ", eventAttributes:" + set);
            Set hashSet = new HashSet();
            for (AnalyticAttribute analyticAttribute : set) {
                if (isNameValid(analyticAttribute.getName())) {
                    hashSet.add(analyticAttribute);
                }
            }
            return addEvent(AnalyticsEventFactory.createEvent(str, analyticsEventCategory, str2, hashSet));
        } else {
            log.warning("Analytics controller is not enabled!");
            return false;
        }
    }

    public boolean addEvent(AnalyticsEvent analyticsEvent) {
        if (!initialized.get()) {
            log.warning("Analytics controller is not initialized!");
            return false;
        } else if (this.isEnabled) {
            Set hashSet = new HashSet();
            hashSet.add(new AnalyticAttribute(AnalyticAttribute.SESSION_TIME_SINCE_LOAD_ATTRIBUTE, ((float) this.agentImpl.getSessionDurationMillis()) / 1000.0f));
            analyticsEvent.addAttributes(hashSet);
            return this.eventManager.addEvent(analyticsEvent);
        } else {
            log.warning("Analytics controller is not enabled!");
            return false;
        }
    }

    public int getMaxEventPoolSize() {
        return this.eventManager.getMaxEventPoolSize();
    }

    public void setMaxEventPoolSize(int i) {
        this.eventManager.setMaxEventPoolSize(i);
    }

    public void setMaxEventBufferTime(int i) {
        this.eventManager.setMaxEventBufferTime(i);
    }

    public int getMaxEventBufferTime() {
        return this.eventManager.getMaxEventBufferTime();
    }

    public EventManager getEventManager() {
        return this.eventManager;
    }

    public static AnalyticsControllerImpl getInstance() {
        return instance;
    }

    void loadPersistentAttributes() {
        log.debug("AnalyticsControllerImpl.loadPersistentAttributes - loading userAttributes from the attribute store...");
        List<AnalyticAttribute> fetchAll = this.agentConfiguration.getAnalyticAttributeStore().fetchAll();
        log.debug("AnalyticsControllerImpl.loadPersistentAttributes - found " + fetchAll.size() + " userAttributes in the attribute store...");
        for (AnalyticAttribute add : fetchAll) {
            this.userAttributes.add(add);
        }
    }

    private AnalyticAttribute getSystemAttribute(String str) {
        for (AnalyticAttribute analyticAttribute : this.systemAttributes) {
            if (analyticAttribute.getName().equals(str)) {
                return analyticAttribute;
            }
        }
        return null;
    }

    private AnalyticAttribute getUserAttribute(String str) {
        for (AnalyticAttribute analyticAttribute : this.userAttributes) {
            if (analyticAttribute.getName().equals(str)) {
                return analyticAttribute;
            }
        }
        return null;
    }

    private void clear() {
        log.debug("AnalyticsControllerImpl.clear - clearing out attributes and events");
        this.systemAttributes.clear();
        this.userAttributes.clear();
        this.eventManager.empty();
    }

    private boolean isNameValid(String str) {
        boolean z = (str == null || str.equals(Trace.NULL) || str.length() >= AnalyticAttribute.ATTRIBUTE_NAME_MAX_LENGTH) ? false : true;
        if (z) {
            if (isNameReserved(str)) {
                z = false;
            } else {
                z = true;
            }
            if (!z) {
                log.error("Attribute name " + str + " is reserved for internal use and will be ignored.");
            }
        } else {
            log.error("Attribute name " + str + " is null, empty, or exceeds the maximum length of " + AnalyticAttribute.ATTRIBUTE_NAME_MAX_LENGTH + " characters.");
        }
        return z;
    }

    private boolean isStringValueValid(String str, String str2) {
        boolean z = (str2 == null || str2.equals(Trace.NULL) || str2.getBytes().length >= AnalyticAttribute.ATTRIBUTE_VALUE_MAX_LENGTH) ? false : true;
        if (!z) {
            log.error("Attribute value for name " + str + " is null, empty, or exceeds the maximum length of " + AnalyticAttribute.ATTRIBUTE_VALUE_MAX_LENGTH + " bytes.");
        }
        return z;
    }

    private boolean isNameReserved(String str) {
        boolean contains = reservedNames.contains(str);
        if (contains) {
            log.debug("Name " + str + " is in the reserved names list.");
        } else {
            if (contains || str.startsWith(NEW_RELIC_PREFIX)) {
                contains = true;
            } else {
                contains = false;
            }
            if (contains) {
                log.debug("Name " + str + " starts with reserved prefix " + NEW_RELIC_PREFIX);
            } else {
                if (contains || str.startsWith(NR_PREFIX)) {
                    contains = true;
                } else {
                    contains = false;
                }
                if (contains) {
                    log.debug("Name " + str + " starts with reserved prefix " + NR_PREFIX);
                }
            }
        }
        return contains;
    }

    public boolean recordEvent(String str, Map<String, Object> map) {
        log.debug("AnalyticsControllerImpl.recordEvent - " + str + ": " + map.size() + " attributes");
        Set hashSet = new HashSet();
        try {
            for (String str2 : map.keySet()) {
                Object obj = map.get(str2);
                try {
                    if (obj instanceof String) {
                        hashSet.add(new AnalyticAttribute(str2, (String) obj));
                    } else if (obj instanceof Float) {
                        hashSet.add(new AnalyticAttribute(str2, Float.valueOf(((Float) obj).floatValue()).floatValue()));
                    } else if (obj instanceof Double) {
                        hashSet.add(new AnalyticAttribute(str2, Float.valueOf(((Double) obj).floatValue()).floatValue()));
                    } else if (obj instanceof Integer) {
                        hashSet.add(new AnalyticAttribute(str2, Float.valueOf((float) ((Integer) obj).intValue()).floatValue()));
                    } else if (obj instanceof Short) {
                        hashSet.add(new AnalyticAttribute(str2, Float.valueOf((float) ((Short) obj).shortValue()).floatValue()));
                    } else if (obj instanceof Long) {
                        hashSet.add(new AnalyticAttribute(str2, Float.valueOf((float) ((Long) obj).longValue()).floatValue()));
                    } else if (obj instanceof BigDecimal) {
                        hashSet.add(new AnalyticAttribute(str2, Float.valueOf(((BigDecimal) obj).floatValue()).floatValue()));
                    } else if (obj instanceof BigInteger) {
                        hashSet.add(new AnalyticAttribute(str2, Float.valueOf(((BigInteger) obj).floatValue()).floatValue()));
                    } else {
                        log.error("Unsupported event attribute type for key [" + str2 + "]: " + obj.getClass().getName());
                        return false;
                    }
                } catch (Throwable e) {
                    log.error(String.format("Error casting attribute [%s] to String or Float: ", new Object[]{str2}), e);
                }
            }
        } catch (Throwable e2) {
            log.error(String.format("Error occurred while recording event [%s]: ", new Object[]{str}), e2);
        }
        return addEvent(str, AnalyticsEventCategory.Custom, AnalyticAttribute.EVENT_TYPE_ATTRIBUTE_MOBILE, hashSet);
    }
}
