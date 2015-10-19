package com.facebook.share.model;

import android.os.Parcel;
import java.util.ArrayList;

public final class GameRequestContent implements ShareModel {
    private final String f1245a;
    private final String f1246b;
    private final String f1247c;
    private final String f1248d;
    private final C0182a f1249e;
    private final String f1250f;
    private final C0183b f1251g;
    private final ArrayList<String> f1252h;

    /* renamed from: com.facebook.share.model.GameRequestContent.a */
    public enum C0182a {
        SEND,
        ASKFOR,
        TURN
    }

    /* renamed from: com.facebook.share.model.GameRequestContent.b */
    public enum C0183b {
        APP_USERS,
        APP_NON_USERS
    }

    public C0182a m1626a() {
        return this.f1249e;
    }

    public String m1627b() {
        return this.f1250f;
    }

    public C0183b m1628c() {
        return this.f1251g;
    }

    public ArrayList<String> m1629d() {
        return this.f1252h;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f1245a);
        parcel.writeString(this.f1246b);
        parcel.writeString(this.f1247c);
        parcel.writeString(this.f1248d);
        parcel.writeString(m1626a().toString());
        parcel.writeString(m1627b());
        parcel.writeString(m1628c().toString());
        parcel.writeStringList(m1629d());
    }
}
