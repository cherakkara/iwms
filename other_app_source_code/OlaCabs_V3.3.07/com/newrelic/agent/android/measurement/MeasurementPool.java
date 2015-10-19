package com.newrelic.agent.android.measurement;

import com.newrelic.agent.android.logging.AgentLog;
import com.newrelic.agent.android.logging.AgentLogManager;
import com.newrelic.agent.android.measurement.consumer.MeasurementConsumer;
import com.newrelic.agent.android.measurement.producer.BaseMeasurementProducer;
import com.newrelic.agent.android.measurement.producer.MeasurementProducer;
import java.util.Collection;
import java.util.concurrent.CopyOnWriteArrayList;

public class MeasurementPool extends BaseMeasurementProducer implements MeasurementConsumer {
    private static final AgentLog log;
    private final Collection<MeasurementConsumer> consumers;
    private final Collection<MeasurementProducer> producers;

    static {
        log = AgentLogManager.getAgentLog();
    }

    public MeasurementPool() {
        super(MeasurementType.Any);
        this.producers = new CopyOnWriteArrayList();
        this.consumers = new CopyOnWriteArrayList();
        addMeasurementProducer(this);
    }

    public void addMeasurementProducer(MeasurementProducer measurementProducer) {
        if (measurementProducer != null) {
            synchronized (this.producers) {
                if (this.producers.contains(measurementProducer)) {
                    log.debug("Attempted to add the same MeasurementProducer " + measurementProducer + "  multiple times.");
                    return;
                }
                this.producers.add(measurementProducer);
                return;
            }
        }
        log.debug("Attempted to add null MeasurementProducer.");
    }

    public void removeMeasurementProducer(MeasurementProducer measurementProducer) {
        synchronized (this.producers) {
            if (this.producers.contains(measurementProducer)) {
                this.producers.remove(measurementProducer);
                return;
            }
            log.debug("Attempted to remove MeasurementProducer " + measurementProducer + " which is not registered.");
        }
    }

    public void addMeasurementConsumer(MeasurementConsumer measurementConsumer) {
        if (measurementConsumer != null) {
            synchronized (this.consumers) {
                if (this.consumers.contains(measurementConsumer)) {
                    log.debug("Attempted to add the same MeasurementConsumer " + measurementConsumer + " multiple times.");
                    return;
                }
                this.consumers.add(measurementConsumer);
                return;
            }
        }
        log.debug("Attempted to add null MeasurementConsumer.");
    }

