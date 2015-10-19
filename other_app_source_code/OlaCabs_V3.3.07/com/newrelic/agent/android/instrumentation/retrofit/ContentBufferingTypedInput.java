package com.newrelic.agent.android.instrumentation.retrofit;

import com.newrelic.agent.android.instrumentation.io.CountingInputStream;
import com.newrelic.agent.android.logging.AgentLog;
import com.newrelic.agent.android.logging.AgentLogManager;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import retrofit.mime.TypedInput;

public class ContentBufferingTypedInput implements TypedInput {
    private static final AgentLog log;
    private TypedInput impl;
    private CountingInputStream inputStream;

    static {
        log = AgentLogManager.getAgentLog();
    }

    public ContentBufferingTypedInput(TypedInput typedInput) {
        if (typedInput == null) {
            typedInput = new EmptyBodyTypedInput();
        }
        this.impl = typedInput;
        this.inputStream = null;
    }

    public String mimeType() {
        return this.impl.mimeType();
    }

    public long length() {
        try {
            prepareInputStream();
            return (long) this.inputStream.available();
        } catch (Throwable e) {
            log.error("ContentBufferingTypedInput generated an IO exception: ", e);
            return -1;
        }
    }

    public InputStream in() throws IOException {
        prepareInputStream();
        return this.inputStream;
    }

    private void prepareInputStream() throws IOException {
        if (this.inputStream == null) {
            InputStream in = this.impl.in();
            if (in == null) {
                in = new ByteArrayInputStream(new byte[0]);
            }
            this.inputStream = new CountingInputStream(in, true);
        }
    }
}
