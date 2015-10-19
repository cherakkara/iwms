package com.facebook.share.internal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.util.Log;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.FacebookException;
import com.facebook.FacebookRequestError;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphRequest.C0153b;
import com.facebook.GraphRequestBatch.GraphRequestBatch;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.LoggingBehavior;
import com.facebook.p022b.BundleJSONConverter;
import com.facebook.p022b.CallbackManagerImpl.CallbackManagerImpl;
import com.facebook.p022b.FileLruCache;
import com.facebook.p022b.Logger;
import com.facebook.p022b.PlatformServiceClient.PlatformServiceClient;
import com.facebook.p022b.Utility;
import com.facebook.p022b.WorkQueue;
import com.facebook.p023a.AppEventsLogger;
import com.facebook.share.internal.LikeContent.C0180a;
import com.facebook.share.p024a.LikeView.LikeView;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import com.newrelic.agent.android.instrumentation.Trace;
import com.olacabs.customer.utils.Constants;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Locale;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: com.facebook.share.internal.b */
public class LikeActionController {
    private static final String f1168a;
    private static FileLruCache f1169b;
    private static final ConcurrentHashMap<String, LikeActionController> f1170c;
    private static WorkQueue f1171d;
    private static WorkQueue f1172e;
    private static Handler f1173f;
    private static String f1174g;
    private static boolean f1175h;
    private static volatile int f1176i;
    private static AccessTokenTracker f1177j;
    private String f1178k;
    private LikeView f1179l;
    private boolean f1180m;
    private String f1181n;
    private String f1182o;
    private String f1183p;
    private String f1184q;
    private String f1185r;
    private String f1186s;
    private boolean f1187t;
    private boolean f1188u;
    private boolean f1189v;
    private Bundle f1190w;
    private AppEventsLogger f1191x;

    /* renamed from: com.facebook.share.internal.b.c */
    public interface LikeActionController {
        void m1365a(LikeActionController likeActionController, FacebookException facebookException);
    }

    /* renamed from: com.facebook.share.internal.b.1 */
    class LikeActionController implements PlatformServiceClient {
        final /* synthetic */ LikeActionController f1117a;

        LikeActionController(LikeActionController likeActionController) {
            this.f1117a = likeActionController;
        }

        public void m1433a(Bundle bundle) {
            if (bundle != null && bundle.containsKey("com.facebook.platform.extra.OBJECT_IS_LIKED")) {
                this.f1117a.m1477a(bundle.getBoolean("com.facebook.platform.extra.OBJECT_IS_LIKED"), bundle.containsKey("com.facebook.platform.extra.LIKE_COUNT_STRING_WITH_LIKE") ? bundle.getString("com.facebook.platform.extra.LIKE_COUNT_STRING_WITH_LIKE") : this.f1117a.f1181n, bundle.containsKey("com.facebook.platform.extra.LIKE_COUNT_STRING_WITHOUT_LIKE") ? bundle.getString("com.facebook.platform.extra.LIKE_COUNT_STRING_WITHOUT_LIKE") : this.f1117a.f1182o, bundle.containsKey("com.facebook.platform.extra.SOCIAL_SENTENCE_WITH_LIKE") ? bundle.getString("com.facebook.platform.extra.SOCIAL_SENTENCE_WITH_LIKE") : this.f1117a.f1183p, bundle.containsKey("com.facebook.platform.extra.SOCIAL_SENTENCE_WITHOUT_LIKE") ? bundle.getString("com.facebook.platform.extra.SOCIAL_SENTENCE_WITHOUT_LIKE") : this.f1117a.f1184q, bundle.containsKey("com.facebook.platform.extra.UNLIKE_TOKEN") ? bundle.getString("com.facebook.platform.extra.UNLIKE_TOKEN") : this.f1117a.f1185r);
            }
        }
    }

    /* renamed from: com.facebook.share.internal.b.2 */
    class LikeActionController implements GraphRequestBatch {
        final /* synthetic */ LikeActionController f1118a;
        final /* synthetic */ LikeActionController f1119b;
        final /* synthetic */ LikeActionController f1120c;
        final /* synthetic */ LikeActionController f1121d;

        LikeActionController(LikeActionController likeActionController, LikeActionController likeActionController2, LikeActionController likeActionController3, LikeActionController likeActionController4) {
            this.f1121d = likeActionController;
            this.f1118a = likeActionController2;
            this.f1119b = likeActionController3;
            this.f1120c = likeActionController4;
        }

        public void m1434a(com.facebook.GraphRequestBatch graphRequestBatch) {
            this.f1121d.f1186s = this.f1118a.f1151e;
            if (Utility.m1126a(this.f1121d.f1186s)) {
                this.f1121d.f1186s = this.f1119b.f1156e;
                this.f1121d.f1187t = this.f1119b.f1157f;
            }
            if (Utility.m1126a(this.f1121d.f1186s)) {
                Logger.m1000a(LoggingBehavior.DEVELOPER_ERRORS, LikeActionController.f1168a, "Unable to verify the FB id for '%s'. Verify that it is a valid FB object or page", this.f1121d.f1178k);
                this.f1121d.m1472a("get_verified_id", this.f1119b.c != null ? this.f1119b.c : this.f1118a.c);
            }
            if (this.f1120c != null) {
                this.f1120c.m1437a();
            }
        }
    }

