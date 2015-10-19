package com.newrelic.agent.android.harvest;

import com.newrelic.agent.android.metric.Metric;
import com.newrelic.agent.android.tracing.ActivityTrace;
import com.newrelic.agent.android.tracing.TraceMachine;
import java.util.List;

public class HarvestDataValidator extends HarvestAdapter {
    public void onHarvestFinalize() {
        if (Harvest.isInitialized()) {
            ensureActivityNameMetricsExist();
        }
    }

    public void ensureActivityNameMetricsExist() {
        HarvestData harvestData = Harvest.getInstance().getHarvestData();
        ActivityTraces activityTraces = harvestData.getActivityTraces();
        if (activityTraces != null && activityTraces.count() != 0) {
            MachineMeasurements metrics = harvestData.getMetrics();
            if (metrics != null && !metrics.isEmpty()) {
                for (ActivityTrace activityTrace : activityTraces.getActivityTraces()) {
                    int i;
                    String str = activityTrace.rootTrace.displayName;
                    int indexOf = str.indexOf("#");
                    if (indexOf > 0) {
                        str = str.substring(0, indexOf);
                    }
                    String str2 = TraceMachine.ACTIVITY_METRIC_PREFIX + str;
                    List<Metric> allUnscoped = metrics.getMetrics().getAllUnscoped();
                    if (allUnscoped != null && allUnscoped.size() > 0) {
                        for (Metric name : allUnscoped) {
                            if (name.getName().startsWith(str2)) {
                                i = 1;
                                break;
                            }
                        }
                    }
                    i = 0;
                    if (i == 0) {
                        metrics.addMetric(new Metric(str2));
                    }
                }
            }
        }
    }
}
