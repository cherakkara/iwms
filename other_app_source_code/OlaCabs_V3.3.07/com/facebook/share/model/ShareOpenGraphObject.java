package com.facebook.share.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public final class ShareOpenGraphObject extends ShareOpenGraphValueContainer<ShareOpenGraphObject, Object> {
    public static final Creator<ShareOpenGraphObject> CREATOR;

    /* renamed from: com.facebook.share.model.ShareOpenGraphObject.1 */
    static class C01911 implements Creator<ShareOpenGraphObject> {
        C01911() {
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return m1671a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return m1672a(i);
        }

        public ShareOpenGraphObject m1671a(Parcel parcel) {
            return new ShareOpenGraphObject(parcel);
        }

        public ShareOpenGraphObject[] m1672a(int i) {
            return new ShareOpenGraphObject[i];
        }
    }

    ShareOpenGraphObject(Parcel parcel) {
        super(parcel);
    }

    static {
        CREATOR = new C01911();
    }
}