    /* renamed from: com.facebook.share.internal.b.3 */
    static class LikeActionController implements Runnable {
        final /* synthetic */ LikeActionController f1122a;

        LikeActionController(LikeActionController likeActionController) {
            this.f1122a = likeActionController;
        }

        public void run() {
            this.f1122a.m1520n();
        }
    }

    /* renamed from: com.facebook.share.internal.b.4 */
    static class LikeActionController implements CallbackManagerImpl {
        LikeActionController() {
        }
    }

    /* renamed from: com.facebook.share.internal.b.5 */
    static class LikeActionController implements Runnable {
        final /* synthetic */ LikeActionController f1123a;
        final /* synthetic */ LikeActionController f1124b;
        final /* synthetic */ FacebookException f1125c;

        LikeActionController(LikeActionController likeActionController, LikeActionController likeActionController2, FacebookException facebookException) {
            this.f1123a = likeActionController;
            this.f1124b = likeActionController2;
            this.f1125c = facebookException;
        }

        public void run() {
            this.f1123a.m1365a(this.f1124b, this.f1125c);
        }
    }

    /* renamed from: com.facebook.share.internal.b.6 */
    static class LikeActionController extends AccessTokenTracker {
        LikeActionController() {
        }

        protected void m1435a(AccessToken accessToken, AccessToken accessToken2) {
            Context f = FacebookSdk.m1208f();
            if (accessToken2 == null) {
                LikeActionController.f1176i = (LikeActionController.f1176i + 1) % Constants.MILLIS_IN_A_SECOND;
                f.getSharedPreferences("com.facebook.LikeActionController.CONTROLLER_STORE_KEY", 0).edit().putInt("OBJECT_SUFFIX", LikeActionController.f1176i).apply();
                LikeActionController.f1170c.clear();
                LikeActionController.f1169b.m995a();
            }
            LikeActionController.m1500d(null, "com.facebook.sdk.LikeActionController.DID_RESET");
        }
    }

    /* renamed from: com.facebook.share.internal.b.k */
    private interface LikeActionController {
        void m1437a();
    }

    /* renamed from: com.facebook.share.internal.b.7 */
    class LikeActionController implements LikeActionController {
        final /* synthetic */ Bundle f1128a;
        final /* synthetic */ LikeActionController f1129b;

        /* renamed from: com.facebook.share.internal.b.7.1 */
        class LikeActionController implements GraphRequestBatch {
            final /* synthetic */ LikeActionController f1126a;
            final /* synthetic */ LikeActionController f1127b;

            LikeActionController(LikeActionController likeActionController, LikeActionController likeActionController2) {
                this.f1127b = likeActionController;
                this.f1126a = likeActionController2;
            }

            public void m1436a(com.facebook.GraphRequestBatch graphRequestBatch) {
                this.f1127b.f1129b.f1189v = false;
                if (this.f1126a.c != null) {
                    this.f1127b.f1129b.m1476a(false);
                    return;
                }
                this.f1127b.f1129b.f1185r = Utility.m1099a(this.f1126a.f1162e, null);
                this.f1127b.f1129b.f1188u = true;
                this.f1127b.f1129b.m1516l().m856a("fb_like_control_did_like", null, this.f1127b.f1128a);
                this.f1127b.f1129b.m1499d(this.f1127b.f1128a);
            }
        }

        LikeActionController(LikeActionController likeActionController, Bundle bundle) {
            this.f1129b = likeActionController;
            this.f1128a = bundle;
        }

        public void m1438a() {
            if (Utility.m1126a(this.f1129b.f1186s)) {
                Bundle bundle = new Bundle();
                bundle.putString("com.facebook.platform.status.ERROR_DESCRIPTION", "Invalid Object Id");
                LikeActionController.m1486b(this.f1129b, "com.facebook.sdk.LikeActionController.DID_ERROR", bundle);
                return;
            }
            com.facebook.GraphRequestBatch graphRequestBatch = new com.facebook.GraphRequestBatch();
            LikeActionController likeActionController = new LikeActionController(this.f1129b, this.f1129b.f1186s, this.f1129b.f1179l);
            likeActionController.m1445a(graphRequestBatch);
            graphRequestBatch.m1333a(new LikeActionController(this, likeActionController));
            graphRequestBatch.m1343h();
        }
    }

    /* renamed from: com.facebook.share.internal.b.8 */
    class LikeActionController implements GraphRequestBatch {
        final /* synthetic */ LikeActionController f1130a;
        final /* synthetic */ Bundle f1131b;
        final /* synthetic */ LikeActionController f1132c;

        LikeActionController(LikeActionController likeActionController, LikeActionController likeActionController2, Bundle bundle) {
            this.f1132c = likeActionController;
            this.f1130a = likeActionController2;
            this.f1131b = bundle;
        }

