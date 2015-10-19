package com.facebook;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.facebook.p022b.Utility;
import com.facebook.p022b.Validate;
import com.olacabs.customer.p076d.AppInfo;
import com.olacabs.customer.utils.Constants;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class AccessToken implements Parcelable {
    public static final Creator<AccessToken> CREATOR;
    private static final Date f596a;
    private static final Date f597b;
    private static final Date f598c;
    private static final AccessTokenSource f599d;
    private final Date f600e;
    private final Set<String> f601f;
    private final Set<String> f602g;
    private final String f603h;
    private final AccessTokenSource f604i;
    private final Date f605j;
    private final String f606k;
    private final String f607l;

    /* renamed from: com.facebook.AccessToken.1 */
    static class C01521 implements Creator {
        C01521() {
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return m688a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return m689a(i);
        }

        public AccessToken m688a(Parcel parcel) {
            return new AccessToken(parcel);
        }

        public AccessToken[] m689a(int i) {
            return new AccessToken[i];
        }
    }

    static {
        f596a = new Date(Long.MAX_VALUE);
        f597b = f596a;
        f598c = new Date();
        f599d = AccessTokenSource.FACEBOOK_APPLICATION_WEB;
        CREATOR = new C01521();
    }

    public AccessToken(String str, String str2, String str3, Collection<String> collection, Collection<String> collection2, AccessTokenSource accessTokenSource, Date date, Date date2) {
        Validate.m1147a(str, "accessToken");
        Validate.m1147a(str2, "applicationId");
        Validate.m1147a(str3, Constants.PREF_USER_ID);
        if (date == null) {
            date = f597b;
        }
        this.f600e = date;
        this.f601f = Collections.unmodifiableSet(collection != null ? new HashSet(collection) : new HashSet());
        this.f602g = Collections.unmodifiableSet(collection2 != null ? new HashSet(collection2) : new HashSet());
        this.f603h = str;
        if (accessTokenSource == null) {
            accessTokenSource = f599d;
        }
        this.f604i = accessTokenSource;
        if (date2 == null) {
            date2 = f598c;
        }
        this.f605j = date2;
        this.f606k = str2;
        this.f607l = str3;
    }

    public static AccessToken m690a() {
        return AccessTokenManager.m1171a().m1177b();
    }

    public static void m695a(AccessToken accessToken) {
        AccessTokenManager.m1171a().m1176a(accessToken);
    }

    public String m698b() {
        return this.f603h;
    }

    public Set<String> m699c() {
        return this.f601f;
    }

    public Set<String> m700d() {
        return this.f602g;
    }

    public AccessTokenSource m701e() {
        return this.f604i;
    }

    public Date m702f() {
        return this.f605j;
    }

    public String m703g() {
        return this.f606k;
    }

    public String m704h() {
        return this.f607l;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{AccessToken");
        stringBuilder.append(" token:").append(m697j());
        m696a(stringBuilder);
        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(java.lang.Object r5) {
        /*
        r4 = this;
        r0 = 1;
        r1 = 0;
        if (r4 != r5) goto L_0x0005;
    L_0x0004:
        return r0;
    L_0x0005:
        r2 = r5 instanceof com.facebook.AccessToken;
        if (r2 != 0) goto L_0x000b;
    L_0x0009:
        r0 = r1;
        goto L_0x0004;
    L_0x000b:
        r5 = (com.facebook.AccessToken) r5;
        r2 = r4.f600e;
        r3 = r5.f600e;
        r2 = r2.equals(r3);
        if (r2 == 0) goto L_0x0057;
    L_0x0017:
        r2 = r4.f601f;
        r3 = r5.f601f;
        r2 = r2.equals(r3);
        if (r2 == 0) goto L_0x0057;
    L_0x0021:
        r2 = r4.f602g;
        r3 = r5.f602g;
        r2 = r2.equals(r3);
        if (r2 == 0) goto L_0x0057;
    L_0x002b:
        r2 = r4.f603h;
        r3 = r5.f603h;
        r2 = r2.equals(r3);
        if (r2 == 0) goto L_0x0057;
    L_0x0035:
        r2 = r4.f604i;
        r3 = r5.f604i;
        if (r2 != r3) goto L_0x0057;
    L_0x003b:
        r2 = r4.f605j;
        r3 = r5.f605j;
        r2 = r2.equals(r3);
        if (r2 == 0) goto L_0x0057;
    L_0x0045:
        r2 = r4.f606k;
        if (r2 != 0) goto L_0x0059;
    L_0x0049:
        r2 = r5.f606k;
        if (r2 != 0) goto L_0x0057;
    L_0x004d:
        r2 = r4.f607l;
        r3 = r5.f607l;
        r2 = r2.equals(r3);
        if (r2 != 0) goto L_0x0004;
    L_0x0057:
        r0 = r1;
        goto L_0x0004;
    L_0x0059:
        r2 = r4.f606k;
        r3 = r5.f606k;
        r2 = r2.equals(r3);
        if (r2 == 0) goto L_0x0057;
    L_0x0063:
        goto L_0x004d;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.AccessToken.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        return (((this.f606k == null ? 0 : this.f606k.hashCode()) + ((((((((((((this.f600e.hashCode() + 527) * 31) + this.f601f.hashCode()) * 31) + this.f602g.hashCode()) * 31) + this.f603h.hashCode()) * 31) + this.f604i.hashCode()) * 31) + this.f605j.hashCode()) * 31)) * 31) + this.f607l.hashCode();
    }

    @SuppressLint({"FieldGetter"})
    static AccessToken m692a(AccessToken accessToken, Bundle bundle) {
        if (accessToken.f604i == AccessTokenSource.FACEBOOK_APPLICATION_WEB || accessToken.f604i == AccessTokenSource.FACEBOOK_APPLICATION_NATIVE || accessToken.f604i == AccessTokenSource.FACEBOOK_APPLICATION_SERVICE) {
            Date a = Utility.m1107a(bundle, "expires_in", new Date(0));
            String string = bundle.getString("access_token");
            if (Utility.m1126a(string)) {
                return null;
            }
            return new AccessToken(string, accessToken.f606k, accessToken.m704h(), accessToken.m699c(), accessToken.m700d(), accessToken.f604i, a, new Date());
        }
        throw new FacebookException("Invalid token source: " + accessToken.f604i);
    }

    static AccessToken m691a(Bundle bundle) {
        Collection a = m694a(bundle, "com.facebook.TokenCachingStrategy.Permissions");
        Collection a2 = m694a(bundle, "com.facebook.TokenCachingStrategy.DeclinedPermissions");
        String d = LegacyTokenHelper.m1359d(bundle);
        if (Utility.m1126a(d)) {
            d = FacebookSdk.m1210h();
        }
        String b = LegacyTokenHelper.m1357b(bundle);
        try {
            return new AccessToken(b, d, Utility.m1141e(b).getString("id"), a, a2, LegacyTokenHelper.m1358c(bundle), LegacyTokenHelper.m1354a(bundle, "com.facebook.TokenCachingStrategy.ExpirationDate"), LegacyTokenHelper.m1354a(bundle, "com.facebook.TokenCachingStrategy.LastRefreshDate"));
        } catch (JSONException e) {
            return null;
        }
    }

    static List<String> m694a(Bundle bundle, String str) {
        Collection stringArrayList = bundle.getStringArrayList(str);
        if (stringArrayList == null) {
            return Collections.emptyList();
        }
        return Collections.unmodifiableList(new ArrayList(stringArrayList));
    }

    JSONObject m705i() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(AppInfo.APP_VERSION_KEY, 1);
        jSONObject.put("token", this.f603h);
        jSONObject.put("expires_at", this.f600e.getTime());
        jSONObject.put("permissions", new JSONArray(this.f601f));
        jSONObject.put("declined_permissions", new JSONArray(this.f602g));
        jSONObject.put("last_refresh", this.f605j.getTime());
        jSONObject.put("source", this.f604i.name());
        jSONObject.put("application_id", this.f606k);
        jSONObject.put(Constants.USER_ID, this.f607l);
        return jSONObject;
    }

    static AccessToken m693a(JSONObject jSONObject) throws JSONException {
        if (jSONObject.getInt(AppInfo.APP_VERSION_KEY) > 1) {
            throw new FacebookException("Unknown AccessToken serialization format.");
        }
        String string = jSONObject.getString("token");
        Date date = new Date(jSONObject.getLong("expires_at"));
        JSONArray jSONArray = jSONObject.getJSONArray("permissions");
        JSONArray jSONArray2 = jSONObject.getJSONArray("declined_permissions");
        Date date2 = new Date(jSONObject.getLong("last_refresh"));
        return new AccessToken(string, jSONObject.getString("application_id"), jSONObject.getString(Constants.USER_ID), Utility.m1109a(jSONArray), Utility.m1109a(jSONArray2), AccessTokenSource.valueOf(jSONObject.getString("source")), date, date2);
    }

    private String m697j() {
        if (this.f603h == null) {
            return "null";
        }
        if (FacebookSdk.m1200a(LoggingBehavior.INCLUDE_ACCESS_TOKENS)) {
            return this.f603h;
        }
        return "ACCESS_TOKEN_REMOVED";
    }

    private void m696a(StringBuilder stringBuilder) {
        stringBuilder.append(" permissions:");
        if (this.f601f == null) {
            stringBuilder.append("null");
            return;
        }
        stringBuilder.append("[");
        stringBuilder.append(TextUtils.join(", ", this.f601f));
        stringBuilder.append("]");
    }

    AccessToken(Parcel parcel) {
        this.f600e = new Date(parcel.readLong());
        Collection arrayList = new ArrayList();
        parcel.readStringList(arrayList);
        this.f601f = Collections.unmodifiableSet(new HashSet(arrayList));
        arrayList.clear();
        parcel.readStringList(arrayList);
        this.f602g = Collections.unmodifiableSet(new HashSet(arrayList));
        this.f603h = parcel.readString();
        this.f604i = AccessTokenSource.valueOf(parcel.readString());
        this.f605j = new Date(parcel.readLong());
        this.f606k = parcel.readString();
        this.f607l = parcel.readString();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.f600e.getTime());
        parcel.writeStringList(new ArrayList(this.f601f));
        parcel.writeStringList(new ArrayList(this.f602g));
        parcel.writeString(this.f603h);
        parcel.writeString(this.f604i.name());
        parcel.writeLong(this.f605j.getTime());
        parcel.writeString(this.f606k);
        parcel.writeString(this.f607l);
    }
}
