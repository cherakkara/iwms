package com.facebook.share.model;

import android.net.Uri;
import android.os.Parcel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class ShareContent<P extends ShareContent, E extends C0184a> implements ShareModel {
    private final Uri f1257a;
    private final List<String> f1258b;
    private final String f1259c;
    private final String f1260d;

    /* renamed from: com.facebook.share.model.ShareContent.a */
    public static abstract class C0184a<P extends ShareContent, E extends C0184a> {
        private Uri f1253a;
        private List<String> f1254b;
        private String f1255c;
        private String f1256d;

        public E m1634a(Uri uri) {
            this.f1253a = uri;
            return this;
        }
    }

    protected ShareContent(C0184a c0184a) {
        this.f1257a = c0184a.f1253a;
        this.f1258b = c0184a.f1254b;
        this.f1259c = c0184a.f1255c;
        this.f1260d = c0184a.f1256d;
    }

    ShareContent(Parcel parcel) {
        this.f1257a = (Uri) parcel.readParcelable(Uri.class.getClassLoader());
        this.f1258b = m1635a(parcel);
        this.f1259c = parcel.readString();
        this.f1260d = parcel.readString();
    }

    public Uri m1636a() {
        return this.f1257a;
    }

    public List<String> m1637b() {
        return this.f1258b;
    }

    public String m1638c() {
        return this.f1259c;
    }

    public String m1639d() {
        return this.f1260d;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.f1257a, 0);
        parcel.writeStringList(this.f1258b);
        parcel.writeString(this.f1259c);
        parcel.writeString(this.f1260d);
    }

    private List<String> m1635a(Parcel parcel) {
        List arrayList = new ArrayList();
        parcel.readStringList(arrayList);
        return arrayList.size() == 0 ? null : Collections.unmodifiableList(arrayList);
    }
}
