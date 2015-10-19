package com.google.gson.p064b;

import java.math.BigDecimal;

/* renamed from: com.google.gson.b.f */
public final class LazilyParsedNumber extends Number {
    private final String f8430a;

    public LazilyParsedNumber(String str) {
        this.f8430a = str;
    }

    public int intValue() {
        try {
            return Integer.parseInt(this.f8430a);
        } catch (NumberFormatException e) {
            try {
                return (int) Long.parseLong(this.f8430a);
            } catch (NumberFormatException e2) {
                return new BigDecimal(this.f8430a).intValue();
            }
        }
    }

    public long longValue() {
        try {
            return Long.parseLong(this.f8430a);
        } catch (NumberFormatException e) {
            return new BigDecimal(this.f8430a).longValue();
        }
    }

    public float floatValue() {
        return Float.parseFloat(this.f8430a);
    }

    public double doubleValue() {
        return Double.parseDouble(this.f8430a);
    }

    public String toString() {
        return this.f8430a;
    }
}
