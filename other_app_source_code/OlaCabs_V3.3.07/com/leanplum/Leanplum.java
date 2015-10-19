package com.leanplum;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import com.leanplum.Util.DeviceIdInfo;
import com.leanplum.callbacks.ActionCallback;
import com.leanplum.callbacks.RegisterDeviceCallback;
import com.leanplum.callbacks.RegisterDeviceFinishedCallback;
import com.leanplum.callbacks.StartCallback;
import com.leanplum.callbacks.VariablesChangedCallback;
import com.leanplum.messagetemplates.MessageTemplates;
import com.newrelic.agent.android.analytics.AnalyticAttribute;
import com.newrelic.agent.android.instrumentation.Trace;
import com.olacabs.customer.utils.Constants;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.TimeZone;
import java.util.concurrent.atomic.AtomicBoolean;

public class Leanplum {
    public static int ACTION_KIND_ACTION = 0;
    public static int ACTION_KIND_MESSAGE = 0;
    public static final String PURCHASE_EVENT_NAME = "Purchase";
    static boolean f8554a;
    static boolean f8555b;
    static boolean f8556c;
    private static ArrayList<StartCallback> f8557d;
    private static ArrayList<VariablesChangedCallback> f8558e;
    private static ArrayList<VariablesChangedCallback> f8559f;
    private static ArrayList<VariablesChangedCallback> f8560g;
    private static Map<String, List<ActionCallback>> f8561h;
    private static RegisterDeviceCallback f8562i;
    private static RegisterDeviceFinishedCallback f8563j;
    private static C0629c f8564k;
    private static boolean f8565l;
    private static boolean f8566m;
    private static boolean f8567n;
    private static LeanplumDeviceIdMode f8568o;
    private static String f8569p;
    private static boolean f8570q;
    private static boolean f8571r;
    private static Object f8572s;
    private static Context f8573t;
    private static Queue<Map<String, ?>> f8574u;
    private static StartCallback f8575v;

    static {
        ACTION_KIND_MESSAGE = 1;
        ACTION_KIND_ACTION = 2;
        f8557d = new ArrayList();
        f8558e = new ArrayList();
        f8559f = new ArrayList();
        f8560g = new ArrayList();
        f8561h = new HashMap();
        f8555b = false;
        f8568o = LeanplumDeviceIdMode.MD5_MAC_ADDRESS;
        f8572s = new Object();
        f8574u = new LinkedList();
    }

    private Leanplum() {
    }

    public static void setApiConnectionSettings(String str, String str2, boolean z) {
        C0633g.f8794a = str;
        C0633g.f8810q = str2;
        C0633g.f8797d = z;
    }

    public static void setSocketConnectionSettings(String str, int i) {
        C0633g.f8795b = str;
        C0633g.f8796c = i;
    }

    public static void setFileHashingEnabledInDevelopmentMode(boolean z) {
        C0633g.f8803j = z;
    }

    public static void setFileUploadingEnabledInDevelopmentMode(boolean z) {
        C0633g.f8807n = z;
    }

    public static void enableVerboseLoggingInDevelopmentMode() {
        C0633g.f8806m = true;
    }

    public static void setNetworkTimeout(int i, int i2) {
        C0633g.f8798e = i;
        C0633g.f8799f = i2;
    }

    public static void setCanDownloadContentMidSessionInProductionMode(boolean z) {
        C0633g.f8808o = z;
    }

    public static void setAppIdForDevelopmentMode(String str, String str2) {
        C0633g.f8804k = true;
        C0618S.m12526a(str, str2);
    }

    public static void setAppIdForProductionMode(String str, String str2) {
        C0633g.f8804k = false;
        C0618S.m12526a(str, str2);
    }

    public static void setDeviceIdMode(LeanplumDeviceIdMode leanplumDeviceIdMode) {
        f8568o = leanplumDeviceIdMode;
        f8570q = true;
    }

    public static void setDeviceId(String str) {
        f8569p = str;
        f8570q = true;
    }

    public static void setApplicationContext(Context context) {
        f8573t = context;
    }

    static Context m12431a() {
        if (f8573t == null) {
            Log.e("Leanplum", "Your application context is not set. You should call Leanplum.setApplicationContext(this) or LeanplumActivityHelper.enableLifecycleCallbacks(this) in your application's onCreate method, or have your application extend LeanplumApplication.");
        }
        return f8573t;
    }

