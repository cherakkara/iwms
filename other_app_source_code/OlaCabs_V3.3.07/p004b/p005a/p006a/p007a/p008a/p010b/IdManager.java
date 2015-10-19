package p004b.p005a.p006a.p007a.p008a.p010b;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import com.crashlytics.android.core.CrashlyticsCore;
import com.newrelic.agent.android.api.common.WanType;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import com.newrelic.agent.android.instrumentation.Trace;
import com.olacabs.customer.p076d.ao;
import com.olacabs.customer.utils.Constants;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;
import java.util.concurrent.locks.ReentrantLock;
import java.util.regex.Pattern;
import javax.crypto.Cipher;
import org.apache.http.HttpStatus;
import org.json.JSONObject;
import p004b.p005a.p006a.p007a.Fabric;
import p004b.p005a.p006a.p007a.Kit;

/* renamed from: b.a.a.a.a.b.o */
public class IdManager {
    private static final Pattern f167a;
    private static final String f168b;
    private final ReentrantLock f169c;
    private final InstallerPackageNameProvider f170d;
    private final boolean f171e;
    private final boolean f172f;
    private final Context f173g;
    private final String f174h;
    private final String f175i;
    private final Collection<Kit> f176j;

    /* renamed from: b.a.a.a.a.b.o.a */
    public enum IdManager {
        WIFI_MAC_ADDRESS(1),
        BLUETOOTH_MAC_ADDRESS(2),
        FONT_TOKEN(53),
        ANDROID_ID(100),
        ANDROID_DEVICE_ID(HttpStatus.SC_SWITCHING_PROTOCOLS),
        ANDROID_SERIAL(HttpStatus.SC_PROCESSING),
        ANDROID_ADVERTISING_ID(103);
        
        public final int f166h;

        private IdManager(int i) {
            this.f166h = i;
        }
    }

    static {
        f167a = Pattern.compile("[^\\p{Alnum}]");
        f168b = Pattern.quote("/");
    }

    public IdManager(Context context, String str, String str2, Collection<Kit> collection) {
        this.f169c = new ReentrantLock();
        if (context == null) {
            throw new IllegalArgumentException("appContext must not be null");
        } else if (str == null) {
            throw new IllegalArgumentException("appIdentifier must not be null");
        } else if (collection == null) {
            throw new IllegalArgumentException("kits must not be null");
        } else {
            this.f173g = context;
            this.f174h = str;
            this.f175i = str2;
            this.f176j = collection;
            this.f170d = new InstallerPackageNameProvider();
            this.f171e = CommonUtils.m170a(context, "com.crashlytics.CollectDeviceIdentifiers", true);
            if (!this.f171e) {
                Fabric.m512h().m474a(CrashlyticsCore.TAG, "Device ID collection disabled for " + context.getPackageName());
            }
            this.f172f = CommonUtils.m170a(context, "com.crashlytics.CollectUserIdentifiers", true);
            if (!this.f172f) {
                Fabric.m512h().m474a(CrashlyticsCore.TAG, "User information collection disabled for " + context.getPackageName());
            }
        }
    }

    public String m210a(String str, String str2) {
        try {
            Cipher a = CommonUtils.m162a(1, CommonUtils.m157a(str + str2.replaceAll("\\.", new StringBuilder(new String(new char[]{'s', 'l', 'c'})).reverse().toString())));
            JSONObject jSONObject = new JSONObject();
            m203a(jSONObject);
            m206b(jSONObject);
            m208c(jSONObject);
            m209d(jSONObject);
            String str3 = Trace.NULL;
            if (jSONObject.length() > 0) {
                try {
                    return CommonUtils.m159a(a.doFinal((!(jSONObject instanceof JSONObject) ? jSONObject.toString() : JSONObjectInstrumentation.toString(jSONObject)).getBytes()));
                } catch (Throwable e) {
                    Fabric.m512h().m482e(CrashlyticsCore.TAG, "Could not encrypt IDs", e);
                }
            }
            return str3;
        } catch (Throwable e2) {
            Fabric.m512h().m482e(CrashlyticsCore.TAG, "Could not create cipher to encrypt headers.", e2);
            return Trace.NULL;
        }
    }

    private void m203a(JSONObject jSONObject) {
        try {
            jSONObject.put("APPLICATION_INSTALLATION_UUID".toLowerCase(Locale.US), m212b());
        } catch (Throwable e) {
            Fabric.m512h().m482e(CrashlyticsCore.TAG, "Could not write application id to JSON", e);
        }
    }

    private void m206b(JSONObject jSONObject) {
        for (Entry entry : m217g().entrySet()) {
            try {
                jSONObject.put(((IdManager) entry.getKey()).name().toLowerCase(Locale.US), entry.getValue());
            } catch (Throwable e) {
                Fabric.m512h().m482e(CrashlyticsCore.TAG, "Could not write value to JSON: " + ((IdManager) entry.getKey()).name(), e);
            }
        }
    }

    private void m208c(JSONObject jSONObject) {
        try {
            jSONObject.put(ao.OS_VERSION_KEY, m214d());
        } catch (Throwable e) {
            Fabric.m512h().m482e(CrashlyticsCore.TAG, "Could not write OS version to JSON", e);
        }
    }

    private void m209d(JSONObject jSONObject) {
        try {
            jSONObject.put("model", m215e());
        } catch (Throwable e) {
            Fabric.m512h().m482e(CrashlyticsCore.TAG, "Could not write model to JSON", e);
        }
    }

