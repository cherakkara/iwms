package com.newrelic.agent.android.measurement.producer;

import com.newrelic.agent.android.measurement.MeasurementType;
import com.newrelic.agent.android.measurement.http.HttpTransactionMeasurement;
import com.newrelic.agent.android.util.Util;

public class NetworkMeasurementProducer extends BaseMeasurementProducer {
    public NetworkMeasurementProducer() {
        super(MeasurementType.Network);
    }

    public void produceMeasurement(String str, String str2, int i, int i2, long j, double d, long j2, long j3, String str3) {
        String sanitizeUrl = Util.sanitizeUrl(str);
        if (sanitizeUrl != null) {
            produceMeasurement(new HttpTransactionMeasurement(sanitizeUrl, str2, i, i2, j, d, j2, j3, str3));
        }
    }

    public void produceMeasurement(HttpTransactionMeasurement httpTransactionMeasurement) {
        String sanitizeUrl = Util.sanitizeUrl(httpTransactionMeasurement.getUrl());
        if (sanitizeUrl != null) {
            httpTransactionMeasurement.setUrl(sanitizeUrl);
            super.produceMeasurement(httpTransactionMeasurement);
        }
    }
}