    @Deprecated
    public static void setRegisterDeviceHandler(RegisterDeviceCallback registerDeviceCallback, RegisterDeviceFinishedCallback registerDeviceFinishedCallback) {
        f8562i = registerDeviceCallback;
        f8563j = registerDeviceFinishedCallback;
    }

    public static void syncResources() {
        if (!C0633g.m12775a()) {
            FileManager.m12422a(null, null, false);
        }
    }

    public static void syncResourcesAsync() {
        if (!C0633g.m12775a()) {
            FileManager.m12422a(null, null, true);
        }
    }

    public static void syncResources(List<String> list, List<String> list2) {
        FileManager.m12422a((List) list, (List) list2, false);
    }

    public static void syncResourcesAsync(List<String> list, List<String> list2) {
        FileManager.m12422a((List) list, (List) list2, true);
    }

    public static void start(Context context) {
        m12433a(context, null, null, null, null);
    }

    public static void start(Context context, StartCallback startCallback) {
        m12433a(context, null, null, startCallback, null);
    }

    public static void start(Context context, Map<String, ?> map) {
        m12433a(context, null, (Map) map, null, null);
    }

    public static void start(Context context, String str) {
        m12433a(context, str, null, null, null);
    }

    public static void start(Context context, String str, StartCallback startCallback) {
        m12433a(context, str, null, startCallback, null);
    }

    public static void start(Context context, String str, Map<String, ?> map) {
        m12433a(context, str, (Map) map, null, null);
    }

    public static synchronized void start(Context context, String str, Map<String, ?> map, StartCallback startCallback) {
        synchronized (Leanplum.class) {
            m12433a(context, str, (Map) map, startCallback, null);
        }
    }

    private static synchronized void m12433a(Context context, String str, Map<String, ?> map, StartCallback startCallback, Boolean bool) {
        synchronized (Leanplum.class) {
            boolean z;
            C0612M.m12512a();
            if (context instanceof Activity) {
                LeanplumActivityHelper.f8577b = (Activity) context;
            }
            if (LeanplumActivityHelper.f8577b == null || LeanplumActivityHelper.f8576a) {
                z = true;
            } else {
                z = false;
            }
            if (C0633g.m12775a()) {
                f8565l = true;
                f8566m = true;
                m12460d(true);
                m12477u();
                m12478v();
                aT.m12668a(new HashMap(), new HashMap(), new HashMap(), new ArrayList());
            } else {
                if (startCallback != null) {
                    addStartResponseHandler(startCallback);
                }
                if (context != null) {
                    setApplicationContext(context.getApplicationContext());
                }
                if (!f8554a) {
                    MessageTemplates.register(m12431a());
                    f8556c = z;
                    Map a = m12432a((Map) map, "userAttributes", true);
                    f8554a = true;
                    if (a != null) {
                        f8574u.add(a);
                    }
                    f8564k = C0629c.m12764a();
                    aT.m12669a(true);
                    aT.m12676d();
                    aT.m12669a(false);
                    aT.m12664a(new aU());
                    C0618S.m12524a(new C0624Z());
                    if (f8569p == null && f8568o.equals(LeanplumDeviceIdMode.ADVERTISING_ID)) {
                        Util.m12568a(new C0604E(context, str, a, z), new Void[0]);
                    } else {
                        m12450b(context, str, a, z);
                    }
                } else if (z || !f8556c) {
                    Log.i("Leanplum", "Already called start");
                } else {
                    f8556c = false;
                    m12473q();
                }
            }
        }
    }

