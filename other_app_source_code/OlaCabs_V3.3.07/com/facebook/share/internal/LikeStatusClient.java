package com.facebook.share.internal;

import android.content.Context;
import android.os.Bundle;
import com.facebook.p022b.PlatformServiceClient;

/* renamed from: com.facebook.share.internal.g */
final class LikeStatusClient extends PlatformServiceClient {
    private String f1214a;

    LikeStatusClient(Context context, String str, String str2) {
        super(context, 65542, 65543, 20141001, str);
        this.f1214a = str2;
    }

    protected void m1551a(Bundle bundle) {
        bundle.putString("com.facebook.platform.extra.OBJECT_ID", this.f1214a);
    }
}