        public void m1439a(com.facebook.GraphRequestBatch graphRequestBatch) {
            this.f1132c.f1189v = false;
            if (this.f1130a.c != null) {
                this.f1132c.m1476a(true);
                return;
            }
            this.f1132c.f1185r = null;
            this.f1132c.f1188u = false;
            this.f1132c.m1516l().m856a("fb_like_control_did_unlike", null, this.f1131b);
            this.f1132c.m1499d(this.f1131b);
        }
    }

    /* renamed from: com.facebook.share.internal.b.9 */
    class LikeActionController implements LikeActionController {
        final /* synthetic */ LikeActionController f1136a;

        /* renamed from: com.facebook.share.internal.b.9.1 */
        class LikeActionController implements GraphRequestBatch {
            final /* synthetic */ LikeActionController f1133a;
            final /* synthetic */ LikeActionController f1134b;
            final /* synthetic */ LikeActionController f1135c;

            LikeActionController(LikeActionController likeActionController, LikeActionController likeActionController2, LikeActionController likeActionController3) {
                this.f1135c = likeActionController;
                this.f1133a = likeActionController2;
                this.f1134b = likeActionController3;
            }

            public void m1440a(com.facebook.GraphRequestBatch graphRequestBatch) {
                if (this.f1133a.c == null && this.f1134b.c == null) {
                    this.f1135c.f1136a.m1477a(this.f1133a.f1153e, this.f1134b.f1146e, this.f1134b.f1147f, this.f1134b.f1148g, this.f1134b.f1149h, this.f1133a.f1154f);
                    return;
                }
                Logger.m1000a(LoggingBehavior.REQUESTS, LikeActionController.f1168a, "Unable to refresh like state for id: '%s'", this.f1135c.f1136a.f1178k);
            }
        }

        LikeActionController(LikeActionController likeActionController) {
            this.f1136a = likeActionController;
        }

        public void m1441a() {
            LikeActionController likeActionController = new LikeActionController(this.f1136a, this.f1136a.f1186s, this.f1136a.f1179l);
            LikeActionController likeActionController2 = new LikeActionController(this.f1136a, this.f1136a.f1186s, this.f1136a.f1179l);
            com.facebook.GraphRequestBatch graphRequestBatch = new com.facebook.GraphRequestBatch();
            likeActionController.m1445a(graphRequestBatch);
            likeActionController2.m1445a(graphRequestBatch);
            graphRequestBatch.m1333a(new LikeActionController(this, likeActionController, likeActionController2));
            graphRequestBatch.m1343h();
        }
    }

    /* renamed from: com.facebook.share.internal.b.a */
    private abstract class LikeActionController {
        protected String f1138a;
        protected LikeView f1139b;
        FacebookRequestError f1140c;
        final /* synthetic */ LikeActionController f1141d;
        private GraphRequest f1142e;

        /* renamed from: com.facebook.share.internal.b.a.1 */
        class LikeActionController implements C0153b {
            final /* synthetic */ LikeActionController f1137a;

            LikeActionController(LikeActionController likeActionController) {
                this.f1137a = likeActionController;
            }

            public void m1442a(GraphResponse graphResponse) {
                this.f1137a.f1140c = graphResponse.m1352a();
                if (this.f1137a.f1140c != null) {
                    this.f1137a.m1444a(this.f1137a.f1140c);
                } else {
                    this.f1137a.m1446a(graphResponse);
                }
            }
        }

        protected abstract void m1446a(GraphResponse graphResponse);

        protected LikeActionController(LikeActionController likeActionController, String str, LikeView likeView) {
            this.f1141d = likeActionController;
            this.f1138a = str;
            this.f1139b = likeView;
        }

        void m1445a(com.facebook.GraphRequestBatch graphRequestBatch) {
            graphRequestBatch.m1334a(this.f1142e);
        }

        protected void m1443a(GraphRequest graphRequest) {
            this.f1142e = graphRequest;
            graphRequest.m775a("v2.3");
            graphRequest.m772a(new LikeActionController(this));
        }

        protected void m1444a(FacebookRequestError facebookRequestError) {
            Logger.m1000a(LoggingBehavior.REQUESTS, LikeActionController.f1168a, "Error running request for object '%s' with type '%s' : %s", this.f1138a, this.f1139b, facebookRequestError);
        }
    }

    /* renamed from: com.facebook.share.internal.b.b */
    private static class LikeActionController implements Runnable {
        private String f1143a;
        private LikeView f1144b;
        private LikeActionController f1145c;

        LikeActionController(String str, LikeView likeView, LikeActionController likeActionController) {
            this.f1143a = str;
            this.f1144b = likeView;
            this.f1145c = likeActionController;
        }

        public void run() {
            LikeActionController.m1495c(this.f1143a, this.f1144b, this.f1145c);
        }
    }

    /* renamed from: com.facebook.share.internal.b.d */
    private class LikeActionController extends LikeActionController {
        String f1146e;
        String f1147f;
        String f1148g;
        String f1149h;
        final /* synthetic */ LikeActionController f1150i;