    private static void m12450b(Context context, String str, Map<String, ?> map, boolean z) {
        LeanplumPushService.m12497b();
        Boolean bool = null;
        if (C0618S.m12537c() == null) {
            String str2;
            if (!f8570q && C0633g.f8802i != null) {
                str2 = C0633g.f8802i;
            } else if (f8569p != null) {
                str2 = f8569p;
            } else {
                DeviceIdInfo a = Util.m12559a(f8568o);
                str2 = a.f8628a;
                bool = Boolean.valueOf(a.f8629b);
            }
            C0618S.m12525a(str2);
        }
        if (str == null) {
            str = C0618S.m12540d();
            if (str == null) {
                str = C0618S.m12537c();
            }
        }
        C0618S.m12534b(str);
        Object a2 = Util.m12561a();
        if (a2 == null) {
            a2 = Trace.NULL;
        }
        TimeZone timeZone = TimeZone.getDefault();
        int offset = timeZone.getOffset(new Date().getTime()) / Constants.MILLIS_IN_A_SECOND;
        Map hashMap = new HashMap();
        hashMap.put("includeDefaults", "false");
        if (z) {
            hashMap.put("background", Boolean.toString(z));
        }
        hashMap.put("versionName", a2);
        hashMap.put("deviceName", Util.m12575f());
        hashMap.put(AnalyticAttribute.DEVICE_MODEL_ATTRIBUTE, Util.m12571b());
        hashMap.put("systemName", Util.m12572c());
        hashMap.put("systemVersion", Util.m12573d());
        hashMap.put("timezone", timeZone.getID());
        hashMap.put("timezoneOffsetSeconds", offset);
        hashMap.put("locale", Util.m12576g());
        hashMap.put("country", "(detect)");
        hashMap.put("region", "(detect)");
        hashMap.put(Constants.CITY, "(detect)");
        hashMap.put("location", "(detect)");
        if (Boolean.TRUE.equals(bool)) {
            hashMap.put("limitTracking", bool.toString());
        }
        if (map != null) {
            hashMap.put("userAttributes", C0625a.m12597a((Map) map));
        }
        if (C0633g.f8804k) {
            hashMap.put("devMode", Boolean.TRUE.toString());
        }
        C0618S b = C0618S.m12530b("start", hashMap);
        b.m12551a(new C0605F(context, z));
        b.m12550a(new C0609J());
        b.m12557h();
    }

    private static void m12473q() {
        synchronized (f8572s) {
            if (f8571r) {
                return;
            }
            f8571r = true;
            if (C0633g.f8804k && !C0633g.m12775a()) {
                ab abVar = new ab();
            }
            m12447a(new String[]{"start", "resume"}, null, 3, null, null);
            m12479w();
        }
    }

    static void reset() {
        f8554a = false;
        f8565l = false;
        f8567n = false;
        f8566m = false;
        f8557d.clear();
        f8558e.clear();
        f8559f.clear();
        f8560g.clear();
        f8561h.clear();
        f8574u.clear();
    }

    static void setClient(String str, String str2, String str3) {
        C0633g.f8801h = str;
        C0633g.f8800g = str2;
        C0633g.f8802i = str3;
    }

    static void m12437a(RuntimeException runtimeException) {
        if (C0633g.f8804k) {
            throw runtimeException;
        }
        Log.e("Leanplum", runtimeException.getMessage() + " This error is only thrown in development mode.", runtimeException);
    }

    private static <T> Map<String, T> m12432a(Map<String, T> map, String str, boolean z) {
        if (map == null) {
            return null;
        }
        Map<String, T> hashMap = new HashMap();
        for (String str2 : map.keySet()) {
            Object obj = map.get(str2);
            if (obj != null) {
                if (z && (obj instanceof Iterable)) {
                    Object obj2;
                    for (Object a : (Iterable) obj) {
                        if (!m12448a(a, str)) {
                            obj2 = null;
                            break;
                        }
                    }
                    obj2 = 1;
                    if (obj2 == null) {
                    }
                } else {
                    if (obj instanceof Date) {
                        obj = Long.valueOf(((Date) obj).getTime());
                    }
                    if (m12448a(obj, str)) {
                    }
                }
                hashMap.put(str2, obj);
            }
        }
        return hashMap;
    }

    private static boolean m12448a(Object obj, String str) {
        if ((obj instanceof Number) || (obj instanceof String) || (obj instanceof Boolean)) {
            return true;
        }
        m12437a(new LeanplumException(new StringBuilder(String.valueOf(str)).append(" values must be of type String, Number, or Boolean.").toString()));
        return false;
    }

    static void m12449b() {
        if (!C0633g.m12775a()) {
            if (f8554a) {
                f8555b = true;
                if (f8565l) {
                    m12474r();
                    return;
                } else {
                    addStartResponseHandler(new C0610K());
                    return;
                }
            }
            Log.e("Leanplum", "You cannot call pause before calling start");
        }
    }

    private static void m12474r() {
        C0618S.m12530b("pauseSession", null).m12557h();
    }

    static void m12455c() {
        if (!C0633g.m12775a()) {
            if (f8554a) {
                f8555b = false;
                if (f8565l) {
                    m12475s();
                    return;
                } else {
                    addStartResponseHandler(new C0611L());
                    return;
                }
            }
            Log.e("Leanplum", "You cannot call resume before calling start");
        }
    }

