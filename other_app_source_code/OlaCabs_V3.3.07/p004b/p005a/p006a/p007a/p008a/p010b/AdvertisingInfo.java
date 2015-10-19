package p004b.p005a.p006a.p007a.p008a.p010b;

/* renamed from: b.a.a.a.a.b.b */
class AdvertisingInfo {
    public final String f115a;
    public final boolean f116b;

    AdvertisingInfo(String str, boolean z) {
        this.f115a = str;
        this.f116b = z;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        AdvertisingInfo advertisingInfo = (AdvertisingInfo) obj;
        if (this.f116b != advertisingInfo.f116b) {
            return false;
        }
        if (this.f115a != null) {
            if (this.f115a.equals(advertisingInfo.f115a)) {
                return true;
            }
        } else if (advertisingInfo.f115a == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int hashCode;
        int i = 0;
        if (this.f115a != null) {
            hashCode = this.f115a.hashCode();
        } else {
            hashCode = 0;
        }
        hashCode *= 31;
        if (this.f116b) {
            i = 1;
        }
        return hashCode + i;
    }
}
