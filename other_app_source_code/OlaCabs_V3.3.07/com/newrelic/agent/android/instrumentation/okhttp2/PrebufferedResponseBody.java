package com.newrelic.agent.android.instrumentation.okhttp2;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.ResponseBody;
import java.io.IOException;
import okio.BufferedSource;

public class PrebufferedResponseBody extends ResponseBody {
    private ResponseBody impl;
    private BufferedSource source;

    public PrebufferedResponseBody(ResponseBody responseBody, BufferedSource bufferedSource) {
        this.impl = responseBody;
        this.source = bufferedSource;
    }

    public MediaType contentType() {
        return this.impl.contentType();
    }

    public long contentLength() {
        return this.source.buffer().size();
    }

    public BufferedSource source() {
        return this.source;
    }

    public void close() throws IOException {
        this.impl.close();
    }
}
