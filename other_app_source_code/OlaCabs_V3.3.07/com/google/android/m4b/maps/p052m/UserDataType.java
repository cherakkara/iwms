package com.google.android.m4b.maps.p052m;

import android.os.Parcel;
import com.google.android.m4b.maps.p037h.C0591c;
import com.google.android.m4b.maps.p047g.Preconditions;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

/* renamed from: com.google.android.m4b.maps.m.m */
public final class UserDataType implements C0591c {
    public static final UserDataTypeCreator CREATOR;
    private static UserDataType f7520d;
    private static UserDataType f7521e;
    final int f7522a;
    final String f7523b;
    final int f7524c;

    static {
        f7520d = UserDataType.m10701a("test_type", 1);
        f7521e = UserDataType.m10701a("saved_offers", 4);
        Collections.unmodifiableSet(new HashSet(Arrays.asList(new UserDataType[]{f7520d, f7521e})));
        CREATOR = new UserDataTypeCreator();
    }

    UserDataType(int i, String str, int i2) {
        Preconditions.m10461a(str);
        this.f7522a = i;
        this.f7523b = str;
        this.f7524c = i2;
    }

    private static UserDataType m10701a(String str, int i) {
        return new UserDataType(0, str, i);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof UserDataType)) {
            return false;
        }
        UserDataType userDataType = (UserDataType) obj;
        if (this.f7523b.equals(userDataType.f7523b) && this.f7524c == userDataType.f7524c) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return this.f7523b.hashCode();
    }

    public final String toString() {
        return this.f7523b;
    }

    public final int describeContents() {
        UserDataTypeCreator userDataTypeCreator = CREATOR;
        return 0;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        UserDataTypeCreator userDataTypeCreator = CREATOR;
        UserDataTypeCreator.m10702a(this, parcel);
    }
}
