package com.facebook.share.internal;

import android.os.Bundle;
import com.facebook.FacebookException;
import com.facebook.p022b.Utility;
import com.facebook.p022b.Validate;
import com.facebook.share.model.ShareContent;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.ShareOpenGraphContent;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.model.ShareVideo;
import com.facebook.share.model.ShareVideoContent;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.facebook.share.internal.h */
public class NativeDialogParameters {
    public static Bundle m1557a(UUID uuid, ShareContent shareContent, boolean z) {
        Validate.m1146a((Object) shareContent, "shareContent");
        Validate.m1146a((Object) uuid, "callId");
        if (shareContent instanceof ShareLinkContent) {
            return NativeDialogParameters.m1553a((ShareLinkContent) shareContent, z);
        }
        if (shareContent instanceof SharePhotoContent) {
            SharePhotoContent sharePhotoContent = (SharePhotoContent) shareContent;
            return NativeDialogParameters.m1555a(sharePhotoContent, ShareInternalUtility.m1616a(sharePhotoContent, uuid), z);
        } else if (shareContent instanceof ShareVideoContent) {
            return NativeDialogParameters.m1556a((ShareVideoContent) shareContent, z);
        } else {
            if (!(shareContent instanceof ShareOpenGraphContent)) {
                return null;
            }
            ShareOpenGraphContent shareOpenGraphContent = (ShareOpenGraphContent) shareContent;
            try {
                return NativeDialogParameters.m1554a(shareOpenGraphContent, ShareInternalUtility.m1620a(ShareInternalUtility.m1619a(uuid, shareOpenGraphContent.m1669e()), false), z);
            } catch (JSONException e) {
                throw new FacebookException("Unable to create a JSON Object from the provided ShareOpenGraphContent: " + e.getMessage());
            }
        }
    }

    private static Bundle m1553a(ShareLinkContent shareLinkContent, boolean z) {
        Bundle a = NativeDialogParameters.m1552a((ShareContent) shareLinkContent, z);
        Utility.m1114a(a, "TITLE", shareLinkContent.m1650f());
        Utility.m1114a(a, "DESCRIPTION", shareLinkContent.m1649e());
        Utility.m1113a(a, "IMAGE", shareLinkContent.m1651g());
        return a;
    }

    private static Bundle m1555a(SharePhotoContent sharePhotoContent, List<String> list, boolean z) {
        Bundle a = NativeDialogParameters.m1552a((ShareContent) sharePhotoContent, z);
        a.putStringArrayList("PHOTOS", new ArrayList(list));
        return a;
    }

    private static Bundle m1556a(ShareVideoContent shareVideoContent, boolean z) {
        ShareVideo h = shareVideoContent.m1707h();
        Bundle a = NativeDialogParameters.m1552a((ShareContent) shareVideoContent, z);
        Utility.m1114a(a, "TITLE", shareVideoContent.m1705f());
        Utility.m1114a(a, "DESCRIPTION", shareVideoContent.m1704e());
        Utility.m1113a(a, "VIDEO", h.m1701a());
        return a;
    }

    private static Bundle m1554a(ShareOpenGraphContent shareOpenGraphContent, JSONObject jSONObject, boolean z) {
        Bundle a = NativeDialogParameters.m1552a((ShareContent) shareOpenGraphContent, z);
        Utility.m1114a(a, "PREVIEW_PROPERTY_NAME", (String) ShareInternalUtility.m1613a(shareOpenGraphContent.m1670f()).second);
        Utility.m1114a(a, "ACTION_TYPE", shareOpenGraphContent.m1669e().m1666a());
        Utility.m1114a(a, "ACTION", !(jSONObject instanceof JSONObject) ? jSONObject.toString() : JSONObjectInstrumentation.toString(jSONObject));
        return a;
    }

    private static Bundle m1552a(ShareContent shareContent, boolean z) {
        Bundle bundle = new Bundle();
        Utility.m1113a(bundle, "LINK", shareContent.m1636a());
        Utility.m1114a(bundle, "PLACE", shareContent.m1638c());
        Utility.m1114a(bundle, "REF", shareContent.m1639d());
        bundle.putBoolean("DATA_FAILURES_FATAL", z);
        Collection b = shareContent.m1637b();
        if (!Utility.m1127a(b)) {
            bundle.putStringArrayList("FRIENDS", new ArrayList(b));
        }
        return bundle;
    }
}