    private static void m12475s() {
        C0618S b = C0618S.m12530b("resumeSession", null);
        if (f8556c) {
            f8556c = false;
            b.m12554e();
            m12473q();
            return;
        }
        b.m12555f();
        m12442a("resume", null, 3, null, null);
    }

    public static boolean hasStarted() {
        return f8565l;
    }

    public static boolean hasStartedAndRegisteredAsDeveloper() {
        return f8567n;
    }

    static void m12458d() {
        synchronized (FileManager.f8548b) {
            if (FileManager.m12423a()) {
                FileManager.m12420a(new C0638l());
            } else {
                m12476t();
            }
        }
    }

    private static void m12476t() {
        if (!f8567n) {
            f8567n = true;
            aT.m12679g();
            aT.m12680h();
        }
    }

    public static void addStartResponseHandler(StartCallback startCallback) {
        if (f8565l) {
            startCallback.setSuccess(f8566m);
            startCallback.run();
            return;
        }
        synchronized (f8557d) {
            if (f8557d.indexOf(startCallback) == -1) {
                f8557d.add(startCallback);
            }
        }
    }

    public static void removeStartResponseHandler(StartCallback startCallback) {
        synchronized (f8557d) {
            f8557d.remove(startCallback);
        }
    }

    private static void m12460d(boolean z) {
        synchronized (f8557d) {
            Iterator it = f8557d.iterator();
            while (it.hasNext()) {
                StartCallback startCallback = (StartCallback) it.next();
                startCallback.setSuccess(z);
                C0612M.m12512a().m12513a(startCallback);
            }
            f8557d.clear();
        }
    }

    public static void addVariablesChangedHandler(VariablesChangedCallback variablesChangedCallback) {
        synchronized (f8558e) {
            f8558e.add(variablesChangedCallback);
        }
        if (aT.m12675c()) {
            variablesChangedCallback.variablesChanged();
        }
    }

    public static void removeVariablesChangedHandler(VariablesChangedCallback variablesChangedCallback) {
        synchronized (f8558e) {
            f8558e.remove(variablesChangedCallback);
        }
    }

    private static void m12477u() {
        synchronized (f8558e) {
            Iterator it = f8558e.iterator();
            while (it.hasNext()) {
                C0612M.m12512a().m12513a((VariablesChangedCallback) it.next());
            }
        }
    }

    public static void addVariablesChangedAndNoDownloadsPendingHandler(VariablesChangedCallback variablesChangedCallback) {
        synchronized (f8559f) {
            f8559f.add(variablesChangedCallback);
        }
        if (aT.m12675c() && C0618S.m12542i() == 0) {
            variablesChangedCallback.variablesChanged();
        }
    }

    public static void removeVariablesChangedAndNoDownloadsPendingHandler(VariablesChangedCallback variablesChangedCallback) {
        synchronized (f8559f) {
            f8559f.remove(variablesChangedCallback);
        }
    }

    public static void addOnceVariablesChangedAndNoDownloadsPendingHandler(VariablesChangedCallback variablesChangedCallback) {
        if (aT.m12675c() && C0618S.m12542i() == 0) {
            variablesChangedCallback.variablesChanged();
            return;
        }
        synchronized (f8560g) {
            f8560g.add(variablesChangedCallback);
        }
    }

    public static void removeOnceVariablesChangedAndNoDownloadsPendingHandler(VariablesChangedCallback variablesChangedCallback) {
        synchronized (f8560g) {
            f8560g.remove(variablesChangedCallback);
        }
    }

    private static void m12478v() {
        synchronized (f8559f) {
            Iterator it = f8559f.iterator();
            while (it.hasNext()) {
                C0612M.m12512a().m12513a((VariablesChangedCallback) it.next());
            }
        }
        synchronized (f8560g) {
            it = f8560g.iterator();
            while (it.hasNext()) {
                C0612M.m12512a().m12513a((VariablesChangedCallback) it.next());
            }
            f8560g.clear();
        }
    }

    public static void defineAction(String str, int i, ActionArgs actionArgs) {
        m12440a(str, i, actionArgs, null, null);
    }

    public static void defineAction(String str, int i, ActionArgs actionArgs, ActionCallback actionCallback) {
        m12440a(str, i, actionArgs, null, actionCallback);
    }

