package com.facebook;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.facebook.p022b.Utility.Utility;
import com.facebook.p022b.Validate;
import com.olacabs.customer.utils.Constants;
import org.json.JSONException;
import org.json.JSONObject;

public final class Profile implements Parcelable {
    public static final Creator<Profile> CREATOR;
    private final String f644a;
    private final String f645b;
    private final String f646c;
    private final String f647d;
    private final String f648e;
    private final Uri f649f;

    /* renamed from: com.facebook.Profile.1 */
    static class C01641 implements Utility {
        C01641() {
        }

        public void m790a(JSONObject jSONObject) {
            String optString = jSONObject.optString("id");
            if (optString != null) {
                String optString2 = jSONObject.optString("link");
                Profile.m794a(new Profile(optString, jSONObject.optString("first_name"), jSONObject.optString("middle_name"), jSONObject.optString("last_name"), jSONObject.optString(Constants.BUNDLE_NAME), optString2 != null ? Uri.parse(optString2) : null));
            }
        }

        public void m789a(FacebookException facebookException) {
        }
    }

    /* renamed from: com.facebook.Profile.2 */
    static class C01652 implements Creator {
        C01652() {
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return m791a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return m792a(i);
        }

        public Profile m791a(Parcel parcel) {
            return new Profile(null);
        }

        public Profile[] m792a(int i) {
            return new Profile[i];
        }
    }

    public static Profile m793a() {
        return ProfileManager.m1708a().m1712b();
    }

    public static void m794a(Profile profile) {
        ProfileManager.m1708a().m1711a(profile);
    }

    public static void m795b() {
        AccessToken a = AccessToken.m690a();
        if (a == null) {
            m794a(null);
        } else {
            com.facebook.p022b.Utility.m1118a(a.m698b(), new C01641());
        }
    }

    public Profile(String str, String str2, String str3, String str4, String str5, Uri uri) {
        Validate.m1147a(str, "id");
        this.f644a = str;
        this.f645b = str2;
        this.f646c = str3;
        this.f647d = str4;
        this.f648e = str5;
        this.f649f = uri;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Profile)) {
            return false;
        }
        Profile profile = (Profile) obj;
        if (this.f644a.equals(profile.f644a) && this.f645b == null) {
            if (profile.f645b != null) {
                return false;
            }
            return true;
        } else if (this.f645b.equals(profile.f645b) && this.f646c == null) {
            if (profile.f646c != null) {
                return false;
            }
            return true;
        } else if (this.f646c.equals(profile.f646c) && this.f647d == null) {
            if (profile.f647d != null) {
                return false;
            }
            return true;
        } else if (this.f647d.equals(profile.f647d) && this.f648e == null) {
            if (profile.f648e != null) {
                return false;
            }
            return true;
        } else if (!this.f648e.equals(profile.f648e) || this.f649f != null) {
            return this.f649f.equals(profile.f649f);
        } else {
            if (profile.f649f != null) {
                return false;
            }
            return true;
        }
    }

    public int hashCode() {
        int hashCode = this.f644a.hashCode() + 527;
        if (this.f645b != null) {
            hashCode = (hashCode * 31) + this.f645b.hashCode();
        }
        if (this.f646c != null) {
            hashCode = (hashCode * 31) + this.f646c.hashCode();
        }
        if (this.f647d != null) {
            hashCode = (hashCode * 31) + this.f647d.hashCode();
        }
        if (this.f648e != null) {
            hashCode = (hashCode * 31) + this.f648e.hashCode();
        }
        if (this.f649f != null) {
            return (hashCode * 31) + this.f649f.hashCode();
        }
        return hashCode;
    }

    JSONObject m796c() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("id", this.f644a);
            jSONObject.put("first_name", this.f645b);
            jSONObject.put("middle_name", this.f646c);
            jSONObject.put("last_name", this.f647d);
            jSONObject.put(Constants.BUNDLE_NAME, this.f648e);
            if (this.f649f == null) {
                return jSONObject;
            }
            jSONObject.put("link_uri", this.f649f.toString());
            return jSONObject;
        } catch (JSONException e) {
            return null;
        }
    }

    Profile(JSONObject jSONObject) {
        Uri uri = null;
        this.f644a = jSONObject.optString("id", null);
        this.f645b = jSONObject.optString("first_name", null);
        this.f646c = jSONObject.optString("middle_name", null);
        this.f647d = jSONObject.optString("last_name", null);
        this.f648e = jSONObject.optString(Constants.BUNDLE_NAME, null);
        String optString = jSONObject.optString("link_uri", null);
        if (optString != null) {
            uri = Uri.parse(optString);
        }
        this.f649f = uri;
    }

    private Profile(Parcel parcel) {
        this.f644a = parcel.readString();
        this.f645b = parcel.readString();
        this.f646c = parcel.readString();
        this.f647d = parcel.readString();
        this.f648e = parcel.readString();
        String readString = parcel.readString();
        this.f649f = readString == null ? null : Uri.parse(readString);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f644a);
        parcel.writeString(this.f645b);
        parcel.writeString(this.f646c);
        parcel.writeString(this.f647d);
        parcel.writeString(this.f648e);
        parcel.writeString(this.f649f == null ? null : this.f649f.toString());
    }

    static {
        CREATOR = new C01652();
    }
}
