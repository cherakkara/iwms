package com.facebook.p022b;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.content.pm.Signature;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import com.facebook.FacebookException;
import com.facebook.FacebookOperationCanceledException;
import com.facebook.FacebookSdk;
import com.facebook.login.DefaultAudience;
import com.olacabs.customer.p076d.AppInfo;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.UUID;

/* renamed from: com.facebook.b.o */
public final class NativeProtocol {
    private static final NativeProtocol f821a;
    private static List<NativeProtocol> f822b;
    private static Map<String, List<NativeProtocol>> f823c;
    private static final List<Integer> f824d;

    /* renamed from: com.facebook.b.o.c */
    private static abstract class NativeProtocol {
        private static final HashSet<String> f820a;

        protected abstract String m1029a();

        private NativeProtocol() {
        }

        static {
            f820a = NativeProtocol.m1028b();
        }

        private static HashSet<String> m1028b() {
            HashSet<String> hashSet = new HashSet();
            hashSet.add("8a3c4b262d721acd49a4bf97d5213199c86fa2b9");
            hashSet.add("a4b7452e2ed8f5f191058ca7bbfd26b0d3214bfc");
            hashSet.add("5e8f16062ea3cd2c4a0d547876baa6f38cabf625");
            return hashSet;
        }

        public boolean m1030a(Context context, String str) {
            String str2 = Build.BRAND;
            int i = context.getApplicationInfo().flags;
            if (str2.startsWith("generic") && (i & 2) != 0) {
                return true;
            }
            try {
                for (Signature toByteArray : context.getPackageManager().getPackageInfo(str, 64).signatures) {
                    if (f820a.contains(Utility.m1103a(toByteArray.toByteArray()))) {
                        return true;
                    }
                }
                return false;
            } catch (NameNotFoundException e) {
                return false;
            }
        }
    }

    /* renamed from: com.facebook.b.o.a */
    private static class NativeProtocol extends NativeProtocol {
        private NativeProtocol() {
            super();
        }

        protected String m1031a() {
            return "com.facebook.katana";
        }
    }

    /* renamed from: com.facebook.b.o.b */
    private static class NativeProtocol extends NativeProtocol {
        private NativeProtocol() {
            super();
        }

        protected String m1032a() {
            return "com.facebook.orca";
        }
    }

    /* renamed from: com.facebook.b.o.d */
    private static class NativeProtocol extends NativeProtocol {
        private NativeProtocol() {
            super();
        }

        protected String m1033a() {
            return "com.facebook.wakizashi";
        }
    }

    static {
        f821a = new NativeProtocol();
        f822b = NativeProtocol.m1055b();
        f823c = NativeProtocol.m1058c();
        f824d = Arrays.asList(new Integer[]{Integer.valueOf(20141218), Integer.valueOf(20141107), Integer.valueOf(20141028), Integer.valueOf(20141001), Integer.valueOf(20140701), Integer.valueOf(20140324), Integer.valueOf(20140204), Integer.valueOf(20131107), Integer.valueOf(20130618), Integer.valueOf(20130502), Integer.valueOf(20121101)});
    }

    private static List<NativeProtocol> m1055b() {
        List<NativeProtocol> arrayList = new ArrayList();
        arrayList.add(f821a);
        arrayList.add(new NativeProtocol());
        return arrayList;
    }

    private static Map<String, List<NativeProtocol>> m1058c() {
        Map<String, List<NativeProtocol>> hashMap = new HashMap();
        ArrayList arrayList = new ArrayList();
        arrayList.add(new NativeProtocol());
        hashMap.put("com.facebook.platform.action.request.OGACTIONPUBLISH_DIALOG", f822b);
        hashMap.put("com.facebook.platform.action.request.FEED_DIALOG", f822b);
        hashMap.put("com.facebook.platform.action.request.LIKE_DIALOG", f822b);
        hashMap.put("com.facebook.platform.action.request.APPINVITES_DIALOG", f822b);
        hashMap.put("com.facebook.platform.action.request.MESSAGE_DIALOG", arrayList);
        hashMap.put("com.facebook.platform.action.request.OGMESSAGEPUBLISH_DIALOG", arrayList);
        return hashMap;
    }

    static Intent m1041a(Context context, Intent intent, NativeProtocol nativeProtocol) {
        if (intent == null) {
            return null;
        }
        ResolveInfo resolveActivity = context.getPackageManager().resolveActivity(intent, 0);
        if (resolveActivity == null) {
            return null;
        }
        if (nativeProtocol.m1030a(context, resolveActivity.activityInfo.packageName)) {
            return intent;
        }
        return null;
    }

    static Intent m1053b(Context context, Intent intent, NativeProtocol nativeProtocol) {
        if (intent == null) {
            return null;
        }
        ResolveInfo resolveService = context.getPackageManager().resolveService(intent, 0);
        if (resolveService == null) {
            return null;
        }
        if (nativeProtocol.m1030a(context, resolveService.serviceInfo.packageName)) {
            return intent;
        }
        return null;
    }

