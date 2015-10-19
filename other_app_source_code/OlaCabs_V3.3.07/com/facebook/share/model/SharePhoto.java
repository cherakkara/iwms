package com.facebook.share.model;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import java.util.ArrayList;
import java.util.List;

public final class SharePhoto implements ShareModel {
    public static final Creator<SharePhoto> CREATOR;
    private final Bitmap f1274a;
    private final Uri f1275b;
    private final boolean f1276c;

    /* renamed from: com.facebook.share.model.SharePhoto.1 */
    static class C01921 implements Creator<SharePhoto> {
        C01921() {
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return m1673a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return m1674a(i);
        }

        public SharePhoto m1673a(Parcel parcel) {
            return new SharePhoto(parcel);
        }

        public SharePhoto[] m1674a(int i) {
            return new SharePhoto[i];
        }
    }

    /* renamed from: com.facebook.share.model.SharePhoto.a */
    public static final class C0193a {
        private Bitmap f1271a;
        private Uri f1272b;
        private boolean f1273c;

        public C0193a m1681a(Bitmap bitmap) {
            this.f1271a = bitmap;
            return this;
        }

        public C0193a m1682a(Uri uri) {
            this.f1272b = uri;
            return this;
        }

        public C0193a m1685a(boolean z) {
            this.f1273c = z;
            return this;
        }

        Uri m1680a() {
            return this.f1272b;
        }

        Bitmap m1686b() {
            return this.f1271a;
        }

        public SharePhoto m1687c() {
            return new SharePhoto();
        }

        public C0193a m1684a(SharePhoto sharePhoto) {
            return sharePhoto == null ? this : m1681a(sharePhoto.m1688a()).m1682a(sharePhoto.m1689b()).m1685a(sharePhoto.m1690c());
        }

        public C0193a m1683a(Parcel parcel) {
            return m1684a((SharePhoto) parcel.readParcelable(SharePhoto.class.getClassLoader()));
        }

        public static void m1676a(Parcel parcel, List<SharePhoto> list) {
            List arrayList = new ArrayList();
            for (SharePhoto add : list) {
                arrayList.add(add);
            }
            parcel.writeTypedList(arrayList);
        }

        public static List<SharePhoto> m1678b(Parcel parcel) {
            List<SharePhoto> arrayList = new ArrayList();
            parcel.readTypedList(arrayList, SharePhoto.CREATOR);
            return arrayList;
        }
    }

    private SharePhoto(C0193a c0193a) {
        this.f1274a = c0193a.f1271a;
        this.f1275b = c0193a.f1272b;
        this.f1276c = c0193a.f1273c;
    }

    SharePhoto(Parcel parcel) {
        this.f1274a = (Bitmap) parcel.readParcelable(Bitmap.class.getClassLoader());
        this.f1275b = (Uri) parcel.readParcelable(Uri.class.getClassLoader());
        this.f1276c = parcel.readByte() != null;
    }

    public Bitmap m1688a() {
        return this.f1274a;
    }

    public Uri m1689b() {
        return this.f1275b;
    }

    public boolean m1690c() {
        return this.f1276c;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int i2 = 0;
        parcel.writeParcelable(this.f1274a, 0);
        parcel.writeParcelable(this.f1275b, 0);
        if (this.f1276c) {
            i2 = 1;
        }
        parcel.writeByte((byte) i2);
    }

    static {
        CREATOR = new C01921();
    }
}
