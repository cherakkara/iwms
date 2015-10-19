package com.facebook;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import com.facebook.p022b.Logger;
import com.facebook.p022b.Utility;
import com.facebook.p022b.Validate;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import java.util.ArrayList;
import java.util.Date;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.facebook.q */
final class LegacyTokenHelper {
    private static final String f1032a;
    private String f1033b;
    private SharedPreferences f1034c;

    static {
        f1032a = LegacyTokenHelper.class.getSimpleName();
    }

    public LegacyTokenHelper(Context context) {
        this(context, null);
    }

    public LegacyTokenHelper(Context context, String str) {
        Validate.m1146a((Object) context, "context");
        if (Utility.m1126a(str)) {
            str = "com.facebook.SharedPreferencesTokenCachingStrategy.DEFAULT_KEY";
        }
        this.f1033b = str;
        Context applicationContext = context.getApplicationContext();
        if (applicationContext != null) {
            context = applicationContext;
        }
        this.f1034c = context.getSharedPreferences(this.f1033b, 0);
    }

    public Bundle m1360a() {
        Bundle bundle = new Bundle();
        for (String str : this.f1034c.getAll().keySet()) {
            try {
                m1355a(str, bundle);
            } catch (JSONException e) {
                Logger.m998a(LoggingBehavior.CACHE, 5, f1032a, "Error reading cached value for key: '" + str + "' -- " + e);
                return null;
            }
        }
        return bundle;
    }

    public void m1361b() {
        this.f1034c.edit().clear().apply();
    }

    public static boolean m1356a(Bundle bundle) {
        if (bundle == null) {
            return false;
        }
        String string = bundle.getString("com.facebook.TokenCachingStrategy.Token");
        if (string == null || string.length() == 0 || bundle.getLong("com.facebook.TokenCachingStrategy.ExpirationDate", 0) == 0) {
            return false;
        }
        return true;
    }

    public static String m1357b(Bundle bundle) {
        Validate.m1146a((Object) bundle, "bundle");
        return bundle.getString("com.facebook.TokenCachingStrategy.Token");
    }

    public static AccessTokenSource m1358c(Bundle bundle) {
        Validate.m1146a((Object) bundle, "bundle");
        if (bundle.containsKey("com.facebook.TokenCachingStrategy.AccessTokenSource")) {
            return (AccessTokenSource) bundle.getSerializable("com.facebook.TokenCachingStrategy.AccessTokenSource");
        }
        return bundle.getBoolean("com.facebook.TokenCachingStrategy.IsSSO") ? AccessTokenSource.FACEBOOK_APPLICATION_WEB : AccessTokenSource.WEB_VIEW;
    }

    public static String m1359d(Bundle bundle) {
        Validate.m1146a((Object) bundle, "bundle");
        return bundle.getString("com.facebook.TokenCachingStrategy.ApplicationId");
    }

    static Date m1354a(Bundle bundle, String str) {
        if (bundle == null) {
            return null;
        }
        long j = bundle.getLong(str, Long.MIN_VALUE);
        if (j != Long.MIN_VALUE) {
            return new Date(j);
        }
        return null;
    }

    private void m1355a(String str, Bundle bundle) throws JSONException {
        int i = 0;
        JSONObject init = JSONObjectInstrumentation.init(this.f1034c.getString(str, "{}"));
        String string = init.getString("valueType");
        if (string.equals("bool")) {
            bundle.putBoolean(str, init.getBoolean("value"));
        } else if (string.equals("bool[]")) {
            r1 = init.getJSONArray("value");
            boolean[] zArr = new boolean[r1.length()];
            while (i < zArr.length) {
                zArr[i] = r1.getBoolean(i);
                i++;
            }
            bundle.putBooleanArray(str, zArr);
        } else if (string.equals("byte")) {
            bundle.putByte(str, (byte) init.getInt("value"));
        } else if (string.equals("byte[]")) {
            r1 = init.getJSONArray("value");
            byte[] bArr = new byte[r1.length()];
            while (i < bArr.length) {
                bArr[i] = (byte) r1.getInt(i);
                i++;
            }
            bundle.putByteArray(str, bArr);
        } else if (string.equals("short")) {
            bundle.putShort(str, (short) init.getInt("value"));
        } else if (string.equals("short[]")) {
            r1 = init.getJSONArray("value");
            short[] sArr = new short[r1.length()];
            while (i < sArr.length) {
                sArr[i] = (short) r1.getInt(i);
                i++;
            }
            bundle.putShortArray(str, sArr);
        } else if (string.equals("int")) {
            bundle.putInt(str, init.getInt("value"));
        } else if (string.equals("int[]")) {
            r1 = init.getJSONArray("value");
            int[] iArr = new int[r1.length()];
            while (i < iArr.length) {
                iArr[i] = r1.getInt(i);
                i++;
            }
            bundle.putIntArray(str, iArr);
        } else if (string.equals("long")) {
            bundle.putLong(str, init.getLong("value"));
        } else if (string.equals("long[]")) {
            r1 = init.getJSONArray("value");
            long[] jArr = new long[r1.length()];
            while (i < jArr.length) {
                jArr[i] = r1.getLong(i);
                i++;
            }
            bundle.putLongArray(str, jArr);
        } else if (string.equals("float")) {
            bundle.putFloat(str, (float) init.getDouble("value"));
        } else if (string.equals("float[]")) {
            r1 = init.getJSONArray("value");
            float[] fArr = new float[r1.length()];
            while (i < fArr.length) {
                fArr[i] = (float) r1.getDouble(i);
                i++;
            }
            bundle.putFloatArray(str, fArr);
        } else if (string.equals("double")) {
            bundle.putDouble(str, init.getDouble("value"));
        } else if (string.equals("double[]")) {
            r1 = init.getJSONArray("value");
            double[] dArr = new double[r1.length()];
            while (i < dArr.length) {
                dArr[i] = r1.getDouble(i);
                i++;
            }
            bundle.putDoubleArray(str, dArr);
        } else if (string.equals("char")) {
            String string2 = init.getString("value");
            if (string2 != null && string2.length() == 1) {
                bundle.putChar(str, string2.charAt(0));
            }
        } else if (string.equals("char[]")) {
            r2 = init.getJSONArray("value");
            char[] cArr = new char[r2.length()];
            for (r1 = 0; r1 < cArr.length; r1++) {
                String string3 = r2.getString(r1);
                if (string3 != null && string3.length() == 1) {
                    cArr[r1] = string3.charAt(0);
                }
            }
            bundle.putCharArray(str, cArr);
        } else if (string.equals("string")) {
            bundle.putString(str, init.getString("value"));
        } else if (string.equals("stringList")) {
            r2 = init.getJSONArray("value");
            int length = r2.length();
            ArrayList arrayList = new ArrayList(length);
            for (r1 = 0; r1 < length; r1++) {
                Object obj = r2.get(r1);
                if (obj == JSONObject.NULL) {
                    obj = null;
                } else {
                    String str2 = (String) obj;
                }
                arrayList.add(r1, obj);
            }
            bundle.putStringArrayList(str, arrayList);
        } else if (string.equals("enum")) {
            try {
                bundle.putSerializable(str, Enum.valueOf(Class.forName(init.getString("enumType")), init.getString("value")));
            } catch (ClassNotFoundException e) {
            } catch (IllegalArgumentException e2) {
            }
        }
    }
}
