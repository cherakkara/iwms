package com.facebook.share.internal;

import android.app.Activity;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import com.facebook.p022b.AppCall;
import com.facebook.p022b.CallbackManagerImpl.CallbackManagerImpl;
import com.facebook.p022b.DialogFeature;
import com.facebook.p022b.DialogPresenter.DialogPresenter;
import com.facebook.p022b.FacebookDialogBase;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.facebook.share.internal.e */
public class LikeDialog extends FacebookDialogBase<LikeContent, Object> {
    private static final int f1210b;

    /* renamed from: com.facebook.share.internal.e.a */
    private class LikeDialog extends FacebookDialogBase.FacebookDialogBase {
        final /* synthetic */ LikeDialog f1208b;

        /* renamed from: com.facebook.share.internal.e.a.1 */
        class LikeDialog implements DialogPresenter {
            final /* synthetic */ LikeContent f1206a;
            final /* synthetic */ LikeDialog f1207b;

            LikeDialog(LikeDialog likeDialog, LikeContent likeContent) {
                this.f1207b = likeDialog;
                this.f1206a = likeContent;
            }

            public Bundle m1531a() {
                return LikeDialog.m1542b(this.f1206a);
            }

            public Bundle m1532b() {
                Log.e("LikeDialog", "Attempting to present the Like Dialog with an outdated Facebook app on the device");
                return new Bundle();
            }
        }

        private LikeDialog(LikeDialog likeDialog) {
            this.f1208b = likeDialog;
            super(likeDialog);
        }

        public boolean m1533a(LikeContent likeContent) {
            return likeContent != null && LikeDialog.m1543e();
        }

        public AppCall m1535b(LikeContent likeContent) {
            AppCall d = this.f1208b.m1548d();
            com.facebook.p022b.DialogPresenter.m918a(d, new LikeDialog(this, likeContent), LikeDialog.m1546h());
            return d;
        }
    }

    /* renamed from: com.facebook.share.internal.e.b */
    private class LikeDialog extends FacebookDialogBase.FacebookDialogBase {
        final /* synthetic */ LikeDialog f1209b;

        private LikeDialog(LikeDialog likeDialog) {
            this.f1209b = likeDialog;
            super(likeDialog);
        }

        public boolean m1537a(LikeContent likeContent) {
            return likeContent != null && LikeDialog.m1544f();
        }

        public AppCall m1539b(LikeContent likeContent) {
            AppCall d = this.f1209b.m1548d();
            com.facebook.p022b.DialogPresenter.m916a(d, LikeDialog.m1542b(likeContent), LikeDialog.m1546h());
            return d;
        }
    }

    static {
        f1210b = CallbackManagerImpl.Like.m908a();
    }

    public static boolean m1543e() {
        return VERSION.SDK_INT >= 14 && com.facebook.p022b.DialogPresenter.m921a(LikeDialog.m1546h());
    }

    public static boolean m1544f() {
        return VERSION.SDK_INT >= 14 && com.facebook.p022b.DialogPresenter.m924b(LikeDialog.m1546h());
    }

    LikeDialog(Activity activity) {
        super(activity, f1210b);
    }

    LikeDialog(Fragment fragment) {
        super(fragment, f1210b);
    }

    protected AppCall m1548d() {
        return new AppCall(m932a());
    }

    protected List<FacebookDialogBase.FacebookDialogBase> m1547c() {
        List arrayList = new ArrayList();
        arrayList.add(new LikeDialog());
        arrayList.add(new LikeDialog());
        return arrayList;
    }

    private static DialogFeature m1546h() {
        return LikeDialogFeature.LIKE_DIALOG;
    }

    private static Bundle m1542b(LikeContent likeContent) {
        Bundle bundle = new Bundle();
        bundle.putString("object_id", likeContent.m1425a());
        bundle.putString("object_type", likeContent.m1426b().toString());
        return bundle;
    }
}
