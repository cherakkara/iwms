package com.facebook.share.internal;

import android.graphics.Bitmap;
import android.net.Uri;
import android.util.Pair;
import com.facebook.FacebookException;
import com.facebook.p022b.CallbackManagerImpl.CallbackManagerImpl;
import com.facebook.p022b.NativeAppCallAttachmentStore.NativeAppCallAttachmentStore;
import com.facebook.p022b.Utility.Utility;
import com.facebook.share.internal.OpenGraphJSONUtility.OpenGraphJSONUtility;
import com.facebook.share.model.ShareOpenGraphAction;
import com.facebook.share.model.ShareOpenGraphContent;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.p024a.LikeView.LikeView;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.facebook.share.internal.m */
public final class ShareInternalUtility {

    /* renamed from: com.facebook.share.internal.m.1 */
    static class ShareInternalUtility implements CallbackManagerImpl {
        final /* synthetic */ int f1226a;

        ShareInternalUtility(int i) {
            this.f1226a = i;
        }
    }

    /* renamed from: com.facebook.share.internal.m.2 */
    static class ShareInternalUtility implements Utility<SharePhoto, NativeAppCallAttachmentStore> {
        final /* synthetic */ UUID f1227a;

        ShareInternalUtility(UUID uuid) {
            this.f1227a = uuid;
        }

        public NativeAppCallAttachmentStore m1607a(SharePhoto sharePhoto) {
            return ShareInternalUtility.m1622b(this.f1227a, sharePhoto);
        }
    }

    /* renamed from: com.facebook.share.internal.m.3 */
    static class ShareInternalUtility implements Utility<NativeAppCallAttachmentStore, String> {
        ShareInternalUtility() {
        }

        public String m1610a(NativeAppCallAttachmentStore nativeAppCallAttachmentStore) {
            return nativeAppCallAttachmentStore.m1016a();
        }
    }

    /* renamed from: com.facebook.share.internal.m.4 */
    static class ShareInternalUtility implements OpenGraphJSONUtility {
        final /* synthetic */ UUID f1228a;
        final /* synthetic */ ArrayList f1229b;

        ShareInternalUtility(UUID uuid, ArrayList arrayList) {
            this.f1228a = uuid;
            this.f1229b = arrayList;
        }

