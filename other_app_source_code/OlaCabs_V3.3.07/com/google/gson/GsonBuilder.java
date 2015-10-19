package com.google.gson;

import com.google.gson.p064b.Excluder;
import com.google.gson.p066c.TypeToken;
import com.newrelic.agent.android.instrumentation.Trace;
import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* renamed from: com.google.gson.g */
public final class GsonBuilder {
    private Excluder f8492a;
    private LongSerializationPolicy f8493b;
    private FieldNamingStrategy f8494c;
    private final Map<Type, InstanceCreator<?>> f8495d;
    private final List<TypeAdapterFactory> f8496e;
    private final List<TypeAdapterFactory> f8497f;
    private boolean f8498g;
    private String f8499h;
    private int f8500i;
    private int f8501j;
    private boolean f8502k;
    private boolean f8503l;
    private boolean f8504m;
    private boolean f8505n;
    private boolean f8506o;

    public GsonBuilder() {
        this.f8492a = Excluder.f8423a;
        this.f8493b = LongSerializationPolicy.DEFAULT;
        this.f8494c = FieldNamingPolicy.IDENTITY;
        this.f8495d = new HashMap();
        this.f8496e = new ArrayList();
        this.f8497f = new ArrayList();
        this.f8500i = 2;
        this.f8501j = 2;
        this.f8504m = true;
    }

    public GsonBuilder m12354a() {
        this.f8502k = true;
        return this;
    }

    public Gson m12355b() {
        List arrayList = new ArrayList();
        arrayList.addAll(this.f8496e);
        Collections.reverse(arrayList);
        arrayList.addAll(this.f8497f);
        m12353a(this.f8499h, this.f8500i, this.f8501j, arrayList);
        return new Gson(this.f8492a, this.f8494c, this.f8495d, this.f8498g, this.f8502k, this.f8506o, this.f8504m, this.f8505n, this.f8503l, this.f8493b, arrayList);
    }

    private void m12353a(String str, int i, int i2, List<TypeAdapterFactory> list) {
        Object defaultDateTypeAdapter;
        if (str != null && !Trace.NULL.equals(str.trim())) {
            defaultDateTypeAdapter = new DefaultDateTypeAdapter(str);
        } else if (i != 2 && i2 != 2) {
            defaultDateTypeAdapter = new DefaultDateTypeAdapter(i, i2);
        } else {
            return;
        }
        list.add(TreeTypeAdapter.m12395a(TypeToken.m12299b(Date.class), defaultDateTypeAdapter));
        list.add(TreeTypeAdapter.m12395a(TypeToken.m12299b(Timestamp.class), defaultDateTypeAdapter));
        list.add(TreeTypeAdapter.m12395a(TypeToken.m12299b(java.sql.Date.class), defaultDateTypeAdapter));
    }
}