        LikeActionController(LikeActionController likeActionController, String str, LikeView likeView) {
            this.f1150i = likeActionController;
            super(likeActionController, str, likeView);
            this.f1146e = this.f1150i.f1181n;
            this.f1147f = this.f1150i.f1182o;
            this.f1148g = this.f1150i.f1183p;
            this.f1149h = this.f1150i.f1184q;
            Bundle bundle = new Bundle();
            bundle.putString("fields", "engagement.fields(count_string_with_like,count_string_without_like,social_sentence_with_like,social_sentence_without_like)");
            m1443a(new GraphRequest(AccessToken.m690a(), str, bundle, HttpMethod.GET));
        }

        protected void m1448a(GraphResponse graphResponse) {
            JSONObject b = Utility.m1131b(graphResponse.m1353b(), "engagement");
            if (b != null) {
                this.f1146e = b.optString("count_string_with_like", this.f1146e);
                this.f1147f = b.optString("count_string_without_like", this.f1147f);
                this.f1148g = b.optString("social_sentence_with_like", this.f1148g);
                this.f1149h = b.optString("social_sentence_without_like", this.f1149h);
            }
        }

        protected void m1447a(FacebookRequestError facebookRequestError) {
            Logger.m1000a(LoggingBehavior.REQUESTS, LikeActionController.f1168a, "Error fetching engagement for object '%s' with type '%s' : %s", this.a, this.b, facebookRequestError);
            this.f1150i.m1472a("get_engagement", facebookRequestError);
        }
    }

    /* renamed from: com.facebook.share.internal.b.e */
    private class LikeActionController extends LikeActionController {
        String f1151e;
        final /* synthetic */ LikeActionController f1152f;

        LikeActionController(LikeActionController likeActionController, String str, LikeView likeView) {
            this.f1152f = likeActionController;
            super(likeActionController, str, likeView);
            Bundle bundle = new Bundle();
            bundle.putString("fields", "og_object.fields(id)");
            bundle.putString("ids", str);
            m1443a(new GraphRequest(AccessToken.m690a(), Trace.NULL, bundle, HttpMethod.GET));
        }

        protected void m1449a(FacebookRequestError facebookRequestError) {
            if (facebookRequestError.m1193d().contains("og_object")) {
                this.c = null;
                return;
            }
            Logger.m1000a(LoggingBehavior.REQUESTS, LikeActionController.f1168a, "Error getting the FB id for object '%s' with type '%s' : %s", this.a, this.b, facebookRequestError);
        }

        protected void m1450a(GraphResponse graphResponse) {
            JSONObject b = Utility.m1131b(graphResponse.m1353b(), this.a);
            if (b != null) {
                b = b.optJSONObject("og_object");
                if (b != null) {
                    this.f1151e = b.optString("id");
                }
            }
        }
    }

    /* renamed from: com.facebook.share.internal.b.f */
    private class LikeActionController extends LikeActionController {
        boolean f1153e;
        String f1154f;
        final /* synthetic */ LikeActionController f1155g;

        LikeActionController(LikeActionController likeActionController, String str, LikeView likeView) {
            this.f1155g = likeActionController;
            super(likeActionController, str, likeView);
            this.f1153e = this.f1155g.f1180m;
            Bundle bundle = new Bundle();
            bundle.putString("fields", "id,application");
            bundle.putString("object", str);
            m1443a(new GraphRequest(AccessToken.m690a(), "me/og.likes", bundle, HttpMethod.GET));
        }

        protected void m1452a(GraphResponse graphResponse) {
            JSONArray c = Utility.m1139c(graphResponse.m1353b(), "data");
            if (c != null) {
                for (int i = 0; i < c.length(); i++) {
                    JSONObject optJSONObject = c.optJSONObject(i);
                    if (optJSONObject != null) {
                        this.f1153e = true;
                        JSONObject optJSONObject2 = optJSONObject.optJSONObject("application");
                        AccessToken a = AccessToken.m690a();
                        if (!(optJSONObject2 == null || a == null || !Utility.m1125a(a.m703g(), optJSONObject2.optString("id")))) {
                            this.f1154f = optJSONObject.optString("id");
                        }
                    }
                }
            }
        }

        protected void m1451a(FacebookRequestError facebookRequestError) {
            Logger.m1000a(LoggingBehavior.REQUESTS, LikeActionController.f1168a, "Error fetching like status for object '%s' with type '%s' : %s", this.a, this.b, facebookRequestError);
            this.f1155g.m1472a("get_og_object_like", facebookRequestError);
        }
    }

    /* renamed from: com.facebook.share.internal.b.g */
    private class LikeActionController extends LikeActionController {
        String f1156e;
        boolean f1157f;
        final /* synthetic */ LikeActionController f1158g;

        LikeActionController(LikeActionController likeActionController, String str, LikeView likeView) {
            this.f1158g = likeActionController;
            super(likeActionController, str, likeView);
            Bundle bundle = new Bundle();
            bundle.putString("fields", "id");
            bundle.putString("ids", str);
            m1443a(new GraphRequest(AccessToken.m690a(), Trace.NULL, bundle, HttpMethod.GET));
        }

        protected void m1454a(GraphResponse graphResponse) {
            JSONObject b = Utility.m1131b(graphResponse.m1353b(), this.a);
            if (b != null) {
                this.f1156e = b.optString("id");
                this.f1157f = !Utility.m1126a(this.f1156e);
            }
        }

