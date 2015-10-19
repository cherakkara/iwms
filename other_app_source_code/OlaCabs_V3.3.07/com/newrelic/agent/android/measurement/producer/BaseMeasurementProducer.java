package com.newrelic.agent.android.measurement.producer;

import com.newrelic.agent.android.measurement.Measurement;
import com.newrelic.agent.android.measurement.MeasurementType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class BaseMeasurementProducer implements MeasurementProducer {
    private final MeasurementType producedMeasurementType;
    private final ArrayList<Measurement> producedMeasurements;

    public BaseMeasurementProducer(MeasurementType measurementType) {
        this.producedMeasurements = new ArrayList();
        this.producedMeasurementType = measurementType;
    }

    public MeasurementType getMeasurementType() {
        return this.producedMeasurementType;
    }

    public void produceMeasurement(Measurement measurement) {
        synchronized (this.producedMeasurements) {
            if (measurement != null) {
                this.producedMeasurements.add(measurement);
            }
        }
    }

    public void produceMeasurements(Collection<Measurement> collection) {
        synchronized (this.producedMeasurements) {
            if (collection != null) {
                this.producedMeasurements.addAll(collection);
                do {
                } while (this.producedMeasurements.remove(null));
            }
        }
    }

    public Collection<Measurement> drainMeasurements() {
        Collection<Measurement> emptyList;
        synchronized (this.producedMeasurements) {
            if (this.producedMeasurements.size() == 0) {
                emptyList = Collections.emptyList();
            } else {
                emptyList = new ArrayList(this.producedMeasurements);
                this.producedMeasurements.clear();
            }
        }
        return emptyList;
    }
}
