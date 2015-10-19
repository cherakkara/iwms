package com.newrelic.agent.android.measurement.consumer;

import com.newrelic.agent.android.Agent;
import com.newrelic.agent.android.harvest.Harvest;
import com.newrelic.agent.android.harvest.HttpTransaction;
import com.newrelic.agent.android.measurement.Measurement;
import com.newrelic.agent.android.measurement.MeasurementType;
import com.newrelic.agent.android.measurement.http.HttpTransactionMeasurement;

public class HttpTransactionHarvestingConsumer extends BaseMeasurementConsumer {
    public HttpTransactionHarvestingConsumer() {
        super(MeasurementType.Network);
    }

    public void consumeMeasurement(Measurement measurement) {
        HttpTransactionMeasurement httpTransactionMeasurement = (HttpTransactionMeasurement) measurement;
        HttpTransaction httpTransaction = new HttpTransaction();
        httpTransaction.setUrl(httpTransactionMeasurement.getUrl());
        httpTransaction.setHttpMethod(httpTransactionMeasurement.getHttpMethod());
        httpTransaction.setStatusCode(httpTransactionMeasurement.getStatusCode());
        httpTransaction.setErrorCode(httpTransactionMeasurement.getErrorCode());
        httpTransaction.setTotalTime(httpTransactionMeasurement.getTotalTime());
        httpTransaction.setCarrier(Agent.getActiveNetworkCarrier());
        httpTransaction.setWanType(Agent.getActiveNetworkWanType());
        httpTransaction.setBytesReceived(httpTransactionMeasurement.getBytesReceived());
        httpTransaction.setBytesSent(httpTransactionMeasurement.getBytesSent());
        httpTransaction.setAppData(httpTransactionMeasurement.getAppData());
        httpTransaction.setTimestamp(Long.valueOf(httpTransactionMeasurement.getStartTime()));
        Harvest.addHttpTransaction(httpTransaction);
    }
}
