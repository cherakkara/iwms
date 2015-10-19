package com.newrelic.agent.android.harvest;

import com.newrelic.agent.android.harvest.type.HarvestableArray;
import com.newrelic.com.google.gson.JsonArray;
import java.util.Collection;
import java.util.concurrent.CopyOnWriteArrayList;

public class HttpErrors extends HarvestableArray {
    private final Collection<HttpError> httpErrors;

    public HttpErrors() {
        this.httpErrors = new CopyOnWriteArrayList();
    }

    public void addHttpError(HttpError httpError) {
        synchronized (httpError) {
            for (HttpError httpError2 : this.httpErrors) {
                if (httpError.getHash().equals(httpError2.getHash())) {
                    httpError2.incrementCount();
                    return;
                }
            }
            this.httpErrors.add(httpError);
        }
    }

    public synchronized void removeHttpError(HttpError httpError) {
        this.httpErrors.remove(httpError);
    }

    public void clear() {
        this.httpErrors.clear();
    }

    public JsonArray asJsonArray() {
        JsonArray jsonArray = new JsonArray();
        for (HttpError asJson : this.httpErrors) {
            jsonArray.add(asJson.asJson());
        }
        return jsonArray;
    }

    public Collection<HttpError> getHttpErrors() {
        return this.httpErrors;
    }

    public int count() {
        return this.httpErrors.size();
    }
}
