package com.crashlytics.android.answers;

import java.util.HashSet;
import java.util.Set;

public class SamplingEventFilter implements EventFilter {
    static final Set<Type> EVENTS_TYPE_TO_SAMPLE;
    final int samplingRate;

    /* renamed from: com.crashlytics.android.answers.SamplingEventFilter.1 */
    static class C01151 extends HashSet<Type> {
        C01151() {
            add(Type.CREATE);
            add(Type.START);
            add(Type.RESUME);
            add(Type.SAVE_INSTANCE_STATE);
            add(Type.PAUSE);
            add(Type.STOP);
            add(Type.DESTROY);
            add(Type.ERROR);
            add(Type.CRASH);
        }
    }

    static {
        EVENTS_TYPE_TO_SAMPLE = new C01151();
    }

    public SamplingEventFilter(int i) {
        this.samplingRate = i;
    }

    public boolean skipEvent(SessionEvent sessionEvent) {
        if (isNeverSampledEvent(sessionEvent)) {
            return false;
        }
        return shouldSkipEventBasedOnInstallID(sessionEvent);
    }

    boolean isNeverSampledEvent(SessionEvent sessionEvent) {
        return (EVENTS_TYPE_TO_SAMPLE.contains(sessionEvent.type) && sessionEvent.sessionEventMetadata.betaDeviceToken == null) ? false : true;
    }

    boolean shouldSkipEventBasedOnInstallID(SessionEvent sessionEvent) {
        return Math.abs(sessionEvent.sessionEventMetadata.installationId.hashCode() % this.samplingRate) != 0;
    }
}
