package com.facebook.share.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.facebook.share.model.SharePhoto.C0193a;
import java.util.Collections;
import java.util.List;

public final class SharePhotoContent extends ShareContent<SharePhotoContent, Object> {
    public static final Creator<SharePhotoContent> CREATOR;
    private final List<SharePhoto> f1277a;

    /* renamed from: com.facebook.share.model.SharePhotoContent.1 */
    static class C01941 implements Creator<SharePhotoContent> {
        C01941() {
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return m1691a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return m1692a(i);
        }

        public SharePhotoContent m1691a(Parcel parcel) {
            return new SharePhotoContent(parcel);
        }

        public SharePhotoContent[] m1692a(int i) {
            return new SharePhotoContent[i];
        }
    }

    SharePhotoContent(Parcel parcel) {
        super(parcel);
        this.f1277a = Collections.unmodifiableList(C0193a.m1678b(parcel));
    }

    public List<SharePhoto> m1693e() {
        return this.f1277a;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        C0193a.m1676a(parcel, this.f1277a);
    }

    static {
        CREATOR = new C01941();
    }
}