    public static Intent m1044a(Context context, String str, Collection<String> collection, String str2, boolean z, DefaultAudience defaultAudience) {
        for (NativeProtocol nativeProtocol : f822b) {
            Intent putExtra = new Intent().setClassName(nativeProtocol.m1029a(), "com.facebook.katana.ProxyAuth").putExtra("client_id", str);
            if (!Utility.m1127a((Collection) collection)) {
                putExtra.putExtra("scope", TextUtils.join(",", collection));
            }
            if (!Utility.m1126a(str2)) {
                putExtra.putExtra("e2e", str2);
            }
            putExtra.putExtra("response_type", "token,signed_request");
            putExtra.putExtra("return_scopes", "true");
            putExtra.putExtra("default_audience", defaultAudience.m1310a());
            putExtra.putExtra("legacy_override", "v2.3");
            if (z) {
                putExtra.putExtra("auth_type", "rerequest");
            }
            Intent a = NativeProtocol.m1041a(context, putExtra, nativeProtocol);
            if (a != null) {
                return a;
            }
        }
        return null;
    }

    public static Intent m1040a(Context context) {
        for (NativeProtocol nativeProtocol : f822b) {
            Intent b = NativeProtocol.m1053b(context, new Intent().setClassName(nativeProtocol.m1029a(), "com.facebook.katana.platform.TokenRefreshService"), nativeProtocol);
            if (b != null) {
                return b;
            }
        }
        return null;
    }

    public static final int m1034a() {
        return ((Integer) f824d.get(0)).intValue();
    }

    private static Intent m1042a(Context context, String str, String str2) {
        List<NativeProtocol> list = (List) f823c.get(str2);
        if (list == null) {
            return null;
        }
        Intent intent = null;
        for (NativeProtocol nativeProtocol : list) {
            intent = NativeProtocol.m1041a(context, new Intent().setAction(str).setPackage(nativeProtocol.m1029a()).addCategory("android.intent.category.DEFAULT"), nativeProtocol);
            if (intent != null) {
                return intent;
            }
        }
        return intent;
    }

    public static boolean m1050a(int i) {
        return f824d.contains(Integer.valueOf(i)) && i >= 20140701;
    }

    public static Intent m1043a(Context context, String str, String str2, int i, Bundle bundle) {
        Intent a = NativeProtocol.m1042a(context, "com.facebook.platform.PLATFORM_ACTIVITY", str2);
        if (a == null) {
            return null;
        }
        NativeProtocol.m1049a(a, str, str2, i, bundle);
        return a;
    }

    public static void m1049a(Intent intent, String str, String str2, int i, Bundle bundle) {
        String h = FacebookSdk.m1210h();
        String i2 = FacebookSdk.m1211i();
        intent.putExtra("com.facebook.platform.protocol.PROTOCOL_VERSION", i).putExtra("com.facebook.platform.protocol.PROTOCOL_ACTION", str2).putExtra("com.facebook.platform.extra.APPLICATION_ID", h);
        if (NativeProtocol.m1050a(i)) {
            Bundle bundle2 = new Bundle();
            bundle2.putString("action_id", str);
            Utility.m1114a(bundle2, "app_name", i2);
            intent.putExtra("com.facebook.platform.protocol.BRIDGE_ARGS", bundle2);
            if (bundle == null) {
                bundle = new Bundle();
            }
            intent.putExtra("com.facebook.platform.protocol.METHOD_ARGS", bundle);
            return;
        }
        intent.putExtra("com.facebook.platform.protocol.CALL_ID", str);
        if (!Utility.m1126a(i2)) {
            intent.putExtra("com.facebook.platform.extra.APPLICATION_NAME", i2);
        }
        intent.putExtras(bundle);
    }

    public static Intent m1045a(Intent intent, Bundle bundle, FacebookException facebookException) {
        UUID b = NativeProtocol.m1056b(intent);
        if (b == null) {
            return null;
        }
        Intent intent2 = new Intent();
        intent2.putExtra("com.facebook.platform.protocol.PROTOCOL_VERSION", NativeProtocol.m1035a(intent));
        Bundle bundle2 = new Bundle();
        bundle2.putString("action_id", b.toString());
        if (facebookException != null) {
            bundle2.putBundle("error", NativeProtocol.m1046a(facebookException));
        }
        intent2.putExtra("com.facebook.platform.protocol.BRIDGE_ARGS", bundle2);
        if (bundle == null) {
            return intent2;
        }
        intent2.putExtra("com.facebook.platform.protocol.RESULT_ARGS", bundle);
        return intent2;
    }

    public static Intent m1052b(Context context) {
        for (NativeProtocol nativeProtocol : f822b) {
            Intent b = NativeProtocol.m1053b(context, new Intent("com.facebook.platform.PLATFORM_SERVICE").setPackage(nativeProtocol.m1029a()).addCategory("android.intent.category.DEFAULT"), nativeProtocol);
            if (b != null) {
                return b;
            }
        }
        return null;
    }

