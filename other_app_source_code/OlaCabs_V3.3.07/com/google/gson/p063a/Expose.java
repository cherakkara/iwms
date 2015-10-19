package com.google.gson.p063a;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
/* renamed from: com.google.gson.a.a */
public @interface Expose {
    boolean m12018a() default true;

    boolean m12019b() default true;
}