    private static void m12440a(String str, int i, ActionArgs actionArgs, Map<String, Object> map, ActionCallback actionCallback) {
        MessageTemplates.register(m12431a());
        aT.m12665a(str, i, actionArgs.m12398a(), new HashMap());
        if (actionCallback != null) {
            onAction(str, actionCallback);
        }
    }

    public static void onAction(String str, ActionCallback actionCallback) {
        if (f8561h == null) {
            f8561h = new HashMap();
        }
        List list = (List) f8561h.get(str);
        if (list == null) {
            list = new ArrayList();
            f8561h.put(str, list);
        }
        list.add(actionCallback);
    }

    static void m12435a(ActionContext actionContext) {
        m12436a(actionContext, null);
    }

    private static void m12436a(ActionContext actionContext, VariablesChangedCallback variablesChangedCallback) {
        synchronized (f8561h) {
            List list = (List) f8561h.get(actionContext.actionName());
            if (list == null) {
                if (variablesChangedCallback != null) {
                    variablesChangedCallback.variablesChanged();
                }
                return;
            }
            List<ActionCallback> arrayList = new ArrayList(list);
            AtomicBoolean atomicBoolean = new AtomicBoolean(false);
            for (ActionCallback c0669s : arrayList) {
                C0612M.m12512a().m12513a(new C0669s(c0669s, actionContext, variablesChangedCallback, atomicBoolean));
            }
        }
    }

    static void m12442a(String str, String str2, int i, String str3, C0628b c0628b) {
        m12447a(new String[]{str}, str2, i, str3, c0628b);
    }

