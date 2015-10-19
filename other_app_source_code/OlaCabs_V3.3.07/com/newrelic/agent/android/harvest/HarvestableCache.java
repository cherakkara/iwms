package com.newrelic.agent.android.harvest;

import com.newrelic.agent.android.harvest.type.Harvestable;
import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.CopyOnWriteArrayList;

public class HarvestableCache {
    private static final int DEFAULT_CACHE_LIMIT = 1024;
    private final Collection<Harvestable> cache;
    private int limit;

    public HarvestableCache() {
        this.limit = DEFAULT_CACHE_LIMIT;
        this.cache = getNewCache();
    }

    protected Collection<Harvestable> getNewCache() {
        return new CopyOnWriteArrayList();
    }

    public void add(Harvestable harvestable) {
        if (harvestable != null && this.cache.size() < this.limit) {
            this.cache.add(harvestable);
        }
    }

    public boolean get(Object obj) {
        return this.cache.contains(obj);
    }

    public Collection<Harvestable> flush() {
        if (this.cache.size() == 0) {
            return Collections.emptyList();
        }
        Collection<Harvestable> newCache;
        synchronized (this) {
            newCache = getNewCache();
            newCache.addAll(this.cache);
            this.cache.clear();
        }
        return newCache;
    }

    public int getSize() {
        return this.cache.size();
    }

    public void setLimit(int i) {
        this.limit = i;
    }
}
