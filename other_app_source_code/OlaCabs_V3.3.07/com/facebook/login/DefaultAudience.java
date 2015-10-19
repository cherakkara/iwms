package com.facebook.login;

/* renamed from: com.facebook.login.a */
public enum DefaultAudience {
    NONE(null),
    ONLY_ME("only_me"),
    FRIENDS("friends"),
    EVERYONE("everyone");
    
    private final String f995e;

    private DefaultAudience(String str) {
        this.f995e = str;
    }

    public String m1310a() {
        return this.f995e;
    }
}
