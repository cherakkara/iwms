package com.newrelic.agent.android.metric;

import com.newrelic.agent.android.analytics.AnalyticAttribute;
import com.newrelic.agent.android.harvest.type.HarvestableObject;
import com.newrelic.agent.android.instrumentation.Trace;
import com.newrelic.com.google.gson.JsonElement;
import com.newrelic.com.google.gson.JsonObject;
import com.newrelic.com.google.gson.JsonPrimitive;

public class Metric extends HarvestableObject {
    private long count;
    private Double exclusive;
    private Double max;
    private Double min;
    private String name;
    private String scope;
    private Double sumOfSquares;
    private Double total;

    public Metric(String str) {
        this(str, null);
    }

    public Metric(String str, String str2) {
        this.name = str;
        this.scope = str2;
        this.count = 0;
    }

    public Metric(Metric metric) {
        this.name = metric.getName();
        this.scope = metric.getScope();
        this.min = Double.valueOf(metric.getMin());
        this.max = Double.valueOf(metric.getMax());
        this.total = Double.valueOf(metric.getTotal());
        this.sumOfSquares = Double.valueOf(metric.getSumOfSquares());
        this.exclusive = Double.valueOf(metric.getExclusive());
        this.count = metric.getCount();
    }

    public void sample(double d) {
        this.count++;
        if (this.total == null) {
            this.total = Double.valueOf(d);
            this.sumOfSquares = Double.valueOf(d * d);
        } else {
            this.total = Double.valueOf(this.total.doubleValue() + d);
            this.sumOfSquares = Double.valueOf(this.sumOfSquares.doubleValue() + (d * d));
        }
        setMin(Double.valueOf(d));
        setMax(Double.valueOf(d));
    }

    public void setMin(Double d) {
        if (d != null) {
            if (this.min == null) {
                this.min = d;
            } else if (d.doubleValue() < this.min.doubleValue()) {
                this.min = d;
            }
        }
    }

    public void setMinFieldValue(Double d) {
        this.min = d;
    }

    public void setMax(Double d) {
        if (d != null) {
            if (this.max == null) {
                this.max = d;
            } else if (d.doubleValue() > this.max.doubleValue()) {
                this.max = d;
            }
        }
    }

    public void setMaxFieldValue(Double d) {
        this.max = d;
    }

    public void aggregate(Metric metric) {
        if (metric != null) {
            increment(metric.getCount());
            if (!metric.isCountOnly()) {
                this.total = Double.valueOf(this.total == null ? metric.getTotal() : this.total.doubleValue() + metric.getTotal());
                this.sumOfSquares = Double.valueOf(this.sumOfSquares == null ? metric.getSumOfSquares() : this.sumOfSquares.doubleValue() + metric.getSumOfSquares());
                this.exclusive = Double.valueOf(this.exclusive == null ? metric.getExclusive() : this.exclusive.doubleValue() + metric.getExclusive());
                setMin(Double.valueOf(metric.getMin()));
                setMax(Double.valueOf(metric.getMax()));
            }
        }
    }

    public void increment(long j) {
        this.count += j;
    }

    public void increment() {
        increment(1);
    }

    public double getSumOfSquares() {
        return this.sumOfSquares == null ? 0.0d : this.sumOfSquares.doubleValue();
    }

    public long getCount() {
        return this.count;
    }

    public double getExclusive() {
        return this.exclusive == null ? 0.0d : this.exclusive.doubleValue();
    }

    public void addExclusive(double d) {
        if (this.exclusive == null) {
            this.exclusive = Double.valueOf(d);
        } else {
            this.exclusive = Double.valueOf(this.exclusive.doubleValue() + d);
        }
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getScope() {
        return this.scope;
    }

    public String getStringScope() {
        return this.scope == null ? Trace.NULL : this.scope;
    }

    public void setScope(String str) {
        this.scope = str;
    }

    public double getMin() {
        return this.min == null ? 0.0d : this.min.doubleValue();
    }

    public double getMax() {
        return this.max == null ? 0.0d : this.max.doubleValue();
    }

    public double getTotal() {
        return this.total == null ? 0.0d : this.total.doubleValue();
    }

    public void setTotal(Double d) {
        this.total = d;
    }

    public void setSumOfSquares(Double d) {
        this.sumOfSquares = d;
    }

    public void setExclusive(Double d) {
        this.exclusive = d;
    }

    public void setCount(long j) {
        this.count = j;
    }

    public void clear() {
        this.min = null;
        this.max = null;
        this.total = null;
        this.sumOfSquares = null;
        this.exclusive = null;
        this.count = 0;
    }

    public boolean isCountOnly() {
        return this.total == null;
    }

    public boolean isScoped() {
        return this.scope != null;
    }

    public boolean isUnscoped() {
        return this.scope == null;
    }

    public JsonElement asJson() {
        if (isCountOnly()) {
            return new JsonPrimitive(Long.valueOf(this.count));
        }
        return asJsonObject();
    }

    public JsonObject asJsonObject() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("count", new JsonPrimitive(Long.valueOf(this.count)));
        if (this.total != null) {
            jsonObject.add(AnalyticAttribute.PURCHASE_EVENT_TOTAL_PRICE_ATTRIBUTE, new JsonPrimitive(this.total));
        }
        if (this.min != null) {
            jsonObject.add("min", new JsonPrimitive(this.min));
        }
        if (this.max != null) {
            jsonObject.add("max", new JsonPrimitive(this.max));
        }
        if (this.sumOfSquares != null) {
            jsonObject.add("sum_of_squares", new JsonPrimitive(this.sumOfSquares));
        }
        if (this.exclusive != null) {
            jsonObject.add("exclusive", new JsonPrimitive(this.exclusive));
        }
        return jsonObject;
    }

    public String toString() {
        return "Metric{count=" + this.count + ", total=" + this.total + ", max=" + this.max + ", min=" + this.min + ", scope='" + this.scope + '\'' + ", name='" + this.name + '\'' + ", exclusive='" + this.exclusive + '\'' + ", sumofsquares='" + this.sumOfSquares + '\'' + '}';
    }
}