        protected void m1453a(FacebookRequestError facebookRequestError) {
            Logger.m1000a(LoggingBehavior.REQUESTS, LikeActionController.f1168a, "Error getting the FB id for object '%s' with type '%s' : %s", this.a, this.b, facebookRequestError);
        }
    }

    /* renamed from: com.facebook.share.internal.b.h */
    private static class LikeActionController implements Runnable {
        private static ArrayList<String> f1159a;
        private String f1160b;
        private boolean f1161c;

        static {
            f1159a = new ArrayList();
        }

        LikeActionController(String str, boolean z) {
            this.f1160b = str;
            this.f1161c = z;
        }

        public void run() {
            if (this.f1160b != null) {
                f1159a.remove(this.f1160b);
                f1159a.add(0, this.f1160b);
            }
            if (this.f1161c && f1159a.size() >= AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS) {
                while (64 < f1159a.size()) {
                    LikeActionController.f1170c.remove((String) f1159a.remove(f1159a.size() - 1));
                }
            }
        }
    }

    /* renamed from: com.facebook.share.internal.b.i */
    private class LikeActionController extends LikeActionController {
        String f1162e;
        final /* synthetic */ LikeActionController f1163f;

        LikeActionController(LikeActionController likeActionController, String str, LikeView likeView) {
            this.f1163f = likeActionController;
            super(likeActionController, str, likeView);
            Bundle bundle = new Bundle();
            bundle.putString("object", str);
            m1443a(new GraphRequest(AccessToken.m690a(), "me/og.likes", bundle, HttpMethod.POST));
        }

        protected void m1456a(GraphResponse graphResponse) {
            this.f1162e = Utility.m1102a(graphResponse.m1353b(), "id");
        }

        protected void m1455a(FacebookRequestError facebookRequestError) {
            if (facebookRequestError.m1191b() == 3501) {
                this.c = null;
                return;
            }
            Logger.m1000a(LoggingBehavior.REQUESTS, LikeActionController.f1168a, "Error liking object '%s' with type '%s' : %s", this.a, this.b, facebookRequestError);
            this.f1163f.m1472a("publish_like", facebookRequestError);
        }
    }

    /* renamed from: com.facebook.share.internal.b.j */
    private class LikeActionController extends LikeActionController {
        final /* synthetic */ LikeActionController f1164e;
        private String f1165f;

        LikeActionController(LikeActionController likeActionController, String str) {
            this.f1164e = likeActionController;
            super(likeActionController, null, null);
            this.f1165f = str;
            m1443a(new GraphRequest(AccessToken.m690a(), str, null, HttpMethod.DELETE));
        }

        protected void m1458a(GraphResponse graphResponse) {
        }

        protected void m1457a(FacebookRequestError facebookRequestError) {
            Logger.m1000a(LoggingBehavior.REQUESTS, LikeActionController.f1168a, "Error unliking object with unlike token '%s' : %s", this.f1165f, facebookRequestError);
            this.f1164e.m1472a("publish_unlike", facebookRequestError);
        }
    }

    /* renamed from: com.facebook.share.internal.b.l */
    private static class LikeActionController implements Runnable {
        private String f1166a;
        private String f1167b;

        LikeActionController(String str, String str2) {
            this.f1166a = str;
            this.f1167b = str2;
        }

        public void run() {
            LikeActionController.m1489b(this.f1166a, this.f1167b);
        }
    }

    static {
        f1168a = LikeActionController.class.getSimpleName();
        f1170c = new ConcurrentHashMap();
        f1171d = new WorkQueue(1);
        f1172e = new WorkQueue(1);
    }

    public static void m1473a(String str, LikeView likeView, LikeActionController likeActionController) {
        if (!f1175h) {
            LikeActionController.m1513j();
        }
        LikeActionController a = LikeActionController.m1460a(str);
        if (a != null) {
            LikeActionController.m1466a(a, likeView, likeActionController);
        } else {
            f1172e.m1167a(new LikeActionController(str, likeView, likeActionController));
        }
    }

    private static boolean m1478a(LikeView likeView) {
        if (LikeDialog.m1543e() || LikeDialog.m1544f()) {
            return true;
        }
        if (likeView == LikeView.PAGE) {
            return false;
        }
        AccessToken a = AccessToken.m690a();
        if (a == null || a.m699c() == null || !a.m699c().contains("publish_actions")) {
            return false;
        }
        return true;
    }

    private static void m1466a(LikeActionController likeActionController, LikeView likeView, LikeActionController likeActionController2) {
        FacebookException facebookException;
        LikeActionController likeActionController3 = null;
        LikeView a = ShareInternalUtility.m1615a(likeView, likeActionController.f1179l);
        if (a == null) {
            facebookException = new FacebookException("Object with id:\"%s\" is already marked as type:\"%s\". Cannot change the type to:\"%s\"", likeActionController.f1178k, likeActionController.f1179l.toString(), likeView.toString());
        } else {
            likeActionController.f1179l = a;
            facebookException = null;
            likeActionController3 = likeActionController;
        }
        LikeActionController.m1462a(likeActionController2, likeActionController3, facebookException);
    }

