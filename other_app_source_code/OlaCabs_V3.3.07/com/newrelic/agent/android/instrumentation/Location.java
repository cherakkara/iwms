package com.newrelic.agent.android.instrumentation;

public class Location {
    private final String countryCode;
    private final String region;

    public Location(String str, String str2) {
        if (str == null || str2 == null) {
            throw new IllegalArgumentException("Country code and region must not be null.");
        }
        this.countryCode = str;
        this.region = str2;
    }

    public String getCountryCode() {
        return this.countryCode;
    }

    public String getRegion() {
        return this.region;
    }
}
