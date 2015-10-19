package com.newrelic.agent.android.harvest;

import org.apache.http.HttpStatus;

public class HarvestResponse {
    private static final String DISABLE_STRING = "DISABLE_NEW_RELIC";
    private String responseBody;
    private long responseTime;
    private int statusCode;

    public enum Code {
        OK(HttpStatus.SC_OK),
        UNAUTHORIZED(HttpStatus.SC_UNAUTHORIZED),
        FORBIDDEN(HttpStatus.SC_FORBIDDEN),
        ENTITY_TOO_LARGE(HttpStatus.SC_REQUEST_TOO_LONG),
        INVALID_AGENT_ID(450),
        UNSUPPORTED_MEDIA_TYPE(HttpStatus.SC_UNSUPPORTED_MEDIA_TYPE),
        INTERNAL_SERVER_ERROR(HttpStatus.SC_INTERNAL_SERVER_ERROR),
        UNKNOWN(-1);
        
        int statusCode;

        private Code(int i) {
            this.statusCode = i;
        }

        public int getStatusCode() {
            return this.statusCode;
        }

        public boolean isError() {
            return this != OK;
        }

        public boolean isOK() {
            return !isError();
        }
    }

    public Code getResponseCode() {
        if (isOK()) {
            return Code.OK;
        }
        for (Code code : Code.values()) {
            if (code.getStatusCode() == this.statusCode) {
                return code;
            }
        }
        return Code.UNKNOWN;
    }

    public boolean isDisableCommand() {
        return Code.FORBIDDEN == getResponseCode() && DISABLE_STRING.equals(getResponseBody());
    }

    public boolean isError() {
        return this.statusCode >= HttpStatus.SC_BAD_REQUEST;
    }

    public boolean isUnknown() {
        return getResponseCode() == Code.UNKNOWN;
    }

    public boolean isOK() {
        return !isError();
    }

    public int getStatusCode() {
        return this.statusCode;
    }

    public void setStatusCode(int i) {
        this.statusCode = i;
    }

    public String getResponseBody() {
        return this.responseBody;
    }

    public void setResponseBody(String str) {
        this.responseBody = str;
    }

    public long getResponseTime() {
        return this.responseTime;
    }

    public void setResponseTime(long j) {
        this.responseTime = j;
    }
}
