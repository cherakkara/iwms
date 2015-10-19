package com.facebook.share.p024a;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import com.facebook.p022b.AppCall;
import com.facebook.p022b.CallbackManagerImpl.CallbackManagerImpl;
import com.facebook.p022b.DialogFeature;
import com.facebook.p022b.DialogPresenter;
import com.facebook.p022b.FacebookDialogBase;
import com.facebook.p023a.AppEventsLogger;
import com.facebook.share.internal.LegacyNativeDialogParameters;
import com.facebook.share.internal.NativeDialogParameters;
import com.facebook.share.internal.OpenGraphActionDialogFeature;
import com.facebook.share.internal.ShareContentValidation;
import com.facebook.share.internal.ShareDialogFeature;
import com.facebook.share.internal.ShareInternalUtility;
import com.facebook.share.internal.WebDialogParameters;
import com.facebook.share.model.ShareContent;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.ShareOpenGraphContent;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.model.ShareVideoContent;
import com.newrelic.agent.android.api.common.WanType;
import com.sothree.slidinguppanel.p086a.R.R;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.facebook.share.a.b */
public final class ShareDialog extends FacebookDialogBase<ShareContent, Object> {
    private static final int f1110b;
    private boolean f1111c;
    private boolean f1112d;

    /* renamed from: com.facebook.share.a.b.1 */
    static /* synthetic */ class ShareDialog {
        static final /* synthetic */ int[] f1097a;

        static {
            f1097a = new int[ShareDialog.values().length];
            try {
                f1097a[ShareDialog.AUTOMATIC.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f1097a[ShareDialog.WEB.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f1097a[ShareDialog.NATIVE.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
        }
    }

    /* renamed from: com.facebook.share.a.b.a */
    private class ShareDialog extends FacebookDialogBase.FacebookDialogBase {
        final /* synthetic */ ShareDialog f1098b;

        private ShareDialog(ShareDialog shareDialog) {
            this.f1098b = shareDialog;
            super(shareDialog);
        }

        public Object m1387a() {
            return ShareDialog.FEED;
        }

        public boolean m1388a(ShareContent shareContent) {
            return shareContent instanceof ShareLinkContent;
        }

        public AppCall m1390b(ShareContent shareContent) {
            this.f1098b.m1406a(this.f1098b.m935b(), shareContent, ShareDialog.FEED);
            ShareLinkContent shareLinkContent = (ShareLinkContent) shareContent;
            AppCall d = this.f1098b.m1418d();
            ShareContentValidation.m1593b(shareLinkContent);
            DialogPresenter.m920a(d, "feed", WebDialogParameters.m1625b(shareLinkContent));
            return d;
        }
    }

    /* renamed from: com.facebook.share.a.b.b */
    public enum ShareDialog {
        AUTOMATIC,
        NATIVE,
        WEB,
        FEED
    }

    /* renamed from: com.facebook.share.a.b.c */
    private class ShareDialog extends FacebookDialogBase.FacebookDialogBase {
        final /* synthetic */ ShareDialog f1108b;

        /* renamed from: com.facebook.share.a.b.c.1 */
        class ShareDialog implements DialogPresenter.DialogPresenter {
            final /* synthetic */ AppCall f1104a;
            final /* synthetic */ ShareContent f1105b;
            final /* synthetic */ boolean f1106c;
            final /* synthetic */ ShareDialog f1107d;

            ShareDialog(ShareDialog shareDialog, AppCall appCall, ShareContent shareContent, boolean z) {
                this.f1107d = shareDialog;
                this.f1104a = appCall;
                this.f1105b = shareContent;
                this.f1106c = z;
            }

            public Bundle m1392a() {
                return NativeDialogParameters.m1557a(this.f1104a.m879c(), this.f1105b, this.f1106c);
            }

            public Bundle m1393b() {
                return LegacyNativeDialogParameters.m1432a(this.f1104a.m879c(), this.f1105b, this.f1106c);
            }
        }

        private ShareDialog(ShareDialog shareDialog) {
            this.f1108b = shareDialog;
            super(shareDialog);
        }

        public Object m1394a() {
            return ShareDialog.NATIVE;
        }

        public boolean m1395a(ShareContent shareContent) {
            return shareContent != null && ShareDialog.m1414e(shareContent.getClass());
        }

        public AppCall m1397b(ShareContent shareContent) {
            this.f1108b.m1406a(this.f1108b.m935b(), shareContent, ShareDialog.NATIVE);
            ShareContentValidation.m1579a(shareContent);
            AppCall d = this.f1108b.m1418d();
            DialogPresenter.m918a(d, new ShareDialog(this, d, shareContent, this.f1108b.m1419e()), ShareDialog.m1416g(shareContent.getClass()));
            return d;
        }
    }

    /* renamed from: com.facebook.share.a.b.d */
    private class ShareDialog extends FacebookDialogBase.FacebookDialogBase {
        final /* synthetic */ ShareDialog f1109b;

        private ShareDialog(ShareDialog shareDialog) {
            this.f1109b = shareDialog;
            super(shareDialog);
        }

        public Object m1400a() {
            return ShareDialog.WEB;
        }

        public boolean m1401a(ShareContent shareContent) {
            return shareContent != null && ShareDialog.m1415f(shareContent.getClass());
        }

        public AppCall m1403b(ShareContent shareContent) {
            Bundle a;
            this.f1109b.m1406a(this.f1109b.m935b(), shareContent, ShareDialog.WEB);
            AppCall d = this.f1109b.m1418d();
            ShareContentValidation.m1593b(shareContent);
            if (shareContent instanceof ShareLinkContent) {
                a = WebDialogParameters.m1623a((ShareLinkContent) shareContent);
            } else {
                a = WebDialogParameters.m1624a((ShareOpenGraphContent) shareContent);
            }
            DialogPresenter.m920a(d, m1399c(shareContent), a);
            return d;
        }

        private String m1399c(ShareContent shareContent) {
            if (shareContent instanceof ShareLinkContent) {
                return "share";
            }
            if (shareContent instanceof ShareOpenGraphContent) {
                return "share_open_graph";
            }
            return null;
        }
    }

    static {
        f1110b = CallbackManagerImpl.Share.m908a();
    }

    public static boolean m1408a(Class<? extends ShareContent> cls) {
        return ShareDialog.m1415f(cls) || ShareDialog.m1414e(cls);
    }

    private static boolean m1414e(Class<? extends ShareContent> cls) {
        DialogFeature g = ShareDialog.m1416g(cls);
        return g != null && DialogPresenter.m921a(g);
    }

    private static boolean m1415f(Class<? extends ShareContent> cls) {
        return ShareLinkContent.class.isAssignableFrom(cls) || ShareOpenGraphContent.class.isAssignableFrom(cls);
    }

    public ShareDialog(Fragment fragment) {
        super(fragment, f1110b);
        this.f1111c = false;
        this.f1112d = true;
        ShareInternalUtility.m1621a(f1110b);
    }

    public boolean m1419e() {
        return this.f1111c;
    }

    protected AppCall m1418d() {
        return new AppCall(m932a());
    }

    protected List<FacebookDialogBase.FacebookDialogBase> m1417c() {
        List arrayList = new ArrayList();
        arrayList.add(new ShareDialog());
        arrayList.add(new ShareDialog());
        arrayList.add(new ShareDialog());
        return arrayList;
    }

    private static DialogFeature m1416g(Class<? extends ShareContent> cls) {
        if (ShareLinkContent.class.isAssignableFrom(cls)) {
            return ShareDialogFeature.SHARE_DIALOG;
        }
        if (SharePhotoContent.class.isAssignableFrom(cls)) {
            return ShareDialogFeature.PHOTOS;
        }
        if (ShareVideoContent.class.isAssignableFrom(cls)) {
            return ShareDialogFeature.VIDEO;
        }
        if (ShareOpenGraphContent.class.isAssignableFrom(cls)) {
            return OpenGraphActionDialogFeature.OG_ACTION_DIALOG;
        }
        return null;
    }

    private void m1406a(Context context, ShareContent shareContent, ShareDialog shareDialog) {
        String str;
        String str2;
        if (this.f1112d) {
            shareDialog = ShareDialog.AUTOMATIC;
        }
        switch (ShareDialog.f1097a[shareDialog.ordinal()]) {
            case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                str = "automatic";
                break;
            case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                str = "web";
                break;
            case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                str = "native";
                break;
            default:
                str = WanType.UNKNOWN;
                break;
        }
        DialogFeature g = ShareDialog.m1416g(shareContent.getClass());
        if (g == ShareDialogFeature.SHARE_DIALOG) {
            str2 = NotificationCompatApi21.CATEGORY_STATUS;
        } else if (g == ShareDialogFeature.PHOTOS) {
            str2 = "photo";
        } else if (g == ShareDialogFeature.VIDEO) {
            str2 = "video";
        } else if (g == OpenGraphActionDialogFeature.OG_ACTION_DIALOG) {
            str2 = "open_graph";
        } else {
            str2 = WanType.UNKNOWN;
        }
        AppEventsLogger a = AppEventsLogger.m830a(context);
        Bundle bundle = new Bundle();
        bundle.putString("fb_share_dialog_show", str);
        bundle.putString("fb_share_dialog_content_type", str2);
        a.m856a("fb_share_dialog_show", null, bundle);
    }
}