    public void removeMeasurementConsumer(MeasurementConsumer measurementConsumer) {
        synchronized (this.consumers) {
            if (this.consumers.contains(measurementConsumer)) {
                this.consumers.remove(measurementConsumer);
                return;
            }
            log.debug("Attempted to remove MeasurementConsumer " + measurementConsumer + " which is not registered.");
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void broadcastMeasurements() {
        /*
        r9 = this;
        r2 = new java.util.ArrayList;
        r2.<init>();
        r1 = r9.producers;
        monitor-enter(r1);
        r0 = r9.producers;	 Catch:{ all -> 0x00a4 }
        r3 = r0.iterator();	 Catch:{ all -> 0x00a4 }
    L_0x000e:
        r0 = r3.hasNext();	 Catch:{ all -> 0x00a4 }
        if (r0 == 0) goto L_0x002f;
    L_0x0014:
        r0 = r3.next();	 Catch:{ all -> 0x00a4 }
        r0 = (com.newrelic.agent.android.measurement.producer.MeasurementProducer) r0;	 Catch:{ all -> 0x00a4 }
        r0 = r0.drainMeasurements();	 Catch:{ all -> 0x00a4 }
        r4 = r0.size();	 Catch:{ all -> 0x00a4 }
        if (r4 <= 0) goto L_0x000e;
    L_0x0024:
        r2.addAll(r0);	 Catch:{ all -> 0x00a4 }
    L_0x0027:
        r0 = 0;
        r0 = r2.remove(r0);	 Catch:{ all -> 0x00a4 }
        if (r0 == 0) goto L_0x000e;
    L_0x002e:
        goto L_0x0027;
    L_0x002f:
        monitor-exit(r1);	 Catch:{ all -> 0x00a4 }
        r0 = r2.size();
        if (r0 <= 0) goto L_0x00a8;
    L_0x0036:
        r3 = r9.consumers;
        monitor-enter(r3);
        r0 = r9.consumers;	 Catch:{ all -> 0x00a1 }
        r4 = r0.iterator();	 Catch:{ all -> 0x00a1 }
    L_0x003f:
        r0 = r4.hasNext();	 Catch:{ all -> 0x00a1 }
        if (r0 == 0) goto L_0x00a7;
    L_0x0045:
        r0 = r4.next();	 Catch:{ all -> 0x00a1 }
        r0 = (com.newrelic.agent.android.measurement.consumer.MeasurementConsumer) r0;	 Catch:{ all -> 0x00a1 }
        r1 = new java.util.ArrayList;	 Catch:{ all -> 0x00a1 }
        r1.<init>(r2);	 Catch:{ all -> 0x00a1 }
        r5 = r1.iterator();	 Catch:{ all -> 0x00a1 }
    L_0x0054:
        r1 = r5.hasNext();	 Catch:{ all -> 0x00a1 }
        if (r1 == 0) goto L_0x003f;
    L_0x005a:
        r1 = r5.next();	 Catch:{ all -> 0x00a1 }
        r1 = (com.newrelic.agent.android.measurement.Measurement) r1;	 Catch:{ all -> 0x00a1 }
        r6 = r0.getMeasurementType();	 Catch:{ all -> 0x00a1 }
        r7 = r1.getType();	 Catch:{ all -> 0x00a1 }
        if (r6 == r7) goto L_0x0072;
    L_0x006a:
        r6 = r0.getMeasurementType();	 Catch:{ all -> 0x00a1 }
        r7 = com.newrelic.agent.android.measurement.MeasurementType.Any;	 Catch:{ all -> 0x00a1 }
        if (r6 != r7) goto L_0x0054;
    L_0x0072:
        r0.consumeMeasurement(r1);	 Catch:{ Exception -> 0x0076 }
        goto L_0x0054;
    L_0x0076:
        r1 = move-exception;
        com.newrelic.agent.android.util.ExceptionHelper.exceptionToErrorCode(r1);	 Catch:{ all -> 0x00a1 }
        r6 = log;	 Catch:{ all -> 0x00a1 }
        r7 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00a1 }
        r7.<init>();	 Catch:{ all -> 0x00a1 }
        r8 = "broadcastMeasurements exception[";
        r7 = r7.append(r8);	 Catch:{ all -> 0x00a1 }
        r1 = r1.getClass();	 Catch:{ all -> 0x00a1 }
        r1 = r1.getName();	 Catch:{ all -> 0x00a1 }
        r1 = r7.append(r1);	 Catch:{ all -> 0x00a1 }
        r7 = "]";
        r1 = r1.append(r7);	 Catch:{ all -> 0x00a1 }
        r1 = r1.toString();	 Catch:{ all -> 0x00a1 }
        r6.error(r1);	 Catch:{ all -> 0x00a1 }
        goto L_0x0054;
    L_0x00a1:
        r0 = move-exception;
        monitor-exit(r3);	 Catch:{ all -> 0x00a1 }
        throw r0;
    L_0x00a4:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x00a4 }
        throw r0;
    L_0x00a7:
        monitor-exit(r3);	 Catch:{ all -> 0x00a1 }
    L_0x00a8:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.newrelic.agent.android.measurement.MeasurementPool.broadcastMeasurements():void");
    }

    public void consumeMeasurement(Measurement measurement) {
        produceMeasurement(measurement);
    }

    public void consumeMeasurements(Collection<Measurement> collection) {
        produceMeasurements(collection);
    }

    public MeasurementType getMeasurementType() {
        return MeasurementType.Any;
    }

    public Collection<MeasurementProducer> getMeasurementProducers() {
        return this.producers;
    }

    public Collection<MeasurementConsumer> getMeasurementConsumers() {
        return this.consumers;
    }
}
