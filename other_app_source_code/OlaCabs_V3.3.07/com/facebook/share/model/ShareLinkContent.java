package com.facebook.share.model;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.facebook.share.model.ShareContent.C0184a;

public final class ShareLinkContent extends ShareContent<ShareLinkContent, C0186a> {
    public static final Creator<ShareLinkContent> CREATOR;
    private final String f1264a;
    private final String f1265b;
    private final Uri f1266c;

    /* renamed from: com.facebook.share.model.ShareLinkContent.1 */
    static class C01851 implements Creator<ShareLinkContent> {
        C01851() {
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return m1640a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return m1641a(i);
        }

        public ShareLinkContent m1640a(Parcel parcel) {
            return new ShareLinkContent(parcel);
        }

        public ShareLinkContent[] m1641a(int i) {
            return new ShareLinkContent[i];
        }
    }

    /* renamed from: com.facebook.share.model.ShareLinkContent.a */
    public static final class C0186a extends C0184a<ShareLinkContent, C0186a> {
        private String f1261a;
        private String f1262b;
        private Uri f1263c;

        public C0186a m1645a(String str) {
            this.f1261a = str;
            return this;
        }

        public C0186a m1648b(String str) {
            this.f1262b = str;
            return this;
        }

        public C0186a m1647b(Uri uri) {
            this.f1263c = uri;
            return this;
        }

        public ShareLinkContent m1646a() {
            return new ShareLinkContent();
        }
    }

    private ShareLinkContent(C0186a c0186a) {
        super((C0184a) c0186a);
        this.f1264a = c0186a.f1261a;
        this.f1265b = c0186a.f1262b;
        this.f1266c = c0186a.f1263c;
    }

    ShareLinkContent(Parcel parcel) {
        super(parcel);
        this.f1264a = parcel.readString();
        this.f1265b = parcel.readString();
        this.f1266c = (Uri) parcel.readParcelable(Uri.class.getClassLoader());
    }

    public String m1649e() {
        return this.f1264a;
    }

    public String m1650f() {
        return this.f1265b;
    }

    public Uri m1651g() {
        return this.f1266c;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.f1264a);
        parcel.writeString(this.f1265b);
        parcel.writeParcelable(this.f1266c, 0);
    }

    static {
        CREATOR = new C01851();
    }
}
