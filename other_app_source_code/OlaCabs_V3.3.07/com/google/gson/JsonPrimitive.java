package com.google.gson;

import com.google.gson.b.C$Gson$Preconditions;
import com.google.gson.p064b.LazilyParsedNumber;
import java.math.BigInteger;

/* renamed from: com.google.gson.q */
public final class JsonPrimitive extends JsonElement {
    private static final Class<?>[] f8510a;
    private Object f8511b;

    static {
        f8510a = new Class[]{Integer.TYPE, Long.TYPE, Short.TYPE, Float.TYPE, Double.TYPE, Byte.TYPE, Boolean.TYPE, Character.TYPE, Integer.class, Long.class, Short.class, Float.class, Double.class, Byte.class, Boolean.class, Character.class};
    }

    public JsonPrimitive(Boolean bool) {
        m12383a((Object) bool);
    }

    public JsonPrimitive(Number number) {
        m12383a((Object) number);
    }

    public JsonPrimitive(String str) {
        m12383a((Object) str);
    }

    void m12383a(Object obj) {
        if (obj instanceof Character) {
            this.f8511b = String.valueOf(((Character) obj).charValue());
            return;
        }
        boolean z = (obj instanceof Number) || JsonPrimitive.m12381b(obj);
        C$Gson$Preconditions.m12215a(z);
        this.f8511b = obj;
    }

    public boolean m12390o() {
        return this.f8511b instanceof Boolean;
    }

    Boolean m12389n() {
        return (Boolean) this.f8511b;
    }

    public boolean m12388f() {
        if (m12390o()) {
            return m12389n().booleanValue();
        }
        return Boolean.parseBoolean(m12384b());
    }

    public boolean m12391p() {
        return this.f8511b instanceof Number;
    }

    public Number m12382a() {
        return this.f8511b instanceof String ? new LazilyParsedNumber((String) this.f8511b) : (Number) this.f8511b;
    }

    public boolean m12392q() {
        return this.f8511b instanceof String;
    }

    public String m12384b() {
        if (m12391p()) {
            return m12382a().toString();
        }
        if (m12390o()) {
            return m12389n().toString();
        }
        return (String) this.f8511b;
    }

    public double m12385c() {
        return m12391p() ? m12382a().doubleValue() : Double.parseDouble(m12384b());
    }

    public long m12386d() {
        return m12391p() ? m12382a().longValue() : Long.parseLong(m12384b());
    }

    public int m12387e() {
        return m12391p() ? m12382a().intValue() : Integer.parseInt(m12384b());
    }

    private static boolean m12381b(Object obj) {
        if (obj instanceof String) {
            return true;
        }
        Class cls = obj.getClass();
        for (Class isAssignableFrom : f8510a) {
            if (isAssignableFrom.isAssignableFrom(cls)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        if (this.f8511b == null) {
            return 31;
        }
        long longValue;
        if (JsonPrimitive.m12380a(this)) {
            longValue = m12382a().longValue();
            return (int) (longValue ^ (longValue >>> 32));
        } else if (!(this.f8511b instanceof Number)) {
            return this.f8511b.hashCode();
        } else {
            longValue = Double.doubleToLongBits(m12382a().doubleValue());
            return (int) (longValue ^ (longValue >>> 32));
        }
    }

    public boolean equals(Object obj) {
        boolean z = false;
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        JsonPrimitive jsonPrimitive = (JsonPrimitive) obj;
        if (this.f8511b == null) {
            if (jsonPrimitive.f8511b != null) {
                return false;
            }
            return true;
        } else if (JsonPrimitive.m12380a(this) && JsonPrimitive.m12380a(jsonPrimitive)) {
            if (m12382a().longValue() != jsonPrimitive.m12382a().longValue()) {
                return false;
            }
            return true;
        } else if (!(this.f8511b instanceof Number) || !(jsonPrimitive.f8511b instanceof Number)) {
            return this.f8511b.equals(jsonPrimitive.f8511b);
        } else {
            double doubleValue = m12382a().doubleValue();
            double doubleValue2 = jsonPrimitive.m12382a().doubleValue();
            if (doubleValue == doubleValue2 || (Double.isNaN(doubleValue) && Double.isNaN(doubleValue2))) {
                z = true;
            }
            return z;
        }
    }

    private static boolean m12380a(JsonPrimitive jsonPrimitive) {
        if (!(jsonPrimitive.f8511b instanceof Number)) {
            return false;
        }
        Number number = (Number) jsonPrimitive.f8511b;
        if ((number instanceof BigInteger) || (number instanceof Long) || (number instanceof Integer) || (number instanceof Short) || (number instanceof Byte)) {
            return true;
        }
        return false;
    }
}
