package com.facebook.share.internal;

import android.graphics.Bitmap;
import android.net.Uri;
import com.facebook.FacebookException;
import com.facebook.p022b.Utility;
import com.facebook.share.model.ShareContent;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.ShareOpenGraphAction;
import com.facebook.share.model.ShareOpenGraphContent;
import com.facebook.share.model.ShareOpenGraphObject;
import com.facebook.share.model.ShareOpenGraphValueContainer;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.model.ShareVideo;
import com.facebook.share.model.ShareVideoContent;
import java.util.List;
import java.util.Locale;

/* renamed from: com.facebook.share.internal.k */
public class ShareContentValidation {
    private static ShareContentValidation f1219a;
    private static ShareContentValidation f1220b;

    /* renamed from: com.facebook.share.internal.k.a */
    private static class ShareContentValidation {
        private boolean f1218a;

        private ShareContentValidation() {
            this.f1218a = false;
        }

        public void m1565a(ShareLinkContent shareLinkContent) {
            ShareContentValidation.m1594b(shareLinkContent, this);
        }

        public void m1571a(SharePhotoContent sharePhotoContent) {
            ShareContentValidation.m1600b(sharePhotoContent, this);
        }

        public void m1573a(ShareVideoContent shareVideoContent) {
            ShareContentValidation.m1602b(shareVideoContent, this);
        }

        public void m1567a(ShareOpenGraphContent shareOpenGraphContent) {
            this.f1218a = true;
            ShareContentValidation.m1596b(shareOpenGraphContent, this);
        }

        public void m1566a(ShareOpenGraphAction shareOpenGraphAction) {
            ShareContentValidation.m1595b(shareOpenGraphAction, this);
        }

        public void m1568a(ShareOpenGraphObject shareOpenGraphObject) {
            ShareContentValidation.m1597b(shareOpenGraphObject, this);
        }

        public void m1569a(ShareOpenGraphValueContainer shareOpenGraphValueContainer, boolean z) {
            ShareContentValidation.m1598b(shareOpenGraphValueContainer, this, z);
        }

        public void m1570a(SharePhoto sharePhoto) {
            ShareContentValidation.m1603c(sharePhoto, this);
        }

        public void m1572a(ShareVideo shareVideo) {
            ShareContentValidation.m1601b(shareVideo, this);
        }

        public boolean m1574a() {
            return this.f1218a;
        }
    }

    /* renamed from: com.facebook.share.internal.k.b */
    private static class ShareContentValidation extends ShareContentValidation {
        private ShareContentValidation() {
            super();
        }

        public void m1576a(SharePhotoContent sharePhotoContent) {
            throw new FacebookException("Cannot share SharePhotoContent via web sharing dialogs");
        }

        public void m1577a(ShareVideoContent shareVideoContent) {
            throw new FacebookException("Cannot share ShareVideoContent via web sharing dialogs");
        }

        public void m1575a(SharePhoto sharePhoto) {
            ShareContentValidation.m1604d(sharePhoto, this);
        }
    }

    public static void m1579a(ShareContent shareContent) {
        ShareContentValidation.m1580a(shareContent, ShareContentValidation.m1578a());
    }

    public static void m1593b(ShareContent shareContent) {
        ShareContentValidation.m1580a(shareContent, ShareContentValidation.m1592b());
    }

    private static ShareContentValidation m1578a() {
        if (f1220b == null) {
            f1220b = new ShareContentValidation();
        }
        return f1220b;
    }

    private static ShareContentValidation m1592b() {
        if (f1219a == null) {
            f1219a = new ShareContentValidation();
        }
        return f1219a;
    }

    private static void m1580a(ShareContent shareContent, ShareContentValidation shareContentValidation) throws FacebookException {
        if (shareContent == null) {
            throw new FacebookException("Must provide non-null content to share");
        } else if (shareContent instanceof ShareLinkContent) {
            shareContentValidation.m1565a((ShareLinkContent) shareContent);
        } else if (shareContent instanceof SharePhotoContent) {
            shareContentValidation.m1571a((SharePhotoContent) shareContent);
        } else if (shareContent instanceof ShareVideoContent) {
            shareContentValidation.m1573a((ShareVideoContent) shareContent);
        } else if (shareContent instanceof ShareOpenGraphContent) {
            shareContentValidation.m1567a((ShareOpenGraphContent) shareContent);
        }
    }

    private static void m1594b(ShareLinkContent shareLinkContent, ShareContentValidation shareContentValidation) {
        Uri g = shareLinkContent.m1651g();
        if (g != null && !Utility.m1135b(g)) {
            throw new FacebookException("Image Url must be an http:// or https:// url");
        }
    }

    private static void m1600b(SharePhotoContent sharePhotoContent, ShareContentValidation shareContentValidation) {
        List<SharePhoto> e = sharePhotoContent.m1693e();
        if (e == null || e.isEmpty()) {
            throw new FacebookException("Must specify at least one Photo in SharePhotoContent.");
        } else if (e.size() > 6) {
            throw new FacebookException(String.format(Locale.ROOT, "Cannot add more than %d photos.", new Object[]{Integer.valueOf(6)}));
        } else {
            for (SharePhoto a : e) {
                shareContentValidation.m1570a(a);
            }
        }
    }

