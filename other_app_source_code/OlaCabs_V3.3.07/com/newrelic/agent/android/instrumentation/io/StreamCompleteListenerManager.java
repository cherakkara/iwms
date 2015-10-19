package com.newrelic.agent.android.instrumentation.io;

import java.util.ArrayList;
import java.util.List;

class StreamCompleteListenerManager {
    private boolean streamComplete;
    private ArrayList<StreamCompleteListener> streamCompleteListeners;

    StreamCompleteListenerManager() {
        this.streamComplete = false;
        this.streamCompleteListeners = new ArrayList();
    }

    public boolean isComplete() {
        boolean z;
        synchronized (this) {
            z = this.streamComplete;
        }
        return z;
    }

    public void addStreamCompleteListener(StreamCompleteListener streamCompleteListener) {
        synchronized (this.streamCompleteListeners) {
            this.streamCompleteListeners.add(streamCompleteListener);
        }
    }

    public void removeStreamCompleteListener(StreamCompleteListener streamCompleteListener) {
        synchronized (this.streamCompleteListeners) {
            this.streamCompleteListeners.remove(streamCompleteListener);
        }
    }

    public void notifyStreamComplete(StreamCompleteEvent streamCompleteEvent) {
        if (!checkComplete()) {
            for (StreamCompleteListener streamComplete : getStreamCompleteListeners()) {
                streamComplete.streamComplete(streamCompleteEvent);
            }
        }
    }

    public void notifyStreamError(StreamCompleteEvent streamCompleteEvent) {
        if (!checkComplete()) {
            for (StreamCompleteListener streamError : getStreamCompleteListeners()) {
                streamError.streamError(streamCompleteEvent);
            }
        }
    }

    private boolean checkComplete() {
        boolean isComplete;
        synchronized (this) {
            isComplete = isComplete();
            if (!isComplete) {
                this.streamComplete = true;
            }
        }
        return isComplete;
    }

    private List<StreamCompleteListener> getStreamCompleteListeners() {
        List arrayList;
        synchronized (this.streamCompleteListeners) {
            arrayList = new ArrayList(this.streamCompleteListeners);
            this.streamCompleteListeners.clear();
        }
        return arrayList;
    }
}
