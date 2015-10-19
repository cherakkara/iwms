package com.newrelic.agent.android.instrumentation.io;

import java.io.IOException;
import java.io.OutputStream;

public final class CountingOutputStream extends OutputStream implements StreamCompleteListenerSource {
    private long count;
    private final OutputStream impl;
    private final StreamCompleteListenerManager listenerManager;

    public CountingOutputStream(OutputStream outputStream) {
        this.count = 0;
        this.listenerManager = new StreamCompleteListenerManager();
        this.impl = outputStream;
    }

    public void addStreamCompleteListener(StreamCompleteListener streamCompleteListener) {
        this.listenerManager.addStreamCompleteListener(streamCompleteListener);
    }

    public void removeStreamCompleteListener(StreamCompleteListener streamCompleteListener) {
        this.listenerManager.removeStreamCompleteListener(streamCompleteListener);
    }

    public long getCount() {
        return this.count;
    }

    public void write(int i) throws IOException {
        try {
            this.impl.write(i);
            this.count++;
        } catch (Exception e) {
            notifyStreamError(e);
            throw e;
        }
    }

    public void write(byte[] bArr) throws IOException {
        try {
            this.impl.write(bArr);
            this.count += (long) bArr.length;
        } catch (Exception e) {
            notifyStreamError(e);
            throw e;
        }
    }

    public void write(byte[] bArr, int i, int i2) throws IOException {
        try {
            this.impl.write(bArr, i, i2);
            this.count += (long) i2;
        } catch (Exception e) {
            notifyStreamError(e);
            throw e;
        }
    }

    public void flush() throws IOException {
        try {
            this.impl.flush();
        } catch (Exception e) {
            notifyStreamError(e);
            throw e;
        }
    }

    public void close() throws IOException {
        try {
            this.impl.close();
            notifyStreamComplete();
        } catch (Exception e) {
            notifyStreamError(e);
            throw e;
        }
    }

    private void notifyStreamComplete() {
        if (!this.listenerManager.isComplete()) {
            this.listenerManager.notifyStreamComplete(new StreamCompleteEvent(this, this.count));
        }
    }

    private void notifyStreamError(Exception exception) {
        if (!this.listenerManager.isComplete()) {
            this.listenerManager.notifyStreamError(new StreamCompleteEvent(this, this.count, exception));
        }
    }
}
