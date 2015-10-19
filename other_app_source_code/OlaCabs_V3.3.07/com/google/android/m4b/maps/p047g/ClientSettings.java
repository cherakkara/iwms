package com.google.android.m4b.maps.p047g;

import android.os.Parcel;
import android.view.View;
import com.google.android.m4b.maps.p037h.C0591c;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* renamed from: com.google.android.m4b.maps.g.b */
public final class ClientSettings {
    private final ClientSettings f7382a;

    /* renamed from: com.google.android.m4b.maps.g.b.a */
    public static final class ClientSettings implements C0591c {
        public static final ParcelableClientSettingsCreator CREATOR;
        private final int f7377a;
        private final String f7378b;
        private final List<String> f7379c;
        private final int f7380d;
        private final String f7381e;

        static {
            CREATOR = new ParcelableClientSettingsCreator();
        }

        ClientSettings(int i, String str, List<String> list, int i2, String str2) {
            this.f7379c = new ArrayList();
            this.f7377a = i;
            this.f7378b = str;
            this.f7379c.addAll(list);
            this.f7380d = i2;
            this.f7381e = str2;
        }

        public ClientSettings(String str, Collection<String> collection, int i, String str2) {
            this(3, str, new ArrayList(collection), i, str2);
        }

        public final String m10317a() {
            return this.f7378b;
        }

        public final int m10318b() {
            return this.f7380d;
        }

        public final String m10319c() {
            return this.f7381e;
        }

        public final List<String> m10320d() {
            return new ArrayList(this.f7379c);
        }

        public final int describeContents() {
            return 0;
        }

        public final void writeToParcel(Parcel parcel, int i) {
            ParcelableClientSettingsCreator.m10458a(this, parcel);
        }

        public final int m10321e() {
            return this.f7377a;
        }
    }

    public ClientSettings(String str, Collection<String> collection, int i, View view, String str2) {
        this.f7382a = new ClientSettings(str, collection, i, str2);
    }

    public final List<String> m10322a() {
        return this.f7382a.m10320d();
    }
}
