package com.facebook.share.internal;

import android.os.Bundle;
import com.facebook.FacebookException;
import com.facebook.p022b.Utility;
import com.facebook.p022b.Validate;
import com.facebook.share.model.ShareContent;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.ShareOpenGraphContent;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.model.ShareVideoContent;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.facebook.share.internal.a */
public class LegacyNativeDialogParameters {
    public static Bundle m1432a(UUID uuid, ShareContent shareContent, boolean z) {
        Validate.m1146a((Object) shareContent, "shareContent");
        Validate.m1146a((Object) uuid, "callId");
        if (shareContent instanceof ShareLinkContent) {
            return LegacyNativeDialogParameters.m1428a((ShareLinkContent) shareContent, z);
        }
        if (shareContent instanceof SharePhotoContent) {
            SharePhotoContent sharePhotoContent = (SharePhotoContent) shareContent;
            return LegacyNativeDialogParameters.m1430a(sharePhotoContent, ShareInternalUtility.m1616a(sharePhotoContent, uuid), z);
        } else if (shareContent instanceof ShareVideoContent) {
            return LegacyNativeDialogParameters.m1431a((ShareVideoContent) shareContent, z);
        } else {
            if (!(shareContent instanceof ShareOpenGraphContent)) {
                return null;
            }
            ShareOpenGraphContent shareOpenGraphContent = (ShareOpenGraphContent) shareContent;
            try {
                return LegacyNativeDialogParameters.m1429a(shareOpenGraphContent, ShareInternalUtility.m1619a(uuid, shareOpenGraphContent.m1669e()), z);
            } catch (JSONException e) {
                throw new FacebookException("Unable to create a JSON Object from the provided ShareOpenGraphContent: " + e.getMessage());
            }
        }
    }

    private static Bundle m1428a(ShareLinkContent shareLinkContent, boolean z) {
        Bundle a = LegacyNativeDialogParameters.m1427a((ShareContent) shareLinkContent, z);
        Utility.m1114a(a, "com.facebook.platform.extra.TITLE", shareLinkContent.m1650f());
        Utility.m1114a(a, "com.facebook.platform.extra.DESCRIPTION", shareLinkContent.m1649e());
        Utility.m1113a(a, "com.facebook.platform.extra.IMAGE", shareLinkContent.m1651g());
        return a;
    }

    private static Bundle m1430a(SharePhotoContent sharePhotoContent, List<String> list, boolean z) {
        Bundle a = LegacyNativeDialogParameters.m1427a((ShareContent) sharePhotoContent, z);
        a.putStringArrayList("com.facebook.platform.extra.PHOTOS", new ArrayList(list));
        return a;
    }

    private static Bundle m1431a(ShareVideoContent shareVideoContent, boolean z) {
        return null;
    }

    private static Bundle m1429a(ShareOpenGraphContent shareOpenGraphContent, JSONObject jSONObject, boolean z) {
        Bundle a = LegacyNativeDialogParameters.m1427a((ShareContent) shareOpenGraphContent, z);
        Utility.m1114a(a, "com.facebook.platform.extra.PREVIEW_PROPERTY_NAME", shareOpenGraphContent.m1670f());
        Utility.m1114a(a, "com.facebook.platform.extra.ACTION_TYPE", shareOpenGraphContent.m1669e().m1666a());
        Utility.m1114a(a, "com.facebook.platform.extra.ACTION", !(jSONObject instanceof JSONObject) ? jSONObject.toString() : JSONObjectInstrumentation.toString(jSONObject));
        return a;
    }

    private static Bundle m1427a(ShareContent shareContent, boolean z) {
        Bundle bundle = new Bundle();
        Utility.m1113a(bundle, "com.facebook.platform.extra.LINK", shareContent.m1636a());
        Utility.m1114a(bundle, "com.facebook.platform.extra.PLACE", shareContent.m1638c());
        Utility.m1114a(bundle, "com.facebook.platform.extra.REF", shareContent.m1639d());
        bundle.putBoolean("com.facebook.platform.extra.DATA_FAILURES_FATAL", z);
        Collection b = shareContent.m1637b();
        if (!Utility.m1127a(b)) {
            bundle.putStringArrayList("com.facebook.platform.extra.FRIENDS", new ArrayList(b));
        }
        return bundle;
    }
}
