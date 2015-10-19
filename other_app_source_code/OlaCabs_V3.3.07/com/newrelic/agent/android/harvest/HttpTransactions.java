package com.newrelic.agent.android.harvest;

import com.newrelic.agent.android.harvest.type.HarvestableArray;
import com.newrelic.com.google.gson.JsonArray;
import java.util.Collection;
import java.util.concurrent.CopyOnWriteArrayList;

public class HttpTransactions extends HarvestableArray {
    private final Collection<HttpTransaction> httpTransactions;

    public HttpTransactions() {
        this.httpTransactions = new CopyOnWriteArrayList();
    }

    public synchronized void add(HttpTransaction httpTransaction) {
        this.httpTransactions.add(httpTransaction);
    }

    public synchronized void remove(HttpTransaction httpTransaction) {
        this.httpTransactions.remove(httpTransaction);
    }

    public void clear() {
        this.httpTransactions.clear();
    }

    public JsonArray asJsonArray() {
        JsonArray jsonArray = new JsonArray();
        for (HttpTransaction asJson : this.httpTransactions) {
            jsonArray.add(asJson.asJson());
        }
        return jsonArray;
    }

    public Collection<HttpTransaction> getHttpTransactions() {
        return this.httpTransactions;
    }

    public int count() {
        return this.httpTransactions.size();
    }

    public String toString() {
        return "HttpTransactions{httpTransactions=" + this.httpTransactions + '}';
    }
}
