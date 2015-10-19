package com.facebook.share.model;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;

public final class ShareVideo implements ShareModel {
    public static final Creator<ShareVideo> CREATOR;
    private final Uri f1279a;

    /* renamed from: com.facebook.share.model.ShareVideo.1 */
    static class C01951 implements Creator<ShareVideo> {
        C01951() {
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return m1694a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return m1695a(i);
        }

        public ShareVideo m1694a(Parcel parcel) {
            return new ShareVideo(parcel);
        }

        public ShareVideo[] m1695a(int i) {
            return new ShareVideo[i];
        }
    }

    /* renamed from: com.facebook.share.model.ShareVideo.a */
    public static final class C0196a {
        private Uri f1278a;

        public C0196a m1697a(Uri uri) {
            this.f1278a = uri;
            return this;
        }

        public ShareVideo m1700a() {
            return new ShareVideo();
        }

        public C0196a m1699a(ShareVideo shareVideo) {
            return shareVideo == null ? this : m1697a(shareVideo.m1701a());
        }

        public C0196a m1698a(Parcel parcel) {
            return m1699a((ShareVideo) parcel.readParcelable(ShareVideo.class.getClassLoader()));
        }
    }

    private ShareVideo(C0196a c0196a) {
        this.f1279a = c0196a.f1278a;
    }

    ShareVideo(Parcel parcel) {
        this.f1279a = (Uri) parcel.readParcelable(Uri.class.getClassLoader());
    }

    public Uri m1701a() {
        return this.f1279a;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.f1279a, 0);
    }

    static {
        CREATOR = new C01951();
    }
}
