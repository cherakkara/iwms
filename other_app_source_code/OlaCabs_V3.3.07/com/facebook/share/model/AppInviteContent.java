package com.facebook.share.model;

import android.os.Parcel;

public final class AppInviteContent implements ShareModel {
    private final String f1236a;
    private final String f1237b;

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f1236a);
        parcel.writeString(this.f1237b);
    }
}
