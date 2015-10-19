package com.facebook.share.internal;

import android.os.Parcel;
import com.facebook.share.model.ShareModel;
import com.facebook.share.p024a.LikeView.LikeView;

public class LikeContent implements ShareModel {
    private final String f1115a;
    private final LikeView f1116b;

    /* renamed from: com.facebook.share.internal.LikeContent.a */
    public static class C0180a {
        private String f1113a;
        private LikeView f1114b;

        public C0180a() {
            this.f1114b = LikeView.UNKNOWN;
        }

        public C0180a m1423a(String str) {
            this.f1113a = str;
            return this;
        }

        public C0180a m1422a(LikeView likeView) {
            if (likeView == null) {
                likeView = LikeView.UNKNOWN;
            }
            this.f1114b = likeView;
            return this;
        }

        public LikeContent m1424a() {
            return new LikeContent();
        }
    }

    private LikeContent(C0180a c0180a) {
        this.f1115a = c0180a.f1113a;
        this.f1116b = c0180a.f1114b;
    }

    public String m1425a() {
        return this.f1115a;
    }

    public LikeView m1426b() {
        return this.f1116b;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f1115a);
        parcel.writeInt(this.f1116b.m1369a());
    }
}