    private static void m12447a(String[] strArr, String str, int i, String str2, C0628b c0628b) {
        Map l = aT.m12684l();
        if (l != null) {
            for (String str3 : l.keySet()) {
                if (str3 == null || !str3.equals(str2)) {
                    Map map = (Map) l.get(str3);
                    String str4 = (String) map.get("action");
                    if (str4 instanceof String) {
                        if ((str4.equals("__Push Notification") ? null : 1) == null || (i & 1) != 0) {
                            C0632f c0632f = new C0632f();
                            for (String a : strArr) {
                                C0632f a2 = f8564k.m12772a(str3, map, a, str, c0628b);
                                c0632f.f8791a |= a2.f8791a;
                                c0632f.f8792b |= a2.f8792b;
                                c0632f.f8793c = a2.f8793c | c0632f.f8793c;
                            }
                            if (c0632f.f8792b) {
                                m12436a(new ActionContext("__Cancel" + map.get("action"), new HashMap(), str3), new C0670t(str3));
                            }
                            if (c0632f.f8791a) {
                                f8564k.m12773a(str3);
                                if (c0632f.f8793c) {
                                    ActionContext actionContext = new ActionContext(map.get("action").toString(), (Map) map.get("vars"), str3);
                                    actionContext.m12403a(c0628b);
                                    m12436a(actionContext, new C0671u(str3));
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    static void m12441a(String str, String str2) {
        Map l = aT.m12684l();
        if (l != null) {
            l = (Map) l.get(str2);
            if (l != null) {
                new ActionContext(l.get("action").toString(), (Map) l.get("vars"), str2).runTrackedActionNamed(str);
            }
        }
    }

    public static void setUserAttributes(String str, Map<String, ?> map) {
        if (!C0633g.m12775a()) {
            if (f8554a) {
                HashMap hashMap = new HashMap();
                if (str != null) {
                    hashMap.put("newUserId", str);
                }
                if (map != null) {
                    Map a = m12432a((Map) map, "userAttributes", true);
                    hashMap.put("userAttributes", C0625a.m12597a(a));
                    f8574u.add(a);
                }
                if (f8565l) {
                    m12451b(str, hashMap);
                    return;
                } else {
                    addStartResponseHandler(new C0672v(str, hashMap));
                    return;
                }
            }
            Log.e("Leanplum", "You cannot call setUserAttributes before calling start");
        }
    }

    private static void m12451b(String str, HashMap<String, String> hashMap) {
        C0618S.m12530b("setUserAttributes", hashMap).m12554e();
        if (str != null && str.length() > 0) {
            C0618S.m12534b(str);
            if (f8565l) {
                aT.m12677e();
            }
        }
        m12479w();
    }

    private static void m12479w() {
        Object obj = null;
        for (Map map : f8574u) {
            Map m = aT.m12685m();
            Object obj2 = obj;
            for (String str : map.keySet()) {
                Object obj3 = m.get(str);
                Object obj4 = map.get(str);
                if ((obj3 == null && obj4 != null) || !(obj3 == null || obj3.equals(obj4))) {
                    C0628b c0628b = new C0628b();
                    c0628b.f8782b = obj3;
                    c0628b.f8783c = obj4;
                    m.put(str, obj4);
                    m12442a("userAttribute", str, 3, null, c0628b);
                    obj2 = 1;
                }
            }
            obj = obj2;
        }
        f8574u.clear();
        if (obj != null) {
            aT.m12686n();
        }
    }

    public static void setUserId(String str) {
        setUserAttributes(str, null);
    }

    public static void setUserAttributes(Map<String, Object> map) {
        setUserAttributes(null, map);
    }

    static void m12438a(String str) {
        if (!C0633g.m12775a()) {
            StartCallback c0673w = new C0673w(str);
            f8575v = c0673w;
            addStartResponseHandler(c0673w);
        }
    }

    public static void setTrafficSourceInfo(Map<String, String> map) {
        if (!C0633g.m12775a()) {
            if (f8554a) {
                HashMap hashMap = new HashMap();
                hashMap.put("trafficSource", C0625a.m12597a(m12432a((Map) map, "info", false)));
                if (f8565l) {
                    m12453b(hashMap);
                    return;
                } else {
                    addStartResponseHandler(new C0674x(hashMap));
                    return;
                }
            }
            Log.e("Leanplum", "You cannot call setTrafficSourceInfo before calling start");
        }
    }

    private static void m12453b(HashMap<String, String> hashMap) {
        C0618S.m12530b("setTrafficSourceInfo", hashMap).m12554e();
    }

    public static void track(String str, double d, String str2, Map<String, ?> map) {
        m12439a(str, d, str2, (Map) map, null);
    }

    static void m12439a(String str, double d, String str2, Map<String, ?> map, Map<String, String> map2) {
        if (!C0633g.m12775a()) {
            if (f8554a) {
                Map hashMap = new HashMap();
                if (map2 != null) {
                    hashMap.putAll(map2);
                }
                hashMap.put("value", d);
                hashMap.put("info", str2);
                if (str != null) {
                    hashMap.put(Constants.PUSH_ACK_TYPE, str);
                }
                if (map != null) {
                    map = m12432a((Map) map, "params", false);
                    hashMap.put("params", C0625a.m12597a((Map) map));
                }
                if (!f8571r || LeanplumActivityHelper.f8576a) {
                    hashMap.put("allowOffline", Boolean.TRUE.toString());
                }
                if (f8565l) {
                    m12456c(str, map, hashMap);
                    return;
                } else {
                    addStartResponseHandler(new C0675y(str, map, hashMap));
                    return;
                }
            }
            Log.e("Leanplum", "You cannot call track before calling start");
        }
    }

    private static void m12456c(String str, Map<String, ?> map, Map<String, String> map2) {
        C0618S.m12530b("track", map2).m12554e();
        String str2 = (String) map2.get("messageId");
        if (str2 != null) {
            String str3 = ".m" + str2;
            if (str == null || str.length() <= 0) {
                str = str3;
            } else {
                str = new StringBuilder(String.valueOf(str3)).append(" ").append(str).toString();
            }
        }
        C0628b c0628b = new C0628b();
        c0628b.f8781a = map;
        m12442a(Constants.PUSH_ACK_TYPE, str, 3, str2, c0628b);
    }

    public static void trackGooglePlayPurchase(String str, long j, String str2, String str3, String str4) {
        trackGooglePlayPurchase(PURCHASE_EVENT_NAME, str, j, str2, str3, str4, null);
    }

    public static void trackGooglePlayPurchase(String str, long j, String str2, String str3, String str4, Map<String, ?> map) {
        trackGooglePlayPurchase(PURCHASE_EVENT_NAME, str, j, str2, str3, str4, map);
    }

    public static void trackGooglePlayPurchase(String str, String str2, long j, String str3, String str4, String str5, Map<String, ?> map) {
        Map hashMap;
        Map hashMap2 = new HashMap();
        hashMap2.put("googlePlayPurchaseData", str4);
        hashMap2.put("googlePlayPurchaseDataSignature", str5);
        hashMap2.put("currencyCode", str3);
        if (map == null) {
            hashMap = new HashMap();
        } else {
            hashMap = new HashMap(map);
        }
        hashMap.put("item", str2);
        m12439a(str, ((double) j) / 1000000.0d, null, hashMap, hashMap2);
    }

    public static void track(String str) {
        track(str, 0.0d, Trace.NULL, null);
    }

    public static void track(String str, double d) {
        track(str, d, Trace.NULL, null);
    }

    public static void track(String str, String str2) {
        track(str, 0.0d, str2, null);
    }

    public static void track(String str, Map<String, ?> map) {
        track(str, 0.0d, Trace.NULL, map);
    }

    public static void track(String str, double d, Map<String, ?> map) {
        track(str, d, Trace.NULL, map);
    }

    public static void track(String str, double d, String str2) {
        track(str, d, str2, null);
    }

    public static void advanceTo(String str, String str2, Map<String, ?> map) {
        if (!C0633g.m12775a()) {
            if (f8554a) {
                Map a;
                Map hashMap = new HashMap();
                hashMap.put("info", str2);
                hashMap.put("state", str);
                if (map != null) {
                    a = m12432a((Map) map, "params", false);
                    hashMap.put("params", C0625a.m12597a(a));
                } else {
                    a = null;
                }
                if (f8565l) {
                    m12459d(str, a, hashMap);
                    return;
                } else {
                    addStartResponseHandler(new C0676z(str, a, hashMap));
                    return;
                }
            }
            Log.e("Leanplum", "You cannot call advanceTo before calling start");
        }
    }

    private static void m12459d(String str, Map<String, ?> map, Map<String, String> map2) {
        C0618S.m12530b("advance", map2).m12554e();
        C0628b c0628b = new C0628b();
        c0628b.f8781a = map;
        m12442a("state", str, 3, null, c0628b);
    }

    public static void advanceTo(String str) {
        advanceTo(str, Trace.NULL, null);
    }

    public static void advanceTo(String str, String str2) {
        advanceTo(str, str2, null);
    }

    public static void advanceTo(String str, Map<String, ?> map) {
        advanceTo(str, Trace.NULL, map);
    }

    public static void pauseState() {
        if (!C0633g.m12775a()) {
            if (!f8554a) {
                Log.e("Leanplum", "You cannot call pauseState before calling start");
            } else if (f8565l) {
                m12480x();
            } else {
                addStartResponseHandler(new C0599A());
            }
        }
    }

    private static void m12480x() {
        C0618S.m12530b("pauseState", new HashMap()).m12554e();
    }

    public static void resumeState() {
        if (!C0633g.m12775a()) {
            if (!f8554a) {
                Log.e("Leanplum", "You cannot call resumeState before calling start");
            } else if (f8565l) {
                m12481y();
            } else {
                addStartResponseHandler(new C0600B());
            }
        }
    }

    private static void m12481y() {
        C0618S.m12530b("resumeState", new HashMap()).m12554e();
    }

    public static void forceContentUpdate() {
        forceContentUpdate(null);
    }

    public static void forceContentUpdate(VariablesChangedCallback variablesChangedCallback) {
        if (!C0633g.m12775a()) {
            Map hashMap = new HashMap();
            hashMap.put("includeDefaults", "false");
            C0618S b = C0618S.m12530b("getVars", hashMap);
            b.m12551a(new C0601C(variablesChangedCallback));
            b.m12550a(new C0603D(variablesChangedCallback));
            b.m12557h();
        } else if (variablesChangedCallback != null) {
            C0612M.m12512a().m12513a(variablesChangedCallback);
        }
    }

    public static void enableTestMode() {
        C0633g.f8805l = true;
    }

    public static boolean isTestModeEnabled() {
        return C0633g.f8805l;
    }

    public static void setIsTestModeEnabled(boolean z) {
        C0633g.f8805l = z;
    }

    public static String pathForResource(String str) {
        return Var.defineFile(str, str).fileValue();
    }

    public static Object objectForKeyPath(Object... objArr) {
        return aT.m12659a(objArr);
    }

    public static Object objectForKeyPathComponents(Object[] objArr) {
        return aT.m12659a(objArr);
    }

    public static List<Map<String, Object>> variants() {
        List<Map<String, Object>> j = aT.m12682j();
        if (j == null) {
            return new ArrayList();
        }
        return j;
    }
}