    private static void m1603c(SharePhoto sharePhoto, ShareContentValidation shareContentValidation) {
        if (sharePhoto == null) {
            throw new FacebookException("Cannot share a null SharePhoto");
        }
        Bitmap a = sharePhoto.m1688a();
        Uri b = sharePhoto.m1689b();
        if (a != null) {
            return;
        }
        if (b == null) {
            throw new FacebookException("SharePhoto does not have a Bitmap or ImageUrl specified");
        } else if (Utility.m1135b(b) && !shareContentValidation.m1574a()) {
            throw new FacebookException("Cannot set the ImageUrl of a SharePhoto to the Uri of an image on the web when sharing SharePhotoContent");
        }
    }

    private static void m1604d(SharePhoto sharePhoto, ShareContentValidation shareContentValidation) {
        if (sharePhoto == null) {
            throw new FacebookException("Cannot share a null SharePhoto");
        }
        Uri b = sharePhoto.m1689b();
        if (b == null || !Utility.m1135b(b)) {
            throw new FacebookException("SharePhoto must have a non-null imageUrl set to the Uri of an image on the web");
        }
    }

    private static void m1602b(ShareVideoContent shareVideoContent, ShareContentValidation shareContentValidation) {
        shareContentValidation.m1572a(shareVideoContent.m1707h());
        SharePhoto g = shareVideoContent.m1706g();
        if (g != null) {
            shareContentValidation.m1570a(g);
        }
    }

    private static void m1601b(ShareVideo shareVideo, ShareContentValidation shareContentValidation) {
        if (shareVideo == null) {
            throw new FacebookException("Cannot share a null ShareVideo");
        } else if (shareVideo.m1701a() == null) {
            throw new FacebookException("ShareVideo does not have a LocalUrl specified");
        }
    }

    private static void m1596b(ShareOpenGraphContent shareOpenGraphContent, ShareContentValidation shareContentValidation) {
        shareContentValidation.m1566a(shareOpenGraphContent.m1669e());
        String f = shareOpenGraphContent.m1670f();
        if (Utility.m1126a(f)) {
            throw new FacebookException("Must specify a previewPropertyName.");
        } else if (shareOpenGraphContent.m1669e().m1662a(f) == null) {
            throw new FacebookException("Property \"" + f + "\" was not found on the action. " + "The name of the preview property must match the name of an " + "action property.");
        }
    }

    private static void m1595b(ShareOpenGraphAction shareOpenGraphAction, ShareContentValidation shareContentValidation) {
        if (shareOpenGraphAction == null) {
            throw new FacebookException("Must specify a non-null ShareOpenGraphAction");
        } else if (Utility.m1126a(shareOpenGraphAction.m1666a())) {
            throw new FacebookException("ShareOpenGraphAction must have a non-empty actionType");
        } else {
            shareContentValidation.m1569a(shareOpenGraphAction, false);
        }
    }

    private static void m1597b(ShareOpenGraphObject shareOpenGraphObject, ShareContentValidation shareContentValidation) {
        if (shareOpenGraphObject == null) {
            throw new FacebookException("Cannot share a null ShareOpenGraphObject");
        }
        shareContentValidation.m1569a(shareOpenGraphObject, true);
    }

    private static void m1598b(ShareOpenGraphValueContainer shareOpenGraphValueContainer, ShareContentValidation shareContentValidation, boolean z) {
        for (String str : shareOpenGraphValueContainer.m1665c()) {
            ShareContentValidation.m1591a(str, z);
            Object a = shareOpenGraphValueContainer.m1662a(str);
            if (a instanceof List) {
                for (Object next : (List) a) {
                    if (next == null) {
                        throw new FacebookException("Cannot put null objects in Lists in ShareOpenGraphObjects and ShareOpenGraphActions");
                    }
                    ShareContentValidation.m1590a(next, shareContentValidation);
                }
                continue;
            } else {
                ShareContentValidation.m1590a(a, shareContentValidation);
            }
        }
    }

    private static void m1591a(String str, boolean z) {
        if (z) {
            String[] split = str.split(":");
            if (split.length < 2) {
                throw new FacebookException("Open Graph keys must be namespaced: %s", str);
            }
            for (String isEmpty : split) {
                if (isEmpty.isEmpty()) {
                    throw new FacebookException("Invalid key found in Open Graph dictionary: %s", str);
                }
            }
        }
    }

    private static void m1590a(Object obj, ShareContentValidation shareContentValidation) {
        if (obj instanceof ShareOpenGraphObject) {
            shareContentValidation.m1568a((ShareOpenGraphObject) obj);
        } else if (obj instanceof SharePhoto) {
            shareContentValidation.m1570a((SharePhoto) obj);
        }
    }
}