    public boolean m211a() {
        return this.f172f;
    }

    private boolean m204a(String str) {
        return this.f173g.checkCallingPermission(str) == 0;
    }

    private String m205b(String str) {
        return str == null ? null : f167a.matcher(str).replaceAll(Trace.NULL).toLowerCase(Locale.US);
    }

    public String m212b() {
        String str = this.f175i;
        if (str != null) {
            return str;
        }
        SharedPreferences a = CommonUtils.m152a(this.f173g);
        str = a.getString("crashlytics.installation.id", null);
        if (str == null) {
            return m201a(a);
        }
        return str;
    }

    public String m213c() {
        return this.f174h;
    }

    public String m214d() {
        return String.format(Locale.US, "%s/%s", new Object[]{m207c(VERSION.RELEASE), m207c(VERSION.INCREMENTAL)});
    }

    public String m215e() {
        return String.format(Locale.US, "%s/%s", new Object[]{m207c(Build.MANUFACTURER), m207c(Build.MODEL)});
    }

    private String m207c(String str) {
        return str.replaceAll(f168b, Trace.NULL);
    }

    public String m216f() {
        String str = Trace.NULL;
        if (!this.f171e) {
            return str;
        }
        str = m220j();
        if (str != null) {
            return str;
        }
        SharedPreferences a = CommonUtils.m152a(this.f173g);
        str = a.getString("crashlytics.installation.id", null);
        if (str == null) {
            return m201a(a);
        }
        return str;
    }

    private String m201a(SharedPreferences sharedPreferences) {
        this.f169c.lock();
        try {
            String string = sharedPreferences.getString("crashlytics.installation.id", null);
            if (string == null) {
                string = m205b(UUID.randomUUID().toString());
                sharedPreferences.edit().putString("crashlytics.installation.id", string).commit();
            }
            this.f169c.unlock();
            return string;
        } catch (Throwable th) {
            this.f169c.unlock();
        }
    }

    public Map<IdManager, String> m217g() {
        Map hashMap = new HashMap();
        for (Kit kit : this.f176j) {
            if (kit instanceof DeviceIdentifierProvider) {
                for (Entry entry : ((DeviceIdentifierProvider) kit).getDeviceIdentifiers().entrySet()) {
                    m202a(hashMap, (IdManager) entry.getKey(), (String) entry.getValue());
                }
            }
        }
        m202a(hashMap, IdManager.ANDROID_ID, m220j());
        m202a(hashMap, IdManager.ANDROID_DEVICE_ID, m221k());
        m202a(hashMap, IdManager.ANDROID_SERIAL, m224n());
        m202a(hashMap, IdManager.WIFI_MAC_ADDRESS, m222l());
        m202a(hashMap, IdManager.BLUETOOTH_MAC_ADDRESS, m223m());
        m202a(hashMap, IdManager.ANDROID_ADVERTISING_ID, m219i());
        return Collections.unmodifiableMap(hashMap);
    }

    public String m218h() {
        return this.f170d.m226a(this.f173g);
    }

    public String m219i() {
        if (!this.f171e) {
            return null;
        }
        AdvertisingInfo a = new AdvertisingInfoProvider(this.f173g).m126a();
        if (a != null) {
            return a.f115a;
        }
        return null;
    }

    private void m202a(Map<IdManager, String> map, IdManager idManager, String str) {
        if (str != null) {
            map.put(idManager, str);
        }
    }

    public String m220j() {
        if (!this.f171e) {
            return null;
        }
        String string = Secure.getString(this.f173g.getContentResolver(), "android_id");
        if ("9774d56d682e549c".equals(string)) {
            return null;
        }
        return m205b(string);
    }

    public String m221k() {
        if (this.f171e && m204a("android.permission.READ_PHONE_STATE")) {
            TelephonyManager telephonyManager = (TelephonyManager) this.f173g.getSystemService(Constants.PHONE);
            if (telephonyManager != null) {
                return m205b(telephonyManager.getDeviceId());
            }
        }
        return null;
    }

    public String m222l() {
        if (this.f171e && m204a("android.permission.ACCESS_WIFI_STATE")) {
            WifiManager wifiManager = (WifiManager) this.f173g.getSystemService(WanType.WIFI);
            if (wifiManager != null) {
                WifiInfo connectionInfo = wifiManager.getConnectionInfo();
                if (connectionInfo != null) {
                    return m205b(connectionInfo.getMacAddress());
                }
            }
        }
        return null;
    }

    public String m223m() {
        if (this.f171e && m204a("android.permission.BLUETOOTH")) {
            try {
                BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
                if (defaultAdapter != null) {
                    m205b(defaultAdapter.getAddress());
                }
            } catch (Throwable e) {
                Fabric.m512h().m482e(CrashlyticsCore.TAG, "Utils#getBluetoothMacAddress failed, returning null. Requires prior call to BluetoothAdatpter.getDefaultAdapter() on thread that has called Looper.prepare()", e);
            }
        }
        return null;
    }

    public String m224n() {
        if (this.f171e && VERSION.SDK_INT >= 9) {
            try {
                return m205b((String) Build.class.getField("SERIAL").get(null));
            } catch (Throwable e) {
                Fabric.m512h().m482e(CrashlyticsCore.TAG, "Could not retrieve android.os.Build.SERIAL value", e);
            }
        }
        return null;
    }
}