        public JSONObject m1611a(SharePhoto sharePhoto) {
            NativeAppCallAttachmentStore a = ShareInternalUtility.m1622b(this.f1228a, sharePhoto);
            if (a == null) {
                return null;
            }
            this.f1229b.add(a);
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("url", a.m1016a());
                if (!sharePhoto.m1690c()) {
                    return jSONObject;
                }
                jSONObject.put("user_generated", true);
                return jSONObject;
            } catch (Throwable e) {
                throw new FacebookException("Unable to attach images", e);
            }
        }
    }

    /* renamed from: com.facebook.share.internal.m.5 */
    static class ShareInternalUtility implements OpenGraphJSONUtility {
        ShareInternalUtility() {
        }

        public JSONObject m1612a(SharePhoto sharePhoto) {
            Uri b = sharePhoto.m1689b();
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("url", b.toString());
                return jSONObject;
            } catch (Throwable e) {
                throw new FacebookException("Unable to attach images", e);
            }
        }
    }

    public static void m1621a(int i) {
        com.facebook.p022b.CallbackManagerImpl.m909a(i, new ShareInternalUtility(i));
    }

    public static List<String> m1616a(SharePhotoContent sharePhotoContent, UUID uuid) {
        if (sharePhotoContent != null) {
            List e = sharePhotoContent.m1693e();
            if (e != null) {
                List a = com.facebook.p022b.Utility.m1108a(e, new ShareInternalUtility(uuid));
                List<String> a2 = com.facebook.p022b.Utility.m1108a(a, new ShareInternalUtility());
                com.facebook.p022b.NativeAppCallAttachmentStore.m1025a(a);
                return a2;
            }
        }
        return null;
    }

    public static JSONObject m1619a(UUID uuid, ShareOpenGraphAction shareOpenGraphAction) throws JSONException {
        Collection arrayList = new ArrayList();
        JSONObject a = OpenGraphJSONUtility.m1563a(shareOpenGraphAction, new ShareInternalUtility(uuid, arrayList));
        com.facebook.p022b.NativeAppCallAttachmentStore.m1025a(arrayList);
        return a;
    }

    public static JSONObject m1618a(ShareOpenGraphContent shareOpenGraphContent) throws JSONException {
        return OpenGraphJSONUtility.m1563a(shareOpenGraphContent.m1669e(), new ShareInternalUtility());
    }

    public static JSONArray m1617a(JSONArray jSONArray, boolean z) throws JSONException {
        JSONArray jSONArray2 = new JSONArray();
        for (int i = 0; i < jSONArray.length(); i++) {
            Object obj = jSONArray.get(i);
            if (obj instanceof JSONArray) {
                obj = ShareInternalUtility.m1617a((JSONArray) obj, z);
            } else if (obj instanceof JSONObject) {
                obj = ShareInternalUtility.m1620a((JSONObject) obj, z);
            }
            jSONArray2.put(obj);
        }
        return jSONArray2;
    }

    public static JSONObject m1620a(JSONObject jSONObject, boolean z) {
        if (jSONObject == null) {
            return null;
        }
        try {
            JSONObject jSONObject2 = new JSONObject();
            JSONObject jSONObject3 = new JSONObject();
            JSONArray names = jSONObject.names();
            for (int i = 0; i < names.length(); i++) {
                Object a;
                String string = names.getString(i);
                Object obj = jSONObject.get(string);
                if (obj instanceof JSONObject) {
                    a = ShareInternalUtility.m1620a((JSONObject) obj, true);
                } else if (obj instanceof JSONArray) {
                    JSONArray a2 = ShareInternalUtility.m1617a((JSONArray) obj, true);
                } else {
                    a = obj;
                }
                Pair a3 = ShareInternalUtility.m1613a(string);
                String str = (String) a3.first;
                String str2 = (String) a3.second;
                if (!z) {
                    jSONObject2.put(str2, a);
                } else if (str != null && str.equals("fbsdk")) {
                    jSONObject2.put(string, a);
                } else if (str == null || str.equals("og")) {
                    jSONObject2.put(str2, a);
                } else {
                    jSONObject3.put(str2, a);
                }
            }
            if (jSONObject3.length() > 0) {
                jSONObject2.put("data", jSONObject3);
            }
            return jSONObject2;
        } catch (JSONException e) {
            throw new FacebookException("Failed to create json object from share content");
        }
    }

    public static Pair<String, String> m1613a(String str) {
        Object obj = null;
        int indexOf = str.indexOf(58);
        if (indexOf != -1 && str.length() > indexOf + 1) {
            obj = str.substring(0, indexOf);
            str = str.substring(indexOf + 1);
        }
        return new Pair(obj, str);
    }

    private static NativeAppCallAttachmentStore m1622b(UUID uuid, SharePhoto sharePhoto) {
        Bitmap a = sharePhoto.m1688a();
        Uri b = sharePhoto.m1689b();
        if (a != null) {
            return com.facebook.p022b.NativeAppCallAttachmentStore.m1017a(uuid, a);
        }
        if (b != null) {
            return com.facebook.p022b.NativeAppCallAttachmentStore.m1018a(uuid, b);
        }
        return null;
    }

    public static LikeView m1615a(LikeView likeView, LikeView likeView2) {
        if (likeView == likeView2) {
            return likeView;
        }
        if (likeView == LikeView.UNKNOWN) {
            return likeView2;
        }
        return likeView2 != LikeView.UNKNOWN ? null : likeView;
    }
}
