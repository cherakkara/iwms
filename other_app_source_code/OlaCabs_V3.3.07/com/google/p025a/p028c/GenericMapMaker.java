package com.google.p025a.p028c;

import com.google.p025a.p026a.Objects;
import com.google.p025a.p028c.as.MapMaker;

/* renamed from: com.google.a.c.u */
public abstract class GenericMapMaker<K0, V0> {
    MapMaker<K0, V0> f1667a;

    /* renamed from: com.google.a.c.u.a */
    enum GenericMapMaker implements MapMaker<Object, Object> {
        INSTANCE;

        public void m2970a(MapMaker<Object, Object> mapMaker) {
        }
    }

    GenericMapMaker() {
    }

    <K extends K0, V extends V0> MapMaker<K, V> m2530a() {
        return (MapMaker) Objects.m1812b(this.f1667a, GenericMapMaker.INSTANCE);
    }
}
