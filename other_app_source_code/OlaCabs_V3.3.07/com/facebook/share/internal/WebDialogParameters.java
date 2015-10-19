package com.facebook.share.internal;

import android.os.Bundle;
import com.facebook.FacebookException;
import com.facebook.p022b.Utility;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.ShareOpenGraphContent;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import com.olacabs.customer.utils.Constants;
import org.json.JSONObject;

/* renamed from: com.facebook.share.internal.n */
public class WebDialogParameters {
    public static Bundle m1623a(ShareLinkContent shareLinkContent) {
        Bundle bundle = new Bundle();
        Utility.m1113a(bundle, "href", shareLinkContent.m1636a());
        return bundle;
    }

    public static Bundle m1624a(ShareOpenGraphContent shareOpenGraphContent) {
        Bundle bundle = new Bundle();
        Utility.m1114a(bundle, "action_type", shareOpenGraphContent.m1669e().m1666a());
        try {
            JSONObject a = ShareInternalUtility.m1620a(ShareInternalUtility.m1618a(shareOpenGraphContent), false);
            if (a != null) {
                Utility.m1114a(bundle, "action_properties", !(a instanceof JSONObject) ? a.toString() : JSONObjectInstrumentation.toString(a));
            }
            return bundle;
        } catch (Throwable e) {
            throw new FacebookException("Unable to serialize the ShareOpenGraphContent to JSON", e);
        }
    }

    public static Bundle m1625b(ShareLinkContent shareLinkContent) {
        Bundle bundle = new Bundle();
        Utility.m1114a(bundle, Constants.BUNDLE_NAME, shareLinkContent.m1650f());
        Utility.m1114a(bundle, "description", shareLinkContent.m1649e());
        Utility.m1114a(bundle, "link", Utility.m1097a(shareLinkContent.m1636a()));
        Utility.m1114a(bundle, "picture", Utility.m1097a(shareLinkContent.m1651g()));
        return bundle;
    }
}
