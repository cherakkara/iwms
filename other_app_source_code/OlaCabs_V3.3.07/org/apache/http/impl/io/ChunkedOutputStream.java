package org.apache.http.impl.io;

import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import com.newrelic.agent.android.instrumentation.Trace;
import java.io.IOException;
import java.io.OutputStream;
import org.apache.http.annotation.NotThreadSafe;
import org.apache.http.io.SessionOutputBuffer;

@NotThreadSafe
public class ChunkedOutputStream extends OutputStream {
    private final byte[] cache;
    private int cachePosition;
    private boolean closed;
    private final SessionOutputBuffer out;
    private boolean wroteLastChunk;

    @Deprecated
    public ChunkedOutputStream(SessionOutputBuffer sessionOutputBuffer, int i) throws IOException {
        this(i, sessionOutputBuffer);
    }

    @Deprecated
    public ChunkedOutputStream(SessionOutputBuffer sessionOutputBuffer) throws IOException {
        this((int) AccessibilityNodeInfoCompat.ACTION_PREVIOUS_HTML_ELEMENT, sessionOutputBuffer);
    }

    public ChunkedOutputStream(int i, SessionOutputBuffer sessionOutputBuffer) {
        this.cachePosition = 0;
        this.wroteLastChunk = false;
        this.closed = false;
        this.cache = new byte[i];
        this.out = sessionOutputBuffer;
    }

    protected void flushCache() throws IOException {
        if (this.cachePosition > 0) {
            this.out.writeLine(Integer.toHexString(this.cachePosition));
            this.out.write(this.cache, 0, this.cachePosition);
            this.out.writeLine(Trace.NULL);
            this.cachePosition = 0;
        }
    }

    protected void flushCacheWithAppend(byte[] bArr, int i, int i2) throws IOException {
        this.out.writeLine(Integer.toHexString(this.cachePosition + i2));
        this.out.write(this.cache, 0, this.cachePosition);
        this.out.write(bArr, i, i2);
        this.out.writeLine(Trace.NULL);
        this.cachePosition = 0;
    }

    protected void writeClosingChunk() throws IOException {
        this.out.writeLine("0");
        this.out.writeLine(Trace.NULL);
    }

    public void finish() throws IOException {
        if (!this.wroteLastChunk) {
            flushCache();
            writeClosingChunk();
            this.wroteLastChunk = true;
        }
    }

    public void write(int i) throws IOException {
        if (this.closed) {
            throw new IOException("Attempted write to closed stream.");
        }
        this.cache[this.cachePosition] = (byte) i;
        this.cachePosition++;
        if (this.cachePosition == this.cache.length) {
            flushCache();
        }
    }

    public void write(byte[] bArr) throws IOException {
        write(bArr, 0, bArr.length);
    }

    public void write(byte[] bArr, int i, int i2) throws IOException {
        if (this.closed) {
            throw new IOException("Attempted write to closed stream.");
        } else if (i2 >= this.cache.length - this.cachePosition) {
            flushCacheWithAppend(bArr, i, i2);
        } else {
            System.arraycopy(bArr, i, this.cache, this.cachePosition, i2);
            this.cachePosition += i2;
        }
    }

    public void flush() throws IOException {
        flushCache();
        this.out.flush();
    }

    public void close() throws IOException {
        if (!this.closed) {
            this.closed = true;
            finish();
            this.out.flush();
        }
    }
}