    private static void m1495c(String str, LikeView likeView, LikeActionController likeActionController) {
        LikeActionController a = LikeActionController.m1460a(str);
        if (a != null) {
            LikeActionController.m1466a(a, likeView, likeActionController);
            return;
        }
        a = LikeActionController.m1481b(str);
        if (a == null) {
            a = new LikeActionController(str, likeView);
            LikeActionController.m1517l(a);
        }
        LikeActionController.m1474a(str, a);
        f1173f.post(new LikeActionController(a));
        LikeActionController.m1462a(likeActionController, a, null);
    }

    private static synchronized void m1513j() {
        synchronized (LikeActionController.class) {
            if (!f1175h) {
                f1173f = new Handler(Looper.getMainLooper());
                f1176i = FacebookSdk.m1208f().getSharedPreferences("com.facebook.LikeActionController.CONTROLLER_STORE_KEY", 0).getInt("OBJECT_SUFFIX", 1);
                f1169b = new FileLruCache(f1168a, new FileLruCache.FileLruCache());
                LikeActionController.m1514k();
                com.facebook.p022b.CallbackManagerImpl.m909a(CallbackManagerImpl.Like.m908a(), new LikeActionController());
                f1175h = true;
            }
        }
    }

    private static void m1462a(LikeActionController likeActionController, LikeActionController likeActionController2, FacebookException facebookException) {
        if (likeActionController != null) {
            f1173f.post(new LikeActionController(likeActionController, likeActionController2, facebookException));
        }
    }

    private static void m1514k() {
        f1177j = new LikeActionController();
    }

    private static void m1474a(String str, LikeActionController likeActionController) {
        String d = LikeActionController.m1498d(str);
        f1171d.m1167a(new LikeActionController(d, true));
        f1170c.put(d, likeActionController);
    }

    private static LikeActionController m1460a(String str) {
        String d = LikeActionController.m1498d(str);
        LikeActionController likeActionController = (LikeActionController) f1170c.get(d);
        if (likeActionController != null) {
            f1171d.m1167a(new LikeActionController(d, false));
        }
        return likeActionController;
    }

    private static void m1517l(LikeActionController likeActionController) {
        String m = LikeActionController.m1518m(likeActionController);
        String d = LikeActionController.m1498d(likeActionController.f1178k);
        if (!Utility.m1126a(m) && !Utility.m1126a(d)) {
            f1172e.m1167a(new LikeActionController(d, m));
        }
    }

    private static void m1489b(String str, String str2) {
        Closeable closeable = null;
        try {
            closeable = f1169b.m996b(str);
            closeable.write(str2.getBytes());
            if (closeable != null) {
                Utility.m1116a(closeable);
            }
        } catch (Throwable e) {
            Log.e(f1168a, "Unable to serialize controller to disk", e);
            if (closeable != null) {
                Utility.m1116a(closeable);
            }
        } catch (Throwable th) {
            if (closeable != null) {
                Utility.m1116a(closeable);
            }
        }
    }

    private static LikeActionController m1481b(String str) {
        Closeable a;
        Throwable e;
        Throwable th;
        LikeActionController likeActionController = null;
        try {
            a = f1169b.m993a(LikeActionController.m1498d(str));
            if (a != null) {
                try {
                    String a2 = Utility.m1098a((InputStream) a);
                    if (!Utility.m1126a(a2)) {
                        likeActionController = LikeActionController.m1491c(a2);
                    }
                } catch (IOException e2) {
                    e = e2;
                    try {
                        Log.e(f1168a, "Unable to deserialize controller from disk", e);
                        if (a != null) {
                            Utility.m1116a(a);
                        }
                        return likeActionController;
                    } catch (Throwable th2) {
                        th = th2;
                        if (a != null) {
                            Utility.m1116a(a);
                        }
                        throw th;
                    }
                }
            }
            if (a != null) {
                Utility.m1116a(a);
            }
        } catch (IOException e3) {
            e = e3;
            a = null;
            Log.e(f1168a, "Unable to deserialize controller from disk", e);
            if (a != null) {
                Utility.m1116a(a);
            }
            return likeActionController;
        } catch (Throwable e4) {
            a = null;
            th = e4;
            if (a != null) {
                Utility.m1116a(a);
            }
            throw th;
        }
        return likeActionController;
    }

    private static LikeActionController m1491c(String str) {
        LikeActionController likeActionController;
        try {
            JSONObject init = JSONObjectInstrumentation.init(str);
            if (init.optInt("com.facebook.share.internal.LikeActionController.version", -1) != 3) {
                return null;
            }
            likeActionController = new LikeActionController(init.getString("object_id"), LikeView.m1368a(init.optInt("object_type", LikeView.UNKNOWN.m1369a())));
            likeActionController.f1181n = init.optString("like_count_string_with_like", null);
            likeActionController.f1182o = init.optString("like_count_string_without_like", null);
            likeActionController.f1183p = init.optString("social_sentence_with_like", null);
            likeActionController.f1184q = init.optString("social_sentence_without_like", null);
            likeActionController.f1180m = init.optBoolean("is_object_liked");
            likeActionController.f1185r = init.optString("unlike_token", null);
            init = init.optJSONObject("facebook_dialog_analytics_bundle");
            if (init != null) {
                likeActionController.f1190w = BundleJSONConverter.m906a(init);
            }
            return likeActionController;
        } catch (Throwable e) {
            Log.e(f1168a, "Unable to deserialize controller from JSON", e);
            likeActionController = null;
        }
    }

