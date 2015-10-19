package com.leanplum;

import android.util.Log;
import com.newrelic.agent.android.analytics.AnalyticAttribute;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import com.newrelic.agent.android.instrumentation.Trace;
import com.olacabs.customer.utils.Constants;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: com.leanplum.a */
class C0625a<T> {
    private String f8663a;
    private String f8664b;
    private T f8665c;
    private boolean f8666d;

    public static String m12597a(Map<String, ?> map) {
        try {
            JSONObject b = C0625a.m12605b(map);
            return !(b instanceof JSONObject) ? b.toString() : JSONObjectInstrumentation.toString(b);
        } catch (Throwable e) {
            Log.e("Leanplum", "Error converting " + map + " to JSON", e);
            return null;
        }
    }

    private C0625a() {
    }

    private static <T> C0625a<T> m12595a(String str, T t, String str2) {
        C0625a<T> c0625a = new C0625a();
        c0625a.f8663a = str;
        c0625a.f8664b = str2;
        c0625a.f8665c = t;
        return c0625a;
    }

    public static Map<String, Object> m12599a(String str) {
        try {
            return C0625a.m12600a(JSONObjectInstrumentation.init(str));
        } catch (Throwable e) {
            Log.e("Leanplum", "Error converting " + str + " from JSON", e);
            return null;
        }
    }

    public static boolean m12602a(Number number, Number number2) {
        return (number.intValue() == 4 || number.intValue() == 1) && number2.intValue() == 2;
    }

    public static <T> C0625a<T> m12594a(String str, T t) {
        return C0625a.m12595a(str, (Object) t, aT.m12661a((Object) t));
    }

    public static String m12596a(String str, String str2) {
        try {
            String arrays = Arrays.toString(C0625a.m12603a(1, str, str2.getBytes(HTTP.UTF_8)));
            if (arrays != null) {
                return arrays;
            }
            return str2;
        } catch (Throwable e) {
            Log.w("Leanplum", "Unable to encrypt " + str2, e);
            return str2;
        }
    }

    public static JSONObject m12605b(Map<String, ?> map) {
        JSONObject jSONObject = new JSONObject();
        for (String str : map.keySet()) {
            Object obj = map.get(str);
            if (obj instanceof Map) {
                obj = C0625a.m12605b((Map) obj);
            } else if (obj instanceof Iterable) {
                obj = C0625a.m12601a((Iterable) obj);
            } else if (obj == null) {
                obj = JSONObject.NULL;
            }
            jSONObject.put(str, obj);
        }
        return jSONObject;
    }

    public static C0625a<Integer> m12593a(String str, int i) {
        return C0625a.m12595a(str, Integer.valueOf(i), "color");
    }

    public static boolean m12606b(Number number, Number number2) {
        return number.intValue() == 2 && number2.intValue() == 4;
    }

    public static C0625a<String> m12607c(String str, String str2) {
        Object obj;
        if (str2 == null) {
            obj = Trace.NULL;
        }
        C0625a<String> a = C0625a.m12595a(str, obj, "file");
        aT.m12670a((String) a.f8665c, (String) a.f8665c, a.m12613d(), false, null, 0);
        return a;
    }

    public static JSONArray m12601a(Iterable<?> iterable) {
        JSONArray jSONArray = new JSONArray();
        for (Object next : iterable) {
            Object next2;
            if (next2 instanceof Map) {
                next2 = C0625a.m12605b((Map) next2);
            } else if (next2 instanceof Iterable) {
                next2 = C0625a.m12601a((Iterable) next2);
            } else if (next2 == null) {
                next2 = JSONObject.NULL;
            }
            jSONArray.put(next2);
        }
        return jSONArray;
    }

    public static C0625a<String> m12608d(String str, String str2) {
        C0625a<String> a = C0625a.m12595a(str, (Object) str2, "file");
        a.f8666d = true;
        aT.m12670a((String) a.f8665c, (String) a.f8665c, a.m12613d(), false, null, 0);
        return a;
    }

