package com.crashlytics.android.answers;

import java.util.HashMap;
import java.util.Map;

public final class EventAttributes {
    public static final int MAX_NUM_ATTRIBUTES = 20;
    public static final int MAX_STRING_LENGTH = 100;
    final Map<String, Object> attributes;

    public EventAttributes() {
        this.attributes = new HashMap();
    }

    public EventAttributes put(String str, String str2) {
        if (str == null) {
            throw new NullPointerException("key must not be null");
        } else if (str2 == null) {
            throw new NullPointerException("value must not be null");
        } else {
            validateStringLength(str);
            validateStringLength(str2);
            putAttribute(str, str2);
            return this;
        }
    }

    public EventAttributes put(String str, Number number) {
        if (str == null) {
            throw new NullPointerException("key must not be null");
        } else if (number == null) {
            throw new NullPointerException("value must not be null");
        } else {
            validateStringLength(str);
            putAttribute(str, number);
            return this;
        }
    }

    void putAttribute(String str, Object obj) {
        if (this.attributes.size() < MAX_NUM_ATTRIBUTES || this.attributes.containsKey(str)) {
            this.attributes.put(str, obj);
        } else {
            throw new IllegalStateException(String.format("Event cannot have more than %d attributes", new Object[]{Integer.valueOf(MAX_NUM_ATTRIBUTES)}));
        }
    }

    static void validateStringLength(String str) {
        if (str.length() > MAX_STRING_LENGTH) {
            throw new IllegalArgumentException(String.format("String cannot be longer than %d characters", new Object[]{Integer.valueOf(MAX_STRING_LENGTH)}));
        }
    }
}
