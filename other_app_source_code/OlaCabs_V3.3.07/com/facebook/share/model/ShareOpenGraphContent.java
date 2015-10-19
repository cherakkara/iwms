package com.facebook.share.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.facebook.share.model.ShareOpenGraphAction.C0189a;

public final class ShareOpenGraphContent extends ShareContent<ShareOpenGraphContent, Object> {
    public static final Creator<ShareOpenGraphContent> CREATOR;
    private final ShareOpenGraphAction f1269a;
    private final String f1270b;

    /* renamed from: com.facebook.share.model.ShareOpenGraphContent.1 */
    static class C01901 implements Creator<ShareOpenGraphContent> {
        C01901() {
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return m1667a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return m1668a(i);
        }

        public ShareOpenGraphContent m1667a(Parcel parcel) {
            return new ShareOpenGraphContent(parcel);
        }

        public ShareOpenGraphContent[] m1668a(int i) {
            return new ShareOpenGraphContent[i];
        }
    }

    ShareOpenGraphContent(Parcel parcel) {
        super(parcel);
        this.f1269a = new C0189a().m1657a(parcel).m1660a();
        this.f1270b = parcel.readString();
    }

    public ShareOpenGraphAction m1669e() {
        return this.f1269a;
    }

    public String m1670f() {
        return this.f1270b;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeParcelable(this.f1269a, 0);
        parcel.writeString(this.f1270b);
    }

    static {
        CREATOR = new C01901();
    }
}