    public static String m12604b(String str, String str2) {
        try {
            str2 = str2.trim();
            String[] split = str2.substring(1, str2.length() - 1).trim().split("\\s*,\\s*");
            byte[] bArr = new byte[split.length];
            for (int i = 0; i < split.length; i++) {
                bArr[i] = Byte.parseByte(split[i]);
            }
            return new String(C0625a.m12603a(2, str, bArr));
        } catch (Throwable e) {
            Throwable th = e;
            String str3 = str2;
            Log.w("Leanplum", "Invalid ciphertext: " + str3, th);
            return str3;
        }
    }

    public static C0625a<String> m12609e(String str, String str2) {
        Object obj;
        if (str2 == null) {
            obj = Trace.NULL;
        }
        return C0625a.m12595a(str, obj, "action");
    }

    public static <T> Map<String, T> m12600a(JSONObject jSONObject) {
        Iterator keys = jSONObject.keys();
        Map<String, T> hashMap = new HashMap();
        while (keys.hasNext()) {
            String str = (String) keys.next();
            Object opt = jSONObject.opt(str);
            if (opt == null || opt == JSONObject.NULL) {
                opt = null;
            } else if (opt instanceof JSONObject) {
                opt = C0625a.m12600a((JSONObject) opt);
            } else if (opt instanceof JSONArray) {
                opt = C0625a.m12598a((JSONArray) opt);
            } else if (JSONObject.NULL.equals(opt)) {
                opt = null;
            }
            hashMap.put(str, opt);
        }
        return hashMap;
    }

    public String m12610a() {
        return this.f8663a;
    }

    public String m12611b() {
        return this.f8664b;
    }

    public T m12612c() {
        return this.f8665c;
    }

    public InputStream m12613d() {
        if (this.f8664b.equals("file")) {
            return FileManager.m12417a(false, Boolean.valueOf(this.f8666d), Boolean.valueOf(this.f8666d), (String) this.f8665c, (String) this.f8665c, null);
        }
        return null;
    }

    private static byte[] m12603a(int i, String str, byte[] bArr) {
        byte[] bArr2 = null;
        try {
            byte[] bytes = "L3@nP1Vm".getBytes(HTTP.UTF_8);
            byte[] bytes2 = "__l3anplum__iv__".getBytes(HTTP.UTF_8);
            Key secretKeySpec = new SecretKeySpec(SecretKeyFactory.getInstance("PBEWithMD5And128BitAES-CBC-OpenSSL").generateSecret(new PBEKeySpec(str.toCharArray(), bytes, Constants.MILLIS_IN_A_SECOND, AnalyticAttribute.ATTRIBUTE_NAME_MAX_LENGTH)).getEncoded(), "AES");
            Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
            instance.init(i, secretKeySpec, new IvParameterSpec(bytes2));
            bArr2 = instance.doFinal(bArr);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e2) {
            e2.printStackTrace();
        } catch (NoSuchPaddingException e3) {
            e3.printStackTrace();
        } catch (InvalidAlgorithmParameterException e4) {
            e4.printStackTrace();
        } catch (IllegalBlockSizeException e5) {
            e5.printStackTrace();
        } catch (BadPaddingException e6) {
            e6.printStackTrace();
        } catch (UnsupportedEncodingException e7) {
            e7.printStackTrace();
        } catch (InvalidKeySpecException e8) {
            e8.printStackTrace();
        }
        return bArr2;
    }

    public static <T> List<T> m12598a(JSONArray jSONArray) {
        List<T> arrayList = new ArrayList(jSONArray.length());
        for (int i = 0; i < jSONArray.length(); i++) {
            Object opt = jSONArray.opt(i);
            if (opt == null || opt == JSONObject.NULL) {
                opt = null;
            } else if (opt instanceof JSONObject) {
                opt = C0625a.m12600a((JSONObject) opt);
            } else if (opt instanceof JSONArray) {
                opt = C0625a.m12598a((JSONArray) opt);
            } else if (JSONObject.NULL.equals(opt)) {
                opt = null;
            }
            arrayList.add(opt);
        }
        return arrayList;
    }
}
