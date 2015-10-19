package com.facebook.share.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.facebook.share.model.SharePhoto.C0193a;
import com.facebook.share.model.ShareVideo.C0196a;

public final class ShareVideoContent extends ShareContent<ShareVideoContent, Object> implements ShareModel {
    public static final Creator<ShareVideoContent> CREATOR;
    private final String f1280a;
    private final String f1281b;
    private final SharePhoto f1282c;
    private final ShareVideo f1283d;

    /* renamed from: com.facebook.share.model.ShareVideoContent.1 */
    static class C01971 implements Creator<ShareVideoContent> {
        C01971() {
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return m1702a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return m1703a(i);
        }

        public ShareVideoContent m1702a(Parcel parcel) {
            return new ShareVideoContent(parcel);
        }

        public ShareVideoContent[] m1703a(int i) {
            return new ShareVideoContent[i];
        }
    }

    ShareVideoContent(Parcel parcel) {
        super(parcel);
        this.f1280a = parcel.readString();
        this.f1281b = parcel.readString();
        C0193a a = new C0193a().m1683a(parcel);
        if (a.m1680a() == null && a.m1686b() == null) {
            this.f1282c = null;
        } else {
            this.f1282c = a.m1687c();
        }
        this.f1283d = new C0196a().m1698a(parcel).m1700a();
    }

    public String m1704e() {
        return this.f1280a;
    }

    public String m1705f() {
        return this.f1281b;
    }

    public SharePhoto m1706g() {
        return this.f1282c;
    }

    public ShareVideo m1707h() {
        return this.f1283d;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.f1280a);
        parcel.writeString(this.f1281b);
        parcel.writeParcelable(this.f1282c, 0);
        parcel.writeParcelable(this.f1283d, 0);
    }

    static {
        CREATOR = new C01971();
    }
}
