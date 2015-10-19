package com.facebook.share.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.facebook.share.model.ShareOpenGraphValueContainer.C0188a;

public final class ShareOpenGraphAction extends ShareOpenGraphValueContainer<ShareOpenGraphAction, C0189a> {
    public static final Creator<ShareOpenGraphAction> CREATOR;

    /* renamed from: com.facebook.share.model.ShareOpenGraphAction.1 */
    static class C01871 implements Creator<ShareOpenGraphAction> {
        C01871() {
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return m1652a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return m1653a(i);
        }

        public ShareOpenGraphAction m1652a(Parcel parcel) {
            return new ShareOpenGraphAction(parcel);
        }

        public ShareOpenGraphAction[] m1653a(int i) {
            return new ShareOpenGraphAction[i];
        }
    }

    /* renamed from: com.facebook.share.model.ShareOpenGraphAction.a */
    public static final class C0189a extends C0188a<ShareOpenGraphAction, C0189a> {
        public C0189a m1659a(String str) {
            m1656a("og:type", str);
            return this;
        }

        public ShareOpenGraphAction m1660a() {
            return new ShareOpenGraphAction();
        }

        public C0189a m1658a(ShareOpenGraphAction shareOpenGraphAction) {
            return shareOpenGraphAction == null ? this : ((C0189a) super.m1655a((ShareOpenGraphValueContainer) shareOpenGraphAction)).m1659a(shareOpenGraphAction.m1666a());
        }

        public C0189a m1657a(Parcel parcel) {
            return m1658a((ShareOpenGraphAction) parcel.readParcelable(ShareOpenGraphAction.class.getClassLoader()));
        }
    }

    private ShareOpenGraphAction(C0189a c0189a) {
        super((C0188a) c0189a);
    }

    ShareOpenGraphAction(Parcel parcel) {
        super(parcel);
    }

    public String m1666a() {
        return m1664b("og:type");
    }

    static {
        CREATOR = new C01871();
    }
}
