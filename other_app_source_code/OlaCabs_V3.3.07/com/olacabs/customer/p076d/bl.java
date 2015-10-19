package com.olacabs.customer.p076d;

import com.google.gson.p063a.SerializedName;

/* compiled from: Localities */
/* renamed from: com.olacabs.customer.d.bl */
public class bl {
    @SerializedName(a = "id")
    private int id;
    @SerializedName(a = "name")
    private String name;

    public bl(int i, String str) {
        this.id = i;
        this.name = str;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getJsonObjectTag() {
        return "localities";
    }

    public String getJsonArrayTag() {
        return "localities";
    }
}