    public static int m1035a(Intent intent) {
        return intent.getIntExtra("com.facebook.platform.protocol.PROTOCOL_VERSION", 0);
    }

    public static UUID m1056b(Intent intent) {
        UUID uuid = null;
        if (intent != null) {
            String string;
            if (NativeProtocol.m1050a(NativeProtocol.m1035a(intent))) {
                Bundle bundleExtra = intent.getBundleExtra("com.facebook.platform.protocol.BRIDGE_ARGS");
                if (bundleExtra != null) {
                    string = bundleExtra.getString("action_id");
                } else {
                    Object obj = uuid;
                }
            } else {
                string = intent.getStringExtra("com.facebook.platform.protocol.CALL_ID");
            }
            if (string != null) {
                try {
                    uuid = UUID.fromString(string);
                } catch (IllegalArgumentException e) {
                }
            }
        }
        return uuid;
    }

    public static Bundle m1057c(Intent intent) {
        if (NativeProtocol.m1050a(NativeProtocol.m1035a(intent))) {
            return intent.getBundleExtra("com.facebook.platform.protocol.METHOD_ARGS");
        }
        return intent.getExtras();
    }

    public static FacebookException m1047a(Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        String string = bundle.getString("error_type");
        if (string == null) {
            string = bundle.getString("com.facebook.platform.status.ERROR_TYPE");
        }
        String string2 = bundle.getString("error_description");
        if (string2 == null) {
            string2 = bundle.getString("com.facebook.platform.status.ERROR_DESCRIPTION");
        }
        if (string == null || !string.equalsIgnoreCase("UserCanceled")) {
            return new FacebookException(string2);
        }
        return new FacebookOperationCanceledException(string2);
    }

    public static Bundle m1046a(FacebookException facebookException) {
        if (facebookException == null) {
            return null;
        }
        Bundle bundle = new Bundle();
        bundle.putString("error_description", facebookException.toString());
        if (!(facebookException instanceof FacebookOperationCanceledException)) {
            return bundle;
        }
        bundle.putString("error_type", "UserCanceled");
        return bundle;
    }

    public static int m1051b(int i) {
        return NativeProtocol.m1038a(f822b, new int[]{i});
    }

    public static int m1037a(String str, int[] iArr) {
        return NativeProtocol.m1038a((List) f823c.get(str), iArr);
    }

    private static int m1038a(List<NativeProtocol> list, int[] iArr) {
        if (list == null) {
            return -1;
        }
        for (NativeProtocol a : list) {
            int a2 = NativeProtocol.m1036a(a, iArr);
            if (a2 != -1) {
                return a2;
            }
        }
        return -1;
    }

    private static int m1036a(NativeProtocol nativeProtocol, int[] iArr) {
        return NativeProtocol.m1039a(NativeProtocol.m1048a(nativeProtocol), NativeProtocol.m1034a(), iArr);
    }

    private static TreeSet<Integer> m1048a(NativeProtocol nativeProtocol) {
        Throwable th;
        TreeSet<Integer> treeSet = new TreeSet();
        Cursor query;
        try {
            query = FacebookSdk.m1208f().getContentResolver().query(NativeProtocol.m1054b(nativeProtocol), new String[]{AppInfo.APP_VERSION_KEY}, null, null, null);
            if (query != null) {
                while (query.moveToNext()) {
                    try {
                        treeSet.add(Integer.valueOf(query.getInt(query.getColumnIndex(AppInfo.APP_VERSION_KEY))));
                    } catch (Throwable th2) {
                        th = th2;
                    }
                }
            }
            if (query != null) {
                query.close();
            }
            return treeSet;
        } catch (Throwable th3) {
            th = th3;
            query = null;
            if (query != null) {
                query.close();
            }
            throw th;
        }
    }

    public static int m1039a(TreeSet<Integer> treeSet, int i, int[] iArr) {
        int length = iArr.length - 1;
        Iterator descendingIterator = treeSet.descendingIterator();
        int i2 = -1;
        int i3 = length;
        while (descendingIterator.hasNext()) {
            int intValue = ((Integer) descendingIterator.next()).intValue();
            length = Math.max(i2, intValue);
            i2 = i3;
            while (i2 >= 0 && iArr[i2] > intValue) {
                i2--;
            }
            if (i2 < 0) {
                return -1;
            }
            if (iArr[i2] != intValue) {
                i3 = i2;
                i2 = length;
            } else if (i2 % 2 == 0) {
                return Math.min(length, i);
            } else {
                return -1;
            }
        }
        return -1;
    }

    private static Uri m1054b(NativeProtocol nativeProtocol) {
        return Uri.parse("content://" + nativeProtocol.m1029a() + ".provider.PlatformProvider/versions");
    }
}