    private static String m1518m(LikeActionController likeActionController) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("com.facebook.share.internal.LikeActionController.version", 3);
            jSONObject.put("object_id", likeActionController.f1178k);
            jSONObject.put("object_type", likeActionController.f1179l.m1369a());
            jSONObject.put("like_count_string_with_like", likeActionController.f1181n);
            jSONObject.put("like_count_string_without_like", likeActionController.f1182o);
            jSONObject.put("social_sentence_with_like", likeActionController.f1183p);
            jSONObject.put("social_sentence_without_like", likeActionController.f1184q);
            jSONObject.put("is_object_liked", likeActionController.f1180m);
            jSONObject.put("unlike_token", likeActionController.f1185r);
            if (likeActionController.f1190w != null) {
                JSONObject a = BundleJSONConverter.m907a(likeActionController.f1190w);
                if (a != null) {
                    jSONObject.put("facebook_dialog_analytics_bundle", a);
                }
            }
            if (jSONObject instanceof JSONObject) {
                return JSONObjectInstrumentation.toString(jSONObject);
            }
            return jSONObject.toString();
        } catch (Throwable e) {
            Log.e(f1168a, "Unable to serialize controller to JSON", e);
            return null;
        }
    }

    private static String m1498d(String str) {
        String str2 = null;
        AccessToken a = AccessToken.m690a();
        if (a != null) {
            str2 = a.m698b();
        }
        if (str2 != null) {
            str2 = Utility.m1129b(str2);
        }
        return String.format(Locale.ROOT, "%s|%s|com.fb.sdk.like|%d", new Object[]{str, Utility.m1099a(str2, Trace.NULL), Integer.valueOf(f1176i)});
    }

    private static void m1500d(LikeActionController likeActionController, String str) {
        LikeActionController.m1486b(likeActionController, str, null);
    }

    private static void m1486b(LikeActionController likeActionController, String str, Bundle bundle) {
        Intent intent = new Intent(str);
        if (likeActionController != null) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            bundle.putString("com.facebook.sdk.LikeActionController.OBJECT_ID", likeActionController.m1522a());
        }
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        LocalBroadcastManager.getInstance(FacebookSdk.m1208f()).sendBroadcast(intent);
    }

    private LikeActionController(String str, LikeView likeView) {
        this.f1178k = str;
        this.f1179l = likeView;
    }

    public String m1522a() {
        return this.f1178k;
    }

    public String m1524b() {
        return this.f1180m ? this.f1181n : this.f1182o;
    }

    public String m1525c() {
        return this.f1180m ? this.f1183p : this.f1184q;
    }

    public boolean m1526d() {
        return this.f1180m;
    }

    public boolean m1527e() {
        return !LikeActionController.m1478a(this.f1179l);
    }

    public void m1523a(Activity activity, Fragment fragment, Bundle bundle) {
        boolean z = true;
        m1516l().m856a("fb_like_control_did_tap", null, bundle);
        boolean z2 = !this.f1180m;
        if (m1519m()) {
            m1490b(z2);
            if (this.f1189v) {
                m1516l().m856a("fb_like_control_did_undo_quickly", null, bundle);
                return;
            } else if (!m1480a(z2, bundle)) {
                if (z2) {
                    z = false;
                }
                m1490b(z);
                m1484b(activity, fragment, bundle);
                return;
            } else {
                return;
            }
        }
        m1484b(activity, fragment, bundle);
    }

    private AppEventsLogger m1516l() {
        if (this.f1191x == null) {
            this.f1191x = AppEventsLogger.m830a(FacebookSdk.m1208f());
        }
        return this.f1191x;
    }

    private boolean m1480a(boolean z, Bundle bundle) {
        if (m1519m()) {
            if (z) {
                m1485b(bundle);
                return true;
            } else if (!Utility.m1126a(this.f1185r)) {
                m1494c(bundle);
                return true;
            }
        }
        return false;
    }

    private void m1476a(boolean z) {
        m1490b(z);
        Bundle bundle = new Bundle();
        bundle.putString("com.facebook.platform.status.ERROR_DESCRIPTION", "Unable to publish the like/unlike action");
        LikeActionController.m1486b(this, "com.facebook.sdk.LikeActionController.DID_ERROR", bundle);
    }

    private void m1490b(boolean z) {
        m1477a(z, this.f1181n, this.f1182o, this.f1183p, this.f1184q, this.f1185r);
    }

    private void m1477a(boolean z, String str, String str2, String str3, String str4, String str5) {
        Object obj;
        Object a = Utility.m1099a(str, null);
        Object a2 = Utility.m1099a(str2, null);
        Object a3 = Utility.m1099a(str3, null);
        Object a4 = Utility.m1099a(str4, null);
        Object a5 = Utility.m1099a(str5, null);
        if (z == this.f1180m && Utility.m1125a(a, this.f1181n) && Utility.m1125a(a2, this.f1182o) && Utility.m1125a(a3, this.f1183p) && Utility.m1125a(a4, this.f1184q) && Utility.m1125a(a5, this.f1185r)) {
            obj = null;
        } else {
            obj = 1;
        }
        if (obj != null) {
            this.f1180m = z;
            this.f1181n = a;
            this.f1182o = a2;
            this.f1183p = a3;
            this.f1184q = a4;
            this.f1185r = a5;
            LikeActionController.m1517l(this);
            LikeActionController.m1500d(this, "com.facebook.sdk.LikeActionController.UPDATED");
        }
    }

    private void m1484b(Activity activity, Fragment fragment, Bundle bundle) {
        String str;
        if (LikeDialog.m1543e()) {
            str = "fb_like_control_did_present_dialog";
        } else if (LikeDialog.m1544f()) {
            str = "fb_like_control_did_present_fallback_dialog";
        } else {
            m1471a("present_dialog", bundle);
            Utility.m1134b(f1168a, "Cannot show the Like Dialog on this device.");
            str = null;
        }
        if (str != null) {
            LikeContent a = new C0180a().m1423a(this.f1178k).m1422a(this.f1179l).m1424a();
            if (fragment != null) {
                new LikeDialog(fragment).m933a(a);
            } else {
                new LikeDialog(activity).m933a(a);
            }
            m1461a(bundle);
            m1516l().m856a("fb_like_control_did_present_dialog", null, bundle);
        }
    }

    private void m1461a(Bundle bundle) {
        LikeActionController.m1503e(this.f1178k);
        this.f1190w = bundle;
        LikeActionController.m1517l(this);
    }

    private static void m1503e(String str) {
        f1174g = str;
        FacebookSdk.m1208f().getSharedPreferences("com.facebook.LikeActionController.CONTROLLER_STORE_KEY", 0).edit().putString("PENDING_CONTROLLER_KEY", f1174g).apply();
    }

    private boolean m1519m() {
        AccessToken a = AccessToken.m690a();
        return (this.f1187t || this.f1186s == null || a == null || a.m699c() == null || !a.m699c().contains("publish_actions")) ? false : true;
    }

    private void m1485b(Bundle bundle) {
        this.f1189v = true;
        m1463a(new LikeActionController(this, bundle));
    }

    private void m1494c(Bundle bundle) {
        this.f1189v = true;
        com.facebook.GraphRequestBatch graphRequestBatch = new com.facebook.GraphRequestBatch();
        LikeActionController likeActionController = new LikeActionController(this, this.f1185r);
        likeActionController.m1445a(graphRequestBatch);
        graphRequestBatch.m1333a(new LikeActionController(this, likeActionController, bundle));
        graphRequestBatch.m1343h();
    }

    private void m1520n() {
        if (AccessToken.m690a() == null) {
            m1521o();
        } else {
            m1463a(new LikeActionController(this));
        }
    }

    private void m1521o() {
        LikeStatusClient likeStatusClient = new LikeStatusClient(FacebookSdk.m1208f(), FacebookSdk.m1210h(), this.f1178k);
        if (likeStatusClient.m1065a()) {
            likeStatusClient.m1064a(new LikeActionController(this));
        }
    }

    private void m1499d(Bundle bundle) {
        if (this.f1180m != this.f1188u && !m1480a(this.f1180m, bundle)) {
            m1476a(!this.f1180m);
        }
    }

    private void m1463a(LikeActionController likeActionController) {
        if (Utility.m1126a(this.f1186s)) {
            LikeActionController likeActionController2 = new LikeActionController(this, this.f1178k, this.f1179l);
            LikeActionController likeActionController3 = new LikeActionController(this, this.f1178k, this.f1179l);
            com.facebook.GraphRequestBatch graphRequestBatch = new com.facebook.GraphRequestBatch();
            likeActionController2.m1445a(graphRequestBatch);
            likeActionController3.m1445a(graphRequestBatch);
            graphRequestBatch.m1333a(new LikeActionController(this, likeActionController2, likeActionController3, likeActionController));
            graphRequestBatch.m1343h();
        } else if (likeActionController != null) {
            likeActionController.m1437a();
        }
    }

    private void m1471a(String str, Bundle bundle) {
        Bundle bundle2 = new Bundle(bundle);
        bundle2.putString("object_id", this.f1178k);
        bundle2.putString("object_type", this.f1179l.toString());
        bundle2.putString("current_action", str);
        m1516l().m856a("fb_like_control_error", null, bundle2);
    }

    private void m1472a(String str, FacebookRequestError facebookRequestError) {
        Bundle bundle = new Bundle();
        if (facebookRequestError != null) {
            JSONObject e = facebookRequestError.m1194e();
            if (e != null) {
                bundle.putString("error", !(e instanceof JSONObject) ? e.toString() : JSONObjectInstrumentation.toString(e));
            }
        }
        m1471a(str, bundle);
    }
}
