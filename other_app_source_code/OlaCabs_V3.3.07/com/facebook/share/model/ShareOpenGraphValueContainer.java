package com.facebook.share.model;

import android.os.Bundle;
import android.os.Parcel;
import java.util.Set;

public abstract class ShareOpenGraphValueContainer<P extends ShareOpenGraphValueContainer, E extends C0188a> implements ShareModel {
    private final Bundle f1268a;

    /* renamed from: com.facebook.share.model.ShareOpenGraphValueContainer.a */
    public static abstract class C0188a<P extends ShareOpenGraphValueContainer, E extends C0188a> {
        private Bundle f1267a;

        public C0188a() {
            this.f1267a = new Bundle();
        }

        public E m1656a(String str, String str2) {
            this.f1267a.putString(str, str2);
            return this;
        }

        public E m1655a(P p) {
            if (p != null) {
                this.f1267a.putAll(p.m1663b());
            }
            return this;
        }
    }

    protected ShareOpenGraphValueContainer(C0188a<P, E> c0188a) {
        this.f1268a = (Bundle) c0188a.f1267a.clone();
    }

    ShareOpenGraphValueContainer(Parcel parcel) {
        this.f1268a = parcel.readBundle(C0188a.class.getClassLoader());
    }

    public Object m1662a(String str) {
        return this.f1268a.get(str);
    }

    public String m1664b(String str) {
        return this.f1268a.getString(str);
    }

    public Bundle m1663b() {
        return (Bundle) this.f1268a.clone();
    }

    public Set<String> m1665c() {
        return this.f1268a.keySet();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeBundle(this.f1268a);
    }
}
