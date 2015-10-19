package com.facebook;

/* renamed from: com.facebook.l */
public class FacebookServiceException extends FacebookException {
    private final FacebookRequestError f949a;

    public FacebookServiceException(FacebookRequestError facebookRequestError, String str) {
        super(str);
        this.f949a = facebookRequestError;
    }

    public final FacebookRequestError m1216a() {
        return this.f949a;
    }

    public final String toString() {
        return "{FacebookServiceException: " + "httpResponseCode: " + this.f949a.m1190a() + ", facebookErrorCode: " + this.f949a.m1191b() + ", facebookErrorType: " + this.f949a.m1192c() + ", message: " + this.f949a.m1193d() + "}";
    }
}
