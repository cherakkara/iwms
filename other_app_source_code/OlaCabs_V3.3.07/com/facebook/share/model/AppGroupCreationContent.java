package com.facebook.share.model;

import android.os.Parcel;

public final class AppGroupCreationContent implements ShareModel {
    private final String f1233a;
    private final String f1234b;
    private C0181a f1235c;

    /* renamed from: com.facebook.share.model.AppGroupCreationContent.a */
    public enum C0181a {
        Open,
        Closed
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f1233a);
        parcel.writeString(this.f1234b);
        parcel.writeString(this.f1235c.toString());
    }
}
